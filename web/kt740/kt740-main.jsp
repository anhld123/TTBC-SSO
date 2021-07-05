<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head/>
    </head>
    
    <style>
        #menuBcttv{
            width: 100%;
            height: 30px;                
            border: 1px solid; 
            padding-bottom: 5px;
        }
        
        #containBcttv{
            width: 100%;
            min-height:390px;
            border: 1px solid;
            margin-top: 2px;
        }
        
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
        
        
    </style>
    
    <script>
        //CuongBM: 18Jul14
        //Desc: Lan dau tien load trang, goi den su kien click load all bc
        function initPage(){
            $("#loadQuery")[0].click();
        }
    </script>
    <body onload="initPage()">
        <div id="menuBcttv">
            <s:form id="Query" theme="simple">
                <div style="float: right; width: 100px">
                    <s:url id="editDelQuerykt740" action="loadgroupEditQuerykt740" />
                    <sj:submit id="idEditDelQuery" targets="containBcttv"  href="%{editDelQuerykt740}" indicator="loadingImage" cssClass="metroButtonStyle" value="Sửa/Xóa"></sj:submit>
                    
                    <sj:submit id="loadQuery" targets="containBcttv"  href="kt740/exp_query.jsp" indicator="loadingImage" cssStyle="display: none;" value="Load BC"></sj:submit>
                    
                     <s:url id="LoadMainParamterKt740" action="loadfmainparameterkt740" />
                    <sj:submit id="loadQuery" targets="containBcttv"  href="%{LoadMainParamterKt740}" indicator="loadingImage" cssStyle="display: none;" value="Load BC"></sj:submit>
                </div>
                <div style="float: right; width: 100px" >
                     <s:url id="LoadAddNewQuerykt740" action="LoadAddNewQuerykt740" />
                    <%--<sj:submit id="addQuery" targets="containBcttv"  href="bctheotruyvan/add_query.jsp" cssClass="metroButtonStyle" value="Thêm"></sj:submit>--%>
                    <sj:submit id="addQuery" targets="containBcttv"  href="%{LoadAddNewQuerykt740}" cssClass="metroButtonStyle" value="Thêm"></sj:submit>
                </div>
            </s:form>
            
            
        </div>
        
        <div id="containBcttv">
        </div>
        
        <div id="messageDiv">
        </div>
    </body>
</html>
