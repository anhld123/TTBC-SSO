/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author HP
 */
public class DateUtil {

    public static Date stringToDate(String value, String format) {
        try {
            SimpleDateFormat formatter = new SimpleDateFormat(format);
            Date dateValue = formatter.parse(value);
            return dateValue;
        } catch (Exception e) {
            CoreLogger.error("Error ~ stringToDate: " + e.getMessage());            
        }
        return null;
    }
    
    public static Date toDate(String value) {
        return stringToDate(value, "yyyy-MM-dd'T'HH:mm:ss");
    }
    
    public static String dateToString(Date value, String format)
    {
        SimpleDateFormat formatter = new SimpleDateFormat(format);  
        String strDate = formatter.format(value);  
        return strDate;
    }
}
