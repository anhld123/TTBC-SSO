/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import vbsp.ims.dao.DaoCommuneInput;
import vbsp.ims.dao.DaoProcessVb819;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelCommune;
import vbsp.ims.model.ModelTreeNode;

/**
 *
 * @author BAOANH
 */
public class ActionCommuneInput extends ActionSupport {

    //Khoi tao cho treeview
    private TreeNode nodes_pos = new TreeNode();
    //Cho message thong bao loi
    private String message;
    //Lay pos tren treeview
    private List<String> poscd;
    //khai bao cho truong date
    private String report_dt;

    //object data cho table
    private List<ModelCommune> lstCommune = new ArrayList<ModelCommune>();

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
            String reportGrade = session.get("reportGrade").toString();

            if (sUserName == null || sUserName.isEmpty()) {
                setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
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

    public String loadDataCommune() {
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
                setMessage("Chỉ tiêu này chỉ nhập ở cấp Phòng giao dịch ");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
                setMessage("Bạn phải chọn xã cần nhập chỉ tiêu ");
                return ERROR;
            }
            if (poscd.size() > 1 && !poscd.get(0).equals("004709")) {
                setMessage("Bạn chỉ được chọn 1 xã để nhập chỉ tiêu ");
                return ERROR;
            }
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
            Calendar calendar = Calendar.getInstance();  
                calendar.setTime(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));  

                calendar.add(Calendar.MONTH, 1);  
                calendar.set(Calendar.DAY_OF_MONTH, 1);  
                calendar.add(Calendar.DATE, -1);  

                Date lastDayOfMonth = calendar.getTime();  
            String sReportdt1 = new SimpleDateFormat("dd-MMM-yyyy").format(lastDayOfMonth);  
            
            if (!sReportdt.equals(sReportdt1))
            {
                setMessage("Bạn phải chọn gày cuối tháng để nhập liệu");
                return ERROR;
            }

//            int nTotalRow = new DaoCommuneInput().getCountTotalRow(poscd.get(0), sReportdt);
//            if(nTotalRow == 0)
//                {
//                setMessage("Dữ liệu chưa được đồng bộ về CN");
//                return ERROR;
//            }
                
//            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
            
//            System.err.println("sReportdt=" + sReportdt + " pos_cd=" + poscd.get(0));
            lstCommune = new DaoCommuneInput().getDataInputCommune(sUserName, poscd.get(0), sReportdt);
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadDataCommune -> " + e.getMessage());
        }
        return SUCCESS;

    }

    public String saveInputCommune() {
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
            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
                setMessage("Bạn phải chọn xã cần nhập chỉ tiêu ");
                return ERROR;
            }
            if (poscd.size() > 1) {
                setMessage("Bạn phải chỉ được chọn 1 xã cần nhập chỉ tiêu ");
                return ERROR;
            }
            
//            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));                
            
            System.err.println("size="+lstCommune.size()+"poscd="+poscd.get(0));
            new DaoCommuneInput().saveDataCommune(sUserName, poscd.get(0).replace("[", "").replace("]", ""), sReportdt, lstCommune);
            setMessage("Bạn đã lưu dữ liệu thành công");
//            for(ModelCommune commune:lstCommune)
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

    public List<ModelCommune> getLstCommune() {
        return lstCommune;
    }

    public void setLstCommune(List<ModelCommune> lstCommune) {
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
