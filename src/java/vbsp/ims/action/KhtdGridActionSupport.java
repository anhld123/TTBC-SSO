package vbsp.ims.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import vbsp.ims.dao.DaoCreditPlan;
import vbsp.ims.model.KhtdGrid;

public class KhtdGridActionSupport extends ActionSupport {
    private List<KhtdGrid> khtdList; //Liet ke noi dung tron list
    private String ngayNhap;
    private String maPGD;
    private Integer page = 0; // Get the requested page. By default grid sets this to 1.
    private Integer total = 0; // Your Total Pages
    private Integer records = 0;
    private ArrayList<KhtdGrid> myKhtdList; //Liet ke noi dung trong list
    private DaoCreditPlan daoCreditPlan;
    
    public KhtdGridActionSupport() {
    }
    
    public String execute() throws Exception {
        return "success";
    }
    
    //Xay dung ke hoach
    public String xayDungKH() throws Exception{
        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(ngayNhap);
        //Tungnv them de dong bo bao ca ve tw
        //lay user dang nhap
        
        Map mapSession = ActionContext.getContext().getSession();
        String strUserName=mapSession.get("username").toString();
//        System.err.println("User lay ra la "+mapSession.get("username"));
        daoCreditPlan = new DaoCreditPlan();
        myKhtdList = daoCreditPlan.getDataKhtdGrid(maPGD, new SimpleDateFormat("dd-MMM-yyyy").format(sdf), strUserName);
        khtdList = decreaseIndent(myKhtdList);  //Them dau cach vao truong Chi Tieu cho de doc
        
        total = 1; //Tong so trang
        records = 100;
        return "success";
    }
    
    //Giao ke hoach
    public String giaoKH(){
        return "success";
    }
    
    //Dieu chinh ke hoach
    public String dieuChinhKH(){
        return "success";
    }
    
    //CuongBM: 22May14
    //Desc: Them khoang trong vao truong MoTa cho de doc
    private List<KhtdGrid> decreaseIndent(ArrayList<KhtdGrid> khtd) {
        ArrayList<KhtdGrid> arrList = khtd;

        //List luu gia tri sau khi xu ly xong truong Mota
        ArrayList<KhtdGrid> resultList = new ArrayList<KhtdGrid>();

        for (KhtdGrid obj : arrList) {
            int level = obj.getCapHienThi();

            //O day ta xu ly
            //Tang 2 level => thut vao 3 dau cach
            String indent = ""; //So dau cach thut vao
            for (int i = 1; i < level; i++) {
                indent += "       ";//7 dau cach (7 spaces)
            }

            obj.setChiTieu(indent + obj.getChiTieu()); //Thut vao cho truong Mota

            //Luu gia tri vao mang moi
            resultList.add(obj);
        }

        return resultList;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">    
    
    public List<KhtdGrid> getKhtdList() {
        return khtdList;
    }

    public void setKhtdList(List<KhtdGrid> khtdList) {
        this.khtdList = khtdList;
    }

    public ArrayList<KhtdGrid> getMyKhtdList() {
        return myKhtdList;
    }

    public void setMyKhtdList(ArrayList<KhtdGrid> myKhtdList) {
        this.myKhtdList = myKhtdList;
    }
    
    
    public Integer getPage() {
        return page;
    }
    
    public void setPage(Integer page) {
        this.page = page;
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
    
    public String getNgayNhap() {
        return ngayNhap;
    }
    
    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    public String getMaPGD() {
        return maPGD;
    }
    
    public void setMaPGD(String maPGD) {
        this.maPGD = maPGD;
    }
    
    
    
//</editor-fold>
}
