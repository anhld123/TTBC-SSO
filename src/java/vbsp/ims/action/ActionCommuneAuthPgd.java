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
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.ImsReadWriteXmlFile;

/**
 *
 * @author BAOANH
 */
public class ActionCommuneAuthPgd extends ActionSupport {

    //Khoi tao cho treeview
    private TreeNode nodes_pos = new TreeNode();
    //Cho message thong bao loi
    private String message;
    //Lay pos tren treeview
    private List<String> poscd;
    //khai bao cho truong date
    private String report_dt;
    
    

    //object data cho table
    private List<ModelCommuneAuth> lstCommune = new ArrayList<ModelCommuneAuth>();
    
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

    public String loadDataCommuneAuthPgd() {
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
            if(!reportGrade.equals("1"))
            {
                setMessage("Chỉ có cấp PGD mới được xem dữ liệu này");
                return ERROR;
            }
//            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
//                setMessage("Bạn phải chọn xã cần nhập chỉ tiêu ");
//                return ERROR;
//            }
//            if (poscd.size() > 1) {
//                setMessage("Bạn chỉ được chọn 1 xã để nhập chỉ tiêu ");
//                return ERROR;
//            }
//            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
//            System.err.println("sReportdt=" + sReportdt + " pos_cd=" + poscd.get(0));
            lstCommune = new DaoCommuneInput().getDataInputCommuneAuthPgd(sUserName, sReportdt);
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadDataCommune -> " + e.getMessage());
        }
        return SUCCESS;

    }

    public String saveInputCommuneAuthPgd() {
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
//            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
//                setMessage("Bạn phải chọn xã cần nhập chỉ tiêu ");
//                return ERROR;
//            }
//            int i_pos = new DaoCommuneInput().getPosNumber(sUserName);
//            if (poscd.size() != i_pos + 1) {
//                setMessage("Bạn phải chọn tất cả các PGD để duyệt");
//                return ERROR;
//            }
            String  lsPosErr = "";
            String  lsPosSuccess = "";
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
            
            lstCommuneErr = new DaoCommuneInput().getDataAuthErr_1(sUserName,sReportdt); 
                if(lstCommuneErr.size()>0)
                    return "notdata";
                
            int iSend = new DaoCommuneInput().getCountsResendPGD(sUserName, sReportdt);
            if(iSend>0)
            {
                setMessage("Dữ liệu đã được duyệt. Nếu có chỉnh liên hệ với cấp trên.");
                return ERROR;
            }
            
            
            if(!new DaoCommuneInput().saveDataCommunAuthPgd(sUserName,sReportdt))
               setMessage("Duyệt không thành công");
            
            lstCommuneResult = new DaoCommuneInput().getDataAuthResultPGD(sUserName,sReportdt);           
//            for(ModelCommuneAuth commune:lstCommune)
//                System.err.println("sCode="+commune.getsCode()+" sSubCode="+commune.getsSubcode()+" sDecription="+commune.getsDescription());
        } catch (Exception e) {
            System.err.println("Loi trong ham saveInputCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveInputCommune -> " + e.getMessage());
            setMessage("Lỗi bạn chưa lưu được số liệu xin kiểm tra lại");
            return ERROR;
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

    public List<ModelCommuneAuth> getLstCommune() {
        return lstCommune;
    }

    public void setLstCommune(List<ModelCommuneAuth> lstCommune) {
        this.lstCommune = lstCommune;
    }

    public String getReport_dt() {
        return report_dt;
    }

    public void setReport_dt(String report_dt) {
        this.report_dt = report_dt;
    }

    //</editor-fold>
}
