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
public class ManualInputObject {
    
    private String code;
    private String groupCode;
    private String shortDesc;
    private String fullDesc;
    private String period;
    private String link;
    private String permit;    
    
    public ManualInputObject(){}
    
    public ManualInputObject(String code,String groupCode,
            String shortDesc,String fullDesc,String period,
            String link,String permit){
        this.code = code;
        this.groupCode = groupCode;
        this.shortDesc = shortDesc;
        this.fullDesc = fullDesc;
        this.period = period;
        this.link = link;
        this.permit = permit;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getGroupCode() {
        return groupCode;
    }

    public void setGroupCode(String groupCode) {
        this.groupCode = groupCode;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public String getFullDesc() {
        return fullDesc;
    }

    public void setFullDesc(String fullDesc) {
        this.fullDesc = fullDesc;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }                
}
