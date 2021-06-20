/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.dtw;

/**
 *
 * @author Trung
 */
public class UploadFileLogObject extends LogObject {

    private String dwh_file_path;
    private String dwh_filename;
    private String dwh_filesize;
    private String dwh_upload_time;
    private String dwh_process_time;
    private String dwh_upload_status;
    private String dwh_mkr_id;
    private String dwh_comment;
    private int row_total;
    private int processed_row;

    public UploadFileLogObject(String dwh_file_path,
            String dwh_filename,
            String dwh_filesize,
            String dwh_upload_time,
            String dwh_process_time,
            String dwh_upload_status,
            String dwh_mkr_id,
            String dwh_comment) {
        this.dwh_file_path = dwh_file_path;
        this.dwh_filename = dwh_filename;
        this.dwh_filesize = dwh_filesize;
        this.dwh_upload_time = dwh_upload_time;
        this.dwh_process_time = dwh_process_time;
        this.dwh_upload_status = dwh_upload_status;
        this.dwh_mkr_id = dwh_mkr_id;
        this.dwh_comment = dwh_comment;
    }

    public UploadFileLogObject(
            String filename,
            String filesize,
            String upload_time,
            String upload_status,
            int row_total,
            int processed_row
    ) {
        this.dwh_filename = filename;
        this.dwh_filesize = filesize;
        this.dwh_upload_time = upload_time;
        this.dwh_upload_status = upload_status;
        this.row_total = row_total;
        this.processed_row = processed_row;
    }

    public String getDwh_file_path() {
        return dwh_file_path;
    }

    public void setDwh_file_path(String dwh_file_path) {
        this.dwh_file_path = dwh_file_path;
    }

    public String getDwh_filename() {
        return dwh_filename;
    }

    public void setDwh_filename(String dwh_filename) {
        this.dwh_filename = dwh_filename;
    }

    public String getDwh_filesize() {
        return dwh_filesize;
    }

    public void setDwh_filesize(String dwh_filesize) {
        this.dwh_filesize = dwh_filesize;
    }

    public String getDwh_upload_time() {
        return dwh_upload_time;
    }

    public void setDwh_upload_time(String dwh_upload_time) {
        this.dwh_upload_time = dwh_upload_time;
    }

    public String getDwh_process_time() {
        return dwh_process_time;
    }

    public void setDwh_process_time(String dwh_process_time) {
        this.dwh_process_time = dwh_process_time;
    }

    public String getDwh_upload_status() {
        return dwh_upload_status;
    }

    public void setDwh_upload_status(String dwh_upload_status) {
        this.dwh_upload_status = dwh_upload_status;
    }

    public String getDwh_mkr_id() {
        return dwh_mkr_id;
    }

    public void setDwh_mkr_id(String dwh_mkr_id) {
        this.dwh_mkr_id = dwh_mkr_id;
    }

    public String getDwh_comment() {
        return dwh_comment;
    }

    public void setDwh_comment(String dwh_comment) {
        this.dwh_comment = dwh_comment;
    }

    public int getRow_total() {
        return row_total;
    }

    public void setRow_total(int row_total) {
        this.row_total = row_total;
    }

    public int getProcessed_row() {
        return processed_row;
    }

    public void setProcessed_row(int processed_row) {
        this.processed_row = processed_row;
    }

}
