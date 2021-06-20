/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.phts;

import vbsp.ims.phts.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URI;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.jxls.common.Context;
import vbsp.ims.core.ColumnReportTemplate;
import vbsp.ims.core.GroupDataClass;
import vbsp.ims.core.MappingClassValue;
import vbsp.ims.core.Node;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.xml.imsQueryConfig;
import vbsp.ims.xml.imsTemplateConfig;
import vbsp.ims.xml.imsXmlTemplateConfig;
import vbsp.ims.xml.xmlTemplateConfig;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;
import vbsp.ims.query.PlSqlQueryExecuterIms;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.excel.exportDataExcelTemplate;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.model.DownloadFileInfor;
import vbsp.ims.model.ExportText2SbvManager;
import vbsp.ims.model.FileInfo;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKtgsSync;
import vbsp.ims.zip.FileZip;

/**
 *
 * @author BAOANH
 */
public class actionPhts extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien cho xuat bao cao">
    private static List<String> lstOfTextFile = new ArrayList<>();
    private static List<DownloadFileInfor> filesList = new ArrayList<>();
    private static final List<String> zipFileList = new ArrayList<>();

    protected String Grade; //lay cho cấp báo cáo
    protected String UserName;//lấy cho user
    protected String Message;//cho message
    private String tonghop;
    private String mainContent;
    private String cnContent;
    private String ContentMTK;
    private String ContentTT35;
    
    protected String contentPhanhoi;
    protected String contentPhanhoi_input;
    
    protected String contentPhanhoiTT35;
    
    
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();

    //Bien cho doc xml tu file excel
    private imsTemplateConfig objExcelTemplate = imsTemplateConfig.newInstance();
    private xmlTemplateConfig xmlConfig = imsXmlTemplateConfig.newInstance();

    //Khai bao cac doi tuong cho tham so
    private List<ListValue> lstLoaibc = new ArrayList<ListValue>();
    private List<ListValue> lstKybc = new ArrayList<ListValue>();

    private List<ListValue> lstLoaifile = new ArrayList<ListValue>();
    private List<ListValue> lstMacn = new ArrayList<ListValue>();
    private List<ListValue> lstLanxuat = new ArrayList<ListValue>();
    //Khai bao bien lay cho action
    private String loai_bc, ky_bc, loai_file, macn, ngay_bc,ngaygui;

    private String defaultky_bc;
    private String defaultloai_bc;
    private String defaultloai_file;
    private String defaultlan_xuat;

    protected List<ListValue> lstParameters = new ArrayList<>();

//    protected TreeNode tree_bc = new TreeNode();
    protected List<TreeNode> tree_bc = new ArrayList<TreeNode>();

    protected List<String> ma_bc = new ArrayList<String>();
    protected List<String> pos_cd = new ArrayList<String>();
    private List<ListValue> lstObjRpt = new ArrayList<>();//cho select các báo báo
    private String fileNamelocal;
    private String filereport;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get session, parameter">
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cac ham cho action">
    
    public String tranPhts() {
        try {
            System.err.println("tranPhts--");   
            
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            daoPhts daoMain = new daoPhts();
            //khoi tao cho treeview cac pos                   
            pos_cd.remove("999999");
            lstDulieuNt = daoMain.transferDataPhts(conn,"PHTS_001",ngay_bc,UserName,Grade,pos_cd,mainContent);
            if (conn != null) {
                conn.close();
            } 
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadParameters -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    
    public String tranPhtsInput() {
        try {
            System.err.println("tranPhtsInput--");   
            
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            HttpServletRequest request = ServletActionContext.getRequest();
            String name12 = request.getParameter(" ");
            
            daoPhts daoMain = new daoPhts();
            //khoi tao cho treeview cac pos                   
            pos_cd.add(macn);
            lstDulieuNt = daoMain.transferDataPhts(conn,"PHTS_001",ngay_bc,UserName,Grade,pos_cd,cnContent);
            if (conn != null) {
                conn.close();
            } 
            if(Grade.equals("2"))
            {
                SendPhts("PHTS_001",macn,ngay_bc);
            }            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadParameters -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String tranPhtsInputMTK() {
        try {
            System.err.println("tranPhtsInputMTK--");   
            
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            HttpServletRequest request = ServletActionContext.getRequest();
            String name12 = request.getParameter(" ");
            
            daoPhts daoMain = new daoPhts();
            //khoi tao cho treeview cac pos                   
            pos_cd.add(macn);
            lstDulieuNt = daoMain.transferDataPhts(conn,"PHTS_002",ngay_bc,UserName,Grade,pos_cd,ContentMTK);
            if (conn != null) {
                conn.close();
            } 
            if(Grade.equals("2"))
            {
                SendPhts("PHTS_002",macn,ngay_bc);
            }            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadParameters -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    public String tranPhtsInputTT35() {
        try {
            System.err.println("tranPhtsInputTT35--");   
            
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            HttpServletRequest request = ServletActionContext.getRequest();
            String name12 = request.getParameter(" ");
            
            daoPhts daoMain = new daoPhts();
            //khoi tao cho treeview cac pos                   
            pos_cd.add(macn);
            lstDulieuNt = daoMain.transferDataPhts(conn,"PHTS_003",ngay_bc,UserName,Grade,pos_cd,ContentTT35);
            if (conn != null) {
                conn.close();
            } 
            if(Grade.equals("2"))
            {
                SendPhts("PHTS_003",macn,ngay_bc);
            }            
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadParameters -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    public void SendPhts(String sKhoa, String smapgd, String sNgaybc)
    {    
        String sStatusSend = "";
        String iStatus = "";
        try {
            if (!getParaSession()) {
                return ;
            }
            HashMap hmParameter = getParameter();            
            daoPhts daoMain = new daoPhts();
            Map<String, Integer> mapStatusSend = new HashMap();
            ServletContext context = ServletActionContext.getServletContext();
            String strPathSave = !context.getRealPath("/").endsWith("/")
                    ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                    : context.getRealPath("/") + Define.M_REPORT_XML;
            strPathSave += sKhoa + "_"  + smapgd
                    + "_" + UserName + "_"
                    + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";


            List<String> lstData = new ArrayList<>();
            boolean bStatus_file = false;

            lstData = daoMain.getDataSendPhts("NT", sKhoa,
                    smapgd, sNgaybc);
//            java.sql.Date date_ngay_bc = new java.sql.Date(new SimpleDateFormat("dd/mm/yyyy").parse(sNgaybc).getTime());
            Date date_ngay_bc = new SimpleDateFormat("dd/MM/yyyy").parse(sNgaybc);
//            DateFormat df = new SimpleDateFormat("DD-MON-YYYY");
//            DateFormat df =  new SimpleDateFormat("dd-MMM-yyyy").format(date_ngay_bc);
            
            bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_PHTS, "NT",
                    "PHTS_001", new SimpleDateFormat("dd-MMM-yyyy").format(date_ngay_bc), UserName, Grade,
                    smapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

            if (!bStatus_file) {
            //                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                CoreLogger.error(this.getClass().getName() + " Exception -> sendphts001: Khong tao duoc file " + strPathSave);
                sStatusSend = "5";
//                mapStatusSend.put(smapgd, 1); //1 la tao file xml bi loi
//                daoMain.updateStatusSendPhts(sKhoa,  sMapgd,  sNgaybc, 1);
            //                    return ERROR;
            }
            //Tao file xml theo cau truc
            //
            File checkfile = new File(strPathSave);
            if (!checkfile.exists()) {
            //                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                CoreLogger.error(this.getClass().getName() + " Exception -> sendphts001: Khong tao duoc file " + strPathSave);
//                mapStatusSend.put(smapgd, 2); //2 la khong tim thay file xml
                sStatusSend = "6";
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
                CoreLogger.error(this.getClass().getName() + " Exception -> sendphts001: Khong dong bo duoc file " + strPathSave);
                mapStatusSend.put(smapgd, 3); //3 la gui file du lieu bi loi
                iStatus = "3";
            //                    return ERROR;
            } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
            //                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                if (checkfile.exists()) {
                    checkfile.delete();
                }
                mapStatusSend.put(smapgd, 4);  //gui du lieu thanh cong
                iStatus = "4";
            } else {
            //                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                if (checkfile.exists()) {
                    checkfile.delete();
                }
                mapStatusSend.put(smapgd, 5);  //pgd bi khoa khong gui duoc du lieu
                iStatus = "5";
            //                    return ERROR;
            }
            daoMain.updateStatusSendPhts(sKhoa,  smapgd,  sNgaybc, iStatus);
//            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendphts001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendphts001: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");            
        }
    }
    
    public String getMainDataPhts() {
        try {
            System.err.println("getMainDataPhts");  
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            daoPhts daoMain = new daoPhts();
            //khoi tao cho treeview cac pos            
            lstDulieuNt = daoMain.getMainDataPhts(conn, ngay_bc,UserName,Grade);
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getMainDataPhts: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getMainDataPhts: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String mathongke() {
        try {
            System.err.println("mathongke" + macn);  
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            daoPhts daoMain = new daoPhts();
            //khoi tao cho treeview cac pos            
            contentPhanhoi = daoMain.getContent("PHTS_002", Grade,macn,ngay_bc,"0");
            contentPhanhoi_input = daoMain.getContent("PHTS_002", Grade,macn,ngay_bc,"1");
            lstDulieuNt = daoMain.getMathongkePhts(conn, ngay_bc,macn,Grade);
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> mathongke: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> mathongke: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String maubieutt35() {
        try {
            System.err.println("maubieutt35 " + macn);    
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            daoPhts daoMain = new daoPhts();
            //khoi tao cho treeview cac pos  
            contentPhanhoiTT35 = daoMain.getContent("PHTS_003", Grade,macn,ngay_bc,"0");
            
            lstDulieuNt = daoMain.getMaubieutt35Phts(conn, ngay_bc,macn,Grade);
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> maubieutt35: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> maubieutt35: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    public String nhapthucong() {
        try {
            System.err.println("nhapthucong");   
            if (!getParaSession()) {
                            return ERROR;
                        }            
            HashMap hmParameter = getParameter();            
            Connection conn = new DaoConnect().getConnect();
            daoPhts daoMain = new daoPhts();
            //khoi tao cho treeview cac pos            
            lstDulieuNt = daoMain.getNhapthucongPhts(conn,"PHTS_001" ,UserName,Grade,ngay_bc,macn);
            if (conn != null) {
                conn.close();
            }            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> nhapthucong: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> nhapthucong: " + e.getMessage());
        }
        return SUCCESS;
    }
    
    
                
    
    
    public String loadParameters() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
//            HashMap<String, List<ListValue>> hmParameter = daoPhts.newInstance().getDmParaExp(UserName, Grade);
//
//            setLstKybc(hmParameter.get("ky_bc"));
//            setLstLoaibc(hmParameter.get("loai_bc"));
//            setLstLoaifile(hmParameter.get("loai_file"));
//            setLstMacn(hmParameter.get("macn"));
//            setLstLanxuat(hmParameter.get("lan_xuat"));
//            setKy_bc("1");
//            setDefaultloai_bc("S");
//            setDefaultloai_file("M");
//            setDefaultlan_xuat("1");
//            setMacn("000100");
//            setDefaultky_bc("1");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadParameters -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    

    public String reloadTree() {
        try {
            System.err.println("Vao day reloadTree");
            tree_bc = new ArrayList<TreeNode>();
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmpara = getParameter();
//            System.err.println("kybc=" + ky_bc);
//            if (ky_bc == null) {
//            HashMap<String, List<ListValue>> hmParameter = daoPhts.newInstance().getDmParaExp(UserName, Grade);
//            setLstKybc(hmParameter.get("ky_bc"));
//            }
            List<ModelTreeNode> lstNode = daoPhts.newInstance().getDataPosTreeNode(UserName, Grade);
//            setTreeNodeGrade12(lstNode);
//            ngay_bc=hmpara.get("ngay_bc").toString();
//            System.err.println("ngay_bc="+ngay_bc);
            TreeNode nodeParent = new TreeNode();

            for (int i = 0; i < lstNode.size(); i++) {
                ModelTreeNode modelTree = lstNode.get(i);
                if (i == 0) {
                    nodeParent.setId(modelTree.getStrParentCd());
                    nodeParent.setTitle(modelTree.getStrParentDesc());
                    nodeParent.setState(TreeNode.NODE_STATE_OPEN);
                    nodeParent.setChildren(new LinkedList<TreeNode>());
                }

                TreeNode node = new TreeNode();
                node.setId(modelTree.getStrChildCd());
                node.setTitle(modelTree.getStrChildDesc());
                node.setState(TreeNode.NODE_STATE_LEAF);
                nodeParent.getChildren().add(node);

            }
            tree_bc.add(nodeParent);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " exportSbv -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadAllRptExp() {
        try {
//            System.err.println("Vao day");
//            System.err.println("kybc=" + ky_bc);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            if (ky_bc == null) {
                setKy_bc("1");
            }
            lstObjRpt = daoPhts.newInstance().getLoadAllQuery(ky_bc, Grade,"M");
//            setLstObjRpt(daoPhts.newInstance().getLoadAllQuery(ky_bc, Grade,loai_file));
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadAllRptExp -> " + e.getMessage());
        }
//        System.err.println(lstObjRpt.size());
        return SUCCESS;
    }
    
    public String loadAllRptExp_Ps() {
        try {
//            System.err.println("Vao day");
//            System.err.println("kybc=" + ky_bc);
            if (!getParaSession()) {
                return ERROR;
            }

            Connection conn = null;
            conn = new DaoConnect().getConnect();
            if (ky_bc == null) {
                setKy_bc("1");
            }
//            lstObjRpt = daoPhts.newInstance().getLoadAllQuery(ky_bc, Grade);             
                setLstObjRpt(daoPhts.newInstance().getLoadAllQuery(ky_bc, Grade, loai_file));            
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadAllRptExp -> " + e.getMessage());
        }
//        System.err.println(lstObjRpt.size());
        return SUCCESS;
    }

    
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Ham de xuat bao cao">

    /**
     * ham nay lay ra duong dan goc cua thu muc web
     *
     * @return
     * @throws Exception
     */
    private String getPathRoot() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath("/");
        path = DefineFun.backlashReplace(path);
        if (!path.endsWith("/") || !path.endsWith("\\")) {
            path += "/";
        }
        return path;
    }

    private String getExtendFile(String file_excel) throws Exception {
        if (file_excel.indexOf(".") < 0) {
            throw new Exception("Not found extend file " + file_excel);
        }
        String extendFile = file_excel.substring(file_excel.lastIndexOf(".") + 1, file_excel.length());
        if (!extendFile.equals("xlsx") && !extendFile.equals("xls")) {
            throw new Exception("Incorrect file excel template extend. Extend file only xlsx or xls ");
        }
        return extendFile;
    }

    /**
     * ham nay get ra tat ca cac sheet trong file excel
     *
     * @param file_excel duong dan day du toi file excel
     * @return
     * @throws Exception
     */
    public List<String> getSheetName(String file_excel) throws Exception {
        List<String> lstSheet = new ArrayList<>();
        String ext = getExtendFile(file_excel);
        org.apache.poi.ss.usermodel.Workbook workbook = null;
        if (ext.toLowerCase().equals("xls")) {
            workbook = new HSSFWorkbook(new FileInputStream(file_excel));
        } else {
            workbook = new XSSFWorkbook(new FileInputStream(file_excel));
        }
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            String sheet = workbook.getSheetName(i);
            lstSheet.add(sheet);
            System.err.println("sheet=" + sheet);
        }
        return lstSheet;
    }

    /**
     * Ham nay put du lieu theo file cau hinh ra mau
     *
     * @param transformer
     * @param context
     * @param sheetName
     * @param fileConfig
     * @throws FileNotFoundException
     * @throws IOException
     */
    private void putDataExcel(Transformer transformer, Context context, String sheetName, String fileConfig) throws FileNotFoundException, IOException {
        try (InputStream configInputStream = new FileInputStream(fileConfig)) {
            AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
            List<Area> xlsAreaList = areaBuilder.build();
            Area xlsArea = xlsAreaList.get(0);
            xlsArea.applyAt(new CellRef(sheetName + "!A1"), context);
        }
    }

    //Ham nay dung de xuat bao cao cho gui nhnn
    /**
     * Ham nay dung de xuat du lieu cho file excel
     *
     * @param conn tham so bien ket noi csdl
     * @param file_excel ten file excel mau
     * @param mabc mã báo cáo (mã NHNN quy dinh)
     * @param ma_cn Mã chi nhánh xuất báo cáo
     * @param tong_hop biến tổng hợp dữ liệu
     * @param kybc mã kỳ bao cáo (4 tháng ...)
     * @param ngaybc Ngày báo cáo
     * @param guimoi_quahan loại file S: gửi mới B gửi quá hạn
     * @param loai_bc Loại báo cáo M là báo cáo Chính, N không phát sinh số liệu
     * ...
     * @param langui_bc Số lần gửi file báo cáo
     * @return
     * @throws Exception
     */


    private boolean isCheckfile(String filename) throws Exception {
        //Lay ra duong dan root cua thu muc web
        String filePath = getPathRoot();

        filePath = filePath + Define.M_EXCEL_CONFIG + filename;
        File file = new File(filePath);
        if (file.exists()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param conn
     * @param mabc
     * @param ma_cn
     * @param kybc
     * @param ngaybc
     * @param guimoi_quahan
     * @param loai_bc
     * @param langui_bc
     * @return
     * @throws Exception
     */
    
    
    public List<ListValue> getLstObjRpt() {
        return lstObjRpt;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khai bao get/set">
    
    
    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public String getNgaygui() {
        return ngaygui;
    }

    public void setNgaygui(String ngaygui) {
        this.ngaygui = ngaygui;
    }

    public String getContentPhanhoiTT35() {
        return contentPhanhoiTT35;
    }

    public void setContentPhanhoiTT35(String contentPhanhoiTT35) {
        this.contentPhanhoiTT35 = contentPhanhoiTT35;
    }
    
    
    
    
    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public void setLstObjRpt(List<ListValue> lstObjRpt) {
        this.lstObjRpt = lstObjRpt;
    }

    

    public String getTonghop() {
        return tonghop;
    }

    public void setTonghop(String tonghop) {
        this.tonghop = tonghop;
    }

    public List<TreeNode> getTree_bc() {
        return tree_bc;
    }

    public void setTree_bc(List<TreeNode> tree_bc) {
        this.tree_bc = tree_bc;
    }

    public String getDefaultky_bc() {
        return defaultky_bc;
    }

    public void setDefaultky_bc(String defaultky_bc) {
        this.defaultky_bc = defaultky_bc;
    }

    public List<ListValue> getLstMacn() {
        return lstMacn;
    }

    public void setLstMacn(List<ListValue> lstMacn) {
        this.lstMacn = lstMacn;
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

    public imsTemplateConfig getObjExcelTemplate() {
        return objExcelTemplate;
    }

    public void setObjExcelTemplate(imsTemplateConfig objExcelTemplate) {
        this.objExcelTemplate = objExcelTemplate;
    }

    public xmlTemplateConfig getXmlConfig() {
        return xmlConfig;
    }

    public void setXmlConfig(xmlTemplateConfig xmlConfig) {
        this.xmlConfig = xmlConfig;
    }

    public List<ListValue> getLstLoaibc() {
        return lstLoaibc;
    }

    public void setLstLoaibc(List<ListValue> lstLoaibc) {
        this.lstLoaibc = lstLoaibc;
    }

    public List<ListValue> getLstKybc() {
        return lstKybc;
    }

    public void setLstKybc(List<ListValue> lstKybc) {
        this.lstKybc = lstKybc;
    }

    public List<ListValue> getLstLoaifile() {
        return lstLoaifile;
    }

    public void setLstLoaifile(List<ListValue> lstLoaifile) {
        this.lstLoaifile = lstLoaifile;
    }

    public String getLoai_bc() {
        return loai_bc;
    }

    public void setLoai_bc(String loai_bc) {
        this.loai_bc = loai_bc;
    }

    public String getKy_bc() {
        return ky_bc;
    }

    public void setKy_bc(String ky_bc) {
        this.ky_bc = ky_bc;
    }

    public String getLoai_file() {
        return loai_file;
    }

    public void setLoai_file(String loai_file) {
        this.loai_file = loai_file;
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
   

    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }

    public List<String> getMa_bc() {
        return ma_bc;
    }

    public void setMa_bc(List<String> ma_bc) {
        this.ma_bc = ma_bc;
    }

    public List<String> getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(List<String> pos_cd) {
        this.pos_cd = pos_cd;
    }

    public static List<String> getLstOfTextFile() {
        return lstOfTextFile;
    }

    public static void setLstOfTextFile(List<String> lstOfTextFile) {
        actionPhts.lstOfTextFile = lstOfTextFile;
    }

    public static List<DownloadFileInfor> getFilesList() {
        return filesList;
    }

    public static void setFilesList(List<DownloadFileInfor> filesList) {
        actionPhts.filesList = filesList;
    }

    public List<ListValue> getLstLanxuat() {
        return lstLanxuat;
    }

    public void setLstLanxuat(List<ListValue> lstLanxuat) {
        this.lstLanxuat = lstLanxuat;
    }

    public String getDefaultloai_bc() {
        return defaultloai_bc;
    }

    public void setDefaultloai_bc(String defaultloai_bc) {
        this.defaultloai_bc = defaultloai_bc;
    }

    public String getDefaultloai_file() {
        return defaultloai_file;
    }

    public void setDefaultloai_file(String defaultloai_file) {
        this.defaultloai_file = defaultloai_file;
    }

    public String getDefaultlan_xuat() {
        return defaultlan_xuat;
    }

    public void setDefaultlan_xuat(String defaultlan_xuat) {
        this.defaultlan_xuat = defaultlan_xuat;
    }
    
     public String getMainContent() {
        return mainContent;
    }

    public void setMainContent(String mainContent) {
        this.mainContent = mainContent;
    }
    
    public String getCnContent() {
        return cnContent;
    }

    public void setCnContent(String cnContent) {
        this.cnContent = cnContent;
    }
      

    public String getContentMTK() {
        return ContentMTK;
    }

    public void setContentMTK(String ContentMTK) {
        this.ContentMTK = ContentMTK;
    }

    public String getContentTT35() {
        return ContentTT35;
    }

    public void setContentTT35(String ContentTT35) {
        this.ContentTT35 = ContentTT35;
    }

    public String getContentPhanhoi() {
        return contentPhanhoi;
    }

    public void setContentPhanhoi(String contentPhanhoi) {
        this.contentPhanhoi = contentPhanhoi;
    }
    
    public String getContentPhanhoi_input() {
        return contentPhanhoi_input;
    }

    public void setContentPhanhoi_input(String contentPhanhoi_input) {
        this.contentPhanhoi_input = contentPhanhoi_input;
    }
    //</editor-fold>

    

    
   

}
