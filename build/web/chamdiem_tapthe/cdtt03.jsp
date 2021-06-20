<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<s:if test="!RULEUSER.equalsIgnoreCase('9') && Grade.equalsIgnoreCase('2')">
    <table>
        <tr>
            <td>
                TW
            </td>
            <td>
                <input type="text" id="D11_<s:property value='MA'/>" value="<s:property value='D11'/>" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D11" 
                       onblur="if (this.value == '') {
                                                           this.value = 0
                                                       }
                                                       ;" class="number2 TEN_KH" />
            </td>
        </tr>
        <tr>
            <td>
                ĐP
            </td>
            <td>
                <input type="text" style="text-align: right;" id="D6_<s:property value='MA'/>" value="<s:property value='D8'/>" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D8" onblur="if (this.value == '') {
                                   this.value = 0
                               }
                               ;"  
                       class="number2 TEN_KH"/>
            </td>
        </tr>
    </table>
</s:if>
<s:if test="RULEUSER.equalsIgnoreCase('9') && Grade.equalsIgnoreCase('2')">
    <table>
        <tr>
            <td>
                TW
            </td>
            <td>
                <input type="text" style="text-align: right;" id="D4_<s:property value='MA'/>" value="<s:property value='D4'/>" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" onblur="if (this.value == '') {
                                   this.value = 0
                               }
                               ;
                               evaluateSum('CHAMDIEMTT_001', 'D4')"  
                       class="number2 TEN_KH"/>
            </td>
        </tr>
        <tr>
            <td>
                ĐP
            </td>
            <td>
                <input type="text" style="text-align: right;" id="D6_<s:property value='MA'/>" value="<s:property value='D6'/>" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" onblur="if (this.value == '') {
                                   this.value = 0
                               }
                               ;"  
                       class="number2 TEN_KH"/>
            </td>
        </tr>
    </table>
</s:if>

<s:if test="Grade.equalsIgnoreCase('1')">
    <table>
        <tr>
            <td>
                TW
            </td>
            <td>
                <input type="text" style="text-align: right;" id="D4_<s:property value='MA'/>" value="<s:property value='D4'/>" 
                       name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D4" onblur="if (this.value == '') {
                                   this.value = 0
                               }
                               ;
                               evaluateSum('CHAMDIEMTT_001', 'D4')"  
                       class="number2 TEN_KH"/>
            </td>
        </tr>
        <tr>
            <td>
                ĐP
            </td>
            <td>
                <s:if test="MA.equalsIgnoreCase('CDTT03')">
                    <input type="text" style="text-align: right;" id="D6_<s:property value='MA'/>" value="<s:property value='D6'/>" 
                           name="lstDulieuNt[<s:property  value="%{#rowstatus.index}" />].D6" onblur="if (this.value == '') {
                                       this.value = 0
                                   }
                                   ;"  
                           class="number2 TEN_KH"/>
                </s:if> 
            </td>
        </tr>
    </table>
</s:if>


