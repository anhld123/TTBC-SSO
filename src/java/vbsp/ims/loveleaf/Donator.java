/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.loveleaf;

/**
 *
 * @author Trung
 */
public class Donator {

    private String id;
    private String name;
    private String ll_name;
    private String pass_no;
    private String pass_i_plc;
    private String pass_i_dt;
    private String address;

    public Donator() {
    }

    public Donator(String id,
            String name,
            String ll_name,
            String pass_no,
            String pass_i_plc,
            String pass_i_dt,
            String address) {
        this.id = id;
        this.name = name;
        this.ll_name = ll_name;
        this.pass_no = pass_no;
        this.pass_i_plc = pass_i_plc;
        this.pass_i_dt = pass_i_dt;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLl_name() {
        return ll_name;
    }

    public void setLl_name(String ll_name) {
        this.ll_name = ll_name;
    }

    public String getPass_no() {
        return pass_no;
    }

    public void setPass_no(String pass_no) {
        this.pass_no = pass_no;
    }

    public String getPass_i_plc() {
        return pass_i_plc;
    }

    public void setPass_i_plc(String pass_i_plc) {
        this.pass_i_plc = pass_i_plc;
    }

    public String getPass_i_dt() {
        return pass_i_dt;
    }

    public void setPass_i_dt(String pass_i_dt) {
        this.pass_i_dt = pass_i_dt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

}
