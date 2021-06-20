<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<%--<sj:head jqueryui="false" jquerytheme="simple"/>
<s:head/>
<sj:head/>--%>
<!DOCTYPE html>

      
        <style type="text/css">
            *{
                font: 12px Arial, Helvetica, sans-serif;
            }
            table{
                border-style: solid;
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
            }
            .tbhead th{
                background-color: #5e5e55;
                font-weight: bold;
                color: #fff;
                text-align: center;
                padding: 5px;
            }
            .cscontent td{
                padding-left:5px;
            }
            .cscontent:hover{
                background-color: #ffff99;
            }
            
            .cscontent:hover input[type="text"]{
                background-color: #ffff99;
            }
            .tblmain tr td{
                font-weight: bold;
                color: #018c3b;
            }
            
            input{
                border: 0px;
            }
            
            .BOLD input[type="text"]
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
            
            .ITALIC input[type="text"]
            {
                font-style: italic;
                font-size: 12px;
                width: 95%;
            }
            
            input[type="text"]
            {
                width: 95%;
            }
            
            input[type="button"]
            {
                margin-left: 3px;
            }
            
            .parameter{
                border: 1px solid black;
                width: 50%;
            }
            
            #posCD, #quyBc, #namBc, #maCn, #userId{
                width: 70px;
            }
            
            .tdtest {
                width: 20%;
            }
        </style>
        
     <SCRIPT language="javascript">

//            function clk_glkhtd() {
//                var lstPos = "";
//                $('#treeView').jstree("get_checked", null, true).each(
//                        function() {
//                            lstPos = lstPos + this.id + ',';
//                        });
//                document.getElementById("selectedPos").value = lstPos;
//            }
            
            //Xu ly tinh tong cho tung dong
          
            
            //Check xem du lieu da ok chua
            //Neu ok roi thi goi su kien submit du lieu

           
            
            function validateRequiredFields(){
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                
                //Cac class number phai nhap kieu so
                $(".number").each(function(index){
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){
                        result = false; 
                        return false;
                    }
                });
                
                //Cac class nubmer2 phai nhap kieu so
                $(".number2").each(function(index){
                    //Kiem tra xem co nhap kieu so khong
                    if(isNaN(parseFloat($(this).val()))){
                        result = false;
                        return false;
                    }
                });
                
                if(result == false){
                    //Neu nguoi dung khong nhap dung kieu du lieu
                    //Dua ra canh bao
                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                }
                
                return result;
            }
        </SCRIPT>   
        
      
    <div id="TEST">
    <div id="valueview_div" class="BalanceSheetStyle" >                        
        <table border="1px" id="tableKtnb">
                            <tr class="tbhead">
                                <th rowspan="5">Đơn vị</th>
                                <th rowspan="5">Tổng số đơn</th>
                                <th colspan="5">Tiếp nhận</th>
                                <th colspan="19">Phân loại đơn khiếu nại, tố cáo (số đơn)</th>
                                <th rowspan="5">Đơn khác (kiến nghị, phản ánh, đơn nặc danh)</th>
                                <th colspan="5">Kết quả xử lý đơn khiếu nại, tố cáo</th>
                                <th rowspan="5">Ghi chú</th>
                                
                               
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2" colspan="2">Đơn tiếp nhận trong kỳ</th>
                                <th rowspan="2" colspan="2">Đơn kỳ trước chuyển sang</th>
                                <th rowspan="4">Đơn đủ điều kiện xử lý</th>
                                <th colspan="13">Theo nội dung</th>
                                <th colspan="3">Theo thẩm quyền giải quyết</th>
                                <th colspan="3">Theo trình tự giải quyết</th>
                                <th rowspan="4">Số văn bản hướng dẫn</th>
                                <th rowspan="4">Số đơn chuyển cơ quan có thẩm quyền</th>
                                <th rowspan="4">Số công văn đôn đốc việc giải quyết</th>
                                <th rowspan="2" colspan="2">Đơn thuộc thẩm quyền</th>
                            </tr>
                            <tr class="tbhead">
                                <th colspan="7">Khiếu nại</th>
                                <th colspan="6">Tố cáo</th>
                                <th rowspan="3">Của các cơ quan hành chính các cấp</th>
                                <th rowspan="3">Của các cơ quan tư pháp các cấp</th>
                                <th rowspan="3">Của cơ quan Đảng</th>
                                <th rowspan="3">Chưa được giải quyết</th>
                                <th rowspan="3">Đã được giải quyết lần đầu</th>
                                <th rowspan="3">Đã được giải quyết nhiều lần</th>
                            </tr>
                            <tr class="tbhead"> 
                               <th rowspan="2">Đơn có nhiều người đứng tên</th>
                                <th rowspan="2">Đơn một người đứng tên</th>
                                <th rowspan="2">Đơn có nhiều người đứng tên</th>
                                <th rowspan="2">Đơn có một người đứng tên</th>
                                <th colspan="5">Lĩnh vực hành chính</th>
                                <th rowspan="2">Lĩnh vực tư pháp</th>
                                <th rowspan="2">Về Đảng</th>
                                <th rowspan="2">Tổng</th>
                                <th rowspan="2">Lĩnh vực hành chính</th>
                                <th rowspan="2">Lĩnh vực tư pháp</th>
                                <th rowspan="2">Tham nhũng</th>
                                <th rowspan="2">Về Đảng</th>
                                <th rowspan="2">Lĩnh vực khác</th>
                                <th rowspan="2">Khiếu nại</th>
                                <th rowspan="2">Tố cáo</th>
                            </tr>
                            <tr class="tbhead">
                                <th>Tổng</th>
                                <th>Liên quan đến đất đai</th>
                                <th>Về nhà, tài sản</th>
                                <th>Về chính sách,chế độ CC,VC</th>
                                <th>Lĩnh vực CT, VH, XH</th>
                            </tr>
                            <tr class="tbhead">
                                <th>MS</th>
                                <th>1=2+3+4+5</th>
                                <th>2</th>
                                <th>3</th>
                                <th>4</th>
                                <th>5</th>
                                <th>6</th>
                                <th>7=8+9+10+11</th>                                
                                <th>8</th>
                                <th>9</th>
                                <th>10</th>
                                <th>11</th>
                                <th>12</th>                                
                                <th>13</th>
                                <th>14=15+16+17+18+19</th>
                                <th>15</th>
                                <th>16</th>
                                <th>17</th>
                                <th>18</th>
                                <th>19</th>
                                <th>20</th>
                                <th>21</th>
                                <th>22</th>
                                <th>23</th>
                                <th>24</th>
                                <th>25</th>
                                <th>26</th>
                                <th>27</th>
                                <th>28</th>
                                <th>29</th>
                                <th>30</th>
                                <th>31</th>                                
                                <th>32</th>
                                
                                
                            </tr>
                            
                            
                            <s:iterator value="ktnb09ModelList">
                                <tr class="cscontent">
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DV'/>" name="KT_DV" class="KT_DV" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>

                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_TS'/>" name="KT_TN_TS" class="KT_TN_TS number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_TK_NN'/>" name="KT_TN_TK_NN" class="KT_TN_TK_NN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_TK_MN'/>" name="KT_TN_TK_MN" class="KT_TN_TK_MN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_KT_NN'/>" name="KT_TN_KT_NN" class="KT_TN_KT_NN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_KT_MN'/>" name="KT_TN_KT_MN" class="KT_TN_KT_MN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TN_DDK'/>" name="KT_TN_DDK" class="KT_TN_DDK number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_T'/>" name="KT_PL_ND_KN_HC_T" class="KT_PL_ND_KN_HC_T number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_DD'/>" name="KT_PL_ND_KN_HC_DD" class="KT_PL_ND_KN_HC_DD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_NTS'/>" name="KT_PL_ND_KN_HC_NTS" class="KT_PL_ND_KN_HC_NTS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_CS'/>" name="KT_PL_ND_KN_HC_CS" class="KT_PL_ND_KN_HC_CS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_HC_CT'/>" name="KT_PL_ND_KN_HC_CT" class="KT_PL_ND_KN_HC_CT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_TP'/>" name="KT_PL_ND_KN_TP" class="KT_PL_ND_KN_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_KN_D'/>" name="KT_PL_ND_KN_D" class="KT_PL_ND_KN_D number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_T'/>" name="KT_PL_ND_TC_T" class="KT_PL_ND_TC_T number" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_HC'/>" name="KT_PL_ND_TC_HC" class="KT_PL_ND_TC_HC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_TP'/>" name="KT_PL_ND_TC_TP" class="KT_PL_ND_TC_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_TN'/>" name="KT_PL_ND_TC_TN" class="KT_PL_ND_TC_TN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_D'/>" name="KT_PL_ND_TC_D" class="KT_PL_ND_TC_D number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_ND_TC_K'/>" name="KT_PL_ND_TC_K" class="KT_PL_ND_TC_K number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TQ_HC'/>" name="KT_PL_TQ_HC" class="KT_PL_TQ_HC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TQ_TP'/>" name="KT_PL_TQ_TP" class="KT_PL_TQ_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TQ_D'/>" name="KT_PL_TQ_D" class="KT_PL_TQ_D number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TT_CGQ'/>" name="KT_PL_TT_CGQ" class="KT_PL_TT_CGQ number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TT_DGQ1'/>" name="KT_PL_TT_DGQ1" class="KT_PL_TT_DGQ1 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_PL_TT_GDQN'/>" name="KT_PL_TT_GDQN" class="KT_PL_TT_GDQN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK'/>" name="KT_DK" class="KT_DK number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly" /></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_SVB'/>" name="KT_KQ_SVB" class="KT_KQ_SVB number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CTQ'/>" name="KT_KQ_CTQ" class="KT_KQ_CTQ number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_SCV'/>" name="KT_KQ_SCV" class="KT_KQ_SCV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_TTQ_KN'/>" name="KT_KQ_TTQ_KN" class="KT_KQ_TTQ_KN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_TTQ_TC'/>" name="KT_KQ_TTQ_TC" class="KT_KQ_TTQ_TC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_GHICHU'/>" name="KT_GHICHU" class="KT_GHICHU" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    
                                    
                                </tr>
                            </s:iterator>
                        </table>  
    </div>      
</div>