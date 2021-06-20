<%-- 
    Document   : exp_excel
    Created on : Jul 14, 2014, 4:17:12 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head/> 
        <script src="js/JavaScriptUtil.js"></script>
        <script src="js/InputMask.js"></script>
        <script src="js/Parsers.js"></script>
        <script src="js/Checkdate.js"></script>
        <script>
            $(document).ready(function () {
                $("#mapGenReport").bind("click", function () {
//                    alert("1");
                    if ($("#exp_phts input:checkbox:checked").length <=0)     
                    {
                        // none is checked
                        alert("Bạn phải chọn báo cáo để xuất số liệu!");
                        return;
                    }
                    $("#exportReport").trigger('click');    //Goi den su kien click cua nut that
                });                               
            });

            $.subscribe('beforeClick', function (event, data) {
                $("#divParams").empty();
            });
            function change_kybc()
            {
                $('#containTree').empty();
                var ky_bc = $("#idky_bc").val();
//                alert(ky_bc);
                //location.href ="/IMS_REPORTS/loadKyBC_Tree.action?ky_bc="+ky_bc;

                //$('#idma_bc').l $("#loadKhDetail").trigger("click");ad("loadKyBC_Tree.action?ky_bc=" + ky_bc);
                $.publish("reloadTree");
            }
            function loadbc()
            {
//                alert('vao onchange');
                 $("#loadDataReport").trigger("click");
            }
            
            
                        
        </script>

        <style>

            #container{
                width: 100%;
                height: 460px;
                border: 0px solid;
                padding-left: 0px;        
                /*background: #FFE6B0*/

            }

            #containTree{
                width: 18%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
                background: #DDFFDD;
                /*border: 1px solid;*/
            }

            #containParm{
                width: 100%;
                height: 200px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                /*border: 1px solid;*/
                /*background: #d58512;*/
            }
            
            #containReport{
                width: 90%;
                height: 200px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                border: 1px solid;
                /*background: #d58512;*/
            }

            #navParam{
                width: 81%;
                padding-left: 5px;
                height: 250px;
                float: right;
                /*border: 1px solid;*/
            }
            li {
  font-size: 23px;
}

        </style>
    </head>
    <body>
        <h2>Phản hồi tra soát</h2>
        <hr/>    
        
        <li >            
            <a href="#"
                onclick="callDirectLink('urlMaThongKe');"><u>Chất lượng khai báo mã thống kê</u></a>
        </li>                
        <li>            
            <a href="#"
                onclick="callDirectLink('urlMauBieuTT35');"><u>Mẫu biểu thông tư 35</u></a>
        </li>
        <li>            
            <a href="#"
                onclick="callDirectLink('urlNhapThuCong');"><u>Nhập thủ công</u></a>
        </li>

    </body>
</html>

<script>
    
    function callDirectLink(link) {
        
        var ht = screen.availHeight / 2;
        var wt = screen.availWidth / 3 + 50;       

        var resize = window.open(link + "?random=" + Math.random(),
                "IMS_REPORTS0", "height=" + (ht) + ",width=" + (wt)
                + ",left=0,top=0,directories=no,status=no,menubar=no,\n\personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        if (navigator.userAgent.indexOf('Chrome') !== -1
                && parseFloat(
                        navigator.userAgent.substring(
                                navigator.userAgent.indexOf('Chrome') + 7
                                ).split(' ')[0]) >= 15) {
            resize.resizeBy(wt, ht);
        } else {
            resize.resizeTo(wt, ht);
        }
        
        resize.moveTo(150, 50);
        resize.focus();
    }
</script>