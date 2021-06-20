package ims.test.all;


import java.io.File;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Stack;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.lang.StringUtils;
import vbsp.ims.dao.DaoRptFormula;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import static vbsp.ims.define.DefineFun.splitFormulaString;
import vbsp.ims.export.excel.ExportExcelFile;
import vbsp.ims.model.RptFormula;
import vbsp.ims.model.ValueFormula;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author lion
 */
public class MainTest {

    /**
     *
     * @param str Chuoi dua vao la dang
     * (TK9_2_0_704_:(DDNO+PSNO-PSCO):)/2*TK9_1_0_704_:(DDNO+PSNO):-2+TK9_5_0_704_:PSNO:";//"TK9_2_0_704_:(DDNO+PSNO-PSCO)/2*TK9_1_0_704_:(DDNO+PSNO)-2
     * @param startSub Ky tu bat dau can cat dang "TK"
     * @param endSub Ky tu ket thuc can cat dang ":"
     * @return ket qua tra ra la 1 mang TK9_2_0_704_, TK9_1_0_704_,TK9_5_0_704_
     */
    public static String[] splitString(String str, String startSub, String endSub) {
        if (str.isEmpty() || startSub.isEmpty() || endSub.isEmpty()) {
            return null;
        }
        //Khoi tao mang voi so phan tu la so tai khoan duoc dua vao
        String[] ArrOut = new String[StringUtils.countMatches(str, startSub)];
        int index = 0;
        int Start = -1;
        int End = -1;
        while (true) {
            Start = str.indexOf(startSub, Start + 1);
            End = str.indexOf(endSub, Start + 1);
            if (Start < 0) {
                break;
            }
            String sCon = str.substring(Start, End);
            ArrOut[index] = sCon;
            index++;
            if (sCon.isEmpty()) {
                break;
            }
            Start = End;
        }
        return ArrOut;
    }

    /**
     *
     * @param str Chuoi dau vao la cong thuc
     * @param arrStrSub Mang cac TK da cat ra o tren
     * @return la chuoi sau khi da loai bo cac TK
     */
    public static String removeString(String str, String[] arrStrSub) {
        String strOut = str;
        if (str.isEmpty() || arrStrSub.length < 1) {
            return null;
        }
        for (String substr : arrStrSub) {
            strOut = strOut.replaceAll(substr, "");
        }
        return strOut;
    }

    public void CallMethodsProcess(Object callmyclass, String strNameMethos, int a, int b) {
        //callmyclass = new Object();
        // create a script engine manager
        ScriptEngineManager factory = new ScriptEngineManager();
        // create a JavaScript engine
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        // evaluate JavaScript code from String
        try {
            //engine.eval("println('Welcome to Java world')");
            //goi lop es vao bien es
            engine.put("callmyclass", callmyclass);
            ScriptEngineFactory sef = engine.getFactory();
            String s = sef.getMethodCallSyntax("callmyclass", strNameMethos, new String[]{"\"" + a + "\"", "\"" + b + "\"", "\"" + b + "\""});
            engine.eval(s);
            int giatri = (int) engine.eval(s);

            System.err.println(giatri);
        } catch (ScriptException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            //Global.WriteLogFile(e.getMessage());
        }
        // add the Java object into the engine.
    }

    /**
     *
     * @param strFormula Tham so truyen vao la cong thuc can kiem tra dang
     * ((6+8-9)/2*(6+8)-2+8
     * @return gia tri tra ra true cong thuc dung false cong thuc sai
     */
    public static boolean checkCaculator(String strFormula) {
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        try {
            engine.eval(strFormula);
            double giatri = (double) engine.eval(strFormula);

            System.err.println(giatri);
            return true;
        } catch (ScriptException ex) {
//            Logger.getLogger(MainTest.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    public static HashMap<String, List<String>> standardListFormula(RptFormula objFormula, HashMap<Integer, List<String>> hmArrFormula) {
        HashMap<String, List<String>> hmArrlst = new HashMap<String, List<String>>();
        if (objFormula == null || hmArrFormula.size() == 0) {
            return hmArrlst;
        }
        String sPosList = "";
        List<String> lstFormula = new ArrayList<String>();
        if (objFormula.getReport_type().equals("02")) {
            if (objFormula.getRow_column().equals("1")) {
                System.err.println("Loai bao cao theo chi nhanh");
                //List<String> lstFormula = new ArrayList<String>();
                for (int i = 0; i < hmArrFormula.size(); i++) {
                    List<String> lst = hmArrFormula.get(i);
                    //int j=0;
                    for (int j = 0; j < lst.size(); j++) {
                        //Lay ra danh sach pos
                        if (j == 0 && i != 0) {
                            String sPos = lst.get(j);
                            sPosList += sPos.substring(0, sPos.indexOf("#")) + "#";
                        }
                        if (j != 0 && i == 1) {
                            lstFormula.add(lst.get(j));
                        }
                        //System.err.println(Integer.toString(j) + " -> " + Integer.toString(j) + " -> " + lst.get(j));
                    }

                }
                // hmArrlst.put(sPosList, lstFormula);
            } else {
                System.err.println("Loai bao cao theo chi tieu");

                for (int i = 0; i < hmArrFormula.size(); i++) {
                    List<String> lst = hmArrFormula.get(i);
                    for (int j = 0; j < lst.size(); j++) {
                        if (i == 0 && j != 0) {
                            //System.err.println(lst.get(j));
                            String sPos = lst.get(j);
                            sPosList += sPos.substring(0, sPos.indexOf("#")) + "#";
                        }
                        if (i != 0 && j == 1) {
                            //System.err.println(lst.get(j));
                            lstFormula.add(lst.get(j));
                        }

                    }
                }

            }

        } else {
            System.err.println("Danh sach pos");
            //hmArrlst.put("ARR_POS_CD", null)
            for (int i = 0; i < hmArrFormula.size(); i++) {
                List<String> lst = hmArrFormula.get(i);
                for (int j = 0; j < lst.size(); j++) {

                    if (i != 0 && j != 0) {
                        lstFormula.add(lst.get(j));
                    }
                }
            }
        }
        if (sPosList.isEmpty()) {
            sPosList = "ARR_POS_CD";
        }
        hmArrlst.put(sPosList, lstFormula);
        return hmArrlst;
    }

    public static HashMap<Integer, List<String>> standardListFormulaAddPos(RptFormula objFormula, HashMap<Integer, List<String>> hmArrFormula) {
        HashMap<Integer, List<String>> hmArrlst = new HashMap<Integer, List<String>>();
        if (objFormula == null || hmArrFormula.size() == 0) {
            return hmArrlst;
        }
        String sPosList = "";
        List<String> lstFormula = new ArrayList<String>();
        HashMap<Integer, String> hmPosFormula = new HashMap<Integer, String>();
        if (objFormula.getReport_type().equals("02")) {
            //bao cao theo chi nhanh hang doc
            if (objFormula.getRow_column().equals("1")) {
                System.err.println("Loai bao cao theo chi nhanh");
                for (int i = 0; i < hmArrFormula.size(); i++) {
                    List<String> lst = hmArrFormula.get(i);
                    String PosCd_Add = "";
                    for (int j = 0; j < lst.size(); j++) {
                        //Lay ra danh sach pos
                        if (i != 0 && j == 0) {
                            String sPos = lst.get(j);
                            PosCd_Add = sPos.substring(0, sPos.indexOf("#"));
                        }
                        if (j != 0 && i != 0) {
                            lstFormula.add(lst.get(j));
                            String sFormula = PosCd_Add + "#" + lst.get(j);
                            lst.remove(j);
                            lst.add(j, sFormula);
                        }
                        //System.err.println(Integer.toString(i) + " -> " + Integer.toString(j) + " -> " + lst.get(j));
                    }
                    hmArrlst.put(i, lst);
                }
                // hmArrlst.put(sPosList, lstFormula);
            } else {
                System.err.println("Loai bao cao theo chi tieu");

                for (int i = 0; i < hmArrFormula.size(); i++) {
                    List<String> lst = hmArrFormula.get(i);
                    for (int j = 0; j < lst.size(); j++) {
                        if (i == 0 && j != 0) {
                            //System.err.println(lst.get(j));
                            String sPos = lst.get(j);
                            sPosList += sPos.substring(0, sPos.indexOf("#"));
                            hmPosFormula.put(j, sPos.substring(0, sPos.indexOf("#")));
                        }
                        if (i != 0 && j != 0) {
                            String sFormula = hmPosFormula.get(j) + "#" + lst.get(j);
                            lst.remove(j);
                            lst.add(j, sFormula);
                        }
                        //System.err.println(Integer.toString(i) + " -> " + Integer.toString(j) + " -> " + lst.get(j));
                    }
                    hmArrlst.put(i, lst);
                }

            }

        } else {
            System.err.println("Danh sach pos");
            //hmArrlst.put("ARR_POS_CD", null)
            for (int i = 0; i < hmArrFormula.size(); i++) {
                List<String> lst = hmArrFormula.get(i);
                for (int j = 0; j < lst.size(); j++) {

                    if (i != 0 && j != 0) {
                        lstFormula.add(lst.get(j));
                    }
                    //System.err.println(Integer.toString(i) + " -> " + Integer.toString(j) + " -> " + lst.get(j));
                }
                hmArrlst.put(i, lst);
            }
        }
        return hmArrlst;
    }

    public static boolean isNumeric1(String str) {
        try {
            double d = Double.parseDouble(str);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }

    public static boolean isNumeric2(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    public static boolean isNumeric(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

    public static void main(String[] args) throws Exception {
        
        String fileUploadFileName="IMG_1383.zip";
         if(!fileUploadFileName.toLowerCase().endsWith("zip"))
            {
                System.err.println("Khong dung dinh dang file");
            }
         if(!fileUploadFileName.toLowerCase().endsWith("zip")||!fileUploadFileName.toLowerCase().endsWith("xls")
                    ||!fileUploadFileName.toLowerCase().endsWith("xlsx"))
            {
                System.err.println("Khong dung dinh dang file");
            }
         if(!fileUploadFileName.toLowerCase().endsWith("zip")||!fileUploadFileName.toLowerCase().endsWith("xls")
                    ||!fileUploadFileName.toLowerCase().endsWith("xlsx"))
            {
                System.err.println("Khong dung dinh dang file");
            }
        
       String str="#1#2#3#";
//       ArrayList<String> lst=DefineFun.SplitStringToArrayList(str, "\\");
       
       String[] arr = str.split("#");
       
       List<String> lst=DefineFun.SplitStringToArrayList(str, "#");
//       lst.add("");
//    for(String strr:lst)
//        if (strr.isEmpty())
//                lst.remove(strr);
//       for (int i=0;i<arr.length;i++)
//           if(!arr[i].isEmpty())
//               lst.add(arr[i]);
        System.err.println(lst.toString());
       /*
        String sNumber ="-13,432.9788";
        System.err.println(isNumeric(sNumber));
        System.err.println(isNumeric1(sNumber));
        System.err.println(isNumeric2(sNumber));
        
        System.err.println(DefineFun.isNumeric(sNumber));
        String sKey1 = "row_data_1";
        System.err.println(sKey1.toLowerCase().startsWith("row_data"));
        String strPathSave = "D:\\" + Define.M_REPORT_XLS;
        DaoRptFormula dao = new DaoRptFormula();
        String save_id = "FORMULA0000000048";
        //String str=save_id.substring(5, 4);
        RptFormula rptObj = dao.getLoadEditFormula(save_id);

        //HashMap<Integer, List<String>> hmDataBody_tmp = dao.getLoadBodyCaculatorHashMap_tmp(save_id, rptObj);
        HashMap<Integer, List<String>> hmDataBody = dao.getLoadBodyCaculatorHashMap(save_id);

        //chuan hoa de lay ra cong thuc duy nhat cho tat ca cac pos
        HashMap<String, List<String>> hmLstFormulaByPos = DefineFun.standardListFormula(rptObj, hmDataBody);
        String sPos_cd = "";
        //Lay ra object cac cong thuc voi dieu kien
        List<ValueFormula> lstObjFormula = new ArrayList<ValueFormula>();
        for (String sKey : hmLstFormulaByPos.keySet()) {
            sPos_cd = sKey;
            List<String> lstFormula = hmLstFormulaByPos.get(sKey);
            //Cong thuc can thiet de dua vao tinh toan
            lstObjFormula = DefineFun.splitFormulaByFormula(save_id, lstFormula, 1);

        }

        if (sPos_cd.equals("ARR_POS_CD")) {
            sPos_cd = "000100";
        }
        String strDateExport = "31-mar-2015";
        String strDateExportFile;
        String strUserName = "GIANGTTT_KTTC";
        //Du lieu da duoc tinh toan ra se duoc map la:
        //POS_CD#CONGTHUC -> Giatri
        HashMap<String, String> hmDataFormula = dao.CaculatorRptFormula(save_id, lstObjFormula, strUserName, sPos_cd, strDateExport);
        //tu cong thuc load len khi luu chuan hoa them pos vao do
        //HashMap<Integer, List<String>> hmDataBody_tmp = dao.getLoadBodyCaculatorHashMap_tmp(save_id, rptObj);
        HashMap<Integer, List<String>> hmDataxls= new  HashMap<Integer, List<String>>();
        
        if(rptObj.getReport_type().equals("02") && rptObj.getRow_column().equals("1") && rptObj.getPos_cd().equals("000100"))
            hmDataxls = dao.getLoadBodyCaculatorHashMap_tmp(save_id, rptObj);
        else 
            hmDataxls = DefineFun.standardListFormulaAddPos(rptObj, hmDataBody);
        //replace cong thuc bang du lieu da luu
        for (Integer i : hmDataxls.keySet()) {
            List<String> lstData = hmDataxls.get(i);
            for (int j = 0; j < lstData.size(); j++) {
                String sData = hmDataFormula.get(lstData.get(j));
                if (sData != null) {
                    lstData.remove(j);
                    lstData.add(j, sData);
                }
                if (lstData.get(j).indexOf("#") > 0) {
                    String sRemovePos = lstData.get(j);
                    int pos = sRemovePos.indexOf("#");
                    lstData.remove(j);
                    lstData.add(j, sRemovePos.substring(pos + 1, sRemovePos.length()));
                }
//                    System.err.println("Data -> " + lstData.get(j));
            }
        }

//            for (String key:hmDataFormula.keySet())
//                System.err.println("Khoa -> "+key+" Du lieu -> "+hmDataFormula.get(key));
        //Zip file sau do cho nguoi dung tai ve
        strDateExportFile = "16032015";//new SimpleDateFormat("ddMMyyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(new Date(strDateExport)));
        Random rand = new Random();
        String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
        String filereport = save_id + "_" + strUserName + "_" + strDateExportFile + "_" + Integer.toString(rand.nextInt(10000)) + ".xlsx";
        String fileNamelocal = strPathSave + filereport;

        File fileOut = new File(fileNamelocal);
        if (fileOut.exists()) {
            System.err.println("File nay da co nen tao file moi " + fileNamelocal);
            //setMessage("Lỗi tìm thấy file dữ liệu đã xuất ra");
            //continue;
            int random = (int) (Math.random() * 50000 + 1);
            // filereport = "HSTDCT_" + strUserName + "_" + strCurrDate + "_" + strDateExportFile + "_" + Integer.toString(random) + ".zip";
            filereport = save_id + "_" + strUserName + "_" + strDateExportFile + "_" + Integer.toString(rand.nextInt(10000)) + ".xlsx";
            fileNamelocal = strPathSave + filereport;
        }
        //lay ra phan tieu de bao cao va don vi tinh
        List<String> lstTitleUntil = dao.getTitleUntil(save_id);
        ExportExcelFile expXls = new ExportExcelFile();
        File Checkpath = new File(strPathSave);

        if (!Checkpath.exists()) {
            System.out.println("Da tao thu muc: " + strPathSave);
            Checkpath.mkdirs();
        }
       
        //ghi ra file excel
        expXls.ExportFileExcelFormula(hmDataxls, lstTitleUntil.get(0), lstTitleUntil.get(1), fileNamelocal);
       */
//        
//            for (Integer key : hmArrlst.keySet()) {
//         List<String> lst = hmArrlst.get(key);
//         for(String str:lst)
//                 System.err.println("-----------------------------------------"+str);
//         }
//         
//        for (String key : hmArrlst.keySet()) {
//            List<String> lst = hmArrlst.get(key);
//            List<ValueFormula> hmOutTK = DefineFun.splitFormulaByFormula(sSave_id, lst, 4);
//            int count = 1;
//            for (ValueFormula keyobj : hmOutTK) {
////                String strData = "ARR_FORMULA(" + Integer.toString(count) + "):= TYPE_FORMULA( '" + keyobj.getsKey() + "','" + keyobj.getsFormula() + "','','" + keyobj.getsFieldData()
////                        + "','" + keyobj.getsWhereFormula() + "'," + Integer.toString(keyobj.getnOrder()) + ",'','','' ) ;";
////                System.err.println(Integer.toString(count)+key.getsKey()+" Chuoi ban dau ==>> " + 
////                        key.getsFormula() + " Key -> " + key.getsWhereFormula() + " Value -> " + key.getsFieldData());
//                String strData=keyobj.getsFormula()+" -> "+keyobj.getsFieldData()+" -> "+keyobj.getsWhereFormula();
//                System.err.println(strData);
//                count++;
//            }

//            int j = 0;
//            for (String str : lst) {
//                System.err.println(str);
//            }
        // }
//        String str = "(TK9_2_0_704_:(DDNO+PSNO-PSCO)):/2*TK9_1_0_704_:(DDNO+PSNO):-2+TK9_5_0_704_:PSNO:+CT1A014:GIATRI:-CT01345:GIATRI:";//"TK9_2_0_704_:(DDNO+PSNO-PSCO)/2*TK9_1_0_704_:(DDNO+PSNO)-2";
//        
//         DaoRptFormula daofor = new DaoRptFormula();
//        HashMap<String, Integer> hmMap = daofor.getMappingColumn();
//        
//
//        if(DefineFun.isCheckFormula(str, hmMap))
//            System.err.println("Dung cong thuc");
//        else System.err.println("sai cong thuc");
//        String findStr1 = "TK";
//        String findStr2 = ":";
//        //buoc 1 tach tai khoan ra rieng
//        String[] ArrStr = splitString(str, "TK", ":");
//        for (String astr : ArrStr) {
//            System.err.println(astr);
//        }
//
//        System.err.println(removeString(str, ArrStr));
//        System.err.println(removeString(str, ArrStr).replaceAll(":", ""));
//        //buoc 2 remove tai khoan va cac ky tu dac bien
//        String strCongthuc = removeString(str, ArrStr).replaceAll(":", "");
//
////        DaoRptFormula daofor = new DaoRptFormula();
////        HashMap<String, Integer> hmMap = daofor.getMappingColumn();
//        //buoc 3 chuyen cong thuc ve cong thuc toan hoc
//        for (String key : hmMap.keySet()) {
//            strCongthuc = strCongthuc.replaceAll(key, hmMap.get(key).toString());
//        }
//        //buoc 4 kiem tra xem cong thuc co dung hay khong
//        System.err.println(strCongthuc);
//        if(checkCaculator(strCongthuc))
//            System.err.println("Dung cong thuc");
//        else System.err.println("sai cong thuc");
        /*
         System.err.println(convertToInfix(str));
         int countTK = StringUtils.countMatches(str, findStr1);
         int countTwoDot = StringUtils.countMatches(str, findStr2);
         if (countTK == countTwoDot) {
         System.err.println("So dau : bang voi TK");
         } else {
         System.err.println("Cong thuc sai");
         }
         */
        //System.err.println(3 % 2);
    }
}
