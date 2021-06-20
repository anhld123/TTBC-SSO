<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<sj:head/>
                <table border="1" class="editDelete">
                    <tr>
                        <th>Số TT</th>
                        <th>Mô Tả</th>
                        <th>Sửa</th>
                        <th>Xóa</th>
                    </tr>
                    <s:iterator value="lstObjQuery">                
                        <tr>
                            <td style="text-align: center;">
                                <s:property value="sStt"></s:property>
                                </td>
                                <td>
                                <s:property value="sDesc"></s:property>
                                </td>
                                <td style="text-align: center;">
                                <s:url id="Edit" value="editquery.action">
                                    <s:param name="save_id" value="sKey"/>
                                </s:url>
                                <sj:a targets="containBcttv"  href="%{Edit}">Sửa</sj:a>
                                </td>

                                <td style="text-align: center;">
                                <s:url id="Delete" value="DeleteQuery.action">
                                    <s:param name="save_id" value="sKey"/>
                                </s:url>
                                <sj:a targets="messageDiv"  href="%{Delete}" onClickTopics="onClickDel">Xóa</sj:a>
                                </td>
                            </tr>
                    </s:iterator>
                </table>
