<%-- classic2 layout --%> 
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html style="height: 100%;">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="shortcut icon" type="image/x-icon" href="img/Logo_VBSP.ico"/>
        <link rel="icon" type="image/icon" href="img/Logo_VBSP.ico"/>
        <link href="${param.css}" type="text/css" rel="stylesheet" />        
        <title> ${param.title}</title>
        <style>
            body,td,th,font{ font-family:Tahoma; font-size:12px; }
        </style>
    </head>
    <body style="height:100%;" style="font-family: Tahoma; font-size: 10pt" topmargin="0" leftmargin="0">
        <table border="0" cellpadding="0" cellspacing="0" width="100%" valign = "top" 
               style="background: url(img/Background_Index.png) no-repeat right bottom;">
            <tr>
                <td valign="top" height="20px">
                    <jsp:include page="${param.header}" flush="false"/>
                </td>
            </tr>
            <tr>
                <td align="left" height="40px" width="100%" bgcolor="#f8f3f7" valign = "bottom">
                    <jsp:include page="${param.menu}" flush="false"/>
                </td>                
            </tr>
            <tr>
                <td align="left" height="8px" width="100%" bgcolor="FFFFFF" valign = "top" style="height: 5px;">                    
                </td>                
            </tr>
            <tr>
                <td valign="top" height="522px">                    
                        <jsp:include page="${param.body}" flush="true"/>                    
                </td>
            </tr>
            <tr>
                <td height="7px"  align="left" valign = "bottom"><p style="font-size: 12px;color:#565656; 
                font-face: Tahoma; margin-bottom:5;">&nbsp;&nbsp;&nbsp;
                © Bản quyền thuộc về Trung tâm Công nghệ thông tin - Ngân hàng Chính sách xã hội Việt Nam </p></td>
            </tr>
            <tr>
                <td height="7px" width = "100%" border="0" cellpadding="0" cellspacing="0">
                    <table width = "100%" cellpadding="0" cellspacing="0">
                        <tr>
                            <td width="65%" bgcolor="015323" height="7px"></td>
                            <td width="9%" bgcolor="018c3b" height="7px"></td>
                            <td width="26%" bgcolor="efefef" height="7px"></td>
                        </tr>
                    </table></td>
            </tr>
        </table>
    </body>
</html>
