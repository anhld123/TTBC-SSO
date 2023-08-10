<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<table border="1px" id="tableKtnb">
                <tr class="tbhead">
                    <th class="TD_BUTTON1">MS</th>
                    <th class="TD_THOIGIAN">Nội dung</th>
                    <th class="TD_TENKH123">Đơn vị tính</th>
                    <th class="SOKU1">Kết quả</th>
                    <th class="SOKU">Ghi chú</th>
                </tr>

                <tr class="tbhead">
                    <th>(1)</th>
                    <th>(2)</th>
                    <th>(3)</th>
                    <th>(4)</th>
                    <th>(5)</th>
                </tr>

                <s:iterator value="#attr.lstDulieuNt" var="modelView" status="rowstatus">
                    <tr height="cscontent">    
                        <s:if test="D7.equalsIgnoreCase('Y')">                                     

                            <td>
                                <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TD_MAPGD" onfocus="this.select()"
                                       onblur="if (this.value == '')
                                                   ;" readonly="true" style="background: #E7DCDA !important; font-weight: bold; "/>
                                <input type="hidden" value="<s:property  value="THUTU" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                                <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                                <input type="hidden" value="<s:property  value="D7" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>"/>  
                                <input type="hidden" value="<s:property  value="D8" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
                                <input type="hidden" value="<s:property  value="D9" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>"/>
                            </td>
                            <td>
                                <input type="text"   value="<s:property  value="D1" />"  style="background: #E7DCDA !important;  font-weight: bold;" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                       class="TD_MAPGD Bold"
                                       onfocus="this.select();" /> 
                            </td>                                  
                            <td>
                                <input type="text" value="<s:property  value="D2" />" style="background: #E7DCDA !important;"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TD_MAPGD" onfocus="this.select()"
                                       onblur="if (this.value == '')
                                                   ;" readonly="true"/>
                            </td>                                  
                            <td>
                                <input type="text" value="<s:property  value="D5" />" style="background: #E7DCDA !important;" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number" onfocus="this.select()"
                                       onblur="if (this.value == '')
                                                   ;"/>
                            </td>                                                                                                

                            <td>
                                <input type="text" value="<s:property  value="D10" />" style="background: #E7DCDA !important;" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                       onblur="if (this.value == '')
                                                   ;"/>
                            </td>

                        </tr>
                    </s:if>
                    <s:if test="D7.equalsIgnoreCase('N')">                                     

                        <td>
                            <input type="text" value="<s:property  value="TT_HIENTHI" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TT_HIENTHI" class="D0 TD_MAPGD" onfocus="this.select()"
                                   onblur="if (this.value == '')
                                               ;" readonly="true"/>
                            <input type="hidden" value="<s:property  value="THUTU" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].THUTU" value="<s:property  value="THUTU"/>"/>  
                            <input type="hidden" value="<s:property  value="NHAPTAY" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].NHAPTAY" value="<s:property  value="NHAPTAY"/>"/>
                            <input type="hidden" value="<s:property  value="D7" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D7" value="<s:property  value="D7"/>"/>  
                            <input type="hidden" value="<s:property  value="D8" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" value="<s:property  value="D8"/>"/>
                            <input type="hidden" value="<s:property  value="D9" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D9" value="<s:property  value="D9"/>"/>
                            <input type="hidden" value="<s:property  value="TEN" />"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].TEN" value="<s:property  value="TEN"/>"/>

                        </td>
                        <td>
                            <input type="text"   value="<s:property  value="D1" />"  readonly="true"
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D1" 
                                   class="TD_MAPGD"
                                   onfocus="this.select();" /> 
                        </td>                                  
                        <td>
                            <input type="text" value="<s:property  value="D2" />" 
                                   name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D2" class="D0 TD_MAPGD" onfocus="this.select()"
                                   onblur="if (this.value == '')
                                               ;" readonly="true"/>
                        </td> 
                        <s:if test="D8.equalsIgnoreCase('Y') && D9.equalsIgnoreCase('N')">     
                            <td>
                                <input type="text" value="<s:property  value="D5" />" style="background: #df8505 !important;" 
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number2" onfocus="this.select();
                                       "/> 
                            </td>
                        </s:if>
                        <s:if test="D8.equalsIgnoreCase('N') && D9.equalsIgnoreCase('N')"> 

                            <s:if test="THUTU.toString().equalsIgnoreCase('54') ||THUTU.toString().equalsIgnoreCase('53')"> 
                                <td>
                                    <input type="text" value="<s:property  value="D5" />"  
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"
                                           class="D0 SOKU number amount" onfocus="this.select()"
                                           onblur="if (this.value == '') {
                                                       this.value = 0
                                                   }
                                                   ;
                                                   findTotal(this)"/>
                                </td>
                            </s:if>
                            <s:if test="THUTU.toString().equalsIgnoreCase('52')"> 
                                <td>
                                    <input type="text" value="<s:property  value="D5" />" id="totalordercost"
                                           style="background: #E7DCDA !important;" readonly="true"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5"
                                           class="D0 SOKU number " onfocus="this.select()"
                                           onblur="if (this.value == '')
                                                       ;"/>
                                </td>
                            </s:if>
                            <s:if test="!THUTU.toString().equalsIgnoreCase('54') &&!THUTU.toString().equalsIgnoreCase('53')&& !THUTU.toString().equalsIgnoreCase('52')"> 
                                <td>
                                    <input type="text" value="<s:property  value="D5" />" id="TT_<s:property  value="THUTU" />"
                                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU number" onfocus="this.select()"
                                           onblur="if (this.value == '')
                                                       ;"/>
                                </td>
                            </s:if>
                        </s:if>
                        <s:if test="D8.equalsIgnoreCase('N') && D9.equalsIgnoreCase('Y')">     
                            <td>
                                <input type="text" value="<s:property  value="D5" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D5" class="D0 SOKU" onfocus="this.select()"
                                       onblur="if (this.value == 0)
                                                   ;"/>
                            </td>  
                        </s:if>

                        <s:if test="D8.equalsIgnoreCase('Y')">
                            <td>
                                <input type="text" value="<s:property  value="D10" />" placeholder="Trường bắt buộc phải nhập/ không cho nhập số 0" readonly="true"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                       onblur="if (this.value == '')
                                                   ;"/>
                            </td>
                        </s:if>
                        <s:if test="D8.equalsIgnoreCase('N')">
                            <td>
                                <input type="text" value="<s:property  value="D10" />"
                                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D10" class="SOKU" onfocus="this.select()"
                                       onblur="if (this.value == '')
                                                   ;"/>
                            </td>
                        </s:if>
            </tr>
        </s:if>
    </s:iterator>

</table>            

<script>
    var max_row = 0;
    $(document).ready(function () {
        $('input.number').css({"text-align": "right"});
        $('input.number2').css({"text-align": "right"});
        $('.D0').css({"text-align": "center"});
        $('.number').number(true, 0);
//            //Cac truong bang so --> se co so truong = 0
        $('.number2').number(true, 0);
        $(".SOKU").css({"width": "99%%"});
        $(".SOKU1").css({"width": "50px"});
        $(".TD_CHECKBOX").css({"width": "38px"});
        $(".TD_SOKU").css({"width": "80px"});
        $(".TD_TENKH123").css({"width": "150px"});
        $(".TD_TENTS").css({"width": "190px"});
        $(".TD_SOTK").css({"width": "105px"});
        $(".TD_MAKH").css({"width": "60px"});
        $(".TD_THOIGIAN").css({"width": "auto"});
        $(".TD_MAPGD").css({"width": "99%"});
        $(".TD_BUTTON1").css({"width": "40px"});
        $(".TD_SOTIEN").css({"width": "100px"});
        $(".TEN_KH").css({"width": "50%"});
        $(".TEN_KH1").css({"width": "30%"});
    });
    $('.TEN_KH').focus(function () {
        $(this).closest('tr').addClass('highlight_row');
    });
    $('.TEN_KH').blur(function () {
        $(this).closest('tr').removeClass('highlight_row');
    });

    $(document).ready(function () {       
        $(".KT_STT_HT").css({"width": "35px"});
        $(".KT_DT").css({"width": "240px"});

        //An di cac cot chuc nang
        $('.hideColumn').hide();

        //Cac truong bang so --> se co so truong = 0
        $('.number').number(true, 0);

        //Cac truong bang so --> se co so truong = 0
        $('.number2').number(true, 0);
    });

</script>       