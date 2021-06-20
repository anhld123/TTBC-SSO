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
public class UserStaticInfor {
    
    private String code;
    private int grade_static;

    public UserStaticInfor(){}
    
    public UserStaticInfor(String code,int grade_static){
        this.code = code;
        this.grade_static = grade_static;
    }
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getGrade_static() {
        return grade_static;
    }

    public void setGrade_static(int grade_static) {
        this.grade_static = grade_static;
    }    
}
