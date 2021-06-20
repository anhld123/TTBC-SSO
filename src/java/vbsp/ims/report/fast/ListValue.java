/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.report.fast;

/**
 *
 * @author LION
 */
public class ListValue {

    public String sKey;
    public String sDesc;
    public String sStt;

     public ListValue(String sDesc) {
        this.sDesc = sDesc;
    }
    public ListValue(String sKey, String sDesc) {
        this.sDesc = sDesc;
        this.sKey = sKey;
    }

     public ListValue(String sKey, String sDesc, String sStt) {
        this.sDesc = sDesc;
        this.sKey = sKey;
        this.sStt=sStt;
    }
    public String getsKey() {
        return sKey;
    }

    public void setsKey(String sKey) {
        this.sKey = sKey;
    }

    public String getsDesc() {
        return sDesc;
    }

    public void setsDesc(String sDesc) {
        this.sDesc = sDesc;
    }

    public String getsStt() {
        return sStt;
    }

    public void setsStt(String sStt) {
        this.sStt = sStt;
    }

}
