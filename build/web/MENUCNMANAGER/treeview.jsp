<%-- 
    Document   : treeview
    Created on : Jan 22, 2016, 2:17:35 PM
    Author     : Trung
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>


<s:head/>
<sj:head/>


<s:url var="echoO" action="buildMenuTreeView"/>
<sjt:tree  
    id="treeView"
    jstreetheme="apple"
    rootNode="nodes"
    nodeIdProperty="id"
    nodeTitleProperty="name"
    href="%{echoO}"
    childCollectionProperty="children"    
    openAllOnLoad="true" 
    cssStyle="border:none;background:white;"
    /> 
