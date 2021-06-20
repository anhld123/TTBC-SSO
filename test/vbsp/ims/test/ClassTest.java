/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import vbsp.ims.model.ModelDsHongheo;
import vbsp.ims.model.ModelMapping;

/**
 *
 * @author LION
 */
public class ClassTest {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("vbsp.ims.model.ModelMapping");

        Method[] method = clazz.getDeclaredMethods();
        Constructor ct = clazz.getConstructor();
        Object objCls = ct.newInstance();
        System.err.println("sss="+clazz.getDeclaredMethod("setData_lenght", int.class).invoke(objCls, 123));
        for (int i = 0; i < method.length; i++) {
            Type[] type = method[i].getGenericParameterTypes();
            if (type.length > 0) {
                System.err.println("Day la phuong thuc set "+method[i].getName());
                for (int k = 0; k < method[i].getGenericParameterTypes().length; k++) {
                    System.err.println(" -- " + method[i].getGenericParameterTypes()[k]);
                    
                }
            }
//            System.err.println("objCls="+clazz.getDeclaredField("data_lenght").getInt(objCls));
            
             
             
//            else System.err.println("Day la phuong thuc get "+method[i].getName());
                
//             System.err.println("method="+method[i].invoke(objCls, args));
        }
        System.err.println("sss="+clazz.getDeclaredMethod("getData_lenght", null).invoke(objCls, null));
        Field[] field = clazz.getDeclaredFields();

        ArrayList<Object> lst = new ArrayList<>();
        lst.add(objCls);
        
        for(Object objj:lst)
        {
            ModelMapping value=(ModelMapping)objj;
            System.err.println("ssss="+value.getData_lenght());
        }
        /*
         for (int i = 0; i < field.length; i++) {
         Class clazzType = field[i].getType();
         if (clazzType.getName().toLowerCase().equals("boolean")) {
         field[i].setBoolean(objCls, true);
         }
         if (clazzType.getName().toLowerCase().equals("characters")) {
         field[i].setChar(objCls, 'a');
         }
         if (clazzType.getName().toLowerCase().equals("byte")) {
         //                field[i].setByte(objCls, );
         }
         if (clazzType.getName().toLowerCase().equals("double")) {
         field[i].setDouble(objCls, 1212.33);
         }
         if (clazzType.getName().toLowerCase().equals("float")) {
         field[i].setFloat(objCls, (float) 1212.33);
         }
         if (clazzType.getName().toLowerCase().equals("int")) {
         field[i].setInt(objCls, 123);
         }

         if (clazzType.getName().toLowerCase().equals("loog")) {
         field[i].setLong(objCls, (long) 123.2);
         }
         if (clazzType.getName().toLowerCase().equals("short")) {
         field[i].setShort(objCls, (short) 123);
         }
         if (clazzType.getName().toLowerCase().equals("string")) {
         field[i].set(objCls, "sdfdsfds");
         }
         */
//        
//        System.err.println(field);
//        
//        System.err.println("Type=" + clazzType.getName());
//
//        field.setInt(objCls, 123);
//
//        System.err.println("col_excel=" + field.getInt(objCls));
    }
}
