/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.traiphieucp;

import vbsp.ims.nghiquyet11cp.*;
import vbsp.ims.nhaptaycn.action.*;
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
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT.saveDulieuNT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT.saveDulieuNT_Phi;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDcptNo;
import vbsp.ims.dao.DaoRptQuery;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.Define;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.Pagination;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.sbv.daoSbv;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKtgsSync;
//import vbsp.ims.xml.XmlNhaptaycnSync;

/**
 *
 * @author LION
 */
public class ActionTraiphieuCp extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    String fileNamelocal;
    String filereport;
    public String reportId; //CuongBM: Ma bao cao
    public List<File> fileUpload = new ArrayList<>();
    private List<String> fileUploadContentType = new ArrayList<>();
    public List<String> fileUploadFileName = new ArrayList<>();
    public List<ModelExcelFile> lstExcel = new ArrayList<>();
    private String fileNameNew;
    public String message;
    protected List<ListValue> lstNamBC = new ArrayList<>();

    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;

    protected String pos_cd_username;

    public HttpServletRequest request = null;
    public String query;

    public HttpServletRequest getRequest() {
        return request;
    }

    
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<File> getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(List<File> fileUpload) {
        this.fileUpload = fileUpload;
    }

    public List<String> getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(List<String> fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
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

    public String getReportId() {
        return reportId;
    }

    public List<ListValue> getLstNamBC() {
        return lstNamBC;
    }

    public void setLstNamBC(List<ListValue> lstNamBC) {
        this.lstNamBC = lstNamBC;
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
    protected String Grade;
    protected String UserName;

    protected String Message;
    protected String khoa_traiphieucp;
    protected List<ListValue> lstAllBaocao = new ArrayList<>();
    protected List<ReportParam> lstNhaptaycnParams = new ArrayList<>();
    protected TreeNode nodes_pos = new TreeNode();

    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstDulieuNt_tong = new ArrayList<>();
    protected List<QT_DULIEU_NT_50> lstDulieuNt50 = new ArrayList<>();

    protected List<ListValue> lstChuongtrinh = new ArrayList<ListValue>();
    protected List<ListValue> lstMaxa = new ArrayList<ListValue>();
    protected List<ListValue> lstMato = new ArrayList<ListValue>();
    protected List<ListValue> lstPhanloai = new ArrayList<ListValue>();
    protected List<ListValue> lstGiaiNgan = new ArrayList<ListValue>();
    protected List<ListValue> lstNhadautu = new ArrayList<ListValue>();

    public List<QT_DULIEU_NT> getLstDulieuNt_tong() {
        return lstDulieuNt_tong;
    }

    public void setLstDulieuNt_tong(List<QT_DULIEU_NT> lstDulieuNt_tong) {
        this.lstDulieuNt_tong = lstDulieuNt_tong;
    }

    protected List<ListValue> lstParameters = new ArrayList<>();
    protected List<String> poscd = new ArrayList<String>();
    protected String poslist;
    protected String isDisplayTM = "N";
    protected String type_bcqt;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();
    private String tt_khoa;
    private String macn;
    private String ngay_bc;
    public String chotsl;

    protected String totalDataView;

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
        posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
        pos_cd_username = posMainModel.getPosCd();

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
            setLstAllBaocao(DaoNghiquyet11cp.newInstance().getAllBaocao(Grade,"TRAIPHIEUCP"));

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadParaBaocao() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade, khoa_traiphieucp);
            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            if(khoa_traiphieucp.equals("TRAIPHIEU_001"))
                return SUCCESS;
            else if(khoa_traiphieucp.equals("TRAIPHIEU_002"))
            {
                lstNamBC = new XDKHDao2021().getLOV(UserName, "NAM_TP");
                return "phi_phaitra";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadParaBaocao: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadParaBaocao: " + e.getMessage());
            return ERROR;
        }       
        return SUCCESS;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">

    public String getChotsl() {
        return chotsl;
    }

    public void setChotsl(String chotsl) {
        this.chotsl = chotsl;
    }


    
    
    public String getPoslist() {
        return poslist;
    }

    public void setPoslist(String poslist) {
        this.poslist = poslist;
    }

    public String getTotalDataView() {
        return totalDataView;
    }

    public void setTotalDataView(String totalDataView) {
        this.totalDataView = totalDataView;
    }

    public List<QT_DULIEU_NT_50> getLstDulieuNt50() {
        return lstDulieuNt50;
    }

    public void setLstDulieuNt50(List<QT_DULIEU_NT_50> lstDulieuNt50) {
        this.lstDulieuNt50 = lstDulieuNt50;
    }

    public List<ListValue> getLstGiaiNgan() {
        return lstGiaiNgan;
    }

    public void setLstGiaiNgan(List<ListValue> lstGiaiNgan) {
        this.lstGiaiNgan = lstGiaiNgan;
    }

    public List<ListValue> getLstNhadautu() {
        return lstNhadautu;
    }

    public void setLstNhadautu(List<ListValue> lstNhadautu) {
        this.lstNhadautu = lstNhadautu;
    }

    public List<ListValue> getLstMato() {
        return lstMato;
    }

    public void setLstMato(List<ListValue> lstMato) {
        this.lstMato = lstMato;
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

    public List<ListValue> getLstPhanloai() {
        return lstPhanloai;
    }

    public void setLstPhanloai(List<ListValue> lstPhanloai) {
        this.lstPhanloai = lstPhanloai;
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

    public List<ReportParam> getLstNhaptaycnParams() {
        return lstNhaptaycnParams;
    }

    public void setLstNhaptaycnParams(List<ReportParam> lstNhaptaycnParams) {
        this.lstNhaptaycnParams = lstNhaptaycnParams;
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

    public List<ListValue> getLstAllBaocao() {
        return lstAllBaocao;
    }

    public void setLstAllBaocao(List<ListValue> lstAllBaocao) {
        this.lstAllBaocao = lstAllBaocao;
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

    public String getKhoa_traiphieucp() {
        return khoa_traiphieucp;
    }

    public void setKhoa_traiphieucp(String khoa_traiphieucp) {
        this.khoa_traiphieucp = khoa_traiphieucp;
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

    public String getPos_cd_username() {
        return pos_cd_username;
    }

    public void setPos_cd_username(String pos_cd_username) {
        this.pos_cd_username = pos_cd_username;
    }
    //</editor-fold>

}
