<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>


<input type="text" 
                                       placeholder="Chọn kiểm tra để tạo mã MN mới..." 
                                       size="60" 
                                       name="groupQuery.groupQueryPK.groupId"
                                       id="groupId"
                                       readonly="true"
                                       class="readonly"
                                       value="<s:property value='groupQuery.groupQueryPK.groupId'/>"/> 
