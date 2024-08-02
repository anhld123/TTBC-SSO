/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import static com.opensymphony.xwork2.Action.ERROR;
import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.restapi.ApiFileCic_Tmp;
import vbsp.ims.restapi.CicGenFile_Tmp;
import vbsp.ims.tdnn.DaoTdnnMain;

/**
 *
 * @author Trung
 */
public class CIC_2024 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _server;
    Service_CIC_2024 _server_tmp;
    private List<CicGenFile_Tmp> lstData;
    private List<ApiFileCic_Tmp> lstData_tmp;
    private String mapgd;
    private String vbspId;

    public String getVbspId() {
        return vbspId;
    }

    public void setVbspId(String vbspId) {
        this.vbspId = vbspId;
    }

    public String getMapgd() {
        return mapgd;
    }

    public void setMapgd(String mapgd) {
        this.mapgd = mapgd;
    }

    @Override
    public String load() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            Connection conn = new DaoConnect().getConnect();
            String PosFlag = "S";
//            if (Grade.equals("3")) {
//                PosFlag = "H";
//            } else if (Grade.equals("2")) {
//                PosFlag = "M";
//            } else {
//                PosFlag = "S";
//            }
            String s = hmParameter.get("ngay_bc").toString();
            mapgd = hmParameter.get("lstCN").toString();

            if (mapgd.equals("000000")) {
                addActionError("Bạn chưa chọn chi nhánh!");
                return ERROR;
            }

            String[] values = new String[50];
            for (int i = 0; i < 50; i++) {
                values[i] = "D" + (i + 1);
            }

            Service_GQVL2023 _server = new Service_GQVL2023();

            for (int i = 0; i < 50; i++) {
                CicGenFile_Tmp nn = _server.xuatfileExcel_cic(values[i], mapgd, PosFlag, s);
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                try {
                    String branchCode = nn.getBranchCode();
                    if (branchCode != null && !branchCode.isEmpty()) {
                        row.setD1(branchCode);
                        row.setD2(nn.getBranchName());
                        row.setD3(nn.getFlag());
                        row.setD4(nn.getLink());
                        row.setD5(nn.getSbvCode());
                        row.setD6(values[i]);
                        row.setD7(nn.getData());

                        String fileId = nn.getLink().substring(nn.getLink().lastIndexOf("file/") + 5);
                        row.setD8(fileId);
//                        Service_CIC_2024 _server_tmp = new Service_CIC_2024();
//                        ApiFileCic_Tmp kk = _server_tmp.getFileCic(fileId);

//                        for (int j = 0; j < 100; j++) {
//
//                            String base64 = kk.data;
//                            try {
//                                if (base64 != null && kk.data != null) {
//
//                                    row.setD9(kk.data);
////                                    byte[] decodedBytes = Base64.getDecoder().decode(kk.data);
////                                    createExcelFile(decodedBytes, "output_" + fileId + "_" + j + ".xlsx");
//                                }
//                            } catch (Exception e) {
//                                e.printStackTrace();
//
//                            }
//                        }
                        // Thêm đối tượng row vào danh sách lstDulieuNt
                        lstDulieuNt.add(row);

                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> cic 2024 : " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> cic 2024: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.isEmpty()) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

            DaoTdnnMain daoMain = DaoTdnnMain.newInstance();
            HashMap hmParameter = getParameter();

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> SAVE_TDNN_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> SAVE_TDNN_01: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

//    private void createExcelFile(byte[] data, String fileName) throws Exception {
//        XSSFWorkbook workbook = new XSSFWorkbook();
//        XSSFSheet sheet = workbook.createSheet("Data");
//
//        Row row = sheet.createRow(0);
//        Cell cell = row.createCell(0);
//        cell.setCellValue(new String(data));
//
//        try (OutputStream fileOut = new FileOutputStream(fileName)) {
//            workbook.write(fileOut);
//        }
//        workbook.close();
//    }
    public String popupTableFile() throws Exception {
        try {
            QT_DULIEU_NT row = new QT_DULIEU_NT();

            Service_CIC_2024 _server_tmp = new Service_CIC_2024();
            ApiFileCic_Tmp kk = _server_tmp.getFileCic(vbspId);
            row.setD9(kk.data);
            row.setD10(kk.description);
            row.setD11(kk.link.substring(kk.link.lastIndexOf("json/") + 5));
            
            lstDulieuNt.add(row);

        } catch (Exception e) {
            System.err.println("Loi trong ham saveDataaa " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " saveDataaa -> " + e.getMessage());
        }
        return "success";
    }
    
}
