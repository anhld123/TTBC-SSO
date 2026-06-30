<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>  
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:head/>
<sj:head/>

<script>
    function Redirect(url) {
        var ua = navigator.userAgent.toLowerCase(),
                isIE = ua.indexOf('msie') !== -1,
                version = parseInt(ua.substr(4, 2), 10);
        // IE8 and lower
        if (isIE && version < 9) {
            var link = document.createElement('a');
            link.href = url;
            document.body.appendChild(link);
            link.click();
        }
        // All other browsers
        else {
            window.location.href = url;
        }
    }
</script>
<script type="text/javascript" src="js/jquery-2.1.26.js"></script>
<link href="menu/Menustyle.css" rel="stylesheet" type="text/css"/>

<s:bean name="vbsp.ims.bean.MenuBean" var="menu">  
</s:bean>

<div id="cssmenu">       
<ul>
    <s:iterator value="#menu.menuItems" status="menu1" var="link">            
        <s:if test="#link.parentId == 0 ">        
            <li>     
                <s:url action="Menu_redirect.action" var="urlTag" escapeAmp="false">                
                    <s:param name="menuUrl">${link.navigateUrl}</s:param>                                    
                    <s:param name="menuId">${link.menuId}</s:param>  
                    <s:param name="userName">${username}</s:param>  
                </s:url>
                <s:if test="#link.childTotal == 0 ">
                    <a href="<s:property value="#urlTag" />" style="font-family: Arial; color: #666666;font-size: 12px;">${link.text}</a>                                
                </s:if>
                <s:else>
                    <a href="#"  style="text-transform: uppercase; font-family: Arial; color: #06713F; font-size: 12px;">${link.text}</a>
                </s:else>
                <ul class="sub_menu">
                    <s:iterator value="#menu.menuItems" status="menu2" var="link2">            
                        <s:if test="#link2.parentId == #link.menuId ">        
                            <li>     
                                <s:url action="Menu_redirect.action" var="urlTag" escapeAmp="false">                
                                    <s:param name="menuUrl">${link2.navigateUrl}</s:param>                                    
                                    <s:param name="menuId">${link2.menuId}</s:param>    
                                    <s:param name="userName">${username}</s:param>  
                                </s:url>
                                <s:if test="#link2.childTotal == 0 ">
                                    <a href="<s:property value="#urlTag" />" style="font-family: Arial; color: #666666;font-size: 12px;">${link2.text}</a>                                
                                </s:if>
                                <s:else>
                                    <a href="#"  style="font-family: Arial; color: #666666; font-size: 12px;">${link2.text}</a>
                                </s:else>
                                <s:if test="#link2.childTotal > 0 ">  
                                    <ul>
                                        <s:iterator value="#menu.menuItems" status="menu3" var="link3">   
                                            <s:if test="#link3.parentId == #link2.menuId "> 
                                                <li>
                                                    <s:url action="Menu_redirect.action" var="urlTag" escapeAmp="false">                
                                                        <s:param name="menuUrl">${link3.navigateUrl}</s:param>                                    
                                                        <s:param name="menuId">${link3.menuId}</s:param>    
                                                        <s:param name="userName">${username}</s:param>  
                                                    </s:url>
                                                    <a href="<s:property value="#urlTag" />"  style="font-family: Arial; color: #666666; font-size: 12px;" >${link3.text}</a>
                                                </li>
                                            </s:if>
                                        </s:iterator>
                                    </ul>
                                </s:if>
                            </li>        
                        </s:if>
                    </s:iterator>
                </ul>
            </li>        
        </s:if>
    </s:iterator>
</ul>  
</div>
