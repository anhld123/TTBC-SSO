<%-- 
    Document   : ViewAuthor
    Created on : Jun 17, 2021, 10:08:30 AM
    Author     : Nguyễn Phú Vinh
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<s:head/>
<sj:head/>

<!DOCTYPE html>
<html>
    <title>Xây dựng kế hoạch</title>

    <style>
        *{
            font-family: tahoma;
            font-size: 12px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            /*height: 1000px;*/
        }

        table thead { position: sticky; top: 0; z-index: 1; }

        th, td {
            text-align: left;
            padding: 8px;
            border: 1PX solid #f2f2f2;
            /*text-align: center;*/
        }

        tr:nth-child(even){background-color: #f2f2f2}

        th {
            background-color: #04AA6D;
            color: white;
        }
        .sttCol>td{
            font-style: italic;
        }
        .clss-body-ngnhan{
            box-sizing: content-box;
            padding: 5px;
        }
        textarea
        {
            border:1px solid #000;
            width:100%;
            height: 100px;
        }
        .clss-lable{
            font-weight: bold;
        }
        .cls-over{
            overflow-y: scroll;
            height: 76vh;
        }
        .cmd, input[type="submit"]{
            padding: 5px;
            background-image: linear-gradient(#f2f2f2,#c2c2c2);
            border: 1px solid #c2c2c2;
            border-radius: 2px;
        }

        .CLS-BOLD{
            font-weight: bold;
        }
        iframe:focus {
            outline: none;
        }
        iframe{
            border:none
        }
    </style>
    <SCRIPT language="javascript">
        $.subscribe("beforediv_send", function (event, data) {
            $('#loadingImage_next').slideDown("slow");
            $('#loadingImage_next').empty();
            $('#divKhDetail').empty();
        });

        $.subscribe("completediv_send", function (event, data) {
            $("#loadingImage_next").hide();
            $('#loadingImage_next').empty();

        });

        function onReloadSubCommune()
        {
            $('#divKhDetail').empty();
            var commune_cd = $("#commune_cd").val();

        }

        function callDirectLink(link) {
            var ht = screen.availHeight / 5 + 35;
            var wt = screen.availWidth / 5 + 20;

            var resize = window.open(link
                    + "random=" + Math.random(),
                    "IMS_REPORTS_FRM2", "height=" + ht + ",width=" + wt
                    + ",left=0,top=0,directories=no,status=no,menubar=no,\n\
        personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

            if (navigator.userAgent.indexOf('Chrome') !== -1
                    && parseFloat(
                            navigator.userAgent.substring(
                                    navigator.userAgent.indexOf('Chrome') + 7
                                    ).split(' ')[0]) >= 15) {
                resize.resizeBy(wt, ht);
            } else {
                resize.resizeTo(wt, ht);
            }
            resize.moveTo(wt, ht);
            resize.focus();
        }
    </SCRIPT>
</head>
<body>
    <s:form id="id_khnv2021" name="id_khnv2021"  theme="simple">
        <div class="cls-fix">

            <div>
                <%--<s:url id="reloadDataSubCommune" action="reloadSubCommune" includeParams="post"></s:url>--%>

                <span class="clss-lable" id="cboDonvi" name="cboDonvi">Mẫu báo cáo:</span>
                <s:select list="lstMaBC" theme="simple"
                          name="maBc" id="maBc"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                &nbsp;
                <span class="clss-lable">Kế hoạch năm:</span>
                <s:select list="lstNamBC" theme="simple"
                          name="namBc" id="namBc"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                &nbsp;
                <span class="clss-lable">Đợt báo cáo:</span>
                <s:select list="lstDotBC" theme="simple"
                          name="dotBc" id="dotBc"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                &nbsp;
                <!--                <span class="clss-lable">Tổng hợp</span>
                <s:select list="lstTongHop" theme="simple"
                          name="tonghopView" id="tonghopView"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                &nbsp;-->
                &nbsp;

                <span class="clss-lable">Mã xã:</span>
                <s:url var="buildCommuneComboUrl" action="communeBuildCombo"></s:url>
                <sj:select href="%{buildCommuneComboUrl}" 
                           name="commune_cd"
                           id="form_khnv2021_commune_cd"
                           list="custCommuneList"        
                           onChangeTopics="reloadState"
                           onCompleteTopics = "reloadState"                                   
                           listKey="id"
                           listValue="desc"                           
                           theme="simple"
                           ></sj:select> 
                    </b> &nbsp;&nbsp;           
                    &nbsp;
                    <span class="clss-lable">Mã thôn:</span>
                <sj:select href="%{buildCommuneComboUrl}" 
                           name="subcommune_cd"
                           id="form_khnv2021_subcommune_cd"
                           list="custSubCommuneList"        
                           reloadTopics = "reloadState" 
                           listKey="id"
                           listValue="desc"                           
                           theme="simple"
                           ></sj:select>                 
                    &nbsp;
                    &nbsp;
                    <!--<input type="button" id="cmdTai" name="cmdTai" value="Tải dữ liệu" class="cmd">-->
                <s:url id="idLoadDataKhnv" action="loadDataKhnv.action"></s:url>                                      
                <sj:submit id="idloadDataKhnvtmp" name="nameSend" href="%{idLoadDataKhnv}" value="Xem dữ liệu" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>


                <s:url id="idSendCNKhnv" action="sendCN.action"></s:url>                                      
                <sj:submit id="idloadDataKhnvtmp2" name="nameSend2" href="%{idSendCNKhnv}" value="Gửi chi nhánh" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>
                
                <s:url id="cmdPrint" action="SendAction.action">
                    <s:param name="status">4</s:param>
                    <s:param name="showprint">4</s:param>
                </s:url>                                      
                <sj:submit id="cmdPrinttmp" name="cmdPrint" href="%{cmdPrint}" value="In báo cáo" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>
      
            </div>
            <hr/>
            <div>

                <s:url id="idExpEcelKhnv01a" action="khnv/dk/ExpExcelKhnv01a"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp01a" name="nameSend01a" href="%{idExpEcelKhnv01a}" value="Xuất xls mẫu 01a" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>

                &nbsp;&nbsp;|&nbsp;&nbsp;      
                <s:url id="idExpEcelKhnv01New" action="khnv/dk/ExpExcelKhnv01New.action"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp01new" name="nameSendnew" href="%{idExpEcelKhnv01New}" value="Xuất xls mẫu 01" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>

                <s:url id="idExpEcelKhnv01" action="ExpExcelKhnv01.action"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp01" name="nameSend" href="%{idExpEcelKhnv01}" value="   In mẫu 01   " targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>

                &nbsp;&nbsp;|&nbsp;&nbsp;
                <s:url id="idExpEcelKhnv02" action="khnv/dk/ExpExcelKhnv02"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp02" name="nameSend02" href="%{idExpEcelKhnv02}" value="Xuất xls mẫu 02" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>&nbsp;

                &nbsp;&nbsp;|&nbsp;&nbsp;
                <sj:a class="cmd" href="#" onclick="callDirectLink('khvn_open_upload?');" cssClass="metroButtonStyle">
                    <b> <u>Upload Excel</u>  </b> </sj:a>                        
                </div>
                <hr/>
            </div>
            <div class="cls-over">
                <img id="loadingImage_next" src="img/loading.gif" style="display:none"/>
                <div id="divKhDetail">
                </div>
            </div>
    </s:form>
    <script>
        $(document).ready(function () {
            $("#ifPrint").hide();
        });
    </script>
</body>
</html>
