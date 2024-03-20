<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:head/>
<sj:head/>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
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
            #idTitle{
                font-family: Cambria,Verdana,Arial,Tahoma,Helvetica;
                font-size: 11pt;
                font-weight: bold;
                color: blue;
            }
        </style>

        <script src="css/js/jquery-ui.min.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('.D0').css({"text-align": "center"});
                $(".TD_TEN_KH").css({"width": "280px"});
                $(".TD_CHUCVU").css({"width": "40px"});
                $(".TD_CMND").css({"width": "80px"});
                $(".TD_MAIL").css({"width": "110px"});
                $(".TD_M").css({"width": "140px"});
                $(".TD_TVTT").css({"width": "1110px"});
                $(".TD_THEMXOA").css({"width": "10px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".datepick").datepicker({dateFormat: 'dd/mm/yy'});

                $('#ui-datepicker-div').css('clip', 'auto');
            });

            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });


        </script>

        <script>
            function closeSelf() {
                window.close();
                return true;
            }
        </script>

        <script>
            window.onunload = function (e) {
                opener.refreshData();
            };
        </script>

    </head>
    <body>
        <!--<div id="container_popup">-->
        <s:form name="frmAddBDD002" id="frmAddBDD002"  theme="simple">              
            <div id="divChiTieu" style="text-align: center;">   
                <s:hidden name="khoa_ktgs"/>
                <s:hidden name="ngay_bc"/>                    

                <s:if test="addedit.equalsIgnoreCase('1')">
                    <span id="idTitle" >THÊM THÀNH VIÊN BAN ĐẠI DIỆN</span>
                </s:if>
                <s:if test="addedit.equalsIgnoreCase('2')">
                    <span id="idTitle" >CẬP NHẬT THÀNH VIÊN BAN ĐẠI DIỆN</span>
                </s:if>    


                <hr/>
                <s:iterator value="#attr.lstDulieuNt" var="modelDcpt" status="rowstatus">                        
                    <div id="divChiTieu" style="text-align: left; color: red">1. Thông tin chung</div>                        
                    <table border="1" class="editDelete" align="center" style="width: 97%">

                        <tr>
                            <th class="TD_TEN_KH"  style="font-weight:bold;">Họ tên<span style="color:red">*</span></th>
                            <th class="TD_CMND" style="font-weight:bold;">Ngày sinh<span style="color:red">*</span></th>                  

                            <th class="TD_CHUCVU" style="font-weight:bold;">Giới tính<span style="color:red">*</span></th>    
                            <th class="TD_CHUCVU" style="font-weight:bold;">Dân tộc<span style="color:red">*</span></th> 

                            <th class="TD_CHUCVU" style="font-weight:bold;">Đơn vị công tác<span style="color:red">*</span></th> 
                            <th class="TD_CHUCVU" style="font-weight:bold;">Chức vụ<span style="color:red">*</span></th>                                 
                                <s:if test="Grade.equalsIgnoreCase('1')">
                                <th class="TD_CHUCVU" style="font-weight:bold;">Thuộc ban Đại diện<span style="color:red">*</span></th>                                 
                                </s:if>

                        </tr>

                        <tr>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="TEN" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"   />
                                <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 

                            </td>

                            <td align = "center" class="TD_CHUCVU">
                                <input type="text" value="<s:property  value="D1" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 datepick" placeholder="dd/MM/yyyy" />
                            </td>

                            <td align = "left" class="TD_CHUCVU">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D2"
                                    name="lstDulieuNt[%{#rowstatus.index}].D2"
                                    list="lstGioiTinh" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="width: 80px;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>
                            <td align = "left" class="TD_CHUCVU">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D3"
                                    name="lstDulieuNt[%{#rowstatus.index}].D3"
                                    list="lstDanToc" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="width: 150px;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>
                            <td align = "left" class="TD_CHUCVU">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D4"
                                    name="lstDulieuNt[%{#rowstatus.index}].D4"
                                    list="lstDonVi" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="width: 140px;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>
                            <td align = "left" class="TD_CHUCVU">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D5"
                                    name="lstDulieuNt[%{#rowstatus.index}].D5"
                                    list="lstChucVu" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="width: 140px;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>   
                            <s:if test="Grade.equalsIgnoreCase('1')">
                                <td align = "left" class="TD_CHUCVU">                                        
                                    <s:select  
                                        id="lstDulieuNt[%{#rowstatus.index}].D17"
                                        name="lstDulieuNt[%{#rowstatus.index}].D17"
                                        list="lstBDD" 
                                        listKey="sKey"
                                        listValue="sDesc"                                        
                                        cssStyle="width: 140px;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                    </s:select>
                                </td> 
                            </s:if>

                        </tr>
                    </table>

                    <hr/>
                    <div id="divChiTieu" style="text-align: left; color: red">2. Thông tin hiệu lực</div>                        
                    <table border="1" class="editDelete" align="center">

                        <tr>
                            <th class="TD_CMND"  style="font-weight:bold;">Ngày bắt đầu hiệu lực<span style="color:red">*</span></th>
                            <th class="TD_CMND" style="font-weight:bold;">Ngày kết thúc hiện lực</th>                  

                            <th class="TD_MAIL" style="font-weight:bold;">Số CMND</th>    
                            <th class="TD_CMND" style="font-weight:bold;">Ngày cấp</th> 

                            <th class="TD_TEN_KH" style="font-weight:bold;">Nơi cấp</th> 
                            <th class="TD_TEN_KH" style="font-weight:bold;">Địa chỉ</th> 
                            <th class="TD_MAIL" style="font-weight:bold;">Điện thoại</th> 
                            <th class="TD_M" style="font-weight:bold;">Email</th>
                        </tr>

                        <tr>
                            <td align = "center" class="TD_CMND">
                                <input type="text" value="<s:property  value="D6" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D0 datepick" placeholder="dd/MM/yyyy" />
                            </td>


                            <td align = "center" class="TD_CMND">
                                <input type="text" value="<s:property  value="D7" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D0 datepick" placeholder="dd/MM/yyyy" />
                            </td>

                            <td align = "right" class="TD_MAIL">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D0" />
                            </td>

                            <td align = "center" class="TD_CMND">
                                <input type="text" value="<s:property  value="D11" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D0 datepick" placeholder="dd/MM/yyyy" />
                            </td>

                            <td align = "right" class="TD_CMND">
                                <input type="text" value="<s:property  value="D12" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"  />
                            </td>

                            <td align = "right" class="TD_CMND">
                                <input type="text" value="<s:property  value="D13" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13"  />
                            </td>

                            <td align = "right" class="TD_MAIL">
                                <input type="text" value="<s:property  value="D14" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D0" />
                            </td>
                            <td align = "right" class="TD_M">
                                <input type="text" value="<s:property  value="D15" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15"  />
                            </td>

                        </tr>

                    </table>
                    <hr/>

                    <div id="divChiTieu" style="text-align: left; color: red">3. Trạng thai</div>     
                    <table border="1" class="editDelete" align="center" style="width: 97%">

                        <tr>
                            <th class="TD_TEN_KH"  style="font-weight:bold;">Trạng thái<span style="color:red">*</span></th>
                            <th class="TD_TVTT" style="font-weight:bold;">Thành viên thay thế</th>                                                                                  
                        </tr>

                        <tr>                                
                            <td align = "left" class="TD_TEN_KH">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D8"
                                    name="lstDulieuNt[%{#rowstatus.index}].D8"
                                    list="lstTrangThai" 
                                    listKey="sKey"
                                    listValue="sDesc"                                        
                                    cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td> 

                            <td align = "left" class="TD_TVTT">                                        
                                <s:select  
                                    id="lstDulieuNt[%{#rowstatus.index}].D9"
                                    name="lstDulieuNt[%{#rowstatus.index}].D9"
                                    list="lstThanhVien" 
                                    listKey="sKey"
                                    listValue="sDesc"
                                    headerKey="-1"
                                    headerValue="--- Chọn ---"
                                    cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                                </s:select>
                            </td>                                                                                            
                        </tr>
                    </table>    

                    <table class="editDelete" align="center">
                        <tr align="center">
                            <td colspan="2" align="left">
                                <span id="idTitle">Ghi chú</span>
                            </td>
                        </tr>

                        <tr align="center">
                            <td  colspan="2" align="center" >
                                <textarea   value="<s:property  value="D16"/>" 
                                            name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" 
                                            style="font-size: 16px; font-family: inherit; width: 100%;background-color: #FFCCBA" rows="4"><s:property value='D16'/></textarea>
                            </td>
                        </tr>

                    </table>                                    
                    <table  border="0" class="editDelete" align="center"> 
                        <tr align="center" height="3px">                                    
                        </tr>
                        <tr align="center">                                       

                            <td width="35%">
                                <div id="content_div"></div>
                            </td>   

                            <td  align="center">
                                <div id="button_div" <s:property value="disabled" /> >
                                    <s:url id="edit_url" action="saveaddTVBDD002" escapeAmp="false"
                                           var="update_url">
                                        <s:param name="proc">update</s:param>  
                                    </s:url>                        
                                    <sj:a id="update_button_id"  href="%{#update_url}" 
                                          targets="content_div"
                                          formIds="frmAddBDD002"    
                                          onBeforeTopics="before-next"
                                          button="false"
                                          cssClass="metroButtonStyle"   
                                          >     
                                        Cập nhật
                                    </sj:a>
                                </div>
                            </td>                                     


                            <td align="center">
                                <sj:submit id="idClose" cssClass="metroButtonStyle"  name="nameClose" value="Thoát" onclick="closeSelf()"
                                           cssStyle="height:31px;width:95px"></sj:submit>
                                </td>

                                <td width="35%"></td>                                    


                            </tr>
                        </table>              

                </s:iterator>                                        
            </div>

        </s:form>
        <!--</div>-->
    </body>
</html>
