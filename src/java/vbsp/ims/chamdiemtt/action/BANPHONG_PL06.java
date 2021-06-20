/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author BAOANH
 */
public class BANPHONG_PL06 extends ActionChamdiemttMain implements CdttFunction {

    @Override
    public String load() {
        try {
            System.err.println("BANPHONG_PL06");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            System.err.println("BANPHONG_PL06");
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

}
