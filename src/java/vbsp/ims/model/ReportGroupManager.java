/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.util.ArrayList;
import java.util.List;
import vbsp.ims.dao.IMSRptDao;

/**
 *
 * @author Trung
 */
public class ReportGroupManager {

    private static List<ReportGroup> reportGroups;
    private static String reportGroupCode;
    private static String updateType;

    static {
        updateType = "01";
        reportGroups = new ArrayList<>();    
        reportGroups = IMSRptDao.getReportGroups();
    }

    public static List<ReportGroup> getReportGroups() {
        reportGroups = IMSRptDao.getReportGroups();
        return reportGroups;
    }

    public static void create(ReportGroup reportGroup) {
        reportGroups.add(reportGroup);        
        // Cập nhật vào database
        IMSRptDao.addReportGroup(reportGroup);
    }

    public static ReportGroup find(String reportGroupCode) {
        for (ReportGroup reportGroup : reportGroups) {
            if (reportGroup.getPriGroupCode().equals(reportGroupCode)) {
                System.out.println("found");
                return reportGroup;
            }
        }
        return null;
    }

    public static void update(ReportGroup reportGroup) {
        String lcUserGroupCode = reportGroup.getPriGroupCode();
        for (ReportGroup rptGroup : reportGroups) {
            if (rptGroup.getPriGroupCode().equals(lcUserGroupCode)) {
                rptGroup.setPriGroupDesc(reportGroup.getPriGroupDesc());
                rptGroup.setPriGroupAlias(reportGroup.getPriGroupAlias());
                rptGroup.setPriGroupType(reportGroup.getPriGroupType());
                rptGroup.setPriGroupStatus(reportGroup.getPriGroupStatus());
                // Cập nhật vào Database
                IMSRptDao.updateReportGroup(reportGroup);                
                break;
            }
        }        
    }
    public static void delete(String reportGroupCode) {
        for (ReportGroup reportGroup : reportGroups) {
            if (reportGroup.getPriGroupCode().equals(reportGroupCode)) {
                reportGroups.remove(reportGroup);
                // Cap nhat vao database
                IMSRptDao.deleteReportGroup(reportGroupCode);                
                break;
            }
        }
    }        
    //--------------------------------------------------------------------------
    public static String getUpdateType() {
        return updateType;
    }

    public static void setUpdateType(String updateType) {
        ReportGroupManager.updateType = updateType;
    }
    //--------------------------------------------------------------------------    
}
