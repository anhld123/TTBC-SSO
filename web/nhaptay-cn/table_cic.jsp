<%-- 
    Document   : 
    Created on : 
    Author     :
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>

<style>
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 120%;
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
        $(".TD_TENTS").css({"width": "190px"});
        $(".TD_SOTK").css({"width": "70px"});
        $(".TD_MAKH").css({"width": "60px"});
        $(".TD_THOIGIAN").css({"width": "55px"});
        $(".TD_MAPGD").css({"width": "50px"});
        $(".TD_BUTTON1").css({"width": "40px"});
        $(".TD_SOTIEN").css({"width": "auto"});
        $(".TEN_KH").css({"width": "80px"});
        $(".TD_THUTU").css({"width": "50px"});
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
            var matmp1 = getMabyNumber1(i);//                       
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
            //cho combox 15
            var matmp15 = getMabyNumber15(i);//                       
            if (matmp15 == 1)
            {
                $('input:checkbox[id=idc15' + i + ']').attr('checked', true);
            }
            //cho combox 16
            var matmp16 = getMabyNumber16(i);//                       
            if (matmp16 == 1)
            {
                $('input:checkbox[id=idc16' + i + ']').attr('checked', true);
            }
            //cho combox 17
            var matmp17 = getMabyNumber17(i);//                       
            if (matmp17 == 1)
            {
                $('input:checkbox[id=idc17' + i + ']').attr('checked', true);
            }
            
            //cho combox 18
            var matmp18 = getMabyNumber18(i);//                       
            if (matmp18 == 1)
            {
                $('input:checkbox[id=idc18' + i + ']').attr('checked', true);
            }
            
            //cho combox 19
            var matmp19 = getMabyNumber19(i);//                       
            if (matmp19 == 1)
            {
                $('input:checkbox[id=idc19' + i + ']').attr('checked', true);
            }
        }
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
    
    function getMabyNumber17(idx)
    {
        var ma = '';
        try {
            var ma_id = 'id17_' + idx;
            ma = document.getElementById(ma_id).value;
        } catch (e)
        {
            ma = '999999';
        }
        return ma;
    }
    
    function getMabyNumber18(idx)
    {
        var ma = '';
        try {
            var ma_id = 'id18_' + idx;
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
</script>   
</head>
<body>
    <s:form id="id_sv_CIC_001" action="SAVE_CIC_001" theme="simple">
        <div style="overflow:scroll; width: 99vw; padding: 5">     
            <a style="color: #003eff; font-weight: 550; font-size: 18px">
                BẢNG RÀ SOÁT THÔNG TIN CHUNG CỦA KHÁCH HÀNG 
            </a>
            <table id="subTableSum" style="z-index: 10; width: 50%">
                <tr style="height:32px;">
                    <th  class="hdtitle">Mã xã</th>
                    <th  class="hdtitle">Tên xã</th>
                    <th  class="hdtitle">Số KH cần rà soát</th>
                    <th  class="hdtitle">Số KH đã xác nhận</th>
                </tr>
                <tr>
                    <td><input value="040101" disabled style="width: 120px"></td> 
                    <td><input value="Phạm Ngũ Lão" disabled style="width: 350px"></td> 
                    <td><input value="623.424" disabled style="width: 150px; text-align: right"></td> 
                    <td><input value="733" disabled style="width: 150px; text-align: right"></td> 
            </table>
            </br>
            <table id="subTable" style="z-index: 10;">
                <thead>
                    <tr >      
                        <th rowspan="2" class="hdtitle ">STT</th>   
                        <th rowspan="2" class="TEN_KH">Mã khách hàng</th>  
                        <th rowspan="2" class="TD_SOTIEN">Họ và tên khách hàng</th>  
                        <th rowspan="2" class="hdtitle TD_TENTS">Dữ liệu <br>đã cập nhật</th>  
                        <th colspan="3" class="hdtitle TEN_KH">Thông tin trên CMND</th>
                        <th colspan="3" class="hdtitle TEN_KH">Thông tin trên CCCD</th>
                        <th rowspan="2" class="hdtitle" style="width: 80px">Ngày tháng năm sinh</th>   
                        <th rowspan="2" class="hdtitle TD_SOKU">Dư nợ</th>   
                        <th rowspan="2" class="hdtitle TD_SOKU">Số dư tiền gửi</th>  
                        <th rowspan="2" class="hdtitle TD_SOKU">Lãi tồn</th>              
                        <th colspan="5" class="D0">Xác nhận sai sót</th> 
                        <th rowspan="2" class="hdtitle TD_SOTIEN">Đã hoàn thành <br>chỉnh sửa trên hệ thống Intellect</th>                              
                    </tr>         
                    <tr >
                        <th  class="hdtitle TD_MAPGD">Số CMTND</th>
                        <th  class="hdtitle TD_SOTK">Ngày cấp</th>
                        <th  class="hdtitle TD_TENKH123">Nơi cấp</th>
                        <th  class="hdtitle TD_MAPGD">Số CCCD</th>
                        <th  class="hdtitle TD_SOTK">Ngày cấp</th>
                        <th  class="hdtitle TD_TENKH123">Nơi cấp</th>
                        <th  class="TD_SOTK">Sai họ và tên</th>
                        <th  class="TD_SOTK">Sai số CMND/CCCD</th>
                        <th  class="TD_SOTK">Sai ngày cấp CMND/CCCD</th>
                        <th  class="TD_SOTK">Sai nơi cấp CMND/CCCD</th>
                        <th  class="TD_SOTK">Sai ngày tháng năm sinh</th>
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
                        <th style="color: #000; font: italic; font-size: xx-small;">(15)</th>
                        <th style="color: #000; font: italic; font-size: xx-small;">(16)</th>
                        <th style="color: #000; font: italic; font-size: xx-small;">(17)</th>
                        <th style="color: #000; font: italic; font-size: xx-small;">(18)</th>
                        <th style="color: #000; font: italic; font-size: xx-small;">(19)</th>
                        <th style="color: #000; font: italic; font-size: xx-small;">(20)</th>  
                    </tr>
                </thead>
                <s:iterator value="#attr.custCIC" var="modelView" status="rowstatus">   
                    <tr>
                        <td class="D0">
                            <s:property value="%{#rowstatus.index + 1}" /> 
                        </td>
                        <td class="TEN_KH D0"><s:property value="customerCode" />
                            <input type="hidden" value="<s:property  value="wrongFullNameConfirmFlag" />"  id="id15_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="wrongFullNameConfirmFlag"/>"/>
                            <input type="hidden" value="<s:property  value="wrongIdNoConfirmFlag" />"  id="id16_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="wrongIdNoConfirmFlag"/>"/>
                            <input type="hidden" value="<s:property  value="wrongIssueDateConfirmFlag" />"  id="id17_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="wrongIssueDateConfirmFlag"/>"/>
                            <input type="hidden" value="<s:property  value="wrongIssuePlaceConfirmFlag" />"  id="id18_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="wrongIssuePlaceConfirmFlag"/>"/>
                             <input type="hidden" value="<s:property  value="wrongBirthdayConfirmFlag" />"  id="id19_<s:property  value="%{#rowstatus.index}" />" 
                                   value="<s:property  value="wrongBirthdayConfirmFlag"/>"/>

                            <input type="hidden" value="<s:property  value="mainPos"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].mainPos" value="<s:property  value="mainPos"/>"/>
                            <input type="hidden" value="<s:property  value="posCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].posCode" value="<s:property  value="posCode"/>"/>
                            <input type="hidden" value="<s:property  value="customerCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].customerCode" value="<s:property  value="customerCode"/>"/>
                            <input type="hidden" value="<s:property  value="cicCode"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].cicCode" value="<s:property  value="cicCode"/>"/>
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
                            <input type="hidden" value="<s:property  value="status"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].status" value="<s:property  value="status"/>"/>
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
                            <input type="hidden" value="<s:property  value="coreBankingBirthday"/>"name="custCIC[<s:property  value="%{#rowstatus.index}" />].coreBankingBirthday" value="<s:property  value="coreBankingBirthday"/>"/>  </td>  

                        <td class="TD_SOTIEN"><s:property value="customerName" /></td>
                        <td class="D0"><s:property value="coreBankingCustomerName"/><s:property value="coreBankingBirthday"/><s:property value="coreBankingIdNo"/><s:property value="coreBankingIssuePlace"/>
                            <s:property value="coreBankingIssueDate"/></td>
                        <!--số CMTND-->
                        <td><s:property value="idNo"/></td>
                        <td  class="D0"><s:property value="IdExpiredDate"/></td>
                        <td> </td>
                        <!--số CCCD-->
                        <td ><s:property value="oldIdNo"/></td> 
                        <td></td>
                        <td> </td>
                        <!--ngày sinh--> 
                        <td class="D0"><s:property value="birthDay"/></td>
                        <!--dư nợ--> 
                        <td class="number"><s:property value="PrincipleBalance"/></td>
                        <!--dư tiền gửi--> 
                        <td class="number"><s:property value="savingBalance"/></td>
                        <!--lãi tồn--> 
                        <td class="number"><s:property value="remainIntAmount"/></td>
                        <!--sai họ tên-->
                        <td>    
                            <input type="checkbox" id ="idc15<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c15" value="<s:property  value="cicCode" />"             
                        </td> 
                        <!--sai số cm-->
                        <td>
                            <input type="checkbox" id ="idc16<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c16" value="<s:property  value="cicCode" />"      
                        </td>

                        <td>
                            <input type="checkbox" id ="idc17<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c17" value="<s:property  value="cicCode" />"     
                        </td>

                        <td>
                            <input type="checkbox" id ="idc18<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c18" value="<s:property  value="cicCode" />"      
                        </td>

                        <td>
                            <input type="checkbox" id ="idc19<s:property  value="%{#rowstatus.index}" />"  class=" TEN_KH D0" name="lstCombox[<s:property  value="%{#rowstatus.index}" />].c19" value="<s:property  value="cicCode" />"
                        </td>

                        <td class="TD_SOTIEN">
                            <s:if test="status.toString().equalsIgnoreCase('0') || status.equalsIgnoreCase('0')"><a style="color: red">Chưa rà soát</a></s:if>
                            <s:elseif test="status.toString().equalsIgnoreCase('1') || status.equalsIgnoreCase('1')"><a>Đã rà soát và cập nhật trên Intellect</a></s:elseif>
                            <s:elseif test="status.toString().equalsIgnoreCase('2') || status.equalsIgnoreCase('2')"><a>Không làm rõ được</a></s:elseif>
                            </td>
                        </tr>
                </s:iterator>            
            </table>

        </div>
        <sj:submit id="CIC_001_save" name="CIC_001_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>
</body>
<script>
    initTable();
</script>
