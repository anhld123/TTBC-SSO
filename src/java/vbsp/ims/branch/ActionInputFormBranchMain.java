/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.branch;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class ActionInputFormBranchMain extends ActionSupport {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien">
    protected String Grade;
    protected String UserName;
    public String message;
    protected String khoa;
    protected List<DULIEU_NT_CN> lstDulieuChitieu = new ArrayList<>();
    protected List<DULIEU_NT_CN> lstDulieuCot = new ArrayList<>();
    protected DaoInputFormBranchMain daobranc = new DaoInputFormBranchMain();

    public List<DULIEU_NT_CN> getLstDulieuChitieu() {
        return lstDulieuChitieu;
    }

    public void setLstDulieuChitieu(List<DULIEU_NT_CN> lstDulieuChitieu) {
        this.lstDulieuChitieu = lstDulieuChitieu;
    }

    public List<DULIEU_NT_CN> getLstDulieuCot() {
        return lstDulieuCot;
    }

    public void setLstDulieuCot(List<DULIEU_NT_CN> lstDulieuCot) {
        this.lstDulieuCot = lstDulieuCot;
    }

    public String getKhoa() {
        return khoa;
    }

    public void setKhoa(String khoa) {
        this.khoa = khoa;
    }

    public String getGrade() {
        return Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Ham chung cho lop main">
    public String execute() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    protected List<String> convertStringtoList(String[] value) {
        List<String> lst = new ArrayList<>();
        try {
            for (int i = 0; i < value.length; i++) {
                if (!value[i].equals("999999") && !value[i].isEmpty()) {
                    lst.add(value[i]);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
        }
        return lst;
    }

    protected boolean getParaSession() {
        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return false;
        }
        //lay ra user
        setUserName(session.get("username").toString());

//            System.err.println("execute sUserName=" + sUserName);
        if (UserName == null || UserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        setGrade(session.get("reportGrade").toString());
        if (Grade == null || Grade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        return true;
    }

    protected HashMap<String, Object> getParameter() throws Exception {
        HashMap<String, Object> paramHashMap = new HashMap<>();
        Map<String, String[]> prameters = ServletActionContext.getRequest().getParameterMap();
//        Map<String, Object> prameters1=ActionContext.getContext().getParameters();
        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);

            if (values != null) {
                if (values.length > 1) {
                    String valueString = StringUtils.join(values, ",");
                    paramHashMap.put(parameter, valueString);
                    System.out.println("parameter=" + parameter + " valueString=" + valueString);
                } else {
                    paramHashMap.put(parameter, values[0]);
                    System.out.println("parameter=" + parameter + " valueString=" + values[0]);
                }
            }

//            if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0) {
//                if (parameter.startsWith("1_")) {
//                    parameter = parameter.substring(2, parameter.length());
//                }
//                if (parameter.indexOf("DATE") > 0) {
//                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
//                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
////                    lstParameters.add(new ListValue(parameter, values[0]));
//                } else {
//                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
////                    lstParameters.add(new ListValue(parameter, values[0]));
//                }
//            } else {
//                if (parameter.startsWith("1_")) {
//                    parameter = parameter.substring(2, parameter.length());
//                }
//                if (parameter.equals("poscd")) {
////                    paramHashMap.put(parameter, convertStringtoList(values));
//                } else {
//                    paramHashMap.put(parameter, values[0]);
////                    lstParameters.add(new ListValue(parameter, values[0]));
//                }
//            }
        }
        return paramHashMap;
    }

    protected List<DULIEU_NT_CN> getParameterLoadData(HashMap<String, Object> mapParameter) throws Exception {
        List<DULIEU_NT_CN> lstParaloadData = new ArrayList<DULIEU_NT_CN>();
        try {
            for (String parameter : mapParameter.keySet()) {
                DULIEU_NT_CN thamso = new DULIEU_NT_CN();

                String values = mapParameter.get(parameter).toString();

                if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0 || parameter.indexOf("NUMB") > 0) {
                    if (parameter.startsWith("1_")) {
                        parameter = parameter.substring(2, parameter.length());
                    }
                    if (parameter.indexOf("DATE") > 0) {
                        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values);
                        thamso.setMA(parameter.substring(0, parameter.length() - 5));
                        thamso.setTEN(new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                        thamso.setKIEUDULIEU("D");
                    } else {

                        thamso.setMA(parameter.substring(0, parameter.length() - 5));
                        thamso.setTEN(values);
                        if (parameter.indexOf("TEXT") > 0) {
                            thamso.setKIEUDULIEU("T");
                        }
                        if (parameter.indexOf("LIST") > 0) {
                            thamso.setKIEUDULIEU("L");
                        }
                        if (parameter.indexOf("NUMB") > 0) {
                            thamso.setKIEUDULIEU("N");
                        }
                    }
                    if(thamso.getTEN()==null || thamso.getTEN().isEmpty())
                    {
                        throw new Exception("Bạn phải điền dữ liệu cho tham số, không thể để tham số null hoặc không có dữ liệu !");
                    }
                    lstParaloadData.add(thamso);
                }
            }
        } catch (Exception e) {
            throw new Exception(e);
        }
        return lstParaloadData;
    }
//</editor-fold>
    
     //<editor-fold defaultstate="collapsed" desc="Tham so và sessession">
    /**
     * Hàm này convert tham số về lớp đối tượng tham số
     *
     * @param khoa
     * @param hmParameter
     * @param index
     * @return
     * @throws Exception
     */
    protected ModelParameter getParameterObject(String khoa, HashMap<String, Object> hmParameter, int index) throws Exception {
        try {
            ModelParameter modelParameter = new ModelParameter();
            modelParameter.setKhoa(khoa);
//            modelParameter.setLoaitso("LOAITSO_" + index);
            String loaithamso = hmParameter.get("LOAITSO_" + index).toString();
            if (loaithamso == null) {
                throw new Exception("Tham số LOAITSO là null");
            }
            if (loaithamso.equals("L")) {
                modelParameter.setLoaitso(loaithamso);
                modelParameter.setMota(hmParameter.get("MOTA_THAMSO_" + index).toString());
                modelParameter.setBangsl(hmParameter.get("BANGSL_" + index).toString());
                modelParameter.setCothienthi(hmParameter.get("COTHIENTHI_" + index).toString());
                modelParameter.setCottso(hmParameter.get("COTTSO_" + index).toString());
                modelParameter.setDkloc(hmParameter.get("DKLOC_" + index).toString());
                modelParameter.setDksapxep(hmParameter.get("DKSAPXEP_" + index).toString());
                if (modelParameter.getBangsl() == null || modelParameter.getBangsl().isEmpty()
                        || modelParameter.getCothienthi() == null || modelParameter.getCothienthi().isEmpty()
                        || modelParameter.getCottso() == null || modelParameter.getCottso().isEmpty()
                        || modelParameter.getCottso() == null || modelParameter.getCottso().isEmpty()) {
                    throw new Exception("Bạn phải điền đầy đủ thông tin cho tham số dạng List !");
                }
            } else {
                modelParameter.setLoaitso(loaithamso);
                String motathamso = hmParameter.get("MOTA_THAMSO_" + index).toString();
                if (motathamso == null || motathamso.isEmpty()) {
                    throw new Exception("Bạn phải nhập mô tả cho tham số !");
                }
                modelParameter.setMota(motathamso);
            }

            switch (loaithamso) {
                case "D":
                    modelParameter.setThamso("ngaysl_" + index);
                    break;
                case "L":
                    modelParameter.setThamso("danhmuc_" + index);
                    break;
                case "N":
                    modelParameter.setThamso("kieuso_" + index);
                    break;
                case "T":
                    modelParameter.setThamso("kieutext_" + index);
                    break;
            }
            return modelParameter;
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    /**
     * Hàm này thực hiện lấy ra mảng các phần tử của id theo số ví dụ LOAITSO_0,
     * LOAITSO_3, LOAITSO_4 thì sẽ lấy ra màng {0,3,4}
     *
     * @param hmParameter
     * @return
     * @throws Exception
     */
    protected int[] getCountParameter(HashMap<String, Object> hmParameter) throws Exception {
        try {

            int index = 0;
            for (String key : hmParameter.keySet()) {
                //đếm xem có bao nhiêu tham số
                if (key.startsWith("LOAITSO")) {
                    index++;
                }
            }
            //khai báo mảng
            int[] count = new int[index];
            index = 0;
            for (String key : hmParameter.keySet()) {
                if (key.startsWith("LOAITSO")) {
                    String tt_tso = key.substring(key.lastIndexOf("_") + 1, key.length());
                    int index_tmp = Integer.valueOf(tt_tso);
                    count[index] = index_tmp;
                    index++;
                }
            }
            return count;
//            LOAITSO_0
        } catch (Exception e) {
            throw new Exception(e);
        }
    }
//</editor-fold>
}
