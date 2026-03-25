package vbsp.ims.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
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
import vbsp.ims.jasper.ExportJasperReport;
import vbsp.ims.loadparams.Combo;
import vbsp.ims.loadparams.LoadReportParams;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class LoadReportParamsActionSupport
        extends ActionSupport implements ServletRequestAware {

    private String reportId; //CuongBM: Ma bao cao
    private List<Combo> reportList;  //CuongBM: Danh sach ma bao cao
    private List<ReportParam> reportParamsList; //CuongBM: Danh sach tham so cua bao cao

    private LoadReportParams objLRP = new LoadReportParams();
    private HttpServletRequest request = null;
    ExportJasperReport exportReport = new ExportJasperReport();
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
    public String getReportParams() {
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

    //CuongBM: Sinh bao cao (file pdf, exel, text...)
    public String genReport() throws Exception {
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
//            if (parameter.startsWith("dojo")) {
//                continue;
//            }

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
//                        System.err.println(parameter+" Ngay bao cao "+strNgaybc);
//                    if(parameter.indexOf("NGAYBC")>0)
//                    {
//                        strNgaybc=new SimpleDateFormat("ddMMyyyy").format(sdf);
//                        System.err.println(" Ngay bao cao "+strNgaybc);
//                    }
                    //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                    //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+values[0]);
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
            exportReport.ExportJasperPdf(strSourceJasper, paramHashMap, connect, strPathSave + strFileSave);
        } else {
            strPathSave += Define.M_REPORT_XLS;
            strFileSave += ".XLSX";
            filereport = strFileSave;
            File Checkpath = new File(strPathSave);
            if (!Checkpath.exists()) {
                System.out.println("Da tao thu muc: " + strPathSave);
                Checkpath.mkdirs();
            }
            exportReport.ExportJasperExcel(strSourceJasper, paramHashMap, connect, strPathSave + strFileSave);
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

    public String genViewReport() //throws Exception
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
            String strSourceJasper = strPathSave + Define.M_REPORT + hmNameReport_Jasper.get("NAME_JASPER") + ".jrxml";

            //kiem tra file xem da co chua neu chua co thi return
            File checkFile = new File(strSourceJasper);
            if (!checkFile.exists()) {
                //Cho nay can xua lai de bat loi
                setMessage("Lỗi file mẫu báo cáo jasper không có");
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
                fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
                filereport = "ERROR_JASPER_REPORT.PDF";
                setMessage("Lỗi không thể kết nối được với cơ sở dữ liệu");
                return SUCCESS;
            }

            strPathSave += Define.M_REPORT_PDF;
            strFileSave += ".PDF";
            filereport = strFileSave;
            File Checkpath = new File(strPathSave);
            if (!Checkpath.exists()) {
                System.out.println("Da tao thu muc: " + strPathSave);
                Checkpath.mkdirs();
            }
//            exportReport.ExportJasperPdf(strSourceJasper, paramHashMap, connect, strPathSave + strFileSave);
            final String fStrSourceJasper = strSourceJasper;
            final HashMap<String, Object> fParamHashMap = paramHashMap;
            final Connection fConnect = connect;
            final String fFullPath = strPathSave + strFileSave;

            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<?> future = executor.submit(() -> {
                exportReport.ExportJasperPdf(fStrSourceJasper, fParamHashMap, fConnect, fFullPath);
            });

            try {
                future.get(300, TimeUnit.SECONDS);
            } catch (TimeoutException e) {
                future.cancel(true);
                fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
                filereport = "ERROR_JASPER_REPORT.PDF";
                setMessage("Thời gian tạo báo cáo quá lâu, hệ thống đã dừng xử lý. Vui lòng liên hệ TTCNTT để kiểm tra và tối ưu báo cáo.");
                return ERROR;
            } finally {
                executor.shutdownNow();
            }
            //Kiem tra xem file da tao thanh cong chua        
            File filerpt = new File(strPathSave + strFileSave);
            if (!filerpt.exists()) {
                fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
                filereport = "ERROR_JASPER_REPORT.PDF";
//            setMessage("Lỗi bạn chưa tạo được file báo cáo "+strFileSave);
//            return ERROR;
            } else {
                fileNamelocal = strPathSave + strFileSave;
            }
//            System.err.println(fileNamelocal);
            System.gc();
        } catch (Exception e) {
            fileNamelocal = sMessagepdf + "ERROR_JASPER_REPORT.PDF";
//            System.err.println(fileNamelocal);
            filereport = "ERROR_JASPER_REPORT.PDF";
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getName() + " Loi khi view bao cao genViewReport " + e.getMessage());
        }

//        System.err.println(fileNamelocal);
        return "success";
    }

    public String genViewReport1() throws Exception {
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
        //CuongBM: Comment
        String strSourceJasper = strPathSave + Define.M_REPORT + hmNameReport_Jasper.get("NAME_JASPER") + ".jrxml";

        //kiem tra file xem da co chua neu chua co thi return
        File checkFile = new File(strSourceJasper);
        if (!checkFile.exists()) {
            //Cho nay can xua lai de bat loi
            setMessage("Lỗi file mẫu báo cáo jasper không có");
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

        strPathSave += Define.M_REPORT_PDF;
        strFileSave += ".PDF";
        filereport = strFileSave;
        File Checkpath = new File(strPathSave);
        if (!Checkpath.exists()) {
            System.out.println("Da tao thu muc: " + strPathSave);
            Checkpath.mkdirs();
        }
        exportReport.ExportJasperPdf(strSourceJasper, paramHashMap, connect, strPathSave + strFileSave);

        //Kiem tra xem file da tao thanh cong chua        
        File filerpt = new File(strPathSave + strFileSave);
        if (!filerpt.exists()) {
            setMessage("Lỗi bạn chưa tạo được file báo cáo " + strFileSave);
            return ERROR;
        }
        fileNamelocal = strPathSave + strFileSave;
        System.gc();
//        System.err.println(fileNamelocal);
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

    public LoadReportParamsActionSupport() {
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

}
