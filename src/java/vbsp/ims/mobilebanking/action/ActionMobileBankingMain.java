/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.mobilebanking.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.mobilebanking.dao.DaoMobileBankingMain;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoRptQuery;
import vbsp.ims.define.Define;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.Pagination;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.sbv.daoSbv;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKtgsSync;
//import vbsp.ims.xml.XmlNhaptaycnSync;

/**
 *
 * @author LION
 */
public class ActionMobileBankingMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    String fileNamelocal;
    String filereport;
    public String reportId; //CuongBM: Ma bao cao
    public HttpServletRequest request = null;

    
    protected String Grade;
    protected String UserName;

    protected String Message;
    protected String khoa_mb;
    private List<ListValue> lstTinh = new ArrayList<ListValue>();
    private List<ListValue> lstHuyen = new ArrayList<ListValue>();
    private List<ListValue> lstChisoXdCb = new ArrayList<ListValue>();
    private List<ListValue> lstPhongBan = new ArrayList<ListValue>();
    private List<ListValue> lstChucVu = new ArrayList<ListValue>();
    private List<ListValue> lstTrangthai = new ArrayList<ListValue>();
    private List<ListValue> lstMaNhanbiet = new ArrayList<ListValue>();    
    private List<ListValue> lstGioiTinh = new ArrayList<ListValue>();
    private List<ListValue> lstPosCD = new ArrayList<ListValue>();
    
    protected List<ListValue> lstAllMB = new ArrayList<>();
    protected List<ReportParam> lstMBParams = new ArrayList<>();            
    protected TreeNode nodes_pos = new TreeNode();

    protected List<DULIEU_MB> lstDulieuNt = new ArrayList<>();

    protected List<ListValue> lstParameters = new ArrayList<>();
    protected List<String> poscd = new ArrayList<String>();
    
    
    protected String poslist;
       
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();

    private String ngay_bc;

    protected Pagination pagination = new Pagination(50, 1);
    


//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Xu ly cho action">
    //<editor-fold defaultstate="collapsed" desc="Cho phan khoi tao form chinh">
    private boolean setTreeNodeGrade3(List<ModelTreeNode> lstModelTree) {

        try {
            TreeNode nodePar = new TreeNode();
            List<TreeNode> lstTree = new ArrayList<TreeNode>();
            for (int i = 0; i < lstModelTree.size(); i++) {
                //String strPos_key = ArrlstPoscd.get(i);
                ModelTreeNode modelTree = lstModelTree.get(i);

                //Neu la row dau tien thi la node root
                if (i == 0) {
//                    System.err.println("getStrParentCd=" + modelTree.getStrParentCd() + " getStrParentDesc=" + modelTree.getStrParentDesc());
                    nodes_pos.setId("999999");
                    nodes_pos.setTitle(modelTree.getStrParentDesc());
                    nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                    nodes_pos.setChildren(new LinkedList<TreeNode>());
                } else {
                    if (modelTree.getStrChildCd().equals("999999")) {
                        if (i != 1) {
//                        nodes_pos.getChildren().add(nodePar);
                            lstTree.add(nodePar);
                            nodePar = null;
                            nodePar = new TreeNode();
                        }
//                    nodePar= new TreeNode();
//                        System.err.println("  - nodePar getStrChildCd=" + modelTree.getStrChildCd() + " getStrChildDesc=" + modelTree.getStrChildDesc());
                        nodePar.setId("999999");
                        nodePar.setTitle(modelTree.getStrChildDesc());
                        nodePar.setState(TreeNode.NODE_STATE_CLOSED);
                        nodePar.setChildren(new LinkedList<TreeNode>());
                    } else {
                        //Khoi tao cho node child
//                        System.err.println("      - nodeChild getStrChildCd=" + modelTree.getStrChildCd() + " getStrChildDesc=" + modelTree.getStrChildDesc());
                        TreeNode nodeChild = new TreeNode();
                        nodeChild.setId(modelTree.getStrChildCd());
                        nodeChild.setTitle(modelTree.getStrChildDesc());
                        nodePar.getChildren().add(nodeChild);
                    }
                }

            }
            lstTree.add(nodePar);
            for (TreeNode node : lstTree) {
                nodes_pos.getChildren().add(node);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade3 -> " + e.getMessage());
            return false;
        }
        return true;
    }

    private boolean setTreeNodeGrade12(List<ModelTreeNode> lstModelTree) {

        try {
            for (int i = 0; i < lstModelTree.size(); i++) {
                //String strPos_key = ArrlstPoscd.get(i);
                ModelTreeNode modelTree = lstModelTree.get(i);
                //Neu la row dau tien thi la node root
                if (i == 0) {
                    nodes_pos.setId("999999");
                    nodes_pos.setTitle(modelTree.getStrParentDesc());
                    nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                    nodes_pos.setChildren(new LinkedList<TreeNode>());
                }
                //Khoi tao cho node child
                TreeNode nodeChild = new TreeNode();
                nodeChild.setId(modelTree.getStrChildCd());
                nodeChild.setTitle(modelTree.getStrChildDesc());
//                System.err.println(ArrlstPosDesc.get(i));
                nodes_pos.getChildren().add(nodeChild);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade12 -> " + e.getMessage());
            return false;
        }
        return true;
    }

    protected boolean getParaSession() {
        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return false;
        }
        //lay ra user
        setUserName(session.get("username").toString());

//            System.err.println("execute sUserName=" + sUserName);
        if (UserName == null || UserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        setGrade(session.get("reportGrade").toString());
        if (Grade == null || Grade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        return true;
    }

    private List<String> convertStringtoList(String[] value) {
        List<String> lst = new ArrayList<>();
        try {
            for (int i = 0; i < value.length; i++) {
                if (!value[i].equals("999999") && !value[i].isEmpty()) {
                    lst.add(value[i]);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
        }
        return lst;
    }

    protected HashMap<String, Object> getParameter() throws Exception {
        HashMap<String, Object> paramHashMap = new HashMap<>();
        Map<String, String[]> prameters = ServletActionContext.getRequest().getParameterMap();
        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);
            if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0) {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                    lstParameters.add(new ListValue(parameter, values[0]));
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            } else {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.equals("poscd")) {
                    paramHashMap.put(parameter, convertStringtoList(values));
                } else {
                    paramHashMap.put(parameter, values[0]);
                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            }
        }
        return paramHashMap;
    }

    public String execute() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            setLstAllMB(DaoMobileBankingMain.newInstance().getAllReportMB(Grade));

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadPataMobileBanking() {
        try {
//            System.err.println("khoa_mb=" + khoa_mb);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoMobileBankingMain daoMain = new DaoMobileBankingMain();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade, khoa_mb);

            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }

            lstMBParams = daoMain.getReportParaMB(conn, khoa_mb, UserName, Grade);
            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataNhaptaycn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataNhaptaycn: " + e.getMessage());
        }
        return SUCCESS;
    }


    public String loadAll_BC() {
        try {
//            System.err.println("khoa_mb=" + khoa_mb);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoMobileBankingMain daoMain = new DaoMobileBankingMain();
            //khoi tao cho treeview cac pos
            HashMap hmParameter = getParameter();
//            System.out.println(tt_nha_dt);

//                lstNhaptaycnParams = daoMain.getReportParmamsNhaptaycn(conn, "PHIUT_001",UserName,Grade);
//            lstDulieuNt_pgd = daoMain.getDataDMBC(conn, UserName, Grade, tt_nha_dt);
//                lstDulieuNt_spham = daoMain.getDataPhiUT_SP(conn, "PHIUT_001", "",UserName, Grade,poscd,tt_nha_dt);
//                lstDulieuNt_phanbo = daoMain.getDataPhiUT_PHANBO(conn, "PHIUT_001", "",UserName, Grade,poscd,"");

            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataNhaptaycn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataNhaptaycn: " + e.getMessage());
        }
        return SUCCESS;
    }

    //</editor-fold>
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

    public String sendDataMobileBanking() {
        System.err.println("Vao ham sendDataMobileBanking");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoMobileBankingMain daosync = DaoMobileBankingMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();
            Connection conn = new DaoConnect().getConnect();
//            lstDulieuNt = daosync.getStatusPosLock(conn, khoa_mb, "", UserName, Grade, poscd, 
//                    hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(), hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
                if (lstDulieuNt.size() > 0) {
                    addActionError("Các PGD bạn chọn chưa chốt hết số liệu nên không thể gửi lên Trung ưng");
                    return ERROR;
                }    

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += "NTDN"+hmParameter.get("nam_bc").toString()+ hmParameter.get("trangthaims").toString()+ hmParameter.get("dot_ms").toString()+ hmParameter.get("nghiepvums").toString() + "_" +
                        hmParameter.get("khoa_mb").toString() + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;
//                lstData = daosync.getDataSendMobileBanking("NT", khoa_mb,
//                        mapgd, "", hmParameter.get("nghiepvums").toString(), hmParameter.get("nam_bc").toString(),
//                        hmParameter.get("dot_ms").toString() , hmParameter.get("trangthaims").toString());

                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_MUASAMTS, "NT",
                        hmParameter.get("khoa_mb").toString(), "", UserName, Grade,
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
            CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendPhiUT: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
    }

    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">

    public List<ListValue> getLstHuyen() {
        return lstHuyen;
    }

    public void setLstHuyen(List<ListValue> lstHuyen) {
        this.lstHuyen = lstHuyen;
    }

    public List<ListValue> getLstChisoXdCb() {
        return lstChisoXdCb;
    }

    public void setLstChisoXdCb(List<ListValue> lstChisoXdCb) {
        this.lstChisoXdCb = lstChisoXdCb;
    }

    public List<ListValue> getLstPhongBan() {
        return lstPhongBan;
    }

    public void setLstPhongBan(List<ListValue> lstPhongBan) {
        this.lstPhongBan = lstPhongBan;
    }

    public List<ListValue> getLstChucVu() {
        return lstChucVu;
    }

    public void setLstChucVu(List<ListValue> lstChucVu) {
        this.lstChucVu = lstChucVu;
    }

    public List<ListValue> getLstTrangthai() {
        return lstTrangthai;
    }

    public void setLstTrangthai(List<ListValue> lstTrangthai) {
        this.lstTrangthai = lstTrangthai;
    }

    
    public List<ListValue> getLstTinh() {
        return lstTinh;
    }

    public void setLstTinh(List<ListValue> lstTinh) {
        this.lstTinh = lstTinh;
    }
    

    public String getPoslist() {
        return poslist;
    }

    public void setPoslist(String poslist) {
        this.poslist = poslist;
    }

    
    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }

    public List<DULIEU_MB> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<DULIEU_MB> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

   
    

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    

    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }

    public String getKhoa_nhaptaycn() {
        return khoa_mb;
    }

    public void setKhoa_nhaptaycn(String khoa_mb) {
        this.khoa_mb = khoa_mb;
    }

    public String getNgay_bc() {
        return ngay_bc;
    }

    public void setNgay_bc(String ngay_bc) {
        this.ngay_bc = ngay_bc;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getKhoa_mb() {
        return khoa_mb;
    }

    public void setKhoa_mb(String khoa_mb) {
        this.khoa_mb = khoa_mb;
    }

    public List<ListValue> getLstAllMB() {
        return lstAllMB;
    }

    public void setLstAllMB(List<ListValue> lstAllMB) {
        this.lstAllMB = lstAllMB;
    }

    public List<ReportParam> getLstMBParams() {
        return lstMBParams;
    }

    public void setLstMBParams(List<ReportParam> lstMBParams) {
        this.lstMBParams = lstMBParams;
    }


    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public List<ListValue> getLstMaNhanbiet() {
        return lstMaNhanbiet;
    }

    public void setLstMaNhanbiet(List<ListValue> lstMaNhanbiet) {
        this.lstMaNhanbiet = lstMaNhanbiet;
    }
    
     public List<ListValue> getLstGioiTinh() {
        return lstGioiTinh;
    }

    public void setLstGioiTinh(List<ListValue> lstGioiTinh) {
        this.lstGioiTinh = lstGioiTinh;
    }
    
    public List<ListValue> getLstPosCD() {
        return lstPosCD;
    }

    public void setLstPosCD(List<ListValue> lstPosCD) {
        this.lstPosCD = lstPosCD;
    }
    
    //</editor-fold>



}
