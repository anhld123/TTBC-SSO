<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="css/bcqt.css" />
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
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
                $('.number2').number(true, 2);
                $(".TD_STT").css({"width": "20px"});
                $(".TD_MAKH").css({"width": "30px"});
                $(".TD_TOTIEN").css({"width": "70px"});
                $(".TD_NGAY").css({"width": "55px"});
                $(".TD_TENKH").css({"width": "330px"});
                $(".TD_SOKU").css({"width": "60px"});
                $(".TD_NGAY").css({"width": "40px"});
                $(".TD_CHITIEU").css({"width": "300px"});
                $(".TD_GHICHU").css({"width": "150px"});
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
                var table = document.getElementById("tblTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
//                for (var i = 0; i < rowcount; i++)
//                {                    
//                    var matmp1 = getMabyNumber1(i);//                       
//                    if (matmp1 === 1)
//                    {
//                        $('input:checkbox[id=idc11' + i + ']').attr('checked', true);
//                    }
//                }
            }

            function sumColumn()
            {
                try {
                    var table = document.getElementById("tblTable");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var value = 0;
                    for (var i = 0; i < rowcount; i++)
                    {        
                        value = (getValue('D1_' + i)*0.02).toFixed(2);
                        setValue('D2_' + i, value);
                    }                        
                } 
                catch (e)
                {
                    alert(e);
                    console.log(e.toString());
                }
                $('.number2').number(true, 2);
            }

            function getValue(id)
            {
                var value = 0;
                try 
                {
                    value = document.getElementById(id).value;
                    value = value.replace(/,/g, "");
                    if (value === '-1')
                        value = 0.0;
                } 
                catch (e)
                {
                    value = 0.0;
                }
                return parseFloat(value);
            }
            
            function setValue(id, value)
            {
                try 
                {
                    document.getElementById(id).value = value;
                } 
                catch (e)
                {
                }
            }
        </script>

        <link href="css/css/style.css" rel="stylesheet" type="text/css"/>
        <style>
            .CLS-BOLD{
                font-weight: bold;
            }
            .fix_th {
                position: -webkit-sticky;                
                top: -1px;
                z-index: 1;
                background: #fff;
            }	
        </style>
    </head>
    <body style="font-family: ">
        <s:form id="id_sv_NQ11_GKH" action="SAVE_NQ11_GKH" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                
            </br>
            <div id="divTitle">
                <s:property  value="title" />                
                <div id="luu_thanhcong_del"></div>
            </div>
            <s:hidden name="khoa_nghiquyet11cp"/>            
            <div id="divDonvitinh">
                Đơn vị tính: Triệu đồng              &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </div>
            <div class="cls-over">
            <div id="scrolling_table_1" class="editDelete" style="width: 98%; max-height:80vh">
                <table id="tblTable">
                    <tr >                                                                                  
                        <th class="TD_STT">STT</th>                           
                        <th class="TD_TENKH">Đơn vị</th>   
                        <th class="TD_TENKH">Dự kiến dư nợ cho vay được hỗ trợ lãi suất</th>                              
                        <th class="TD_SOKU">Nhu cầu hỗ trợ lãi suất </th>                                                                              
                    </tr>         
                        
                    <tr style="font-style: italic;">                        
                        <td style="text-align: center">(1)</td>                            
                        <td style="text-align: center">(2)</td>
                        <td style="text-align: center">(3)</td>
                        <td style="text-align: center">(4)=(3)*2%</td>                        
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                        <tr>  
                            <td align = "right" class="TD_STT" >
                                <input type="text"   value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" 
                                       class=" TEN_KH D0" 
                                       onfocus="this.select();"
                                       readonly="true"/>   
                                <input type="hidden" value="<s:property  value="MA" />" id="id_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA"/> 
                            </td>  
                            <td align = "right" class="TD_TENKH" >
                                <input type="text"   value="<s:property  value="TEN" />" style="color: green;" title="<s:property  value="TEN" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                       class=" TEN_KH " 
                                       onfocus="this.select();" readonly="true"/> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D1" />"  id="D1_<s:property  value="%{#rowstatus.index}" />"                                    
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       class=" TEN_KH  number"                                       
                                       onblur="if (this.value === '') {this.value = 0;}; sumColumn();"
                                       /> 
                            </td>
                            <td align = "right" class="TD_SOKU" >
                                <input type="text"   value="<s:property  value="D2" />" id="D2_<s:property  value="%{#rowstatus.index}" />"        
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" 
                                       class=" TEN_KH  number2" 
                                       readonly
                                       style="background: #C0C0C0 !important;"
                                       /> 
                            </td>                                  
                        </tr>                                                                                                                                                                                   
                    </s:iterator>
                </table>        
            </div>
            </div>

            <sj:submit id="NQ11_GKH_save" name="NQ11_GKH_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
           

        <div id="luu_thanhcong"></div>

        <script>
            initTable();
        </script>
    </body>
</html>

