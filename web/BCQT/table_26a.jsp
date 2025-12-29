<%-- 
    Document   : bcqt_26a
    Created on : 13/12/2017
    Author     : Moon
--%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css"  href="css/bcqt.css" />
<!DOCTYPE html>
<html>
    <head>
        <style>
            a {
                color: #0000FF;
            }
            .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
            .CLS-BOLD{
                font-weight: bold;
            }
        </style>
    </style>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="js/jquery.number.js"></script>
    <script src="js/format_num.js"></script>
    <script>
        $(document).ready(function () {
            $('input.number').css({"text-align": "right"});
            $('input.number5').css({"text-align": "right"});
            $('.D0').css({"text-align": "center"});
            $('input.number2').css({"text-align": "right"});
            $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
            $('#ui-datepicker-div').css('clip', 'auto');
            //Cac truong bang so --> se co so truong = 0
            $('.number').number(true, 2);
//            //Cac truong bang so --> se co so truong = 0
            $('.number2').number(true, 2);
            $('.number5').number(true, 5);
            $(".TD_NHOM").css({"width": "10px"});
            $(".TD_THUTU").css({"width": "40px"});
            $(".TD_CHITIEU").css({"width": "130px"});
            $(".TD_SOTIEN").css({"width": "70px"});
            $(".TD_GHICHU").css({"width": "170px"});

        });
        $('.TEN_KH').focus(function () {
            $(this).closest('tr').addClass('highlight_row');
        });
        $('.TEN_KH').blur(function () {
            $(this).closest('tr').removeClass('highlight_row');
        });
//            autoEvaluate();
    </script>    

    <script>
        function autoEvaluate() {
//                alert('vao doClick');
            var arrCot = [".D10"]; //Luu cac cot cua du lieu can tinh toan

            // Tinh cho dong A1
            for (i = 0; i < arrCot.length; i++) {

                let rawValue06 = $(arrCot[i]).eq(6).val();
                let value06 = rawValue06 === null || rawValue06 === "" ? 0 : parseFloat(rawValue06);

                //Tinh tong cho dong "Các khoan thu noi bang"
                $(arrCot[i]).eq(1).val(
                        parseFloat($(arrCot[i]).eq(2).val())
                        + parseFloat($(arrCot[i]).eq(3).val())
                        + parseFloat($(arrCot[i]).eq(4).val())
                        + parseFloat($(arrCot[i]).eq(5).val())
                        + value06
                        //+ parseFloat($(arrCot[i]).eq(6).val())
                        );
            }
            //tính cho dòng A20013
//                for (i = 0; i < arrCot.length; i++) { 
//                    //Tinh tong cho dong "Các khoản được cộng"
//                     $(arrCot[i]).eq(12).val(parseFloat($(arrCot[i]).eq(13).val())
//                             +parseFloat($(arrCot[i]).eq(14).val()));
//                }

            // Tinh cho dong A2001 2.1. Các khoản được cộng:
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các khoản được cộng"
                let rawValue11 = $(arrCot[i]).eq(11).val();
                let value11 = rawValue11 === null || rawValue11 === "" ? 0 : parseFloat(rawValue11);

                let rawValue12 = $(arrCot[i]).eq(12).val();
                let value12 = rawValue12 === null || rawValue12 === "" ? 0 : parseFloat(rawValue12);

                let rawValue13 = $(arrCot[i]).eq(13).val();
                let value13 = rawValue13 === null || rawValue13 === "" ? 0 : parseFloat(rawValue13);

                $(arrCot[i]).eq(8).val(parseFloat($(arrCot[i]).eq(9).val())
                        + parseFloat($(arrCot[i]).eq(10).val())
                        + value11
                        + value12
                        + value13
                        );
            }

            // Tinh cho dong A2002
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các khoản phải trừ"
                $(arrCot[i]).eq(14).val(parseFloat($(arrCot[i]).eq(15).val()) + parseFloat($(arrCot[i]).eq(16).val()) +
                        parseFloat($(arrCot[i]).eq(17).val()));
            }
            // Tinh cho dong A2
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các khoản thu ngoại bảng"
                $(arrCot[i]).eq(7).val(parseFloat($(arrCot[i]).eq(8).val()) -
                        parseFloat($(arrCot[i]).eq(14).val()));
//                window.alert("8= "+ parseFloat($(arrCot[i]).eq(8).val()) + " 14 = " +parseFloat($(arrCot[i]).eq(14).val()));
            }

            // Tinh cho dong A
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Tong thu"
                $(arrCot[i]).eq(0).val(parseFloat($(arrCot[i]).eq(1).val()) +
                        parseFloat($(arrCot[i]).eq(7).val()));
            }

            // Tinh cho dong  B1
            for (i = 0; i < arrCot.length; i++) {
                $(arrCot[i]).eq(19).val(parseFloat($(arrCot[i]).eq(20).val()) +
                        parseFloat($(arrCot[i]).eq(21).val()) + parseFloat($(arrCot[i]).eq(22).val()) +
                        parseFloat($(arrCot[i]).eq(23).val()) + parseFloat($(arrCot[i]).eq(24).val()) +
                        parseFloat($(arrCot[i]).eq(25).val()) + parseFloat($(arrCot[i]).eq(26).val()));
            }

            // Tinh cho dong B2001   
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "2.1 Các khoản cộng thêm"
                $(arrCot[i]).eq(28).val(parseFloat($(arrCot[i]).eq(29).val()) + parseFloat($(arrCot[i]).eq(30).val())
                        + parseFloat($(arrCot[i]).eq(31).val()));
            }

            // Tinh cho dong B2002   
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "2.2 Các khoản được loại trừ"
                $(arrCot[i]).eq(32).val(parseFloat($(arrCot[i]).eq(33).val()) + parseFloat($(arrCot[i]).eq(34).val())
                        + parseFloat($(arrCot[i]).eq(35).val()) + parseFloat($(arrCot[i]).eq(36).val()));
            }

            // Tinh cho dong B2  
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Các khoản chi ngoại bảng"
                $(arrCot[i]).eq(27).val(parseFloat($(arrCot[i]).eq(28).val()) - parseFloat($(arrCot[i]).eq(32).val()));
            }

            // Tinh cho dong B  
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Tổng chi"
                $(arrCot[i]).eq(18).val(parseFloat($(arrCot[i]).eq(19).val()) + parseFloat($(arrCot[i]).eq(27).val()));
            }

            // Tinh cho dong C 
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Chênh lệch thu - chi chưa có lương"
                $(arrCot[i]).eq(37).val(parseFloat($(arrCot[i]).eq(0).val()) - parseFloat($(arrCot[i]).eq(18).val()));
            }

            // Tinh cho dong D  
//                for (i = 0; i < arrCot.length; i++) { 
//                    //Tinh tong cho dong "Quỹ tiền lương V1"
//                    $(arrCot[i]).eq(37).val( parseFloat($(arrCot[i]).eq(56).val()) + parseFloat($(arrCot[i]).eq(57).val()));
//                }

            // Tinh cho dong F  
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Quỹ lương đạt được"
                $(arrCot[i]).eq(40).val(parseFloat($(arrCot[i]).eq(43).val()) + parseFloat($(arrCot[i]).eq(41).val()) + parseFloat($(arrCot[i]).eq(42).val()));
            }


            // Tinh cho dong G 
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Quỹ lương đạt được"
                $(arrCot[i]).eq(47).val(
                        parseFloat($(arrCot[i]).eq(51).val())
                        + parseFloat($(arrCot[i]).eq(48).val())
                        + parseFloat($(arrCot[i]).eq(49).val())
                        + parseFloat($(arrCot[i]).eq(50).val())
                        );
            }

            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Quỹ lương đạt được"
                $(arrCot[i]).eq(44).val(
                        parseFloat($(arrCot[i]).eq(47).val())
                        + parseFloat($(arrCot[i]).eq(45).val())
                        - parseFloat($(arrCot[i]).eq(46).val())
                        );
            }

            // Tinh cho dong I
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Lao động và quỹ lương thực hiện"
                $(arrCot[i]).eq(7).val(parseFloat($(arrCot[i]).eq(8).val())
                        - parseFloat($(arrCot[i]).eq(14).val()));
            }


            // Tinh cho dong H
            for (i = 0; i < arrCot.length; i++) {
                //Tinh tong cho dong "Lao động và quỹ lương thực hiện"
                $(arrCot[i]).eq(52).val(parseFloat($(arrCot[i]).eq(40).val())
                        - parseFloat($(arrCot[i]).eq(44).val()));
            }
            // Tinh cho dong G
//                for (i = 0; i < arrCot.length; i++) { 
//                    //Tinh tong cho dong "Lao động và quỹ lương thực hiện"
//                    $(arrCot[i]).eq(45).val(
//                             parseFloat($(arrCot[i]).eq(46).val())
//                             + parseFloat($(arrCot[i]).eq(47).val())
//                             - parseFloat($(arrCot[i]).eq(48).val())
//                             );                   
//                }

        }
    </script>
</head>
<body>
    <s:form id="id_sv_%{khoa_bcqt}" action="SAVE_%{khoa_bcqt}" theme="simple">  
        <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
            <input type="hidden" id="<s:property  value="sKey" />" 
                   name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
        </s:iterator>
        <div id="divTitle">
            BÁO CÁO KẾT QUẢ THỰC HIỆN KHOÁN TÀI CHÍNH
        </div>
        <s:hidden name="khoa_bcqt"/>
        <div id="divDonvitinh">
            Đơn vị: người/triệu đồng
        </div>
        <table border="1" class="editDelete" id="tablems01" align="center">
            <tr height="32" >
                <th  class="TD_NHOM">Phân nhóm</th>
                <th   class="TD_THUTU">Mã chỉ tiêu</th>
                <th  class="TD_NHOM">Số thứ tự</th>
                <th  class="TD_CHITIEU">Mô tả</th>
                <th  class="TD_GHICHU">Công thức</th>                         
                <!--<th  class="TD_SOTIEN">Số kế hoạch</th>-->  
                <th  class="TD_SOTIEN">Số thực hiện</th>  
            </tr>                              
            <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                    <tr height="22">  
                        <td  align="left" class= "<s:property value='FONTFORMAT'/> TD_NHOM">    
                            <input type="text" value="<s:property  value="D1" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                   class="D0 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"  readonly="readonly" />                                  
                        </td>    
                        <td  align="left" class= "<s:property value='FONTFORMAT'/> TD_THUTU">    
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"  readonly="readonly" />                                  
                            <input type="hidden" value="<s:property  value="MA" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 

                            <input type="hidden" value="<s:property  value="THUTU" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 


                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/> 
                            <input type="hidden" value="<s:property  value="D2" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/> 
                            <input type="hidden" value="<s:property  value="D11" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" value="<s:property  value="D11"/>"/> 
                            <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 

                        </td>
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_NHOM">
                            <input type="text" value="<s:property  value="D15" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" readonly/>
                        </td>
                        <td  align="right" class= "<s:property value='FONTFORMAT'/> TD_CHITIEU">    
                            <input type="text" value="<s:property  value="TEN" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN"  class="<s:property value='FONTFORMAT'/>" onfocus="this.select()"    readonly="readonly" />                                  
                        </td>              

                        <td align = "right" class="TD_GHICHU">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5  <s:property value='FONTFORMAT'/>  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   readonly="readonly"/>
                        </td> 
                        <td align = "right" class="TD_GHICHU">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number <s:property value='FONTFORMAT'/>  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="autoEvaluate()"                             
                                   readonly="readonly"/>
                        </td> 

                    </tr>
                </s:if>
                <s:if test="NHAPTAY.equalsIgnoreCase('Y')">
                    <tr height="22">      
                        <td align ="left" class= "<s:property value='FONTFORMAT'/> TD_NHOM">
                            <input type="text" style="text-align:center" value="<s:property  value="D1" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" readonly/>
                        </td>

                        <td align ="left" class= "<s:property value='FONTFORMAT'/> TD_THUTU">
                            <input type="text"  value="<s:property  value="TT_HIENTHI" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" readonly/>
                            <input type="hidden" value="<s:property  value="MA" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/> 
                            <input type="hidden" value="<s:property  value="THUTU" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/> 

                            <input type="hidden" value="<s:property  value="CO_TONGHOP" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].CO_TONGHOP" value="<s:property  value="CO_TONGHOP"/>"/> 
                            <input type="hidden" value="<s:property  value="D2" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" value="<s:property  value="D2"/>"/> 
                            <input type="hidden" value="<s:property  value="D11" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" value="<s:property  value="D11"/>"/> 
                            <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/> 

                        </td>
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_NHOM">
                            <input type="text" value="<s:property  value="D15" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D15" readonly/>
                        </td>
                        <s:if test="(MA.equalsIgnoreCase('CT9999') || MA.equalsIgnoreCase('CT9998')) && Grade.equalsIgnoreCase('1')">
                            <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN">
                                <input type="text" style="color: red" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                            </td>
                        </s:if>
                        <s:else>
                            <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN">
                                <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                            </td>
                        </s:else>



                        <td align = "right" class="TD_GHICHU">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5    <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   readonly/>
                        </td>
                        <s:if test="(MA.equalsIgnoreCase('CT9999')||MA.equalsIgnoreCase('CT9998') )&& Grade.equalsIgnoreCase('1')">
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number5 <s:property value='FONTFORMAT'/>  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="autoEvaluate()"                                  
                                       />
                            </td> 
                        </s:if>
                        <s:else>
                            <td align = "right" class="TD_GHICHU">
                                <input type="text" value="<s:property  value="D10" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D10 number <s:property value='FONTFORMAT'/>  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="autoEvaluate()"                                  
                                       />
                            </td> 
                        </s:else>


                    </tr>
                </s:if>

            </s:iterator>
        </table>
        <p></p>          

        <sj:submit id="%{khoa_bcqt}_save" name="%{khoa_bcqt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                   onCompleteTopics="completediv_ss" cssStyle="display: none"/>
    </s:form>
    <div id="luu_thanhcong"></div>
    <s:form action="KHOANTC001.action" id="paginationForm1">
        <s:iterator value="poscd" status="row">
            <s:hidden name="poscd[%{#row.index}]" />
        </s:iterator>
        <s:hidden name="ngay_bc" id="ngay_bc"/>        
        <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                   onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
    </s:form>
    <div id="divBrowseRisk"></div>
</body>
</html>
