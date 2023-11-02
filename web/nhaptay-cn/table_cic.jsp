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
                        <td class="TEN_KH D0"><s:property value="customerCode" /></td>  
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
                            <s:if test="wrongFullNameConfirmFlag.equalsIgnoreCase('1')">
                                <input type="checkbox" style="text-align:center" value="<s:property  value="wrongFullNameConfirmFlag" />"  
                                       class="checkboxdat TD_SOTK D0" id="check1<s:property value="%{#rowstatus.index}"/>"
                                       name="custCIC[<s:property value="%{#rowstatus.index}" />].wrongFullNameConfirmFlag" checked/>

                            </s:if>
                            <s:else>
                                <input type="checkbox" style="text-align:center" value="<s:property  value="wrongFullNameConfirmFlag" />"  
                                       class="checkboxdat TD_SOTK D0" id="check1<s:property value="%{#rowstatus.index}"/>"
                                       name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongFullNameConfirmFlag"/>
                            </s:else>
                            <input type="hidden" value="<s:property value="wrongFullNameConfirmFlag" />"  id="id9_<s:property value="%{#rowstatus.index}" />"
                                   name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongFullNameConfirmFlag"/>
                            <input type="hidden" value="<s:property value="wrongFullNameConfirmFlag" />"
                                   name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongFullNameConfirmFlag"/>
                            <input type="hidden" value="<s:property value="customerCode" />" name="custCIC[<s:property  value="%{#rowstatus.index}" />].customerCode"/>
                            <input type="hidden" value="<s:property value="cicCode" />" name="custCIC[<s:property  value="%{#rowstatus.index}" />].cicCode"/>  </td>  
                        <!--sai số cm-->
                        <td>
                            <input type="checkbox" style="text-align:center" value="<s:property  value="wrongIdNoConfirmFlag" />"  
                                   class="checkboxdat TD_SOTK D0" id="check2<s:property value="%{#rowstatus.index}"/>"
                                   name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongIdNoConfirmFlag" />
                        </td>

                        <td>
                            <input type="checkbox" style="text-align:center" value="<s:property  value="wrongIssueDateConfirmFlag" />"  
                                   class="checkboxdat TD_SOTK D0" id="check3<s:property value="%{#rowstatus.index}"/>"
                                   name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongIssueDateConfirmFlag" />
                        </td>

                        <td>
                            <input type="checkbox" style="text-align:center" value="<s:property  value="wrongIssuePlaceConfirmFlag" />" 
                                   class="checkboxdat TD_SOTK D0" id="check4<s:property value="%{#rowstatus.index}"/>"
                                   name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongIssuePlaceConfirmFlag" readonly/>
                        </td>

                        <td>
                            <input type="checkbox" style="text-align:center" value="<s:property  value="wrongBirthdayConfirmFlag" />"  
                                   class="checkboxdat TD_SOTK D0" id="check5<s:property value="%{#rowstatus.index}"/>"
                                   name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongBirthdayConfirmFlag" readonly/>
                        </td>

                        <td class="TD_SOTIEN">
                            <s:if test="status.equalsIgnoreCase('0')"><a style="color: red">Chưa rà soát</a></s:if>
                            <s:elseif test="status.equalsIgnoreCase('1')"><a>Đã rà soát và cập nhật trên Intellect</a></s:elseif>
                            <s:elseif test="status.equalsIgnoreCase('2')"><a>Không làm rõ được</a></s:elseif>
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
            //cho combox 1
            var matmp1 = getMabyNumber1(i);
            if (matmp1 === 1)
            {
                $('input:checkbox[id=check1' + i + ']').attr('checked', true);
//                        $('input:checkbox[id=check2' + i + ']').attr('checked', true);
//                        $('input:checkbox[id=check3' + i + ']').attr('checked', true);
//                        $('input:checkbox[id=check4' + i + ']').attr('checked', true);
//                        $('input:checkbox[id=check5' + i + ']').attr('checked', true);
            }
        }
    }
    initTable();

</script>
