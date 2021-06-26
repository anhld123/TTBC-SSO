package vbsp.ims.khnv2021;

import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.apache.struts2.ServletActionContext;
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


    public XayDungKeHoach2021() {}

    
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_xaydungkh_2021() {

        try {
            namBc = getDefaultYearReport();
            getInfo();
            if(!reportGrade.equals("1"))
            {
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
            int yearPre = Integer.parseInt(namBc)  -1;
            namBc_pre = String.valueOf(yearPre);
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
                lstDulieuNt2 = daoXdkh.getDataAuthCommuneSum(maBc, userId, reportGrade, namBc, dotBc, "270201", "27020101");
                return "loadAllCommuneAuth";
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
            String message = daoXdkh.getCheckInputPGD(maBc, namBc, dotBc, pos_cd_username, reportGrade,userId);
            if(!message.endsWith("AAA"))
            {
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
            commune_cd = request.getParameter("commune_cd");
            setSubCommuneList(daoXdkh.getSubCommuneList(pos_cd_username, commune_cd, reportGrade));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getSubcommune -> " + e.getMessage());
        }
        return SUCCESS;
    }

    
    public String Lock_Unlock() {
        try {
            getInfo();
            HashMap hmParameter = getParameter();
//            String a =hmParameter.get("lock_unlock").toString();
            if(daoXdkh.setLockUnlockCommune(commune_cd, namBc, dotBc, lock_unlock, userId, reportGrade))
            {
                addActionMessage("Bạn đã chốt/mở chốt thành công");
                return SUCCESS;
            }
            else
            {
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
            FileExportInfo fileInfo = excelExport.xuatExcelMau01(lstCommune, savedDir, namBc, dotBc);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv01 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv01 " + ex.getMessage());
            return ERROR;
        }
    }
    
    public String ExpExcelKhnv01a() {
        try {
            getInfo();
            request = ServletActionContext.getRequest();
            String savedDir = !request.getRealPath("/").endsWith("/") ? request.getRealPath("/") + "/" : request.getRealPath("/");
            ExcelExport excelExport = new ExcelExport();
            List<String> lstSubCommune = new ArrayList<>();
            
            if (commune_cd.equals("000000")) {
                addActionError("Bạn chưa chọn xã/phường");
                return ERROR;
            }
            
            List<POSModel> lstCommuneFull = new ArrayList<>();
            lstCommuneFull = daoXdkh.getCommuneList(pos_cd_username);
            
            if (!subcommune_cd.equals("000000")) {
                lstSubCommune.add(subcommune_cd);
            } else {
                lstSubCommune = daoXdkh.getAllSubCommune(pos_cd_username, commune_cd);
            }
            
            String communeName = "";
            for(int i = 0; i < lstCommuneFull.size(); i++) {
                if (lstCommuneFull.get(i).getId().equals(commune_cd)) {
                    communeName = lstCommuneFull.get(i).getDesc();
                    break;                    
                }
            }
            
            FileExportInfo fileInfo = excelExport.xuatExcelMau01a(pos_cd_username, commune_cd, communeName, lstSubCommune, "31-may-2021", namBc, dotBc, savedDir);
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
            FileExportInfo fileInfo = excelExport.xuatExcelMau02(pos, "N", "31-may-2021", namBc, dotBc, savedDir);
            fileNamelocal = fileInfo.fileName;
            filereport = fileInfo.filePath;
            return SUCCESS;
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " ExpExcelKhnv02 " + ex.getMessage());
            System.err.println(this.getClass().getName() + " Loi ExpExcelKhnv02 " + ex.getMessage());
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
    
    public String openExcelUpload(){
        return SUCCESS;
    }
//<editor-fold defaultstate="collapsed" desc="Getter Setter">


//</editor-fold>    

}
