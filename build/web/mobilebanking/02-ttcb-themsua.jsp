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
        <style>
            a {
                color: #0000FF;
            }
            .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
        </style>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="js/jquery.number.js"></script>
    <script src="js/format_num.js"></script>
    <script>
        $(document).ready(function () {
            $('input.number').css({"text-align": "right"});
            $('.D0').css({"text-align": "center"});
            $('input.number2').css({"text-align": "right"});
//                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('#ui-datepicker-div').css('clip', 'auto');
            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 0);
            $(".TD_3").css({"width": "3%"});           
            $(".TD_5").css({"width": "5%"});
            $(".TD_7").css({"width": "7%"});
            $(".TD_9").css({"width": "9%"});
            $(".TD_11").css({"width": "11%"});
            $(".TD_13").css({"width": "13%"});
        });

        $(document).ready(function () {
            $("#allCheck").change(function () {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
        });

        $('.TEN_KH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.TEN_KH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });
    </script>

    <script>
        function autoEvaluate() {
            //                alert('vao doClick');
            var arrCot = [".D2", ".D3", ".D4"]; //Luu cac cot cua du lieu can tinh toan

            //                  Tinh toan cho 7 dong
            for (var i = 0; i < 99; i++) {
                //8=2+4-6
                $(".D4").eq(i).val(parseFloat($(".D2").eq(i).val()) - parseFloat($(".D3").eq(i).val()));

            }
            //                              
        }
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
            THÊM/SỬA THÔNG TIN CÁN BỘ: QUẢN LÝ ĐỊA BÀN; LÃNH ĐẠO PGD PHỤ TRÁCH TÍN DỤNG; GIÁM ĐỐC PGD                
        </div>
        <s:hidden name="khoa_mb"/>


        <s:iterator value="#attr.lstDulieuNt" var="modelDcpt" status="rowstatus">                        
            <hr/>
            <div id="divTitle" style="text-align: left; color: red">1. Thông tin chung </div>    
            </br>
            <table border="1" class="editDelete" align="center" style="width: 97%">
                <tr height="30">
                    <th class="TD_3" style="font-weight:bold;">Mã cán bộ</th>    
                    <th class="TD_7" style="font-weight:bold;">Họ và tên</th>
                    <th class="TD_5" style="font-weight:bold;">Điện thoại</th>    
                    <th class="TD_5" style="font-weight:bold;">Email</th>
                    <th class="TD_9" style="font-weight:bold;">Địa chỉ</th>
                    <th class="TD_5" style="font-weight:bold;">Ngày sinh</th> 
                    <th class="TD_3" style="font-weight:bold;">Giới tính cán bộ</th> 
                    <th class="TD_7" style="font-weight:bold;">Tỉnh</th>    
                    <th class="TD_7" style="font-weight:bold;">Huyện</th>                    
                    <th class="TD_7" style="font-weight:bold;">PGD</th>  
                                            
                </tr>

                <tr>
                    <td align = "right" class="TD_TEN_KH">
                        <input type="text" value="<s:property  value="D1" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1"   readonly="readonly"/>
                        <input type="hidden" value="<s:property  value="MA" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                    </td>

                    <td align = "right" class="TD_CHUCVU">
                        <input type="text" value="<s:property  value="D2" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0" />
                    </td>
                    <td align = "center" class="TD_CHUCVU">
                        <input type="text" value="<s:property  value="D3" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D0" />
                    </td>
                    <td align = "right" class="TD_CHUCVU">
                        <input type="text" value="<s:property  value="D4" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D0"  />
                    </td>
                    <td align = "right" class="TD_CHUCVU">
                        <input type="text" value="<s:property  value="D5" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0"  />
                    </td>
                     <td align = "center" class="TD_CHUCVU">
                        <input type="text" value="<s:property  value="D6" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D0" placeholder="dd/MM/yyyy" />
                    </td>
                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D7"
                            name="lstDulieuNt[%{#rowstatus.index}].D7"
                            list="lstGioiTinh" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
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
            <div id="divTitle" style="text-align: left; color: red">2. Thông tin nhận dạng </div>    
            </br>
            <table border="1" class="editDelete" align="center">

                <tr tr height="30">
                    <th class="TD_5" >Chỉ số xác định thông tin của Cán bộ</span></th>
                    <th class="TD_5" style="font-weight:bold;">Mã khách hàng trên Intellect của cán bộ</th>   
                    <th class="TD_5" style="font-weight:bold;">Mã nhận biết thông tin nhận dạng thành viên</th>   
                    <th class="TD_5" style="font-weight:bold;">Số chứng minh nhân dân/Thẻ căn cước</th>   
                    <th class="TD_5" style="font-weight:bold;">Ngày cấp CMND/Thẻ căn cước</th>   
                    <th class="TD_5" style="font-weight:bold;">Nơi cấp CMND/Thẻ căn cước</th>   
                    <th class="TD_5" style="font-weight:bold;">Ngày hết hạn CMND/Thẻ căn cước</th>   
                   
                    <th class="TD_5" style="font-weight:bold;">Phòng ban</th> 
                    <th class="TD_5" style="font-weight:bold;">Chức vụ</th> 
                    <th class="TD_5" style="font-weight:bold;">Ngày hiệu lực của cán bộ giữ vị trí tại đơn vị/phòng ban/lĩnh vực</th> 
                    <th class="TD_5" style="font-weight:bold;">Ngày hết hiệu lực của cán bộ giữ vị trí tại đơn vị/phòng ban/lĩnh vực</th> 

                </tr>

                <tr>
                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D10"
                            name="lstDulieuNt[%{#rowstatus.index}].D10"
                            list="lstChisoXdCb" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>


                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D11" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D0" />
                    </td>

                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D12"
                            name="lstDulieuNt[%{#rowstatus.index}].D12"
                            list="lstMaNhanbiet" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D13" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D0" />
                    </td>
                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D14" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D0" />
                    </td>

                    <td align = "right" class="TD_MAIL">
                        <input type="text" value="<s:property  value="D15" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D0" />
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D11" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D0" placeholder="dd/MM/yyyy" />
                    </td>

                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D17"
                            name="lstDulieuNt[%{#rowstatus.index}].D17"
                            list="lstPhongBan" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>

                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D18"
                            name="lstDulieuNt[%{#rowstatus.index}].D18"
                            list="lstChucVu" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>

                    <td align = "right" class="TD_MAIL">
                        <input type="text" value="<s:property  value="D19" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D0" />
                    </td>
                    <td align = "right" class="TD_M">
                        <input type="text" value="<s:property  value="D20" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D20"  />
                    </td>

                </tr>

            </table>
            <hr/>

            <div id="divTitle" style="text-align: left; color: red">3. Thông tin tạo lập</div>    
            </br>
            <table border="1" class="editDelete" align="center" style="width: 97%">

                <tr tr height="30">
                    <th class="TD_MAIL" style="font-weight:bold;">Trạng thái</th> 
                    <th class="TD_M" style="font-weight:bold;">Người dùng tạo bản ghi</th>
                    <th class="TD_MAIL" style="font-weight:bold;">Ngày tạo bản ghi</th> 
                    <th class="TD_M" style="font-weight:bold;">Người dùng sửa bản ghi gần nhất</th>    
                    <th class="TD_MAIL" style="font-weight:bold;">Ngày sửa bản ghi gần nhất</th>                       
                </tr>
                <tr>
                    <td align = "left" class="TD_CHUCVU">                                        
                        <s:select  
                            id="lstDulieuNt[%{#rowstatus.index}].D21"
                            name="lstDulieuNt[%{#rowstatus.index}].D21"
                            list="lstTrangthai" 
                            listKey="sKey"
                            listValue="sDesc"
                            headerKey="-1"
                            headerValue="--- Chọn ---"
                            cssStyle="width: 100%;vertical-align: middle;background-color: #FFCCBA; TEN_KH">
                        </s:select>
                    </td>


                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D22" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D22" class="D0" readonly="readonly"/>
                    </td>

                    <td align = "right" class="TD_MAIL">
                        <input type="text" value="<s:property  value="D23" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D23" class="D0" readonly="readonly"/>
                    </td>

                    <td align = "center" class="TD_CMND">
                        <input type="text" value="<s:property  value="D24" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D24" class="D0" readonly="readonly"/>
                    </td>

                    

                    <td align = "right" class="TD_MAIL">
                        <input type="text" value="<s:property  value="D25" />" 
                               name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D25" class="D0" readonly="readonly"/>
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
