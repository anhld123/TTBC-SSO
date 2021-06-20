<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông báo</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                //alert('Bat dau vao');

                $('#namBc').change(function () {
                    $("#loadThongBaoDetail").trigger("click");
                    //Set gia tri co input khi thay doi nam
                    $('#label').removeAttr('readonly').val("Thông báo năm " + $('#namBc').val());
                    $('#label').attr('readonly', true);
                });

                //Khi load xong
                $('#namBc').ready(function () {
                    $("#loadThongBaoDetail").trigger("click");
                });
            });
            $.subscribe('before-next', function (event, data) {
                $("#divKhDetail").empty();
                $("#divKhDetail").hide();
                $("#result").empty();
            });

            $.subscribe('after-next', function (event, data) {
                // Effect cho the div
                $("#divKhDetail").slideDown("slow");
            });

        </script>
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
                background-color: #DCDCDC;
                font-weight: normal;
                text-align: center;
                padding: 5px;
                font: 14px Arial, Helvetica, sans-serif;
            }
            .cscontent td{
                padding-left:5px;
                padding-top:5px;
                padding-bottom: 5px;     
                font: 14px Arial, Helvetica, sans-serif;
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

            #divChuaXD{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width:400px; 
                min-height: 550px; 
                padding: 10px;
                background-color: #E2E8C9;
                float: left;
                margin-bottom: 10px;
            }

            #divChiTieu{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width:848px; 
                min-height: 550px; 
                padding: 10px;
                background-color: #E2E8C9;
                float: left;
                margin-left: 10px;
                margin-bottom: 10px;
            }

            .cTitle{
                font-family: Verdana,Arial,Tahoma,Helvetica;
                font-size: 13pt;
                font-weight: bold;
                color: #116600;
            }
        </style>
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px; width: 1300px">
            <s:form name="frmdata_tb" id="frmdata_tb" action="getDataThongbaoDetail.action" theme="simple">
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                    <!--                    <tr>
                                            <td colspan="2" style="font-size: 14px;">Thông báo năm <s:property value="namBc"/><hr></td>                    
                                        </tr>-->
                    <tr>
                        <td colspan="1" style="font-size: 14px;">
                            <input type="text" name="label" id="label" style="font-size: 14px; color: #18ab29; font-weight: bold" value="Thông báo năm <s:property value="namBc"/>" readonly="readonly"/>
                            <hr></td>    
                    </tr>
                    <tr>
                        <td width="70%" >
                            <s:if test="reportGrade.equalsIgnoreCase('1')">
                                Phòng giao dịch: <input type="text" name="pos_cd_username" id="pos_cd_username" value="<s:property value="pos_cd_username"/>" readonly="readonly" style="width: 50px"/>
                            </s:if>
                            <s:else>
                                Chi nhánh: <input type="text" name="maCn" id="maCn" value="<s:property value="maCn"/>" readonly="readonly"/>
                            </s:else>   
                            <!--<b>Phòng giao dịch: </b><input type="text" name="posCD" id="posCD" value="<s:property value="posCD"/>" readonly="readonly"/>-->
                            <!--<b>Chi nhánh: </b><input type="text" name="maCn" id="maCn" value="<s:property value="maCn"/>" readonly="readonly"/>-->
                            <b>Năm báo cáo: </b>
                            <s:select list="lstYearReport" theme="simple"
                                      name="namBc" id="namBc"
                                      value="defaultYearReport" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</b>
                            <b>Người dùng: </b><input type="text" name="userId" id="userId" value="<s:property value="userId"/>" readonly="readonly"/>
                        </td>
                    </tr>
                </table>
                <hr/>
                <div id="divSave" style="color: red; font-weight: initial;text-align: right;" a >
                    Đơn vị tính: Triệu đồng
                </div>
                <img id="loadingImage_next" src="img/loading.gif" style="display:none"/>
                <div id="divKhDetail">

                </div>
            </s:form>
            <sj:a id="loadThongBaoDetail" formIds="frmdata_tb" targets="divKhDetail" 
                  indicator="loadingImage_next" href="#" onBeforeTopics="before-next" 
                  onCompleteTopics="after-next"></sj:a>
        </div>
    </body>
</html>
