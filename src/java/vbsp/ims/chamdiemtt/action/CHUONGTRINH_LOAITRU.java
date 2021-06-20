/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class CHUONGTRINH_LOAITRU {

    private String macn;
    public String mapgd;
    public String TenPGD;
    public String mact;
    public String tenct;
    public List<String> monthList;
    public String[] monthDefault;
    public List<ListValue> thangApdung;

    private boolean T1;
    private boolean T2;
    private boolean T3;
    private boolean T4;
    private boolean T5;
    private boolean T6;
    private boolean T7;
    private boolean T8;
    private boolean T9;
    private boolean T10;
    private boolean T11;
    private boolean T12;

    public boolean isT1() {
        return T1;
    }

    public void setT1(boolean T1) {
        this.T1 = T1;
    }

    public boolean isT2() {
        return T2;
    }

    public void setT2(boolean T2) {
        this.T2 = T2;
    }

    public boolean isT3() {
        return T3;
    }

    public void setT3(boolean T3) {
        this.T3 = T3;
    }

    public boolean isT4() {
        return T4;
    }

    public void setT4(boolean T4) {
        this.T4 = T4;
    }

    public boolean isT5() {
        return T5;
    }

    public void setT5(boolean T5) {
        this.T5 = T5;
    }

    public boolean isT6() {
        return T6;
    }

    public void setT6(boolean T6) {
        this.T6 = T6;
    }

    public boolean isT7() {
        return T7;
    }

    public void setT7(boolean T7) {
        this.T7 = T7;
    }

    public boolean isT8() {
        return T8;
    }

    public void setT8(boolean T8) {
        this.T8 = T8;
    }

    public boolean isT9() {
        return T9;
    }

    public void setT9(boolean T9) {
        this.T9 = T9;
    }

    public boolean isT10() {
        return T10;
    }

    public void setT10(boolean T10) {
        this.T10 = T10;
    }

    public boolean isT11() {
        return T11;
    }

    public void setT11(boolean T11) {
        this.T11 = T11;
    }

    public boolean isT12() {
        return T12;
    }

    public void setT12(boolean T12) {
        this.T12 = T12;
    }

    public static CHUONGTRINH_LOAITRU newInstance() {
        return new CHUONGTRINH_LOAITRU();
    }

    public String getMapgd() {
        return mapgd;
    }

    public void setMapgd(String mapgd) {
        this.mapgd = mapgd;
    }

    public String getMact() {
        return mact;
    }

    public void setMact(String mact) {
        this.mact = mact;
    }

    public String getTenct() {
        return tenct;
    }

    public void setTenct(String tenct) {
        this.tenct = tenct;
    }

    public List<String> getMonthList() {
        return monthList;
    }

    public void setMonthList(List<String> monthList) {
        this.monthList = monthList;
    }

    public void setMonthList(String month) {
        this.monthList = convertStringtoList(month);
    }

    public String[] getMonthDefault() {
        return monthDefault;
    }

    public void setMonthDefault(String[] monthDefault) {
        this.monthDefault = monthDefault;
    }

    public void setMonthDefault(List<String> monthList) {
        this.monthDefault = monthList.toArray(new String[monthList.size()]);
    }

    public void setMonthDefault(String month) {
        List<String> arr = convertStringtoList(month);
        this.monthDefault = arr.toArray(new String[arr.size()]);
    }

    public String getMacn() {
        return macn;
    }

    public void setMacn(String macn) {
        this.macn = macn;
    }

    public List<ListValue> getThangApdung() {
        thangApdung = new ArrayList<>();
        for (int i = 1; i < 13; i++) {
            String key = Integer.toString(i);

            ListValue value = new ListValue(key, "Tháng " + key);
            thangApdung.add(value);
        }
        return thangApdung;
    }

    public void setThangApdung(List<ListValue> thangApdung) {
        this.thangApdung = thangApdung;
    }

    public String getTenPGD() {
        return TenPGD;
    }

    public void setTenPGD(String TenPGD) {
        this.TenPGD = TenPGD;
    }

    public void setThangMacdinh(String thang) {
        List<String> arr = convertStringtoList(thang);
        for (String string : arr) {
            switch (string) {
                case "1":
                    setT1(true);
                    break;
                case "2":
                    setT2(true);
                    break;
                case "3":
                    setT3(true);
                    break;
                case "4":
                    setT4(true);
                    break;
                case "5":
                    setT5(true);
                    break;
                case "6":
                    setT6(true);
                    break;
                case "7":
                    setT7(true);
                    break;
                case "8":
                    setT8(true);
                    break;
                case "9":
                    setT9(true);
                    break;
                case "10":
                    setT10(true);
                    break;
                case "11":
                    setT11(true);
                    break;
                case "12":
                    setT12(true);
                    break;
            }
        }
    }

    public String getMonthString() {
        String monthString = "/";
        if (isT1()) {
            monthString += "1/";
        }
        if (isT2()) {
            monthString += "2/";
        }
        if (isT3()) {
            monthString += "3/";
        }
        if (isT4()) {
            monthString += "4/";
        }
        if (isT5()) {
            monthString += "5/";
        }
        if (isT6()) {
            monthString += "6/";
        }
        if (isT7()) {
            monthString += "7/";
        }
        if (isT8()) {
            monthString += "8/";
        }
        if (isT9()) {
            monthString += "9/";
        }
        if (isT10()) {
            monthString += "10/";
        }
        if (isT11()) {
            monthString += "11/";
        }
        if (isT12()) {
            monthString += "12/";
        }

        return monthString;
    }

    protected List<String> convertStringtoList(String month) {
        String[] value = month.split("/");
        List<String> lst = new ArrayList<>();
        try {
            for (int i = 0; i < value.length; i++) {
                if (value[i] != null && !value[i].isEmpty()) {
                    lst.add(value[i]);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
        }
        return lst;
    }

    public static void main(String[] agrs)
    {
        
        try {
            Date sdf = new SimpleDateFormat("dd-MMM-yyyy").parse("31-dec-2020");
            
            System.err.println(sdf);
        } catch (ParseException ex) {
            Logger.getLogger(CHUONGTRINH_LOAITRU.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
