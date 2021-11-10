<%-- 
    Document   : index
    Created on : Nov 4, 2021, 1:09:48 PM
    Author     : WELCOME
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<s:head/>
<sj:head/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--        <script type="text/javascript" src="DMChitieu/js/jquery-ui.js"></script>-->
<!--        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/jquery.number.js"></script>-->
        <style>
            *{
                font-family: Tahoma, Arial, Helvetica, sans-serif;
                font-size: 13px;
            }
            #tblTable {

                border-collapse: collapse;
                width: 100%;
            }

            #tblTable td, #tblTable th {
                border: 1px solid #ddd;
                padding: 4px;
            }

            #tblTable tr:nth-child(even){background-color: #f2f2f2;}

            #tblTable tr:hover {background-color: #ddd;}

            #tblTable th {
                padding-top: 6px;
                padding-bottom: 6px;
                text-align: left;
                background-color: #06713F;
                color: white;
            }
            .clsTitle, .clsHeader{
                width: 100%;
                text-align: center;
            }
            .clsTitle{
                margin-bottom: 5px;
                padding: 8px;
                background-color: lightgray;
                width:98.8%
            }
            .clsSoso{
                width: 99%;
                margin: 0px 3px 0px 3px;
                height: 100%;
                border: 0px;
                background-color: transparent;
                outline: none;
            }
            select{
                min-width: 155px;
            }
            .clsChon{
                text-align: center;
            }
        </style>
    </head>
    <body>
        <form id="frmMain">
            <div style="margin: 12px;">
                <div class="clsHeader"><h1>KẾT QUẢ HUY ĐỘNG TIẾT KIỆM</h1></div>
                <div class="clsTitle">
                    <b>Ngày báo cáo:</b> <input type="date" id="dtNgaybc" name="dtNgaybc">
                    <b>Cán bộ:</b> 
                    <select id="lstCanBo" name="lstCanBo">
                        <option value="000000">----Chọn cán bộ----</option>
                        <s:iterator value="lstCanBo">
                            <option value='<s:property value="MaCB"/>'><s:property value="TenCB"/></option>
                        </s:iterator>
                    </select>
                    <b>Chỉ tiêu được giao:</b> <input type="number" id="txtChitieu" name="txtChitieu" value=0>
                    <input type="button" value="Tải dữ liệu" id="cmdTaiDL" name="cmdTaiDL">
                    <input type="button" value="Lưu dữ liệu" id="cmdLuuDL" name="cmdLuuDL">
                </div>
                <div id="viewData"></div>
            </div>
        </form>
        <script>
            $(document).ready(function () {
                //Tải dữ liệu
                $("#cmdTaiDL").click(function () {
                    $('#viewData').html('<img src="img/loading.gif"/>');
                    var url;
                    var reportDate = $("#dtNgaybc").val();
                    var staffId = $('#lstCanBo option:selected').val();
                    alert(reportDate + staffId);
                    url = "HDTK_Viewdata?ReportDate="+reportDate+"&Username="+staffId+"&ReportGrade=3";
                    //sdata = jQuery("#frmMain").serialize();
                    $.ajax({
                        type: "GET",                                        
                        url: url,
                        success: function (res) {
                            //alert(res);
                            $("#viewData").html(res);
                        },
                        error: function (res) {
                            alert("Lỗi xử lý!");
                        }
                    });
                });

                //Lưu dữ liệu
                $("#cmdLuuDL").click(function () {
                    var url, sdata;
                    url = "savedata.action";
                    sdata = jQuery("#frmMain").serialize();
                    $.post(url, sdata, function (data) {
                        alert(data);
                    });
                });

                function checkAll() {
                    var ChkAll = document.getElementById("chkChon").length();
                    alert(ChkAll);
                }
                ;
            });
        </script>
    </body>
</html>