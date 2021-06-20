/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.sbv;

import static com.opensymphony.xwork2.Action.ERROR;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;

/**
 *
 * @author Administrator
 */
public class SBV_043_CSTT extends actionMainSbv implements sbvInterface {

    private String ngay_bc_DATE;
    private String genhead;
    private int dayofmonth;
    private String str01, str02;
    private int kybc;
    private List<QT_DULIEU_NT> lstData = new ArrayList<>();

    public SBV_043_CSTT() {
    }

    public String load() {
        try {
            dayofmonth = 0;
            kybc = 0;
            //------------------------------------------------------------
            String[] ArayDate = ngay_bc_DATE.split("/");
            int Day = Integer.parseInt(ArayDate[0]);
            int Month = Integer.parseInt(ArayDate[1]);
            int Year = Integer.parseInt(ArayDate[2]);
            // -----------------------------------------------------------
            kybc = (int) Math.ceil(Day / 10.0);
            if(kybc>3) 
                kybc=3;
                    
            str01 = "";
            str02 = "";
            // -----------------------------------------------------------
            switch (Month) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dayofmonth = 31;
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dayofmonth = 30;
                    break;
                case 2:
                    if (Year % 400 == 0 || (Year % 4 == 0 && Year % 100 != 0)) {
                        dayofmonth = 29;
                    } else {
                        dayofmonth = 28;
                    }
                    break;
            }
            // -----------------------------------------------------------      
            genhead = "";
            switch (kybc) {
                case 1:
                    for (int i = 1; i <= 11; i++) {
                        if (i == 11) {
                            genhead = genhead + "<td>" + GetGomonth(ArayDate[0], ArayDate[1], ArayDate[2]) + "</td>";
                        } else {
                            genhead = genhead + "<td>" + i + "/" + Month + "/" + Year + "</td>";
                        }
                    }
                    break;
                case 2:
                    for (int i = 11; i <= 21; i++) {
                        if (i == 21) {
                            genhead = genhead + "<td>" + GetGomonth(ArayDate[0], ArayDate[1], ArayDate[2]) + "</td>";
                        } else {
                            genhead = genhead + "<td>" + i + "/" + Month + "/" + Year + "</td>";
                        }
                    }
                    break;
                default:
                    switch (dayofmonth) {
                        case 28:
                            for (int i = 21; i <= 30; i++) {
                                if (i != 29 && i != 30) {
                                    genhead = genhead + "<td>" + i + "/" + Month + "/" + Year + "</td>";
                                } else {
                                    genhead = genhead + "<td>Không xác định</td>";
                                }
                            }
                            genhead = genhead + "<td>" + GetGomonth(ArayDate[0], ArayDate[1], ArayDate[2]) + "</td>";
                            break;
                        case 29:
                            for (int i = 21; i <= 30; i++) {
                                if (i != 30) {
                                    genhead = genhead + "<td>" + i + "/" + Month + "/" + Year + "</td>";
                                } else {
                                    genhead = genhead + "<td>Không xác định</td>";
                                }
                            }
                            genhead = genhead + "<td>" + GetGomonth(ArayDate[0], ArayDate[1], ArayDate[2]) + "</td>";
                            break;
                        case 30:
                            for (int i = 21; i <= 30; i++) {
                                genhead = genhead + "<td>" + i + "/" + Month + "/" + Year + "</td>";
                            }
                            genhead = genhead + "<td>" + GetGomonth(ArayDate[0], ArayDate[1], ArayDate[2]) + "</td>";
                            break;
                        case 31:
                            for (int i = 21; i <= 30; i++) {
                                genhead = genhead + "<td>" + i + "/" + Month + "/" + Year + "</td>";
                            }
                            genhead = genhead + "<td>" + GetGomonth(ArayDate[0], ArayDate[1], ArayDate[2]) + "</td>";
//                            str01 = "<td>+/-</td>";
//                            str02 = "<td style=\"width:15px;\">(14)</td>";
                            break;
                    }

                    break;
            }
            // Lấy số liệu theo POS, KYBC, NAMBC
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmPara = getParameter();
            ngay_bc = hmPara.get("ngay_bc").toString();
            Connection conn = new DaoConnect().getConnect();
            //-------------------------------------
            lstData = new ArrayList<>();
            CallableStatement cs = conn.prepareCall("{call IMS_SBV.SP_LOAD_SBV_043_CSTT(?,?,?,?)}");
            cs.setString(1, ngay_bc);
            cs.setString(2, UserName);
            cs.setString(3, Grade);
            cs.registerOutParameter(4, oracle.jdbc.OracleTypes.CURSOR);
            cs.execute();
            ResultSet rs = null;
            rs = (ResultSet) cs.getObject(4);
            while (rs.next()) {
                QT_DULIEU_NT obj = new QT_DULIEU_NT();
                obj.setTHUTU(rs.getInt("THUTU"));
                obj.setMA(rs.getString("MA"));
                obj.setTT_HIENTHI(rs.getString("TT_HIENTHI"));
                obj.setTEN(rs.getString("TEN"));
                obj.setD1(rs.getString("D1"));
                obj.setD2(rs.getString("D2"));
                obj.setD3(rs.getString("D3"));
                obj.setD4(rs.getString("D4"));
                obj.setD5(rs.getString("D5"));
                obj.setD6(rs.getString("D6"));
                obj.setD7(rs.getString("D7"));
                obj.setD8(rs.getString("D8"));
                obj.setD9(rs.getString("D9"));
                obj.setD10(rs.getString("D10"));
                obj.setD11(rs.getString("D11"));
                obj.setD12(rs.getString("D12"));
                obj.setD29(rs.getString("D29"));
                obj.setD30(rs.getString("D30"));
                obj.setKIEUIN(rs.getInt("KIEUIN"));
                obj.setD20(rs.getString("D20"));
                lstData.add(obj);
            }
            if (conn != null) {
                conn.close();
            }
            return "success";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SBV_043_CSTT: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SBV_043_CSTT: " + e.getMessage());
            return "error";
        }
    }

    @Override
    public String save() {
        try {
            // Để lưu được dữ liệu cần chuyền vào các biến
            // Ngày báo cáo , User, Cấp báo cáo, Kỳ báo cáo, số ngày của tháng
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap<String, Object> hmPara = getParameter();
            ngay_bc = hmPara.get("ngay_bc").toString();
            Connection conn = new DaoConnect().getConnect();
            //-------------------------------------
            Object array[] = lstData.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor(QT_DULIEU_NT.ORACLE_TABLE_TYPE, conn);
            ARRAY array_to_pass = new ARRAY(des, conn, array);
            //-------------------------------------
            CallableStatement cs = conn.prepareCall("{call IMS_SBV.SP_SAVE_SBV_043_CSTT(?,?,?,?,?,?)}");
            cs.setString(1, ngay_bc);
            cs.setString(2, UserName);
            cs.setString(3, Grade);
            cs.setDouble(4, kybc);
            cs.setInt(5, dayofmonth);
            cs.setArray(6, array_to_pass);
            cs.execute();
            if (conn != null) {
                conn.close();
            }
            addActionMessage("Bạn đã lưu dữ liệu thành công!");
            return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SBV_043_CSTT: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SBV_043_CSTT: " + e.getMessage());
            return ERROR;
        }
    }

    public String getNgay_bc_DATE() {
        return ngay_bc_DATE;
    }

    public void setNgay_bc_DATE(String ngay_bc_DATE) {
        this.ngay_bc_DATE = ngay_bc_DATE;
    }

    public String getGenhead() {
        return genhead;
    }

    public void setGenhead(String genhead) {
        this.genhead = genhead;
    }

    public String getStr01() {
        return str01;
    }

    public void setStr01(String str01) {
        this.str01 = str01;
    }

    public String getStr02() {
        return str02;
    }

    public void setStr02(String str02) {
        this.str02 = str02;
    }

    public List<QT_DULIEU_NT> getLstData() {
        return lstData;
    }

    public void setLstData(List<QT_DULIEU_NT> lstData) {
        this.lstData = lstData;
    }

    public int getKybc() {
        return kybc;
    }

    public void setKybc(int kybc) {
        this.kybc = kybc;
    }

    public int getDayofmonth() {
        return dayofmonth;
    }

    public void setDayofmonth(int dayofmonth) {
        this.dayofmonth = dayofmonth;
    }

    //Hàm thực hiện trả về Ngày của tháng tiếp theo
    public static String GetGomonth(String sDay, String sMonth, String sYear) {
        //Date dateold = new Date();
        String Result = "";
        int monthNEW = 0, dayNew = 0, yearNew = 0;
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat formatVN = new SimpleDateFormat(pattern);
        Date dateOLD;
        try {
            dateOLD = formatVN.parse(sDay + "/" + sMonth + "/" + sYear);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dateOLD);
            cal.add(Calendar.MONTH, 1);
            Date dateNEW = cal.getTime();

            Calendar calNEW = Calendar.getInstance();
            calNEW.setTime(dateNEW);

            monthNEW = calNEW.get(Calendar.MONTH) + 1; // Note: zero based!
            yearNew = calNEW.get(Calendar.YEAR);
            if (formatVN.format(dateOLD).equals(vbsp.ims.define.DefineFun.getLastDayOfMonth(formatVN.format(dateOLD), pattern, pattern))) // If last day of month
            {
                Result = "21/" + Integer.toString(monthNEW) + "/" + Integer.toString(yearNew);
            } else if (sDay.equals("10")) {
                Result = "01/" + Integer.toString(monthNEW) + "/" + Integer.toString(yearNew);
            } else {
                Result = "11/" + Integer.toString(monthNEW) + "/" + Integer.toString(yearNew);
            }
            //Thực hiện xét xem Ngày 1 tháng tiếp theo có roi vào ngày Thứ 7, Chủ nhật hoặc ngày lễ tết không?
            Date dateNew;
            dateNew = formatVN.parse(Result);
            Calendar CalDateNEW = Calendar.getInstance();
            CalDateNEW.setTime(dateNew);
            if (CalDateNEW.get(Calendar.MONTH) == Calendar.JANUARY && CalDateNEW.get(Calendar.DATE) == 1) {
                CalDateNEW.add(Calendar.DATE, -1);
            }
            if (CalDateNEW.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                CalDateNEW.add(Calendar.DATE, -1);
            }
            if (CalDateNEW.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                CalDateNEW.add(Calendar.DATE, -1);
            }
            if (CalDateNEW.get(Calendar.MONTH) == Calendar.JANUARY && CalDateNEW.get(Calendar.DATE) == 1) {
                CalDateNEW.add(Calendar.DATE, -1);
            }
            if (CalDateNEW.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
                CalDateNEW.add(Calendar.DATE, -1);
            }
            if (CalDateNEW.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
                CalDateNEW.add(Calendar.DATE, -1);
            }
            Result = Integer.toString(CalDateNEW.get(Calendar.DATE)) + "/" + Integer.toString(CalDateNEW.get(Calendar.MONTH) + 1) + "/" + Integer.toString(CalDateNEW.get(Calendar.YEAR));
        } catch (ParseException ex) {
            Logger.getLogger("SBV_043_CSTT").log(Level.SEVERE, null, ex);
        }
        return Result;
    }

}
