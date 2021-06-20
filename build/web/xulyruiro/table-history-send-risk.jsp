<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<script type="text/javascript" src="js/pagination.js"></script>
<!--<hr/>-->
<style>
    th{
        background-color: #DCDCDC;
        border-color: #999;
        height: 26px;
    }
    /*    td{
            border-color: #999;
            height: 24px;
        }*/
    table.editDelete{
        border-collapse: collapse;
        width: 70%;
        border-color: #999;
    }
    /*    table.editDelete tr:hover{
            background-color:#FFE47A;
            cursor: pointer;
        }*/
</style>
<script>
    function detailcnsend(macn,nam_xlrr,dot_xlrr,nhom_xlrr,vb_xlrr) {
            var ht1 = screen.availHeight;
            var wt1 = 1050;
            var left1 = (screen.width / 2) - (wt1 / 2);
            var top1 = 10;
            var url = "ViewHistorySend.action?macn=" + macn + "&nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr
                    +"&nhom_xlrr=" + nhom_xlrr +"&vb_xlrr=" + vb_xlrr;
            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }
</script>
<body>

    <h2 style="color: red;">Danh sách chi tiết các chi nhánh gửi/chưa gửi dữ liệu</h2> </br>


    <s:form id="formviewHistory" name="formviewHistory" action="BlockAllPos" theme="simple">
        <s:hidden name="nam_xlrr" id="nam_xlrr"/>
        <s:hidden name="dot_xlrr" id="dot_xlrr"/>
        <s:hidden name="nhom_xlrr" id="nhom_xlrr"/>
        <s:hidden name="vb_xlrr" id="vb_xlrr"/>

        <table border="1" class="editDelete">
            <tr>
                <th width="15"><s:checkbox id ="allCheck" name="allCheck" onclick="selectallMeMacn()"/></th> 
                <th >STT</th>
                <th >Mã Đơn vị</th>
                <th >Tên Đơn vị</th>
                <th >Trạng thái</th>
                <!--                <th >Cho phép gửi</th>
                                <th>Khóa gửi</th> -->

            </tr>
            <s:iterator value="#attr.lstModelHist" var="ModelHist" status="rowstatus">
                <tr>
                    <!--<td>aaaaaaaaaaaaaaaaaaa</td>-->
                    <s:if test="sSend.equalsIgnoreCase('true')">
                        <td align = "center"> 
                            <s:checkbox id ="idMacn" name="macn" fieldValue="%{sMacn}" onclick="selectallMacn()"/>
                        </td>
                        <td style="text-align: center;"><s:property  value="nStt" /></td>
                        <td style="text-align: center;">
                            <a href="javascript:detailcnsend('<s:property value="sMacn"/>',
                               '<s:property value="nam_xlrr"/>','<s:property value="dot_xlrr"/>',
                               '<s:property value="nhom_xlrr"/>', '<s:property value="vb_xlrr"/>')" class="SOKU linkKh">
                                <s:property  value="sMacn"/>
                            </a> 
                        </td>
                        <td><s:property  value="sTencn" /></td>
                        <td style="text-align: left;"><s:property  value="sStatus" /></td>
                        <!--                         <td style="text-align: center;"> 
                        <s:url id="idOpenSend" value="setOpenSend.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="nam_xlrr" value="nam_xlrr"/>
                            <s:param name="dot_xlrr" value="dot_xlrr"/>
                            <s:param name="nhom_xlrr" value="nhom_xlrr"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idOpenSend}">Open</sj:a>
                        </td>
                    <td style="text-align: center;"> 
                        <s:url id="idBlockSend" value="setBlockSend.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="nam_xlrr" value="nam_xlrr"/>
                            <s:param name="dot_xlrr" value="dot_xlrr"/>
                            <s:param name="nhom_xlrr" value="nhom_xlrr"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idBlockSend}">Close</sj:a>
                        </td>                       -->

                    </s:if>
                    <s:else>
                        <td align = "center"> 
                            <s:checkbox id ="idmacn" name="macn" fieldValue="%{sMacn}" onclick="selectall()"/>
                        </td>
                        <td style="text-align: center; color: red"><s:property  value="nStt" /></td>
                        <td style="text-align: center; color: red"> 
                            <a href="javascript:detailcnsend('<s:property value="sMacn"/>',
                               '<s:property value="nam_xlrr"/>','<s:property value="dot_xlrr"/>',
                               '<s:property value="nhom_xlrr"/>','<s:property value="vb_xlrr"/>')" class="SOKU linkKh">
                                <s:property  value="sMacn"/>
                            </a> 
                        </td>
                        <td style=" color: red"><s:property  value="sTencn" /></td>
                        <td style="text-align: left; color: red"><s:property  value="sStatus" /></td>
                        <!--                         <td style="text-align: center;"> 
                        <s:url id="idOpenSend" value="setOpenSend.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="nam_xlrr" value="nam_xlrr"/>
                            <s:param name="dot_xlrr" value="dot_xlrr"/>
                            <s:param name="nhom_xlrr" value="nhom_xlrr"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idOpenSend}">Open</sj:a>
                        </td>
                    <td style="text-align: center;"> 
                        <s:url id="idBlockSend" value="setBlockSend.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="nam_xlrr" value="nam_xlrr"/>
                            <s:param name="dot_xlrr" value="dot_xlrr"/>
                            <s:param name="nhom_xlrr" value="nhom_xlrr"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idBlockSend}">Close</sj:a>
                        </td>-->

                    </s:else>
                </tr>
            </s:iterator>
        </table>
        <sj:submit id="idBockAll" name="nameBockAll" targets="divMessage" cssClass="metroButtonStyle" value="Thêm" cssStyle="display: none"></sj:submit>

        <s:url id="idurlOpenAll" action="setOpenAll.action"></s:url>
        <sj:submit id="idOpenAll" name="nameSearch" href="%{idurlOpenAll}" value="Mở tất cả" targets="divMessage"
                   onBeforeTopics="beforediv1"
                   onCompleteTopics="completediv1" cssStyle="display: none"/>

    </s:form>
</body>
