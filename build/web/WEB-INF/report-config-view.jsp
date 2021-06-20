<%-- 
    Document   : balance-sheet-view
    Created on : Jun 3, 2014, 10:01:58 AM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>
<style>
    .reportTbl {
        margin:0px;padding:0px;
        width:100%;
        border:1px solid #000000;
        -moz-border-radius-bottomleft:0px;
        -webkit-border-bottom-left-radius:0px;
        border-bottom-left-radius:0px;
        -moz-border-radius-bottomright:0px;
        -webkit-border-bottom-right-radius:0px;
        border-bottom-right-radius:0px;
        -moz-border-radius-topright:0px;
        -webkit-border-top-right-radius:0px;
        border-top-right-radius:0px;
        -moz-border-radius-topleft:0px;
        -webkit-border-top-left-radius:0px;
        border-top-left-radius:0px;
    }.reportTbl table{
        border-collapse: collapse;
        border-spacing: 0;
        width:100%;
        /*        height:100%;*/
        margin:0px;padding:0px;
    }.reportTbl tr:last-child td:last-child {
        -moz-border-radius-bottomright:0px;
        -webkit-border-bottom-right-radius:0px;
        border-bottom-right-radius:0px;
    }
    .reportTbl table tr:first-child td:first-child {
        -moz-border-radius-topleft:0px;
        -webkit-border-top-left-radius:0px;
        border-top-left-radius:0px;
    }
    .reportTbl table tr:first-child td:last-child {
        -moz-border-radius-topright:0px;
        -webkit-border-top-right-radius:0px;
        border-top-right-radius:0px;
    }.reportTbl tr:last-child td:first-child{
        -moz-border-radius-bottomleft:0px;
        -webkit-border-bottom-left-radius:0px;
        border-bottom-left-radius:0px;
    }.reportTbl tr:hover td{

    }
    /*    .reportTbl tr:nth-child(odd){ background-color:#e5e5e5; }
        .reportTbl tr:nth-child(even)    { background-color:#ffffff; }*/
    .reportTbl td{
        vertical-align:middle;
        border:1px solid #000000;
        border-width:0px 1px 1px 0px;
        /*text-align:left;*/
        padding:1px;
        font-size:11px;
        font-family:Verdana, Arial, Helvetica, sans-serif;
        font-weight:normal;
        color:#000000;
    }
    .reportTbl tr:last-child td{
        border-width:0px 1px 0px 0px;
    }.reportTbl tr td:last-child{
        border-width:0px 0px 1px 0px;
    }.reportTbl tr:last-child td:last-child{
        border-width:0px 0px 0px 0px;
    }       
    .alignLeft{
        text-align: right;        
    }

    .alignRight{
        text-align: right;        
    }
    .alignCenter {
        text-align: center;
    }    
</style>
<script>
    $.subscribe('before-next', function(event, data) {
        alert('clicked');
    });
</script>
<div style="background:#f9f9f9;
     border:1px solid #ccc;">        
    <s:form id="reportGroupViewForm" theme="simple"
            action="loadParam.action" enctype="multipart/form-data">
        <table style="width: 100%;">
            <tr>
                <td colspan="2">                    
                    <table class="reportTbl">                        
                        <tr>
                            <td style="font-weight: bold;">
                                Mã báo cáo:                             
                                <s:property value="autoGenerateRptCode"></s:property>
                                </td>                            
                                <td> Tên viết tắt(*):                             
                                <s:textfield name="shortcutName" />
                            </td>                            
                            <td> Đơn vị(*):                                                                 
                                <s:select headerKey="-1"
                                          list="{1,1000,1000000}" 
                                          name="reportUnit" 
                                          value="#reportUnit"/>
                            </td>                            
                            <td> Kỳ báo cáo(*): &nbsp;                             
                                <s:textfield name="reportTerm" />
                            </td>
                            <td>Phân loại báo cáo(*):                                     
                                <s:select headerKey="-1"
                                          list="#{'1':'01','2':'02','3':'03'}" 
                                          name="reportType" 
                                          value="%{getMappingCode(reportType,2)}"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="3"> Mô tả(*): &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;                             
                                <s:textfield name="reportDescript" size="100"/>
                            </td>                            
                            <td>Cấp báo cáo(*):        
                                <s:select headerKey="-1"
                                          list="#{'1':'/01/','2':'/02/','3':'/03/',
                                                  '4':'/01/02/','5':'/02/03/','6':'/01/02/03/'}" 
                                          name="reportGrade" 
                                          value="%{getMappingCode(reportGrade,3)}"/>
                            </td>
                            <td>
                                Tên file sử dụng(*): &nbsp;&nbsp;
                                <s:textfield name="generatedName" />
                            </td>
                        </tr>
                    </table>                           
                </td>
            </tr>
            <tr>
                <td>File báo cáo:</td>
                <td><s:file label="File 1" name="fileUpload" size="65" /></td>
            </tr>
            <tr>
                <td>File báo cáo phụ 01:</td>
                <td><s:file label="File 2" name="fileUpload" size="65" /></td>
            </tr>    
            <tr>    
                <td>File báo cáo phụ 02:</td>
                <td><s:file label="File 2" name="fileUpload" size="65" /></td>                
            </tr>
            <tr>    
                <td>File báo cáo phụ 03:</td>
                <td><s:file label="File 3" name="fileUpload" size="65" /></td>                
            </tr>
            <tr>
                <td colspan="2"><hr/></td>
            </tr>
            <tr>
                <td colspan="2" align="right">
                    <sj:submit value="Tải tham số" targets="reportParamDiv"></sj:submit>
                    </td>
                </tr>                                                    
            </table> 
        <s:hidden name="selectedGroup"/>
        <s:hidden name="autoGenerateRptCode"/>
        <s:hidden name="selectedReport"/>
    </s:form>                                    
</div>        
<div id="reportParamDiv" style="padding-left: 5px; width: 100%;"/>       