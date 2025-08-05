/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import java.sql.Connection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.restapi.DuLieuNTService;
import vbsp.ims.restapi.ListOfValue;

/**
 *
 * @author DucAnh
 */
public class Login {

    DuLieuNTService _serverAPI = new DuLieuNTService();
    private List<ListOfValue> lstDmKhac;

    public DuLieuNTService getServerAPI() {
        return _serverAPI;
    }

    public void setServerAPI(DuLieuNTService _serverAPI) {
        this._serverAPI = _serverAPI;
    }

    public List<ListOfValue> getLstDmKhac() {
        return lstDmKhac;
    }

    public void setLstDmKhac(List<ListOfValue> lstDmKhac) {
        this.lstDmKhac = lstDmKhac;
    }

    public boolean checkIpAllowedFromDao() {
        try {
            lstDmKhac = _serverAPI.getListOfValue("194", "");

            if (lstDmKhac == null || lstDmKhac.isEmpty()) {
                System.err.println("Kết nối đến TW bị lỗi, liên hệ tin học để hỗ trợ!");
                return false;
            }

            List<String> allowedIps = lstDmKhac.stream()
                    .map(dm -> dm.getDescription())
                    .filter(Objects::nonNull)
                    .map(String::trim)
                    .collect(Collectors.toList());

            String dbUrl;
            try (Connection con = new DaoConnect().getConnect()) {
                ;
                if (con == null) {
                    System.err.println("Không thể kết nối để kiểm tra IP.");
                    return false;
                }
                dbUrl = con.getMetaData().getURL(); // ví dụ: jdbc:oracle:thin:@10.63.48.70:1521:DEVIMS
                // đóng sau khi lấy metadata
            }

            String ipPart = dbUrl.split("@")[1]; // "10.63.48.70:1521:DEVIMS"
            String currentDbIp = ipPart.split(":")[0].trim(); // "10.63.48.70"

            boolean isAllowed = allowedIps.contains(currentDbIp);
            if (!isAllowed) {
                System.err.println("IP không nằm trong danh sách cho phép: " + currentDbIp);
            }

            return isAllowed;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
