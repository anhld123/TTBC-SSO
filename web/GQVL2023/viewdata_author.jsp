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
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH123").css({"width": "110px"});
                $(".TD_TENTS").css({"width": "190px"});
                $(".TD_SOTK").css({"width": "105px"});
                $(".TD_MAKH").css({"width": "60px"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "45px"});
                $(".TD_BUTTON1").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>     

        <script>

            function hienthichitiet(soku) {
                var ht1 = screen.availHeight - 360;
                var wt1 = 500;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 100;
                var url = "getDetialTIDE.action?soku=" + soku;
                popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }

            var max_row = 0;
            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var matmp = getMabyNumber(i);//    
                    if (matmp == 1)
                    {
                        $('input:checkbox[id=' + i + ']').attr('checked', true);
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

        </script>

        <style>                                                

            .hdtitle1 {
                z-index: 6;
                font-style: italic;
                font-size: xx-small;
                width: 500px;
            }
            #subTable {
                font-size: 16px;
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                border-spacing: 0;
                width: 200%;
            }


            #subTable th, #subTable td {
                border: 1px solid gray;
                width: auto;
            }
            #subTable tr:nth-child(even){background-color: #f2f2f2;}

            #subTable tr:hover {background-color: #ddd;}
            .txtPublic{
                width: 85px;
            }
            .ui-datepicker-trigger{
                height: 100%;
            }
            .txtBody{
                text-align: center;
            }
            .txtBody > .ui-datepicker-trigger{
                display: none;
            }
            td.hdtitle {
                position: sticky;
                top: 0;
                z-index: 10;
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
                BÁO CÁO 10% SỐ TIỀN LÃI THU ĐƯỢC ĐỂ BỔ SUNG VÀO NGUỒN VỐN QUỸ QUỐC GIA VỀ VIỆC LÀM
                <BR>                    
            </div>

            <s:hidden name="khoa_nhaptaycn"/>

            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <div style="overflow:scroll; width: 99vw;">
                <!--<table border="1" class="editDelete" id="tablesms01" align="center">-->
                <table id="subTable" style="z-index: 1;">
                    <tr>                                            
                        <!--<th rowspan="2" class="hdtitle">STT</th>-->
                        <s:if test="!Grade.equalsIgnoreCase('3')">
                         <th rowspan="2" class="hdtitle">Tên PGD</th>    
                        </s:if>
                       <s:if test="Grade.equalsIgnoreCase('3')">
                         <th rowspan="2" class="hdtitle">Tên chi nhánh</th>    
                        </s:if>
                        <th rowspan="2" class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ QGVL</th>
                        <th rowspan="2" class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th rowspan="2" class="hdtitle">Tổng số lãi thu được</th>
                        <th rowspan="2" class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th colspan="3" class="hdtitle">UBND</th>
                        <th colspan="3" class="hdtitle">Hội Phụ nữ</th>
                        <th colspan="3" class="hdtitle">Hội Nông dân</th>
                        <th colspan="3" class="hdtitle">Hội Cứu chiến binh</th>
                        <th colspan="3" class="hdtitle">Đoàn thanh niên</th>
                        <th colspan="3" class="hdtitle">Liên minh Hợp tác xã</th>
                        <th colspan="3" class="hdtitle">Tổng Liên đoàn lao động</th>
                        <th colspan="3" class="hdtitle">Hội người mù</th>
                    </tr>  
                    <tr>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>
                        <th class="hdtitle">Tổng chỉ tiêu kế hoạch dư nợ Quỹ</th>
                        <th class="hdtitle">Tổng dư nợ Quỹ QGVL</th>
                        <th class="hdtitle">10% bổ sung vào nguồn vốn của Qũy</th>        
                    </tr>           
                    <tr>
                        <th class="hdtitle1">(1)</th> 
                        <th class="hdtitle1">(2)</th> 
                        <th class="hdtitle1">(3)</th> 
                        <th class="hdtitle1">(4)</th> 
                        <th class="hdtitle1">(5)</th> 
                        <th class="hdtitle1">(6)</th> 
                        <th class="hdtitle1">(7)</th> 
                        <th class="hdtitle1">(8)</th> 
                        <th class="hdtitle1">(9)</th> 
                        <th class="hdtitle1">(10)</th> 
                        <th class="hdtitle1">(11)</th> 
                        <th class="hdtitle1">(12)</th> 
                        <th class="hdtitle1">(13)</th> 
                        <th class="hdtitle1">(14)</th> 
                        <th class="hdtitle1">(15)</th> 
                        <th class="hdtitle1">(16)</th> 
                        <th class="hdtitle1">(17)</th> 
                        <th class="hdtitle1">(18)</th> 
                        <th class="hdtitle1">(19)</th> 
                        <th class="hdtitle1">(20)</th>
                        <th class="hdtitle1">(21)</th> 
                        <th class="hdtitle1">(22)</th> 
                        <th class="hdtitle1">(23)</th> 
                        <th class="hdtitle1">(24)</th>
                        <th class="hdtitle1">(25)</th> 
                        <th class="hdtitle1">(26)</th>
                        <th class="hdtitle1">(27)</th> 
                        <th class="hdtitle1">(28)</th>
                        <th class="hdtitle1">(29)</th> 
                        <!--<th class="hdtitle1">(30)</th>-->
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                    
                        <tr> 
      
                           <td class="txtBody" >
                                <input type="text"   value="<s:property  value="TEN" />" style="text-align: right"
                                       class="D0 txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                       onfocus="this.select();" /> 
                            </td>
                           <s:if test="Grade.equalsIgnoreCase('1')">
                            <td class="txtBody">
                                <input type="text"   value="<s:property  value="D3" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                       onfocus="this.select();" /> 
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>"/>

                            </td>
                            <td class="txtBody" >
                                <input type="text"   value="<s:property  value="D4" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D5" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D6" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D7" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D8" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D9" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D10" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D11" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D12" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D13" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D14" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D15" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D16" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D17" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D18" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D19" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D20" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D21" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D22" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" 
                                       onfocus="this.select();" /> 
                            </td>
                           
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D23" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D24" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D25" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D26" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D27" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D28" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D29" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D29" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D30" />" style="text-align: right"
                                       class="number txtBody"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" 
                                       onfocus="this.select();" /> 
                            </td>  
                          </s:if>
                            <s:if test="!Grade.equalsIgnoreCase('1')">
                            <td class="txtBody">
                                <input type="text"   value="<s:property  value="D3" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" 
                                       onfocus="this.select();" /> 
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>"/>

                            </td>
                            <td class="txtBody" >
                                <input type="text"   value="<s:property  value="D4" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D5" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D6" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D7" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D8" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D9" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D10" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D11" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D12" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D13" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D14" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D15" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D16" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D17" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D18" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D19" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D20" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D21" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D22" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" 
                                       onfocus="this.select();" /> 
                            </td>
                           
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D23" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D24" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D25" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D26" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D27" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D28" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D29" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D29" 
                                       onfocus="this.select();" /> 
                            </td>
                            <td  class="txtBody" >
                                <input type="text"   value="<s:property  value="D30" />" style="text-align: right"
                                       class="number txtBody" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" 
                                       onfocus="this.select();" /> 
                            </td>  
                          </s:if>
                        </tr>                                                                                                       
                    </s:iterator>

                </table>                    
            </div>
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initTable();
        </script>
    </body>


</html>
