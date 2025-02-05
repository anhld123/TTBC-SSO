/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tdnn;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import java.util.Date;
import java.util.HashMap;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.define.GenericResult;

/**
 *
 * @author HP
 */
public class Unlock_Time_GDX extends ActionTdnnMain implements TdnnFunction {

    private String status;
    private String message;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            String sngaybc = hmParameter.get("ngay_bc").toString();
            ActionContext.getContext().getSession().put("sngaybc", sngaybc);
//            String smacn = hmParameter.get("lstCN").toString();
//            String skhoa = hmParameter.get("skhoa").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoTdnnMain daoMain = new DaoTdnnMain();
            lstDulieuNt = daoMain.getData_UnlocTimekGdx(conn, sngaybc, "", "");
//            System.out.println(sngaybc +" " + skhoa +" " + smacn);
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> gdx: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String save() {
        System.err.println("Save - TDNN - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            DaoTdnnMain daoMain = DaoTdnnMain.newInstance();
            HashMap hmParameter = getParameter();
            if (!daoMain.saveTdnn01(khoa_tdnn, UserName, "", hmParameter.get("ngay_bc").toString(), lstDulieuNt, hmParameter.get("cap_kt").toString())) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_TDNN_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_TDNN_01: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String cancelAssign() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D4 = ServletActionContext.getRequest().getParameter("ssngaybc");
            String D5 = ServletActionContext.getRequest().getParameter("skye");
            SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MMM/yyyy");
            Date date = inputFormat.parse(D4);
            String formattedDate = outputFormat.format(date);
            
            String sngaybc = (String) ActionContext.getContext().getSession().get("sngaybc");
            System.out.println("d1= " + D1 +" D4= " + D4 +" D5= " +D5 +" formattedDate= " + sngaybc);
            DaoTdnnMain daoMain = new DaoTdnnMain();
            GenericResult<String> _result = daoMain.unlock_c3_THTK("TDNN",D1,"", sngaybc,D5);

            if (_result.isIsSuccess()) {
                status = "1";
                message = "";
            } else {
                status = "0";
                message = _result.getMessage();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> cancelAssign: " + e.getMessage());
            status = "0";
            message = e.getMessage();
        }
        return SUCCESS;
    }
}
