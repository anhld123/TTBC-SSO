<%-- 
    Document   : pq_xa_chitieu
    Created on : Oct 11, 2016, 2:52:54 PM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <sj:head/>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phân quyền chỉ tiêu cho danh sách các xã</title>
        <style>
            table{
                border-style: solid;
                border-collapse: collapse;
                /*width: 100%;*/
                /*line-height: 19px;*/
            }
            .tbhead th{
                background-color: #5e5e55;
                font-weight: bold;
                color: #fff;
                text-align: center;
                padding: 5px;
            }
            .cscontent td{
                padding-left:5px;
                padding-top:5px;
                padding-bottom: 5px;
            }
            .tblmain tr td{
                font-weight: bold;
                color: #018c3b;
            }

            .BOLD 
            {
                font-weight: bold;
                font-size: 13px;
                /*width: 95%;*/
            }

            .ITALIC 
            {
                font-style: italic;
                font-size: 12px;
                /*width: 95%;*/
            }

            .BOLD a
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }

            .ITALIC a
            {
                font-style: italic;
                font-size: 12px;
                width: 95%;
            }

            #posCD, #namBc, #maCn, #userId{
                width: 70px;
            }

            a.linkKh{
                color: #116600;
                text-decoration: none;            
            }
            a.linkKh:hover
            {
                color: #5494ea;
                text-decoration: underline;
            }
            a.linkKh:visited
            {
                color: #ab59a6;

            }
            
            #divChiTieu{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width:700px; 
                min-height: 580px; 
                margin: 0px auto 10px auto; 
                padding: 10px;
                background-color: #E2E8C9;
            }
        </style>
        <script>
            $(document).ready(function () {

                $("#Checkxa").change(function () {
                    $(".Checkxa1").prop('checked', $(this).prop("checked"));
                });
            });
        </script>
    </head>
    <body>
        <div id="divChiTieu" align="center">
            <s:form id="idDSXaChitieu" action="saveChitieuDanhsachXa.action" theme="simple">
                <!--<h1>Phân quyền chỉ tiêu cho danh sách các xã</h1>-->
                <table border="0">
                    <tr >
                        <td style="width: 300px; font-size: 14px; color: #18ab29; font-weight: bold">
                            Phân quyền chỉ tiêu cho danh sách các xã
                        </td>
                        <td style="width: 300px">
                            <div id="message_suc_err"></div>
                        </td>
                        <td style="width: 100px">
                            <sj:submit id="idsaveAdd" formIds="idDSXaChitieu" value="Lưu phân quyền"
                                       targets="message_suc_err" indicator="loadingImage_next"  onBeforeTopics="before-next" 
                                       onCompleteTopics="after-next" cssStyle="float: right;"/>
                        </td>
                    </tr>
                </table>
                    <hr/>
                <table border="0">
                    <tr>
                        <td>
                        Mã chỉ tiêu:
                        <input type="text" name="maCt" id="maCt" value="<s:property value="ma_chitieu"/>" readonly="readonly" style="width: 350px; background-color: #E2E8C9; border: 0; color: #cd0a0a "/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                        Tên chỉ tiêu:
                        <input type="text" name="tenCt" id="tenCt" value="<s:property value="ten_chitieu"/>" readonly="readonly" style="width: 400px; background-color: #E2E8C9; border: 0; color: #cd0a0a"/>
                        </td>
                    </tr>
                </table>
                <hr/>
                <p></p>
                <s:hidden id="idmachitieu" name="ma_chitieu"></s:hidden>
                <s:hidden id="idloai_nv" name="loai_nv"></s:hidden>
                    <table border="1px" id="tableKhnv" class="tableKhnv">
                        <tr class="tbhead">
                            <th><s:checkbox id ="Checkxa" name="Checkxa" theme="simple"/></th>
                        <th style="width: 150px;">Mã đơn vị</th>
                        <th style="width: 300px">Tên đơn vị</th>
                    </tr>
                    <s:iterator value="lstPhanquyen" var="modelView" status="rowstatus">
                        <tr class="cscontent">
                            <td >
                                <s:checkbox id ="%{#rowstatus.index}" theme="simple" cssClass="Checkxa1" value="%{trangthai}" name="ma_donvi" fieldValue="%{khoa}"/>
                            </td>
                            <td align="center">
                                <s:property  value="khoa" />
                            </td>
                            <td>
                                <s:property  value="ten" />
                            </td>
                        </tr>
                    </s:iterator>
                </s:form>
        </div>
    </body>
</html>
