/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.bcqt.model;

/**
 *
 * @author Trung
 */
public class ThuyetMinh {
    
    private String code;
    private String title;
    private String content;
    private String pos_cd;
    
    public ThuyetMinh(){}
    
    public ThuyetMinh(String code,String title,String content,String pos_cd){
        this.code = code;
        this.title = title;
        this.content = content;
        this.pos_cd = pos_cd;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }
    
    
}
