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
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.util.DateUtil;
import vbsp.ims.xml.XmlBcqtSync;

/**
 *
 * @author Haha
 */
public class PCTNReportActionSupport extends ActionSupport implements ServletRequestAware {

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
    private String statusAuthor;
    private String ngayBC;

    public String getNgayBC() {
        return ngayBC;
    }

    public void setNgayBC(String ngayBC) {
        this.ngayBC = ngayBC;
    }

    public String getStatusAuthor() {
        return statusAuthor;
    }

    public void setStatusAuthor(String statusAuthor) {
        this.statusAuthor = statusAuthor;
    }

    PCTNService _service;

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

    public PCTNReportActionSupport() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_ktnb_bieu01() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDulieuNt = _service.getDataKTKSNB("01_PCTN", pos_cd_username, Grade, _reportDate, "", "0");
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                lstDulieuNt = _service.getDataKTKSNB("01_PCTN", pos_cd_username, Grade, _reportDate, "", "1");
            }
            String sStatus;
            try {
                sStatus = lstDulieuNt.get(0).getD50();
                if (sStatus == null) {
                    sStatus = "0";
                }
            } catch (Exception e) {
                sStatus = "0";
            }

            if (sStatus.equals("3")) {
                setStatusAuthor("Tỉnh đã duyệt");
            } else if (sStatus.equals("4")) {
                setStatusAuthor("Tw đã duyệt");
            } else if (sStatus.equals("1") || sStatus.equals("2")) {
                setStatusAuthor("Huyện đã nhập");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb04: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String get_data_ktnb_bieu02() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDulieuNt = _service.getDataKTKSNB("02_PCTN", pos_cd_username, Grade, _reportDate, "", "0");
            String sStatus;
            try {
                sStatus = lstDulieuNt.get(0).getD50();
                if (sStatus == null) {
                    sStatus = "0";
                }
            } catch (Exception e) {
                sStatus = "0";
            }

            if (sStatus.equals("3")) {
                setStatusAuthor("Tỉnh đã duyệt");
            } else if (sStatus.equals("4")) {
                setStatusAuthor("Tw đã duyệt");
            } else if (sStatus.equals("1") || sStatus.equals("2")) {
                setStatusAuthor("Huyện đã nhập");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb02: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb02: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String get_data_ktnb_bieu03() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDulieuNt = _service.getDataKTKSNB("03_PCTN", pos_cd_username, Grade, _reportDate, "", "0");
            String sStatus;
            try {
                sStatus = lstDulieuNt.get(0).getD50();
                if (sStatus == null) {
                    sStatus = "0";
                }
            } catch (Exception e) {
                sStatus = "0";
            }

            if (sStatus.equals("3")) {
                setStatusAuthor("Tỉnh đã duyệt");
            } else if (sStatus.equals("4")) {
                setStatusAuthor("Tw đã duyệt");
            } else if (sStatus.equals("1") || sStatus.equals("2")) {
                setStatusAuthor("Huyện đã nhập");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb003: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb03: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String get_data_ktnb_bieu04() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDulieuNt = _service.getDataKTKSNB("04_PCTN", pos_cd_username, Grade, _reportDate, "", "0");
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                lstDulieuNt = _service.getDataKTKSNB("04_PCTN", pos_cd_username, Grade, _reportDate, "", "1");
            }
            String sStatus;
            try {
                sStatus = lstDulieuNt.get(0).getD50();
                if (sStatus == null) {
                    sStatus = "0";
                }
            } catch (Exception e) {
                sStatus = "0";
            }

            if (sStatus.equals("3")) {
                setStatusAuthor("Tỉnh đã duyệt");
            } else if (sStatus.equals("4")) {
                setStatusAuthor("Tw đã duyệt");
            } else if (sStatus.equals("1") || sStatus.equals("2")) {
                setStatusAuthor("Huyện đã nhập");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb04: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String get_data_ktnb_bieu05() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            if (reportGrade.equals("1")) {
                addActionMessage("Mẫu này không thực hiện tại cấp PGD");
                return "error";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDulieuNt = _service.getDataKTKSNB("05_PCTN", pos_cd_username, Grade, _reportDate, "", "0");
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                lstDulieuNt = _service.getDataKTKSNB("05_PCTN", pos_cd_username, Grade, _reportDate, "", "1");
            }
            String sStatus;
            try {
                sStatus = lstDulieuNt.get(0).getD50();
                if (sStatus == null) {
                    sStatus = "0";
                }
            } catch (Exception e) {
                sStatus = "0";
            }

            if (sStatus.equals("3")) {
                setStatusAuthor("Tỉnh đã duyệt");
            } else if (sStatus.equals("4")) {
                setStatusAuthor("Tw đã duyệt");
            } else if (sStatus.equals("1") || sStatus.equals("2")) {
                setStatusAuthor("Huyện đã nhập");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb05: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb05: " + e.getMessage());
            return "error";
        }

        return "success";
    }

    public String get_data_ktnb_bieu06() {
        try {
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            if (reportGrade.equals("1")) {
                addActionMessage("Mẫu này không thực hiện tại cấp PGD");
                return "error";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDulieuNt = _service.getDataKTKSNB("06_PCTN", pos_cd_username, Grade, _reportDate, "", "0");
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                lstDulieuNt = _service.getDataKTKSNB("06_PCTN", pos_cd_username, Grade, _reportDate, "", "1");
            }
            String sStatus;
            try {
                sStatus = lstDulieuNt.get(0).getD50();
                if (sStatus == null) {
                    sStatus = "0";
                }
            } catch (Exception e) {
                sStatus = "0";
            }

            if (sStatus.equals("3")) {
                setStatusAuthor("Tỉnh đã duyệt");
            } else if (sStatus.equals("4")) {
                setStatusAuthor("Tw đã duyệt");
            } else if (sStatus.equals("1") || sStatus.equals("2")) {
                setStatusAuthor("Huyện đã nhập");
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> get_data_ktnb06A: " + e.getMessage());
            return "error";
        }

        return "success";
    }

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

    public String save_data_ktnb_bieu01() throws SQLException {
        try {
            List<QT_DULIEU_NT> lstDL = new ArrayList<>();
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDL = _service.getDataKTKSNB("01_PCTN", pos_cd_username, Grade, _reportDate, "", "0");

            if (lstDL == null || lstDL.isEmpty()) {
            } else {

                if (lstDL.get(0).getD50().equals("3") || lstDL.get(0).getD50().equals("4") && lstDL.get(0).getCO_TONGHOP().equals("S")) {
                    addActionMessage("Cấp Tỉnh đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
                else if (lstDL.get(0).getD50().equals("4") && lstDL.get(0).getCO_TONGHOP().equals("M"))
                {
                    addActionMessage("Cấp TW đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
            }

            if (!daoMain.saveKTNB_bieu01("01_PCTN", userName, pos_cd_username, "", "", Integer.parseInt(reportGrade), lstDulieuNt, _reportDate)) {
                addActionMessage("Lưu dữ liệu nội bộ chưa thành công.");
                return "error";
            }
            lstDulieuNt = daoMain.getDataKtnb_bieu01(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);

            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionMessage("Không có dữ liệu tại chi nhánh ((ko gửi api).");
                return "error";
            }
            List<DuLieuNTRow> lstData = new ArrayList<>();

            try {
                lstData = mapList(lstDulieuNt, "01_PCTN", userName, _reportDate);
            } catch (Exception e) {
                addActionMessage("Map dữ liệu lỗi DuLieuNTRow <> QT_DULIEU_NT.");
                return "error";
            }

            _service = new PCTNService();
            if (_service.saveDataKTKSNB("01_PCTN", pos_cd_username, Grade, _reportDate, userId, userId, lstData) == 0) {
                addActionMessage("Cập nhật Api lên Tw không thành công");
                return "error";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb_bieu01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb_bieu01: " + e.getMessage());
            addActionMessage("Cập nhật không thành công");
            return "error";
        }

        return "success";
    }

    public String save_data_ktnb_bieu02() throws SQLException {
        try {
            List<QT_DULIEU_NT> lstDL = new ArrayList<>();
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDL = _service.getDataKTKSNB("02_PCTN", pos_cd_username, Grade, _reportDate, "", "0");

            if (lstDL == null || lstDL.isEmpty()) {

            } else {

                if (lstDL.get(0).getD50().equals("3") || lstDL.get(0).getD50().equals("4") && lstDL.get(0).getCO_TONGHOP().equals("S")) {
                    addActionMessage("Cấp Tỉnh đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
                else if (lstDL.get(0).getD50().equals("4") && lstDL.get(0).getCO_TONGHOP().equals("M"))
                {
                    addActionMessage("Cấp TW đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
            }

            if (!daoMain.saveKTNB_bieu02("02_PCTN", userName, pos_cd_username, "", "", Integer.parseInt(reportGrade), lstDulieuNt,_reportDate)) {
                addActionMessage("Lưu dữ liệu nội bộ chưa thành công.");
                return "error";
            }
            lstDulieuNt = daoMain.getDataKtnb_bieu02(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);

            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionMessage("Không có dữ liệu tại chi nhánh ((ko gửi api).");
                return "error";
            }
            List<DuLieuNTRow> lstData = new ArrayList<>();

            try {
                lstData = mapList(lstDulieuNt, "02_PCTN", userName, _reportDate);
            } catch (Exception e) {
                addActionMessage("Map dữ liệu lỗi DuLieuNTRow <> QT_DULIEU_NT.");
                return "error";
            }

            _service = new PCTNService();
            if (_service.saveDataKTKSNB("02_PCTN", pos_cd_username, Grade, _reportDate, userId, userId, lstData) == 0) {
                addActionMessage("Cập nhật Api lên Tw không thành công");
                return "error";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            addActionMessage("Cập nhật không thành công");
            return "error";
        }

        return "success";
    }

    public String save_data_ktnb_bieu03() throws SQLException {
        try {
            List<QT_DULIEU_NT> lstDL = new ArrayList<>();
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDL = _service.getDataKTKSNB("03_PCTN", pos_cd_username, Grade, _reportDate, "", "0");

            if (lstDL == null || lstDL.isEmpty()) {

            } else {

                if (lstDL.get(0).getD50().equals("3") || lstDL.get(0).getD50().equals("4") && lstDL.get(0).getCO_TONGHOP().equals("S")) {
                    addActionMessage("Cấp Tỉnh đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
                else if (lstDL.get(0).getD50().equals("4") && lstDL.get(0).getCO_TONGHOP().equals("M"))
                {
                    addActionMessage("Cấp TW đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
            }

            if (!daoMain.saveKTNB_bieu03("03_PCTN", userName, pos_cd_username, "", "", Integer.parseInt(reportGrade), lstDulieuNt,_reportDate)) {
                addActionMessage("Lưu dữ liệu nội bộ chưa thành công.");
                return "error";
            }
            lstDulieuNt = daoMain.getDataKtnb_bieu03(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);

            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionMessage("Không có dữ liệu tại chi nhánh ((ko gửi api).");
                return "error";
            }
            List<DuLieuNTRow> lstData = new ArrayList<>();

            try {
                lstData = mapList(lstDulieuNt, "03_PCTN", userName, _reportDate);
            } catch (Exception e) {
                addActionMessage("Map dữ liệu lỗi DuLieuNTRow <> QT_DULIEU_NT.");
                return "error";
            }

            _service = new PCTNService();
            if (_service.saveDataKTKSNB("03_PCTN", pos_cd_username, Grade, _reportDate, userId, userId, lstData) == 0) {
                addActionMessage("Cập nhật Api lên Tw không thành công");
                return "error";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            addActionMessage("Cập nhật không thành công");
            return "error";
        }

        return "success";
    }

    public String save_data_ktnb_bieu04() throws SQLException {
        try {
            List<QT_DULIEU_NT> lstDL = new ArrayList<>();
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDL = _service.getDataKTKSNB("04_PCTN", pos_cd_username, Grade, _reportDate, "", "0");

            if (lstDL == null || lstDL.isEmpty()) {

            } else {

                if (lstDL.get(0).getD50().equals("3") || lstDL.get(0).getD50().equals("4") && lstDL.get(0).getCO_TONGHOP().equals("S")) {
                    addActionMessage("Cấp Tỉnh đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
                else if (lstDL.get(0).getD50().equals("4") && lstDL.get(0).getCO_TONGHOP().equals("M"))
                {
                    addActionMessage("Cấp TW đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
            }

            if (!daoMain.saveKTNB_bieu04("04_PCTN", userName, pos_cd_username, "", "", Integer.parseInt(reportGrade), lstDulieuNt,_reportDate)) {
                addActionMessage("Lưu dữ liệu nội bộ chưa thành công.");
                return "error";
            }
            lstDulieuNt = daoMain.getDataKtnb_bieu04(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);

            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionMessage("Không có dữ liệu tại chi nhánh ((ko gửi api).");
                return "error";
            }
            List<DuLieuNTRow> lstData = new ArrayList<>();

            try {
                lstData = mapList(lstDulieuNt, "04_PCTN", userName, _reportDate);
            } catch (Exception e) {
                addActionMessage("Map dữ liệu lỗi DuLieuNTRow <> QT_DULIEU_NT.");
                return "error";
            }

            _service = new PCTNService();
            if (_service.saveDataKTKSNB("04_PCTN", pos_cd_username, Grade, _reportDate, userId, userId, lstData) == 0) {
                addActionMessage("Cập nhật Api lên Tw không thành công");
                return "error";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            addActionMessage("Cập nhật không thành công");
            return "error";
        }

        return "success";
    }

    public String save_data_ktnb_bieu05() throws SQLException {
        try {
            List<QT_DULIEU_NT> lstDL = new ArrayList<>();
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDL = _service.getDataKTKSNB("05_PCTN", pos_cd_username, Grade, _reportDate, "", "0");

            if (lstDL == null || lstDL.isEmpty()) {

            } else {

                if ( lstDL.get(0).getD50().equals("4")) {
                    addActionMessage("Cấp trên đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
            }

            if (!daoMain.saveKTNB_bieu05("05_PCTN", userName, pos_cd_username, "", "", Integer.parseInt(reportGrade), lstDulieuNt,_reportDate)) {
                addActionMessage("Lưu dữ liệu nội bộ chưa thành công.");
                return "error";
            }
            lstDulieuNt = daoMain.getDataKtnb_bieu05(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);

            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionMessage("Không có dữ liệu tại chi nhánh ((ko gửi api).");
                return "error";
            }
            List<DuLieuNTRow> lstData = new ArrayList<>();

            try {
                lstData = mapList(lstDulieuNt, "05_PCTN", userName, _reportDate);
            } catch (Exception e) {
                addActionMessage("Map dữ liệu lỗi DuLieuNTRow <> QT_DULIEU_NT.");
                return "error";
            }

            _service = new PCTNService();
            if (_service.saveDataKTKSNB("05_PCTN", pos_cd_username, Grade, _reportDate, userId, userId, lstData) == 0) {
                addActionMessage("Cập nhật Api lên Tw không thành công");
                return "error";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            addActionMessage("Cập nhật không thành công");
            return "error";
        }

        return "success";
    }

    public String save_data_ktnb_bieu06() throws SQLException {
        try {
            List<QT_DULIEU_NT> lstDL = new ArrayList<>();
            userName = request.getSession().getAttribute("username").toString();
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            posMainModel = listKTNBDA.get_pos_main_pos(userName, reportGrade);
            pos_cd_username = posMainModel.getPosCd();
            main_pos_username = posMainModel.getMainPosCd();
            Connection conn = new DaoConnect().getConnect();
            DaoKTNBMain daoMain = new DaoKTNBMain();
            _service = new PCTNService();
            String _reportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(ngayBC));
            lstDL = _service.getDataKTKSNB("06_PCTN", pos_cd_username, Grade, _reportDate, "", "0");

            if (lstDL == null || lstDL.isEmpty()) {

            } else {

                if (lstDL.get(0).getD50().equals("4")) {
                    addActionMessage("Cấp trên đã duyệt, bạn không thể sửa dữ liệu");
                    return "error";
                }
            }

            if (!daoMain.saveKTNB_bieu06("06_PCTN", userName, pos_cd_username, "", "", Integer.parseInt(reportGrade), lstDulieuNt,_reportDate)) {
                addActionMessage("Lưu dữ liệu nội bộ chưa thành công.");
                return "error";
            }
            lstDulieuNt = daoMain.getDataKtnb_bieu06(conn, quyBc, namBc, userName, Integer.parseInt(reportGrade), poscd);

            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionMessage("Không có dữ liệu tại chi nhánh ((ko gửi api).");
                return "error";
            }
            List<DuLieuNTRow> lstData = new ArrayList<>();

            try {
                lstData = mapList(lstDulieuNt, "06_PCTN", userName, _reportDate);
            } catch (Exception e) {
                addActionMessage("Map dữ liệu lỗi DuLieuNTRow <> QT_DULIEU_NT.");
                return "error";
            }

            _service = new PCTNService();
            if (_service.saveDataKTKSNB("06_PCTN", pos_cd_username, Grade, _reportDate, userId, userId, lstData) == 0) {
                addActionMessage("Cập nhật Api lên Tw không thành công");
                return "error";
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save_data_ktnb_bieu04: " + e.getMessage());
            addActionMessage("Cập nhật không thành công");
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

    private String getReportDate1(String sQuyBC, String sNamBC) {
        String ngay_bc = "";
        if (sQuyBC.equals("1")) {
            ngay_bc = "31-MAR-" + sNamBC;
        } else if (sQuyBC.equals("2")) {
            ngay_bc = "30-JUN-" + sNamBC;
        } else if (sQuyBC.equals("3")) {
            ngay_bc = "30-SEP-" + sNamBC;
        } else if (sQuyBC.equals("4")) {
            ngay_bc = "30-NOV-" + sNamBC;
        }
        return ngay_bc;
    }

    private List<DuLieuNTRow> mapList(List<QT_DULIEU_NT> lst, String key, String UserName, String sNgaybc) {
        try {
            SimpleDateFormat CvDate = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

            final String _reportDate = new SimpleDateFormat("yyyyMMdd").format(new SimpleDateFormat("dd-MMM-yyyy").parse(sNgaybc));

            int year = Integer.valueOf(_reportDate.substring(0, 4));
            reportGrade = request.getSession().getAttribute("reportGrade").toString();
            String Grade;
            if (reportGrade.equalsIgnoreCase("1")) {
                Grade = "S";
            } else if (reportGrade.equalsIgnoreCase("2")) {
                Grade = "M";
            } else {
                Grade = "H";
            }
            List<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            for (QT_DULIEU_NT tmp : lst) {
                if (tmp == null) {
                    continue;
                }

                DuLieuNTRow tempadd = new DuLieuNTRow();

                tempadd.setKey(tmp.getKHOA());
                tempadd.setOrderValue("");
                tempadd.setOrderDescription("");
                tempadd.setCode(tmp.getMA());
                tempadd.setMakerId(userName);
                tempadd.setAuthoriseId(userName);
                tempadd.setReportDate(CvDate.format(new SimpleDateFormat("dd-MMM-yyyy").parse(sNgaybc)));
                tempadd.setName(tmp.getTEN());
                tempadd.setReportYear(year);
                tempadd.setPosCode(tmp.getMAPGD());
                tempadd.setPosFlag(Grade);
                tempadd.setBranchCode(tmp.getMACN());
                tempadd.setD1(tmp.getD1());
                tempadd.setD2(tmp.getD2());
                tempadd.setD3(tmp.getD3());
                tempadd.setD4(tmp.getD4());
                tempadd.setD5(tmp.getD5());
                tempadd.setD6(tmp.getD6());
                tempadd.setD7(tmp.getD7());
                tempadd.setD8(tmp.getD8());
                tempadd.setD9(tmp.getD9());
                tempadd.setD10(tmp.getD10());
                tempadd.setD11(tmp.getD11());
                tempadd.setD12(tmp.getD12());
                tempadd.setD13(tmp.getD13());
                tempadd.setD14(tmp.getD14());
                tempadd.setD15(tmp.getD15());
                tempadd.setD16(tmp.getD16());
                tempadd.setD17(tmp.getD17());
                tempadd.setD18(tmp.getD18());
                tempadd.setD19(tmp.getD19());
                tempadd.setD20(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()));
                tempadd.setD21(tmp.getD21());
                tempadd.setD22(tmp.getD22());
                tempadd.setD23(tmp.getD23());
                tempadd.setD24(tmp.getD24());
                tempadd.setD25(tmp.getD25());
                tempadd.setD26(tmp.getD26());
                tempadd.setD27(tmp.getD27());
                tempadd.setD28(tmp.getD28());
                tempadd.setD29(tmp.getD29());
                tempadd.setD30(tmp.getD30());
                tempadd.setFontFormat(tmp.getFONTFORMAT());
                lstUpdateDate.add(tempadd);

            }
            return lstUpdateDate;
        } catch (Exception e) {
            return null;
        }

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
