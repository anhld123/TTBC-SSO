/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bcqt.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bcqt.dao.TmDao;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.Define;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlBcqtSync;

/**
 *
 * @author LION
 */
public class ActionBcqtMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    protected String Grade;
    protected String UserName;
    protected String Message;
    protected String msgError;
    protected List<ListValue> lstAllBcqt = new ArrayList<>();
    protected List<ReportParam> lstBcqtParams = new ArrayList<>();
    protected String khoa_bcqt;
    protected TreeNode nodes_pos = new TreeNode();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<ListValue> lstParameters = new ArrayList<>();
    protected List<String> poscd = new ArrayList<String>();
    private List<ListValue> lstCBChuongtrinh = new ArrayList<ListValue>();
    private List<ListValue> lstDmkhac = new ArrayList<ListValue>();
    protected String isDisplayTM = "N";
    protected String type_bcqt;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();
    private Map<String, String> dmChtrinh = new LinkedHashMap<String, String>();
    
    protected List<ListValue> lstChuongtrinh = new ArrayList<ListValue>();
    protected List<ListValue> lstMaxa = new ArrayList<ListValue>();
    protected List<ListValue> lstNguonvon = new ArrayList<ListValue>();
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected List<ListValue> lstMato = new ArrayList<ListValue>();
   

    protected PosMainModel posMainModel;
    protected String pos_cd_username;
    
    

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
            if (DaoBcqtMain.newInstance().checkUser(UserName) > 0) {
                setLstAllBcqt(DaoBcqtMain.newInstance().getAllBcqt_SUB());
            } else {
                setLstAllBcqt(DaoBcqtMain.newInstance().getAllBcqt());
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadPataBcqt() {
        try {
//            System.err.println("khoa_bcqt=" + khoa_bcqt);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade);
            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            lstBcqtParams = daoMain.getReportParmams(conn, khoa_bcqt);

            // BO SUNG PHAN KIEM TRA XEM CO THUYET MINH HAY KO
            TmDao tmDao = new TmDao();
            isDisplayTM = tmDao.getCO_TM(khoa_bcqt);
            if (conn != null) {
                conn.close();
            }
            if(khoa_bcqt.equals("BCQT_LAITONAM")  && Grade.equals("1"))
            {
                DaoNghiquyet11cp daoMain11 = new DaoNghiquyet11cp();                
                setLstChuongtrinh(daoMain11.getDanhMuc(UserName, "CT_LAIAM", Grade));                
                setLstMaxa(daoMain11.getDanhMuc(UserName, "MAXA_LAIAM", Grade));                
                setLstNguonvon(daoMain11.getDanhMuc(UserName, "NGUONVON", Grade));         
                 setLstMato(daoMain11.getDanhMuc(UserName, "MATO", Grade));
                return "BCQT_LAIAM";
            }
            else if (khoa_bcqt.equals("BCQT_HOAHONG") && Grade.equals("1"))
            {
                DaoNghiquyet11cp daoMain11 = new DaoNghiquyet11cp();                
                setLstChuongtrinh(daoMain11.getDanhMuc(UserName, "CT_HOAHONG", Grade));                
                setLstMaxa(daoMain11.getDanhMuc(UserName, "MAXA", Grade));                
                setLstNguonvon(daoMain11.getDanhMuc(UserName, "NGUONVON_HOAHONG", Grade));         
                 setLstMato(daoMain11.getDanhMuc(UserName, "MATO_HOAHONG", Grade));
                return "BCQT_LAIAM";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataBcqt: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataBcqt: " + e.getMessage());
        }
        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cho phan gui va xac nhan so lieu">
    private List<ModelViewSend> getViewStatusSend(List<String> lstPos, Map<String, Integer> mapStatus) {
        addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();
        try {
            Map<String, String> mapPosByName = DaoBcqtMain.newInstance().getPosByName(lstPos);

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

    public String sendBCQT() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoBcqtMain daosync = DaoBcqtMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += hmParameter.get("khoa_bcqt").toString() + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                type_bcqt = hmParameter.get("type_bcqt").toString();
                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = daosync.getDataSendBcqt(type_bcqt, hmParameter.get("khoa_bcqt").toString(),
                        mapgd, hmParameter.get("ngay_bc").toString());
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlBcqtSync().createXmlFileBcqt(Define.PARA_SYN_REPORT_BCQT, type_bcqt,
                        hmParameter.get("khoa_bcqt").toString(), hmParameter.get("ngay_bc").toString(), UserName, Grade,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_OK, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendBCQT: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendBCQT: Khong tao duoc file " + strPathSave);
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
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendBCQT: Khong dong bo duoc file " + strPathSave);
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
            CoreLogger.error(this.getClass().getName() + " Exception -> sendBCQT: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendBCQT: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
    }

    public String sendLockBCQT() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoBcqtMain daosync = DaoBcqtMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            Map<String, Integer> mapStatusSend = new HashMap();
            type_bcqt = hmParameter.get("type_bcqt").toString();
            String pos_cd_check = daosync.isCheckPosSend(type_bcqt, hmParameter.get("khoa_bcqt").toString(), UserName, hmParameter.get("ngay_bc").toString());
            if (pos_cd_check != null) {
                //neu ky tu dau tien la , thi cat di
                if (pos_cd_check.startsWith(",")) {
                    pos_cd_check = pos_cd_check.substring(1);
                }
                //convert ve kieu list
                List<String> itemspos = Arrays.asList(pos_cd_check.split("\\s*,\\s*"));
                for (String str : itemspos) {
                    //add trang thai la chua co so lieu
                    mapStatusSend.put(str, 6);
                }
                setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
                addActionError("Danh sách PGD chưa có số liệu (Xin kiểm tra lại các pgd này trước khi xác nhận xố liệu)");
//                addActionError("Phòng giao dich " + pos_cd_check + " Chưa có dữ liệu xin kiểm tra lại");
                CoreLogger.error("Phòng giao dich " + pos_cd_check + " Chưa có dữ liệu xin kiểm tra lại");
                return ERROR;
            }

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += hmParameter.get("khoa_bcqt").toString() + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = daosync.getDataSendBcqt(type_bcqt, hmParameter.get("khoa_bcqt").toString(), mapgd, hmParameter.get("ngay_bc").toString());
                //Do có biểu là bán tự động nên vẫn cho gửi file trắng để xác nhận số liệu
//                if (lstData == null || lstData.size() == 0) {
//                    mapStatusSend.put(mapgd, 6);
//                    continue;
//                }
                bStatus_file = new XmlBcqtSync().createXmlFileBcqt(Define.PARA_SYN_REPORT_BCQT, type_bcqt,
                        hmParameter.get("khoa_bcqt").toString(), hmParameter.get("ngay_bc").toString(), UserName,
                        Grade, mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendBCQT: Khong tao duoc file " + strPathSave);
//                    return ERROR;
                    mapStatusSend.put(mapgd, 1);
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
                    mapStatusSend.put(mapgd, 2);
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendBCQT: Khong tao duoc file " + strPathSave);
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
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendLockBCQT: Khong dong bo duoc file " + strPathSave);
//                    return ERROR;
                    mapStatusSend.put(mapgd, 3);
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 4);
                } else {
//                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
//                    return ERROR;
                    mapStatusSend.put(mapgd, 5);
                }
            }
            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendLockBCQT: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendLockBCQT: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");
        return SUCCESS;
    }
//</editor-fold>
    
    public String resetData() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            if(lstPos.size()>1 || lstPos.size() ==0)
            {
                addActionError("Bạn chỉ được reset dữ liệu cho 1 phòng giao dịch");
                return ERROR;
            }
            
            DaoBcqtMain daoMain = DaoBcqtMain.newInstance();
//            String s= hmParameter.get("ngay_bc").toString();
//            System.out.println("Ngaybc="+s);
            
//            String pos_cd_check = daoMain.ResetData(type_bcqt, hmParameter.get("khoa_bcqt").toString(), UserName, hmParameter.get("ngay_bc").toString(),"");
            
            if (!daoMain.ResetData(type_bcqt, hmParameter.get("khoa_bcqt").toString(), UserName, hmParameter.get("ngay_bc").toString(),lstPos.get(0))) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_BCQT_M12A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_BCQT_M12A: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã reset dữ liệu thành công");
        return SUCCESS;
    }

    public String loadDMChtrinh() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            List<ListValue> danhmucChtrinh = daoMain.getLov(Grade, "CHUONGTRINH");
            Map<String, String> mapAllChtrinh = new LinkedHashMap<String, String>();
            for (ListValue value : danhmucChtrinh) {
                mapAllChtrinh.put(value.getsKey(), value.getsDesc());
            }

            setDmChtrinh(mapAllChtrinh);
            setLstCBChuongtrinh(danhmucChtrinh);
            setLstDmkhac(daoMain.getLov(Grade, "DMXAMTIEU_CHIEMDUNG"));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadDMChtrinh: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadDMChtrinh: " + e.getMessage());
            addActionError("Bạn lấy được danh mục chương tình vay. Xin liên hệ với quản trị để được khắc phục");
            setMsgError("Chưa lấy được danh mục. Xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">

    public PosMainModel getPosMainModel() {
        return posMainModel;
    }

    public void setPosMainModel(PosMainModel posMainModel) {
        this.posMainModel = posMainModel;
    }
    

    public DaoListPosFromUser getListKTNBDA() {
        return listKTNBDA;
    }

    public void setListKTNBDA(DaoListPosFromUser listKTNBDA) {
        this.listKTNBDA = listKTNBDA;
    }

    public String getPos_cd_username() {
        return pos_cd_username;
    }

    public void setPos_cd_username(String pos_cd_username) {
        this.pos_cd_username = pos_cd_username;
    }

    
    public List<ListValue> getLstChuongtrinh() {
        return lstChuongtrinh;
    }

    public void setLstChuongtrinh(List<ListValue> lstChuongtrinh) {
        this.lstChuongtrinh = lstChuongtrinh;
    }

    public List<ListValue> getLstMaxa() {
        return lstMaxa;
    }

    public void setLstMaxa(List<ListValue> lstMaxa) {
        this.lstMaxa = lstMaxa;
    }

    public List<ListValue> getLstNguonvon() {
        return lstNguonvon;
    }

    public void setLstNguonvon(List<ListValue> lstNguonvon) {
        this.lstNguonvon = lstNguonvon;
    }
    
    

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }    
       
    public List<ListValue> getLstDmkhac() {
        return lstDmkhac;
    }

    public void setLstDmkhac(List<ListValue> lstDmkhac) {
        this.lstDmkhac = lstDmkhac;
    }

    public Map<String, String> getDmChtrinh() {
        return dmChtrinh;
    }

    public void setDmChtrinh(Map<String, String> dmChtrinh) {
        this.dmChtrinh = dmChtrinh;
    }

    public List<ListValue> getLstCBChuongtrinh() {
        return lstCBChuongtrinh;
    }

    public void setLstCBChuongtrinh(List<ListValue> lstCBChuongtrinh) {
        this.lstCBChuongtrinh = lstCBChuongtrinh;
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

    public List<ReportParam> getLstBcqtParams() {
        return lstBcqtParams;
    }

    public void setLstBcqtParams(List<ReportParam> lstBcqtParams) {
        this.lstBcqtParams = lstBcqtParams;
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

    public List<ListValue> getLstAllBcqt() {
        return lstAllBcqt;
    }

    public void setLstAllBcqt(List<ListValue> lstAllBcqt) {
        this.lstAllBcqt = lstAllBcqt;
    }

    public String getKhoa_bcqt() {
        return khoa_bcqt;
    }

    public void setKhoa_bcqt(String khoa_bcqt) {
        this.khoa_bcqt = khoa_bcqt;
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

     public List<ListValue> getLstMato() {
        return lstMato;
    }

    public void setLstMato(List<ListValue> lstMato) {
        this.lstMato = lstMato;
    }
//</editor-fold>

   
}
