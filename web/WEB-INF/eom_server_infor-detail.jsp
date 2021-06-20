<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:head/>
<sj:head/>

<style type="text/css">
    /*Phan xu ly text cho readonly*/    
    .readonly_txt_STYLE {
        background-color: paleturquoise;
        width: 100%;
    }
    .serverinfor_form_STYLE {
        padding:10px;
        width:50%;        
        border:1px solid #ccc;
        font-family:Arial;    
        font-size: 11pt;
        text-align:left;
        height: 240px;
        /*    overflow: scroll;*/
    }
    .label_STYLE {
        font-family: Arial;
        font-size: 11pt;
    }
</style>

<div id="server_infor_form_ID" 
     class="serverinfor_form_STYLE">  
    <h4 style="padding: 5px;"><u>Thông tin máy chủ:</u></h4>
    <s:form action="#" theme="simple">        
        <table style="padding: 5px;" cellspacing="5px">            
            <tr>
                <td style="width: 15%;">
                    <s:label value="Mã"
                             cssClass="label_STYLE"/>
                </td>
                <td style="width: 35%;">
                    <s:textfield key="serverInfor.serverID" 
                                 readonly="true"  
                                 cssClass="readonly_txt_STYLE"/>
                </td>                
            </tr>    
            <tr>
                <td style="width: 15%;">
                    <s:label value="Tên"
                             cssClass="label_STYLE"/>
                </td>
                <td style="width: 35%;">
                    <s:textfield key="serverInfor.serverName"
                                 readonly="true"  
                                 cssClass="readonly_txt_STYLE"/>
                </td>
            </tr>
            <tr>
                <td style="width: 15%;">
                    <s:label value="Địa chỉ"
                             cssClass="label_STYLE"/>
                </td>
                <td style="width: 35%;">
                    <s:textfield key="serverInfor.serverAddress" 
                                 readonly="true"  
                                 cssClass="readonly_txt_STYLE"/>
                </td>
            </tr>
            <tr>
                <td style="width: 15%;">
                    <s:label value="Tổng số JOB(s) tồn tại:"
                             cssClass="label_STYLE"/>
                </td>
                <td style="width: 35%;">
                    <s:textfield key="serverInfor.numofjob" 
                                 readonly="true"  
                                 cssClass="readonly_txt_STYLE"/>
                </td>
            </tr>
            <tr>
                <td style="width: 15%;">
                    <s:label value="Chi tiết JOB(s):"
                             cssClass="label_STYLE"/>
                </td>
                <td style="width: 35%;">
                    <s:textarea key="serverInfor.jobdetail" 
                                rows="6"
                                 readonly="true"  
                                 cssClass="readonly_txt_STYLE"/>
                </td>
            </tr>
        </s:form>
</div>