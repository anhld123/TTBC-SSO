<%-- 
    Document   : fastreport-group-configure
    Created on : Feb 16, 2016, 11:21:53 AM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head />

<style type="text/css">
    .auto-style1 {
        text-align: center;
        text-decoration: underline;
        font-family: Arial, Helvetica, sans-serif;
        font-size: small;
    }
    .auto-style2 {
        border-collapse: collapse;                
    }
    .auto-style3 {
        font-family: Arial, Helvetica, sans-serif;
        font-size: small;
    }
</style>

<s:form theme="simple" id="fastparamconfig_form"  >
    <table class="auto-style2" style="width: 100%;" cellpadding="2">
        <tr>
            <td class="auto-style3" style="width: 166px; height: 23px"><strong>Khoá:</strong></td>
            <td style="height: 23px">
                <s:textfield name="paraRptQuery.paraKey" 
                         size="60" id="paraKeyId" cssStyle="background: #99ffff;"
                         onchange="onKeyChange()"/></td>
        <script>
            function onKeyChange() {
//                alert('aaa');
                $('#action_type_ID').val('CREATE');
            }
        </script>
    </tr>
    <tr>
        <td class="auto-style3" style="width: 166px; height: 24px"><strong>Mô 
                tả:</strong></td>
        <td style="height: 24px">
            <s:textfield name="paraRptQuery.paraDesc" 
                         size="100" id="paraDescId"/>
        </td>
    </tr>
    <tr>
        <td class="auto-style3" style="width: 166px"><strong>Kiểu hiển thị:</strong></td>
        <td class="auto-style3">
            <s:url var="buildComboUrl_0" 
                   action="FRCFG_Build_Group_Combo"></s:url>
            <sj:select href="%{buildComboUrl_0}" 
                       name="paraRptQuery.paraType"
                       id="paraTypeId"
                       list="paraQueryTypes" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="false"                                                          
                       theme="simple"     
                       ></sj:select>  
            </td>
        </tr>
        <tr>
            <td class="auto-style3" style="height: 21px; width: 166px"><strong>Bảng 
                    dữ liệu tham chiếu:</strong></td>
            <td style="height: 21px">
            <s:textfield name="paraRptQuery.paraTable" 
                         size="100" id="paraTableId"/>
        </td>
    </tr>
    <tr>
        <td class="auto-style3" style="width: 166px; height: 21px"><strong>Cột 
                hiển thị:</strong></td>
        <td style="height: 21px">
            <s:textfield name="paraRptQuery.paraColDesc" 
                         size="60" id="paraColDescId"/>
        </td>
    </tr>
    <tr>
        <td class="auto-style3" style="height: 21px; width: 166px"><strong>Giá 
                trị sử dụng cho biến:</strong></td>
        <td class="auto-style3" style="height: 21px">
            <s:textfield name="paraRptQuery.paraColValue" 
                         size="60" id="paraColValueId"/>
        </td>
    </tr>
    <tr>
        <td class="auto-style3" style="height: 21px; width: 166px"><strong>Điều 
                kiện lọc:</strong></td>
        <td class="auto-style3" style="height: 21px">
            <s:textfield name="paraRptQuery.paraFilter" 
                         size="100" id="paraFilterId"/>
        </td>
    </tr>
    <tr>
        <td class="auto-style3" style="height: 21px; width: 166px"><strong>Sắp 
                xếp cột:</strong></td>
        <td class="auto-style3" style="height: 21px">
            <s:textfield name="paraRptQuery.paraSort" 
                         size="60" id="paraSortId"/>
        </td>
    </tr>
    <tr>
        <td colspan="3"><hr/></td>
    </tr>
    <tr>
        <td align="right" colspan="3">
            <s:url id="updateUrl" action="FRCFG_Update_ParaKey.action">                                            
            </s:url>
            <sj:a id="updateUrl_id"  
                  href="%{updateUrl}"                                                                                                
                  formIds="fastparamconfig_form"                                
                  targets="messageDiv"
                  button="false"                                                                                 
                  theme="simple"></sj:a>
                <input type="button" value="Lưu trữ" onclick="update_click();"></input>
                <input type="button" value="Xoá tham biến" onclick="delete_click();"></input>
                <input type="hidden" name="action_type" id="action_type_ID" value="CREATE"/>
                <script>
                    function update_click() {
                        var paraKeyId = $('#paraKeyId').val();
                        var text = $('#paraDescId').val();
                        if (paraKeyId === '' || text === '') {
                            alert('Bạn không thể lưu dữ liệu khi paraKeyId hoặc Text đang trống.');
                            return false;
                        }
                        $("#messageDiv").empty();
                        $('#updateUrl_id').click();
                        $("#loadTableBtn").trigger("click");
                        //                        $("#loadTableBtn").trigger("click");
                    }

                    function delete_click() {
                        var paraKeyId = $('#paraKeyId').val();
                        if (paraKeyId === '') {
                            alert('Bạn không thể xoá khi menuId đang trống.');
                            return false;
                        }
                        var r = confirm('Bạn có chắc chắn muốn xoá :' + paraKeyId + '?');
                        if (r === true) {
                            $('#action_type_ID').val('DELETE');
                            $("#messageDiv").empty();
                            $('#updateUrl_id').click();
                            $("#loadTableBtn").trigger("click");
                            $('#action_type_ID').val('CREATE');
                        } else {
                            e.preventDefault();
                        }
                    }
                </script>
            </td>
        </tr>
    </table>  
    <table style="border: 1px;width: 100%;" cellspacing="5">
        <tr>
            <td style="font-size: 12pt;">
            <s:url id="loadTableUrl" 
                   action="FRCFG_Display_Variable_Table"/>    
            <sj:a id="loadTableBtn"  
                  href="%{loadTableUrl}"
                  button="false" 
                  theme="simple"
                  formIds="fastparamconfig_form"                    
                  targets="tableDiv"> 
        <u>Làm mới</u>
        </sj:a> 
</td>
</tr>

<tr>
    <td>        
        <div id="tableDiv"></div>                
        <div id="messageDiv"></div>                
    </td>
</tr>
</table>

</s:form>
<script>
    $(function() {
        $("#loadTableBtn").trigger("click");
    });
</script>
