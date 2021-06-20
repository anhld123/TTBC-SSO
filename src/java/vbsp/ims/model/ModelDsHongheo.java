/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author LION
 */
public class ModelDsHongheo {

    public String Matinh;
    public String Mahuyen;
    public String Maxa;
    public String Mathon;
    public String Tenkh;
    public String Gioitinh;
    public String Ngaysinh;
    public String Socmt;
    public String Ngaycap;
    public String Noicap;
    public String Dantoc;
    public String Loai_Kh;
    public String Ngayloai;
    public String Makh;
    
    public String toString()
    {
        String str="{Matinh="+Matinh+", Mahuyen="+Mahuyen+", Maxa="+Maxa+", Mathon="+Mathon+", Tenkh="+Tenkh+", "
                + "Gioitinh="+Gioitinh+", Ngaysinh="+Ngaysinh+", Socmt="+Socmt+
                ", Ngaycap="+Ngaycap+", Noicap="+Noicap+", Dantoc="+Dantoc+", Loai_Kh="+Loai_Kh+", Ngayloai="+Ngayloai+"]";
        return str;
    }
    public String getMatinh() {
        return Matinh;
    }

    public void setMatinh(String Matinh) {
        this.Matinh = Matinh;
    }

    public String getMahuyen() {
        return Mahuyen;
    }

    public void setMahuyen(String Mahuyen) {
        this.Mahuyen = Mahuyen;
    }

    public String getMaxa() {
        return Maxa;
    }

    public void setMaxa(String Maxa) {
        this.Maxa = Maxa;
    }

    public String getMathon() {
        return Mathon;
    }

    public void setMathon(String Mathon) {
        this.Mathon = Mathon;
    }

    public String getTenkh() {
        return Tenkh;
    }

    public void setTenkh(String Tenkh) {
        this.Tenkh = Tenkh;
    }

    public String getGioitinh() {
        return Gioitinh;
    }

    public void setGioitinh(String Gioitinh) {
        this.Gioitinh = Gioitinh;
    }

    public String getNgaysinh() {
        return Ngaysinh;
    }

    public void setNgaysinh(String Ngaysinh) {
        this.Ngaysinh = Ngaysinh;
    }

    public String getSocmt() {
        return Socmt;
    }

    public void setSocmt(String Socmt) {
        this.Socmt = Socmt;
    }

    public String getNgaycap() {
        return Ngaycap;
    }

    public void setNgaycap(String Ngaycap) {
        this.Ngaycap = Ngaycap;
    }

    public String getNoicap() {
        return Noicap;
    }

    public void setNoicap(String Noicap) {
        this.Noicap = Noicap;
    }

    public String getDantoc() {
        return Dantoc;
    }

    public void setDantoc(String Dantoc) {
        this.Dantoc = Dantoc;
    }

    public String getLoai_Kh() {
        return Loai_Kh;
    }

    public void setLoai_Kh(String Loai_Kh) {
        this.Loai_Kh = Loai_Kh;
    }

    public String getNgayloai() {
        return Ngayloai;
    }

    public void setNgayloai(String Ngayloai) {
        this.Ngayloai = Ngayloai;
    }

    public String getMakh() {
        return Matinh.trim()+Mahuyen.trim()+Maxa.trim()+Mathon.trim();
    }

    public void setMakh(String Makh) {
        this.Makh = Makh;
    }
    
    public static void main(String[] args)
    {
        try {
            System.err.println(new java.sql.Date(new SimpleDateFormat("dd/MM/yyyy").parse("20/11/2015").getTime()));
        } catch (ParseException ex) {
            Logger.getLogger(ModelDsHongheo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
