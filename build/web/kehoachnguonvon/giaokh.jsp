<%@page import="vbsp.ims.log.CoreLogger"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Kế hoạch tín dụng</title>
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

        <SCRIPT>
            $(document).ready(function () {

                var namBc = $("#namBc").val();

                $('#namBc').change(function () {
                    //alert(<s:property value="reportGrade"/>);
//                   $("#idTitle").text("Giao kế hoạch tín dụng năm "+namBc);
                    if (<s:property value="reportGrade"/> == "3") {
                        $('#idTitle').removeAttr('readonly').val("Điều hành chỉ tiêu kế hoạch tín dụng " + $('#namBc').val());
                        $('#idTitle').attr('readonly', true);
                    }
                    if (<s:property value="reportGrade"/> == "2") {
                        $('#idTitle').removeAttr('readonly').val("Giao kế hoạch tín dụng năm " + $('#namBc').val());
                        $('#idTitle').attr('readonly', true);
                    }
                    if(<s:property value="reportGrade"/> == "1")
                    {
                        $('#idTitle').removeAttr('readonly').val("Giao kế hoạch tín dụng năm " + $('#namBc').val());
                        $('#idTitle').attr('readonly', true);
                    }
                });
                //An di cac cot chuc nang
                $('.hideColumn').hide();

                $(".KH_STT_HT").css({"width": "30px"});
                $(".KH_STT_HT").css({"text-align": "center"});
                $(".ZZZ").css({"text-align": "center"});
//                $(".KH_CHI_TIEU").css({"width" : "800px"});

                //CuongBM: 01Nov14
                // Boi dam mot so dong cho de nhin                
                // IN tieu de bao cao
                // Neu la cap tw in: ĐIỀU HÀNH CHỈ TIÊU KẾ HOẠCH TÍN DỤNG
                // Neu la cap cn in: GIAO KẾ HOẠCH TÍN DỤNG NĂM
                /*if (<s:property value="reportGrade"/> == "3") {
                    //Neu la cap trung uong boi dam 2 dong nay   
                    $("#tableKhnv tr").eq(2).css({"background-color": "#DCDCDC"});
                    $("#tableKhnv tr").eq(2).find("input").css({"background-color": "#DCDCDC"});

                    $("#tableKhnv tr").eq(29).css({"background-color": "#DCDCDC"});
                    $("#tableKhnv tr").eq(29).find("input").css({"background-color": "#DCDCDC"});

                    //Tieu de bao cao cap tw
                    $('#idTitle').removeAttr('readonly').val("Điều hành chỉ tiêu kế hoạch tín dụng " + $('#namBc').val());
                    $('#idTitle').attr('readonly', true);
//                    $("#idTitle").text("Điều hành chỉ tiêu kế hoạch tín dụng "+namBc);
                } else if (<s:property value="reportGrade"/> == "2") {
                    //Neu la cap CN boi dam 2 dong nay
                    $("#tableKhnv tr").eq(2).css({"background-color": "#DCDCDC"});
                    $("#tableKhnv tr").eq(2).find("input").css({"background-color": "#DCDCDC"});

                    $("#tableKhnv tr").eq(5).css({"background-color": "#DCDCDC"});
                    $("#tableKhnv tr").eq(5).find("input").css({"background-color": "#DCDCDC"});

                    $('#idTitle').removeAttr('readonly').val("Giao kế hoạch tín dụng năm " + $('#namBc').val());
                    $('#idTitle').attr('readonly', true);
                    //Tieu de bao cao
//                    $("#idTitle").text("Giao kế hoạch tín dụng năm "+namBc);
                }*/
            });

            function Callbaocao(fullname) {
                var ht1 = screen.availHeight - 100;
                var wt1 = 600;
                var left1 = (screen.width / 2) - (wt1 / 2);
                var top1 = 10;
                //tungnv sua phan nay de lau dong nam dua vao
                var namBc = $("#namBc").val();
//                window.open("fullname", "IMS_REPORTS",);
//                alert(namBc);
                //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
                var resize = window.open(fullname + "&namBc=" + namBc, "IMS_REPORTS", "height=" + ht1 + ",width=" + wt1 + ",left=" + left1 + ",top=" + top1 + ",directories=no,status=no,menubar=no,personalbar=no,resizable=no,location=no,scrollbars=yes,toolbar=no,border=no");

            }
        </SCRIPT>
    </head>
    <body>
        <div style="margin: 7px 7px 7px 7px;">            
            <table border="0" cellspacing="0" cellpading="0" height="100%" class="tblmain">
                <tr>
                    <td colspan="2" style="font-size: 14px;">
                        <!--<span id="idTitle" style="font-size: 14px; font-weight: bold"></span>-->
                        <%--<s:property value="namBc"/>--%>
                        <input type="text" name="idTitle" id="idTitle" style="font-size: 14px; color: #18ab29; font-weight: bold" value="Giao kế hoạch tín dụng năm <s:property value="namBc"/>" readonly="readonly"/>
                        <hr>
                    </td>                    
                </tr>
                <tr>
                    <td width="70%" >
                        
                        <b>Phòng giao dịch:</b><input type="text" name="pos_cd_username" id="pos_cd_username" value="<s:property value="pos_cd_username"/>" readonly="readonly" style="width: 50px"/>
                        <b>Chi nhánh: </b><input type="text" name="maCn" id="maCn" value="<s:property value="maCn"/>" readonly="readonly" style="width: 50px"/>
                        <!--tungnv sua de lay nam dong-->
                        <b>Năm báo cáo:</b>
                        <s:select list="lstYearReport" theme="simple"
                                  name="namBc" id="namBc"
                                  value="defaultYearReport" />                          
                        <b>Người dùng: </b><input type="text" name="userId" id="userId" value="<s:property value="userId"/>" readonly="readonly" style="width: 50px"/>
                        <b>Cấp báo cáo: </b><input type="text" name="userId" id="userId" value="<s:property value="reportGrade"/>" readonly="readonly" style="width: 50px"/>
                    </td>
                    <td align="right">                             
                    </td>
                </tr>
                <tr>
                    <td colspan="2">
                        <hr>
                        <table border="1px" id="tableKhnv" class="tableKhnv">
                            <tr class="tbhead">
                                <th class="KH_STT_HT">STT</th>
                                <th class="hideColumn">Mã Chỉ Tiêu</th>
                                <th class="KH_CHI_TIEU">Chỉ tiêu</th>

                                <th class="hideColumn">Được nhập</th>
                                <th class="hideColumn">Font</th>
                                <th class="hideColumn">Cấp hiển thị</th>
                                <th class="hideColumn">Số thứ tự</th>
                            </tr>
                            <tr class="tbhead">
                                <th class='locked_class_name'>(1)</th>
                                <th class="hideColumn">(2)</th>
                                <th class="KH_CHI_TIEU">(2)</th>

                                <th class="hideColumn">(6)</th>
                                <th class="hideColumn">(7)</th>
                                <th class="hideColumn">(8)</th>
                                <th class="hideColumn">(9)</th>                       
                            </tr>    

                            <s:iterator value="giaokhModelList">
                                <tr class="cscontent">
                                    <!-- CuongBM: Nếu KT_DN: Được nhập = N, người dùng không được phép nhập, thay đổi -->
                                    <s:if test="KH_DN.equalsIgnoreCase('N')">                                     
                                        <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_STT_HT'/>" name="KH_STT_HT" class="KH_STT_HT" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_MA_CT'/>" name="KH_MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/>"><input type="text" value="<s:property value='KH_CHI_TIEU'/>" name="KH_CHI_TIEU" onfocus="this.select()" readonly="readonly"/></td>

                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_DN'/>" name="KH_DN"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_FONTWEIGHT'/>" name="KH_FONTWEIGHT"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CAPHT'/>" name="KH_CAPHT"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_STT'/>" name="KH_STT"/></td>
                                        </s:if>

                                    <!-- CuongBM: Nếu KT_DN: Được nhập = Y -->
                                    <s:if test="KH_DN.equalsIgnoreCase('Y')">                                     
                                        <td class="<s:property value='KH_FONTWEIGHT'/> ZZZ"><a href="javascript:Callbaocao('<s:property value="KH_LINK"/>')" class="linkKh"><s:property value='KH_STT_HT'/></a></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_MA_CT'/>" name="KH_MA_CT" onfocus="this.select()" readonly="readonly"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/>"><a href="javascript:Callbaocao('<s:property value="KH_LINK"/>')" class="KH_CHI_TIEU linkKh"><s:property value='KH_CHI_TIEU'/></a></td>

                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_DN'/>" name="KH_DN"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_FONTWEIGHT'/>" name="KH_FONTWEIGHT"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_CAPHT'/>" name="KH_CAPHT"/></td>
                                        <td class="<s:property value='KH_FONTWEIGHT'/> hideColumn"><input type="text" value="<s:property value='KH_STT'/>" name="KH_STT"/></td>
                                        </s:if>
                                </tr>
                            </s:iterator>
                        </table>
                </tr>
            </table>
        </div>
    </body>
</html>
