/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.dao.DaoCommuneInput;
import vbsp.ims.dao.DaoProcessRisk;
import vbsp.ims.dao.DaoProcessVb819;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelCommuneAuth;
import vbsp.ims.model.ModelCommune;
import vbsp.ims.model.ModelCommuneAuthErr;
import vbsp.ims.model.ModelCommuneAuthResult;
import vbsp.ims.model.ModelCommuneRejectTW;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.ImsReadWriteXmlFile;

/**
 *
 * @author BAOANH
 */
public class ActionCommuneReject extends ActionSupport {

    //Khoi tao cho treeview
    private TreeNode nodes_pos = new TreeNode();
    //Cho message thong bao loi
    private String message;
    //Lay pos tren treeview
    private List<String> poscd;
    //khai bao cho truong date
    private String report_dt;


    //object data cho table
    private List<ModelCommuneRejectTW> lstCommuneRejectTw = new ArrayList<ModelCommuneRejectTW>();

    public List<ModelCommuneRejectTW> getLstCommuneRejectTw() {
        return lstCommuneRejectTw;
    }

    public void setLstCommuneRejectTw(List<ModelCommuneRejectTW> lstCommuneRejectTw) {
        this.lstCommuneRejectTw = lstCommuneRejectTw;
    }
    
    private List<ModelCommuneAuthResult> lstCommuneResult = new ArrayList<ModelCommuneAuthResult>();
    
    private List<ModelCommuneAuthErr> lstCommuneErr = new ArrayList<ModelCommuneAuthErr>();

    public List<ModelCommuneAuthErr> getLstCommuneErr() {
        return lstCommuneErr;
    }

    public void setLstCommuneErr(List<ModelCommuneAuthErr> lstCommuneErr) {
        this.lstCommuneErr = lstCommuneErr;
    }

    public List<ModelCommuneAuthResult> getLstCommuneResult() {
        return lstCommuneResult;
    }

    public void setLstCommuneResult(List<ModelCommuneAuthResult> lstCommuneResult) {
        this.lstCommuneResult = lstCommuneResult;
    }

    public String execute() {
        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới tạo được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

            System.err.println("execute sUserName=" + sUserName);

            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            String reportGrade = session.get("reportGrade").toString();
            //Lay ra du lieu cua xa, pos, chi nhanh dua vao user
            DaoCommuneInput dao819 = new DaoCommuneInput();
            List<ModelTreeNode> lstModelTree = dao819.getDataPosTreeNode(sUserName,reportGrade);

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
        } catch (Exception e) {
            System.err.println("Loi trong ham execute " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " execute -> " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadDataCommuneReject() {
        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới tạo được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

            System.err.println("execute sUserName=" + sUserName);

            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            String reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if(!reportGrade.equals("3"))
            {
                setMessage("Chỉ có Trung ương mới được xem dữ liệu này");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
                setMessage("Bạn phải chọn PGD để từ chối");
                return ERROR;
            }

//            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
//            System.err.println("sReportdt=" + sReportdt + " pos_cd=" + poscd.get(0));
//            lstCommune = new DaoCommuneInput().getDataInputCommuneAuth(poscd.get(0), sReportdt);
            for (int i=0;i < poscd.size();i++)
                {
                    if(new DaoCommuneInput().CheckRejectTW(poscd.get(i), sReportdt) == 0)
                    {
                        setMessage("CN " + poscd.get(i).substring(3, 4) + " chưa gửi dữ liệu");
                        return ERROR;   
                    }
                }
            for (int i=0;i < poscd.size();i++)
                {
                    if(!new DaoCommuneInput().RejectAuth(reportGrade, poscd.get(i), sReportdt) && !poscd.get(i).equals("000100"))
                    {
                        setMessage("Từ chối lỗi CN " + poscd.get(i));
                        return ERROR;   
                    }
                }
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadDataCommune -> " + e.getMessage());
        }
        setMessage("Từ chối thành công");
        return SUCCESS;

    }
    
    public String loadDataCommuneUnReject() {
        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới tạo được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

            System.err.println("execute sUserName=" + sUserName);

            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            String reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if(!reportGrade.equals("3"))
            {
                setMessage("Chỉ có Trung ương mới được xem dữ liệu này");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
                setMessage("Bạn phải chọn PGD để từ chối");
                return ERROR;
            }

//            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
            for (int i=0;i < poscd.size();i++)
                {
                    if(!new DaoCommuneInput().UnRejectAuth(reportGrade, poscd.get(i), sReportdt) && !poscd.get(i).equals("000100"))
                    {
                        setMessage("Bỏ từ chối lỗi CN " + poscd.get(i));
                        return ERROR;   
                    }
                }
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadDataCommune -> " + e.getMessage());
        }
        setMessage("Bỏ từ chối thành công");
        return SUCCESS;

    }
    
    public String loadDataCommuneCHECK() {
        try {
            Map session = ActionContext.getContext().getSession();

            if (session == null || session.size() == 0 || session.isEmpty()) {
                setMessage("Bạn phải đăng nhập lại mới tạo được báo cáo");
                return ERROR;
            }
            //lay ra user
            String sUserName = session.get("username").toString();

            System.err.println("execute sUserName=" + sUserName);

            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            String reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            if(!reportGrade.equals("3"))
            {
                setMessage("Chỉ có Trung ương mới được xem dữ liệu này");
                return ERROR;
            }
//            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
//                setMessage("Bạn phải chọn PGD để từ chối");
//                return ERROR;
//            }

//            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
//            System.err.println("sReportdt=" + sReportdt + " pos_cd=" + poscd.get(0));
//            lstCommune = new DaoCommuneInput().getDataInputCommuneAuth(poscd.get(0), sReportdt);
            lstCommuneRejectTw = new DaoCommuneInput().getDataCheckTW(sReportdt);
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadDataCommune -> " + e.getMessage());
        }
        return SUCCESS;

    }
    //<editor-fold defaultstate="collapsed" desc="Get set method">

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

//    public String getPoscd() {
//        return poscd;
//    }
//
//    public void setPoscd(String poscd) {
//        this.poscd = poscd;
//    }
    public List<String> getPoscd() {
        return poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    public String getReport_dt() {
        return report_dt;
    }

    public void setReport_dt(String report_dt) {
        this.report_dt = report_dt;
    }

    //</editor-fold>
}
