/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class SMS_001 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
        try {
//            System.err.println("NTMOI - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");   
            String s = hmParameter.get("soku").toString();
            String s1 = hmParameter.get("tenkh").toString();
            lstDulieuNt = daoMain.getDataSms01(conn, "SMS_001",  hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd,hmParameter.get("soku").toString(),hmParameter.get("tenkh").toString());
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String getDetialTIDE(){
        try {
//            System.err.println("NTMOI - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");   
            String s = hmParameter.get("soku").toString();
            lstDulieuNt = daoMain.getDataSms01(conn, "SMS_001", "",UserName, Grade,poscd,s,"XXX111");
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> BDP_001: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String reload(){        
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_DAT) {
                if(value != null)                    
                    if (!value.getD3().equals("false")) {
                        lstDat.add(value.getD3());
                    }
            }
            
            if (lstDat == null || lstDat.size() == 0) {
                addActionError("Bạn chưa chọn tài khoản nào.");;
                return ERROR;
            }
            
            for (QT_DULIEU_NT  value : lstDulieuNt) {
                String[] parts = value.getD8().split(";");
                for (String part : parts) {
                    if((part.length()!=10  || !part.substring(0,1).equals("0") || !isNumeric(part)) 
                        && lstDat.contains(value.getD3()))
                        {
                            addActionError("Số điện thoại phải đủ 10 chữ số, bắt đầu bằng số 0 và phân cách bằng dấu ;");;
                            return ERROR;
                        }
                }                                
            }
            
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();          
            if(!daoMain.saveSms02("SMS_001", UserName, "","", lstDulieuNt,"",lstDat))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            if(daoMain.checkUser(UserName)>0)
            {
                addActionMessage("Bạn đã lưu dữ liệu thành công");
                return SUCCESS;
            }
            String result = sendSMS(lstDat);
            if (result.equals("1"))
            {
                addActionError("Lưu báo cáo thành công nhưng tạo file xml gửi tw bị lỗi. Liên hệ quản trị để khắc phục");
                return ERROR;
            }
            else if (result.equals("2"))
            {
                addActionError("Lưu báo cáo thành công nhưng không tìm thấy file xml để gửi tw. Liên hệ quản trị để khắc phục");
                return ERROR;
            }  
            else if (result.equals("3"))
            {
                addActionError("Lưu báo cáo thành công nhưng gửi lên tw bị lỗi. Liên hệ quản trị để khắc phục");
                return ERROR;
            }
            else if (result.equals(ERROR))
            {
                addActionError("Lưu báo cáo thành công nhưng gửi lên tw bị lỗi. Liên hệ quản trị để khắc phục");
                return ERROR;
            }
            else if (result.equals("5"))
            {
                addActionError("PGD đã bị khóa. Liên hệ với tw để mở khóa khi gửi số liệu");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }     
    
    private boolean isNumeric(String str) { 
    try {  
      Double.parseDouble(str);  
      return true;
    } catch(NumberFormatException e){  
      return false;  
    }  
  }
    
    public String saveSMS() {
         try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_DAT) {
                if(value != null)                    
                    if (!value.getD3().equals("false")) {
                        lstDat.add(value.getD3());
                    }
                    else
                    {
                        lstDat.add("999999");
                    }
            }
            if (lstsaveNT_DAT == null || lstsaveNT_DAT.size() == 0) {
                lstDat.add("999999");
            }
            List<String> lstSend = new ArrayList<String>();
            

            
            for (QT_DULIEU_NT  value : lstDulieuNt) {  
                String s = value.getD8().substring(0,1);
                lstSend.add(value.getD3());                
                String[] parts = value.getD8().split(";");
                for (String part : parts) {
                    if((part.length()!=10  || !part.substring(0,1).equals("0") || !isNumeric(part)) 
                        && lstDat.contains(value.getD3()))
                        {
                            addActionError("Số điện thoại phải đủ 10 chữ số, bắt đầu bằng số 0 và phân cách bằng dấu ;");;
                            return ERROR;
                        }
                }   
                
            }
            
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();          
            if(!daoMain.saveSms03("SMS_001", UserName, "","", lstDulieuNt,"",lstDat))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
            if(daoMain.checkUser(UserName)>0)
            {
                addActionMessage("Bạn đã lưu dữ liệu thành công");
                return SUCCESS;
            }
                
            String result = sendSMS(lstSend);
            if (result.equals("1"))
            {
                addActionError("Lưu báo cáo thành công nhưng tạo file xml gửi tw bị lỗi. Liên hệ quản trị để khắc phục");
                return ERROR;
            }
            else if (result.equals("2"))
            {
                addActionError("Lưu báo cáo thành công nhưng không tìm thấy file xml để gửi tw. Liên hệ quản trị để khắc phục");
                return ERROR;
            }  
            else if (result.equals("3"))
            {
                addActionError("Lưu báo cáo thành công nhưng gửi lên tw bị lỗi. Liên hệ quản trị để khắc phục");
                return ERROR;
            }
            else if (result.equals(ERROR))
            {
                addActionError("Lưu báo cáo thành công nhưng gửi lên tw bị lỗi. Liên hệ quản trị để khắc phục");
                return ERROR;
            }
            else if (result.equals("5"))
            {
                addActionError("PGD đã bị khóa. Liên hệ với tw để mở khóa khi gửi số liệu");
                return ERROR;
            }
            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }   
    
    private String sendSMS(List<String> arrTK) {
        System.err.println("Vao ham sendSMS");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoNhaptaycnMain daosync = DaoNhaptaycnMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();
            
            lstPos = daosync.getAllPosUser(UserName, "SMS_001");
                
            String khoa =  "SMS_001";

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += "SMS_001" + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";


                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;
                lstData = daosync.getDataSendSms("NT","SMS_001",
                        mapgd, "",arrTK);
                
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
                Date date = new Date();
                String sDate = format2.format(date);
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_PHIUT, "NT",
                        "SMS_001", sDate, UserName, Grade,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendSMS: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi                    
                    return "1";
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendSMS: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
                    return "2";
                }
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
                if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
//                    addActionError("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendSMS: Khong dong bo duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
                    return "3";
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                    return "4";
                } else {
//                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
                    return "5";
                }
            }
//            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendSMS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendSMS: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
    }
}
