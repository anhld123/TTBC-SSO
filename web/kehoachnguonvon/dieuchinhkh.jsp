<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Điều chỉnh kế hoạch</title>

        <s:head/>
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

            .BOLD a
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }

            .ITALIC a
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

            a.linkKh{
                color: #116600;
                text-decoration: none;            
            }
            a.linkKh:hover
            {
                color: #5494ea;
                text-decoration: underline;
            }
            a.linkKh:visited
            {
                color: #ab59a6;
            }
        </style>

        <script>
            $(document).ready(function () {
                //Khi thay doi
                $('#posCdLoadData').change(function () {
                    $("#loadKhDetail").trigger("click");
                });
                //Khi load xong
                $('#posCdLoadData').ready(function () {
                    $("#loadKhDetail").trigger("click");
                });

                $('#namBc').change(function () {
                    $("#loadKhDetail").trigger("click");
                    //Set gia tri co input khi thay doi nam
                    $('#label').removeAttr('readonly').val("Điều chỉnh kế hoạch năm " + $('#namBc').val());
                    $('#label').attr('readonly', true);
                });
                //Khi load xong
                $('#namBc').ready(function () {
                    $("#loadKhDetail").trigger("click");

                });

                if (<s:property value="reportGrade"/> == "3") {
                    //An di chi nhanh
                    $('#chiNhanh').hide();

                    //Tieu de bao cao cap tw
                    $("#idTitle").text("Điều hành chỉ tiêu kế hoạch tín dụng ");

                    //Tieu de phong giao dich
                    $("#phongGd").text("Mã CN: ");
                } else if (<s:property value="reportGrade"/> == "2") {
                    //Tieu de phong giao dich
                    $("#phongGd").text("Mã PGD: ");
                }
                else if (<s:property value="reportGrade"/> == "1") {
                    //Tieu de phong giao dich
                    $("#phongGd").text("Mã xã: ");
                }
            });

            $.subscribe('before-next', function (event, data) {
                $("#divKhDetail").empty();
                $("#divKhDetail").hide();
                $("#result").empty();
            });
            $.subscribe('before-save-khtd', function (event, data) {
                $("#result").empty();
            });
            $.subscribe('after-next', function (event, data) {
                // Effect cho the div
                $("#divKhDetail").slideDown("slow");
            });

        </script>
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">
            <s:form name="frmdata" id="frmdata" action="getDataDcKhDetail.action" theme="simple">
                <s:hidden name="vbsprandom" id="vbsprandom"/>
                <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                    <tr>
                        <td colspan="1" style="font-size: 14px;">
                            <input type="text" name="label" id="label" style="font-size: 14px; color: #18ab29; font-weight: bold"
                                   value="Điều chỉnh kế hoạch năm <s:property value="namBc"/>" readonly="readonly"/>
                            <hr>
                        </td>    
                    </tr>
                    <tr>
                        <td width="70%" >
                            <table border="0">
                                <tr>
                                    <td>
                                        <span id="phongGd"></span>
                                        <s:select id="posCdLoadData" name="pos_cd" list="posList" listKey="id" listValue="desc"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                    </td>
                                    <td>
                                        <span id="chiNhanh">                                           
                                            <s:if test="reportGrade.equalsIgnoreCase('1')">
                                                 Phòng giao dịch: <input type="text" name="pos_cd_username" id="pos_cd_username" value="<s:property value="pos_cd_username"/>" readonly="readonly" style="width: 50px"/>
                                            </s:if>
                                            <s:else>
                                                Chi nhánh: <input type="text" name="maCn" id="maCn" value="<s:property value="maCn"/>" readonly="readonly"/>
                                            </s:else>                                            
                                        </span>
                                    </td>
                                    <td>
                                        Năm báo cáo: 
                                        <s:select list="lstYearReport" theme="simple"
                                                  name="namBc" id="namBc"
                                                  value="defaultYearReport" /> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;

                                        Người dùng: <input type="text" name="userId" id="userId" value="<s:property value="userId"/>" readonly="readonly"/>
                                    </td>
                                </tr>
                            </table>
                        </td>
                        <td align="right">     
                            <span id="result" style="color: red">                            
                            </span>
                            <img id="loadingImage_Save_khtd" src="img/loading.gif" style="display:none"/>
                            <input type="button" id="checkThenSubmit" value="Cập nhật" onclick="fnCheckThenSubmit()" style="width:122px;height:25px;color: red;"/>
                        </td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <hr>    
                            <div id="divSave" style="color: red; font-weight: initial;text-align: right;" a >
                                Đơn vị tính: Triệu đồng
                            </div>
                            <img id="loadingImage" src="img/loading.gif" style="display:none"/>
                            <div id="divKhDetail">

                            </div>
                        <td/>
                    </tr>
                </table>
            </s:form>

            <sj:a id="loadKhDetail" formIds="frmdata" targets="divKhDetail" 
                  indicator="loadingImage" href="#" onBeforeTopics="before-next" 
                  onCompleteTopics="after-next"></sj:a>

            <s:url id="save" action="save_data_dieuchinhkh" />
            <sj:a name="update" id="update"  formIds="frmdata" 
                  href="%{save}" targets="result"  indicator="loadingImage_Save_khtd" onBeforeTopics="before-save-khtd"></sj:a>

        </div>
    </body>
</html>
