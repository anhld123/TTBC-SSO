/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.test;

import vbsp.ims.sbv.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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
import vbsp.ims.excel.exportDataExcelTemplate;

/**
 *
 * @author BAOANH
 */
public class actionExpSbv extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien cho xuat bao cao">
    protected String Grade; //lay cho cấp báo cáo
    protected String UserName;//lấy cho user
    protected String Message;//cho message
    private String tonghop;

    //Bien cho doc xml tu file excel
    private imsTemplateConfig objExcelTemplate = imsTemplateConfig.newInstance();
    private xmlTemplateConfig xmlConfig = imsXmlTemplateConfig.newInstance();

    //Khai bao cac doi tuong cho tham so
    private List<ListValue> lstLoaibc = new ArrayList<ListValue>();
    private List<ListValue> lstKybc = new ArrayList<ListValue>();
    private List<ListValue> lstLoaifile = new ArrayList<ListValue>();
    private List<ListValue> lstMacn = new ArrayList<ListValue>();
    //Khai bao bien lay cho action
    private String loai_bc, ky_bc, loai_file, macn, ngay_bc, lan_xuat, mabc, group_id;

    private String defaultky_bc;

    protected List<ListValue> lstParameters = new ArrayList<>();

//    protected TreeNode tree_bc = new TreeNode();
    protected List<TreeNode> tree_bc = new ArrayList<TreeNode>();

    protected List<String> ma_bc = new ArrayList<String>();
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
    public String loadParameters() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, List<ListValue>> hmParameter = daoExpSbv.newInstance().getDmParaExp(UserName, Grade);

            setLstKybc(hmParameter.get("ky_bc"));
            setLstLoaibc(hmParameter.get("loai_bc"));
            setLstLoaifile(hmParameter.get("loai_file"));
            setLstMacn(hmParameter.get("macn"));
//            setKy_bc("1");
//            setLoai_bc("S");
//            setLoai_file("M");
//            setMacn("000100");
            setDefaultky_bc("1");
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
            tree_bc = new ArrayList<TreeNode>();
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmpara = getParameter();
            System.err.println("kybc=" + ky_bc);
//            if (ky_bc == null) {
            HashMap<String, List<ListValue>> hmParameter = daoExpSbv.newInstance().getDmParaExp(UserName, Grade);

            setLstKybc(hmParameter.get("ky_bc"));
//            }
            List<ModelTreeNode> lstNode = daoExpSbv.newInstance().getDataPosTreeNode(UserName, Grade);
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
            System.err.println("kybc=" + ky_bc);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            if (ky_bc == null) {
                setKy_bc("1");
            }
            //lstObjRpt = daoExpSbv.newInstance().getLoadAllQuery(ky_bc, Grade);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadAllRptExp -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String exportSbv() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (ky_bc == null || ky_bc.equals("-1")) {
                addActionError("Bạn phải chọn kỳ báo cáo");
                return ERROR;
            }
            if (mabc == null || mabc.equals("-1")) {
                addActionError("Bạn phải chọn báo cáo");
                return ERROR;
            }
            if (loai_bc == null || loai_bc.equals("-1")) {
                addActionError("Bạn phải chọn loại báo cáo cần gửi");
                return ERROR;
            }
            if (loai_file == null || loai_file.equals("-1")) {
                addActionError("Bạn phải chọn loại file báo cáo cần tạo");
                return ERROR;
            }
            if (macn == null || macn.equals("-1")) {
                addActionError("Bạn phải chọn chi nhánh cần tạo báo cáo");
                return ERROR;
            }
            System.err.println("ngay_bc=" + ngay_bc);
            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngay_bc);
            ngay_bc = new SimpleDateFormat("dd-MMM-yyyy").format(sdf);
            System.err.println("ngay_bc=" + ngay_bc);
            //De test thu 2 bao cao
            System.err.println("mabc=" + mabc);
            List<String> lstMabc = new ArrayList<>();
            lstMabc.add(mabc);
//            lstMabc.add("G00014");
            List<ListRptExpModel> lstRptExp = daoExpSbv.newInstance().
                    getReportExportExcelTemplate(Grade, lstMabc, getPathRoot()+Define.M_EXCEL_CONFIG);
            Connection conn = new DaoConnect().getConnect();
            for (ListRptExpModel exp : lstRptExp) {
                fileNamelocal = Xuatbaocao(conn, exp.getFile_excel(), exp.getMa_bc(),
                        macn, tonghop, ky_bc, ngay_bc, loai_bc, loai_file, "1", exp.getLstSheetName());
            }
            if (conn != null) {
                conn.close();
            }
            if (fileNamelocal.lastIndexOf("\\") > 0) {
                filereport = fileNamelocal.substring(fileNamelocal.lastIndexOf("\\") + 1, fileNamelocal.length());
            } else if (fileNamelocal.lastIndexOf("/") > 0) {
                filereport = fileNamelocal.substring(fileNamelocal.lastIndexOf("/") + 1, fileNamelocal.length());
            } else {
                filereport = fileNamelocal;
            }
            //ket thu test thu
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " exportSbv -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
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
    private String Xuatbaocao(Connection conn, String file_excel, String mabc, String ma_cn, String tong_hop, String kybc,
            String ngaybc, String guimoi_quahan, String loai_bc, String langui_bc) throws Exception {
        String fileBaocao_output = "";
        try {
            if (conn.isClosed()) {
                conn = new DaoConnect().getConnect();
            }
            if (file_excel == null || file_excel.equals("") || file_excel.equals(" ")) {
                throw new Exception("File excel template null ");
            }
            //kiem tra file neu khong tim thay thi return ra exception
            if (!isCheckfile(file_excel)) {
                throw new Exception("Not found file " + file_excel);
            }
            String extend_file = getExtendFile(file_excel);
            //lay ra file xml cấu hình
            String xmlFile = getFileXmlConfig(file_excel);
            //doc file xml va lay ra cac doi tuong
            objExcelTemplate = this.xmlConfig.readXmlFile(xmlFile);
            //lay ra cac bien config cho mau
            List<Node> lstNodePara = objExcelTemplate.getParameter();
            //lay ra duong dan root
            String path = getPathRoot();

            String file_name_nhnn = daoExpSbv.newInstance().getFileNameExport(conn, mabc, ma_cn,
                    kybc, ngaybc, guimoi_quahan, loai_bc, langui_bc) + "." + extend_file;

            //Duong dan toi thang file mau va file xuat ra
            String fullPathFileIn = path + Define.M_EXCEL_CONFIG + file_excel;
            String fullPathFileOut = path + Define.M_REPORT_XLS + file_name_nhnn;
            fileBaocao_output = fullPathFileOut;
            Map parameters = new HashMap();
            //xu ly cho phan tham so vi khong load tham so tu bao cao nen phai tim tham so
            for (Node node : lstNodePara) {
                //Lay ra bien duoc cau hinh cho mau
                String para = node.getKey();
                //Neu bien la MAPGD thi dua ma cn vao neu la ngaybc thi cua ngay bc vao
                ImsFillParaMeter clspara = null;
                if (para.contains("MAPGD")) {
                    clspara = ImsFillParaMeter.newInstance(node.getValue(), ma_cn);
                    parameters.put(node.getKey(), clspara);
                } else if (para.contains("NGAYBC")) {
                    clspara = ImsFillParaMeter.newInstance(node.getValue(), ngaybc);
                    parameters.put(node.getKey(), clspara);
                } else if (para.contains("TONGHOP")) {
                    clspara = ImsFillParaMeter.newInstance(node.getValue(), tong_hop);
                    parameters.put(node.getKey(), clspara);
                } else { //truong hop khac chua dua vao
                    clspara = ImsFillParaMeter.newInstance(node.getValue(), null);
                    parameters.put(node.getKey(), clspara);
                }
                //khoi tao lop cho tham so
                //ImsFillParaMeter clspara = ImsFillParaMeter.newInstance(node.getValue(), hmPara.get(node.getKey()));

            }
            //Chuyen duong dan ve URI
            File file = new File(fullPathFileIn);
            URI uri = file.toURI();
            file = new File(uri.toURL().getFile());
            //Lay ra danh sach cac truy van du lieu
            List<imsQueryConfig> lstQuery = objExcelTemplate.getLstQuery();
            //kiểm tra file cấu hình để fill dữ liệu.
            boolean bconfig = false;
            String fileConfig = null;
            //Lay ra danh sach cac sheet cua file excel mau
            List<String> lstSheetName = getSheetName(fullPathFileIn);
            //Kiem tra cac file cau hinh de fill du lieu
            for (int i = 0; i < lstSheetName.size(); i++) {
                String sheetName = lstSheetName.get(i);
                //Phai viet 2 truong hop vi tren linux co phan biet hoa thuong
                if (isCheckfile(sheetName + ".xml")) {
                    fileConfig = path + Define.M_EXCEL_CONFIG + sheetName + ".xml";
                    bconfig = true;
                    break;
                } else if (isCheckfile(sheetName + ".XML")) {
                    fileConfig = path + Define.M_EXCEL_CONFIG + sheetName + ".XML";
                    bconfig = true;
                    break;
                } else {
                    bconfig = false;
                }
            }
            //Xu ly lay du lieu cho tung truy van 
            exportDataExcelTemplate dataexp = new exportDataExcelTemplate();
            try (InputStream is = new FileInputStream(file)) {
                try (OutputStream os = new FileOutputStream(fullPathFileOut)) {
                    Context context = new Context();
                    //Thuc hien lay du lieu cho tat ca cac truy van
                    for (int i = 0; i < lstQuery.size(); i++) {
                        List<String> qryparameters = lstQuery.get(i).splitParaProcedure();
                        String qryPara = lstQuery.get(i).removeParaProcedure();
                        PlSqlQueryExecuterIms execute = new PlSqlQueryExecuterIms(conn, qryparameters, parameters, qryPara);
                        ResultSet reset = null;
                        //Neu bien luu du lieu co noi dung la header thi lay du lieu trong procedure chu ko lay du lieu cau hinh
                        if (lstQuery.get(i).getMapData().toLowerCase().contains("header")) {
                            //reset = daoExpSbv.newInstance().getHeaderReport(conn, file_name_nhnn, ngaybc, macn, kybc);
                        } else {
                            reset = execute.createDatasource();
                        }
                        //lay ra cot group 
                        String group_key = lstQuery.get(i).getKey_group();
                        String group_value = lstQuery.get(i).getValue_group();
                        //neu group la empty (khong co group du lieu)
                        if (group_key != null && !group_key.equals("EMPTY")) {
                            List<GroupDataClass> lstGroupData = dataexp.getDataGroupCollect(reset, group_key, group_value);
                            context.putVar(lstQuery.get(i).getMapData(), lstGroupData);
                        } else {
                            List<ColumnReportTemplate> lstdata = dataexp.getDataCollect(reset);
                            context.putVar(lstQuery.get(i).getMapData(), lstdata);
                        }
                        if (reset != null) {
                            reset.close();
                        }
                    }
                    //Fill du lieu truong hop co file cau hinh
                    if (bconfig) {
                        Transformer transformer = TransformerFactory.createTransformer(is, os);
                        //Chu y doan nay la file xml de fill du lieu dat bang voi ten sheet
                        for (int i = 0; i < lstSheetName.size(); i++) {
                            String sheetName = lstSheetName.get(i);
                            //Phai viet 2 truong hop vi tren linux co phan biet hoa thuong
                            if (isCheckfile(sheetName + ".xml")) {
                                fileConfig = path + Define.M_EXCEL_CONFIG + sheetName + ".xml";
                                putDataExcel(transformer, context, sheetName, fileConfig);
                            } else if (isCheckfile(sheetName + ".XML")) {
                                fileConfig = path + Define.M_EXCEL_CONFIG + sheetName + ".XML";
                                putDataExcel(transformer, context, sheetName, fileConfig);
                            }
                        }
                        transformer.write();

                    } else {
                        JxlsHelper.getInstance().processTemplate(is, os, context);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " Xuatbaocao -> " + e.getMessage());
            throw new Exception();
        }
        return fileBaocao_output;
    }
private String Xuatbaocao(Connection conn, String file_excel, String mabc, String ma_cn, String tong_hop, String kybc,
            String ngaybc, String guimoi_quahan, String loai_bc, String langui_bc, List<String> lstSheetName) throws Exception {
        String fileBaocao_output = "";
        try {
            if (conn.isClosed()) {
                conn = new DaoConnect().getConnect();
            }
            if (file_excel == null || file_excel.equals("") || file_excel.equals(" ")) {
                throw new Exception("File excel template null ");
            }
            //kiem tra file neu khong tim thay thi return ra exception
            if (!isCheckfile(file_excel)) {
                throw new Exception("Not found file " + file_excel);
            }
            String extend_file = getExtendFile(file_excel);
            //lay ra file xml cấu hình
            String xmlFile = getFileXmlConfig(file_excel);
            //doc file xml va lay ra cac doi tuong
            objExcelTemplate = this.xmlConfig.readXmlFile(xmlFile);
            //lay ra cac bien config cho mau
            List<Node> lstNodePara = objExcelTemplate.getParameter();
            //lay ra duong dan root
            String path = getPathRoot();

            String file_name_nhnn = daoExpSbv.newInstance().getFileNameExport(conn, mabc, ma_cn,
                    kybc, ngaybc, guimoi_quahan, loai_bc, langui_bc) + "." + extend_file;

            //Duong dan toi thang file mau va file xuat ra
            String fullPathFileIn = path + Define.M_EXCEL_CONFIG + file_excel;
            String fullPathFileOut = path + Define.M_REPORT_XLS + file_name_nhnn;
            fileBaocao_output = fullPathFileOut;
            Map parameters = new HashMap();
            //xu ly cho phan tham so vi khong load tham so tu bao cao nen phai tim tham so
            for (Node node : lstNodePara) {
                //Lay ra bien duoc cau hinh cho mau
                String para = node.getKey();
                //Neu bien la MAPGD thi dua ma cn vao neu la ngaybc thi cua ngay bc vao
                ImsFillParaMeter clspara = null;
                if (para.contains("MAPGD")) {
                    clspara = ImsFillParaMeter.newInstance(node.getValue(), ma_cn);
                    parameters.put(node.getKey(), clspara);
                } else if (para.contains("NGAYBC")) {
                    clspara = ImsFillParaMeter.newInstance(node.getValue(), ngaybc);
                    parameters.put(node.getKey(), clspara);
                } else if (para.contains("TONGHOP")) {
                    clspara = ImsFillParaMeter.newInstance(node.getValue(), tong_hop);
                    parameters.put(node.getKey(), clspara);
                } else { //truong hop khac chua dua vao
                    clspara = ImsFillParaMeter.newInstance(node.getValue(), null);
                    parameters.put(node.getKey(), clspara);
                }
                //khoi tao lop cho tham so
                //ImsFillParaMeter clspara = ImsFillParaMeter.newInstance(node.getValue(), hmPara.get(node.getKey()));

            }
            //Chuyen duong dan ve URI
            File file = new File(fullPathFileIn);
            URI uri = file.toURI();
            file = new File(uri.toURL().getFile());
            //Lay ra danh sach cac truy van du lieu
            List<imsQueryConfig> lstQuery = objExcelTemplate.getLstQuery();
            //kiểm tra file cấu hình để fill dữ liệu.
            boolean bconfig = false;
            String fileConfig = null;
            //Lay ra danh sach cac sheet cua file excel mau
//            List<String> lstSheetName = getSheetName(fullPathFileIn);
            //Kiem tra cac file cau hinh de fill du lieu
            for (int i = 0; i < lstSheetName.size(); i++) {
                String sheetName = lstSheetName.get(i);
                //Phai viet 2 truong hop vi tren linux co phan biet hoa thuong
                if (isCheckfile(sheetName + ".xml")) {
                    fileConfig = path + Define.M_EXCEL_CONFIG + sheetName + ".xml";
                    bconfig = true;
                    break;
                } else if (isCheckfile(sheetName + ".XML")) {
                    fileConfig = path + Define.M_EXCEL_CONFIG + sheetName + ".XML";
                    bconfig = true;
                    break;
                } else {
                    bconfig = false;
                }
            }
            //Xu ly lay du lieu cho tung truy van 
            exportDataExcelTemplate dataexp = new exportDataExcelTemplate();
            try (InputStream is = new FileInputStream(file)) {
                try (OutputStream os = new FileOutputStream(fullPathFileOut)) {
                    Context context = new Context();
                    //Thuc hien lay du lieu cho tat ca cac truy van
                    for (int i = 0; i < lstQuery.size(); i++) {
                        List<String> qryparameters = lstQuery.get(i).splitParaProcedure();
                        String qryPara = lstQuery.get(i).removeParaProcedure();
                        PlSqlQueryExecuterIms execute = new PlSqlQueryExecuterIms(conn, qryparameters, parameters, qryPara);
                        ResultSet reset = null;
                        //Neu bien luu du lieu co noi dung la header thi lay du lieu trong procedure chu ko lay du lieu cau hinh
                        if (lstQuery.get(i).getMapData().toLowerCase().contains("header")) {
                            //reset = daoExpSbv.newInstance().getHeaderReport(conn, file_name_nhnn, ngaybc, macn, kybc);
                        } else {
                            reset = execute.createDatasource();
                        }
                        //lay ra cot group 
                        String group_key = lstQuery.get(i).getKey_group();
                        String group_value = lstQuery.get(i).getValue_group();
                        //neu group la empty (khong co group du lieu)
                        if (group_key != null && !group_key.equals("EMPTY")) {
                            List<GroupDataClass> lstGroupData = dataexp.getDataGroupCollect(reset, group_key, group_value);
                            context.putVar(lstQuery.get(i).getMapData(), lstGroupData);
                        } else {
                            List<ColumnReportTemplate> lstdata = dataexp.getDataCollect(reset);
                            context.putVar(lstQuery.get(i).getMapData(), lstdata);
                        }
                        if (reset != null) {
                            reset.close();
                        }
                    }
                    //Fill du lieu truong hop co file cau hinh
                    if (bconfig) {
                        Transformer transformer = TransformerFactory.createTransformer(is, os);
                        //Chu y doan nay la file xml de fill du lieu dat bang voi ten sheet
                        for (int i = 0; i < lstSheetName.size(); i++) {
                            String sheetName = lstSheetName.get(i);
                            //Phai viet 2 truong hop vi tren linux co phan biet hoa thuong
                            if (isCheckfile(sheetName + ".xml")) {
                                fileConfig = path + Define.M_EXCEL_CONFIG + sheetName + ".xml";
                                putDataExcel(transformer, context, sheetName, fileConfig);
                            } else if (isCheckfile(sheetName + ".XML")) {
                                fileConfig = path + Define.M_EXCEL_CONFIG + sheetName + ".XML";
                                putDataExcel(transformer, context, sheetName, fileConfig);
                            }
                        }
                        transformer.write();

                    } else {
                        JxlsHelper.getInstance().processTemplate(is, os, context);
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " Xuatbaocao -> " + e.getMessage());
            throw new Exception();
        }
        return fileBaocao_output;
    }
    private String getFileXmlConfig(String excel_temp) throws Exception {
        if (excel_temp == null || excel_temp.equals("") || excel_temp.equals(" ")) {
            throw new Exception("File excel template null ");
        }
        //lay ra duong dan to thu muc luu file
        String filePath = getPathRoot();
        //lay den thu muc luu file
        filePath += Define.M_EXCEL_CONFIG;
        //lay ra ten file xml
        String xmlTemp = getFileXmlFromExelFile(excel_temp);
        if (xmlTemp == null || xmlTemp.isEmpty()) {
            throw new Exception("Incorrect file excel template extend " + excel_temp);
        }
        //kiem tra file excel va file xml xem co khong
        if (!new File(filePath + xmlTemp).exists() || !new File(filePath + excel_temp).exists()) {
            throw new Exception("Not found file xml config " + filePath + xmlTemp);
        }
        return filePath + xmlTemp;
    }

    public String getFileXmlFromExelFile(String NameFileExcel) {
        if (NameFileExcel == null) {
            return "";
        }
        if (NameFileExcel.indexOf(".") < 0) {
            return "";
        }

        return NameFileExcel.substring(0, NameFileExcel.toLowerCase().lastIndexOf(".xls")) + ".XML";
    }

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
    private String layTenFileBaocaoGuiNHNN(Connection conn, String mabc, String ma_cn, String kybc,
            String ngaybc, String guimoi_quahan, String loai_bc, String langui_bc) throws Exception {
        String fileName = daoExpSbv.newInstance().getFileNameExport(mabc, ma_cn, kybc, ngaybc, guimoi_quahan, loai_bc, langui_bc);

        return fileName;
    }

    public String getGroup_id() {
        return group_id;
    }

    public List<ListValue> getLstObjRpt() {
        return lstObjRpt;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khai bao get/set">
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

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getMabc() {
        return mabc;
    }

    public void setMabc(String mabc) {
        this.mabc = mabc;
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

    public String getLan_xuat() {
        return lan_xuat;
    }

    public void setLan_xuat(String lan_xuat) {
        this.lan_xuat = lan_xuat;
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
//</editor-fold>

}
