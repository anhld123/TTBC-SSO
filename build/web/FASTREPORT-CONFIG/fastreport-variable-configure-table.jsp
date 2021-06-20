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
    function capnhat(pv_row) {

        setValue('paraKeyId', html2text($('#cell_1_' + pv_row).html()));
        setValue('paraDescId', html2text($('#cell_2_' + pv_row).html()));
        setValue('paraTypeId', html2text($('#cell_3_' + pv_row).html()));
        setValue('paraTableId', html2text($('#cell_4_' + pv_row).html()));
        setValue('paraColDescId', html2text($('#cell_5_' + pv_row).html()));
        setValue('paraColValueId', html2text($('#cell_6_' + pv_row).html()));
        setValue('paraFilterId', html2text($('#cell_7_' + pv_row).html()));
        setValue('paraSortId', html2text($('#cell_8_' + pv_row).html()));
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


    function html2text(html) {
        var tag = document.createElement('div');
        tag.innerHTML = html;
        return tag.innerText.toString().trim();
    }
</script>

<div style="height: 360px; overflow:scroll;" >
    <table style="width: 100%; 
           border-collapse: collapse;" border=1 
           class="tableStyle">
        <tr>
            <th>PARA_KEY</th>
            <th>PARA_DESC</th>
            <th>PARA_TYPE</th>
            <th>PARA_TABLE</th>       
            <th>PARA_COL_DESC</th>
            <th>PARA_COL_VALUE</th>
            <th>PARA_FILTER</th>
            <th>PARA_SORT</th>
        </tr>
        <s:iterator value="paraRptQueries" status="stat">
            <tr >
                <td align="left" >
                    <a href="#" onclick="capnhat('<s:property value="#stat.count"/>')" >                      
                        <u id="cell_1_<s:property value='#stat.count'/>"> <s:property value="paraKey"/></u></a></td>
                <td align="left" id="cell_2_<s:property value='#stat.count'/>"><s:property value="paraDesc"/></td>
                <td align="left" id="cell_3_<s:property value='#stat.count'/>"><s:property value="paraType"/></td>
                <td align="left" id="cell_4_<s:property value='#stat.count'/>"><s:property value="paraTable"/></td>                                      
                <td align="left" id="cell_5_<s:property value='#stat.count'/>"><s:property value="paraColDesc"/></td>                                      
                <td align="left" id="cell_6_<s:property value='#stat.count'/>"><s:property value="paraColValue"/></td>                                      
                <td align="left" id="cell_7_<s:property value='#stat.count'/>"><s:property value="paraFilter"/></td>                                      
                <td align="left" id="cell_8_<s:property value='#stat.count'/>"><s:property value="paraSort"/></td>                                      
            </tr>
        </s:iterator>
    </table>
</div>