<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head/>
<style>
    .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;        
        width: 90px;
        height: 25px;
        padding: 5px;
        margin: 0px 0px 0px 0px;
        font-size: 12px;
        background: none repeat scroll 0 0 #808080;
        color: #FFF;
        border: 0px none;
        border-radius: 1px 1px 1px 1px;        
    }
    .metroButtonStyle:hover {
        background: #018c3b;
    }
    .metroButtonStyle:active {
        background: #DCDCDC;
    }
</style>
<script>
    $.subscribe('before-next', function(event, data) {
        $("#contentDiv").empty();
        $("#contentDiv").hide();
        $("#loadingImageDiv").show();
    });

    $.subscribe('after-next', function(event, data) {
        $("#loadingImageDiv").hide();
        $("#contentDiv").slideDown('slow');
    });
</script>
<div style="padding-left: 10px;">
    <div  style="
          float: top;          
          z-index:1;">    
        <p style="text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            Cập nhật thông báo
        </p>
        <s:form theme="simple" id="viewVbspNewsForm">        
            <table width="85%" border="3px" 
                   style="border-collapse: collapse; border-color: #DCDCDC;
                   border-style: solid;">
                <tr style="line-height: 25px;">
                    <td style="width: 9.4%;">
                        Thông báo:
                    </td>        
                    <td style="width: 20%;">
                        <s:url var="buildVbspNewsComboUrl" action="buildVbspNewsCombo"></s:url>
                        <sj:select href="%{buildVbspNewsComboUrl}" 
                                   name="newsId"
                                   id="newsId"
                                   list="vbspNewsList"    
                                   onChangeTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false" 
                                   headerKey="0"
                                   headerValue="--- Chọn thông báo ---" 
                                   theme="simple"
                                   ></sj:select>                       
                        </td>               
                        <td style="width: 10%;" align="center">
                        <s:url var="viewUrl" action="viewVbspNews.action"></s:url>                                                                              
                        <sj:a id="addService1"  href="%{viewUrl}" targets="contentDiv"
                              formIds="viewVbspNewsForm"
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next"><u>&gt;&gt;Truy vấn</u></sj:a>  
                              &nbsp;         
                        <s:url var="deleteUrl" action="deleteVbspNews.action"></s:url>                                                                              
                        <sj:a id="addService2"  href="%{deleteUrl}" targets="contentDiv"
                              formIds="viewVbspNewsForm"
                              onBeforeTopics="before-next"
                              onCompleteTopics="after-next"><u>&gt;&gt;Xoá thông báo</u></sj:a>        
                        </td>
                    </tr>                     
                </table>
        </s:form>        
    </div>
    <div id="contentDiv"/>       
</div>

