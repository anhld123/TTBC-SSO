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
                                <th rowspan="2" colspan="2">Ban hành văn bản quản lý, chỉ đạo về công tác KNTC</th>
                                <th rowspan="2" colspan="2">Tập huấn, tuyên truyền, giáo dục pháp luật về KNTC cho cán bộ, công chức, viên chức, nhân dân</th>
                                <th colspan="7">Thanh tra kiểm tra trách nhiệm</th>
                                <th colspan="5">Kiểm tra việc thực hiện kết luận thanh tra trách nhiệm, quyết định xử lý</th>
                                <th rowspan="4">Ghi chú</th>
                                
                                
                            </tr>
                            <tr class="tbhead">
                                <th colspan="2">Thực hiện pháp luật về KNTC</th>
                                <th rowspan="3">Số đơn vị vi phạm</th>
                                <th colspan="4">Kiến nghị xử lý</th>
                                <th rowspan="3">Tổng số KLTT và QĐ xử lý đã kiểm tra</th>
                                <th colspan="4">Kết quả kiểm tra</th>
                            </tr>
                            <tr class="tbhead">
                                
                                <th rowspan="2">Số văn bản ban hành mới</th>
                                <th rowspan="2">Số văn bản ban được sửa đổi bổ sung</th>
                                <th colspan="2">Pháp luật về KNTC</th>
                                
                                <th rowspan="2">Số cuộc</th>
                                <th rowspan="2">Số đơn vị</th>
                                <th colspan="2">Kiểm điểm, rút kinh nghiệm</th>
                                <th colspan="2">Hành chính</th>
                                <th colspan="2">Đã kiểm điểm, rút kinh nghiệm</th>
                                <th colspan="2">Đã xử lý hành chính</th>
                            </tr>
                            <tr class="tbhead"> 
                                <th>Lớp</th>
                                <th>Người</th>
                                <th>Tổ chức1</th>
                                <th>Cá nhân</th>
                                <th>Tổ chức2</th>
                                <th>Cá nhân</th>
                                <th>Tổ chức3</th>
                                <th>Cá nhân</th>
                                <th>Tổ chức4</th>
                                <th>Cá nhân</th>
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
                                
                                
                            </tr>
                            
                            
                            <s:iterator value="ktnb12ModelList">
                                <tr class="cscontent">
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_DV'/>" name="KT_DV" class="KT_DV" onfocus="this.select()" onblur="autoEvaluate()" readonly="readonly"/></td>
                                    
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_VB_NEW'/>" name="KT_SO_VB_NEW" class="KT_SO_VB_NEW number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_VB_BS'/>" name="KT_SO_VB_BS" class="KT_SO_VB_BS number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_LOP_TH'/>" name="KT_SO_LOP_TH" class="KT_SO_LOP_TH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_NG_TH'/>" name="KT_SO_NG_TH" class="KT_SO_NG_TH number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CUOC'/>" name="KT_SO_CUOC" class="KT_SO_CUOC number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_DV'/>" name="KT_SO_DV" class="KT_SO_DV number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_DV_VP'/>" name="KT_SO_DV_VP" class="KT_SO_DV_VP number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TOCHUC1'/>" name="KT_SO_TOCHUC1" class="KT_SO_TOCHUC1 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CANHAN1'/>" name="KT_SO_CANHAN1" class="KT_SO_CANHAN1 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TOCHUC2'/>" name="KT_SO_TOCHUC2" class="KT_SO_TOCHUC2 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CANHAN2'/>" name="KT_SO_CANHAN2" class="KT_SO_CANHAN2 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_TONG_KLTT'/>" name="KT_TONG_KLTT" class="KT_TONG_KLTT number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TOCHUC3'/>" name="KT_SO_TOCHUC3" class="KT_SO_TOCHUC3 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CANHAN3'/>" name="KT_SO_CANHAN3" class="KT_SO_CANHAN3 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_TOCHUC4'/>" name="KT_SO_TOCHUC4" class="KT_SO_TOCHUC4 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_SO_CANHAN4'/>" name="KT_SO_CANHAN4" class="KT_SO_CANHAN4 number" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>
                                    <td class="<s:property value='KT_FONTWEIGHT'/>"><input type="text" value="<s:property value='KT_GHICHU'/>" name="KT_GHICHU" class="KT_GHICHU" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate(this)" readonly="readonly"/></td>

                                    
                                </tr>
                            </s:iterator>
                        </table> 
    </div>      
</div>