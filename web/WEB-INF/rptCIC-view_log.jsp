<%-- 
    Document   : anti-money_laundering-view
    Created on : Jun 3, 2014, 10:01:58 AM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<html>
    <head>    
<!--        <link rel="stylesheet" type="text/css" media="all" href="css/tooltip.css">-->
    </head> 
    <style>
        .cicViewStl {
            margin:0px;padding:0px;
            width:99%;
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
        }.cicViewStl table{
            border-collapse: collapse;
            border-spacing: 0;
            width:100%;
            /*        height:100%;*/
            margin:0px;padding:0px;
        }.cicViewStl tr:last-child td:last-child {
            -moz-border-radius-bottomright:0px;
            -webkit-border-bottom-right-radius:0px;
            border-bottom-right-radius:0px;
        }
        .cicViewStl table tr:first-child td:first-child {
            -moz-border-radius-topleft:0px;
            -webkit-border-top-left-radius:0px;
            border-top-left-radius:0px;
        }
        .cicViewStl table tr:first-child td:last-child {
            -moz-border-radius-topright:0px;
            -webkit-border-top-right-radius:0px;
            border-top-right-radius:0px;
        }.cicViewStl tr:last-child td:first-child{
            -moz-border-radius-bottomleft:0px;
            -webkit-border-bottom-left-radius:0px;
            border-bottom-left-radius:0px;
        }.cicViewStl tr:hover td{

        }
        .cicViewStl tr:nth-child(odd){ background-color:#e5e5e5; }
        .cicViewStl tr:nth-child(even)    { background-color:#ffffff; }.BalanceSheetStyle td{
            vertical-align:middle;
            border:1px solid #000000;
            border-width:0px 1px 1px 0px;
            /*text-align:left;*/
            padding:7px;
            font-size:11px;
            font-family:Verdana, Arial, Helvetica, sans-serif;
            font-weight:normal;
            color:#000000;
        }.cicViewStl tr:last-child td{
            border-width:0px 1px 0px 0px;
        }.cicViewStl tr td:last-child{
            border-width:0px 0px 1px 0px;
        }.cicViewStl tr:last-child td:last-child{
            border-width:0px 0px 0px 0px;
        }
        .cicViewStl tr:first-child td{
            background:-o-linear-gradient(bottom, #999999 5%, #b2b2b2 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #b2b2b2) );
            background:-moz-linear-gradient( center top, #999999 5%, #b2b2b2 100% );
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#999999", endColorstr="#b2b2b2");	background: -o-linear-gradient(top,#999999,b2b2b2);

            background-color:#999999;
            border:0px solid #000000;
            text-align:center;
            border-width:0px 0px 1px 1px;
            font-size:12px;
            font-family:Verdana, Arial, Helvetica, sans-serif;
            font-weight:bold;
            color:#ffffff;
        }
        .cicViewStl tr:first-child:hover td{
            background:-o-linear-gradient(bottom, #999999 5%, #b2b2b2 100%);	background:-webkit-gradient( linear, left top, left bottom, color-stop(0.05, #999999), color-stop(1, #b2b2b2) );
            background:-moz-linear-gradient( center top, #999999 5%, #b2b2b2 100% );
            filter:progid:DXImageTransform.Microsoft.gradient(startColorstr="#999999", endColorstr="#b2b2b2");	background: -o-linear-gradient(top,#999999,b2b2b2);

            background-color:#999999;
        }
        .cicViewStl tr:first-child td:first-child{
            border-width:0px 0px 1px 0px;
        }
        .cicViewStl tr:first-child td:last-child{
            border-width:0px 0px 1px 1px;
        }

        .cicViewStl td, th, font {
            font-family: Tahoma;
            font-size: 12px;
            height: 25px;
        }

        .ui-dialog{
            font-size: 12px;
        } 

        .alignRight{
            text-align: right;        
        }

        .alignCenter {
            text-align: center;
        }  
        input.NumberDisplayStyle {                               
            /*        height: 20px;*/
            font-weight:bold;
            text-align: right;        
            padding: 0px 0px 0px 0px;
            border-style: solid;
            border-width: 1px;
            font-size: 9pt;
            height: 22px;
            width: 140px;
            /*        font-family:Arial,Georgia,Serif;*/
        }
        input.StringTextDisplay {
            background: #DCDCDC;   
            padding: 0px;        
            font-family:Arial,Georgia,Serif;
            font-size: 9pt;
            font-weight:bold;
            border-style: solid;
            border-width: 1px;
            height: 22px;
        }
    </style>
    <script>
    </script>
    <body>
        <div id="cicview_div" style="padding-left: 5px;">
            <div style="float:left; " id="mainview_div">     
            </div>    
            <div id="tableViewDiv" class="cicViewStl" >                        
                <table>                                    
                    <tr>
                        <td>STT</td>
                        <td>Đơn vị</td>
                        <td>Mô tả</td>
                        <td>Ngày báo cáo</td>
                        <td>Mã lỗi</td>
                        <td>Mô tả lỗi</td>
                        <td>Tổng số bản ghi</td>                
                        <td>Trạng thái</td>                
                    </tr>
                    <s:iterator value="logList" status="stat">
                        <tr>
                            <td align='center'><u><s:property value="#stat.count"/></u></td>
                            <td><u><strong><s:property value="pos_cd"/></strong></u></td>
                            <td>
                                <a href="#" class="tooltip right"
                                   data-tool="<s:property value='descript'/>"><s:property value='descript'/></a>                                
                            </td>
                            <td><s:property value="report_dt"/></td>                                       
                            <td class="alignRight">                   
                                <u><s:property value="error_cd"/></u>
                            </td>
                            <td>                   
                                <s:property value="error_msg"/>
                            </td>
                            <td class="alignRight">                   
                                <u><fmt:formatNumber type="number" 
                                                  maxFractionDigits="3" value="${record_total}" /></u>
                            </td>
                            <td align='center'>                   
                                <u><s:property value="rec_st"/></u>
                            </td>
                        </tr>
                    </s:iterator>
                </table>        
            </div>            
        </div>
    </body>
</html>