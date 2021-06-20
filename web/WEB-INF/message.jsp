<%-- 
    Document   : message
    Created on : Jul 1, 2014, 10:04:08 AM
    Author     : Trung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<style>
    .metroButtonStyle {
        font-family: 'Segoe UI', 'Open Sans', Arial, sans-serif;
        display: block;
        color: rgb(255, 255, 255);
        text-decoration: none;
        text-align: center;
        width: 90px;
        height: 26px;
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
    .ui-dialog{
        font-size: 12px;
    } 
</style>

<script>
    $(document).ready(function(){
        // your code
        var message = document.getElementById('message_ID').innerHTML;
        var dem = 0;        
        // xu ly phan dam
        while(true){
//            alert(message);
            var vitri = message.search('&lt;dam&gt;');    
            if (vitri === -1)
                break;     
            if (dem%2===0){
                message = message.substr(0,vitri) 
                        + '<b>' + message.substr(vitri+11) ;
//                alert(message);
            }else {
                message = message.substr(0,vitri) 
                        + '</b>' + message.substr(vitri+11) ;
            }
            dem++;                   
        }
        // xy ly phan nghieng
        dem = 0;
        while(true){
            var vitri = message.search('&lt;nghieng&gt;');     
            if (vitri === -1)
                break;      
            if (dem%2===0){
                message = message.substr(0,vitri) 
                        + '<i>' + message.substr(vitri+15) ;
//                alert(message);
            }else {
                message = message.substr(0,vitri) 
                        + '</i>' + message.substr(vitri+15) ;
            }
            dem++;                  
        }
        //Mau
        dem = 0;
        while(true){
            alert(message);
            var vitri = message.search('&lt;do&gt;');    
            if (vitri === -1)
                break;     
            if (dem%2===0){
                message = message.substr(0,vitri) 
                        + '<font color="red">' + message.substr(vitri+10) ;
//                alert(message);
            }else {
                message = message.substr(0,vitri) 
                        + '</font>' + message.substr(vitri+10) ;
            }
            dem++;                   
        } 
        dem = 0;
        while(true){
//            alert(message);
            var vitri = message.search('&lt;xanh&gt;');    
            if (vitri === -1)
                break;     
            if (dem%2===0){
                message = message.substr(0,vitri) 
                        + '<font color="blue">' + message.substr(vitri+12) ;
//                alert(message);
            }else {
                message = message.substr(0,vitri) 
                        + '</font>' + message.substr(vitri+12) ;
            }
            dem++;                   
        } 
//        alert(message);
        document.getElementById('message_ID').innerHTML = message;
        });
</script>

<div style="padding-left: 5px;">
    <p style="color: red; font-family: Arial; font-size: 13px;"
       id="message_ID"
       ><s:property value="message" /></p>
</div>

