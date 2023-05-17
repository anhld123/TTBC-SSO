/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.log;


import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.log4j.Logger;

public class CoreLogger
{
  public static Logger mLog = Logger.getLogger(CoreLogger.class.getName());
  public static String getCurrentTimeStamp() {
    SimpleDateFormat sdfDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");//dd/MM/yyyy
    Date now = new Date();
    String strDate = sdfDate.format(now);
    return strDate+":  ";
}
  public static void log(String paramString)
  {
    mLog.debug(paramString);
  }
  
  public static void log(int paramInt)
  {
    mLog.debug(getCurrentTimeStamp()+String.valueOf(paramInt));
  }
  
  public static void log(double paramDouble)
  {
    mLog.debug(getCurrentTimeStamp()+String.valueOf(paramDouble));
  }
  
  public static void log(long paramLong)
  {
    mLog.debug(getCurrentTimeStamp()+String.valueOf(paramLong));
  }
  
  public static void log(Object paramObject)
  {
    if (paramObject != null) {
      mLog.debug(getCurrentTimeStamp()+paramObject);
    }
  }
  
  public static void info(String paramString)
  {
    mLog.info(getCurrentTimeStamp()+paramString);
  }
  
  public static void info(int paramInt)
  {
    mLog.info(getCurrentTimeStamp()+String.valueOf(paramInt));
  }
  
  public static void info(double paramDouble)
  {
    mLog.info(getCurrentTimeStamp()+String.valueOf(paramDouble));
  }
  
  public static void info(long paramLong)
  {
    mLog.info(getCurrentTimeStamp()+String.valueOf(paramLong));
  }
  
  public static void info(Object paramObject)
  {
    if (paramObject != null) {
      mLog.info(getCurrentTimeStamp()+paramObject);
    }
  }
  
  public static void error(String paramString)
  {
    mLog.error(getCurrentTimeStamp()+paramString);
  }
  
  public static void error(int paramInt)
  {
    mLog.error(getCurrentTimeStamp()+String.valueOf(paramInt));
  }
  
  public static void error(double paramDouble)
  {
    mLog.error(getCurrentTimeStamp()+String.valueOf(paramDouble));
  }
  
  public static void error(long paramLong)
  {
    mLog.error(getCurrentTimeStamp()+String.valueOf(paramLong));
  }
  
  public static void error(Object paramObject)
  {
    if (paramObject != null) {
      mLog.error(getCurrentTimeStamp()+paramObject);
    }
  }
  
  public static void critical(String paramString)
  {
    mLog.fatal(getCurrentTimeStamp()+paramString);
  }
  
  public static void critical(int paramInt)
  {
    mLog.fatal(getCurrentTimeStamp()+String.valueOf(paramInt));
  }
  
  public static void critical(double paramDouble)
  {
    mLog.fatal(getCurrentTimeStamp()+String.valueOf(paramDouble));
  }
  
  public static void critical(long paramLong)
  {
    mLog.fatal(getCurrentTimeStamp()+String.valueOf(paramLong));
  }
  
  public static void critical(Object paramObject)
  {
    if (paramObject != null) {
      mLog.fatal(getCurrentTimeStamp()+paramObject);
    }
  }
  
  public static void warn(String paramString)
  {
    mLog.warn(getCurrentTimeStamp()+paramString);
  }
  
  public static void warn(int paramInt)
  {
    mLog.warn(getCurrentTimeStamp()+String.valueOf(paramInt));
  }
  
  public static void warn(double paramDouble)
  {
    mLog.warn(getCurrentTimeStamp()+String.valueOf(paramDouble));
  }
  
  public static void warn(long paramLong)
  {
    mLog.warn(getCurrentTimeStamp()+String.valueOf(paramLong));
  }
  
  public static void warn(Object paramObject)
  {
    if (paramObject != null) {
      mLog.warn(getCurrentTimeStamp()+paramObject);
    }
  }
  
  public static void message(String paramString)
  {
    mLog.info(getCurrentTimeStamp()+paramString);
  }
  
  public static void critical(Exception paramException)
  {
    mLog.fatal(getCurrentTimeStamp()+paramException.getMessage(), paramException);
  }
  
  public static void debug(Object paramObject, Throwable paramThrowable)
  {
    mLog.debug(getCurrentTimeStamp()+paramObject, paramThrowable);
  }
  
  public static void debug(String paramString)
  {
    mLog.debug(getCurrentTimeStamp()+paramString);
  }
  
  public static void error(Object paramObject, Throwable paramThrowable)
  {
    mLog.error(getCurrentTimeStamp()+paramObject, paramThrowable);
  }
  
  static {}
}
