/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.action.khnv;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import vbsp.ims.dao.khnv.daoChitieuXa;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.model.khnv.ChitieuKHoachXa;
import vbsp.ims.model.khnv.GiaokhModel;
import vbsp.ims.model.khnv.Phanquyen;
import vbsp.ims.report.fast.ListValue;

/**
 *
 * @author BAOANH
 */
public class ChitieuTindungXaAction extends actionMainKHNV {

    //<editor-fold defaultstate="collapsed" desc="KHai bao bien">
    private TreeNode nodes_pos = new TreeNode();
    //Cho message thong bao loi
    private String message;
    //Lay pos tren treeview lstSoku
    private List<String> ma_donvi = new ArrayList<String>();
    private List<ListValue> lstDmChitieu = new ArrayList<>();
    private List<ListValue> lstQuyetdinh = new ArrayList<>();
    private List<ListValue> lstDmChitieu_truoc = new ArrayList<>();
    private String ma_chitieu_cha;
    private String loai_nv;
    private String ma_chitieu;
    private List<ListValue> lstLoainv = new ArrayList<>();
    private String kytu_hienthi, ten_chitieu, loai_ct, ma_ct_truoc;
    private List<ChitieuKHoachXa> lstChitieu = new ArrayList<>();

    private Map<String, String> chitieuconmap = new LinkedHashMap<String, String>();
    List<ModelTreeNode> lstDSDonvi = new ArrayList<>();
    List<String> ma_ct = new ArrayList<>();
    private String donvi;
    private String ten_donvi;
    private List<Phanquyen> lstPhanquyen = new ArrayList<>();
    private String ma_quyetdinh;

    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Khoi tao treeview">
    private boolean setTreeNodeGrade3(List<ModelTreeNode> lstModelTree) {

        try {
            TreeNode nodePar = new TreeNode();
            List<TreeNode> lstTree = new ArrayList<TreeNode>();
            for (int i = 0; i < lstModelTree.size(); i++) {
                //String strPos_key = ArrlstPoscd.get(i);
                ModelTreeNode modelTree = lstModelTree.get(i);

                //Neu la row dau tien thi la node root
                if (i == 0) {
//                    System.err.println("getStrParentCd=" + modelTree.getStrParentCd() + " getStrParentDesc=" + modelTree.getStrParentDesc());
                    nodes_pos.setId("999999");
                    nodes_pos.setTitle(modelTree.getStrParentDesc());
                    nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                    nodes_pos.setChildren(new LinkedList<TreeNode>());
                } else if (modelTree.getStrChildCd().equals("999999")) {
                    if (i != 1) {
//                        nodes_pos.getChildren().add(nodePar);
                        lstTree.add(nodePar);
                        nodePar = null;
                        nodePar = new TreeNode();
                    }
//                    nodePar= new TreeNode();
//                        System.err.println("  - nodePar getStrChildCd=" + modelTree.getStrChildCd() + " getStrChildDesc=" + modelTree.getStrChildDesc());
                    nodePar.setId("999999");
                    nodePar.setTitle(modelTree.getStrChildDesc());
                    nodePar.setState(TreeNode.NODE_STATE_CLOSED);
                    nodePar.setChildren(new LinkedList<TreeNode>());
                } else {
                    //Khoi tao cho node child
//                        System.err.println("      - nodeChild getStrChildCd=" + modelTree.getStrChildCd() + " getStrChildDesc=" + modelTree.getStrChildDesc());
                    TreeNode nodeChild = new TreeNode();
                    nodeChild.setId(modelTree.getStrChildCd());
                    nodeChild.setTitle(modelTree.getStrChildDesc());
                    nodePar.getChildren().add(nodeChild);
                }

            }
            lstTree.add(nodePar);
            for (TreeNode node : lstTree) {
                nodes_pos.getChildren().add(node);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade3 -> " + e.getMessage());
            return false;
        }
        return true;
    }

    private boolean setTreeNodeGrade12(List<ModelTreeNode> lstModelTree) {

        try {
            for (int i = 0; i < lstModelTree.size(); i++) {
                //String strPos_key = ArrlstPoscd.get(i);
                ModelTreeNode modelTree = lstModelTree.get(i);
                //Neu la row dau tien thi la node root
                if (i == 0) {
                    nodes_pos.setId("999999");
                    nodes_pos.setTitle(modelTree.getStrParentDesc());
                    nodes_pos.setState(TreeNode.NODE_STATE_OPEN);
                    nodes_pos.setChildren(new LinkedList<TreeNode>());
                }
                //Khoi tao cho node child
                TreeNode nodeChild = new TreeNode();
                nodeChild.setId(modelTree.getStrChildCd());
                nodeChild.setTitle(modelTree.getStrChildDesc());
//                System.err.println(ArrlstPosDesc.get(i));
                nodes_pos.getChildren().add(nodeChild);

            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade12 -> " + e.getMessage());
            return false;
        }
        return true;
    }
//</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Xử lý action"> 
//Action nay dùng để reload lại danh mục chỉ tiêu khi chọn chỉ tiêu cha thay đổi

    public String load_Chitieu_chacon() {
        try {

            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được mã nghiệp vụ");
                setMessage("Không thể lấy ra được mã nghiệp vụ");
                return ERROR;
            }

            if (ma_chitieu_cha == null || ma_chitieu_cha.isEmpty()) {
                addActionError("Không thể lấy ra được mã chỉ tiêu cha");
                setMessage("Không thể lấy ra được mã chỉ tiêu cha");
                return ERROR;
            }
            setLstDmChitieu(daoChitieuXa.newInstance().getchitieucha("Y", loai_nv));

            setLstDmChitieu_truoc(daoChitieuXa.newInstance().getchitieucha(ma_chitieu_cha == null ? "" : ma_chitieu_cha, loai_nv));

            for (ListValue value : lstDmChitieu_truoc) {
                chitieuconmap.put(value.getsKey(), value.getsDesc());
            }
            setMessage("Đã khởi tạo thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " load_Chitieu_chacon -> " + e.getMessage());
        }
        return SUCCESS;
    }

//    public String load_nv_ct() {
//        try {
//            lstLoainv.add(new ListValue("XDKH_XA", "Xây dựng kế hoạch"));
//            lstLoainv.add(new ListValue("GIAO_DC_PGD", "Giao, điều chỉnh kế hoạch"));
//            if (loai_nv == null || loai_nv.isEmpty()) {
//                loai_nv = "XDKH_XA";
//            }
//            setLstDmChitieu(daoChitieuXa.newInstance().getchitieucha("Y", loai_nv));
//        } catch (Exception e) {
//            System.err.println(e.getMessage());
//            CoreLogger.error(this.getClass().getCanonicalName() + " load_nv_ct -> " + e.getMessage());
//        }
//        return SUCCESS;
//    }
    //lấy ra danh mục xã và load lên treeview
    public String getdanhmucxa() {
        try {
            System.err.println("thu nhe");
            getInfo();

            List<ModelTreeNode> lstModelTree = daoChitieuXa.newInstance().getDataPosTreeNode(reportGrade, userId);

            if (reportGrade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " getdanhmucxa -> " + e.getMessage());
        }
        return SUCCESS;
    }

    //action nay de khoi tao form thêm chỉ tiêu
    public String addChitieuxa() {
        try {
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được mã nghiệp vụ");
                return ERROR;
            }
            //load quyet dinh
            setLstQuyetdinh(daoChitieuXa.newInstance().getquyetdinh());
            //danh mục chỉ tiêu cha
            setLstDmChitieu(daoChitieuXa.newInstance().getchitieucha("Y", loai_nv));
            //danh mục chỉ tiêu sẽ lấy ra trước
            setLstDmChitieu_truoc(daoChitieuXa.newInstance().getchitieucha(ma_chitieu_cha == null ? "" : ma_chitieu_cha, loai_nv));
            
//            setLstDmChitieu(daoChitieuXa.newInstance().getchitieucha("N", loai_nv));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " addChitieuxa -> " + e.getMessage());
        }
        return SUCCESS;
    }

    //Action cho load bộ chỉ tiêu lên để có thể sửa xóa
    public String loadEditChitieuxa() {
        try {
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được mã nghiệp vụ");
                return ERROR;
            }
            setLstChitieu(daoChitieuXa.newInstance().getsuaxoachitieu(loai_nv, ""));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadEditChitieuxa -> " + e.getMessage());
        }
        return SUCCESS;
    }

    //Action kiểm tra mã chỉ tiêu đã có hay chưa?
    public String machitieuCheck() {
        try {
            System.err.println("loai_nv=" + loai_nv + " ma_chitieu=" + ma_chitieu);
            if (daoChitieuXa.newInstance().isCheckmachitieu(loai_nv, ma_chitieu) > 0) {
                setMessage("Mã chỉ tiêu này đã có bạn chọn mã chỉ tiêu khác");
            }

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadEditChitieuxa -> " + e.getMessage());
        }
        return SUCCESS;
    }

    //Lưu chỉ tiêu khi thêm mới
    public String saveChitieuAdd() {
        try {
            getInfo();
            if (ma_chitieu_cha == null || ma_chitieu_cha.equals("-1")) {
                addActionError("Bạn phải chọn thứ tự thêm vào trước chỉ tiêu nào đó !!! ");
                return ERROR;
            }
            if (ma_chitieu == null || ma_chitieu.isEmpty()) {
                addActionError("Bạn phải điền mã chỉ tiêu ");
                return ERROR;
            }
            if (ten_chitieu == null || ten_chitieu.isEmpty()) {
                addActionError("Bạn phải điền tên chỉ tiêu ");
                return ERROR;
            }
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được loại nghiệp vụ ");
                return ERROR;
            }
            if (loai_ct == null || loai_ct.isEmpty()) {
                addActionError("Không thể lấy ra được loại chỉ tiêu là chỉ tiêu CHA hay CON ");
                return ERROR;
            }
            if (ma_quyetdinh == null || ma_quyetdinh.isEmpty()) {
                addActionError("Bạn phải chọn mã quyết định ");
                return ERROR;
            }
            daoChitieuXa.newInstance().saveChitieuAdd(loai_nv, ma_chitieu_cha, ma_ct_truoc,
                    ma_chitieu, kytu_hienthi, ten_chitieu, loai_ct, userId, ma_quyetdinh);
            addActionMessage("Bạn đã lưu chỉ tiêu thành công");
            setMessage("Bạn đã lưu chỉ tiêu thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveChitieuAdd -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;

    }

    //xóa chỉ tiêu
    public String Xoachitieu() {
        try {
            if (ma_chitieu == null || ma_chitieu.isEmpty()) {
                addActionError("Không thể lấy ra được mã chỉ tiêu ");
                return ERROR;
            }
            message = daoChitieuXa.newInstance().deleteChitieu(loai_nv, ma_chitieu);
            if (message.equals("SUCCESS")) {
                addActionMessage("Bạn đã xóa chỉ tiêu thành công");
                setMessage("DELETE_SUCCESS");
            } else {
                addActionError(message);
                setMessage(message);
                return ERROR;
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " Xoachitieu -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    //Load chỉ tiêu lên để sửa
    public String Suachitieu() {
        try {
            if (ma_chitieu == null || ma_chitieu.isEmpty()) {
                addActionError("Không thể lấy ra được mã chỉ tiêu ");
                return ERROR;
            }
            setLstQuyetdinh(daoChitieuXa.newInstance().getquyetdinh());
            setLstChitieu(daoChitieuXa.newInstance().getsuaxoachitieu(loai_nv, ma_chitieu));

            for (ChitieuKHoachXa chitieu : lstChitieu) {
                kytu_hienthi = chitieu.getKH_STT_HT();
                ten_chitieu = chitieu.getKH_CHI_TIEU();
                ma_quyetdinh = chitieu.getKH_MAQD();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " Suachitieu -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    //Lưu chỉ tiêu khi đã sửa
    public String saveSuaChitieu() {
        try {
            getInfo();

            if (ma_chitieu == null || ma_chitieu.isEmpty()) {
                addActionError("Bạn phải điền mã chỉ tiêu ");
                return ERROR;
            }
            if (ten_chitieu == null || ten_chitieu.isEmpty()) {
                addActionError("Bạn phải điền tên chỉ tiêu ");
                return ERROR;
            }
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được loại nghiệp vụ ");
                return ERROR;
            }
            if (loai_ct == null || loai_ct.isEmpty()) {
                addActionError("Không thể lấy ra được loại chỉ tiêu là chỉ tiêu CHA hay CON ");
                return ERROR;
            }
            daoChitieuXa.newInstance().saveSuaChitieu(loai_nv,
                    ma_chitieu, kytu_hienthi, ten_chitieu, loai_ct, userId, ma_quyetdinh);
            addActionMessage("Bạn đã lưu chỉ tiêu thành công");
            setMessage("Bạn đã lưu chỉ tiêu thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveSuaChitieu -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;

    }

    //Load bộ chỉ tiêu, các đơn vị để phân quyền
    public String phanquyenChitieu() {
        try {
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được loại nghiệp vụ ");
                return ERROR;
            }
            getInfo();

            lstDSDonvi = daoChitieuXa.newInstance().getDataPosTreeNode(reportGrade, userId);

//            if (reportGrade.equals("3")) {
//                setTreeNodeGrade3(lstModelTree);
//            } else {
//                setTreeNodeGrade12(lstModelTree);
//            }
            setLstChitieu(daoChitieuXa.newInstance().getsuaxoachitieu(loai_nv, ""));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " phanquyenChitieu -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    //lưu phân quyền danh sách chỉ tiêu và danh sách đơn vị
    public String savePQChitieu() {
        try {
//            if (ma_ct == null || ma_ct.isEmpty()) {
//                addActionError("Bạn phải chọn chỉ tiêu cần phân quyền");
//                return ERROR;
//            }
//            if (ma_donvi == null || ma_donvi.isEmpty()) {
//                addActionError("Bạn phải chọn đơn vị cần phân quyền");
//                return ERROR;
//            }
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được loại nghiệp vụ ");
                return ERROR;
            }
            getInfo();

            daoChitieuXa.newInstance().saveChitieu_Donvi(loai_nv, ma_ct, ma_donvi, userId);
            addActionMessage("Bạn đã lưu phân quyền thành công");
            setMessage("Bạn đã lưu phân quyền thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " savePQChitieu -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    //load phân quyền cho chỉ tiêu và danh sách các xã
    public String PhanquyenCT_xa() {
        try {
            if (ma_chitieu == null || ma_chitieu.isEmpty()) {
                addActionError("Không lấy ra được mã chỉ tiêu");
                return ERROR;
            }
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được loại nghiệp vụ ");
                return ERROR;
            }
            getInfo();
//            lstDSDonvi = daoChitieuXa.newInstance().getDataPosTreeNode(reportGrade, userId);
            setLstPhanquyen(daoChitieuXa.newInstance().getDataChitieuDanhsachDonvi(loai_nv, ma_chitieu, reportGrade, userId));
            setTen_chitieu(daoChitieuXa.newInstance().gettenChitieuDonvi(loai_nv, ma_chitieu, reportGrade, userId, "N"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " PhanquyenCT_xa -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    //load phân quyền cho xã và danh sách các chỉ tiêu
    public String PhanquyenXa_CT() {
        try {
            if (donvi == null || donvi.isEmpty()) {
                addActionError("Không lấy ra được mã đơn vị");
                return ERROR;
            }
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được loại nghiệp vụ ");
                return ERROR;
            }
            getInfo();

//            setLstChitieu(daoChitieuXa.newInstance().getsuaxoachitieu(loai_nv, ""));
            setLstPhanquyen(daoChitieuXa.newInstance().getDataDonviDanhsachChitieu(loai_nv, donvi, reportGrade, userId));
            setTen_donvi(daoChitieuXa.newInstance().gettenChitieuDonvi(loai_nv, donvi, reportGrade, userId, "Y"));
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " PhanquyenXa_CT -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    //<editor-fold defaultstate="collapsed" desc="Lưu danh sách chỉ tiêu đơn vị, và dánh sách đơn vị chỉ tiêu">
    public String saveChitieuDanhsachXa() {
        try {
            if (ma_chitieu == null || ma_chitieu.isEmpty()) {
                addActionError("Không lấy ra được mã chỉ tiêu");
                return ERROR;
            }
//            if (ma_donvi == null || ma_donvi.isEmpty()) {
//                addActionError("Bạn phải chọn đơn vị cần phân quyền");
//                return ERROR;
//            }
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được loại nghiệp vụ ");
                return ERROR;
            }
            getInfo();
            daoChitieuXa.newInstance().saveChitieu_DSDonvi(loai_nv, ma_chitieu, ma_donvi, userId);
            addActionMessage("Bạn đã lưu phân quyền cho các đơn vị thành công");
            setMessage("Bạn đã lưu phân quyền cho các đơn vị thành công");
        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveChitieuDanhsachXa -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

    public String saveXaDanhsachChitieu() {
        try {
            if (donvi == null || donvi.isEmpty()) {
                addActionError("Không lấy ra được mã đơn vị");
                return ERROR;
            }
//            if (ma_ct == null || ma_ct.isEmpty()) {
//                addActionError("Bạn phải chọn danh sách chỉ tiêu cần phân quyền");
//                return ERROR;
//            }
            if (loai_nv == null || loai_nv.isEmpty()) {
                addActionError("Không thể lấy ra được loại nghiệp vụ ");
                return ERROR;
            }
            getInfo();

            daoChitieuXa.newInstance().saveDonvi_DSChitieu(loai_nv, ma_ct, donvi, userId);

            addActionMessage("Bạn đã lưu phân quyền cho chỉ tiêu thành công");

        } catch (Exception e) {
            System.err.println(e.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " saveXaDanhsachChitieu -> " + e.getMessage());
            addActionError(e.getMessage());
            return ERROR;
        }
        return SUCCESS;
    }

//</editor-fold>
    //</editor-fold>
    //<editor-fold defaultstate="collapsed" desc="Get set cac bien">
    public List<ListValue> getLstQuyetdinh() {
        return lstQuyetdinh;
    }

    public void setLstQuyetdinh(List<ListValue> lstQuyetdinh) {
        this.lstQuyetdinh = lstQuyetdinh;
    }

    public List<Phanquyen> getLstPhanquyen() {
        return lstPhanquyen;
    }

    public void setLstPhanquyen(List<Phanquyen> lstPhanquyen) {
        this.lstPhanquyen = lstPhanquyen;
    }

    public String getTen_donvi() {
        return ten_donvi;
    }

    public void setTen_donvi(String ten_donvi) {
        this.ten_donvi = ten_donvi;
    }

    public List<String> getMa_ct() {
        return ma_ct;
    }

    public void setMa_ct(List<String> ma_ct) {
        this.ma_ct = ma_ct;
    }

    public String getDonvi() {
        return donvi;
    }

    public void setDonvi(String donvi) {
        this.donvi = donvi;
    }

    public List<ModelTreeNode> getLstDSDonvi() {
        return lstDSDonvi;
    }

    public void setLstDSDonvi(List<ModelTreeNode> lstDSDonvi) {
        this.lstDSDonvi = lstDSDonvi;
    }

    public Map<String, String> getChitieuconmap() {
        return chitieuconmap;
    }

    public void setChitieuconmap(Map<String, String> chitieuconmap) {
        this.chitieuconmap = chitieuconmap;
    }

    public List<ListValue> getLstDmChitieu_truoc() {
        return lstDmChitieu_truoc;
    }

    public void setLstDmChitieu_truoc(List<ListValue> lstDmChitieu_truoc) {
        this.lstDmChitieu_truoc = lstDmChitieu_truoc;
    }

    public String getMa_ct_truoc() {
        return ma_ct_truoc;
    }

    public void setMa_ct_truoc(String ma_ct_truoc) {
        this.ma_ct_truoc = ma_ct_truoc;
    }

    public List<ChitieuKHoachXa> getLstChitieu() {
        return lstChitieu;
    }

    public void setLstChitieu(List<ChitieuKHoachXa> lstChitieu) {
        this.lstChitieu = lstChitieu;
    }

    public String getLoai_ct() {
        return loai_ct;
    }

    public void setLoai_ct(String loai_ct) {
        this.loai_ct = loai_ct;
    }

    public String getTen_chitieu() {
        return ten_chitieu;
    }

    public void setTen_chitieu(String ten_chitieu) {
        this.ten_chitieu = ten_chitieu;
    }

    public String getKytu_hienthi() {
        return kytu_hienthi;
    }

    public void setKytu_hienthi(String kytu_hienthi) {
        this.kytu_hienthi = kytu_hienthi;
    }

    public String getMa_chitieu() {
        return ma_chitieu;
    }

    public void setMa_chitieu(String ma_chitieu) {
        this.ma_chitieu = ma_chitieu;
    }

    public String getMa_chitieu_cha() {
        return ma_chitieu_cha;
    }

    public String getLoai_nv() {
        return loai_nv;
    }

    public void setLoai_nv(String loai_nv) {
        this.loai_nv = loai_nv;
    }

    public List<ListValue> getLstLoainv() {
        return lstLoainv;
    }

    public void setLstLoainv(List<ListValue> lstLoainv) {
        this.lstLoainv = lstLoainv;
    }

    public void setMa_chitieu_cha(String ma_chitieu_cha) {
        this.ma_chitieu_cha = ma_chitieu_cha;
    }

    public List<ListValue> getLstDmChitieu() {
        return lstDmChitieu;
    }

    public void setLstDmChitieu(List<ListValue> lstDmChitieu) {
        this.lstDmChitieu = lstDmChitieu;
    }

    public TreeNode getNodes_pos() {
        return nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getMa_donvi() {
        return ma_donvi;
    }

    public void setMa_donvi(List<String> ma_donvi) {
        this.ma_donvi = ma_donvi;
    }

    public String getMa_quyetdinh() {
        return ma_quyetdinh;
    }

    public void setMa_quyetdinh(String ma_quyetdinh) {
        this.ma_quyetdinh = ma_quyetdinh;
    }

//</editor-fold>
    public static void main(String[] args) {
        ChitieuTindungXaAction c = new ChitieuTindungXaAction();
        c.getdanhmucxa();
    }
}
