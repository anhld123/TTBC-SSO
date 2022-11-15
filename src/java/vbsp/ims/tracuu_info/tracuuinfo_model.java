/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu_info;

import com.opensymphony.xwork2.ActionContext;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Administrator
 */
public class tracuuinfo_model {
    
    private String tendn,capbc;

    //Hàm thực hiện kết nối đến CSDL
    private Connection connectCSDL() {
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        return con;
    }
    
    private void get_cap_user(){
        tendn = capbc ="";
        Map session = ActionContext.getContext().getSession();
        tendn = session.get("username").toString();
        capbc = session.get("reportGrade").toString();
    }

    // Thực hiện lấy ra danh sách loại tra cứu
    public List<tracuuinfo_dndm> get_tracuu_info() throws SQLException {
        List<tracuuinfo_dndm> lsinfo = new ArrayList<>();
        Connection con = connectCSDL();
        CallableStatement st = con.prepareCall("{call APP_TRACUU_TT.PROC_INFO_TRACUU(?)}");
        st.registerOutParameter(1, OracleTypes.CURSOR);
        st.execute();
        ResultSet rs = (ResultSet) st.getObject(1);
        while (rs.next()) {
            tracuuinfo_dndm obj = new tracuuinfo_dndm(rs.getString("MADM"), rs.getString("TENDM"));
            lsinfo.add(obj);
        }
        return lsinfo;
    }

    // Thực hiện tạo đối tượng điều kiện tìm kiếm
    // Thực hiện đưa cấp xuống để có thể sinh các control theo câp
    public String get_tracuu_control(String loaitc) throws SQLException {
        String gentable;
        String subgentable = "";
        Integer i = 0;
        get_cap_user();
        Connection con = connectCSDL();
        // Lấy ra danh sách tên và loại đối tượng
        CallableStatement st = con.prepareCall("{call APP_TRACUU_TT.PROC_INFO_CONTROL_MAIN(?,?,?)}");
        st.setString(1, capbc);
        st.setString(2, loaitc);
        st.registerOutParameter(3, OracleTypes.CURSOR);
        st.execute();
        ResultSet rs = (ResultSet) st.getObject(3);
        while (rs.next()) {
            i++;
            // Nếu đối tượng cần đưa ra không phải là dạng select thì thì để đối tượng là TEXT
            // Xác định xem trường đó có phải nhập liệu hay không
            String strRequired,strreqtext = "";
                CallableStatement SrtOp = con.prepareCall("{call APP_TRACUU_TT.PROC_INFO_DATA_CONTROL(?,?,?,?)}");
                SrtOp.setString(1, rs.getString("MAKD"));
                SrtOp.setString(2, capbc);
                SrtOp.setString(3, loaitc);
                SrtOp.registerOutParameter(4, OracleTypes.CURSOR);
                SrtOp.execute();
                ResultSet rsop = (ResultSet) SrtOp.getObject(4);
                while (rsop.next()) {
                    if(rsop.getString("REQUIRED").equals("A")){strRequired=" required"; strreqtext="<span style='color:red;'>*</span>";}else{strRequired = strreqtext = "";}
                    subgentable = subgentable + "<td style='20%'>" + rs.getString("TENDK")+ " " + strreqtext + "</td><td><input type='hidden' id='ListDK' name='ListDK' value='" + rs.getString("MAKD")+ "'><input type='text' id='ListVal' name='ListVal' value='" + rsop.getString("HIENTHI") + "'" + strRequired + " placeholder="+rsop.getString("PLAECHOLDER")+"></td>";
                }
                subgentable = subgentable.replace("null", "");
            if(i%4==0){
                subgentable = subgentable + "VNP2";
            }
        }
        gentable = "<table class='gentable'><tr>" + subgentable.replace("VNP2","</tr><tr>") + "</tr></table>";
        return gentable;
    }
    
    // Thực hiện lấy dữ liệu từ Query
    // Đưa cấp và User xuống Query để có thể lọc được dữ liệu
    public List<tracuuinfo_listgt> get_query_info(String dieukien, String loainv,String chkexcel) throws SQLException {
        List<tracuuinfo_listgt> lsinfo = new ArrayList<>();
        get_cap_user();
        Connection con = connectCSDL();
        // VinhNP: Chuyển từ viết câu lệnh select thành chạy các Procedure
        CallableStatement st = con.prepareCall("{call APP_TRACUU_TT.PROC_INFO_QUERY(?,?,?,?,?,?)}");
        st.setString(1, dieukien);
        st.setString(2, chkexcel);
        st.setString(3, loainv);
        st.setString(4, capbc);
        st.setString(5, tendn);
        st.registerOutParameter(6, OracleTypes.CURSOR);
        st.execute();
        ResultSet rs = (ResultSet) st.getObject(6);
        // Thực hiện lấy Metadata
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        String strtieude = "";
        for (int i = 1; i <= numberOfColumns; i++) {
            strtieude = strtieude + "<th>" + rsmd.getColumnName(i) + "</th>";
        }
        strtieude = strtieude.replace("null", "") + "</tr>";
        lsinfo.add(new tracuuinfo_listgt(strtieude));
        while (rs.next()) {
            strtieude = "";
            for (int i = 1; i <= numberOfColumns; i++) {
                strtieude = strtieude + "<td>" + rs.getString(i) + "</td>";
            }
            strtieude = strtieude.replace("null", "") + "</tr>";
            lsinfo.add(new tracuuinfo_listgt(strtieude));
        }
        return lsinfo;
        
    }
}
