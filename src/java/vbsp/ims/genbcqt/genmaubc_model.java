/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.genbcqt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.tracuu.Tracuu_viewdata;

/**
 *
 * @author Administrator
 */
public class genmaubc_model {
    
    public List<genmaubc> gentidebc() throws SQLException {
        List<genmaubc> lsgiatri = new ArrayList<>();
        DaoConnect db = new DaoConnect();
        Connection con = db.getConnect();
        CallableStatement st = con.prepareCall("{call BCQT_GETCAPTD(?,?)}");
        st.setString(1,"MS03");
        st.registerOutParameter(2, OracleTypes.CURSOR);
        st.execute();
        ResultSet rs = (ResultSet) st.getObject(2);
        while (rs.next()) {
            String strstring = "<tr>";
            CallableStatement st2 = con.prepareCall("{call BCQT_GENMAUBC(?,?,?)}");
            st2.setString(1,"MS03");
            st2.setInt(2, rs.getInt("CAPTD"));
            st2.registerOutParameter(3, OracleTypes.CURSOR);
            st2.execute();
            ResultSet rs2 = (ResultSet) st2.getObject(3);
            while (rs2.next()) {                
                strstring = strstring + "<th rowspan=\"" + rs2.getInt("ROWSPAN") + "\" colspan=\"" + rs2.getInt("COLSPAN") + "\">" + rs2.getString("TENHT") + "</th>";
            }
            strstring = strstring + "</tr>";
            lsgiatri.add( new genmaubc(strstring));
        }
        return lsgiatri;
    }
    
}
