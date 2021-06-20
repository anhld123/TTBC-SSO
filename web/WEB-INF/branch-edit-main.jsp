<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:head/>
<sj:head/>

<link rel="stylesheet" type="text/css" href="css/main.css">
<style>
    .readonly {
        background: whitesmoke;
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
</style>

<script>
    $.subscribe('before-next', function(event, data) {

        var pos_name_tag = document.getElementById('pos_name_id');
        var pos_address_tag = document.getElementById('pos_address_id');

        if (pos_name_tag.value === '' || pos_address_tag.value === '') {
            alert('(!)Bạn chưa nhập tên đơn vị hoặc địa chỉ.');
            Event.preventDefault();
            return false;
        }
    });
</script>

<div id="maindiv" class="pos_edit_form">
    <s:form id="branch_edit_form" >
        <p style="font-family: Arial;font-size: 12pt;
           text-decoration: underline;padding-left: 5px;font-size: 12pt;font-weight: bold;"> 
            Cập nhật thông tin Branch
        </p>
        <table style="padding: 5px;" cellspacing="5px">            
            <tr>                
                <td>
                    <s:textfield key="pos_cd" readonly="true" size="35" label="Branch (*)"
                                 cssClass="readonly"/>
                </td>                
                <td>
                    <s:textfield id="pos_name_id" name="pos_name" size="35" label="Tên đơn vị (*)"/>
                </td>
            </tr>
            <tr>                
                <td>
                    <s:textfield id="pos_address_id" name="pos_address" size="35" label="Địa chỉ (*)"/>
                </td>               
                <td>
                    <s:textfield name="pos_fax" size="35" label="Fax"/>
                </td>
            </tr>
            <tr>                
                <td>
                    <s:textfield name="pos_mobile" size="35" label="Tel/Mobile"/>
                </td>                
                <td>
                    <s:textfield name="main_pos" readonly="true" size="35"
                                 cssClass="readonly" label="Main pos (*)"
                                 />
                </td>
            </tr>
            <tr>                
                <td>
                    <s:textfield name="pos_sbvcode" size="35" readonly="true"
                                 cssClass="readonly" label="Mã SBV (*)"/>
                </td>   

                <td>
                    <s:hidden name="status" ></s:hidden>
                       <%----* <s:select name="status" label="Trạng thái (*)"
                                  list="{'O','C'}"  cssStyle="visibility: hidden;"

                                  />            ---%>        

                </td>    
            </tr>
            <tr>                      
                <td>      
                    <s:hidden name="pos_flag" ></s:hidden>
                    <%----* <s:select name="pos_flag" label= "Pos_flag (*)"
                              list="{'S','M'}" cssStyle="visibility: hidden;"                             
                              /> ---%>

                </td>                
            </tr>            
            <tr>
                <td colspan="2" align="right">
                    <div id="button_div" <s:property value="disabled" /> >
                        <s:url id="edit_url" action="update_branch_infor" escapeAmp="false"
                               var="update_url">
                            <s:param name="proc">update</s:param>  
                        </s:url>                        
                        <sj:a id="update_button_id"  href="%{#update_url}" 
                              targets="content_div"
                              formIds="branch_edit_form"    
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
    </s:form>
</div>