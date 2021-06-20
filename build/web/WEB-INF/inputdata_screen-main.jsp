<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>

<script>
    //CuongBM: 15-May-14
    //Desc: khi thay doi selectbox thi goi den su kien click
    $(function() {
        //Khi thay doi
        $('#groupId').change(function() {
            $("#loadParameter").trigger("click");
        });
        //Khi load xong
        $('#groupId').ready(function() {
            $("#loadParameter").trigger("click");
        });
    });

    $.subscribe('before-next', function(event, data) {
        $("#divListParams").empty();
        $("#divListParams").hide();
    });

    $.subscribe('after-next', function(event, data) {
        // Effect cho the div
        $("#divListParams").show();
        //1. Lay danh sach cac truong datetimepicker
        var allDate = $(".hasDatepicker").map(function() {
            return $(this).attr("name");
        }).get();

        //2. Them input mask
        for (var i = 0; i < allDate.length; i++) {
            new DateMask("dd/MM/yyyy", allDate[i].toString());
        }
    });

    function callDirectLink(fullname) {
        var ht = screen.availHeight;
        var wt = screen.availWidth;
        var resize = window.open(fullname + "?random=" + Math.random(), "IMS_REPORTS", "height=" + ht + ",width=" + wt
                + ",left=0,top=0,directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        if (navigator.userAgent.indexOf('Chrome') !== -1
                && parseFloat(navigator.userAgent.substring(navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
            resize.resizeBy(wt, ht);
        } else {
            resize.resizeTo(wt, ht);
        }
        resize.focus();
    }
    ;
</script>

<style type="text/css">
    *{
        margin: 0px;
    }
    .main_div{
        padding:0px;
        width:100%;    
        background:#f9f9f9;
        border:1px solid #ccc;
        text-align:left;   
        font-family:Arial;    
        font-size: 10pt;
    }
    table.table_1{
        border-style: solid;
        border-collapse: collapse;
        background:#f9f9f9;
        width: 100%;
        font: 13px Arial, Helvetica, sans-serif;             
    }
    table.table_2{
        border-style: solid;
        border-collapse: collapse;
        width: 100%;
        font: 13px Arial, Helvetica, sans-serif; 
        line-height: 28px;
    }
    .tbhead{
        background-color: #5e5e55;
        font-weight: bold;
        color: #fff;
        text-align: center;            
    }
    .cscontent td{
        padding-left:5px;
        /*            line-height: 25px;
                    height: 25px;*/
        font: 13px Arial, Helvetica, sans-serif; 
    }
    .cscontent:hover{
        background-color: #ffff99;
    }
</style>


<div style="padding-left: 5px;">        
    <div id="main_form_div" class="main_div">    
        <s:form id="rpt_form" theme="simple" action="doReportFilter_Manual">
            <table border="0" cellspacing="0" cellpading="0" height="100%" width="100%"
                   class="table_1">
                <tr>
                    <td width="150">Nhóm báo cáo:                                       
                        <s:url var="buildGroupComboUrl" action="doGroupReportFilter_Manual.action"></s:url>
                        <sj:select href="%{buildGroupComboUrl}" 
                                   name="groupId"
                                   id="groupId"
                                   list="lstRptGroupObj"    
                                   onChangeTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false"                                                               
                                   theme="simple"    
                                   headerKey="ALL"
                                   headerValue="--- Tất cả ---"
                                   ></sj:select>
                        </td>        
                    </tr>                    
                </table>       
        </s:form>            
    </div>    
    <div align="right" >
        <sj:a id="loadParameter" formIds="rpt_form" targets="divListParams" 
              indicator="loadingImage_next" href="#" onBeforeTopics="before-next" 
              onCompleteTopics="after-next"></sj:a>
        </div>         
    <sj:div id="divListParams"></sj:div>
</div>
