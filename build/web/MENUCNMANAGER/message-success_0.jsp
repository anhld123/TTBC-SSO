<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>


<input type="text" name="menu.menuId" 
                                       placeholder="Chọn kiểm tra để tạo mã MN mới..." 
                                       size="60" id="menuId"
                                       readonly="true"
                                       class="readonly"
                                       value="<s:property value='menu.menuId'/>"/> 
