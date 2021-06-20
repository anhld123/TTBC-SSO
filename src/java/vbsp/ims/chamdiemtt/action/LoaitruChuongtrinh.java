/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.chamdiemtt.action;

import static com.opensymphony.xwork2.Action.ERROR;
import java.io.File;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.chamdiemtt.dao.DaoChamdiemttMain;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlChuongtrinhLoaitru;

/**
 *
 * @author BAOANH
 */
public class LoaitruChuongtrinh extends ActionChamdiemttMain {

    public List<CHUONGTRINH_LOAITRU> lstChtrinhLoaitru = new ArrayList<>();
    protected List<ModelViewSend> lstViewSend = new ArrayList<>();

    public List<ModelViewSend> getLstViewSend() {
        return lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }

    public List<CHUONGTRINH_LOAITRU> getLstChtrinhLoaitru() {
        return lstChtrinhLoaitru;
    }

    public void setLstChtrinhLoaitru(List<CHUONGTRINH_LOAITRU> lstChtrinhLoaitru) {
        this.lstChtrinhLoaitru = lstChtrinhLoaitru;
    }

    public String loadPageMain() {
        try {

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadPageMain -> " + e.getMessage());
            addActionError("Lỗi bạn không thể load được tham số " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String khoitaoLoaitru() {
        try {
//            System.err.println("khoa_bcqt=" + khoa_bcqt);
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade, "CDTT_CN");
            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else if (Grade.equals("2")) {
                setTreeNodeGrade12(lstModelTree);
            } else {
                addActionError("Cấp Phòng giao dịch không có quyền ở chức năng này");

                return ERROR;
            }
            // lstBcqtParams = daoMain.getReportParmams(conn, khoa_bcqt);

            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> khoitaoLoaitru: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> khoitaoLoaitru: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String loadFormChtrinhLoaitru() {
        try {
//            System.err.println("khoa_bcqt=" + khoa_bcqt);
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();

            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            if(lstPos==null) lstPos= new ArrayList<String>();
            if (lstPos.size() > 1) {
                addActionError("Để chỉnh sửa thông tin bạn chỉ được phép chọn 1 đơn vị !");

                return ERROR;
            }

            String ngaybc = "";
            try {
                ngaybc = hmParameter.get("ngay_bc").toString();
            } catch (Exception e) {
                ngaybc = "31-jan-2020";
            }
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            //khoi tao cho treeview cac pos
            if (Grade.equals("1")) {
                addActionError("Cấp Phòng giao dịch không có quyền ở chức năng này");

                return ERROR;
            } else if (Grade.equals("2")) {
                if (lstPos == null || lstPos.size() == 0) {
                    addActionError("Bạn phải chọn Phòng giao dịch để tải số liệu");
                    return ERROR;
                }
                lstChtrinhLoaitru = daoMain.getDataChuongtrinhLoaitru("999999", ngaybc, Grade, UserName, lstPos);
            } else {
                if (lstPos == null || lstPos.size() == 0) {
                    lstPos.add("000100");
                    pos_string = "000100";
                }
                else pos_string=lstPos.get(0);
                lstChtrinhLoaitru = daoMain.getDataChuongtrinhLoaitru("999999", ngaybc, Grade, UserName, lstPos);
                if(lstChtrinhLoaitru.size()>0) thuyetminh=lstChtrinhLoaitru.get(0).getTenPGD();
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadFormChtrinhLoaitru: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadFormChtrinhLoaitru: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveChuongtrinhLoaitru() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();

            String ngaybc = "";
            try {
                ngaybc = hmParameter.get("ngay_bc").toString();
            } catch (Exception e) {
                ngaybc = "31-jan-2020";
            }
//            List<String> lstPos = (List<String>) hmParameter.get("poscd");
//
//            if (lstPos == null || lstPos.size() == 0) {
//                addActionError("Bạn phải chọn Phòng giao dịch để tải số liệu");
//
//                return ERROR;
//            }
            if (lstChtrinhLoaitru == null || lstChtrinhLoaitru.size() == 0) {
                addActionError("Bạn phải tải dữ liệu trước khi lưu !");
                return ERROR;
            }
            for (CHUONGTRINH_LOAITRU value : lstChtrinhLoaitru) {
                QT_DULIEU_NT dulieu = new QT_DULIEU_NT();
                dulieu.setMACN(value.getMacn());
                dulieu.setMAPGD(value.getMapgd());
                dulieu.setMA(value.getMact());
                dulieu.setTEN(value.getTenct());
                dulieu.setTT_HIENTHI(value.getMonthString());
                dulieu.setD1(value.getMonthString()); //Thang ap dung
                dulieu.setD2("A"); //Trạng thái
                dulieu.setD3(Grade); //Cấp báo cáo
                Date sdf = new SimpleDateFormat("dd-MMM-yyyy").parse(ngaybc);
                dulieu.setNGAYBC(sdf);
                dulieu.setNGAY_NHAP(new Date());
                dulieu.setNGUOI_NHAP(UserName);
                lstDulieuNt.add(dulieu);
            }

            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            daoMain.saveChuongtrinhLoaitru(UserName, "999999", ngaybc, lstDulieuNt, Grade);

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> saveChuongtrinhLoaitru: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> saveChuongtrinhLoaitru: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
    }

    public String sendChuongtrinhLoaitru() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            HashMap hmParameter = getParameter();
            List<String> lstPos = (List<String>) hmParameter.get("poscd");
            Map<String, Integer> mapStatusSend = new HashMap();
            String ngaybc = "";
            try {
                ngaybc = hmParameter.get("ngay_bc").toString();
            } catch (Exception e) {
                ngaybc = "31-jan-2020";
            }
            DaoChamdiemttMain daoMain = new DaoChamdiemttMain();
            String msg = daoMain.getMsgSendata(Grade, ngaybc);
            if (msg != null && !msg.isEmpty()) {
                addActionError(msg);
                return ERROR;
            }
            for (String mapgd : lstPos) {

                ServletContext context = ServletActionContext.getServletContext();
                String strPathSave = !context.getRealPath("/").endsWith("/")
                        ? context.getRealPath("/") + "/" + Define.M_REPORT_XML
                        : context.getRealPath("/") + Define.M_REPORT_XML;

                strPathSave += "ChtrinhLoaitru_" + mapgd
                        + "_" + UserName + "_"
                        + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";

                List<String> lstData = new ArrayList<>();
                boolean bStatus_file = false;

                lstData = daoMain.getDataSendCTLT(mapgd, ngaybc, Grade);
                if (lstData == null || lstData.size() == 0) {
                    mapStatusSend.put(mapgd, 6);
                    continue;
                }
                bStatus_file = new XmlChuongtrinhLoaitru().createXmlFileCTLT(Define.PARA_SYN_REPORT_CTLT,
                        ngaybc, UserName, Grade,
                        mapgd, lstData, strPathSave);

                if (!bStatus_file) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi của PGD " + mapgd);
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong tao duoc file " + strPathSave);

                    mapStatusSend.put(mapgd, 1); //1 la tao file xml bi loi
//                    return ERROR;
                }
                //Tao file xml theo cau truc
//
                File checkfile = new File(strPathSave);
                if (!checkfile.exists()) {
//                    addActionError("Bạn chưa tạo được file dữ liệu để gửi. Xin liên hệ với quản trị để khắc phục");
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong tao duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 2); //2 la khong tim thay file xml
//                    return ERROR;
                }
                ProcessReportSyn clientWritexml = new ProcessReportSyn();
                String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
//
                if (sStatus.equals(Define.WEB_SERVICES_STATUS_FAIL)) {
                    System.err.println("Ban chua dong bo du lieu duoc ve TW");
//                    addActionError("Lỗi bạn chưa gửi dữ liệu được về trung ương ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong dong bo duoc file " + strPathSave);
                    mapStatusSend.put(mapgd, 3); //3 la gui file du lieu bi loi
//                    return ERROR;
                } else if (sStatus.equals(Define.WEB_SERVICES_STATUS_OK)) {
//                    addActionMessage("Bạn gửi dữ liệu về trung ương thành công");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 4);  //gui du lieu thanh cong
                } else {
//                    addActionMessage("Bạn không thể gửi dữ liệu lên trung ương do bị khóa </br>Xin liên hệ về Ban KT&QLTC để được gửi lại số liệu ! ");
                    if (checkfile.exists()) {
                        checkfile.delete();
                    }
                    mapStatusSend.put(mapgd, 5);  //pgd bi khoa khong gui duoc du lieu
//                    return ERROR;
                }
            }
            setLstViewSend(getViewStatusSend(lstPos, mapStatusSend));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendChươngtrinhLoaitru: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendChươngtrinhLoaitru: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
            return ERROR;
        }
        return SUCCESS;
    }

    private List<ModelViewSend> getViewStatusSend(List<String> lstPos, Map<String, Integer> mapStatus) {
        addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        List<ModelViewSend> lstStatus = new ArrayList();
        try {
            Map<String, String> mapPosByName = DaoKtgsMain.newInstance().getPosByName(lstPos);

            for (String key : mapStatus.keySet()) {

                if (mapPosByName.get(key) == null) {
                    continue;
                };
                Integer value = mapStatus.get(key);
                ModelViewSend modelview = ModelViewSend.newInstance();
                modelview.setMapgd(key);
                modelview.setKey(value);
                modelview.setTenpgd(mapPosByName.get(key));

                switch (value) {
                    case 1:
                        modelview.setMota_loi("Tạo file xml bị lỗi");
                        break;
                    case 2:
                        modelview.setMota_loi("Không tìm thấy file xml");
                        break;
                    case 3:
                        modelview.setMota_loi("Gửi dữ liệu bị lỗi");
                        break;
                    case 4:
                        modelview.setMota_loi("Thành công");
                        break;
                    case 5:
                        modelview.setMota_loi("Phòng giao dịch này bị khóa");
                        break;
                    case 6:
                        modelview.setMota_loi("Không có dữ liệu");
                        break;
                    case 7:
                        modelview.setMota_loi("Bạn chỉ được gửi dữ liệu trước ngày cuối tháng");
                        break;
                    default:
                        modelview.setMota_loi("Không đúng trạng thái lỗi");
                        break;
                }
                lstStatus.add(modelview);
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + e.getMessage());
            addActionError("Có lỗi xảy ra: " + e.getMessage().replaceAll("\\r\\n|\\r|\\n|\"|\'", " "));
        }
        return lstStatus;
    }
}
