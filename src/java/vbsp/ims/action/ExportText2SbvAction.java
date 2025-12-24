/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.ExportText2SbvDao;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.define.DefineFun;
import vbsp.ims.model.DownloadFileInfor;
import vbsp.ims.model.ExportText2SbvManager;
import vbsp.ims.model.Indicator;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class ExportText2SbvAction extends ActionSupport
        implements ServletRequestAware {

    //--------------------------------------------------------------------------
    private static final long serialVersionUID = 5078264277068533593L;
    //--------------------------------------------------------------------------
    private List<ListValue> lstRptGroupObj;
    private List<ListValue> lstRptPeriod;
    private String selectedReport;
    private String reportDate;
    private String selectedPos;
    private String message;
    private String userName;
    private int reportGrade;
    private String consolidateFlag;
    private String reportPeriod;
    private List<String> lstOfFile;
    private String fileNamelocal;
    private String filereport;
    private List<DownloadFileInfor> filesList = new ArrayList<>();
    private List<Indicator> sendIndiList = new ArrayList<>();
    private String sbvSendIndiGroup = "";
    private boolean send2sbv = false;
    private boolean send9acc = false;
    protected List<ListValue> lstPara = new ArrayList<ListValue>();
    HttpServletRequest request;
    ExportText2SbvManager export = new ExportText2SbvManager();

    public List<ListValue> getLstPara() {
        return lstPara;
    }

    public void setLstPara(List<ListValue> lstPara) {
        this.lstPara = lstPara;
    }

    //--------------------------------------------------------------------------
    public String buildReportGroupCombo() {

        reportGrade = Integer.parseInt(
                request.getSession().getAttribute("reportGrade").toString());
        userName = request.getSession().getAttribute("username").toString();

        ExportText2SbvManager exporter = new ExportText2SbvManager();

        this.lstRptGroupObj = exporter.getLstRptGroupObj(userName, reportGrade);

        System.err.println("selectedReport -->" + selectedReport);

        if (selectedReport == null || selectedReport.isEmpty()) {
            lstRptPeriod = exporter.getLstRptPeriod("ALL");
        } else {
            lstRptPeriod = exporter.getLstRptPeriod(selectedReport);
        }
        return "success";
    }

    public String buildPara() {

        DaoNghiquyet11cp daoMain11 = new DaoNghiquyet11cp();
        lstPara = daoMain11.getDanhMuc("", "PCRT", "");
        System.out.println("lstPara= " +lstPara);
        return "success";
    }

    public String buildReportPeridoCombo() {
        ExportText2SbvManager exporter = new ExportText2SbvManager();
        lstRptPeriod = exporter.getLstRptPeriod("ALL");
        return "success";
    }

    //--------------------------------------------------------------------------
    public String exportFile() throws Exception {
        if (!selectedReport.equals("EX050001") && (reportPeriod == null || selectedReport == null
                || reportPeriod.isEmpty() || selectedReport.isEmpty()
                || reportPeriod.trim().equals("NULL"))) {
            this.message = "(*) Bạn chưa chọn báo cáo hoặc kỳ báo cáo.";
            return "error";
        } else {
            String strDateExport = "";
            String gennew = "";
            try {
                strDateExport = new SimpleDateFormat("dd-MMM-yyyy").format(
                        new SimpleDateFormat("dd/MM/yyyy").parse(reportDate));
            } catch (ParseException ex) {
                Logger.getLogger(ExportText2SbvAction.class.getName()).log(Level.SEVERE, null, ex);
            }
            gennew = request.getParameter("gennew");
//            System.err.println(gennew);
            if ((gennew == null || gennew.trim().equals("Y")) && !selectedReport.equals("EX050001")) {
                String lstOfPos = getListOfPos();
                if (export.exportTextFile(selectedReport, lstOfPos, strDateExport,
                        consolidateFlag, reportPeriod, sbvSendIndiGroup, send2sbv, send9acc)) {
                    System.err.println(sbvSendIndiGroup);
                    lstOfFile = ExportText2SbvManager.getLstOfTextFile();
                    filesList = ExportText2SbvManager.getFilesList();
                    filereport = ExportText2SbvManager.getZipFileList().get(0);
                    fileNamelocal = ExportText2SbvManager.getZipFileList().get(1);
                } else {
                    this.message = "(*) Xuất dữ liệu báo cáo " + selectedReport + " ngày: "
                            + strDateExport + " cho đơn vị: " + lstOfPos + " thất bại.";
                    return "error";
                }
            } else {
                if (selectedReport.equals("EX050001")) {
                    String txtGetData = request.getParameter("txtGetData");
                    System.out.println("txtGetData== " +txtGetData);
                    export.exportTextFile(selectedReport, txtGetData, strDateExport,
                            consolidateFlag, reportPeriod, sbvSendIndiGroup, send2sbv, send9acc);
                    lstOfFile = ExportText2SbvManager.getLstOfTextFile();
                    filesList = ExportText2SbvManager.getFilesList();
                    filereport = ExportText2SbvManager.getZipFileList().get(0);
                    fileNamelocal = ExportText2SbvManager.getZipFileList().get(1);
                } else {
                    lstOfFile = ExportText2SbvManager.getLstOfTextFile();
                    filesList = ExportText2SbvManager.getFilesList();
                    filereport = ExportText2SbvManager.getZipFileList().get(0);
                    fileNamelocal = ExportText2SbvManager.getZipFileList().get(1);
                }
            }

            System.err.println("genfile ~" + filesList.size());

            for (int i = 1; i <= filesList.size(); i++) {
                DownloadFileInfor downloadfile = (DownloadFileInfor) filesList.get(i - 1);
                System.err.println(downloadfile.getFileName() + "~" + downloadfile.getFilePath());
            }

            System.err.println("success ~" + filesList.size());
            return "success";
        }
    }

    public String selectIndicator() {
        userName = request.getSession().getAttribute("username").toString();
        ExportText2SbvDao exporter = new ExportText2SbvDao();
        sendIndiList = exporter.getIndicatorList(1, Integer.parseInt(reportPeriod));
        return "success";
    }

    //--------------------------------------------------------------------------
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

//    public HttpServletRequest getServletRequest() {
//        return this.request;
//    }
    //--------------------------------------------------------------------------
    public List<ListValue> getLstRptGroupObj() {
        return lstRptGroupObj;
    }

    public void setLstRptGroupObj(List<ListValue> lstRptGroupObj) {
        this.lstRptGroupObj = lstRptGroupObj;
    }

    public String getSelectedReport() {
        return selectedReport;
    }

    public void setSelectedReport(String selectedReport) {
        this.selectedReport = selectedReport;
    }

    public String getMessage() {
        return message;
    }

    public String getSbvSendIndiGroup() {
        return sbvSendIndiGroup;
    }

    public void setSbvSendIndiGroup(String sbvSendIndiGroup) {
        this.sbvSendIndiGroup = sbvSendIndiGroup;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReportDate() {
        return reportDate;
    }

    public String getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(String selectedPos) {
        this.selectedPos = selectedPos;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getReportPeriod() {
        return reportPeriod;
    }

    public List<Indicator> getSendIndiList() {
        return sendIndiList;
    }

    public void setSendIndiList(List<Indicator> sendIndiList) {
        this.sendIndiList = sendIndiList;
    }

    public void setReportPeriod(String reportPeriod) {
        this.reportPeriod = reportPeriod;
    }

    public List<ListValue> getLstRptPeriod() {
        return lstRptPeriod;
    }

    public void setLstRptPeriod(List<ListValue> lstRptPeriod) {
        this.lstRptPeriod = lstRptPeriod;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public boolean getSend2sbv() {
        return send2sbv;
    }

    public void setSend2sbv(boolean send2sbv) {
        this.send2sbv = send2sbv;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public List<DownloadFileInfor> getFilesList() {
        return filesList;
    }

    public boolean isSend9acc() {
        return send9acc;
    }

    public void setSend9acc(boolean send9acc) {
        this.send9acc = send9acc;
    }

    public void setFilesList(List<DownloadFileInfor> filesList) {
        this.filesList = filesList;
    }

    //------------------------------------------------------------------------------------------
    private String getListOfPos() {
        String posString = "";
        userName = request.getSession().getAttribute("username").toString();
        reportGrade = Integer.parseInt(request.getSession().getAttribute("reportGrade").toString());
        if (selectedPos == null || selectedPos.trim().isEmpty()) {
            switch (reportGrade) {
                case 3:
                    posString = "000100";
                    this.consolidateFlag = "Y";
                    break;
                case 2:
                    posString = IMSRptDao.getPosOfUser(userName);
                    this.consolidateFlag = "Y";
                    break;
                default:
                    posString = IMSRptDao.getPosOfUser(userName);
                    this.consolidateFlag = "N";
                    break;
            }
        } else {
            ArrayList<String> pos_stack = new ArrayList<>();
            ArrayList<String> listOfId
                    = (ArrayList<String>) DefineFun.string2Array(selectedPos, ",", 1);
            boolean isAdded;
            String pos_cd;
            if (listOfId.size() > 0) {
                for (String id : listOfId) {
                    isAdded = false;
                    pos_cd = IMSRptDao.getPosbyTreeId(Integer.parseInt(id));
                    for (String added_pos : pos_stack) {
                        if (added_pos.equals(pos_cd)) {
                            isAdded = true;
                            break;
                        }
                    }
                    if (!isAdded && pos_cd != null && !"999999".equals(pos_cd)) {
                        posString += pos_cd + ",";
                        pos_stack.add(pos_cd);
                    }
                }
            }
            posString = posString.substring(0, posString.length() - 1);
            if (reportGrade == 3) {
                this.consolidateFlag = "Y";
            } else {
                this.consolidateFlag = "N";
            }
        }
        return posString;
    }

    //--------------------------------------------------------------------------
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

}
