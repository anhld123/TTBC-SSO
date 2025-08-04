package vbsp.ims.restapi;

import java.util.List;

public class PLNRequestBody {

    private String posCode;
    private String posFlag;
    private List<String> lstSubPos;
    private String reportDate;
    private String massOrg;
    private String groupId;
    private String customerId;
    private String status;
    private String capitalSource;
    private String loanProgram;
    private String loanId;
    private int pageSize;
    private int startRow;
    private int endRow;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public String getPosFlag() {
        return posFlag;
    }

    public void setPosFlag(String posFlag) {
        this.posFlag = posFlag;
    }

    public List<String> getLstSubPos() {
        return lstSubPos;
    }

    public void setLstSubPos(List<String> lstSubPos) {
        this.lstSubPos = lstSubPos;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getMassOrg() {
        return massOrg;
    }

    public void setMassOrg(String massOrg) {
        this.massOrg = massOrg;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCapitalSource() {
        return capitalSource;
    }

    public void setCapitalSource(String capitalSource) {
        this.capitalSource = capitalSource;
    }

    public String getLoanProgram() {
        return loanProgram;
    }

    public void setLoanProgram(String loanProgram) {
        this.loanProgram = loanProgram;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

}
