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
            <%=pagination.getPage_records() %> Rows dữ liệu
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
                   type="label"  onblur="fnPagination(7,<%=pagination.getTotal_pages()%>);hoanthanh()"/></td>

        <!--onclick="fnPagination(7,<%=pagination.getTotal_pages()%>);hoanthanh();"-->
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
<script type="text/javascript">
    (function () {
        function applyPageSizeIfRequested() {
            try {
                var requested = null;
                try {
                    requested = localStorage.getItem('set_page_size_after_reload');
                } catch (e) {
                    requested = null;
                }
                if (!requested)
                    return false;

                var select = document.getElementById('page_size');
                if (!select)
                    return false;

                // Set giá trị
                select.value = requested;

                // Gọi hàm phân trang / hàm hoàn thành nếu tồn tại
                if (typeof fnPagination === 'function') {
                    try {
                        fnPagination(5, 0);
                    } catch (e) {
                    }
                }
                if (typeof hoanthanh === 'function') {
                    try {
                        hoanthanh();
                    } catch (e) {
                    }
                }

                // Xoá flag để không chạy lại
                try {
                    localStorage.removeItem('set_page_size_after_reload');
                } catch (e) {
                }

                return true;
            } catch (e) {
                console && console.error && console.error(e);
                return false;
            }
        }

        function observeForPageSize() {
            // nếu phần tử #page_size chưa có, quan sát DOM để phát hiện khi nó xuất hiện
            var observer = new MutationObserver(function (mutations, obs) {
                if (applyPageSizeIfRequested()) {
                    obs.disconnect();
                }
            });
            // observe toàn bộ document để bắt bất kỳ thay đổi nào (append element từ AJAX)
            observer.observe(document.documentElement || document.body, {childList: true, subtree: true});

            // backup: polling nhỏ trong 3s để đảm bảo không bỏ sót
            var attempts = 0, maxAttempts = 10;
            var poll = setInterval(function () {
                attempts++;
                if (applyPageSizeIfRequested() || attempts >= maxAttempts) {
                    clearInterval(poll);
                    try {
                        observer.disconnect();
                    } catch (e) {
                    }
                }
            }, 300);
        }

        // Khi DOM sẵn sàng, thử apply ngay, nếu chưa thì quan sát/polling
        if (document.readyState === 'complete' || document.readyState === 'interactive') {
            if (!applyPageSizeIfRequested())
                observeForPageSize();
        } else {
            document.addEventListener('DOMContentLoaded', function () {
                if (!applyPageSizeIfRequested())
                    observeForPageSize();
            }, false);
        }

        // Ngoài ra expose một hàm để popup có thể gọi trực tiếp (AJAX case)
        window.afterReload = function () {
            applyPageSizeIfRequested();
        };
    })();
</script>
