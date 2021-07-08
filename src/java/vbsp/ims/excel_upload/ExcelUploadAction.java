/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.excel_upload;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FilenameUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.Define;
import vbsp.ims.dtw.UploadFileLogObject;
import vbsp.ims.dtw.dao.DtwUploadDao;
import vbsp.ims.fileutil.FileUtil;
import vbsp.ims.zip.FileZip;
import vbsp.ims.khnv2021.ReportTemplate;
import vbsp.ims.excel_upload.model.ResultModel;

/**
 *
 * @author Trung Nguyen
 */
public class ExcelUploadAction extends ActionSupport
        implements ServletRequestAware {
    
    private List<File> fileUpload = new ArrayList<>();
    private List<String> fileUploadContentType = new ArrayList<>();
    private List<String> fileUploadFileName = new ArrayList<>();
    HttpServletRequest request;

    private String message;
    private String font_type;
    private static List<UploadFileLogObject> logObj = new ArrayList<>();
    private String logPath;
    private String logPathType;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String upload_file() {
        
        //System.err.println("Vao phan upload file");
        if (fileUploadFileName.isEmpty()) {
            
            message = "(*) Chưa có file nào được lựa chọn. Bạn hãy kiểm tra lại. ";
            return "success";
            
        } else {
            
            /* Phan cap nhat file */
            String new_file_path = copy_file();
            File new_file = new File(new_file_path);
            
            if (new_file.isFile()) {
                
                String fileExtend = FilenameUtils.getExtension(new_file_path);
                String file_name = new_file.getName();
                
                if (fileExtend.toLowerCase().equals("zip")) {
                    boolean is_unzip = unzip_file(new_file.getAbsolutePath(), new_file.getParent());
                
                    if (is_unzip) {

                        ExcelUploader excelUploader = new ExcelUploader();
                        ResultModel status = excelUploader.import_directory(new_file.getParent() + "/"
                                + FilenameUtils.removeExtension(file_name), font_type);

                        DtwUploadDao uploadDao = new DtwUploadDao();
                        String dir_path = new_file.getParent() + "/"
                                + FilenameUtils.removeExtension(file_name);

                        logPath = dir_path.replace("/", "\\");
                        logPathType = ReportTemplate.DIRECTORY;
                        logObj = uploadDao.get_uploaded_log(dir_path.replace("/", "\\"), ReportTemplate.DIRECTORY);
                        if (status.status) {
                        message = "(*) Copy và giải nén vào thư mục thành công: [" + file_name + "].";
                        } else {
                            message = status.message;
                        }

                    } else {
                        message = "(*) Copy thành công nhưng không giải nén được: [" + file_name + "].";
                    }
                } else if (fileExtend.toLowerCase().equals("xls")
                        || fileExtend.toLowerCase().equals("xlsx")) {
                    ExcelUploader excelUploader = new ExcelUploader();
                       ResultModel status = excelUploader.import_file(new_file.getAbsolutePath(), font_type);

                        DtwUploadDao uploadDao = new DtwUploadDao();
                        String file_path = FilenameUtils.removeExtension(new_file.getAbsolutePath());

                        logPath = file_name;
                        logPathType = ReportTemplate.FILE;
                        logObj = uploadDao.get_uploaded_log(file_name,ReportTemplate.FILE);
                        if (status.status) {
                        message = "(*) Xử lý file thành công: [" + file_name + "].";
                        }else {
                            message = status.message;
                        }
                } else {
                    message = "(*) Không hỗ trợ định dạng file: " + fileExtend.toLowerCase();
                }
                
                
            } else {
                message = "(*) Copy file vào thư mục thất bại.";
            }

            return "success";
        }
    }

    public String view_log() {
        //logObj
        DtwUploadDao uploadDao = new DtwUploadDao();
        logObj = uploadDao.get_uploaded_log(logPath, logPathType);
        return SUCCESS;
    }

    //-----------------------------------------------------------------------------------------------

    private String copy_file() {
        String destPath, mainReportPath = "";
        File destFile;
        int index = 0;
        for (String filename : fileUploadFileName) {
            try {
                destPath = !request.getRealPath("/").endsWith("/")
                        ? request.getRealPath("/") + "/" + Define.M_UPLOAD_DIR
                        : request.getRealPath("/") + Define.M_UPLOAD_DIR;
                destFile = new File(destPath, filename);
                //FileUtils.copyFile(fileUpload.get(index), destFile);
                copyFileUsingFileStreams(fileUpload.get(index), destFile);
                if (index == 0) {
                    mainReportPath = destPath + filename;
                }
                index++;
            } catch (IOException e) {
                System.err.println("error when copy large file: " //+fileUpload.get(index)
                        + "~" + e.getMessage());
            }
        }
        return mainReportPath;
    }

    private static void copyFileUsingFileStreams(File source, File dest)
            throws IOException {
        
        InputStream input = null;
        OutputStream output = null;
        
        try {
            input = new FileInputStream(source);
            output = new FileOutputStream(dest);
            byte[] buf = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buf)) > 0) {
                output.write(buf, 0, bytesRead);
            }
        } catch (FileNotFoundException ex) {
            // complain to user
        } catch (IOException ex) {
            // notify user    
        } finally {
            input.close();
            output.close();
        }
    }

    private boolean unzip_file(String zip_file_path, String directory_path) {
        
        File zip_file = new File(zip_file_path);        
        String strFileName = zip_file.getName();
        
        // Xoa du lieu truoc khi giai nen
            FileUtil.deleteFolder(directory_path +  "/"
                            + FilenameUtils.removeExtension(strFileName));
        
        String file_ext = FilenameUtils.getExtension(zip_file_path);
        
        if (zip_file.isFile()
                && file_ext.toLowerCase().equals("zip")) {
            FileZip.UnzipFile(zip_file_path, directory_path);
            return true;
        } else {
            return false;
        }

    }

    //--------------------------------------------------------------------------
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

    //--------------------------------------------------------------------------
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getFont_type() {
        return font_type;
    }

    public void setFont_type(String font_type) {
        this.font_type = font_type;
    }

    public List<UploadFileLogObject> getLogObj() {
        return logObj;
    }

    public void setLogObj(List<UploadFileLogObject> logObj) {
        this.logObj = logObj;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public String getLogPathType() {
        return logPathType;
    }

    public void setLogPathType(String logPathType) {
        this.logPathType = logPathType;
    }
    
    

    
}
