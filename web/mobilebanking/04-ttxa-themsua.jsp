<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script src="css/js/jquery-ui.min.js"></script>
        
        <script>
            var max_row = 0;
            $(document).ready(function () {
            $('input.number').css({"text-align": "right"});
//            $('.D0').css({"text-align": "center"});
            $('input.number2').css({"text-align": "right"});
//            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
             $(".datepick").datepicker({dateFormat: 'dd/mm/yy'});

            $('#ui-datepicker-div').css('clip', 'auto');
            $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 2);
            $(".TD_3").css({"width": "3%"});           
            $(".TD_5").css({"width": "5%"});
            $(".TD_7").css({"width": "7%"});
            $(".TD_9").css({"width": "9%"});
            $(".TD_11").css({"width": "11%"});
            $(".TD_13").css({"width": "13%"});
        });

//        $(document).ready(function () {
//            $("#allCheck").change(function () {
//                $(".checkbox1").prop('checked', $(this).prop("checked"));
//            });
//        });
//
//        $('.TEN_KH').focus(function () {
//            $(this).closest('tr').addClass('highlight_row');
//        });
//        $('.TEN_KH').blur(function () {
//            $(this).closest('tr').removeClass('highlight_row');
//        });
    </script>
     
</head>
<body>
    <s:form id="id_sv_%{khoa_mb}" action="SAVE_%{khoa_mb}" theme="simple">  
        <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
            <input type="hidden" id="<s:property  value="sKey" />" 
                   name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
        </s:iterator>
            </br>
        <div id="divTitle">
            THÊM/SỬA THÔNG TIN CHI TIẾT VỀ ĐỊA PHƯƠNG XÃ/PHƯỜNG              
        </div>
        <s:hidden name="khoa_mb"/>


        <s:iterator value="#attr.lstDulieuNt" var="modelDcpt" status="rowstatus">                        
            <hr/>
            <div id="divTitle" style="text-align: left; color: red">1. Thông tin chung </div>    
            </br>
            <table border="1" class="editDelete" align="center" style="width: 97%">
                <tr height="30">
                    <th class="TD_3" style="font-weight:bold;">Mã xã</th>    
                    <th class="TD_7" style="font-weight:bold;">Tên xã</th>
                    <th class="TD_3" style="font-weight:bold;">Ngày hiệu lực</th>
                    <th class="TD_7" style="font-weight:bold;">Tỉnh</th>    
                    <th class="TD_7" style="font-weight:bold;">Huyện</th>                    
                    <th class="TD_7" style="font-weight:bold;">PGD</th>                                             
                </tr>

                <tr height="25">
                    <td align = "center" class="TD_TEN_KH">
                        <input type="text" value="<s:property  value="D8" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8"  class="D0"  readonly="readonly"/>
                        <input type="hidden" value="<s:property  value="MA" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                    </td>

                    <td align = "right" class="TD_CHUCVU">
                        <input type="text" value="<s:property  value="D9" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" class="" />
                    </td>
                    <td align = "center" class="TD_CHUCVU">
<!--                        <input type="text" value="<s:property  value="D12" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"  class="D0 datepicker" placeholder="dd/MM/yyyy" />-->
                         <input type="text" name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D12"  class="datepick dates D0" id="date<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D12" />" />
                    </td>
                    
                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D4"
                            name="lstDulieuNt[%{#rowstatus.index}].D4"
                            list="lstTinh" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;height: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>
                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D6"
                            name="lstDulieuNt[%{#rowstatus.index}].D6"
                            list="lstHuyen" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;height: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>

                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D2"
                            name="lstDulieuNt[%{#rowstatus.index}].D2"
                            list="lstPosCD" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;height: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>
                    

                </tr>
            </table>

            <hr/>
            <div id="divTitle" style="text-align: left; color: red">2. Thông tin tín dụng</div>    
            </br>
            <table border="1" class="editDelete" align="center">

                <tr height="30">
                    <th class="TD_5" >Tổng dân số theo địa bàn cập nhật theo xã/phường</th>
                    <th class="TD_5" style="font-weight:bold;">Số lượng khách hàng của NHCSXH theo xã/phường</th>   
                    <th class="TD_5" style="font-weight:bold;">Số hộ nghèo của xã/phường</th>   
                    <th class="TD_5" style="font-weight:bold;">Số hộ cận nghèo của xã/phường</th>   
                    <th class="TD_5" style="font-weight:bold;">Số hộ mới thoát nghèo của xã/phường</th>   
                    <th class="TD_5" style="font-weight:bold;">Số hộ dân tộc thiểu số xã/phường</th>   
                    <th class="TD_5" style="font-weight:bold;">Tỷ lệ hộ nghèo của xã/phường</th>   
                   
                    <th class="TD_5" style="font-weight:bold;">Tỷ lệ hộ cận nghèo của xã/phường</th> 
                    <th class="TD_5" style="font-weight:bold;">Tỷ lệ hộ mới thoát nghèo của xã/phường</th> 
                    <th class="TD_5" style="font-weight:bold;">Tỷ lệ hộ dân tộc thiểu số xã/phường</th> 
                    <th class="TD_11" style="font-weight:bold;">Thông tin ngành kinh tế của xã/phường</th> 
                    <th class="TD_11" style="font-weight:bold;">Thông tin khác của xã/phường</th> 
                </tr>

                <tr  height="25">
                    
                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D13" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="number" />
                    </td>
                    

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D14" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="number" />
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D15" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="number" />
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D16" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="number" />
                    </td>
                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D17" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="number" />
                    </td>

                    <td align = "right" class="TD_MAIL">
                        <input type="text" value="<s:property  value="D18" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D18" class="number" />
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D19" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="number2" />
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D20" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20" class="number2" />
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D21" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D21" class="number2" />
                    </td>

                    <td align = "right" class="TD_MAIL">
                        <input type="text" value="<s:property  value="D22" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="number2" />
                    </td>
                    <td align = "right" class="TD_M">
                        <input type="text" value="<s:property  value="D23" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D123"  />
                    </td>
                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D24" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" class="" />
                    </td>

                </tr>

            </table>
            <hr/>

            <div id="divTitle" style="text-align: left; color: red">3. Thông tin tạo lập</div>    
            </br>
            <table border="1" class="editDelete" align="center" style="width: 97%">

                <tr height="30">
                    <th class="TD_MAIL" style="font-weight:bold;">Trạng thái</th> 
                    <th class="TD_M" style="font-weight:bold;">Người dùng tạo bản ghi</th>
                    <th class="TD_MAIL" style="font-weight:bold;">Ngày tạo bản ghi</th> 
                    <th class="TD_M" style="font-weight:bold;">Người dùng sửa bản ghi gần nhất</th>    
                    <th class="TD_MAIL" style="font-weight:bold;">Ngày sửa bản ghi gần nhất</th>                       
                </tr>
                <tr  height="25">
                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D29"
                            name="lstDulieuNt[%{#rowstatus.index}].D29"
                            list="lstTrangthai" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;height: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>


                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D25" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" class="D0" readonly="readonly"/>
                    </td>

                    <td align = "right" class="TD_MAIL">
                        <input type="text" value="<s:property  value="D26" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D26" class="D0" readonly="readonly"/>
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D27" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D27" class="D0" readonly="readonly"/>
                    </td>

                    

                    <td align = "right" class="TD_MAIL">
                        <input type="text" value="<s:property  value="D28" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D28" class="D0" readonly="readonly"/>
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
                                                   cssStyle="height:38px;width:95px"></sj:submit>
                                </td>
                                
                                <td width="35%"></td>                                    
                                
                                
                            </tr>
                            </table>  
        </s:iterator>                  


        <p></p>          

        <sj:submit id="%{khoa_mb}_save" name="%{khoa_mb}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>


    <div id="luu_thanhcong"></div>
</body>
</html>
