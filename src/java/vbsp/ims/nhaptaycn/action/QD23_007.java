/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.Utilities;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.DaoChamdiemcnMain;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.encrypt.DES;
import vbsp.ims.khnv2021.excel.ExcelExport;
import vbsp.ims.khnv2021.model.FileExportInfo;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.POSModel;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class QD23_007 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    @Override
    public String load() {
        try {
//            System.err.println("QD23_001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            Date date1 = new SimpleDateFormat("dd-MMM-yyyy").parse(hmParameter.get("ngay_bc").toString());
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String dateStr = sdf.format(date1);
            setThangbc(dateStr);
                        
            // Nhập kết quả lần 1
            if (Grade.equals("1") && hmParameter.get("type_action").toString().equals("1")) {
                lstDulieuNt50 = daoMain.getDataQd23_Kiemtra(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
//                setLstTrong30Ngay(daoMain.getCanBo(UserName, "KQ_TRONG30NGAY"));                                
                return "nhap_1_trong30ngay";
            } 
            else if (Grade.equals("1") && hmParameter.get("type_action").toString().equals("2")) {
                lstDulieuNt50 = daoMain.getDataQd23_Kiemtra(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
//                setLstSau30Ngay(daoMain.getCanBo(UserName, "KQ_SAU30NGAY"));
                return "nhap_1_sau30ngay";
            } 
            else if (Grade.equals("1") && hmParameter.get("type_action").toString().equals("3")) {
                lstDulieuNt50 = daoMain.getDataQd23_Kiemtra(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
//                setLstKTLai(daoMain.getCanBo(UserName, "KT_LAI"));   
                return "nhap_1_kiemtralai";
            } 

            else if (Grade.equals("2") && hmParameter.get("type_action").toString().equals("1")) {
                lstDulieuNt50 = daoMain.getDataQd23_Kiemtra(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
//                setLstTrong30Ngay(daoMain.getCanBo(UserName, "KQ_TRONG30NGAY"));                                
                return "nhap_2_trong30ngay";
            } 
            else if (Grade.equals("2") && hmParameter.get("type_action").toString().equals("2")) {
                lstDulieuNt50 = daoMain.getDataQd23_Kiemtra(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
//                setLstSau30Ngay(daoMain.getCanBo(UserName, "KQ_SAU30NGAY"));
                return "nhap_2_sau30ngay";
            } 
            else if (Grade.equals("2") && hmParameter.get("type_action").toString().equals("3")) {
                lstDulieuNt50 = daoMain.getDataQd23_Kiemtra(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd, hmParameter.get("nha_dt").toString(), hmParameter.get("type_action").toString());
//                setLstKTLai(daoMain.getCanBo(UserName, "KT_LAI"));   
                return "nhap_2_kiemtralai";
            } 

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QD23_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QD23_001: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    @Override
    public String save() {
//        System.err.println("Save - QD23_001");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt50 == null || lstDulieuNt50.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();
                        
            String iCheck = daoMain.checkData_Info_50(lstDulieuNt50,khoa_nhaptaycn,hmParameter.get("ngay_bc").toString(),UserName, Grade, lstDat,hmParameter.get("type_action").toString());
            if(!iCheck.equals("XXXAAA"))
            {
                addActionError("Lỗi! "+ iCheck);
                    return ERROR; 
            }     
            if (!daoMain.saveQD23_Kiemtra_007(khoa_nhaptaycn, UserName, "", hmParameter.get("ngay_bc").toString(),Grade, lstDulieuNt50, hmParameter.get("type_action").toString())) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COVID_03: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }
       
    
}
