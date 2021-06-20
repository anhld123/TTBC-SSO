<%-- 
    Document   : balance-sheet-view
    Created on : Jun 3, 2014, 10:01:58 AM
    Author     : Trung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:head/>
<sj:head/>


<div style="height:450px;overflow:scroll;"
     id = "sysParamView_div">                    
    <table class="tblstyle">
        <tr>
            <td>STT</td>
            <td>Tên bảng </td>
            <td>Mô tả</td>
            <td>Khác biệt</td>
            <td>Cập nhật bằng tay</td>
            <td>Trạng thái</td>          
            <td>Cập nhật</td>            
        </tr>
        <s:iterator value="sysparams" status="trow">
            <tr>                
                <td style="width: 30px; text-align: center;">
                    <s:property value="seqno"/>
                </td>
                <td style="width: 20px;"><s:property value="tablename"/></td>
                <td style="width: 50%;">
                    <p style="font-family: Arial; font-size: 13px; color: #0044cc;">
                        <s:property value="descript"/></p>
                </td>
                <td align="right" style="width: 180px;"> 
                    <p style="text-align: right;"><s:property value="differcount"/></p>
                </td>
                <td style="width: 80px;"><s:property value="manualflg"/></td>
                <td style="width: 60px;" align="center">
            <center><s:property value="updateflg"/></center>
                </td>      
                <td style="width: 30px; text-align: center;">
                    <s:url id="updateUrl" value="sysncSysParam.action">
                        <s:param name="updateObjId" value="seqno"/>                        
                    </s:url>
                    <sj:a href="%{updateUrl}"
                          targets="sysParamMessage_div"   
                          onBeforeTopics="display-loading"
                          onCompleteTopics="hide-loading"
                          theme="simple"><u>sync</u></sj:a>
                    </td>                
                </tr>    
        </s:iterator>
        <script>
            $.subscribe('display-loading', function(event, data) {    
                $("#sysParamMessage_div").empty();
                $("#sysParamMessage_div").hide();
                $("#waitingImageDiv").show();
            });
            $.subscribe('hide-loading', function(event, data) {
                $("#waitingImageDiv").hide();
                $("#sysParamMessage_div").show();
            });
        </script>        
    </table>    
</div>      
<hr/>

<div id="waitingImageDiv" style="display: none;">
    <center> 
        <img id="waitingImage" height="60px;" width="60px;" src='img/ajax-loader_1.gif' border='0' >
    </center>
</div>
<div id="sysParamMessage_div">    
</div>
