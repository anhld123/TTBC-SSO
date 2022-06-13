/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.nghiquyet11cp;

import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.math.BigInteger;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.dtw.UploadFileLogObject;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.LockSendModel;
import vbsp.ims.restapi.UpdateLockModel;

import vbsp.ims.util.DateUtil;

/**
 *
 * @author Trung
 */
public class NQ11_GKH extends ActionNghiquyet11cpMain
        implements NhaptaycnFunction {

    DuLieuNTService service;
    private String message;

    private static List<UploadFileLogObject> logObj = new ArrayList<>();
    private String logPath;
    private String logPathType;
    private String nghiepvu;

    public String getNghiepvu() {
        return nghiepvu;
    }

    public void setNghiepvu(String nghiepvu) {
        this.nghiepvu = nghiepvu;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static List<UploadFileLogObject> getLogObj() {
        return logObj;
    }

    public static void setLogObj(List<UploadFileLogObject> logObj) {
        NQ11_GKH.logObj = logObj;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogPathType() {
        return logPathType;
    }

    public void setLogPathType(String logPathType) {
        this.logPathType = logPathType;
    }

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();           
            ArrayList<DuLieuNTRow> lstData = new ArrayList<>();
            service = new DuLieuNTService();
            
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();

            String sReportDate = hmParameter.get("ngay_bc").toString();
            Date reportDate = DateUtil.stringToDate(sReportDate, "dd-MMM-yyyy");
            String sApiReportDate = DateUtil.dateToString(reportDate, "yyyyMMdd");
                
            if (Grade.equals(Define.HEAD_POS_GRADE )) 
            {                
                lstData = service.getData(Define.NQ11_GIAO_KE_HOACH, Define.HEAD_POS_CODE, Define.HEAD_POS_FLAG, sApiReportDate);
            } 
            else if (Grade.equals(Define.MAIN_POS_GRADE)) 
            {
                lstData = service.getData(Define.NQ11_GIAO_KE_HOACH, posMainModel.getMainPosCd(), Define.MAIN_POS_FLAG, sApiReportDate);
            }
            lstData.sort(Comparator.comparing(o -> Integer.parseInt(o.getOrderValue())));
            int i = 1;
            for (DuLieuNTRow item : lstData) {
                try {
                    QT_DULIEU_NT row = new QT_DULIEU_NT();
                    row.setKHOA(Define.NQ11_GIAO_KE_HOACH);
                    row.setTHUTU(i);
                    row.setTT_HIENTHI(item.getOrderDescription());
                    row.setMA(item.getCode());
                    row.setTEN(item.getName());
                    //Date reportDate = DateUtil.toDate(item.getReportDate());
                    row.setNGAYBC(reportDate);
                    row.setMAPGD(item.getPosCode());
                    row.setMACN(item.getBranchCode());

                    row.setD1(item.getD1());
                    row.setD2(item.getD2());                    
                    lstDulieuNt.add(row);
                    i++;
                } catch (Exception e) {
                    CoreLogger.error(this.getClass().getName() + " Exception -> NQ11_GKH: " + e.getMessage());
                    System.err.println(this.getClass().getName() + " Exception -> NQ11_GKH: " + e.getMessage());
                }
            }
            if (conn != null) {
                conn.close();
            }
            return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11_DKKH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11_DKKH: " + e.getMessage());
        }
        return SUCCESS;
    }    

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }

            HashMap hmParameter = getParameter();
            service = new DuLieuNTService();
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            
            String sReportDate = hmParameter.get("ngay_bc").toString();
            Date reportDate = DateUtil.stringToDate(sReportDate, "dd-MMM-yyyy");
            String sApiReportDate = DateUtil.dateToString(reportDate, "yyyyMMdd");
            
            ArrayList<LockSendModel> lstDataLock = service.getDataLockManual(Define.NQ11_GIAO_KE_HOACH, pos_cd_username, Define.SUB_POS_GRADE, sApiReportDate);
            ArrayList<DuLieuNTRow> lstUpdateData = new ArrayList<>();
            
            if (lstDataLock != null && lstDataLock.get(0).getStatus().equals("1")) 
            {
                addActionError("Đơn vị đã chốt số liệu. Bạn không thể điều chỉnh.");
                return ERROR;
            } else {                    
                for (QT_DULIEU_NT tmp : lstDulieuNt) {
                    DuLieuNTRow tempadd = new DuLieuNTRow();
                    tempadd.setKey(tmp.getKHOA());
                    tempadd.setOrderDescription(tmp.getTT_HIENTHI());
                    tempadd.setCode(tmp.getMA());
                    tempadd.setName(tmp.getTEN());
                    tempadd.setPosCode(tmp.getMAPGD());
                    tempadd.setD1(tmp.getD1());
                    tempadd.setD2(tmp.getD2());                        
                    lstUpdateData.add(tempadd);
                }
                String sPosFlag;
                if (Grade.equals(Define.MAIN_POS_GRADE)) 
                {
                    sPosFlag = Define.MAIN_POS_FLAG;
                }
                else 
                {
                    sPosFlag = Define.HEAD_POS_FLAG;
                }
                int status = service.updateData(Define.NQ11_GIAO_KE_HOACH, pos_cd_username, sPosFlag, sApiReportDate, UserName, "system", lstUpdateData);                
                if (status == 200 && Grade.equals(Define.MAIN_POS_GRADE)) 
                {                    
                    if (!DaoNghiquyet11cp.newInstance().saveNQ11CP_01_DKKH(khoa_nghiquyet11cp, UserName, Grade, posMainModel.getMainPosCd(), "31-DEC-" + hmParameter.get("nambc").toString(), lstDulieuNt)) {
                            addActionError("Cập nhật thành công tại CN nhưng API không thành công. Xin liên hệ với quản trị để khắc phục");
                            return ERROR;
                        }                    
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> NQ11_GIAO_KE_HOACH: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> NQ11_GIAO_KE_HOACH: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }        

//    public String sendDataNV_QTByApi(List<QT_DULIEU_NT> lstDulieuNt, String file) {
//        ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
//        SimpleDateFormat sdf;
//        sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
//        for (QT_DULIEU_NT tmp : lstDulieuNt) {
//            DuLieuNTRow tempadd = new DuLieuNTRow();
//            tempadd.setKey(tmp.getKHOA());
//            tempadd.setOrderDescription(tmp.getTT_HIENTHI());
//            tempadd.setCode(tmp.getMA());
//            tempadd.setName(tmp.getTEN());
//            String text = sdf.format(tmp.getNGAYBC());
//            tempadd.setReportDate(text);
//
//            tempadd.setReportYear(tmp.getNAMBC());
//            tempadd.setPosCode(tmp.getMAPGD());
//
//            tempadd.setPosFlag(tmp.getCO_TONGHOP());
//            tempadd.setBranchCode(tmp.getMACN());
//            tempadd.setMakerId(tmp.getNGUOI_NHAP());
//
//            tempadd.setD1(tmp.getD1());
//            tempadd.setD2(tmp.getD2());
//            
//            lstUpdateDate.add(tempadd);
//        }
//        service = new DuLieuNTService();
//        int status = service.updateData(Define.NQ11_GIAO_KE_HOACH, file.split("_", -1)[2], "S", file.split("_", -1)[1] + "1231", UserName, "system", lstUpdateDate);
//        if (status == 200) {
//            return SUCCESS;
//        }
//        return ERROR;
//    }

//    public String getPathRoot() throws Exception {
//        String path = ServletActionContext.getServletContext().getRealPath("/");
//        path = DefineFun.backlashReplace(path);
//        if (!path.endsWith("/")) {
//            path += "/";
//        }
//        return path;
//    }
    
    public void main(String[] args) {
//        saveUploadKH04();
//        DuLieuNTService service = new DuLieuNTService();
//        Date timeServer = service.getTimeServer();

//         Date date = Calendar.getInstance().getTime();  
//                DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");  
//                String strDate = dateFormat.format(timeServer);  
//        System.out.println("Converted String: " + service.getTimeServer());
//          String file = "NV_QT_000401_S_31122021_quyennv_6283";
//          
//          String[] array = file.split("_", -1);
//          String s1 = array[0];
//          String s2 = array[3];
//          String s3 = array[1];    
//        ArrayList<DuLieuNTRow> lstData = service.getData("COVID_03", "000401", "S", "20210630");
//        System.out.println(lstData.size());
//
//        ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
//        DuLieuNTRow testItem = new DuLieuNTRow();
//        testItem.setKey("COVID_03");
//        testItem.setCode("1004003452");
//        testItem.setReportDate("2021-06-30T00:00:00");
//        testItem.setPosCode("000401");
//        testItem.setPosFlag("S");
//        testItem.setD1("1004003452");
//        testItem.setD2("100");
//        testItem.setReportYear(2021);
//        lstUpdateDate.add(testItem );
//
//        int status = service.updateData("COVID_03", "000401", "S", "20210630", "trungnt", "", lstUpdateDate);
//        List<PLNO_DULIEU> lstPLNo = new ArrayList<>();
//        PLNO_DULIEU plno_dulieu = PLNO_DULIEU.newInstance();
//        plno_dulieu.setsSoku("6600000715491945");
//        plno_dulieu.setsSoku("6600000717477667");
//        lstPLNo.add(plno_dulieu);
//        List<NQ11cpModel> lstDulieuNt = new ArrayList<>();
//        List<QT_DULIEU_NT> lstDulieuNt1 = new ArrayList<>();
//        service = new DuLieuNTService();
////        lstDulieuNt = service.getDataNQ11CP("000601", "20220228", "03",
////                "060101", "0091543");
//
//        ArrayList<DuLieuNTRow> lstData = service.getDataNQ11CP_01KH("000601", "20220331", "S");
//        margerData(lstData,"0","0000");
    }
}
