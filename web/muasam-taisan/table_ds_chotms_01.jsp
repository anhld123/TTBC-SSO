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
                $(".TD_TEN_KH").css({"width": "30%"});
                $(".TD_CHUCVU").css({"width": "30px"});
                $(".TD_CMND").css({"width": "20%"});
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

            function CallReview(ma) {
                try
                {
                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var ngay_bc = $("#ngay_bc_DATE").val();                    
                    var heso_k = $("#heso_k").val();
                    
//                    var pos_string = laypostreecheck(khoa_cdtt);
//                    if (pos_string === '' || pos_string == null)
//                    {
//                        pos_string = '999999,';
//                    }
                    //swal(pos_string);
                    var ht1 = screen.availHeight-60;
                    var wt1 = screen.availWidth;
                    var left1 = screen.width-50;
                    var top1 = 0;
//                    var ngay_bc = $("#ngay_bc_DATE").val();
//                    var khoa_cdtt = $("#khoa_cdtt").val();
                    var url = "ReCallCDTT.action?khoa_cdtt=" + khoa_cdtt + "&ngay_bc=" + ngay_bc + "&tt_cdtt=" + ma + "&heso_k=" + heso_k;

                    //$.post(url,param,function(data){});
                    popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=no,toolbar=yes,border=yes");
                } catch (e)
                {
                    swal('Lỗi', 'Lỗi: ' + e.toString(), 'error');
                }
            }

        </script>
    </head>
    <body>
        <!--<div id="container_popup">-->
        <s:form name="frmTrangthaiChot" id="frmTrangthaiChot"  theme="simple">              
            <div id="divChiTieu" style="text-align: center;">                
                   <span id="idTitle" >Trạng thái</span>                                             
                <hr/>    
            </div>
            <div>
                <table id="tabledetail" align="center">
                    <tr>
                        <th class="TD_CMN">Mã PGD</th>
                        <th class="TD_TEN_KH">Tên PGD</th>
                        <th class="TD_CMND">Trạng thái chốt</th>
                    </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">      
                        <tr>
                            <s:if test="D4.equalsIgnoreCase('1')">                                                            
                                <td class="TD_CMND"><s:property value='D1' escape="false" /></td>
                                <td class="TD_CMND"><s:property value='D2' escape="false" /></td>
                                <td class="TD_CMND"><s:property value='D3' escape="false" /></td>
                            </s:if>
                            <s:else>                            
                                <td class="TD_CMND"><font color="red"><s:property value='D1' escape="false" /></font></td>
                                <td class="TD_CMND"><font color="red"><s:property value='D2' escape="false" /></font></td>
                                <td class="TD_CMND"><font color="red"><s:property value='D3' escape="false" /></font></td>
                            </s:else>   
                            
                        </tr>
                    </s:iterator>
                </table>

            </div>

        </s:form>
        <!--</div>-->

    </body>
</html>
