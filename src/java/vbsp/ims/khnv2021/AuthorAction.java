/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.define.Define;
import vbsp.ims.khnv2021.dao.DaoMau01A;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKhnv2021Sync;
import vbsp.ims.xml.XmlKtgsSync;

public class AuthorAction extends ActionSupport {

    //Cac truong chua thong tin bo xung luu du lieu
    private String CapBC, TenDN, status, cboTonghop, strNguyennhan, chkSuccess, dataReult, cboDonvi, cboNam, cboDot, btnSend;
    private Map session;
    private List<PosClass> lstPos = new ArrayList<>();
    private List<DULIEU_NT> lstData = new ArrayList<>();
    private InputStream pageResult;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();

    @Override
    //Lấy danh đơn vị theo cấp báo cáo
    public String execute() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        CapBC = (String) session.get("reportGrade");
        TenDN = (String) session.get("username");
        lstPos = new AuthorModel().getPosCD(CapBC, TenDN);
        if (CapBC.equalsIgnoreCase("2")) {
            btnSend = "Gửi cấp trên";
        } else {
            btnSend = "Duyệt";
        }
        return SUCCESS;
    }
    
    public void sendTwKhnv() {
        System.err.println("Vao ham sendKTGS");
        try {
            session = ActionContext.getContext().getSession();
            CapBC = (String) session.get("reportGrade");
            TenDN = (String) session.get("username");

            List<String> lstPos = new ArrayList<>();
            
            Map<String, Integer> mapStatusSend = new HashMap();

                lstPos = new DaoMau01A().getAllPosUser(TenDN);

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += "KHNV02_" + mapgd
                        + "_" + TenDN + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";


                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = new DaoMau01A().getDataSendKhnv("NT", "KHNV02", mapgd,cboNam,cboDot);
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKhnv2021Sync().createXmlFileKhnv2021(Define.PARA_SYN_REPORT_KTGS, "NT",
                        "KHNV02", "31-dec-2020", TenDN, CapBC,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
//                    return ERROR;
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
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong dong bo duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
//                    return ERROR;
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                } else {
//                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
//                    return ERROR;
                }
            }
            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendKTGS: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
//            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

//        return SUCCESS;
    }
    
    private List<ModelViewSend> getViewStatusSend(List<String> lstPos, Map<String, Integer> mapStatus) {
        addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();
        try {
            Map<String, String> mapPosByName = DaoKtgsMain.newInstance().getPosByName(lstPos);

            for (String key : mapStatus.keySet()) {

                if (mapPosByName.get(key) == null) {
                    continue;
                };
                Integer value = mapStatus.get(key);
                ModelViewSend modelview = ModelViewSend.newInstance();
                modelview.setMapgd(key);
                modelview.setKey(value);
                modelview.setTenpgd(mapPosByName.get(key));

                switch (value) {
                    case 1:
                        modelview.setMota_loi("Tạo file xml bị lỗi");
                        break;
                    case 2:
                        modelview.setMota_loi("Không tìm thấy file xml");
                        break;
                    case 3:
                        modelview.setMota_loi("Gửi dữ liệu bị lỗi");
                        break;
                    case 4:
                        modelview.setMota_loi("Thành công");
                        break;
                    case 5:
                        modelview.setMota_loi("Phòng giao dịch này bị khóa");
                        break;
                    case 6:
                        modelview.setMota_loi("Không có dữ liệu");
                        break;
                    default:
                        modelview.setMota_loi("Không đúng trạng thái lỗi");
                        break;
                }
                lstStatus.add(modelview);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
        }
        return lstStatus;
    }

    //0 - Tải; 1 - Gửi; 2 - Trả lại
    public String SendAction() throws Exception {
        //Lấy danh sách đơn vị theo cấp báo cáo
        session = ActionContext.getContext().getSession();
        CapBC = (String) session.get("reportGrade");
        TenDN = (String) session.get("username");
        switch (status.trim()) {
            case "0":
                //Nhớ truyền đủ 7 tham số
                lstData = new AuthorModel().getData(CapBC, TenDN, cboDonvi, cboNam, cboDot, cboTonghop, strNguyennhan);
                if (lstData != null && !lstData.isEmpty()) {
                    chkSuccess = "SuccessLoad";
                    pageResult = new StringBufferInputStream("00");
                } else {
                    chkSuccess = "FaildMessage";
                    pageResult = new StringBufferInputStream("01");
                }
                break;
            case "1":
                sendTwKhnv();
//                if (dataReult != null) {
                    chkSuccess = "SuccessMessage";
                    pageResult = new StringBufferInputStream("00");
//                } else {
//                    chkSuccess = "FaildMessage";
//                    pageResult = new StringBufferInputStream("01");
//                }
//                pageResult = new StringBufferInputStream(dataReult);
                break;
            case "2":
                dataReult = new AuthorModel().rollBackData(CapBC, TenDN, cboDonvi, cboNam, cboDot, cboTonghop, strNguyennhan);
                if (dataReult != null) {
                    chkSuccess = "SuccessMessage";
                    pageResult = new StringBufferInputStream("20");
                } else {
                    chkSuccess = "FaildMessage";
                    pageResult = new StringBufferInputStream("21");
                }
                pageResult = new StringBufferInputStream(dataReult);
                break;
        }
        return chkSuccess;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }
    
    
    public String getCapBC() {
        return CapBC;
    }

    public void setCapBC(String CapBC) {
        this.CapBC = CapBC;
    }

    public String getTenDN() {
        return TenDN;
    }

    public void setTenDN(String TenDN) {
        this.TenDN = TenDN;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCboTonghop() {
        return cboTonghop;
    }

    public void setCboTonghop(String cboTonghop) {
        this.cboTonghop = cboTonghop;
    }

    public String getStrNguyennhan() {
        return strNguyennhan;
    }

    public void setStrNguyennhan(String strNguyennhan) {
        this.strNguyennhan = strNguyennhan;
    }

    public String getChkSuccess() {
        return chkSuccess;
    }

    public void setChkSuccess(String chkSuccess) {
        this.chkSuccess = chkSuccess;
    }

    public String getDataReult() {
        return dataReult;
    }

    public void setDataReult(String dataReult) {
        this.dataReult = dataReult;
    }

    public String getCboDonvi() {
        return cboDonvi;
    }

    public void setCboDonvi(String cboDonvi) {
        this.cboDonvi = cboDonvi;
    }

    public String getCboNam() {
        return cboNam;
    }

    public void setCboNam(String cboNam) {
        this.cboNam = cboNam;
    }

    public String getCboDot() {
        return cboDot;
    }

    public void setCboDot(String cboDot) {
        this.cboDot = cboDot;
    }

    public Map getSession() {
        return session;
    }

    public void setSession(Map session) {
        this.session = session;
    }

    public List<PosClass> getLstPos() {
        return lstPos;
    }

    public void setLstPos(List<PosClass> lstPos) {
        this.lstPos = lstPos;
    }

    public List<DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getBtnSend() {
        return btnSend;
    }

    public void setBtnSend(String btnSend) {
        this.btnSend = btnSend;
    }
    
    //</editor-fold> 
}
