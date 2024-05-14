/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tdnn;

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
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoKt740;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.Define;
import vbsp.ims.huydongtk.clsCanBo;
import vbsp.ims.huydongtk.clsHuyDongTK;
import vbsp.ims.leavelocal.LeaveHomeService;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListMainPos;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.restapi.ListTransactionPoint;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author LION
 */
public class ActionTdnnMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    LeaveHomeService _server_tmp;
    protected String Grade;
    protected String UserName;
    protected String Message;
    protected List<ListValue> lstAllTdnn = new ArrayList<>();
    public List<ModelExcelFile> lstExcel = new ArrayList<>();
    private String fileNameNew;
    private List<ListMainPos> lstCN_API;
    private List<ListPosCode> lstPGD_API;
    private List<ListCommune> lstXa_API;
    private List<ListTransactionPoint> lstPoint_API;
    private String pos_cd;
    private String main_pos;
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;
    List<clsCanBo> lstCanBo = new ArrayList<>();
    //<editor-fold defaultstate="collapsed" desc="khai báo get,set">

    public List<ListTransactionPoint> getLstPoint_API() {
        return lstPoint_API;
    }

    public void setLstPoint_API(List<ListTransactionPoint> lstPoint_API) {
        this.lstPoint_API = lstPoint_API;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getMain_pos() {
        return main_pos;
    }

    public void setMain_pos(String main_pos) {
        this.main_pos = main_pos;
    }

    public DaoListPosFromUser getListKTNBDA() {
        return listKTNBDA;
    }

    public void setListKTNBDA(DaoListPosFromUser listKTNBDA) {
        this.listKTNBDA = listKTNBDA;
    }

    public PosMainModel getPosMainModel() {
        return posMainModel;
    }

    public void setPosMainModel(PosMainModel posMainModel) {
        this.posMainModel = posMainModel;
    }

    public List<ListMainPos> getLstCN_API() {
        return lstCN_API;
    }

    public void setLstCN_API(List<ListMainPos> lstCN_API) {
        this.lstCN_API = lstCN_API;
    }

    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
    }

    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
    }

    public List<ModelExcelFile> getLstExcel() {
        return lstExcel;
    }

    public List<clsCanBo> getLstCanBo() {
        return lstCanBo;
    }

    public void setLstCanBo(List<clsCanBo> lstCanBo) {
        this.lstCanBo = lstCanBo;
    }

    public void setLstExcel(List<ModelExcelFile> lstExcel) {
        this.lstExcel = lstExcel;
    }

    public String getFileNameNew() {
        return fileNameNew;
    }

    public void setFileNameNew(String fileNameNew) {
        this.fileNameNew = fileNameNew;
    }
    public List<File> fileUpload = new ArrayList<>();

    public List<File> getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(List<File> fileUpload) {
        this.fileUpload = fileUpload;
    }
    public List<String> fileUploadFileName = new ArrayList<>();

    public List<String> getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(List<String> fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }
    protected List<ReportParam> lstTdnnParams = new ArrayList<>();
    protected String khoa_tdnn;
    protected TreeNode nodes_pos = new TreeNode();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<ListValue> lstParameters = new ArrayList<>();
    protected List<String> poscd = new ArrayList<String>();
    protected String isDisplayTM = "N";
    protected String type_bcqt;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();
    private String tt_khoa;
    private String macn;
    private String ngay_bc;

    private String cap_kt;
    private String nha_dt;
    private String pos_cam;
    private String txn_cam;
    protected String txn_detail;
    protected List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT = new ArrayList<QT_DULIEU_NT.saveDulieuNT_Phi>();
    protected List<String> lstDat = new ArrayList<String>();

    public List<String> getLstDat() {
        return lstDat;
    }

    public void setLstDat(List<String> lstDat) {
        this.lstDat = lstDat;
    }

    public List<QT_DULIEU_NT.saveDulieuNT_Phi> getLstsaveNT_DAT() {
        return lstsaveNT_DAT;
    }

    public void setLstsaveNT_DAT(List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT) {
        this.lstsaveNT_DAT = lstsaveNT_DAT;
    }

    public String getTxn_detail() {
        return txn_detail;
    }

    public void setTxn_detail(String txn_detail) {
        this.txn_detail = txn_detail;
    }

    public String getTxn_cam() {
        return txn_cam;
    }

    public void setTxn_cam(String txn_cam) {
        this.txn_cam = txn_cam;
    }
    protected List<ListValue> lstPOS = new ArrayList<ListValue>();
    protected List<ListValue> lstTXN = new ArrayList<ListValue>();

    public List<ListValue> getLstTXN() {
        return lstTXN;
    }

    public void setLstTXN(List<ListValue> lstTXN) {
        this.lstTXN = lstTXN;
    }

    public String getPos_cam() {
        return pos_cam;
    }

    public void setPos_cam(String pos_cam) {
        this.pos_cam = pos_cam;
    }

    public List<ListValue> getLstPOS() {
        return lstPOS;
    }

    public void setLstPOS(List<ListValue> lstPOS) {
        this.lstPOS = lstPOS;
    }

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
            if (DaoTdnnMain.newInstance().checkUser(UserName) > 0) {
                setLstAllTdnn(DaoTdnnMain.newInstance().getAllTdnn_SUB());
            } else {
                setLstAllTdnn(DaoTdnnMain.newInstance().getAllTdnn(Grade));
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadPataTdnn() {
        try {
//            System.err.println("khoa_tdnn=" + khoa_tdnn);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoTdnnMain daoMain = new DaoTdnnMain();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade);
            if (!khoa_tdnn.equals("01_TDNN_2024")|| !khoa_tdnn.equals("04_TDNN_2024")) {
                if (Grade.equals("3")) {
                    setTreeNodeGrade3(lstModelTree);
                } else {
                    setTreeNodeGrade12(lstModelTree);
                }
            }
            lstTdnnParams = daoMain.getReportParmamsTdnn(conn, khoa_tdnn, UserName, Grade);

            // BO SUNG PHAN KIEM TRA XEM CO THUYET MINH HAY KO
//            TmDao tmDao = new TmDao();
//            isDisplayTM = tmDao.getCO_TM(khoa_tdnn);
            if (khoa_tdnn.equals("GSCMR_001")) {
//                lstPOS = new DaoKt740().getGroupQuery(null);
                return "GSCMR_001";
            }
            if (khoa_tdnn.equals("01_TDNN_2024") || khoa_tdnn.equals("04_TDNN_2024")) {
                String PosFlag = "";
                if (Grade.equals("3")) {
                    PosFlag = "H";
                } else if (Grade.equals("2")) {
                    PosFlag = "M";
                } else {
                    PosFlag = "S";
                }
                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd = posMainModel.getPosCd();
                main_pos = posMainModel.getMainPosCd();
                _server_tmp = new LeaveHomeService();

                if (PosFlag == "S") {
                    lstPGD_API = _server_tmp.getListPgd(main_pos, pos_cd);
                    lstCN_API = _server_tmp.getListCn(main_pos.substring(2, 4));
                    lstXa_API = _server_tmp.getListXa(main_pos.substring(2, 4), pos_cd.substring(2, 6), "", pos_cd);
                    lstPoint_API = _server_tmp.getListPoint(pos_cd, PosFlag, "TXN");
                } else if (PosFlag == "M") {
                    lstPGD_API = _server_tmp.getListPgd(main_pos, "");
                    lstCN_API = _server_tmp.getListCn(main_pos.substring(2, 4));
                    lstXa_API = _server_tmp.getListXa(main_pos.substring(2, 4),"", "", "");
                    lstPoint_API = _server_tmp.getListPoint(pos_cd, PosFlag, "TXN");
                } else {
                    lstCN_API = _server_tmp.getListCn("");
                    lstPGD_API = _server_tmp.getListPgd("", "");
                    lstXa_API = _server_tmp.getListXa("", "", "", "");
                    lstPoint_API = _server_tmp.getListPoint("", "", "TXN");
                }

                System.err.println(pos_cd + " " + main_pos + " " + PosFlag + " " + Grade);
                return "TDNN_2024";
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataTdnn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataTdnn: " + e.getMessage());
        }
        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cho phan gui va xac nhan so lieu">
    private List<ModelViewSend> getViewStatusSend(List<String> lstPos, Map<String, Integer> mapStatus) {
        addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();
        try {
            Map<String, String> mapPosByName = DaoTdnnMain.newInstance().getPosByName(lstPos);

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

    public String sendTDNN() {
        System.err.println("Vao ham sendTDNN");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoTdnnMain daosync = DaoTdnnMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();

            for (String mapgd : lstPos) {
                if (hmParameter.get("khoa_tdnn").toString().equals("GSCMR_001") && daosync.checkMainPos(mapgd) == 0) {
                    continue;
                }
                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += hmParameter.get("khoa_tdnn").toString() + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = daosync.getDataSendTdnn("NT", hmParameter.get("khoa_tdnn").toString(),
                        mapgd, hmParameter.get("ngay_bc").toString());
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_TDNN, "NT",
                        hmParameter.get("khoa_tdnn").toString(), hmParameter.get("ngay_bc").toString(), UserName, Grade,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendTDNN: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendTDNN: Khong tao duoc file " + strPathSave);
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
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendTDNN: Khong dong bo duoc file " + strPathSave);
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
            CoreLogger.error(this.getClass().getName() + " Exception -> sendTDNN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendTDNN: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cho phan khoa va mo khoa">
    public String LoadParaTdnn_unlock() {
        try {
            System.err.println("khoa_tdnn=" + khoa_tdnn);
            if (!getParaSession()) {
                return ERROR;
            }
            if (khoa_tdnn == null) {
                setKhoa_tdnn("ALL");
            } else {
                if (khoa_tdnn.isEmpty()) {
                    setKhoa_tdnn("ALL");
                }
            }
            Connection conn = new DaoConnect().getConnect();
            DaoTdnnMain daoMain = new DaoTdnnMain();

            lstTdnnParams = daoMain.getReportParmamsTdnn(conn, khoa_tdnn, UserName, Grade);
            lstParameters = DaoSyncMain.newInstance().getMainPosLock(conn);
            // BO SUNG PHAN KIEM TRA XEM CO THUYET MINH HAY KO
//            TmDao tmDao = new TmDao();
//            isDisplayTM = tmDao.getCO_TM(khoa_tdnn);
//            if (conn != null) {
//                conn.close();
//            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadParaTdnn_unlock: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadParaTdnn_unlock: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String LoadStatusSendCnTdnn() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoTdnnMain daosync = DaoTdnnMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();
            lstDulieuNt = daosync.getStatusSendCn(getType_bcqt(), hmParameter.get("khoa_tdnn").toString(),
                    macn, hmParameter.get("ngay_bc").toString(),
                    hmParameter.get("khoa_tdnn").toString().equals("ALL") ? tt_khoa : Define.WEB_SERVICES_STATUS_SEND);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadStatusSendCnTdnn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadStatusSendCnTdnn: " + e.getMessage());
            addActionError("Bạn chưa tải được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");
        return SUCCESS;
    }

    public String OpenPgd() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoTdnnMain daosync = DaoTdnnMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();

            setKhoa_tdnn(hmParameter.get("khoa_tdnn").toString());
//            setType_bcqt(hmParameter.get("type_bcqt").toString());
            setMacn(hmParameter.get("macn").toString());
            setNgay_bc(hmParameter.get("ngay_bc").toString());
//            (String type, String khoa,List<String> lstMapgd,  String ngaybc, String tt_khoa,  String username,  String grade)
            if (!daosync.setStatusLock("NT", khoa_tdnn, poscd, hmParameter.get("ngay_bc").toString(),
                    Define.WEB_SERVICES_STATUS_SEND, UserName, Grade)) {
                addActionError("Lỗi !, Mở khóa bị lỗi xin liên hệ với quản trị để được khắc phục");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadStatusSendCn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadStatusSendCn: " + e.getMessage());
            addActionError("Mở khóa bị lỗi xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");
        return SUCCESS;
    }

    public String getPOSCAM() {
        try {
            System.err.println("Vao ham getTotruong " + pos_cam);

            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoTdnnMain daoMain = new DaoTdnnMain();
            lstTXN = new DaoKt740().getGroupQuery(null);
//            lstTdnnParams = daoMain.getReportParmamsTdnn(conn, khoa_tdnn,UserName,Grade);

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getTotruong -> " + e.getMessage());
        }
        return SUCCESS;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">
    public String getNha_dt() {
        return nha_dt;
    }

    public void setNha_dt(String nha_dt) {
        this.nha_dt = nha_dt;
    }

    public String getCap_kt() {
        return cap_kt;
    }

    public void setCap_kt(String cap_kt) {
        this.cap_kt = cap_kt;
    }

    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }

    public String getType_bcqt() {
        return type_bcqt;
    }

    public void setType_bcqt(String type_bcqt) {
        this.type_bcqt = type_bcqt;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public List<ReportParam> getLstTdnnParams() {
        return lstTdnnParams;
    }

    public void setLstTdnnParams(List<ReportParam> lstTdnnParams) {
        this.lstTdnnParams = lstTdnnParams;
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

    public List<ListValue> getLstAllTdnn() {
        return lstAllTdnn;
    }

    public void setLstAllTdnn(List<ListValue> lstAllTdnn) {
        this.lstAllTdnn = lstAllTdnn;
    }

    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    public String getIsDisplayTM() {
        return isDisplayTM;
    }

    public void setIsDisplayTM(String isDisplayTM) {
        this.isDisplayTM = isDisplayTM;
    }

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }

    public String getKhoa_tdnn() {
        return khoa_tdnn;
    }

    public void setKhoa_tdnn(String khoa_tdnn) {
        this.khoa_tdnn = khoa_tdnn;
    }

    public String getTt_khoa() {
        return tt_khoa;
    }

    public void setTt_khoa(String tt_khoa) {
        this.tt_khoa = tt_khoa;
    }

    public String getMacn() {
        return macn;
    }

    public void setMacn(String macn) {
        this.macn = macn;
    }

    public String getNgay_bc() {
        return ngay_bc;
    }

    public void setNgay_bc(String ngay_bc) {
        this.ngay_bc = ngay_bc;
    }

//</editor-fold>
}
