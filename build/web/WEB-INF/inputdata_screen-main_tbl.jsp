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
        width: 80%;
        font: 13px Arial, Helvetica, sans-serif; 
        line-height: 28px;
    }
</style>

<script>
    function callDirectLink(fullname) {
        var ht = screen.availHeight;
        var wt = screen.availWidth;
        var resize = window.open(fullname + "?random=" + Math.random(), 
        "IMS_REPORTS", "height=" + ht + ",width=" + wt
                + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        if (navigator.userAgent.indexOf('Chrome') !== -1
                && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
            resize.resizeBy(wt, ht);
        } else {
            resize.resizeTo(wt, ht);
        }
        resize.focus();
    }
</script>    

<table border="1px" class="table_2">
    <tr class="tbhead">
        <td>Báo cáo</td>
        <td>Tên viết tắt</td>
        <td>Mô tả</td>
        <td>Chuẩn bị số liệu</td>
        <td>Phê duyệt</td>                                    
    </tr>
    <s:iterator value="manualInputObjects">
        <tr class="cscontent">
            <td><s:property value="code"/></td>
            <td><s:property value="shortDesc"/></td>
            <td><s:property value="fullDesc"/></td>
            <td align="center">
                <a href="javascript:callDirectLink('<s:property value="link"/>_input.action')" 
                   style="text-decoration:none;" 
                    <s:property value="%{getPermit(permit,2)}"/>                   
                    >
                    <u>chọn</u></a>                                            
            </td>
            <td align="center">
                <a href="javascript:callDirectLink('<s:property value="link"/>_authorize.action')" 
                   style="text-decoration:none;" 
                   <s:property value="%{getPermit(permit,4)}"/>                   
                   >
                    <u>chọn</u></a>                                            
            </td>                                       
        </tr>
    </s:iterator>
</table>
