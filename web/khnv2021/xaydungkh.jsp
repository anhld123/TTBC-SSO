<%-- 
    Document   : ViewAuthor
    Created on : Jun 17, 2021, 10:08:30 AM
    Author     : Nguyễn Phú Vinh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<%@ taglib prefix="sx" uri="/struts-dojo-tags" %> 
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <sj:head jqueryui="true" jquerytheme="smoothness"/> 
    <head></head>
    <title>Kiểm duyệt KHTD</title>
   
    <style>
        *{
            font-family: tahoma;
            font-size: 12px;
        }
        table {
            border-collapse: collapse;
            width: 100%;
            height: 1000px;
        }

        table thead { position: sticky; top: 0; z-index: 1; }

        th, td {
            text-align: left;
            padding: 8px;
            border: 1PX solid #f2f2f2;
            text-align: center;
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
            $.subscribe("beforediv_send", function(event, data) {
                $('#loadingImage_next').slideDown("slow");
                $('#loadingImage_next').empty();
                $('#divKhDetail').empty();
            });
            
            $.subscribe("completediv_send", function(event, data) {
            $("#loadingImage_next").hide();
            $('#loadingImage_next').empty();

            });
       </SCRIPT>
</head>
<body>
    <s:form id="id_khnv2021" name="id_khnv2021"  theme="simple">
        <div class="cls-fix">

            <div>
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
                <input type="button" id="cmdGui" name="cmdGui" value="Xuất xls mẫu 01a" class="cmd">&nbsp;
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
</body>
</html>
