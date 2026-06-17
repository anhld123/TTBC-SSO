/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chtrinh_cn;

import vbsp.ims.nhaptaycn.action.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import java.io.File;
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
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT.saveDulieuNT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT.saveDulieuNT_Phi;
import vbsp.ims.chamdiemcn.ModelExcelFile;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDcptNo;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.define.Define;
import vbsp.ims.huydongtk.clsCanBo;
import vbsp.ims.khnv2021.PosClass;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.leavelocal.LeaveHomeService;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.Pagination;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListCommune;
import vbsp.ims.restapi.ListMainPos;
import vbsp.ims.restapi.ListOfValue;
import vbsp.ims.restapi.ListPosCode;
import vbsp.ims.sbv.daoSbv;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlKtgsSync;
import com.opensymphony.xwork2.ActionSupport;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;
import oracle.sql.ArrayDescriptor;
import oracle.sql.StructDescriptor;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import oracle.sql.ARRAY;
import oracle.sql.STRUCT;
import java.sql.Array;
import java.sql.Struct;
import oracle.jdbc.OracleConnection;

/**
 *
 * @author LION
 */
public class ActionChtrinhcnMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao cac bien">
    String fileNamelocal;
    String filereport;
    LeaveHomeService _server_tmp;
    public String reportId; //CuongBM: Ma bao cao
    public List<ModelExcelFile> lstExcel = new ArrayList<>();
    private String fileNameNew;
    private List<ListValue> lstBDD = new ArrayList<ListValue>();
    public String chotCic;
    private List<ListPosCode> lstPGD_API;
    private List<ListMainPos> lstCN_API;
    private List<ListCommune> lstXa_API;
    private String pos_cd;
    private String main_pos;
    private List<ListOfValue> lstDmKhac;
    private File fileUpload;
    private String fileUploadFileName;
    private String fileUploadContentType;
    private static StructDescriptor structDesc;
    private static ArrayDescriptor arrayDesc;
    private static boolean warmedUp = false;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static boolean isWarmedUp() {
        return warmedUp;
    }

    public static void setWarmedUp(boolean warmedUp) {
        ActionChtrinhcnMain.warmedUp = warmedUp;
    }

    public static StructDescriptor getStructDesc() {
        return structDesc;
    }

    public static void setStructDesc(StructDescriptor structDesc) {
        ActionChtrinhcnMain.structDesc = structDesc;
    }

    public static ArrayDescriptor getArrayDesc() {
        return arrayDesc;
    }

    public static void setArrayDesc(ArrayDescriptor arrayDesc) {
        ActionChtrinhcnMain.arrayDesc = arrayDesc;
    }

    public List<ListOfValue> getLstDmKhac() {
        return lstDmKhac;
    }

    public void setLstDmKhac(List<ListOfValue> lstDmKhac) {
        this.lstDmKhac = lstDmKhac;
    }

    public List<ListCommune> getLstXa_API() {
        return lstXa_API;
    }

    public void setLstXa_API(List<ListCommune> lstXa_API) {
        this.lstXa_API = lstXa_API;
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

    public List<ListPosCode> getLstPGD_API() {
        return lstPGD_API;
    }

    public void setLstPGD_API(List<ListPosCode> lstPGD_API) {
        this.lstPGD_API = lstPGD_API;
    }

    public List<ListMainPos> getLstCN_API() {
        return lstCN_API;
    }

    public void setLstCN_API(List<ListMainPos> lstCN_API) {
        this.lstCN_API = lstCN_API;
    }

    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;

    private String gradeAuthor1;

    public String getGradeAuthor1() {
        return gradeAuthor1;
    }

    public void setGradeAuthor1(String gradeAuthor1) {
        this.gradeAuthor1 = gradeAuthor1;
    }

    protected String pos_cd_username;

    protected int lockStatus;
    private List<PosClass> lstDonvi;

    public List<ListValue> getLstBDD() {
        return lstBDD;
    }

    public void setLstBDD(List<ListValue> lstBDD) {
        this.lstBDD = lstBDD;
    }
    public String langiangan;

    public String getLangiangan() {
        return langiangan;
    }

    public String getChotCic() {
        return chotCic;
    }

    public void setChotCic(String chotCic) {
        this.chotCic = chotCic;
    }

    public void setLangiangan(String langiangan) {
        this.langiangan = langiangan;
    }

    public List<ListValue> lstAllCdtt = new ArrayList<>();

    public List<ListValue> getLstAllCdtt() {
        return lstAllCdtt;
    }

    public void setLstAllCdtt(List<ListValue> lstAllCdtt) {
        this.lstAllCdtt = lstAllCdtt;
    }

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

    public LeaveHomeService getServer_tmp() {
        return _server_tmp;
    }

    public void setServer_tmp(LeaveHomeService _server_tmp) {
        this._server_tmp = _server_tmp;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getDvut_ksnb02() {
        return dvut_ksnb02;
    }

    public void setDvut_ksnb02(String dvut_ksnb02) {
        this.dvut_ksnb02 = dvut_ksnb02;
    }

    public String getCapkt_ksnb02() {
        return capkt_ksnb02;
    }

    public void setCapkt_ksnb02(String capkt_ksnb02) {
        this.capkt_ksnb02 = capkt_ksnb02;
    }

    public String getMato_ksnb02() {
        return mato_ksnb02;
    }

    public void setMato_ksnb02(String mato_ksnb02) {
        this.mato_ksnb02 = mato_ksnb02;
    }

    public String getChutich_ksnb02() {
        return chutich_ksnb02;
    }

    public void setChutich_ksnb02(String chutich_ksnb02) {
        this.chutich_ksnb02 = chutich_ksnb02;
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

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public String getSoku() {
        return soku;
    }

    public void setSoku(String soku) {
        this.soku = soku;
    }
    protected String masothue;
    protected String tc_von;
    protected String khoadc;
    protected String thangbc;
    protected String dtth;

    protected String tenkh;
    protected String soku;

    protected String type_action;
    protected String chuongtrinh;
    protected String maxa;
    protected String mato;
    protected String phanloai;

    public String getChuongtrinh() {
        return chuongtrinh;
    }

    public void setChuongtrinh(String chuongtrinh) {
        this.chuongtrinh = chuongtrinh;
    }

    public String getMaxa() {
        return maxa;
    }

    public void setMaxa(String maxa) {
        this.maxa = maxa;
    }

    public String getMato() {
        return mato;
    }

    public void setMato(String mato) {
        this.mato = mato;
    }

    public String getPhanloai() {
        return phanloai;
    }

    public void setPhanloai(String phanloai) {
        this.phanloai = phanloai;
    }

    public String getType_action() {
        return type_action;
    }

    public void setType_action(String type_action) {
        this.type_action = type_action;
    }

    public String getMasothue() {
        return masothue;
    }

    public void setMasothue(String masothue) {
        this.masothue = masothue;
    }
    protected String Message;
    protected String khoa_nhaptaycn;
    protected List<ListValue> lstAllNhaptaycn = new ArrayList<>();
    protected List<ReportParam> lstNhaptaycnParams = new ArrayList<>();
    protected TreeNode nodes_pos = new TreeNode();
    protected List<DULIEU_NT> lstNt = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstDulieuNt_tong = new ArrayList<>();
    protected List<QT_DULIEU_NT_50> lstDulieuNt50 = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstCombox = new ArrayList<>();
    protected List<ListValue> lstHinhthucTNHS = new ArrayList<ListValue>();
    protected List<ListValue> lstNgayluongHD = new ArrayList<ListValue>();
    protected List<ListValue> lstLuongVung = new ArrayList<ListValue>();
    protected List<ListValue> lstTinhchatNV = new ArrayList<ListValue>();
    protected List<ListValue> lstChotKH = new ArrayList<ListValue>();
    protected List<ListValue> lstTide = new ArrayList<ListValue>();
    protected List<ListValue> lstGiaiNgan = new ArrayList<ListValue>();
    protected List<ListValue> lstNhadautu = new ArrayList<ListValue>();
    private List<DuLieuNTRow> lstData_Api;
    protected List<ListValue> lstChuongtrinh = new ArrayList<ListValue>();
    protected List<ListValue> lstMaxa = new ArrayList<ListValue>();
    protected List<ListValue> lstMato = new ArrayList<ListValue>();
    protected List<ListValue> lstPhanloai = new ArrayList<ListValue>();

    protected List<ListValue> lstPLKT = new ArrayList<ListValue>();
    protected List<ListValue> lstDTTH = new ArrayList<ListValue>();
    protected List<ListValue> lstDNVON = new ArrayList<ListValue>();
    protected List<ListValue> lstSoKU = new ArrayList<ListValue>();
    protected List<ListValue> lstTrong30Ngay = new ArrayList<ListValue>();
    protected List<ListValue> lstSau30Ngay = new ArrayList<ListValue>();
    protected List<ListValue> lstKTLai = new ArrayList<ListValue>();
    protected List<ListValue> lstThangvay = new ArrayList<ListValue>();

    private List<ListValue> lstCapKT = new ArrayList<ListValue>();
    private List<ListValue> lstDVUT = new ArrayList<ListValue>();
    public List<clsCanBo> lstCanBo = new ArrayList<>();
//    private List<ListValue> lstMato = new ArrayList<ListValue>();
    private List<ListValue> lstChutichXaHoi = new ArrayList<ListValue>();

    public List<DuLieuNTRow> getLstData_Api() {
        return lstData_Api;
    }

    public void setLstData_Api(List<DuLieuNTRow> lstData_Api) {
        this.lstData_Api = lstData_Api;
    }

    public List<QT_DULIEU_NT> getLstCombox() {
        return lstCombox;
    }

    public void setLstCombox(List<QT_DULIEU_NT> lstCombox) {
        this.lstCombox = lstCombox;
    }
    private List<ListValue> lstCBTindung = new ArrayList<ListValue>();
    private List<ListValue> lstCBKetoan = new ArrayList<ListValue>();
    protected List<QT_DULIEU_NT> lstDulieuNt_pgd = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstDulieuNt_spham = new ArrayList<>();
    protected List<QT_DULIEU_NT> lstDulieuNt_phanbo = new ArrayList<>();
    protected List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_DAT = new ArrayList<QT_DULIEU_NT.saveDulieuNT_Phi>();
    protected List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_XAKK = new ArrayList<QT_DULIEU_NT.saveDulieuNT_Phi>();
    protected List<QT_DULIEU_NT.saveDulieuNT_Phi> lstsaveNT_XAKOKK_VAY = new ArrayList<QT_DULIEU_NT.saveDulieuNT_Phi>();

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

    public List<clsCanBo> getLstCanBo() {
        return lstCanBo;
    }

    public void setLstCanBo(List<clsCanBo> lstCanBo) {
        this.lstCanBo = lstCanBo;
    }

    public List<ListValue> getLstPhanloai() {
        return lstPhanloai;
    }

    public void setLstPhanloai(List<ListValue> lstPhanloai) {
        this.lstPhanloai = lstPhanloai;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt_tong() {
        return lstDulieuNt_tong;
    }

    public void setLstDulieuNt_tong(List<QT_DULIEU_NT> lstDulieuNt_tong) {
        this.lstDulieuNt_tong = lstDulieuNt_tong;
    }

    public List<saveDulieuNT_Phi> getLstsaveNT_XAKK() {
        return lstsaveNT_XAKK;
    }

    public void setLstsaveNT_XAKK(List<saveDulieuNT_Phi> lstsaveNT_XAKK) {
        this.lstsaveNT_XAKK = lstsaveNT_XAKK;
    }

    public List<saveDulieuNT_Phi> getLstsaveNT_XAKOKK_VAY() {
        return lstsaveNT_XAKOKK_VAY;
    }

    public void setLstsaveNT_XAKOKK_VAY(List<saveDulieuNT_Phi> lstsaveNT_XAKOKK_VAY) {
        this.lstsaveNT_XAKOKK_VAY = lstsaveNT_XAKOKK_VAY;
    }
    protected List<String> lstDat = new ArrayList<String>();
    protected List<String> lstXakk = new ArrayList<String>();
    protected List<String> lstXakoVay = new ArrayList<String>();

    public List<String> getLstXakk() {
        return lstXakk;
    }

    public void setLstXakk(List<String> lstXakk) {
        this.lstXakk = lstXakk;
    }

    public List<String> getLstXakoVay() {
        return lstXakoVay;
    }

    public void setLstXakoVay(List<String> lstXakoVay) {
        this.lstXakoVay = lstXakoVay;
    }

    protected List<QT_DULIEU_NT> lstDulieuNt_chitiet = new ArrayList<>();

    protected List<ListValue> lstParameters = new ArrayList<>();
    protected List<String> poscd = new ArrayList<String>();
    protected String poslist;
    protected String isDisplayTM = "N";
    protected String type_bcqt;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();
    private String tt_khoa;
    private String macn;
    private String ngay_bc;
    private List<ListValue> lstNgnhanDm = new ArrayList<ListValue>();
    protected String totalDataView;

    protected Pagination pagination = new Pagination(50, 1);
    protected List<saveDulieuNT> lstsaveNT = new ArrayList<saveDulieuNT>();
    protected List<saveDulieuNT_Phi> lstsaveNT_PGD = new ArrayList<saveDulieuNT_Phi>();
    protected List<saveDulieuNT_Phi> lstsaveNT_SP = new ArrayList<saveDulieuNT_Phi>();
    protected List<saveDulieuNT_Phi> lstsaveNT_PB = new ArrayList<saveDulieuNT_Phi>();
    private String user_id;
    private String nha_dt;
    private String group_from;
    private String group_to;
    private String nha_dt_LIST;

    private String exten1; //địa giới mới
    private String exten2; //địa giới cũ

    String dvut_ksnb02 = null;
    String capkt_ksnb02 = null;
    String mato_ksnb02 = null;
    String chutich_ksnb02 = null;

    public String getExten1() {
        return exten1;
    }

    public void setExten1(String exten1) {
        this.exten1 = exten1;
    }

    public String getExten2() {
        return exten2;
    }

    public void setExten2(String exten2) {
        this.exten2 = exten2;
    }

    public String getGroup_from() {
        return group_from;
    }

    public void setGroup_from(String group_from) {
        this.group_from = group_from;
    }

    public String getGroup_to() {
        return group_to;
    }

    public void setGroup_to(String group_to) {
        this.group_to = group_to;
    }
    private String tt_nha_dt;
    private String dvut_id;
    private String data_chart;

    public String getData_chart() {
        return data_chart;
    }

    public void setData_chart(String data_chart) {
        this.data_chart = data_chart;
    }

    public String getDvut_id() {
        return dvut_id;
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

    public List<DULIEU_NT> getLstNt() {
        return lstNt;
    }

    public void setLstNt(List<DULIEU_NT> lstNt) {
        this.lstNt = lstNt;
    }

    public List<saveDulieuNT_Phi> getLstsaveNT_DAT() {
        return lstsaveNT_DAT;
    }

    public void setLstsaveNT_DAT(List<saveDulieuNT_Phi> lstsaveNT_DAT) {
        this.lstsaveNT_DAT = lstsaveNT_DAT;
    }

    public List<String> getLstDat() {
        return lstDat;
    }

    public void setLstDat(List<String> lstDat) {
        this.lstDat = lstDat;
    }

    public void setDvut_id(String dvut_id) {
        this.dvut_id = dvut_id;
    }

    public String getNha_dt_LIST() {
        return nha_dt_LIST;
    }

    public void setNha_dt_LIST(String nha_dt_LIST) {
        this.nha_dt_LIST = nha_dt_LIST;
    }

    protected List<String> sanpham = new ArrayList<String>();
    protected List<String> phanbo = new ArrayList<String>();
    protected List<String> pgd = new ArrayList<String>();

//</editor-fold>
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
            for (String value1 : value) {
                if (!value1.equals("999999") && !value1.isEmpty()) {
                    lst.add(value1);
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
            if (DaoChtrinhcnMain.newInstance().checkUser(UserName) > 0) {
                setLstAllNhaptaycn(DaoChtrinhcnMain.newInstance().getAllNhaptaycn_SUB());
            } else {
                setLstAllNhaptaycn(DaoChtrinhcnMain.newInstance().getAllNhaptaycn(Grade));
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadPataNhaptaycn() {
        try {
//            System.err.println("khoa_nhaptaycn=" + khoa_nhaptaycn);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            //khoi tao cho treeview cac pos
//            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade, khoa_nhaptaycn);

            lstNhaptaycnParams = daoMain.getReportParmamsNhaptaycn(conn, khoa_nhaptaycn, UserName, Grade);

            if (this.khoa_nhaptaycn.equals("KTKSNB_01") || this.khoa_nhaptaycn.equals("KTKSNB_02") || this.khoa_nhaptaycn.equals("KTKSNB_03")
                    || this.khoa_nhaptaycn.equals("KTKSNB_04")) {
                posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
                pos_cd = posMainModel.getPosCd();
                main_pos = posMainModel.getMainPosCd();
                DuLieuNTService _serverAPI = new DuLieuNTService();

                Date currentDate = new Date();
                SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");
                String currentYear = yearFormat.format(currentDate);
                final String _reportDate = new SimpleDateFormat("ddMMyyyy").format(currentDate);
                List<String> years = new ArrayList<>();
                for (int i = 0; i <= 5; i++) {
                    int year = Integer.parseInt(currentYear) - i;
                    years.add(String.valueOf(year));
                }

                for (String year : years) {
                    try {
                        // Gọi API với năm tương ứng
                        List<DuLieuNTRow> lstData_Api = _serverAPI.getDataKTKSNB("CB_KTKSNB", pos_cd_username, "S", year + "1231", "", "0");

                        for (DuLieuNTRow item : lstData_Api) {
                            QT_DULIEU_NT row = new QT_DULIEU_NT();
                            if ("1".equals(item.getD14())) {
                                try {
                                    String[] values = item.getD16().split("\\/");
                                    String value1 = values[0];
                                    String value2 = values[1];
                                    String value3 = values[2];
                                    String _reportDate1 = value1 + value2 + value3;

                                    if (_reportDate1.compareTo(_reportDate) >= 0) {
                                        row.setD1(item.getD1());
                                        row.setD2(item.getD2());
                                        row.setD3(item.getD3());
                                        lstDulieuNt.add(row);
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Không có dữ liệu cho năm " + year);
                    }
                }

                // Hiển thị danh sách dữ liệu
                for (QT_DULIEU_NT row : lstDulieuNt) {
                    System.out.println("D1: " + row.getD1() + ", D2: " + row.getD2() + ", D3: " + row.getD3());
                }
                lstXa_API = _serverAPI.getListXa("", "", "", pos_cd);
                lstPGD_API = _serverAPI.getListPgd(main_pos, "");
                lstCN_API = _serverAPI.getListCn("");
                lstDmKhac = _serverAPI.getListOfValue("199", main_pos);

                switch (this.khoa_nhaptaycn) {
                    case "KTKSNB_01":
                        return "success_1";
                    case "KTKSNB_02":
                        return "success_2";
                    case "KTKSNB_03":
                        return "success_3";
                    case "KTKSNB_04":
                        addActionError("Chương trình đang chờ ban chuyên môn nghiệp vụ ban hành!");
                        return ERROR;
                    default:
                        return "success";
                }
            }

            if (conn != null) {
                conn.close();
            }

            lockStatus = 0;

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataNhaptaycn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataNhaptaycn: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadAllPos_Sp() {
        try {
//            System.err.println("khoa_nhaptaycn=" + khoa_nhaptaycn);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            //khoi tao cho treeview cac pos
            HashMap hmParameter = getParameter();
            System.out.println(tt_nha_dt);

//                lstNhaptaycnParams = daoMain.getReportParmamsNhaptaycn(conn, "PHIUT_001",UserName,Grade);
            lstDulieuNt_pgd = daoMain.getDataPhiUT_PGD(conn, UserName, Grade, tt_nha_dt);
            lstDulieuNt_spham = daoMain.getDataPhiUT_SP(conn, "PHIUT_001", "", UserName, Grade, poscd, tt_nha_dt);
            lstDulieuNt_phanbo = daoMain.getDataPhiUT_PHANBO(conn, "PHIUT_001", "", UserName, Grade, poscd, "");

            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadPataNhaptaycn: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadPataNhaptaycn: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String chart001() {
        String sData_chart = "[['Task', 'Hours per Day'],";
//        data_chart = "[\n" +
//                "          ['Task', 'Hours per Day'],\n" +
//                "          ['Vùng đồng bằng sông Hồng',     11],\n" +
//                "          ['Vùng Trung du và miền núi phía Bắc',      2],\n" +
//                "          ['Vùng Bắc Trung Bộ và Duyên hải miền Trung',  2],\n" +
//                "          ['Vùng Tây Nguyên', 2],\n" +
//                "          ['Vùng Đông Nam Bộ',    7],\n" +
//                "          ['Đồng bằng sông Cửu Long',    7]\n" +
//                "        ]";
        try {
//            if (!getParaSession()) {
//                return ERROR;
//            }
//           
//            HashMap<String, Object> hmPara = getParameter();
//            // Thực hiện chuyển ngày về đúng định dạng DD-MON-YYYY
//            ngay_bc = hmPara.get("ngay_bc").toString();
//            khoa_sbv = hmPara.get("khoa_sbv").toString();
//            if (ngay_bc == null || khoa_sbv == null) {
//                addActionError("Không thể lấy ra được tham số để load dữ liệu");
//                return ERROR;
//            }            
            lstDulieuNt = daoSbv.newInstance().loadTestChar("", ngay_bc, UserName, Grade, poscd);

            for (QT_DULIEU_NT valueNt : lstDulieuNt) {
                sData_chart = sData_chart + "['" + valueNt.getD2() + "'," + valueNt.getD4() + "],";
            }
            sData_chart = sData_chart.substring(0, sData_chart.length() - 1) + "]";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            addActionError("Lỗi khi tải dữ liệu " + e.getMessage());
            return ERROR;
        }
        data_chart = sData_chart;
        return SUCCESS;
    }

    public String chart002() {
        String sData_chart = "[['Task', 'Hours per Day'],";
//        data_chart = "[\n" +
//                "          ['Task', 'Hours per Day'],\n" +
//                "          ['Vùng đồng bằng sông Hồng',     11],\n" +
//                "          ['Vùng Trung du và miền núi phía Bắc',      2],\n" +
//                "          ['Vùng Bắc Trung Bộ và Duyên hải miền Trung',  2],\n" +
//                "          ['Vùng Tây Nguyên', 2],\n" +
//                "          ['Vùng Đông Nam Bộ',    7],\n" +
//                "          ['Đồng bằng sông Cửu Long',    7]\n" +
//                "        ]";
        try {
//            if (!getParaSession()) {
//                return ERROR;
//            }
//           
//            HashMap<String, Object> hmPara = getParameter();
//            // Thực hiện chuyển ngày về đúng định dạng DD-MON-YYYY
//            ngay_bc = hmPara.get("ngay_bc").toString();
//            khoa_sbv = hmPara.get("khoa_sbv").toString();
//            if (ngay_bc == null || khoa_sbv == null) {
//                addActionError("Không thể lấy ra được tham số để load dữ liệu");
//                return ERROR;
//            }            
            lstDulieuNt = daoSbv.newInstance().loadTestChar("", ngay_bc, UserName, Grade, poscd);

            for (QT_DULIEU_NT valueNt : lstDulieuNt) {
                sData_chart = sData_chart + "['" + valueNt.getD2() + "'," + valueNt.getD4() + "],";
            }
            sData_chart = sData_chart.substring(0, sData_chart.length() - 1) + "]";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            addActionError("Lỗi khi tải dữ liệu " + e.getMessage());
            return ERROR;
        }
        data_chart = sData_chart;
        return SUCCESS;
    }

    public String chart003() {
        String sData_chart = "[['Task', 'Hours per Day'],";
//        data_chart = "[\n" +
//                "          ['Task', 'Hours per Day'],\n" +
//                "          ['Vùng đồng bằng sông Hồng',     11],\n" +
//                "          ['Vùng Trung du và miền núi phía Bắc',      2],\n" +
//                "          ['Vùng Bắc Trung Bộ và Duyên hải miền Trung',  2],\n" +
//                "          ['Vùng Tây Nguyên', 2],\n" +
//                "          ['Vùng Đông Nam Bộ',    7],\n" +
//                "          ['Đồng bằng sông Cửu Long',    7]\n" +
//                "        ]";
        try {
//            if (!getParaSession()) {
//                return ERROR;
//            }
//           
//            HashMap<String, Object> hmPara = getParameter();
//            // Thực hiện chuyển ngày về đúng định dạng DD-MON-YYYY
//            ngay_bc = hmPara.get("ngay_bc").toString();
//            khoa_sbv = hmPara.get("khoa_sbv").toString();
//            if (ngay_bc == null || khoa_sbv == null) {
//                addActionError("Không thể lấy ra được tham số để load dữ liệu");
//                return ERROR;
//            }            
            lstDulieuNt = daoSbv.newInstance().loadTestChar("", ngay_bc, UserName, Grade, poscd);

            for (QT_DULIEU_NT valueNt : lstDulieuNt) {
                sData_chart = sData_chart + "['" + valueNt.getD2() + "'," + valueNt.getD4() + "],";
            }
            sData_chart = sData_chart.substring(0, sData_chart.length() - 1) + "]";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            addActionError("Lỗi khi tải dữ liệu " + e.getMessage());
            return ERROR;
        }
        data_chart = sData_chart;
        return SUCCESS;
    }

    public String chart004() {
        String sData_chart = "[['Task', 'Hours per Day'],";
//        data_chart = "[\n" +
//                "          ['Task', 'Hours per Day'],\n" +
//                "          ['Vùng đồng bằng sông Hồng',     11],\n" +
//                "          ['Vùng Trung du và miền núi phía Bắc',      2],\n" +
//                "          ['Vùng Bắc Trung Bộ và Duyên hải miền Trung',  2],\n" +
//                "          ['Vùng Tây Nguyên', 2],\n" +
//                "          ['Vùng Đông Nam Bộ',    7],\n" +
//                "          ['Đồng bằng sông Cửu Long',    7]\n" +
//                "        ]";
        try {
//            if (!getParaSession()) {
//                return ERROR;
//            }
//           
//            HashMap<String, Object> hmPara = getParameter();
//            // Thực hiện chuyển ngày về đúng định dạng DD-MON-YYYY
//            ngay_bc = hmPara.get("ngay_bc").toString();
//            khoa_sbv = hmPara.get("khoa_sbv").toString();
//            if (ngay_bc == null || khoa_sbv == null) {
//                addActionError("Không thể lấy ra được tham số để load dữ liệu");
//                return ERROR;
//            }            
            lstDulieuNt = daoSbv.newInstance().loadTestChar("", ngay_bc, UserName, Grade, poscd);

            for (QT_DULIEU_NT valueNt : lstDulieuNt) {
                sData_chart = sData_chart + "['" + valueNt.getD2() + "'," + valueNt.getD4() + "],";
            }
            sData_chart = sData_chart.substring(0, sData_chart.length() - 1) + "]";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> load: " + e.getMessage());
            addActionError("Lỗi khi tải dữ liệu " + e.getMessage());
            return ERROR;
        }
        data_chart = sData_chart;
        return SUCCESS;
    }

    public String loadAll_BC() {
        try {
//            System.err.println("khoa_nhaptaycn=" + khoa_nhaptaycn);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            //khoi tao cho treeview cac pos
            HashMap hmParameter = getParameter();
            System.out.println(tt_nha_dt);

//                lstNhaptaycnParams = daoMain.getReportParmamsNhaptaycn(conn, "PHIUT_001",UserName,Grade);
            lstDulieuNt_pgd = daoMain.getDataDMBC(conn, UserName, Grade, tt_nha_dt);
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
    //<editor-fold defaultstate="collapsed" desc="phan nay thua">
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

    public String getTotruong() {
        try {
            System.err.println("Vao ham getTotruong");
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.isEmpty() || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

//            System.err.println("execute sUserName=" + sUserName);
            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
//            reportGrade = session.get("reportGrade").toString();
//            if (reportGrade == null || reportGrade.isEmpty()) {
//                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
//                return ERROR;
//            }
            HttpServletRequest request = ServletActionContext.getRequest();

            Map<String, String[]> mappos = request.getParameterMap();
            String[] arrposcd = mappos.get("poscd");
            ArrayList<String> ArrlstPosCd = new ArrayList<String>();
            if (arrposcd == null || arrposcd.length == 0) {
                setMessage("Bạn phải chọn chi nhánh cần phê duyệt dữ liệu ");
//                return ERROR;
            } else {
                ArrlstPosCd = new ArrayList<String>(Arrays.asList(arrposcd));
                ArrlstPosCd.remove("999999");
            }
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            setLstCapKT(daoMain.getCanBo(UserName, "CAPKT"));
            setLstDVUT(daoMain.getCanBo(UserName, "DVUT"));
            setLstMato(daoMain.getCanBo(UserName, "MATO"));
            setLstChutichXaHoi(daoMain.getCanBo(UserName, "CHUTICH"));
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            dvut_ksnb02 = request.getParameter("dvut_dcpt");
            if (dvut_ksnb02 == null || dvut_ksnb02.isEmpty() || dvut_ksnb02.equals("-1")) {
//                System.err.println("dvut_dcpt la null " + dvut_dcpt);
                return SUCCESS;
            }

//            DaoDcptNo daoRisk = new DaoDcptNo();
            //Khoi tao cho treenode
//            System.err.println("dvut_dcpt=" + dvut_dcpt + " poscd=" + ArrlstPosCd.size());
            //neu don vi uy thac khong phai la truc tiep thi moi load ma to truong hoac du an
            if (!dvut_ksnb02.equals("1")) {
                setLstMato(new DaoDcptNo().getTotruong(sUserName, "1", ArrlstPosCd, dvut_ksnb02));
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getTotruong -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String unLockData() {
        System.err.println("Vao ham unLockData");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoNhaptaycnMain daosync = DaoNhaptaycnMain.newInstance();
            //Kiem tra xem cac pgd da du du lieu chua neu du moi cho xac nhan so lieu

            HashMap hmParameter = getParameter();

            setKhoa_nhaptaycn(hmParameter.get("khoa_nhaptaycn").toString());
//            setType_bcqt(hmParameter.get("type_bcqt").toString());
//            setMacn(hmParameter.get("macn").toString());
            setNgay_bc(hmParameter.get("ngay_bc").toString());
//            (String type, String khoa,List<String> lstMapgd,  String ngaybc, String tt_khoa,  String username,  String grade)
            if (!daosync.setStatusLock("NT", khoa_nhaptaycn, poscd, hmParameter.get("ngay_bc").toString(),
                    Define.WEB_SERVICES_STATUS_SEND, UserName, Grade)) {
                addActionError("Lỗi !, Mở khóa bị lỗi xin liên hệ với quản trị để được khắc phục");
                return ERROR;
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendPhiUT: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã mở khóa thành công!");
        return SUCCESS;
    }

    public String sendPhiUT() {
        System.err.println("Vao ham sendPhiUT");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoNhaptaycnMain daosync = DaoNhaptaycnMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();
            if (khoa_nhaptaycn.equals("QD23_001")) {
                if (daosync.checkSave_Send(khoa_nhaptaycn, Grade, hmParameter.get("ngay_bc").toString(), "SEND", UserName, poscd) == 0) {
                    addActionError("Bạn vui lòng chọn ngày hiện tại và nhập cột 43,44!");
                    return ERROR;
                }
            }
            if (khoa_nhaptaycn.equals("PHIUT_001")) {
                lstPos = daosync.getAllPosUser(UserName, "PHIUT_001");
            }
            if (khoa_nhaptaycn.equals("QD23_004")) {
                lstPos = new ArrayList<>();
                lstPos.add(pos_cd_username);
            }

            if (khoa_nhaptaycn.equals("NHAPTAYCN_02")) {
                return SUCCESS;
            }
            DuLieuNTService service;
            service = new DuLieuNTService();
            String timeServer = service.getTimeServer();
//            String khoa =  hmParameter.get("khoa_nhaptaycn").toString();
            if (khoa_nhaptaycn.equals("QD23_001") && new DaoNhaptaycnMain().check_date_input_qd23(khoa_nhaptaycn, timeServer) == 1) {
                addActionError("Đã hết thời gian gửi số liệu, vui lòng quay trở lại vào hôm sau.");
                return ERROR;
            }

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += hmParameter.get("khoa_nhaptaycn").toString() + "_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;
                lstData = daosync.getDataSendPhiut("NT", khoa_nhaptaycn,
                        mapgd, hmParameter.get("ngay_bc").toString());

                if (lstData == null || lstData.isEmpty()) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_PHIUT, "NT",
                        hmParameter.get("khoa_nhaptaycn").toString(), hmParameter.get("ngay_bc").toString(), UserName, Grade,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
//                    return ERROR;
                }
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
                switch (sStatus) {
                    case Define.WEB_SERVICES_STATUS_FAIL:
                        System.err.println("Ban chua dong bo du lieu duoc ve TW");
                        //                    addActionError("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }
                        CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: Khong dong bo duoc file " + strPathSave);
                        mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
//                    return ERROR;
                        break;
                    case Define.WEB_SERVICES_STATUS_OK:
                        //                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }
                        mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                        if (khoa_nhaptaycn.equals("QD23_001")) {
                            new DaoNhaptaycnMain().updateAfterSendQd23(khoa_nhaptaycn, mapgd, hmParameter.get("ngay_bc").toString());
                        }
                        break;
                    default:
                        //                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                        if (checkfile.exists()) {
                            checkfile.delete();
                        }
                        mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
//                    return ERROR;
                        break;
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

    public String sendOnePos(String mabc, String ngaybc, String mapgd) {
        System.err.println("Vao ham sendPhiUT");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            DaoNhaptaycnMain daosync = DaoNhaptaycnMain.newInstance();
//            Map<String, Integer> mapStatusSend = new HashMap();

            ServletContext context = ServletActionContext.getServletContext();
            String strPathSave = !context.getRealPath("/").endsWith("/")
                    ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                    : context.getRealPath("/") + Define.M_REPORT_XML;
            strPathSave += hmParameter.get("khoa_nhaptaycn").toString() + "_" + mapgd
                    + "_" + UserName + "_"
                    + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

            List<String> lstData = new ArrayList<>();
            boolean bStatus_file = false;
            lstData = daosync.getDataSendPhiut("NT", khoa_nhaptaycn,
                    mapgd, hmParameter.get("ngay_bc").toString());

            if (lstData == null || lstData.isEmpty()) {
//                    mapStatusSend.put(mapgd, 6);
                return "Không có dữ liệu";
            }
            bStatus_file = new XmlKtgsSync().createXmlFileKtgs(Define.PARA_SYN_REPORT_PHIUT, "NT",
                    hmParameter.get("khoa_nhaptaycn").toString(), hmParameter.get("ngay_bc").toString(), UserName, Grade,
                    mapgd, lstData, Define.WEB_SERVICES_STATUS_SEND, strPathSave);

            if (!bStatus_file) {
                CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: Khong tao duoc file " + strPathSave);
//                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
                return "Không tạo được file gửi tw";
            }
            //Tao file xml theo cau truc
//
            File checkfile = new File(strPathSave);
            if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: Khong tao duoc file " + strPathSave);
//                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
                return "Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục";
            }
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
            switch (sStatus) {
                case Define.WEB_SERVICES_STATUS_FAIL:
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
//                    addActionError("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: Khong dong bo duoc file " + strPathSave);
//                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
                    return "Ban chua dong bo du lieu duoc ve TW";
//            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
                case Define.WEB_SERVICES_STATUS_OK:
                    //                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
//                    mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                    return SUCCESS;
                default:
                    //                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
//                    mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
                    return "Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendPhiUT: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendPhiUT: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

//        return SUCCESS;
    }

    public String checkLockStatus() throws Exception {
        DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
        String sKey = ServletActionContext.getRequest().getParameter("Key");
        String sReportDate = ServletActionContext.getRequest().getParameter("ReportDate");
        String sReportGrade = ServletActionContext.getRequest().getParameter("ReportGrade");
        String sUserName = ServletActionContext.getRequest().getParameter("UserName");
        lockStatus = daoMain.getLockStatus(sKey, sReportDate, sUserName, sReportGrade);
        return SUCCESS;
    }
    //</editor-fold>

//<editor-fold defaultstate="collapsed" desc="phan cua up excel">
    public String openExcelUploadQtKh() {
        try (Connection conn = new DaoConnect().getConnect()) {
            String maLoai = ServletActionContext.getRequest().getParameter("type");
            DuLieuNTService _serverAPI = new DuLieuNTService();
            lstDmKhac = _serverAPI.getListOfValue("92", "");
            type = type;
            return SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
    }

//    public String dtwUploadExcel() throws SQLException {
//        
//        if (fileUpload == null) {
//            addActionError("Chưa chọn file Excel!");
//            return ERROR;
//        }
//
//        if (fileUploadFileName == null || !fileUploadFileName.contains("_")) {
//            addActionError("Tên file không đúng định dạng");
//            return ERROR;
//        }
//
//        String[] values = fileUploadFileName.split("\\_");
//        String Key = values[0];
//        String file = values[1];
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        String ngaybc = new SimpleDateFormat("yyyyMMdd").format(new Date());
//        String fileName = Key + "_" + timeStamp + file;
//
//        Connection conn = null;
//        int uploadedRows = 0;
//
//        Map session = ActionContext.getContext().getSession();
//        String sUserName = session.get("username").toString();
//
//        DaoChtrinhcnMain dao = new DaoChtrinhcnMain();
//        String start_end = dao.getStartEndCel(Key);
//        int startrow = 0, startcell = 0, endcell = 0;
//        if (!start_end.equals("AAA")) {
//            startrow = Integer.parseInt(start_end.split("-")[0]);
//            startcell = Integer.parseInt(start_end.split("-")[1]);
//            endcell = Integer.parseInt(start_end.split("-")[2]);
//        }
//        
//        try (FileInputStream fis = new FileInputStream(fileUpload);
//                Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//            conn = new DaoConnect().getConnect();
//
//            // ===== Đọc dữ liệu từ Excel đưa vào List<String[]> =====
//            List<String[]> excelData = new ArrayList<>();
//            for (int i = startrow; i <= sheet.getLastRowNum(); i++) {
//                Row row = sheet.getRow(i);
//                if (row == null) {
//                    continue;
//                }
//
//                boolean hasData = false;
//                String[] rowValues = new String[endcell - startcell];
//                for (int c = startcell; c < endcell; c++) {
//                    String cellValue = getCellValueAsString(row.getCell(c));
//                    if (cellValue != null && !cellValue.trim().isEmpty()) {
//                        hasData = true;
//                    }
//                    rowValues[c - startcell] = cellValue;
//                }
//                if (hasData) {
//                    excelData.add(rowValues);
//                }
//            }
//            uploadedRows = excelData.size();
//
//            // Gọi thủ tục INSERT_REPORT_DATA
//            callInsertReportData(conn, excelData, fileName, Key, sUserName, ngaybc, startrow, startcell, endcell);
//
//            //Gọi EVEN_EXCEL
//            callEvenExcel(conn, Key, fileName, ngaybc);
//
//            //Lưu file
//            saveUploadedFile(fileUpload, fileName);
//
//            addActionMessage("Upload thành công file: " + fileName + " với " + uploadedRows + " dòng dữ liệu.");
//            return SUCCESS;
//
//        } catch (SQLException e) {
//            String err = e.getMessage();
//            if (err != null && err.contains("ORA-00600")) {
//                // Lỗi nội bộ Oracle → cảnh báo nhẹ nhàng
//                addActionError("Hệ thống gặp lỗi nội bộ (ORA-600). Vui lòng chờ sau đó thử lại.");
//            } else {
//                // Các lỗi SQL khác thì vẫn hiện bình thường
//                addActionError("Lỗi khi xử lý file: " + err);
//            }
//            return ERROR;
//        } catch (Exception e) {
//            addActionError("Có lỗi không xác định: " + e.getMessage());
//            return ERROR;
//        } finally {
//            if (conn != null) {
//                try {
//                    conn.close();
//                } catch (Exception ignored) {
//                }
//            }
//        }
//    }
    public String dtwUploadExcel() throws SQLException {

//        long totalStart = System.currentTimeMillis();
//        System.out.println("===== START UPLOAD =====");
        if (fileUpload == null) {
            addActionError("Chưa chọn file Excel!");
            return ERROR;
        }

        if (fileUploadFileName == null || !fileUploadFileName.contains("_")) {
            addActionError("Tên file không đúng định dạng");
            return ERROR;
        }

        String[] values = fileUploadFileName.split("\\_");
        String Key = values[0];
        String file = values[1];
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String ngaybc = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String fileName;
        if (Key.equals("RRBANKS")) {
            fileName = Key + "_" + timeStamp + file;
        } else {
            fileName = fileUploadFileName;
        }
        Connection conn = null;
        int uploadedRows = 0;

        Map session = ActionContext.getContext().getSession();
        String sUserName = session.get("username").toString();

        DaoChtrinhcnMain dao = new DaoChtrinhcnMain();

//        long tCfg = System.currentTimeMillis();
        String start_end = dao.getStartEndCel(Key);
//        System.out.println("getStartEndCel = "
//                + (System.currentTimeMillis() - tCfg) + " ms");

        int startrow = 0, startcell = 0, endcell = 0;

        if (!start_end.equals("AAA")) {
            startrow = Integer.parseInt(start_end.split("-")[0]);
            startcell = Integer.parseInt(start_end.split("-")[1]);
            endcell = Integer.parseInt(start_end.split("-")[2]);
        }
        try {

//            long tOpenExcel = System.currentTimeMillis();
            FileInputStream fis = new FileInputStream(fileUpload);
            Workbook workbook = new XSSFWorkbook(fis);

//            System.out.println("Open XSSFWorkbook = "
//                    + (System.currentTimeMillis() - tOpenExcel) + " ms");
            Sheet sheet = workbook.getSheetAt(0);

//            long tConn = System.currentTimeMillis();
            conn = new DaoConnect().getConnect();

//            System.out.println("Get Connection = "
//                    + (System.currentTimeMillis() - tConn) + " ms");
            // ===== READ EXCEL =====
//            long tReadExcel = System.currentTimeMillis();
            List<String[]> excelData = new ArrayList<>();

            for (int i = startrow; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null) {
                    continue;
                }

                boolean hasData = false;

                String[] rowValues = new String[endcell - startcell];

                for (int c = startcell; c < endcell; c++) {

                    String cellValue = getCellValueAsString(row.getCell(c));

                    if (cellValue != null && !cellValue.trim().isEmpty()) {
                        hasData = true;
                    }

                    rowValues[c - startcell] = cellValue;
                }

                if (hasData) {
                    excelData.add(rowValues);
                }
            }

            uploadedRows = excelData.size();

//            System.out.println("Read Excel Data = " + (System.currentTimeMillis() - tReadExcel) + " ms");
//            System.out.println("Total rows = " + uploadedRows);
            // ===== INSERT_REPORT_DATA =====
//            long tInsert = System.currentTimeMillis();
            callInsertReportData(conn, excelData, fileName, Key, sUserName, ngaybc, startrow, startcell, endcell);

//            System.out.println("INSERT_REPORT_DATA = " + (System.currentTimeMillis() - tInsert) + " ms");
//            long tEven = System.currentTimeMillis();
            callEvenExcel(conn, Key, fileName, ngaybc);

//            System.out.println("EVEN_EXCEL = " + (System.currentTimeMillis() - tEven) + " ms");
//            long tSave = System.currentTimeMillis();
            saveUploadedFile(fileUpload, fileName);

//            System.out.println("SAVE_FILE = " + (System.currentTimeMillis() - tSave) + " ms");
            workbook.close();
            fis.close();
//            System.out.println("TOTAL UPLOAD = " + (System.currentTimeMillis() - totalStart) + " ms");
            addActionMessage("Upload thành công file: " + fileName + " với " + uploadedRows + " dòng dữ liệu.");
            return SUCCESS;

        } catch (SQLException e) {

            String err = e.getMessage();

            if (err != null && err.contains("ORA-00600")) {
                addActionError("Hệ thống gặp lỗi nội bộ (ORA-600). Vui lòng chờ sau đó thử lại.");
            } else {
                addActionError("Lỗi khi xử lý file: " + err);
            }

            return ERROR;

        } catch (Exception e) {

            e.printStackTrace();

            addActionError("Có lỗi không xác định: " + e.getMessage());

            return ERROR;

        } finally {

            if (conn != null) {
                try {
                    conn.close();
                } catch (Exception ignored) {
                }
            }
        }
    }

    public void callInsertReportData(
            Connection conn,
            List<String[]> excelData,
            String fileName,
            String key,
            String user,
            String ngaybc,
            int startrow,
            int startcell,
            int endcell) throws Exception {

        OracleConnection oraConn
                = conn.unwrap(OracleConnection.class);

        int colCount = endcell - startcell;

        Struct[] structArray = new Struct[excelData.size()];

        for (int i = 0; i < excelData.size(); i++) {

            String[] row = excelData.get(i);

            Object[] attrs = new Object[colCount];

            for (int c = 0; c < colCount; c++) {
                attrs[c] = (c < row.length) ? row[c] : null;
            }

            structArray[i]
                    = oraConn.createStruct(
                            "TYPE_UPLOAD_EXCEL",
                            attrs);
        }

        Array oracleArray
                = oraConn.createOracleArray(
                        "TAB_UPLOAD_EXCEL",
                        structArray);

        try (CallableStatement cs
                = conn.prepareCall(
                        "{ call VBSP_IMS_CHTRINHCN.INSERT_REPORT_DATA(?, ?, ?, ?, ?) }")) {

            cs.setArray(1, oracleArray);
            cs.setString(2, fileName);
            cs.setString(3, key);
            cs.setString(4, user);
            cs.setString(5, ngaybc);

            cs.execute();

            System.out.println("INSERT_REPORT_DATA executed OK!");
        }
    }
//    public void callInsertReportData(Connection conn,
//            List<String[]> excelData,
//            String fileName,
//            String key,
//            String user,
//            String ngaybc,
//            int startrow,
//            int startcell,
//            int endcell) throws Exception {
//        StructDescriptor structDesc = ActionChtrinhcnMain.getStructDesc();
//        ArrayDescriptor arrayDesc = ActionChtrinhcnMain.getArrayDesc();
//
//        int colCount = endcell - startcell;
//        STRUCT[] structArray = new STRUCT[excelData.size()];
//
//        // 2. Convert List<String[]> thành mảng STRUCT
//        for (int i = 0; i < excelData.size(); i++) {
//            String[] row = excelData.get(i);
//            Object[] attributes = new Object[colCount];
//
//            for (int c = 0; c < colCount; c++) {
//                int excelIndex = startcell + c;
//                attributes[c] = row[c];
//            }
//
//            structArray[i] = new STRUCT(structDesc, conn, attributes);
//        }
//
//        // 3. Tạo ARRAY để truyền vào thủ tục
//        ARRAY oracleArray = new ARRAY(arrayDesc, conn, structArray);
//
//        // 4. Gọi stored procedure
//        try (CallableStatement cs = conn.prepareCall(
//                "{ call VBSP_IMS_CHTRINHCN.INSERT_REPORT_DATA(?, ?, ?, ?, ?) }")) {
//            cs.setArray(1, oracleArray);
//            cs.setString(2, fileName);
//            cs.setString(3, key);
//            cs.setString(4, user);
//            cs.setString(5, ngaybc);
//            cs.execute();
//            System.out.println("INSERT_REPORT_DATA executed OK!");
//        }
//    }

    public void callEvenExcel(Connection conn, String key, String fileName, String ngaybc) {
        try (CallableStatement csEven = conn.prepareCall(
                "{ call VBSP_IMS_CHTRINHCN.EVEN_EXCEL(?, ?, ?, ?, ?, ?) }")) {
            csEven.setString(1, key);
            csEven.setString(2, fileName);
            csEven.setString(3, ngaybc);
            csEven.setString(4, "");
            csEven.setString(5, "");
            csEven.setString(6, "");
            csEven.execute();
            System.out.println("EVEN_EXCEL executed OK for file: " + fileName);
        } catch (Exception exEven) {
            exEven.printStackTrace();
            addActionMessage("Upload thành công, nhưng xử lý EVEN_EXCEL bị lỗi: " + exEven.getMessage());
        }
    }

    public void saveUploadedFile(File sourceFile, String fileName) {
        try {
            // Thư mục đích nằm trong ứng dụng (webapp/EXPORT_REPORT/XLS/HSRR)
            String exportFolder = ServletActionContext.getServletContext()
                    .getRealPath("/EXPORT_REPORT/XLS/HSRR/");

            File exportDir = new File(exportFolder);
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            if (!fileName.toLowerCase().endsWith(".xlsx")) {
                fileName += ".xlsx";
            }

            File destFile = new File(exportDir, fileName);
            Files.copy(sourceFile.toPath(), destFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

//            System.out.println("File đã lưu vào: " + destFile.getAbsolutePath());
        } catch (IOException ioe) {
            ioe.printStackTrace();
            addActionMessage("Upload thành công, nhưng lưu file bị lỗi: " + ioe.getMessage());
        }
    }

    private String getCellValueAsString(Cell cell) {
        if (cell == null) {
            return "";
        }
        switch (cell.getCellType()) {
            case Cell.CELL_TYPE_STRING:
                return cell.getStringCellValue().trim();

            case Cell.CELL_TYPE_NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return new SimpleDateFormat("yyyy-MM-dd").format(cell.getDateCellValue());
                } else {
                    double num = cell.getNumericCellValue();
                    if (num == Math.floor(num)) {
                        return String.valueOf((long) num);
                    }
                    return String.valueOf(num);
                }

            case Cell.CELL_TYPE_BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case Cell.CELL_TYPE_FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (IllegalStateException e) {
                    return String.valueOf(cell.getNumericCellValue());
                }

            default:
                return "";
        }
    }

    public String downloadTemplate() throws Exception {
        HttpServletResponse response = ServletActionContext.getResponse();

        if (mauBc == null || mauBc.trim().isEmpty()) {
            addActionError("Bạn chưa chọn loại file mẫu!");
            return ERROR;
        }

        try {
            String templateFolder;
            if (mauBc.equals("RRBANKS")) {
                templateFolder = ServletActionContext.getServletContext().getRealPath("/EXCEL_TEMPLATE/HSRR/");
            } else {
                templateFolder = ServletActionContext.getServletContext().getRealPath("/EXCEL_TEMPLATE/");
            }
            String templateFileName = mauBc + "_.xlsx";
            File templateFile = new File(templateFolder, templateFileName);

            if (!templateFile.exists()) {
                addActionError("File mẫu không tồn tại!");
                return ERROR;
            }

            // encode tên file để tránh lỗi Unicode
            String fileName = URLEncoder.encode(templateFile.getName(), "UTF-8").replace("+", "%20");
            response.setContentType(
                    "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
            );
            response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

            try (FileInputStream in = new FileInputStream(templateFile);
                    OutputStream out = response.getOutputStream()) {

                byte[] buffer = new byte[4096];
                int length;
                while ((length = in.read(buffer)) > 0) {
                    out.write(buffer, 0, length);
                }
                out.flush();
            }

            return NONE; // Quan trọng: không forward sang JSP
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Lỗi khi tải file mẫu: " + e.getMessage());
            return ERROR;
        }
    }

    public static void init(Connection conn) throws SQLException {
        structDesc = StructDescriptor.createDescriptor("TYPE_UPLOAD_EXCEL", conn);
        arrayDesc = ArrayDescriptor.createDescriptor("TAB_UPLOAD_EXCEL", conn);
    }
//</editor-fold>
//<editor-fold defaultstate="collapsed" desc="Khai bao phuong thuc get/set cho bien">
    private String mauBc;

    public String getMauBc() {
        return mauBc;
    }

    public void setMauBc(String mauBc) {
        this.mauBc = mauBc;
    }

    private String font_type;            // radio button

    public String getFont_type() {
        return font_type;
    }

    public void setFont_type(String font_type) {
        this.font_type = font_type;
    }

    public String getPoslist() {
        return poslist;
    }

    public void setPoslist(String poslist) {
        this.poslist = poslist;
    }

    public List<saveDulieuNT> getLstsaveNT() {
        return lstsaveNT;
    }

    public void setLstsaveNT(List<saveDulieuNT> lstsaveNT) {
        this.lstsaveNT = lstsaveNT;
    }

    public List<saveDulieuNT_Phi> getLstsaveNT_SP() {
        return lstsaveNT_SP;
    }

    public void setLstsaveNT_SP(List<saveDulieuNT_Phi> lstsaveNT_SP) {
        this.lstsaveNT_SP = lstsaveNT_SP;
    }

    public List<saveDulieuNT_Phi> getLstsaveNT_PB() {
        return lstsaveNT_PB;
    }

    public void setLstsaveNT_PB(List<saveDulieuNT_Phi> lstsaveNT_PB) {
        this.lstsaveNT_PB = lstsaveNT_PB;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getTotalDataView() {
        return totalDataView;
    }

    public void setTotalDataView(String totalDataView) {
        this.totalDataView = totalDataView;
    }

    public List<ListValue> getLstNgnhanDm() {
        return lstNgnhanDm;
    }

    public String getNha_dt() {
        return nha_dt;
    }

    public void setNha_dt(String nha_dt) {
        this.nha_dt = nha_dt;
    }

    public List<saveDulieuNT_Phi> getLstsaveNT_PGD() {
        return lstsaveNT_PGD;
    }

    public void setLstsaveNT_PGD(List<saveDulieuNT_Phi> lstsaveNT_PGD) {
        this.lstsaveNT_PGD = lstsaveNT_PGD;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt_chitiet() {
        return lstDulieuNt_chitiet;
    }

    public void setLstDulieuNt_chitiet(List<QT_DULIEU_NT> lstDulieuNt_chitiet) {
        this.lstDulieuNt_chitiet = lstDulieuNt_chitiet;
    }

    public List<String> getPgd() {
        return pgd;
    }

    public void setPgd(List<String> pgd) {
        this.pgd = pgd;
    }

    public List<ListValue> getLstHinhthucTNHS() {
        return lstHinhthucTNHS;
    }

    public void setLstHinhthucTNHS(List<ListValue> lstHinhthucTNHS) {
        this.lstHinhthucTNHS = lstHinhthucTNHS;
    }

    public List<ListValue> getLstNgayluongHD() {
        return lstNgayluongHD;
    }

    public void setLstNgayluongHD(List<ListValue> lstNgayluongHD) {
        this.lstNgayluongHD = lstNgayluongHD;
    }

    public List<ListValue> getLstLuongVung() {
        return lstLuongVung;
    }

    public void setLstLuongVung(List<ListValue> lstLuongVung) {
        this.lstLuongVung = lstLuongVung;
    }

    public List<ListValue> getLstTinhchatNV() {
        return lstTinhchatNV;
    }

    public void setLstTinhchatNV(List<ListValue> lstTinhchatNV) {
        this.lstTinhchatNV = lstTinhchatNV;
    }

    public List<ListValue> getLstPLKT() {
        return lstPLKT;
    }

    public void setLstPLKT(List<ListValue> lstPLKT) {
        this.lstPLKT = lstPLKT;
    }

    public List<ListValue> getLstDTTH() {
        return lstDTTH;
    }

    public void setLstDTTH(List<ListValue> lstDTTH) {
        this.lstDTTH = lstDTTH;
    }

    public List<QT_DULIEU_NT_50> getLstDulieuNt50() {
        return lstDulieuNt50;
    }

    public void setLstDulieuNt50(List<QT_DULIEU_NT_50> lstDulieuNt50) {
        this.lstDulieuNt50 = lstDulieuNt50;
    }

    public List<ListValue> getLstTide() {
        return lstTide;
    }

    public void setLstTide(List<ListValue> lstTide) {
        this.lstTide = lstTide;
    }

    public String getThangbc() {
        return thangbc;
    }

    public void setThangbc(String thangbc) {
        this.thangbc = thangbc;
    }

    public String getKhoadc() {
        return khoadc;
    }

    public void setKhoadc(String khoadc) {
        this.khoadc = khoadc;
    }

    public String getTc_von() {
        return tc_von;
    }

    public void setTc_von(String tc_von) {
        this.tc_von = tc_von;
    }

    public List<ListValue> getLstChotKH() {
        return lstChotKH;
    }

    public void setLstChotKH(List<ListValue> lstChotKH) {
        this.lstChotKH = lstChotKH;
    }

    public List<ListValue> getLstDNVON() {
        return lstDNVON;
    }

    public void setLstDNVON(List<ListValue> lstDNVON) {
        this.lstDNVON = lstDNVON;
    }

    public List<ListValue> getLstSoKU() {
        return lstSoKU;
    }

    public void setLstSoKU(List<ListValue> lstSoKU) {
        this.lstSoKU = lstSoKU;
    }

    public String getDtth() {
        return dtth;
    }

    public void setDtth(String dtth) {
        this.dtth = dtth;
    }

    public List<ListValue> getLstTrong30Ngay() {
        return lstTrong30Ngay;
    }

    public void setLstTrong30Ngay(List<ListValue> lstTrong30Ngay) {
        this.lstTrong30Ngay = lstTrong30Ngay;
    }

    public List<ListValue> getLstSau30Ngay() {
        return lstSau30Ngay;
    }

    public void setLstSau30Ngay(List<ListValue> lstSau30Ngay) {
        this.lstSau30Ngay = lstSau30Ngay;
    }

    public List<ListValue> getLstKTLai() {
        return lstKTLai;
    }

    public void setLstKTLai(List<ListValue> lstKTLai) {
        this.lstKTLai = lstKTLai;
    }

    public List<ListValue> getLstThangvay() {
        return lstThangvay;
    }

    public void setLstThangvay(List<ListValue> lstThangvay) {
        this.lstThangvay = lstThangvay;
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

    public List<PosClass> getLstDonvi() {
        return lstDonvi;
    }

    public void setLstDonvi(List<PosClass> lstDonvi) {
        this.lstDonvi = lstDonvi;
    }

    public List<ListValue> getLstCapKT() {
        return lstCapKT;
    }

    public int getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(int lockStatus) {
        this.lockStatus = lockStatus;
    }

    public void setLstCapKT(List<ListValue> lstCapKT) {
        this.lstCapKT = lstCapKT;
    }

    public List<ListValue> getLstDVUT() {
        return lstDVUT;
    }

    public void setLstDVUT(List<ListValue> lstDVUT) {
        this.lstDVUT = lstDVUT;
    }

    public List<ListValue> getLstMato() {
        return lstMato;
    }

    public void setLstMato(List<ListValue> lstMato) {
        this.lstMato = lstMato;
    }

    public List<ListValue> getLstChutichXaHoi() {
        return lstChutichXaHoi;
    }

    public void setLstChutichXaHoi(List<ListValue> lstChutichXaHoi) {
        this.lstChutichXaHoi = lstChutichXaHoi;
    }

    public List<String> getSanpham() {
        return sanpham;
    }

    public void setSanpham(List<String> sanpham) {
        this.sanpham = sanpham;
    }

    public List<String> getPhanbo() {
        return phanbo;
    }

    public void setPhanbo(List<String> phanbo) {
        this.phanbo = phanbo;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt_pgd() {
        return lstDulieuNt_pgd;
    }

    public void setLstDulieuNt_pgd(List<QT_DULIEU_NT> lstDulieuNt_pgd) {
        this.lstDulieuNt_pgd = lstDulieuNt_pgd;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt_spham() {
        return lstDulieuNt_spham;
    }

    public void setLstDulieuNt_spham(List<QT_DULIEU_NT> lstDulieuNt_spham) {
        this.lstDulieuNt_spham = lstDulieuNt_spham;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt_phanbo() {
        return lstDulieuNt_phanbo;
    }

    public void setLstDulieuNt_phanbo(List<QT_DULIEU_NT> lstDulieuNt_phanbo) {
        this.lstDulieuNt_phanbo = lstDulieuNt_phanbo;
    }

    public void setLstNgnhanDm(List<ListValue> lstNgnhanDm) {
        this.lstNgnhanDm = lstNgnhanDm;
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

    public List<ListValue> getLstAllNhaptaycn() {
        return lstAllNhaptaycn;
    }

    public void setLstAllNhaptaycn(List<ListValue> lstAllNhaptaycn) {
        this.lstAllNhaptaycn = lstAllNhaptaycn;
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

    public String getKhoa_nhaptaycn() {
        return khoa_nhaptaycn;
    }

    public void setKhoa_nhaptaycn(String khoa_nhaptaycn) {
        this.khoa_nhaptaycn = khoa_nhaptaycn;
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

    public String getTt_nha_dt() {
        return tt_nha_dt;
    }

    public void setTt_nha_dt(String tt_nha_dt) {
        this.tt_nha_dt = tt_nha_dt;
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
