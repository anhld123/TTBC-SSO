package vbsp.ims.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import vbsp.ims.ctieu.ChiTieuLoad;
import vbsp.ims.dao.ConnectDatabase;
import vbsp.ims.dao.DaoConnect;

public class LoadChiTieu extends ActionSupport {

    private List<ChiTieuLoad> loadChiTieuList;
    private String maChiTieu;
    private String SubmaChiTieu;
    private String nganHang;
    private Map session;
    private String capbc;
    private ResultSet rs;
    private final int ROWONPAGE = 20;
    private int numpage;
    private int TotalPage;
    private String TotalIndicator;
    private String SumValue;

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

    public void setTotalPage(int TotalPage) {
        this.TotalPage = TotalPage;
    }
    

    public int getNumpage() {
        return numpage;
    }

    public void setNumpage(int numpage) {
        this.numpage = numpage;
    }

    public LoadChiTieu() {
    }

    @Override
    public String execute() throws Exception {
        DaoConnect db = new DaoConnect();
        //Connection con = db.getConnection();
        loadChiTieuList = new ArrayList<ChiTieuLoad>();

        session = ActionContext.getContext().getSession();
        capbc = (String) session.get("reportGrade");
        String tendn = (String) session.get("username");

        //Statement stm = con.createStatement();
        /* Người sửa: NguyetLM
         Ngày sửa: 15/5/14
         Mục đích: Load chỉ tiêu vbsp, sbv theo user và cấp báo cáo
         */

        if (numpage == 0 || numpage <=0) {
            // Trường hợp mới load trang lần đầu tiên hoặc nhấn về trước khi mà Page=1
            numpage = 1;
        }
        Connection dbConnection = null;
        CallableStatement callableStmt = null;
        CallableStatement callableStatement = null;
        dbConnection = db.getConnect();
 
        // Goi Procedure Load chi tieu 
        String getDBUSERCursorSql = "{call P_LOAD_INDICATOR(?,?,?,?,?,?)}";
        callableStmt = dbConnection.prepareCall(getDBUSERCursorSql);
        callableStmt.setString(1, nganHang); // Ngan hang
        callableStmt.setString(2, capbc); // Cap bao cao
        callableStmt.setString(3, tendn); // Ten dang nhap
        callableStmt.setString(4, maChiTieu); // Ma chi tieu
        callableStmt.registerOutParameter(5, OracleTypes.NVARCHAR); // tong so chi tieu
        callableStmt.registerOutParameter(6, OracleTypes.NVARCHAR); // tong gia tri cac chi tieu
        callableStmt.executeUpdate();
        TotalIndicator = callableStmt.getString(5);
        SumValue = callableStmt.getString(6);
        
        dbConnection =  db.getConnect();
        // Goi Procedure Load chi tieu (Cach goi Procedure co tham so dau vao va tham so dau ra)
        String CursorSql = "{call P_DIVIDE_PAGES(?,?,?,?,?)}";
        callableStatement = dbConnection.prepareCall(CursorSql);
        callableStatement.setString(1, "temp_indicator"); // Ten bang chua du lieu theo cac dieu kien truyen vao
        callableStatement.setInt(2, numpage); // trang hien thi
        callableStatement.setInt(3, ROWONPAGE); // so dong tren 1 trang
        callableStatement.registerOutParameter(4, OracleTypes.NUMBER); // tong so trang
        callableStatement.registerOutParameter(5, OracleTypes.CURSOR); //cursor ket qua dau ra
        // execute DIVIDE_PAGES store procedure
        callableStatement.executeUpdate();
        TotalPage = callableStatement.getInt(4);
        // get cursor and cast it to ResultSet
        rs = (ResultSet) callableStatement.getObject(5);

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

    public List<ChiTieuLoad> getLoadChiTieuList() {
        return loadChiTieuList;
    }

    public void setLoadChiTieuList(List<ChiTieuLoad> loadChiTieuList) {
        this.loadChiTieuList = loadChiTieuList;
    }

    public String getMaChiTieu() {
        return maChiTieu;
    }

    public void setMaChiTieu(String maChiTieu) {
        this.maChiTieu = maChiTieu;
    }

    public String getNganHang() {
        return nganHang;
    }

    public void setNganHang(String nganHang) {
        this.nganHang = nganHang;
    }
}
