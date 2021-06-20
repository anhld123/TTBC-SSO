<%-- 
    Document   : risk_detail_customer
    Created on : May 27, 2015, 8:50:46 AM
    Author     : BAOANH
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Thông tin chi tiết khách hàng xử lý rủi ro từ chối của chi nhánh</title>
        <sx:head/>
        <sj:head/>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script type="text/javascript" src="js/pagination.js"></script>
        <style>      
            *{
                font: 14px Arial, Helvetica, sans-serif;
            }

            #divChiTieu{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width:99%; 
                height: 99%; 
                margin: 0px auto 10px auto; 
                /*padding: 10px;*/
                background-color: #E2E8C9;
                /*overflow: scroll;*/
                /*border: 1px solid;*/
            }
            #container_popup{
                -webkit-border-radius: 10px;
                -moz-border-radius: 10px;
                border-radius: 10px;
                width: 100%;
                 height:96vh; 
                /*border: 1px solid;*/
                padding-left: 0px;     
                background-color: #E2E8C9;
            }
            #idTitle{
                font-family: Verdana,Arial,Tahoma,Helvetica;
                font-size: 13pt;
                font-weight: bold;
                color: #116600;
                /*border: 1px solid;*/
            }

            #divSave{
                text-align: right;
            }

            table{
                border-collapse: collapse;
                width: 100%;
                line-height: 19px;
                /*border: 1px solid;*/
            }
            .tbhead th{
                background-color: #DCDCDC;
                text-align: center;
                font-weight: normal;
                padding: 2px;
            }
            .cscontent td{
                background-color: white;
                text-align: center;
                padding-left:5px;
                padding-top:5px;
                padding-bottom: 5px;
            }

            input{
                border: 0px;
            }

            input[type="text"]
            {
                width: 98%;
            }

            .maPGD{
                width: 150px;
            }

            .tenPGD{
                width: 200px;
            }

            textarea:focus {
                box-shadow: 0 0 5px rgba(81, 203, 238, 1);
                padding: 3px 0px 3px 3px;
                margin: 5px 1px 3px 0px;
                border: 1px solid rgba(81, 203, 238, 1);
            }
        </style>
        <script>
            $(document).ready(function () {
                //Format cac truong input.number can le phai
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
                //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $('.maPGD').css({"text-align": "left"});
                $('.tenPGD').css({"text-align": "right"});
//                $(".maPGD").css({"width": "150px"});
//                $(".maPGD").css({"width": "140px"});
            });</script>
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <SCRIPT language="javascript">

            $(document).ready(function () {
                $("#idRejecttmp").click(function () {
                    $('#divBrowseRisk').empty();
                    var nguyennhan_tuchoi = $("#idnguyennhan_tuchoi").val();
                    nguyennhan_tuchoi = nguyennhan_tuchoi.replace(/^\s*|\s*$/g, "");
//                nguyennhan_tuchoi = trim(nguyennhan_tuchoi);
                    if (nguyennhan_tuchoi == null || nguyennhan_tuchoi.length < 4)
                    {
                        alert('Bạn phải nhập nguyên nhân từ chối trước khi nhấn đồng ý');
                        $('#divBrowseRisk').html("<h2 style='color: red'>Bạn phải nhập nguyên nhân từ chối trước khi nhấn đồng ý ! </h2>");
                        $('#idnguyennhan_tuchoi').focus();
                        //document.getElementById("myAnchor").focus();
                        return;
                    }
                    nguyennhan_tuchoi = $("#idnguyennhan_tuchoi").val();

//                    alert('vao ham goi submit ' + soku);
                    var nam_xlrr = $("#nam_xlrr").val();
                    var dot_xlrr = $("#dot_xlrr").val();
                    var nhom_xlrr = $("#nhom_xlrr").val();
                    var nguon_von = $("#nguon_von").val();
                    var poscd = $('#poscd').val();
                    var vb_xlrr = $("#vb_xlrr").val();
                    var url = "setContentRejectMainPos.action?nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr + "&nhom_xlrr=" + nhom_xlrr
                            + "&nguon_von=" + nguon_von + "&poscd=" + poscd + "&nguyennhan_tuchoi=" + nguyennhan_tuchoi + "&vb_xlrr=" + vb_xlrr;
                    var data1 = "nam_xlrr=" + nam_xlrr + "&dot_xlrr=" + dot_xlrr + "&nhom_xlrr=" + nhom_xlrr
                            + "&nguon_von=" + nguon_von + "&poscd=" + poscd + "&nguyennhan_tuchoi=" + nguyennhan_tuchoi + "&vb_xlrr=" + vb_xlrr;
                    $.ajax({
                        type: 'POST',
                        url: url,
                        data: data1,
                        dataType: 'json',
                        contentType: 'application/json',
                        type: 'POST',
                                async: true,
                        success: function (data) {
                            try {
                                alert("Bạn đã nhập nguyên nhân từ chối cho các chi nhánh thành công ")
//                                self.opener.document.forms['paginationForm'].idSubmit.click();
                                window.close();

                            }
                            catch (e)
                            {
                                alert(e.toString());
                            }

                        },
                        error: function (data)
                        {
                            alert('Bạn đã nhập nguyên nhân từ chối Bị lỗi xin liên hệ với quản trị để được hỗ trợ ');
                            $('#divBrowseRisk').html("<h2 style='color: red'>Bạn đã nhập nguyên nhân từ chối Bị lỗi xin liên hệ với quản trị để được hỗ trợ ! </h2>");
                        }
                    });
                    return false;
                });
            });

            function closeSelf() {
                window.close();
                return true;
            }
        </script>
    </head>
    <body>
        <div id="container_popup" style="">
            <s:form name="frmdata" id="frmdata" action="setContentRejectMainPos.action" theme="simple">
                <s:hidden name="nam_xlrr" id="nam_xlrr"/>
                <s:hidden name="dot_xlrr" id="dot_xlrr"/>
                <s:hidden name="nhom_xlrr" id="nhom_xlrr"/>
                <s:hidden name="nguon_von" id="nguon_von"/>
                <s:hidden name="poscd" id="poscd"/>
                <div id="divChiTieu" style="text-align: center;">
                    <span id="idTitle">Thông tin chi tiết khách hàng từ chối xử lý rủi ro</span>
                    <hr/>
                    <table border="1px" id="tableKhnv" class="tableKhnv">
                        <tr>
                            <th rowspan="2" style="width: 4%; font-weight:bold;">STT</th>
                            <th rowspan="2" style="width: 6%; font-weight:bold;">Mã Đơn vị</th>
                            <th rowspan="2" style="width: 18%; font-weight:bold;">Tên Đơn vị</th>
                            <th rowspan="2" style="width: 4%; font-weight:bold;">Tổng số món</th>
                            <th rowspan="2" style="width: 10%; font-weight:bold;"> Tổng tiền gốc</th>
                            <th colspan="2" style="font-weight:bold;">Trong đó</th> 
                            <th rowspan="2" style="width: 10%; font-weight:bold;"> Tổng tiền Đề nghị</th>
                            <th colspan="2" style="font-weight:bold;">Trong đó</th> 
                        </tr>
                        <tr>
                            <th style="width: 10%; font-weight:bold;">Tổng gốc</th>
                            <th style="width: 10%; font-weight:bold;">Tổng lãi</th>
                            <th style="width: 10%; font-weight:bold;">Tổng gốc</th>
                            <th style="width: 10%; font-weight:bold;">Tổng lãi</th>
                        </tr>
                        <s:iterator value="lstBrowerView">
                            <s:if test="sPoscd.equalsIgnoreCase('999999')">
                                <tr class="cscontent">
                                <td colspan="3"><input type="text" style="font-weight:bold; text-align: center; color: #007fff;" value="<s:property value='sPosDesc'/>" name="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sSoKh'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly" style="font-weight:bold; color: #007fff;"/></td>
                                <td><input type="text" value="<s:property value='sTongtien'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly" style="font-weight:bold; color: #007fff;"/></td>
                                <td><input type="text" value="<s:property value='sTongDuno'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly" style="font-weight:bold; color: #007fff;"/></td>
                                <td><input type="text" value="<s:property value='sTongLai'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly" style="font-weight:bold; color: #007fff;"/></td>
                                <td><input type="text" value="<s:property value='sTongtienDn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly" style="font-weight:bold; color: #007fff;"/></td>
                                <td><input type="text" value="<s:property value='sTongDunoDn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly" style="font-weight:bold; color: #007fff;"/></td>
                                <td><input type="text" value="<s:property value='sTongLaiDn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly" style="font-weight:bold; color: #007fff;"/></td>
                            </tr>
                            </s:if>
                            <s:else>
                            <tr class="cscontent">                                
                                <td><input type="text" style="text-align: center;" value="<s:property value='nStt'/>" name="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sPoscd'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sPosDesc'/>" name="tenPGD" class="maPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sSoKh'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTongtien'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTongDuno'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTongLai'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTongtienDn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTongDunoDn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                                <td><input type="text" value="<s:property value='sTongLaiDn'/>" name="tenPGD" class="tenPGD" onfocus="this.select()" readonly="readonly"/></td>
                            </tr>
                           </s:else>
                        </s:iterator>
                    </table>


                    <div id="divBrowseRisk"></div>
                    <!--</br>-->
                    <table class="tableKhnv" align="center">
                        <tr align="center">
                            <td colspan="2" align="center">
                                <span id="idTitle">Nhập nguyên nhân từ chối cho các chi nhánh</span>
                            </td>
                        </tr>
                        <tr align="center">
                            <td colspan="2" align="center">
                                <hr/>
                            </td>
                        </tr>

                        <tr align="center">
                            <td  colspan="2" align="center">
                                <s:textarea id="idnguyennhan_tuchoi" name="nguyennhan_tuchoi" cssStyle="width: 98%" rows="5" >
                                </s:textarea>
                            </td>
                        </tr>
                        <tr align="center">
                            <td colspan="2" align="center">
                                <hr/>
                            </td>
                        </tr>
                        <tr align="center">
                            
                            <td align="center">
                                <sj:submit id="idReject" name="nameReject" value="Đồng ý 1"  cssStyle="display: none" targets="divBrowseRisk"></sj:submit>
                                    <input type="button"  id="idRejecttmp" name="nameRejecttmp" value="Đồng ý"  
                                           Style="margin-right:25px; float: right; height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"/>
                                </td>
                                <td align="center">
                                <sj:submit id="idClose" name="nameClose" value="Hủy bỏ" onclick="closeSelf()"
                                           cssStyle="margin-left:25px; float: left;height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"></sj:submit>
                                </td>
                                
                            </tr>
                        </table>
                        <hr/>


                    </div>

            </s:form>
        </div>
    </body>
</html>

