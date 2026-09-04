<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Xây dựng kế hoạch</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>

        <style type="text/css">
            *{
                font: 12px Arial, Helvetica, sans-serif;
            }
            table{
                border-style: solid;
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
            }
            .tbhead th{
                background-color: #5e5e55;
                font-weight: bold;
                color: #fff;
                text-align: center;
                padding: 5px;
            }
            .cscontent td{
                padding-left:5px;
                padding-top:5px;
                padding-bottom: 5px;
            }
            .cscontent:hover{
                background-color: #ffff99;
            }

            .cscontent:hover input[type="text"]{
                background-color: #ffff99;
            }
            .tblmain tr td{
                font-weight: bold;
                color: #018c3b;
            }

            input{
                border: 0px;
            }

            .BOLD input[type="text"]
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }

            .ITALIC input[type="text"]
            {
                font-style: italic;
                font-size: 12px;
                width: 95%;
            }

            input[type="text"]
            {
                width: 95%;
            }

            input[type="button"]
            {
                margin-left: 3px;
            }

            .parameter{
                border: 1px solid black;
                width: 50%;
            }

            #posCD, #namBc, #maCn, #userId{
                width: 70px;
            }
        </style>

        <SCRIPT language="javascript">
            $(document).ready(function () {
                //Khi thay doi
                $('#namBc').change(function () {
                    $("#loadKhDetail").trigger("click");
                    //Set gia tri co input khi thay doi nam
                    $('#label').removeAttr('readonly').val("Xây dựng kế hoạch tín dụng năm " + $('#namBc').val());
                    $('#label').attr('readonly', true);
                });

                $('#pos_cd').change(function () {
                    $("#loadKhDetail").trigger("click");
                    //Set gia tri co input khi thay doi nam
//                    $('#label').removeAttr('readonly').val("Xây dựng kế hoạch tín dụng năm " + $('#namBc').val());
//                    $('#label').attr('readonly', true);
                });

                //Khi load xong
                $('#namBc').ready(function () {
                    $("#loadKhDetail").trigger("click");
                });


            });
            $.subscribe('before-next', function (event, data) {
                $("#divKhDetail").empty();
                $("#divKhDetail").hide();
                $("#result").empty();
            });


            $.subscribe('before-save-xdkh', function (event, data) {
                $("#result").empty();
            });
            $.subscribe('after-next', function (event, data) {
                // Effect cho the div
                $("#divKhDetail").slideDown("slow");
            });
            function disable()
            {
                try {

                    var val = $('input[name=xa_pgd]:checked', '#frmdata').val();

                    if (val == '1')
                    {
                        $("#dmxa").show();
//                        $("#pos_cd").attr('disabled', true);
//                        alert('if '+val);
                    } else
                    {
                        //alert('else '+val);
                        $("#dmxa").hide();
//                        $("#pos_cd").attr('disabled', false);
//                        alert('else '+val);                            
                    }
                    $("#loadKhDetail").trigger("click");
                } catch (e)
                {
                    alert(e.toString());
                }
            }
        </SCRIPT>
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata" action="getDetailXayDungKh.action" theme="simple">
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                    <tr>
                        <td colspan="2" style="font-size: 14px;">
                            <input type="text" name="label" id="label" style="font-size: 14px; color: #18ab29; font-weight: bold" 
                                   value="Xây dựng kế hoạch tín dụng năm <s:property value="namBc"/>" readonly="readonly"/>
                            <hr></td>    
                    </tr>
                    <tr>
                        <td width="70%" >
                            <table border="0">
                                <tr>
                                    <td>
                                        <b>Phòng giao dịch:</b> <input type="text" name="pos_cd_username" id="pos_cd_username" 
                                                                       value="<s:property value="pos_cd_username"/>" readonly="readonly" style="width: 50px"/> &nbsp;&nbsp;
                                    </td>
                                    <td>
                                        <b>Năm báo cáo: </b> <s:select list="lstYearReport" theme="simple"
                                                  name="namBc" id="namBc"
                                                  value="defaultYearReport" /> </b>
                                    </td>
                                    <td>
                                        <b>Người dùng: </b><input type="text" name="userId" id="userId" value="<s:property value="userId"/>" readonly="readonly"/>
                                    </td>
                                </tr>
                                <tr>                                  
                                    <s:if test="reportGrade.equalsIgnoreCase('1')">
                                        <td id="dmxa">
                                            <b>Mã xã:</b> <s:select id="pos_cd" name="pos_cd" list="posList" listKey="id" listValue="desc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                        </td>
                                        <td>
                                            <b>Xây dựng kế hoạch theo:</b>
                                            <input type="radio" id="xa_pgd_1" name="xa_pgd" value="1" checked="checked" onclick="disable()" /> Xã
                                            <input type="radio" id="xa_pgd_2" name="xa_pgd" value="2" onclick="disable()" /> Phòng giao dịch
                                        </td>
                                    </s:if>
                                    <s:else>
                                        <td>
                                            <b>Chi nhánh: </b><input type="text" name="maCn" id="maCn" value="<s:property value="maCn"/>" readonly="readonly"/>
                                        </td>
                                    </s:else>
                                </tr>
                            </table>
                        </td>
                        <td align="right">     
                            <span id="result" style="color: red">                            
                            </span>
                            <img id="loadingImage_Save_Xdkh" src="img/loading.gif" style="display:none"/>
                            <input type="button" id="checkThenSubmit" value="Cập nhật" onclick="fnCheckThenSubmit()" style="width:122px;height:25px;color: red;"/>

                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr>       

                            <div id="divSave" style="color: red; font-weight: initial;text-align: right;" a >
                                Đơn vị tính: Triệu đồng
                            </div>
                            <img id="loadingImage_next" src="img/loading.gif" style="display:none"/>
                            <div id="divKhDetail">

                            </div>
                        </td>
                    </tr>
                </table>
            </s:form>

            <sj:a id="loadKhDetail" formIds="frmdata" targets="divKhDetail" 
                  indicator="loadingImage_next" href="#" onBeforeTopics="before-next" 
                  onCompleteTopics="after-next"></sj:a>

            <s:url id="save" action="save_data_xaydungkh" />
            <sj:a name="update" id="update"  formIds="frmdata" 
                  href="%{save}" targets="result"  indicator="loadingImage_Save_Xdkh" onBeforeTopics="before-save-xdkh" ></sj:a>
        </div>
    </body>
</html>
