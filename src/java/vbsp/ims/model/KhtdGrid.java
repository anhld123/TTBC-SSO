package vbsp.ims.model;

public class KhtdGrid {    
    private Integer id;
    private String maCT;
    private String chiTieu;
    private String keHoachTon;
    private String xayDungKH;
    private String giaoKH;
    private String dieuChinhKH;
    private String chinhSua;
    private String stt;
    private String ctTong;
    private String ctTongCongThuc;
    private String ctTongCongThucChiTiet;
    private Integer capHienThi;
    
    public KhtdGrid(){
    
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public Integer getId() {
        return id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    
    public String getMaCT() {
        return maCT;
    }
    
    public void setMaCT(String maCT) {
        this.maCT = maCT;
    }
    
    public String getChiTieu() {
        return chiTieu;
    }
    
    public void setChiTieu(String chiTieu) {
        this.chiTieu = chiTieu;
    }
    
    public String getXayDungKH() {
        return xayDungKH;
    }
    
    public void setXayDungKH(String xayDungKH) {
        this.xayDungKH = xayDungKH;
    }
    
    public String getGiaoKH() {
        return giaoKH;
    }
    
    public void setGiaoKH(String giaoKH) {
        this.giaoKH = giaoKH;
    }
    
    public String getDieuChinhKH() {
        return dieuChinhKH;
    }
    
    public void setDieuChinhKH(String dieuChinhKH) {
        this.dieuChinhKH = dieuChinhKH;
    }
    
    public String getChinhSua() {
        return chinhSua;
    }
    
    public void setChinhSua(String chinhSua) {
        this.chinhSua = chinhSua;
    }
    
    public String getStt() {
        return stt;
    }
    
    public void setStt(String stt) {
        this.stt = stt;
    }
    
    public String getCtTong() {
        return ctTong;
    }
    
    public void setCtTong(String ctTong) {
        this.ctTong = ctTong;
    }
    
    public String getCtTongCongThuc() {
        return ctTongCongThuc;
    }
    
    public void setCtTongCongThuc(String ctTongCongThuc) {
        this.ctTongCongThuc = ctTongCongThuc;
    }
    
    public Integer getCapHienThi() {
        return capHienThi;
    }
    
    public void setCapHienThi(Integer capHienThi) {
        this.capHienThi = capHienThi;
    }
    
        public String getKeHoachTon() {
        return keHoachTon;
    }

    public void setKeHoachTon(String keHoachTon) {
        this.keHoachTon = keHoachTon;
    }    
    
    public String getCtTongCongThucChiTiet() {
        return ctTongCongThucChiTiet;
    }

    public void setCtTongCongThucChiTiet(String ctTongCongThucChiTiet) {
        this.ctTongCongThucChiTiet = ctTongCongThucChiTiet;
    }
//</editor-fold>

}
