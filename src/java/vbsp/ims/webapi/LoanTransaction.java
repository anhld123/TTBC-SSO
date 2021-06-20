/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.webapi;

import java.math.BigDecimal;

/**
 *
 * @author BAOANH
 */
public class LoanTransaction {
    //<editor-fold defaultstate="collapsed" desc="variable">
    
    private String PosCode;
    
    private String GroupId;
    
    private String GroupName;
    
    private String MassOrg;
    
    private String ProductDesc;
    
    private String Cif_No;
    
    private String CustName;
    
    private String CustAddress;
    
    private String LoanId;
    
    private String RefNo;
    
    private String TxnType;
    
    private String TxnDate;
    
    private BigDecimal DisbAmount;
    
    private BigDecimal PrinPaid;
    
    private BigDecimal IntPaid;
    
    private BigDecimal IntRate;
    
    private BigDecimal PrinOS;
    
    private String AuthId;
    
    private String MakerId;
    
    private String MakerDt;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="get/set">
    public String getPosCode() {
        return PosCode;
    }
    
    public void setPosCode(String PosCode) {
        this.PosCode = PosCode;
    }
    
    public String getGroupId() {
        return GroupId;
    }
    
    public void setGroupId(String GroupId) {
        this.GroupId = GroupId;
    }
    
    public String getGroupName() {
        return GroupName;
    }
    
    public void setGroupName(String GroupName) {
        this.GroupName = GroupName;
    }
    
    public String getMassOrg() {
        return MassOrg;
    }
    
    public void setMassOrg(String MassOrg) {
        this.MassOrg = MassOrg;
    }
    
    public String getProductDesc() {
        return ProductDesc;
    }
    
    public void setProductDesc(String ProductDesc) {
        this.ProductDesc = ProductDesc;
    }
    
    public String getCif_No() {
        return Cif_No;
    }
    
    public void setCif_No(String Cif_No) {
        this.Cif_No = Cif_No;
    }
    
    public String getCustName() {
        return CustName;
    }
    
    public void setCustName(String CustName) {
        this.CustName = CustName;
    }
    
    public String getCustAddress() {
        return CustAddress;
    }
    
    public void setCustAddress(String CustAddress) {
        this.CustAddress = CustAddress;
    }
    
    public String getLoanId() {
        return LoanId;
    }
    
    public void setLoanId(String LoanId) {
        this.LoanId = LoanId;
    }
    
    public String getRefNo() {
        return RefNo;
    }
    
    public void setRefNo(String RefNo) {
        this.RefNo = RefNo;
    }
    
    public String getTxnType() {
        return TxnType;
    }
    
    public void setTxnType(String TxnType) {
        this.TxnType = TxnType;
    }
    
    public String getTxnDate() {
        return TxnDate;
    }
    
    public void setTxnDate(String TxnDate) {
        this.TxnDate = TxnDate;
    }
    
    public BigDecimal getDisbAmount() {
        return DisbAmount;
    }
    
    public void setDisbAmount(BigDecimal DisbAmount) {
        this.DisbAmount = DisbAmount;
    }
    
    public BigDecimal getPrinPaid() {
        return PrinPaid;
    }
    
    public void setPrinPaid(BigDecimal PrinPaid) {
        this.PrinPaid = PrinPaid;
    }
    
    public BigDecimal getIntPaid() {
        return IntPaid;
    }
    
    public void setIntPaid(BigDecimal IntPaid) {
        this.IntPaid = IntPaid;
    }
    
    public BigDecimal getIntRate() {
        return IntRate;
    }
    
    public void setIntRate(BigDecimal IntRate) {
        this.IntRate = IntRate;
    }
    
    public BigDecimal getPrinOS() {
        return PrinOS;
    }
    
    public void setPrinOS(BigDecimal PrinOS) {
        this.PrinOS = PrinOS;
    }
    
    public String getAuthId() {
        return AuthId;
    }
    
    public void setAuthId(String AuthId) {
        this.AuthId = AuthId;
    }
    
    public String getMakerId() {
        return MakerId;
    }
    
    public void setMakerId(String MakerId) {
        this.MakerId = MakerId;
    }
    
    public String getMakerDt() {
        return MakerDt;
    }
    
    public void setMakerDt(String MakerDt) {
        this.MakerDt = MakerDt;
    }

//</editor-fold>
}
