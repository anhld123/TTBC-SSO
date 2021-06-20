<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<style>
    a.disabled {
        color: gray;
    }
</style>

<script>
    var dem = 0;
    
    function callDirectLink(fullname) {
        var ht = screen.availHeight;
        var wt = screen.availWidth;
        
        var isOk = check_date();
        
        if (isOk) {
        
        var v_report_dt = document.getElementById(
                'selectedrptDate').value.toLocaleString();
        var v_period = document.getElementById(
                'period_id').value;
        var bool_reload_flg = document.getElementById(
                'reload_data_flag').checked;
        var reload_flg = 'N';
        
         if (bool_reload_flg === true)
            reload_flg ='Y';
        
        if (!dem)
            var dem = 0;
        
        if (dem > 0){
            var r = confirm('Bạn có chắc chắn muốn tạo lại dữ liệu');
            if (r === false){
                return;
            }
        }
        
        var username = document.getElementById('username_ID').value;    
               
        var resize = window.open("eom_view_sub_task?task=" + fullname + "&report_dt=" + v_report_dt 
                +"&period="+v_period 
                +"&reload_data_flg="+reload_flg
                +"&username="+username
                +"&random=" + Math.random(), 
        "IMS_REPORTS_FRM1", "height=" + ht + ",width=" + wt
                + ",left=0,top=0,directories=no,status=no,menubar=no,\n\
personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        if (navigator.userAgent.indexOf('Chrome') !== -1
                && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
            resize.resizeBy(wt, ht);
        } else {
            resize.resizeTo(wt, ht);
        }
        resize.focus();
        
        dem++;
    }else {
        return;
    }
    }
</script>    

<table border="0" class="table_2">
    <tr class="tbhead">
        <td>Thứ tự</td>
        <td>Task</td>
        <td>Mô tả</td>
        <td>Ghi chú</td>
        <td>Trạng thái</td>
        <td>Link</td>                                    
    </tr>
    <s:iterator value="eomTasks" status="cnt">
        <tr class="cscontent">
            <td align="center"><s:property value="#cnt.count"/></td>
            <td><u><s:property value="task_no"/></u></td>
            <td>
                <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; "> 
                <s:property value="descript"/> </font>
            </td>
            <td><s:property value="comment"/></td>
            <td align="center">
                <s:set name="st_string_ID" value="%{status}" />              
                <s:hidden value="%{#st_string_ID}"></s:hidden>
                <s:if test="%{#st_string_ID.equalsIgnoreCase('D')}">
                    <p style="background-color: #a4ecab;">
                        <s:property value="%{get_status_descript(status)}"/>   
                    </p>
                </s:if>
                <s:elseif test="%{#st_string_ID.equalsIgnoreCase('E')}">
                    <p style="background-color: #d14; font-weight: bold;">
                        <s:property value="%{get_status_descript(status)}"/>   
                    </p>
                </s:elseif >
                <s:elseif test="%{#st_string_ID.equalsIgnoreCase('P')}">
                    <p style="background-color: #aaffff;">
                        <s:property value="%{get_status_descript(status)}"/>   
                    </p>
                </s:elseif >
                <s:else>
                    <p style="background-color: #ffff99; font-weight: bold;">
                        <s:property value="%{get_status_descript(status)}"/>   
                    </p>
                </s:else>                                                                         
            </td>
            <td align="center">
                 <a href="javascript:callDirectLink('<s:property value="task_no"/>')" 
                   style="text-decoration:none;"                                  
                   >
                    <u>chọn</u></a>                                                
            </td>
        </tr>
    </s:iterator>
</table>
