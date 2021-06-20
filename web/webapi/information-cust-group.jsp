<%-- 
    Document   : input_main
    Created on : Oct 26, 2015, 1:45:53 PM
    Author     : LION
--%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<span id="id_custid">Mã tổ</span>&nbsp;
<input type="text" value="" name="groupId" id="idgroupId" placeholder="Mã tổ" />
&nbsp;&nbsp;|
<input type="button" id="idtruyvan" name="nametruyvan"  value="Truy vấn" onclick="submittruyvan()"/>
