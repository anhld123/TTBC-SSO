<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<sj:head/>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

        <style>
            th{
                background-color: #DCDCDC;
                border-color: #999;
            }
            td{
                border-color: #999;
            }
            table.editDelete{
                border-collapse: collapse;
                width: 60%;
                border-color: #999;
            }
            table.editDelete tr:hover{
                background-color:#FFE47A;
                cursor: pointer;
            }
        </style>
        <script>
            $(document).ready(function () {
                $('.ui-multiselect-checkboxes ui-helper-reset').remove();
//                alert('ui-multiselect-menu ui-widget ui-widget-content ui-corner-all');
            });
            $.subscribe('onClickDel', function () {
                var r = confirm("Bạn có thật sự muốn xóa báo cáo này không ? OK : Đồng ý, Cancel : Hủy bỏ");
                if (r == true) {
                    return true;
                } else
                    return false;
            });
            function onloadquery()
            {
                $('#divParams').text("");
//                window.location = "${pageContext.request.contextPath}/loadallquery.action";
                $("#idloadAllBC").trigger("click");
            }
//             $("#containBcttv").load('bctheotruyvan/exp_query.jsp');
            $.subscribe("myBeforeHandler", function (event, data) {
                $("#loadingImageDiv_rpt").show();
                $("#messageDiv").empty();
            });

            $.subscribe("myCompleteTopics1", function (event, data) {
                $("#loadingImageDiv_rpt").hide();
            });
        </script>
    </head>
    <body>
        <div align="center">
            <!--<h2 style="color: red">Sửa/xóa báo cáo</h2>-->
            <s:form id="formeditdelete" action="LoadAllBCEditDel" theme="simple">
                <table >
                    <tr>
                        <td style="font-weight: bold;vertical-align: middle;">
                            Nhóm báo cáo
                        </td>
                        <td>
                            <s:select  list="lstGroupRpt" name="grouprpt_id" 
                                       listKey="sKey" listValue="sDesc" 
                                       headerKey="-1" headerValue="------ Chon nhóm BC ------"
                                       id="grouprpt_id" value="grouprpt_id" 
                                       onBeforeTopics="myBeforeHandler" 
                                       onCompleteTopics="myCompleteTopics1"
                                       onchange="onloadquery()" 
                                       cssStyle="font-weight: bold;vertical-align: middle;"></s:select>

                            </td>
                            <td>
                                <img id="loadingImage-next" src="img/loaderB32.gif" style="display:none"/>
                                <div id="loadingImageDiv_rpt" style="display: none;">
                                    <img id="loadingImage_rpt" src='img/loading.gif' border='0'>
                                </div>
                            </td>
                        </tr>
                    </table>
                    <!--<br>-->
                    <hr>

                <%--<s:url id="editDeleteBc" action="LoadAllBCEditDel" />--%>
                <sj:submit id="idloadAllBC" value="Tiếp theo" href="%{editDeleteBc}" targets="divParams" indicator="loadingImage-next" onCompleteTopics="after-next" cssStyle="display: none;"/>            
                <!--                        </td>
                    </tr>
                </table>-->
            </s:form>
            <div id="divParams"></div>
        </div>
    </body>
</html>
