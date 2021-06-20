/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.bctccic;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlCICSync;

/**
 *
 * @author BAOANH
 */
public class BCTCCICAction extends ActionSupport {

    //lay ra cap bao cao
    protected String Grade;
    //lay ra user dang nhap
    protected String UserName;
    //message se tra ve
    protected String message;

    private String loai_module;
    private String ma_dn;
    private String nambc;
    private String defaultNambc;
    private List<ListValue> moduleList = new ArrayList<>();
    private List<ListValue> doanhNghiepList = new ArrayList<>();
    private List<ListValue> nambcList = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<String> poscd = new ArrayList<String>();    
    protected TreeNode nodes_pos = new TreeNode();
    private String totalDataView;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();

    
    //<editor-fold defaultstate="collapsed" desc="phan chung">
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
//                    lstParameters.add(new ListValue(parameter, values[0]));
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
//                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            } else {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.equals("poscd")) {
                    paramHashMap.put(parameter, convertStringtoList(values));
                } else {
                    paramHashMap.put(parameter, values[0]);
//                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            }
        }
        return paramHashMap;
    }

//</editor-fold>
    public String execute() {
        try {

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadPageMain() {
        try {

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadPageMain -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
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

    public String loadModuleCic() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            daoBCTCCIC dao = new daoBCTCCIC();
            moduleList = dao.getModuleCic();
            doanhNghiepList = dao.getDoanhnghiepCic(UserName, Grade);
            nambcList = dao.getNambcCic();
            Connection conn = new DaoConnect().getConnect();
            DaoBcqtMain daoMain = new DaoBcqtMain();
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade);
            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            //get pos_cd va url để vào truong hiden
            System.err.println("vao action");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadModuleCic -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadDataDoanhnghiep() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (loai_module == null || loai_module.equals("-1")) {
                addActionError("Bạn phải chọn loại module cần load số liệu");
                return ERROR;
            }
            
            if (nambc == null || nambc.equals("")) {
                addActionError("Bạn phải chọn năm báo cáo tài chính của doanh nghiệp");
                return ERROR;
            }
            
            daoBCTCCIC dao = new daoBCTCCIC();
            
            if(Grade.equals("1"))
            {
                if (ma_dn == null || ma_dn.equals("")) {
                addActionError("Bạn phải chọn mã doanh nghiệp cần load số liệu");
                return ERROR;
                }
//                totalDataView = dao.loadDataTotalDoanhnghiep(loai_module, Integer.parseInt(nambc), ma_dn, UserName, Grade,poscd);

                lstDulieuNt = dao.loadDataDoanhnghiep(loai_module, Integer.parseInt(nambc), ma_dn, UserName, Grade,poscd);
                return loai_module;
            }
            else if(Grade.equals("2"))
            {
                if (ma_dn == null || ma_dn.equals("")) {
                addActionError("Bạn phải chọn mã doanh nghiệp cần load số liệu");
                return ERROR;
                }
                totalDataView = dao.loadDataTotalDoanhnghiep(loai_module, Integer.parseInt(nambc), ma_dn, UserName, Grade,poscd);

                lstDulieuNt = dao.loadDataDoanhnghiep(loai_module, Integer.parseInt(nambc), ma_dn, UserName, Grade,poscd);
                return loai_module;
            }
            else
            {
                HashMap hmParameter = getParameter();
                
                List<String> lstPos = (List<String>) hmParameter.get("poscd");
                lstDulieuNt = dao.getStatusSendCn("NT", hmParameter.get("loai_module").toString(),
                    poscd, hmParameter.get("nambc").toString(), 
                    hmParameter.get("loai_module").toString().equals("ALL")?loai_module:Define.WEB_SERVICES_STATUS_SEND);

                return "checksend";
            }
            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadDataDoanhnghiep -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
//        return SUCCESS;
    }

    public String saveBctcCic() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (loai_module == null || loai_module.equals("-1")) {
                addActionError("Bạn phải chọn loại module cần load số liệu");
                return ERROR;
            }
            if (ma_dn == null || ma_dn.equals("")) {
                addActionError("Bạn phải chọn mã doanh nghiệp cần load số liệu");
                return ERROR;
            }
            if (nambc == null || nambc.equals("")) {
                addActionError("Bạn phải chọn năm báo cáo tài chính của doanh nghiệp");
                return ERROR;
            }
            if(lstDulieuNt==null||lstDulieuNt.size()<1)
            {
                addActionError("Không thể load được dữ liệu bạn đã nhập");
                return ERROR;
            }
            daoBCTCCIC dao = new daoBCTCCIC();
            dao.saveDataDoanhnghiep(loai_module, Integer.parseInt(nambc), ma_dn, UserName, Grade, lstDulieuNt);
        } catch (SQLException e) {
                addActionError("Lỗi bạn không thể lưu dữ liệu, xin liên hệ với quản trị để khắc phục." + e.getMessage().substring(e.getMessage().indexOf("<messageError>"), e.getMessage().indexOf("</messageError>")));
            return ERROR;
        }

        addActionMessage("Bạn đã lưu dứ liệu thành công");
        return SUCCESS;
    }

    public String sendCIC() {
        System.err.println("Vao ham sendCIC");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            daoBCTCCIC dao = new daoBCTCCIC();
            Map<String, Integer> mapStatusSend = new HashMap();            

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += hmParameter.get("loai_module").toString() + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";


                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = dao.getDataSendCIC("NT", hmParameter.get("loai_module").toString(),
                        mapgd, hmParameter.get("nambc").toString());
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlCICSync().createXmlFileCIC(Define.PARA_SYN_REPORT_CIC, "NT",
                        hmParameter.get("loai_module").toString(), hmParameter.get("nambc").toString(), UserName, Grade,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong tao duoc file " + strPathSave);
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
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong dong bo duoc file " + strPathSave);
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
            CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendCIC: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
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
    
    
    
    //<editor-fold defaultstate="collapsed" desc="get/set">
    
    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }
    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }
    
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public String getLoai_module() {
        return loai_module;
    }

    public void setLoai_module(String loai_module) {
        this.loai_module = loai_module;
    }

    public List<ListValue> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ListValue> moduleList) {
        this.moduleList = moduleList;
    }

    public List<ListValue> getDoanhNghiepList() {
        return doanhNghiepList;
    }

    public void setDoanhNghiepList(List<ListValue> doanhNghiepList) {
        this.doanhNghiepList = doanhNghiepList;
    }

    public String getMa_dn() {
        return ma_dn;
    }

    public void setMa_dn(String ma_dn) {
        this.ma_dn = ma_dn;
    }

    public String getNambc() {
        return nambc;
    }

    public void setNambc(String nambc) {
        this.nambc = nambc;
    }

    public List<ListValue> getNambcList() {
        return nambcList;
    }

    public void setNambcList(List<ListValue> nambcList) {
        this.nambcList = nambcList;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public String getDefaultNambc() {
        return String.valueOf(Calendar.getInstance().get(Calendar.YEAR) - 1);
    }

    public void setDefaultNambc(String defaultNambc) {
        this.defaultNambc = defaultNambc;
    }
    
    //</editor-fold>

    public String getTotalDataView() {
        return totalDataView;
    }

    public void setTotalDataView(String totalDataView) {
        this.totalDataView = totalDataView;
    }

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }


}
