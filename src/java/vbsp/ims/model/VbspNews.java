/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.model;

import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class VbspNews {
    private int id;
    private String title;
    private String message;
    
    public VbspNews(){}
    
    public VbspNews(int id,String title,String message){
        this.id = id;
        this.title = title;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public ListValue getListValue(){
        return new ListValue(String.valueOf(id), String.valueOf(id) + " - " + title);
    }
}
