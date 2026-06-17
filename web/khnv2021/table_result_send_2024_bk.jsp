<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <script>
            $(document).ready(function () {
                $("#allCheck").change(function () {
                    $(".checkbox1").prop('checked', $(this).prop("checked"));
                });
            });
            var change_color = '#FFB951';
            function mover(aa) {
                bgcolor = aa.style.backgroundColor;
                aa.style.backgroundColor = change_color;
            }
            function mout(aa) {
                aa.style.backgroundColor = bgcolor;
            }
        </script>
        <script type="text/javascript">
            <s:if test="khoa_ktgs.equalsIgnoreCase('ALL')" >
            $(document).ready(function () {
                gridviewScroll();
            });
            function gridviewScroll() {
                $("#tablepl01").gridviewScroll({
                    width: $("#containParm_full").width() - 20,
                    height: 390
                });
            }
            $("#tablepl01 td:first").focus();
            </s:if>

            function hienthichitietCN(macn_detail) {
                var ht1 = screen.availHeight - 100;
                var wt1 = screen.availWidth - 100;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 100;

                var cboNam = $('#cboNam').val();
                var cboDot = $('#cboDot').val();

//            alert(namBc);
                var url = "getDetailKhnvByCN.action?macn_detail=" + macn_detail
                        + "&cboDot=" + cboDot + "&cboNam=" + cboNam;
                popup = window.open(url, '_blank', "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
            }
        </script> 
    </head>
    <body>
        <s:form id="idform_status_khnv"  theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>

            <div id="divTitle" style="text-align: center">
                DANH SÁCH CHI NHÁNH GỬI DỮ LIỆU VÀ TRẠNG THÁI KHÓA
            </div>
            <p></p>
            <table border="1" class="editDelete" id="tablepl01" align="center" style="width: 50%">
                <tr>
                    <!--                            <th align = "center"  style="width: 30px;">
                    <s:checkbox id ="allCheck" name="allCheck"/></th>-->
                    <th align = "center"  style="width: 40px;">Mã Chi nhánh</th>
                    <th style="width: 80px;">Tên CN</th>
                    <th style="width: 60px;">Ngày gửi</th>
                    <th style="width: 90px;">Trạng thái xử lý</th>
                    <th style="width: 90px;">Trạng thái dữ liệu</th>
                </tr>

                <s:iterator value="#attr.lstData" var="modelView" status="rowstatus">

                    <tr>
                        <s:if test="D12.equalsIgnoreCase('1')">
                            <td style="width: 60px; color: red;font-weight: bold"><s:property  value="D1" /></td>
                            <td style="width: 40px; text-align: left; color: red;font-weight: bold"><s:property  value="D2" /></td>
                            <td style="width: 90px; color: red;font-weight: bold"><s:property  value="D4" /></td>
                            <td style="width: 90px; color: red;font-weight: bold"><s:property  value="D3" /></td>
                            <td style="width: 90px; color: red;font-weight: bold;text-align: left"><s:property  value="TEN" /></td>
                        </s:if>
                        <s:elseif test="D12.equalsIgnoreCase('2')">
                            <td style="width: 60px; color: #3dc21b;font-weight: bold"><s:property  value="D1" /></td>
                            <td style="width: 40px; text-align: left; color: #3dc21b;font-weight: bold"><s:property  value="D2" /></td>
                            <td style="width: 90px; color: #3dc21b;font-weight: bold"><s:property  value="D4" /></td>
                            <td style="width: 90px; color: #3dc21b;font-weight: bold"><s:property  value="D3" /></td>
                            <td style="width: 90px; color: #3dc21b;font-weight: bold;text-align: left"><s:property  value="TEN" /></td>
                        </s:elseif>
                        <s:else>
                            <td style="width: 60px;font-weight: bold"><s:property  value="D1" /></td>
                            <td style="width: 40px; text-align: left;font-weight: bold"><s:property  value="D2" /></td>
                            <td style="width: 90px; font-weight: bold"><s:property  value="D4" /></td>
                            <td style="width: 90px;font-weight: bold"><s:property  value="D3" /></td>
                            <td style="width: 90px; font-weight: bold;text-align: left"><s:property  value="TEN" /></td>   
                        </s:else>
                    </tr>

                </s:iterator>

            </table>
        </s:form>
    </body>
</html>
