<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
</style>
<script>
    var max_row = 0;
    $(document).ready(function () {
        $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
        $(".datepicker_month").datepicker({dateFormat: 'mm/yy'});
        $('.D0').css({"text-align": "center"});
        $(".TD_STT").css({"width": "20px"});
        $(".TD_MAKH").css({"width": "70px"});
        $(".TD_TOTIEN").css({"width": "90px"});
        $(".TD_NGAY").css({"width": "55px"});
        $(".TD_TENKH").css({"width": "130px"});
        $(".TD_SOKU").css({"width": "120px"});
        $(".TD_NGAY").css({"width": "40px"});
        $(".TD_CHITIEU").css({"width": "300px"});
        $(".TEN_KH").css({"width": "100%"});
    });

    $("#allCheck_dat").change(function () {
        $(".checkboxdat").prop('checked', $(this).prop("checked"));
    });
</script>
</head>
<body>
    <div style="overflow:scroll; width: 99vw; padding: 5">     
        <a style="color: #003eff; font-weight: 550; font-size: 18px">
            BẢNG RÀ SOÁT THÔNG TIN CHUNG CỦA KHÁCH HÀNG 
        </a>
        <table id="subTableSum" style="z-index: 10; width: 50%">
            <!--<thead>-->

            <tr style="height:32px;">
                <th  class="hdtitle">Mã xã</th>
                <th  class="hdtitle">Tên xã</th>
                <!--                <th  class="hdtitle">Mã tổ</th>
                                <th  class="hdtitle">Tên tổ trưởng</th>-->
                <th  class="hdtitle">Số KH cần rà soát</th>
                <th  class="hdtitle">Số KH đã xác nhận</th>
                <!--<th  class="hdtitle">Sai họ và tên</th>-->

            </tr>

            <!--</thead>-->

            <!--<tbody>-->
            <tr>
                <%--<s:iterator value="lstData" status="idxRows">--%>
                <!--<td style="text-align: center">1</td>--> 
                <td><input value="040101" disabled style="width: 120px"></td> 
                <td><input value="Phạm Ngũ Lão" disabled style="width: 350px"></td> 
                <!--                <td><input value="2342342" disabled style="width: 150px"></td> 
                                <td><input value="Nguyễn Anh Đức" disabled style="width: 250px"></td> -->
                <td><input value="623.424" disabled style="width: 150px; text-align: right"></td> 
                <td><input value="733" disabled style="width: 150px; text-align: right"></td> 


        </table>
        </br>
        <table id="subTable" style="z-index: 10;">
            <thead>
                <tr >      
                    <th rowspan="2" class="hdtitle">STT</th>   
                    <th rowspan="2" class="hdtitle">Mã khách hàng</th>  
                    <th rowspan="2" class="hdtitle">Họ và tên khách hàng</th>  
                    <th rowspan="2" class="hdtitle">Dữ liệu <br>đã cập nhật</th>  
                    <th colspan="3" class="hdtitle">Thông tin trên CMND</th>
                    <th colspan="3" class="hdtitle">Thông tin trên CCCD</th>
                    <th rowspan="2" class="hdtitle" style="width: 80px">Ngày tháng năm sinh</th>   
                    <th rowspan="2" class="hdtitle" style="width: 150px">Dư nợ</th>   
                    <th rowspan="2" class="hdtitle">Số dư tiền gửi</th>  
                    <th rowspan="2" class="hdtitle">Lãi tồn</th>              
                    <th colspan="5" class="hdtitle">Xác nhận sai sót</th> 
                    <th rowspan="2" class="hdtitle">Đã hoàn thành <br>chỉnh sửa trên hệ thống Intellect</th>                              

                </tr>         
                <tr >
                    <th  class="hdtitle">Số CMTND</th>
                    <th  class="hdtitle">Ngày cấp</th>
                    <th  class="hdtitle">Nơi cấp</th>
                    <th  class="hdtitle">Số CCCD</th>
                    <th  class="hdtitle">Ngày cấp</th>
                    <th  class="hdtitle">Nơi cấp</th>
                    <th  class="hdtitle">Sai họ và tên</th>
                    <th  class="hdtitle">Sai số CMND/CCCD</th>
                    <th  class="hdtitle">Sai ngày cấp CMND/CCCD</th>
                    <th  class="hdtitle">Sai nơi cấp CMND/CCCD</th>
                    <th  class="hdtitle">Sai ngày tháng năm sinh</th>
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
                    <td style="text-align: center">
                        <s:property value="%{#rowstatus.index + 1}" /> 
                    </td>
                    <td style="text-align: center"><s:property value="customerCode" /></td>  
                    <td style="text-align: center"><s:property value="customerName" /></td>
                    <td style="text-align: center"><s:property value="coreBankingCustomerName"/><s:property value="coreBankingBirthday"/><s:property value="coreBankingIdNo"/><s:property value="coreBankingIssuePlace"/>
                        <s:property value="coreBankingIssueDate"/></td>
                    <!--số CMTND-->
                    <td style="text-align: center"><s:property value="idNo"/></td>
                    <td> </td>
                    <td> </td>
                    <!--số CCCD-->
                    <td style="text-align: center"><s:property value="oldIdNo"/></td> 
                    <td></td>
                    <td> </td>
                    <!--ngày sinh--> 
                    <td><s:property value="birthDay"/></td>
                    <!--dư nợ--> 
                    <td><s:property value="PrincipleBalanceString"/></td>
                    <!--dư tiền gửi--> 
                    <td><s:property value="savingBalance"/></td>
                    <!--lãi tồn--> 
                    <td><s:property value="remainIntAmount"/></td>
                    <!--sai họ tên-->
                    <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                        <input type="checkbox" style="text-align:center" value="<s:property  value="wrongFullNameConfirmFlag" />"  class="checkboxdat <s:property value='FONTFORMAT'/> TEN_KH" 
                               name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongFullNameConfirmFlag" readonly/>
                    </td>  
                    <!--sai số cm-->
                    <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                        <input type="checkbox" style="text-align:center" value="<s:property  value="wrongIdNoConfirmFlag" />"  class="checkboxdat <s:property value='FONTFORMAT'/> TEN_KH" 
                               name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongIdNoConfirmFlag" readonly/>
                    </td>


                    <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                        <input type="checkbox" style="text-align:center" value="<s:property  value="wrongIssueDateConfirmFlag" />"  class="checkboxdat <s:property value='FONTFORMAT'/> TEN_KH" 
                               name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongIssueDateConfirmFlag" readonly/>
                    </td>

                    <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                        <input type="checkbox" style="text-align:center" value="<s:property  value="wrongIssuePlaceConfirmFlag" />"  class="checkboxdat <s:property value='FONTFORMAT'/> TEN_KH" 
                               name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongIssuePlaceConfirmFlag" readonly/>
                    </td>

                    <td align ="center" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                        <input type="checkbox" style="text-align:center" value="<s:property  value="wrongBirthdayConfirmFlag" />"  class="checkboxdat <s:property value='FONTFORMAT'/> TEN_KH" 
                               name="custCIC[<s:property  value="%{#rowstatus.index}" />].wrongBirthdayConfirmFlag" readonly/>
                    </td>

                </tr>
            </s:iterator>            
        </table>
        </br>
    </div>
</body>
<script>
</script>
