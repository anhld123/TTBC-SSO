/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.tracuu;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import oracle.sql.ARRAY;
import oracle.sql.ArrayDescriptor;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author Administrator
 */
public class Tracuu_Model {

    private Integer totalpage;

    // Hàm lấy ra danh sách điều kiện để hiển thị vào combo điều kiện
    public List<Tracuu_Utill> getdanhsachdk() throws SQLException {
        List<Tracuu_Utill> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        CallableStatement st = con.prepareCall("{call PROC_GETLISTDK(?)}");
        st.registerOutParameter(1, OracleTypes.CURSOR);
        st.execute();
        ResultSet rs = (ResultSet) st.getObject(1);
        while (rs.next()) {
            Tracuu_Utill obj = new Tracuu_Utill();
            obj.setTENTRUONG(rs.getString("TENCOT"));
            obj.setGIATRI(rs.getString("GIAITHICH"));
            lst.add(obj);
        }
        return lst;
    }

    public getcontrol getvaluedk(String ListKD,String sUserName,String reportGrade) throws SQLException {
        getcontrol lst = new getcontrol();
        List<Tracuu_Utill> ls = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        CallableStatement st = con.prepareCall("{call PROC_GETCONTROL(?,?,?,?,?)}");
        st.setString(1, ListKD);
        st.setString(2, sUserName);
        st.setString(3, reportGrade);
        st.registerOutParameter(4, java.sql.Types.NVARCHAR);
        st.registerOutParameter(5, OracleTypes.CURSOR);
        st.execute();
        ResultSet rs = (ResultSet) st.getObject(5);
        while (rs.next()) {
            Tracuu_Utill obj = new Tracuu_Utill();
            obj.setTENTRUONG(rs.getString("GIATRI"));
            obj.setGIATRI(rs.getString("TENHT"));
            ls.add(obj);
        }
        lst.setLControl(st.getString(4));
        lst.setLscontrol(ls);
        return lst;
    }

    public Tracuu_getalldata loadviewcontent(String ListKD, Integer pagenum) throws SQLException {
        List<Tracuu_viewdata> lsgiatri = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        CallableStatement st = con.prepareCall("{call PROC_VIEWCONTENT(?,?,?,?)}");
        st.setInt(1, pagenum);
        st.setString(2, ListKD);
        st.registerOutParameter(3, OracleTypes.NUMBER);
        st.registerOutParameter(4, OracleTypes.CURSOR);
        st.execute();
        totalpage = st.getInt(3);
        ResultSet rs = (ResultSet) st.getObject(4);
        ResultSetMetaData rsmd = rs.getMetaData();
        int numberOfColumns = rsmd.getColumnCount();
        String strtieude = "<tr bgcolor=\"#DCDCDC\"><th>Chức năng</th>";
        for (int i = 2; i <= numberOfColumns; i++) {
            strtieude = strtieude + "<th>" + rsmd.getColumnName(i) + "</th>";
        }
        strtieude = strtieude.replace("null", "") + "</tr>";
        lsgiatri.add(new Tracuu_viewdata(strtieude));
        while (rs.next()) {
            strtieude = "<tr><td align=\"center\"><a href=\"javascript:Callbaocao('edit_ds_hongheo.action?ds_makh=" + rs.getString(4) + "&pagenum=" + pagenum + "')\">Sửa</a></td>";
            for (int i = 2; i <= numberOfColumns; i++) {
                strtieude = strtieude + "<td>" + rs.getString(i) + "</td>";
            }
            strtieude = strtieude.replace("null", "") + "</tr>";
            lsgiatri.add(new Tracuu_viewdata(strtieude));
        }
        Tracuu_getalldata retracuu = new Tracuu_getalldata();
        retracuu.setTotalpage(totalpage);
        retracuu.setLsview(lsgiatri);
        return retracuu;
    }

    public List<Tracuu_edit_util> edit_dshongheo(String dsmakh) throws SQLException {
        List<Tracuu_edit_util> lst = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        CallableStatement st = con.prepareCall("{call PROC_EDIT_DSHN(?,?)}");
        st.setString(1, dsmakh);
        st.registerOutParameter(2, OracleTypes.CURSOR);
        st.execute();
        ResultSet rs = (ResultSet) st.getObject(2);
        while (rs.next()) {
            Tracuu_edit_util obj = new Tracuu_edit_util();
            obj.setDS_MAKH(rs.getString("DS_MAKH"));
            obj.setDS_MATINH(rs.getString("DS_MATINH"));
            obj.setDS_MAHUYEN(rs.getString("DS_MAHUYEN"));
            obj.setDS_MAXA(rs.getString("DS_MAXA"));
            obj.setDS_MATHON(rs.getString("DS_MATHON"));
            obj.setDS_TENKH(rs.getString("DS_TENKH"));
            obj.setDS_GIOITINH(rs.getString("DS_GIOITINH"));
            obj.setDS_NGAYSINH(rs.getString("DS_NGAYSINH"));
            obj.setDS_DANTOC(rs.getString("DS_DANTOC"));
            obj.setDS_SOCMT(rs.getString("DS_SOCMT"));
            obj.setDS_NGAYCAP(rs.getString("DS_NGAYCAP"));
            obj.setDS_NOICAP(rs.getString("DS_NOICAP"));
            obj.setDS_TD_HOCVAN(rs.getString("DS_TD_HOCVAN"));
            obj.setDS_FLAG_CH(rs.getString("DS_FLAG_CH"));
            obj.setDS_QUANHE(rs.getString("DS_QUANHE"));
            obj.setDS_DT_CHINHSACH(rs.getString("DS_DT_CHINHSACH"));
            obj.setDS_TN_BINHQUAN(rs.getString("DS_TN_BINHQUAN"));
            obj.setDS_LOAI_KH(rs.getString("DS_LOAI_KH"));
            obj.setDS_NGAYLOAI(rs.getString("DS_NGAYLOAI"));
            obj.setDS_NAMSL(rs.getString("DS_NAMSL"));
            obj.setDS_NGUYENNHAN(rs.getString("DS_NGUYENNHAN"));
            obj.setDS_GHICHU(rs.getString("DS_GHICHU"));
            lst.add(obj);
        }
        return lst;
    }

    public Integer save_dshongheo(List<Tracuu_edit_util> lsupdate, String dsmakh) throws SQLException {
        Integer chkrs = 0;
        try {
            DaoConnect db = new DaoConnect();
            Connection con = db.getConnect();
            CallableStatement st = con.prepareCall("{call PROC_SAVE_DSHN(?,?,?)}");
            Object array[] = lsupdate.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DS_HONGHEO", con);
            ARRAY array_to_pass = new ARRAY(des, con, array);
            st.setString(1, dsmakh);
            st.setArray(2, array_to_pass);
            st.registerOutParameter(3, OracleTypes.INTEGER);
            st.execute();
            chkrs = st.getInt(3);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return chkrs;
    }
     public Integer add_dshongheo(List<Tracuu_edit_util> lsupdate) throws SQLException {
        Integer chkrs = 0;
        try {
            DaoConnect db = new DaoConnect();
            Connection con = db.getConnect();
            CallableStatement st = con.prepareCall("{call PROC_ADD_DSHN(?,?)}");
            Object array[] = lsupdate.toArray();
            ArrayDescriptor des = ArrayDescriptor.createDescriptor("TAB_DS_HONGHEO", con);
            ARRAY array_to_pass = new ARRAY(des, con, array);
            st.setArray(1, array_to_pass);
            st.registerOutParameter(2, OracleTypes.INTEGER);
            st.execute();
            chkrs = st.getInt(2);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return chkrs;
    }
}
