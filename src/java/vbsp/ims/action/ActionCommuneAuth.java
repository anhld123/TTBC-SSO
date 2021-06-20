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
import java.io.File;
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
public class ActionCommuneAuth extends ActionSupport {

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

    @Override
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
            if(reportGrade.equals("2"))
                return INPUT;
        } catch (Exception e) {
            System.err.println("Loi trong ham execute " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " execute -> " + e.getMessage());
        }
          
        return SUCCESS;
    }

    public String loadDataCommuneAuth() {
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
            if(!reportGrade.equals("2"))
            {
                setMessage("Chỉ có cấp chi nhánh mới được xem dữ liệu này");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
                setMessage("Bạn phải chọn PGD  ");
                return ERROR;
            }
            if (poscd.size() > 1) {
                setMessage("Bạn chỉ được chọn 1 PGD ");
                return ERROR;
            }
//            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
//            System.err.println("sReportdt=" + sReportdt + " pos_cd=" + poscd.get(0));
            lstCommune = new DaoCommuneInput().getDataInputCommuneAuth(poscd.get(0), sReportdt);
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
            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
                setMessage("Bạn phải chọn PGD ");
                return ERROR;
            }
            int i_pos = new DaoCommuneInput().getPosNumber(sUserName);
//            if (poscd.size() != i_pos + 1) {
//                setMessage("Bạn phải chọn tất cả các PGD để duyệt");
//                return ERROR;
//            }
            String  lsPosErr = "";
            String  lsPosSuccess = "";
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
             if (poscd.size() == i_pos + 1) {
                lstCommuneErr = new DaoCommuneInput().getDataAuthErr_1(sUserName,sReportdt); 
                if(lstCommuneErr.size()>0)
                    return "notdata";
                for (int i=1;i <= poscd.size();i++)
                {
                    if(!new DaoCommuneInput().saveDataCommunAuth(poscd.get(i),sReportdt))
                    {
                        lsPosErr = lsPosErr + " - " + poscd.get(i);
                    }
                    lsPosSuccess = lsPosSuccess + " - " + poscd.get(i);
                }
                System.err.println("Bạn đã lưu dữ liệu thành công. Các PGD lỗi: " + lsPosErr);
                CoreLogger.error("Bạn đã lưu dữ liệu thành công. Các PGD lỗi: " + lsPosErr);
            }
             else if (poscd.size() == 1)
             {
                lstCommuneErr = new DaoCommuneInput().getDataAuthErr(poscd.get(0),sReportdt); 
                if(lstCommuneErr.size()>0)
                return "notdata";

                    if(!new DaoCommuneInput().saveDataCommunAuth(poscd.get(0),sReportdt))
                    {
                        lsPosErr = lsPosErr + " - " + poscd.get(0);
                    }

                System.err.println("Bạn đã lưu dữ liệu thành công. Các PGD lỗi: " + lsPosErr);
                CoreLogger.error("Bạn đã lưu dữ liệu thành công. Các PGD lỗi: " + lsPosErr);
             }
            else
             {
                 for (int i=0;i < poscd.size();i++){
                    lstCommuneErr = new DaoCommuneInput().getDataAuthErr(poscd.get(i),sReportdt); 
                    if(lstCommuneErr.size()>0)
                    return "notdata";                     
                 }
                for (int i=0;i < poscd.size();i++)
                {
                    if(!new DaoCommuneInput().saveDataCommunAuth(poscd.get(i),sReportdt))
                    {
                        lsPosErr = lsPosErr + " - " + poscd.get(i);
                    }
                    lsPosSuccess = lsPosSuccess + " - " + poscd.get(i);
                }
                System.err.println("Bạn đã lưu dữ liệu thành công. Các PGD lỗi: " + lsPosErr);
                CoreLogger.error("Bạn đã lưu dữ liệu thành công. Các PGD lỗi: " + lsPosErr);
             }
            
            lstCommuneResult = new DaoCommuneInput().getDataAuthResult(sUserName,sReportdt);           
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
    
    public String saveInputCommuneSend() {
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
                setMessage("Bạn phải chọn PGD");
                return ERROR;
            }
            String reportGrade = session.get("reportGrade").toString();
            if (reportGrade == null || reportGrade.isEmpty()) {
                setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                return ERROR;
            }
            
            int i_pos = new DaoCommuneInput().getPosNumber(sUserName);
            if (poscd.size() != i_pos + 1) {
                setMessage("Bạn phải chọn tất cả các PGD để gửi");
                return ERROR;
            }
//            Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
//            System.err.println("size="+lstCommune.size()+"poscd="+poscd.get(0));
             
            
            for (int i=1;i < poscd.size();i++)
            {
                int nTotalRow = new DaoCommuneInput().getCountTotalRowAuth(poscd.get(i), sReportdt);
                if(nTotalRow == -1)
                {
                    setMessage("Cần phải duyệt số PGD " +poscd.get(i)+" liệu trước khi gửi báo cáo");
                    return ERROR;
                }
                if(nTotalRow >=30)
                {
                    setMessage("Dữ liệu PGD " +poscd.get(i)+" đã được duyệt trước 30 ngày. Hãy xem lại và duyệt trước khi gửi báo cáo");
                    return ERROR;
                }
            } 
            String lsPos = new DaoCommuneInput().getCheckAllAuth(poscd.get(0), sReportdt);
            if(lsPos != null)
             {
                setMessage("PGD "  + lsPos +" yêu cầu gửi lại nhưng chưa gửi");
                return ERROR;
            }  
            for (int i=1;i < poscd.size();i++)
            {
                if(syn_data_HO(poscd.get(i),  sUserName,  sReportdt,reportGrade).equals(ERROR))
                {
                    setMessage("Các PGD lỗi: " + poscd.get(i));
                    return ERROR;
                }
                else if(syn_data_HO(poscd.get(i),  sUserName,  sReportdt,reportGrade).equals("send"))
                {
                    setMessage("CN đã gửi số liệu. Liên hệ với Hội sở chính nếu muốn gửi lại.");
                    return ERROR;
                }
            }
            setMessage("Bạn đã gửi thành công báo cáo về TW");
//            lstCommuneResult = new DaoCommuneInput().getDataAuthResult(sUserName);           
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
    
    public String syn_data_HO(String sPOS, String sUser, String sNgaybc, String sreportGrade)
    {
        try {   
                List<ModelCommune> mCommune =  new DaoCommuneInput().getDataInputCommuneAuthSend(sPOS,sNgaybc);
                if(mCommune.size() == 0)
                    return ERROR;
                //Lay duong dan va ten xml se ghi ra
                //String strPathSave = servletRequest.getRealPath("/") + "/" + Define.M_REPORT_XML
                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;
                strPathSave += sUser + "_VB819_" + sPOS + "_" + new SimpleDateFormat("ddMMMyyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt)) + ".xml";
                //Tao file xml theo cau truc
                String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
                new ImsReadWriteXmlFile().createXmlFileVB819(Define.PARA_SYN_REPORT_VB819, sUser, sreportGrade,
                        sPOS,sReportdt, mCommune, strPathSave);

                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String bSuccess = clientWritexml.SendFileXmlToWebServices819(strPathSave);
                File checkfile = new File(strPathSave);
                if (bSuccess.equals("fail")) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
                    setMessage("Bạn chưa gửi dữ liệu được về trung ương lỗi pos " + poscd);
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    return ERROR;
//                    sMessage = sMessage + "<tr><td style=\"text-align: center; color: red\">" + sPOS + "</td><td style=\"text-align: center; color: red\">Gửi dữ liệu lỗi</td><td style=\"text-align: center;color: red\">" + lstRisk.size() + "</td></tr>";
                } 
                else if(bSuccess.equals("send"))
                {   
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    return "send";
                }
                else {
//                    sMessage = sMessage + "<tr><td style=\"text-align: center;\">" + sPOS + "</td><td style=\"text-align: center;\">Thành công</td><td style=\"text-align: center;\">" + lstRisk.size() + "</td></tr>";
                }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " sendDataVB819 -> " + e.getMessage());
            return ERROR;
        }
        //System.err.println("Da gui du lieu thanh cong");
//        setMessage(sMessage);
        //setMessage("<span style=\"color:yellow\">Bạn đã gửi dữ liệu thành công của Phòng giao dịch </br>" + poscd+"</span>");
        return SUCCESS;
        }
    
    public String loadDataCommuneRejectCN() {
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
            if(!reportGrade.equals("2"))
            {
                setMessage("Chỉ có CN mới được thao tác dữ liệu này");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
                setMessage("Bạn phải chọn PGD để từ chối");
                return ERROR;
            }
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
//            System.err.println("sReportdt=" + sReportdt + " pos_cd=" + poscd.get(0));
//            lstCommune = new DaoCommuneInput().getDataInputCommuneAuth(poscd.get(0), sReportdt);
            int i_pos = new DaoCommuneInput().getPosNumber(sUserName);
            if(poscd.size()==1)
            {
                if(!new DaoCommuneInput().RejectAuth(reportGrade, poscd.get(0), sReportdt))
                {
                    setMessage("Từ chối lỗi PGD " + poscd.get(0));
                    return ERROR;   
                }
            }
            else if(poscd.size() < i_pos+1)
            {
                for (int i=0;i < poscd.size();i++)
                {
                    if(!new DaoCommuneInput().RejectAuth(reportGrade, poscd.get(i), sReportdt))
                    {
                        setMessage("Từ chối lỗi PGD " + poscd.get(i));
                        return ERROR;   
                    }
                }
            }
            else if (poscd.size() == i_pos+1)
            {
                for (int i=1;i < poscd.size();i++)
                {
                    if(!new DaoCommuneInput().RejectAuth(reportGrade, poscd.get(i), sReportdt))
                    {
                        setMessage("Từ chối lỗi PGD " + poscd.get(i));
                        return ERROR;   
                    }
                }
            }        
            
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadDataCommune -> " + e.getMessage());
        }
        setMessage("Từ chối thành công");
        return SUCCESS;

    }
    
    public String loadDataCommuneUnRejectCN() {
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
            if(!reportGrade.equals("2"))
            {
                setMessage("Chỉ có CN mới được thao tác dữ liệu này");
                return ERROR;
            }
            if (poscd == null || poscd.isEmpty() || poscd.size() == 0) {
                setMessage("Bạn phải chọn PGD để bỏ từ chối");
                return ERROR;
            }
            String sReportdt = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(report_dt));
//            System.err.println("sReportdt=" + sReportdt + " pos_cd=" + poscd.get(0));
//            lstCommune = new DaoCommuneInput().getDataInputCommuneAuth(poscd.get(0), sReportdt);
            int i_pos = new DaoCommuneInput().getPosNumber(sUserName);
            if(poscd.size()==1)
            {
                if(!new DaoCommuneInput().UnRejectAuth(reportGrade, poscd.get(0), sReportdt))
                {
                    setMessage("Bỏ từ chối lỗi PGD " + poscd.get(0));
                    return ERROR;   
                }
            }
            else if(poscd.size() < i_pos+1)
            {
                for (int i=0;i < poscd.size();i++)
                {
                    if(!new DaoCommuneInput().UnRejectAuth(reportGrade, poscd.get(i), sReportdt))
                    {
                        setMessage("Bỏ từ chối lỗi PGD " + poscd.get(i));
                        return ERROR;   
                    }
                }
            }
            else if (poscd.size() == i_pos+1)
            {
                for (int i=1;i < poscd.size();i++)
                {
                    if(!new DaoCommuneInput().UnRejectAuth(reportGrade, poscd.get(i), sReportdt))
                    {
                        setMessage("Bỏ từ chối lỗi PGD " + poscd.get(i));
                        return ERROR;   
                    }
                }
            }        
            
        } catch (Exception e) {
            System.err.println("Loi trong ham loadDataCommune " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " loadDataCommune -> " + e.getMessage());
        }
        setMessage("Từ chối thành công");
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
