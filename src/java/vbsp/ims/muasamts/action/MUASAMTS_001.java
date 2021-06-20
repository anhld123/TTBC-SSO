/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.muasamts.action;

import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.File;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.dao.DaoDCPLNO;
import vbsp.ims.define.Define;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class MUASAMTS_001 extends ActionMuasamtsMain
        implements NhaptaycnFunction {

    @Override
    public String load() {
        try {
            System.err.println("MUASAMTS_001");
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();

            if ((hmParameter.get("dot_ms").toString().equals("1") && Integer.parseInt(hmParameter.get("trangthaims").toString()) > 1) && !Grade.equals("3")) {
                addActionError("Lần 1 chỉ được chọn trạng thái Thêm mới");
                return ERROR;
            }

            if ((Integer.parseInt(hmParameter.get("dot_ms").toString()) > 1 && hmParameter.get("trangthaims").toString().equals("1")) && !Grade.equals("3")) {
                addActionError("Từ đợt 2 không được phép chọn trạng thái Thêm mới");
                return ERROR;
            }

            Connection conn = new DaoConnect().getConnect();
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            if (Grade.equals("1")) {
                String iCheck = daoMain.Get_status_input(khoa_muasamts, UserName, "", hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(), "1",
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
                if (iCheck.equals("2")) //Chốt số liệu đợt trước
                {
                    setStatusInput("Đã chốt số liệu");
                } else if (iCheck.equals("1")) {
                    setStatusInput("Đã chốt số liệu");
                } else if (iCheck.equals("0")) {
                    setStatusInput("Chưa chốt số liệu");
                } else if (iCheck.equals("3")) {
                    addActionError("Vui lòng hoàn thiện đợt trước khi muốn làm đợt mới");
                    return ERROR;
                }
            }

            //Nhập kế hoạch
            if (Grade.equals("1") && hmParameter.get("nghiepvums").toString().equals("1")) {
                setCacngaynhap(daoMain.Get_status_input(khoa_muasamts, UserName, "", hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(), "2",
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString()));

                lstDulieuNt = daoMain.getDataMuasamTS_01(conn, khoa_muasamts, "", UserName, Grade, poscd, hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(),
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
                setLstCBNguonVon(daoMain.getCanBo(UserName, "NGUONVON"));
//                setLstCBTrangthaiTS(daoMain.getLOV_Muasamts(UserName,"TRANGTHAITS", hmParameter.get("nam_bc").toString(), hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvu").toString()));

                return "nhapkehoach";
            } else if (Grade.equals("1") && hmParameter.get("nghiepvums").toString().equals("2")) {
                setCacngaynhap(daoMain.Get_status_input(khoa_muasamts, UserName, "", hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(), "2",
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString()));

                lstDulieuNt = daoMain.getDataMuasamTS_01(conn, khoa_muasamts, "", UserName, Grade, poscd, hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(),
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
                setLstCBNguonVon(daoMain.getCanBo(UserName, "NGUONVON"));
                return "nhapnhapqkqpheduyetkh";
            } else if (Grade.equals("1") && hmParameter.get("nghiepvums").toString().equals("3")) {
                setCacngaynhap(daoMain.Get_status_input(khoa_muasamts, UserName, "", hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(), "2",
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString()));

                lstDulieuNt = daoMain.getDataMuasamTS_01(conn, khoa_muasamts, "", UserName, Grade, poscd, hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(),
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
                setLstCBNguonVon(daoMain.getCanBo(UserName, "NGUONVON"));
                setLstCBHinhthucMS(daoMain.getCanBo(UserName, "HINHTHUCMS"));
                return "nhapqkqmuasam";
            } else if (Grade.equals("2")) {
                lstDulieuNt = daoMain.getStatusPosLock(conn, khoa_muasamts, "", UserName, Grade, poscd, hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(), hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
                if (lstDulieuNt.size() > 0) {
                    return "chuachot";
                } else {
                    lstDulieuNt = daoMain.getDataMuasamTS_01(conn, khoa_muasamts, "", UserName, Grade, poscd, hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(),
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
                    if (hmParameter.get("nghiepvums").toString().equals("1")) {
                        return "nhapkehoach";
                    } else if (hmParameter.get("nghiepvums").toString().equals("2")) {
                        return "nhapnhapqkqpheduyetkh";
                    } else if (hmParameter.get("nghiepvums").toString().equals("3")) {
                        return "nhapqkqmuasam";
                    } else {
                        return ERROR;
                    }

                }
            }
            else if (Grade.equals("3")) {
                String macnAll = "AAA";
                if(poscd.contains("999999"))
                {
                    macnAll = "ALL";
                }                
                setMacn(macnAll);
                lstDulieuNt = daoMain.getStatusSendCnMuasamTS(khoa_muasamts, "", poscd,  hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(),
                        hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString(), macnAll);
                return "captw1";
            }

            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> MUASAMTS_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> MUASAMTS_001: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - MUASAMTS_001");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();

            String iCheck = daoMain.Get_status_input(khoa_muasamts, UserName, "", hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(), "1",
                    hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
            if (iCheck.endsWith("1")) {
                addActionError("Bạn không thể lưu khi đã chốt số liệu");
                return ERROR;
            }

            List<String> lsTmp = new ArrayList<>();

            String iCheckInput = daoMain.checkData_MuasamTS(lstDulieuNt, khoa_muasamts, "", UserName, hmParameter.get("trangthaims").toString(),
                    hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(),
                    hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
            if (!iCheckInput.equals("XXXAAA")) {
                addActionError("Lỗi! " + iCheckInput);
                return ERROR;
            }

            if (!daoMain.saveMuasamTS_001(khoa_muasamts, UserName, "", "", lstDulieuNt, hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(),
                    hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString())) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> MUASAMTS_001: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> MUASAMTS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String ChotMuaSamTS() {
        System.err.println("Save - ChotMuaSamTS");
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();
            int result = daoMain.ChotMuasamTS(khoa_muasamts, UserName, "", hmParameter.get("nam_bc").toString(), hmParameter.get("trangthaims").toString(), hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString());
            if (result == 0) {
                addActionError("Bạn chưa chốt được báo cáo xin liên hệ với quản trị để khắc phục");
                return ERROR;
            } else if (result == 1) {
                addActionError("Pgd chưa nhập số liệu nên bạn không thể chốt số liệu");
                return ERROR;
            }

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ChotMuaSamTS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ChotMuaSamTS: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã chốt dữ liệu thành công");
        return SUCCESS;
    }

    public String MoChotMuaSamTS() {
        System.err.println("Save - ChotMuaSamTS");
        try {
            if (!getParaSession()) {
                return ERROR;
            }

            DaoNhaptaycnMain daoMain = new DaoNhaptaycnMain();
            HashMap hmParameter = getParameter();
            if (Grade.equals("2"))
            {
                int result = daoMain.MoChotMuasamTS(khoa_muasamts, UserName, "", hmParameter.get("nam_bc").toString(), hmParameter.get("dot_ms").toString(), poscd, hmParameter.get("nghiepvums").toString(),
                    hmParameter.get("trangthaims").toString());
                if (result == 0) {
                    addActionError("Bạn chưa mở duyệt được báo cáo xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
            }
            else if (Grade.equals("3"))
            {
                if(!daoMain.setStatusLockTw(khoa_muasamts,poscd,"SEND", UserName, Grade, hmParameter.get("nam_bc").toString(), hmParameter.get("dot_ms").toString(), hmParameter.get("nghiepvums").toString(),
                    hmParameter.get("trangthaims").toString())){
                    addActionError("Bạn chưa mở khóa được cho PGD, xin liên hệ với quản trị để khắc phục");
                    return ERROR;
                }
                
            }
            

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> ChotMuaSamTS: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> ChotMuaSamTS: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã mở duyệt thành công");
        return SUCCESS;
    }
}
