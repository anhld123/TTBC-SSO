/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chatbox;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import vbsp.ims.dao.DaoConnect;

/**
 *
 * @author admin
 */
public class UserService {

    public static String getUserGroupId(String userName) {
        String sql = "SELECT ND_NHOMND FROM ng_dung WHERE ND_MA = ?";
        try (Connection conn = new DaoConnect().getConnect();
                PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("ND_NHOMND");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "0";
    }
}
