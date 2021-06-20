<%-- 
    Document   : message
    Created on : Jul 1, 2014, 10:04:08 AM
    Author     : Trung
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

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
//            alert(message);
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


<p id="message_ID" style="padding-left: 5px; font-family: Arial; font-size: 10pt;"
   ><s:property value="message" /></p>

