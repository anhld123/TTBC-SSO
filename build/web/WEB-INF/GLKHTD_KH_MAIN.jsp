<%-- 
    Document   : newgrid
    Created on : Apr 24, 2014, 8:55:09 AM
    Author     : Administrator
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<!DOCTYPE html>
<html>
    <head>
        <sj:head jqueryui="true" jquerytheme="redmond" />
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ke hoach thuc hien nguon von </title>
        
         <style>
            .ui-dialog, .ui-jqdialog-content{ 
                font: 12px verdana,arial; 
            }
            
            .ui-jqdialog-title{
                font: 12px verdana,arial; 
                font-weight: bold;
            }
            
            #chitieu{
                width: 575px;
            }
            
            .ui-jqgrid .ui-jqgrid-view{
                font-family: Verdana, Arial, sans-serif; 
                font-size: 12px;
            }
        </style>
    </head>
    <script>
        $.subscribe('editGLKHTD', function(event,data) {
            
            var isInput = $("#khtdgrid").jqGrid("getCell", event.originalEvent.id, 3);
            if (isInput === "1") {
                $("#khtdgrid").jqGrid('editGridRow', event.originalEvent.id, {
                                        editCaption: "LẬP KẾ HOẠCH",
                                        bSubmit: "Lưu tạm",
                                        bCancel: "Thoát",
                                        width:700,
                                        height:"auto",
                                        closeAfterEdit:true
                                        }) ;
            }
            else
                alert("Không nhập chỉ tiêu tổng !!") ;
        });
         
   </script>
  
    <body>
        
        
        <s:if test="hasActionErrors()">
            <sj:dialog 
                modal="true"  
                title="Errors :"
                width="500"
                buttons="{'OK':function() { $(this).dialog('close'); }}" >

                <s:actionerror/>
            </sj:dialog>
        </s:if>
        <s:elseif test="hasActionMessages()">
            <sj:dialog 
                modal="true"  
                title="Result :"
                width="500"
                buttons="{'OK':function() { $(this).dialog('close'); }}" >

                <s:actionmessage/>
            </sj:dialog>    
        </s:elseif>
        
        <br/>
        <div align="right">
            <s:form theme="simple" action="GLKHTD_KH_Action"> 
                <s:submit value = "LƯU KẾ HOẠCH" action="GLKHTD_Save" />
                <s:submit value = "QUAY RA" />
            </s:form>    
        </div>
        
        <s:url var="homeURL" action="GLKHTDGrid"/>
        <s:url var="cellURL" action="GLKHTDGrid-Edit"/> 
        <sjg:grid
            id="khtdgrid"
            caption="Điều chỉnh Kế hoạch :"
            dataType="json"
            href="%{homeURL}"
            gridModel="gridModel"
            pager="true"
            rowNum="-1"
            rownumbers="true"
            editurl="%{cellURL}"
            editinline="true"
            navigator="false"
            viewrecords="true"
            autowidth="true"
            onSelectRowTopics="editGLKHTD"

        >
            <sjg:gridColumn name="id" index="id" title="STT" formatter="integer" sortable="false" fixed="true" width="20" />
            <sjg:gridColumn name="code" index="code" title="CODE" sortable="false" fixed="true" width="30"/>
            <sjg:gridColumn name="isinput" index="isinput" title="INPUT" sortable="false" fixed="true" width="20"/>
            <sjg:gridColumn name="chitieu" index="chitieu" title="Chỉ Tiêu" sortable="false" fixed="true" width="350" editable="true"/>
            <sjg:gridColumn name="kehoach_xd" index="kehoach_xd" title="Kế Hoạch Giao" sortable="false" 
                            formatter="currency" editable="true"  editrules="{integer: true}" align="right"/>                                   
            <sjg:gridColumn name="kehoach_dc" index="kehoach_dc" title="Điều Chỉnh" align="right"
                            formatter="currency" sortable="false" editable="true" editrules="{number: true}"/>
            <sjg:gridColumn name="kehoach_giao" index="kehoach_giao" title="KẾ HOẠCH GIAO MỚI" align="right"
                            formatter="currency" sortable="false" />

        </sjg:grid>
        

    </body>
</html>
