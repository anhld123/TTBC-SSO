package vbsp.ims.cictt200;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.bcqt.dao.DaoBcqtMain;
import vbsp.ims.bcqt.model.ModelViewSend;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.ktgs.dao.DaoKtgsMain;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.xml.XmlCICSync;

public class BCTCCICTT200Action extends ActionSupport {

    protected String Grade;
    protected String UserName;
    protected String message;
    private String loai_module;
    private String ma_dn;
    private String nambc;
    private String defaultNambc;
    private List<ListValue> moduleList = new ArrayList();
    private List<ListValue> doanhNghiepList = new ArrayList();
    private List<ListValue> nambcList = new ArrayList();
    protected List<QT_DULIEU_NT> lstDulieuNt = new ArrayList();
    protected List<String> poscd = new ArrayList();
    protected TreeNode nodes_pos = new TreeNode();
    private String totalDataView;
    protected List<ModelViewSend> lstViewSend = new ArrayList();
    private String sKiemtoan;
    private String sBcao;
    private String sThongtu;

    public String getsThongtu() {
        return sThongtu;
    }

    public void setsThongtu(String sThongtu) {
        this.sThongtu = sThongtu;
    }

    public String getsKiemtoan() {
        return sKiemtoan;
    }

    public void setsKiemtoan(String sKiemtoan) {
        this.sKiemtoan = sKiemtoan;
    }

    public String getsBcao() {
        return sBcao;
    }

    public void setsBcao(String sBcao) {
        this.sBcao = sBcao;
    }

    protected boolean getParaSession() {
        Map session = ActionContext.getContext().getSession();
        if (session != null && session.size() != 0 && !session.isEmpty()) {
            this.setUserName(session.get("username").toString());
            if (this.UserName != null && !this.UserName.isEmpty()) {
                this.setGrade(session.get("reportGrade").toString());
                if (this.Grade != null && !this.Grade.isEmpty()) {
                    return true;
                } else {
                    this.setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                    this.addActionError("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
                    return false;
                }
            } else {
                this.setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                this.addActionError("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
                return false;
            }
        } else {
            this.setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            this.addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return false;
        }
    }

    private List<String> convertStringtoList(String[] value) {
        ArrayList lst = new ArrayList();

        try {
            for (int i = 0; i < value.length; ++i) {
                if (!value[i].equals("999999") && !value[i].isEmpty()) {
                    lst.add(value[i]);
                }
            }
        } catch (Exception var4) {
            CoreLogger.error(this.getClass().getName() + " Exception -> convertStringtoList: " + var4.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> convertStringtoList: " + var4.getMessage());
        }

        return lst;
    }

    protected HashMap<String, Object> getParameter() throws Exception {
        HashMap<String, Object> paramHashMap = new HashMap();
        Map<String, String[]> prameters = ServletActionContext.getRequest().getParameterMap();
        Iterator var3 = prameters.keySet().iterator();

        while (true) {
            while (var3.hasNext()) {
                String parameter = (String) var3.next();
                String[] values = (String[]) prameters.get(parameter);
                if (parameter.indexOf("TEXT") <= 0 && parameter.indexOf("DATE") <= 0 && parameter.indexOf("LIST") <= 0) {
                    if (parameter.startsWith("1_")) {
                        parameter = parameter.substring(2, parameter.length());
                    }

                    if (parameter.equals("poscd")) {
                        paramHashMap.put(parameter, this.convertStringtoList(values));
                    } else {
                        paramHashMap.put(parameter, values[0]);
                    }
                } else {
                    if (parameter.startsWith("1_")) {
                        parameter = parameter.substring(2, parameter.length());
                    }

                    if (parameter.indexOf("DATE") > 0) {
                        Date sdf = (new SimpleDateFormat("dd/MM/yyyy")).parse(values[0]);
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), (new SimpleDateFormat("dd-MMM-yyyy")).format(sdf));
                    } else {
                        paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                    }
                }
            }

            return paramHashMap;
        }
    }

    private boolean setTreeNodeGrade3(List<ModelTreeNode> lstModelTree) {
        try {
            TreeNode nodePar = new TreeNode();
            List<TreeNode> lstTree = new ArrayList();

            for (int i = 0; i < lstModelTree.size(); ++i) {
                ModelTreeNode modelTree = (ModelTreeNode) lstModelTree.get(i);
                if (i == 0) {
                    this.nodes_pos.setId("999999");
                    this.nodes_pos.setTitle(modelTree.getStrParentDesc());
                    this.nodes_pos.setState("open");
                    this.nodes_pos.setChildren(new LinkedList());
                } else if (modelTree.getStrChildCd().equals("999999")) {
                    if (i != 1) {
                        lstTree.add(nodePar);
                        nodePar = null;
                        nodePar = new TreeNode();
                    }

                    nodePar.setId("999999");
                    nodePar.setTitle(modelTree.getStrChildDesc());
                    nodePar.setState("closed");
                    nodePar.setChildren(new LinkedList());
                } else {
                    TreeNode nodeChild = new TreeNode();
                    nodeChild.setId(modelTree.getStrChildCd());
                    nodeChild.setTitle(modelTree.getStrChildDesc());
                    nodePar.getChildren().add(nodeChild);
                }
            }

            lstTree.add(nodePar);
            Iterator var8 = lstTree.iterator();

            while (var8.hasNext()) {
                TreeNode node = (TreeNode) var8.next();
                this.nodes_pos.getChildren().add(node);
            }

            return true;
        } catch (Exception var7) {
            System.err.println(var7.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade3 -> " + var7.getMessage());
            return false;
        }
    }

    private boolean setTreeNodeGrade12(List<ModelTreeNode> lstModelTree) {
        try {
            for (int i = 0; i < lstModelTree.size(); ++i) {
                ModelTreeNode modelTree = (ModelTreeNode) lstModelTree.get(i);
                if (i == 0) {
                    this.nodes_pos.setId("999999");
                    this.nodes_pos.setTitle(modelTree.getStrParentDesc());
                    this.nodes_pos.setState("open");
                    this.nodes_pos.setChildren(new LinkedList());
                }

                TreeNode nodeChild = new TreeNode();
                nodeChild.setId(modelTree.getStrChildCd());
                nodeChild.setTitle(modelTree.getStrChildDesc());
                this.nodes_pos.getChildren().add(nodeChild);
            }

            return true;
        } catch (Exception var5) {
            System.err.println(var5.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " setTreeNodeGrade12 -> " + var5.getMessage());
            return false;
        }
    }

    public String execute() {
        return "success";
    }

    public String loadPageMain() {
        try {
            System.err.println("Vao ham loadPageMain");
            return "success";
        } catch (Exception var2) {
            System.err.println(var2.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadPageMain -> " + var2.getMessage());
            this.addActionError("Lỗi bạn không thể load được tham số " + var2.getMessage());
            return "error";
        }
    }

    public String loadModuleCic() {
        try {
            if (!this.getParaSession()) {
                return "error";
            } else {
                daoBCTCCICTT200 dao = new daoBCTCCICTT200();
                this.moduleList = dao.getModuleCic();
                this.doanhNghiepList = dao.getDoanhnghiepCic(this.UserName, this.Grade);
                this.nambcList = dao.getNambcCic();
                Connection conn = (new DaoConnect()).getConnect();
                DaoBcqtMain daoMain = new DaoBcqtMain();
                List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, this.UserName, this.Grade);
                if (this.Grade.equals("3")) {
                    this.setTreeNodeGrade3(lstModelTree);
                } else {
                    this.setTreeNodeGrade12(lstModelTree);
                }

                System.err.println("vao action");
                return "success";
            }
        } catch (Exception var5) {
            System.err.println(var5.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadModuleCic -> " + var5.getMessage());
            this.addActionError("Lỗi bạn không thể load được tham số " + var5.getMessage());
            return "error";
        }
    }

    public String loadDataDoanhnghiep() {
        try {
            HashMap hmParameter = this.getParameter();
            setsKiemtoan(hmParameter.get("txtKiemtoan").toString());
            setsBcao(hmParameter.get("txtBchopnhat").toString());
            setsThongtu(hmParameter.get("txtThongtu").toString());
            if (sKiemtoan == null || sKiemtoan.equals("")) {
                this.addActionError("Bạn chưa chọn kiểm toán!");
                return "error";
            }
            if (sBcao == null || sBcao.equals("")) {
                this.addActionError("Bạn chưa chọn báo cáo tài chính hợp nhất!");
                return "error";
            }
            if (sThongtu == null || sThongtu.equals("")) {
                this.addActionError("Bạn chưa chọn số Thông tư!");
                return "error";
            }
            ActionContext.getContext().getSession().put("sKiemtoan", sKiemtoan);
            ActionContext.getContext().getSession().put("sBcao", sBcao);
            ActionContext.getContext().getSession().put("sThongtu", sThongtu);
            if (!this.getParaSession()) {
                return "error";
            } else if (this.loai_module != null && !this.loai_module.equals("-1")) {
                if (this.nambc != null && !this.nambc.equals("")) {
                    daoBCTCCICTT200 dao = new daoBCTCCICTT200();
                    if (this.Grade.equals("1")) {
                        if (this.ma_dn != null && !this.ma_dn.equals("")) {
                            this.lstDulieuNt = dao.loadDataDoanhnghiep(this.loai_module, Integer.parseInt(this.nambc), this.ma_dn, this.UserName, this.Grade, this.poscd, sKiemtoan, sBcao, sThongtu);
                            return this.loai_module;
                        } else {
                            this.addActionError("Bạn phải chọn mã doanh nghiệp cần load số liệu");
                            return "error";
                        }
                    } else if (this.Grade.equals("2")) {
                        if (this.ma_dn != null && !this.ma_dn.equals("")) {
                            this.totalDataView = dao.loadDataTotalDoanhnghiep(this.loai_module, Integer.parseInt(this.nambc), this.ma_dn, this.UserName, this.Grade, this.poscd, sKiemtoan, sBcao, sThongtu);
                            this.lstDulieuNt = dao.loadDataDoanhnghiep(this.loai_module, Integer.parseInt(this.nambc), this.ma_dn, this.UserName, this.Grade, this.poscd, sKiemtoan, sBcao, sThongtu);
                            return this.loai_module;
                        } else {
                            this.addActionError("Bạn phải chọn mã doanh nghiệp cần load số liệu");
                            return "error";
                        }
                    } else {
                        List<String> lstPos = (List) hmParameter.get("poscd");
                        this.lstDulieuNt = dao.getStatusSendCn("NT", hmParameter.get("loai_module").toString(), this.poscd, hmParameter.get("nambc").toString(), hmParameter.get("loai_module").toString().equals("ALL") ? this.loai_module : "SEND");
                        return "checksend";
                    }
                } else {
                    this.addActionError("Bạn phải chọn năm báo cáo tài chính của doanh nghiệp");
                    return "error";
                }
            } else {
                this.addActionError("Bạn phải chọn loại module cần load số liệu");
                return "error";
            }
        } catch (Exception var4) {
            System.err.println(var4.getMessage());
            CoreLogger.error(this.getClass().getCanonicalName() + " loadDataDoanhnghiep -> " + var4.getMessage());
            this.addActionError("Lỗi bạn không thể load được tham số " + var4.getMessage());
            return "error";
        }
    }

    public String saveBctcCic() {
        try {
            label57:
            {
                if (!this.getParaSession()) {
                    return "error";
                }
                sKiemtoan = (String) ActionContext.getContext().getSession().get("sKiemtoan");
                sBcao = (String) ActionContext.getContext().getSession().get("sBcao");
                sThongtu = (String) ActionContext.getContext().getSession().get("sThongtu");

                // Kiểm tra xem giá trị đã được lấy từ session chưa
                if (sKiemtoan == null || sKiemtoan.equals("")) {
                    this.addActionError("Bạn chưa chọn kiểm toán!");
                    return "error";
                }
                if (sBcao == null || sBcao.equals("")) {
                    this.addActionError("Bạn chưa chọn báo cáo tài chính hợp nhất!");
                    return "error";
                }
                if (sThongtu == null || sThongtu.equals("")) {
                    this.addActionError("Bạn chưa chọn số Thông tư");
                    return "error";
                }
                if (this.loai_module != null && !this.loai_module.equals("-1")) {
                    if (this.ma_dn != null && !this.ma_dn.equals("")) {
                        if (this.nambc != null && !this.nambc.equals("")) {
                            if (this.lstDulieuNt == null || this.lstDulieuNt.size() < 1) {
                                this.addActionError("Không thể load được dữ liệu bạn đã nhập");
                                return "error";
                            }

                            daoBCTCCICTT200 dao = new daoBCTCCICTT200();
                            dao.saveDataDoanhnghiep(this.loai_module, Integer.parseInt(this.nambc), this.ma_dn, this.UserName, this.Grade, sKiemtoan, sBcao, sThongtu, this.lstDulieuNt);
                            break label57;
                        }

                        this.addActionError("Bạn phải chọn năm báo cáo tài chính của doanh nghiệp");
                        return "error";
                    }

                    this.addActionError("Bạn phải chọn mã doanh nghiệp cần load số liệu");
                    return "error";
                }

                this.addActionError("Bạn phải chọn loại module cần load số liệu");
                return "error";
            }
        } catch (SQLException var2) {
            this.addActionError("Lỗi bạn không thể lưu dữ liệu, xin liên hệ với quản trị để khắc phục.<br>" + var2.getMessage().substring(var2.getMessage().indexOf("<messageError>"), var2.getMessage().indexOf("</messageError>")));
            return "error";
        }

        this.addActionMessage("Bạn đã lưu dứ liệu thành công");
        return "success";
    }

    public String sendCIC() {
        System.err.println("Vao ham sendCIC");

        try {
            if (!this.getParaSession()) {
                return "error";
            } else {
                HashMap hmParameter = this.getParameter();
                List<String> lstPos = (List) hmParameter.get("poscd");
                daoBCTCCICTT200 dao = new daoBCTCCICTT200();
                Map<String, Integer> mapStatusSend = new HashMap();
                Iterator var5 = lstPos.iterator();

                while (true) {
                    while (var5.hasNext()) {
                        String mapgd = (String) var5.next();
                        ServletContext context = ServletActionContext.getServletContext();
                        String strPathSave = !context.getRealPath("/").endsWith("/") ? context.getRealPath("/") + "/" + "EXPORT_REPORT/XML/" : context.getRealPath("/") + "EXPORT_REPORT/XML/";
                        strPathSave = strPathSave + hmParameter.get("loai_module").toString() + "_" + mapgd + "_" + this.UserName + "_" + Long.toString(System.currentTimeMillis()).substring(Long.toString(System.currentTimeMillis()).length() - 6) + ".xml";
                        new ArrayList();
                        boolean bStatus_file = false;
                        List<String> lstData = dao.getDataSendCIC("NT", hmParameter.get("loai_module").toString(), mapgd, hmParameter.get("nambc").toString());
                        if (lstData != null && lstData.size() != 0) {
                            bStatus_file = (new XmlCICSync()).createXmlFileCIC("21", "NT", hmParameter.get("loai_module").toString(), hmParameter.get("nambc").toString(), this.UserName, this.Grade, mapgd, lstData, "SEND", strPathSave);
                            if (!bStatus_file) {
                                CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong tao duoc file " + strPathSave);
                                mapStatusSend.put(mapgd, 1);
                            }

                            File checkfile = new File(strPathSave);
                            if (!checkfile.exists()) {
                                CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong tao duoc file " + strPathSave);
                                mapStatusSend.put(mapgd, 2);
                            }

                            ProcessReportSyn clientWritexml = new ProcessReportSyn();
                            String sStatus = clientWritexml.SendFileXmlToWebServices(strPathSave);
                            if (sStatus.equals("FAIL")) {
                                System.err.println("Ban chua dong bo du lieu duoc ve TW");
                                if (checkfile.exists()) {
                                    checkfile.delete();
                                }

                                CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: Khong dong bo duoc file " + strPathSave);
                                mapStatusSend.put(mapgd, 3);
                            } else if (sStatus.equals("OK")) {
                                if (checkfile.exists()) {
                                    checkfile.delete();
                                }

                                mapStatusSend.put(mapgd, 4);
                            } else {
                                if (checkfile.exists()) {
                                    checkfile.delete();
                                }

                                mapStatusSend.put(mapgd, 5);
                            }
                        } else {
                            mapStatusSend.put(mapgd, 6);
                        }
                    }

                    this.setLstViewSend(this.getViewStatusSend(lstPos, mapStatusSend));
                    return "success";
                }
            }
        } catch (Exception var14) {
            CoreLogger.error(this.getClass().getName() + " Exception -> sendCIC: " + var14.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> sendCIC: " + var14.getMessage());
            this.addActionError("Bạn chưa gửi được dữ liệu xin liên hệ với quản trị để được khắc phục");
            return "error";
        }
    }

    private List<ModelViewSend> getViewStatusSend(List<String> lstPos, Map<String, Integer> mapStatus) {
        this.addActionMessage("Danh sách các PGD gửi dữ liệu và tình trạng dữ liệu");
        ArrayList lstStatus = new ArrayList();

        try {
            Map<String, String> mapPosByName = DaoKtgsMain.newInstance().getPosByName(lstPos);
            Iterator var5 = mapStatus.keySet().iterator();

            while (var5.hasNext()) {
                String key = (String) var5.next();
                if (mapPosByName.get(key) != null) {
                    Integer value = (Integer) mapStatus.get(key);
                    ModelViewSend modelview = ModelViewSend.newInstance();
                    modelview.setMapgd(key);
                    modelview.setKey(value);
                    modelview.setTenpgd((String) mapPosByName.get(key));
                    switch (value) {
                        case 1:
                            modelview.setMota_loi("Tạo file xml bị lỗi");
                            break;
                        case 2:
                            modelview.setMota_loi("Không tìm thấy file xml");
                            break;
                        case 3:
                            modelview.setMota_loi("Gửi dữ liệu bị lỗi");
                            break;
                        case 4:
                            modelview.setMota_loi("Thành công");
                            break;
                        case 5:
                            modelview.setMota_loi("Phòng giao dịch này bị khóa");
                            break;
                        case 6:
                            modelview.setMota_loi("Không có dữ liệu");
                            break;
                        default:
                            modelview.setMota_loi("Không đúng trạng thái lỗi");
                    }

                    lstStatus.add(modelview);
                }
            }
        } catch (Exception var9) {
            CoreLogger.error(this.getClass().getName() + " Exception -> getViewStatusSend: " + var9.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> getViewStatusSend: " + var9.getMessage());
        }

        return lstStatus;
    }

    public List<String> getPoscd() {
        return this.poscd;
    }

    public void setPoscd(List<String> poscd) {
        this.poscd = poscd;
    }

    public TreeNode getNodes_pos() {
        return this.nodes_pos;
    }

    public void setNodes_pos(TreeNode nodes_pos) {
        this.nodes_pos = nodes_pos;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGrade() {
        return this.Grade;
    }

    public void setGrade(String Grade) {
        this.Grade = Grade;
    }

    public String getUserName() {
        return this.UserName;
    }

    public void setUserName(String UserName) {
        this.UserName = UserName;
    }

    public String getLoai_module() {
        return this.loai_module;
    }

    public void setLoai_module(String loai_module) {
        this.loai_module = loai_module;
    }

    public List<ListValue> getModuleList() {
        return this.moduleList;
    }

    public void setModuleList(List<ListValue> moduleList) {
        this.moduleList = moduleList;
    }

    public List<ListValue> getDoanhNghiepList() {
        return this.doanhNghiepList;
    }

    public void setDoanhNghiepList(List<ListValue> doanhNghiepList) {
        this.doanhNghiepList = doanhNghiepList;
    }

    public String getMa_dn() {
        return this.ma_dn;
    }

    public void setMa_dn(String ma_dn) {
        this.ma_dn = ma_dn;
    }

    public String getNambc() {
        return this.nambc;
    }

    public void setNambc(String nambc) {
        this.nambc = nambc;
    }

    public List<ListValue> getNambcList() {
        return this.nambcList;
    }

    public void setNambcList(List<ListValue> nambcList) {
        this.nambcList = nambcList;
    }

    public List<QT_DULIEU_NT> getLstDulieuNt() {
        return this.lstDulieuNt;
    }

    public void setLstDulieuNt(List<QT_DULIEU_NT> lstDulieuNt) {
        this.lstDulieuNt = lstDulieuNt;
    }

    public String getDefaultNambc() {
        return String.valueOf(Calendar.getInstance().get(1) - 1);
    }

    public void setDefaultNambc(String defaultNambc) {
        this.defaultNambc = defaultNambc;
    }

    public String getTotalDataView() {
        return this.totalDataView;
    }

    public void setTotalDataView(String totalDataView) {
        this.totalDataView = totalDataView;
    }

    public List<ModelViewSend> getLstViewSend() {
        return this.lstViewSend;
    }

    public void setLstViewSend(List<ModelViewSend> lstViewSend) {
        this.lstViewSend = lstViewSend;
    }
}
