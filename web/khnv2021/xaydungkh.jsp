<%-- 
    Document   : ViewAuthor
    Created on : Jun 17, 2021, 10:08:30 AM
    Author     : Nguyễn Phú Vinh
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

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

    </style>
    <SCRIPT language="javascript">
//            $(document).ready(function () {
//                
//                 $('#idExpEcelKhnv01').click(function () {
//                     alert(;)
//                     
//                     $('#divKhDetail').empty();
//                 };
////                //Khi thay doi
////                $('#namBc').change(function () {
////                    $("#loadKhDetail").trigger("click");
////                    //Set gia tri co input khi thay doi nam
////                    $('#label').removeAttr('readonly').val("Xây dựng kế hoạch tín dụng năm " + $('#namBc').val());
////                    $('#label').attr('readonly', true);
////                });
////
////                $('#pos_cd').change(function () {
////                    $("#loadKhDetail").trigger("click");
////                    //Set gia tri co input khi thay doi nam
//////                    $('#label').removeAttr('readonly').val("Xây dựng kế hoạch tín dụng năm " + $('#namBc').val());
//////                    $('#label').attr('readonly', true);
////                });
//            }
//                )
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
    </SCRIPT>
</head>
<body>
    <s:form id="id_khnv2021" name="id_khnv2021"  theme="simple">
        <div class="cls-fix">

            <div>
                <%--<s:url id="reloadDataSubCommune" action="reloadSubCommune" includeParams="post"></s:url>--%>

                <span class="clss-lable" id="cboDonvi" name="cboDonvi">Mẫu báo cáo:</span>
                <s:select list="lstMaBC" theme="simple"
                          name="maBc" id="namBc"
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
                <s:select id="commune_cd" name="commune_cd" list="posList" listKey="id" listValue="desc"/> </b> &nbsp;&nbsp;

                &nbsp;
                <span class="clss-lable">Mã thôn:</span>
                <s:select id="subcommune_cd" name="subcommune_cd" list="subCommuneList" listKey="id" listValue="desc"/> </b> &nbsp;&nbsp;
                &nbsp;

                <!--<input type="button" id="cmdTai" name="cmdTai" value="Tải dữ liệu" class="cmd">-->
                <s:url id="idLoadDataKhnv" action="loadDataKhnv.action"></s:url>                                      
                <sj:submit id="idloadDataKhnvtmp" name="nameSend" href="%{idLoadDataKhnv}" value="Tải dữ liệu" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>


                <input type="button" id="cmdTuChoi" name="cmdTuChoi" value="Gửi chi nhánh" class="cmd">
            </div>
            <hr/>
            <div>
                
                <s:url id="idExpEcelKhnv01a" action="khnv/dk/ExpExcelKhnv01a"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp01a" name="nameSend01a" href="%{idExpEcelKhnv01a}" value="Xuất xls mẫu 01a" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>
                
<!--                <input type="button" id="cmdGui" name="cmdGui" value="Xuất xls mẫu 01a" class="cmd">-->
                &nbsp;
                <input type="button" id="cmdTuChoi" name="cmdTuChoi" value="Upload xls mẫu 01a" class="cmd">
                &nbsp;&nbsp;|&nbsp;&nbsp

                <!--<input type="button" id="cmdGui" name="cmdGui" value="Xuất xls mẫu 01" class="cmd">&nbsp;-->
                <s:url id="idExpEcelKhnv01" action="ExpExcelKhnv01.action"></s:url>                                      
                <sj:submit id="idExpEcelKhnvtmp01" name="nameSend" href="%{idExpEcelKhnv01}" value="Xuất xls mẫu 01" targets="divKhDetail"
                           onBeforeTopics="beforediv_send"
                           onCompleteTopics="completediv_send" class="cmd"/>



                &nbsp;&nbsp;|&nbsp;&nbsp
                <input type="button" id="cmdGui" name="cmdGui" value="Xuất xls mẫu 02" class="cmd">&nbsp;
                <input type="button" id="cmdTuChoi" name="cmdTuChoi" value="Upload xls mẫu 02" class="cmd">
            </div>
            <hr/>
        </div>
        <div class="cls-over">
            <img id="loadingImage_next" src="img/loading.gif" style="display:none"/>
            <div id="divKhDetail"></div>
        </div>
    </s:form>
<!--    <script src="js/jquery-1.4.2.min.js" type="text/javascript"></script>-->
<!--    <script>
        $(document).ready(function () {
            $('#commune_cd').change(function () {
                var surl, sdata, idView, idMess, idForm, method;
                surl = "SendAction.action";
                idForm = "#id_khnv2021";
                method = "POST";
                sdata = jQuery(idForm).serialize();
                alert(sdata);
                $.ajax({
                    url: surl,
                    data: sdata,
                    type: method,
                    async: true,
                    beforeSend: function () {
                        $(idMess).html('<img src="imgs/newloading.gif" class="ViewMess"/>');
                    },
                    success: function (result) {
                       alert('Abc');
                    },
                    error: function (result) {
                        alert('Lỗi khi thực hiện.');
                    }
                });
            });
        });
    </script>-->
</body>
</html>
