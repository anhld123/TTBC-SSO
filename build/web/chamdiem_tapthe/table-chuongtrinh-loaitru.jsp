<%-- 
    Document   : table-chuongtrinh-loaitru
    Created on : Feb 21, 2020, 3:28:37 PM
    Author     : BAOANH
--%>

<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />-->

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>  
        <script src="chamdiem_tapthe/js/chamdiem_tapthe.js"></script>  
        <script>
            $(document).ready(function () {

            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script type="text/javascript">


            $(document).ready(function () {

                select_all('.Thang1', '#select_all1');
                select_all('.Thang2', '#select_all2');
                select_all('.Thang3', '#select_all3');
                select_all('.Thang4', '#select_all4');
                select_all('.Thang5', '#select_all5');
                select_all('.Thang6', '#select_all6');
                select_all('.Thang7', '#select_all7');
                select_all('.Thang8', '#select_all8');
                select_all('.Thang9', '#select_all9');
                select_all('.Thang10', '#select_all10');
                select_all('.Thang11', '#select_all11');
                select_all('.Thang12', '#select_all12');
            });

            function select_all(class_name, id)
            {
                try
                {
                    $(id).change(function () {
//                        alert('change = '+id);
                        if (this.checked) {
                            $(class_name).each(function () {
                                this.checked = true;
                            });
                        } else {
                            $(class_name).each(function () {
                                this.checked = false;
                            });
                        }

                    });

                    $(class_name).click(function () {
                        if (!$(this).is(":checked")) {
                            $(id).prop("checked", false);
                            //alert('Không phải :checked trong class name');
                        } else {

                            //alert('Thang1');
                            var flag = 0;
                            var i = 1;
                            $(class_name).each(function () {
                                if (!this.checked)
                                    flag = 1;

                                //console.log('i=' + i + ' giatri=' + this.checked);
                                i++;
                            });
                            //alert('flag=' + flag);
                            if (flag == 0) {
                                $(id).prop("checked", true);
                            }
                            var allChecked = $(class_name + ':checked').length == $(class_name).length - 1;
                            if (allChecked) {
                                $(id).prop("checked", true);
                            }
                        }

                    });


//                    $(class_name).change(function () {
////                        alert('change = '+id);
//                        var allChecked = $(class_name + ':checked').length == $(class_name).length - 1;
//                        if (allChecked) {
//                            $(id).prop("checked", true);
//                        }
//                    });

                    var allChecked = $(class_name + ':checked').length == $(class_name).length - 1;
                    if (allChecked) {
                        $(id).prop("checked", true);
                    } else {
                        $(id).prop("checked", false);
                    }

                } catch (e)
                {
                    alert(e.toString());
                }
            }
            
             $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
    </head>
    <style>

    </style>
    <body>
        <s:form id="id_save_ctlt" action="saveChuongtrinhLoaitru.action" theme="simple">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>

                <div id="divTitle" class="top:10px">  
                <s:if test="Grade.equalsIgnoreCase('3')">
                  
                    <s:if test="pos_string.equalsIgnoreCase('000100')">
                        <font color="blue" style=" font-size: 17px; font: bold">CẤU HÌNH CHƯƠNG TRÌNH LOẠI TRỪ KHI CHẠY SỐ LIỆU ÁP DỤNG CHO TOÀN QUỐC </font>
                    </s:if>
                    <s:else>
                        <font color="blue" style=" font-size: 17px; font: bold">CẤU HÌNH CHƯƠNG TRÌNH LOẠI TRỪ KHI CHẠY SỐ LIỆU ÁP DỤNG CHO CHI NHÁNH 
                        </br>(<s:property  value="pos_string"/> - <s:property  value="thuyetminh"/>) </font>
                    </s:else>
                </s:if>
                <s:else>
                    <font color="blue" style=" font-size: 17px; font: bold">CẤU HÌNH CHƯƠNG TRÌNH LOẠI TRỪ KHI CHẠY SỐ LIỆU CHO PGD</font>
                </s:else>
                
            </div>    
                <br>
            <!--Đối với cấp chi nhánh-->
            <table border="1" class="editDelete" id="CHAMDIEMTT_001" align="center">
                <tr height="23">
                    <th class="TD_THUTU">Mã CT</th>
                    <th class="TD_CHITIEU">Tên chương trình</th>                 
                    <th  align="center">Tháng 1<s:checkbox name="Select All" id="select_all1" theme="simple"  cssClass="Thang1"/></th>
                    <th  align="center">Tháng 2<s:checkbox name="Select All" id="select_all2" theme="simple"  cssClass="Thang2"/></th>
                    <th  align="center">Tháng 3<s:checkbox name="Select All" id="select_all3" theme="simple"  cssClass="Thang3"/></th>
                    <th  align="center">Tháng 4<s:checkbox name="Select All" id="select_all4" theme="simple"  cssClass="Thang4"/></th>
                    <th  align="center">Tháng 5<s:checkbox name="Select All" id="select_all5" theme="simple"  cssClass="Thang5"/></th>
                    <th  align="center">Tháng 6<s:checkbox name="Select All" id="select_all6" theme="simple"  cssClass="Thang6"/></th>
                    <th  align="center">Tháng 7<s:checkbox name="Select All" id="select_all7" theme="simple"  cssClass="Thang7"/></th>
                    <th  align="center">Tháng 8<s:checkbox name="Select All" id="select_all8" theme="simple"  cssClass="Thang8"/></th>
                    <th  align="center">Tháng 9<s:checkbox name="Select All" id="select_all9" theme="simple"  cssClass="Thang9"/></th>
                    <th  align="center">Tháng 10<s:checkbox name="Select All" id="select_all10" theme="simple"  cssClass="Thang10"/></th>
                    <th  align="center">Tháng 11<s:checkbox name="Select All" id="select_all11" theme="simple"  cssClass="Thang11"/></th>
                    <th  align="center">Tháng 12<s:checkbox name="Select All" id="select_all12" theme="simple"  cssClass="Thang12"/></th>

                </tr>                
                <s:iterator value="#attr.lstChtrinhLoaitru" var="modelView" status="rowstatus">                    
                    <tr height="16"  class="TEN_KH">      
                        <input type="text" value="<s:property  value="mapgd" />" 
                                   name="lstChtrinhLoaitru[<s:property  value="%{#rowstatus.index}" />].mapgd" class="TEN_KH" onfocus="this.select()" style="display: none"/>    
                         <input type="text" value="<s:property  value="macn" />" 
                                   name="lstChtrinhLoaitru[<s:property  value="%{#rowstatus.index}" />].macn" class="TEN_KH" onfocus="this.select()" style="display: none"/>    
                        <td align="center" class="TD_CHITIEU TEN_KH">
                            <s:property  value="mact" />
                            <input type="text" value="<s:property  value="mact" />" 
                                   name="lstChtrinhLoaitru[<s:property  value="%{#rowstatus.index}" />].mact" class="TEN_KH" onfocus="this.select()" style="display: none"/>                                  
                        </td>
                        <td align="left" class="TD_CHITIEU TEN_KH">
                            <s:property  value="tenct" />
                            <input type="text" value="<s:property  value="tenct" />" 
                                   name="lstChtrinhLoaitru[<s:property  value="%{#rowstatus.index}" />].tenct" class="TEN_KH" onfocus="this.select()" style="display: none"/>                                  
                        </td>

                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T1" fieldValue="true" cssClass="Thang1 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T2" fieldValue="true" cssClass="Thang2 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T3" fieldValue="true" cssClass="Thang3 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T4" fieldValue="true" cssClass="Thang4 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T5" fieldValue="true" cssClass="Thang5 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T6" fieldValue="true" cssClass="Thang6 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T7" fieldValue="true" cssClass="Thang7 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T8" fieldValue="true" cssClass="Thang8 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T9" fieldValue="true" cssClass="Thang9 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T10" fieldValue="true" cssClass="Thang10 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T11" fieldValue="true" cssClass="Thang11 TEN_KH"></s:checkbox></td>
                        <td align="center" class="TEN_KH"><s:checkbox name="lstChtrinhLoaitru[%{#rowstatus.index}].T12" fieldValue="true" cssClass="Thang12 "></s:checkbox></td>


                        </tr>       
                </s:iterator>
            </table>


            <sj:submit id="idctlt_save" name="ctlt_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
            
        </s:form>
        <div id="luu_thanhcong"></div>     
<!--        <div align="left" style="padding-left: 30px">
            <span style="color:red; font-size: 13px; font: bolder">Để cấu hình loại trừ các chương trình cho vay không có khả tăng trưởng dư nợ cho chỉ tiêu </span>
            <span style="color:blue; font-size: 13px; font: bold">"3. Tăng trưởng tín dụng nguồn TW quy định tại VB3936/NHCS-TCCB"</span> 
            <span style="color:red; font-size: 13px; font: bold">Chi nhánh thực hiện cấu hình như sau:</span>
			<br><span style="color:red; font-size: 12px">- Chọn PGD cần cấu hình loại trừ chương trình cho vay không có khả năng tăng trưởng dư nợ. Sau đó, chọn "Tải dữ liệu";</span>
            <br><span style="color:red; font-size: 12px">- Đối với các chương trình cho vay không có khả năng tăng trưởng dư nợ, người dùng được phân quyền thực hiện tích chọn các tháng liên quan trong năm rồi chọn "Lưu dữ liệu";</span>
			<br><span style="color:red; font-size: 12px">- Sau khi cấu hình xong các PGD liên quan, Phòng KHNVTD của Chi nhánh gửi dữ liệu về HSC. Thời gian gửi dữ liệu cấu hình trước 17h00 ngày cuối tháng.</span>
        </div>-->
    </body>
</html>
