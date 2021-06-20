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
        var ht = screen.availHeight*4/5;
        var wt = screen.availWidth*4/5;
        var resize = window.open(fullname + "&random=" + Math.random(), "IMS_REPORTS", "height=" + ht + ",width=" + wt
                + ",left=0,top=0,directories=no,status=no,statusbar=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,addressbar=no,border=no");
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
        width: 80%;
        font: 13px Arial, Helvetica, sans-serif; 
        line-height: 28px;
    }
    
    .tbhead{
        background-color: #005580;
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
    
    
    a.disabled {
        color: gray;
    }
</style>


<div style="padding-left: 5px;">        
    <table border="1px" class="table_2">
        <tr class="tbhead">
            <td>Mã</td>
            <td>Tên viết tắt</td>
            <td>Mô tả</td>
            <td>Link</td>            
        </tr>
        <s:iterator value="manualInputObjects">
            <tr class="cscontent">
                <td><p style="color: #0000FF;"><s:property value="code"/></p></td>
                <td><s:property value="shortDesc"/></td>
                <td><s:property value="fullDesc"/></td>
                <td align="center">
                    <a href="javascript:callDirectLink('<s:property value="link"/>?permit=<s:property value="permit"/>')" 
                       style="text-decoration:none;"    >                    
                        <u>chọn</u></a>              
                       
                </td>            
                
<!--                <s1:property value="%{getPermit(permit,2)}"/>                   -->
                       
            </tr>
        </s:iterator>
    </table>

</div>
