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
            <s:if test="loai_module.equalsIgnoreCase('ALL')" >
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
        <s:form id="idform_open_%{loai_module}" action="OPEN_PGD_CIC_TT200" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            
                    <div id="divTitle">
                        CHI TIẾT DANH SÁCH CÁC CHI NHÁNH GỬI/CHƯA GỬI DỮ LIỆU
                    </div>
                    <p></p>                
                    <table border="1" class="editDelete" style="width: 60%" id="tablepl01" align="center">
                        <tr>

                            <th align = "center"  style="width: 50px;">Mã Chi nhánh</th>
                            <th style="width: 100px;">Tên Chi nhánh</th>
                            <th style="width: 60px;">Tổng số PGD</th>
                            <th style="width: 50px;">Số PGD Đã gửi</th>
                            <th style="width: 90px;">Số PGD Chưa Đã gửi</th>
                            <th style="width: 90px;">Số DN đã gửi</th>
                            <!--<th style="width: 90px;">Trạng thái gửi</th>-->
                        </tr>
                        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">

                            <s:if test="D9.equalsIgnoreCase(D10)">
                                <tr style="text-align: center; color: #0000FF; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">

                                    <td align = "center"  style="width: 30px;"><s:property  value="D3" /></td>
                                    <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                    <td align = "right" style="width: 60px;"><s:property  value="D9" /></td>
                                    <td align = "right" style="width: 50px;"><s:property  value="D10" /></td>
                                    <td align = "right" style="width: 90px;"><s:property  value="D11" /></td>
                                    <td align = "right" style="width: 90px;"><s:property  value="D12" /></td>
                                    <!--<td style="width: 90px;"><s:property  value="D11" /></td>-->
                                </tr>
                            </s:if>
                            <s:elseif test="D11.equalsIgnoreCase(D9)">
                                <tr style="text-align: center; color: red; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">

                                    <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                    <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                    <td align = "right" style="width: 60px;"><s:property  value="D9" /></td>
                                    <td align = "right" style="width: 50px;"><s:property  value="D10" /></td>
                                    <td align = "right" style="width: 90px;"><s:property  value="D11" /></td>
                                    <td align = "right" style="width: 90px;"><s:property  value="D12" /></td>
                                    <!--<td style="width: 90px;"><s:property  value="D11" /></td>-->
                                </tr>
                            </s:elseif>
                            <s:else>
                                <tr style="text-align: center; color: darkorchid; font-weight: bold;" onmouseover="mover(this);"  onmouseout="mout(this);">

                                    <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                    <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                    <td align = "right" style="width: 60px;"><s:property  value="D9" /></td>
                                    <td align = "right" style="width: 50px;"><s:property  value="D10" /></td>
                                    <td align = "right" style="width: 90px;"><s:property  value="D11" /></td>
                                    <td align = "right" style="width: 90px;"><s:property  value="D12" /></td>
                                    <!--<td style="width: 90px;"><s:property  value="D11" /></td>-->
                                </tr>
                            </s:else>
                        </s:iterator>
                    </table>                        
                

            <sj:submit id="%{loai_module}_open" name="%{loai_module}_open" value="save" targets="divExportReport" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
            <s:url id="idLock" action="LOCK_PGD_CIC_TT200.action"></s:url>                                      
            <sj:submit id="%{loai_module}_lock" name="%{loai_module}_lock" href="%{idLock}" value="lock" targets="divExportReport"
                       onBeforeTopics="completediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display:none"/>
        </s:form>
    </body>
</html>
