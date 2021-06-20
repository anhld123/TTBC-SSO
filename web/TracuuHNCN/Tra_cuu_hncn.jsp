<%-- 
    Document   : Tra_cuu_hncn
    Created on : Oct 1, 2015, 10:49:00 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tra cứu thông tin hộ nghèo, cận nghèo</title>
        <script type="text/javascript" src="DMChitieu/js/jquery-1.4.4.min.js"></script>
        <SCRIPT language="javascript">
            function addRow(tableID,index) {
                var table = document.getElementById(tableID);
                var rowCount = table.rows.length;
                if (rowCount < 23) {
                    var row = table.insertRow(rowCount);
                    var colCount = table.rows[0].cells.length;
                    for (var i = 0; i < colCount; i++) {
                        var newcell = row.insertCell(i);
                        newcell.innerHTML = table.rows[0].cells[i].innerHTML;
                    }
                }
                while(listgt[index+1].options.length > 0){                
                    listgt[index+1].remove(0);
                }
                
            }
            function deleteRow(tableID, indx) {
                var rowCount = document.getElementById(tableID).rows.length;
                if (rowCount > 1) {
                    var table = document.getElementById(tableID);
                    table.deleteRow(indx);
                }
            }
            function getvaluedk(indexselect) {
                var el = document.getElementsByName('select')[indexselect];
                var url, sdata;
                url = "loadcontrol.action?indexctl=" + indexselect;
                sdata = jQuery("#frmmain").serialize();
                $.post(url, sdata, function (data) {
                    document.getElementsByName("dvcontent")[indexselect].innerHTML = data;
                });
            }
            function submitvalue(sel) {
                var url, sdata;
                url = "getvalue.action?pagenum=" + sel;
                sdata = jQuery("#frmmain").serialize();
                $.post(url, sdata, function (data) {
                    document.getElementById("viewcontent").innerHTML = data;
                });
            }
            function funcandk() {
                $("#dkcontent").toggle();
            }
            function Callbaocao(fullname) {
                var ht = "454px";
                var wt = "700px";
                var resize = window.open(fullname + "&vbsprandom=" + Math.random(), "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                    resize.resizeBy(wt, ht);
                } else {
                    resize.resizeTo(wt, ht);
                }
                resize.focus();
            }
            function Openpop(fullname) {
                var ht = "454px";
                var wt = "700px";
                var resize = window.open(fullname + "?vbsprandom=" + Math.random(), "IMS_REPORTS", "height=" + ht + ",width=" + wt + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
                if (navigator.userAgent.indexOf('Chrome') != -1 && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                    resize.resizeBy(wt, ht);
                } else {
                    resize.resizeTo(wt, ht);
                }
                resize.focus();
            }
        </SCRIPT>
    </head>
    <body style="font-family: tahoma; font-size: 13px;">
        <form name="frmmain" id="frmmain">
            <TABLE border="1"  width="100%">
                <tr><td colspan="2">
                        <INPUT type="button" value="Ẩn/Hiện" id="andk" id="btnhide" onclick="funcandk()"/>
                        <INPUT type="button" value="Thêm mới" onclick="Openpop('TracuuHNCN/add_ds_hongheo.jsp')"/>
                        <INPUT type="button" value="Thêm điều kiện" onclick="addRow('dataTable',this.parentNode.parentNode.rowIndex)" />
                        <INPUT type="button" value="Thực hiện" onclick="submitvalue(1)"/>
                        <hr>
                    </td></tr>
                <tr><td width="20%" valign="top" id="dkcontent"> 
                        <TABLE id="dataTable" border="0" width="100%">
                            <TR>
                                <TD align="Left">
                                    <select id="listdk" name="listdk" onchange="getvaluedk(this.parentNode.parentNode.rowIndex)">
                                        <OPTION value="CHON">(Chọn tiêu chí)</OPTION>
                                            <s:iterator value="lstdieukien">
                                            <OPTION value="<s:property value="TENTRUONG"/>"><s:property value="GIATRI"/></OPTION>
                                            </s:iterator>
                                    </Select>
                                </TD>
                                <td  align="Left"><div name="dvcontent"/></td>
                                <td  align="Left"><a href="#" id="cmdxoa" onclick="deleteRow('dataTable', this.parentNode.parentNode.rowIndex);
                                        return false;" style="font-style: italic;"/>Xóa</a></td>
                            </TR>
                        </TABLE>
                    </td>
                    <td valign="top">
                        <div name="viewcontent" id="viewcontent"></div>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>
