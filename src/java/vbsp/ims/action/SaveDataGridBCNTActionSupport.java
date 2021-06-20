package vbsp.ims.action;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.opensymphony.xwork2.ActionSupport;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.bcnt.BaoCaoNhapTay;
import vbsp.ims.dao.DaoBCNT;
import vbsp.ims.define.Define;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.ImsReadWriteXmlFile;

public class SaveDataGridBCNTActionSupport extends ActionSupport implements ServletRequestAware {

    private HttpServletRequest servletRequest;
    private String dataJson;    //Du lieu dang json truyen tu client ve
    //Can chuyen sang dang List bao cao nhap tay

    private List<BaoCaoNhapTay> bcntList; //Liet ke noi dung tron list
    private String reportId;
    private String reportDate;
    private String quarteryear; //CuongBM: 02Jul14 
    //         Quy bao cao (Dung thay the cho truong reportDate)
    private String posId;
    private String data = "";    //Du lieu se co dang:
    //NUMBER_1: 100#NUMBER_2: 3000# .... TEXT_1: Đã nhập dữ liệu vào

    private DaoBCNT daoBcnt = new DaoBCNT();

    public SaveDataGridBCNTActionSupport() {
    }

    public String execute() throws Exception {
        //Chuyen du lieu json sang object
        convertJsonToBcntList();

        //Lay du lieu
        for (BaoCaoNhapTay obj : bcntList) {
            reportId = obj.getMaBC();
            reportDate = obj.getNgayGt();
            posId = obj.getMaPgd();
            quarteryear = obj.getQuyBc();

            if (obj.getChinhSua().equalsIgnoreCase("Y") || obj.getCtTong().equalsIgnoreCase("Y")) {
                data += obj.getMaCtMap() + ":" + obj.getGiaTri().replace(",", "") + "#";//Bo dau phay format
            }
        }

        //Bo ky tu # cuoi cung:
        //Data se co dang: NUMBER_1: 100#NUMBER_2: 3000# .... TEXT_1: Đã nhập dữ liệu vào#
        // --> Bo dau # o cuoi chuoi
        data = data.substring(0, data.length() - 1);

        daoBcnt.saveGcntGrid(reportId, posId, reportDate, quarteryear, data);
        //Tungnv them de dong bo bao ca ve tw
        HttpSession session = servletRequest.getSession();
        //lay user dang nhap
        String strUserName = session.getAttribute("username").toString();
        //Lay cap bao cao
        String strGrade = session.getAttribute("reportGrade").toString();
        if (!strGrade.equals("3")) {
            //Lay duong dan va ten xml se ghi ra
            String strPathSave = !servletRequest.getRealPath("/").endsWith("/")
                      ?servletRequest.getRealPath("/")+"/"+ Define.M_REPORT_XML
                      :servletRequest.getRealPath("/")+ Define.M_REPORT_XML;
                    strPathSave+= posId + "_BCNT_KTNB_"+reportId+".xml";
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServices(Define.PARA_SYN_REPORT_KTNB,
                    reportId, reportDate, strUserName, posId, strGrade, "BCNT", data, quarteryear, strPathSave);
            if (!bSuccess) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
            }
        }
// public boolean saveGcntGrid_updateSys(String reportId, String posId, String reportDate, 
//            String sUserId, String quarteryear, String strSysDate, String data)
        return "success";
    }

    private void convertJsonToBcntList() throws ParseException {
        Gson gson = new Gson();

        Type collectionType = new TypeToken<ArrayList<BaoCaoNhapTay>>() {
        }.getType();
        bcntList = gson.fromJson(dataJson, collectionType);
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getDataJson() {
        return dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public List<BaoCaoNhapTay> getBcntList() {
        return bcntList;
    }

    public void setBcntList(List<BaoCaoNhapTay> bcntList) {
        this.bcntList = bcntList;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
//</editor-fold>
}
