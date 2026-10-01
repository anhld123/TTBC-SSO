/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.sso;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class BranchCodeByPosCd {

    private String ma;
    private String ten;
    private String macn;
    private String vungKT;
    private String trangThai;
    private String mapgd;

    public BranchCodeByPosCd() {
    }

    public String getMa() {
        return ma;
    }

    public void setMa(String ma) {
        this.ma = ma;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMacn() {
        return macn;
    }

    public void setMacn(String macn) {
        this.macn = macn;
    }

    public String getVungKT() {
        return vungKT;
    }

    public void setVungKT(String vungKT) {
        this.vungKT = vungKT;
    }

    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getMapgd() {
        return mapgd;
    }

    public void setMapgd(String mapgd) {
        this.mapgd = mapgd;
    }

    public static class BranchCodeByPosCdResp {

        public boolean isSuccess;
        public int code;
        public String message;
        public ArrayList<BranchCodeByPosCd> result;

        public BranchCodeByPosCdResp() {
        }

        public boolean isIsSuccess() {
            return isSuccess;
        }

        public void setIsSuccess(boolean isSuccess) {
            this.isSuccess = isSuccess;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public ArrayList<BranchCodeByPosCd> getResult() {
            return result;
        }

        public void setResult(ArrayList<BranchCodeByPosCd> result) {
            this.result = result;
        }
    }
}
