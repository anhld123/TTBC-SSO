<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<input type="text" name="tm.code" 
       placeholder="Chọn kiểm tra để tạo mã TM mới..." 
       style="width: 320px;" id="code_Txt"
       readonly="true"
       value="<s:property value='tm.code'/>"/> 
