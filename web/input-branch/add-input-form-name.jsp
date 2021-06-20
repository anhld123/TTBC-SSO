<%-- 
    Document   : input-form-name
    Created on : Nov 20, 2018, 3:20:16 PM
    Author     : BAOANH
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html> 
<html>
    <head>
        <sj:head/>

        <script type="text/javascript" src="input-branch/js/inputbranch.js"></script>
    </head>
    <body>
        <div align="center">

            <s:form id="addform" name="addfrm" method="post" action="saveFormName1.action" theme="simple">
                <!--<div id="ContentDatain">--> 
                <input type="hidden" value="<s:property  value="loaimau_daluu" />"  name="loaimau_daluu"/> 

                <s:if test="lstParameter.empty">
                    <h2 style="color: indigo">Thêm khóa và tiêu đề mẫu nhập tay</h2>
                    <table align="center"  border="0" cellspacing="0px" cellpadding="0" id="tieudemau">
                        <tr style="border-spacing: 5px">
                            <td width="100"><s:label value="Nhóm báo cáo: "/></td>
                            <td width="200">  
                                <s:select  list="lstGroupRpt" name="grouprpt_id" 
                                           listKey="sKey" listValue="sDesc" 
                                           headerKey="-1" headerValue="------ Chon nhóm BC ------"
                                           id="grouprpt_id" value="grouprpt_id" 
                                           ></s:select>
                                </td>
                            </tr>
                            <tr >
                                <td colspan="2">
                                    <table>
                                        <tr>
                                            <td width="100"><s:label value="Điền khóa: "/></td>
                                        <td width="200"><s:textfield id="khoa" name="khoa" size="30" placeholder="MABCxxxxxx"></s:textfield></td>
                                            <td width="100" align="right">
                                                        <!--<input type="checkbox" name="copydl" value="<s:property  value="copydl" />">-->
                                                    <!--<input type="checkbox" name="copydl" class="inline checkbox" id="copydl_id" value="<s:property  value="copydl" />">-->
                                            <input type="checkbox" id="copydl_id" name="copydl" <s:if test="%{#copydl == true}">checked="true"</s:if> </>
                                            <%--<s:checkbox id="copydl_id" name="copydl" value="false" label="Lấy dữ liệu của kỳ gần nhất"/>--%>
                                        </td>
                                        <td width="200" align="left"><s:label value="Lấy dữ liệu của kỳ gần nhất"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr style="border-spacing: 5px">
                            <td width="100"><s:label value="Tiêu đề: "/></td>
                            <td width="200">  <s:textfield id="tenmau" name="tenmau" size="100" placeholder="Nhập tiêu đề cho báo cáo"></s:textfield></td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <table>
                                        <tr>
                                            <td width="100"><s:label value="Đơn vị tính: "/></td>
                                        <td width="200"><s:select name="donvitinh" list="#{'1':'Đồng','1000':'Ngàn đồng','1000000':'Triệu đồng','1000000000':'Tỷ đồng'}"
                                                  id="id_donvitinh"></s:select> </td>
                                            <td width="50" align="right">

                                                <input type="checkbox" id="sysnc_id" name="dongbo_dl" <s:if test="%{#dongbo_dl == true}">checked="true"</s:if> </>

                                            </td>
                                            <td width="200" align="left"><s:label value="Đồng bộ dữ liệu về TW" cssStyle="color:red"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <p></p>
                        <tr style="margin: 20px; height: 45px;">
                            <td><s:label value="Cấp nhập liệu: "/></td>
                            <td>
                                <s:checkboxlist list="lstGrade" value="defaultGrade" listKey="sKey" listValue="sDesc"
                                                name="rptGrade"></s:checkboxlist>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <!--                                <INPUT TYPE="button" VALUE="Lưu báo cáo"  onClick="isCheckInput();" class="metroButtonStyle">-->
                                <%--<sj:submit targets="divExportReportQuery" value="Lưu mẫu" cssClass="metroButtonStyle"/>--%>
                            </td>
                        </tr>
                    </table>
                    <h2 style="color: indigo">Thêm tham số mẫu nhập tay</h2>
                    <table id="ThemthamsoTable" align="center" style="width:90%;border-spacing:0px 0px"  border="0" cellspacing="0px" cellpadding="0px">
                        <tr align="center"  style="background: #c5dbec">
                            <td colspan="4">
                                <s:label value="Mô tả tham số:"/>&nbsp;&nbsp; 
                                <s:textfield id="MOTA_ID_0" name="MOTA_THAMSO_0" size="50" placeholder="Nhập tên hiển thị cho tham số" cssClass="motathamsoclass"></s:textfield>
                                <s:label value="Chọn loại tham số:"/>&nbsp;&nbsp; 
                                <s:select name="LOAITSO_0" list="#{'D':'D -> Ngày tháng năm','L':'L -> Danh mục','N':'N -> Kiểu số','T':'T -> Kiểu text'}"
                                          id="LOAITSO_ID_0" onchange="addRowPara('LOAITSO_ID_0',0,this.parentNode.parentNode.rowIndex,'HIDDEN_ID_0')"></s:select>   
                                </td>
                                <td></td>
                            </tr>
                            <tr  style="background: peachpuff">
                                <td></td>
                                <td></td>
                                <td></td>
                                <td></td>
                                <td align="center"> <input type="button" value="Thêm tham số" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle"/></td>
                            </tr>
                        </table>
                </s:if>
                <s:else>
                    <h2 style="color: indigo">Sửa mẫu báo cáo nhập tay</h2>
                    <table align="center"  border="0" cellspacing="0px" cellpadding="0" id="tieudemau">
                        <tr style="border-spacing: 5px">
                            <td width="100"><s:label value="Nhóm báo cáo: "/></td>
                            <td width="200">  
                                <s:select  list="lstGroupRpt" name="grouprpt_id" 
                                           listKey="sKey" listValue="sDesc" 
                                           headerKey="-1" headerValue="------ Chon nhóm BC ------"
                                           id="grouprpt_id" value="grouprpt_id" 
                                           ></s:select>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <table>
                                        <tr>
                                            <td width="100"><s:label value="Điền khóa: "/></td>
                                        <td width="200"><s:textfield id="khoa" name="khoa" size="30" placeholder="MABCxxxxxx"></s:textfield></td>
                                            <td width="100" align="right">
                                            <%--<s:if test="copydl">True</s:if>--%> 
                                            <input type="checkbox" id="copydl_id" name="copydl" <s:if test="copydl">checked</s:if> />
                                            <%--<s:checkbox id="copydl_id" name="copydl" value="%{copydl}" label="Lấy dữ liệu của kỳ gần nhất" fieldValue="true"/>--%>
                                            <!--<input type="checkbox" name="copydl" class="inline checkbox" id="copydl_id" value="<s:property  value="copydl" />">-->
                                        </td>
                                        <td width="200" align="left"><s:label value="Lấy dữ liệu của kỳ gần nhất"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td></td>
                            <td></td>
                        </tr>
                        <tr style="border-spacing: 5px">
                            <td width="100"><s:label value="Tiêu đề: "/></td>
                            <td width="200">  <s:textfield id="tenmau" name="tenmau" size="100" placeholder="Nhập tiêu đề cho báo cáo"></s:textfield></td>
                            </tr>
                            <tr>
<!--                                <td ><s:label value="Đơn vị tính: "/></td>
                            <td>
                                <%--<s:property  value="donvitinh" />--%>
                                <select name="donvitinh" id="id_donvitinh">
                                    <option value="1" <s:if test="donvitinh.equalsIgnoreCase('1')"> selected </s:if> >Đồng</option>
                                    <option value="1000" <s:if test="donvitinh.equalsIgnoreCase('1000')"> selected </s:if>>Ngàn đồng</option>
                                    <option value="1000000" <s:if test="donvitinh.equalsIgnoreCase('1000000')"> selected </s:if>>Triệu đồng</option>
                                    <option value="1000000000" <s:if test="donvitinh.equalsIgnoreCase('1000000000')"> selected </s:if>>Tỷ đồng</option>
                                    </select>
                                </td>-->
                                
                                 <td colspan="2">
                                    <table>
                                        <tr>
                                            <td width="100"><s:label value="Đơn vị tính: "/></td>
                                        <td width="200">
                                            <s:select name="donvitinh" list="#{'1':'Đồng','1000':'Ngàn đồng','1000000':'Triệu đồng','1000000000':'Tỷ đồng'}"
                                                  id="id_donvitinh"></s:select> 
                                        </td>
                                            <td width="50" align="right">

                                                <input type="checkbox" id="sysnc_id" name="dongbo_dl" <s:if test="dongbo_dl">checked</s:if>/>

                                            </td>
                                            <td width="200" align="left"><s:label value="Đồng bộ dữ liệu về TW" cssStyle="color:red"/></td>
                                    </tr>
                                </table>
                            </td>
                            </tr>
                            <tr style="margin: 20px; height: 45px;">
                                <td><s:label value="Cấp nhập liệu: "/></td>
                            <td>
                                <s:checkboxlist list="lstGrade" value="defaultGrade" listKey="sKey" listValue="sDesc"
                                                name="rptGrade"></s:checkboxlist>
                                </td>
                            </tr>
                            <tr>
                                <td></td>
                                <td>
                                    <!--                                <INPUT TYPE="button" VALUE="Lưu báo cáo"  onClick="isCheckInput();" class="metroButtonStyle">-->
                                <%--<sj:submit targets="divExportReportQuery" value="Lưu mẫu" cssClass="metroButtonStyle"/>--%>
                            </td>
                        </tr>
                    </table>
                    <h2 style="color: indigo">Thêm tham số mẫu nhập tay</h2>
                    <table id="ThemthamsoTable" align="center" style="width:90%;border-spacing:0px 0px"  border="0" cellspacing="0px" cellpadding="0px">
                        <s:iterator value="#attr.lstParameter" var="modelView" status="rowstatus">
                            <tr align="center"  style="background: #c5dbec">
                                <td colspan="4">
                                    <s:label value="Mô tả tham số:"/>&nbsp;&nbsp; 
                                    <input type="text" size="50" value="<s:property  value="Mota" />" name="MOTA_THAMSO_<s:property  value="%{#rowstatus.index}"/>"  cssStyle="width: 100%;" class="motathamsoclass" placeholder="Nhập tên hiển thị cho tham số"/>
                                    <s:label value="Chọn loại tham số:"/>&nbsp;&nbsp; 
                                    <select name="LOAITSO_<s:property  value="%{#rowstatus.index}" />" id="LOAITSO_ID_<s:property  value="%{#rowstatus.index}" />" 
                                            onchange="addRowPara('LOAITSO_ID_<s:property  value="%{#rowstatus.index}" />', <s:property  value="%{#rowstatus.index}" />, this.parentNode.parentNode.rowIndex, 'HIDDEN_ID_<s:property  value="%{#rowstatus.index}" />')">
                                        <option value="D"  <s:if test="Loaitso.equalsIgnoreCase('D')"> selected </s:if> >D -&gt; Ngày tháng năm</option>
                                        <option value="L"  <s:if test="Loaitso.equalsIgnoreCase('L')"> selected </s:if>>L -&gt; Danh mục</option>
                                        <option value="N"  <s:if test="Loaitso.equalsIgnoreCase('N')"> selected </s:if>>N -&gt; Kiểu số</option>
                                        <option value="T"  <s:if test="Loaitso.equalsIgnoreCase('T')"> selected </s:if>>T -&gt; Kiểu text</option>
                                        </select>
                                    </td>
                                    <td>
                                        <input type="button" value="Xóa tham số" onclick="deleteRow(this.parentNode.parentNode.rowIndex, 'HIDDEN_ID_<s:property  value="%{#rowstatus.index}" />')" class="metroButtonStyle">
                                </td>
                            </tr>
                            <s:if test="Loaitso.equalsIgnoreCase('L')">
                                <tr style="background: #E2E8C9" align="center">
                                    <td colspan="5">
                                        <input type="hidden" name="" value="L" id="HIDDEN_ID_<s:property  value="%{#rowstatus.index}" />">
                                        <label>Tên bảng: </label>
                                        <input type="text" name="BANGSL_<s:property  value="%{#rowstatus.index}"/>" value="<s:property  value="Bangsl"/>" id="BANGSL_ID_<s:property  value="%{#rowstatus.index}"/>" style="width:180px" placeholder="DMPOS"> &nbsp;&nbsp;
                                        <label>Cột hiển thị: </label>
                                        <input type="text" name="COTHIENTHI_<s:property  value="%{#rowstatus.index}"/>" value="<s:property  value="Cothienthi"/>" id="COTHIENTHI_ID_<s:property  value="%{#rowstatus.index}"/>" style="width:100px" placeholder="PO_MA||' -> '||PO_TEN">&nbsp;&nbsp;
                                        <label>Cột tham số: </label>
                                        <input type="text" name="COTTSO_<s:property  value="%{#rowstatus.index}"/>" value="<s:property  value="Cottso"/>" id="COTTSO_ID_<s:property  value="%{#rowstatus.index}"/>" style="width:100px" placeholder="PO_MA">&nbsp;&nbsp;
                                        <label>Điều kiện lọc: </label>
                                        <input type="text" name="DKLOC_<s:property  value="%{#rowstatus.index}"/>" value="<s:property  value="Dkloc"/>" id="DKLOC_ID_<s:property  value="%{#rowstatus.index}"/>" style="width:100px" placeholder="PO_MACN='002721'">&nbsp;&nbsp;
                                        <label>Cột sắp xếp: </label>
                                        <input type="text" name="DKSAPXEP_<s:property  value="%{#rowstatus.index}"/>" value="<s:property  value="Dksapxep"/>" id="DKSAPXEP_ID_<s:property  value="%{#rowstatus.index}"/>" style="width:100px" placeholder="PO_MA">&nbsp;&nbsp;
                                    </td>
                                </tr>
                            </s:if>
                        </s:iterator>

                        <tr  style="background: peachpuff">
                            <td></td>
                            <td></td>
                            <td></td>
                            <td></td>
                            <td align="center"> <input type="button" value="Thêm tham số" onclick="addRow(this.parentNode.parentNode.rowIndex)" class="metroButtonStyle"/></td>
                        </tr>
                    </table>
                </s:else>

                <!--</div>-->     
                <INPUT TYPE="button" VALUE="Lưu báo cáo"  onClick="isCheckInput();" class="metroButtonStyle" >
 
            </s:form>   
            <div id="ContentDatain">
                <div id="divExportReportQuery"></div>
            </div>
        </div>

    </body>
</html>
