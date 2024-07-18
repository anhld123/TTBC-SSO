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
                $('.D00').css({"text-align": "left"});
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
                $(".TD_SOTIEN").css({"width": "200px"});
                $(".TEN_KH").css({"width": "100px"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>     

        <script>

            var max_row = 0;
            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    var matmp = getMabyNumber(i);//    
                    if (matmp === 1)
                    {
                        $('input:checkbox[id=' + i + ']').attr('checked', true);
                    }
                }
                autoEvaluate();
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
                width: auto;
            }
            #subTable {
                font-size: 16px;
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                border-spacing: 0;
                width: 150%;
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
            <div style="overflow:scroll; width: 98vw; height: 25vw">
                <table id="subTable" style="z-index: 1;">
                    <tr>      
                        <th rowspan="2" class="TD_CHECKBOX">TT</th>
                            <s:if test="!Grade.equalsIgnoreCase('3')">
                            <th rowspan="2" class="TD_SOTIEN">Tên PGD</th>    
                            </s:if>
                            <s:if test="Grade.equalsIgnoreCase('3')">
                            <th rowspan="2" class="TD_SOTIEN">Tên chi nhánh</th>    
                            </s:if>
                        <th colspan="3" class="hdtitle">Ủy ban nhân dân tỉnh quản lý</th>
                        <th colspan="3" class="hdtitle">Tổng LĐ lao động Việt Nam</th>
                        <th colspan="3" class="hdtitle">TW Đoàn TNCS Hồ Chí Minh</th>
                        <th colspan="3" class="hdtitle">TW Hội Liên hiệp Phụ nữ VN</th>
                        <th colspan="3" class="hdtitle">Hội Nông dân Việt Nam</th>
                        <th colspan="3" class="hdtitle">Hội Cựu chiến binh Việt Nam</th>
                        <th colspan="3" class="hdtitle">Liên minh Hợp tác xã Việt Nam</th>
                        <th colspan="3" class="hdtitle">Hội Người mù Việt Nam</th>
                        <!--<th colspan="3" class="hdtitle">NHCSXH</th>-->

                    </tr>  
                    <tr>
                        <th class="TEN_KH">Lãi thu được trong tháng</th>
                        <th class="TEN_KH">Số trích 10% trong tháng</th>
                        <th class="TEN_KH">Lũy kế số trích 10% từ đầu năm</th>
                        <th class="TEN_KH">Lãi thu được trong tháng</th>
                        <th class="TEN_KH">Số trích 10% trong tháng</th>
                        <th class="TEN_KH">Lũy kế số trích 10% từ đầu năm</th>
                        <th class="TEN_KH">Lãi thu được trong tháng</th>
                        <th class="TEN_KH">Số trích 10% trong tháng</th>
                        <th class="TEN_KH">Lũy kế số trích 10% từ đầu năm</th>
                        <th class="TEN_KH">Lãi thu được trong tháng</th>
                        <th class="TEN_KH">Số trích 10% trong tháng</th>
                        <th class="TEN_KH">Lũy kế số trích 10% từ đầu năm</th>
                        <th class="TEN_KH">Lãi thu được trong tháng</th>
                        <th class="TEN_KH">Số trích 10% trong tháng</th>
                        <th class="TEN_KH">Lũy kế số trích 10% từ đầu năm</th>
                        <th class="TEN_KH">Lãi thu được trong tháng</th>
                        <th class="TEN_KH">Số trích 10% trong tháng</th>
                        <th class="TEN_KH">Lũy kế số trích 10% từ đầu năm</th>
                        <th class="TEN_KH">Lãi thu được trong tháng</th>
                        <th class="TEN_KH">Số trích 10% trong tháng</th>
                        <th class="TEN_KH">Lũy kế số trích 10% từ đầu năm</th>
                        <th class="TEN_KH">Lãi thu được trong tháng</th>
                        <th class="TEN_KH">Số trích 10% trong tháng</th>
                        <th class="TEN_KH">Lũy kế số trích 10% từ đầu năm</th>
                        <!--                        <th class="hdtitle">Lãi thu được trong tháng</th>
                                                <th class="hdtitle">Số trích 10% trong tháng</th>
                                                <th class="hdtitle">Lũy kế số trích 10% từ đầu năm</th>       -->
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
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                    
                        <tr> 
                            <td class="D0"> <s:property value="%{#rowstatus.index + 1}" /> </td>
                            <td class="txtBody" >
                                <input type="text"   value="<s:property  value="TEN" />" style="text-align: right"
                                       class="D00 txtBody" readonly="true" id="TEN_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" 
                                       onfocus="this.select();" /> 
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" value="<s:property  value="TT_HIENTHI"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>

                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MACN" value="<s:property  value="MACN"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" value="<s:property  value="D4"/>"/>
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" value="<s:property  value="D5"/>"/>

                            </td>
                            <td class="txtBody" >
                                <input type="text"   value="<s:property  value="D6" />" style="text-align: right"
                                       class="number txtBody" id="D6<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D7" />" style="text-align: right"
                                       class="number txtBody" id="D7<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D8" />" style="text-align: right"
                                       class="number txtBody" id="D8<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D9" />" style="text-align: right"
                                       class="number txtBody" id="D9<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D10" />" style="text-align: right"
                                       class="number txtBody" id="D10<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D11" />" style="text-align: right"
                                       class="number txtBody" id="D11<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D12" />" style="text-align: right"
                                       class="number txtBody" id="D12<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D13" />" style="text-align: right"
                                       class="number txtBody" id="D13<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D14" />" style="text-align: right"
                                       class="number txtBody" id="D14<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D15" />" style="text-align: right"
                                       class="number txtBody" id="D15<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D16" />" style="text-align: right"
                                       class="number txtBody" id="D16<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D17" />" style="text-align: right"
                                       class="number txtBody" id="D17<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D18" />" style="text-align: right"
                                       class="number txtBody" id="D18<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D19" />" style="text-align: right"
                                       class="number txtBody" id="D19<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D20" />" style="text-align: right"
                                       class="number txtBody" id="D20<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D21" />" style="text-align: right"
                                       class="number txtBody" id="D21<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D22" />" style="text-align: right"
                                       class="number txtBody" id="D22<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D23" />" style="text-align: right"
                                       class="number txtBody" id="D23<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D24" />" style="text-align: right"
                                       class="number txtBody" id="D24<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D25" />" style="text-align: right"
                                       class="number txtBody" id="D25<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D26" />" style="text-align: right"
                                       class="number txtBody" id="D26<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D27" />" style="text-align: right"
                                       class="number txtBody" id="D27<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D28" />" style="text-align: right"
                                       class="number txtBody" id="D28<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <td class="txtBody" >
                                    <input type="text"   value="<s:property  value="D29" />" style="text-align: right"
                                       class="number txtBody" id="D29<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D29" 
                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                </td>
                                <!--                                <td class="txtBody" >
                                                                    <input type="text"   value="<s:property  value="D30" />" style="text-align: right"
                                                                       class="number txtBody"
                                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D30" 
                                                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                                                </td>
                                                                <td class="txtBody" >
                                                                    <input type="text"   value="<s:property  value="D31" />" style="text-align: right"
                                                                       class="number txtBody"
                                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D31" 
                                                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                                                </td>
                                                                <td class="txtBody" >
                                                                    <input type="text"   value="<s:property  value="D32" />" style="text-align: right"
                                                                       class="number txtBody"
                                                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D32" 
                                                                       onfocus="this.select();" <s:if test="!Grade.equalsIgnoreCase('1')"> readonly="true"</s:if>/> 
                                                                </td>-->

                            </tr>                                                                                                       
                    </s:iterator>
                    <tr>
                        <td style="text-align: center; background: #9ad717" ></td>
                        <td style="text-align: center; background: #9ad717;font-weight: bold" >TỔNG CỘNG</td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD6" id="SumD6" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD7" id="SumD7" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD8" id="SumD8" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD9" id="SumD9" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD10" id="SumD10" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD11" id="SumD11" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD12" id="SumD12" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD13" id="SumD13" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD14" id="SumD14" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD15" id="SumD15" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD16" id="SumD16" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD17" id="SumD17" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD18" id="SumD18" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD19" id="SumD19" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD20" id="SumD20" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD21" id="SumD21" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD22" id="SumD22" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD23" id="SumD23" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD24" id="SumD24" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD25" id="SumD25" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD26" id="SumD26" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD27" id="SumD27" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD28" id="SumD28" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>
                        <td style="background: #9ad717;font-weight: bold" >
                            <input type="text" value="0" name="SumD29" id="SumD29" class="number" readonly="readonly" style="background: #9ad717;font-weight: bold"/></td>

                    </tr>
                </table>                    
            </div>
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            function autoEvaluate() {
//                alert('vao doClick');
                var arrCot = [".D6", ".D7", ".D8", ".D9", ".D10", ".D11", ".D12", ".D13", ".D14", ".D15",
                    ".D16", ".D17", ".D18", ".D19", ".D20", ".D21", ".D22", ".D23", ".D24", ".D25",
                    ".D26", ".D27", ".D28", ".D29"]; //Luu cac cot cua du lieu can tinh toan
//                alert($(".number").size());
                var SumD6 = 0;
                var SumD7 = 0;
                var SumD8 = 0;
                var SumD9 = 0;
                var SumD10 = 0;
                var SumD11 = 0;
                var SumD12 = 0;
                var SumD13 = 0;
                var SumD14 = 0;
                var SumD15 = 0;
                var SumD16 = 0;
                var SumD17 = 0;
                var SumD18 = 0;
                var SumD19 = 0;
                var SumD20 = 0;
                var SumD21 = 0;
                var SumD22 = 0;
                var SumD23 = 0;
                var SumD24 = 0;
                var SumD25 = 0;
                var SumD26 = 0;
                var SumD27 = 0;
                var SumD28 = 0;
                var SumD29 = 0;
                for (var i = 0; i < 100; i++) {
                    let element;
                    element = document.getElementById("D6" + i);
                    if (element !== null) {
                        SumD6 = SumD6 + parseInt(document.getElementById("D6" + i).value.replaceAll(',', ''));
                        SumD7 = SumD7 + parseInt(document.getElementById("D7" + i).value.replaceAll(',', ''));
                        SumD8 = SumD8 + parseInt(document.getElementById("D8" + i).value.replaceAll(',', ''));
                        SumD9 = SumD9 + parseInt(document.getElementById("D9" + i).value.replaceAll(',', ''));
                        SumD10 = SumD10 + parseInt(document.getElementById("D10" + i).value.replaceAll(',', ''));
                        SumD11 = SumD11 + parseInt(document.getElementById("D11" + i).value.replaceAll(',', ''));
                        SumD12 = SumD12 + parseInt(document.getElementById("D12" + i).value.replaceAll(',', ''));
                        SumD13 = SumD13 + parseInt(document.getElementById("D13" + i).value.replaceAll(',', ''));
                        SumD14 = SumD14 + parseInt(document.getElementById("D14" + i).value.replaceAll(',', ''));
                        SumD15 = SumD15 + parseInt(document.getElementById("D15" + i).value.replaceAll(',', ''));
                        SumD16 = SumD16 + parseInt(document.getElementById("D16" + i).value.replaceAll(',', ''));
                        SumD17 = SumD17 + parseInt(document.getElementById("D17" + i).value.replaceAll(',', ''));
                        SumD18 = SumD18 + parseInt(document.getElementById("D18" + i).value.replaceAll(',', ''));
                        SumD19 = SumD19 + parseInt(document.getElementById("D19" + i).value.replaceAll(',', ''));
                        SumD20 = SumD20 + parseInt(document.getElementById("D20" + i).value.replaceAll(',', ''));
                        SumD21 = SumD21 + parseInt(document.getElementById("D21" + i).value.replaceAll(',', ''));
                        SumD22 = SumD22 + parseInt(document.getElementById("D22" + i).value.replaceAll(',', ''));
                        SumD23 = SumD23 + parseInt(document.getElementById("D23" + i).value.replaceAll(',', ''));
                        SumD24 = SumD24 + parseInt(document.getElementById("D24" + i).value.replaceAll(',', ''));
                        SumD25 = SumD25 + parseInt(document.getElementById("D25" + i).value.replaceAll(',', ''));
                        SumD26 = SumD26 + parseInt(document.getElementById("D26" + i).value.replaceAll(',', ''));
                        SumD27 = SumD27 + parseInt(document.getElementById("D27" + i).value.replaceAll(',', ''));
                        SumD28 = SumD28 + parseInt(document.getElementById("D28" + i).value.replaceAll(',', ''));
                        SumD29 = SumD29 + parseInt(document.getElementById("D29" + i).value.replaceAll(',', ''));
                    }
                }
//                totalD8
                document.getElementById("SumD6").value = SumD6;
                document.getElementById("SumD7").value = SumD7;
                document.getElementById("SumD8").value = SumD8;
                document.getElementById("SumD9").value = SumD9;
                document.getElementById("SumD10").value = SumD10;
                document.getElementById("SumD11").value = SumD11;
                document.getElementById("SumD12").value = SumD12;
                document.getElementById("SumD13").value = SumD13;
                document.getElementById("SumD14").value = SumD14;
                document.getElementById("SumD15").value = SumD15;
                document.getElementById("SumD16").value = SumD16;
                document.getElementById("SumD17").value = SumD17;
                document.getElementById("SumD18").value = SumD18;
                document.getElementById("SumD19").value = SumD19;
                document.getElementById("SumD20").value = SumD20;
                document.getElementById("SumD21").value = SumD21;
                document.getElementById("SumD22").value = SumD22;
                document.getElementById("SumD23").value = SumD23;
                document.getElementById("SumD24").value = SumD24;
                document.getElementById("SumD25").value = SumD25;
                document.getElementById("SumD26").value = SumD26;
                document.getElementById("SumD27").value = SumD27;
                document.getElementById("SumD28").value = SumD28;
                document.getElementById("SumD29").value = SumD29;
                $('.number').number(true, 0);
            }
            ;

            initTable();


        </script>
    </body>


</html>
