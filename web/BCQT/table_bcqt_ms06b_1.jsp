<%-- 
    Document   : table_bcqt_pl01
    Created on : Nov 16, 2015, 1:26:33 PM
    Author     : LION
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <sj:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_TEN_KH").css({"width": "40px"});
                $(".TD_TENTS").css({"width": "100px"});
                $(".TD_SOTIEN").css({"width": "50px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_THEMXOA").css({"width": "40px"});
            });
//            $('.TEN_KH').focus(function () {
//                $(this).closest('tr').addClass('highlight_row');
//            });
//            $('.TEN_KH').blur(function () {
//                $(this).closest('tr').removeClass('highlight_row');
//            });

//            $.subscribe("loitoroi", function (event) {
//                alert('Loi roi khong lam gi duoc dau');
//            });
//            $.subscribe('completediv_ss', function (event) {
//                var responseText = event.originalEvent.responseText;
//                alert('thanh cong roi ' + responseText);
//            });
        </script>
        <script>
            function deleteRow(indx) {
                var table = document.getElementById("tablems06B1");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang

                if (max_row < rowCount)
                    max_row = rowCount;

//                alert('max_row='+max_row+' rowCount='+rowCount);
                table.deleteRow(indx);
            }

            function addRow(indx, ma) {
//                sleep(1000);
                var index = parseInt(indx); //ko hieu so vao vong for lai mat index nen phai luu lai o day
                var table = document.getElementById("tablems06B1");
                var rowCount = table.rows.length - 2; //Dem so dong cua bang
                if (max_row < rowCount)
                    max_row = rowCount;
                else
                {
                    max_row++;
                    rowCount = max_row;
                }
                var code = (ma + rowCount).toString();
//                alert('Tong so dong ' + rowCount);
                var newTr = '<tr>\n\\n\\n\
                                <td align = "right" class="TD_TENTS"><input type="text" value="" id="D2' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D2" class="TEN_KH " onfocus="this.select();sumColumn( ' + code + ');" /><input type="hidden" id="id_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].MA" value="' + code + '"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D3_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D3" class="TEN_KH " onfocus="this.select();sumColumn( ' + code + ');" /></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="" id="D4_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D4" class="TEN_KH" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "right" class="TD_TEN_KH"><input type="text" value="0" id="D5_' + rowCount + '" name="lstDulieuNt[' + rowCount + '].D5" class="TEN_KH number" onfocus="this.select();sumColumn(' + code + ');"/></td>\n\
                                <td align = "center" class="TEN_THEMXOA"><input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/></td>\n\
                                </tr>';
                $($('table#tablems06B1 tr')[index]).after(newTr);

                $(".TD_TEN_KH").css({"width": "40px"});
                $(".TD_TENTS").css({"width": "100px"});
                $(".TD_SOTIEN").css({"width": "50px"});
                $(".TEN_KH").css({"width": "100%"});
                $(".TEN_THEMXOA").css({"width": "40px"});
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
//                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
//                $('#ui-datepicker-div').css('clip', 'auto');
//                $('.TEN_KH').focus(function () {
//                    $(this).closest('tr').addClass('highlight_row');
//                });
//                $('.TEN_KH').blur(function () {
//                    $(this).closest('tr').removeClass('highlight_row');
//                });
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);

            }
            function getMabyNumber(idx)
            {
                var ma = '';
                try {
                    var ma_id = 'id_' + idx;
                    ma = document.getElementById(ma_id).value;
                } catch (e)
                {
                    ma = '999999';
                }
                return ma;
            }
            function closeSelf() {
                window.close();
                return true;
            }
            function getValue(id)
            {
                var value = 0;
                try {
                    value = document.getElementById(id).value;
                    value = value.replace(/,/g, "");
                    if (value == '-1')
                        value = 0.0;
                } catch (e)
                {
                    value = 0.0;
                }
                return parseFloat(value);
            }
            function setValue(id, value)
            {
                try {
                    document.getElementById(id).value = value;
                } catch (e)
                {
//                    alert(e);
                }
            }
            function sumColumn(mainput_tmp)
            {

                var mainput = $.trim(mainput_tmp.toString());
                try {
//                    alert('\'' + mainput+ '\'');
                    var table = document.getElementById("tablems06B1");
                    var rowcount = table.rows.length;
                    rowcount = rowcount > max_row ? rowcount : max_row;
                    var D5 = 0, D6 = 0;
                    var pos = -2;
                    for (var i = 0; i < rowcount; i++)
                    {
                        var matmp = getMabyNumber(i);
                        if (mainput.substr(0, 3) == matmp.substr(0, 3) && matmp.length == mainput.length)
                        {
//                            alert(getValue('D5_' + i));
//Lay gia tri cho cac truong tu D2->d6
                            //neu ky tu cuoi cung cua ma la '1' Vi du voi ma '100001,100030... thi se chi lam voi ma khac '1' o cuoi
                            if (matmp.substr(matmp.length - 2, matmp.length) != '01')
                            {
                                //lay ra gia tri cua truong D7,D8,D9
                                D5 = D5 + getValue('D5_' + i);
                                D6 = D6 + getValue('D6_' + i);
                            }
                            if (matmp.substr(matmp.length - 2, matmp.length) == '01')
                            {
//                                console.log(getValue('D2_' + i));
                                pos = i;
                            }
                        }

                    }
                    //set gia tri
//                    alert('D8_' + pos)
                    setValue('D5_' + pos, D5);
                    setValue('D6_' + pos, D6);
                }
                catch (e)
                {
                    alert(e);
                }
                $('.number').number(true, 0);
//                  $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
//                $('.number2').number(true, 2);
            }
            //                alert('sorown='+$('#tablepl01 tr').length+' socot='+$('#tablepl01 td').length);

            function initTable()
            {
                //SET GIA TRI CHO SELECT 
                var table = document.getElementById("tablems06B1");
                var rowcount = table.rows.length;
                rowcount = rowcount > max_row ? rowcount : max_row;
                for (var i = 0; i < rowcount; i++)
                {
                    
                    var matmp = getMabyNumber(i);
                    if (matmp.substr(matmp.length - 2, matmp.length) != '01')
                    {
                        sumColumn(matmp);
                    }
                }
            }
            function onchangsave()
            {
                $("#button_save")[0].click();
            }

        </script>
        <script>
            function stopRKey(evt) {
                var evt = (evt) ? evt : ((event) ? event : null);
                var node = (evt.target) ? evt.target : ((evt.srcElement) ? evt.srcElement : null);
                if ((evt.keyCode == 13) && (node.type == "text")) {
                    return false;
                }
            }

            //Disable enter key form submit            
            document.onkeypress = stopRKey;
        </script>
    </head>
    <body>
        <s:form id="id_sv_123" action="SAVE_%{khoa_bcqt}" theme="simple" >
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                1.1. Điều chuyển nội bộ TSCĐ với TW, NHCSXH khác tỉnh, TP hoặc ngược lại.
            </div>
            <s:hidden name="khoa_bcqt"/>
            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>
            <table border="1" class="editDelete" id="tablems06B1" align="center">
                <tr>
                    <!--<th  style="width: 30px;" class="TD_TENTS">TT</th>-->
                    <th class="TD_TENTS">Tên TSCĐ điều chuyển</th>
                    <th class="TD_TEN_KH">Nơi điều chuyển đi</th>
                    <th  style="width: 30px;" class="TD_TEN_KH">Nơi nhận điều chuyển đến</th>
                    <th  style="width: 30px;" class="TD_TEN_KH">Nguyên giá</th>
                    <!--<th  style="width: 30px;" class="TD_TEN_KH">Hao mòn lũy kế</th>-->

                    <th  class="TEN_THEMXOA">Thêm/Xóa</th>
                </tr>                
                <tr>         
                    <!--<th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TENTS">(1)</th>-->
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TENTS">(2)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(3)</th>
                    <th style="width: 20px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(4</th>
                    <th style="width: 40px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(5)</th>
                    <!--<th style="width: 30px; font: italic; font-size: xx-small;" class="TD_TEN_KH">(6)</th>-->
                    <th style="font: italic; font-size: xx-small;" class="TEN_THEMXOA">(7)</th>

                </tr>
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr>  
                            <td align = "left" class="TD_TENTS">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH " onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D4" />"  id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH " onfocus="this.select();"
                                       readonly="true"/>
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onfocus="this.select();
                                       " readonly="true"/>
                            </td>                            


                            <td align = "center" class="TEN_THEMXOA">
                                <s:if test="TT_HIENTHI.equalsIgnoreCase('1')">
                                    <input type="button" value="Thêm" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class="TEN_KH"/>
                                </s:if>
                                <s:else>
                                    <input type="button" value="Xóa" onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                </s:else> 
                            </td>

                        </tr>
                    </s:if>

                    <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                        <tr>  
                            <td align = "left" class="TD_TENTS">
                                <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>
                                <input type="text" id="D2_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D2" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH " onfocus="this.select();"
                                       />
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" id="D3_<s:property  value="%{#rowstatus.index}" />" value="<s:property  value="D3" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="TEN_KH " onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');"
                                       />
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D4" />"  id="D4_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH " onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');"
                                       />
                            </td>
                            <td align = "right" class="TD_TEN_KH">
                                <input type="text" value="<s:property  value="D5" />" id="D5_<s:property  value="%{#rowstatus.index}" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="TEN_KH number" onblur="sumColumn('<s:property  value="MA"/>');"  onfocus="this.select();
                                               sumColumn('<s:property  value="MA"/>');" />
                            </td>                            
                          

                            <td align = "center" class="TEN_THEMXOA">
                                <s:if test="TT_HIENTHI.equalsIgnoreCase('1')">
                                    <input type="button" value="Thêm" id="them" onclick="addRow(this.parentNode.parentNode.rowIndex,<s:property  value="MA"/>)" class=" TEN_KH"/>
                                </s:if>
                                <s:else>
                                    <input type="button" value="Xóa" id="xoa"  onclick="deleteRow(this.parentNode.parentNode.rowIndex)" class="TEN_KH"/>
                                </s:else> 
                            </td>

                        </tr>
                    </s:if>

                </s:iterator>
            </table>
            <table align="center">
                <tr align="center">

                    <td align="center">
                                                <sj:submit id="button_save" name="%{khoa_bcqt}_save" value="Cập nhật" targets="message_suc_err1" onBeforeTopics="beforediv_ss"
                                   onCompleteTopics="completediv_ss" onErrorTopics="loitoroi"
                                   cssStyle="margin-left:25px; float: left;height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"/>
                    </td>
                    <td align="center">
                        <sj:submit id="idClose" name="nameClose" value="    Thoát    " onclick="closeSelf()"
                                   cssStyle="margin-left:25px; float: left;height:28px;width:95px; background-color: #FFFFC0; border: 2pt ridge lightgrey;"
                                   ></sj:submit>
                        </td>

                    </tr>
                </table>
            <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err1" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
            <div id="message_suc_err1">
            </div>
            <script>
            initTable();
        </script>
        </s:form>

    </body>
</html>
