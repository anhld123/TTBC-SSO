<%-- 
    Document   : select_privileage
    Created on : Dec 15, 2014, 1:48:25 PM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<s:head/>
<sj:head/>

<!DOCTYPE html>
<html>
    <head>
        <title>Đồng bộ số liệu từ nguồn MSSQL</title>        
    </head>    
    <style type="text/css">             

    </style>   
    <body>       
        <s:form id="importDataForm" theme="simple" >
            <p style="font-family: Time; font-size: 12pt; font-weight: bold; color: #18ab29"> 
                Đồng bộ bảng danh mục: </p>
            <div id="maindiv">
                <table border="1" style="width: 100%;">
                    <tr>
                        <td>Địa chỉ Host:</td>
                        <td>
                            <s:url var="buildHostAddressComboUrl" 
                                   action="MSSQLImportBuildCombo.action"></s:url>
                            <sj:select href="%{buildHostAddressComboUrl}" 
                                       name="hostAddress"
                                       id="hostAddressId"
                                       list="serverAddresses"    
                                       onChangeTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false"                                        
                                       theme="simple"                                       
                                       ></sj:select>                               
                        </tr>
                        <tr>
                            <td>Cơ sở dữ liệu:</td>
                            <td>
                            <sj:select href="%{buildHostAddressComboUrl}" 
                                       name="databaseName"
                                       id="databaseNameId"
                                       list="databaseNames"    
                                       onChangeTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false"                                        
                                       theme="simple"                                       
                                       ></sj:select>    
                            </td>
                        </tr>
                        <tr>
                            <td>Bảng dữ liệu:</td>
                            <td>
                            <sj:select href="%{buildHostAddressComboUrl}" 
                                       name="tableName"
                                       id="tableNameId"
                                       list="tableNames"    
                                       onChangeTopics="reloadModuleList"
                                       listKey="sKey"
                                       listValue="sDesc"
                                       emptyOption="false"                                        
                                       theme="simple"                                       
                                       ></sj:select>
                            </td>
                        </tr>  
                        <tr>
                            <td>Từ ngày:</td>
                            <td>
                            <sj:datepicker name="fromDate" value=""  
                                           onblur="validatedate(this.value)"
                                           placeholder="DD/MM/YYYY" changeYear="true" 
                                           changeMonth="true" displayFormat="dd/mm/yy"
                                           id="datepicker001" size="15"/>                            
                        </td>
                    </tr>
                    <tr>
                        <td>Đến ngày:</td>
                        <td>
                            <sj:datepicker name="toDate" value=""  
                                           onblur="validatedate(this.value)"
                                           placeholder="DD/MM/YYYY" changeYear="true" 
                                           changeMonth="true" displayFormat="dd/mm/yy"
                                           id="datepicker002" size="15"/>
                            <script>
                                var ls_today = new Date();

                                if (ls_today.getDate() > 29) {
                                    var lj_setDate = 1 + "/" +
                                            (ls_today.getMonth()+1) + "/" + ls_today.getFullYear();
                                    document.getElementById("datepicker001").value = lj_setDate;
                                    var lastDayOfMonth =
                                            new Date(ls_today.getFullYear(), ls_today.getMonth()+1, 0);
                                    var lastDayOfMonth_fstr = formatDate(lastDayOfMonth);
                                    document.getElementById("datepicker002").value = lastDayOfMonth_fstr;
                                } else
                                {
                                    var lj_setDate = 1 + "/" +
                                            (ls_today.getMonth()) + "/" + ls_today.getFullYear();
                                    document.getElementById("datepicker001").value = lj_setDate;
                                    var lastDayOfMonth =
                                            new Date(ls_today.getFullYear(), ls_today.getMonth(), 0);
                                    var lastDayOfMonth_fstr = formatDate(lastDayOfMonth);
                                    document.getElementById("datepicker002").value = lastDayOfMonth_fstr;
                                }
                                function formatDate(value)
                                {
                                    return value.getDate()
                                            + "/"
                                            + (value.getMonth() + 1)
                                            + "/"
                                            + value.getFullYear();
                                }
                            </script>
                        </td>
                    </tr>                             
                </table>
                <hr/>
                <table border="0" style="width: 100%;">
                    <tr ><td colspan="2" align="right">
                            <input type="button" value="Synchronize" 
                                   onclick="syncclick();"></input>

                            <s:url id="syncDataUrl" action="MSSQLImportDataSync.action">                                            
                            </s:url>
                            <sj:a id="syncData_id"  
                                  href="%{syncDataUrl}"                                                                                                
                                  formIds="importDataForm"                                
                                  targets="messageDiv"
                                  button="false"                                                                                 
                                  theme="simple"></sj:a>
                                <script>
                                    function syncclick() {
                                        $("#syncData_id").trigger("click");
                                    }
                                </script>
                            </td></tr>
                    </table>
                </div>
        </s:form>
        <div  style="float: left; width: 100%; font-family: Arial;font-size: 8pt;" >          
            <div id="loadingImageDiv" style="display: none;">
                <img id="loadingImage" src='img/loading.gif' border='0' >
            </div>            
            <div id="messageDiv"></div>                  
        </div>
    </body>    
</html>