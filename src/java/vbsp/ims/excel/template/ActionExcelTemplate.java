/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.excel.template;

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
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;
import org.jxls.area.Area;
import org.jxls.builder.AreaBuilder;
import org.jxls.builder.xml.XmlAreaBuilder;
import org.jxls.common.CellRef;
import org.jxls.common.Context;
import org.jxls.transform.Transformer;
import org.jxls.util.JxlsHelper;
import org.jxls.util.TransformerFactory;
import org.w3c.dom.Document;
import vbsp.ims.core.ColumnReportTemplate;
import vbsp.ims.core.GroupDataClass;
import vbsp.ims.core.MappingClassValue;
import vbsp.ims.core.Node;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.PlSqlQueryExecuterIms;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.xml.imsQueryConfig;
import vbsp.ims.xml.imsTemplateConfig;
import vbsp.ims.xml.imsXmlTemplateConfig;
import vbsp.ims.xml.xmlTemplateConfig;

/**
 *
 * @author BAOANH
 */
public class ActionExcelTemplate extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai báo biến">
    protected String Grade; //lay cho cấp báo cáo
    protected String UserName;//lấy cho user
    protected String Message;//cho message
    private String group_id;//cho nhóm báo cáo
    protected List<ListValue> lstParameters = new ArrayList<>(); //cho danh sách tham số khi load trên jsp xuống
    private List<ListValue> lstObjRpt = new ArrayList<>();//cho select các báo báo
    private List<ListValue> lstObjGroup = new ArrayList<ListValue>();//cho danh sách nhóm báo cáo
    private List<ListValue> lstGrade = new ArrayList<ListValue>();//danh sách các cấp báo cáo 1,2,3
    private String[] defaultGrade;//giá trị mặc định cho danh sách cấp được tạo báo cáo ["1","2","3"]
    private List<String> rptGrade = new ArrayList<>();//danh sách các báo cáo
    private String description;//cho phần mô tả cho truy vấn/ tham số
    private File fileUpload;//cho file upload
    private String fileUploadFileName; //cho tên file upload
    private String[] Ext_File = {"xls", "xlsx"}; //phần mở rộng của file
    private String fileTemplate; //file excel template 
    private String defaultClassName; //khoi tao giá trị mặc định cho class name
    private String class_name; //tên class name lấy ra trên select option
    private String mapdata; //tên biến dữ liệu map
    private String query; //truy vấn lấy ra
    private String parameter;//tham số lấy ra
    private String key_hidden; //giá trị key để ẩn trên jsp cho tham số, truy vấn
    private String fileOutFormat;//định dạng file đầu ra
    private String id;
    private imsTemplateConfig objExcelTemplate = imsTemplateConfig.newInstance();
    private xmlTemplateConfig xmlConfig = imsXmlTemplateConfig.newInstance();
    private List<ReportParam> lstParameterExp;
    private String fileNamelocal;
    private String filereport;
    private String key_group;
    private String value_group;
    private List<ListValue> lstObjField = new ArrayList<>();//Laay tat ca cac field trong truy vaan
    private Map<String, String> columnNameMap = new LinkedHashMap<String, String>(); //load all field trong truy van
    private String dummyMsg;
    private String defaultKeyGroup;
    private String defaultValueGroup;
    private String module;
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="thiet lap get/set">

    public String getModule() {
        return module;
    }

    public void setModule(String module) {
        this.module = module;
    }

    public String getDefaultKeyGroup() {
        return defaultKeyGroup;
    }

    public void setDefaultKeyGroup(String defaultKeyGroup) {
        this.defaultKeyGroup = defaultKeyGroup;
    }

    public String getDefaultValueGroup() {
        return defaultValueGroup;
    }

    public void setDefaultValueGroup(String defaultValueGroup) {
        this.defaultValueGroup = defaultValueGroup;
    }

    public String getDummyMsg() {
        return dummyMsg;
    }

    public void setDummyMsg(String dummyMsg) {
        this.dummyMsg = dummyMsg;
    }

    public Map<String, String> getColumnNameMap() {
        return columnNameMap;
    }

    public void setColumnNameMap(Map<String, String> columnNameMap) {
        this.columnNameMap = columnNameMap;
    }

    public List<ListValue> getLstObjField() {
        return lstObjField;
    }

    public void setLstObjField(List<ListValue> lstObjField) {
        this.lstObjField = lstObjField;
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

    public List<ReportParam> getLstParameterExp() {
        return lstParameterExp;
    }

    public void setLstParameterExp(List<ReportParam> lstParameterExp) {
        this.lstParameterExp = lstParameterExp;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }

    public List<ListValue> getLstObjRpt() {
        return lstObjRpt;
    }

    public void setLstObjRpt(List<ListValue> lstObjRpt) {
        this.lstObjRpt = lstObjRpt;
    }

    public List<ListValue> getLstObjGroup() {
        return lstObjGroup;
    }

    public void setLstObjGroup(List<ListValue> lstObjGroup) {
        this.lstObjGroup = lstObjGroup;
    }

    public List<ListValue> getLstGrade() {
        return lstGrade;
    }

    public void setLstGrade(List<ListValue> lstGrade) {
        this.lstGrade = lstGrade;
    }

    public String[] getDefaultGrade() {
        return defaultGrade;
    }

    public void setDefaultGrade(String[] defaultGrade) {
        this.defaultGrade = defaultGrade;
    }

    public List<String> getRptGrade() {
        return rptGrade;
    }

    public void setRptGrade(List<String> rptGrade) {
        this.rptGrade = rptGrade;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String[] getExt_File() {
        return Ext_File;
    }

    public void setExt_File(String[] Ext_File) {
        this.Ext_File = Ext_File;
    }

    public String getFileTemplate() {
        return fileTemplate;
    }

    public void setFileTemplate(String fileTemplate) {
        this.fileTemplate = fileTemplate;
    }

    public String getDefaultClassName() {
        return defaultClassName;
    }

    public void setDefaultClassName(String defaultClassName) {
        this.defaultClassName = defaultClassName;
    }

    public String getClass_name() {
        return class_name;
    }

    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

    public String getMapdata() {
        return mapdata;
    }

    public void setMapdata(String mapdata) {
        this.mapdata = mapdata;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getKey_hidden() {
        return key_hidden;
    }

    public void setKey_hidden(String key_hidden) {
        this.key_hidden = key_hidden;
    }

    public String getFileOutFormat() {
        return fileOutFormat;
    }

    public void setFileOutFormat(String fileOutFormat) {
        this.fileOutFormat = fileOutFormat;
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

    public String getKey_group() {
        return key_group;
    }

    public void setKey_group(String key_group) {
        this.key_group = key_group;
    }

    public String getValue_group() {
        return value_group;
    }

    public void setValue_group(String value_group) {
        this.value_group = value_group;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Các hàm chung">
    /**
     * Hàm này lấy ra giá trị trong sesstion gồm user, cấp đăng nhập
     *
     * @return
     */
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

    /**
     * Hàm này convert một mảng chuỗi về liểu list
     *
     * @param value
     * @return
     */
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

    /**
     * Hàm này lấy ra tất cả các tham số trên giao diện html đẩy xuống
     *
     * @return
     * @throws Exception
     */
    protected HashMap<String, Object> getParameterObj() throws Exception {
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

    /**
     * Hàm này sẽ trả ra tên file xml từ tên file excel truyền vào
     *
     * @param NameFileExcel
     * @return
     */
    public String getFileXmlFromExelFile(String NameFileExcel) {
        if (NameFileExcel == null) {
            return "";
        }
        if (NameFileExcel.indexOf(".") < 0) {
            return "";
        }

        return NameFileExcel.substring(0, NameFileExcel.toLowerCase().lastIndexOf(".xls")) + ".XML";
    }

    /**
     * Hàm này lấy ra đường dẫn chi tiết tới file xml từ file excel truyền vào
     *
     * @param excel_temp
     * @return
     */
    private String getCheckXml(String excel_temp) {
        String filePath = ServletActionContext.getServletContext().getRealPath("/");
        filePath = DefineFun.backlashReplace(filePath);
        if (!filePath.endsWith("/") || !filePath.endsWith("\\")) {
            filePath += "/";
        }
        filePath += Define.M_EXCEL_CONFIG;
        String xmlTemp = getFileXmlFromExelFile(excel_temp);
        if (xmlTemp == null || xmlTemp.isEmpty()) {
            addActionError("Không đúng mẫu template excel " + excel_temp + " trong thư mục " + filePath);
            return null;
        }
        //kiem tra file excel va file xml xem co co khong
        if (!new File(filePath + xmlTemp).exists() || !new File(filePath + excel_temp).exists()) {
            addActionError("Không tìm thấy file mẫu " + excel_temp + " trong thư mục " + filePath);
            return null;
        }
        return filePath + xmlTemp;
    }

    /**
     * Khởi tạo form khi thêm báo cáo
     *
     * @return
     */
    public String AddNewExcel() {
        try {
//            addActionError(getErrorMessages().toString());
            lstGrade.add(new ListValue("1", "Ngân hàng"));
            lstGrade.add(new ListValue("2", "Chi nhánh"));
            lstGrade.add(new ListValue("3", "Toàn quốc"));

            setDefaultGrade(new String[]{"1", "2", "3"});
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            DaoExcelTemplate daoQuery = new DaoExcelTemplate();
            lstObjGroup = daoQuery.getGroupQuery(conn, null);
            //khoi tao rieng cho module sbv
            if (module != null) {
                if (module.equals("SBV")) {
                    setLstObjRpt(daoQuery.getbcSbv(conn));
                    setGroup_id(group_id);
                }
            }

            System.err.println("module=" + module + " group_id=" + group_id);
//            lstObjRpt = daoQuery.getLoadAllQuery(conn, UserName, group_id, Grade);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            addActionError("Lỗi khi khởi tạo " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    /**
     * Ham nay cho action luu file bao cao
     *
     * @return
     */
    public String AddFileTemplateExcel() {
        try {
//            addActionError(getErrorMessages().toString());
            if (module != null) {
                if (module.equals("SBV")) {
                    if (id == null || id.equals("-1")) {
                        addActionError("Bạn phải chọn báo cáo để lưu ID !");
                        return ERROR;
                    }
                }
            }
            if (group_id.equals("-1") || group_id == null) {
                addActionError("Bạn phải chọn nhóm báo cáo !");
                return ERROR;
            }
            if (description == null || description.isEmpty()) {
                addActionError("Bạn phải điền mô tả cho báo cáo !");
                return ERROR;
            }
            if (rptGrade == null || rptGrade.isEmpty()) {
                addActionError("Bạn phải chọn cấp xuất báo cáo !");
                return ERROR;
            }
            if (fileUploadFileName == null) {
                addActionError("Bạn phải chọn file excel mẫu trước khi lưu !");
                return ERROR;
            }
            if (fileUploadFileName.indexOf(" ") >= 0) {
                addActionError("Bạn phải chọn file excel khác, File mẫu không được có ký tự \" \" (ký tự space) !");
                return ERROR;
            }
            if (fileOutFormat == null || fileOutFormat.isEmpty()) {
                addActionError("Bạn phải điền định sạng file cần xuất !");
                return ERROR;
            }
            if (!getParaSession()) {
                return ERROR;
            }

            boolean bExt = false;
            //Lay duong dan goc thu muc
            String filePath = ServletActionContext.getServletContext().getRealPath("/");
            filePath = DefineFun.backlashReplace(filePath);
            if (!filePath.endsWith("/") || !filePath.endsWith("\\")) {
                filePath += "/";
            }
            //cong them thu muc can luu trữ
            filePath += Define.M_EXCEL_CONFIG;
            //Kiem tra xem neu thu muc nay chua co thi tao ra
            File checkdir = new File(filePath);
            if (!checkdir.exists()) {
                System.err.println("Tao duong dan " + filePath);
                checkdir.mkdirs();
            }
            //kiem tra file xem co dung đinh dạng ko
            for (int i = 0; i < Ext_File.length; i++) {
                if (fileUploadFileName.toLowerCase().endsWith(Ext_File[i])) {
                    bExt = true;
                    break;
                }
            }
            if (!bExt) {
                addActionError("Bạn upload định dạng file không đúng chỉ được phép upload file *.xls, *.xlsx");
                return ERROR;
            }
            DaoExcelTemplate dao = new DaoExcelTemplate();
//            if (save_id != null) {
//                String sUserCreaterpt = daoQuery.getUserCreateReport(save_id);
//                if (!sUserCreaterpt.toLowerCase().equals(sUserName.toLowerCase())) {
//                    setMessage("Bạn chưa lưu được báo cáo, Do bạn không phải là người tạo báo cáo! Người tạo báo cáo là: " + sUserCreaterpt);
//                    return ERROR;
//                }
//            }

            String sGradeReport = "";
            for (String sRptGrade : rptGrade) {
                sGradeReport += "#" + sRptGrade;
            }
            sGradeReport += "#";
            objExcelTemplate.setDesCription(description);
            objExcelTemplate.setFileName(this.fileUploadFileName);
            objExcelTemplate.setFileOutFormat(fileOutFormat);
            objExcelTemplate.setUserName(UserName);
            objExcelTemplate.setDate(new SimpleDateFormat("dd-MMM-yyyy HH:mm:SS").format(new Date()));
            //add thêm tham số là cursor cho mẫu 
            List<vbsp.ims.core.Node> lstParaCursor = new ArrayList<>();
            lstParaCursor.add(vbsp.ims.core.Node.newInstance("ORACLE_REF_CURSOR", "java.sql.ResultSet", "Tham số mặc định cho cursor khi thực hiện với procedure, package"));
            objExcelTemplate.setParameter(lstParaCursor);
            //tu ten file excel upload cat ra lay ten va dua ve file xml
            String xmlName = getFileXmlFromExelFile(this.fileUploadFileName);
            //Kiểm tra file xem da co chua neu co file mẫu này rồi thì return
            //ResultSet
            //ney key_hiden ma  null va  empty (day la truong hop them moi)
            if (key_hidden == null || key_hidden.trim().isEmpty() || key_hidden.equals("")) {
                if (!xmlConfig.createFileConfig(filePath + xmlName, objExcelTemplate)) {
                    addActionError("Bạn chưa tạo được file cấu hình xml xin liên hệ với quản trị ");
                    return ERROR;
                }
                int outCount = dao.saveExcelTemplate(UserName, this.fileUploadFileName, group_id, description, sGradeReport, key_hidden, id);
                if (outCount > 0) {
                    addActionError("File excel mẫu này đã có bạn phải đổi tên mẫu hoặc chọn file excel khác ");
                    return ERROR;
                }
            } else {//truong hop la sua mau
                int outCount = dao.saveExcelTemplate(UserName, key_hidden, group_id, description, sGradeReport, this.fileUploadFileName, id);
                if (outCount > 0) {
                    addActionError("File excel mẫu này đã có bạn phải đổi tên mẫu hoặc chọn file excel khác ");
                    return ERROR;
                }
                //copy file xml ra file moi
                //Lay ra ten file xml moi
                String nameXmlNew = getFileXmlFromExelFile(this.fileUploadFileName);
                //lay ra ten file xml cu
                String nameXmlOld = getFileXmlFromExelFile(this.key_hidden);
                File fileXmlNew = new File(filePath, nameXmlNew);

                File fileXmlOld = new File(filePath, nameXmlOld);
                Document doc = null;
                if (!this.key_hidden.toUpperCase().equals(this.fileUploadFileName.toUpperCase())) {
                    FileUtils.copyFile(fileXmlOld, fileXmlNew);

                    doc = xmlConfig.getDocument(filePath + nameXmlNew);
                } else {
                    doc = xmlConfig.getDocument(filePath + nameXmlOld);
                }
                //update ten file va mota trong file xml
                if (!xmlConfig.updateAttribute(doc, xmlTemplateConfig.excel_template,
                        xmlTemplateConfig.name, this.fileUploadFileName)) {
                    addActionError("Update tên file trong file xml bị lỗi " + fileUploadFileName);
                    return ERROR;
                }

                if (!xmlConfig.updateAttribute(doc, xmlTemplateConfig.excel_template,
                        xmlTemplateConfig.fileOutFormat, fileOutFormat)) {
                    addActionError("Update định dạng file trong xml lỗi " + fileOutFormat);
                    return ERROR;
                }

                if (!xmlConfig.updatevalue(doc, xmlTemplateConfig.title,
                        description)) {
                    addActionError("Update tiêu đề trong file xml bị lỗi " + description);
                    return ERROR;
                }
                if (!xmlConfig.updatevalue(doc, xmlTemplateConfig.date,
                        new SimpleDateFormat("dd-MMM-yyyy HH:mm:SS").format(new Date()))) {
                    addActionError("Update thời giam trong file bị lỗi");
                    return ERROR;
                }
                if (!xmlConfig.saveFileXml(filePath + nameXmlNew, doc)) {
                    addActionError("Lỗi bạn chưa lưu được mẫu báo cáo vào file xml");
                    return ERROR;
                }
            }

            //Nếu lưu thành công thì copy file vao thuc muc can luu
            File fileToCreate = new File(filePath, this.fileUploadFileName);
//            if (!this.key_hidden.toUpperCase().equals(this.fileUploadFileName.toUpperCase())) {
            FileUtils.copyFile(this.fileUpload, fileToCreate);
//            }

            addActionMessage("Bạn đã lưu mẫu thành công " + this.fileUploadFileName);
            setFileTemplate(this.fileUploadFileName);
            setMessage("ADD_SUCCESS");
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " AddFileTemplateExcel -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " AddFileTemplateExcel -> " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị: chi tiết lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String LoadEditTemplate() {
        try {
            System.err.println("module=" + module);
            if (!getParaSession()) {
                return ERROR;
            }

            if (fileTemplate == null) {
                addActionError("Bạn phải chọn mẫu cần sửa");
                return ERROR;
            }
            String xmlTemp = getCheckXml(fileTemplate);
            if (xmlTemp == null || xmlTemp.isEmpty()) {
                addActionError("Không thể lấy ra file xml từ file excel mẫu " + fileTemplate);
                return ERROR;
            }

            //cong them thu muc can luu trữ
            objExcelTemplate = xmlConfig.readXmlFile(xmlTemp);

        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " LoadEditTemplate -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " LoadEditTemplate -> " + e.getMessage());
            addActionError("Lỗi không thể load được file xml " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cho phần tham số">
    public String configEditParameter() {
        try {
//            System.err.println(Message);
            HashMap hmPara = getParameterObj();
            if (hmPara == null || hmPara.isEmpty()) {
                addActionError("Không thể lấy ra được tham số ");
                return ERROR;
            }
//            lstParameters.add(new ListValue("java.lang.Boolean", "java.lang.Boolean"));
//            lstParameters.add(new ListValue("java.lang.Byte", "java.lang.Byte"));
            lstParameters.add(new ListValue("java.util.Date", "java.util.Date"));
            lstParameters.add(new ListValue("java.sql.Timestamp", "java.sql.Timestamp"));
            lstParameters.add(new ListValue("java.sql.Time", "java.sql.Time"));
            lstParameters.add(new ListValue("java.lang.Double", "java.lang.Double"));
            lstParameters.add(new ListValue("java.lang.Float", "java.lang.Float"));
            lstParameters.add(new ListValue("java.lang.Integer", "java.lang.Integer"));
            lstParameters.add(new ListValue("java.lang.Long", "java.lang.Long"));
            lstParameters.add(new ListValue("java.lang.Short", "java.lang.Short"));
            lstParameters.add(new ListValue("java.math.BigDecimal", "java.math.BigDecimal"));
            lstParameters.add(new ListValue("java.lang.Number", "java.lang.Number"));
            lstParameters.add(new ListValue("java.lang.String", "java.lang.String"));
//            lstParameters.add(new ListValue("java.util.Collection", "java.util.Collection"));
//            lstParameters.add(new ListValue("java.util.List", "java.util.List"));
//            lstParameters.add(new ListValue("java.lang.Object", "java.lang.Object"));
//            lstParameters.add(new ListValue("java.io.InputStream", "java.io.InputStream"));
            lstParameters.add(new ListValue("java.sql.ResultSet", "java.sql.ResultSet"));

            if (!parameter.toUpperCase().equals("NEW")) {
                objExcelTemplate = xmlConfig.readXmlFile(getCheckXml((String) hmPara.get("fileTemplate")));

                List<Node> nodePara = objExcelTemplate.getParameter();
                for (Node Para : nodePara) {
                    if (Para.getKey().toString().equals(parameter)) {
                        setDefaultClassName(Para.getValue());
                        setDescription(Para.getDescription());
                        setMessage(Para.getKey());
                    }
                }
                setKey_hidden(parameter);
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " configEditParameter -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " configEditParameter -> " + e.getMessage());
            addActionError("Lỗi không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveParameter() {
        try {
            System.err.println(parameter);

            if (fileTemplate == null || fileTemplate.isEmpty()) {
                addActionError("Không thể lấy ra được file mẫu excel ");
                return ERROR;
            }
            if (parameter == null || parameter.isEmpty()) {
                addActionError("Bạn phải điền tham số trước khi lưu ");
                addFieldError("parameter", "Bạn phải điền tham số trước khi lưu");
                return ERROR;
            }
            if (class_name == null || class_name.isEmpty()) {
                addActionError("Bạn phải chọn kiểu dữ liệu trước khi lưu ");
                addFieldError("class_name", "Bạn phải chọn kiểu dữ liệu trước khi lưu ");
                return ERROR;
            }
            if (description == null || description.isEmpty()) {
                addActionError("Bạn phải điền mô tả cho tham số ");
                addFieldError("description", "Bạn phải điền mô tả cho tham số");
                return ERROR;
            }
            //lấy ra tên file xml
            String xmlName = getCheckXml(fileTemplate);
            //load file xml ra dạng document
            Document doc = xmlConfig.getDocument(xmlName);

            //nếu trường hợp là thêm mới thì kiểm tra xem 
            if (key_hidden == null || key_hidden.trim().isEmpty() || key_hidden.equals("")) {
                if (xmlConfig.findAttribute(doc, xmlTemplateConfig.parameter, xmlTemplateConfig.name, parameter)) {
                    addFieldError("parameter", "Tham số này đã có bạn phải điền tham số khác mới lưu được");
                    addActionError("Tham số này đã có bạn phải điền tham số khác mới lưu được ");
                    return ERROR;
                }
            } else { //nếu là trường hợp sửa tham số thì xóa tham số cũ đi
                if (!xmlConfig.deleteAttribute(doc, xmlTemplateConfig.parameter, xmlTemplateConfig.name, key_hidden)) {
                    addActionError("Không thể cập nhật được tham số này ");
                    return ERROR;
                }
            }
            //add thêm tham số mới vào
            if (!xmlConfig.addFileConfig(doc, Node.newInstance(parameter, class_name, description))) {
                addActionError("Không thể lưu được cấu hình cho tham số " + parameter);
                return ERROR;
            }
            //lưu lại file cấu hình xml
            if (!xmlConfig.saveFileXml(xmlName, doc)) {
                addActionError("Lỗi bạn chưa lưu được tham số " + parameter + " vào file xml");
                return ERROR;
            }
            addActionMessage("Bạn đã lưu tham số thành công");
            setMessage("SAVE_PARAMETER");
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " saveParameter -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveParameter -> " + e.getMessage());
            addActionError("Lưu tham số bị lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String deleteParameter() {
        try {
            System.err.println("parameter=" + parameter);

            if (fileTemplate == null || fileTemplate.isEmpty()) {
                addActionError("Không thể lấy ra được file mẫu excel ");
                return ERROR;
            }
            if (parameter == null || parameter.isEmpty()) {
                addActionError("Không thể lấy ra được tham số ");
                return ERROR;
            }

            String xmlName = getCheckXml(fileTemplate);

            if (!xmlConfig.deleteAttribute(xmlName, parameter)) {
                addActionError("Không thể xóa được tham số này ");
                return ERROR;
            }

            addActionMessage("Bạn đã xóa tham số " + parameter + " thành công");
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " deleteParameter -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " deleteParameter -> " + e.getMessage());
            addActionError("Lỗi bạn chưa xóa được tham số xin liểm tra lại " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Cho phần cấu hình truy vấn">
    public String loadColumnQuery() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmPara = getParameterObj();

            String xmlFile = getCheckXml(fileTemplate);
            if (xmlFile == null || xmlFile.isEmpty()) {
                addActionError("Không tìm thấy file cấu hình xml");
                return ERROR;
            }
            //doc file xml va lay ra cac doi tuong
            objExcelTemplate = xmlConfig.readXmlFile(xmlFile);
//            query="{call  BCSL_KHNV( $P{PARA_MAPGD},$P{PARA_NGAYBC},$P{ORACLE_REF_CURSOR})}";
            //lay ra cac bien config cho mau
            List<Node> lstNodePara = objExcelTemplate.getParameter();
//            setLstObjField(DaoExcelTemplate.newInstance().getAllFieldQuery(query, UserName, lstNodePara));
            setColumnNameMap(DaoExcelTemplate.newInstance().getAllColumnNameQuery(query, UserName, lstNodePara));
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " loadColumnQuery -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadColumnQuery -> " + e.getMessage());
            setDummyMsg("Lỗi khi thực hiện getcolumnName của truy vấn " + e.getMessage());
//            return ERROR;
        }
        return SUCCESS;
    }

    public String configEditQuery() {
        try {
            System.err.println(id);
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmPara = getParameterObj();
            if (hmPara == null || hmPara.isEmpty()) {
                addActionError("Không thể lấy ra được tham số ");
                return ERROR;
            }
            if (!id.toUpperCase().equals("NEW")) {
                objExcelTemplate = xmlConfig.readXmlFile(getCheckXml((String) hmPara.get("fileTemplate")));

                List<imsQueryConfig> nodeQuery = objExcelTemplate.getLstQuery();
                for (imsQueryConfig Para : nodeQuery) {
                    if (Para.getId().equals(id)) {
                        //set cac gia tri cho khi edit truy van
                        setId(id);
                        setKey_hidden(id);
                        setMapdata(Para.getMapData());
                        setDescription(Para.getDesCription());
                        setQuery(Para.getQueryString());
                        //load cac cot du lieu can group
                        setLstObjField(DaoExcelTemplate.newInstance().getAllFieldQuery(query, UserName, objExcelTemplate.getParameter()));
                        setDefaultKeyGroup(Para.getKey_group());
                        setDefaultValueGroup(Para.getValue_group());
                    }
                }
            } else {
                setId("1");
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " configEditQuery -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " configEditQuery -> " + e.getMessage());
            addActionError("Lỗi khi load truy vấn " + e.getMessage());
            setMessage("Lỗi khi load truy vấn " + e.getMessage());
//            return ERROR;
        }
        return SUCCESS;
    }

    public String saveQuery() {
        try {
            System.err.println("id=" + id);
            if (id == null || id.isEmpty()) {
                addActionError("Bạn phải điền số thứ tự của truy vấn ");
                return ERROR;
            }

            if (fileTemplate == null || fileTemplate.isEmpty()) {
                addActionError("Không thể lấy ra được file mẫu excel ");
                return ERROR;
            }
            if (mapdata == null || mapdata.isEmpty()) {
                addActionError("Bạn phải điền tham số mapdata cho truy vấn ");
                return ERROR;
            }
            if (query == null || query.isEmpty()) {
                addActionError("Bạn phải điền truy vấn trước khi lưu ");
                return ERROR;
            }
            if (description == null || description.isEmpty()) {
                addActionError("Bạn phải điền mô tả cho truy vấn");
                return ERROR;
            }
            String xmlName = getCheckXml(fileTemplate);
            Document doc = xmlConfig.getDocument(xmlName);

            //trường hợp là thêm mới truy vấn 
            if (key_hidden == null || key_hidden.trim().isEmpty() || key_hidden.equals("")) {
                if (xmlConfig.findAttribute(doc, xmlTemplateConfig.queryString, xmlTemplateConfig.id, id)) {
                    addActionError("Số thứ tự truy vấn " + id + " này đã có bạn phải chọn số thứ tự khác ! ");
                    return ERROR;
                }
                if (xmlConfig.findAttribute(doc, xmlTemplateConfig.queryString, xmlTemplateConfig.maplist, mapdata)) {
                    addActionError("Biến để lưu dữ liệu " + mapdata + " đã có rồi bạn phải chọn biến khác ");
                    return ERROR;
                }
            } else { //nếu là trường hợp sửa tham số thì xóa tham số cũ đi
                //xoa giá trị của tham số cũ
                if (!xmlConfig.deleteAttribute(doc, xmlTemplateConfig.queryString, xmlTemplateConfig.id, key_hidden)) {
                    addActionError("Không thể cập nhật được tham số này ");
                    return ERROR;
                }
                //tim xem có còn giá trị nào như trương hợp thêm mới vào không
                if (xmlConfig.findAttribute(doc, xmlTemplateConfig.queryString, xmlTemplateConfig.id, id)) {
                    addActionError("Số thứ tự truy vấn " + id + " này đã có bạn phải chọn số thứ tự khác ! ");
                    return ERROR;
                }
                if (xmlConfig.findAttribute(doc, xmlTemplateConfig.queryString, xmlTemplateConfig.maplist, mapdata)) {
                    addActionError("Biến để lưu dữ liệu " + mapdata + " đã có rồi bạn phải chọn biến khác ");
                    return ERROR;
                }
            }

            imsQueryConfig querycfg = imsQueryConfig.newInstance(id, mapdata, description, query);
            //neu khong group du lieu (chi can kiem tra group key la du) se de gia tri null
            if (key_group.equals("1")) {
                key_group = null;
                value_group = null;
            }
            querycfg.setKey_group(key_group);
            querycfg.setValue_group(value_group);
            if (!xmlConfig.addFileConfig(doc, querycfg)) {
                addActionError("Không thể lưu được cấu hình cho truy vấn " + id);
                return ERROR;
            }

            //lưu lại file cấu hình xml
            if (!xmlConfig.saveFileXml(xmlName, doc)) {
                addActionError("Lỗi bạn chưa lưu được truy vấn với stt= " + id + " vào file xml");
                return ERROR;
            }
            addActionMessage("Bạn đã lưu truy vấn thành công");
            setMessage("SAVE_QUERY");
            querycfg = null;
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " saveQuery -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveQuery -> " + e.getMessage());
            addActionError("Lưu truy vấn số bị lỗi " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String deleteQuery() {
        try {
            System.err.println("id=" + id);

            if (fileTemplate == null || fileTemplate.isEmpty()) {
                addActionError("Không thể lấy ra được file mẫu excel ");
                return ERROR;
            }
            if (id == null || id.isEmpty()) {
                addActionError("Không thể lấy ra được số thứ tự và id của truy vấn ");
                return ERROR;
            }

            String xmlName = getCheckXml(fileTemplate);

            if (!xmlConfig.deleteAttribute(xmlName, id)) {
                addActionError("Không thể xóa được truy vấn này ");
                return ERROR;
            }

            addActionMessage("Bạn đã xóa truy vấn " + id + " thành công");
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " deleteQuery -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " deleteQuery -> " + e.getMessage());
            addActionError("Lỗi bạn chưa xóa được truy vấn xin liểm tra lại " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cho phần thêm, sửa, xóa và xuất file excel mẫu">
    /**
     * Hàm này load tất cả các báo cáo đã cấu hình theo nhóm để export
     *
     * @return
     */
    public String loadAllRptExp() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = null;
            conn = new DaoConnect().getConnect();
            DaoExcelTemplate daoQuery = new DaoExcelTemplate();
            lstObjGroup = daoQuery.getGroupQuery(conn, UserName);
            lstObjRpt = daoQuery.getLoadAllQuery(conn, UserName, group_id, Grade);
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadAllRptExp -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String deleteExcelTemplate() {
        try {
            System.err.println(fileTemplate);
            if (!getParaSession()) {
                return ERROR;
            }
            if (fileTemplate == null || fileTemplate.isEmpty()) {
                addActionError("Không thể lấy ra được id của mẫu xin kiểm tra lại");
                return ERROR;
            }
            String xmlFile = getCheckXml(fileTemplate);
            if (xmlFile == null || xmlFile.isEmpty()) {
                addActionError("Không tìm thấy file cấu hình xml");
                return ERROR;
            }
            objExcelTemplate = xmlConfig.readXmlFile(xmlFile);
            String userDelete = objExcelTemplate.getUserName() == null ? "" : objExcelTemplate.getUserName();
//            neu la user ADMIN thi cho xoa khong thi kiem tra xem co dung la user tao ko
            if (!UserName.toUpperCase().contains("ADMIN")) {
                if (!userDelete.toLowerCase().equals(UserName.toLowerCase())) {
                    addActionError("Bạn chưa xóa được báo cáo, Do bạn không phải là người tạo báo cáo này! Người tạo báo cáo là: " + userDelete);
                    return ERROR;
                }
            }
//            String filePath = ServletActionContext.getServletContext().getRealPath("/");
//            filePath = DefineFun.backlashReplace(filePath);
//            if (!filePath.endsWith("/") || !filePath.endsWith("\\")) {
//                filePath += "/";
//            }
//            filePath += Define.M_EXCEL_CONFIG;
            //Phai xoa file xml, excel đi nua nhung hien tai van chua xoa
            DaoExcelTemplate dao = new DaoExcelTemplate();
            if (!dao.deleteExcelTemplate(fileTemplate)) {
                addActionError("Lỗi bạn chưa xóa được mẫu này");
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " deleteExcelTemplate -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " deleteExcelTemplate -> " + e.getMessage());
            addActionError("Có lỗi sảy ra chi tiết lỗi -> " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String editTemplateHeader() {
        try {
            System.err.println("fileTemplate=" + fileTemplate + " module=" + module);
            if (!getParaSession()) {
                return ERROR;
            }
            if (fileTemplate == null || fileTemplate.isEmpty()) {
                addActionError("Không thể lấy ra được id của mẫu xin kiểm tra lại");
                return ERROR;
            }
            String xmlFile = getCheckXml(fileTemplate);
            if (xmlFile == null || xmlFile.isEmpty()) {
                addActionError("Không tìm thấy file cấu hình xml");
                return ERROR;
            }
            objExcelTemplate = xmlConfig.readXmlFile(xmlFile);
            String userDelete = objExcelTemplate.getUserName() == null ? "" : objExcelTemplate.getUserName();
//            neu la user ADMIN thi cho xoa khong thi kiem tra xem co dung la user tao ko
            if (!UserName.toUpperCase().contains("ADMIN")) {
                if (!userDelete.toLowerCase().equals(UserName.toLowerCase())) {
                    addActionError("Bạn không được phép sửa báo cáo này, Do bạn không phải là người tạo báo cáo này! Người tạo báo cáo là: " + userDelete);
                    return ERROR;
                }
            }

            HashMap<String, String> hmEdit = new DaoExcelTemplate().getEditTemplate(fileTemplate);

            setFileOutFormat(objExcelTemplate.getFileOutFormat());
            setFileUploadFileName(objExcelTemplate.getFileName());
            setDescription(objExcelTemplate.getDesCription());
            ArrayList<String> lsttmp = DefineFun.SplitStringToArrayList(hmEdit.get("GRADE_REPORT"), "#");
            defaultGrade = new String[lsttmp.size()];
            for (int i = 0; i < lsttmp.size(); i++) {
                defaultGrade[i] = lsttmp.get(i);
            }
            setDefaultGrade(defaultGrade);
            setGroup_id(hmEdit.get("GROUP_ID"));

            lstGrade.add(new ListValue("1", "Ngân hàng"));
            lstGrade.add(new ListValue("2", "Chi nhánh"));
            lstGrade.add(new ListValue("3", "Toàn quốc"));

            Connection conn = null;
            conn = new DaoConnect().getConnect();
            DaoExcelTemplate daoQuery = new DaoExcelTemplate();
            lstObjGroup = daoQuery.getGroupQuery(conn, null);

            if (module != null) {
                if (module.equals("SBV")) {
                    setLstObjRpt(daoQuery.getbcSbv(conn));
                    setId(hmEdit.get("ID_BC"));
                }
            } else {
                setId(hmEdit.get("ID_BC") == "" || hmEdit.get("ID_BC") == null ? "N" : hmEdit.get("ID_BC"));
            }

            if (conn != null) {
                conn.close();
            }

            String filePath = ServletActionContext.getServletContext().getRealPath("/");
            filePath = DefineFun.backlashReplace(filePath);
            if (!filePath.endsWith("/") || !filePath.endsWith("\\")) {
                filePath += "/";
            }
            filePath += Define.M_EXCEL_CONFIG;

            setFileUpload(new File(filePath + fileTemplate));
            setKey_hidden(fileTemplate);

        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            addActionError("Có lỗi sảy ra chi tiết lỗi -> " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String execute() {
        try {
            System.err.println("abc");
        } catch (Exception e) {
            System.err.println(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            addActionError("Có lỗi sảy ra chi tiết lỗi -> " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Cho phan load tham so, export">
    public String loadParameter() {
        try {
            if (fileTemplate == null) {
                addActionError("Lỗi bạn không thể lấy ra được file mẫu excel");
                return ERROR;
            }
            if (!getParaSession()) {
                return ERROR;
            }
            List<String> lstPara = new ArrayList<String>();

            String xmlFile = getCheckXml(fileTemplate);
            if (xmlFile == null || xmlFile.isEmpty()) {
                addActionError("Không tìm thấy file cấu hình xml");
                return ERROR;
            }
            objExcelTemplate = xmlConfig.readXmlFile(xmlFile);
            List<Node> lstNodePara = objExcelTemplate.getParameter();
            for (Node node : lstNodePara) {
                lstPara.add(node.getKey());
            }
            setLstParameterExp(DaoExcelTemplate.newInstance().getReportParmams(lstPara, UserName));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadParameter -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    private String getPathRoot() throws Exception {
        String path = ServletActionContext.getServletContext().getRealPath("/");
        path = DefineFun.backlashReplace(path);
        if (!path.endsWith("/") || !path.endsWith("\\")) {
            path += "/";
        }
        return path;
    }

    private boolean isCheckfile(String filename) throws Exception {
        //Lay ra duong dan root cua thu muc web
        String filePath = getPathRoot();

        filePath = filePath + Define.M_EXCEL_CONFIG + filename;
        File file = new File(filePath);
        if (file.exists()) {
            imsTemplateConfig objExcel = imsTemplateConfig.newInstance();
            xmlTemplateConfig xmlCfg = imsXmlTemplateConfig.newInstance();

            objExcel = xmlCfg.readXmlFile(filePath);
            if(objExcel.getFileName()==null||objExcel.getDesCription()==null)
                 return true;
            else
                return false;
        } else {
            return false;
        }
    }

    public String ExportTemplate() {
        Connection connection = null;
        String MsgError="";
        try {
            if (fileTemplate == null) {
                addActionError("Lỗi bạn không thể lấy ra được file mẫu excel");
                return ERROR;
            }
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmPara = getParameterObj();
            String xmlFile = getCheckXml(fileTemplate);
            if (xmlFile == null || xmlFile.isEmpty()) {
                addActionError("Không tìm thấy file cấu hình xml");
                return ERROR;
            }
            //get cac tham so cho mau
            HashMap<String, String> hmEdit = new DaoExcelTemplate().getEditTemplate(fileTemplate);
            //doc file xml va lay ra cac doi tuong
            objExcelTemplate = xmlConfig.readXmlFile(xmlFile);
            //lay ra cac bien config cho mau
            List<Node> lstNodePara = objExcelTemplate.getParameter();
//            ImsFillParaMeter imsParameter = new ImsFillParaMeter(Grade, Grade);
            Map parameters = new HashMap();
            String fileOut = objExcelTemplate.getFileOutFormat();

            for (Object key : hmPara.keySet()) {
                if (!key.toString().toUpperCase().contains("NGAYBC")) {
                    fileOut = fileOut.replaceAll(key.toString(), hmPara.get(key).toString());
                } else {
                    String ddmmyyyy;
                    try {
                        ddmmyyyy = new SimpleDateFormat("ddMMyyyy").format(new Date(hmPara.get(key).toString()));
                    } catch (Exception e) {
                        ddmmyyyy = new SimpleDateFormat("ddMMyyyy").format(new Date());
                    }
                    fileOut = fileOut.replaceAll(key.toString(), ddmmyyyy);
                }

            }

            String ddmmyyyy = new SimpleDateFormat("ddMMyyyy").format(new Date());
            fileOut = fileOut.toUpperCase().replaceAll("DDMMYYYY", ddmmyyyy)
                    + fileTemplate.substring(fileTemplate.toLowerCase().lastIndexOf(".xls"), fileTemplate.length());
            filereport = fileOut;
            String filePath = getPathRoot();

            String fullPathFileIn = filePath + Define.M_EXCEL_CONFIG + fileTemplate;
            String fullPathFileOut = filePath + Define.M_REPORT_XLS + fileOut;
            fileNamelocal = fullPathFileOut;

            for (Node node : lstNodePara) {
                //khoi tao lop cho tham so
                ImsFillParaMeter clspara = ImsFillParaMeter.newInstance(node.getValue(), hmPara.get(node.getKey()));
                parameters.put(node.getKey(), clspara);
            }

            //kiem tra xem co file cau hinh xml khong
            boolean bconfig = false;
            String fileConfig = null;
            //Lay ra danh sach cac sheet cua file excel mau
            List<String> lstSheetName = getSheetName(fullPathFileIn);
            //Kiem tra cac file cau hinh de fill du lieu
            for (int i = 0; i < lstSheetName.size(); i++) {
                String sheetName = lstSheetName.get(i);
                //Phai viet 2 truong hop vi tren linux co phan biet hoa thuong
                if (isCheckfile(sheetName + ".xml")) {
                    fileConfig = filePath + Define.M_EXCEL_CONFIG + sheetName + ".xml";
                    bconfig = true;
                    break;
                } else if (isCheckfile(sheetName + ".XML")) {
                    fileConfig = filePath + Define.M_EXCEL_CONFIG + sheetName + ".XML";
                    bconfig = true;
                    break;
                } else {
                    bconfig = false;
                }
            }

            File file = new File(fullPathFileIn);

            URI uri = file.toURI();
            file = new File(uri.toURL().getFile());
            //cho phan co tinh toan cong thuc excel hay de nguyen cong thuc
            boolean isFormula = false;
            if (hmEdit.get("ID_BC") == null || hmEdit.get("ID_BC") == "" || hmEdit.get("ID_BC").equals("N")) {
                isFormula = false; //de nguyen cong thuc
            } else {
                isFormula = true; //chuong trinh se tu tinh toan
            }
            List<imsQueryConfig> lstQuery = objExcelTemplate.getLstQuery();
            //Xu ly lay du lieu cho tung truy van 
            try (InputStream is = new FileInputStream(file)) {
                try (OutputStream os = new FileOutputStream(fullPathFileOut)) {
                    Context context = new Context();
                    context.getConfig().setIsFormulaProcessingRequired(isFormula);//thiet lap de tinh toan cong thuc
                    connection = new DaoConnect().getConnect();
                    for (int i = 0; i < lstQuery.size(); i++) {
                        List<String> qryparameters = lstQuery.get(i).splitParaProcedure();
                        String qryPara = lstQuery.get(i).removeParaProcedure();
                        MsgError=qryPara;
                        PlSqlQueryExecuterIms execute = new PlSqlQueryExecuterIms(connection, qryparameters, parameters, qryPara);
                        int index =1;
                        for(String para:qryparameters)
                        {
                            ImsFillParaMeter clsPara = (ImsFillParaMeter)parameters.get(para);
                            if(!para.equals("ORACLE_REF_CURSOR"))
                                MsgError+=" "+para+"="+(clsPara.getValue()==null?"":clsPara.getValue().toString());
                            index++;
                        }
                        ResultSet reset = execute.createDatasource();
                        //lay ra cot group 
                        String group_key = lstQuery.get(i).getKey_group();
                        String group_value = lstQuery.get(i).getValue_group();

                        if (group_key != null && !group_key.equals("EMPTY")) {
                            List<GroupDataClass> lstGroupData = getDataGroupCollect(reset, group_key, group_value);
                            context.putVar(lstQuery.get(i).getMapData(), lstGroupData);
                        } else {
                            List<ColumnReportTemplate> lstdata = getDataCollect(reset);
                            context.putVar(lstQuery.get(i).getMapData(), lstdata);
                        }
                        if (reset != null) {
                            reset.close();
                        }
                    }

                    if (bconfig) {
                        MsgError="";
                        Transformer transformer = TransformerFactory.createTransformer(is, os);
                        //Chu y doan nay la file xml de fill du lieu dat bang voi ten sheet
                        for (int i = 0; i < lstSheetName.size(); i++) {
                            String sheetName = lstSheetName.get(i);
                            //Phai viet 2 truong hop vi tren linux co phan biet hoa thuong
                            if (isCheckfile(sheetName + ".xml")) {
                                fileConfig = filePath + Define.M_EXCEL_CONFIG + sheetName + ".xml";
                                putDataExcel(transformer, context, sheetName, fileConfig);
                            } else if (isCheckfile(sheetName + ".XML")) {
                                fileConfig = filePath + Define.M_EXCEL_CONFIG + sheetName + ".XML";
                                putDataExcel(transformer, context, sheetName, fileConfig);
                            }
                        }
                        transformer.write();

                    } else {
                        MsgError="";
                        JxlsHelper.getInstance().processTemplate(is, os, context);
                    }

                    //JxlsHelper.getInstance().processTemplate(is, os, context);
                    if (connection != null) {
                        connection.close();
                    }
                }
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " ExportTemplate -> " + e.getMessage()+" "+MsgError);
            addActionError("Lỗi bạn không thể tạo được báo cáo " + e.getMessage()+" "+MsgError);
            return ERROR;
        }

        return SUCCESS;
    }

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

    private void putDataExcel(Transformer transformer, Context context, String sheetName, String fileConfig) throws FileNotFoundException, IOException {
        try (InputStream configInputStream = new FileInputStream(fileConfig)) {
            AreaBuilder areaBuilder = new XmlAreaBuilder(configInputStream, transformer);
            List<Area> xlsAreaList = areaBuilder.build();
            Area xlsArea = xlsAreaList.get(0);
            xlsArea.applyAt(new CellRef(sheetName + "!A1"), context);
        }
    }

    private List<ColumnReportTemplate> getDataCollect(ResultSet reset) throws Exception {
        List<ColumnReportTemplate> data = new ArrayList<>();
        try {
            ResultSetMetaData resetMetadata = reset.getMetaData();
            int nCol = resetMetadata.getColumnCount();
            while (reset.next()) {
                MappingClassValue mappingvalue = new MappingClassValue("vbsp.ims.core.ColumnReportTemplate");
                for (int i = 1; i <= nCol; i++) {
                    String columnname = resetMetadata.getColumnName(i);
                    Object objData = reset.getObject(i);
                    if (objData == null && isCheckColumnName(columnname, "STR")) {
                        objData = "";
                    } else if (objData == null && isCheckColumnName(columnname, "D")) {
                        objData = 0;
                    }

                    mappingvalue.setValueField(columnname, objData);
                }
                data.add((ColumnReportTemplate) mappingvalue.getObjClass());
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCollect -> cot du lieu khong dung ten quy dinh " + e.getMessage());
            addActionError("Lỗi khi lấy dữ liệu, Cột dữ liệu " + e.getMessage() + " Không đúng tên quy định là STRn hoặc Dn");
        }
        return data;
    }

    private List<GroupDataClass> getDataGroupCollect(ResultSet reset, String column_key, String column_value) throws Exception {
        List<GroupDataClass> groupdata = new ArrayList<>();
        try {
            //khai bao 1 lop group data
            GroupDataClass objGroupData = null;
            ArrayList lstRowData = null;
            String keyGroup = "";
            int stt = 1;
            while (reset.next()) {
                String keyGroup_new = reset.getString(column_key);
                //dau tien khai gao keygroup="" sau do lay group so sanh
                //neu chuyen sang group tiep theo cung tuong tu
                if (!keyGroup.equals(keyGroup_new)) {
                    if (objGroupData != null) {
                        objGroupData.setGroup(lstRowData);
                        groupdata.add(objGroupData);
                    }
                    objGroupData = new GroupDataClass();
                    lstRowData = new ArrayList<>();

                    objGroupData.setKey(reset.getString(column_key));
                    objGroupData.setValue(reset.getString(column_value));
                    objGroupData.setStt(stt);
                    lstRowData.add(getdataRow(reset));
                    stt++;

                } else { //add rowdata
                    lstRowData.add(getdataRow(reset));
                }
                keyGroup = keyGroup_new;

            }
            if (objGroupData != null) {
                if (lstRowData != null) {
                    objGroupData.setGroup(lstRowData);
                    groupdata.add(objGroupData);
                }
            }

            for (GroupDataClass lay : groupdata) {
                System.err.println("Stt=" + lay.getStt() + " key=" + lay.getKey() + " value=" + lay.getValue());
                for (int i = 0; i < lay.getGroup().size(); i++) {
                    ColumnReportTemplate innao = (ColumnReportTemplate) lay.getGroup().get(i);
                    System.err.println("STR1=" + innao.getSTR1() + " STR2=" + innao.getSTR2());
                }
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getDataCollect -> cot du lieu khong dung ten quy dinh " + e.getMessage());
            addActionError("Lỗi khi lấy dữ liệu, Cột dữ liệu " + e.getMessage() + " Không đúng tên quy định là STRn hoặc Dn");
        }
        return groupdata;
    }

    private boolean isCheckColumnName(String columnName, String start) {
        boolean found = false;
        try {
            String end = columnName.substring(start.length(), columnName.length());
            int stt = Integer.valueOf(end);
            if (stt > 0) {
                found = true;
            }

        } catch (Exception e) {
//            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " isCheckColumnName -> " + e.getMessage());
//            addActionError("Lỗi khi lấy dữ liệu, Cột dữ liệu " + e.getMessage() + " Không đúng tên quy định là STRn hoặc Dn");
            return false;
        }
        return found;
    }

    private ColumnReportTemplate getdataRow(ResultSet rs) throws SQLException, Exception {

        ResultSetMetaData rsmd = rs.getMetaData();
        int nCol = rsmd.getColumnCount();

        MappingClassValue mappingvalue = new MappingClassValue("vbsp.ims.core.ColumnReportTemplate");
        for (int i = 1; i <= nCol; i++) {

            String columnname = rsmd.getColumnName(i);
            Object objData = rs.getObject(i);
            if (objData == null && isCheckColumnName(columnname, "STR")) {
                objData = "";
            } else if (objData == null && isCheckColumnName(columnname, "D")) {
                objData = 0;
            }

            mappingvalue.setValueField(columnname, objData);
        }
        return (ColumnReportTemplate) mappingvalue.getObjClass();
    }
    //</editor-fold>

    public static void main(String[] args) {
        String ddmmyyyy;
        try {
            String filePath="I:\\PROJECT\\IMS_REPORTS\\IMS_REPORTS\\web\\EXCEL_TEMPLATE\\TEMPLATE_CONFIG\\dienbao.xml";
            imsTemplateConfig objExcel = imsTemplateConfig.newInstance();
            xmlTemplateConfig xmlCfg = imsXmlTemplateConfig.newInstance();

            //H00031.xml  dienbao.XML
            objExcel = xmlCfg.readXmlFile(filePath);
            
            if(objExcel.getFileName()==null||objExcel.getDesCription()==null)
                System.err.println("Khong phai file cau hinh");
            else
                 System.err.println("file cau hinh");
            ddmmyyyy = new SimpleDateFormat("ddMMyyyy").format(new Date("000314"));
        } catch (Exception e) {
            ddmmyyyy = new SimpleDateFormat("ddMMyyyy").format(new Date());
        }

        String PARA_NGAYBC = "PARA_MAPGD";
        if (PARA_NGAYBC.contains("NGAYBC")) {
            System.err.println(ddmmyyyy);
        }
    }
}
