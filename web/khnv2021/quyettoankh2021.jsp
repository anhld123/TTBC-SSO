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

        table thead {
            position: sticky;
            top: 0;
            z-index: 1;
        }

        th, td {
            text-align: left;
            padding: 8px;
            border: 1PX solid #f2f2f2;
            /*text-align: center;*/
        }

        tr:nth-child(even){
            background-color: #f2f2f2
        }

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

        .cmd{
            padding: 5px;
            background-image: linear-gradient(#f2f2f2,#c2c2c2);
            border: 1px solid #c2c2c2;
            border-radius: 2px;
            color: blue;
        }

        /*        .cmd {
                    padding: 5px;
                    background-image: linear-gradient(#f2f2f2,#c2c2c2);
                    border: 1px solid #c2c2c2;
                    border-radius: 2px;
                    color: blue;
                }*/

        .cmd_red{
            padding: 5px;
            background-image: linear-gradient(#f2f2f2,#c2c2c2);
            border: 1px solid #c2c2c2;
            border-radius: 2px;
            color: red;
        }

        .CLS-BOLD{
            font-weight: bold;
        }

        .CLS-ITALIC{
            font-style: italic;
        }

        iframe:focus {
            outline: none;
        }
        iframe{
            border:none
        }
    </style>
    <SCRIPT language="javascript">

        $.subscribe("before_thkh", function (event, data) {
            if (!confirm("Khi tổng hợp từ PGD dữ liệu đã nhập sẽ bị mất. Bạn có chắc chắn muốn tổng hợp?")) {
                event.originalEvent.options.submit = false;
            } else {
                $('#loadingImage_next').slideDown("slow");
                $('#loadingImage_next').empty();
                $('#divKhDetail').empty();
            }
        });

        $.subscribe("before_chotsl", function (event, data) {
            if (!confirm("Sau khi chốt số liệu, bạn sẽ không thể chỉnh sửa. Bạn có chắc chắn muốn chốt số liệu?")) {
                event.originalEvent.options.submit = false;
            } else {
                $('#loadingImage_next').slideDown("slow");
                $('#loadingImage_next').empty();
                $('#divKhDetail').empty();
            }
        });

        $.subscribe("beforediv_send", function (event, data) {
            $('#loadingImage_next').slideDown("slow");
            $('#loadingImage_next').empty();
            $('#divKhDetail').empty();
        });

        $.subscribe("completediv_send", function (event, data) {
            $("#loadingImage_next").hide();
            $('#loadingImage_next').empty();

        });

//        function onReloadSubCommune()
//        {
//            $('#divKhDetail').empty();
//            var commune_cd = $("#commune_cd").val();
//
//        }

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
                <span class="clss-lable">Quyết toán năm:</span>
                <s:select list="lstNamBC" theme="simple"
                          name="namBc" id="namBc" onchange="updatenamBc()"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                &nbsp;
                <s:if test="reportGrade.equalsIgnoreCase('2') || reportGrade.equalsIgnoreCase('3')">
                    <span class="clss-lable">Đơn vị:</span>
                    <s:select list="lstDonvi" theme="simple"
                              name="donvi" id="donvi"
                              listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                    &nbsp;
                </s:if>                

                <!--<input type="button" id="cmdTai" name="cmdTai" value="Tải dữ liệu" class="cmd">-->
                <s:url id="idLoadDataQtKhnv" action="loadDataQuyettoan2021.action"></s:url>                                      
                <sj:submit id="idloadDataQtKhnvtmp" name="nameSend" href="%{idLoadDataQtKhnv}" value="Xem dữ liệu" targets="divKhDetail"
                           onBeforeTopics="beforediv_send" 
                           onCompleteTopics="completediv_send" cssClass="cmd"/>

                <s:if test="!reportGrade.equalsIgnoreCase('3')">
                    <s:url id="idChotQtKehoach" action="ChotQtKehoach.action"></s:url>                                      
                    <sj:submit id="idloadDataQtKhnvtmp2" 
                               name="nameSend2" 
                               href="%{idChotQtKehoach}" 
                               value="Chốt số liệu" 
                               targets="divKhDetail"
                               onBeforeTopics="before_chotsl"
                               onCompleteTopics="completediv_send" cssClass="cmd"/>


                </s:if>


                <s:if test="reportGrade.equalsIgnoreCase('2')">
                    <s:url id="idMoChotQtKehoach" action="MoChotQtKehoach.action"></s:url>                                      
                    <sj:submit id="idloadDataMoChotQtKhnvtmp2" 
                               name="nameChot2" 
                               href="%{idMoChotQtKehoach}" 
                               value="Mở chốt số liệu" 
                               targets="divKhDetail"
                               onBeforeTopics="beforediv_send"
                               onCompleteTopics="completediv_send" cssClass="cmd"/>

                    <s:if test="reportGrade.equalsIgnoreCase('2')">
                        <!--                             &nbsp;&nbsp;|&nbsp;&nbsp;-->
                        <s:url id="idTongHopKehoach" action="TongHopQtKehoach.action"></s:url>                                      
                        <sj:submit id="idloadTongHopQtKhnvtmp2"                                    
                                   name="nameTongHop" 
                                   href="%{idTongHopKehoach}" 
                                   value="Tổng hợp từ PGD" 
                                   targets="divKhDetail"
                                   onBeforeTopics="before_thkh"
                                   onCompleteTopics="completediv_send" cssClass="cmd_red"
                                   />   
                    </s:if>

                </s:if>
                <s:if test="reportGrade.equalsIgnoreCase('3')"> 
                    &nbsp;|&nbsp;<input type="button" id="idSearch" value="Danh sách gửi dữ liệu" style="color: red" targets="divKhDetail" class="cmd" >
                </s:if>
            </div>
            <hr/>
            <div>
                <s:url id="idExpEcelQtKhnv11" action="khnv/dk/ExpExcelEcelQtKhnv11"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp01a" name="nameSend01a" href="%{idExpEcelQtKhnv11}" value="Tải file Excel" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" cssClass="cmd"/>

                <!--                &nbsp;&nbsp;|&nbsp;&nbsp;-->
                <!--                <sj:submit class="cmd" href="#" onclick="callDirectLink('khvn_open_upload_qt_kh?');" 
                           id="upload_tmp" cssClass="cmd" value="Upload Excel & Gửi số liệu">
                </sj:submit>       -->
                <a href="#" id="upload_tmp" class="cmd" onclick="callDirectLink('khvn_open_upload_qt_kh?');" style="text-decoration: none;">
                    Upload Excel & Gửi số liệu
                </a>

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
        $("#idSearch").click(function () {
            var url, sdata;
            url = "seach_QTNV_2024.action";
            sdata = jQuery("#id_khnv2021").serialize();
            $("#divKhDetail").html('<img src="img/loading.gif"/>');
//                btnDisabled(1);
            $.ajax({
                type: "POST",
                url: url,
                data: sdata,
                success: function (data) {
                    $("#divKhDetail").html(data);
                    $("#idSend").prop('disabled', true);
                    $("#idSave").prop('disabled', false);
                    $("#idDelete").prop('disabled', false);
                },
                complete: function () {
//                        btnDisabled(0);
                },
                error: function (request) {
                    console.log(request);
                    alert("Lỗi: Vui lòng liên hệ với quản trị viên.");
                }
            });
        });

        function updatenamBc() {
            var namBc = document.getElementById("namBc");
            var selectedValue = namBc.value;
            var selectLink = document.getElementById("upload_tmp");

            // Kiểm tra giá trị chọn và cập nhật nội dung link
            if (selectedValue !== "000000") {
                selectLink.innerText = "Upload Excel & Gửi số liệu năm " + selectedValue;
            } else {
                selectLink.innerText = "Upload Excel & Gửi số liệu";
            }
        }

    </script>
</body>
</html>
