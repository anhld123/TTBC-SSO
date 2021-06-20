<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>
<script src="js/formatter.js"></script>
<script src="js/pattern.js"></script>
<script src="js/pattern-matcher.js"></script>
<script src="js/utils.js"></script>

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
        background: #ffffff;
    }

</style>

<html>
    <body>           

        <h4><u>Thay đổi thông tin:</u></h4>           
        <div class="main_form">
            <s:form id="loveleaf_donator_infor_form" 
                    action="loveleaf_update_donator" theme="simple">                                                     
                <table>
                    <tr>
                        <td>Mã nhà HT</td>
                        <td><s:textfield name="donator.id" readonly="true" size="40"
                                     cssClass="readonly_txt"/></td>
                    </tr>
                    <tr>
                        <td>Tên <font style="font-family: Arial; font-size: 11pt; color: red">(*)</font></td>
                        <td><s:textfield name="donator.name" readonly="false" size="40"
                                     cssClass="edit_txt"
                                     id="donator_name_ID"/></td>
                    </tr>
                    <tr>                
                        <td>Tên TV <font style="font-family: Arial; font-size: 11pt; color: red">(*)</font></td>
                        <td><s:textfield name="donator.ll_name" 
                                     readonly="false" size="40"
                                     cssClass="edit_txt"
                                     id="donator_ll_name_ID"
                                     /></td>
                    </tr>                                            
                    <tr>                
                        <td>CMT</td>
                        <td><s:textfield name="donator.pass_no" 
                                     readonly="false" size="40"
                                     cssClass="edit_txt"
                                     /></td>
                    </tr>               
                    <tr>                
                        <td>Ngày cấp</td>
                        <td><s:textfield name="donator.pass_i_dt" 
                                     readonly="false" size="40"
                                     cssClass="edit_txt"
                                     id="pass_i_dt_ID"
                                     placeholder="dd/mm/yyyy"
                                     /></td>
                    </tr>      
                    <tr>                
                        <td>Nơi cấp</td>
                        <td><s:textfield name="donator.pass_i_plc" 
                                     readonly="false" size="40"                                     
                                     cssClass="edit_txt"
                                     /></td>
                    </tr> 
                    <tr>                
                        <td>Địa chỉ</td>
                        <td><s:textfield name="donator.address" 
                                     readonly="false" size="40"
                                     cssClass="edit_txt"
                                     /></td>
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
                                   formIds="loveleaf_donator_infor_form"
                                   targets="div_contentID"                                                                                                   
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
    </body>
</html>


<script language="javascript" 
        type="text/javascript">

            $('#pass_i_dt_ID').formatter({
                'pattern': '{{99}}/{{99}}/{{9999}}'
            });

            function call_submit() {
                var lv_name_STR =
                        document.getElementById('donator_name_ID').value;
                var lv_llname_STR =
                        document.getElementById('donator_ll_name_ID').value;
                if (lv_llname_STR === ''
                        || lv_name_STR === '') {
                    alert('(Thông báo)Bạn chưa nhập giá trị tên nên không thể ghi nhận.');
                    return false;
                } else {
                    document.getElementById('turn_flag_ID').value = 'Y';
                    $("#update_ID").trigger("click");
                    return true;
                }
            }

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
</script>