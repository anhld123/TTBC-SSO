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
<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <style>
        *{
            font-family: tahoma;
            font-size: 12px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            /*height: 1000px;*/
        }

        table thead { position: sticky; top: 0; z-index: 1; }

        th, td {
            text-align: left;
            padding: 8px;
            border: 1PX solid #f2f2f2;
            /*text-align: center;*/
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #04AA6D;
            color: white;
        }
        .sttCol>td{
            font-style: italic;
        }
        .clss-body-ngnhan{
            box-sizing: content-box;
            padding: 5px;
        }
        textarea
        {
            border:1px solid #000;
            width:100%;
            height: 100px;
        }
        .clss-lable{
            font-weight: bold;
        }
        .cls-over{
            overflow-y: scroll;
            height: 76vh;
        }
        .cmd, input[type="submit"]{
            padding: 5px;
            background-image: linear-gradient(#f2f2f2,#c2c2c2);
            border: 1px solid #c2c2c2;
            border-radius: 2px;
        }
        
        .CLS-BOLD{
                font-weight: bold;
            }
            #divTitle{
    font: 14px Arial, Helvetica, sans-serif;
    font-weight: bold;
    color: #0077b3;
    text-align: center;

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
    
        function hienthichitiet(commune_detai) {
            var ht1 = screen.availHeight - 100;
            var wt1 = screen.availWidth -100;
            var left1 = (screen.width / 2) - (wt1 / 2);
             var namBc = $('#namBc').val();
            var dotBc = $('#dotBc').val();
            var maBc = $('#maBc').val();
            var top1 = 100;           
            var url = "getDetailKhnvByAllSubCommune.action?commune_detai=" + commune_detai
            +"&dotBc=" + dotBc+"&namBc=" + namBc+"&maBc=" + maBc;
            popup = window.open(url, '_blank', "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
        
        
    function js_confirmdelete() {
//        $("#luu_thanhcong").hide();
        $('#luu_thanhcong').empty();
        var r = confirm('(Msg)Bạn chắc chắn muốn chốt/mở chốt số liệu xã này?');
        if (r === false) {
            event.preventDefault();
        }
    }
    
    
//        var max_row = 0;
            
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
        <div id="luu_thanhcong"></div>
        <s:form id="id_khnv_view_all_commune" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>   
                </br>
                <div id="divTitle">
                    TỔNG HỢP NHU CẦU VAY VỐN TÍN DỤNG THEO PGD
                    <BR>    
                    <s:if test="!reasonReject.equalsIgnoreCase('AAA')">
                        <font color="red">Nguyên nhân từ chối/TT chốt số liệu: <s:property value="reasonReject"/></font>       
                    </s:if> 
                </div>
                
                <s:hidden name="dotBc"/>
                </br>
                <table border="1" class="editDelete1" id="tablesms011" style="width: 99%" >
                    <tr>                                               
                        <!--<th  class="TD_BUTTON1">STT</th>-->      
                        <th  class="TD_GIATRI">Mã xã</th>    
                        <th class="TD_TEN">Tên xã</th>
                        <th  class="TD_STT">Mã chỉ tiêu</th> 
                        <th class="TD_CHITIEU">Tên chỉ tiêu</th>
                        <th class="TD_GIATRI">Giá trị</th>
                        <!--<th class="TD_GIATRI">Duyệt</th>-->       
                    </tr>                                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                    
                            <tr> 
                                <s:if test="MA.equalsIgnoreCase('XD00001')">
                                    <td style="text-align:center" class="TD_GIATRI <s:property value="D19"/>" >
                                            <s:property value="D5"/>
                                        </td>

                                    <td align = "left" class="TD_TEN <s:property value="D19"/>" >
                                        <a href="javascript:hienthichitiet('<s:property value="D5"/>' )" class="linkKh">
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

<!--                                <s:if test="MA.equalsIgnoreCase('XD00001')&& D18.equalsIgnoreCase('1')">
                                         <td style="text-align: center;">
                                            <s:url id="unlockId" value="Lock_Unlock.action" escapeAmp="false">
                                                <s:param name="commune_cd" value="D1"/>
                                                <s:param name="lock_unlock" value="0"/>
                                                <s:param name="dotBc" value="dotBc"/>
                                                
                                            </s:url>
                                            <sj:a  href="%{unlockId}" onclick="js_confirmdelete();" targets="luu_thanhcong"  ><b>Mở chốt</b></sj:a>
                                        </td>    
                                  </s:if>
                                  <s:elseif test="MA.equalsIgnoreCase('XD00001')&& !D18.equalsIgnoreCase('1')">
                                        <td style="text-align: center;">
                                       <s:url id="lockId" value="Lock_Unlock.action" escapeAmp="false">
                                                <s:param name="commune_cd" value="D1"/>
                                                <s:param name="lock_unlock" value="1"/>
                                                <s:param name="dotBc" value="dotBc"/>
                                            </s:url>
                                            <sj:a href="%{lockId}" onclick="js_confirmdelete();" targets="luu_thanhcong"><b>Chốt</b></sj:a>
                                            </td>
                                  </s:elseif>      
                                  <s:else>
                                      <td></td>
                                  </s:else>          -->
                        </tr>                                                                                                       
                    </s:iterator>
                </table>                    
        </s:form>
        
<!--        <script>
            initTable();
        </script>-->
    </body>
    
    
</html>
