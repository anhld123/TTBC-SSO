/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.test;

/**
 *
 * @author LION
 */
import java.lang.reflect.Field;

public class Main {

    public static void main(String[] args) throws Exception {
        Object clazz = new TestClass();
        String lookingForValue = "firstValue";
        System.err.println("giatri ="+clazz.getClass().getField(lookingForValue));
        Field field = clazz.getClass().getField(lookingForValue);
        Class clazzType = field.getType();
        if (clazzType.toString().equals("double")) {
            System.out.println(field.getDouble(clazz));
            field.setDouble(clazz,  12345.9655);
        } else if (clazzType.toString().equals("int")) {
            System.out.println(field.getInt(clazz));
            field.setInt(clazz, 100);
        }

        System.out.println(field.get(clazz));
    }
}

class TestClass {

    public double firstValue = 3.14;
    public double giatri=123.88;
}
