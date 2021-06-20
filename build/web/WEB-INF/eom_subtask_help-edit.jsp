<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head/>

<link rel="stylesheet" type="text/css"  href="css/styles-xlrr.css" />
<script src="js/jquery.number.js"></script>
<script src="js/format_num.js"></script>

<style>
    .main_form {
        padding:5px;        
        background:#f9f9f9;
        border:1px solid #ccc;        
        text-align:left;        
    }

    .readonly_txt {
        background: #ccc;        
    }

    .edit_txt {
        background: #FFE47A;
    }

    a.attach_link:link    {        
        background-color: #ccc;
        background-image: url(img/attach.png);
        background-position: left;
        padding-left: 20px;
        margin-right: 5px;
        background-repeat:no-repeat;        
    }
</style>

<html>
    <body>           

        <h4><u>Thay đổi thông tin:</u></h4>           
        <div class="main_form">
            <s:form id="eom_subtask_edit_form" 
                    action="eom_update_subtask_addinfor" theme="simple">                                  
                <s:hidden name="para_username" id="role_ID"  />     
                <table>
                    <tr>
                        <td>Công việc</td>
                        <td><s:textfield name="para_subtask" label="Công việc" 
                                     readonly="true" size="48"
                                     cssClass="readonly_txt"/></td>
                    </tr>
                    <tr>
                        <td>Ngày thực hiện</td>
                        <td><s:textfield name="para_report_date" label="Ngày thực hiện" 
                                     readonly="true" size="48"
                                     cssClass="readonly_txt"/></td>
                    </tr>
                    <tr>                
                        <td>Chu kỳ</td>
                        <td><s:textfield name="para_period" label="Chu kỳ" readonly="true" size="48"
                                     cssClass="readonly_txt"/></td>
                    </tr>
                    <tr>
                        <td>Trạng thái hiện tại</td>
                        <td>

                            <s:set name="st_string_ID" value="%{para_status}" />
                            <s:hidden value="%{#st_string_ID}"></s:hidden>
                            <s:if test="%{#st_string_ID.equalsIgnoreCase('D')}">
                                <input type="text" name="para_status" label="Trạng thái" size="48" 
                                       value="<s:property value="%{get_status_descript(para_status)}"/>"
                                       style="background-color: #a4ecab;"/>                                                  
                            </s:if>
                            <s:elseif test="%{#st_string_ID.equalsIgnoreCase('E')}">
                                <input type="text" name="para_status" label="Trạng thái" size="48" 
                                       value="<s:property value="%{get_status_descript(para_status)}"/>"
                                       style="background-color: #d14;"/>                                   
                            </s:elseif >
                            <s:else>
                                <input type="text" name="para_status" label="Trạng thái" size="48" 
                                       value="<s:property value="%{get_status_descript(para_status)}"/>"
                                       style="background-color: #ffff99;"/>                                    
                            </s:else>

                        </td> 
                    </tr> 
                    <tr>
                        <td>Thời gian <font color="red">(*)</font></td>
                        <td>
                            <s:textfield name="para_expected_time"  
                                         id="para_expected_time_ID"  size="48"/>                                                    
                        </td>
                    </tr>
                    <tr></tr>
                    <tr>
                        <td>Ghi chú <font color="red">(*)</font></td>
                        <td>
                            <s:textarea name="para_comment" cols="36" rows="5" 
                                        id="para_comment_ID"/>                                                    
                        </td>
                    </tr>
                    <tr>
                        <td>File đính kèm</td>
                        <td>
                            <s:url id="fileDownload" action="text2SbvDownload.action">
                                <s:param name="downloadFileName" value="{attachFile_path}" />
                            </s:url> 
                            <s:if test="%{attachFile_path != null && attachFile_path!= ''}">
                                <s:a href="%{fileDownload}"
                                     cssClass="attach_link"
                                     > <s:property value="attachFile_name"/> </s:a>
                            </s:if>
                            <s:else>
                                No attach file.
                            </s:else>

                        </td>
                    </tr> 
                    <tr>
                        <td>Trạng thái mới</td>
                        <td>
                            <s:url var="buildStatusComboUrl" 
                                   action="eom_build_status_combo.action"></s:url>
                            <sj:select href="%{buildStatusComboUrl}" 
                                       name="new_status_ID"
                                       id="new_status_ID"
                                       list="status"    
                                       onChangeTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false"                                                               
                                       theme="simple"    
                                       headerKey=""
                                       headerValue="--- Chưa chọn ---"
                                       ></sj:select>  

                            </td>
                        </tr>                           
                        <tr>
                            <td>Tổng số PGD/CN</td>
                            <td class="CLASS1">                                              
                                <input type="text" value="<s:property value='para_expected_row_total'/>" 
                                       class="CLASS2 number2" 
                                   onblur="if (this.value === '') {
                                               this.value = 0;
                                           }
                                           isNumber(this.value)" onfocus="
                                           check_permit('<s:property value="para_username"/>');
                                           this.select()"                                           
                                   size="48"
                                   id="para_expected_row_total_ID"
                                   name="para_expected_row_total"
                                   />
                    </tr>                           
                </table>                
                <hr/>
                <div style="float: right; padding-bottom: 5px;"> 
                    <input type="hidden" value="N" id="turn_flag_ID"/>
                    <input type="button" onclick="call_submit();" 
                           value ="Ghi nhận" </input>
                    <div style="display: none;" >
                        <sj:submit id="update_ID" 
                                   name="update_NAME"                           
                                   formIds="eom_subtask_edit_form"
                                   targets="div_contentID"                                   
                                   onClickTopics="turn_flag"                               
                                   button="false"
                                   ></sj:submit> 
                        </div>
                        <input type="button" onclick="javascript:closeWindow();
                                return;" value="Đóng"
                               width="60px">                        
                        </input>
                    </div>
                    <br/>                                   
            </s:form>
        </div>

        <div id="div_contentID"> </div>
        
           <s:hidden name="para_username" id="para_username_ID"/>
</body>
</html>


<script language="javascript" 
        type="text/javascript">
//            var username = '';
            function check_permit(username) {
//                alert('a');
                if (username === 'ADMIN') {
                    $('#para_expected_row_total_ID').prop('readonly', false);
                    $('#para_expected_row_total_ID').css('background-color', '#fff');
                } else {
                    $('#para_expected_row_total_ID').prop('readonly', true);
                    $('#para_expected_row_total_ID').css('background-color', '#ccc');
                }
            }
            $(document).ready(function() {
                $('input.number').css({"text-align": "left"});
                $('input.number2').css({"text-align": "left"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
//                var username = $('para_username_ID').val();
//                alert(username);
//                if (username === 'ADMIN') {
//                    $('#para_username_ID').prop('readonly', false);
//                    $('#para_username_ID').css('background-color', '#ffffff');
//                } else {
//                    $('#para_username_ID').prop('readonly', true);
//                    $('#para_username_ID').css('background-color', '#ccc');
//                }
            });
            function call_submit() {
                var lv_comment_STR =
                        document.getElementById('para_comment_ID').value;
                if (lv_comment_STR === '') {
                    alert('(Thông báo)Bạn chưa nhập ghi chú nên không thể ghi nhận.');
                    return false;
                } else {
                    document.getElementById('turn_flag_ID').value = 'Y';
                    $("#update_ID").trigger("click");
                    return true;
                }
            }

//            $.subscribe('turn_flag', function (event, data) {                
//                
//            });


//            function turn_flag() {
//                var lv_comment_STR = document.getElementById('para_comment_ID').value;
//                if (lv_comment_STR === '') {
//                    alert('Bạn phải nhập ghi chú trước khi cập nhật.');
//                    return;
//                }
//                document.getElementById('turn_flag_ID').value = 'Y';
//            }


            window.onunload = closeWindow;
            function closeWindow() {
                var turn_flag = document.getElementById('turn_flag_ID').value;
                if (turn_flag === 'Y')
                    window.opener.location.reload(true);
                if (navigator.userAgent.indexOf('Chrome') !== -1
                        && parseFloat(navigator.userAgent.substring(
                                navigator.userAgent.indexOf('Chrome') + 7).split(' ')[0]) >= 15) {
                    window.open('', '_self', '');
                    self.close();
                    return false;
                } else {
                    window.open('', '_parent', '');
                    window.close();
                }
            }


            function isNumber(value)
            {
                if (value == null)
                {
                    alert('Bạn phải nhập dữ liệu cho trường này');
                    return false;
                }
                value = value.replace(/,/g, "");
                var result = true; //Luu ket qua kiem tra kieu so co dung khong
                //Kiem tra xem co nhap kieu so khong
                if (isNaN(parseFloat(value))) {
                    result = false;
                    //Neu nguoi dung khong nhap dung kieu du lieu
                    //Dua ra canh bao
                    alert('Bạn nhập không đúng kiểu số xin nhập lại dữ liệu');
//                    $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Bạn chưa nhập đầy đủ dữ liệu!');
                    focus();
                    return false;
                }
                else {
                    //Neu la kieu so --> Kiem tra xem kieu nhap co < 9999999999
                    if (parseFloat(value) > 711) {
                        result = false;
                        alert('Giá trị bạn nhập vượt quá giới hạn!');
                        //Dua ra canh bao
//                        $("#result").html('<span style="font-weight: bold; color">Thông báo:</span>  Giá trị bạn nhập vượt quá giới hạn!');
                        focus();
                        return false;
                    }
                }
            }
</script>
