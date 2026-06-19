
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
    <script type="text/javascript" src="js/jquery-2.1.26.js"></script>
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

                <span class="clss-lable">Kế hoạch năm:</span>
                <s:select list="lstNamBC" theme="simple"
                          name="namBc" id="namBc"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;&nbsp;
                <span class="clss-lable">Đợt báo cáo:</span>
                <s:select list="lstDotBC" theme="simple"
                          name="dotBc" id="dotBc"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;&nbsp;
                <span class="clss-lable">Mã xã:</span>
                <s:select list="custCommuneList" theme="simple" name="commune_cd" id="commune_cd" listKey="id" listValue="desc" /> </b> 
                &nbsp;&nbsp;&nbsp;
                <span class="clss-lable">Mã thôn:</span>
                <s:select list="custSubCommuneList" theme="simple" name="subcommune_cd" id="subcommune_cd" listKey="id" listValue="desc" /> </b> 
                &nbsp;&nbsp;&nbsp;
                <span class="clss-lable" id="cboDonvi" name="cboDonvi">Mẫu báo cáo:</span>
                <s:select list="lstMaBC" theme="simple"
                          name="maBc" id="maBc"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                &nbsp;    
                <s:url id="idLoadDataKhnv" action="loadDataKhnv.action"></s:url>                                      
                <sj:submit id="idloadDataKhnvtmp" name="nameSend" href="%{idLoadDataKhnv}" value="Xem dữ liệu" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>

                <s:url id="idSendCNKhnv" action="sendCN.action"></s:url>                                      
                <sj:submit id="idloadDataKhnvtmp2" name="nameSend2" href="%{idSendCNKhnv}" value="Gửi chi nhánh" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>

                <%--<s:url id="cmdPrint" action="SendAction.action">
                    <s:param name="status">4</s:param>
                </s:url>                                      
                <sj:submit id="cmdPrinttmp" name="cmdPrint" href="%{cmdPrint}" value="In báo cáo" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>
                --%>
            </div>
            <hr/>
            <div>
                <s:url id="idExpEcelKhnv01_2026" action="khnv/dk/ExpExcelKhnv01_2026.action"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp01_2026" name="nameSend01_2026" href="%{idExpEcelKhnv01_2026}" value="Xuất xls mẫu 01 (Thôn)" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>

                &nbsp;&nbsp;|&nbsp;&nbsp;
                <s:url id="idExpEcelKhnv02_2026" action="khnv/dk/ExpExcelKhnv02_2026.action"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp02_2026" name="nameSend02_2026" href="%{idExpEcelKhnv02_2026}" value="Xuất xls mẫu 02 (Xã)" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>
                &nbsp;&nbsp;|&nbsp;&nbsp;
                <s:url id="idExpEcelKhnv03_2026" action="khnv/dk/ExpExcelKhnv03_2026.action"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp03_2026" name="nameSend03_2026" href="%{idExpEcelKhnv03_2026}" value="Xuất xls mẫu 03" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>
                &nbsp;&nbsp;|&nbsp;&nbsp;
                <!--<input type="button" class="cmd" onclick="callDirectLink('khvn_open_upload?');" value="Upload Excel">-->
                <input class="cmd" type="button" id="idUpload" value="Upload Excel" 
                       onclick="callDirectLink('uploadfile.action?type=1');">
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

        function callDirectLink(link) {
            const curentYear = new Date().getFullYear();
            PopupCenter(link, 'Upload excel', 800, 400);

        }
        function PopupCenter(pageURL, title, w, h) {
            var left = (screen.width / 2) - (w / 2);
            var top = (screen.height / 2) - (h / 2);
            var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
            return targetWin;
        }

        $(document).ready(function () {
            // 1. Lưu lại toàn bộ danh sách thôn ban đầu làm kho dữ liệu gốc
            var allSubCommunes = $("#subcommune_cd option").map(function () {
                return {value: $(this).val(), text: $(this).text()};
            }).get();

            function filterSubCommune() {
                var communeCd = $("#commune_cd").val();

                // Trường hợp 1: Xã chưa chọn hoặc chọn "Tất cả xã"
                if (!communeCd || communeCd === "000000" || communeCd === "") {
                    // Thiết lập thôn về "Tất cả thôn" và khóa lại
                    $("#subcommune_cd").html('<option value="000000">-- Tất cả --</option>');
                    $("#subcommune_cd").val("000000");
                    $("#subcommune_cd").prop("disabled", true);
                    return;
                }

                // Trường hợp 2: Chọn một xã cụ thể
                $("#subcommune_cd").prop("disabled", false);

                // Luôn luôn có option "Tất cả" ở đầu danh sách thôn của xã đó
                var html = '<option value="000000">-- Tất cả thôn --</option>';

                // Lọc các thôn có 6 ký tự đầu khớp với mã xã
                $.each(allSubCommunes, function (i, item) {
                    // Bỏ qua chính option "000000" gốc trong kho dữ liệu để tránh trùng lặp
                    if (item.value && item.value !== "000000" && item.value.substring(0, 6) === communeCd) {
                        html += '<option value="' + item.value + '">' + item.text + '</option>';
                    }
                });

                $("#subcommune_cd").html(html);
            }

            // Chạy khi load trang
            filterSubCommune();

            // Lắng nghe sự kiện thay đổi xã
            $("#commune_cd").change(function () {
                filterSubCommune();
            });
        });
    </script>
</body>
</html>
