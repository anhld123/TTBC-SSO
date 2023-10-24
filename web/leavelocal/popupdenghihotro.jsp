<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN PHU VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
    <script src="js/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="js/3.6.0/jquery-ui.css">
    <script src="js/3.6.0/jquery-ui.js"></script>
    <script src="js/jquery.number.js"></script>
    <script src="js/format_num.js"></script>
</head>
<html>
    <script>
        var max_row = 0;

//        function loadbc(targetElement)
//        {
//            var kk = targetElement.options[targetElement.selectedIndex].value;
//            document.getElementById('XuLyNo').value = kk;
////            alert(kk);
//            if (kk === '2')
////                $('#test12').empty();
//                document.getElementById('paymentDiv').style.display = '';
//            else
//                document.getElementById('paymentDiv').style.display = 'none';
//        }
        

       

    </script>
    <style>
        .cssDate {
            text-align: right;
        }
        #divTitle{
            font: 14px Arial, Helvetica, sans-serif;
            font-weight: bold;
            color: #0077b3;
            text-align: center;
        }
    </style>

    <body>
        <form id="frmDeNghiHT">        
            <!--<input type="hidden" name="XuLyNo" id="XuLyNo" value="<s:property value='XuLyNo'/>">-->            
            <!--<input type="hidden" name="ngaydenghi" id="ngaydenghi" value="<s:property value='ngaydenghi'/>">-->
            <!--<input type="hidden" name="sovbdenghi" id="sovbdenghi" value="<s:property value='sovbdenghi'/>">-->
            <input type="hidden" name="txtNgayBc" id="txtNgayBc" readonly="readonly" value="31/12/2050"/>
            <input type="hidden" name="txtFromDate" id="txtFromDate" readonly="readonly" value="31/12/2022"/>
            <input type="hidden" name="txtToDate" id="txtToDate" readonly="readonly" value="31/12/2050"/>
            <input type="hidden" name="vsbpMakh" value="<s:property value='vsbpMakh'/>">
            <input type="hidden" name="vsbpNgayBC" id="vsbpNgayBC" value="31/12/2050">
            <input type="hidden" name="vsbpMaPgd" value="<s:property value='vsbpMaPgd'/>">
            <div id="divTitle">
                CẬP NHẬT THÔNG TIN ĐỀ NGHỊ HỖ TRỢ
            </div>
            <table id="tblChung">
                <tr style="text-align: center;">
                    <th colspan="6" style="text-align: left; color: #07B200;">Khách hàng: <s:property value='vsbpTenKh'/> (<s:property value='vsbpMakh'/>)</th>
                </tr>                
            </table>
            <div style="height:10px"></div>    
                <div>
                    Ngày đề nghị:  &nbsp; <input class="cssDate" readonly="readonly" style="text-align: left" type="text" name="ngaydenghi" id="ngaydenghi" value="<s:property value='ngaydenghi'/>" >
                </div>
                <div style="height:10px"></div>  
                <div id="paymentDiv">
                    Số VB đề nghị: <input style="text-align: left" type="text" name="sovbdenghi"  id="sovbdenghi" value="<s:property value='sovbdenghi'/>" >  
                </div>
            <div style="height:20px"></div>    
            
            <div style="text-align: center;">
                <%--<s:if test="flagPos.equalsIgnoreCase('1')">--%> 
                    <input type="button" value="Gửi phê duyệt" name="cmdLuu" id="cmdLuu"/>
                 <%--</s:if>--%>
            </div>
            <div id="divExportReport"></div>
        </form>

    </body>

    <script>
//        document.getElementById('paymentDiv').style.display = 'none';
//        document.getElementById('XuLyNolov').style.display = 'none';
//        document.getElementById('XuLyNolov').value = document.getElementById('XuLyNo').value;
        
        
        
//        var vbspNgayDNHT = document.getElementById('vbspNgayDNHT').value;
//        document.getElementById('ngaydn').value = vbspNgayDNHT
//        if (xuly.substring(0,1) == 2)
//        {
//            document.getElementById('paymentDiv').style.display = '';
//        }
//        
//        if(xuly.length > 1)
//        {
//            
//            document.getElementById('XuLyNolov').value = xuly.substring(0,1);
//            var date = xuly.substring(2,11)
//            var s = xuly.substring(7,11) + '-'+ xuly.substring(4,6)+ '-'+ xuly.substring(1,3);
////            alert(s);
            
//        }
//        else
//        {
//            document.getElementById('XuLyNolov').value = xuly
//            
//        }
        
        
        
        //Tìm dữ liệu
        $("#cmdLuu").click(function () {
            let aCheck = confirm("Bạn chắc chắn muốn lưu số liệu báo cáo ?");
            if (aCheck) {
                var url, sdata;
                url = "saveDeNghiHT.action";
                sdata = jQuery("#frmDeNghiHT").serialize();
                $.ajax({
                    type: "POST",
                    url: url,
                    data: sdata,
                    success: function (data) {
                        if (data === "200") {
                            alert("Thành công: Lưu dữ liệu.");
                            window.opener.document.getElementById('idSearch').click();
                            window.close();
                        } else {
                            alert("Lỗi: Lưu dữ liệu.");
                        }
                    },
                    error: function (request) {
                        alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                    }
                });
            }
        });

        
       


        $(function () {
            setCssStyle();
        });

        function setCssStyle() {
            $(".cssDate").datepicker(
                    {
                        dateFormat: 'dd/mm/yy',
                        showOn: "button",
                        buttonImage: "img/icon-ui_datepicker.png",
                        buttonImageOnly: true,
                        // dateFormat: 'dd/mm/yy',
                        showButtonPanel: true,
                        buttonText: "icono",
                        changeMonth: true,
                        changeYear: true
                    });
        }
    </script>
