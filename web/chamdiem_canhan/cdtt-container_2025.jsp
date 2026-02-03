<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<style>
.tab-header {
    margin-bottom: 10px;
}
.tab-header button {
    padding: 6px 12px;
    margin-right: 5px;
    cursor: pointer;
}
.tab-content {
    width: 100%;
}
</style>

<div class="tab-header">
    <button type="button" onclick="showTab('cdtt')">Chấm điểm cá nhân</button>
    <button type="button" onclick="showTab('excel')">Upload Excel</button>
</div>

<div id="cdtt" class="tab-content">
    <jsp:include page="/chamdiem_canhan/cdtt-file-upload-main.jsp"/>
</div>

<div id="excel" class="tab-content" style="display:none">
    <jsp:include page="/EXCEL_UPLOAD/excel_upload_2025.jsp"/>
</div>

<script>
function showTab(tab) {
    document.getElementById('cdtt').style.display = (tab === 'cdtt') ? 'block' : 'none';
    document.getElementById('excel').style.display = (tab === 'excel') ? 'block' : 'none';
}
</script>
