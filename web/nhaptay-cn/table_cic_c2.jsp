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
         </br>
        <a style="color: #003eff; font-weight: 150; font-size: 18px;">
            <b>CHỐT SỐ LIỆU RÀ SOÁT THÔNG TIN CHUNG CỦA KHÁCH HÀNG 
        </a>
        
        </br>
         </br>
        <table id="subTable" style="width: 85%">
            <thead>
                  
                <tr >
                    <th  class="hdtitle">STT</th>
                    <th  class="hdtitle">Mã PGD</th>
                    <th  class="hdtitle">Tên PGD</th>
                   
                    <th  class="hdtitle">Chốt số liệu</th>
                   
                </tr>

            </thead>
            <tr class="txtBody">
                <th style="color: #000; font: italic; font-size: xx-small;">(1)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(2)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(3)</th>
                <th style="color: #000; font: italic; font-size: xx-small;">(4)</th>
               
               
                
            </tr>
            <tbody>
            <td style="text-align: center; width: 100px">1</td> 
            <td><input value="000401" disabled style="width: 100%"></td> 
            <td><input value="PGD Chương Mỹ" disabled style="width: 100%"></td> 
           
            <td  align="center" >    
                <input type="checkbox"  class="checkboxdat TD_MAKH"/>
            </td>  

            </tbody>
            <tbody>
            <td style="text-align: center; width: 100px">1</td> 
            <td><input value="000402" disabled style="width: 100%"></td> 
            <td><input value="PGD Ân Thi" disabled style="width: 100%"></td> 
           
            <td  align="center" >    
                <input type="checkbox"  class="checkboxdat TD_MAKH"/>
            </td>  

            </tbody>
        </table>
            </br>
    </div>
</body>
