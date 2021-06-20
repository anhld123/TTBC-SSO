<%-- 
    Document   : add_excel
    Created on : Jan 22, 2016, 8:35:20 AM
    Author     : BAOANH
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css"  href="css/excel_template.css" />
        <sj:head/>
        <script>


            $.subscribe("thanhcong", function (event, data) {
                alert('thanh cong roi');
            });

            $.subscribe("loiroi", function (event, data) {
                alert('loi to roi');
            });
        </script>
    </head>
    <body>
        <s:form id="AddFileTemplate" action="AddFileTemplateExcel" theme="simple">
            <div id="filetemplate" align="center" style="height: 220px;">
                <p></p>
                <s:hidden name="key_hidden"></s:hidden>
                <s:hidden name="module"></s:hidden>
                <s:if test="key_hidden == null || key_hidden == ''">
                    <span style="color:blue; font-weight: bolder; font-size: larger">Tạo báo cáo từ excel mẫu</span>
                </s:if>
                <s:else>
                    <span style="color:blue; font-weight: bolder; font-size: larger">Sửa báo cáo từ excel mẫu</span>
                </s:else>
                <table border="1" cellspacing="0px" cellpadding="1px">
                    <tr>
                        <td width="width: 200px; height: 20px;">
                            <label id="mota_lbl" class="font_lable">Chọn nhóm báo cáo:</label>
                        </td>
                        <td width="width: 650px; height: 20px;">
                            <s:select id="group_id" 
                                      cssClass="font_lable"
                                      name="group_id"
                                      list="lstObjGroup" 
                                      listKey="sKey"
                                      listValue="sDesc" 
                                      headerKey="-1"
                                      headerValue="---Chọn nhóm báo cáo---"
                                      cssStyle="width: 350px;"                                      
                                      >                    
                            </s:select>
                        </td>

                    </tr>
                    <s:if test="module.equalsIgnoreCase('SBV')">
                        <tr>
                            <td width="width: 200px; height: 20px;">
                                <label id="mota_lbl" class="font_lable">Chọn Báo cáo:</label>
                            </td>
                            <td width="width: 650px; height: 20px;">
                                <s:select id="id_bc" 
                                          cssClass="font_lable"
                                          name="id"
                                          list="lstObjRpt" 
                                          listKey="sKey"
                                          listValue="sDesc" 
                                          headerKey="-1"
                                          headerValue="---Chọn nhóm báo cáo---"
                                          cssStyle="width: 350px;"                                      
                                          >                    
                                </s:select>
                            </td>

                        </tr>
                    </s:if>
                    <tr>
                        <td width="width: 200px; height: 20px;">
                            <label id="mota_lbl" class="font_lable">Mô tả mẫu:</label>
                        </td>
                        <td width="width: 650px; height: 20px;">
                            <s:textfield id="MotaMau" name="description" size="80%" cssClass="font_lable"/>
                        </td>
                    </tr>
                    <tr>
                        <td width="width: 200px; height: 20px;">
                            <label id="mota_lbl" class="font_lable">File excel mẫu:</label>
                        </td>
                        <td width="width: 650px; height: 20px;">
                            <s:file name="fileUpload" cssClass="font_lable" label="Chọn file" placeholder="fileUpload"></s:file>
                            </td>
                        </tr>      
                        <tr>
                            <td width="width: 200px; height: 20px;">
                                <label id="mota_lbl" class="font_lable">Định dạng file xuất:</label>
                            </td>
                            <td width="width: 650px; height: 20px;">
                            <s:textfield id="MotaMau" name="fileOutFormat" size="80%" cssClass="font_lable"/>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 200px; height: 10px;">                            
                            <label id="mota_lbl" class="font_lable">Chọn cấp xuất báo cáo:</label>
                        </td>
                        <td style="width: 650px; height: 10px;font-size: small;font-weight: bold;" >
                            <s:checkboxlist  list="lstGrade" value="defaultGrade" listKey="sKey" listValue="sDesc"
                                             name="rptGrade" cssClass="font_lable" cssStyle="font-size: larger;font-weight:"></s:checkboxlist>
                            </td>
                        </tr>
                        <s:if test="module.equalsIgnoreCase('SBV')">
                            
                        </s:if>
                        <s:else>
                            <tr>
                            <td style="width: 200px; height: 10px;">                            
                                <label id="mota_lbl" class="font_lable">Xử lý công thức:</label>
                            </td>
                            <td style="width: 650px; height: 10px;font-size: small;font-weight: bold;" >
                               <s:select id="ID_TT" name="id" list="#{'N':'Để nguyên công thức excel tự xử lý','Y':'Xử lý công thức theo Fill dữ liệu'}"
                                      cssStyle="font-weight: bold;width: 350px; vertical-align: middle;" cssClass="font_lable"/>
                                </td>
                            </tr>
                        </s:else>
                        <tr>
                            <td colspan="2" align="center">
                            <sj:submit id="luubc" targets="Parameter_div" value="lưu báo cáo" cssClass="font_lable"/>
                            <%--<s:submit id="test" value="thunhe" />--%>
                        </td>
                    </tr>
                </table>
            </div>
        </s:form>
        <div id="Parameter_div" align="center" style=" height: 180px;">
        </div>   
    </body>
</html>
