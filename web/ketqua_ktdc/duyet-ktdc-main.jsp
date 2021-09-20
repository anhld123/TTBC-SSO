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
    <title>Phê duyệt kết quả kiểm tra đối chiếu</title>

    <style>
        *{
            font-family: tahoma;
            font-size: 12px;
        }
        table {
            border-collapse: collapse;
            /*width: 100%;*/
            /*height: 1000px;*/
            /*overflow:scroll;*/
        }

        table thead { position: sticky; top: 0; z-index: 1; }

        th, td {
            text-align: left;
            padding: 8px;
            border: 1PX solid #f2f2f2;
            /*text-align: center;*/
        }

        /*tr:nth-child(even){background-color: #f2f2f2}*/

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
/*        .cls-over{
            overflow-y: scroll;
            overflow-x: scroll;
            height: 76vh;            
        }*/
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
        .div-1{
            height: 25px;
            text-align: center;
            background: #f2f2f2;
            vertical-align: middle;
        }
        .div-2{

            background: #fbf9ee;

        }
        .div_h
        {
            height: 10px;
        }
        .div-scroll
        {
            /*overflow-x: scroll;*/
            /*overflow-y: scroll;*/
            width:  193vh;
            height: 68vh;
        }
        
        input[type="text"]
        {
            /*width: 100%;*/
            border: 0px;

            /*color: #000000*/
            border-color: #18ab29;
            /*background: #F9F9F9;*/
            color:#666666;
        }
        input[type=text]:focus, textarea:focus {
            box-shadow: 0 0 5px fuchsia;
            border: 1px solid fuchsia;
        }
       
       input:read-only {
            background-color: #E5E5E5;
        }
        
        .highlight_row {
            background-color: #FFB951; 
            color:#000;
        }
        
        #your_div_id {
            width: 255px;
            margin:0 auto;
            text-align: center;
          }

    </style>
    <SCRIPT language="javascript">
          var bsubmit = false;
          $.subscribe("myBeforeHandler", function(event, data) {
                $("#loadingImage_next").show();
            });
            $.subscribe("myCompleteTopics", function(event, data) {
                $("#loadingImage_next").hide();
            });
//        $.subscribe("beforediv_send", function (event, data) {
//            $('#loadingImage_next').slideDown("slow");
//            $('#loadingImage_next').empty();
//            $('#divKtdcDetail').empty();
//        });
//
//        $.subscribe("completediv_send", function (event, data) {
//            $("#loadingImage_next").hide();
//            $('#loadingImage_next').empty();
//
//        });

        function onSaveData()
            {
                $('#message_suc_err').empty();
//                var khoa = $("#khoa").val() + "_save";
//                if (!bsubmit)
//                {
////                    alert('Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !');
//                    $('#message_suc_err').html("<h2 style='color: red'>Bạn phải tải dữ liệu và sửa mới lưu được dữ liệu !</h2>");
//                    return;
//                }
//                var poscd = getposfromtreecheck();
//                if (poscd == null ||  poscd == "")
//                {
//                    
//                }
//                else
//                {
//                    $('#message_suc_err').html("<h2 style='color: red'>Bạn không được tích chọn PGD khi lưu dữ liệu cho chi nhánh !</h2>");
//                    bsubmit = false;
//                    return;
//                }
                
//                if (validateRequiredFields())
                    $("#ktdc_save")[0].click();
//                alert(khoa);
            }

    </SCRIPT>
</head>
<body>
    <s:form id="id_ketqua_ktdc_duyet" name="id_ketqua_ktdc_duyet"  theme="simple">
        <div class="cls-fix">
            <div class="div-1">
                <span class="clss-lable">Chọn đối tượng được kiểm tra, đối chiếu:</span>
                <s:select list="lstMaBaocao" theme="simple"
                          name="mabc" id="mabc"                          
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;                          
            </div> 
            <hr/>
            <div class="div-2">
                <div> 
<!--                         <span id="label_maxa" class="clss-lable">Phòng giao dịch:</span>
                        <s:select list="lstMapgd" theme="simple"
                                  name="mapgd" id="mapgd"
                                  listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                        &nbsp;&nbsp;-->
                        <span id="label_maxa" class="clss-lable">Xã: </span>
                        <s:select list="lstMaxa" theme="simple"
                                  name="maxa" id="maxa"
                                  listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                        &nbsp;

                        &nbsp;
                        <span class="clss-lable">Tháng kiểm tra:</span>  
                        <sj:datepicker name="ngay_kt" value="%{new java.util.Date()}" 
                                       placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="mm/yy" />   
                        &nbsp;&nbsp;
                        &nbsp;
                        <span id="label_maxa" class="clss-lable">Trạng thái KT:</span>
                        <s:select list="lstTrangthaiKT" theme="simple"
                                  name="trangthai" id="trangthai"
                                  listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                        &nbsp;&nbsp;
                        


                </div>



                    <div class="div_h"></div>
                    <div>
                        <s:url id="idLoadDataKtdcDuyet" action="loadDataKtdcDuyet.action"></s:url>                                      
                        <sj:submit id="idloadDataKtdctmp" name="nameSend" href="%{idLoadDataKtdcDuyet}" value="Xem dữ liệu" targets="divKtdcDetail"
                                   onBeforeTopics="myBeforeHandler"
                                   onCompleteTopics="myCompleteTopics" class="cmd"/>
                         &nbsp;
                            &nbsp;

                        <input type="button" id="idsaveDatatmp" name="namesaveDatatmp"  onclick="onSaveData()" value="Phê duyệt" class="cmd"/>
                        &nbsp;
                            &nbsp;
                         <div id="message_result" style="margin: 0 auto; width:355px;">
                    </div>    
                    <hr/>
            </div>            

            </div>
        </div>
        <div class="cls-over">
            <img id="loadingImage_next" src="img/loading.gif" style="display:none"/>
            <div class="div-scroll" id="divKtdcDetail">
            </div>
        </div>

    </s:form>
    
     <script>
                $(document).ready(function () {
//                    $("#mabc").change(function () {
//                        if ($("#mabc").val().trim() == "KTDC01" || $("#mabc").val().trim() == "KTDC02") {
//                            $('#form_ketqua_ktdc_dvut').show();
//                            $('#form_ketqua_ktdc_mato').show();
//                            $('#lable_cust_search').show();
//                            $('#cust_search').show();
//                            $('#label_hinhthuckt').show();
//                            $('#hinhthuckt').show();
//                            $('#label_dvut').show();
//                            $('#label_mato').show();
//                            $('#label_maxa').show();
//                            $('#maxa').show();
////                            $('#cboTonghop option')[0].selected = true;
////                            $('.cls-over').height("86vh")
//                        } 
//                        //Tổ
//                         else if ($("#mabc").val().trim() == "KTDC03" || $("#mabc").val().trim() == "KTDC04") 
//                         {
//                            $('#form_ketqua_ktdc_dvut').hide();
//                            $('#form_ketqua_ktdc_mato').hide();
//                            $('#lable_cust_search').hide();
//                            $('#cust_search').hide();
//                            $('#label_hinhthuckt').hide();
//                            $('#hinhthuckt').hide();
//                             $('#label_dvut').hide();
//                            $('#label_mato').hide();
//                            $('#label_maxa').show();
//                            $('#maxa').show();
//
//                            
//                            
//                        } 
//                        else {
//                            $('#form_ketqua_ktdc_dvut').hide();
//                            $('#form_ketqua_ktdc_mato').hide();
//                            $('#lable_cust_search').hide();
//                            $('#cust_search').hide();
//                            $('#label_hinhthuckt').hide();
//                            $('#hinhthuckt').hide();
//                             $('#label_dvut').hide();
//                            $('#label_mato').hide();
//                            $('#label_maxa').hide();
//                            $('#maxa').hide();
//                        }
//                });
//                
//                $("#maxa").change(function () {
//                });
                });
            </script>
</body>
</html>
