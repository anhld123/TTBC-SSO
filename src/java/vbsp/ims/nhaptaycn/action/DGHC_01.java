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
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class DGHC_01 extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
       try {
            System.err.println("DGHC_01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();   
                        
            if(hmParameter.get("nha_dt").toString().equals("1") && (hmParameter.get("exten1").toString().length() <3 || hmParameter.get("exten2").toString().length() <3))
            {
                addActionError("Với trường hợp chia tách, sát nhập thôn bạn cần nhập địa giới cũ và địa giới mới.");
                return ERROR;
            }
            if (hmParameter.get("nha_dt").toString().equals("3") && daoMain.checkUserMainPos(UserName, Grade) == 0) {
                addActionError("Chỉ hội sở tỉnh mới được thực hiện chia tác, sát nhập huyện");;
                return ERROR;
            }
            
            lstDulieuNt = daoMain.getDataThaydoiDGHC(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd, hmParameter.get("user_id").toString(), hmParameter.get("nha_dt").toString(), hmParameter.get("exten1").toString(),hmParameter.get("exten2").toString() );
            setLstCBKetoan(daoMain.getDANHMUC(UserName,hmParameter.get("nha_dt").toString(), hmParameter.get("exten1").toString(),hmParameter.get("exten2").toString()));
            setLstCBTindung(daoMain.getCanBo(UserName,"HTSP"));
            if (conn != null) {
                conn.close();
            }    
            if(Grade.equals("1"))
            {
                if(hmParameter.get("user_id").toString().equals("1") && hmParameter.get("nha_dt").toString().equals("1"))
                {
                    return "ct_thon";
                }
                else if (hmParameter.get("user_id").toString().equals("1") && hmParameter.get("nha_dt").toString().equals("2"))
                {
                    return "ct_xa";
                }                
                //sát nhập
                else if(hmParameter.get("user_id").toString().equals("2") && hmParameter.get("nha_dt").toString().equals("1"))
                {
                    return "sn_thon";
                }
                else if (hmParameter.get("user_id").toString().equals("2") && hmParameter.get("nha_dt").toString().equals("2"))
                {
                    return "sn_xa";
                }  
                else
                {
                    addActionError("Cấp PGD chỉ được thực hiện chia tách/sát nhập thôn, xã");;
                    return ERROR;
                }
            }
            else if(Grade.equals("2"))
            {
                if (hmParameter.get("nha_dt").toString().equals("1") || hmParameter.get("nha_dt").toString().equals("2"))
                {
                    return "cap2_th";
                }
                else if (hmParameter.get("user_id").toString().equals("1") && hmParameter.get("nha_dt").toString().equals("3"))
                {
                    return "ct_huyen";
                }
                else if (hmParameter.get("user_id").toString().equals("1") && hmParameter.get("nha_dt").toString().equals("4"))
                {
                    return "ct_tinh";
                }
                //sát nhập                
                else if (hmParameter.get("user_id").toString().equals("2") && hmParameter.get("nha_dt").toString().equals("3"))
                {
                    return "sn_huyen";
                }
                else if (hmParameter.get("user_id").toString().equals("2") && hmParameter.get("nha_dt").toString().equals("4"))
                {
                    return "sn_tinh";
                }
                else
                {
                    addActionError("Cấp CN chỉ được thực hiện chia tách/sát nhập huyện/tỉnh");;
                    return ERROR;
                }
            }
                        
            
//            if(hmParameter.get("user_id").toString().equals("1") && hmParameter.get("nha_dt").toString().equals("1"))
//            {
//                return "ct_thon";
//            }
//            else if (hmParameter.get("user_id").toString().equals("1") && hmParameter.get("nha_dt").toString().equals("2"))
//            {
//                return "ct_xa";
//            }
//            else if (hmParameter.get("user_id").toString().equals("1") && hmParameter.get("nha_dt").toString().equals("3"))
//            {
//                return "ct_huyen";
//            }
//            else if (hmParameter.get("user_id").toString().equals("1") && hmParameter.get("nha_dt").toString().equals("4"))
//            {
//                return "ct_tinh";
//            }
//            //sát nhập
//            else if(hmParameter.get("user_id").toString().equals("2") && hmParameter.get("nha_dt").toString().equals("1"))
//            {
//                return "sn_thon";
//            }
//            else if (hmParameter.get("user_id").toString().equals("2") && hmParameter.get("nha_dt").toString().equals("2"))
//            {
//                return "sn_xa";
//            }
//            else if (hmParameter.get("user_id").toString().equals("2") && hmParameter.get("nha_dt").toString().equals("3"))
//            {
//                return "sn_huyen";
//            }
//            else if (hmParameter.get("user_id").toString().equals("2") && hmParameter.get("nha_dt").toString().equals("4"))
//            {
//                return "sn_tinh";
//            }
//            else
//            {
//                return SUCCESS;
//            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> DGHC_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> DGHC_01: " + e.getMessage());
        }        
        return SUCCESS;
    }
    

    @Override
    public String save() {
        System.err.println("Save - DGHC_01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();    
            HashMap hmParameter = getParameter();    
            
            if(Grade.equals("2") && hmParameter.get("nha_dt").toString().equals("2") )
            {
                addActionError("Chỉ cấp PGD mới được thực hiện chia tách/ sát nhập xã");;
                return ERROR;
            }
            
            
            for (QT_DULIEU_NT.saveDulieuNT_Phi value : lstsaveNT_DAT) {
                if(value != null)                    
                    if (!value.getD1().equals("false")) 
                    {
                        lstDat.add(value.getD1());
                    }
            }            
            
                    
            
            String iCheck = daoMain.checkData_Info(lstDulieuNt,khoa_nhaptaycn,hmParameter.get("ngay_bc").toString(),UserName, Grade, lstDat);
            if(!iCheck.equals("XXXAAA"))
            {
                addActionError("Lỗi! "+ iCheck);
                    return ERROR; 
            }                        
            
             if(!daoMain.saveThaydoiDGHC(khoa_nhaptaycn, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt, lstDat))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            } 
            
//            if(daoMain.checkUser(UserName)>0)
//            {
//                addActionMessage("Bạn đã lưu dữ liệu thành công");
//                return SUCCESS;
//            }
//            String result = sendPTDB();
//            if (result.equals("1"))
//            {
//                addActionError("Lưu báo cáo thành công nhưng tạo file xml gửi tw bị lỗi. Liên hệ quản trị để khắc phục");
//                return ERROR;
//            }
//            else if (result.equals("2"))
//            {
//                addActionError("Lưu báo cáo thành công nhưng không tìm thấy file xml để gửi tw. Liên hệ quản trị để khắc phục");
//                return ERROR;
//            }  
//            else if (result.equals("3"))
//            {
//                addActionError("Lưu báo cáo thành công nhưng gửi lên tw bị lỗi. Liên hệ quản trị để khắc phục");
//                return ERROR;
//            }
//            else if (result.equals(ERROR))
//            {
//                addActionError("Lưu báo cáo thành công nhưng gửi lên tw bị lỗi. Liên hệ quản trị để khắc phục");
//                return ERROR;
//            }
//            else if (result.equals("5"))
//            {
//                addActionError("PGD đã bị khóa. Liên hệ với tw để mở khóa khi gửi số liệu");
//                return ERROR;
//            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> DGHC_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> DGHC_01: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }     
        
    private String sendPTDB() {
        System.err.println("Vao ham sendSMS");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoNhaptaycnMain daosync = DaoNhaptaycnMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();
            
            lstPos = daosync.getAllPosUser(UserName, "QLDB_001");
                
            String khoa =  "QLDB_001";

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += "QLDB_001" + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";


                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;
                lstData = daosync.getDataSendNhaptaycn("NT","QLDB_001",
                        mapgd, hmParameter.get("ngay_bc").toString());
                
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                SimpleDateFormat format2 = new SimpleDateFormat("dd-MMM-yyyy");
                Date date = new Date();
                String sDate = format2.format(date);
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_PHIUT, "NT",
                        "QLDB_001", sDate, UserName, Grade,
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
