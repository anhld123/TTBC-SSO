/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.balanceadjust;

import vbsp.ims.loveleaf.*;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.Define;
import vbsp.ims.dtw.Dtw_Import;

/**
 *
 * @author Trung
 */
public class BalanceUploadAction extends ActionSupport
        implements ServletRequestAware {

    private List<File> fileUpload = new ArrayList<>();
    private List<String> fileUploadContentType = new ArrayList<>();
    private List<String> fileUploadFileName = new ArrayList<>();
    HttpServletRequest request;

    private String message;
    private String fileName;
    private String userName;

    private List<PoorTransaction> poorTransactions;

    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String upload_file() {
        if (fileUploadFileName.isEmpty()) {

            message = "(*) Chưa có file nào được lựa chọn. Bạn hãy kiểm tra lại. ";
            return ERROR;

        } else {

            /* Phan cap nhat file */
            String new_file_path = copy_file();
            File new_file = new File(new_file_path);

            if (new_file.isFile()) {

                String file_name = new_file.getName();
                Dtw_Import dtw_import = new Dtw_Import();

                dtw_import.import_file(
                        new_file_path,
                        vbsp.ims.define.Constant.dtw_insert_table._ACCOUNT_UPOAD);

                message = "(*) Copy và ghi nhận thành công: [" + file_name + "].";
                fileName = file_name;

            } else {
                message = "(*) Copy file vào thư mục thất bại.";
            }
            return SUCCESS;
        }
    }

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
                FileUtils.copyFile(fileUpload.get(index), destFile);
                if (index == 0) {
                    mainReportPath = destPath + filename;
                }
                index++;
            } catch (IOException e) {
            }
        }
        return mainReportPath;
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

    public List<PoorTransaction> getPoorTransactions() {
        return poorTransactions;
    }

    public void setPoorTransactions(List<PoorTransaction> poorTransactions) {
        this.poorTransactions = poorTransactions;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    
    
    
}
