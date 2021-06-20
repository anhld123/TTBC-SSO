/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Trung
 */
public class NHAPTAYCN_01 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
        try {
            System.err.println("NT - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            //khoi tao cho treeview cac pos
            lstAllNhaptaycn = daoMain.getDmKhac(conn, "56");
//            lstDulieuNt = daoMain.getDataNhaptaycn_01(conn, khoa_nhaptaycn, "",UserName, Grade,poscd);
            
            if (pagination.getStart() == 0) {
                int nCountCust = 1000;
//                        daoDcptNo.getCountTotalCustData(conn, sUserName, reportGrade,
//                        sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,trangthai);
                int size=(int) Math.ceil((double)nCountCust / 10);
                pagination.setPage_size(size*10);
                pagination.setPreperties(nCountCust);
                
            }
//            setLstModelDcptNo(daoDcptNo.getDataCust(conn, sUserName, reportGrade, sNgaySl,
//                    poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh,trangthai,
//                    pagination.getStart() + 1, pagination.getStart() + pagination.getEnd()));
            String s = hmParameter.get("user_id").toString();
            
            totalDataView = daoMain.loadDataTotal(khoa_nhaptaycn,  hmParameter.get("user_id").toString(), UserName, poscd);
            lstDulieuNt = daoMain.getDataNhaptaycn_01(conn, khoa_nhaptaycn, "",UserName, Grade,poscd,pagination.getStart() + 1, pagination.getStart() + pagination.getEnd(),hmParameter.get("user_id").toString());

            pagination.setPage_records(lstDulieuNt.size());
            
            setLstNgnhanDm(daoMain.getNguyennhanDm());
//            setLstViewTotalCust(daoDcptNo.getViewTotalCustData(conn, sUserName, reportGrade,
//                    sNgaySl, poscd, dvut_dcpt, totruong_dcpt, nguon_von, chuongtrinh, trangthai));
            
            if (conn != null) {
                conn.close();
            }
            System.err.println("Goi bao cao quyet toan Nhaptaycn - 01 khoa_nhaptaycn=" + khoa_nhaptaycn);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> Nhaptaycn_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> Nhaptaycn_01: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){        
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - Nhaptaycn - 01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoNhaptaycnMain daoMain = DaoNhaptaycnMain.newInstance();
            HashMap hmParameter = getParameter();
            
            List<QT_DULIEU_NT.saveDulieuNT> lstdctmp = new ArrayList<QT_DULIEU_NT.saveDulieuNT>();

            for (QT_DULIEU_NT.saveDulieuNT value : lstsaveNT) {
                if (!value.getD2().equals("false")) {
                    lstdctmp.add(value);
                }
            }
            
            if(!daoMain.saveNhaptaycn01( UserName,hmParameter.get("ngay_bc").toString(), lstdctmp,hmParameter.get("user_id").toString()))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_Nhaptaycn_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_Nhaptaycn_01: " + e.getMessage());
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }                            
}
