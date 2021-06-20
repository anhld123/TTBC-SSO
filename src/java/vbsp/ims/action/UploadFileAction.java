/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.INPUT;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.core.DataTable;
import vbsp.ims.core.RowField;
import vbsp.ims.dao.DaoDsHongheo;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelDsHongheo;
import vbsp.ims.model.ModelMapping;
import vbsp.ims.model.ModelProcessDsHongheo;
import vbsp.ims.reader.ReaderFileExcel;

/**
 *
 * @author LION
 */
public class UploadFileAction extends ActionSupport {

    private File fileUpload;
    private String fileUploadContentType;
    private String fileUploadFileName;
    private String[] Ext_File = {"xls", "xlsx"};
    private InputStream fileInputStream;
    private List<ModelDsHongheo> lstDsHongheo = new ArrayList<>();
    private String fileName;

    public String execute() {

        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return INPUT;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            boolean bExt = false;
            String filePath = ServletActionContext.getServletContext().getRealPath("/");
            if (!filePath.endsWith("/") || !filePath.endsWith("\\")) {
                filePath += "/";
            }
            filePath += Define.M_IMPORT_FILE;

            File checkdir = new File(filePath);
            if (!checkdir.exists()) {
                System.err.println("Tao duong dan " + filePath);
                checkdir.mkdirs();
            }
            System.out.println("Server path:" + filePath);
            for (int i = 0; i < Ext_File.length; i++) {
                if (fileUploadFileName.toLowerCase().endsWith(Ext_File[i])) {
                    bExt = true;
                    break;
                }
            }
            if (!bExt) {
                addActionError("Bạn upload định dạng file không đúng chỉ được phép upload file *.xls, *.xlsx, *.zip");
//                addActionError("Bạn upload định dạng file không đúng");
//                addFieldError("fileUpload", "Bạn upload định dạng file không đúng --");
                return INPUT;
            }
            File fileToCreate = new File(filePath, this.fileUploadFileName);
            FileUtils.copyFile(this.fileUpload, fileToCreate);
//            fileUploadFileName = "../" + fileUploadFileName;

            //neu la file zip thi giai nen sau do lay file xls, xlsx de xu ly
            if (fileUploadFileName.toLowerCase().endsWith("zip")) {

            }
            String FullPath = filePath + fileUploadFileName;
            ReaderFileExcel read = new ReaderFileExcel(FullPath);
            read.setStartingRow(3);
//            read.setLastRow(100);
            read.setLastColumn(12);
            DataTable table = read.readFile();

            //kiem tra xem trong du lieu co dong nao du lieu bi loi khong
            Map<Integer, RowField> mapErr = new ModelProcessDsHongheo().getCheckDataError(table);
            if (mapErr.size() > 0) {
                String Mess = "Lỗi dữ liệu tại dòng ";
                for (Integer i : mapErr.keySet()) {
                    Mess += Integer.toString(mapErr.get(i).getRownum() + 1) + ",";
//                    addActionError("Lỗi dữ liệu tại dòng "+(i+1)+", ");
                }
                addActionError(Mess);
                return INPUT;
            }
            DaoDsHongheo daoDs = new DaoDsHongheo();
            //Luu du lieu vao csdl
            HashMap<Integer, ModelMapping> hmMapCol = daoDs.getMappingColumn();
            List<Object> lstDsNgheo = new ModelProcessDsHongheo().getDataMapColumnTable(table, hmMapCol);
            if (daoDs.saveDsHongheo(lstDsNgheo, sUserName)) {
                addActionMessage("Bạn đã lưu vào csdl thành công số row dữ liệu là: " + lstDsNgheo.size());
//                return SUCCESS;
            } else {
                CoreLogger.error(" Lỗi khi lưu dữ liệu xin liên hệ với quản trị để khác phục ");
                return INPUT;
            }
            setLstDsHongheo(new ModelProcessDsHongheo().getTableviewInsert(lstDsNgheo));
            table.clear();
            lstDsNgheo.clear();

        } catch (Exception e) {
            e.printStackTrace();
            addActionError("Loi khi upload du lieu len " + e.getMessage());
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute " + e.getMessage());
            return INPUT;
        }
        return SUCCESS;
    }

    public String saveDsHongheo() {
        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
                return INPUT;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
            String filePath = ServletActionContext.getServletContext().getRealPath("/");
            if (!filePath.endsWith("/") || !filePath.endsWith("\\")) {
                filePath += "/";
            }
            filePath += Define.M_IMPORT_FILE;
            String FullPath = filePath + fileUploadFileName;
            ReaderFileExcel read = new ReaderFileExcel(FullPath);
            read.setStartingRow(3);
//            read.setLastRow(100);
            read.setLastColumn(12);
            DataTable table = read.readFile();

            //kiem tra xem trong du lieu co dong nao du lieu bi loi khong
            Map<Integer, RowField> mapErr = new ModelProcessDsHongheo().getCheckDataError(table);
            if (mapErr.size() > 0) {
                String Mess = "Lỗi dữ liệu tại dòng ";
                for (Integer i : mapErr.keySet()) {
                    Mess += Integer.toString(mapErr.get(i).getRownum() + 1) + ",";
//                    addActionError("Lỗi dữ liệu tại dòng "+(i+1)+", ");
                }
                addActionError(Mess);
                return ERROR;
            }

            DaoDsHongheo daoDs = new DaoDsHongheo();
            //Luu du lieu vao csdl
            HashMap<Integer, ModelMapping> hmMapCol = daoDs.getMappingColumn();
            List<Object> lstDsNgheo = new ModelProcessDsHongheo().getDataMapColumnTable(table, hmMapCol);
            if (daoDs.saveDsHongheo(lstDsNgheo, sUserName)) {
                addActionMessage("Bạn đã lưu vào csdl thành công số row dữ liệu là: " + lstDsNgheo.size());
                return SUCCESS;
            } else {
                addActionError("Lỗi khi lưu dữ liệu xin liên hệ với quản trị để khác phục");
                CoreLogger.error(" Lỗi khi lưu dữ liệu xin liên hệ với quản trị để khác phục ");
                return ERROR;
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError(e.getMessage());
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveDsHongheo " + e.getMessage());
            return ERROR;
        }
//        return SUCCESS;
    }

    public String Downloadfile() throws Exception {
        String filePath = ServletActionContext.getServletContext().getRealPath("/");
        if (!filePath.endsWith("/") || !filePath.endsWith("\\")) {
            filePath += "/";
        }
        
        File fileToDownload = new File(filePath + Define.M_EXCEL_TEMP + "maunhap_hongheo.xlsx");
        if(!fileToDownload.exists())
        {
            addActionError("Không tìm thấy file dữ liệu để tải về");
            return ERROR;
        }
        fileName = fileToDownload.getName();
        fileInputStream = new FileInputStream(fileToDownload);
        return SUCCESS;
    }

    public File getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(File fileUpload) {
        this.fileUpload = fileUpload;
    }

    public String getFileUploadContentType() {
        return fileUploadContentType;
    }

    public void setFileUploadContentType(String fileUploadContentType) {
        this.fileUploadContentType = fileUploadContentType;
    }

    public String getFileUploadFileName() {
        return fileUploadFileName;
    }

    public void setFileUploadFileName(String fileUploadFileName) {
        this.fileUploadFileName = fileUploadFileName;
    }

    public List<ModelDsHongheo> getLstDsHongheo() {
        return lstDsHongheo;
    }

    public void setLstDsHongheo(List<ModelDsHongheo> lstDsHongheo) {
        this.lstDsHongheo = lstDsHongheo;
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

}
