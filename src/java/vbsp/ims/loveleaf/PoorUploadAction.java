/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.loveleaf;

import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.dtw.Dtw_Import;

/**
 *
 * @author Trung
 */
public class PoorUploadAction extends ActionSupport
        implements ServletRequestAware {

    private List<File> fileUpload = new ArrayList<>();
    private List<String> fileUploadContentType = new ArrayList<>();
    private List<String> fileUploadFileName = new ArrayList<>();
    HttpServletRequest request;

    private String message;
    private String fileName;
    private String query_dt;
    private String pass_dt_NAME;

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

                if (file_name.toUpperCase().startsWith("DSLARACH_")) {
                    dtw_import.import_file(
                            new_file_path,
                            vbsp.ims.define.Constant.dtw_insert_table._LOVELEAF_POOR_TRANSACTION);
                } else {
                    dtw_import.import_file(
                            new_file_path,
                            vbsp.ims.define.Constant.dtw_insert_table._LOVELEAF_POOR_NOACCOUNT);
                }
                message = "(*) Copy và ghi nhận thành công: [" + file_name + "].";
                fileName = file_name;

            } else {
                message = "(*) Copy file vào thư mục thất bại.";
            }
            return SUCCESS;
        }
    }

    public String view_upload_file() {
        LoveLeafDao leafDao = new LoveLeafDao();
        poorTransactions = leafDao.list_poor_trans(fileName);
        return SUCCESS;

    }

    public String view_poor_infor() {
        message = "Ngày truyền vào: " + query_dt;
        if (query_dt == null) {
            return ERROR;
        } else {
            LoveLeafDao leafDao = new LoveLeafDao();
            System.err.println("PoorUploadAction.view_poor_infor-->" + query_dt);
            poorTransactions = leafDao.list_poor_trans_by_date(
                    DefineFun.convert2OracleDateFormat(query_dt)
            );
            return SUCCESS;
        }

    }

    public String open_upload() {
        return SUCCESS;
    }

    public String list_wrong_poor() {
        message = "Ngày truyền vào: " + query_dt;
        if (query_dt == null) {
            return ERROR;
        } else {
            LoveLeafDao leafDao = new LoveLeafDao();
            System.err.println("PoorUploadAction.list_wrong_poor-->" + query_dt);
            poorTransactions = leafDao.list_wrong_poor(
                    DefineFun.convert2OracleDateFormat(query_dt)
            );
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

    public String getQuery_dt() {
        return query_dt;
    }

    public void setQuery_dt(String query_dt) {
        this.query_dt = query_dt;
    }

    public String getPass_dt_NAME() {
        return pass_dt_NAME;
    }

    public void setPass_dt_NAME(String pass_dt_NAME) {
        this.pass_dt_NAME = pass_dt_NAME;
    }

}
