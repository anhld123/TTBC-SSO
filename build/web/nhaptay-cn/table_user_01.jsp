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
        }
        .BOLD
            {
                font-weight: bold;
                font-size: 13px;
                width: 95%;
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
                $(".TD_THUTU").css({"width": "30px"});
                $(".TD_CHITIEU").css({"width": "250px"});
                $(".TD_CAPKT").css({"width": "200px"});
                $(".TD_SOTIEN").css({"width": "155px"});                                
            });
            $('.TEN_KH').focus(function () {
                $(this).closest('tr').addClass('highlight_row');
            });
            $('.TEN_KH').blur(function () {
                $(this).closest('tr').removeClass('highlight_row');
            });
            
            function initSelectOption()
            {
                var pos_cd = '';
                var i = document.id_sv_USER_001.elements.length;                
                for (var k = 0; k < i; k++)
                {
                    if (document.id_sv_USER_001.elements[k].name.indexOf('D4')>1)
                    {   
                        var id=document.id_sv_USER_001.elements[k].id;
                        if(id != null && id != '')
                        {
//                            alert(id);
                            var subid = id.substr(1, 1);
//                            alert(subid);
                            document.id_sv_USER_001.elements[k].value=subid;
                        }
                                                                      
                    }
                    if (document.id_sv_USER_001.elements[k].name.indexOf('D6')>1)
                    {   
                        var id=document.id_sv_USER_001.elements[k].id;
                        if(id != null && id != '')
                        {
//                            alert(id);
                            var subid = id.substr(1, 1);
//                            alert(subid);
                            document.id_sv_USER_001.elements[k].value=subid;
                        }
                                                                      
                    }
                    
                }
            }
        
        </script>
        <script>
            function checkleng(d)
            {
                if(d.value.length != 9 && d.value.length !=12)
                {
                    alert('Độ dài cmnd phải bằng 9 hoặc 12');
                    d.value = '';
                    return;
                }
                    
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
                BỔ SUNG THÔNG TIN NGƯỜI DÙNG
            </div>
                &nbsp;
            <s:hidden name="khoa_nhaptaycn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablems01" style="width: 75%" align="center">
                <tr height="35">
                    <!--<th class="TD_SOTIEN">Mã người dùng</th>-->                    
                    <th class="TD_CHITIEU">Tên người dùng</th>                                            
                    <th class="TD_SOTIEN">Số CMND</th>                         
                    <th  class="TD_CHITIEU">Chức vụ</th>
                    <th  class="TD_CHITIEU">Nghiệp vụ</th>
                </tr>
                            
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">       
                    <s:if test="NHAPTAY.equalsIgnoreCase('N')">
                        <tr>
<!--                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" 
                                       readonly="readonly"/>                                  
                        </td>                                               -->
                        
                        <td  align="right" class="TD_CHITIEU">    
                            <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" 
                                       readonly="readonly"/>     
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>   
                        </td> 
                        
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" 
                                       onblur="checkleng(this)"/>                                  
                        </td> 
                        
                        <td  align="right" class="TD_CHITIEU">    
                            <input type="text" value="<s:property  value="D4" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" 
                                       readonly="readonly"/>                                  
                        </td> 
                                                                      
                        </tr>  
                    </s:if>
                    <s:else>
<!--                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                        </td>                                               -->
                        
                        <td  align="right" class="TD_CHITIEU">    
                            <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" readonly="readonly"/>                                  
                            <input type="hidden" id="id_<s:property  value="%{#rowstatus.index}" />" 
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" value="<s:property  value="D1"/>"/>  
                        </td> 
                        
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" onblur="checkleng(this)"/>                                  
                        </td> 
                        
                        <td align = "center" class="TD_CHITIEU">
                            <select name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" 
                                            id="<s:property value='NHAPTAY'/><s:property value='D4'/>" 
                                            style="width:300px;">  
                                    <option value="0" selected>--Chức vụ--</option>
                                    <option value="1">1 - Cán bộ</option>
                                    <option value="2">2 - Tổ trưởng</option>
                                    <option value="3">3 - Phó Giám đốc PGD</option>
                                    <option value="4">4 - Giám đốc PGD</option>
                                    <option value="5">5 - Thủ quỹ</option>
                                    <option value="6">6 - Giám đốc chi nhánh</option>                          
                                    <option value="7">7 - Phó giám đốc chi nhánh</option>                          
                                    <option value="8">8 - Trưởng phòng</option>                          
                                    <option value="9">9 - Phó Phòng</option>                          
                            </select>
                    </td>
                    
                    <td align = "center" class="TD_CHITIEU">
                            <select name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" 
                                            id="<s:property value='NHAPTAY'/><s:property value='D6'/>" 
                                            style="width:300px;">  
                                    <option value="0" selected>--Nghiệp vụ--</option>
                                    <option value="T">T - Tín dung</option>
                                    <option value="K">K - Kế toán</option>
                                    <option value="Q">Q - Quỹ</option>                                                             
                            </select>
                    </td>
                    </s:else>    
                    </tr>
                    
                </s:iterator>
            </table>
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
                TỔNG HỢP BỔ SUNG THÔNG TIN NGƯỜI DÙNG
            </div>
                &nbsp;
            <s:hidden name="khoa_nhaptaycn"/>
<!--            <div id="divDonvitinh">
                Đơn vị tính: Đồng
            </div>-->
            <table border="1" class="editDelete" id="tablems01" style="width: 90%" align="center">
                <tr height="35">
                    <!--<th class="TD_SOTIEN">Mã người dùng</th>-->                    
                    <th class="TD_CHITIEU">Tên người dùng</th>                                            
                    <th class="TD_SOTIEN">Số CMND</th>                         
                    <th  class="TD_CHITIEU">Chức vụ</th>
                </tr>
                              
                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">                    
<!--                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D1" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" class="D1 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" 
                                       readonly="readonly"/>                                  
                        </td>                                               -->
                        
                        <td  align="right" class="TD_CHITIEU">    
                            <input type="text" value="<s:property  value="D2" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D2 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" 
                                       readonly="readonly"/>                                  
                        </td> 
                        
                        <td  align="right" class="TD_SOTIEN">    
                            <input type="text" value="<s:property  value="D3" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D3" class="D3 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" 
                                       readonly="readonly"/>                                  
                        </td> 
                        
                        <td  align="right" class="TD_CHITIEU">    
                            <input type="text" value="<s:property  value="D4" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" class="D4 <s:property value='FONTFORMAT'/> TEN_KH" onfocus="this.select()" 
                                       readonly="readonly"/>                                  
                        </td>                                                                    
                    </tr>                    
                    
                </s:iterator>
            </table>
            <p></p>    
        </s:if>   
        <s:if test="Grade.equalsIgnoreCase('3')">
            <div id="divTitle">
                        TRẠNG THÁI GỬI SỐ LIỆU CỦA CHI NHÁNH
                    </div>
                    <p></p>
                    <table border="1" class="editDelete" id="tablepl01" align="center">
                        <tr>
<!--                            <th align = "center"  style="width: 30px;">
                                <s:checkbox id ="allCheck" name="allCheck"/></th>-->
                            <th align = "center"  style="width: 50px;">Mã PGD</th>
                            <th style="width: 100px;">Tên PGD</th>
                            <th style="width: 60px;">Ngày gửi</th>
                            <th style="width: 50px;">User gửi</th>
                            <th style="width: 90px;">Trạng thái xử lý</th>
                            <th style="width: 90px;">Trạng thái gửi</th>
                        </tr>
                    <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                
                        <s:if test="D1.equalsIgnoreCase('true')">
                            <tr style="text-align: center; color: #0000FF" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>-->
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:if>
                        <s:else>
                            <tr style="text-align: center; color: red" onmouseover="mover(this);"  onmouseout="mout(this);">
                                <!--<td align = "center"  style="width: 20px;"><s:checkbox id ="%{#rowstatus.index}" cssClass="checkbox1" name="poscd" fieldValue="%{D3}"/></td>-->
                                <td align = "center"  style="width: 50px;"><s:property  value="D3" /></td>
                                <td align = "left" style="width: 100px;"><s:property  value="D4" /></td>
                                <td style="width: 60px;"><s:property  value="D8" /></td>
                                <td style="width: 50px;"><s:property  value="D7" /></td>
                                <td style="width: 90px;"><s:property  value="D9" /></td>
                                <td style="width: 90px;"><s:property  value="D11" /></td>
                            </tr>
                        </s:else>
                    </s:iterator>
        </s:if>    
        </s:form>
        <div id="luu_thanhcong"></div>
        <script>
            initSelectOption();
        </script>
    </body>
</html>
