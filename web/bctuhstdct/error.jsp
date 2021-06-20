<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<hr/>
 <style>
            th{
                background-color: #DCDCDC;
                border-color: #999;
            }
            td{
                border-color: #999;
            }
            table.editDelete{
                border-collapse: collapse;
                width: 60%;
                border-color: #999;
            }
            table.editDelete tr:hover{
                background-color:#FFE47A;
                cursor: pointer;
            }
        </style>
        <h3 style="color: red">
            <div id="divMessage">
                
            </div>
            <input type="text" id="message" value="<s:property value="message" escape="false"/>" style="display: none;"/> 
        </h3>
<script>
    $("#divMessage").empty();
    var message = $("#message").val();
//    $("#divMessage").html(message);
    alert(message);
    
    //tungnv: viet cho form risk
    //Load lai form tai lai du lieu
//    $("#idSubmit")[0].click();
</script>
