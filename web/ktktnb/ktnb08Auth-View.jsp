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
                                <th rowspan="4">Đơn vị</th>
                                <th colspan="8">Tiếp thường xuyên</th>                 
                                <th colspan="8">Tiếp định kỳ và đột xuất của Lãnh đạo</th>
                                <th colspan="10">Nội dung tiếp công dân/khách hàng (số vụ việc)</th>
                                <th colspan="4">Kết quả tiếp công dân/khách hàng (số vụ việc)</th>
                                <th rowspan="4">Ghi chú</th>
                                
                                
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="3">Lượt</th>
                                <th rowspan="3">Người</th>
                                <th colspan="2">Vụ việc</th>
                                <th colspan="4">Đoàn người</th>
                                <th rowspan="3">Lượt</th>
                                <th rowspan="3">Người</th>
                                <th colspan="2">Vụ việc</th>
                                <th colspan="4">Đoàn người</th>
                                <th colspan="6">Khiếu nại</th>
                                <th colspan="3">Tố cáo</th>
                                <th rowspan="3">Phản ánh, kiến nghị khác</th>
                                <th rowspan="3">Chưa được giải quyết</th>
                                <th colspan="3">Đã được giải quyết</th>
                            </tr>
                            <tr class="tbhead">
                                <th rowspan="2">Cũ</th>
                                <th rowspan="2">Mới phát sinh</th>
                                <th rowspan="2">Số đoàn</th>
                                <th rowspan="2">Người</th>
                                <th colspan="2">Vụ việc</th>
                                <th rowspan="2">Cũ</th>
                                <th rowspan="2">Mới phát sinh</th>
                                <th rowspan="2">Số đoàn</th>
                                <th rowspan="2">Người</th>
                                <th colspan="2">Vụ việc</th>
                                <th colspan="4">Lĩnh vực hành chính</th>
                                <th rowspan="2">Lĩnh vực tư pháp</th>
                                <th rowspan="2">Lĩnh vực CT, VH, XH khác</th>
                                <th rowspan="2">Lĩnh vực hành chính</th>
                                <th rowspan="2">Lĩnh vực tư pháp</th>
                                <th rowspan="2">Tham nhũng</th>
                                <th rowspan="2">Chưa có QĐ giải quyết</th>
                                <th rowspan="2">Đã có QĐ giải quyết (lần 1, lần 2, cuối cùng)</th>
                                <th rowspan="2">Đã có bản án của Tòa</th>
                            </tr>
                            <tr class="tbhead"> 
                                <th>Cũ</th>
                                <th>Mới phát sinh</th>
                                <th>Cũ</th>
                                <th>Mới phát sinh</th>
                                <th>Về tranh chấp, đòi đất cũ, đền bù, giải tỏa,...</th>
                                <th>Về chính sách</th>
                                <th>Về nhà, tài sản</th>
                                <th>Về chế độ CC, VC</th>
                            </tr>
                            <tr class="tbhead">
                                <th>MS</th>
                                <th>(1)</th>
                                <th>(2)</th>
                                <th>(3)</th>
                                <th>(4)</th>
                                <th>(5)</th>
                                <th>(6)</th>
                                <th>(7)</th>                                
                                <th>(8)</th>
                                <th>(9)</th>
                                <th>(10)</th>
                                <th>(11)</th>
                                <th>(12)</th>                                
                                <th>(13)</th>
                                <th>(14)</th>
                                <th>(15)</th>
                                <th>(16)</th>
                                <th>(17)</th>
                                <th>(18)</th>
                                <th>(19)</th>
                                <th>(20)</th>
                                <th>(21)</th>
                                <th>(22)</th>
                                <th>(23)</th>
                                <th>(24)</th>
                                <th>(25)</th>
                                <th>(26)</th>
                                <th>(27)</th>
                                <th>(28)</th>
                                <th>(29)</th>
                                <th>(30)</th>
                                <th>(31)</th>
                                
                                
                            </tr>
                            
                            
                            <s:iterator value="ktnb08ModelList">
                                <tr class="cscontent">                                 
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DV'/>" name="KT_DV" class="KT_DV" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>

                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_L'/>" name="KT_TX_L" class="KT_TX_L number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_N'/>" name="KT_TX_N" class="KT_TX_N number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_VV_C'/>" name="KT_TX_VV_C" class="KT_TX_VV_C number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_VV_M'/>" name="KT_TX_VV_M" class="KT_TX_VV_M number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_DDN_SD'/>" name="KT_TX_DDN_SD" class="KT_TX_DDN_SD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_DDN_N'/>" name="KT_TX_DDN_N" class="KT_TX_DDN_N number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_DDN_VV_C'/>" name="KT_TX_DDN_VV_C" class="KT_TX_DDN_VV_C number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TX_DDN_VV_M'/>" name="KT_TX_DDN_VV_M" class="KT_TX_DDN_VV_M number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly" /></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_L'/>" name="KT_DK_L" class="KT_DK_L number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_N'/>" name="KT_DK_N" class="KT_DK_N number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_VV_C'/>" name="KT_DK_VV_C" class="KT_DK_VV_C number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_VV_M'/>" name="KT_DK_VV_M" class="KT_DK_VV_M number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_DDN_SD'/>" name="KT_DK_DDN_SD" class="KT_DK_DDN_SD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_DDN_N'/>" name="KT_DK_DDN_N" class="KT_DK_DDN_N number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_DDN_VV_C'/>" name="KT_DK_DDN_VV_C" class="KT_DK_DDN_VV_C number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DK_DDN_VV_M'/>" name="KT_DK_DDN_VV_M" class="KT_DK_DDN_VV_M number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_HC_TC'/>" name="KT_ND_KN_HC_TC" class="KT_ND_KN_HC_TC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_HC_CS'/>" name="KT_ND_KN_HC_CS" class="KT_ND_KN_HC_CS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_HC_NTS'/>" name="KT_ND_KN_HC_NTS" class="KT_ND_KN_HC_NTS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_HC_CD'/>" name="KT_ND_KN_HC_CD" class="KT_ND_KN_HC_CD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_TP'/>" name="KT_ND_KN_TP" class="KT_ND_KN_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KN_CT'/>" name="KT_ND_KN_CT" class="KT_ND_KN_CT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_TC_HC'/>" name="KT_ND_TC_HC" class="KT_ND_TC_HC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_TC_TP'/>" name="KT_ND_TC_TP" class="KT_ND_TC_TP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_TC_TN'/>" name="KT_ND_TC_TN" class="KT_ND_TC_TN number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_ND_KHAC'/>" name="KT_ND_KHAC" class="KT_ND_KHAC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_CGQ'/>" name="KT_KQ_CGQ" class="KT_KQ_CGQ number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_GQ_CCQD'/>" name="KT_KQ_GQ_CCQD" class="KT_KQ_GQ_CCQD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_GQ_DCQD'/>" name="KT_KQ_GQ_DCQD" class="KT_KQ_GQ_DCQD number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_KQ_GQ_DCBA'/>" name="KT_KQ_GQ_DCBA" class="KT_KQ_GQ_DCBA number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_GHICHU'/>" name="KT_GHICHU" class="KT_GHICHU" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    
                                    </tr>
                            </s:iterator>
                        </table> 
    </div>      
</div>