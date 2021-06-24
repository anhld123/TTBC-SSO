<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<!DOCTYPE html>
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
         <style>
            .readonly {
                background: #FFFFC0;        
            }
            .pos_edit_form {
                padding:0px;
                width:30%;    
                background:#f9f9f9;
                border:1px solid #ccc;
                text-align:left;   
                font-family: Arial;
                font-size: 12pt;
            }    

            .metroButtonStyle {
                font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
                display: block;
                color: rgb(255, 255, 255);
                text-decoration: none;
                text-align: center;
                width: 90px;
                height: 20px;
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
            .metroButtonStyle:disabled {
                background: #DCDCDC;
            }

            #divTitle{
            font: 14px Arial, Helvetica, sans-serif;
            font-weight: bold;
            color: #0077b3;
            text-align: center;
            
        }
        </style>     
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
//                $('.D0').css({"text-align": "center"});               
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".SOKU").css({"width": "100%"});
                
                $(".TD_STT").css({"width": "5%"});
                $(".TD_GIATRI").css({"width": "8%"});
                $(".TD_TEN").css({"width": "12%"});
                $(".TD_CHITIEU").css({"width": "20%"});

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>     
        
        <script>
    
        function hienthichitiet(commune_detai,subcommune_detail ) {
            var ht1 = screen.availHeight - 100;
            var wt1 = screen.availWidth -100;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 100;       
            var url = "getDetailKhnvByOneSubCommune.action?commune_detai=" + commune_detai+"&subcommune_detail=" + subcommune_detail;
            popup = window.open(url, '_blank', "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
        
        var max_row = 0;
            
        </script>
        
        <style>                                                
            table.editDelete{
                border-collapse: collapse;
                width: 100%;
                border-color: #999;
            }            
        </style>

    </head>
    <body style="font-family: ">
        <s:form id="id_khnv_view_all_subcommune"  theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                </br>
                <div id="divTitle">
                    TỔNG HỢP NHU CẦU VAY VỐN TÍN DỤNG THEO XÃ
                    <BR>                                
                    <s:if test="!reasonReject.equalsIgnoreCase('AAA')">
                        <font color="red">Nguyên nhân từ chối: <s:property value="reasonReject"/></font>       
                    </s:if> 
                </div>                
                </br>
                <table border="1" class="editDelete1" id="tablesms011" style="width: 99%" >
                    <tr>                                               
                        <!--<th  class="TD_BUTTON1">STT</th>-->      
                        <th  class="TD_GIATRI">Mã thôn</th>    
                        <th class="TD_TEN">Tên thôn</th>
                        <th  class="TD_STT">Mã chỉ tiêu</th> 
                        <th class="TD_CHITIEU">Tên chỉ tiêu</th>
                        <th class="TD_GIATRI">Giá trị</th>
                        <!--<th class="TD_GIATRI">Duyệt</th>-->       
                    </tr>                                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                    
                            <tr> 
                                <s:if test="MA.equalsIgnoreCase('XD00001')">
                                    <td style="text-align:center" class="TD_GIATRI <s:property value="D19"/>" >
                                            <s:property value="D6"/>
                                        </td>

                                    <td align = "left" class="TD_TEN <s:property value="D19"/>" >
                                        <a href="javascript:hienthichitiet('<s:property value="D5"/>' ,'<s:property value="D6"/>' )" class="linkKh">
                                            <s:property value='D9'/> 
                                        </a>
                                    </td>
                                </s:if>
                                    <s:else>
                                        <td>
                                            
                                        </td>
                                        <td>
                                            
                                        </td>
                                    </s:else>    
                                
                                
                                
                                <td style="text-align:center"  class="TD_STT <s:property value="D19"/>">
                                     <s:property value="MA"/>
                                </td>
                                <td align = "left" class="TD_CHITIEU <s:property value="D19"/>">
                                     <s:property value="TEN"/>
                                </td>
                                
                                <td style="text-align:right"  class="TD_GIATRI <s:property value="D19"/>">
                                    <s:property value="D15"/>
                                </td>
<!--
                                <s:if test="D3.equalsIgnoreCase('2B')&& D1.equalsIgnoreCase('000301')">
                                       <td style="text-align:center"  class="TD_GIATRI" >
                                            <a href="javascript:hienthichitiet('<s:property value="D1"/>' )" class="linkKh">
                                            Mở chốt
                                        </a>
                                  </s:if>
                                    <s:if test="D3.equalsIgnoreCase('2B')&& !D1.equalsIgnoreCase('000301')">
                                       <td style="text-align:center"  class="TD_GIATRI" >
                                            <a href="javascript:hienthichitiet('<s:property value="D1"/>' )" class="linkKh">
                                            <font color="red">Chốt</font>
                                        </a>
                                  </s:if>      -->
                        </tr>                                                                                                       
                    </s:iterator>
                </table>                    
        </s:form>
        <div id="luu_thanhcong"></div>
<!--        <script>
            initTable();
        </script>-->
    </body>
    
    
</html>
