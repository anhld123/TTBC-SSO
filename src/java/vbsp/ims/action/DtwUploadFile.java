/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
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
import vbsp.ims.dtw.Dtw_Import;
import vbsp.ims.dtw.UploadFileLogObject;
import vbsp.ims.dtw.dao.DtwUploadDao;
import vbsp.ims.zip.FileZip;

/**
 *
 * @author Trung
 */
public class DtwUploadFile extends ActionSupport
        implements ServletRequestAware {

    private List<File> fileUpload = new ArrayList<>();
    private List<String> fileUploadContentType = new ArrayList<>();
    private List<String> fileUploadFileName = new ArrayList<>();
    HttpServletRequest request;

    private String message;
    private String font_type;
    private static List<UploadFileLogObject> logObj = new ArrayList<>();

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String upload_file() {
        System.err.println("Vao phan upload file");
        if (fileUploadFileName.isEmpty()) {
            message = "(*) Chưa có file nào được lựa chọn. Bạn hãy kiểm tra lại. ";
            return "success";
        } else {
            /* Phan cap nhat file */
            String new_file_path = copy_file();
            File new_file = new File(new_file_path);
            if (new_file.isFile()) {
                String file_name = new_file.getName();
                System.err.println("Vao phan unzip file");
                boolean is_unzip = unzip_file(new_file.getAbsolutePath(), new_file.getParent());
                if (is_unzip) {
                    System.err.println("Vao phan doc du lieu");
                    Dtw_Import dtw_import = new Dtw_Import();
                    dtw_import.import_directory(new_file.getParent() + "/"
                            + FilenameUtils.removeExtension(file_name), font_type);
                    DtwUploadDao uploadDao = new DtwUploadDao();
                    String dir_path = new_file.getParent() + "/"
                            + FilenameUtils.removeExtension(file_name);
                    logObj = uploadDao.get_uploaded_log(dir_path.replace("/", "\\"));
                    message = "(*) Copy và giải nén vào thư mục thành công: [" + file_name + "].";
                } else {
                    message = "(*) Copy thành công nhưng không giải nén được: [" + file_name + "].";
                }
            } else {
                message = "(*) Copy file vào thư mục thất bại.";
            }

            return "success";
        }
    }

    public String view_log() {
        //logObj
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
        } finally {
            input.close();
            output.close();
        }
    }

    private boolean unzip_file(String zip_file_path, String directory_path) {
        File zip_file = new File(zip_file_path);
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

}
