<%-- 
    Document   : table_template
    Created on : Nov 18, 2015, 9:23:22 AM
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
        <script>
            var max_row = 0;
            $(document).ready(function () {
                
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('.datepicker').each(function(){
//                    $(this).datepicker();
//                });
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_TEN_KH").css({"width": "100px"});
                $(".TD_CHUCVU").css({"width": "40px"});
                $(".TD_CMND").css({"width": "60px"});
                $(".TD_THEMXOA").css({"width": "10px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_KH_ADD").css({"width": "100%"});
            });

            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            function addRow(indx) {
                
                var ht1 = screen.availHeight - 260;
                var wt1 = 1024;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 100;
                var ngay_bc = $("#ngay_bc_DATE").val();
                var khoa_ktgs = $("#khoa_ktgs").val();                
                var url = "addTVBDD002.action?ngay_bc=" + ngay_bc +
                         "&khoa_ktgs=" + khoa_ktgs + "&addedit=" + indx;;
                popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        
                               
            }
        </script>
        <script>
            function autoEvaluate(){
//                alert('vao doClick');
                var arrCot = [".D5",".D8",".D9"]; //Luu cac cot cua du lieu can tinh toan
                
//                  Tinh toan cho 7 dong
                for(var i=0; i<99; i++){
                    //8=2+4-6
                    $(".D9").eq(i).val(parseFloat($(".D5").eq(i).val()) - parseFloat($(".D8").eq(i).val()));
                    
                }
//                              
            }
            
            function hienthichitiet(ma, stt) {
                
            var ht1 = screen.availHeight - 260;
            var wt1 = 1024;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 100;
            var ngay_bc = $("#ngay_bc_DATE").val();
            var khoa_ktgs = $("#khoa_ktgs").val();
            var url = "addTVBDD003.action?MATV=" + ma + "&ngay_bc=" + ngay_bc +
                         "&khoa_ktgs=" + khoa_ktgs + "&addedit=" + stt;
            popup = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");
        }
        </script>
        
<script>
    window.onunload = refreshParent;
    function refreshParent() {
        window.opener.location.reload();
    }
</script>        
    </head>
    <body>
        <s:form id="id_sv_%{khoa_ktgs}" action="SAVE_%{khoa_ktgs}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                THÔNG TIN THÀNH VIÊN BAN ĐẠI DIỆN CÁC CẤP
            </div>
            <s:hidden name="khoa_ktgs"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            
            <table class="editDelete" id="tablems04" align="center">
                    <tr>
                        <td width="35%" class="TD_THEMXOA"><input type="button" value="Thêm mới" onclick="addRow(1)"  
                                style="color: #0000FF;"
                                class="TEN_KH_ADD"/></td>
                        <td ></td>                                                                                                                                                                
                     </tr>
            </table>
            
            <s:if test="Grade.equalsIgnoreCase('1')">
                <table border="1" class="editDelete" id="tablems04" align="center">
                <tr>
                    <!--<th  class="TD_CHUCVU">ID</th>-->
                    <th  class="TD_TEN_KH">Họ tên</th>
                    <th  class="TD_CHUCVU">Ngày sinh</th>       
                    <th  class="TD_CHUCVU">Ngày bắt đầu hiệu lực</th>
                    <th  class="TD_CHUCVU">Ngày kết thúc hiệu lực</th>            
                    <th  class="TD_CMND">Trạng thái</th>
                    <th  class="TD_CMND">Số CMND</th>
                    <th  class="TD_CHUCVU">Ngày cấp</th>                    
                    <th  class="TD_TEN_KH">Địa chỉ</th>
                    <th  class="TD_CHUCVU">Điện thoại</th>                    
                    <th  class="TD_TEN_KH">Ghi chú</th>                                                            
                </tr>
                 
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>                         
                        
                        <td align = "left" class="TD_TEN_KH"> 
                            <a href="javascript:hienthichitiet('<s:property value="MA"/>',2)" class="SOKU linkKh">
                                <s:property value='TEN'/>
                            </a>
                        </td>
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D1" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 datepicker" placeholder="dd/MM/yyyy" readonly/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>                         
                        </td>
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D0 datepicker" placeholder="dd/MM/yyyy" readonly/>
                        </td>
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D0 datepicker" placeholder="dd/MM/yyyy" readonly/>
                        </td>
                        <td align = "right" class="TD_CMND">
                            <input type="text" value="<s:property  value="D8" />" id="D8"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D0" onfocus="this.select();"
                                   readonly/>
                        </td>
                        <td align = "right" class="TD_CMND">
                            <input type="text" value="<s:property  value="D10" />" id="D10"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D0" onfocus="this.select();"
                                   readonly/>
                        </td>
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D0 datepicker" placeholder="dd/MM/yyyy" readonly/>
                        </td>
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D13" />" id="D13"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select();" readonly/>
                        </td>
                        <td align = "right" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D14" />" id="D14"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D0" onfocus="this.select();"
                                   readonly/>
                        </td>                        
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D16" />" id="D16"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16" onfocus="this.select();"
                                   readonly/>
                        </td> 
                        
                        
                    </tr>
                </s:iterator>                                               
            </table>
            </s:if>
            <s:else>
                <table border="1" class="editDelete" id="tablems04" align="center">
                <tr>
                    <!--<th  class="TD_CHUCVU">ID</th>-->
                    <th  class="TD_TEN_KH">Họ tên</th>
                    <th  class="TD_CHUCVU">Mã PGD</th>
                    <th  class="TD_CHUCVU">TV BĐD cấp</th>
                    <th  class="TD_CHUCVU">Ngày sinh</th>       
                    <th  class="TD_CHUCVU">Ngày bắt đầu hiệu lực</th>
                    <th  class="TD_CHUCVU">Ngày kết thúc hiệu lực</th>            
                    <th  class="TD_CMND">Trạng thái</th>
                    <th  class="TD_CMND">Số CMND</th>
                    <th  class="TD_CHUCVU">Ngày cấp</th>                    
                    <th  class="TD_TEN_KH">Địa chỉ</th>
                    <th  class="TD_CHUCVU">Điện thoại</th>                    
                    <th  class="TD_TEN_KH">Ghi chú</th>                                                            
                </tr>
                 
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr>                         
                        <s:if test="CO_TONGHOP.equalsIgnoreCase('M')">
                            <td align = "left" class="TD_TEN_KH"> 
                                <a href="javascript:hienthichitiet('<s:property value="MA"/>',2)" class="SOKU linkKh"> 
                                    <s:property value='TEN'/>
                                </a>
                            </td>
                        </s:if>
                        <s:else>
                                <td align = "right" class="TD_TEN_KH">
                                    <input type="text" value="<s:property  value="TEN" />" id="TEN"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" onfocus="this.select();"
                                           readonly/>
                                </td>
                        </s:else>    
                        
                        
                        <td align = "right" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="MAPGD" />" id="MAPGD"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" class="D0" onfocus="this.select();"
                                   readonly/>
                        </td> 
                        
                        <td align = "right" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D17" />" id="D17"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D0" onfocus="this.select();"
                                   readonly/>
                        </td> 
                        
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D1" />" id="D1_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D0 datepicker" placeholder="dd/MM/yyyy" readonly/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>                         
                        </td>
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D6" />" id="D6_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D0 datepicker" placeholder="dd/MM/yyyy" readonly/>
                        </td>
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D7" />" id="D7_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D0 datepicker" placeholder="dd/MM/yyyy" readonly/>
                        </td>
                        <td align = "right" class="TD_CMND">
                            <input type="text" value="<s:property  value="D8" />" id="D8"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="D0" onfocus="this.select();"
                                   readonly/>
                        </td>
                        <td align = "right" class="TD_CMND">
                            <input type="text" value="<s:property  value="D10" />" id="D10"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D0" onfocus="this.select();"
                                   readonly/>
                        </td>
                        <td align = "center" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D11" />" id="D11_<s:property  value="%{#rowstatus.index}" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D0 datepicker" placeholder="dd/MM/yyyy" readonly/>
                        </td>
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D13" />" id="D13"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D13" class="TEN_KH" onfocus="this.select();" readonly/>
                        </td>
                        <td align = "right" class="TD_CHUCVU">
                            <input type="text" value="<s:property  value="D14" />" id="D14"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D0" onfocus="this.select();"
                                   readonly/>
                        </td>                        
                        <td align = "right" class="TD_TEN_KH">
                            <input type="text" value="<s:property  value="D16" />" id="D16"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16" onfocus="this.select();"
                                   readonly/>
                        </td> 
                        
                        
                    </tr>
                </s:iterator>                                               
                </table>
            </s:else>    
            <sj:submit id="%{khoa_ktgs}_save" name="%{khoa_ktgs}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
    </body>
</html>
