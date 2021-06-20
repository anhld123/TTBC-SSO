/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.test;

import java.util.ArrayList;
import java.util.List;
//import sun.org.mozilla.javascript.internal.ObjArray;
import vbsp.ims.core.MappingClassValue;
import vbsp.ims.model.ModelDsHongheo;

/**
 *
 * @author LION
 */
public class TestMappingClass {

    public static void main(String[] args) {
        try {
            String className = "vbsp.ims.model.ModelDsHongheo";
            MappingClassValue map = new MappingClassValue(className);
            List<Object> lstobj = new ArrayList<>();
            lstobj.add(map.setValueFieldOutClass("AA", "Nguyen van tung"));
            
            for(Object obj:lstobj)
            {
                ModelDsHongheo value=(ModelDsHongheo)obj;
                System.err.println("giatri="+value.getTenkh());
            }
        } catch (Exception e) {
                 System.err.println(e.getMessage());
        }
//        int col_excel;
//    private String col_table;
    }
}
