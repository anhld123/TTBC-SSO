/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 *
 * @author Trung
 */
public class DownloadAction extends ActionSupport {

    private InputStream fileInputStream;
    private String downloadFileName;

    /**
     * Will override the default in struts.xml.
     *
     * @return
     */
    public String getContentDisposition() {
        return "attachment;filename=" + getFileName(getDownloadFileName());
    }

    public InputStream getFileInputStream() {
        return fileInputStream;
    }

    public void setFileInputStream(InputStream fileInputStream) {
        this.fileInputStream = fileInputStream;
    }

    public String getDownloadFileName() {
        return downloadFileName;
    }

    public void setDownloadFileName(String downloadFileName) {
        this.downloadFileName = downloadFileName;
    }

    public String download() throws Exception {        
        String filesPath = downloadFileName.trim();//Define.M_ROOT + Define.M_REPORT_TXT;
        try {
            String file = filesPath; //+ File.separator + getDownloadFileName();
            setFileInputStream(new FileInputStream(file));
            return "success";
        } catch (FileNotFoundException e) {
            System.err.println("download error" + e.getMessage());
            throw e;
        }
    }
    
    private String getFileName(String filePath){
        File fileOut = new File(downloadFileName);
        if (!fileOut.exists()) {
            System.err.println("Khong co file du lieu");            
            return ERROR;
        }
        String strPath=fileOut.getName();
//        String strPath = filePath.substring(filePath.lastIndexOf("/")+1, 
//                filePath.length());
        return strPath;
    }
}
