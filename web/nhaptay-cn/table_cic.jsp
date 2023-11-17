<%-- 
    Document   : 
    Created on : 
    Author     :
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <style>
            #subTable {
                font-size: 16px;
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                border-spacing: 0;
                width: 99%;
            }
            #subTable th{
                background-color: #ddd;
                color: #0000FF;
            }

            #subTable th, #subTable td {
                border: 1px solid gray;
                height: 20px;
            }

            #subTable tr:nth-child(even){background-color: #f2f2f2;}

            #subTable tr:hover {background-color: #ddd;}

            #subTableSum {
                font-size: 16px;
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                border-spacing: 0;
                width: 99%;
            }
            #subTableSum th{
                background-color: #F5AC9C;
                color: #0e6647d;
            }

            #subTableSum th, #subTableSum td {
                border: 1px solid gray;
                height: 20px;
                background-color: #F5AC9C;
            }

            #subTableSum tr:nth-child(even){background-color: #F5AC9C;}

            #subTableSum tr:hover {background-color: #F5AC9C;}


            .txtPublic{
                width: 85px;
            }
            .ui-datepicker-trigger{
                height: 100%;
            }
            .txtBody{
                text-align: center;
                width: 100px;
            }
            .txtBody > .ui-datepicker-trigger{
                display: none;
            }
            td.hdtitle {
                position: sticky;
                top: 0;
                z-index: 10;
            }

            @-webkit-keyframes my {
                0% { color: #F34621; } 
                50% { color: #fff;  } 
                100% { color: #F34621;  } 
            }
            @-moz-keyframes my { 
                0% { color: #F34621;  } 
                50% { color: #fff;  }
                100% { color: #F34621;  } 
            }
            @-o-keyframes my { 
                0% { color: #F34621; } 
                50% { color: #fff; } 
                100% { color: #F34621;  } 
            }
            @keyframes my { 
                0% { color: #F34621;  } 
                50% { color: #fff;  }
                100% { color: #F34621;  } 
            } 
            .test123 {
                /*background:#3d3d3d;*/
                font-size:18px;
                height:  15px;
                font-weight:bold;
                color: red;
                -webkit-animation: my 700ms infinite;
                -moz-animation: my 700ms infinite; 
                -o-animation: my 700ms infinite; 
                animation: my 700ms infinite;
            }
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('td.number').css({"text-align": "right"});
                $('td.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('.number').number(true, 0);
                //            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_SOKU").css({"width": "80px"});
                $(".TD_TENKH123").css({"width": "100px"});
                $(".TD_TENTS").css({"width": "130px"});
                $(".TD_SOTK").css({"width": "70px"});
                $(".TD_MAKH").css({"width": "30px"});
                $(".TD_THOIGIAN").css({"width": "55px"});
                $(".TD_MAPGD").css({"width": "50px"});
                $(".TD_BUTTON1").css({"width": "40px"});
                $(".TD_SOTIEN").css({"width": "100px"});
                $(".TEN_KH").css({"width": "80px"});
                $(".TD_CHECK").css({"width": "60px"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    //cho combox 1
                    var matmp1 = getMabyNumber1(i); //                       
                    if (matmp1 == 1)
                    {
                        $('input:checkbox[id=check1' + i + ']').attr('checked', true);
                    }
                }
            }

            function getMabyNumber1(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id9_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

            function initTable()
            {
                var table = document.getElementById("subTable");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    //cho combox 14
                    var matmp14 = getMabyNumber14(i); //                       
                    if (matmp14 == 1)
                    {
                        $('input:checkbox[id=idc14' + i + ']').attr('checked', true);
                    }
                    //cho combox 15
                    var matmp15 = getMabyNumber15(i); //                       
                    if (matmp15 == 1)
                    {
                        $('input:checkbox[id=idc15' + i + ']').attr('checked', true);
                    }
                    //cho combox 16
                    var matmp16 = getMabyNumber16(i); //                       
                    if (matmp16 == 1)
                    {
                        $('input:checkbox[id=idc16' + i + ']').attr('checked', true);
                    }

                    //cho combox 19
                    var matmp19 = getMabyNumber19(i); //                       
                    if (matmp19 == 1)
                    {
                        $('input:checkbox[id=idc19' + i + ']').attr('checked', true);
                    }

                    //cho combox 20
                    var matmp20 = getMabyNumber20(i); //                       
                    if (matmp20 == 1)
                    {
                        $('input:checkbox[id=idc20' + i + ']').attr('checked', true);
                    }

                    //cho combox 21
                    var matmp21 = getMabyNumber21(i); //                       
                    if (matmp21 == 1)
                    {
                        $('input:checkbox[id=idc21' + i + ']').attr('checked', true);
                    }

                    //-------------------------------------------------------------------------------
                    //cho combox 28
                    var matmp28 = getMabyNumber28(i); //                       
                    if (matmp28 == 1)
                    {
                        $('input:checkbox[id=idc28' + i + ']').attr('checked', true);
                    }
                    //cho combox 21
                    var matmp21 = getMabyNumber21(i); //                       
                    if (matmp21 == 1)
                    {
                        $('input:checkbox[id=idc21' + i + ']').attr('checked', true);
                    }
                    //cho combox 29
                    var matmp29 = getMabyNumber29(i); //                       
                    if (matmp29 == 1)
                    {
                        $('input:checkbox[id=idc29' + i + ']').attr('checked', true);
                    }
                    //cho combox 30
                    var matmp30 = getMabyNumber30(i); //                       
                    if (matmp30 == 1)
                    {
                        $('input:checkbox[id=idc30' + i + ']').attr('checked', true);
                    }
                    //cho combox 31
                    var matmp31 = getMabyNumber31(i); //                       
                    if (matmp31 == 1)
                    {
                        $('input:checkbox[id=idc31' + i + ']').attr('checked', true);
                    }
                    //cho combox 33
                    var matmp33 = getMabyNumber33(i); //                       
                    if (matmp33 == 1)
                    {
                        $('input:checkbox[id=idc33' + i + ']').attr('checked', true);
                    }
                }
            }

            function getMabyNumber14(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id14_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

            function getMabyNumber15(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id15_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            function getMabyNumber16(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id16_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

            function getMabyNumber19(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id19_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

            function getMabyNumber20(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id20_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

            function getMabyNumber21(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id21_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }

            function getMabyNumber28(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id28_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            function getMabyNumber29(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id29_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            function getMabyNumber30(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id30_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            function getMabyNumber31(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id31_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            function getMabyNumber33(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id33_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
        </script>   
        <!--</head>-->
    <body>
        <s:form id="id_sv_CIC_001" action="SAVE_CIC_001" theme="simple">
            <div style="overflow:scroll; width: 99vw; padding: 5">     
                <a style="color: #003eff; font-weight: 550; font-size: 18px">
                    BẢNG RÀ SOÁT THÔNG TIN CHUNG CỦA KHÁCH HÀNG  <font style="color: red"> <s:property value="messagePage"/></font>
                    <!--<font style="color: red"> <s:property value="messageErr"/> </font>-->
                    </br> 
                    <font style="red"  class="test123"> <s:property value="messageErr"/></font>
                    <!--<br>-->
                </a>

                <input type="hidden"  id="rasoat_dc" name="rasoat_dc"
                       value="<s:property  value="rasoat_dc"/>"/>

                <table id="subTableSum" style="z-index: 10; width: 50%">
                    <tr style="height:25px;">

                        <th rowspan="1" style="width: 80px">Mã PGD</th>  
                        <th rowspan="1" style="width: 190px">Tên PGD</th>    
                        <th rowspan="1" style="width: 180px">Tổng số khách hàng cần rà soát</th>                             
                        <th rowspan="1" style="width: 180px">Số khách hàng chưa rà soát hồ sơ vay vốn</th> 
                        <th rowspan="1" style="width: 180px">Số khách hàng chưa đối chiếu với khách hàng</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                             
                        <tr>                               
                            <td class="" style="width: 80px"><s:property value="MAPGD"/></td>
                            <td class="" style="width: 190px"><s:property value="TEN"/></td>
                            <td class="number" style="width: 180px"><s:property value="D1"/></td>

                            <td class="number" style="width: 180px"><s:property value="D2"/></td>
                            <td class="number" style="width: 180px"><s:property value="D6"/></td>
                        </tr>                                                                                                                                                                                   
                    </s:iterator>
                </table>
                </br>
                <table id="subTable" style="z-index: 10;">
                    <thead>
                        <tr >      
                            <th rowspan="3" style="width: 30px">STT</th>   
                            <th rowspan="3" class="TD_CHECK">Mã khách hàng</th>  
                            <th colspan="5" class="TD_SOTIEN">Thông tin trên Intellect</th>  
                            <th colspan="2" class="TD_CHECK">CIC</th>  
                                <s:if test="rasoat_dc.equalsIgnoreCase('1')">
                                <th colspan="5" class="TD_TENTS">Kết quả đối chiếu thông tin trên Intellect với hồ sơ vay vốn</th>  
                                </s:if>  
                                <s:else>
                                <th colspan="4" class="TD_TENTS">Kết quả đối chiếu với khách hàng</th>
                                </s:else>   
                            <th rowspan="3" class="TD_CHECK" >Đã điều chỉnh trên Intellect</th>      
                        </tr>         
                        <tr >
                            <th rowspan="2" class="TD_TENTS">Họ tên</th>
                            <th rowspan="2" class="TD_CHECK">Ngày, tháng, năm sinh</th>
                            <th colspan="3" class="TD_CHECK">Thông tin CMND/CCCD</th>
                            <th rowspan="2" class="TD_TENTS">Họ tên</th>
                            <th rowspan="2" class="TD_CHECK">CCCD</th>
                                <s:if test="rasoat_dc.equalsIgnoreCase('1')">
                                <th rowspan="2" class="TD_CHECK">Thông tin khớp đúng với hồ sơ</th>
                                </s:if>
                                <s:if test="rasoat_dc.equalsIgnoreCase('1')">
                                <th colspan="3" >Thông tin sai sót/chưa cập nhật</th>
                                </s:if>
                                <s:else>
                                <th colspan="3" >Xác nhận có thay đổi thông tin</th>
                                </s:else>    
                                <s:if test="rasoat_dc.equalsIgnoreCase('1')">
                                <th rowspan="2" class="TD_CHECK">Hồ sơ vay vốn thiếu thông tin</th>
                                </s:if>
                                <s:else>
                                <th rowspan="2" class="TD_CHECK">Chưa làm CCCD</th>
                                </s:else>  


                        </tr>
                        <tr >
                            <th class="TD_CHECK">Số</th>
                            <th class="TD_CHECK">Ngày cấp</th>
                            <th class="TD_TENTS">Nơi cấp</th>

                            <th class="TD_CHECK">Họ tên</th>
                            <th class="TD_CHECK">CCCD</th>
                            <th class="TD_CHECK">Ngày, tháng, năm sinh</th>
                        </tr>

                        <tr class="txtBody">
                            <th style="color: #000; font: italic; font-size: xx-small;">(1)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(2)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(3)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(4)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(5)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(6)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(7)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(8)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(9)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(10)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(11)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(12)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(13)</th>
                            <th style="color: #000; font: italic; font-size: xx-small;">(14)</th>
                                <s:if test="rasoat_dc.equalsIgnoreCase('1')">
                                <th style="color: #000; font: italic; font-size: xx-small;">(15)</th>
                                </s:if>



                        </tr>
                    </thead>

                    <s:iterator value="#attr.custCIC" var="modelView" status="rowstatus">   
                        <tr>
                            <td class="D0">
                                <s:property value="%{#rowstatus.index + 1}" /> 
                            </td>
                            <td class=" D0"><s:property value="customerCode" />
                                <input type="hidden" value="<s:property  value="profileCorrectConfirmFlag" />" name="custCIC[<s:property  value="%{#rowstatus.index}" />].profileCorrectConfirmFlag"  
                                       id="id14_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="profileCorrectConfirmFlag"/>"/>
                                <input type="hidden" value="<s:property  value="wrongFullNameConfirmFlag" />" name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongFullNameConfirmFlag" 
                                       id="id15_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="wrongFullNameConfirmFlag"/>"/>
                                <input type="hidden" value="<s:property  value="wrongIdNoConfirmFlag" />"  name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongIdNoConfirmFlag" 
                                       id="id16_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="wrongIdNoConfirmFlag"/>"/>

                                <input type="hidden" value="<s:property  value="wrongBirthdayConfirmFlag" />" name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongBirthdayConfirmFlag" 
                                       id="id19_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="wrongBirthdayConfirmFlag"/>"/>
                                <input type="hidden" value="<s:property  value="profileMissingConfirmFlag" />" name="custCIC[<s:property  value="%{#rowstatus.index}" />].profileMissingConfirmFlag" 
                                       id="id20_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="profileMissingConfirmFlag"/>"/>
                                <input type="hidden" value="<s:property  value="status" />"  name="custCIC[<s:property  value="%{#rowstatus.index}" />].status" 
                                       id="id21_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="status"/>"/>

                                <input type="hidden" value="<s:property  value="customerWrongFullNameConfirmFlag" />"  id="id28_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="customerWrongFullNameConfirmFlag"/>"/>
                                <input type="hidden" value="<s:property  value="customerWrongIdNoConfirmFlag" />"  id="id29_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="customerWrongIdNoConfirmFlag"/>"/>
                                <input type="hidden" value="<s:property  value="customerWrongBirthdayConfirmFlag" />"  id="id30_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="customerWrongBirthdayConfirmFlag"/>"/>
                                <input type="hidden" value="<s:property  value="customerNotIdConfirmFlag" />"  id="id31_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="customerNotIdConfirmFlag"/>"/>
                                <input type="hidden" value="<s:property  value="customerReviewStatus" />"  id="id33_<s:property  value="%{#rowstatus.index}" />" 
                                       value="<s:property  value="customerReviewStatus"/>"/>

                                <input type="hidden" value="<s:property  value="mainPos"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].mainPos" value="<s:property  value="mainPos"/>"/>
                                <input type="hidden" value="<s:property  value="posCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].posCode" value="<s:property  value="posCode"/>"/>
                                <input type="hidden" value="<s:property  value="customerCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].customerCode" value="<s:property  value="customerCode"/>"/>
                                <input type="hidden" value="<s:property  value="cicCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].cicCode" value="<s:property  value="cicCode"/>" id="kye1<s:property  value="%{#rowstatus.index}" />"/>
                                <input type="hidden" value="<s:property  value="customerName"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].customerName" value="<s:property  value="customerName"/>"/>
                                <input type="hidden" value="<s:property  value="isValidCustomerName"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].isValidCustomerName" value="<s:property  value="isValidCustomerName"/>"/>
                                <input type="hidden" value="<s:property  value="birthDay"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].birthDay" value="<s:property  value="birthDay"/>"/>
                                <input type="hidden" value="<s:property  value="idNo"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].idNo" value="<s:property  value="idNo"/>"/>
                                <input type="hidden" value="<s:property  value="newIdNo"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].newIdNo" value="<s:property  value="newIdNo"/>"/>
                                <input type="hidden" value="<s:property  value="isValidNewIdNo"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].isValidNewIdNo" value="<s:property  value="isValidNewIdNo"/>"/>
                                <input type="hidden" value="<s:property  value="oldIdNo"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].oldIdNo" value="<s:property  value="oldIdNo"/>"/>
                                <input type="hidden" value="<s:property  value="isValidOldIdNo"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].isValidOldIdNo" value="<s:property  value="isValidOldIdNo"/>"/>
                                <input type="hidden" value="<s:property  value="c06OldIdNo"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].c06OldIdNo" value="<s:property  value="c06OldIdNo"/>"/>
                                <input type="hidden" value="<s:property  value="c06NewIdNo"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].c06NewIdNo" value="<s:property  value="c06NewIdNo"/>"/>
                                <input type="hidden" value="<s:property  value="communeCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].communeCode" value="<s:property  value="communeCode"/>"/>
                                <input type="hidden" value="<s:property  value="communeName"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].communeName" value="<s:property  value="communeName"/>"/>
                                <input type="hidden" value="<s:property  value="subCommuneCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].subCommuneCode" value="<s:property  value="subCommuneCode"/>"/>
                                <input type="hidden" value="<s:property  value="subCommuneName"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].subCommuneName" value="<s:property  value="subCommuneName"/>"/>
                                <!--<input type="hidden" value="<s:property  value="status"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].status" value="<s:property  value="status"/>"/>-->
                                <input type="hidden" value="<s:property  value="type"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].type" value="<s:property  value="type"/>"/>
                                <input type="hidden" value="<s:property  value="createdBy"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].createdBy" value="<s:property  value="createdBy"/>"/>
                                <input type="hidden" value="<s:property  value="createdDate"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].createdDate" value="<s:property  value="createdDate"/>"/>
                                <input type="hidden" value="<s:property  value="updatedBy"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].updatedBy" value="<s:property  value="updatedBy"/>"/>
                                <input type="hidden" value="<s:property  value="updatedDate"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].updatedDate" value="<s:property  value="updatedDate"/>"/>
                                <input type="hidden" value="<s:property  value="intellectUpdateFlag"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].intellectUpdateFlag" value="<s:property  value="intellectUpdateFlag"/>"/>
                                <input type="hidden" value="<s:property  value="groupCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].groupCode" value="<s:property  value="groupCode"/>"/>
                                <input type="hidden" value="<s:property  value="mobileNumber"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].mobileNumber" value="<s:property  value="mobileNumber"/>"/>
                                <input type="hidden" value="<s:property  value="principleBalance"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].principleBalance" value="<s:property  value="principleBalance"/>"/>
                                <input type="hidden" value="<s:property  value="savingBalance"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].savingBalance" value="<s:property  value="savingBalance"/>"/>
                                <input type="hidden" value="<s:property  value="remainIntAmount"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].remainIntAmount" value="<s:property  value="remainIntAmount"/>"/>
                                <input type="hidden" value="<s:property  value="idExpiredDate"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].idExpiredDate" value="<s:property  value="idExpiredDate"/>"/>
                                <input type="hidden" value="<s:property  value="customerStatus"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].customerStatus" value="<s:property  value="customerStatus"/>"/>
                                <input type="hidden" value="<s:property  value="coreBankingIdNo"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].coreBankingIdNo" value="<s:property  value="coreBankingIdNo"/>"/>
                                <input type="hidden" value="<s:property  value="coreBankingIssuePlace"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].coreBankingIssuePlace" value="<s:property  value="coreBankingIssuePlace"/>"/>
                                <input type="hidden" value="<s:property  value="coreBankingIssueDate"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].coreBankingIssueDate" value="<s:property  value="coreBankingIssueDate"/>"/>
                                <input type="hidden" value="<s:property  value="coreBankingCustomerName"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].coreBankingCustomerName" value="<s:property  value="coreBankingCustomerName"/>"/>
                                <input type="hidden" value="<s:property  value="coreBankingBirthday"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].coreBankingBirthday" value="<s:property  value="coreBankingBirthday"/>"/>  
                            </td>  

                            <td >                                
                                <s:property value="coreBankingCustomerName" />
                            </td>
                            <td class="D0">                                
                                <s:property value="coreBankingBirthday" />
                            </td>
                            <td class="D0">                                
                                <s:property value="coreBankingIdNo" />
                            </td>
                            <td class="D0">                                
                                <s:property value="coreBankingIssueDate" />
                            </td>
                            <td >                                
                                <s:property value="coreBankingIssuePlace" />
                            </td>
                            <td>  
                                <s:if test="isValidCustomerName == 1">
                                    <s:property value="customerName" /> 
                                </s:if>

                            </td>
                            <td class="D0"  title="1 - KH đã xác thực C06 nhưng bị lệch thông tin 
                                2 - Khách hàng chưa xác thực C06 do bị thiếu thông tin (thiếu CMT/CCCD/ngày sinh hoặc CMT/CCCD/ngày sinh ko đúng định dạng) ">  
                                <s:if test="isValidNewIdNo == 1">
                                    <s:property value="c06NewIdNo" /> 
                                </s:if>
                                <s:else>
                                    <s:property value="type" />
                                </s:else>

                            </td>

                            <!--Rà soát với hồ sơ khách hàng-->
                            <s:if test="rasoat_dc.equalsIgnoreCase('1')">  
                                <!--sai với hồ sơ vv-->
                                <td class="D0">    
                                    <input type="checkbox" id ="idc14<s:property  value="%{#rowstatus.index}" />" title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>"  class="onSelectChange_dnht1 D0 " name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c14" value="<s:property  value="cicCode" />"             
                                </td> 

                                <!--sai họ tên-->
                                <td class="D0">    
                                    <input type="checkbox" id ="idc15<s:property  value="%{#rowstatus.index}" />" title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>"  class="onSelectChange_check2 D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c15" value="<s:property  value="cicCode" />"             
                                </td> 
                                <!--sai số cm-->
                                <td class="D0">
                                    <input type="checkbox" id ="idc16<s:property  value="%{#rowstatus.index}" />"  title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>" class="onSelectChange_check2 D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c16" value="<s:property  value="cicCode" />"      
                                </td>


                                <td class="D0">
                                    <input type="checkbox" id ="idc19<s:property  value="%{#rowstatus.index}" />"  title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>" class="onSelectChange_check2 D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c19" value="<s:property  value="cicCode" />"
                                </td>

                                <td class="D0">
                                    <input type="checkbox" id ="idc20<s:property  value="%{#rowstatus.index}" />"  title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>" class="onSelectChange_check1 D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c20" value="<s:property  value="cicCode" />"
                                </td>

                                <td class="D0">
                                    <input type="checkbox" id ="idc21<s:property  value="%{#rowstatus.index}" />"  title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>" class="D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c21" value="<s:property  value="cicCode" />"
                                </td>
                            </s:if>
                            <!--Rà soát trực tiếp với khách hàng mẫu 03/RS-->
                            <s:else>
                                <!--ho tên-->
                                <td class="D0">    
                                    <input type="checkbox" id ="idc28<s:property  value="%{#rowstatus.index}" />" title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>"  class="onSelectChange_dnht2 D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c28" value="<s:property  value="cicCode" />"             
                                </td> 

                                <!--cccd-->
                                <td class="D0">    
                                    <input type="checkbox" id ="idc29<s:property  value="%{#rowstatus.index}" />" title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>"  class="onSelectChange_dnht2 D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c29" value="<s:property  value="cicCode" />"             
                                </td> 
                                <!--ngay thang nam sinh-->
                                <td class="D0">
                                    <input type="checkbox" id ="idc30<s:property  value="%{#rowstatus.index}" />"  title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>" class="onSelectChange_dnht2 D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c30" value="<s:property  value="cicCode" />"      
                                </td>

                                <!--CHưa làm cccd-->
                                <td class="D0">
                                    <input type="checkbox" id ="idc31<s:property  value="%{#rowstatus.index}" />"  title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>" class="onSelectChange_check3 D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c31" value="<s:property  value="cicCode" />"
                                </td>
                                <!--Đã chỉnh intellect-->
                                <td class="D0">
                                    <input type="checkbox" id ="idc33<s:property  value="%{#rowstatus.index}" />"  title="<s:property  value="customerName"/> - <s:property  value="coreBankingBirthday"/>" class="D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c33" value="<s:property  value="cicCode" />"
                                </td>


                            </s:else>
                        </tr>
                    </s:iterator>            
                </table>

            </div>
            <p style="text-align: left; color: red">Phân loại sai CCCD CIC: <br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;1 - KH đã xác thực C06 nhưng bị lệch thông tin <br>
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2 - Khách hàng chưa xác thực C06 do bị thiếu thông tin (thiếu CMT/CCCD/ngày sinh hoặc CMT/CCCD/ngày sinh ko đúng định dạng)
            </p>
            <sj:submit id="CIC_001_save" name="CIC_001_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
    <script>

        function onSelectChange_dnht1(value, index) {
            if (document.getElementById("id14_" + i).value !== '1')
            {
                document.getElementById("idc15" + index).disabled = false;
                document.getElementById("idc16" + index).disabled = false;
                document.getElementById("idc19" + index).disabled = false;
                document.getElementById("idc20" + index).disabled = false;
                document.getElementById("idc21" + index).disabled = false;
            } else
            {
                document.getElementById("idc15" + index).disabled = true;
                document.getElementById("idc16" + index).disabled = true;
                document.getElementById("idc19" + index).disabled = true;
                document.getElementById("idc20" + index).disabled = true;
                document.getElementById("idc21" + index).disabled = true;
            }
            if (document.getElementById("id15_" + i).value !== '1'
                    && document.getElementById("id16_" + i).value !== '1'
                    && document.getElementById("id19_" + i).value !== '1')
            {
                document.getElementById("idc14" + index).disabled = false;
//                document.getElementById("idc20" + index).disabled = false;
                document.getElementById("idc21" + index).disabled = true;
            } else
            {
                document.getElementById("idc14" + index).disabled = true;
                document.getElementById("idc20" + index).disabled = true;
                document.getElementById("idc21" + index).disabled = false;
            }
            if (document.getElementById("id20_" + i).value === '1')
            {
                document.getElementById("idc14" + index).disabled = true;
                document.getElementById("idc16" + index).disabled = true;
                document.getElementById("idc19" + index).disabled = true;
                document.getElementById("idc15" + index).disabled = true;
                document.getElementById("idc21" + index).disabled = true;
            }
        }

        function initTable1()
        {
            var table = document.getElementById("subTable");
            var rowcount = table.rows.length;
            rowcount = rowcount > max_row ? rowcount : max_row;
            for (var i = 0; i < rowcount; i++)
            {
                try {
                    if (document.getElementById("id14_" + i).value !== '1')
                    {
                        document.getElementById("idc15" + i).disabled = false;
                        document.getElementById("idc16" + i).disabled = false;
                        document.getElementById("idc19" + i).disabled = false;
                        document.getElementById("idc20" + i).disabled = false;
                        document.getElementById("idc21" + i).disabled = false;
                    } else
                    {
                        document.getElementById("idc15" + i).disabled = true;
                        document.getElementById("idc16" + i).disabled = true;
                        document.getElementById("idc19" + i).disabled = true;
                        document.getElementById("idc20" + i).disabled = true;
                        document.getElementById("idc21" + i).disabled = true;
                    }
                    if (document.getElementById("id15_" + i).value !== '1'
                            && document.getElementById("id16_" + i).value !== '1'
                            && document.getElementById("id19_" + i).value !== '1')
                    {
                        document.getElementById("idc14" + i).disabled = false;
//                        document.getElementById("idc20" + i).disabled = false;
                        document.getElementById("idc21" + i).disabled = true;
                    } else
                    {
                        document.getElementById("idc14" + i).disabled = true;
                        document.getElementById("idc20" + i).disabled = true;
                        document.getElementById("idc21" + i).disabled = false;
                    }
                    if (document.getElementById("id20_" + i).value === '1')
                    {
                        document.getElementById("idc15" + i).disabled = true;
                        document.getElementById("idc16" + i).disabled = true;
                        document.getElementById("idc19" + i).disabled = true;
                        document.getElementById("idc14" + i).disabled = true;
                        document.getElementById("idc21" + i).disabled = true;
                    }
                } catch (e) {
                }
            }
        }

        var checkboxes = document.querySelectorAll('.onSelectChange_dnht1');
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener('change', function () {
                var currentRow = this.parentNode.parentNode;
                var currentRowIndex = currentRow.rowIndex;
                if (this.checked) {
                    currentRow.cells[10].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[11].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[12].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[13].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[14].querySelector('input[type="checkbox"]').disabled = true;
                } else {
                    currentRow.cells[10].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[11].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[12].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[13].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[14].querySelector('input[type="checkbox"]').disabled = false;
                }
            });
        }

        var checkboxes = document.querySelectorAll('.onSelectChange_check1');
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener('change', function () {
                var currentRow = this.parentNode.parentNode;
                var currentRowIndex = currentRow.rowIndex;
                if (this.checked) {
                    currentRow.cells[10].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[11].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[12].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[9].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[14].querySelector('input[type="checkbox"]').disabled = true;
                } else {
                    currentRow.cells[10].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[11].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[12].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[9].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[14].querySelector('input[type="checkbox"]').disabled = false;
                }
            });
        }

        var checkboxes = document.querySelectorAll('.onSelectChange_check2');
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener('change', function () {
                var currentRow = this.parentNode.parentNode;
                var currentRowIndex = currentRow.rowIndex;
                if (this.checked) {
                    currentRow.cells[9].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[13].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[14].querySelector('input[type="checkbox"]').disabled = false;
                } else {
                    currentRow.cells[9].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[13].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[14].querySelector('input[type="checkbox"]').disabled = true;
                }
            });
        }
        function onSelectChange_dnht2(value, index) {

            if (document.getElementById("id28_" + i).value !== '1'
                    && document.getElementById("id29_" + i).value !== '1'
                    && document.getElementById("id30_" + i).value !== '1')
            {
                document.getElementById("idc31" + index).disabled = false;
            } else
            {
                document.getElementById("idc31" + index).disabled = true;
            }
            if (document.getElementById("id31_" + i).value === '1')
            {
                document.getElementById("idc28" + index).disabled = true;
                document.getElementById("idc29" + index).disabled = true;
                document.getElementById("idc30" + index).disabled = true;
                document.getElementById("idc33" + index).disabled = true;

            }
        }

        function initTable2()
        {
            var table = document.getElementById("subTable");
            var rowcount = table.rows.length;
            rowcount = rowcount > max_row ? rowcount : max_row;
            for (var i = 0; i < rowcount; i++)
            {

                try {

                    if (document.getElementById("id28_" + i).value !== '1'
                            && document.getElementById("id29_" + i).value !== '1'
                            && document.getElementById("id30_" + i).value !== '1')
                    {
                        document.getElementById("idc31" + i).disabled = false;
                    } else
                    {
                        document.getElementById("idc31" + i).disabled = true;
                    }
                    if (document.getElementById("id31_" + i).value === '1')
                    {
                        document.getElementById("idc28" + i).disabled = true;
                        document.getElementById("idc29" + i).disabled = true;
                        document.getElementById("idc30" + i).disabled = true;
                        document.getElementById("idc33" + i).disabled = true;
                    }
                } catch (e) {
                }
            }
        }

        var checkboxes = document.querySelectorAll('.onSelectChange_dnht2');
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener('change', function () {
                var currentRow = this.parentNode.parentNode;
                var currentRowIndex = currentRow.rowIndex;
                if (this.checked) {
                    currentRow.cells[12].querySelector('input[type="checkbox"]').disabled = true;
                } else {

                    currentRow.cells[12].querySelector('input[type="checkbox"]').disabled = false;
                }
            });
        }

        var checkboxes = document.querySelectorAll('.onSelectChange_check3');
        for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].addEventListener('change', function () {
                var currentRow = this.parentNode.parentNode;
                var currentRowIndex = currentRow.rowIndex;
                if (this.checked) {
                    currentRow.cells[9].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[10].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[11].querySelector('input[type="checkbox"]').disabled = true;
                    currentRow.cells[13].querySelector('input[type="checkbox"]').disabled = true;
                } else {

                    currentRow.cells[9].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[10].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[11].querySelector('input[type="checkbox"]').disabled = false;
                    currentRow.cells[13].querySelector('input[type="checkbox"]').disabled = false;
                }
            });
        }
        initTable();
        initTable1();
        initTable2();
    </script>
</html>