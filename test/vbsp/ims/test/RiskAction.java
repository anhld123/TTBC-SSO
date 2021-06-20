/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.test;

import vbsp.ims.action.*;
import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.define.Define;
import vbsp.ims.model.ModelRiskProcess;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.Pagination;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author LION
 */
public class RiskAction extends ActionSupport implements  ModelDriven<ModelRiskProcess> {

    //List load du lieu cho table hien thi du lieu khi phe duyet
    private List<ModelRiskProcess> lstTableRiskObj;
    //So ku cua khach hang khi nsd check vao chon
    public String check_legacyid;

    private String times_risk;//Lan xu ly no
    private String status_risk; //Trang thai xu ly
    private String group_risk; //Nhom no can xu ly
    //private String poscd;       //Ma xa, huyen, tinh chon load du lieu
    private String date_risk;//Ngay so lieu
    //cho form tu day

    //Khoi tao cho treeview
    private TreeNode nodes_pos = new TreeNode();
    //Cho message thong bao loi
    private String message;
    //Lay pos tren treeview
    private String poscd;
    //Ngay hoac nam bao cao
    //private String export_date;
//    private List<ListValue> lstModuleObj;
//    private String module_table;
    //Cho lop phan trang khoi tao ban dau la 6 row tren table
    private Pagination pagination = new Pagination(10, 1);
    ModelRiskProcess modelRisk = new ModelRiskProcess();

    //Khoi tao cho cac dieu kien du lieu
    public String execute() throws Exception {
        //Lay ra user tu sesstion
         //lay ra session
            Map session = ActionContext.getContext().getSession();
            
            if(session==null)
            {
                 setMessage("Bạn phải đăng nhập lại mới tạo được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

            System.err.println("sUserName="+sUserName);
            
        if (sUserName == null || sUserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return ERROR;
        }
        //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
        DaoProcessRisk daoRisk = new DaoProcessRisk();
        List<ModelTreeNode> lstModelTree = daoRisk.getDataPosTreeNode(sUserName);

        //Khoi tao cho treenode
        for (int i = 0; i < lstModelTree.size(); i++) {
            //String strPos_key = ArrlstPoscd.get(i);
            ModelTreeNode modelTree = lstModelTree.get(i);
            //Neu la row dau tien thi la node root
            if (i == 0) {
                nodes_pos.setId(modelTree.getStrParentCd());
                nodes_pos.setTitle(modelTree.getStrParentDesc());
                nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                nodes_pos.setChildren(new LinkedList<TreeNode>());
            }
            //Khoi tao cho node child
            TreeNode nodeChild = new TreeNode();
            nodeChild.setId(modelTree.getStrChildCd());
            nodeChild.setTitle(modelTree.getStrChildDesc());
//                System.err.println(ArrlstPosDesc.get(i));
            nodes_pos.getChildren().add(nodeChild);

        }
        return SUCCESS;
    }

    //Xu ly cho button tai du lieu
    public String process_risk() throws Exception {
//        System.err.println("Mang lay ra day " + date_risk);
//        System.err.println("Check da chon la " + poscd);
//        System.err.println("Dot xu ly " + times_risk);
//        System.err.println("Trang thai " + status_risk);
//        System.err.println("nhom no " + group_risk);
        DaoProcessRisk daoRisk = new DaoProcessRisk();
        ArrayList<String> ArrlstPosCd = new ArrayList<String>();
        //Xu ly cho poscd
        //DO khi chon pos se ra 1 danh sach : pos1, pos2, pos3...
        //Sẽ cat pos nay ra sau do dua vao List
        if (poscd != null && !poscd.isEmpty()) {
            poscd = poscd.replace(" ", "");
            //Dua tu chuoi pos ve dang mang
            ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
        }
        //Do phần nhóm rủi ro có nhóm nợ khoanh sẽ là 02,03 nên sẽ đưa về chuỗi dạng '02','03' sau đó truyền vao
        //procedure để lấy in của chuỗi đó
        if (group_risk != null && group_risk.indexOf(",") > 0) {
//            ArrayList<String> ArrlstGroupRisk = new ArrayList<String>(Arrays.asList(group_risk.split(",")));
            group_risk = group_risk.replace(" ", "");
            group_risk = group_risk.replace(",", "','");
        }

//        System.err.println("Group khi da chuan hoa " + group_risk);
        //Can lai dinh dang ngay bao cao ve dd-MMM-yyyy
        String strDateRisk = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(date_risk));
        //Lay ra user hien tai dang dang nhap he thong
         Map session = ActionContext.getContext().getSession();
            
            if(session==null)
            {
                 setMessage("Bạn phải đăng nhập lại mới tạo được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
        if (sUserName == null || sUserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return ERROR;
        }
        Connection conn = null;

        //connect lớp Dao sau đó get số liệu
        DaoConnect daoconnect = new DaoConnect();
        conn = daoconnect.getConnect();
        if (conn == null) {
            setMessage("Không thể kết nối cơ sở dữ liệu ");
            return ERROR;
        }
        //Lay ra tổng số bản ghi của du liêu để phân trang
        if (pagination.getStart() == 0) {
//            System.err.println("Thiet lap de lay tong so row data " + pagination.getStart() + " thang end " + pagination.getEnd());
//            int nTotalRow = daoRisk.getCountTotalRowRisk(conn, sUserName, ArrlstPosCd, strDateRisk, times_risk, status_risk, group_risk);
//            pagination.setPreperties(nTotalRow);
        }
        //Lay du lieu dua ra list table
//        lstTableRiskObj = daoRisk.getDataRisk(conn, sUserName, ArrlstPosCd, strDateRisk, times_risk, status_risk,
//                group_risk,"01", pagination.getStart() + 1, pagination.getStart() + pagination.getEnd());

        //Dua so dong du lieu len table
        pagination.setPage_records(lstTableRiskObj.size());
        //Dong csdl
        if (!conn.isClosed()) {
            conn.close();
//            System.err.println("Conn da close ");
        }
//        System.err.println("Pos da chon nao--------------- " + poscd);
        return SUCCESS;
    }

    //Xu ly cho button duyet du lieu

    public String browse_risk() throws Exception {
//        System.err.println("Du lieu da check " + check_legacyid+" trang thai rui ro "+status_risk);

        if (check_legacyid == null || check_legacyid.isEmpty()) {
            setMessage("Bạn phải chọn khách hàng cần duyệt ");
            return ERROR;
        }

        if (!status_risk.equals("1")) {
            setMessage("Bạn phải chọn trạng thái xử lý là chờ phê duyệt và tải lại dữ liệu ");
            return ERROR;
        }
        //Đưa mảng số ku về 1 list
        check_legacyid = check_legacyid.replace(" ", "");
        //Dua tu chuoi pos ve dang mang
        ArrayList<String> ArrlstLegacyID = new ArrayList<String>();
        ArrlstLegacyID = new ArrayList<String>(Arrays.asList(check_legacyid.split(",")));
        DaoProcessRisk daoRisk = new DaoProcessRisk();
        //Update trang thang cho phe duyet là 1 len trang thai phe duyet là 2
//        daoRisk.setStatusRisk(ArrlstLegacyID, "2");
        String strStringLagecyId = "";
        for (int i = 0; i < ArrlstLegacyID.size(); i++) {
            if (i != ArrlstLegacyID.size() - 1) {
                strStringLagecyId += "\"" + ArrlstLegacyID.get(i).toString() + "\",";
            } else {
                strStringLagecyId += "\"" + ArrlstLegacyID.get(i).toString() + "\"";
            }
        }
        //Tungnv them de dong bo bao ca ve tw
       Map session = ActionContext.getContext().getSession();
            
            if(session==null)
            {
                 setMessage("Bạn phải đăng nhập lại mới tạo được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();
        //Lay cap bao cao
        String strGrade = session.get("reportGrade").toString();
        if (!strGrade.equals("3")) {
            //Lay duong dan va ten xml se ghi ra
            //String strPathSave = servletRequest.getRealPath("/") + "\\" + Define.M_REPORT_XML
            ServletContext context = ServletActionContext.getServletContext();
            String strPathSave = !context.getRealPath("/").endsWith("/")
                      ?context.getRealPath("/")+"/"+ Define.M_REPORT_XML
                      :context.getRealPath("/")+ Define.M_REPORT_XML;
            strPathSave+= sUserName + "_XLRR_"+Long.toString(System.currentTimeMillis())+".xml";
            //Tao file xml theo cau truc
            ProcessReportSyn clientWritexml = new ProcessReportSyn();
            boolean bSuccess = clientWritexml.SendFileXmlToWebServices(Define.PARA_SYN_REPORT_XLRR,
                    "IDBAOCAO", "NGAYBC", sUserName, "POSCD", strGrade, "XLRR", strStringLagecyId, "QUYBC", strPathSave);
            if (!bSuccess) {
                System.err.println("Ban chua dong bo du lieu duoc ve TW");
            }
        }
        setMessage("Bạn đã phê duyệt thành công");
        return SUCCESS;
    }

    public List<ModelRiskProcess> getLstTableRiskObj() {
        return lstTableRiskObj;
    }

    public void setLstTableRiskObj(List<ModelRiskProcess> lstTableRiskObj) {
        this.lstTableRiskObj = lstTableRiskObj;
    }

    

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPoscd() {
        return poscd;
    }

    public void setPoscd(String poscd) {
        this.poscd = poscd;
    }

    public String getTimes_risk() {
        return times_risk;
    }

    public void setTimes_risk(String times_risk) {
        this.times_risk = times_risk;
    }

    public String getStatus_risk() {
        return status_risk;
    }

    public void setStatus_risk(String status_risk) {
        this.status_risk = status_risk;
    }

    public String getGroup_risk() {
        return group_risk;
    }

    public void setGroup_risk(String group_risk) {
        this.group_risk = group_risk;
    }

    public String getDate_risk() {
        return date_risk;
    }

    public void setDate_risk(String date_risk) {
        this.date_risk = date_risk;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public ModelRiskProcess getModelRisk() {
        return modelRisk;
    }

    public void setModelRisk(ModelRiskProcess modelRisk) {
        this.modelRisk = modelRisk;
    }

    @Override
    public ModelRiskProcess getModel() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        return modelRisk;
    }

    public String getCheck_legacyid() {
        return check_legacyid;
    }

    public void setCheck_legacyid(String check_legacyid) {
        this.check_legacyid = check_legacyid;
    }

}
