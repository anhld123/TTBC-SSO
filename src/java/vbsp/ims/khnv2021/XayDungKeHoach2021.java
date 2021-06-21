package vbsp.ims.khnv2021;

import vbsp.ims.khnv2021.dao.XDKHDao2021;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.util.ArrayList;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.define.Define;
import vbsp.ims.khnv2021.excel.ExcelExport;
import vbsp.ims.khnv2021.model.FileExportInfo;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.khnv.XdkhModel;

/**
 *
 * @author CuongBM0211
 */
public class XayDungKeHoach2021 extends ActionMainKHNV2021 {

    private XDKHDao2021 daoXdkh = new XDKHDao2021();
    private List<XdkhModel> xdkhModelList;  //Lay du lieu load len table
    private String xa_pgd;
    //Cac truong dung cho luu du lieu
    private List<String> KH_MA_CT;
    private List<String> KH_STT_HT;
    private List<String> KH_CHI_TIEU;
    private List<String> KH_UOC_TH;
    private List<String> KH_KH_NAM;
    private List<String> KH_DN;
    private List<String> KH_FONTWEIGHT;
    private List<String> KH_CAPHT;
    private List<String> KH_STT;

    public XayDungKeHoach2021() {
    }

    @Override
    public String execute() throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String get_data_xaydungkh_2021() {

        try {
            namBc = getDefaultYearReport();
            getInfo();
            posList = daoXdkh.getPosList(pos_cd_username, maCn, reportGrade);
            subCommuneList = daoXdkh.getSubCommuneList(pos_cd_username, "", reportGrade);
            lstMaBC = daoXdkh.getLOV(userId, Define.LOV_MABC);
            lstNamBC = daoXdkh.getLOV(userId, Define.LOV_NAMBC);
            lstDotBC = daoXdkh.getLOV(userId, Define.LOV_DOTBC);
            lstTongHop = daoXdkh.getLOV(userId, Define.LOV_VIEW_TYPE);
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " loi get_data_xaydungkh " + e.getMessage());
            System.err.println(this.getClass().getName() + " loi get_data_xaydungkh " + e.getMessage());
        }
        return "success";
    }

    public String getDataXayDungKH() {
        try {

            getInfo();
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
                return "loadAllCommuneAuth";
            }

            if (maBc.equals("KHNV_02")) {
                return "KHNV02";
            }

        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
        }

        return SUCCESS;
    }

    public String getDataXayCommuneDetai() {
        try {

            getInfo();
            //TH load theo 1 thôn
            lstDulieuNt = daoXdkh.getDataAuthCommune(maBc, userId, reportGrade, namBc, dotBc, commune_detai, "000000");
            return "loadAllSubCommune";

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
                lstCommune = daoXdkh.getAllCommune(pos_cd_username);
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
//<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public String getXa_pgd() {
        return xa_pgd;
    }

    public void setXa_pgd(String xa_pgd) {
        this.xa_pgd = xa_pgd;
    }

    public List<XdkhModel> getXdkhModelList() {
        return xdkhModelList;
    }

    public void setXdkhModelList(List<XdkhModel> xdkhModelList) {
        this.xdkhModelList = xdkhModelList;
    }

    public List<String> getKH_MA_CT() {
        return KH_MA_CT;
    }

    public void setKH_MA_CT(List<String> KH_MA_CT) {
        this.KH_MA_CT = KH_MA_CT;
    }

    public List<String> getKH_STT_HT() {
        return KH_STT_HT;
    }

    public void setKH_STT_HT(List<String> KH_STT_HT) {
        this.KH_STT_HT = KH_STT_HT;
    }

    public List<String> getKH_CHI_TIEU() {
        return KH_CHI_TIEU;
    }

    public void setKH_CHI_TIEU(List<String> KH_CHI_TIEU) {
        this.KH_CHI_TIEU = KH_CHI_TIEU;
    }

    public List<String> getKH_UOC_TH() {
        return KH_UOC_TH;
    }

    public void setKH_UOC_TH(List<String> KH_UOC_TH) {
        this.KH_UOC_TH = KH_UOC_TH;
    }

    public List<String> getKH_KH_NAM() {
        return KH_KH_NAM;
    }

    public void setKH_KH_NAM(List<String> KH_KH_NAM) {
        this.KH_KH_NAM = KH_KH_NAM;
    }

    public List<String> getKH_DN() {
        return KH_DN;
    }

    public void setKH_DN(List<String> KH_DN) {
        this.KH_DN = KH_DN;
    }

    public List<String> getKH_FONTWEIGHT() {
        return KH_FONTWEIGHT;
    }

    public void setKH_FONTWEIGHT(List<String> KH_FONTWEIGHT) {
        this.KH_FONTWEIGHT = KH_FONTWEIGHT;
    }

    public List<String> getKH_CAPHT() {
        return KH_CAPHT;
    }

    public void setKH_CAPHT(List<String> KH_CAPHT) {
        this.KH_CAPHT = KH_CAPHT;
    }

    public List<String> getKH_STT() {
        return KH_STT;
    }

    public void setKH_STT(List<String> KH_STT) {
        this.KH_STT = KH_STT;
    }
//</editor-fold>    

}
