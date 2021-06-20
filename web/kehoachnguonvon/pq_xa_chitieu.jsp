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
        <title>Phân quyền cho xã và danh sách chỉ tiêu</title>
    </head>
    <style>
        *{
            /*font: 12px Arial, Helvetica, sans-serif;*/
            font: 14px Arial, Helvetica, sans-serif;
        }
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
            width:80%; 
            min-height: 580px; 
            margin: 0px auto 10px auto; 
            padding: 10px;
            background-color: #E2E8C9;
        }
    </style>
    <script>
        $(document).ready(function () {
            $("#CheckChitieu").change(function () {
                $(".CheckChitieu1").prop('checked', $(this).prop("checked"));
            });
        });
    </script>
    <body>
        <div id="divChiTieu" align="center">
            <s:form id="idDSChitieuXa" action="saveXaDanhsachChitieu.action" theme="simple">
                <table border="0">
                    <tr >
                        <td style="width: 300px; font-size: 14px; color: #18ab29; font-weight: bold">
                            Phân quyền cho xã và danh sách chỉ tiêu
                        </td>
                        <td style="width: 300px">
                            <div id="message_suc_err"></div>
                        </td>
                        <td style="width: 100px">
                            <sj:submit id="idsaveAdd" formIds="idDSChitieuXa" value="Lưu phân quyền"
                                       targets="message_suc_err" indicator="loadingImage_next"  onBeforeTopics="before-next" 
                                       onCompleteTopics="after-next" cssStyle="float: right;"/>
                        </td>
                    </tr>
                    <!--                    <tr>
                                            <td colspan="3" style="width: 300px; font-size: 14px; color: red; font-weight: bold">Mã đơn vị: <s:property value='donvi'/> Tên đơn vị: <s:property value='ten_donvi'/></td>
                                        </tr>-->
                </table>
                <hr/>
                <table border="0">
                    <tr>
                        <td>
                            Mã đơn vị:
                            <input type="text" name="maCt" id="maCt" value="<s:property value="donvi"/>" readonly="readonly" style="width: 350px; background-color: #E2E8C9; border: 0; color: #cd0a0a "/>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Tên đơn vị:
                            <input type="text" name="tenCt" id="tenCt" value="<s:property value="ten_donvi"/>" readonly="readonly" style="width: 400px; background-color: #E2E8C9; border: 0; color: #cd0a0a"/>
                        </td>
                    </tr>
                </table>
                <hr/>
<!--                <p></p>-->
                <s:hidden id="iddonvi" name="donvi"></s:hidden>
                <s:hidden id="idloai_nv" name="loai_nv"></s:hidden>
                    <table border="1px" id="tableKhnv" class="tableKhnv">
                        <tr class="tbhead">
                            <th><s:checkbox id ="CheckChitieu" name="CheckChitieu" theme="simple"/></th>
                        <th>Hiển thị</th>
                        <th>Tên chỉ tiêu</th>
                    </tr>
                    <s:iterator value="lstPhanquyen" var="modelView" status="rowstatus">
                        <tr class="cscontent">
                            <td class="<s:property value='KH_FONTWEIGHT'/>">
                                <s:checkbox id ="%{#rowstatus.index}" theme="simple" cssClass="CheckChitieu1" value="%{trangthai}" name="ma_ct" fieldValue="%{khoa}"/>
                            </td>
                            <td  class="<s:property value='FONTWEIGHT'/>">
                                <s:property  value="hienthi" />
                            </td>
                            <td class="<s:property value='FONTWEIGHT'/>">
                                <s:property  value="ten" />
                            </td>
                        </tr>
                    </s:iterator>
                </s:form>
        </div>
    </body>
</html>
