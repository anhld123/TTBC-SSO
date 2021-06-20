<%-- 
    Document   : VNP01-01BDD
    Created on : Jun 13, 2016, 9:10:14 AM
    Author     : Administrator
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>VNP01-01BDD</title>
        <link rel="stylesheet" href="DatePicker/default.css" type="text/css">
        <script type="text/javascript" src="DatePicker/jquery-1.12.0.js"></script>
        <script>
            $(function () {
                $("#cmdload").click(function () {
                    if ($("#datepicker").val() == "") {
                        alert("Vui lòng chọn ngày báo cáo.");
                        $("#datepicker").trigger("click");
                    } else {
                        //Thực hiện lấy dữ liệu
                        $.ajax(
                        {
                            type: 'post',
                            url: 'load_m01bdd.action',
                            data: jQuery("#frmain").serialize(),
                            beforeSend: function(data){
                                $('#idReturn').html('<b>Đang tải...</b>');
                            },
                            success: function(data){
                                $('#idReturn').html(data);
                                $('#ReMess').html('<span style="color:red;"><b>(*)Tải dữ liệu thành công.</b></span>');
                            },
                            error: function(){
                                $('#idReturn').html('<span style="color:red;"><b>(*)Tải dữ liệu không thành công</b></span>');
                            }
                        });
                    }
                });

                $("#cmdsave").click(function () {
                    if ($("#datepicker").val() == "") {
                        alert("Vui lòng chọn ngày báo cáo.");
                        $("#datepicker").trigger("click");
                    } else {
                        //Thực hiện lưu số liệu báo cáo
                        $.ajax(
                        {
                            type: 'post',
                            url: 'save_m01bdd.action',
                            data: jQuery("#frmain").serialize(),
                            beforeSend: function(data){
                                $('#idReturn').html(data);
                            },
                            success: function(data){
                                $('#idReturn').html(data);
                                $('#ReMess').html('<span style="color:red;"><b>(*) Cập nhật thành công</b></span>');
                            },
                            error: function(){
                                $('#idReturn').html('<span style="color:red;"><b>(*) Cập nhật không thành công</b></span>');
                            }
                        });
                    }
                });
            });
        </script>
    </head>
    <body style="font-family: tahoma; font-size: 12px;">
        <form id="frmain" name="frmain"> 
            <div style="width: 99%;">
                <b>Ngày BC:</b> <input id="datepicker" name="datepicker" type="text" style="width: 125px;">
                <input type="button" name="cmdload" id="cmdload" value=" Tải dữ liệu " style="height: 25px;"/>
                <input type="button" name="cmdsave" id="cmdsave" value=" Cập nhật " style="height: 25px;"/>
            </div>
            <hr>
            <div style="width: 99%; text-align: center;" id="idReturn" name="idReturn"></div>
            <div id="ReMess" name="ReMess" style="padding-top: 10px;"></div>
        </form>
        <script type="text/javascript" src="DatePicker/zebra_datepicker.js"></script>
        <script type="text/javascript" src="DatePicker/core.js"></script>
    </body>
</html>
