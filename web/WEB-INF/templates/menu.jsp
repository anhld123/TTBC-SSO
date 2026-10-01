<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="javax.servlet.http.*,javax.servlet.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>   
<%@ page import="java.io.*,java.util.*" %>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>

<s:head/>
<sj:head/>
<style>
    #ai-chat-icon:hover{transform:scale(1.15) rotate(5deg)}
    #ai-chat-icon img{width:100%;height:100%;object-fit:contain;border-radius:0;filter:drop-shadow(0 6px 12px rgba(6,113,63,.45));transition:filter .3s ease}
    #ai-chat-icon:hover img{filter:drop-shadow(0 0 12px rgba(6,113,63,.9)) drop-shadow(0 6px 18px rgba(6,113,63,.5))}
    #ai-chat-window iframe{border:none;width:100%;height:100%;display:block}
    @keyframes aiFloat{0%,100%{transform:translateY(0)} 50%{transform:translateY(-7px)}}
    #ai-chat-icon {position: fixed;bottom: 25px;right: 25px;width: 80px;height: 80px;display: flex;justify-content: center;align-items: center;cursor: pointer;z-index: 100001;transition: all .3s ease;}
    #ai-chat-window {position: fixed;bottom: 25px;right: 25px;width: 380px;height: 550px;border-radius: 16px;z-index: 100000;display: none;overflow: hidden;box-shadow: 0 12px 35px rgba(0,0,0,.3);}
</style>
<script>
    function Redirect(url) {
        var ua = navigator.userAgent.toLowerCase(),
                isIE = ua.indexOf('msie') !== -1,
                version = parseInt(ua.substr(4, 2), 10);
        if (isIE && version < 9) {
            var link = document.createElement('a');
            link.href = url;
            document.body.appendChild(link);
            link.click();
        } else {
            window.location.href = url;
        }
    }
</script>
<script type="text/javascript" src="js/jquery-2.1.26.js"></script>
<link href="menu/Menustyle.css" rel="stylesheet" type="text/css"/>

<div id="cssmenu">        
    <ul>
        <s:iterator value="#session.USER_MENU_TREE_3LEVELS" var="parentEntry">
            <li>
                <s:url action="Menu_redirect.action" var="urlParent" escapeAmp="false">
                    <s:param name="menuUrl">${parentEntry.key.navigateUrl}</s:param>                        
                    <s:param name="menuId">${parentEntry.key.menuId}</s:param>  
                    <s:param name="userName">${username}</s:param>  
                </s:url>
                <a href="<s:property value='#urlParent' />" style="text-transform: uppercase; font-family: Arial; color: #06713F; font-size: 12px;">
                    <s:property value='#parentEntry.key.text' />
                </a>
                
                <s:if test="#parentEntry.value != null && #parentEntry.value.size() > 0">
                    <ul>
                        <s:iterator value="#parentEntry.value" var="level1Entry">
                            <li>
                                <s:url action="Menu_redirect.action" var="urlLevel1" escapeAmp="false">
                                    <s:param name="menuUrl">${level1Entry.key.navigateUrl}</s:param>                        
                                    <s:param name="menuId">${level1Entry.key.menuId}</s:param>  
                                    <s:param name="userName">${username}</s:param>  
                                </s:url>
                                <a href="<s:property value='#urlLevel1' />" style="font-family: Arial; color: #333333; font-size: 11px;">
                                    <s:property value='#level1Entry.key.text' />
                                </a>
                                
                                <s:if test="#level1Entry.value != null && #level1Entry.value.size() > 0">
                                    <ul>
                                        <s:iterator value="#level1Entry.value" var="level2Item">
                                            <li>
                                                <s:url action="Menu_redirect.action" var="urlLevel2" escapeAmp="false">
                                                    <s:param name="menuUrl">${level2Item.navigateUrl}</s:param>                        
                                                    <s:param name="menuId">${level2Item.menuId}</s:param>  
                                                    <s:param name="userName">${username}</s:param>  
                                                </s:url>
                                                <a href="<s:property value='#urlLevel2' />" style="font-family: Arial; color: #666666; font-size: 11px;" >
                                                    <s:property value='#level2Item.text' />
                                                </a>
                                            </li>
                                        </s:iterator>
                                    </ul>
                                </s:if>
                            </li>
                        </s:iterator>
                    </ul>
                </s:if>
            </li>
        </s:iterator>
    </ul>  
</div>