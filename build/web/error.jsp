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
<h3 style="color: red"><s:property value="message" escape="false"/> </h3>
