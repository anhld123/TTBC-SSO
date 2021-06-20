/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor. vbsp.ims.model.ModelMapping
 */
package vbsp.ims.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author LION
 */
public class ModelMapping {

    private int col_excel;
    private String col_table;
    private String data_type;
    private int data_lenght;
    public String variable_java;

    public int getCol_excel() {
        return col_excel;
    }

    public void setCol_excel(int col_excel) {
        this.col_excel = col_excel;
    }

    public String getCol_table() {
        return col_table;
    }

    public void setCol_table(String col_table) {
        this.col_table = col_table;
    }

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public int getData_lenght() {
        return data_lenght;
    }

    public void setData_lenght(int data_lenght) {
        this.data_lenght = data_lenght;
    }

    public String getVariable_java() {
        return variable_java;
    }

    public void setVariable_java(String variable_java) {
        this.variable_java = variable_java;
    }

//    public Object setvalues(String classname, String method, String value)
//    {
//        
//    }
    public static void main(String[] args) throws Exception {
        Object[] obj = null;//ModelMapping.class.getDeclaredFields();

        Class<?> clazz = Class.forName("vbsp.ims.model.ModelMapping");

        Field field = clazz.getDeclaredField("col_excel");

        clazz.getConstructor();
        Constructor ct = clazz.getConstructor();
        Object objCls = ct.newInstance();

        System.err.println(field);
        Class clazzType = field.getType();
        System.err.println("Type=" + clazzType.getName());

        field.setInt(objCls, 123);

        System.err.println("col_excel=" + field.getInt(objCls));
        
        Method method=clazz.getMethod("setData_type");
        
        System.err.println("abc");
//         
//        for (int i = 0; i < obj.length; i++) {
//            System.err.println("Test thu " + obj[i].toString());
//            try {
//                ModelMapping.class.getDeclaredFields()[i].getInt(123);
//            } catch (IllegalArgumentException ex) {
//                Logger.getLogger(ModelMapping.class.getName()).log(Level.SEVERE, null, ex);
//            } catch (IllegalAccessException ex) {
//                Logger.getLogger(ModelMapping.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
    }
}
