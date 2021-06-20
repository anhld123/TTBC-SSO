<%-- 
    Document   : dsbctuct
    Created on : 02-Jun-2014, 13:39:59
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <%
            boolean getpara1 = (boolean) request.getAttribute("getpara1");
            boolean getpara2 = (boolean) request.getAttribute("getpara2");
            if (!getpara1 && !getpara2) {
        %>
        <div style="width: 0%; overflow: scroll; float: left;"  name="grview1" id="grview1">
            <form id="para1" style="width:0%;height: 0%">
                <table border="1" cellspacing="0" cellpadding="0" width="0%" style="border-collapse:collapse;border-color: #DCDCDC;">
                    <tr style="background: #DCDCDC;">
                        <th  width="10px"><a href="javascript:jscheckall('para1');">Chọn</a></th>
                        <th  width="55px">Mã</th>
                        <th>Tên</th>
                    </tr>
                    <s:iterator value="loadDanhmuclienquan1">
                        <tr>
                            <td width="8px" valign="middle" align="center"><input type="checkbox" name="P_Para1" value="<s:property value="CHITIEU"/>"></td>
                            <td valign="middle" align="center"><s:property value="CHITIEU"/></td>
                            <td>&nbsp;<s:property value="NAME"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </form>
        </div>
        <div style="width: 0%; overflow: scroll; float: left;" name="grview2" id="grview2">
            <form id="para2" style="width:0%;height: 0%">
                <table border="1" cellspacing="0" cellpadding="0" width="0%" style="border-collapse:collapse; border-color:#DCDCDC;">
                    <tr style="background: #DCDCDC;">
                        <th width="10px"><a href="javascript:jscheckall('para2');">Chọn</a></th>
                        <th width="55px">Mã</th>
                        <th>Tên</th>
                    </tr>
                    <s:iterator value="loadDanhmuclienquan2">
                        <tr>
                            <td width="8px" valign="middle" align="center"><input type="checkbox" name="P_Para2" value="<s:property value="CHITIEU"/>"></td>
                            <td valign="middle" align="center"><s:property value="CHITIEU"/></td>
                            <td>&nbsp;<s:property value="NAME"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </form>
        </div>
        <%};
            if (getpara1 && !getpara2) {
        %>
        <div style="width: 100%; overflow: scroll; float: left;"  name="grview1" id="grview1">
            <form id="para1" style="width:100%;height: 100%">
                <table border="1" cellspacing="0" cellpadding="0" width="100%" style="border-collapse:collapse;border-color: #DCDCDC;">
                    <tr style="background: #DCDCDC;">
                        <th  width="10px"><a href="javascript:jscheckall('para1');">Chọn</a></th>
                        <th  width="55px">Mã</th>
                        <th>Tên</th>
                    </tr>
                    <s:iterator value="loadDanhmuclienquan1">
                        <tr>
                            <td width="8px" valign="middle" align="center"><input type="checkbox" name="P_Para1" value="<s:property value="CHITIEU"/>"></td>
                            <td valign="middle" align="center"><s:property value="CHITIEU"/></td>
                            <td>&nbsp;<s:property value="NAME"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </form>
        </div>
        <div style="width: 0%; overflow: scroll; float: left;" name="grview2" id="grview2">
            <form id="para2" style="width:0%;height: 0%">
                <table border="1" cellspacing="0" cellpadding="0" width="0%" style="border-collapse:collapse; border-color:#DCDCDC;">
                    <tr style="background: #DCDCDC;">
                        <th width="10px"><a href="javascript:jscheckall('para2');">Chọn</a></th>
                        <th width="55px">Mã</th>
                        <th>Tên</th>
                    </tr>
                    <s:iterator value="loadDanhmuclienquan2">
                        <tr>
                            <td width="8px" valign="middle" align="center"><input type="checkbox" name="P_Para2" value="<s:property value="CHITIEU"/>"></td>
                            <td valign="middle" align="center"><s:property value="CHITIEU"/></td>
                            <td>&nbsp;<s:property value="NAME"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </form>
        </div>
        <%};
            if (!getpara1 && getpara2) {
        %>
        <div style="width: 0%; overflow: scroll; float: left;"  name="grview1" id="grview1">
            <form id="para1" style="width:0%;height: 0%">
                <table border="1" cellspacing="0" cellpadding="0" width="0%" style="border-collapse:collapse;border-color: #DCDCDC;">
                    <tr style="background: #DCDCDC;">
                        <th  width="10px"><a href="javascript:jscheckall('para1');">Chọn</a></th>
                        <th  width="55px">Mã</th>
                        <th>Tên</th>
                    </tr>
                    <s:iterator value="loadDanhmuclienquan1">
                        <tr>
                            <td width="8px" valign="middle" align="center"><input type="checkbox" name="P_Para1" value="<s:property value="CHITIEU"/>"></td>
                            <td valign="middle" align="center"><s:property value="CHITIEU"/></td>
                            <td>&nbsp;<s:property value="NAME"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </form>
        </div>
        <div style="width: 100%; overflow: scroll; float: left;" name="grview2" id="grview2">
            <form id="para2" style="width:100%;height: 100%">
                <table border="1" cellspacing="0" cellpadding="0" width="100%" style="border-collapse:collapse; border-color:#DCDCDC;">
                    <tr style="background: #DCDCDC;">
                        <th width="10px"><a href="javascript:jscheckall('para2');">Chọn</a></th>
                        <th width="55px">Mã</th>
                        <th>Tên</th>
                    </tr>
                    <s:iterator value="loadDanhmuclienquan2">
                        <tr>
                            <td width="8px" valign="middle" align="center"><input type="checkbox" name="P_Para2" value="<s:property value="CHITIEU"/>"></td>
                            <td valign="middle" align="center"><s:property value="CHITIEU"/></td>
                            <td>&nbsp;<s:property value="NAME"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </form>
        </div>
        <%};
            if (getpara1 && getpara2) {
        %>
        <div style="width: 50%; overflow: scroll; float: left;"  name="grview1" id="grview1">
            <form id="para1" style="width:100%;height: 100%;">
                <table border="1" cellspacing="0" cellpadding="0" width="0%" style="border-collapse:collapse;border-color: #DCDCDC;width:100%;">
                    <tr style="background: #DCDCDC;">
                        <th  width="10px"><a href="javascript:jscheckall('para1');">Chọn</a></th>
                        <th  width="55px">Mã</th>
                        <th>Tên</th>
                    </tr>
                    <s:iterator value="loadDanhmuclienquan1">
                        <tr>
                            <td width="8px" valign="middle" align="center"><input type="checkbox" name="P_Para1" value="<s:property value="CHITIEU"/>"></td>
                            <td valign="middle" align="center"><s:property value="CHITIEU"/></td>
                            <td>&nbsp;<s:property value="NAME"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </form>
        </div>
        <div style="width: 50%; overflow: scroll; float: left;" name="grview2" id="grview2">
            <form id="para2" style="width:100%;height: 100%;">
                <table border="1" cellspacing="0" cellpadding="0" width="100%" style="border-collapse:collapse; border-color:#DCDCDC;">
                    <tr style="background: #DCDCDC;">
                        <th width="10px"><a href="javascript:jscheckall('para2');">Chọn</a></th>
                        <th width="55px">Mã</th>
                        <th>Tên</th>
                    </tr>
                    <s:iterator value="loadDanhmuclienquan2">
                        <tr>
                            <td width="8px" valign="middle" align="center"><input type="checkbox" name="P_Para2" value="<s:property value="CHITIEU"/>"></td>
                            <td valign="middle" align="center"><s:property value="CHITIEU"/></td>
                            <td>&nbsp;<s:property value="NAME"/></td>
                        </tr>
                    </s:iterator>
                </table>
            </form>
        </div>
        <%};%>
    </body>
</html>
