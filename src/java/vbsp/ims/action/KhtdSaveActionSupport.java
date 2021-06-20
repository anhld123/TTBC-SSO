package vbsp.ims.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoCreditPlan;
import vbsp.ims.define.Define;
import vbsp.ims.model.KhtdGrid;
import vbsp.ims.syn.ProcessReportSyn;

public class KhtdSaveActionSupport extends ActionSupport implements ServletRequestAware{

    private String ngayNhap;
    private String maPGD;
    private String dataJson;  //Json cua grid truyen ve tu client
                              //Can chuyen sang dang List ho so tin dung chi tiet
    private String xaThon;
    private String data = "";    //Du lieu gui ve oracle de luu
                                 //Cac truong du lieu cach nhau boi dau #
    private List<KhtdGrid> khtdGridList;
    private DaoCreditPlan daoCreditPlan;
    private HttpServletRequest servletRequest = null;

    public KhtdSaveActionSupport() {
    }

    public String execute() throws Exception {
        return "success";
    }
    
    public String saveKeHoachTon() throws ParseException {
        khtdGridList = new ArrayList<KhtdGrid>();
        daoCreditPlan = new DaoCreditPlan();
        String strNgayNhapFomrmat = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayNhap));
        convertJsonToKhtdGridList();

        //Lay du lieu
        for (KhtdGrid obj : khtdGridList) {
            data += obj.getMaCT() + ":" + obj.getKeHoachTon().replace(",", "") + "#";//Bo dau phay format
        }

        //Bo ky tu # cuoi cung:
        //Data se co dang: NUMBER_1: 100#NUMBER_2: 3000....
        // --> Bo dau # o cuoi chuoi
        data = data.substring(0, data.length() - 1);

        //Tungnv them de dong bo bao ca ve tw
        HttpSession session = servletRequest.getSession();
        //lay user dang nhap
        String strUserName = session.getAttribute("username").toString();
        //Lay cap bao cao
        String strGrade = session.getAttribute("reportGrade").toString();
        if (!strGrade.equals("3")) {
            daoCreditPlan.saveKhtdGrid(maPGD, strNgayNhapFomrmat, strUserName, strGrade, "kehoachton", data);
            //Lay duong dan va ten xml se ghi ra
            String strPathSave = !servletRequest.getRealPath("/").endsWith("/")
                                 ?servletRequest.getRealPath("/")+"/"+ Define.M_REPORT_XML
                                 :servletRequest.getRealPath("/")+ Define.M_REPORT_XML;
            strPathSave+= maPGD + "_KEHOACHTON_KHTD.xml";
            //Tao file xml theo cau truc

            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServices(Define.PARA_SYN_REPORT_KHTD,
                    "KHTD", strNgayNhapFomrmat, strUserName, maPGD, strGrade, "kehoachton", data, "", strPathSave);
//      
            if (!bSuccess) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
            }
        }
        return "success";
    }

    public String saveXayDungKH() throws ParseException {
        khtdGridList = new ArrayList<KhtdGrid>();
        daoCreditPlan = new DaoCreditPlan();
        String strNgayNhapFomrmat = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayNhap));
        convertJsonToKhtdGridList();

        //Lay du lieu
        for (KhtdGrid obj : khtdGridList) {
            data += obj.getMaCT() + ":" + obj.getXayDungKH().replace(",", "") + "#";//Bo dau phay format
        }

        //Bo ky tu # cuoi cung:
        //Data se co dang: NUMBER_1: 100#NUMBER_2: 3000....
        // --> Bo dau # o cuoi chuoi
        data = data.substring(0, data.length() - 1);

        //Tungnv them de dong bo bao ca ve tw
        HttpSession session = servletRequest.getSession();
        if(session==null)
        {
            return ERROR;
        }
        //lay user dang nhap
        String strUserName = session.getAttribute("username").toString();
        //Lay cap bao cao
        String strGrade = session.getAttribute("reportGrade").toString();
        if (!strGrade.equals("3")) {
            daoCreditPlan.saveKhtdGrid(maPGD, strNgayNhapFomrmat, strUserName, strGrade, "xaydungkh", data);
            //Lay duong dan va ten xml se ghi ra
              String strPathSave = !servletRequest.getRealPath("/").endsWith("/")
                                 ?servletRequest.getRealPath("/")+"/"+ Define.M_REPORT_XML
                                 :servletRequest.getRealPath("/")+ Define.M_REPORT_XML;
              strPathSave+= maPGD + "_XAYDUNG_KHTD.xml";
            //Tao file xml theo cau truc

            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServices(Define.PARA_SYN_REPORT_KHTD,
                    "KHTD", strNgayNhapFomrmat, strUserName, maPGD, strGrade, "xaydungkh", data, "", strPathSave);
//      
            if (!bSuccess) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
            }
        }
        return "success";
    }

    public String saveGiaoKH() throws ParseException {
        khtdGridList = new ArrayList<KhtdGrid>();
        daoCreditPlan = new DaoCreditPlan();
        String strNgayNhapFomrmat = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayNhap));
        convertJsonToKhtdGridList();

        //Lay du lieu
        for (KhtdGrid obj : khtdGridList) {
            data += obj.getMaCT() + ":" + obj.getGiaoKH().replace(",", "") + "#";//Bo dau phay format
        }

        //Bo ky tu # cuoi cung:
        //Data se co dang: NUMBER_1: 100#NUMBER_2: 3000....
        // --> Bo dau # o cuoi chuoi
        data = data.substring(0, data.length() - 1);

        //Luu du lieu
//        String userId = "test";
//        String reportGrade = "1";
//        daoCreditPlan.saveKhtdGrid(maPGD, strNgayNhapFomrmat, userId, reportGrade, "giaokh", data);
        //Tungnv them de dong bo bao ca ve tw
        HttpSession session = servletRequest.getSession();
         if(session==null)
        {
            return ERROR;
        }
        //lay user dang nhap
        String strUserName = session.getAttribute("username").toString();
        //Lay cap bao cao
        String strGrade = session.getAttribute("reportGrade").toString();
        if (!strGrade.equals("3")) {
            daoCreditPlan.saveKhtdGrid(maPGD, strNgayNhapFomrmat, strUserName, strGrade, "giaokh", data);
            //Lay duong dan va ten xml se ghi ra
            String strPathSave = !servletRequest.getRealPath("/").endsWith("/")
                                 ?servletRequest.getRealPath("/")+"/"+ Define.M_REPORT_XML
                                 :servletRequest.getRealPath("/")+ Define.M_REPORT_XML;
                    strPathSave+= maPGD + "_GIAO_KHTD.xml";
            //Tao file xml theo cau truc

            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServices(Define.PARA_SYN_REPORT_KHTD,
                    "KHTD", strNgayNhapFomrmat, strUserName, maPGD, strGrade, "giaokh", data, "", strPathSave);

//        ImsReadWriteXmlFile clientWritexml = new ImsReadWriteXmlFile();
//        boolean bSuccess=clientWritexml.createFileXML(Define.PARA_SYN_REPORT_KHTD, 
//                "KHTD", strNgayNhapFomrmat, strUserName, maPGD, strGrade, "giaokh", data, strPathSave,"");
            if (!bSuccess) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
            }
        }
        return "success";
    }

    public String saveDieuChinhKH() throws ParseException {
        khtdGridList = new ArrayList<KhtdGrid>();
        daoCreditPlan = new DaoCreditPlan();
        String strNgayNhapFomrmat = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayNhap));
        convertJsonToKhtdGridList();

        //Lay du lieu
        for (KhtdGrid obj : khtdGridList) {
            data += obj.getMaCT() + ":" + obj.getDieuChinhKH().replace(",", "") + "#";//Bo dau phay format
        }

        //Bo ky tu # cuoi cung:
        //Data se co dang: NUMBER_1: 100#NUMBER_2: 3000....
        // --> Bo dau # o cuoi chuoi
        data = data.substring(0, data.length() - 1);

        //Luu du lieu
//        String userId = "test";
//        String reportGrade = "1";
//        daoCreditPlan.saveKhtdGrid(maPGD, strNgayNhapFomrmat, userId, reportGrade, "dieuchinhkh", data);
        //Tungnv them de dong bo bao ca ve tw
        HttpSession session = servletRequest.getSession();
        if(session==null)
        {
            return ERROR;
        }
        //lay user dang nhap
        String strUserName = session.getAttribute("username").toString();
        //Lay cap bao cao
        String strGrade = session.getAttribute("reportGrade").toString();
        if (!strGrade.equals("3")) {
            daoCreditPlan.saveKhtdGrid(maPGD, strNgayNhapFomrmat, strUserName, strGrade, "dieuchinhkh", data);
            //Lay duong dan va ten xml se ghi ra
              String strPathSave = !servletRequest.getRealPath("/").endsWith("/")
                                 ?servletRequest.getRealPath("/")+"/"+ Define.M_REPORT_XML
                                 :servletRequest.getRealPath("/")+ Define.M_REPORT_XML;
                    strPathSave+= maPGD + "_DIEUCHINH_KHTD.xml";
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServices(Define.PARA_SYN_REPORT_KHTD,
                    "KHTD", strNgayNhapFomrmat, strUserName, maPGD, strGrade, "dieuchinhkh", data, "", strPathSave);
//        ImsReadWriteXmlFile clientWritexml = new ImsReadWriteXmlFile();
//        boolean bSuccess=clientWritexml.createFileXML(Define.PARA_SYN_REPORT_KHTD, 
//                "KHTD", strNgayNhapFomrmat, strUserName, maPGD, strGrade, "dieuchinhkh", data, strPathSave,"");
            if (!bSuccess) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
            }
        }
        return "success";
    }

    private void convertJsonToKhtdGridList() {
        Gson gson = new Gson();

        Type collectionType = new TypeToken<ArrayList<KhtdGrid>>() {
        }.getType();
        khtdGridList = gson.fromJson(dataJson, collectionType);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMaPGD() {
        return maPGD;
    }

    public void setMaPGD(String maPGD) {
        this.maPGD = maPGD;
    }

    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public String getXaThon() {
        return xaThon;
    }

    public void setXaThon(String xaThon) {
        this.xaThon = xaThon;
    }

//</editor-fold>

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        servletRequest = hsr;
    }
}
