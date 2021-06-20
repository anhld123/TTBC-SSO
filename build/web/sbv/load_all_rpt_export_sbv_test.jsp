<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
 <sjt:tree>
        <s:url id="loadtree_kybc" action="loadKyBC_Tree"/>
       <sjt:tree 
    		id="jsonTree" 
    		href="%{loadtree_kybc}"
    		onClickTopics="treeClicked" 
    	/>
</sjt:tree>   
</body>
</html>