/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.io.InputStream;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.jasper.*;

/**
 *
 * @author LION
 */
public class ActionReport extends ActionSupport 
implements ServletRequestAware {

    private String mapgd;
    private Date ngay_bc;

    public String getMapgd() {
        return mapgd;
    }

    public void setMapgd(String mapgd) {
        this.mapgd = mapgd;
    }

    public Date getNgay_bc() {
        return ngay_bc;
    }

    public void setNgay_bc(Date ngay_bc) {
        this.ngay_bc = ngay_bc;
    }
    
    private HttpServletRequest servletRequest;
    //Khai bao InputStream duoc cau hinh trong file xml
    private InputStream inputStream;
    
    public InputStream getInputStream() {
        return inputStream;
    }

    

    public String execute() throws Exception {
        String strPathRoot = !servletRequest.getRealPath("/").endsWith("/")?servletRequest.getRealPath("/")+"/":servletRequest.getRealPath("/");
        Define.M_ROOT = strPathRoot;
        DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String strngay_bc=new String(dateFormat.format(ngay_bc));
        System.err.println("Bat dau vao execute " + mapgd +" " + strngay_bc);
        
        
        if (mapgd!="" || mapgd !=null) {
            DaoConnect daocon = new DaoConnect();
            Connection conn = daocon.getConnect();
            if(conn==null)
                return "error";
            
            //lay ra ten file report
            String strFilejrxml = Define.M_ROOT+Define.M_REPORT+"Candoi.jrxml";
            HashMap<String, Object> paramHashMap = new HashMap<String, Object>();
            paramHashMap.put("EOD_DATE", strngay_bc);
            paramHashMap.put("POS_CD", mapgd);
            ExportJasperReport compliler = new ExportJasperReport();
           // String strPdf = compliler.ExportJasperPdf1(strFilejrxml,paramHashMap,conn);
            if (conn != null) {
                conn.close();
            }
//            System.err.println("duong dan file pdf " + strPdf);
//            inputStream = new DataInputStream(
//			  new FileInputStream(strPdf));
            return "success";
        } else {
            return "error";
        }

    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }
   
}
