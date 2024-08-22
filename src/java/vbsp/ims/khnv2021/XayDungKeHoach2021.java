package vbsp.ims.khnv2021;

import com.lowagie.text.pdf.PdfName;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.action.Utilities;
import vbsp.ims.define.Define;
import vbsp.ims.khnv2021.excel.ExcelExport;
import vbsp.ims.khnv2021.model.FileExportInfo;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.POSModel;

/**
 *
 * @author CuongBM0211
 */
public class XayDungKeHoach2021 extends ActionMainKHNV {

    private XDKHDao2021 daoXdkh = new XDKHDao2021();

    private List<POSModel> custCommuneList = new ArrayList<>();
    private List<POSModel> custSubCommuneList = new ArrayList<>();
    private String namBc_1;
    private String namBc_2;
    private String namBc_3;
    private String namBc_4;

    public XDKHDao2021 getDaoXdkh() {
        return daoXdkh;
    }

    public void setDaoXdkh(XDKHDao2021 daoXdkh) {
        this.daoXdkh = daoXdkh;
    }

    public String getNamBc_1() {
        return namBc_1;
    }

    public void setNamBc_1(String namBc_1) {
        this.namBc_1 = namBc_1;
    }

    public String getNamBc_2() {
        return namBc_2;
    }

    public void setNamBc_2(String namBc_2) {
        this.namBc_2 = namBc_2;
    }

    public String getNamBc_3() {
        return namBc_3;
    }

    public void setNamBc_3(String namBc_3) {
        this.namBc_3 = namBc_3;
    }

    public String getNamBc_4() {
        return namBc_4;
    }

    public void setNamBc_4(String namBc_4) {
        this.namBc_4 = namBc_4;
    }

    public XayDungKeHoach2021() {
    }

    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_xaydungkh_2021() {

        try {
            namBc = getDefaultYearReport();
            getInfo();
            namBc = String.valueOf(Integer.parseInt(namSau));
            if (!reportGrade.equals("1")) {
                addActionError("Chức năng này chỉ thực hiện cho cấp PGD");
                return ERROR;
            }
            posList = daoXdkh.getPosList(pos_cd_username, maCn, reportGrade);
            subCommuneList = daoXdkh.getSubCommuneList(pos_cd_username, "", reportGrade);
            lstMaBC = daoXdkh.getLOV(userId, Define.LOV_MABC);
            lstNamBC = daoXdkh.getLOV(userId, Define.LOV_NAMBC);
            lstDotBC = daoXdkh.getLOV(userId, Define.LOV_DOTBC);
            lstTongHop = daoXdkh.getLOV(userId, Define.LOV_VIEW_TYPE);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " loi get_data_xaydungkh " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi get_data_xaydungkh " + e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String getDataXayDungKH() {
        try {
            HashMap hmParameter = getParameter();
//            setDotBc(hmParameter.get("dotBc").toString());
            int yearPre = Integer.parseInt(namBc) - 1;
            int year2Pre = Integer.parseInt(namBc) - 2;
            namBc_pre = String.valueOf(yearPre);
            namBc_2pre = String.valueOf(year2Pre);

            getInfo();
//            setDotBc(dotBc);
//            setNamBc(namBc);
            setReasonReject(daoXdkh.getReason(maBc, namBc, dotBc, pos_cd_username, reportGrade));
            //TH load mẫu 02 theo pos
            if (maBc.equals("KHNV_02")) {
                lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_cd, subcommune_cd);
                return "load02Pos";
            }
            //TH load theo 1 thôn
            if (maBc.equals("KHNV_01A") && !commune_cd.equals("000000") && !subcommune_cd.equals("000000")) {
                lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_cd, subcommune_cd);
                return "loadOneSubCommune";
            }
            //TH load các thôn trong xã
            if (maBc.equals("KHNV_01A") && !commune_cd.equals("000000") && subcommune_cd.equals("000000")) {
                lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_cd, subcommune_cd);
                return "loadAllSubCommune";
            }
            //TH load các xã
            if (maBc.equals("KHNV_01A") && commune_cd.equals("000000")) {
                lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_cd, subcommune_cd);
                lstDulieuNt2 = daoXdkh.getDataAuthCommuneSum(maBc, userId, reportGrade, namBc, dotBc, "000000", "000000");
                return "loadAllCommuneAuth";
            }
            if (maBc.equals("KHNV_02_2024")) {
                lstDulieuNt = daoXdkh.getData_02_2024(maBc, userId, reportGrade, namBc, dotBc, commune_cd);
                int year1 = Integer.parseInt(namBc) + 1;
                int year2 = Integer.parseInt(namBc) + 2;
                int year3 = Integer.parseInt(namBc) + 3;
                int year4 = Integer.parseInt(namBc) + 4;
                namBc_1 = String.valueOf(year1);
                namBc_2 = String.valueOf(year2);
                namBc_3 = String.valueOf(year3);
                namBc_4 = String.valueOf(year4);
//                System.out.println(maBc + " 2. " + userId + " 3. " + reportGrade + " 4. " + namBc + " 5. " + dotBc + " 6. " + commune_cd);
                return "load02_2024";
            }

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
        }

        return SUCCESS;
    }

    public String guiChinhanh() {
        try {
            getInfo();
            String message = daoXdkh.getCheckInputPGD(maBc, namBc, dotBc, pos_cd_username, reportGrade, userId);
            if (!message.endsWith("AAA")) {
                addActionError("Bạn chưa nhập số liệu mẫu 02 tại pgd!");
                return ERROR;
            }
            addActionMessage("Bạn đã gửi thành công số liệu lên chi nhánh");
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            addActionError("Gửi lỗi!");
            return ERROR;
        }
    }

    public String getSubcommune() {
        try {
//            commune_cd = request.getParameter("commune_cd");
//            setSubCommuneList(daoXdkh.getSubCommuneList(pos_cd_username, commune_cd, reportGrade));
            getInfo();
            custCommuneList = daoXdkh.getPosList(pos_cd_username, maCn, reportGrade);
            if (commune_cd.isEmpty()) {
                custSubCommuneList = daoXdkh.getSubCommuneList(pos_cd_username, "", reportGrade);
            } else {
                custSubCommuneList = daoXdkh.getSubCommuneList(pos_cd_username, commune_cd, reportGrade);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getSubcommune -> " + e.getMessage());
        }
        return "success";
    }

    public String Lock_Unlock() {
        try {
            getInfo();
            HashMap hmParameter = getParameter();
//            String a =hmParameter.get("lock_unlock").toString();
            if (daoXdkh.setLockUnlockCommune(commune_cd, namBc, dotBc, lock_unlock, userId, reportGrade)) {
                addActionMessage("Bạn đã chốt/mở chốt thành công");
                return SUCCESS;
            } else {
                addActionError("Bạn đã chốt/mở chốt thất bại. Vui lòng liên hệ với quản trị");
                return ERROR;
            }
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " Lock_Unlock " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi Lock_Unlock " + ex.getMessage());
            addActionError("Bạn đã chốt/mở chốt thất bại. Vui lòng liên hệ với quản trị");
            return ERROR;
        }

//            return SUCCESS;
    }

    public String getDataXayCommuneDetai() {
        try {

            getInfo();
            //TH load theo 1 thôn
            lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_detai, "000000");
            return "loadAllSubCommune";

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " getDataXayCommuneDetai " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayCommuneDetai " + ex.getMessage());
            return ERROR;
        }

    }

    public String getDataXaySubCommuneDetai() {
        try {

            getInfo();
            //TH load theo 1 thôn
            lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_detai, subcommune_detail);
            return "loadOneSubCommune";

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return ERROR;
        }

    }

    public String ExpExcelKhnv01() {
        try {
            getInfo();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            List<String> lstCommune = new ArrayList<>();
            if (!commune_cd.equals("000000")) {
                lstCommune.add(commune_cd);
            } else {
                lstCommune = daoXdkh.getAllCommune(pos_cd_username, maBc, namBc, dotBc);
            }
            FileExportInfo fileInfo = excelExport.xuatExcelMau01(lstCommune, savedDir, namBc, dotBc, pos_cd_username);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            return ERROR;
        }
    }

//    Xuất xls các chỉ tiêu thuyết minh mẫu 01
    public String ExpExcelKhnv01New() {
        try {
            getInfo();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            List<String> lstCommune = new ArrayList<>();

            List<POSModel> lstCommuneFull = new ArrayList<>();
            lstCommuneFull = daoXdkh.getCommuneListAll(pos_cd_username);

            if (!commune_cd.equals("000000")) {
                lstCommune.add(commune_cd);
            } else {
                for (POSModel communecd : lstCommuneFull) {
                    lstCommune.add(communecd.getId());
                }
            }

//            String communeName = "";
//            for(int i = 0; i < lstCommuneFull.size(); i++) {
//                if (lstCommuneFull.get(i).getId().equals(commune_cd)) {
//                    communeName = lstCommuneFull.get(i).getDesc();
//                    break;                    
//                }
//            }
            FileExportInfo fileInfo = excelExport.xuatExcelMau01(pos_cd_username, lstCommune, new Utilities().fnc_getDateBC(namBc, dotBc), namBc, dotBc, savedDir);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01a " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01a " + ex.getMessage());
            return ERROR;
        }
    }

    public String ExpExcelKhnv02() {
        try {
            getInfo();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            POSModel pos = daoXdkh.getPosByCode(pos_cd_username);
            FileExportInfo fileInfo = excelExport.xuatExcelMau02(pos, "N", new Utilities().fnc_getDateBC(namBc, dotBc), namBc, dotBc, savedDir);
            if (fileInfo != null) {
                fileNamelocal = fileInfo.fileName;
                filereport = fileInfo.filePath;
                return SUCCESS;
            } else {
                addActionError("Không có dữ liệu");
                return ERROR;
            }
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv02 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv02 " + ex.getMessage());
            return ERROR;
        }
    }

    public String ExpExcelKhnv01B() {
        try {
            System.out.println("vào ham ExpExcelKhnv01B");
            getInfo();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            List<POSModel> lstCommune = new ArrayList<>();
            List<POSModel> lstCommuneFull = new ArrayList<>();
            lstCommuneFull = daoXdkh.getCommuneListAll(pos_cd_username);
            System.out.println("So thon trong xa = " + lstCommuneFull.size());
            if (!commune_cd.equals("000000")) {
                System.out.println("vao 1 ");
                for (POSModel item : lstCommuneFull) {
                    if (item.getId().equals(commune_cd)) {
                        lstCommune.add(item);
                        break;
                    }
                }
            } else {
                System.out.println("vao 2 ");
                lstCommune.addAll(lstCommuneFull);
            }
            System.out.println("den day ");
            FileExportInfo fileInfo = excelExport.xuatExcelMau01B(pos_cd_username, lstCommune, new Utilities().fnc_getDateBC(namBc, dotBc), namBc, dotBc, savedDir);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01b " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01b " + ex.getMessage());
            return ERROR;
        }
    }

    public String xuatExcel_KHNV01_2024() {
        try {
            getInfo();
            HashMap hmParameter = getParameter();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            POSModel pos = daoXdkh.getPosByCode(pos_cd_username);
            String maxa = hmParameter.get("commune_cd").toString();
//            String tenthon = pos.getDesc();
            String mathon = hmParameter.get("subcommune_cd").toString();

            if (maxa.equals("000000") || maxa.equals(NONE)) {
                addActionError("Bạn chưa chọn mã xã!");
                return ERROR;
            }
//            if (mathon.equals("000000") || mathon.equals(NONE)) {
//                addActionError("Bạn chưa chọn mã thôn!");
//                return ERROR;
//            }
            ArrayList<POSModel> listxa = daoXdkh.getNameSubCommune(maxa, "");
            String tenthon = listxa.get(0).getDesc();

            FileExportInfo fileInfo = excelExport.xuatExcel_Mau01_2024(pos_cd_username, maxa, mathon, tenthon, new Utilities().fnc_getDateBC(namBc, dotBc), namBc, dotBc, savedDir);
            if (fileInfo != null) {
                fileNamelocal = fileInfo.fileName;
                filereport = fileInfo.filePath;
                return SUCCESS;
            } else {
                addActionError("Không có dữ liệu");
                return ERROR;
            }
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " 0102024 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi 012024 " + ex.getMessage());
            return ERROR;
        }
    }

    public String xuatExcel_KHNV02_2024() {
        try {
            getInfo();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            POSModel pos = daoXdkh.getPosByCode(pos_cd_username);
            FileExportInfo fileInfo = excelExport.xuatExcel_Mau02_2024(pos, "N", new Utilities().fnc_getDateBC(namBc, dotBc), namBc, dotBc, savedDir);
            if (fileInfo != null) {
                fileNamelocal = fileInfo.fileName;
                filereport = fileInfo.filePath;
                return SUCCESS;
            } else {
                addActionError("Không có dữ liệu");
                return ERROR;
            }
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " 022024 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi 022024 " + ex.getMessage());
            return ERROR;
        }
    }

    public String ExpExcelKhnv01B_3N() {
        try {
            System.out.println("vào ham ExpExcelKhnv01B_3N");
            getInfo();
            System.out.println("So thon trong xa = " + pos_cd_username);
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            System.out.println("So thon trong xa = " + savedDir);
            ExcelExport excelExport = new ExcelExport();
            List<POSModel> lstCommune = new ArrayList<>();
            List<POSModel> lstCommuneFull = new ArrayList<>();
            lstCommuneFull = daoXdkh.getCommuneListAll(pos_cd_username);
            System.out.println("So thon trong xa = " + lstCommuneFull.size());
            if (!commune_cd.equals("000000")) {
                for (POSModel item : lstCommuneFull) {
                    if (item.getId().equals(commune_cd)) {
                        lstCommune.add(item);
                        break;
                    }
                }
            } else {
                lstCommune.addAll(lstCommuneFull);
            }
            System.out.println("So thon trong xa 111 = " + lstCommune.size());
            FileExportInfo fileInfo = excelExport.xuatExcelMau01B_3N(pos_cd_username, lstCommune, new Utilities().fnc_getDateBC(namBc, dotBc), namBc, dotBc, savedDir);
            System.out.println("vao day 31 = ");
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            System.out.println("vao day 32 = " + fileNamelocal);
            System.out.println("vao day 33 = " + filereport);
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01B_3N " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01B_3N " + ex.getMessage());
            return ERROR;
        }
    }

//    public String xuatxls() {
//        try {
//            getInfo();
//            HashMap hmParameter1;
//
//            request = ServletActionContext.getRequest();
//            String strTimeFile = Long.toString(System.currentTimeMillis());
//            ArrayList<String> fullPathList = new ArrayList<>();
//            String zipFile = "FileNen_KHNV01_"  + strTimeFile + ".zip", zipPath = "";
//            lstOfTextFile.clear();
//            filesList.clear();
//            zipFileList.clear();
//            
//            List<String> lstCommune = new ArrayList<>();
//            if (!commune_cd.equals("000000"))
//                lstCommune.add(commune_cd);
//            else 
//                lstCommune = daoXdkh.getAllCommune(pos_cd_username);
//            
//
//            for (String value : lstCommune) {
//
//                        String save_id = "KHNV01";
//
//                        HashMap<String, String> paramHashMap = new HashMap<>();
//                        String sPos_cd = "";
//                        String stringParaPos_cd = "";
//                        String sPosFlag = "";
//                        //xy lay lay cac tham so cho vao hashmap
//                        Map mapCollectPara = new HashMap();
//
//                        //xu ly cho export file ra PDF hoac la Excel
//                        String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
//                        //duong dan chua file tren o dia + Define.M_REPORT_XLS
//                        String strPathSave = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");;
//                        //Ham nay lay ra ten file bao cao can tao, ten file jasper report
//
//                        strTimeFile = Long.toString(System.currentTimeMillis());
//
//                        String strFileSave = save_id + "_" + value 
//                                + "_" + strCurrDate
//                                + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());
//
//                        strPathSave += Define.M_REPORT_XLS;
//                        strFileSave += ".XLSX";
//                        filereport = strFileSave;
//                        File Checkpath = new File(strPathSave);
//                        if (!Checkpath.exists()) {
//                            System.out.println("Da tao thu muc: " + strPathSave);
//                            Checkpath.mkdirs();
//                        }
//                        //Xuat file du lieu o day
//
//                        XDKHDao2021 daoQuery = new XDKHDao2021();
//
////                        setQuery(daoQuery.getQuery(save_id, new DaoConnect().getConnect()));
//                        ImsPlSqlQuery plsql = new ImsPlSqlQuery();
//
//                        Date sdf = new  Date();
//                        try {
//                            sdf = new SimpleDateFormat("dd-MMM-yyyy").parse("31-may-2021");
//                        } catch (ParseException ex) {
//                            Logger.getLogger(P0001.class.getName()).log(Level.SEVERE, null, ex);
//                        }
//                        paramHashMap.put("PD_REPORT_DATE", new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
//
//                        mapCollectPara.put("PD_REPORT_DATE",
//                                ImsFillParaMeter.newInstance("VARCHAR2", new SimpleDateFormat("dd-MMM-yyyy").format(sdf)));
//                        sPos_cd = "000000";
//                        stringParaPos_cd = "PV_POS_CD";
//                        sPosFlag = "N";
//
//                        daoQuery.getDataExp(save_id, paramHashMap, sPos_cd, stringParaPos_cd, sPosFlag, strPathSave + strFileSave, namBc, dotBc);
//
//                        //Kiem tra xem file da tao thanh cong chua
//                        File filerpt = new File(strPathSave + strFileSave);
////                        if (!filerpt.exists()) {
////                            setMessage("Lỗi bạn chưa tạo được file báo cáo " + strFileSave);
////                            return ERROR;
////                        }
//                        fileNamelocal = strPathSave + strFileSave;
//
//                        lstOfTextFile.add(fileNamelocal);
//                        FileInfo file = new FileInfo(new File(fileNamelocal));
//                        filesList.add(new DownloadFileInfor(file.getName(), fileNamelocal,
//                                DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
//                        fullPathList.add(file.getAbsolutePath());
//                        zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
//                    }
////                }
////            }
//
//            if (fullPathList.size() > 1) {
//                try {
//                    FileZip.ZipFileFromArray(fullPathList, zipPath);
//                    zipFileList.add(zipFile);
//                    zipFileList.add(zipPath);
//                } catch (Exception ex) {
//                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
//                }
//                filereport = zipFile;
//                fileNamelocal = zipPath;
//            }
//
//            System.gc();
//            return SUCCESS;
//    }
//    catch (Exception ex) {
//            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
//        System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
//        return "";
//    }
//}
    public String openExcelUpload() {
        return SUCCESS;
    }
//<editor-fold defaultstate="collapsed" desc="Getter Setter">

//</editor-fold>    
    public List<POSModel> getCustCommuneList() {
        return custCommuneList;
    }

    public void setCustCommuneList(List<POSModel> custCommuneList) {
        this.custCommuneList = custCommuneList;
    }

    public List<POSModel> getCustSubCommuneList() {
        return custSubCommuneList;
    }

    public void setCustSubCommuneList(List<POSModel> custSubCommuneList) {
        this.custSubCommuneList = custSubCommuneList;
    }
}
