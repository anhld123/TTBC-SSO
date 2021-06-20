/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vbsp.ims.nhaptaycn.action;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.dao.DaoRptQuery;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class COVID_GIAINGAN extends ActionNhaptaycnMain 
        
implements NhaptaycnFunction{
    
    @Override
    public String load(){
       try {
            System.err.println("COV_GIAINGAN");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();            
                lstDulieuNt = daoMain.getDataC0Vid02(conn, khoa_nhaptaycn, hmParameter.get("ngay_bc").toString(),UserName, Grade,poscd, hmParameter.get("user_id").toString(), hmParameter.get("nha_dt").toString() );
                setLstCBKetoan(daoMain.getCanBo(UserName,"GN"));    //Giải ngân: 
                setLstCBTindung(daoMain.getCanBo(UserName,"TT"));   //Hình thức TT: TK vbsp, citad, tiền mặt
            if (conn != null) {
                conn.close();
            }                    
            return SUCCESS;            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COV_GIAINGAN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COV_GIAINGAN: " + e.getMessage());
        }        
        return SUCCESS;
    }
    

    @Override
    public String save() {
        System.err.println("Save - COV_GIAINGAN");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }         
            
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();    
            HashMap hmParameter = getParameter();                                             
            
             if(!daoMain.saveCoVid02(khoa_nhaptaycn, UserName, "",hmParameter.get("ngay_bc").toString(), lstDulieuNt, hmParameter.get("user_id").toString()))
            {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            } 
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> COV_GIAINGAN: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> COV_GIAINGAN: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }     
            
    public String ExpExcel() throws Exception {
        DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain(); 
        if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
        String sPOS_CD = daoMain.getPosCd(UserName);
        String save_id = "COVID_GIAINGAN";
        if (save_id == null | save_id.isEmpty()) {
            setMessage("Bạn chưa chọn mẫu báo cáo nên không thể tạo báo cáo ");
            return ERROR;
        }

        HashMap<String, String> paramHashMap = new HashMap<>();
        request = ServletActionContext.getRequest();
        Map<String, String[]> prameters = request.getParameterMap();
        String sPos_cd = "";
        String stringParaPos_cd = "";
        String sPosFlag = "";
        //xy lay lay cac tham so cho vao hashmap
        Map mapCollectPara = new HashMap();

        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);
            //Do neu parameter kieu date thi he thong se sinh them control dojo.date
            //   nen minh can phai loai bo tham so nay di
            if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0
                    || parameter.indexOf("LIST") > 0 || parameter.indexOf("NUMB") > 0) {
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));

                    mapCollectPara.put(parameter.substring(0, parameter.length() - 5),
                            ImsFillParaMeter.newInstance("VARCHAR2", new SimpleDateFormat("dd-MMM-yyyy").format(sdf)));
                    //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                } else if (parameter.indexOf("NUMB") > 0) {
                    if (parameter.indexOf("_NUMB") > 0) {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);

                        mapCollectPara.put(parameter.substring(0, parameter.length() - 5),
                                ImsFillParaMeter.newInstance("NUMBER", values[0]));
                    }
                } else {
                    if (parameter.indexOf("_MAPGD") > 0) {
                        sPos_cd = values[0].trim();
                        stringParaPos_cd = parameter.substring(0, parameter.length() - 11);

                        mapCollectPara.put(parameter.substring(0, parameter.length() - 11),
                                ImsFillParaMeter.newInstance("VARCHAR2", values[0]));
                    } else if (parameter.indexOf("_TONGHOP") > 0) {
                        sPosFlag = values[0].trim();
                        mapCollectPara.put(parameter.substring(0, parameter.length() - 5),
                                ImsFillParaMeter.newInstance("VARCHAR2", values[0]));
                    } else {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                        mapCollectPara.put(parameter.substring(0, parameter.length() - 5),
                                ImsFillParaMeter.newInstance("VARCHAR2", values[0]));
                    }
                    //System.err.println( parameter.substring(0, parameter.length() - 5)+" Tham so: "+values[0]);
                }
            }
        }
        paramHashMap.put("PV_POS_CD", sPOS_CD);
        mapCollectPara.put("PV_POS_CD", sPOS_CD);

        //xu ly cho export file ra PDF hoac la Excel
        String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
        //duong dan chua file tren o dia + Define.M_REPORT_XLS
        String strPathSave = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");;
        //Ham nay lay ra ten file bao cao can tao, ten file jasper report

        String strTimeFile = Long.toString(System.currentTimeMillis());
        String strFileSave = save_id + "_"
                + "_" + strCurrDate
                + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

        strPathSave += Define.M_REPORT_XLS;
        strFileSave += ".XLSX";
        filereport = strFileSave;
        File Checkpath = new File(strPathSave);
        if (!Checkpath.exists()) {
            System.out.println("Da tao thu muc: " + strPathSave);
            Checkpath.mkdirs();
        }
        //Xuat file du lieu o day

//        DaoRptQuery daoQuery = new DaoRptQuery();

        setQuery(daoMain.getQuery(save_id, new DaoConnect().getConnect()));
        ImsPlSqlQuery plsql = new ImsPlSqlQuery();
        if (plsql.isOracleStoredProcedure(query)) { //neu la procedure thi chay rieng
            daoMain.exportExcelQueryPlSql(mapCollectPara, save_id, strPathSave + strFileSave);
        } else {
            //Xuat file du lieu o day
//            DaoRptQuery daoQuery = new DaoRptQuery();
            if (sPos_cd.isEmpty() || stringParaPos_cd.isEmpty()) {
                daoMain.exportExcelQuery(paramHashMap, save_id, strPathSave + strFileSave);
            } else {
                daoMain.getDataExp(save_id, paramHashMap, sPos_cd, stringParaPos_cd, sPosFlag, strPathSave + strFileSave);
            }
        }
        //Kiem tra xem file da tao thanh cong chua
        File filerpt = new File(strPathSave + strFileSave);
        if (!filerpt.exists()) {
            setMessage("Lỗi bạn chưa tạo được file báo cáo " + strFileSave);
            return ERROR;
        }
        fileNamelocal = strPathSave + strFileSave;
        System.gc();
        return SUCCESS;
    }
    
    
}
