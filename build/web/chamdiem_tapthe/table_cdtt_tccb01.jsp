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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        
        <script>            
            var max_row = 0;
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                //Cac truong bang so --> se co so truong = 0
//                $('.number').number(true, 0);
                $('input.number').css({"text-align": "right"});
                $('input.number2').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 2);
                $(".TD_THUTU").css({"width": "2%"});
                $(".TD_TEN_KH").css({"width": "50px"});
                $(".TD_TENTS").css({"width": "120px"});
                $(".TD_MATS").css({"width": "85px"});
                $(".TD_NGUYENGIA").css({"width": "8%"});
                $(".TD_10").css({"width": "10%"});
                $(".TD_5").css({"width": "5%"});
                $(".TD_15").css({"width": "15%"});
                $(".TD_SOLUONG").css({"width": "4%"});
                $(".TD_TYLE").css({"width": "4%"});
                $(".TD_THEMXOA").css({"width": "5%"});
                $(".TD_CBTH").css({"width": "12%"});
                $(".TD_GHICHU").css({"width": "8%"});
                $(".TD_CHITIEU").css({"width": "20%"});
                $(".TD_NOIDUNG").css({"width": "10%"});
                $(".TD_NOIDUNG06A").css({"width": "35%"});
                $(".TEN_KH").css({"width": "100%"});                
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            
            function OnchangeSelect(subid,ma)
            {
                try {
                    
                    var old = document.getElementById(subid + 'L_' + ma).value
                    document.getElementById(subid + '_' + ma).value = old; //thiết lập vào đúng vị trí 
                    
                    
                    var D2 = document.getElementById('D2_' + ma).value;
                    var D3 = document.getElementById('D3_' + ma).value;
                    var D5 = document.getElementById('D5_' + ma).value;
                    var D7 = document.getElementById('D7_' + ma).value;
                    var D9 = document.getElementById('D9_' + ma).value;
                    var D10 = document.getElementById('D10_' + ma).value;
                    if(D3 === "D")
                    {
                        document.getElementById('D10_' + ma).value = "D";
                        document.getElementById('D11_' + ma).value = "D";
                        if(parseFloat(D9) >=3)
                        {
                            document.getElementById('D10_' + ma).value = "C";
                            document.getElementById('D11_' + ma).value = "C";
                        }
                        return;
                    }                    
                    else if (D3 === "C")
                    {
                        document.getElementById('D10_' + ma).value = "C";
                        document.getElementById('D11_' + ma).value = "C";
                        if(parseFloat(D9) >=4)
                        {
                            document.getElementById('D10_' + ma).value = "B";
                            document.getElementById('D11_' + ma).value = "B";
                        }
                        return;
                    }
                    else if (D3 === "B")
                    {
                        if(D5 === "Y" || D7 === "Y")
                        {
                            document.getElementById('D10_' + ma).value = "C";
                            document.getElementById('D11_' + ma).value = "C";
                            if(parseFloat(D9) >=4)
                            {
                                document.getElementById('D10_' + ma).value = "B";
                                document.getElementById('D11_' + ma).value = "B";
                            }
                            return;
                        }
                        else
                        {
                            document.getElementById('D10_' + ma).value = "B";
                            document.getElementById('D11_' + ma).value = "B";
                            return;
                        }    
                    }
                    else if (D3 === "A")
                    {
                        if(D5 === "Y" || D7 === "Y")
                        {
                            document.getElementById('D10_' + ma).value = "C";
                            document.getElementById('D11_' + ma).value = "C";
                            if(parseFloat(D9) >=4)
                            {
                                document.getElementById('D10_' + ma).value = "B";
                                document.getElementById('D11_' + ma).value = "B";
                            }
                            return;
                        }                        
                        else
                        {
                            if (D2 === "B")
                            {
                                document.getElementById('D10_' + ma).value = "B";
                                document.getElementById('D11_' + ma).value = "B";
                                return;
                            }
                            else if (D2 === "C")
                            {
                                document.getElementById('D10_' + ma).value = "C";
                                document.getElementById('D11_' + ma).value = "C";
                                if(parseFloat(D9) >=4)
                                {
                                    document.getElementById('D10_' + ma).value = "B";
                                    document.getElementById('D11_' + ma).value = "B";
                                }
                                return;
                            }
                            else if (D2 === "D")
                            {
                                document.getElementById('D10_' + ma).value = "D";
                                document.getElementById('D11_' + ma).value = "D";
                                if(parseFloat(D9) >=4)
                                {
                                    document.getElementById('D10_' + ma).value = "C";
                                    document.getElementById('D11_' + ma).value = "C";
                                }
                                return;
                            }
                            else
                            {
                                document.getElementById('D10_' + ma).value = "A";
                                document.getElementById('D11_' + ma).value = "A";
                                return;
                            }
                        }    
                    }
                    
                    if(D5 === "Y")
                    {
                        document.getElementById('D6_' + ma).focus();
                        document.getElementById('D6_' + ma).style.backgroundColor = "#DE76DF";
                    }
                  
                } catch (e) {
                    console.log('ERROR=' + e.toString() );
                }
            }
            
        </script>        
    </head>
    <body>
        <s:form id="id_sv_%{khoa_cdtt}" action="SAVE_%{khoa_cdtt}" theme="simple">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />"  
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                MÀN HÌNH CHẤM ĐIỂM CỦA THƯỜNG TRỰC HỘI ĐỒNG XÉT MỨC ĐỘ HTNV
            </div>                        
            <s:hidden name="khoa_cdtt"/>            
            <table border="1" class="editDelete" id="tablecdtt99" align="center">                
                <table border="1" class="editDelete" id="tablecdtt99" align="center">
                    <tr height="45">
                        <!--<th  class="TD_THUTU">TT</th>-->
                        <th rowspan="2"  class="TD_10">Tên chi nhánh</th>  
                        <th rowspan="2" class="TD_5">Tổng điểm Ban CMNV chấm</th>  
                        <th rowspan="2"  class="TD_5">Xếp loại</th>                         
                        <th colspan="6"  >Công tác Quản trị điều hành</th>  
                        <th rowspan="2"  class="TD_5">Thực hiện đề án nâng cao chất lượng tín dụng</th>
                        <th rowspan="2"  class="TD_5">Kết quả xếp loại</th>                        
                    </tr>  
                    <tr height="35">
                         <th colspan="2" class="TD_SOLUONG">Chỉ đạo, điều hành</th>  
                         <th colspan="2" class="TD_SOLUONG">Đoàn kết nội bộ (đơn thư…)</th>  
                         <th colspan="2" class="TD_SOLUONG">Vụ việc</th>    
                    </tr>
                    
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                                            
                            <tr>  
                                <td align = "left" class="TD_10">
                                    <input type="text"  value="<s:property  value="TEN" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" class="TEN_KH"
                                           readonly="true"/>
                                    <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].MA" value="<s:property  value="MA"/>"/>                            
                                </td>
                                <td align = "left" class="TD_5">
                                    <input type="text"  value="<s:property  value="D1" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="TEN_KH number2"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">
                                    <input type="text" value="<s:property  value="D2"/>"  id="D2_<s:property  value="MA"/>"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="TEN_KH D0"
                                           readonly="true"/>
                                </td>
                                <td align = "left" class="TD_5">                                        
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D3" id="D3L_<s:property  value="MA" />"
                                            class="TEN_KH " onchange="OnchangeSelect('D3','<s:property  value="MA" />');">
                                        <option value="A" <s:if test="D3.equalsIgnoreCase('A')"> selected </s:if> >Tốt</option>
                                        <option value="B" <s:elseif test="D3.equalsIgnoreCase('B')"> selected </s:elseif>>Khá</option>
                                        <option value="C" <s:elseif test="D3.equalsIgnoreCase('C')"> selected </s:elseif>>Trung bình</option>     
                                        <option value="D" <s:elseif test="D3.equalsIgnoreCase('D')"> selected </s:elseif>>Yếu</option>                                           
                                        </select>
                                        <input type="hidden" value="<s:property  value="D3" />" id="D3_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" />
                                </td>                                                          

                                <td align = "center" class="TD_15">
                                    <input type="text" value="<s:property  value="D4" />"  id="D4_<s:property  value="MA"/>"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="TEN_KH"/>
                                </td>
                                <td align = "left" class="TD_5">                                        
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D5" id="D5L_<s:property  value="MA" />"
                                            class="TEN_KH " onchange="OnchangeSelect('D5','<s:property  value="MA" />');">
                                        <option value="N" <s:if test="D5.equalsIgnoreCase('N')"> selected </s:if> >Không</option>
                                        <option value="Y" <s:elseif test="D5.equalsIgnoreCase('Y')"> selected </s:elseif>>Có</option>                                                                                
                                        </select>
                                        <input type="hidden" value="<s:property  value="D5" />" id="D5_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" />
                                </td>
                                
                                <td align = "left" class="TD_15">
                                    <input type="text" value="<s:property  value="D6" />"  id="D6_<s:property  value="MA"/>"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" class="TEN_KH"/>
                                </td>
                                
                                <td align = "left" class="TD_5">                                        
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D7" id="D7L_<s:property  value="MA" />"
                                            class="TEN_KH " onchange="OnchangeSelect('D7','<s:property  value="MA" />');">
                                        <option value="N" <s:if test="D7.equalsIgnoreCase('N')"> selected </s:if> >Không</option>
                                        <option value="Y" <s:elseif test="D7.equalsIgnoreCase('Y')"> selected </s:elseif>>Có</option>                                                                                
                                        </select>
                                        <input type="hidden" value="<s:property  value="D7" />" id="D7_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" />                                
                                </td>  
                            
                            <td align = "left" class="TD_15">
                                    <input type="text" value="<s:property  value="D8" />"  id="D8_<s:property  value="MA"/>"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" class="TEN_KH"/>
                                </td> 
                            <td align = "left" class="TD_5">                                        
                                    <select name="lstDulieu[<s:property  value="%{#rowstatus.index}" />].D9" id="D9L_<s:property  value="MA" />"
                                            class="TEN_KH " onchange="OnchangeSelect('D9','<s:property  value="MA" />');">
                                        <option value="0" <s:if test="D9.equalsIgnoreCase('0')"> selected </s:if> >0</option>
                                        <option value="3" <s:if test="D9.equalsIgnoreCase('3')"> selected </s:if> >3</option>
                                        <option value="4" <s:elseif test="D9.equalsIgnoreCase('4')"> selected </s:elseif>>4</option>                                                                                
                                        <option value="5" <s:elseif test="D9.equalsIgnoreCase('5')"> selected </s:elseif>>5</option>
                                        </select>
                                        <input type="hidden" value="<s:property  value="D9" />" id="D9_<s:property  value="MA" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" />                                
                                </td> 
                            <td align = "left" class="TD_5"> 
                                    <input type="text" value="<s:property  value="D10" />"  id="D10_<s:property  value="MA"/>"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="TEN_KH D0"/>
                                    <input type="hidden" value="<s:property  value="D11" />"  id="D11_<s:property  value="MA"/>"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" class="TEN_KH D0"/>
                               
                                </td>    
                                
                            </tr>                        
                    </s:iterator>
                </table>             
            <sj:submit id="%{khoa_cdtt}_save" name="%{khoa_cdtt}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                       onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:form>
        <div id="luu_thanhcong"></div>
    </body>
</html>
