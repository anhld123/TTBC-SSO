/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.ktgs.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import java.util.Date;
import java.util.HashMap;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.define.GenericResult;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.tdnn.DaoTdnnMain;

/**
 *
 * @author HP
 */
public class UNLOCK_KTKSNB extends ActionKtgsMain implements KtgsFunction {

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
            String skhoa = hmParameter.get("skhoa").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();
            lstDulieuNt = daoMain.getData_Unlock99(conn, sngaybc, skhoa);
            System.out.println(sngaybc + " " + skhoa);
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

        return SUCCESS;
    }

    public String cancelAssign() {
        try {
            String D1 = ServletActionContext.getRequest().getParameter("madiemgd");
            String D7 = ServletActionContext.getRequest().getParameter("ssngaybc");
            String D8 = ServletActionContext.getRequest().getParameter("ssngaybc");
            String D9 = ServletActionContext.getRequest().getParameter("ssngaybc");

            System.out.println(D1 + D7 + D8 + D9);
            DaoKtgsMain daoMain = new DaoKtgsMain();
            GenericResult<String> _result = daoMain.unlock_c3_ktksnb(D1, D7, D8, D9);

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
