<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<style>
    .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;
        width: 90px;
        height: 26px;
        padding: 5px;
        margin: 5px 0px 0px 5px;
        font-size: 12px;
        background: none repeat scroll 0 0 #808080;
        color: #FFF;
        border: 0px none;
        border-radius: 1px 1px 1px 1px;
        outline: 0px none;
    }
    .metroButtonStyle:hover {
        background: #018c3b;
    }
    .metroButtonStyle:active {
        background: #DCDCDC;
    }
    .ui-dialog{
        font-size: 11px;
    } 
    label.editdialog
    {
        font-weight:normal;
        color:#000000;
        letter-spacing:1pt;
        word-spacing:2pt;
        font-size:12px;
        text-align:left;
        font-family:arial, helvetica, sans-serif;
        line-height:1;
    }
    input.editdialog 
    {
        font-weight:normal;
        font-size:12px;
        text-align:left;
        font-family:arial, helvetica, sans-serif;
    }
    p.editdialog {
        font-weight:normal;
        color:#000000;
        letter-spacing:1pt;
        word-spacing:2pt;
        font-size:12px;
        text-align:left;
        font-family:arial, helvetica, sans-serif;
        line-height:1;
    }
</style>
<s:form id="editErrorForm" theme="simple">  
    <table>
        <tr>
            <td><s:label value="Bạn không được phép sửa cân đối GL" cssClass="editdialog"/></td>               
        </tr>             
    </table>
</s:form>