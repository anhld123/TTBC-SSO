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
public class Branch {
    private String pos_cd;
    private String pos_name;
    private String pos_address;
    private String pos_fax;
    private String pos_mobile;
    private String pos_sbvcode;
    private String pos_flag;
    private String main_pos;
    private String status;
    private String maker_id;
    private String maker_dt;
    
    public Branch(){}
    
    public Branch(String pos_cd,String pos_name,String pos_address,String pos_fax,
            String pos_mobile,String pos_sbvcode,String pos_flag,String main_pos,
            String status,String maker_id,String maker_dt){
        this.pos_cd = pos_cd;
        this.pos_name = pos_name;
        this.pos_address = pos_address;
        this.pos_fax = pos_fax;
        this.pos_mobile = pos_mobile;
        this.pos_sbvcode = pos_sbvcode;
        this.pos_flag = pos_flag;
        this.main_pos = main_pos;
        this.status = status;
        this.maker_id = maker_id;
        this.maker_dt = maker_dt;
    }
    
    public void setInfor(String pos_cd,String pos_name,String pos_address,String pos_fax,
            String pos_mobile,String pos_sbvcode,String pos_flag,String main_pos,
            String status,String maker_id,String maker_dt){
        this.pos_cd = pos_cd;
        this.pos_name = pos_name;
        this.pos_address = pos_address;
        this.pos_fax = pos_fax;
        this.pos_mobile = pos_mobile;
        this.pos_sbvcode = pos_sbvcode;
        this.pos_flag = pos_flag;
        this.main_pos = main_pos;
        this.status = status;
        this.maker_id = maker_id;
        this.maker_dt = maker_dt;
    }
    
    public void clone(Branch branch){
        this.pos_cd = branch.getPos_cd();
        this.pos_name = branch.getPos_name();
        this.pos_address = branch.getPos_address();
        this.pos_fax = branch.getPos_fax();
        this.pos_mobile = branch.getPos_mobile();
        this.pos_sbvcode = branch.getPos_sbvcode();
        this.pos_flag = branch.getPos_flag();
        this.main_pos = branch.getMain_pos();
        this.status = branch.getStatus();
        this.maker_id = branch.getMaker_id();
        this.maker_dt = branch.getMaker_dt();
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getPos_name() {
        return pos_name;
    }

    public void setPos_name(String pos_name) {
        this.pos_name = pos_name;
    }

    public String getPos_address() {
        return pos_address;
    }

    public void setPos_address(String pos_address) {
        this.pos_address = pos_address;
    }

    public String getPos_fax() {
        return pos_fax;
    }

    public void setPos_fax(String pos_fax) {
        this.pos_fax = pos_fax;
    }

    public String getPos_mobile() {
        return pos_mobile;
    }

    public void setPos_mobile(String pos_mobile) {
        this.pos_mobile = pos_mobile;
    }

    public String getPos_sbvcode() {
        return pos_sbvcode;
    }

    public void setPos_sbvcode(String pos_sbvcode) {
        this.pos_sbvcode = pos_sbvcode;
    }

    public String getPos_flag() {
        return pos_flag;
    }

    public void setPos_flag(String pos_flag) {
        this.pos_flag = pos_flag;
    }

    public String getMain_pos() {
        return main_pos;
    }

    public void setMain_pos(String main_pos) {
        this.main_pos = main_pos;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMaker_id() {
        return maker_id;
    }

    public void setMaker_id(String maker_id) {
        this.maker_id = maker_id;
    }

    public String getMaker_dt() {
        return maker_dt;
    }

    public void setMaker_dt(String maker_dt) {
        this.maker_dt = maker_dt;
    }
    
    
}
