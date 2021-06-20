<%-- 
    Document   : suachitieu_xa
    Created on : Sep 22, 2016, 3:09:47 PM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<style type="text/css">

    table{
        border-style: solid;
        border-collapse: collapse;
        /*width: 100%;*/
        /*line-height: 19px;*/
    }
    .tbhead th{
        background-color: #5e5e55;
        font-weight: bold;
        color: #fff;
        text-align: center;
        padding: 5px;
    }
    .cscontent td{
        padding-left:5px;
        padding-top:5px;
        padding-bottom: 5px;
    }
    .tblmain tr td{
        font-weight: bold;
        color: #018c3b;
    }

    /*    input{
            border: 0px;
        }*/

    .BOLD 
    {
        font-weight: bold;
        font-size: 13px;
        /*width: 95%;*/
    }

    .ITALIC 
    {
        font-style: italic;
        font-size: 12px;
        /*width: 95%;*/
    }

    .BOLD a
    {
        font-weight: bold;
        font-size: 13px;
        width: 95%;
    }

    .ITALIC a
    {
        font-style: italic;
        font-size: 12px;
        width: 95%;
    }

    /*    input[type="text"]
        {
            width: 95%;
        }*/

    /*    input[type="button"]
        {
            margin-left: 3px;
        }*/
    /*
        .parameter{
            border: 1px solid black;
            width: 50%;
        }*/

    #posCD, #namBc, #maCn, #userId{
        width: 70px;
    }

    a.linkKh{
        color: #116600;
        text-decoration: none;            
    }
    a.linkKh:hover
    {
        color: #5494ea;
        text-decoration: underline;
    }
    a.linkKh:visited
    {
        color: #ab59a6;
    }
</style>
<label id="idtitle" style="font-size: 14px; color: #18ab29; font-weight: bold">
    Sửa/xóa chỉ tiêu kế hoạch tín dụng xã
</label>
<div id="message_suc_err"></div>
<p></p>
<table border="1px" id="tableKhnv" class="tableKhnv">
    <tr class="tbhead">
        <th>STT</th>
        <th>Tên chỉ tiêu</th>
        <th>Sửa chỉ tiêu</th>
        <th>Xóa chỉ tiêu</th>
    </tr>
    <s:iterator value="lstChitieu" >
        <tr class="cscontent">
            <td class="<s:property value='KH_FONTWEIGHT'/>">
                <s:property value='KH_STT'/>
            </td>
            <td class="<s:property value='KH_FONTWEIGHT'/>">
                <s:property value='KH_CHI_TIEU'/>
            </td>
            <td class="<s:property value='KH_FONTWEIGHT'/>">
                <s:url id="idsuachitieu" value="Suachitieu.action">
                    <s:param name="ma_chitieu" value="KH_MA_CT"/>
                </s:url>
                <sj:a targets="navParam2"  href="%{idsuachitieu}" formIds="idchitieuxa" cssClass="linkKh">Sửa chỉ tiêu</sj:a>
                </td>
                <td class="<s:property value='KH_FONTWEIGHT'/>">
                <s:url id="idXoachitieu" value="Xoachitieu.action"  escapeAmp="false"> 
                    <s:param name="ma_chitieu" value="KH_MA_CT"/>
                    <%--<s:param name="loai_nv" value="loai_nv"/>--%>
                </s:url>
                <sj:a targets="message_suc_err"  href="%{idXoachitieu}" formIds="idchitieuxa" onClickTopics="onClickDel">Xóa</sj:a>
                </td>
            </tr>
    </s:iterator>
</table>