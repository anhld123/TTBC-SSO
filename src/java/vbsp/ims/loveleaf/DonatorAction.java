/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.loveleaf;

import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.eom_help.EOMTaskHelpDao;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author Trung
 */
public class DonatorAction extends ActionSupport
        implements
        ServletRequestAware {

    private HttpServletRequest request;
    Map parameters = null;

    private List<DonateTransaction> donateTransaction
            = new ArrayList<>();

    private String tran_dt;
    private String from_dt;
    private String username;
    private String permit;
    private String gendata_FLG;
    private String period;
    private String program;
    
    private List<ListValue> periods;
    private List<ListValue> programs;

//    private Donator donator;
    @Override
    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public String execute() {        
        username = request.getSession().getAttribute("username").toString();
        permit = getParameterValue("permit");
        LoveLeafDao loveleafDao = new LoveLeafDao();
        System.err.println("DonatorAction~" + period + gendata_FLG+tran_dt);        
        if (gendata_FLG== null || gendata_FLG.isEmpty()){
            gendata_FLG = "N";
        }
        if (tran_dt != null && !tran_dt.isEmpty()) {            
            donateTransaction = loveleafDao.list_donate_trans(
                    program,
                    DefineFun.convert2OracleDateFormat(tran_dt),
                    period,
                    DefineFun.convert2OracleDateFormat(from_dt),
                    gendata_FLG
            );
        }
        return SUCCESS;
    }
         
    
    public String build_period_combo() {
        EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        periods = eomTaskDao.list_all_period("LOVELEAF");
        return SUCCESS;
    }
    
    
    public String buildProgramCombo() {
        //EOMTaskHelpDao eomTaskDao = new EOMTaskHelpDao();
        //periods = eomTaskDao.list_all_period("LOVELEAF");
        programs = new ArrayList<> ();
        programs.add(new ListValue(Define.LOVE_LEAF_PROGRAM, "Cặp lá yêu thương"));
        programs.add(new ListValue(Define.VVC_PROGRAM, "Nối vòng tay thương"));
        return SUCCESS;
    }

//    public String load_donator(){
//        String maker_id = getParameterValue("username");
//        String maker_dt = getParameterValue("report_dt");
//        String donator_id = getParameterValue("donator_id"); 
//        
//        LoveLeafDao loveleafDao = new LoveLeafDao();
//        donator = loveleafDao.getDonator(donator_id);        
//        return SUCCESS;
//    }
    public List<DonateTransaction> getDonateTransaction() {
        return donateTransaction;
    }

    public void setDonateTransaction(List<DonateTransaction> donateTransaction) {
        this.donateTransaction = donateTransaction;
    }

    public String getTran_dt() {
        return tran_dt;
    }

    public void setTran_dt(String tran_dt) {
        this.tran_dt = tran_dt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

//    public Donator getDonator() {
//        return donator;
//    }
//
//    public void setDonator(Donator donator) {
//        this.donator = donator;
//    }
    public String getParameterValue(String param) {
        Object paramObj = getParameters().get(param);
        if (paramObj == null) {
            return null;
        }
        return ((String[]) paramObj)[0];
    }

    public final Map getParameters() {
        parameters = ActionContext.getContext().getParameters();
        return parameters;
    }

    public String getPermit() {
        return permit;
    }

    public void setPermit(String permit) {
        this.permit = permit;
    }

    

    public List<ListValue> getPeriods() {
        return periods;
    }

    public void setPeriods(List<ListValue> periods) {
        this.periods = periods;
    }

    public String getGendata_FLG() {
        return gendata_FLG;
    }

    public void setGendata_FLG(String gendata_FLG) {
        this.gendata_FLG = gendata_FLG;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public List<ListValue> getPrograms() {
        return programs;
    }

    public void setPrograms(List<ListValue> programs) {
        this.programs = programs;
    }

    public String getFrom_dt() {
        return from_dt;
    }

    public void setFrom_dt(String from_dt) {
        this.from_dt = from_dt;
    }

    
    
    
    
}

