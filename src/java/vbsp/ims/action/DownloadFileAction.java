package vbsp.ims.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

public class DownloadFileAction extends ActionSupport implements Action  {

    private InputStream fileInputStream;
    private String fileName;
    String fileNamelocal;
    String message;
    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public String execute() throws Exception {
       
     
        //System.err.println("File da tao ra ----- -----"+fileNamelocal);
        File fileOut = new File(fileNamelocal);
        if (!fileOut.exists()) {
            System.err.println("Khong co file du lieu");
            setMessage("Bạn chưa tạo được file nên không thể tải file");
            return ERROR;
        }
        fileName=fileOut.getName();
        fileInputStream = new FileInputStream(new File(fileOut.getParent()+"/"+fileName));
        System.gc();
        return SUCCESS;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }
    
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
