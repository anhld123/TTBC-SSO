<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head/>
    </head>

    <style>
        #menuBcttv{
            width: 100%;
            height: 30px;                
            border: 1px solid; 
            /*background: #CBCBCB;*/
            padding-bottom: 5px;
        }

        #containBcttv{
            width: 100%;
            min-height:450px;
            /*background: #FFCCBA;*/
            border: 1px solid;
            margin-top: 2px;
        }

        .metroButtonStyle {
            font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
            display: block;
            color: rgb(255, 255, 255);
            text-decoration: none;
            text-align: center;
            width: 90px;
            height: 26px;
            padding: 5px;
            margin: 5px 0px 0px 5px;
            font-size: 12px;
            background: none repeat scroll 0 0 #808080;
            color: #FFF;
            border: 0px none;
            border-radius: 1px 1px 1px 1px;
            outline: 0px none;
        }
        .metroButtonStyle:hover {
            background: #018c3b;
        }
        .metroButtonStyle:active {
            background: #DCDCDC;
        }


    </style>

    <script>
        //CuongBM: 18Jul14
        //Desc: Lan dau tien load trang, goi den su kien click load all bc
        function initPage() {
            $("#loadExcel")[0].click();
            //alert('thu nhe');
        }
    </script>
    <body onload="initPage()">
        <div id="menuBcttv" style="border: 1px solid">
            <!--<table border="1"><tr><td>-->
            <div id="thongbao" style="border: 0px solid; float: left; width: 500px"></div>
            <!--</td><td>-->
            <div id="button" style="float: right;">
                <s:form id="Excel" theme="simple">
                    <div style="float: right; width: 100px">
                        <s:url id="editDelExcel" action="loadallrpt_Edit" />
                        <sj:submit id="idEditDelExcel" targets="containBcttv"  href="%{editDelExcel}" indicator="loadingImage" cssClass="metroButtonStyle" value="Sửa/Xóa"></sj:submit>
                        <sj:submit id="loadExcel" targets="containBcttv"  href="excel_config/load_all_rpt_export.jsp" indicator="loadingImage" cssStyle="display: none;" value="Load BC"></sj:submit>
                        </div>
                        <div style="float: right; width: 100px" >
                        <s:url id="LoadAddNewExcel" action="LoadAddNewExcel" />
                        <sj:submit id="addExcel" targets="containBcttv"  href="%{LoadAddNewExcel}" cssClass="metroButtonStyle" value="Thêm"></sj:submit>
                        </div>
                </s:form>
                <!--</td></tr></table>-->
            </div>

        </div>

        <div id="containBcttv">
        </div>

        <div id="messageDiv">
        </div>
    </body>
</html>
