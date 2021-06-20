<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<style>
    a.disabled {
        color: gray;
    }


    .tableStyle td {
        font-size: 14px;
    }
    
    .tableStyle th {
        font-size: 14px;
        background-color: #ccffff;
    }
</style>

<script>
    function capnhat(pGroupId, pDescription, pModule, pRegion) {
        setValue('groupId', pGroupId);
        setValue('textId', pDescription);
        setValue('group_module_ID', pModule);
        setValue('group_apply_region_ID', pRegion);
        $('#action_type_ID').val('UPDATE');        
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

<div>
    <table style="width: 100%; 
           border-collapse: collapse;" border=1 
           class="tableStyle">
        <tr>
            <th>Group Id</th>
            <th>Mô tả</th>
            <th>Module</th>
            <th>Áp dụng</th>       
            <th>Ngày tạo</th>
        </tr>
        <s:iterator value="groupQueries">
            <tr >
                <td align="center" >
                    <a href="#" 
                       onclick="capnhat('<s:property value="groupQueryPK.groupId"/>', 
                                   '<s:property value="groupDesc"/>', 
                                   '<s:property value="groupQueryPK.module"/>', 
                                   '<s:property value="groupQueryPK.applyRegion"/>')">
                       <u> <s:property value="groupQueryPK.groupId"/></u></a></td>
                <td align="left" ><s:property value="groupDesc"/></td>
                <td align="center" ><s:property value="groupQueryPK.module"/></td>
                <td align="center" ><s:property value="groupQueryPK.applyRegion"/></td>                                      
                <td align="center" ><s:property value="mkrDt"/></td>                                      
            </tr>
        </s:iterator>
    </table>
</div>