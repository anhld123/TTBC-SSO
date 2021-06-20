/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoExportHstdct;
import vbsp.ims.define.Define;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.zip.FileZip;

/**
 *
 * @author LION
 */
public class ExportHstdCtAction extends ActionSupport implements ServletRequestAware {

    private TreeNode nodes_pos = new TreeNode();
    private String message;
    private String poscd;
    private String export_date;
    private List<ListValue> lstModuleObj;
    private String module_table;
    private HttpServletRequest servletRequest;
    private String fileNamelocal;
    private String filereport;

    public String export_file() throws Exception {

//        System.err.println("Ngay day " + export_date + " pos cd day " + poscd);
//        System.err.println("Bang da chon " + module_table);
        String strPathSave = !servletRequest.getRealPath("/").endsWith("/")
                             ?servletRequest.getRealPath("/")+"/"+ Define.M_REPORT_TXT
                             :servletRequest.getRealPath("/")+ Define.M_REPORT_TXT;
//        System.err.println("Duong dan " + strPathSave);
        if (poscd == null || poscd.isEmpty()) {
            setMessage("Bạn chưa chọn pos cần tạo số liệu. Xin chọn pos cần tạo số liệu");
            return ERROR;
        }
        poscd = poscd.replace(" ", "");
        File Checkpath = new File(strPathSave);

        if (!Checkpath.exists()) {
            System.out.println("Da tao thu muc: " + strPathSave);
            Checkpath.mkdirs();
        }

        ArrayList<String> ArrlstPosCd = new ArrayList<String>(Arrays.asList(poscd.split(",")));
        String strDateExport = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(export_date));
//        System.err.println("Ngay khi da convert " + strDateExport.toString());

        //Mang luu nhung file da export ra
        ArrayList<String> ArrlstFile = new ArrayList<String>();
        DaoExportHstdct daoExp = new DaoExportHstdct();
        String strFullFile = "";
        HttpSession session = servletRequest.getSession();
        String strUserName = session.getAttribute("username").toString();
        if (module_table.equals("-1")) {

            lstModuleObj = (List<ListValue>) session.getAttribute("moduletable");

            for (int i = 0; i < ArrlstPosCd.size(); i++) {
                String strPosCd = ArrlstPosCd.get(i);
                if (strPosCd.equals("000000")) {
                    continue;
                }
                for (ListValue lstValue : lstModuleObj) {
                    String strTableID = lstValue.getsKey();
//                    System.err.println("Pos xuat du lieu " + strPosCd + " Bang du lieu " + strTableName);
                    strFullFile = daoExp.getDataExportFile(strPosCd, strDateExport, strTableID, strPathSave);
                    ArrlstFile.add(strFullFile);
                }
            }

        } else {
            for (int i = 0; i < ArrlstPosCd.size(); i++) {
                String strPosCd = ArrlstPosCd.get(i);
                if (strPosCd.equals("000000")) {
                    continue;
                }
                strFullFile = daoExp.getDataExportFile(strPosCd, strDateExport, module_table, strPathSave);
                ArrlstFile.add(strFullFile);
            }
        }
        //Zip file sau do cho nguoi dung tai ve
        String strDateExportFile = new SimpleDateFormat("ddMMyyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(export_date));

        String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
        filereport = "HSTDCT_" + strUserName + "_" + strCurrDate + "_" + strDateExportFile + ".zip";
        fileNamelocal = strPathSave + filereport;

        File fileOut = new File(fileNamelocal);
        if (fileOut.exists()) {
            System.err.println("File nay da co nen tao file moi " + fileNamelocal);
                //setMessage("Lỗi tìm thấy file dữ liệu đã xuất ra");
            //continue;
            int random = (int) (Math.random() * 50000 + 1);
            filereport = "HSTDCT_" + strUserName + "_" + strCurrDate + "_" + strDateExportFile + "_" + Integer.toString(random) + ".zip";
            fileNamelocal = strPathSave + filereport;
        }
        FileZip.ZipFileFromArray(ArrlstFile, fileNamelocal);
        return SUCCESS;
    }

    public String execute() throws Exception {

//        System.err.println("Khoi tao treee----");
        //tao ra bang hashmap luu tru pos
        HashMap<String, ArrayList<String>> hmPos = new HashMap<String, ArrayList<String>>();
        //tao ra bang map luu tru bang du lieu load len
        HashMap<String, String> hmModuleTable = new HashMap<String, String>();
        lstModuleObj = new ArrayList<ListValue>();

        //Lay ra user name da luu trong session
        HttpSession session = servletRequest.getSession();
        String strUserName = session.getAttribute("username").toString();

        DaoExportHstdct exporthstdct = new DaoExportHstdct();
        hmPos = exporthstdct.getPosExportHstdct(strUserName);
        hmModuleTable = exporthstdct.getModuleExportHstdct();
        
        
        //Add gia tri bang len select
        for (String key : hmModuleTable.keySet()) {
            lstModuleObj.add(new ListValue(key, hmModuleTable.get(key)));
//            System.err.println("Gia tri " + hmModuleTable.get(key));
        }
//        TreeNode nodeChild = new TreeNode();

        //HttpSession session = servletRequest.getSession();
        //Luu lai module trong session
        session.setAttribute("moduletable", lstModuleObj);
        ArrayList<String> ArrlstPoscd = hmPos.get("POS_CD");
        ArrayList<String> ArrlstPosDesc = hmPos.get("POS_DESC");

        if (ArrlstPosDesc.isEmpty() || ArrlstPoscd.isEmpty()) {
            System.err.println("Loi khi lay pos");
            setMessage("Lỗi không thể lấy ra được pos từ user này");
            return ERROR;
        }
        //Add du lieu len TreeNode cần sửa lại để lấy dữ liệu được theo tỉnh, theo trung ương
//        nodes_pos.setId("HOISOTINH");
//        nodes_pos.setTitle("Hội sở tỉnh");
//        nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
//        nodes_pos.setChildren(new LinkedList<TreeNode>());

        for (int i = 0; i < ArrlstPoscd.size(); i++) {
            String strPos_key = ArrlstPoscd.get(i);
            if (strPos_key.equals("000000")) {
                nodes_pos.setId(strPos_key);
                nodes_pos.setTitle(ArrlstPosDesc.get(i));
                nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                nodes_pos.setChildren(new LinkedList<TreeNode>());
            } else {
                TreeNode nodeChild = new TreeNode();
                nodeChild.setId(strPos_key);
                nodeChild.setTitle(ArrlstPosDesc.get(i));
//                System.err.println(ArrlstPosDesc.get(i));
                nodes_pos.getChildren().add(nodeChild);
            }
        }
        return SUCCESS;
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

    public String getExport_date() {
        return export_date;
    }

    public void setExport_date(String export_date) {
        this.export_date = export_date;
    }

    public List<ListValue> getLstModuleObj() {
        return lstModuleObj;
    }

    public void setLstModuleObj(List<ListValue> lstModuleObj) {
        this.lstModuleObj = lstModuleObj;
    }

    public void setServletRequest(HttpServletRequest servletRequest) {
        this.servletRequest = servletRequest;
    }

    public String getModule_table() {
        return module_table;
    }

    public void setModule_table(String module_table) {
        this.module_table = module_table;
    }

    public String getFileNamelocal() {
        return fileNamelocal;
    }

    public void setFileNamelocal(String fileNamelocal) {
        this.fileNamelocal = fileNamelocal;
    }

    public String getFilereport() {
        return filereport;
    }

    public void setFilereport(String filereport) {
        this.filereport = filereport;
    }

}
