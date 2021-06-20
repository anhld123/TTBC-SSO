package vbsp.ims.action;

import com.jgeppert.struts2.jquery.tree.result.TreeNode;
import com.opensymphony.xwork2.ActionSupport;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.interceptor.ServletRequestAware;
import vbsp.ims.dao.DaoCreditPlan;
import vbsp.ims.model.ModelTreeNode;

public class KhtdActionSupport extends ActionSupport implements ServletRequestAware {
    private List<TreeNode> nodes;
    private String posList; //Danh sach POS
    private String xaThon;  //Lay du lieu cua xa hay cua thon
    private String ngayNhap;    //Ngay nhap du lieu
    private String maPGD;
    private String tenPGD;
    private String labelGrid;
    
    
    private DaoCreditPlan daoCreditPlan = new DaoCreditPlan();
    private String userName;
    private HttpServletRequest request = null;
    private List<ModelTreeNode> lstModelTreeNode;

    public KhtdActionSupport() {
        
    }

    public String execute() throws Exception {
        return "success";
    }
    
    public String populateTree(){
        //Lay username
        HttpSession session = request.getSession();
        userName=session.getAttribute("username").toString();
        
        //Lan dau tien request (khong goi qua radio=xa, radio=thon)
        if(xaThon == null){
            //CuongBM: 18Jun14
            //Truyen vao userName, tham so "N": khi khong liet ke thon
            lstModelTreeNode=daoCreditPlan.getDataPosTreeNode(userName, "N");
            tranferModelTreeNodeToNodes("N");
        }
        //Goi du lieu qua radio xa thon
        else{
            //Neu la xa
            if(xaThon.equalsIgnoreCase("xa")){
                //CuongBM: 18Jun14
                //Truyen vao userName, tham so "N": khi khong liet ke thon
                lstModelTreeNode=daoCreditPlan.getDataPosTreeNode(userName, "N");
                tranferModelTreeNodeToNodes("N");
            }
            //Neu la thon
            else if(xaThon.equalsIgnoreCase("thon")){
                //Goi ham thon
                //CuongBM: 18Jun14
                //Truyen vao userName, tham so "N": khi khong liet ke thon
                lstModelTreeNode=daoCreditPlan.getDataPosTreeNode(userName, "Y");
                tranferModelTreeNodeToNodes("Y");
            }
        }
        
        return "success";
    }
    
    //Ke hoach ton
    public String redirectGridKHT(){
        labelGrid = "Kế Hoạch Tồn Năm Trước - " + maPGD + " - " + tenPGD;
        return "success";
    }
    
    
    //Xay dung ke hoach
    public String redirectGridXDKH(){
        labelGrid = "Xây Dựng Kế Hoạch - " + maPGD + " - " + tenPGD;
        return "success";
    }
    
    //Giao ke hoach
    public String redirectGridGiaoKH(){
        labelGrid = "Giao Kế Hoạch - " + maPGD + " - " + tenPGD;
        return "success";
    }
    
    //Dieu chinh ke hoach
    public String redirectGridDieuChinhKH(){
        labelGrid = "Điều Chỉnh Kế Hoạch - " + maPGD + " - " + tenPGD;
        return "success";
    }
    
    //Truyen gia tri tu ModelTreeNode sang Nodes, de hien thi len tree
    private void tranferModelTreeNodeToNodes(String tranferType){
        if(tranferType.equalsIgnoreCase("Y")){
            nodes = new ArrayList<TreeNode>();
                        
            TreeNode parentLevel1 = new TreeNode(); //Nut cha dau tien (Huyen)
            TreeNode parentLevel2 = new TreeNode(); //Nut cha thu 2 (Xa)
            Boolean fistLoop = true;    //Lap lan dau tien
            String previousParent2 = "";
            
            for (ModelTreeNode obj : lstModelTreeNode) {
                //Lan dau tien
                if(fistLoop == true){
                    parentLevel1.setId(obj.getStrParentCd());
                    parentLevel1.setTitle(obj.getStrParentDesc());
                    parentLevel1.setState(TreeNode.NODE_STATE_OPEN);
                    parentLevel1.setChildren(new LinkedList<TreeNode>());
                    fistLoop = false;
                }else{
                    //Truong hop vong lap dau tien
                    if(previousParent2 == ""){
                        parentLevel2.setId(obj.getStrParentCd());
                        parentLevel2.setTitle(obj.getStrParentDesc());
                        parentLevel2.setState(TreeNode.NODE_STATE_CLOSED);
                        parentLevel2.setChildren(new LinkedList<TreeNode>());

                        TreeNode childNode = new TreeNode();
                        childNode.setId(obj.getStrChildCd());
                        childNode.setTitle(obj.getStrChildDesc());
                        childNode.setState(TreeNode.NODE_STATE_LEAF);

                        parentLevel2.getChildren().add(childNode);

                        //Luu lai gia tri 
                        previousParent2 = parentLevel2.getId();
                    }
                    //Khi ma cha chua thay doi (parentLevel2 chua thay doi)
                    else if(previousParent2.equalsIgnoreCase(obj.getStrParentCd())){
                        TreeNode childNode = new TreeNode();
                        childNode.setId(obj.getStrChildCd());
                        childNode.setTitle(obj.getStrChildDesc());
                        childNode.setState(TreeNode.NODE_STATE_LEAF);

                        parentLevel2.getChildren().add(childNode);

                        //Luu lai gia tri 
                        previousParent2 = parentLevel2.getId();
                    }
                    //Khi thay doi parent 2
                    else{
                        //Them nut moi vao
                        parentLevel1.getChildren().add(parentLevel2);
                        
                        parentLevel2 = new TreeNode();
                        parentLevel2.setId(obj.getStrParentCd());
                        parentLevel2.setTitle(obj.getStrParentDesc());
                        parentLevel2.setState(TreeNode.NODE_STATE_CLOSED);
                        parentLevel2.setChildren(new LinkedList<TreeNode>());

                        TreeNode childNode = new TreeNode();
                        childNode.setId(obj.getStrChildCd());
                        childNode.setTitle(obj.getStrChildDesc());
                        childNode.setState(TreeNode.NODE_STATE_LEAF);

                        parentLevel2.getChildren().add(childNode);

                        //Luu lai gia tri 
                        previousParent2 = parentLevel2.getId();
                    }                    
                }                
            }
            parentLevel1.getChildren().add(parentLevel2); //Vong lap cuoi cung
            nodes.add(parentLevel1);
            
        }else{
            nodes = new ArrayList<TreeNode>();
            
            String previousParentId = "";            
            TreeNode parentNode = new TreeNode(); //Nut cha
            
            for (ModelTreeNode obj : lstModelTreeNode) {
                //Lan dau tien
                if(previousParentId == ""){                    
                    parentNode.setId(obj.getStrParentCd());
                    parentNode.setTitle(obj.getStrParentDesc());
                    parentNode.setState(TreeNode.NODE_STATE_OPEN);
                    parentNode.setChildren(new LinkedList<TreeNode>());
                    
                    //Luu lai gia tri 
                    previousParentId = parentNode.getId();
                }
                
                TreeNode childNode = new TreeNode();
                childNode.setId(obj.getStrChildCd());
                childNode.setTitle(obj.getStrChildDesc());
                childNode.setState(TreeNode.NODE_STATE_LEAF);

                parentNode.getChildren().add(childNode);                
            }
            
            nodes.add(parentNode);
        }
    }
    
    @Override
    public void setServletRequest(HttpServletRequest hsr) {
        this.request = hsr;
    }

    //<editor-fold defaultstate="collapsed" desc="Getter Setter">
    public List<TreeNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<TreeNode> nodes) {
        this.nodes = nodes;
    }

    public String getXaThon() {
        return xaThon;
    }

    public void setXaThon(String xaThon) {
        this.xaThon = xaThon;
    }
    
    public String getPosList() {
        return posList;
    }

    public void setPosList(String posList) {
        this.posList = posList;
    }
    
     public String getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(String ngayNhap) {
        this.ngayNhap = ngayNhap;
    }
    
    public String getMaPGD() {
        return maPGD;
    }

    public void setMaPGD(String maPGD) {
        this.maPGD = maPGD;
    }
    
    public String getLabelGrid() {
        return labelGrid;
    }

    public void setLabelGrid(String labelGrid) {
        this.labelGrid = labelGrid;
    }
    
    public String getTenPGD() {
        return tenPGD;
    }

    public void setTenPGD(String tenPGD) {
        this.tenPGD = tenPGD;
    }
//</editor-fold>

    
}
