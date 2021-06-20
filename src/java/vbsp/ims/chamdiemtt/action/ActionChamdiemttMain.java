/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

import vbsp.ims.bcqt.action.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bcqt.dao.TmDao;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemtt.dao.DaoChamdiemttMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlBcqtSync;
import vbsp.ims.xml.XmlKtgsSync;
import vbsp.ims.define.Define;

/**
 *
 * @author LION
 */
public class ActionChamdiemttMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    protected String Grade;
    protected String UserName;
    protected String Message;
    protected String tableDetail;
    protected String excelDetail;
    protected String thuyetminh;
    protected String khoa_cdtt;
    protected String pos_string;

    public List<QT_DULIEU_NT> getLstDulieuNt_TH() {
        return lstDulieuNt_TH;
    }

    public void setLstDulieuNt_TH(List<QT_DULIEU_NT> lstDulieuNt_TH) {
        this.lstDulieuNt_TH = lstDulieuNt_TH;
    }
    protected TreeNode nodes_pos = new TreeNode();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstDulieuNt_TH = new ArrayList<>();
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
    protected String tt_cdtt;
    protected String RULEUSER;
    
    protected List<DeNghiLoaiTru> lstDeNghiLoaiTru = new ArrayList<>();

    public String getPos_string() {
        return pos_string;
    }

    public void setPos_string(String pos_string) {
        this.pos_string = pos_string;
    }

    public String getThuyetminh() {
        return thuyetminh;
    }

    public void setThuyetminh(String thuyetminh) {
        this.thuyetminh = thuyetminh;
    }

    public String getTableDetail() {
        return tableDetail;
    }

    public void setTableDetail(String tableDetail) {
        this.tableDetail = tableDetail;
    }

    public String getRULEUSER() {
        return RULEUSER;
    }

    public void setRULEUSER(String RULEUSER) {
        this.RULEUSER = RULEUSER;
    }
    protected String TT_DUYET;

    private List<ListValue> lstPhongBan = new ArrayList<ListValue>();
    private List<ListValue> lstDatKhong = new ArrayList<ListValue>();
    private List<ListValue> lstDambao = new ArrayList<ListValue>();
    
    private List<ListValue> lstUser = new ArrayList<ListValue>();
    private List<ListValue> lstFuncTTBC = new ArrayList<ListValue>();

    public String getTT_DUYET() {
        return TT_DUYET;
    }

    public List<ListValue> getLstUser() {
        return lstUser;
    }

    public void setLstUser(List<ListValue> lstUser) {
        this.lstUser = lstUser;
    }

    public List<ListValue> getLstFuncTTBC() {
        return lstFuncTTBC;
    }

    public void setLstFuncTTBC(List<ListValue> lstFuncTTBC) {
        this.lstFuncTTBC = lstFuncTTBC;
    }

    public List<ListValue> getLstPhongBan() {
        return lstPhongBan;
    }

    public void setLstPhongBan(List<ListValue> lstPhongBan) {
        this.lstPhongBan = lstPhongBan;
    }

    public List<ListValue> getLstDatKhong() {
        return lstDatKhong;
    }

    public void setLstDatKhong(List<ListValue> lstDatKhong) {
        this.lstDatKhong = lstDatKhong;
    }

    public List<ListValue> getLstDambao() {
        return lstDambao;
    }

    public void setLstDambao(List<ListValue> lstDambao) {
        this.lstDambao = lstDambao;
    }

    public void setTT_DUYET(String TT_DUYET) {
        this.TT_DUYET = TT_DUYET;
    }

    public String getTt_cdtt() {
        return tt_cdtt;
    }

    public void setTt_cdtt(String tt_cdtt) {
        this.tt_cdtt = tt_cdtt;
    }

    protected List<ReportParam> lstCdttParams = new ArrayList<>();

    public List<ReportParam> getLstCdttParams() {
        return lstCdttParams;
    }

    public void setLstCdttParams(List<ReportParam> lstCdttParams) {
        this.lstCdttParams = lstCdttParams;
    }

    protected List<ListValue> lstAllCdtt = new ArrayList<>();

    public List<ListValue> getLstAllCdtt() {
        return lstAllCdtt;
    }

    public void setLstAllCdtt(List<ListValue> lstAllCdtt) {
        this.lstAllCdtt = lstAllCdtt;
    }

    protected String MACT;

    public String getMACT() {
        return MACT;
    }

    public void setMACT(String MACT) {
        this.MACT = MACT;
    }

    public List<String> getLstDat() {
        return lstDat;
    }

    public void setLstDat(List<String> lstDat) {
        this.lstDat = lstDat;
    }

    public List<DeNghiLoaiTru> getLstDeNghiLoaiTru() {
        return lstDeNghiLoaiTru;
    }

    public void setLstDeNghiLoaiTru(List<DeNghiLoaiTru> lstDeNghiLoaiTru) {
        this.lstDeNghiLoaiTru = lstDeNghiLoaiTru;
    }

    public String getExcelDetail() {
        return excelDetail;
    }

    public void setExcelDetail(String excelDetail) {
        this.excelDetail = excelDetail;
    }    
    
    private List<ListValue> lstGioiTinh = new ArrayList<ListValue>();
    private List<ListValue> lstDanToc = new ArrayList<ListValue>();
    private List<ListValue> lstDonVi = new ArrayList<ListValue>();
    private List<ListValue> lstChucVu = new ArrayList<ListValue>();
    private List<ListValue> lstTrangThai = new ArrayList<ListValue>();
    private List<ListValue> lstThanhVien = new ArrayList<ListValue>();
    private List<ListValue> lstBDD = new ArrayList<ListValue>();

    protected List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT = new ArrayList<QT_DULIEU_NT.saveDulieuNT_Phi>();
    protected String addedit;
    protected List<String> lstDat = new ArrayList<String>();

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Xu ly cho action">
    //<editor-fold defaultstate="collapsed" desc="Cho phan khoi tao form chinh">
    protected boolean setTreeNodeGrade3(List<ModelTreeNode> lstModelTree) {

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

    protected boolean setTreeNodeGrade12(List<ModelTreeNode> lstModelTree) {

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

    protected List<String> convertStringtoList(String[] value) {
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
        try {

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
        } catch (Exception e) {
            throw new Exception(e);
        }
        return paramHashMap;
    }

    public String execute() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
//            if (DaoChamdiemttMain.newInstance().checkUser(UserName) > 0) {
//                setLstAllCdtt(DaoChamdiemttMain.newInstance().getAllBcqt_SUB());
//            } else {
//                setLstAllCdtt(DaoChamdiemttMain.newInstance().getAllBcqt(Grade, UserName));
//            }
            setLstAllCdtt(DaoChamdiemttMain.newInstance().getAllBcqt(Grade, UserName));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadPataChamdiemtt() {
        try {
//            System.err.println("khoa_cdtt=" + khoa_cdtt);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade, khoa_cdtt);
            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
//            lstCdttParams = daoMain.getReportParmams(conn, khoa_cdtt);
            lstCdttParams = daoMain.getReportParmams(conn, khoa_cdtt, UserName, Grade);
            if(!khoa_cdtt.equals("CDTT_CN08A") && !khoa_cdtt.equals("CDTT_CN08B"))
            {
                int iRule = daoMain.checkRuleUser(UserName, Grade);
                setRULEUSER(String.valueOf(iRule));
            }
            else
            {
                int iRule = daoMain.checkRuleUser_CN08AB(UserName, Grade,khoa_cdtt);
                setRULEUSER(String.valueOf(iRule));
            }

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataBcqt: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataBcqt: " + e.getMessage());
            addActionError("Bạn không có quyền với chức năng này !");
            return ERROR;
        }
        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cho phan gui va xac nhan so lieu">
    private List<ModelViewSend> getViewStatusSend(List<String> lstPos, Map<String, Integer> mapStatus) {
        addActionMessage("Danh sách các đơn vị gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();
        try {
            Map<String, String> mapPosByName = DaoChamdiemttMain.newInstance().getPosByName(lstPos);

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
            if (lstStatus.size() > 0) {
                Collections.sort(lstStatus, new Comparator<ModelViewSend>() {
                    @Override
                    public int compare(ModelViewSend o1, ModelViewSend o2) {
                        return  o1.getMapgd().compareTo(o2.getMapgd());
                    }
                });
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
        }
        return lstStatus;
    }

    public String sendCDTT() {
        System.err.println("Vao ham sendKTGS");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoChamdiemttMain daosync = DaoChamdiemttMain.newInstance();

            String kiemtranhaplieu = daosync.kiemtranhaplieu(khoa_cdtt, hmParameter.get("ngay_bc").toString(), Grade, UserName);
            if (kiemtranhaplieu != null && !kiemtranhaplieu.isEmpty()) {
                addActionError(kiemtranhaplieu);
                return ERROR;
            }
            Map<String, Integer> mapStatusSend = new HashMap();
            if (khoa_cdtt.equals("CDTT_CN")) {
                lstPos = daosync.getAllPosUser(UserName, hmParameter.get("khoa_cdtt").toString());
            }

            for (String mapgd : lstPos) {
                SimpleDateFormat format = new SimpleDateFormat("dd-MMM-yyyy");
                Date date = format.parse(hmParameter.get("ngay_bc").toString());

                DateFormat df = new SimpleDateFormat("ddMMyyyy");
                String str2 = df.format(date);

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += hmParameter.get("khoa_cdtt").toString() + "_" + mapgd
                        + "_" + UserName + "_" + str2 + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = daosync.getDataSendCdtt("NT", hmParameter.get("khoa_cdtt").toString(),
                        mapgd, hmParameter.get("ngay_bc").toString());
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_CDTT, "NT",
                        hmParameter.get("khoa_cdtt").toString(), hmParameter.get("ngay_bc").toString(), UserName, Grade,
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
//                        checkfile.delete();
                    }
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendKTGS: Khong dong bo duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
//                    return ERROR;
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    List<String> list1Pos = new ArrayList<>();
                    list1Pos.add(mapgd);
                    daosync.lockCDTT_pgd(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), list1Pos, Define.CDTT_CN_SEND_LOCK_CMNV, Grade);
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

    public String TuchoiCDTT() {
        System.err.println("Vao ham TuchoiCDTT");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoChamdiemttMain daosync = DaoChamdiemttMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();
            if (!daosync.lockCDTT_pgd(khoa_cdtt, UserName, hmParameter.get("ngay_bc").toString(), lstPos, Define.CDTT_CN_UNLOCK_PGD, Grade)) {
                addActionError("Bạn chưa chốt được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }
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
    //</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">
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

    public String getKhoa_cdtt() {
        return khoa_cdtt;
    }

    public void setKhoa_cdtt(String khoa_cdtt) {
        this.khoa_cdtt = khoa_cdtt;
    }

    public List<QT_DULIEU_NT.saveDulieuNT_Phi> getLstsaveNT_DAT() {
        return lstsaveNT_DAT;
    }

    public void setLstsaveNT_DAT(List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT) {
        this.lstsaveNT_DAT = lstsaveNT_DAT;
    }
//</editor-fold>

}
