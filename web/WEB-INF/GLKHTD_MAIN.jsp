<!DOCTYPE html> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>

<html>
  
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head jqueryui="true" jquerytheme="redmond"/>
        
        <style>
            .ui-dialog { font: 13px verdana,arial; }
        </style>
    </head>
    <script> 
        
        function clk_glkhtd() {
            var lstPos = "";
            $('#treeView').jstree("get_checked",null,true).each(
                    function(){
                            lstPos = lstPos + this.id + ',';
            });
            document.getElementById("lstPOS").value = lstPos ;
            document.getElementById("wait").style.visibility="visible";
            document.getElementById("frmdownloadFile").style.visibility="hidden";
        }  
        
        
    </script>
    

    <%
        session.setAttribute("startTreeGLKHTDRecursive", null);
    %>   
    <h4> Chọn POS : </h4> 
    <div  style="
            float: left;
            height:600px;
            width: 450px;
            overflow:scroll">

        <s:url var="echoO" action="GLKHTDTree"/>
        <sjt:tree  
            id="treeView"
            jstreetheme="default"
            rootNode="nodes"
            nodeIdProperty="id"
            nodeTitleProperty="name"
            href="%{echoO}"
            childCollectionProperty="children"
            checkbox="true"
            
        />
    </div>
    <div align="center">
        <h4> KẾ HOẠCH THỰC HIỆN NGUỒN VỐN </h4>    
        <s:form theme="simple" action="GLKHTD_OUT">   
            Ngày báo cáo : 
            <sj:datepicker value="today" displayFormat="dd/mm/yy" id="dpkNgaybc" name="ngaybc" />  
            
            <%
                if (session.getAttribute("reportGrade").equals("1")) {
            %>
                <s:checkbox name="commune" /> Tổng hợp theo Thôn
            <% } %>
            
            </br>
            </br>
            <s:submit value = "Lập kế hoạch" onclick="clk_glkhtd()" action="GLKHTD_KH" />
            <s:submit value = "Thực hiện kế hoạch" onclick="clk_glkhtd()" action="GLKHTD_TH"  />
            <s:hidden id="lstPOS" value="" name="lstPos"/>
            
            <br/>
            <br/>
            <br/>
            <div id="frmdownloadFile" style="visibility: visible">
                <h3 align="center">
                    ${lblFile}
                </h3>

                <s:url id="DownloadFile1_TH" action="DownloadFile1_TH" />
                <s:url id="DownloadFile2_TH" action="DownloadFile2_TH" />
                <s:a href="%{DownloadFile1_TH}" >
                    ${downloadFile1}
                </s:a>
                <br/>
                <s:a href="%{DownloadFile2_TH}">
                    ${downloadFile2}
                </s:a>
            </div>
        </s:form> 
            
    </div>            
  
    <div id ="wait" style="visibility:hidden" align="center" >
        <h3> 
            Đang tổng hợp dữ liệu. Xin đợi ... 
        </h3>
    </div>
    
    <s:if test="hasActionMessages()">
        
        <sj:dialog 
            modal="true"  
            title="Thông báo:"
            width="500"
            buttons="{'OK':function() { $(this).dialog('close'); }}" 
            cssStyle="font: 12px verdana,arial;"
            >
            <s:actionmessage/>
        </sj:dialog>
    </s:if>

</html>