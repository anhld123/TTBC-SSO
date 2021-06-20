<%-- 
    Document   : params
    Created on : Nov 12, 2015, 10:57:10 PM
    Author     : Le Duc Hung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
    <head>        
        <script src="js/jquery-1.10.2.js" type="text/javascript"></script>
        <script src="js/params.js" type="text/javascript"></script>
        <script>                                         
            $(document).ready(function () {                                
                initBranchParams();
            });            
        </script>
    </head>

    <body>                
        <s:hidden id="impDaoImpClassName" value="vbsp.ims.dao.implement.DaoParamsImp"/>
        <s:select id="cboBranchInfo" name="paramBranchInfo"
                  list="{'--Tat ca--'}" label="Chi nhánh" />        
        <s:hidden id="cboBranchInfoProc" value=""/>
        <br />
        <s:select id="cboPosInfo" name="paramPosInfo"
                  list="{'--Tat ca--'}" label="Phong giao dich" />     
        <s:hidden id="cboPosInfoProc" value=""/>
        <br />
        <s:select id="cboCommuneInfo" name="paramCommuneInfo" 
                  list="{'--Tat ca--'}" label="Xa" />        
        <br />
        <s:select id="cboGroupInfo" name="paramGroupInfo"
                  list="{'--Tat ca--'}" label="To" />                        
        <br />        
    </body>
</html>