<%-- 
    Document   : table_list_report
    Created on : Sep 16, 2016, 10:11:55 AM
    Author     : TomFC
--%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <style>
        th{
            background-color: #DCDCDC;
            border-color: #999;
            height: 18px;
        }
        td{
            border-color: #999;
            height: 20px;
        }
        table.editDelete{
            border-collapse: collapse;
            width: 100%;
            border-color: #999;
        }
        table.editDelete tr:focus{
            background-color:#FFE47A;
            /*cursor: pointer; hover*/
        }

    </style>

    <SCRIPT language="javascript">
        $(document).ready(function () {
            $(".TD_CHON").css({"width": "15px"});
            $(".TD_MABC").css({"width": "40px"});
            $(".TD_TENBC").css({"width": "200px"});

        });
        
        $(document).ready(function () {
            $("#allCheck").change(function () {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
        });

    </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%--<s:form id="test" theme="simple">--%>  
            <table border="1" align="center" style="width: 100%; border-collapse: collapse;" >
                <tr>
                    <th width="20px" >
                        <s:checkbox id ="allCheck" name="allCheck" theme="simple"/>
                    </th>                    
                    <th width="60px">Mã báo cáo</th>
                    <th  >Tên báo cáo</th>                    
                </tr>    

                <s:iterator value="#attr.lstObjRpt" var="modelReport" status="rowstatus">
                    <tr>
                        <td align = "center"> 
                            <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="ma_bc[%{#rowstatus.index}]" fieldValue="%{sKey}" theme="simple"/>                        
                        </td>

                        <td width="60px">
                            <s:property  value="sKey" />

                        </td>
                        <td>
                            <s:property  value="sDesc" />
                        </td>
                    </tr>

                </s:iterator>
            </table>
        <%--</s:form>--%>
    </body>
</html>
