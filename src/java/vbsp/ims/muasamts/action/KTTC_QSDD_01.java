/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.muasamts.action;

import vbsp.ims.nhaptaycn.action.*;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.khnv2021.PosClass;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.restapi.ListOfValue;
import java.nio.charset.StandardCharsets;

/**
 *
 * @author Trung
 */
public class KTTC_QSDD_01 extends ActionMuasamtsMain
        implements NhaptaycnFunction {

    private List<DuLieuNTRow> lstData;
    private List<PosClass> lstCN;
    private List<PosClass> lstPGD;
    private List<ListOfValue> lstAssetGroup;
    private String txtNgayBc;
    private String txtMapgd;
    private String userId;    
    
    private String posFlag;
    private InputStream pageResult;

    public List<DuLieuNTRow> getLstData() {
        return lstData;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    

    public void setLstData(List<DuLieuNTRow> lstData) {
        this.lstData = lstData;
    }

    public List<PosClass> getLstCN() {
        return lstCN;
    }

    public void setLstCN(List<PosClass> lstCN) {
        this.lstCN = lstCN;
    }

    public List<PosClass> getLstPGD() {
        return lstPGD;
    }

    public void setLstPGD(List<PosClass> lstPGD) {
        this.lstPGD = lstPGD;
    }

    public String getTxtNgayBc() {
        return txtNgayBc;
    }

    public void setTxtNgayBc(String txtNgayBc) {
        this.txtNgayBc = txtNgayBc;
    }

    public String getTxtMapgd() {
        return txtMapgd;
    }

    public void setTxtMapgd(String txtMapgd) {
        this.txtMapgd = txtMapgd;
    }

    public List<ListOfValue> getLstAssetGroup() {
        return lstAssetGroup;
    }

    public void setLstAssetGroup(List<ListOfValue> lstAssetGroup) {
        this.lstAssetGroup = lstAssetGroup;
    }

    public InputStream getPageResult() {
        return pageResult;
    }

    public void setPageResult(InputStream pageResult) {
        this.pageResult = pageResult;
    }

    public String getPosFlag() {
        return posFlag;
    }

    public void setPosFlag(String posFlag) {
        this.posFlag = posFlag;
    }
                            
    MuaSam_SudungDat_Service _mstsService;
    
    @Override
    public String load() {
        try {
            System.err.println("KTTC_QSDD_01");
            if (!getParaSession()) {
                return ERROR;
            }
            //HashMap hmParameter = getParameter();    
            final String sReportDate = new SimpleDateFormat("dd-MMM-yyyy").format(new SimpleDateFormat("dd/MM/yyyy").parse(this.txtNgayBc));
            _mstsService = new MuaSam_SudungDat_Service();
            posFlag = "M";
            lstData = _mstsService.getAssetsPlanList(txtMapgd, "M", sReportDate); 
            if (lstData == null || lstData.isEmpty()) {
                lstData = new ArrayList<>();
                DuLieuNTRow _item = new DuLieuNTRow();
                _item.setPosCode(txtMapgd);
                _item.setPosFlag("M");
                _item.setKey("KTTC_QSDD_01");
                _item.setCode(txtMapgd +"_" +0);
                lstData.add(_item);
            }
            if (Grade.equals("3")) {
                lstPGD = _mstsService.getDonvi("2", UserName, txtMapgd , 1);
            } else {
                lstPGD = _mstsService.getDonvi("2", UserName, "" , 0);
            }            
            userId = UserName;
            lstAssetGroup = _mstsService.getAssetGroupList();
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> KTTC_QSDD_01: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> KTTC_QSDD_01: " + e.getMessage());
        }
        return SUCCESS;
    }

    @Override
    public String save() {
        System.err.println("Save - KTTC_QSDD_01");
        try {
            if (!getParaSession()) {
                return ERROR;
            }     
            _mstsService = new MuaSam_SudungDat_Service();
            int _status = _mstsService.saveData(txtMapgd, "M", txtNgayBc, UserName, UserName, lstData, "1");
            if (_status == 200) {
                // save local data
                _mstsService.saveData(txtMapgd, "M", txtNgayBc, UserName, UserName, lstData, "0");
            }
            String code = String.valueOf(_status);
            pageResult  = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> save: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> save: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }        
        return SUCCESS;
    }   
    
    
    public String delete() {        
        try {
            if (!getParaSession()) {
                return ERROR;
            }   
            
            List<DuLieuNTRow> lstSelectedData = new ArrayList<>();            
            // Lay ra danh sach ma khach hang duoc chon
            List<String> lstSelectedId = new ArrayList<>();
            for (int i = 0; i < this.lstData.size(); i++) {
                if (this.lstData.get(i).getManualFlag() != null && this.lstData.get(i).getManualFlag().equals("1")) {
                    if (!lstSelectedId.contains(this.lstData.get(i).getCode())) {
                        lstSelectedId.add(this.lstData.get(i).getCode());
                    }
                }
            }

            for (int i = 0; i < this.lstData.size(); i++) {
                if (lstSelectedId.contains(this.lstData.get(i).getCode())) {
                    lstSelectedData.add(this.lstData.get(i));
                }
            }

            String code = "";
            _mstsService = new MuaSam_SudungDat_Service();
            int _status = _mstsService.deleteData(txtMapgd, "M", this.txtNgayBc, UserName, UserName, lstSelectedData, "1");            
            
            if (_status == 200) {
                // save local data
                _mstsService.deleteData(txtMapgd, "M", txtNgayBc, UserName, UserName, lstSelectedData, "0");
            }
            
            code = String.valueOf(_status);
            this.pageResult = new ByteArrayInputStream(code.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            System.err.println("Loi trong ham delete " + e.getMessage());
            CoreLogger.error(this.getClass().getName() + " delete -> " + e.getMessage());
        }
        
        return SUCCESS;
    }
}
