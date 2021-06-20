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
public class Indicator {
    private int id;
    private int type;
    private int period;
    private String group;
    private String code;
    private String name;
    private int gen_id;
    
    public Indicator(){}
    public Indicator(int id, int type, int period, String group, 
            String code,String name,int gen_id){
        this.id = id;
        this.type = type;
        this.period = period;
        this.group = group;
        this.code = code;
        this.name = name;
        this.gen_id = gen_id;
    }

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public int getPeriod() {
        return period;
    }

    public String getGroup() {
        return group;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getGen_id() {
        return gen_id;
    }
            
}
