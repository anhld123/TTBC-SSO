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
                        width: $("#containParm_full").width()- 20,
                        height: 390
                    });
                }
                $("#tablepl01 td:first").focus();
            </s:if>
        </script> 
    </head>
    <body>
        <s:form id="idform_open_%{khoa_ktgs}" action="OPEN_PGD" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <s:if test="khoa_ktgs.equalsIgnoreCase('ALL')" >
                <div id="divTitle">
                    CHI TIẾT DANH SÁCH CÁC PHÒNG GIAO DỊCH CỦA CHI NHÁNH GỬI/CHƯA GỬI DỮ LIỆU
                </div>
                <p></p>                
                <table border="1" class="editDelete" style="width: 100%" id="tablepl01" align="center">
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                        <s:if test="#rowstatus.index==0">
                            <tr class="ROW_STATUS">
                                <th style="width: 40px;" ><s:property  value="D1" /></th>
                                <th align = "left"  style="width: 70px;"><s:property  value="D2"/></th>
                                <th style="width: 40px;"><s:property  value="D4"/></th>
                                <th style="width: 40px;"><s:property  value="D5"/></th>
                                <th style="width: 40px;"><s:property  value="D6"/></th>
                                <th style="width: 40px;"><s:property  value="D7"/></th>
                                <th style="width: 40px;"><s:property  value="D8"/></th>
                                <th style="width: 40px;"><s:property  value="D9"/></th>
                                <th style="width: 40px;"><s:property  value="D10"/></th>
                                <th style="width: 40px;"><s:property  value="D11"/></th>
                                <th style="width: 40px;"><s:property  value="D12"/></th>
                                <th style="width: 40px;"><s:property  value="D13"/></th>
                                <th style="width: 40px;"><s:property  value="D14"/></th>
                                <th style="width: 40px;"><s:property  value="D15"/></th>
                                <th style="width: 40px;"><s:property  value="D16"/></th>
<!--                                <th style="width: 40px;"><s:property  value="D17"/></th>
                                <th style="width: 40px;"><s:property  value="D18"/></th>-->
                                <th style="width: 40px;"><s:property  value="D19"/></th>
<!--                                <th style="width: 40px;"><s:property  value="D20"/></th>
                                <th style="width: 40px;"><s:property  value="D21"/></th>-->
                                <th style="width: 40px;"><s:property  value="D22"/></th>
                                <th style="width: 40px;"><s:property  value="D23"/></th>
                                <th style="width: 40px;"><s:property  value="D24"/></th>
                                <th style="width: 40px;"><s:property  value="D25"/></th>
                                <th style="width: 40px;"><s:property  value="D26"/></th>
<!--                                <th style="width: 40px;"><s:property  value="D27"/></th>
                                <th style="width: 40px;"><s:property  value="D28"/></th>
                                <th style="width: 40px;"><s:property  value="D29"/></th>-->
                                <!--<th style="width: 90px;">Trạng thái gửi</th>-->
                            </tr>
                        </s:if>
                        <s:else>
                            <tr style="text-align: center; color: #0000FF; font-weight: bold;" class="ROW_STATUS" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <td style="width: 40px;" onclick="onclick_tr();"><s:property  value="D1" /></td>
                                <td align = "left"  style="width: 70px;"><s:property  value="D2"/></td>
                                <td style="width: 40px;"><s:property  value="D4"/></td>
                                <td style="width: 40px;"><s:property  value="D5"/></td>
                                <td style="width: 40px;"><s:property  value="D6"/></td>
                                <td style="width: 40px;"><s:property  value="D7"/></td>
                                <td style="width: 40px;"><s:property  value="D8"/></td>
                                <td style="width: 40px;"><s:property  value="D9"/></td>
                                <td style="width: 40px;"><s:property  value="D10"/></td>
                                <td style="width: 40px;"><s:property  value="D11"/></td>
                                <td style="width: 40px;"><s:property  value="D12"/></td>
                                <td style="width: 40px;"><s:property  value="D13"/></td>
                                <td style="width: 40px;"><s:property  value="D14"/></td>
                                <td style="width: 40px;"><s:property  value="D15"/></td>
                                <td style="width: 40px;"><s:property  value="D16"/></td>
<!--                                <td style="width: 40px;"><s:property  value="D17"/></td>
                                <td style="width: 40px;"><s:property  value="D18"/></td>-->
                                <td style="width: 40px;"><s:property  value="D19"/></td>
<!--                                <td style="width: 40px;"><s:property  value="D20"/></td>
                                <td style="width: 40px;"><s:property  value="D21"/></td>-->
                                <td style="width: 40px;"><s:property  value="D22"/></td>
                                <td style="width: 40px;"><s:property  value="D23"/></td>
                                <td style="width: 40px;"><s:property  value="D24"/></td>
                                <td style="width: 40px;"><s:property  value="D25"/></td>
                                <td style="width: 40px;"><s:property  value="D26"/></td>
<!--                                <td style="width: 40px;"><s:property  value="D27"/></td>
                                <td style="width: 40px;"><s:property  value="D28"/></td>
                                <td style="width: 40px;"><s:property  value="D29"/></td>-->
                            </tr>
                        </s:else>
                    </s:iterator>
                </table>

            </s:if>
            <s:else>   
                <s:if test="macn.equalsIgnoreCase('ALL')" >
                    <div id="divTitle">
                        CHI TIẾT DANH SÁCH CÁC PHÒNG GIAO DỊCH CỦA CHI NHÁNH GỬI/CHƯA GỬI DỮ LIỆU
                    </div>
                    <p></p>                
                    <table border="1" class="editDelete" style="width: 60%" id="tablepl01" align="center">
                        <tr>

                            <th align = "center"  style="width: 50px;">Mã Chi nhánh</th>
                            <th style="width: 100px;">Tên Chi nhánh</th>
                            <th style="width: 60px;">Tổng số PGD</th>
                            <th style="width: 50px;">Số PGD Đã gửi</th>
                            <th style="width: 90px;">Số PGD Chưa Đã gửi</th>
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
                                    <!--<td style="width: 90px;"><s:property  value="D11" /></td>-->
                                </tr>
                            </s:else>
                        </s:iterator>
                    </table>                        
                </s:if>
                <s:else>
                    <div id="divTitle">
                        DANH SÁCH CHI NHÁNH GỬI DỮ LIỆU VÀ TRẠNG THÁI KHÓA
                    </div>
                    <p></p>
                    <table border="1" class="editDelete" id="tablepl01" align="center">
                        <tr>
<!--                            <th align = "center"  style="width: 30px;">
                                <s:checkbox id ="allCheck" name="allCheck"/></th>-->
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
                                    <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscdview" fieldValue="%{D3}"/></td>-->
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
<!--                                    <td align = "center"  style="width: 20px;">
                                        <%--<s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscdview" fieldValue="%{D3}"/>--%>
                                        <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstsaveNT[%{#rowstatus.index}].D2" fieldValue="%{D2}"/>
                                    </td>-->
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
                </s:else>
            </s:else> 

            <sj:submit id="%{khoa_ktgs}_open" name="%{khoa_ktgs}_open" value="save" targets="divExportReport" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
            <s:url id="idLock" action="LOCK_PGD.action"></s:url>                                      
            <sj:submit id="%{khoa_ktgs}_lock" name="%{khoa_ktgs}_lock" href="%{idLock}" value="lock" targets="divExportReport"
                       onBeforeTopics="completediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display:none"/>
        </s:form>
    </body>
</html>
