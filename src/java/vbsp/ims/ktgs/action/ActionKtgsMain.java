/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.ktgs.action;

import vbsp.ims.bcqt.action.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bcqt.dao.TmDao;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlBcqtSync;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author LION
 */
public class ActionKtgsMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    protected String Grade;
    protected String UserName;
    protected String Message;
    protected List<ListValue> lstAllKtgs = new ArrayList<>();
    protected List<ReportParam> lstKtgsParams = new ArrayList<>();
    protected String khoa_ktgs;   
    protected TreeNode nodes_pos = new TreeNode();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<ListValue> lstParameters = new ArrayList<>();
    private List<ListValue> lstCBTindung = new ArrayList<ListValue>();
    private List<ListValue> lstCBKetoan = new ArrayList<ListValue>();
    protected List<String> poscd = new ArrayList<String>();
    protected String isDisplayTM = "N";
    protected String type_bcqt;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();
    private String tt_khoa;
    private String macn;
    private String ngay_bc;    
    private String trangthaichotsl;  
    
    private List<ListValue> lstGioiTinh = new ArrayList<ListValue>();
    private List<ListValue> lstDanToc = new ArrayList<ListValue>();
    private List<ListValue> lstDonVi = new ArrayList<ListValue>();
    private List<ListValue> lstChucVu = new ArrayList<ListValue>();
    private List<ListValue> lstTrangThai = new ArrayList<ListValue>();
    private List<ListValue> lstThanhVien = new ArrayList<ListValue>();
    private List<ListValue> lstBDD = new ArrayList<ListValue>();
    
    protected String MATV;
    protected String addedit;
    

    


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
            if(DaoKtgsMain.newInstance().checkUser(UserName)>0)
            {
                setLstAllKtgs(DaoKtgsMain.newInstance().getAllKtgs_SUB());
            }
            else
            {
                setLstAllKtgs(DaoKtgsMain.newInstance().getAllKtgs());
            }
            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadPataKtgs() {
        try {
//            System.err.println("khoa_ktgs=" + khoa_ktgs);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade);
            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            lstKtgsParams = daoMain.getReportParmamsKtgs(conn, khoa_ktgs);
            // BO SUNG PHAN KIEM TRA XEM CO THUYET MINH HAY KO
//            TmDao tmDao = new TmDao();
//            isDisplayTM = tmDao.getCO_TM(khoa_ktgs);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataKtgs: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataKtgs: " + e.getMessage());
        }
        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cho phan gui va xac nhan so lieu">
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

    public String sendKTGS() {
        System.err.println("Vao ham sendKTGS");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoKtgsMain daosync = DaoKtgsMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();
            if(khoa_ktgs.equals("BC00230032") || khoa_ktgs.equals("BC00230033") || khoa_ktgs.equals("BC00230034"))
            {
                lstPos = daosync.getAllPosUser(UserName);
            }

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += hmParameter.get("khoa_ktgs").toString() + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";


                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = daosync.getDataSendKtgs("NT", hmParameter.get("khoa_ktgs").toString(),
                        mapgd, hmParameter.get("ngay_bc").toString());
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_KTGS, "NT",
                        hmParameter.get("khoa_ktgs").toString(), hmParameter.get("ngay_bc").toString(), UserName, Grade,
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
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
    }
   
    
    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cho phan khoa va mo khoa">
    public String LoadParaKtgs_unlock() {
        try {
            System.err.println("khoa_ktgs=" + khoa_ktgs);
            if (!getParaSession()) {
                return ERROR;
            }
            if (khoa_ktgs == null) {
                setKhoa_ktgs("ALL");
            } else {
                if (khoa_ktgs.isEmpty()) {
                    setKhoa_ktgs("ALL");
                }
            }
             if (khoa_ktgs.equals("99")) {
                return "UNLOCK_99";
            }
            Connection conn = new DaoConnect().getConnect();
            DaoKtgsMain daoMain = new DaoKtgsMain();

            lstKtgsParams = daoMain.getReportParmamsKtgs(conn, khoa_ktgs);
            lstParameters = DaoSyncMain.newInstance().getMainPosLock(conn);
            // BO SUNG PHAN KIEM TRA XEM CO THUYET MINH HAY KO
//            TmDao tmDao = new TmDao();
//            isDisplayTM = tmDao.getCO_TM(khoa_ktgs);
//            if (conn != null) {
//                conn.close();
//            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadParaKtgs_unlock: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadParaKtgs_unlock: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String LoadStatusSendCnKtgs() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoKtgsMain daosync = DaoKtgsMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();
            lstDulieuNt = daosync.getStatusSendCn(getType_bcqt(), hmParameter.get("khoa_ktgs").toString(),
                    macn, hmParameter.get("ngay_bc").toString(), 
                    hmParameter.get("khoa_ktgs").toString().equals("ALL")?tt_khoa:Define.WEB_SERVICES_STATUS_SEND);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> LoadStatusSendCnKtgs: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> LoadStatusSendCnKtgs: " + e.getMessage());
            addActionError("Bạn chưa tải được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");
        return SUCCESS;
    }
    
    public String OpenPgdKTGS() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoKtgsMain daosync = DaoKtgsMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();

            setKhoa_ktgs(hmParameter.get("khoa_ktgs").toString());
//            setType_bcqt(hmParameter.get("type_bcqt").toString());
            setMacn(hmParameter.get("macn").toString());
            setNgay_bc(hmParameter.get("ngay_bc").toString());
//            (String type, String khoa,List<String> lstMapgd,  String ngaybc, String tt_khoa,  String username,  String grade)
            if (!daosync.setStatusLock("NT", khoa_ktgs, poscd, hmParameter.get("ngay_bc").toString(),
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
        addActionMessage("Đã mở khóa thành công");
        return SUCCESS;
    }
    
    public String ChotKtgs() {
        System.err.println("Save - ChotKtgs");
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            DaoKtgsMain daoMain = new DaoKtgsMain();
            HashMap hmParameter = getParameter();
            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                addActionError("Bạn phải chọn ngày cuối tháng");
                return ERROR;
            }
            
            int result = daoMain.ChotKtgs(khoa_ktgs, UserName,  hmParameter.get("ngay_bc").toString(),Grade);
            if (result == 0) {
                addActionError("Bạn chưa chốt được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            } else if (result == 1) {
                addActionError("Pgd chưa nhập số liệu nên bạn không thể chốt số liệu");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ChotKtgs: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ChotKtgs: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã chốt dữ liệu thành công");
        return SUCCESS;
    }
    
    public String MoChotKtgsPGD() {
        System.err.println("Save - MoChotKtgsPGD");
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            DaoKtgsMain daoMain = new DaoKtgsMain();
            HashMap hmParameter = getParameter();
            //Kiểm tra ngày truyền vào có phải ngày cuối tháng không?
            String pattern = "dd-MMM-yyyy";
            String sNgayBC = hmParameter.get("ngay_bc").toString();
            if (!sNgayBC.toLowerCase().equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(sNgayBC, pattern, pattern).toLowerCase())) // If last day of month
            {
                addActionError("Bạn phải chọn ngày cuối tháng");
                return ERROR;
            }
            if (poscd.size() <= 0)
                {
                addActionError("Bạn phải chọn PGD để mở chốt");
                return ERROR;
            }
            
            if (Grade.equals("2"))
            {
                int result = daoMain.MoChotKtgsPGD(khoa_ktgs, UserName, hmParameter.get("ngay_bc").toString(),  poscd);
                if (result == 0) {
                    addActionError("Bạn chưa mở duyệt được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            }                        

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> MoChotKtgsPGD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> MoChotKtgsPGD: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã mở duyệt thành công");
        return SUCCESS;
    }
    //</editor-fold>
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">

    public String getTrangthaichotsl() {
        return trangthaichotsl;
    }

    public void setTrangthaichotsl(String trangthaichotsl) {
        this.trangthaichotsl = trangthaichotsl;
    }
    
    
    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }

    public String getAddedit() {
        return addedit;
    }

    public void setAddedit(String addedit) {
        this.addedit = addedit;
    }

    public String getMATV() {
        return MATV;
    }

    public void setMATV(String MATV) {
        this.MATV = MATV;
    }

    public List<ListValue> getLstCBTindung() {
        return lstCBTindung;
    }

    public void setLstCBTindung(List<ListValue> lstCBTindung) {
        this.lstCBTindung = lstCBTindung;
    }

    public List<ListValue> getLstCBKetoan() {
        return lstCBKetoan;
    }

    public void setLstCBKetoan(List<ListValue> lstCBKetoan) {
        this.lstCBKetoan = lstCBKetoan;
    }

    public String getType_bcqt() {
        return type_bcqt;
    }

    public void setType_bcqt(String type_bcqt) {
        this.type_bcqt = type_bcqt;
    }

    public List<ListValue> getLstGioiTinh() {
        return lstGioiTinh;
    }

    public void setLstGioiTinh(List<ListValue> lstGioiTinh) {
        this.lstGioiTinh = lstGioiTinh;
    }

    public List<ListValue> getLstDanToc() {
        return lstDanToc;
    }

    public void setLstDanToc(List<ListValue> lstDanToc) {
        this.lstDanToc = lstDanToc;
    }

    public List<ListValue> getLstDonVi() {
        return lstDonVi;
    }

    public void setLstDonVi(List<ListValue> lstDonVi) {
        this.lstDonVi = lstDonVi;
    }

    public List<ListValue> getLstChucVu() {
        return lstChucVu;
    }

    public void setLstChucVu(List<ListValue> lstChucVu) {
        this.lstChucVu = lstChucVu;
    }

    public List<ListValue> getLstThanhVien() {
        return lstThanhVien;
    }

    public void setLstThanhVien(List<ListValue> lstThanhVien) {
        this.lstThanhVien = lstThanhVien;
    }

    public List<ListValue> getLstTrangThai() {
        return lstTrangThai;
    }

    public void setLstTrangThai(List<ListValue> lstTrangThai) {
        this.lstTrangThai = lstTrangThai;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public List<ReportParam> getLstKtgsParams() {
        return lstKtgsParams;
    }

    public void setLstKtgsParams(List<ReportParam> lstKtgsParams) {
        this.lstKtgsParams = lstKtgsParams;
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

    public List<ListValue> getLstAllKtgs() {
        return lstAllKtgs;
    }

    public void setLstAllKtgs(List<ListValue> lstAllKtgs) {
        this.lstAllKtgs = lstAllKtgs;
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
    
    public String getKhoa_ktgs() {
        return khoa_ktgs;
    }

    public void setKhoa_ktgs(String khoa_ktgs) {
        this.khoa_ktgs = khoa_ktgs;
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

    public List<ListValue> getLstBDD() {
        return lstBDD;
    }

    public void setLstBDD(List<ListValue> lstBDD) {
        this.lstBDD = lstBDD;
    }
//</editor-fold>

    
}
