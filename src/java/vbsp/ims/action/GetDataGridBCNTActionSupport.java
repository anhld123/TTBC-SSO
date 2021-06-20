package vbsp.ims.action;


import com.opensymphony.xwork2.ActionSupport;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import vbsp.ims.bcnt.BaoCaoNhapTay;
import vbsp.ims.dao.DaoBCNT;

public class GetDataGridBCNTActionSupport extends ActionSupport {

    private List<BaoCaoNhapTay> bcntList; //Liet ke noi dung tron list

    // Get the requested page. By default grid sets this to 1.
    private Integer page = 0;

    // Your Total Pages
    private Integer total = 0;
    
    private Integer records = 0;
    
    //Su dung de lay bao cao nhanh
    private DaoBCNT objBCNT;
    
    private String reportId;
    private String reportDate;  //CuongBM: 02Jul14
                                //         Tam thoi bo truong nay, su dung truong quy bao cao quarteryear
    private String posId;
    private String quarteryear; //CuongBM: 02Jul14 
                                //         Quy bao cao (Dung thay the cho truong reportDate)
    
    
    private ArrayList<BaoCaoNhapTay> myBcntList; //Liet ke noi dung tron list
   
    public GetDataGridBCNTActionSupport() {

    }

    public String execute() throws Exception {
        System.err.print("Vao ham GetDataGridBCNTActionSupport/execute():" + reportId + reportDate);
        
        Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(reportDate);

        objBCNT = new DaoBCNT();
        myBcntList = objBCNT.getBCNT(reportId, new SimpleDateFormat("dd-MMM-yyyy").format(sdf), posId, quarteryear);  
        bcntList = decreaseIndent(myBcntList);
        total = 1; //Tong so trang
        records = bcntList.size();
      
        return "success";
    }

    //CuongBM: 22May14
    //Desc: Them khoang trong vao truong MoTa cho de doc
    private List<BaoCaoNhapTay> decreaseIndent(ArrayList<BaoCaoNhapTay> bcnt) {
        ArrayList<BaoCaoNhapTay> arrList = bcnt;

        //List luu gia tri sau khi xu ly xong truong Mota
        ArrayList<BaoCaoNhapTay> resultList = new ArrayList<BaoCaoNhapTay>();

        for (BaoCaoNhapTay obj : arrList) {
//            int lenMaCT = obj.getMaCT().length();

            //Mac dinh level 0: A1, A2, A3 ...
            // => A12, A13...A21, A22..: se la level 2
            int level = obj.getCapHienThi();

            //O day ta xu ly
            //Tang 2 level => thut vao 3 dau cach
            String indent = ""; //So dau cach thut vao
            for (int i = 1; i < level; i++) {
                indent += "       ";//7 dau cach (3 spaces)
            }

            obj.setMoTa(indent + obj.getMoTa()); //Thut vao cho truong Mota

            //Luu gia tri vao mang moi
            resultList.add(obj);
        }

        return resultList;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public List<BaoCaoNhapTay> getBcntList() {
        return bcntList;
    }
    
    public void setBcntList(List<BaoCaoNhapTay> bcntList) {
        this.bcntList = bcntList;
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
    
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }
    
     public String getPosId() {
        return posId;
    }

    public void setPosId(String posId) {
        this.posId = posId;
    }
    
    public String getQuarteryear() {
        return quarteryear;
    }

    public void setQuarteryear(String quarteryear) {
        this.quarteryear = quarteryear;
    }
    
//</editor-fold>
    
    
    
}
