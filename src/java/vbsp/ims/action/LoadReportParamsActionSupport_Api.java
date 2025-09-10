package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.jasper.ExportJasperReport_Api;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.LoadReportParams;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class LoadReportParamsActionSupport_Api
        extends ActionSupport implements ServletRequestAware {

    private String reportId; //CuongBM: Ma bao cao
    private List<Combo> reportList;  //CuongBM: Danh sach ma bao cao
    private List<ReportParam> reportParamsList; //CuongBM: Danh sach tham so cua bao cao

    private LoadReportParams objLRP = new LoadReportParams();
    private HttpServletRequest request = null;
    ExportJasperReport_Api exportReport = new ExportJasperReport_Api();
    DaoConnect daoconnect = new DaoConnect();
    String fileNamelocal;
    String exportType;
    String message;
    String filereport;
    Date defaultRptdate;

    public String execute() throws Exception {
        //CuongBM: tam thoi hard code group
        System.err.println("Tham so execute " + exportType);
        //reportList = objLRP.getReportList("NHOMBC0002"); //CuongBM: Lay danh sach ma bao cao
        return "success";
    }

    //CuongBM: Lay danh sach tham so khi da biet ma bao cao
    public String getReportParams_Api() {
        //Lay username
        HttpSession session = request.getSession();
        String strUserName = session.getAttribute("username").toString();
        reportParamsList = objLRP.getReportPramsList(reportId, strUserName);
        Date today = new Date();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(today);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date lastDayOfMonth = calendar.getTime();
        defaultRptdate = lastDayOfMonth;
        String desiredValue = null;
        for (ReportParam param : reportParamsList) {
            if (param.getFieldName().equals("PARA_API")) {
                desiredValue = "1";
            } else {
                desiredValue = "2";
            }
        }
        if (desiredValue == "1") {
            return "success";
        } else {
            return "success_1";
        }
    }

    public String genViewReport_Api() //throws Exception
    {
        String sMessagepdf = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
        sMessagepdf += Define.M_REPORT;
        try {
            //        System.err.println("Da vao ham tao genViewReport");
            HashMap<String, Object> paramHashMap = new HashMap<>();
            Map<String, String[]> prameters = request.getParameterMap();
            String strMa_pgd = "000000";
            String strTonghop = "";
            String strNgaybc = "";
            String ipAddress = request.getHeader("X-FORWARDED-FOR");
            if (ipAddress == null) {
                ipAddress = request.getRemoteAddr();
            }
            System.err.println(" Dia chi IP Client tao bao cao -->> " + ipAddress);
            CoreLogger.error(" Dia chi IP Client tao bao cao -->> " + ipAddress);

            //CuongBM: xy lay lay cac tham so cho vao hashmap
            for (String parameter : prameters.keySet()) {
                String[] values = prameters.get(parameter);
                //CuongBM: 18-Apr-14
                //Do neu parameter kieu date thi he thong se sinh them control dojo.date
                //   nen minh can phai loai bo tham so nay di
                if (parameter.indexOf("TEXT") > 0
                        || parameter.indexOf("DATE") > 0
                        || parameter.indexOf("LIST") > 0) {
                    if (parameter.indexOf("DATE") > 0) {
                        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5),
                                new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                        strNgaybc = new SimpleDateFormat("ddMMyyyy").format(sdf);

                    } else {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);

                    }

                    //Lay ra ma PGD de gan vao ten file
                    if (parameter.toUpperCase().indexOf("MAPGD") > 0) {
                        strMa_pgd = values[0].trim().substring(2, values[0].length());
                    }

                    if (parameter.toUpperCase().indexOf("TONGHOP") > 0 && values[0].trim().equals("Y")) {
                        strTonghop = "_TONGHOP";
                    }
                    //System.err.println("Key la :"+parameter+" Tham so la :"+values[0]);
                }
            }

            //CuongBM: 01-Jul-14
            //Desc: hardcode truong hop neu o PGD thi khong hien thi cobobox "Tong hop"
            //      gan mac dinh truong nay la No
            if (strTonghop.equalsIgnoreCase("")) {
                paramHashMap.put("PARA_TONGHOP", "N");
            }

            if (reportId == null || reportId.length() < 1) {
                //Cho nay can xua lai de bat loi
                setMessage("Lỗi không thể lấy ra được ID báo cáo");
                return ERROR;
            }
            //xu ly cho export file ra PDF hoac la Excel
            String strCurrDate = strNgaybc.isEmpty() ? new SimpleDateFormat("ddMMyyyy").format(new Date()) : strNgaybc;
            //duong dan chua file tren o dia + Define.M_REPORT_XLS
            String strPathSave = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");

            //Ham nay lay ra ten file bao cao can tao, ten file jasper report
            HashMap<String, String> hmNameReport_Jasper = objLRP.getNameReport_Jasper(reportId);
            if (hmNameReport_Jasper == null) {
                //Cho nay can xua lai de bat loi
                setMessage("Lỗi không thể lấy ra được tham số của báo cáo");
                return ERROR;
            }

            String strNameReport = hmNameReport_Jasper.get("NAME_FILE");

            //Duong dan day du cua file jasper tren o dia
//            String strSourceJasper = strPathSave + Define.M_REPORT + hmNameReport_Jasper.get("NAME_JASPER") + ".jrxml";
//
//            //kiem tra file xem da co chua neu chua co thi return
//            File checkFile = new File(strSourceJasper);
//            if (!checkFile.exists()) {
//                //Cho nay can xua lai de bat loi
//                setMessage("Lỗi file mẫu báo cáo jasper không có");
//                return ERROR;
//            }
            //Ten file tao ra se luu lai de nguoi su dung download (chi ten file chua co duong dan)
            String strTimeFile = Long.toString(System.currentTimeMillis());
            String strFileSave = strMa_pgd + "_"
                    + strNameReport + strTonghop + "_" + strCurrDate
                    + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());
            Connection connect = null;
            connect = daoconnect.getConnect();
            if (connect == null) {
                //Cho nay can xua lai de bat loi
                fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
                filereport = "ERROR_JASPER_REPORT.PDF";
                setMessage("Lỗi không thể kết nối được với cơ sở dữ liệu");
                return SUCCESS;
            }
            boolean swaggerOk = callSwaggerConfig();
            if (!swaggerOk) {
                setMessage("Không thể kết nối Swagger API, liên hệ TTCNT!");
                return ERROR;
            }
            if (exportType.equals("PDF")) {
                strPathSave += Define.M_REPORT_PDF;
                strFileSave += ".PDF";
                filereport = strFileSave;
                File Checkpath = new File(strPathSave);
                if (!Checkpath.exists()) {
                    System.out.println("Da tao thu muc: " + strPathSave);
                    Checkpath.mkdirs();
                }
                exportReport.ExportJasperPdf(paramHashMap, connect, strPathSave + strFileSave, reportId);

            } else {
                strPathSave += Define.M_REPORT_XLS;
                strFileSave += ".XLSX";
                filereport = strFileSave;
                File Checkpath = new File(strPathSave);
                if (!Checkpath.exists()) {
                    System.out.println("Da tao thu muc: " + strPathSave);
                    Checkpath.mkdirs();
                }
                exportReport.ExportJasperExcel(paramHashMap, connect, strPathSave + strFileSave, reportId);
            }
            //Kiem tra xem file da tao thanh cong chua        
            File filerpt = new File(strPathSave + strFileSave);
            if (!filerpt.exists()) {
                fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
                filereport = "ERROR_JASPER_REPORT.PDF";
            } else {
                fileNamelocal = strPathSave + strFileSave;
            }
            System.gc();
        } catch (Exception e) {
            fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
            filereport = "ERROR_JASPER_REPORT.PDF";
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " Loi khi view bao cao genViewReport " + e.getMessage());
        }

//        System.err.println(fileNamelocal);
        return "success";
    }

    public String genReport_Api() throws Exception {
        System.err.println("Vao tao bao cao nhanh " + reportId);
        String strNgaybc = "";
        HashMap<String, Object> paramHashMap = new HashMap<>();
        Map<String, String[]> prameters = request.getParameterMap();
        String strMa_pgd = "000000";
        String strTonghop = "";
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }
        System.err.println(" Dia chi IP Client tao bao cao -->> " + ipAddress);
        CoreLogger.error(" Dia chi IP Client tao bao cao -->> " + ipAddress);
        //CuongBM: xy lay lay cac tham so cho vao hashmap
        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);

            //CuongBM: 18-Apr-14
            //Do neu parameter kieu date thi he thong se sinh them control dojo.date
            //   nen minh can phai loai bo tham so nay di
            if (parameter.indexOf("TEXT") > 0
                    || parameter.indexOf("DATE") > 0
                    || parameter.indexOf("LIST") > 0) {
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5),
                            new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                    strNgaybc = new SimpleDateFormat("ddMMyyyy").format(sdf);
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                }
                //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+values[0]);
                //Lay ra ma PGD de gan vao ten file
                if (parameter.toUpperCase().indexOf("MAPGD") > 0) {
                    strMa_pgd = values[0].trim().substring(2, values[0].length());
                }

                if (parameter.toUpperCase().indexOf("TONGHOP") > 0 && values[0].trim().equals("Y")) {
                    strTonghop = "_TONGHOP";
                }
                //System.err.println("Key la :"+parameter+" Tham so la :"+values[0]);
            }
        }

        //CuongBM: 01-Jul-14
        //Desc: hardcode truong hop neu o PGD thi khong hien thi cobobox "Tong hop"
        //      gan mac dinh truong nay la No
        if (strTonghop.equalsIgnoreCase("")) {
            paramHashMap.put("PARA_TONGHOP", "N");
        }

        if (reportId == null || reportId.length() < 1) {
            //Cho nay can xua lai de bat loi
            setMessage("Lỗi không thể lấy ra được ID báo cáo");
            return ERROR;
        }

        //xu ly cho export file ra PDF hoac la Excel
        String strCurrDate = strNgaybc.isEmpty() ? new SimpleDateFormat("ddMMyyyy").format(new Date()) : strNgaybc;
        //duong dan chua file tren o dia + Define.M_REPORT_XLS
        String strPathSave = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
        //Ham nay lay ra ten file bao cao can tao, ten file jasper report
        HashMap<String, String> hmNameReport_Jasper = objLRP.getNameReport_Jasper(reportId);
        if (hmNameReport_Jasper == null) {
            //Cho nay can xua lai de bat loi
            setMessage("Lỗi không thể lấy ra được tham số của báo cáo");
            return ERROR;
        }

        String strNameReport = hmNameReport_Jasper.get("NAME_FILE");

        //Duong dan day du cua file jasper tren o dia
        //CuongBM: Comment
        String strSourceJasper = strPathSave + Define.M_REPORT
                + hmNameReport_Jasper.get("NAME_JASPER") + ".jrxml";

        //kiem tra file xem da co chua neu chua co thi return
        File checkFile = new File(strSourceJasper);
        if (!checkFile.exists()) {
            //Cho nay can xua lai de bat loi
            setMessage("Lỗi file mẫu báo cáo jasper không có " + strNameReport + ".JRXML");
            return ERROR;
        }
        //Ten file tao ra se luu lai de nguoi su dung download (chi ten file chua co duong dan)
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String strFileSave = strMa_pgd + "_"
                + strNameReport + strTonghop + "_" + strCurrDate
                + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());
        Connection connect = null;
        connect = daoconnect.getConnect();
        if (connect == null) {
            //Cho nay can xua lai de bat loi
            setMessage("Lỗi không thể kết nối được với cơ sở dữ liệu");
            return ERROR;
        }
        if (exportType.equals("PDF")) {
            strPathSave += Define.M_REPORT_PDF;
            strFileSave += ".PDF";
            filereport = strFileSave;
            File Checkpath = new File(strPathSave);
            if (!Checkpath.exists()) {
                System.out.println("Da tao thu muc: " + strPathSave);
                Checkpath.mkdirs();
            }
            exportReport.ExportJasperPdf(paramHashMap, connect, strPathSave + strFileSave, reportId);
        }
        //Kiem tra xem file da tao thanh cong chua
        File filerpt = new File(strPathSave + strFileSave);
        if (!filerpt.exists()) {
            setMessage("Lỗi bạn chưa tạo được file báo cáo " + strFileSave);
            return ERROR;
        }

        fileNamelocal = strPathSave + strFileSave;
        System.gc();
        return "success";
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getExportType() {
        return exportType;
    }

    public void setExportType(String exportType) {
        this.exportType = exportType;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public List<Combo> getReportList() {
        return reportList;
    }

    public void setReportList(List<Combo> reportList) {
        this.reportList = reportList;
    }

    public List<ReportParam> getReportParamsList() {
        return reportParamsList;
    }

    public void setReportParamsList(List<ReportParam> reportParamsList) {
        this.reportParamsList = reportParamsList;
    }

    public LoadReportParamsActionSupport_Api() {
    }

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public Date getDefaultRptdate() {
        return defaultRptdate;
    }

    public void setDefaultRptdate(Date defaultRptdate) {
        this.defaultRptdate = defaultRptdate;
    }

    public boolean callSwaggerConfig() {
        String SWAGGER_URL = "http://10.63.16.52:8010/api-docs/swagger-config";
        HttpURLConnection conn = null;
        try {
            URL url = new URL(SWAGGER_URL);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(5000);

            int status = conn.getResponseCode();
            if (status == HttpURLConnection.HTTP_OK) {
                CoreLogger.info("Kết nối Swagger API thành công");
                return true;
            } else {
                CoreLogger.error("Swagger API trả về lỗi HTTP: " + status);
                return false;
            }
        } catch (Exception e) {
            CoreLogger.error("Không thể kết nối Swagger API: " + e.getMessage());
            return false;
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }

}
