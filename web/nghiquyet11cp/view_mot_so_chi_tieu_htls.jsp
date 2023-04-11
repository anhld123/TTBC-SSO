<%-- 
    Document   : ViewData
    Created on : May 17, 2022, 9:56:22 AM
    Author     : NGUYEN PHU VINH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<style>
    #subTable {
        font-size: 16px;
        font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
    }
    #subTable th{
        background-color: #DA8028;
        color: white;
    }

    #subTable th, #subTable td {
        border: 1px solid gray;
    }

    #subTable tr:nth-child(even){background-color: #f2f2f2;}

    #subTable tr:hover {background-color: #FFE47A;}

    .txtPublic{
        width: 85px;
    }
    .ui-datepicker-trigger{
        height: 100%;
    }
    .txtBody{
        text-align: center;
    }
    .txtBody > .ui-datepicker-trigger{
        display: none;
    }
    td.hdtitle {
        position: sticky;
        top: 0;
        z-index: 10;
    }
    .ThanhVien{
        display: None;
    }
</style>
<div style="overflow:scroll; width: 100%; justify-content: center; display: flex;">
    <table id="subTable" style="z-index: 1; width: 99%;">
        <thead>
            <tr>
                <th style="width: 30%; padding: 5px;">Chỉ tiêu</th>
                <th>Giá trị</th>
            </tr>
        </thead>
        <tbody>
            <s:if test="Grade.equalsIgnoreCase('1')">
                <s:iterator value="lstData" status="idxRows">
                    <tr>
                        <td style="display:none;"><input type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].KHOA" value="<s:property value='KHOA'/>"></td>
                        <td style="padding-left: 10px;"><s:property value='TEN'/></td>
                        <td><input style="width: 100%; background-color: transparent;" type="text" name="lstData[<s:property  value='%{#idxRows.index}' />].D1" value="<s:property value='D1'/>"></td>
                        </td>
                    </tr>
                </s:iterator>
            </s:if>
            <s:else>
                <s:iterator value="lstData" status="idxRows">
                    <tr>
                        <td style="padding: 10px;"><s:property value='TEN'/></td>
                        <td style="padding: 8px;"><s:property value='D1'/></td>
                    </tr>
                </s:iterator>
            </s:else>

        </tbody>
    </table>
</div>

