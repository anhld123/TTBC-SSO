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
    <title>Nhập kết quả kiểm tra đối chiếu</title>

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

    </style>
    <SCRIPT language="javascript">
        $.subscribe("beforediv_send", function (event, data) {
            $('#loadingImage_next').slideDown("slow");
            $('#loadingImage_next').empty();
            $('#divKtdcDetail').empty();
        });

        $.subscribe("completediv_send", function (event, data) {
            $("#loadingImage_next").hide();
            $('#loadingImage_next').empty();

        });

//        function onchangeHinhthuc()
//        {
//            $('#doituongkt').hide();
//
//        }

    </SCRIPT>
</head>
<body>
    <s:form id="id_ketqua_ktdc" name="id_ketqua_ktdc"  theme="simple">
        <div class="cls-fix">
            <div class="div-1">
                <span class="clss-lable">Chọn đối tượng được kiểm tra, đối chiếu:</span>
                <s:select list="lstMaBaocao" theme="simple"
                          name="mabc" id="mabc"
                          onchange="onchangeHinhthuc"
                          listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;                          
            </div> 
            <hr/>
            <div class="div-2">
                <div>    
                            <span class="clss-lable">Ngày KT:</span>  
                        <sj:datepicker name="ngay_kt" value="%{new java.util.Date()}" 
                                       placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/>   
                        &nbsp;&nbsp;
                        &nbsp;
                        <span class="clss-lable">Ngày BC:</span>  
                        <sj:datepicker name="ngay_bc" value="%{new java.util.Date()}" 
                                       placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy" cssClass="NGAY_SL"/> 
                        &nbsp;&nbsp;
                        &nbsp;
                        <span class="clss-lable" id="cboDonvi" name="cboDonvi">Đối tượng KT:</span>
                        <s:select list="lstDoituongKT" theme="simple"
                                  name="doituongkt" id="doituongkt"
                                  listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                        &nbsp;

                </div>
                <div class="div_h"></div>        
                <div >   
                    <div>                        
                        
                        <span class="clss-lable" id="label_hinhthuckt">Hình thức KT:</span>
                        <s:select list="lstHinhthucKT" theme="simple"
                                  name="hinhthuckt" id="hinhthuckt"
                                  listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                        &nbsp;
                        <span class="clss-lable">Cán bộ kiểm tra:</span>
                        <s:select list="lstMaCanbo" theme="simple"
                                  name="canbokt" id="canbokt"
                                  listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                        &nbsp;
                        <s:label value="Thông tin cán bộ:" id="lable_canboinfo" cssStyle="color: #029c44;"> </s:label>                                
                            <s:textfield id="canboinfo" name="canboinfo"  onkeypress="javascript:keyPressEvent();" 
                                         style="border: 1px solid rgba(81, 203, 238, 1);margin:0 auto;width: 250px; background: white;"></s:textfield>
                </div>   
                        <div class="div_h"></div>
                <div>        
                        <span class="clss-lable">Xã:</span>
                        <s:select list="lstMaxa" theme="simple"
                                  name="maxa" id="maxa"
                                  listKey="sKey" listValue="sDesc" /> </b> &nbsp;&nbsp;
                        &nbsp;

                        &nbsp;

                        <span id="label_dvut" class="clss-lable">ĐVUT:</span>
                <s:url var="buildCommuneComboUrl" action="communeBuildComboKTDC"></s:url>
                <sj:select href="%{buildCommuneComboUrl}" 
                           name="commune_cd"
                           id="form_ketqua_ktdc_dvut"
                           list="lstDvut"        
                           onChangeTopics="reloadState"
                           onCompleteTopics = "reloadState"                                   
                           listKey="id"
                           listValue="desc"                           
                           theme="simple"
                           ></sj:select> 
                    </b> &nbsp;&nbsp;           
                    &nbsp;
                    <span id="label_mato" class="clss-lable">Mã tổ:</span>
                <sj:select href="%{buildCommuneComboUrl}" 
                           name="subcommune_cd"
                           id="form_ketqua_ktdc_mato"
                           list="lstMato"        
                           reloadTopics = "reloadState" 
                           listKey="id"
                           listValue="desc"                           
                           theme="simple"
                           ></sj:select>                 
                    &nbsp;
                    &nbsp;
                        <s:label value="Mã KH/ món vay" id="lable_cust_search" cssStyle="color: #029c44;"> </s:label>                                
                            <s:textfield id="cust_search" name="cust_search"  onkeypress="javascript:keyPressEvent();" 
                                         style="border: 1px solid rgba(81, 203, 238, 1);margin:0 auto;width: 150px; background: white;"></s:textfield>
                    </div>


                    <div class="div_h"></div>
                    <div>
                        <s:url id="idLoadDataKtdc" action="loadDataKtdc.action"></s:url>                                      
                        <sj:submit id="idloadDataKtdctmp" name="nameSend" href="%{idLoadDataKtdc}" value="Xem dữ liệu" targets="divKtdcDetail"
                                   onBeforeTopics="beforediv_send"
                                   onCompleteTopics="completediv_send" class="cmd"/>
                         &nbsp;
                            &nbsp;
                        <s:url id="idSaveKtdc" action="saveDataKtdc.action"></s:url>                                      
                        <sj:submit id="idSaveKtdctmp" name="nameSave" href="%{idSaveKtdc}" value="Lưu dữ liệu" targets="divKtdcDetail"
                                   onBeforeTopics="beforediv_send"
                                   onCompleteTopics="completediv_send" class="cmd"/>
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
                    $("#mabc").change(function () {
//                        alert($("#mabc").val().trim());
                        //Hộ vay
                        if ($("#mabc").val().trim() == "KTDC01") {
                            $('#form_ketqua_ktdc_dvut').show();
                            $('#form_ketqua_ktdc_mato').show();
                            $('#lable_cust_search').show();
                            $('#cust_search').show();
                            $('#label_hinhthuckt').show();
                            $('#hinhthuckt').show();
                            $('#label_dvut').show();
                            $('#label_mato').show();
//                            $('#cboTonghop option')[0].selected = true;
//                            $('.cls-over').height("86vh")
                        } 
                        //Tổ
                         else //($("#mabc").val().trim() == "KTDC02") 
                         {
                            $('#form_ketqua_ktdc_dvut').hide();
                            $('#form_ketqua_ktdc_mato').hide();
                            $('#lable_cust_search').hide();
                            $('#cust_search').hide();
                            $('#label_hinhthuckt').hide();
                            $('#hinhthuckt').hide();
                             $('#label_dvut').hide();
                            $('#label_mato').hide();
                        } 
//                        else {
//                            $('#idLuuDL').hide();
//                            $('#cmdGuiDL').hide();
//                            $('#cmdAuthor').show();
//                            $('#cmdTraLaiDL').show();
//                            $('#idNguyenNhan').show();
//                            $('#idViewData').hide();
//                            $('#cboTonghop option')[1].selected = true;
//                        }
                });
                });
            </script>
</body>
</html>
