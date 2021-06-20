/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

/**
 *
 * @author Trung
 */
public class DownloadFileInfor {
    
    private String fileName;
    private String filePath;
    private String fileSize;
    private String url;
    public DownloadFileInfor(){}
    
    public DownloadFileInfor(String fileName,String filePath,String fileSize){        
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
        this.url = "Download";
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }    

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }        
}
