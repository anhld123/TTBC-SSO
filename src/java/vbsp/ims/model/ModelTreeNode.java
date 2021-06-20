/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

/**
 *
 * @author LION
 */
public class ModelTreeNode {
    private String strParentCd;
    private String strParentDesc;
    private String strChildCd;
    private String strChildDesc;

    public ModelTreeNode(String strParentCd,String strParentDesc,String strChildCd,String strChildDesc) {
        this.strParentCd=strParentCd;
        this.strParentDesc=strParentDesc;
        this.strChildCd=strChildCd;
        this.strChildDesc=strChildDesc;
    }
    public String getStrParentCd() {
        return strParentCd;
    }

    public void setStrParentCd(String strParentCd) {
        this.strParentCd = strParentCd;
    }

    public String getStrParentDesc() {
        return strParentDesc;
    }

    public void setStrParentDesc(String strParentDesc) {
        this.strParentDesc = strParentDesc;
    }

    public String getStrChildCd() {
        return strChildCd;
    }

    public void setStrChildCd(String strChildCd) {
        this.strChildCd = strChildCd;
    }

    public String getStrChildDesc() {
        return strChildDesc;
    }

    public void setStrChildDesc(String strChildDesc) {
        this.strChildDesc = strChildDesc;
    }
}
