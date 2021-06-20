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
public class Group {
    private String group_id;
    private String leader_group_name;
    private String mass_org;
    private String commune_id;
    private String status;
    private String standard_flg;
    private String corrupt_flg;
    private String pos_cd;
    private String maker_id;
    private String maker_dt;
    
    public Group(){}
    
    public Group(String group_id,
            String leader_group_name,
            String mass_org,
            String commune_id,
            String status,
            String standard_flg,
            String corrupt_flg,
            String pos_cd,
            String maker_id,
            String maker_dt){
        this.group_id = group_id;
        this.leader_group_name = leader_group_name;
        this.mass_org = mass_org;
        this.commune_id = commune_id;
        this.status = status;
        this.standard_flg = standard_flg;
        this.corrupt_flg = corrupt_flg;
        this.pos_cd = pos_cd;
        this.maker_dt = maker_dt;
        this.maker_id = maker_id;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getLeader_group_name() {
        return leader_group_name;
    }

    public void setLeader_group_name(String leader_group_name) {
        this.leader_group_name = leader_group_name;
    }

    public String getMass_org() {
        return mass_org;
    }

    public void setMass_org(String mass_org) {
        this.mass_org = mass_org;
    }

    public String getCommune_id() {
        return commune_id;
    }

    public void setCommune_id(String commune_id) {
        this.commune_id = commune_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStandard_flg() {
        return standard_flg;
    }

    public void setStandard_flg(String standard_flg) {
        this.standard_flg = standard_flg;
    }

    public String getCorrupt_flg() {
        return corrupt_flg;
    }

    public void setCorrupt_flg(String corrupt_flg) {
        this.corrupt_flg = corrupt_flg;
    }

    public String getPos_cd() {
        return pos_cd;
    }

    public void setPos_cd(String pos_cd) {
        this.pos_cd = pos_cd;
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
