/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import vbsp.ims.dao.DaoConnect;

public class DMQTDA {

    public List<DMQT> getDMQT() {
        String CapBC = "01";
        List<DMQT> list = new ArrayList<>();
        try {
            DaoConnect db = new DaoConnect();
            Connection conn = db.getConnect();
            String url = "SELECT DM_MABC,DM_TENVT,DM_MOTA,DM_CAPBC,APPLY_FLG,DM_INPUT,DM_LINKBC FROM DMBC_CT WHERE DM_NHOMBC='NHOMBC0009' AND DM_INPUT IN ('01','11') AND APPLY_FLG='Y' AND DM_CAPBC LIKE '%" + CapBC + "%'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(url);
            while (rs.next()) {
                DMQT obj = new DMQT();
                obj.setDM_MABC(rs.getString("DM_MABC"));
                obj.setDM_TENVT(rs.getString("DM_TENVT"));
                obj.setDM_MOTA(rs.getString("DM_MOTA"));
                obj.setDM_CAPBC(rs.getString("DM_CAPBC"));
                obj.setAPPLY_FLG(rs.getString("APPLY_FLG"));
                String loai = rs.getString("DM_INPUT");
                switch (loai) {
                    case "00":
                        loai = "Tự động";
                        break;
                    case "01":
                        loai = "Bán tự động";
                        break;
                    case "11":
                        loai = "Thủ công";
                        break;
                }
                obj.setDM_INPUT(loai);
                obj.setDM_LINKBC(rs.getString("DM_LINKBC"));
                list.add(obj);
            }
            rs.close();
            st.close();
            conn.close();
        } catch (Exception ex) {
        }
        return list;
    }
}
