package vbsp.ims.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import vbsp.ims.ctieu.ChiTieuLoad;
import vbsp.ims.dao.ConnectDatabase;
import vbsp.ims.dao.DaoConnect;

public class LoadTruyVanCT extends ActionSupport {

    private String nganHang;
    private String maPGD;
    private String maChiTieu;
    private String ngayBaoCao;
    private String subMaChiTieu;
    private String kyBaoCao;
    private Map session;
    private String capbc;
    private ResultSet rs;
    private final int ROWONPAGE = 20;
    private int numpage;
    private int TotalPage;
    private String TotalIndicator;
    private String   SumValue;
    private List<ChiTieuLoad> loadChiTieuList;

    public String getTotalIndicator() {
        return TotalIndicator;
    }

    public void setTotalIndicator(String TotalIndicator) {
        this.TotalIndicator = TotalIndicator;
    }

    public String getSumValue() {
        return SumValue;
    }

    public void setSumValue(String SumValue) {
        this.SumValue = SumValue;
    }
    
    public int getTotalPage() {
        return TotalPage;
    }

    public String getKyBaoCao() {
        return kyBaoCao;
    }

    public void setKyBaoCao(String kyBaoCao) {
        this.kyBaoCao = kyBaoCao;
    }

    public void setTotalPage(int TotalPage) {
        this.TotalPage = TotalPage;
    }

    public int getNumpage() {
        return numpage;
    }

    public void setNumpage(int numpage) {
        this.numpage = numpage;
    }

    public LoadTruyVanCT() {
    }

    @Override
    public String execute() throws Exception {

        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        loadChiTieuList = new ArrayList<ChiTieuLoad>();
        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        String tendn = (String) session.get("username");
 
        /* Người tạo: NguyetLM
         Ngày tạo: 15/5/14
         Mục đích: Load chỉ tiêu vbsp, sbv theo user và cấp báo cáo
         */
        if (numpage == 0 || numpage <= 0) {
            // Trường hợp mới load trang lần đầu tiên hoặc nhấn về trước khi mà Page=1
            numpage = 1;
        }
        if(maPGD.trim().equals("-1")){
            maPGD ="";
        }
        if(kyBaoCao.trim().equals("-1")){
            kyBaoCao ="";
        }
        Connection Conection = null;
        CallableStatement callableSt = null;
        CallableStatement callableState = null;
        Conection = db.getConnect();

        // Goi Procedure Load truy van chi tieu 
        String getDBUSERCursorSql = "{call P_LOAD_SELECTINDICATOR(?,?,?,?,?,?,?,?,?,?)}";
        callableSt = Conection.prepareCall(getDBUSERCursorSql);
        callableSt.setString(1, nganHang); // Ngan hang
        callableSt.setString(2, capbc); // Cap bao cao
        callableSt.setString(3, tendn); // Ten dang nhap
        callableSt.setString(4, maPGD); // Ma PGD
        callableSt.setString(5, maChiTieu); // Ma nhom chi tieu
        callableSt.setString(6, subMaChiTieu); // Ma chi tieu
        callableSt.setString(7, ngayBaoCao); // Ngay bao cao
        callableSt.setString(8, kyBaoCao); // Ky bao cao
        callableSt.registerOutParameter(9, OracleTypes.NVARCHAR); // tong so chi tieu
        callableSt.registerOutParameter(10, OracleTypes.NVARCHAR); // tong gia tri cac chi tieu
        callableSt.executeUpdate();
        TotalIndicator = callableSt.getString(9);
        SumValue = callableSt.getString(10);
        if(SumValue==null){
            SumValue = "0";
        }
        // Goi Procedure Load chi tieu (Cach goi Procedure co tham so dau vao va tham so dau ra)
        String CursorSql = "{call P_DIVIDE_PAGES(?,?,?,?,?)}";
        callableState = Conection.prepareCall(CursorSql);
        callableState.setString(1, "TEMP_SELECTINDICATOR"); // Ten bang chua du lieu theo cac dieu kien truyen vao
        callableState.setInt(2, numpage); // trang hien thi
        callableState.setInt(3, ROWONPAGE); // so dong tren 1 trang
        callableState.registerOutParameter(4, OracleTypes.NUMBER); // tong so trang
        callableState.registerOutParameter(5, OracleTypes.CURSOR); //cursor ket qua dau ra
        // execute DIVIDE_PAGES store procedure
        callableState.executeUpdate();
        TotalPage = callableState.getInt(4);
        if (TotalPage <= 0) {
            TotalPage = 1;
        }
        // get cursor and cast it to ResultSet
        rs = (ResultSet) callableState.getObject(5);

        while (rs.next()) {
            ChiTieuLoad obj = new ChiTieuLoad();
            obj.setMaChiTieu(rs.getString("NDATA_1"));
            obj.setKieuGiaTri(rs.getString("NDATA_2"));
            obj.setGiaTri(rs.getDouble("NDATA_3"));
            obj.setNgayBaoCao(rs.getString("NDATA_4"));
            obj.setMaPGD(rs.getString("NDATA_5"));
            obj.setMaCN(rs.getString("NDATA_6"));

            loadChiTieuList.add(obj);
        }
        return "success";
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }

    public String getMaPGD() {
        return maPGD;
    }

    public void setMaPGD(String maPGD) {
        this.maPGD = maPGD;
    }

    public String getMaChiTieu() {
        return maChiTieu;
    }

    public void setMaChiTieu(String maChiTieu) {
        this.maChiTieu = maChiTieu;
    }

    public String getSubMaChiTieu() {
        return subMaChiTieu;
    }

    public void setSubMaChiTieu(String subMaChiTieu) {
        this.subMaChiTieu = subMaChiTieu;
    }

    public String getNgayBaoCao() {
        return ngayBaoCao;
    }

    public void setNgayBaoCao(String ngayBaoCao) {
        this.ngayBaoCao = ngayBaoCao;
    }

    public List<ChiTieuLoad> getLoadChiTieuList() {
        return loadChiTieuList;
    }

    public void setLoadChiTieuList(List<ChiTieuLoad> loadChiTieuList) {
        this.loadChiTieuList = loadChiTieuList;
    }
//</editor-fold>
}
