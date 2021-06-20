<%-- 
    Document   : edit_del_report_formula
    Created on : Jul 29, 2014, 3:47:14 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--<sj:head/>--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head/>

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
                width: 70%;
                border-color: #999;
            }
            table.editDelete tr:hover{
                background-color:#FFE47A;
                cursor: pointer;
            }
        </style>
        <script type="text/javascript">
//            $('#messageDiv').show();
            $.subscribe('onClickDel', function() {
                var r = confirm("Bạn có thật sự muốn xóa báo cáo này không ? OK : Đồng ý, Cancel : Hủy bỏ");
                if (r == true) {
                    return true;
                } 
                else return false;
            });
        </script>
    </head>
    <body>
        <div align="center">
            <h1 style="color: red">Sửa/Xóa báo cáo</h1>
            <s:form id="formeditdeleteformula" action="loadallformula">
                <table border="1" class="editDelete">
                    <tr>
                        <th>Số TT</th>
                        <th>Mô Tả</th>
                        <th>Sửa</th>
                        <th>Xóa</th>
                    </tr>
                    <s:iterator value="lstObjFormula">
                        <tr>
                            <td>
                                <s:property value="sStt"></s:property>
                                </td>
                                <td>
                                <s:property value="sDesc"></s:property>
                                </td>
                                <td>
                                <s:url id="Edit" value="editFormula.action">
                                    <s:param name="save_id" value="sKey"/>
                                </s:url>
                                <sj:a targets="containBcttv"  href="%{Edit}">Sửa</sj:a>
                                <%--<s:a href="%{Edit}">Sửa</s:a>--%>
                            </td>

                            <td>
                                <s:url id="Delete" value="Deleteformula.action">
                                    <s:param name="save_id" value="sKey"/>
                                </s:url>
                                <%--<s:a href="%{Delete}">Xóa</s:a>--%>
                                <sj:a targets="messageDiv"  href="%{Delete}" onClickTopics="onClickDel">Xóa</sj:a>
                                </td>

                            </tr>
                    </s:iterator>
                </table>
            </s:form>
        </div>
    </body>
</html>
