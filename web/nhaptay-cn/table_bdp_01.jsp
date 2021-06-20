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
        <style>
        a {
            color: #0000FF;
            text-decoration: underline;
        }
        .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
            }
        #divMuc{
            font: 12px Arial, Helvetica, sans-serif;
            text-align: left;
            color: red;
        }
        </style>
        </style>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.number.js"></script>
        <script src="js/format_num.js"></script>
        <script>
            $(document).ready(function () {
                $('input.number').css({"text-align": "right"});
                $('.D0').css({"text-align": "center"});
                $('input.number2').css({"text-align": "right"});
                $(".datepicker").datepicker({dateFormat: 'dd/mm/yy'});
                $('#ui-datepicker-div').css('clip', 'auto');
                //Cac truong bang so --> se co so truong = 0
                $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
                $('.number2').number(true, 0);
                $(".TD_MONVAY").css({"width": "90px"});
                $(".TD_THUTU").css({"width": "140px"});
                $(".TD_SOKU").css({"width": "190px"});
                $(".TD_SOTK").css({"width": "210px"});
                $(".TD_TENKH").css({"width": "250px"});
                $(".TD_NGAYBC").css({"width": "70px"});
                $(".TD_SOTIEN").css({"width": "70px"});                
                $(".TD_GHICHU").css({"width": "200px"});

            });
            
            $(document).ready(function () {
            $("#allCheck").change(function () {
                $(".checkbox1").prop('checked', $(this).prop("checked"));
            });
        });
        
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
        </script>
        <script>
                function autoEvaluate(){
    //                alert('vao doClick');
                    var arrCot = [".D2",".D3",".D4",".D5",".D13",".D14",".D15",".D16",".D17",".D18",".D19",".D20",".D37",".D38",".D39",".D40"]; //Luu cac cot cua du lieu can tinh toan
                    for(var i=0; i<2; i++){                        
                        //8=2+4-6
                        $(".D23").eq(i).val(parseFloat($(".D2").eq(i).val()) + parseFloat($(".D3").eq(i).val()) + parseFloat($(".D4").eq(i).val())
                                - (parseFloat($(".D13").eq(i).val()) + parseFloat($(".D15").eq(i).val()) + parseFloat($(".D17").eq(i).val())+
                                parseFloat($(".D18").eq(i).val()) + parseFloat($(".D19").eq(i).val()) + parseFloat($(".D21").eq(i).val())+
                                parseFloat($(".D37").eq(i).val()) + parseFloat($(".D39").eq(i).val()) 
                                ));
                        $(".D24").eq(i).val(parseFloat($(".D5").eq(i).val())
                                - (parseFloat($(".D14").eq(i).val()) + parseFloat($(".D16").eq(i).val())  + parseFloat($(".D20").eq(i).val()) + parseFloat($(".D22").eq(i).val())
                                + parseFloat($(".D38").eq(i).val()) + parseFloat($(".D40").eq(i).val()) ));
                    
                    }         
                }
        var max_row = 0;       
        function initTable()
            {
                try
                {
                    var table = document.getElementById("tableBdp02");                
                    var rowcount = table.rows.length; 
                    if (rowcount<=0)
                    {
                        return ;
                    }
                    rowcount = rowcount > max_row ? rowcount : max_row;                
                    for (var i = 0; i < rowcount; i++)
                    {                    
                        var matmp = getMabyNumber(i);//    
                        if(matmp == 1)
                        {
                            $('input:checkbox[id='+i+']').attr('checked',true);
                        }
                    }
                }
                catch(err) {
//                    document.getElementById("demo").innerHTML = err.message;
                }
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
        
        
                </script>       
    </head>
    <body>
        <s:form id="id_sv_%{khoa_nhaptaycn}" action="SAVE_%{khoa_nhaptaycn}" theme="simple">  
        <s:if test="Grade.equalsIgnoreCase('1')">
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                THÔNG TIN HỘ VAY BỎ ĐI KHỎI ĐỊA PHƯƠNG
            </div>
            <s:hidden name="khoa_nhaptaycn"/>  
            
            
             <s:iterator value="#attr.lstNt" var="modelView" status="rowstatus">    
             <div  id="divMuc" style="width: 85%"  align="center">
                                1. Thông tin chung
            </div>    
            <table border="1" class="editDelete" id="tabledbp01" style="width: 90%"  align="center">
                <tr height="25">                    
                    <th  class="TD_NGAYBC">Mã khách hàng</th>
                    <th  class="TD_THUTU">Tên khách hàng</th> 
                    <th  class="TD_NGAYBC">Mã tổ trường</th>
                    <th  class="TD_THUTU">Tên tổ trường</th>
                    <th  class="TD_NGAYBC">Đơn vị ủy thác</th> 
                    <th  class="TD_NGAYBC">Chương trình</th>                                                                                                   
                    <th  class="TD_NGAYBC">Ngày vay</th>
                    <th  class="TD_NGAYBC">Ngày đến hạn</th>
                    <th  class="TD_NGAYBC">Số CMND</th>
                </tr>                             
               
                        <tr height="22">                             
                        <td align = "center" class="TD_NGAYBC">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D26" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D26" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   readonly="readonly"/>
                            </td>  
                        <td align = "right" class="TD_THUTU">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D27" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D27" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td>
                        
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D28" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D28" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_THUTU">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D29" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D29" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" readonly="readonly"/>                                                              
                        </td>
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D30" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D30" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                            <input type="hidden" style="color: #0000FF" value="<s:property  value="D31" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D31" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>                        
                        </td> 
                        
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D32" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D32" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" readonly="readonly"/>
                        </td> 
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D33" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D33" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()"  readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" style="color: #0000FF"  value="<s:property  value="D34" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D34" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td> 
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" style="color: #0000FF"  value="<s:property  value="D35" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D35" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td> 
                    </tr>                                                        
            </table>                                    
                <div style="height:5px;"></div>            
            <table border="1" class="editDelete" id="tablems01" style="width: 90%"  align="center">
                <tr height="25">                    
                    <th  class="TD_MONVAY">Mã món vay</th>
                    <th  class="TD_MONVAY">Địa chỉ(thôn/ấp)</th>
                    <th  class="TD_NGAYBC">Dư nợ trong hạn</th> 
                    <th  class="TD_NGAYBC">Dư nợ quá hạn</th>
                    <th  class="TD_NGAYBC">Dư nợ khoanh</th>                    
                    <th  class="TD_NGAYBC">Nợ lãi</th>                                        
                    <th  class="TD_NGAYBC">Ngày chốt SL</th>                                        
                    <th  class="TD_NGAYBC">Ngày bỏ đi</th>                                                            
                </tr>                             
               
                        <tr height="22">                             
                        <td align = "right" class="TD_MONVAY">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D1" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D1" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_MONVAY">
                            <input type="text" style="color: #0000FF" value="<s:property  value="D36" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D36" class="<s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"                                   
                                   readonly="readonly"/>
                        </td>  
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()"
                                   readonly="readonly"/>
                        </td>
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" readonly="readonly"/>                                                              
                        </td>
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                        </td> 
                        
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" value="<s:property  value="D5" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D5 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" readonly="readonly"/>
                        </td>                         
                        <td align = "right" class="TD_NGAYBC">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D6" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D6" class="D0 datepicker" placeholder="dd/MM/yyyy"  onfocus="this.select()"/>
                        </td> 
                        <td align = "right" style="background-color: #fdf5ce;" class="TD_NGAYBC">
                            <input type="text" value="<s:property  value="D7" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D7" class="D0 datepicker" placeholder="dd/MM/yyyy"  onfocus="this.select()"/>
                        </td>                         
                    </tr>                                                        
            </table>
              &nbsp;&nbsp;&nbsp; 
            <div  id="divMuc" style="width: 85%"  align="center">
                                2. Thông tin tìm kiếm
            </div>
             <table border="1" class="editDelete" id="tablems01" style="width: 90%"  align="center">
                <tr height="25">                    
                    <th  class="TD_CHITIEU">Nhóm địa chỉ</th>
                    <th  class="TD_SOTK">Địa chỉ ti tiết</th>                                                            
                </tr>                             
               
                        <tr height="22">                             
                        <td align = "right" style="width: 15%">
                            <s:select id="trangthai" name="lstNt[%{#rowstatus.index}].D8"
                                              list="#{'0':'--Nhóm địa chỉ--','1':'Nhóm địa chỉ 01','2':'Nhóm địa chỉ 02','3':'Nhóm địa chỉ 03'}"
                                              cssStyle="width: 190px; vertical-align: middle; background-color: #fdf5ce;" />
                        </td>    
                        <td align = "right" class="TD_CHITIEU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D9" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D9" class=" <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()"/>
                        </td>                        
                    </tr>                                                        
            </table> 
                                   <div style="height:5px;"></div>  
            <table border="1" class="editDelete" id="tablems01" style="width: 90%"  align="center">
                <tr height="25">                    
                    <th  class="TD_CHITIEU">Thông tin khác</th>                                                                               
                </tr>                                            
                        <tr height="22">                             
                        <td align = "right" class="TD_THUTU">
                            <textarea id="sNgnhan_Clech"  value="<s:property value='D10'/>"
                                              name="lstNt[<s:property  value="%{#rowstatus.index}" />].D10"
                                              style="width: 100%;background-color: #fdf5ce;" rows="3"><s:property value='D10'/></textarea>
                        </td>                                                    
                    </tr>                                                        
            </table> 
            &nbsp;&nbsp;&nbsp; 
            
            <div  id="divMuc" style="width: 85%"  align="center">
                                3. Thông tin xử lý nợ
            </div>
            <table border="1" class="editDelete" id="tablems01" style="width: 45%"  align="center">
                <tr height="25">   
                    <th  class="TD_THUTU">Thu gốc</th> 
                    <th  class="TD_SOKU">Thu lãi</th> 
                    <th  class="TD_THUTU">Xóa gốc</th> 
                    <th  class="TD_SOKU">Xóa lãi</th>                                                                                                                       
                </tr>                  
                        
                        <tr height="22">                          
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D37" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D37"  class="D37 number2" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D38" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D38" class="D38 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td> 
                            
                        
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D39" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D39" class="D39 number2 <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>  
                        
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D40" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D40" class="D40 number2 <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                    </tr>                                                        
            </table>            
            
            <div  id="divMuc" style="width: 90%"  align="center">
                <table  class="editDelete" id="tableBdp02"   align="center">
                    <tr>
                        <td>
                            <input type="checkbox" id ="<s:property  value="%{#rowstatus.index}" />"  class="checkboxdat" name="lstsaveNT_DAT[<s:property  value="%{#rowstatus.index}" />].D1" 
                                           class="D0"/>   Có công văn đề nghị phối hợp 
                            <input type="hidden" value="<s:property  value="D41" />"  id="id_<s:property  value="%{#rowstatus.index}" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D41" value="<s:property  value="D41"/>"/>                        
                        </td>
                    </tr>
                </table>                                
            </div>
                                
            <table border="1" class="editDelete" id="tablems01" style="width: 90%"  align="center">
                <tr height="25">   
                    <th rowspan ="2" class="TD_THUTU">Nhóm nhận nợ</th> 
                    <th rowspan ="2" class="TD_SOKU">Pos chuyển đến</th> 
                    <th colspan ="2" class="TD_SOKU">Loại thu 01</th>
                    <th colspan ="2"  class="TD_SOKU">Loại thu 02</th> 
                    <th colspan ="4"  class="TD_SOKU">Bàn giao</th> 
                    <th colspan ="2" class="TD_SOKU">Xóa nợ</th>                    
                    <th colspan ="2" class="TD_SOKU">Số tiền chưa thu</th>                                                                                                                      
                </tr>  
                <tr>
                    <th class="TD_SOKU">Gốc</th>
                    <th class="TD_SOKU">Lãi</th>
                    <th class="TD_SOKU">Gốc</th>
                    <th class="TD_SOKU">Lãi</th>
                    <th class="TD_SOKU">Trong hạn</th>
                    <th class="TD_SOKU">Quá hạn</th>
                    <th class="TD_SOKU">Khoanh</th>
                    <th class="TD_SOKU">Lãi</th>
                    <th class="TD_SOKU">Gốc</th>
                    <th class="TD_SOKU">Lãi</th>
                    <th class="TD_SOKU">Gốc</th>
                    <th class="TD_SOKU">Lãi</th>
                </tr>
                        
                        <tr height="22">  
                        <td align = "right" class="TD_THUTU">
                            <s:select id="trangthai" name="lstNt[%{#rowstatus.index}].D11"
                                              list="#{'0':'--Nhóm nhận nợ--','1':'Nhận nợ và trả 1 phần','2':'Nhận nợ và tất toán','3':'Nhận nợ nhưng chưa trả nợ','4':'Không tìm được hộ vay','5':'Trường hợp khác'}"
                                              cssStyle="width: 140px; vertical-align: middle; background-color: #fdf5ce;" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D12" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D12"  onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D13" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D13 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td> 
                            
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D14" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D14 number2 <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D15" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D15" class="D15 number2 <s:property value='FONTFORMAT'/>  TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td>                        
                        
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D16" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D16" class="D16 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()"/>
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text"  style="background-color: #fdf5ce;" value="<s:property  value="D17" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D17" class="D17 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text"  style="background-color: #fdf5ce;" value="<s:property  value="D18" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D18" class="D18 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D19" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D19" class="D19 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D20" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D20" class="D20 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                         <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D21" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D21" class="D21 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text" style="background-color: #fdf5ce;" value="<s:property  value="D22" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D22" class="D22 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" />
                        </td>    
                        <td align = "right" class="TD_SOKU">
                            <input type="text"  value="<s:property  value="D23" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D23" class="D23 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                        </td> 
                        <td align = "right" class="TD_SOKU">
                            <input type="text"  value="<s:property  value="D24" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D24" class="D24 <s:property value='FONTFORMAT'/> number2 TEN_KH" onfocus="this.select()" onblur="if(this.value == '') { this.value=0}; autoEvaluate()" readonly="readonly"/>
                        </td>
                        
                    </tr>                                                        
            </table>
                                    <div style="height:5px;"></div>  
            <table border="1" class="editDelete" id="tablems01" style="width: 90%"  align="center">
                <tr height="25">                    
                    <th  class="TD_CHITIEU">Nguyên nhân chưa thu được</th>                                                                               
                </tr>                                            
                        <tr height="22">                             
                        <td align = "right" class="TD_THUTU">
                            <textarea id="sNgnhan_Clech" value="<s:property value='D25'/>"
                                              name="lstNt[<s:property  value="%{#rowstatus.index}" />].D25"
                                              style="width: 100%;background-color: #fdf5ce;" rows="3"><s:property value='D25'/></textarea>
                        </td>                                                    
                    </tr>                                                        
            </table>            
            </s:iterator>            
            <p></p>          
            
            <sj:submit id="%{khoa_nhaptaycn}_save" name="%{khoa_nhaptaycn}_save" value="save" targets="message_suc_err" onBeforeTopics="beforediv_ss"
                           onCompleteTopics="completediv_ss" cssStyle="display: none"/>
        </s:if>
        
        <s:if test="Grade.equalsIgnoreCase('2')">  
            <s:iterator value="#attr.lstParameters" var="para" status="rowstatus">
                <input type="hidden" id="<s:property  value="sKey" />" 
                       name="1_<s:property  value="sKey" />" value="<s:property  value="sDesc"/>"/>
            </s:iterator>
            <div id="divTitle">
                TỔNG HỢP KẾT QUẢ HỘ VAY BỎ ĐI KHỎI ĐỊA PHƯƠNG
            </div>
                &nbsp;
            <s:hidden name="khoa_nhaptaycn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablems01" style="width: 90%" align="center">
                <tr>
                    <th class="TD_SOTIEN">Mã PGD</th>
                    <th class="TD_SOTK">Tên PGD</th> 
                    <th class="TD_THUTU">Số món vay</th>                                                             
                    <th class="TD_THUTU">Tổng nợ</th>                       
                    <th  class="TD_THUTU">Gốc</th>      
                    <th  class="TD_THUTU">Lãi</th> 
                    <th  class="TD_THUTU">Thu nợ loại 1</th> 
                    <th  class="TD_THUTU">Thu nợ loại 2</th> 
                    <th  class="TD_THUTU">Xóa nợ</th> 
                    <th  class="TD_THUTU">Nợ chưa thu</th> 
                </tr>
                              
                <s:iterator value="#attr.lstNt" var="modelView" status="rowstatus">                    
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_SOTIEN">
                            <input type="text" value="<s:property  value="MAPGD" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].MAPGD" readonly/>
                        </td>
                        
                        <td align = "right" class= "<s:property value='FONTFORMAT'/> TD_SOTK">
                            <input type="text" value="<s:property  value="TEN" />"  class="<s:property value='FONTFORMAT'/> TEN_KH" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].TEN" readonly/>
                        </td>
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="D1" />" 
                                       name="lstNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"   
                                       readonly="readonly"/>                                  
                        </td>                                               
                        
                        <td  align="right" class="TD_THUTU">    
                            <input type="text" value="<s:property  value="D2" />" 
                                       name="lstNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D1 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"   
                                       readonly="readonly"/>                                  
                        </td> 
                        
                        <td align = "right" class="TD_THUTU">
                            <input type="text" value="<s:property  value="D3" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D2 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly/>
                        </td>  
                        <td align = "right" class="TD_THUTU">
                            <input type="text" value="<s:property  value="D4" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D2 number2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                   onblur="autoEvaluate()" readonly/>
                        </td> 
                                              
                        <td align = "center" class="TD_THUTU">
                            <input type="text" value="<s:property  value="D10" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D10" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td>  
                        <td align = "center" class="TD_THUTU">
                            <input type="text" value="<s:property  value="D11" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D11" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td>
                        <td align = "center" class="TD_THUTU">
                            <input type="text" value="<s:property  value="D13" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D13" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td>
                        <td align = "center" class="TD_THUTU">
                            <input type="text" value="<s:property  value="D14" />" 
                                   name="lstNt[<s:property  value="%{#rowstatus.index}" />].D14" class="D0  <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()"
                                    readonly/>
                        </td>
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>    
        </s:if>     
        </s:form>
            
        <s:form action="id_%{khoa_nhaptaycn}.action" id="paginationForm">
        <s:iterator value="poscd" status="row">
            <s:hidden name="poscd[%{#row.index}]" />
        </s:iterator>
        
        <%--<%@ include file="/nhaptay-cn/pagination.jsp" %>--%>
        <sj:submit value="submit" id="idSubmit" name="idSubmit" targets="divExportReport" cssStyle="display: none" 
                   onBeforeTopics="batdauloaddata" onCompleteTopics="hoanthanhloaddata"/>
    </s:form>    
        <div id="luu_thanhcong"></div>
    </body>
    <script>
            initTable();
        </script>
</html>
