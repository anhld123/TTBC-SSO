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
public class QD23_008 extends ActionNhaptaycnMain
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
            SimpleDateFormat sdf = new SimpleDateFormat("MM/yyyy");
            String dateStr = sdf.format(date1);
            setThangbc(dateStr);
                       
            
            lstDulieuNt50 = daoMain.getDataQd23_Rasoat_008(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), UserName, Grade, poscd);
            if (Grade.equals("1")) {                
                return "rasoat_1_pgd";
            }   
            else if (Grade.equals("2")) {                
                return "rasoat_2_cn";
            } 
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QD23_008: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QD23_008: " + e.getMessage());
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
                                    
            if (!daoMain.saveQD23_RaSoat_008(khoa_nhaptaycn, UserName, "", hmParameter.get("ngay_bc").toString(),Grade, lstDulieuNt50)) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            else
            {
                String sResult = sendOnePos(khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(), pos_cd_username);              
                if(sResult.equals(SUCCESS))
                {
                    addActionMessage("Bạn đã lưu và gửi dữ liệu thành công");
                    return SUCCESS;
                }  
                else
                {
                    addActionError(sResult);
                    return ERROR;
                }
            }
            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QD23_008: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QD23_008: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn đã lưu dữ liệu thành công");
//        return SUCCESS;
    }
       
    
}
