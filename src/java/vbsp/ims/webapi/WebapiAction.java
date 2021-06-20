/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.webapi;

import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class WebapiAction extends ActionSupport {

    private List<ListValue> moduleList = new ArrayList<>();
    private List<ListValue> poscdList = new ArrayList<>();
    //lay ra cap bao cao
    protected String Grade;
    //lay ra user dang nhap
    protected String UserName;
    //message se tra ve
    protected String message;

    private String loai_api;

    private String groupId;

    private String loanId;
    private String custId;
    private String typeFind;
    private String posCode;

    private List<Loan> loanList = new ArrayList<>();

    private List<GroupInfo> groupInfoList = new ArrayList<>();
    private List<TideInfo> tideInfoList = new ArrayList<>();

    private List<LoanTransaction> loanTransactionList = new ArrayList<>();
    private List<TranPoint> tranPointList = new ArrayList<>();

    //<editor-fold defaultstate="collapsed" desc="phan chung">
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

    private List<String> convertStringtoList(String[] value) {
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

    protected HashMap<String, Object> getParameter() throws Exception {
        HashMap<String, Object> paramHashMap = new HashMap<>();
        Map<String, String[]> prameters = ServletActionContext.getRequest().getParameterMap();
        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);
            if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0) {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
//                    lstParameters.add(new ListValue(parameter, values[0]));
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
//                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            } else {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.equals("poscd")) {
                    paramHashMap.put(parameter, convertStringtoList(values));
                } else {
                    paramHashMap.put(parameter, values[0]);
//                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            }
        }
        return paramHashMap;
    }

//</editor-fold>
    
    //<editor-fold defaultstate="collapsed" desc="Action Webapi">
    public String execute() {
        try {

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadPageMain() {
        try {

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadModuleWebApi() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            DaoWebapi dao = new DaoWebapi();
            moduleList = dao.getModuleWebApi();
            poscdList = dao.getPoscdWebApi(UserName, Grade);
            //get pos_cd va url để vào truong hiden
            System.err.println("vao action");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadModuleWebApi -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String apitruyvan() {
        try {
            if (loai_api == null || loai_api.isEmpty() || loai_api.equals("-1")) {
                addActionError("Bạn phải chọn loại truy vấn! ");
                setMessage("Bạn phải chọn loại truy vấn! ");
                return ERROR;
            }
            if (!getParaSession()) {
                addActionError("Bạn phải đăng xuất rồi đăng nhập lại! ");
                setMessage("Bạn phải đăng xuất rồi đăng nhập lại! ");
                return ERROR;
            }
            //lay ra ma pgd va url trong csdl
            HashMap<String, String> hmPosUrl = new DaoWebapi().getPoscdUrl(UserName);
            
            String url = hmPosUrl.get("URL");
            if(Grade.equals("1")) posCode = hmPosUrl.get("POS_CD");
            else
            {
                if (posCode == null || posCode.isEmpty() || posCode.equals("-1")) {
                addActionError("Bạn phải chọn phòng giao dịch cần truy vấn! ");
                setMessage("Bạn phải chọn phòng giao dịch cần truy vấn! ");
                return ERROR;
            }
            }
            if (posCode.equals("999999") || url.equals("999999")) {
                addActionError("Lấy tham số mã pgd và đường đẫn web api lỗi! ");
                setMessage("Lấy tham số mã pgd và đường đẫn web api lỗi! ");
                return ERROR;
            }
            JsonWebApi webapi = new JsonWebApi(url);

            switch (loai_api) {
                case "01": {//loai truy van la khach hang
                    if (typeFind == null || typeFind.equals("")) {
                        addActionError("Bạn phải chọn loại khách hàng cần truy vấn! ");
                        setMessage("Bạn phải chọn loại khách hàng cần truy vấn! ");
                        return ERROR;
                    }
                    //chon loai truy van
                    switch (typeFind) {
                        case "1": {//truy van theo ma khach hang
                            if (custId == null || custId.equals("")) {
                                addActionError("Bạn phải điền mã khách hàng! ");
                                setMessage("Bạn phải điền mã khách hàng! ");
                                return ERROR;
                            }
                        }
                        break;
                        case "2": {
                            if (custId == null || custId.equals("")) {
                                addActionError("Bạn phải điền số chứng minh thư khách hàng! ");
                                setMessage("Bạn phải điền số chứng minh thư khách hàng! ");
                                return ERROR;
                            }
                        }
                        break;
                        case "3": {
                            if (custId == null || custId.equals("")) {
                                addActionError("Bạn phải điền số điện thoại khách hàng! ");
                                setMessage("Bạn phải điền số điện thoại khách hàng! ");
                                return ERROR;
                            }
                        }
                        break;
                        default: {

                            addActionError("Không đúng loại truy vấn! ");
                            setMessage("Không đúng loại truy vấn! ");
                            return ERROR;

                        }
                    }
                    loanList = webapi.GetCustInfoByCustId(posCode, custId, Integer.parseInt(typeFind));
                    return "custid";
                }
//                break;
                case "02": //truy van lich su giai ngan, thu no thu lai
                {
                    if (loanId == null || loanId.equals("")) {
                        addActionError("Bạn phải điền mã khoản vay của khách hàng! ");
                        setMessage("Bạn phải điền mã khoản vay của khách hàng! ");
                        return ERROR;
                    }
                    loanTransactionList = webapi.GetLoanTransaction(posCode, loanId);
                    return "loanid";
                }
//                break;
                case "03"://truy vấn tổ TK&VV
                {
                    groupInfoList = webapi.GetGroupInfoList(posCode);
                    return "group";
                }
//                break;
                case "04": {//truy van thanh vien cua to
                    if (groupId == null || groupId.equals("")) {
                        addActionError("Bạn phải điền mã tổ trưởng! ");
                        setMessage("Bạn phải điền mã tổ trưởng! ");
                        return ERROR;
                    }
                    groupInfoList = webapi.GetGroupInfo(posCode, groupId);
                    return "group_cust";
                }
                case "05": {//truy van thanh vien cua to
                    if (groupId == null || groupId.equals("")) {
                        addActionError("Bạn phải điền tài khoản tide của khách hàng! ");
                        setMessage("Bạn phải điền tài khoản tide của khách hàng! ");
                        return ERROR;
                    }
                    tideInfoList = webapi.GetTranHisTide(posCode, groupId);
                    return "tide";
                }
//                break;
                default:
                    addActionError("Không đúng loại truy vấn được cấu hình xin liên hệ với quản trị! ");
                    setMessage("Không đúng loại truy vấn được cấu hình xin liên hệ với quản trị! ");
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
    
    
    public String popup() {
        try {
            if (loai_api == null || loai_api.isEmpty() || loai_api.equals("-1")) {
                addActionError("Bạn phải chọn loại truy vấn! ");
                setMessage("Bạn phải chọn loại truy vấn! ");
                return ERROR;
            }
            if (!getParaSession()) {
                addActionError("Bạn phải đăng xuất rồi đăng nhập lại! ");
                setMessage("Bạn phải đăng xuất rồi đăng nhập lại! ");
                return ERROR;
            }
            //lay ra ma pgd va url trong csdl
            HashMap<String, String> hmPosUrl = new DaoWebapi().getPoscdUrl(UserName);
            
            String url = hmPosUrl.get("URL");
            if(Grade.equals("1")) posCode = hmPosUrl.get("POS_CD");
            else
            {
                if (posCode == null || posCode.isEmpty() || posCode.equals("-1")) {
                addActionError("Bạn phải chọn phòng giao dịch cần truy vấn! ");
                setMessage("Bạn phải chọn phòng giao dịch cần truy vấn! ");
                return ERROR;
            }
            }
            if (posCode.equals("999999") || url.equals("999999")) {
                addActionError("Lấy tham số mã pgd và đường đẫn web api lỗi! ");
                setMessage("Lấy tham số mã pgd và đường đẫn web api lỗi! ");
                return ERROR;
            }
            JsonWebApi webapi = new JsonWebApi(url);

            switch (loai_api) {
                case "01": {//loai truy van la khach hang
                    if (typeFind == null || typeFind.equals("")) {
                        addActionError("Bạn phải chọn loại khách hàng cần truy vấn! ");
                        setMessage("Bạn phải chọn loại khách hàng cần truy vấn! ");
                        return ERROR;
                    }
                    //chon loai truy van
                    switch (typeFind) {
                        case "1": {//truy van theo ma khach hang
                            if (custId == null || custId.equals("")) {
                                addActionError("Bạn phải điền mã khách hàng! ");
                                setMessage("Bạn phải điền mã khách hàng! ");
                                return ERROR;
                            }
                        }
                        break;
                        case "2": {
                            if (custId == null || custId.equals("")) {
                                addActionError("Bạn phải điền số chứng minh thư khách hàng! ");
                                setMessage("Bạn phải điền số chứng minh thư khách hàng! ");
                                return ERROR;
                            }
                        }
                        break;
                        case "3": {
                            if (custId == null || custId.equals("")) {
                                addActionError("Bạn phải điền số điện thoại khách hàng! ");
                                setMessage("Bạn phải điền số điện thoại khách hàng! ");
                                return ERROR;
                            }
                        }
                        break;
                        default: {

                            addActionError("Không đúng loại truy vấn! ");
                            setMessage("Không đúng loại truy vấn! ");
                            return ERROR;

                        }
                    }
                    loanList = webapi.GetCustInfoByCustId(posCode, custId, Integer.parseInt(typeFind));
                    return "custid";
                }
//                break;
                case "02": //truy van lich su giai ngan, thu no thu lai
                {
                    if (loanId == null || loanId.equals("")) {
                        addActionError("Bạn phải điền mã khoản vay của khách hàng! ");
                        setMessage("Bạn phải điền mã khoản vay của khách hàng! ");
                        return ERROR;
                    }
                    loanTransactionList = webapi.GetLoanTransaction(posCode, loanId);
                    return "loanid";
                }
//                break;
                case "03"://truy vấn tổ TK&VV
                {
                    groupInfoList = webapi.GetGroupInfoList(posCode);
                    return "group";
                }
//                break;
                case "04": {//truy van thanh vien cua to
                    if (groupId == null || groupId.equals("")) {
                        addActionError("Bạn phải điền mã tổ trưởng! ");
                        setMessage("Bạn phải điền mã tổ trưởng! ");
                        return ERROR;
                    }
                    groupInfoList = webapi.GetGroupInfo(posCode, groupId);
                    return "group_cust";
                }
                case "05": {//truy van thanh vien cua to
                    if (groupId == null || groupId.equals("")) {
                        addActionError("Bạn phải điền tài khoản tide của khách hàng! ");
                        setMessage("Bạn phải điền tài khoản tide của khách hàng! ");
                        return ERROR;
                    }
                    tideInfoList = webapi.GetTranHisTide(posCode, groupId);
                    return "tide";
                }
//                break;
                default:
                    addActionError("Không đúng loại truy vấn được cấu hình xin liên hệ với quản trị! ");
                    setMessage("Không đúng loại truy vấn được cấu hình xin liên hệ với quản trị! ");
                    break;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " execute -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="Get/set">
    public List<ListValue> getPoscdList() {
        return poscdList;
    }

    public void setPoscdList(List<ListValue> poscdList) {
        this.poscdList = poscdList;
    }

    public String getPosCode() {
        return posCode;
    }

    public void setPosCode(String posCode) {
        this.posCode = posCode;
    }

    public List<ListValue> getModuleList() {
        return moduleList;
    }

    public void setModuleList(List<ListValue> moduleList) {
        this.moduleList = moduleList;
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

    public String getLoai_api() {
        return loai_api;
    }

    public void setLoai_api(String loai_api) {
        this.loai_api = loai_api;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getLoanId() {
        return loanId;
    }

    public void setLoanId(String loanId) {
        this.loanId = loanId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getTypeFind() {
        return typeFind;
    }

    public void setTypeFind(String typeFind) {
        this.typeFind = typeFind;
    }

    public List<Loan> getLoanList() {
        return loanList;
    }

    public void setLoanList(List<Loan> loanList) {
        this.loanList = loanList;
    }

    public List<GroupInfo> getGroupInfoList() {
        return groupInfoList;
    }

    public void setGroupInfoList(List<GroupInfo> groupInfoList) {
        this.groupInfoList = groupInfoList;
    }

    public List<TideInfo> getTideInfoList() {
        return tideInfoList;
    }

    public void setTideInfoList(List<TideInfo> tideInfoList) {
        this.tideInfoList = tideInfoList;
    }

    public List<LoanTransaction> getLoanTransactionList() {
        return loanTransactionList;
    }

    public void setLoanTransactionList(List<LoanTransaction> loanTransactionList) {
        this.loanTransactionList = loanTransactionList;
    }

    public List<TranPoint> getTranPointList() {
        return tranPointList;
    }

    public void setTranPointList(List<TranPoint> tranPointList) {
        this.tranPointList = tranPointList;
    }

//</editor-fold>
}
