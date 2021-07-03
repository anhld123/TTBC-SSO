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
            $("#loadExpRpt")[0].click();
            $("#divMessage").empty();
        }
    </script>
    <body onload="initPage()">
        <div id="menuBcttv">
            <s:form id="Query" theme="simple">
                <div style="float: right; width: 100px">
                    <s:url id="editDelFastRpt" action="loadAllFastRpt" />
                    <sj:submit id="idEditDelQuery" targets="containBcttv"  href="%{editDelFastRpt}" indicator="loadingImage" cssClass="metroButtonStyle" value="Sửa/Xóa"></sj:submit>
                    
                    <s:url id="query_loadfastrptmainparameter" action="loadfastrptmainparameter" />
                    <sj:submit id="loadExpRpt" targets="containBcttv"  href="%{query_loadfastrptmainparameter}" cssStyle="display: none;" value="Load BC"></sj:submit>
                </div>
                <div style="float: right; width: 100px" >
                    <sj:submit id="addFastRpt" targets="containBcttv"  href="bctuhstdct/module_taomau.jsp" cssClass="metroButtonStyle" value="Thêm"></sj:submit>
                </div>
            </s:form>
            
            
        </div>
        
        <div id="containBcttv">
        </div>
        
        <div id="messageDiv">
        </div>
    </body>
</html>
