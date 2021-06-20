/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.dao.MainReportPageMngDao;
import vbsp.ims.dao.ReportPrivilegeDao;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ReportMenuManager extends CoreLogger{

    private static List<ListValue> reportGroupList;
    private static List<ReportListValue> reportList;
    private static List<ReportPrivilege> privilege;

    private static int menuId;
    private static int reportGrade = 1;
    private static String userName;
    private static String userGroup;

    static {
        reportGroupList = new ArrayList<>();
        reportList = new ArrayList<>();
        privilege = new ArrayList<>();
    }

    public static void build() {
        MainReportPageMngDao rptPageMngDao = new MainReportPageMngDao(menuId);
        reportGroupList = rptPageMngDao.getListOfReportGroup();
        reportList = rptPageMngDao.getListOfReport();
        ReportPrivilegeDao privilegeDao = new ReportPrivilegeDao();
        privilege = privilegeDao.getPrivilege();
        userGroup = IMSRptDao.getUserGroup(userName);
    }

    public static void build(int pvMenuId, String groupId) {
        try {
            menuId = pvMenuId;
            userGroup = IMSRptDao.getUserGroup(userName);
            
            MainReportPageMngDao rptPageMngDao = new MainReportPageMngDao(menuId);
            reportGroupList = rptPageMngDao.getListOfReportGroup();

            if (groupId != null
                    && !groupId.isEmpty()) {
                reportList = rptPageMngDao.getListOfReport_Group(groupId,userGroup);
            } else {
                if (reportGroupList != null || reportGroupList.size() > 0) {
                    groupId = reportGroupList.get(0).sKey;
                    reportList = rptPageMngDao.getListOfReport_Group(groupId,userGroup);
                } else {
                    reportList = rptPageMngDao.getListOfReport();
                }
            }
            ReportPrivilegeDao privilegeDao = new ReportPrivilegeDao();
            privilege = privilegeDao.getPrivilege();
            
        } catch (Exception e) {
             System.err.println(e.getMessage());
        }

    }

    //--------------------------------------------------------------------------
    public static List<ListValue> getReportGroupList() {
        return reportGroupList;
    }

    //--------------------------------------------------------------------------
    public static List<ListValue> getReportList() {
        ArrayList<ListValue> lcReportList = new ArrayList();
        ReportPrivilege pri = getPrivilege();

        if (pri != null) {
            if ("0".equals(pri.getPrivilege().substring(1, 1)) && pri.getException().isEmpty()) {
            } else {
                if ("0".equals(pri.getPrivilege().substring(1, 1)) && !pri.getException().isEmpty()) {
                    for (ReportListValue listValue : reportList) {
                        if (pri.getException().contains(listValue.getReportListValue().getsKey())) {
                            lcReportList.add(listValue.getReportListValue());
                        }
                    }
                } else {
                    for (ReportListValue listValue : reportList) {
                        if ("2".equals(pri.getPrivilege().substring(1, 1))) {
                            lcReportList.add(listValue.getReportListValue());
                        } else {
                            if (listValue.getReportGrade().contains(String.valueOf(reportGrade).trim())) {
                                lcReportList.add(listValue.getReportListValue());
                            }
                        }
                    }
                }
            }
        } else {
            for (ReportListValue listValue : reportList) {
                lcReportList.add(listValue.getReportListValue());
            }
        }
        return lcReportList;
    }

    //--------------------------------------------------------------------------
    private static ReportPrivilege getPrivilege() {
        for (ReportPrivilege pri : privilege) {
            if (pri.getUserGroup().trim().equals(userGroup)
                    && pri.getReportGroup().trim().equals(reportGroupList.get(0).getsKey().trim())) {
                return pri;
            }
        }
        return null;
    }

    //--------------------------------------------------------------------------
    public static int getMenuId() {
        return menuId;
    }

    public static void setMenuId(int menuId) {
        ReportMenuManager.menuId = menuId;
    }

    public static String getUserName() {
        return userName;
    }

    public static void setUserName(String userName) {
        ReportMenuManager.userName = userName;
    }

    public static int getReportGrade() {
        return reportGrade;
    }

    public static void setReportGrade(int reportGrade) {
        ReportMenuManager.reportGrade = reportGrade;
    }
    //-------------------------------------------------------------------------- 
}
