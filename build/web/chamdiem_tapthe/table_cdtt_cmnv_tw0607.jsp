<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->
<!DOCTYPE html>
<html>
    <head>
        <style>
           
            #divTitle{
                font: 14px Arial, Helvetica, sans-serif;
                font-weight: bold;
                color: #0077b3;
                text-align: center;
            }
            #idTitle{
                font-family: Cambria,Verdana,Arial,Tahoma,Helvetica;
                font-size: 11pt;
                font-weight: bold;
                color: blue;
            }



            #tabledetail {
                font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
                border-collapse: collapse;
                width: 100%;
            }

            #tabledetail td, #tabledetail th {
                border: 1px solid #ddd;
                padding: 8px;
            }

            #tabledetail tr:nth-child(even){background-color: #f2f2f2;}

            #tabledetail tr:hover {background-color: #ddd;}

            #tabledetail th {
                padding-top: 12px;
                padding-bottom: 12px;
                text-align: left;
                background-color: #4CAF50;
                color: white;
            }

        </style>

        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('.D0').css({"text-align": "center"});
                $(".TD_TEN_KH").css({"width": "280px"});
                $(".TD_CHUCVU").css({"width": "30px"});
                $(".TD_CMND").css({"width": "80px"});
                $(".TD_MAIL").css({"width": "110px"});
                $(".TD_M").css({"width": "140px"});
                $(".TD_TVTT").css({"width": "1110px"});
                $(".TD_THEMXOA").css({"width": "10px"});
                $(".TEN_KH").css({"width": "100%"});

                $("#allCheck_pgd").change(function () {
                    $(".checkboxpgd").prop('checked', $(this).prop("checked"));
                });

                $("#allCheck_sp").change(function () {
                    $(".checkboxsp").prop('checked', $(this).prop("checked"));
                });
            });



            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });


        </script>
    </head>
    <body>
        <!--<div id="container_popup">-->
        <s:form name="frmChitietCdtt" id="frmChitietCdtt"  theme="simple">              
            <div id="divChiTieu" style="text-align: center;">   
                <s:hidden name="khoa_cdtt"/>
                <s:hidden name="ngay_bc"/>                    
                <s:if test="Grade.equalsIgnoreCase('3')">
                    
                </s:if>
                <s:else>
                    <!--<span id="idTitle" >Danh sách phòng/ban chuyên môn nghiệp vụ/đơn vị chưa duyệt, gửi dữ liệu</span>-->
                </s:else>
                   <span id="idTitle" >Danh sách phòng/ban chuyên môn nghiệp vụ/đơn vị chưa duyệt, gửi dữ liệu</span>                                             
                <hr/>    
            </div>
            <div>
                <table id="tabledetail" align="center">
                    <tr>
                        <th>Mã chi nhánh</th>
                        <th>Tên chi nhánh</th>
                        <s:if test="RULEUSER.equalsIgnoreCase('9') && Grade.equalsIgnoreCase('3')">
                            <th>Trạng thái gửi dữ liệu</th>
                        </s:if>                      
                        <!--<th>Đơn vị chưa duyệt</th>-->
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">      
                        <tr>
                            <s:if test="D1.equalsIgnoreCase('Phòng/ban đã nhập số liệu')">
                                <td><s:property value='MACN' escape="false" /></td>
                                <td><s:property value='TEN' escape="false" /></td>

                               
                                <td><s:property value='D1' escape="false" /></td>
                            </s:if>
                            <s:else>
                                <td><font color="red"><s:property value='MACN' escape="false" /></font></td>
                                <td><font color="red"><s:property value='TEN' escape="false" /></font></td>

                                
                                <td><font color="red"><s:property value='D1' escape="false" /></font></td>
                            </s:else>   
                            
                        </tr>
                    </s:iterator>
                </table>

            </div>

        </s:form>
        <!--</div>-->

    </body>
</html>
