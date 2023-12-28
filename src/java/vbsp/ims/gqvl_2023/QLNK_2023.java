/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vbsp.ims.gqvl_2023;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import static com.opensymphony.xwork2.Action.ERROR;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionContext;
import vbsp.ims.nhaptaycn.action.*;
import java.io.File;
import java.math.BigInteger;
import vbsp.ims.nhaptaycn.action.*;
import vbsp.ims.nhaptaycn.action.*;
import java.sql.Connection;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.ServletContext;
import org.apache.struts2.ServletActionContext;
import vbsp.ims.nhaptaycn.dao.DaoNhaptaycnMain;
import vbsp.ims.bcqt.model.DULIEU_NT;
import vbsp.ims.bcqt.model.QT_DULIEU_NT;
import vbsp.ims.dao.DaoConnect;
import vbsp.ims.define.Define;
import vbsp.ims.loadparams.ReportParam;
import vbsp.ims.log.CoreLogger;
import vbsp.ims.model.ModelTreeNode;
import vbsp.ims.nghiquyet11cp.DaoNghiquyet11cp;
import vbsp.ims.report.fast.ListValue;
import vbsp.ims.restapi.DuLieuNTRow;
import vbsp.ims.syn.ProcessReportSyn;
import vbsp.ims.tdnn.DaoTdnnMain;
import vbsp.ims.util.DateUtil;
import vbsp.ims.xml.XmlKtgsSync;

/**
 *
 * @author Trung
 */
public class QLNK_2023 extends ActionNhaptaycnMain
        implements NhaptaycnFunction {

    Service_GQVL2023 _leaveHomeService;
    private List<DuLieuNTRow> lstData;
    private List<DuLieuNTRow> lstDataTw;
    
    private String  totalD8Tw;
    private String  totalD11Tw;
    protected String khoa_rr;

    public String getKhoa_rr() {
        return khoa_rr;
    }

    public void setKhoa_rr(String khoa_rr) {
        this.khoa_rr = khoa_rr;
    }

    public List<ListValue> getLstAllBaocao() {
        return lstAllBaocao;
    }

    public void setLstAllBaocao(List<ListValue> lstAllBaocao) {
        this.lstAllBaocao = lstAllBaocao;
    }

    public List<ReportParam> getLstNhaptaycnParams() {
        return lstNhaptaycnParams;
    }

    public void setLstNhaptaycnParams(List<ReportParam> lstNhaptaycnParams) {
        this.lstNhaptaycnParams = lstNhaptaycnParams;
    }
    protected List<ListValue> lstAllBaocao = new ArrayList<>();
    protected List<ReportParam> lstNhaptaycnParams = new ArrayList<>();
    protected TreeNode nodes_pos = new TreeNode();
    public String getTotalD8Tw() {
        return totalD8Tw;
    }

    public void setTotalD8Tw(String totalD8Tw) {
        this.totalD8Tw = totalD8Tw;
    }

    public String getTotalD11Tw() {
        return totalD11Tw;
    }

    public void setTotalD11Tw(String totalD11Tw) {
        this.totalD11Tw = totalD11Tw;
    }

    

    @Override
    public String load() {
        try {
//            System.err.println("NTMOI - 01");
            if (!getParaSession()) {
                return ERROR;
            }
            posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
            pos_cd_username = posMainModel.getPosCd();
            BigInteger b1 = new BigInteger("0");
            BigInteger b2 = new BigInteger("0");
            DecimalFormat df = new DecimalFormat("#.##");

            HashMap hmParameter = getParameter();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            Connection conn = new DaoConnect().getConnect();
            _leaveHomeService = new Service_GQVL2023();
//            setNgay_bc(hmParameter.get("ngay_bc").toString());
            DuLieuNTRow tmp = new DuLieuNTRow();
            if (Grade.equals("3")) {
                this.lstData = _leaveHomeService.getCustomers(pos_cd_username, "H", hmParameter.get("ngay_bc").toString(), "1", "KS_HTLS_CN");
            } else if (Grade.equals("2")) {
                this.lstData = _leaveHomeService.getCustomers(pos_cd_username, "M", hmParameter.get("ngay_bc").toString(), "1", "KS_HTLS_CN");
                this.lstDataTw = _leaveHomeService.getCustomers("000100", "H", hmParameter.get("ngay_bc").toString(), "1", "KS_HTLS_CN");
                if(this.lstDataTw == null || this.lstDataTw.size() == 0)
                {
                    addActionError("TW chưa cập nhật số liệu của đơn vị. Vui lòng liên hệ với TT CNTT");;
                    return ERROR;
                }
                tmp = findUsingEnhancedForLoop(pos_cd_username, lstDataTw);
                if(tmp == null)
                {
                    addActionError("TW chưa cập nhật số liệu của đơn vị. Vui lòng liên hệ với TT CNTT");;
                    return ERROR;
                }
                else
                {
                    setTotalD8Tw(String.format("%,d", new BigInteger(tmp.getD8() == null ? "0" : tmp.getD8())));
                    setTotalD11Tw(String.format("%,d", new BigInteger(tmp.getD11() == null ? "0" : tmp.getD11())));
                }                                 
            }
            if (lstData == null || lstData.size() == 0) {
                addActionError("Số liệu chưa được tạo tại Tw. Vui lòng liên hệ với TT CNTT");;
                return ERROR;
            }
            int iStt = 1;
            for (DuLieuNTRow item : lstData) {
                QT_DULIEU_NT row = new QT_DULIEU_NT();
                row.setKHOA(item.getKey());
                row.setTHUTU(iStt);
                iStt++;
                row.setTT_HIENTHI(item.getOrderDescription());
                row.setMA(item.getCode());
                row.setTEN(item.getName());

                Date reportDate = DateUtil.toDate(item.getReportDate());
                row.setNGAYBC(reportDate);
                row.setNAMBC(item.getReportYear());
                row.setMAPGD(item.getCode());
                row.setCO_TONGHOP(item.getPosFlag());
                row.setMACN(item.getBranchCode());
                row.setNGUOI_NHAP(item.getMakerId());
//                row.setNGAY_NHAP(item.getMakerDate());
                Date makerDate = DateUtil.toDate(item.getMakerDate());
                row.setNGAY_NHAP(makerDate);
                row.setNGUOI_DUYET(item.getAuthoriseId());
//                row.setNGAY_DUYET(item.getAuthoriseDate());
                Date authoriseDate = DateUtil.toDate(item.getAuthoriseDate());
                row.setNGAY_DUYET(authoriseDate);
                row.setD1(item.getD1());
                row.setD2(item.getD2());
                row.setD3(item.getD3() == null ? "0" : item.getD3());
                row.setD4(item.getD4() == null ? "0" : item.getD4());
                row.setD5(item.getD5() == null ? "0" : item.getD5());
                row.setD6(item.getD6() == null ? "0" : item.getD6());
                row.setD7(item.getD7() == null ? "0" : item.getD7());
                row.setD8(item.getD8() == null ? "0" : item.getD8());
                row.setD9(item.getD9() == null ? "0" : item.getD9());
                row.setD10(item.getD10() == null ? "0" : item.getD10());
                row.setD11(item.getD11() == null ? "0" : item.getD11());
                row.setD12(item.getD12() == null ? "0" : item.getD12());
                row.setD13(item.getD13() == null ? "0" : item.getD13());
                row.setD14(item.getD14());
                row.setD15(item.getD15());
                row.setD16(item.getD16());
                row.setD17(item.getD17());
                row.setD18(item.getD18());
                row.setD19(item.getD19());
                row.setD20(item.getD20());
                row.setD21(item.getD21());
                row.setD22(item.getD22());
                row.setD23(item.getD23());
                row.setD24(item.getD24());
                row.setD25(item.getD25());
                row.setD26(item.getD26());
                row.setD27(item.getD27());
                row.setD28(item.getD28());
                row.setD29(item.getD29());
                row.setD50(item.getD50());
                row.setNHAPTAY(item.getManualFlag());
                row.setFONTFORMAT(item.getFontFormat());
                row.setKIEUIN(item.getStyle());
                lstDulieuNt.add(row);
            }
//            }

            if (conn != null) {
                conn.close();
            }

        } catch (Exception e) {
            addActionError("Đã có lỗi. Vui lòng liên hệ với quản trị");;
            return ERROR;
        }
        return SUCCESS;
    }
    
    public DuLieuNTRow findUsingEnhancedForLoop(
        String key, List<DuLieuNTRow> list) {

          for (DuLieuNTRow row : list) {
              if (row.getCode().equals(key)) {
                  return row;
              }
          }
          return null;
}

    @Override
    public String save() {
        System.out.println("vao váe");
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            if (lstDulieuNt == null || lstDulieuNt.size() == 0) {
                addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");;
                return ERROR;
            }
            ArrayList<DuLieuNTRow> lstUpdateDate = new ArrayList<>();
            _leaveHomeService = new Service_GQVL2023();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            HashMap hmParameter = getParameter();
            if (Grade.equals("3")) {
                if (!daoMain.saveHTLS2023("KS_HTLS_CN", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstDulieuNt, poscd)) {
                        addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                        return ERROR;
                    }
            } else {
                

                ArrayList<QT_DULIEU_NT> lstLocalDataUpdate = new ArrayList<>();
                this.lstDataTw = _leaveHomeService.getCustomers("000100", "H", hmParameter.get("ngay_bc").toString(), "1", "KS_HTLS_CN");
                DuLieuNTRow tmpTw = new DuLieuNTRow();
                tmpTw = findUsingEnhancedForLoop(pos_cd_username, lstDataTw);
                BigInteger d8CN = new BigInteger("0");
                BigInteger d11CN = new BigInteger("0");
                for (QT_DULIEU_NT dulieu : lstDulieuNt) {
                    d8CN = d8CN.add(new BigInteger(dulieu.getD8() == null ? "0" : dulieu.getD8()));
                    d11CN = d11CN.add(new BigInteger(dulieu.getD11() == null ? "0" : dulieu.getD11()));
                }
                if(d8CN.compareTo(new BigInteger(tmpTw.getD8())) !=0)
                {
                    addActionError("Số liệu cột D8 giữa TW và đơn vị nhập đang khác nhau. Vui lòng kiểm tra lại");
                        return ERROR;
                }
                if(d11CN.compareTo(new BigInteger(tmpTw.getD11())) !=0)
                {
                    addActionError("Số liệu cột D11 giữa TW và đơn vị nhập đang khác nhau. Vui lòng kiểm tra lại");
                        return ERROR;
                }
                
                for (QT_DULIEU_NT tmp : lstDulieuNt) {

                    DuLieuNTRow tempadd = new DuLieuNTRow();

                    tempadd.setKey("KS_HTLS_CN");
//                tempadd.setOrderValue("");
//                tempadd.setOrderDescription("");
                    tempadd.setCode(tmp.getMA());
                    tempadd.setMakerId(UserName);
                    tempadd.setAuthoriseId(UserName);
                    tempadd.setReportDate(totalDataView);
//                tempadd.setName(tmp.getTEN());
                    tempadd.setReportYear(2023);
                    tempadd.setPosCode(tmp.getMAPGD());
                    tempadd.setPosFlag("M");
                    tempadd.setBranchCode(tmp.getMACN());
//                tempadd.setD2(tmp.getMAPGD());
//                tempadd.setD3(tmp.getD3());
//                tempadd.setD4(tmp.getD4());
//                tempadd.setD5(tmp.getD5());
//                tempadd.setD6(tmp.getD6());
//                tempadd.setD7(tmp.getD7());
                    tempadd.setD8(tmp.getD8());
//                tempadd.setD9(tmp.getD9());
//                tempadd.setD10(tmp.getD10());
                    tempadd.setD11(tmp.getD11());
//                tempadd.setD12(tmp.getD12());
//                tempadd.setD13(tmp.getD13());
//                tempadd.setD14(tmp.getD14());
//                tempadd.setD15(tmp.getD15());
//                tempadd.setD16(tmp.getD16());
//                tempadd.setD17(tmp.getD17());
//                tempadd.setD18(tmp.getD18());
//                tempadd.setD19(tmp.getD19());
//                tempadd.setD20(tmp.getD20());
//                tempadd.setD21(tmp.getD21());
//                tempadd.setD22(tmp.getD22());
//                tempadd.setD23(tmp.getD23());
//                tempadd.setD24(tmp.getD24());
//                tempadd.setD25(tmp.getD25());
//                tempadd.setD26(tmp.getD26());
//                tempadd.setD27(tmp.getD27());
//                tempadd.setD28(tmp.getD28());
//                tempadd.setD29(tmp.getD29());
                    tempadd.setD50("2");
                    lstUpdateDate.add(tempadd);
                    lstLocalDataUpdate.add(tmp);

                }
                
                
                int status = _leaveHomeService.saveHTLS2023(pos_cd_username, "M", hmParameter.get("ngay_bc").toString(), "", "", lstUpdateDate, "1");
                if (status == 200) {
                    if (!daoMain.saveHTLS2023("KS_HTLS_CN", UserName, Grade, hmParameter.get("ngay_bc").toString(), lstDulieuNt, poscd)) {
                        addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
                        return ERROR;
                    }
                }
            }

        } catch (Exception e) {
//            CoreLogger.error(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
//            System.err.println(this.getClass().getName() + " Exception -> SMS_001: " + e.getMessage());
            addActionError("Bạn chưa lưu được báo cáo xin liên hệ với quản trị để khắc phục");
            return ERROR;
        }
        addActionMessage("Bạn đã lưu dữ liệu thành công");
        return SUCCESS;
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

    protected boolean getParaSession() {
        Map session = ActionContext.getContext().getSession();

        if (session == null || session.size() == 0 || session.isEmpty()) {
            setMessage("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            addActionError("Bạn phải đăng nhập lại mới thực hiện được chức năng này");
            return false;
        }
        //lay ra user
        setUserName(session.get("username").toString());

//            System.err.println("execute sUserName=" + sUserName);
        if (UserName == null || UserName.isEmpty()) {
            setMessage("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được username bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        setGrade(session.get("reportGrade").toString());
        if (Grade == null || Grade.isEmpty()) {
            setMessage("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            addActionError("Không thể lấy ra được cấp báo cáo \"reportGrade\" bạn phải logout hệ thống sau đó đăng nhập lại ");
            return false;
        }
        posMainModel = listKTNBDA.get_pos_main_pos(UserName, Grade);
        pos_cd_username = posMainModel.getPosCd();

        return true;
    }

    private List<String> convertStringtoList(String[] value) {
        List<String> lst = new ArrayList<>();
        try {
            for (int i = 0; i < value.length; i++) {
                if (!value[i].equals("999999") && !value[i].isEmpty()) {
                    lst.add(value[i]);
                }
            }
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> convertStringtoList: " + e.getMessage());
        }
        return lst;
    }
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
                } else {
                    if (modelTree.getStrChildCd().equals("999999")) {
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

    protected HashMap<String, Object> getParameter() throws Exception {
        HashMap<String, Object> paramHashMap = new HashMap<>();
        Map<String, String[]> prameters = ServletActionContext.getRequest().getParameterMap();
        for (String parameter : prameters.keySet()) {
            String[] values = prameters.get(parameter);
            if (parameter.indexOf("TEXT") > 0 || parameter.indexOf("DATE") > 0 || parameter.indexOf("LIST") > 0) {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.indexOf("DATE") > 0) {
                    Date sdf = new SimpleDateFormat("dd/MM/yyyy").parse(values[0]);
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), new SimpleDateFormat("dd-MMM-yyyy").format(sdf));
                    lstParameters.add(new ListValue(parameter, values[0]));
                } else {
                    paramHashMap.put(parameter.substring(0, parameter.length() - 5), values[0]);
                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            } else {
                if (parameter.startsWith("1_")) {
                    parameter = parameter.substring(2, parameter.length());
                }
                if (parameter.equals("poscd")) {
                    paramHashMap.put(parameter, convertStringtoList(values));
                } else {
                    paramHashMap.put(parameter, values[0]);
                    lstParameters.add(new ListValue(parameter, values[0]));
                }
            }
        }
        return paramHashMap;
    }

    public String execute() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            setLstAllBaocao(DaoNghiquyet11cp.newInstance().getAllBaocao(Grade,"01_QLNK"));

        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> execute: " + e.getMessage());
        }
        return SUCCESS;
    }

    public String loadParaBaocao() {
        try {
            if (!getParaSession()) {
                return ERROR;
            }
            Connection conn = new DaoConnect().getConnect();
            DaoNghiquyet11cp daoMain = new DaoNghiquyet11cp();
            //khoi tao cho treeview cac pos
            List<ModelTreeNode> lstModelTree = daoMain.getDataPosTreeNode(conn, UserName, Grade, khoa_rr);
            if (Grade.equals("3")) {
                setTreeNodeGrade3(lstModelTree);
            } else {
                setTreeNodeGrade12(lstModelTree);
            }
            if(khoa_rr.equals("01/QLNK"))
                return SUCCESS;
        } catch (Exception e) {
            CoreLogger.error(this.getClass().getName() + " Exception -> loadParaBaocao: " + e.getMessage());
            System.err.println(this.getClass().getName() + " Exception -> loadParaBaocao: " + e.getMessage());
            return ERROR;
        }       
        return SUCCESS;
    }
}
