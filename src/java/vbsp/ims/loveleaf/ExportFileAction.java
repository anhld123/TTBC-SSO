/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.loveleaf;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;

/**
 *
 * @author Trung
 */
public class ExportFileAction
        extends ActionSupport {

    private InputStream fileInputStream;
     private String fileName;
    private long contentLength;
    Map parameters = null;

    private String filereport;
    private String fileNamelocal;
    private String query_dt;
    
    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    @Override
    public String execute() 
            throws Exception {        
        String tran_dt = getParameterValue("export_dt");
        String period = getParameterValue("period");
        String gendata_FLG = getParameterValue("gendata_FLG");
        ExcelFileWriter writer = new ExcelFileWriter();
        String file_path = Define.M_ROOT + Define.M_REPORT_XLS + "DANHSACHNHAHT_"
                + DefineFun.convertStrDateFormat(tran_dt, "dd/MM/yyyy", "dd_MM_yyyy")
                +".xls";
        writer.export_donator(file_path, 
                DefineFun.convert2OracleDateFormat(tran_dt)  ,
                period,
                gendata_FLG
                );        
        File fileToDownload = new File(file_path);         
        fileInputStream = new FileInputStream(fileToDownload);
        fileName = fileToDownload.getName();
        contentLength = fileToDownload.length();                 
        return SUCCESS;
    }
    
    public String downloadFileUpload(){
        try {
            System.err.println("downloadFileUpload -->Query date" + query_dt);
            ExcelFileWriter writer = new ExcelFileWriter();
            String file_path = Define.M_ROOT + Define.M_REPORT_XLS + "CLYT.FileUploadTC."
                    + DefineFun.convertStrDateFormat(query_dt, "dd/MM/yyyy", "ddMMyyyy")
                    +".xls";
            writer.export_uploadfile(file_path,
                    DefineFun.convert2OracleDateFormat(query_dt)
            );
            File fileToDownload = new File(file_path);
            filereport = fileToDownload.getName();      
            fileNamelocal = file_path;
        } catch (ParseException ex) {
            Logger.getLogger(ExportFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }
    
    public String downloadQTTFileUpload(){
        try {
            System.err.println("downloadQTTFileUpload -->Query date" + query_dt);
            ExcelFileWriter writer = new ExcelFileWriter();
            String file_path = Define.M_ROOT + Define.M_REPORT_XLS + "QTT.FileUploadTC."
                    + DefineFun.convertStrDateFormat(query_dt, "dd/MM/yyyy", "ddMMyyyy")
                    +".xls";
            writer.export_qtt_uploadfile(file_path,
                    DefineFun.convert2OracleDateFormat(query_dt)
            );
            File fileToDownload = new File(file_path);
            filereport = fileToDownload.getName();      
            fileNamelocal = file_path;
        } catch (ParseException ex) {
            Logger.getLogger(ExportFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }
    
    public String downloadVVCFileUpload(){
        try {
            System.err.println("downloadVVCFileUpload -->Query date" + query_dt);
            ExcelFileWriter writer = new ExcelFileWriter();
            String file_path = Define.M_ROOT + Define.M_REPORT_XLS + "VVC.FileUploadTC."
                    + DefineFun.convertStrDateFormat(query_dt, "dd/MM/yyyy", "ddMMyyyy")
                    +".xls";
            writer.export_vvc_uploadfile(file_path,
                    DefineFun.convert2OracleDateFormat(query_dt)
            );
            File fileToDownload = new File(file_path);
            filereport = fileToDownload.getName();      
            fileNamelocal = file_path;
        } catch (ParseException ex) {
            Logger.getLogger(ExportFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return SUCCESS;
    }
    
    public long getContentLength() {
        return contentLength;
    }
 
    public String getFileName() {
        return fileName;
    }
    
    public String getParameterValue(String param) {
        Object paramObj = getParameters().get(param);
        if (paramObj == null) {
            return null;
        }
        return ((String[]) paramObj)[0];
    }

    public final Map getParameters() {
        parameters = ActionContext.getContext().getParameters();
        return parameters;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

    public String getQuery_dt() {
        return query_dt;
    }

    public void setQuery_dt(String query_dt) {
        this.query_dt = query_dt;
    }

    
    
    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }
    
    
}
