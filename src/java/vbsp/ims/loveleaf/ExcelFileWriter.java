/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.loveleaf;

import java.sql.ResultSet;
import vbsp.ims.define.Define;


/**
 *
 * @author Trung
 */
public class ExcelFileWriter {
    
    public void export_donator(
            String pv_file_name,
            String pv_tran_dt,
            String period,
            String generate_FLG
    ){
        String lv_file_path =  pv_file_name;
        LoveLeafDao loveLeafDao = new LoveLeafDao();
        loveLeafDao.export_donator_to_excel(pv_tran_dt, lv_file_path,period,generate_FLG);                         
    }
    
    public void export_uploadfile(
            String pv_file_name,
            String pv_tran_dt         
    )
    {
        String lv_file_path =  pv_file_name;
        LoveLeafDao loveLeafDao = new LoveLeafDao();
        loveLeafDao.export_upload_file(pv_tran_dt, lv_file_path);                         
    }
    
    public void export_qtt_uploadfile(
            String pv_file_name,
            String pv_tran_dt         
    )
    {
        String lv_file_path =  pv_file_name;
        LoveLeafDao loveLeafDao = new LoveLeafDao();
        loveLeafDao.export_qtt_upload_file(Define.QTT_PROGRAM, pv_tran_dt, lv_file_path);                         
    }
    
    public void export_vvc_uploadfile(
            String pv_file_name,
            String pv_tran_dt         
    )
    {
        String lv_file_path =  pv_file_name;
        LoveLeafDao loveLeafDao = new LoveLeafDao();
        loveLeafDao.export_qtt_upload_file(Define.VVC_PROGRAM, pv_tran_dt, lv_file_path);                         
    }
    
}
