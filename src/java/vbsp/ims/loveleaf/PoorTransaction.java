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
public class PoorTransaction {

    private String pos_cd;
    private String ref_no;
    private String id;
    private String name;
    private int amount;
    private String tran_dt;
    private String province;
    private String district;
    private String commune;
    private String status;
    private String source_file;
    private String pass_no;
    private String pass_i_plc;
    private String pass_i_dt;

    public PoorTransaction() {
    }

    public PoorTransaction(
            String pos_cd,
            String ref_no,
            String id,
            String name,
            int amount,
            String tran_dt,
            String province,
            String district,
            String commune,
            String status,
            String source_file
            ) {
        this.pos_cd = pos_cd;
        this.ref_no = ref_no;
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.tran_dt = tran_dt;
        this.province = province;
        this.district = district;
        this.commune = commune;
        this.status = status;
        this.source_file = source_file;
                
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
    }

    public String getRef_no() {
        return ref_no;
    }

    public void setRef_no(String ref_no) {
        this.ref_no = ref_no;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getTran_dt() {
        return tran_dt;
    }

    public void setTran_dt(String tran_dt) {
        this.tran_dt = tran_dt;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCommune() {
        return commune;
    }

    public void setCommune(String commune) {
        this.commune = commune;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSource_file() {
        return source_file;
    }

    public void setSource_file(String source_file) {
        this.source_file = source_file;
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
    
    public String getStatus_desc() {
        if (status.equals("D"))
            return "Đã chuyển";
        else if (status.equals("N"))
            return "Chưa chuyển";
        else
            if (status.equals("R"))
                return "Đang phát tiền";
            else
                if (status.equals("E"))
                    return "Lỗi xủ lý";
                else
                    return "Đang xử lý";
    }
    
}
