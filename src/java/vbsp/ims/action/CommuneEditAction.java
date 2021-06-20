/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action;

import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.CommuneDao;
import vbsp.ims.dao.IMSRptDao;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.Commune;
import vbsp.ims.model.CommuneManager;
import vbsp.ims.model.RefObject;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;

/**
 *
 * @author Trung
 */
public class CommuneEditAction extends ActionSupport
        implements ServletRequestAware {

    private static final long serialVersionUID = 5078264277068533593L;
    //--------------------------------------------------------------------------
    private Integer rows = 0;
    private Integer page = 0;
    private String sord;
    private String sidx;
    // Search Field
    private String searchField;
    // The Search String
    private String searchString;
    // he Search Operation
    // ['eq','ne','lt','le','gt','ge','bw','bn','in','ni','ew','en','cn','nc']
    private String searchOper;
    private Integer total = 0;
    private Integer records = 0;

    private HttpServletRequest request;
    private List<Commune> communes = new ArrayList<>();
    
    private List<ListValue> userList = new ArrayList<>();
    private List<ListValue> yesnoList = new ArrayList<>();

    static CommuneManager communeManager;
    static String session_id;
    
    
    static int view_page = 1;
    static int view_rows = 30;
    

    //--------------------------------------------------------------------------
    private int id;
    private String oper;
    private String commune_name;
    private String nongthonmoi_flg;    
    private String canbotdpt;
//    private String disabled = "test" ;// "style='float:right; padding-top: 5px;'";    
    private String message;
    
//    private String permit;

    @Override
    public String execute() {
        int reportGrade = Integer.parseInt(
                request.getSession().getAttribute("reportGrade").toString());                
        String userName = request.getSession().getAttribute("username").toString();
        String permit = request.getSession().getAttribute("permit").toString();
         
//            if (!permit.isEmpty())
//                disabled = isDisable(permit,2);
        
        if (session_id == null || session_id.isEmpty()) {
            session_id = (String) request.getSession().getId();
        }
        if (oper != null && oper.equalsIgnoreCase("edit")) {

            if (communeManager == null) {
                communeManager = new CommuneManager(userName, reportGrade);
            }
            Commune commune = communeManager.getCommune(
                    (id + (view_page-1)*view_rows) - 1);

//            commune.setCommune_name(commune_name);
            System.err.println("Aaa"+nongthonmoi_flg+canbotdpt);
            
            commune.setNongthonmoi_flg(nongthonmoi_flg);
            commune.setCanbotdpt(canbotdpt);

            CommuneDao cmDao = new CommuneDao();
            boolean update_status = cmDao.updateCommune_tmp(
                    userName, reportGrade, commune);

            if (update_status) {
                System.err.println("Update commune success...");
            } else {
                System.err.println("Update commune fail...");
            }
            return SUCCESS;
        } else {
            int to = (rows * page);
            int from = to - rows;

            view_page = page;
            view_rows = rows;
            String newsession_id = (String) request.getSession().getId();
                       

            if (communeManager == null || !newsession_id.equals(session_id)) {
                communeManager = new CommuneManager(userName, reportGrade);
            }
            records = communeManager.getTotalCount();
            communes = communeManager.find(from, to);
            total = (int) Math.ceil((double) records / (double) rows);
            return SUCCESS;
        }
    }
    
    public String update_data(){
        String userName = 
                request.getSession().getAttribute("username").toString();
        String pos_cd = IMSRptDao.getPosOfUser(userName);
        vbsp.ims.model.RefObject refObj = new RefObject();
        boolean update_status = sync_data(pos_cd,refObj);
        if (update_status)
            message = "(*)Tạo và cập nhật dữ liệu thành công. Số bản ghi được cập nhật [" 
                    + refObj.getInt01() + "]";
        else
            message = "(*)Tạo và cập nhật dữ liệu thất bại."
                    + "(*Có thể do không có xã nào được thay đổi thông tin) ";
        return SUCCESS;
    }
            

    //PHẦN ĐỒNG BỘ SỐ LIỆU VỀ HSC
    private boolean sync_data(String sync_pos_cd, 
            vbsp.ims.model.RefObject refObj) {
        //DONG BO DU LIEU LEN TW
        try {
            int reportGrade = Integer.parseInt(
                    request.getSession().getAttribute("reportGrade").toString());
            String reportDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
            
            if (reportGrade != 3) {
                
                //Lay duong dan va ten xml se ghi ra
                String pathSave = !request.getRealPath("/").endsWith("/")
                        ? request.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : request.getRealPath("/") + Define.M_REPORT_XML;
                pathSave += sync_pos_cd + "_dmxa_" + reportDate + ".xml";                                  

                CommuneDao cmDao = new CommuneDao();
                
                List<Commune> lv_communes  =                         
                        cmDao.getCommuneList_sync(sync_pos_cd);
                
                
                if (lv_communes.size() <= 0) {
                    return false;
                }
                
                refObj.setInt01(lv_communes.size());

                //Tao file xml theo cau truc
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                boolean bSuccess = clientWritexml.SendFileXmlToWebServices_Dmxa(
                        sync_pos_cd, lv_communes, pathSave);
                if (!bSuccess) {
                    //message = "Dữ liệu đồng bộ về HSC thất bại...";
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            CoreLogger.error(this.getClass().getName() + " Dữ liệu đồng bộ về HSC thất bại " + e.getMessage());
            return false;
        }
        return true;
    }

    
    // Bo sung phan chon drop-down user 
    public String getDropdownUser(){
//        userList.add(new ListValue("user1","user1-Nguyen Van A"));
//        userList.add(new ListValue("user2","user2-Nguyen Van B"));
        CommuneDao dao = new CommuneDao();
        String userName = 
                request.getSession().getAttribute("username").toString();
        String pos_cd = IMSRptDao.getPosOfUser(userName);
        userList = dao.getSeller(pos_cd);
        return SUCCESS;
    }
    
    public String getYesNoList(){
        yesnoList.add(new ListValue("Y","Y"));
        yesnoList.add(new ListValue("N","N"));
        return SUCCESS;
    }
    
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public List<Commune> getCommunes() {
        return communes;
    }

    public void setCommunes(List<Commune> communes) {
        this.communes = communes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommune_name() {
        return commune_name;
    }

    public void setCommune_name(String commune_name) {
        this.commune_name = commune_name;
    }

    public String getNongthonmoi_flg() {
        return nongthonmoi_flg;
    }

    public void setNongthonmoi_flg(String nongthonmoi_flg) {
        this.nongthonmoi_flg = nongthonmoi_flg;
    }

    public String getOper() {
        return oper;
    }

    public void setOper(String oper) {
        this.oper = oper;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
        
    
    public String isDisable(String permit, int permit_type){
        if (permit.substring(permit_type-1, permit_type).equals("1"))
            return "style='float:right; padding-top: 5px;'";
        else
            return "style='float:right; padding-top: 5px;display: none;'";
    }

    public List<ListValue> getUserList() {
        return userList;
    }

    public void setUserList(List<ListValue> userList) {
        this.userList = userList;
    }

    public List<ListValue> getYesnoList() {
        return yesnoList;
    }

    public void setYesnoList(List<ListValue> yesnoList) {
        this.yesnoList = yesnoList;
    }

    public String getCanbotdpt() {
        return canbotdpt;
    }

    public void setCanbotdpt(String canbotdpt) {
        this.canbotdpt = canbotdpt;
    }

    

   
    
    
    
}
