<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>


<s:head/>
<sj:head/>

<link rel="stylesheet" type="text/css" href="css/main.css">

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
</style>

<script>
            var max_row = 0;
            $(document).ready(function () {
                $(".TEN_KH").css({"width": "100%"});
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script> 

<script>
    $.subscribe('before-next', function(event, data) {

//        var pos_name_tag = document.getElementById('pos_name_id');
//        var pos_address_tag = document.getElementById('pos_address_id');
//
//        if (pos_name_tag.value === '' || pos_address_tag.value === '') {
//            alert('(!)Bạn chưa nhập tên đơn vị hoặc địa chỉ.');
//            Event.preventDefault();
//            return false;
//        }
    });
    
    function initTable()
            {
                var table = document.getElementById("tablesms02");
                var rowcount = table.rows.length;    
                rowcount = rowcount > max_row ? rowcount : max_row;                
                for (var i = 0; i < rowcount; i++)
                {                    
                    var matmp = getMabyNumber(i);//    
                    if(matmp == 1)
                    {
                        $('input:checkbox[id='+i+']').attr('checked',true);
                    }
                }
            }
            
            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
</script>

    <s:form id="branch_edit_formsms" >
        <p style="font-family: Arial;font-size: 12pt;
           text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            
        </p>
        
        <div id="divTitle">
                    ĐĂNG KÝ SỬ DỤNG DỊCH VỤ TIN NHẮN THAY ĐỔI SỐ DƯ
                </div>
        
        <table id="tablesms02" style="padding: 5px;" cellspacing="5px">                                              
        <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                                                
            <tr >
                <td style="width: 116px" >
                    Mã khách hàng
                </td>
                <td style="width: 316px" >
                    <input type="text"  value="<s:property  value="D1" />"
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH "  onfocus="this.select();"/>
                </td>                                
                                             
            </tr>  
            
            <tr >
                <td style="width: 116px" >
                    Tên khách hàng
                </td>
                <td  style="width: 316px"  >
                    <input type="text"  value="<s:property  value="D2" />"
                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH"   onfocus="this.select();" readonly="readonly"/>
                </td>                                
                                             
            </tr>  
            
            <tr >
                <td style="width: 116px" >
                    Số tài khoản
                </td>
                <td  style="width: 316px"  >
                    <input type="text" value="<s:property  value="D3" />"
                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH"   onfocus="this.select();" readonly="true"/>
                    <input type="hidden" value="<s:property  value="D9" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>"/>
                    <input type="hidden" value="<s:property  value="MAPGD" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" value="<s:property  value="MAPGD"/>"/>
                    
                </td>                                
                                             
            </tr>  
            
            <tr >
                <td style="width: 116px" >
                    Tên tài khoản
                </td>
                <td  style="width: 316px"  >
                    <input type="text" value="<s:property  value="D4" />"
                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH"   onfocus="this.select();" readonly="true"/>
                </td>                                
                                             
            </tr>  
            
            <tr >
                <td style="width: 116px" >
                    Ngày thành lập
                </td>
                <td  style="width: 316px"  >
                    <input type="text"  value="<s:property  value="D5" />"
                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH"  onfocus="this.select();" readonly="true"/>
                </td>                                
                                             
            </tr>  
            
            <tr >
                <td style="width: 116px" >
                    Số giấy phép KD
                </td>
                <td  style="width: 316px"  >
                    <input type="text"  value="<s:property  value="D6" />"
                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH"  onfocus="this.select();" readonly="true"/>
                </td>                                                                             
            </tr>  
            
            <tr >
                <td style="width: 116px" >
                    Địa chỉ
                </td>
                <td  style="width: 316px"  >
                    <input type="text"  value="<s:property  value="D7" />"
                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" class="TEN_KH"  onfocus="this.select();" readonly="true"/>
                </td>                                                                             
            </tr> 
            
            <tr >                
                <td style="width: 116px" >
                    Sử dụng dịch vụ
                </td>
                <td   class="TD_DAT_KODAT">    
                            <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].D3" value="<s:property  value="D3" />" />
                        </td>                                 
                                             
            </tr>  
            
            <tr >
                <td style="width: 116px" >
                    Điện thoại
                </td>
                <td  style="width: 316px"  >
                    <input type="text"  value="<s:property  value="D8" />"
                        name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH readonly"  onfocus="this.select();"/>
                </td>                                
                                             
            </tr> 
            
             </s:iterator>
            <tr>
                <td colspan="2" align="right">
                    <div id="button_div" <s:property value="disabled" /> >
                        <s:url id="edit_url" action="saveSMS" escapeAmp="false"
                               var="update_url">
                            <s:param name="proc">update</s:param>  
                        </s:url>                        
                        <sj:a id="update_button_id"  href="%{#update_url}" 
                              targets="content_div"
                              formIds="branch_edit_formsms"    
                              onBeforeTopics="before-next"
                              button="false"
                              cssClass="metroButtonStyle"   
                              >     

                            Cập nhật
                        </sj:a>
                    </div>
                </td>       

            </tr>                    
        </table>
        <div id="content_div"></div>    
        <script>
            initTable();
        </script>
    </s:form>