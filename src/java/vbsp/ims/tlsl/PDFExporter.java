/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tlsl;

import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.jasper.ExportJasperReport;

/**
 *
 * @author Trung
 */
public class PDFExporter extends ReportExporter {

    private String from_date;
    private String to_date;
    private String term;
    private String group;
    private String no;
    private String type;
    private String fullpath;
    private String pos_cd;
    private String con_flag;
    private String report_name;

    DaoConnect daoconnect = new DaoConnect();
    ExportJasperReport exportReport = new ExportJasperReport();

    public PDFExporter() {
    }

    public PDFExporter(
            String from_date,
            String to_date,
            String term,
            String group,
            String no,
            String type,
            String pos_cd,
            String con_flag
    ) {
        this.from_date = from_date;
        this.to_date = to_date;
        this.term = term;
        this.group = group;
        this.no = no;
        this.type = type;
        this.pos_cd = pos_cd;
        this.con_flag = con_flag;
    }

    @Override
    public boolean export() {
        try {

            //if (report_type.equals("1")) // bao cao tong hop
            //{
            String strNgaybc;
            HashMap<String, Object> paramHashMap = new HashMap<>();

            paramHashMap.put("para_mapgd", pos_cd);
            paramHashMap.put("para_tonghop", con_flag);
            paramHashMap.put("para_tungay",
                    DefineFun.convert2OracleDateFormat(from_date)
            );
            paramHashMap.put("para_loaibc", report_type);
            paramHashMap.put("para_denngay",
                    DefineFun.convert2OracleDateFormat(to_date));
            paramHashMap.put("para_vung", eco_area);

            //xu ly cho export file ra PDF hoac la Excel
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(to_date);
            strNgaybc = new SimpleDateFormat("ddMMyyyy").format(sdf);
            String strCurrDate = strNgaybc.isEmpty()
                    ? new SimpleDateFormat("ddMMyyyy").format(new Date()) : strNgaybc;

            ReportDao reportDao = new ReportDao();
            ExportInfor export = reportDao.get_jasper(group, no);//"KTSL_KT01";

            paramHashMap.put("para_chuongtrinh",
                    export.getProgram());
            
            paramHashMap.put("para_group",group);
            paramHashMap.put("para_no",no);

            String strPathSave = Define.M_ROOT;
            String strNameReport = export.getJasper_name();

            String strSourceJasper = Define.M_ROOT + Define.M_REPORT
                    + strNameReport + ".jrxml";

            report_name = strSourceJasper;

            //kiem tra file xem da co chua neu chua co thi return
            File checkFile = new File(strSourceJasper);
            if (!checkFile.exists()) {
                //Cho nay can xua lai de bat loi
                return false;
            }
            String tonghop_flg;
//                if (con_flag.equals("Y"))
//                    tonghop_flg = "TONGHOP";
//                else
//                    tonghop_flg = "";

            //Ten file tao ra se luu lai de nguoi su dung download (chi ten file chua co duong dan)
            String strTimeFile = Long.toString(System.currentTimeMillis());

            String strFileSave;

            if (report_type.equals("1")) // bao cao tong hop
            {
                tonghop_flg = "TONGHOP";
                strFileSave = strNameReport + "_" + tonghop_flg + "_" + strCurrDate
                        + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());
            } else {
                tonghop_flg = "CHITIET";
                strFileSave = "BCCT_"
                        + strNameReport + "_" + tonghop_flg + "_" + strCurrDate
                        + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());
            }

            Connection connect;
            connect = daoconnect.getConnect();

            if (connect == null) {
                //Cho nay can xua lai de bat loi
                return false;
            }

            strPathSave += Define.M_REPORT_PDF;
            strFileSave += ".PDF";
            File Checkpath = new File(strPathSave);
            if (!Checkpath.exists()) {
                Checkpath.mkdirs();
            }
            exportReport.ExportJasperPdf(strSourceJasper, paramHashMap,
                    connect, strPathSave + strFileSave);

            //Kiem tra xem file da tao thanh cong chua
            File filerpt = new File(strPathSave + strFileSave);
            if (!filerpt.exists()) {
                return false;
            }
            fullpath = strPathSave + strFileSave;
//            } else { // bao cao chi tiet
//
//            }
            System.gc();
            return true;
        } catch (Exception e) {
            System.out.println("Loi ham export~" + e.getMessage());
            return false;
        }
    }

    public String getFrom_date() {
        return from_date;
    }

    public void setFrom_date(String from_date) {
        this.from_date = from_date;
    }

    public String getTo_date() {
        return to_date;
    }

    public void setTo_date(String to_date) {
        this.to_date = to_date;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFullpath() {
        return fullpath;
    }

    public void setFullpath(String fullpath) {
        this.fullpath = fullpath;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getCon_flag() {
        return con_flag;
    }

    public void setCon_flag(String con_flag) {
        this.con_flag = con_flag;
    }

    public String getReport_name() {
        return report_name;
    }

    public void setReport_name(String report_name) {
        this.report_name = report_name;
    }

    @Override
    public String getPath() {
        return getFullpath();
    }

    @Override
    public String getName() {
        return getReport_name();
    }

}
