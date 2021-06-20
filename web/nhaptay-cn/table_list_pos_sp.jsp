<%-- 
    Document   : table_list_report
    Created on : Sep 16, 2016, 10:11:55 AM
    Author     : TomFC
--%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
            #menuBcttv_para{
                width: 100%;
                height: 25px;                
                border: 1px solid; 
                padding-bottom: 0px;
                padding-top: 0px;
            }

            #containBcttv_para{
                width: 100%;
                min-height:390px;
                border: 1px solid;
                margin-top: 2px;
            }

            .metroButtonStyle {
                font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
                display: block;
                color: rgb(255, 255, 255);
                text-decoration: none;
                text-align: center;
                width: 90px;
                height: 26px;
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

            #container{
                width: 100%;
                height: 500px;
                border: 0px solid;
                padding-left: 0px;        
                /*color: #FFE6B0*/
            }

            #containTree{
                width: 15%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 450px;
                float: left;
                overflow: scroll;
            }

            #containParm{
                width: 84%;
                height: 450px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
            }
            #containParm_full{
                width: 100%;
                /*height: 450px;*/
                /*padding-left: 5px;*/
                float: left;
                /*overflow: scroll;*/
            }
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: 12px;
            }

            .report_group_form{
                width: 100%;
            }

            #navParam{
                height: 35px;
                padding:0px;
                padding-bottom: 0px;
                padding-top: 0px;
                /*margin:5px;*/
                /*border-radius: 10px; //bo tron goc*/
                border: 1px solid;                
                /*                height: 50px;
                                border: 1px solid;  
                                border-radius: 10px; //bo tron goc
                                -moz-border-radius: 10px;
                                margin:5px;
                                padding:5px;*/
            }
            #navParam3{
                height: 35px;
                border: 0px solid;
                margin-left: 10px;
                font-weight: bold;
                border-left: 40px;
                float: left;
                padding-bottom: 0px;
                padding-top: 0px;
            }
            #message_suc_err
            {
                height: 30px;
                border: 0px solid;
                padding-bottom: 0px;
                padding-top: 0px;
            }
            
            #divMuc{
            font: 12px Arial, Helvetica, sans-serif;
            text-align: left;
            color: red;
            /*padding-right: 700px;*/            
        }
        
        #containReport{
                width: 100%;
                height: 132px;
                padding-left: 5px;
                float: left;
                /*overflow: scroll;*/
                overflow-x: hidden;
            }
            
        </style>   

    <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});                
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 3);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_THUTU").css({"width": "20px"});
                $(".TD_CHECKBOX").css({"width": "20px"});
                $(".TD_MACHUNG").css({"width": "30px"});
                $(".TD_TENCHUNG").css({"width": "150px"});
                $(".TD_PHIAD").css({"width": "80px"});
                $(".TD_CHITIEU").css({"width": "50px"});
                $(".TD_SOTK").css({"width": "50px"});
                $(".TD_TENKH").css({"width": "320px"});
                $(".TD_SP").css({"width": "70px"});
                $(".TD_SOTIEN").css({"width": "70px"});                
                $(".TD_GHICHU").css({"width": "200px"});

            });
            
            $(document).ready(function () {
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
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
            <s:hidden name="khoa_nhaptaycn"/>
            <s:hidden name="nha_dt"/>
            <table style="width: 100%" align="left">
                    <tr>
                        <td style="width: 35%">
                            <div  id="divMuc">
                                1. Danh sách PGD
                            </div>
                        </td>
                        <td>
                            <div id="divMuc">                             
                                2. Danh sách sản phẩm
                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td>
                            
                            <div id="containReport" align="center">
                                <table border="1" id="tablems01" style="width:100%; " cellspacing="0" >
                                    <tr>        
                                        <td style="width: 20px;" align="center">
                                            <input type="checkbox" id ="allCheck_pgd" name="allCheck_pgd"  />
                                        </td>
                                        <td  class="TD_MACHUNG">Mã PGD</td>
                                        <td  class="TD_TENCHUNG">Tên PGD</td>                                                                         
                                       
                                    </tr>                  
                                    
                                    <s:iterator value="#attr.lstDulieuNt_pgd" var="modelView" status="rowstatus">                    
                                    <tr height="22">           
                                            <td align = "center" class="TD_CHECKBOX"> 
                                                <%--<s:checkbox id ="%{#rowstatus.index}" cssClass="checkboxpgd" name="lstsaveNT_PGD[%{#rowstatus.index}].MA" fieldValue="%{MA}"/>--%>
                                                <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxpgd" name="lstsaveNT_PGD[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA" />" />
                                            </td>   
                                            <td align = "right" class="TD_MACHUNG">
                                                <input type="text" value="<s:property  value="MA" />" 
                                                       name="lstDulieuNt_pgd[<s:property  value="%{#rowstatus.index}" />].MA" class="<s:property value='FONTFORMAT'/>  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                                       readonly="readonly"/>
                                            </td>    
                                            <td align = "center" class="TD_TENCHUNG">
                                                <input type="text" value="<s:property  value="TEN" />" 
                                                       name="lstDulieuNt_pgd[<s:property  value="%{#rowstatus.index}" />].TEN" class=" <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                                       readonly="readonly"/>
                                            </td>                                                                                   
                                    </tr>
                                    </s:iterator>
                                            
                                </table>
                            </div> 
                        </td>

                        <td>
                            <div id="containReport" align="center">
                                <table border="1" class="editDelete" id="tablems01" align="center">
                                    <tr>
<!--                                        <th width="25" class="TD_CHECKBOX" >
                                            <s:checkbox id ="allCheck_sp" name="allCheck"/></th>                    -->
                                        <td style="width: 20px;" align="center">
                                            <input type="checkbox" id ="allCheck_sp" name="allCheck_sp"  />
                                        </td>    
                                        <th  class="TD_MACHUNG">Mã sản phẩm</th>
                                        <th  class="TD_TENCHUNG">Tên sản phẩm</th>                                     
                                        <!--<th  class="TD_PHIAD">Tính phí theo</th>-->  
                                    </tr>                             
                                    <s:iterator value="#attr.lstDulieuNt_spham" var="modelView" status="rowstatus">                    
                                            <tr height="22">   
                                            <td align = "center" class="TD_CHECKBOX"> 
                                                <%--<s:checkbox id ="%{#rowstatus.index}" cssClass="checkboxsp" name="lstsaveNT_SP[%{#rowstatus.index}].MA" fieldValue="%{MA}"/>--%>
                                                <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxsp" name="lstsaveNT_SP[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA" />" />
                                            </td>   
                                            <td align = "right" class="TD_MACHUNG">
                                                <input type="text" value="<s:property  value="MA" />" 
                                                       name="lstDulieuNt_spham[<s:property  value="%{#rowstatus.index}" />].MA" class="<s:property value='FONTFORMAT'/>  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                                       readonly="readonly"/>
                                            </td>    
                                            <td align = "center" class="TD_TENCHUNG">
                                                <input type="text" value="<s:property  value="TEN" />" 
                                                       name="lstDulieuNt_spham[<s:property  value="%{#rowstatus.index}" />].TEN" class=" <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                                       readonly="readonly"/>
                                            </td>   

<!--                                            <td align = "right" class="TD_PHIAD">
                                                <input type="text" value="<s:property  value="D1" />" 
                                                       name="lstDulieuNt_spham[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                                       readonly="readonly"/>
                                            </td>-->
                                            </tr>

                                    </s:iterator>
                                </table>
                            </div> 
                        </td>

                    </tr>
                    <tr>
                        <td colspan="2" >
                            <div id="divMuc">
                                3. Mức phí phân bổ
                            </div>
                        </td>

                    </tr>
                    <tr>
                        <td colspan="2">
                            <table border="1" style="width: 65%" class="editDelete"  id="tablems01" align="center">
                            <tr>
                                <th rowspan="2" class="TD_THUTU">Tạo mới/Kiểm tra</th>
                                <th  rowspan="2" class="TD_CHITIEU">Mức phí</th>
                                <th colspan="3" class="TD_SOTIEN">Tỷ lệ phân bổ</th>                                                                                       
                            </tr>    
                            <tr>
                                <th>Xã</th>   
                                <th>Huyện</th>
                                <th>Tỉnh</th>                            
                            </tr>
                            <s:iterator value="#attr.lstDulieuNt_phanbo" var="modelView" status="rowstatus">                    
                                    <tr height="22">   
                                    <td align = "center" class="TD_THUTU">                                        
                                        <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkbox1" name="lstsaveNT_PB[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA" />" />
                                    </td>   
                                    <td align = "right" class="TD_CHITIEU">
                                        <input type="text" value="<s:property  value="D1" />" 
                                               name="lstDulieuNt_phanbo[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number <s:property value='FONTFORMAT'/>  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                               />
                                    </td>    
                                    <td align = "right" class="TD_SOTIEN">
                                        <input type="text" value="<s:property  value="D2" />" 
                                               name="lstDulieuNt_phanbo[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 number2 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               />
                                    </td>  
                                    <td align = "right" class="TD_SOTIEN">
                                        <input type="text" value="<s:property  value="D3" />" 
                                               name="lstDulieuNt_phanbo[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 number2 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               />
                                    </td> 
                                    <td align = "right" class="TD_SOTIEN">
                                        <input type="text" value="<s:property  value="D4" />" 
                                               name="lstDulieuNt_phanbo[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 number2 <s:property value='FONTFORMAT'/> <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                               />
                                    </td> 
                                    </tr>

                            </s:iterator>
                        </table>  
                        </td>

                    </tr>
                </table>
                                     
    </body>
</html>
