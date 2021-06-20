/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.jasper.ExportJasperReport;
import vbsp.ims.loadparams.LoadReportParams;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.tdnn.DaoTdnnMain;

/**
 *
 * @author Trung
 */


public class DIEUCHUYENTO_01 extends ActionNhaptaycnMain         
implements NhaptaycnFunction , ServletRequestAware{
    private HttpServletRequest request = null;
    private LoadReportParams objLRP = new LoadReportParams();
    DaoConnect daoconnect = new DaoConnect();
    ExportJasperReport exportReport = new ExportJasperReport();
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    @Override
    public String load(){
        try {
            System.err.println("DIEUCHUYENTO_01");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();            
//            DaoTdnnMain daoMain1 = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
//            lstAllTdnn = daoMain.getDmKhac(conn, "56");
//            if(Grade.equals("3"))
//            {
//                lstDulieuNt = daoMain.getStatusSendCn("QLDB_001",
//                    poscd, hmParameter.get("ngay_bc").toString(),"");
//            }
//            else
                lstDulieuNt = daoMain.getDataDieuChuyenTo01(conn, "DIEUCHUYENTO_01", hmParameter.get("ngay_bc").toString(),UserName, Grade,hmParameter.get("group_from").toString(),hmParameter.get("group_to").toString());
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> DIEUCHUYENTO_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> DIEUCHUYENTO_01: " + e.getMessage());
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
                    if (!value.getMA().equals("false")) {
                        lstDat.add(value.getMA());
                    }
            }
            
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();    
            HashMap hmParameter = getParameter();            
             if(!daoMain.saveDieuChuyenTo01(khoa_nhaptaycn, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt, lstDat, hmParameter.get("group_from").toString(),hmParameter.get("group_to").toString()))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }    
             genViewReport();

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> QLDB_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }                            

    public String genViewReport() //throws Exception
    {
        setReportId("BC00630005");
        String sMessagepdf = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
        sMessagepdf += Define.M_REPORT;
        try {
            //        System.err.println("Da vao ham tao genViewReport");
            HashMap<String, Object> paramHashMap = new HashMap<>();
            Map<String, String[]> prameters = request.getParameterMap();
            String strMa_pgd = "000000";
            String strTonghop = "";
            String strNgaybc = "";
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            System.err.println(" Dia chi IP Client tao bao cao -->> " + ipAddress);
            CoreLogger.error(" Dia chi IP Client tao bao cao -->> " + ipAddress);

            //CuongBM: xy lay lay cac tham so cho vao hashmap
            for (String parameter : prameters.keySet()) {
                String[] values = prameters.get(parameter);
            //CuongBM: 18-Apr-14
                //Do neu parameter kieu date thi he thong se sinh them control dojo.date
                //   nen minh can phai loai bo tham so nay di
                if (parameter.indexOf("TEXT") > 0
                        || parameter.indexOf("DATE") > 0
                        || parameter.indexOf("LIST") > 0) {
                    if (parameter.indexOf("DATE") > 0) {
                        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5),
                                new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                        strNgaybc = new SimpleDateFormat("ddMMyyyy").format(sdf);

                    } else {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5).replace("1_group_from", "PARA_GRP_FROM").replace("1_group_to", "PARA_GRP_TO"), values[0]);

                    }

                    //Lay ra ma PGD de gan vao ten file
                    if (parameter.toUpperCase().indexOf("MAPGD") > 0) {
                        strMa_pgd = values[0].trim().substring(2, values[0].length());
                    }

                    if (parameter.toUpperCase().indexOf("TONGHOP") > 0 && values[0].trim().equals("Y")) {
                        strTonghop = "_TONGHOP";
                    }
                    //System.err.println("Key la :"+parameter+" Tham so la :"+values[0]);
                }
            }

        //CuongBM: 01-Jul-14
            //Desc: hardcode truong hop neu o PGD thi khong hien thi cobobox "Tong hop"
            //      gan mac dinh truong nay la No
            if (strTonghop.equalsIgnoreCase("")) {
                paramHashMap.put("PARA_TONGHOP", "N");
            }
            
            if (reportId == null || reportId.length() < 1) {
                //Cho nay can xua lai de bat loi
                setMessage("Lỗi không thể lấy ra được ID báo cáo");
                return ERROR;
            }
            //xu ly cho export file ra PDF hoac la Excel
            String strCurrDate = strNgaybc.isEmpty() ? new SimpleDateFormat("ddMMyyyy").format(new Date()) : strNgaybc;
            //duong dan chua file tren o dia + Define.M_REPORT_XLS
            String strPathSave = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");

            //Ham nay lay ra ten file bao cao can tao, ten file jasper report
            HashMap<String, String> hmNameReport_Jasper = objLRP.getNameReport_Jasper(reportId);
            if (hmNameReport_Jasper == null) {
                //Cho nay can xua lai de bat loi
                setMessage("Lỗi không thể lấy ra được tham số của báo cáo");
                return ERROR;
            }

            String strNameReport = hmNameReport_Jasper.get("NAME_FILE");

        //Duong dan day du cua file jasper tren o dia
            String strSourceJasper = strPathSave + Define.M_REPORT + hmNameReport_Jasper.get("NAME_JASPER") + ".jrxml";

            //kiem tra file xem da co chua neu chua co thi return
            File checkFile = new File(strSourceJasper);
            if (!checkFile.exists()) {
                //Cho nay can xua lai de bat loi
                setMessage("Lỗi file mẫu báo cáo jasper không có");
                return ERROR;
            }
            //Ten file tao ra se luu lai de nguoi su dung download (chi ten file chua co duong dan)
            String strTimeFile = Long.toString(System.currentTimeMillis());
            String strFileSave = strMa_pgd + "_"
                    + strNameReport + strTonghop + "_" + strCurrDate
                    + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());
            Connection connect = null;
            connect = daoconnect.getConnect();
            if (connect == null) {
                //Cho nay can xua lai de bat loi
                fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
                filereport="ERROR_JASPER_REPORT.PDF";
                setMessage("Lỗi không thể kết nối được với cơ sở dữ liệu");
                return SUCCESS;
            }

            strPathSave += Define.M_REPORT_PDF;
            strFileSave += ".PDF";
            filereport = strFileSave;
            File Checkpath = new File(strPathSave);
            if (!Checkpath.exists()) {
                System.out.println("Da tao thu muc: " + strPathSave);
                Checkpath.mkdirs();
            }            
            exportReport.ExportJasperPdf(strSourceJasper, paramHashMap, connect, strPathSave + strFileSave);

            //Kiem tra xem file da tao thanh cong chua        
            File filerpt = new File(strPathSave + strFileSave);
            if (!filerpt.exists()) {
                fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
                filereport="ERROR_JASPER_REPORT.PDF";
//            setMessage("Lỗi bạn chưa tạo được file báo cáo "+strFileSave);
//            return ERROR;
            } else {
                fileNamelocal = strPathSave + strFileSave;
            }
//            System.err.println(fileNamelocal);
            System.gc();
        } catch (Exception e) {
            fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
//            System.err.println(fileNamelocal);
            filereport="ERROR_JASPER_REPORT.PDF";
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " Loi khi view bao cao genViewReport " + e.getMessage());
        }

//        System.err.println(fileNamelocal);
        return "success";
    }    
}
