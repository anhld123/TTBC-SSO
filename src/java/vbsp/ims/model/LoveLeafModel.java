/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.math.BigDecimal;

/**
 *
 * @author BAOANH
 */
public class LoveLeafModel {

    //<editor-fold defaultstate="collapsed" desc="Khai bao bien cho lop">
    public int nStt;
    public String sPosCd;
    public String sRefNo;
    public String sPoorID;
    public String sPoorName;
    public String sAmount;
    public String sTranDT;
    public String sProvince;
    public String sDistrict;
    public String sCommnue;
    public String sStatus;
    public String sComment;
    public String sPoor_Hidden;

    public String getsPoor_Hidden() {
        return sPoor_Hidden;
    }

    public void setsPoor_Hidden(String sPoor_Hidden) {
        this.sPoor_Hidden = sPoor_Hidden;
    }


    
    
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khoi tao phuong thuc get set">  
    
    public int getnStt() {
        return nStt;
    }

    public void setnStt(int nStt) {
        this.nStt = nStt;
    }

    public String getsPosCd() {
        return sPosCd;
    }

    public void setsPosCd(String sPosCd) {
        this.sPosCd = sPosCd;
    }

    public String getsRefNo() {
        return sRefNo;
    }

    public void setsRefNo(String sRefNo) {
        this.sRefNo = sRefNo;
    }

    public String getsPoorID() {
        return sPoorID;
    }

    public void setsPoorID(String sPoorID) {
        this.sPoorID = sPoorID;
    }

    public String getsPoorName() {
        return sPoorName;
    }

    public void setsPoorName(String sPoorName) {
        this.sPoorName = sPoorName;
    }

    public String getsAmount() {
        return sAmount;
    }

    public void setsAmount(String sAmount) {
        this.sAmount = sAmount;
    }

    public String getsTranDT() {
        return sTranDT;
    }

    public void setsTranDT(String sTranDT) {
        this.sTranDT = sTranDT;
    }

    public String getsProvince() {
        return sProvince;
    }

    public void setsProvince(String sProvince) {
        this.sProvince = sProvince;
    }

    public String getsDistrict() {
        return sDistrict;
    }

    public void setsDistrict(String sDistrict) {
        this.sDistrict = sDistrict;
    }

    public String getsCommnue() {
        return sCommnue;
    }

    public void setsCommnue(String sCommnue) {
        this.sCommnue = sCommnue;
    }

    public String getsStatus() {
        return sStatus;
    }

    public void setsStatus(String sStatus) {
        this.sStatus = sStatus;
    }
    
    public String getsComment() {
        return sComment;
    }

    public void setsComment(String sComment) {
        this.sComment = sComment;
    }
    //</editor-fold>

    public static class SaveLoveLeaf {

        public String sPosCd;        
        public String sPoorID;
        public String sTranDT;
        public String sPoor_Hidden;
        public String sComment;
        public String sStatus;

        public String getsStatus() {
            return sStatus;
        }

        public void setsStatus(String sStatus) {
            this.sStatus = sStatus;
        }

        public String getsComment() {
            return sComment;
        }

        public void setsComment(String sComment) {
            this.sComment = sComment;
        }

        public String getsPoor_Hidden() {
            return sPoor_Hidden;
        }

        public void setsPoor_Hidden(String sPoor_Hidden) {
            this.sPoor_Hidden = sPoor_Hidden;
        }
        
        public String getsPosCd() {
            return sPosCd;
        }

        public void setsPosCd(String sPosCd) {
            this.sPosCd = sPosCd;
        }

        public String getsPoorID() {
            return sPoorID;
        }

        public void setsPoorID(String sPoorID) {
            this.sPoorID = sPoorID;
        }

        public String getsTranDT() {
            return sTranDT;
        }

        public void setsTranDT(String sTranDT) {
            this.sTranDT = sTranDT;
        }

    }
    //<editor-fold defaultstate="collapsed" desc="Cho lop view tong so kh, tongtien,no trong han, qua han..">
    public static class ViewTotalCust {

        public String sPoscd;
        public String sPosDesc;
        public String sSoKh;
        public String sTongtien;
        public String sNothan;
        public String sNoqhan;
        public String sNokhoanh;
        public String sNolai;
        public String sSoduCasa;
        public String sNoNCK;
        public String sNoKCKN;

        public String getsNoNCK() {
            return sNoNCK;
        }

        public void setsNoNCK(String sNoNCK) {
            this.sNoNCK = sNoNCK;
        }

        public String getsNoKCKN() {
            return sNoKCKN;
        }

        public void setsNoKCKN(String sNoKCKN) {
            this.sNoKCKN = sNoKCKN;
        }
        
        public String getsPoscd() {
            return sPoscd;
        }

        public void setsPoscd(String sPoscd) {
            this.sPoscd = sPoscd;
        }

        public String getsPosDesc() {
            return sPosDesc;
        }

        public void setsPosDesc(String sPosDesc) {
            this.sPosDesc = sPosDesc;
        }

        public String getsSoKh() {
            return sSoKh;
        }

        public void setsSoKh(String sSoKh) {
            this.sSoKh = sSoKh;
        }

        public String getsTongtien() {
            return sTongtien;
        }

        public void setsTongtien(String sTongtien) {
            this.sTongtien = sTongtien;
        }

        public String getsNothan() {
            return sNothan;
        }

        public void setsNothan(String sNothan) {
            this.sNothan = sNothan;
        }

        public String getsNoqhan() {
            return sNoqhan;
        }

        public void setsNoqhan(String sNoqhan) {
            this.sNoqhan = sNoqhan;
        }

        public String getsNokhoanh() {
            return sNokhoanh;
        }

        public void setsNokhoanh(String sNokhoanh) {
            this.sNokhoanh = sNokhoanh;
        }

        public String getsNolai() {
            return sNolai;
        }

        public void setsNolai(String sNolai) {
            this.sNolai = sNolai;
        }

        public String getsSoduCasa() {
            return sSoduCasa;
        }

        public void setsSoduCasa(String sSoduCasa) {
            this.sSoduCasa = sSoduCasa;
        }

    }

//</editor-fold>
}
