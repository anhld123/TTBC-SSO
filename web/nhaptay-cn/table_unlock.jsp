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
            <s:if test="khoa_nhaptaycn.equalsIgnoreCase('ALL')" >
                $(document).ready(function () {
                    gridviewScroll();
                });
                function gridviewScroll() {
                    $("#tablepl01").gridviewScroll({
                        width: $("#containParm_full").width()- 20,
                        height: 390
                    });
                }
                $("#tablepl01 td:first").focus();
            </s:if>
        </script> 
    </head>
    <body>
        <s:form id="idform_open_%{khoa_nhaptaycn}" action="OPEN_PGD" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
                    <div id="divTitle">
                        DANH SÁCH CHI NHÁNH GỬI DỮ LIỆU VÀ TRẠNG THÁI KHÓA
                    </div>
                    <p></p>
                    <table border="1" class="editDelete" id="tablepl01" align="center">
                        <tr>
                            <th align = "center"  style="width: 30px;">
                                <s:checkbox id ="allCheck" name="allCheck"/></th>
                            <th align = "center"  style="width: 50px;">Mã PGD</th>
                            <th style="width: 100px;">Tên PGD</th>
                            <th style="width: 60px;">Ngày gửi</th>
                            <th style="width: 50px;">User gửi</th>
                            <th style="width: 90px;">Trạng thái xử lý</th>
                            <th style="width: 90px;">Trạng thái gửi</th>
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">

                            <s:if test="D1.equalsIgnoreCase('true')">
                                <tr style="text-align: center; color: #0000FF" onmouseover="mover(this);"  onmouseout="mout(this);">
                                    <td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>
                                    <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                    <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                    <td style="width: 60px;"><s:property  value="D8" /></td>
                                    <td style="width: 50px;"><s:property  value="D7" /></td>
                                    <td style="width: 90px;"><s:property  value="D9" /></td>
                                    <td style="width: 90px;"><s:property  value="D11" /></td>
                                </tr>
                            </s:if>
                            <s:else>
                                <tr style="text-align: center; color: red" onmouseover="mover(this);"  onmouseout="mout(this);">
                                    <td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>
                                    <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                    <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                    <td style="width: 60px;"><s:property  value="D8" /></td>
                                    <td style="width: 50px;"><s:property  value="D7" /></td>
                                    <td style="width: 90px;"><s:property  value="D9" /></td>
                                    <td style="width: 90px;"><s:property  value="D11" /></td>
                                </tr>
                            </s:else>
                        </s:iterator>
                    </table>

            <sj:submit id="%{khoa_nhaptaycn}_open" name="%{khoa_nhaptaycn}_open" value="save" targets="divExportReport" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
            <s:url id="idLock" action="LOCK_PGD.action"></s:url>                                      
            <sj:submit id="%{khoa_nhaptaycn}_lock" name="%{khoa_nhaptaycn}_lock" href="%{idLock}" value="lock" targets="divExportReport"
                       onBeforeTopics="completediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display:none"/>
        </s:form>
    </body>
</html>
