/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.report.fast;

import java.util.List;

/**
 *
 * @author LION
 */
public class paraReportFast {
    public String sColumn_name;
    public String sColumn_desc;
    public String sData_type;
    public String sPara_where;
    public List<ListValue> lstPoslist;
    public paraReportFast()
    {
        
    }
    public paraReportFast(String sColumn_name, String sColumn_desc, String sData_type,  String sPara_where)
    {
        this.sColumn_name=sColumn_name;
        this.sColumn_desc=sColumn_desc;
        this.sData_type=sData_type;
        this.sPara_where=sPara_where;
    }

    public List<ListValue> getLstPoslist() {
        return lstPoslist;
    }

    public void setLstPoslist(List<ListValue> lstPoslist) {
        this.lstPoslist = lstPoslist;
    }
    
    public String getsColumn_name() {
        return sColumn_name;
    }

    public void setsColumn_name(String sColumn_name) {
        this.sColumn_name = sColumn_name;
    }

    public String getsColumn_desc() {
        return sColumn_desc;
    }

    public void setsColumn_desc(String sColumn_desc) {
        this.sColumn_desc = sColumn_desc;
    }
    
    public String getsData_type() {
        return sData_type;
    }

    public void setsData_type(String sData_type) {
        this.sData_type = sData_type;
    }

    public String getsPara_where() {
        return sPara_where;
    }

    public void setsPara_where(String sPara_where) {
        this.sPara_where = sPara_where;
    }
    
    
    
}
