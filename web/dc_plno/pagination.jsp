<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="vbsp.ims.model.Pagination"%>
<%
                                        Pagination pagination = (Pagination) request.getAttribute("pagination");
                                        boolean previous = false;
                                        if(pagination.getPage_number() > 1){
                                                previous = true;
                                        }
                                        boolean next = true;
                                        if(pagination.getTotal_pages() == pagination.getPage_number()){
                                                next = false;
                                        }                                        
%>
<input type="text" style="display: none;" name="pagination.sortColumn" id="sortColumn" value="<%=pagination.getSortColumn()%>"/>

<input type="text" style="display: none;" name="pagination.sortOrder" id="sortOrder" value="<%=pagination.getSortOrder()%>"/>

<table width="70%" align="center">
    <tr>
        <td width="10%">
            <div id="loadingImageData_Div" style="display: none" >
                <img id="loadingImageData" src='img/loading.gif' border='0' >
            </div>
        </td>
        <td>&nbsp</td>
        <td class="pagination-label" width="100%" nowrap="nowrap">
            Tổng số bản ghi: <%=pagination.getPage_records() %> 
        </td>
        <td>
            <%if(previous){%>
            <a href="#" onclick="fnPagination(4,<%=pagination.getTotal_pages()%>);hoanthanh();">
                <img src="imgs/left_end.gif" alt="Go to first page" width="15" height="19"/>
            </a>
            <%}else{ %>
            <img src="imgs/left_end_gray.gif" alt="Go to first page" width="15" height="19"/>
            <%} %>
        </td>
        <td>
            <%if(previous){%>
            <a href="#" onclick="fnPagination(3,<%=pagination.getTotal_pages()%>);hoanthanh();">
                <img src="imgs/left.gif" alt="Go to first page" width="15" height="19"/>
            </a>
            <%}else{ %>
            <img src="imgs/left_gray.gif" alt="Go to first page" width="15" height="19"/>
            <%} %>
        </td>
        <td class="pagination-label" nowrap="nowrap">Trang:</td>
        <td>
            <input name="pagination.page_number" id="page_number" class="pagination-textbox" 
                   <%if(!previous && !next) {%>readonly="readonly"<%}%>
                   style="width: 30px;" maxlen="4" value="<%=pagination.getPage_number()%>" 
                   type="text" onblur="fnPagination(7,<%=pagination.getTotal_pages()%>);hoanthanh()"/></td>
        
        <!--onclick="fnPagination(7,<%=pagination.getTotal_pages()%>);hoanthanh();" onblur=" //hoanthanh()"-->

<!--        targets="divExportReport"
                                   onBeforeTopics="beforediv1"
                                   onCompleteTopics="completediv1"-->
        <td class="pagination-label" nowrap="nowrap">Của <%=pagination.getTotal_pages()%> trang</td>

        <td>
            <% if(next){ %>
            <a href="#" onclick="fnPagination(1,<%=pagination.getTotal_pages()%>);hoanthanh();">
                <img src="imgs/right.gif" alt="Go to next page" border="0" width="15" height="19"/>
            </a>
            <%}else{ %>
            <img src="imgs/right_gray.gif" alt="Go to next page" border="0" width="15" height="19"/>
            <%} %>
        </td>
        <td><% if(next){ %>
            <a href="#" onclick="fnPagination(2,<%=pagination.getTotal_pages()%>);hoanthanh();">
                <img src="imgs/right_end.gif" alt="Go to next page" border="0" width="15" height="19"/>
            </a>
            <%}else{ %>
            <img src="imgs/right_end_gray.gif" alt="Go to next page" border="0" width="15" height="19"/>
            <%} %>
        </td>
        <td>&nbsp;</td>
        <td class="pagination-label" nowrap="nowrap">Hiển thị:</td>
        <td class="pagination-linkoff" style="" nowrap="nowrap">
    <s:select onchange="fnPagination(5, 0);hoanthanh();" list="#{'10':'10','20':'20','30':'30','40':'40','50':'50','60':'60','70':'70','80':'80','90':'90','100':'100'}" 
              theme="simple" name="pagination.page_size" 
              id="page_size" value="#request.pagination.page_size" /> Rows
</td>
<td class="pagination-label" width="100%" nowrap="nowrap">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
</tr>
</table>
