package vbsp.ims.action.ktktnb;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.BuildPosTreeDao;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.khnv.DaoListPosFromUser;
import vbsp.ims.dao.ktnb.DaoKTNBMain;
import vbsp.ims.dao.ktnb.DaoKtnb06;
import vbsp.ims.dao.ktnb.ListKTNBDA;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ktnb.Ktnb01Model;
import vbsp.ims.model.ktnb.Ktnb06Model;
import vbsp.ims.model.ktnb.PosMainModel;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlBcqtSync;

/**
 *
 * @author Haha
 */
public class Ktnb_bieu02ActionSupport extends ActionSupport implements ServletRequestAware {

    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList<>();
    protected List<String> poscd = new ArrayList<String>();
    protected DaoListPosFromUser listKTNBDA = new DaoListPosFromUser();
    protected PosMainModel posMainModel;

    protected String pos_cd_username;

    public String getPos_cd_username() {
        return pos_cd_username;
    }

    public void setPos_cd_username(String pos_cd_username) {
        this.pos_cd_username = pos_cd_username;
    }

    public String getMain_pos_username() {
        return main_pos_username;
    }

    public void setMain_pos_username(String main_pos_username) {
        this.main_pos_username = main_pos_username;
    }
    protected String main_pos_username;
    //Cac truong chua thong tin bo xung luu du lieu
    private String userId;
    private String quyBc;
    private String namBc;
    private String maCn;
    private String posCD;
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }

    protected List<ListValue> lstParameters = new ArrayList<>();

    public List<ListValue> getLstParameters() {
        return lstParameters;
    }

    public void setLstParameters(List<ListValue> lstParameters) {
        this.lstParameters = lstParameters;
    }
    protected String Message;

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    private TreeNode nodes;
    private String reportGrade;

    public String getReportGrade() {
        return reportGrade;
    }

    public void setReportGrade(String reportGrade) {
        this.reportGrade = reportGrade;
    }

    public TreeNode getNodes() {
        return nodes;
    }

    public void setNodes(TreeNode nodes) {
        this.nodes = nodes;
    }

//    public int getReportGrade() {
//        return reportGrade;
//    }
//
//    public void setReportGrade(int reportGrade) {
//        this.reportGrade = reportGrade;
//    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSelectedPos() {
        return selectedPos;
    }

    public void setSelectedPos(String selectedPos) {
        this.selectedPos = selectedPos;
    }

    public TreeNode getSearchNodes() {
        return searchNodes;
    }

    public void setSearchNodes(TreeNode searchNodes) {
        this.searchNodes = searchNodes;
    }
    private String userName;
    private String selectedPos;
    private TreeNode searchNodes;

    private HttpServletRequest request = null;

    public Ktnb_bieu02ActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_ktnb_bieu02() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            lstDulieuNt = daoMain.getDataKtnb_bieu02(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String get_data_ktnb_bieu03() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            lstDulieuNt = daoMain.getDataKtnb_bieu03(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String get_data_ktnb_bieu04() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            lstDulieuNt = daoMain.getDataKtnb_bieu04(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String get_data_ktnb_bieu05() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            lstDulieuNt = daoMain.getDataKtnb_bieu05(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
    }

//    public String ResetDataInput(){
//        getInfo();
////        ktnb06ModelList = daoKtnb06.get_ktnb06_default(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
//        return "success";
//    }
//    public String get_data_ktnb06A_auth() throws SQLException{
//        getInfo();
////        ktnb01ModelList = daoKtnb01.get_ktnb01(posCD, Integer.parseInt(namBc), Integer.parseInt(quyBc));
//        return "success";
//    }
    private List<String> getListOfPos() {
        List<String> posString = new ArrayList<String>();
        userName = request.getSession().getAttribute("username").toString();
        reportGrade = request.getSession().getAttribute("reportGrade").toString();
        String pos_cd;
        ArrayList<String> pos_stack = new ArrayList<>();
        ArrayList<String> listOfId
                = (ArrayList<String>) DefineFun.string2Array(selectedPos, ",", 1);
        boolean isAdded;
        if (listOfId.size() > 0) {
            for (String id : listOfId) {
                isAdded = false;
                pos_cd = DefineFun.searchInTreeView(id, this.searchNodes);
                for (String added_pos : pos_stack) {
                    if (added_pos.equals(pos_cd)) {
                        isAdded = true;
                        break;
                    }
                }
                if (!isAdded && pos_cd != null && !posString.contains(pos_cd)) {
                    posString.add(pos_cd);
                }
            }
        }

        System.err.println(posString);
        return posString;
    }

    public String view() throws SQLException {
        try {
            System.err.println(" da vao action");
            if (this.searchNodes == null) {
                System.err.print("searchNodes is null");
                try {
                    reportGrade = request.getSession().getAttribute("reportGrade").toString();
                    userName = request.getSession().getAttribute("username").toString();
                    BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(Integer.parseInt(reportGrade), userName);
                    buildPosTreeDao.build();
                    this.searchNodes = buildPosTreeDao.getNodes();
                } catch (SQLException ex) {
                }
            }
            getInfo();
            List<String> lstPos = getListOfPos();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            lstDulieuNt = daoMain.getDataKtnb_bieu02(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), lstPos);
            return "success";
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> GetDataAuth06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> GetDataAuth06A: " + e.getMessage());
            return "error";
        }

    }

    public String save_data_ktnb_bieu02() throws SQLException {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            if (!daoMain.saveKTNB_bieu02("02_PCTN", userName, "", quyBc, namBc, lstDulieuNt)) {
                return "error";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String save_data_ktnb_bieu03() throws SQLException {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            if (!daoMain.saveKTNB_bieu03("03_PCTN", userName, "", quyBc, namBc, lstDulieuNt)) {
                return "error";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String save_data_ktnb_bieu04() throws SQLException {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            if (!daoMain.saveKTNB_bieu04("04_PCTN", userName, "", quyBc, namBc, lstDulieuNt)) {
                return "error";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String save_data_ktnb_bieu05() throws SQLException {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            if (!daoMain.saveKTNB_bieu05("05_PCTN", userName, "", quyBc, namBc, lstDulieuNt)) {
                return "error";
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
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
        if (userName == null || userName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        setReportGrade(session.get("reportGrade").toString());
        if (reportGrade == null || reportGrade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        return true;
    }

    public String Auth() throws SQLException {
        System.err.println("Gui KTNB06A");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
//            HashMap hmParameter = getParameter();
            if (this.searchNodes == null) {
                System.err.print("searchNodes is null");
                try {
                    reportGrade = request.getSession().getAttribute("reportGrade").toString();
                    userName = request.getSession().getAttribute("username").toString();
                    BuildPosTreeDao buildPosTreeDao = new BuildPosTreeDao(Integer.parseInt(reportGrade), userName);
                    buildPosTreeDao.build();
                    this.searchNodes = buildPosTreeDao.getNodes();
                } catch (SQLException ex) {
                }
            }

            List<String> lstPos = getListOfPos();
            DaoKTNBMain daosync = DaoKTNBMain.newInstance();
            Map<String, Integer> mapStatusSend = new HashMap();

            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += "KTNB06A" + "_" + mapgd
                        + "_" + userName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

//                type_bcqt = hmParameter.get("type_bcqt").toString();
                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;
                String ngay_bc = "";
                if (quyBc.equals("1")) {
                    ngay_bc = "31-MAR-" + namBc;
                } else if (quyBc.equals("2")) {
                    ngay_bc = "30-JUN-" + namBc;
                } else if (quyBc.equals("3")) {
                    ngay_bc = "30-SEP-" + namBc;
                } else if (quyBc.equals("4")) {
                    ngay_bc = "30-NOV-" + namBc;
                }

                lstData = daosync.getDataSendKTNB06A("NT", "KTNB06A",
                        mapgd, ngay_bc);
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlBcqtSync().createXmlFileBcqt(Define.PARA_SYN_REPORT_KTNB_ADD, "NT",
                        "KTNB06A", ngay_bc, userName, reportGrade,
                        mapgd, lstData, Define.WEB_SERVICES_STATUS_OK, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> KTNB_ADD: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> KTNB_ADD: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
//                    return ERROR;
                }
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
                if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
//                    addActionError("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    CoreLogger.error(this.getClass().getName() + " Exception -> KTNB_ADD: Khong dong bo duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
//                    return ERROR;
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                } else {
//                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
//                    return ERROR;
                }
            }
            setLstViewSend(getViewStatusSendKTNB06A(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTNB_ADD: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTNB_ADD: " + e.getMessage());
            addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return ERROR;
        }
//        addActionMessage("Bạn gửi dữ liệu về trung ương thành công !");

        return SUCCESS;
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
                    lstParameters.add(new ListValue(parameter, values[0]));
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            } else {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.equals("poscd")) {
                    paramHashMap.put(parameter, convertStringtoList(values));
                } else {
                    paramHashMap.put(parameter, values[0]);
                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            }
        }
        return paramHashMap;
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

    private List<ModelViewSend> getViewStatusSendKTNB06A(List<String> lstPos, Map<String, Integer> mapStatus) {
        addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();
        try {
            Map<String, String> mapPosByName = DaoBcqtMain.newInstance().getPosByName(lstPos);

            for (String key : mapStatus.keySet()) {

                if (mapPosByName.get(key) == null) {
                    continue;
                };
                Integer value = mapStatus.get(key);
                ModelViewSend modelview = ModelViewSend.newInstance();
                modelview.setMapgd(key);
                modelview.setKey(value);
                modelview.setTenpgd(mapPosByName.get(key));

                switch (value) {
                    case 1:
                        modelview.setMota_loi("Tạo file xml bị lỗi");
                        break;
                    case 2:
                        modelview.setMota_loi("Không tìm thấy file xml");
                        break;
                    case 3:
                        modelview.setMota_loi("Gửi dữ liệu bị lỗi");
                        break;
                    case 4:
                        modelview.setMota_loi("Thành công");
                        break;
                    case 5:
                        modelview.setMota_loi("Phòng giao dịch này bị khóa");
                        break;
                    case 6:
                        modelview.setMota_loi("Không có dữ liệu");
                        break;
                    default:
                        modelview.setMota_loi("Không đúng trạng thái lỗi");
                        break;
                }
                lstStatus.add(modelview);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
        }
        return lstStatus;
    }

    public void getInfo() {
        //Lay username
        HttpSession session = request.getSession();
        userId = session.getAttribute("username").toString();

        //Lay thong tin ma phogn giao dich, ma chi nhanh
//        PosMainModel posMainModel;
//        posMainModel = listKTNBDA.get_pos_main_pos(userId);
//        posCD = posMainModel.getPosCd();
//        maCn = posMainModel.getMainPosCd();
    }

    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">     
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getQuyBc() {
        return quyBc;
    }

    public void setQuyBc(String quyBc) {
        this.quyBc = quyBc;
    }

    public String getNamBc() {
        return namBc;
    }

    public void setNamBc(String namBc) {
        this.namBc = namBc;
    }

    public String getMaCn() {
        return maCn;
    }

    public void setMaCn(String maCn) {
        this.maCn = maCn;
    }

    public HttpServletRequest getRequest() {
        return request;
    }

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    public String getPosCD() {
        return posCD;
    }

    public void setPosCD(String posCD) {
        this.posCD = posCD;
    }

    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }
//</editor-fold>

}
