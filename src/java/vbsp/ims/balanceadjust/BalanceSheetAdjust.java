/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.balanceadjust;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.DefineFun;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class BalanceSheetAdjust
        extends ActionSupport
        implements ServletRequestAware, ModelDriven {

    private static final long serialVersionUID = 6518221459701336965L;
    HttpServletRequest request;

//    private List<ListValue> posList = new ArrayList<>();
    private List<ListValue> accountList = new ArrayList<>();
    private String pos_cd;
    private String reportDate;
    private String period;
    private String acc_type;
    private String account;
    private String userName;
    private String conflag;
    private String message;

    private AdjustAccount adjustAcc;

    @Override
    public Object getModel() {
        return new AdjustAccount();
    }

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {
        try {
            if (userName == null
                    || userName.isEmpty()) {
                userName = request.getSession().getAttribute("username").toString();
            }
        } catch (Exception e) {
            System.err.println("exe~" + e.getMessage());
        }

        return SUCCESS;
    }

    public String listAccPos() {

        System.err.println("listAccPos" + acc_type);

        adjustAcc = new AdjustAccount();

        //adjustAcc.setTk("");
        adjustAcc.setKybc(period);

        if (conflag != null && conflag.equals("on")) {
            adjustAcc.setTonghop("Y");
        } else {
            adjustAcc.setTonghop("N");
        }

        adjustAcc.setNgaybc(
                DefineFun.convert2OracleDateFormat(reportDate)
        );

        adjustAcc.setMapos(pos_cd);

        return SUCCESS;
    }

    public String fetchData() {

        System.err.println("fetchData" + adjustAcc.toString());
//        message = "fetchData" +adjustAcc.getTk();
        AdjustAccountDao dao = new AdjustAccountDao();
        dao.getAccountValue(adjustAcc, userName);

        return SUCCESS;
    }

    public String adjustData() {
        System.err.println("Dieu chinh TK can doi:" + userName);
        AdjustAccountDao dao = new AdjustAccountDao();
        String resultStr = dao.setAccountValue(adjustAcc, userName);
        message = "<xanh> Cập nhật thông tin:" + userName + "~" + adjustAcc.toString()+"~"+resultStr+"<xanh>";
        return SUCCESS;
    }

    public List<ListValue> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<ListValue> accountList) {
        this.accountList = accountList;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getAcc_type() {
        return acc_type;
    }

    public void setAcc_type(String acc_type) {
        this.acc_type = acc_type;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public AdjustAccount getAdjustAcc() {
        return adjustAcc;
    }

    public void setAdjustAcc(AdjustAccount adjustAcc) {
        this.adjustAcc = adjustAcc;
    }

    public String getConflag() {
        return conflag;
    }

    public void setConflag(String conflag) {
        this.conflag = conflag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
