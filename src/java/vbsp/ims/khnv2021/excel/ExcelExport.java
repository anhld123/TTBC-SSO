/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.khnv2021.excel;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import vbsp.ims.canhbaosaisottt.P0001;
import vbsp.ims.define.Define;
import vbsp.ims.define.DefineFun;
import vbsp.ims.khnv2021.dao.XDKHDao2021;
import vbsp.ims.khnv2021.model.FileExportInfo;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.DownloadFileInfor;
import vbsp.ims.model.ExportText2SbvManager;
import vbsp.ims.model.FileInfo;
import vbsp.ims.query.ImsFillParaMeter;
import vbsp.ims.query.ImsPlSqlQuery;
import vbsp.ims.zip.FileZip;

/**
 *
 * @author HP
 */
public class ExcelExport {

    public ExcelExport(){}
    
    public FileExportInfo xuatExcelMau01(List<String> lstCommune, String savedDirPath, String namBc, String dotBc) {
        String filePath ="", fileName ="";
        List<String> lstOfTextFile = new ArrayList<>();
        List<DownloadFileInfor> filesList = new ArrayList<>();
        List<String> zipFileList = new ArrayList<>();
        lstOfTextFile.clear();
        filesList.clear();
        zipFileList.clear();
        ArrayList<String> fullPathList = new ArrayList<>();
        String strTimeFile = Long.toString(System.currentTimeMillis());
        String zipFile = "FileNen_KHNV01_" + strTimeFile + ".zip", zipPath = "";
        try {

            for (String value : lstCommune) {

                String save_id = "KHNV01";

                HashMap<String, String> paramHashMap = new HashMap<>();
                String sPos_cd = "";
                String stringParaPos_cd = "";
                String sPosFlag = "";
                //xy lay lay cac tham so cho vao hashmap
                Map mapCollectPara = new HashMap();

                //xu ly cho export file ra PDF hoac la Excel
                String strCurrDate = new SimpleDateFormat("ddMMyyyy").format(new Date());
                //duong dan chua file tren o dia + Define.M_REPORT_XLS
                String strPathSave = savedDirPath;
                //Ham nay lay ra ten file bao cao can tao, ten file jasper report
              
                String strFileSave = save_id + "_" + value
                        + "_" + strCurrDate
                        + "_" + strTimeFile.substring(strTimeFile.length() - 4, strTimeFile.length());

                strPathSave += Define.M_REPORT_XLS;
                strFileSave += ".XLSX";
                filePath = strFileSave;
                File Checkpath = new File(strPathSave);
                if (!Checkpath.exists()) {
                    System.out.println("Da tao thu muc: " + strPathSave);
                    Checkpath.mkdirs();
                }
                //Xuat file du lieu o day

                XDKHDao2021 daoQuery = new XDKHDao2021();

//                        setQuery(daoQuery.getQuery(save_id, new DaoConnect().getConnect()));
                ImsPlSqlQuery plsql = new ImsPlSqlQuery();

                Date sdf = new Date();
                try {
                    sdf = new SimpleDateFormat("dd-MMM-yyyy").parse("31-may-2021");
                } catch (ParseException ex) {
                    Logger.getLogger(P0001.class.getName()).log(Level.SEVERE, null, ex);
                }
                paramHashMap.put("PD_REPORT_DATE", new SimpleDateFormat("dd-MMM-yyyy").format(sdf));

                mapCollectPara.put("PD_REPORT_DATE",
                        ImsFillParaMeter.newInstance("VARCHAR2", new SimpleDateFormat("dd-MMM-yyyy").format(sdf)));
                sPos_cd = "000000";
                stringParaPos_cd = "PV_POS_CD";
                sPosFlag = "N";

                daoQuery.getDataExp(save_id, paramHashMap, sPos_cd, stringParaPos_cd, sPosFlag, strPathSave + strFileSave, namBc, dotBc);

                //Kiem tra xem file da tao thanh cong chua
                File filerpt = new File(strPathSave + strFileSave);
                fileName = strPathSave + strFileSave;

                lstOfTextFile.add(fileName);
                FileInfo file = new FileInfo(new File(fileName));
                filesList.add(new DownloadFileInfor(file.getName(), fileName,
                        DefineFun.round_up((double) file.getSize() / 1000) + " KB"));
                fullPathList.add(file.getAbsolutePath());
                zipPath = Define.M_ROOT + Define.M_REPORT_XLS + zipFile;
            }

            if (fullPathList.size() > 1) {
                try {
                    FileZip.ZipFileFromArray(fullPathList, zipPath);
                    zipFileList.add(zipFile);
                    zipFileList.add(zipPath);
                } catch (Exception ex) {
                    Logger.getLogger(ExportText2SbvManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                filePath = zipFile;
                fileName = zipPath;
            }

            System.gc();
            return new FileExportInfo(fileName, filePath);
        } catch (Exception ex) {
            CoreLogger.error(this.getClass().getName() + " get_data_xaydungkh " + ex.getMessage());
            System.err.println(this.getClass().getName() + " loi getDataXayDungKhDetail " + ex.getMessage());
            return null;
        }
    }
}
