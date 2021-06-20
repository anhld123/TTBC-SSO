<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
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
                width: 60%;
                border-color: #999;
            }
            table.editDelete tr:hover{
                background-color:#FFE47A;
                /*cursor: pointer;*/
            }
        </style>
        <script>
            $("#messageDiv").empty();
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
            <h2 style="color: red">Sửa / Xóa báo cáo</h2>
            <s:form id="formeditdelete" action="loadallquery">
                <table border="1" class="editDelete">
                    <tr>
                        <th>Số TT</th>
                        <th>Mô Tả</th>
                        <th>Sửa</th>
                        <th>Xóa</th>
                    </tr>
                    <s:iterator value="lstEditDelFastRpt">                
                        <tr>
                            <td style="text-align: center;">
                                <s:property value="sStt"></s:property>
                                </td>
                                <td>
                                <s:property value="sDesc"></s:property>
                                </td>
                                <td style="text-align: center;">
                                <s:url id="Edit" value="editRptFast.action">
                                    <s:param name="save_id" value="sKey"/>
                                </s:url>
                                <sj:a targets="containBcttv"  href="%{Edit}">Sửa</sj:a>
                                </td>

                                <td style="text-align: center;">
                                <s:url id="Delete" value="DeleteRptFast.action">
                                    <s:param name="save_id" value="sKey"/>
                                </s:url>
                                <sj:a targets="messageDiv"  href="%{Delete}" onClickTopics="onClickDel" >Xóa</sj:a>
                                </td>
                            </tr>
                    </s:iterator>
                </table>
            </s:form>
        </div>
    </body>
</html>
