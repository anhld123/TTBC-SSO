package vbsp.ims.define;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.text.CharacterIterator;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.apache.commons.lang.StringUtils;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.RptFormula;
import vbsp.ims.model.ValueFormula;

public class DefineFun {

    public static boolean isValid(final String stringRegex) {
        if (stringRegex == null||stringRegex.isEmpty()) {
            return true;
        }
        String REGEX_STRING = "^[0-9A-Za-z.()_ \\-\\s]{0,200}[0-9A-Za-z.()_ \\-\\s]$";
        Pattern pattern = Pattern.compile(REGEX_STRING);
        Matcher matcher = pattern.matcher(stringRegex);
        return matcher.matches();
    }

    public static boolean isFileExcel(String fullPath) {
        if (!fullPath.toLowerCase().contains(".xls")) {
            return false;
        }
        return true;
    }

    public static boolean isPathDownloadFile(String path) {
        if (!path.contains("IMS_REPORTS")) {
            return false;
        }
        return true;
    }

    /* 
     Hàm lấy nhân sinh khoá cho thủ tục mã hoá DES 
     type = "1" - Không sinh chuỗi ngẫu nhiên sau đoạn mã
     type = "2" - Sinh chuỗi ngẫu nhiên sau đoạn mã
     Edit by TRUNGNT88
     Edit on 19/05/2014
     */
    public static String getKeyDes(String type) {
        String sKeyOut;
        if (type.equals("1")) {
            sKeyOut = "@123VbspSystemInfomation";
        } else {
            sKeyOut = "@123VbspSystemInfomation";
            String sRandom = "";
            for (int i = 'a'; i <= 'z'; i++) {
                int ascii = (int) i;
                if (ascii % 6 == 0) {
                    sRandom += Integer.toString(i);
                }
            }
            sKeyOut = sKeyOut + sRandom;
        }
        return sKeyOut + "   ";
    }

    public static String converArrayList2String(List<String> lstInput) {
        String strOut = "";
        if (lstInput.isEmpty()) {
            return null;
        }
        for (String sData : lstInput) {
            strOut += (sData == null || sData.isEmpty()) ? " #" : sData + "#";
            //strOut += sData+"#";
        }
//    return strOut.substring(0,strOut.length()-1);
        return strOut;
    }

    public static String FormatNumber(BigDecimal bInput) {
        if (bInput == null) {
            bInput = new BigDecimal(BigInteger.ZERO);
        }

        NumberFormat format = NumberFormat.getInstance(Locale.US);
        return format.format(bInput);
    }

    /* 
     Hàm cắt chuỗi ký tự thành mảng        
     Create by TRUNGNT88        
     */

    public static List<String> string2Array(String listString, String splitCharacter, int type) {
        /*type = 1, loai bo ky tu trang
         type = 2, khong loai bo ky tu trang*/
        List<String> stringArrayList = new ArrayList<>();
        String[] stringArray = listString.split(splitCharacter);
        for (String lcstringArray : stringArray) {
            if (type == 1) {
                if (!lcstringArray.trim().isEmpty()) {
                    stringArrayList.add(lcstringArray.trim());
                }
            } else {
                stringArrayList.add(lcstringArray.trim());
            }
        }
        return stringArrayList;
    }

    /*
     * Nguoi tao: tungnv
     * ham nay lay ra ten cua file bo di phan cuoi dinh dang cua file
     * muc dic ham nay la dung de lay ten khi tao report
     */
    public static String getNameFromFile(String strFileName) {
        String strNameFile;
        File scrFile = new File(strFileName);
        String strFileNameSource = scrFile.getName();
        System.err.println(strFileNameSource);
        int pos = strFileNameSource.indexOf('.');
        strNameFile = strFileNameSource.substring(0, pos);
        return strNameFile;
    }

    /*
     * Nguoi tao: trungnt88
     * ham chuyen doi chuoi dinh dang ngay Java sang oracle
     */
    public static String convert2OracleDateFormat(String javaDateFormat) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date date = sdf.parse(javaDateFormat);
            SimpleDateFormat newSdf = new SimpleDateFormat("dd-MMM-yyyy");
            return newSdf.format(date);
        } catch (ParseException ex) {
            return null;
        }
    }

    public static String searchInTreeView(String id, TreeNode root) {
        if (root.getId().trim().equals(id.trim())) {
            HashMap data = (HashMap) root.getData();
            return (String) data.get("Pos_Code");
        } else {
            if (root.getChildren() != null && root.getChildren().size() > 0) {
                String pos_cd = "999999";
                java.util.Iterator<TreeNode> nodeChilds = root.getChildren().iterator();
                while (nodeChilds.hasNext()) {
                    TreeNode child = nodeChilds.next();
                    if (child.getId().trim().equals(id.trim())) {
                        HashMap data = (HashMap) child.getData();
                        pos_cd = (String) data.get("Pos_Code");
                        break;
                    } else {
                        pos_cd = DefineFun.searchInTreeView(id, child);
                    }
                    if (!pos_cd.equals("999999")) {
                        break;
                    }
                }
                return pos_cd;
            } else {
                return "999999";
            }
        }
    }

    public static ArrayList<String> SplitStringToArrayList(String sInputData, String sMark) {
        if (sInputData.isEmpty() || sMark.isEmpty()) {
            return null;
        }
        ArrayList<String> ArrlstOut = new ArrayList<String>();
        //sInputData=sInputData.substring(1);
        ArrayList<String> ArrlstOuttmp = new ArrayList<String>(Arrays.asList(sInputData.split(sMark)));
        for (String item : ArrlstOuttmp) {
            if (item == null || item.isEmpty() || item.equals(" ")) {
                continue;
            } else {
                ArrlstOut.add(item);
            }
        }

        return ArrlstOut;
    }

    /* 
     Hàm lấy tên cân đối dựa vào tham số truyền vào là loại cân đối 
     Người viết: TrungNT88
     Tham số: 
     01 - Cân đối SBV nội bảng
     02 - Cân đối SBV ngoại bảng
     03 - Cân đối GL nội bảng
     04 - Cân đối GL ngoại bảng
     */

    public static String getBalanceSheetReportId(String balanceType) {
        int lnBalanceType = Integer.parseInt(balanceType);
        String balanceFileName = "";
        switch (lnBalanceType) {
            case 1:
                balanceFileName = "BC00220003";
                break;
            case 2:
                balanceFileName = "BC00220004";
                break;
            case 3:
                balanceFileName = "BC00220001";
                break;
            case 4:
                balanceFileName = "BC00220002";
                break;
        }
        return balanceFileName;
    }

    public static boolean match(String searchString, String regex) {
        boolean isMatched = false;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(searchString);
        while (m.find()) {
            isMatched = true;
            break;
        }
        return isMatched;
    }

    public static String convertString2Regex(String searchString) {
        String regex;
        regex = "\\A" + searchString.replace("?", "[0-9]");
        return regex;
    }

    //Ham nay list tat ca cac file trong folder ke ca cac file trong thu muc con
    public static List<File> listfile(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        //resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                // System.out.println(file.getAbsolutePath());
                resultList.add(file);
            } else if (file.isDirectory()) {
                resultList.addAll(listfile(file.getAbsolutePath()));
            }
        }
        //System.out.println(fList);
        return resultList;
    }

    //Ham nay scan folder va lay ra cac file xml de gui lai ve tw
    public static List<File> listfilexml(String directoryName) {
        File directory = new File(directoryName);

        List<File> resultList = new ArrayList<File>();

        // get all the files from a directory
        File[] fList = directory.listFiles();
        //resultList.addAll(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                // System.out.println(file.getAbsolutePath());
                resultList.add(file);
            }
//            else 
//                if (file.isDirectory()) {
//                resultList.addAll(listfile(file.getAbsolutePath()));
//            }
        }
        //System.out.println(fList);
        return resultList;
    }

    public static String convertStrDateFormat(String dateStr, String old_format, String new_format)
            throws ParseException {
        String newDateString;
        SimpleDateFormat sdf = new SimpleDateFormat(old_format);
        Date d = sdf.parse(dateStr);
        sdf.applyPattern(new_format);
        newDateString = sdf.format(d);
        return newDateString;
    }

    public static String round_up(Double r) {
        BigDecimal bd = new BigDecimal(r);
        bd = bd.setScale(1, BigDecimal.ROUND_UP);
        r = bd.doubleValue();
        return r.toString();
    }

    public static String getFirstDayOfQuater(String dateStr, String inFormatStr, String outFormatStr) {
        try {
            SimpleDateFormat inFormatter = new SimpleDateFormat(inFormatStr);
            Date date = inFormatter.parse(dateStr);
            DateRange quaterDateRange = DateUtils.getQuarterToDate(date);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(quaterDateRange.getStart());
            Date lastDayOfQuater = calendar.getTime();
            DateFormat outFormatter = new SimpleDateFormat(outFormatStr);
            return outFormatter.format(lastDayOfQuater);
        } catch (ParseException ex) {
            Logger.getLogger(DefineFun.class.getName()).log(Level.SEVERE, null, ex);
            return "dd-mmm-yyyy";
        }
    }

    public static String getFirstDayOfYear(String dateStr, String inFormatStr, String outFormatStr) {
        try {
            SimpleDateFormat inFormatter = new SimpleDateFormat(inFormatStr);
            Date date = inFormatter.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_YEAR, 1);
            Date lastDayOfMonth = calendar.getTime();
            DateFormat outFormatter = new SimpleDateFormat(outFormatStr);
            return outFormatter.format(lastDayOfMonth);
        } catch (ParseException ex) {
            Logger.getLogger(DefineFun.class.getName()).log(Level.SEVERE, null, ex);
            return "dd-mmm-yyyy";
        }
    }

    public static String getFirstDayOfMonth(String dateStr, String inFormatStr, String outFormatStr) {
        try {
            SimpleDateFormat inFormatter = new SimpleDateFormat(inFormatStr);
            Date date = inFormatter.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            Date lastDayOfMonth = calendar.getTime();
            DateFormat outFormatter = new SimpleDateFormat(outFormatStr);
            return outFormatter.format(lastDayOfMonth);
        } catch (ParseException ex) {
            Logger.getLogger(DefineFun.class.getName()).log(Level.SEVERE, null, ex);
            return "dd-mmm-yyyy";
        }
    }

    public static String getLastDayOfMonth(String dateStr, String inFormatStr, String outFormatStr) {
        try {
            SimpleDateFormat inFormatter = new SimpleDateFormat(inFormatStr);
            Date date = inFormatter.parse(dateStr);
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(date);
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
            calendar.add(Calendar.DATE, -1);
            Date lastDayOfMonth = calendar.getTime();
            DateFormat outFormatter = new SimpleDateFormat(outFormatStr);
            return outFormatter.format(lastDayOfMonth);
        } catch (ParseException ex) {
            Logger.getLogger(DefineFun.class.getName()).log(Level.SEVERE, null, ex);
            return "dd-mmm-yyyy";
        }
    }

    public static String replaceStr(String input, int index, String replace_val) {
        String returnStr;
        returnStr = input.substring(0, index - 1) + replace_val + input.substring(index);
        return returnStr;
    }

    public static Timestamp convertStringToTimestamp(String strInput, String sFormat) {
        java.sql.Timestamp timestamp = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
            java.util.Date parsedDate;
            parsedDate = dateFormat.parse(strInput);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(DefineFun.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error("convertStringToTimestamp(String strInput, String sFormat) " + ex.getMessage());
        }

        return timestamp;
    }

    public static Timestamp convertStringToTimestamp(String strInput) {
        if (strInput == null || strInput.isEmpty()) {
            return new Timestamp(new Date().getTime());
        }
        String sFormat = "dd/MM/yyyy hh:mm:ss";
        java.sql.Timestamp timestamp = null;
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat(sFormat);
            java.util.Date parsedDate;
            parsedDate = dateFormat.parse(strInput);
            timestamp = new java.sql.Timestamp(parsedDate.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(DefineFun.class.getName()).log(Level.SEVERE, null, ex);
            CoreLogger.error("convertStringToTimestamp(String strInput) " + ex.getMessage());
        }

        return timestamp;
    }

    /**
     * Nguoi tao tungnv
     *
     * @param str
     * @return Hàm này kiểm tra 1 chuối có là số hay không nếu là số trả về true
     * không là số trả về false
     */
    public static boolean isNumeric(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }
        return str.matches("-?\\d+(\\.\\d+)?");  //dấu - đằng trước là số âm.
    }

    //<editor-fold defaultstate="collapsed" desc="cac ham cho bao cao theo cong thuc">
    /**
     *
     * @param ArrIn mang truyen vao gom cac tai khoan, chieu tieu da cat
     * @param sInput cong thuc da cat ra tung lan
     * @return gia tri tra la la false khi chua ton tai, true khi da ton tai
     * @throws Exception
     */
    public static boolean isCheckStringInArr(String[] ArrIn, String sInput) throws Exception {

        if (ArrIn.length == 0 || ArrIn == null || sInput.isEmpty() || sInput == null) {
            return false;
        }
        for (String str : ArrIn) {
            if (str == null || str.isEmpty()) {
                continue;
            }
            if (str.toUpperCase().equals(sInput.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param str Chuoi dua vao la dang
     * (TK9_2_0_704_:(DDNO+PSNO-PSCO):)/2*TK9_1_0_704_:(DDNO+PSNO):-2+TK9_5_0_704_:PSNO:";//"TK9_2_0_704_:(DDNO+PSNO-PSCO)/2*TK9_1_0_704_:(DDNO+PSNO)-2
     * @param startSub Ky tu bat dau can cat dang "TK"
     * @param endSub Ky tu ket thuc can cat dang ":"
     * @return ket qua tra ra la 1 mang TK9_2_0_704_, TK9_1_0_704_,TK9_5_0_704_
     */
    public static String[] splitString(String str, String startSub, String endSub) throws Exception {
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
            if (End < Start) {
                break;
            }
            String sCon = str.substring(Start, End);
            //Cho nay kiem tra xem tai khoan nay da co hay chua
            //neu co roi thi se khong them vao nua
            if (!isCheckStringInArr(ArrOut, sCon)) {
                ArrOut[index] = sCon;
            } else {
                ArrOut[index] = sCon;
            }
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
//        System.err.println("Cong thuc trong ham removeString "+ str+" ");
        if (str.isEmpty()) {
            return "";
        }
        if (arrStrSub == null || arrStrSub.length < 1) {
            return str;
        }
        for (String substr : arrStrSub) {
            if (substr == null || substr.isEmpty()) {
                continue;
            } else {
                strOut = strOut.replaceAll(substr, "");
            }
        }
        return strOut;
    }

    public static boolean isOperator(String sFormula) {
        String sFormulaTmp = sFormula;
        if (sFormula == null) {
            return false;
        }
        for (int i = 0; i < Define.VALID_OPERATORS.length; i++) {
            char ch = Define.VALID_OPERATORS[i];
            sFormulaTmp = sFormulaTmp.replace(ch, ' ');
        }
        sFormulaTmp = sFormulaTmp.replace(" ", "");
        return sFormulaTmp.isEmpty();
    }

    /**
     *
     * @param strFormula Tham so truyen vao la cong thuc can kiem tra dang
     * ((6+8-9)/2*(6+8)-2+8
     * @return gia tri tra ra true cong thuc dung false cong thuc sai
     */
    public static boolean checkCaculator(String strFormula) {
        if (!isOperator(strFormula)) {
            return false;
        }
        ScriptEngineManager factory = new ScriptEngineManager();
        ScriptEngine engine = factory.getEngineByName("JavaScript");
        try {
            engine.eval(strFormula);
            double giatri = (double) engine.eval(strFormula);

//            System.err.println(giatri);
            return true;
        } catch (ScriptException ex) {
            System.err.println(ex.getMessage());
            CoreLogger.error(" checkCaculator -> " + ex.getMessage());
            return false;
        }
    }

    public static boolean isCheckFormula(String sFormula, HashMap<String, Integer> hmMapColumn) throws Exception {
        //try {
        //Dem so dau & khi cau hinh
        int countAnd = StringUtils.countMatches(sFormula, Define.SPACE_STRING);

        if (countAnd % 2 != 0 || countAnd < 2) {
            return false;
        }
        //Buoc 1 tu cong thuc truyen vao tach rieng tai khoan va chi tieu ra
        //Tach tai khoan
        String[] ArrTK = splitString(sFormula, "TK", Define.SPACE_STRING);
//            for (String astr : ArrTK) {
//                System.err.println(astr);
//            }
        //Tach chi tieu
        String[] ArrCT = splitString(sFormula, "CT", Define.SPACE_STRING);
//            for (String astr : ArrCT) {
//                System.err.println(astr);
//            }

        //Buoc 2: Remove tai khoan va cac cong thuc co trong du lieu
        //buoc 2 remove tai khoan va cac ky tu dac bien
        String sRemoveTK = removeString(sFormula, ArrTK).replaceAll(Define.SPACE_STRING, "");
        //Remove chi tieu
        String sCaculatorStandard = removeString(sRemoveTK, ArrCT).replaceAll(Define.SPACE_STRING, "");

        //buoc 3 chuyen cong thuc ve cong thuc toan hoc
        for (String key : hmMapColumn.keySet()) {
            double value = hmMapColumn.get(key) * 1.0;
            sCaculatorStandard = sCaculatorStandard.replaceAll(key, Double.toString(value));
        }

        //buoc 4 kiem tra xem cong thuc co dung hay khong
        //System.err.println(sFormula);
        return checkCaculator(sCaculatorStandard);
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            return false;
//        }

    }

    public static String StardardFormula(String sFormula) {
        String strOut = "";
        if (sFormula == null || sFormula.isEmpty()) {
            return sFormula;
        }
        //Dem so ngoac dong trong chuoi
        int nLeft = StringUtils.countMatches(sFormula, ")");
        //Dem so ngoac mo trong chuoi
        int nRight = StringUtils.countMatches(sFormula, "(");

        //Neu so ngoac dong bang so ngoac mo thi return luon
        if (nLeft == nRight) {
            return sFormula;
        }

        //Truong hop so ngoac dong va mo khong bang nhau 
        //Khai bao 1 stack
        Stack info = new Stack();
        if (nLeft > nRight) {
            for (char ch : sFormula.toCharArray()) {
                if (ch == '(') {
                    strOut += ch;
                    info.push(ch);
                } else if (ch == ')') {
                    if (!info.isEmpty()) {
                        strOut += ch;
                        info.pop();
                    }
                } else {
                    strOut += ch;
                }
            }
        } else //Con truong hop dau ngoac ( nhieu hon dau ngoac ) lam sau
        //Truong hop nay rat kho
        {
            return sFormula;
        }
        return strOut;
    }

    /**
     *
     * @param sFormula Cong thuc tong tu ban dau khi dien vao
     * @param arrFormula Mang cac tai khoan hoac cac chi tieu khi tach ra
     * @return 1 list cac gia tri cong thuc can tinh toan
     * @throws Exception
     */
    public static List<ValueFormula> splitFormulaStringObj(String sFormula, String[] arrFormula) throws Exception {
        List<ValueFormula> lstarrFormula = new ArrayList<ValueFormula>();
        try {

            //neu cong thuc hoac mang truyen vao la null hoac trang thi return null
            if (sFormula.isEmpty() || sFormula == null || arrFormula.length == 0 || arrFormula == null) {
                return lstarrFormula;
            }

            //Duyet mang dieu kien lay du lieu nhu theo tk, chi tieu
            for (String sWhereData : arrFormula) {
                int Start = -1;
                int End = -1;
                while (true) {
                    Start = sFormula.indexOf(sWhereData, Start + 1);
                    if (Start < 0) {
                        break;
                    }
                    Start += sWhereData.length() + 1;
                    End = sFormula.indexOf(Define.SPACE_STRING, Start + 1);
                    String sCon = sFormula.substring(Start, End);
                    ValueFormula value = new ValueFormula();
                    value.setsWhereFormula(sWhereData);
                    value.setsFormula(sFormula);
                    value.setsFieldData(StardardFormula(sCon));
                    lstarrFormula.add(value);
                    if (sCon.isEmpty()) {
                        break;
                    }
                    Start = End;
                }
            }
        } catch (Exception e) {
            System.err.println(sFormula + " LOi " + e.getMessage());
        }

        return lstarrFormula;
    }

    public static List<ValueFormula> splitFormulaString(String sKeyRow, String sFormula, int nOrder) throws Exception {
        List<ValueFormula> lstarrValue = new ArrayList<ValueFormula>();
        if (sFormula == null || sFormula.isEmpty() || sKeyRow == null || sKeyRow.isEmpty()) {
            return lstarrValue;
        }
        ArrayList<String> arrString = SplitStringToArrayList(sFormula, ",");
        for (String str : arrString) {
            //Cat lay ra mang tai khoan
            String[] arrTK = splitString(str, "TK", Define.SPACE_STRING);
            //Cat lay ra mang cac chi tieu
            String[] arrCT = splitString(str, "CT", Define.SPACE_STRING);
            //tach cong thuc voi tai khoan
            List<ValueFormula> valueTK = splitFormulaStringObj(str, arrTK);
            for (ValueFormula value : valueTK) {
                //System.err.println("Chuoi ban dau ==>> " + value.getsFormula() + " Key -> " + value.getsWhereFormula() + " Value -> " + value.getsFieldData());
                value.setnOrder(nOrder);
                value.setsKey(sKeyRow);
                lstarrValue.add(value);
            }
            //tach cong thuc voi chi tieu
            List<ValueFormula> valueCT = splitFormulaStringObj(str, arrCT);
            for (ValueFormula value : valueCT) {
                //System.err.println("Chuoi ban dau ==>> " + value.getsFormula() + " Key -> " + value.getsWhereFormula() + " Value -> " + value.getsFieldData());
                value.setnOrder(nOrder);
                value.setsKey(sKeyRow);
                lstarrValue.add(value);
            }
        }
        //--------------------------------------------------
        return lstarrValue;
    }

    public static List<ValueFormula> splitFormulaByFormula(String sKeyRow, List<String> ArrFormula, int nOrder) throws Exception {
        List<ValueFormula> lstarrValue = new ArrayList<ValueFormula>();
        if (ArrFormula == null || ArrFormula.isEmpty() || sKeyRow == null || sKeyRow.isEmpty()) {
            return lstarrValue;
        }
        //ArrayList<String> arrString = SplitStringToArrayList(sFormula, ",");
        for (String str : ArrFormula) {
            //Cat lay ra mang tai khoan
            String[] arrTK = splitString(str, "TK", Define.SPACE_STRING);
            //Cat lay ra mang cac chi tieu
            String[] arrCT = splitString(str, "CT", Define.SPACE_STRING);
            //tach cong thuc voi tai khoan
            List<ValueFormula> valueTK = splitFormulaStringObj(str, arrTK);
            for (ValueFormula value : valueTK) {
                //System.err.println("Chuoi ban dau ==>> " + value.getsFormula() + " Key -> " + value.getsWhereFormula() + " Value -> " + value.getsFieldData());
                value.setnOrder(nOrder);
                value.setsKey(sKeyRow);
                lstarrValue.add(value);
            }
            //tach cong thuc voi chi tieu
            List<ValueFormula> valueCT = splitFormulaStringObj(str, arrCT);
            for (ValueFormula value : valueCT) {
                //System.err.println("Chuoi ban dau ==>> " + value.getsFormula() + " Key -> " + value.getsWhereFormula() + " Value -> " + value.getsFieldData());
                value.setnOrder(nOrder);
                value.setsKey(sKeyRow);
                lstarrValue.add(value);
            }
        }
        //--------------------------------------------------
        return lstarrValue;
    }

    /**
     *
     * @param objFormula thong tin ve bao cao da luu
     * @param hmArrFormula mang du lieu phan bang da luu
     * @return
     */
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
//                            if(sPos.indexOf("#")>0)
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

    public static boolean isNumeric3(String str) {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }
    //</editor-fold>

    public static String backlashReplace(String myStr) {
        final StringBuilder result = new StringBuilder();
        final StringCharacterIterator iterator = new StringCharacterIterator(myStr);
        char character = iterator.current();
        while (character != CharacterIterator.DONE) {

            if (character == '\\') {
                result.append("/");
            } else {
                result.append(character);
            }

            character = iterator.next();
        }
        return result.toString();
    }

    public static String get_status_descript(String status) {
        String l_descript;
        switch (status) {
            case "N":
                l_descript = "Chưa thực hiện";
                break;
            case "D":
                l_descript = "Hoàn thành";
                break;
            case "E":
                l_descript = "Lỗi xử lý";
                break;
            case "W":
                l_descript = "Đang chờ";
                break;
            case "P":
                l_descript = "Đang xử lý";
                break;
            default:
                l_descript = "Không xác định";
                break;
        }
        return l_descript;
    }

    public static void main(String[] args) throws ParseException {

        System.err.println(backlashReplace("I:\\PROJECT\\IMS_REPORTS\\IMS_REPORTS\\build\\web\\EXCEL_TEMPLATE\\TEMPLATE_CONFIG"));
        String input = "111110001111111111001111111000000000000000000111111111111101011111010111111";
        System.out.println(replaceStr(input, 7, "1"));
        System.out.println(input);
    }
}
