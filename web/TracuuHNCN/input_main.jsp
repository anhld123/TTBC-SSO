<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
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
            padding-bottom: 20px;
        }

        #containBcttv{
            width: 100%;
            min-height:390px;
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
            $("#loadInputFile")[0].click();
        }
    </script>
    <body onload="initPage()">
        <div id="menuBcttv">
            <s:form id="Query" theme="simple">
                <div id="Contentssss" align="center" > 
                    <s:url id="fileDownload" action="download_temp"></s:url>
                    <table align="center">
                        <tr align="center">
                            <td align="center" style="float: left; width:  70%"><h2>Xin chọn file dữ liệu excel cần import dữ liệu vào</h2></td>
                            <td align="center" style="float: right; width: 20%"><h2><s:a href="%{fileDownload}">Tải file Excel mẫu</s:a> </h2></td>
                            </tr>
                        </table>
                    </div>

                    <div style="float: right; width: 100px">
                    <%--<s:url id="editDelQuery" action="loadallquery" />--%>
                    <%--<sj:submit id="idEditDelQuery" targets="containBcttv"  href="%{editDelQuery}" indicator="loadingImage" cssClass="metroButtonStyle" value="Sửa/Xóa"></sj:submit>--%>
                    <sj:submit id="loadInputFile" targets="containBcttv"  href="TracuuHNCN/uploadfile.jsp" indicator="loadingImage" cssStyle="display: none;" value="Load BC"></sj:submit>
                    </div>
                    <div style="float: right; width: 100px" >
                    <%--<s:url id="LoadAddNewQuery" action="LoadAddNewQuery" />--%>
                    <%--<sj:submit id="addQuery" targets="containBcttv"  href="bctheotruyvan/add_query.jsp" cssClass="metroButtonStyle" value="Thêm"></sj:submit>--%>
                    <%--<sj:submit id="addQuery" targets="containBcttv"  href="%{LoadAddNewQuery}" cssClass="metroButtonStyle" value="Thêm"></sj:submit>--%>
                </div>
            </s:form>


        </div>

        <div id="containBcttv">
        </div>

        <div id="messageDiv">
        </div>
    </body>
</html>

