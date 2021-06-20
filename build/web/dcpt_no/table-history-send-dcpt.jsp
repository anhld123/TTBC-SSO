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
    function detailcnsend(macn,macn_dcpt,mapgd_dcpt,maxa_dcpt) {
//            var ht1 = screen.availHeight;
//            var wt1 = 1050;
//            var left1 = (screen.width / 2) - (wt1 / 2);
//            var top1 = 10;
//            var url = "ViewHistorySend.action?macn=" + macn + "&macn_dcpt=" + macn_dcpt + "&mapgd_dcpt=" + mapgd_dcpt
//                    +"&maxa_dcpt=" + maxa_dcpt;
//            //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
//            var resize = window.open(url, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

        }
        $(document).ready(function() {
            $("#allCheck").change(function() {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
        });
</script>
<body>

    <h2 style="color: red;">Danh sách chi tiết các đơn vị gửi/chưa gửi dữ liệu</h2> </br>


    <s:form id="formviewHistoryDcpt" name="formviewHistoryDcpt" action="BlockAllPosDcpt" theme="simple">
        <s:hidden name="macn_dcpt" id="macn_dcpt"/>
        <s:hidden name="mapgd_dcpt" id="mapgd_dcpt"/>
        <s:hidden name="maxa_dcpt" id="maxa_dcpt"/>

        <table border="1" class="editDelete">
            <tr>
                <th width="15" >
                        <s:checkbox id ="allCheck" name="allCheck"/></th>
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
                        
                        <td align = "center" > 
                            <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstModelHist[%{#rowstatus.index}].sMacn" fieldValue="%{sMacn}"/>
                        </td>
                        
                        <td style="text-align: center;"><s:property  value="nStt" /></td>
                        <td style="text-align: center;">
                            <a href="javascript:detailcnsend('<s:property value="sMacn"/>',
                               '<s:property value="macn_dcpt"/>',
                               '<s:property value="mapgd_dcpt"/>',
                               '<s:property value="maxa_dcpt"/>')" class="SOKU linkKh">
                                <s:property  value="sMacn"/>
                            </a> 
                        </td>
                        <td><s:property  value="sTencn" /></td>
                        <td style="text-align: left;"><s:property  value="sStatus" /></td>
                        <!--                         <td style="text-align: center;"> 
                        <s:url id="idOpenSendDcpt" value="setOpenSendDcpt.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="macn_dcpt" value="macn_dcpt"/>
                            <s:param name="mapgd_dcpt" value="mapgd_dcpt"/>
                            <s:param name="maxa_dcpt" value="maxa_dcpt"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idOpenSendDcpt}">Open</sj:a>
                        </td>
                    <td style="text-align: center;"> 
                        <s:url id="idBlockSendDcpt" value="setBlockSendDcpt.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="macn_dcpt" value="macn_dcpt"/>
                            <s:param name="mapgd_dcpt" value="mapgd_dcpt"/>
                            <s:param name="maxa_dcpt" value="maxa_dcpt"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idBlockSendDcpt}">Close</sj:a>
                        </td>                       -->

                    </s:if>
                        <s:elseif test="sSend.equalsIgnoreCase('send')">
                        
                        <td align = "center" > 
                            <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstModelHist[%{#rowstatus.index}].sMacn" fieldValue="%{sMacn}"/>
                        </td>
                        <td style="text-align: center; color: #00B83F"><s:property  value="nStt" /></td>
                        <td style="text-align: center; color: #00B83F"> 
                            <a href="javascript:detailcnsend('<s:property value="sMacn"/>',
                               '<s:property value="macn_dcpt"/>','<s:property value="mapgd_dcpt"/>',
                               '<s:property value="maxa_dcpt"/>')" class="SOKU linkKh">
                                <s:property  value="sMacn"/>
                            </a> 
                        </td>
                        <td style=" color: #00B83F"><s:property  value="sTencn" /></td>
                        <td style="text-align: left; color: #00B83F"><s:property  value="sStatus" /></td>
                        <!--                         <td style="text-align: center;"> 
                        <s:url id="idOpenSendDcpt" value="setOpenSendDcpt.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="macn_dcpt" value="macn_dcpt"/>
                            <s:param name="mapgd_dcpt" value="mapgd_dcpt"/>
                            <s:param name="maxa_dcpt" value="maxa_dcpt"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idOpenSendDcpt}">Open</sj:a>
                        </td>
                    <td style="text-align: center;"> 
                        <s:url id="idBlockSendDcpt" value="setBlockSendDcpt.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="macn_dcpt" value="macn_dcpt"/>
                            <s:param name="mapgd_dcpt" value="mapgd_dcpt"/>
                            <s:param name="maxa_dcpt" value="maxa_dcpt"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idBlockSendDcpt}">Close</sj:a>
                        </td>-->

                        </s:elseif>>    
                    <s:else>
                        <td align = "center" > 
                            <s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="lstModelHist[%{#rowstatus.index}].sMacn" fieldValue="%{sMacn}"/>
                        </td>
                        <td style="text-align: center; color: red"><s:property  value="nStt" /></td>
                        <td style="text-align: center; color: red"> 
                            <a href="javascript:detailcnsend('<s:property value="sMacn"/>',
                               '<s:property value="macn_dcpt"/>','<s:property value="mapgd_dcpt"/>',
                               '<s:property value="maxa_dcpt"/>')" class="SOKU linkKh">
                                <s:property  value="sMacn"/>
                            </a> 
                        </td>
                        <td style=" color: red"><s:property  value="sTencn" /></td>
                        <td style="text-align: left; color: red"><s:property  value="sStatus" /></td>
                        <!--                         <td style="text-align: center;"> 
                        <s:url id="idOpenSendDcpt" value="setOpenSendDcpt.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="macn_dcpt" value="macn_dcpt"/>
                            <s:param name="mapgd_dcpt" value="mapgd_dcpt"/>
                            <s:param name="maxa_dcpt" value="maxa_dcpt"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idOpenSendDcpt}">Open</sj:a>
                        </td>
                    <td style="text-align: center;"> 
                        <s:url id="idBlockSendDcpt" value="setBlockSendDcpt.action"  escapeAmp="false">
                            <s:param name="macn" value="sMacn"/>
                            <s:param name="macn_dcpt" value="macn_dcpt"/>
                            <s:param name="mapgd_dcpt" value="mapgd_dcpt"/>
                            <s:param name="maxa_dcpt" value="maxa_dcpt"/>
                        </s:url>
                        <sj:a targets="divMessage" href="%{idBlockSendDcpt}">Close</sj:a>
                        </td>-->

                    </s:else>
                </tr>
            </s:iterator>
        </table>
        <sj:submit id="idBockAllDcpt" name="nameBockAllDcpt" targets="divMessage" 
                   cssClass="metroButtonStyle" value="Thêm" cssStyle="display: none"></sj:submit>

        <s:url id="idurlOpenAllDcpt" action="setOpenAllDcpt.action"></s:url>
        <sj:submit id="idOpenAllDcpt" name="nameSearch" href="%{idurlOpenAllDcpt}" value="Mở tất cả" targets="divMessage"
                   onBeforeTopics="beforediv1"
                   onCompleteTopics="completediv1" cssStyle="display: none"/>

    </s:form>
</body>
