<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('.D0').css({"text-align": "center"});               
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                $(".TD_CHECKBOX").css({"width": "4%"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH").css({"width": "15%"});
                $(".TD_TENTS").css({"width": "15%"});
                $(".TD_MAKH").css({"width": "7%"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "10%"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            $("#allCheck_dat").change(function () {
                $(".checkboxdat").prop('checked', $(this).prop("checked"));
            });
        </script>     
        
        <script>
    
        
        function initTable()
            {
                var table = document.getElementById("tableCN2301");
                var rowcount = table.rows.length;    
                rowcount = rowcount > max_row ? rowcount : max_row;                
                for (var i = 0; i < rowcount; i++)
                {                    
                    var matmp = getMabyNumber(i);//   
                    
                    if(matmp == 1)
                    {
                        $('input:checkbox[id='+i+']').attr('checked',true);
                    }
                }
            }
            
            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            
            function addRow() {
                
                var ht1 = screen.availHeight - 260;
                var wt1 = 1024;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 100;
                var ngay_bc = $("#ngay_bc_DATE").val();
                var khoa_ktgs = $("#khoa_ktgs").val();                
                var url = "UploadFileCn23.action?ngay_bc=" + ngay_bc ;
                popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                window.refreshData = function () {
                        //alert('aaaa');
                        $("#loadDatatmp").trigger("click");
                    };
                               
            }
            
            function hienthichitiet(ma) {
                
            var ht1 = screen.availHeight - 260;
            var wt1 = 1024;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 100;
            var ngay_bc = $("#ngay_bc_DATE").val();
            var khoa_ktgs = $("#khoa_ktgs").val();
            var url = "editCN23.action?soku=" + ma + "&ngay_bc=" + ngay_bc;
            popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
            
        </script>
        
        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }            
        </style>

    </head>
   <body style="font-family: ">
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                </br>
                <div id="divTitle">                    
                    DANH SÁCH MÓN VAY ĐƯỢC HỖ TRỢ LÃI SUẤT          
                </div>
                <s:hidden name="khoa_nhaptaycn"/>
<!--                <div id="divDonvitinh">
                    Đơn vị tính: Đồng
                </div>-->
                </br>
                <table class="editDelete" id="tablems04sss" style="width: 95%" align="center">
                        <tr>
                            <td width="35%" class="TD_THEMXOA"><input type="button" value="Upload file" onclick="addRow(1)"  
                                    style="color: #0000FF;"
                                    class="TEN_KH_ADD"/></td>
                            <td ></td>                                                                                                                                                                
                         </tr>
                </table>
                <table border="1" class="editDelete" id="tableCN2301" style="width: 95%"  align="center">
                    <tr height="50px">      
                        <th  class="TD_CHECKBOX">Thứ tự</th>  
                        <th  class="TD_MAKH">Mã khách hàng</th>    
                        <th  class="TD_TENTS">Tên KH</th>  
                        <th  class="TD_MAKH">Mã món vay</th>   
                        <th  class="TD_MAKH">Lãi suất cho vay</th> 
                        <th  class="TD_MAKH">Lãi suất hỗ trợ (x100)</th> 
                        <th  class="TD_MAKH">Ngày vay</th>                            
                        <th  class="TD_MAKH">Ngày đến hạn</th>  
                        <th  class="TD_MAKH">Mức vay</th>  
                        <th  class="TD_MAKH">Dư nợ</th>
                        <th  class="TD_MAKH">Ngày hết hạn HTLS</th>
                       
                    </tr>                                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                            <tr>     
                                <td  align="center" class="TD_CHECKBOX">    
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="THUTU" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" class="TEN_KH D0" onfocus="this.select();"
                                           readonly="true"/>
                                    </td>  
                                </td>  

                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D6" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH D0" readonly="true"/>
                                </td> 
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D7" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH" readonly="true"/>
                                </td>
                                   
                                    <td align = "center" class="TD_TEN_KH"> 
                                        <a href="javascript:hienthichitiet('<s:property value="D2"/>')" class="SOKU linkKh ">
                                            <s:property value='D2'/>
                                        </a>
                                    </td>  
                                
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D8" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH D0" readonly="true"/>
                                </td> 
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D14" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="TEN_KH D0" readonly="true"/>
                                </td> 
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D9" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="TEN_KH D0" readonly="true"/>
                                </td>
                               
                               <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D10" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH D0" readonly="true"/>
                                </td> 
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D11" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH number" readonly="true"/>
                                </td>
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D12" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" class="TEN_KH number" readonly="true"/>
                                </td>
                                <td align = "center" class="TD_MAKH">
                                    <input type="text" <s:if test="D8.equalsIgnoreCase('M')">style="color: red"</s:if> value="<s:property  value="D13" />" id="D3_<s:property  value="%{#rowstatus.index}" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH D0" readonly="true"/>
                                </td>
                                    
                            </tr>                                                                                                                                                                                   
                    </s:iterator>
                </table>                    
                
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>
    
    
</html>
