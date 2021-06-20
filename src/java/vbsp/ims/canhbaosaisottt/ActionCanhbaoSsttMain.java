/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.canhbaosaisottt;

import vbsp.ims.chamdiemcn.*;
import vbsp.ims.chamdiemcn.*;
import vbsp.ims.bcqt.action.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoSyncMain;
import vbsp.ims.bcqt.dao.TmDao;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.canhbaosaisottt.DaoCanhbaoSsttMain;
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
import vbsp.ims.model.Pagination;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT.saveDulieuNT;
import vbsp.ims.define.DefineFun;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.model.DownloadFileInfor;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;

/**
 *
 * @author LION
 */
public class ActionCanhbaoSsttMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    protected String Grade;
    protected String UserName;
    protected String Message;
    protected String khoa_cbss;    
    protected String khoa_detail;   
    protected String codeUpdate;   
    protected String ngay_bc;

    
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();
    public List<File> fileUpload = new ArrayList<>();
    
    public List<String> fileUploadFileName = new ArrayList<>();
    public List<ModelExcelFile> lstExcel = new ArrayList<>();
    private String fileNameNew;

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }

    public String getCodeUpdate() {
        return codeUpdate;
    }

    public void setCodeUpdate(String codeUpdate) {
        this.codeUpdate = codeUpdate;
    }

    public String getKhoa_detail() {
        return khoa_detail;
    }

    public void setKhoa_detail(String khoa_detail) {
        this.khoa_detail = khoa_detail;
    }
    protected TreeNode nodes_pos = new TreeNode();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();   
    protected List<String> poscd = new ArrayList<String>();
    
    protected String mapgd;

    public String getNgay_bc() {
        return ngay_bc;
    }

    public void setNgay_bc(String ngay_bc) {
        this.ngay_bc = ngay_bc;
    }

    public String getMapgd() {
        return mapgd;
    }

    public void setMapgd(String mapgd) {
        this.mapgd = mapgd;
    }

    
    protected String tt_cbss;    
    protected Pagination pagination = new Pagination(50, 1);
    protected List<ListValue> lstAllCbss = new ArrayList<>();
    protected List<ListValue> lstParameters = new ArrayList<>();
    protected List<ReportParam> lstCbssParams = new ArrayList<>();
    protected HttpServletRequest request = null;
    protected String filereport;
    protected String query;
    protected String fileNamelocal;
    protected List<String> lstOfTextFile = new ArrayList<>();
    protected List<DownloadFileInfor> filesList = new ArrayList<>();
    protected List<String> zipFileList = new ArrayList<>();
    protected List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT = new ArrayList<QT_DULIEU_NT.saveDulieuNT_Phi>();

    public List<QT_DULIEU_NT.saveDulieuNT_Phi> getLstsaveNT_DAT() {
        return lstsaveNT_DAT;
    }

    public void setLstsaveNT_DAT(List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT) {
        this.lstsaveNT_DAT = lstsaveNT_DAT;
    }

    public List<String> getLstOfTextFile() {
        return lstOfTextFile;
    }

    public void setLstOfTextFile(List<String> lstOfTextFile) {
        this.lstOfTextFile = lstOfTextFile;
    }

    public List<DownloadFileInfor> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<DownloadFileInfor> filesList) {
        this.filesList = filesList;
    }

    public List<String> getZipFileList() {
        return zipFileList;
    }

    public void setZipFileList(List<String> zipFileList) {
        this.zipFileList = zipFileList;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
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
//            if (DaoCanhbaossMain.newInstance().checkUser(UserName) > 0) {
//                setLstAllBcss(DaoCanhbaossMain.newInstance().getAllBcqt_SUB());
//            } else {
//                setLstAllBcss(DaoCanhbaossMain.newInstance().getAllBcqt(Grade, UserName));
//            }
            setLstAllCbss(DaoCanhbaoSsttMain.newInstance().getAllBcqt(Grade, UserName));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadPataCanhbaoss() {
        try {
//            System.err.println("khoa_cbss=" + khoa_cbss);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoCanhbaoSsttMain daoMain = new DaoCanhbaoSsttMain();
            //khoi tao cho treeview cac pos
            try {
                List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade, khoa_cbss);
                if (Grade.equals("3") || khoa_cbss.equals("CDTT_CN01PL")) {
                    setTreeNodeGrade3(lstModelTree);
                } else {
                    setTreeNodeGrade12(lstModelTree);
                }
            } catch (SQLException e) {
                CoreLogger.error(this.getClass().getName() + " Exception -> for lstModelTree cap bang 2 : " + e.getMessage());
                System.err.println(this.getClass().getName() + " Exception -> for lstModelTree cap bang 2 : " + e.getMessage());
            }
//            lstBcssParams = daoMain.getReportParmams(conn, khoa_cbss);
            lstCbssParams = daoMain.getReportParmams(conn, khoa_cbss, UserName, Grade);
            
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
    
    public String sendCBSS() {
        System.err.println("Vao ham sendCBSS");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoCanhbaoSsttMain daosync = DaoCanhbaoSsttMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();
            lstPos = DaoNhaptaycnMain.newInstance().getAllPosUser(UserName, "PHIUT_001");  //Lấy all pos để gửi


            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += hmParameter.get("khoa_cbss").toString() + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;
                lstData = daosync.getDataSendCBSS("NT", khoa_cbss,
                        mapgd, hmParameter.get("ngay_bc").toString());

                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_CBSS, "NT",
                        hmParameter.get("khoa_cbss").toString(), hmParameter.get("ngay_bc").toString(), UserName, Grade,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCBSS: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCBSS: Khong tao duoc file " + strPathSave);
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
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCBSS: Khong dong bo duoc file " + strPathSave);
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
            CoreLogger.error(this.getClass().getName() + " Exception -> sendCBSS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendCBSS: " + e.getMessage());
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

    
    
    //</editor-fold>

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">
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

    public String getKhoa_cbss() {
        return khoa_cbss;
    }

    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }
    
    

    public void setKhoa_cbss(String khoa_cbss) {
        this.khoa_cbss = khoa_cbss;
    }

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

   
    public String getTt_cbss() {
        return tt_cbss;
    }

    public void setTt_cbss(String tt_cbss) {
        this.tt_cbss = tt_cbss;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<ListValue> getLstAllCbss() {
        return lstAllCbss;
    }

    public void setLstAllCbss(List<ListValue> lstAllCbss) {
        this.lstAllCbss = lstAllCbss;
    }
    
    

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }
    
    
    

//</editor-fold>

    public List<ReportParam> getLstCbssParams() {
        return lstCbssParams;
    }

    public void setLstCbssParams(List<ReportParam> lstCbssParams) {
        this.lstCbssParams = lstCbssParams;
    }

    public List<String> getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(List<String> fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public List<ModelExcelFile> getLstExcel() {
        return lstExcel;
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

    public List<File> getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(List<File> fileUpload) {
        this.fileUpload = fileUpload;
    }



    

}
