<%-- 
    Document   : risk_detail_customer
    Created on : May 27, 2015, 8:50:46 AM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin chi tiết chi nhánh gửi dữ liệu</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <style>      
            *{
                font: 14px Arial, Helvetica, sans-serif;
            }

            #divChiTieu{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width:99%; 
                height: 99%; 
                margin: 0px auto 10px auto; 
                /*padding: 10px;*/
                background-color: #E2E8C9;
                /*overflow: scroll;*/
                /*border: 1px solid;*/
            }

            #idTitle{
                font-family: Verdana,Arial,Tahoma,Helvetica;
                font-size: 13pt;
                font-weight: bold;
                color: #116600;
            }

            #divSave{
                text-align: right;
            }

            table{
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
            }
            .tbhead th{
                background-color: #DCDCDC;
                text-align: center;
                font-weight: normal;
                padding: 2px;
            }
            .cscontent td{
                background-color: white;
                text-align: left;
                padding-left:5px;
                padding-top:5px;
                padding-bottom: 5px;
            }
            #container_popup{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width: 100%;
                height:96vh; 
                /*border: 1px solid;*/
                padding-left: 0px;     
                background-color: #E2E8C9;
            }
            input{
                border: 0px;
            }

            input[type="text"]
            {
                width: 95%;
            }

            .maPGD{
                width: 300px;
            }

            .tenPGD{
                width: 200px;
            }
            th{
                background-color: #DCDCDC;
                text-align: center;
                font-weight: bold;

                padding: 2px;
            }
        </style>
        <script>
            $(document).ready(function() {

                $('.maPGD').css({"text-align": "left"});
                $('.tenPGD').css({"text-align": "left"});
                $(".maPGD").css({"width": "250px"});
//                $(".maPGD").css({"width": "140px"});
            });
            function closeSelf() {
                window.close();
                return true;
            }
        </script>
    </head>
    <body>
        <div id="container_popup">
            <s:form name="frmdata" id="frmdata" action="khonglamgica.action" theme="simple">
                <s:hidden name="namBc" id="namBc"/>
                <div id="divChiTieu" style="">
                    <span id="idTitle">Thông tin chi tiết chi nhánh gửi dữ liệu rủi ro</span>
                    <hr/>                    
                    <table border="1">
                        <tr>
                            <th>Mã PGD</th>
                            <th>Tên PGD</th>
                            <th>Mã CN</th>
                            <th>User gửi</th>
                            <th>Năm rủi ro</th>
                            <th>Đợt rủi ro</th>
                            <th>Nhóm rủi ro</th>
                            <th>Ngày gửi</th>
                            <th>Số món</th>
                        </tr>
                        <s:iterator value="lstViewHistorySend">
                            <s:if test="sMacn.equalsIgnoreCase('999999')">
                                <tr>
                                    <th style="text-align: center;" colspan="4">Tổng cộng</th>
                                    <th style="text-align: center;"><s:property value="sNamrr"/></th>
                                    <th style="text-align: center;"><s:property value="sDotrr"/></th>
                                    <th style="text-align: center;"><s:property value="sNhomrr"/></th>
                                    <!--<th><s:property value="sNgaytao"/></td>-->
                                    <th style="text-align: right;" colspan="2"><s:property value="sSomon"/></th>
                                </tr>
                            </s:if>
                            <s:else>
                                <tr class="cscontent">
                                    <td style="text-align: center;"><s:property value="sMapgd"/></td>
                                    <td><s:property value="sTenpgd"/></td>
                                    <td style="text-align: center;"><s:property value="sMacn"/></td>
                                    <td><s:property value="sUserid"/></td>
                                    <td style="text-align: center;"><s:property value="sNamrr"/></td>
                                    <td style="text-align: center;"><s:property value="sDotrr"/></td>
                                    <td style="text-align: center;"><s:property value="sNhomrr"/></td>
                                    <td><s:property value="sNgaytao"/></td>
                                    <td style="text-align: right;"><s:property value="sSomon"/></td>
                                </tr>
                            </s:else>
                        </s:iterator>
                    </table>
                    <br/>
                    <hr/>
                    <br/>

                    <div id="test"  style="height:auto; margin: 0 auto; text-align:center;">
                        <sj:submit id="idClose" name="nameClose" value="Close" onclick="closeSelf()"
                                   cssStyle="margin-left:25px;height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"></sj:submit>
                        </div>
                    </div>
            </s:form>
        </div>
    </body>
</html>

