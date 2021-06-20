<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<style>
    a.disabled {
        color: gray;
    }

    table.table_2{
        border-style: solid;
        border-collapse: collapse;
        width: 100%;
        font: 13px Arial, Helvetica, sans-serif; 
        line-height: 28px;
    }
</style>

<script>
    function capnhat(menuid, text, url, parentId, reportGroup) {
        setValue('menuId', menuid);
        setValue('textId', text);
        setValue('urlId', url);
        setValue('parentId', parentId);
        $('#action_type_ID').val('UPDATE');
        if (url.toUpperCase() === 'RPTMANAGER') {
            $('#selectedGroup').prop('disabled', false);
            $('#selectedGroup').val(reportGroup);
        }
    }

    function setValue(id, value)
    {
        try {
            document.getElementById(id).value = value;
        } catch (e)
        {
        }
    }
</script>


<table border="1px" class="table_2">
    <tr class="tbhead">
        <th>ID</th>
        <th>Mô tả</th>
        <th>URL</th>
        <th>Parent Id</th>       
        <th>Nhóm báo cáo</th>
    </tr>
    <s:iterator value="menus">
        <tr class="cscontent">
            <td align="center"><a href="#" 
                                  onclick="capnhat('<s:property value="menuId"/>', '<s:property value="text"/>', '<s:property value="navigateUrl"/>', '<s:property value="parentId"/>','<s:property value="rptGroup"/>')">
                    <u> <s:property value="menuId"/></u></a></td>
            <td align="center"><s:property value="text"/></td>
            <td align="center"><s:property value="navigateUrl"/></td>
            <td align="right"><s:property value="parentId"/></td>                                      
            <td align="center"><s:property value="rptGroup"/></td>                                      
        </tr>
    </s:iterator>
</table>
