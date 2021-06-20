<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<sj:head jqueryui="false" jquerytheme="simple"/>

<s:head/>
<sj:head/>



<style type="text/css">
    .auto-style1 {
        margin-top: 0;
        margin-bottom: 0;
        font-family: Arial, Helvetica, sans-serif;
        padding-top: 0px;
    }
    .auto-style2 {
        text-align: right;        
    }
    .auto-style3 {
        font-family: Arial, Helvetica, sans-serif;
        font-size: 11pt;        
    }
    .auto-style4 {
        font-size: 11pt;
    }
    .auto-style5 {
        font-family: Arial, Helvetica, sans-serif;
        font-size: large;
    }


    .footer {
        position: absolute;
        right: 0;
        bottom: 0;
        left: 0;
        padding: 1rem;
/*        background-color: #efefef;*/
/*        text-align: center;*/
    }
</style>



<h2 style="color: rgb(0, 0, 0); font-style: normal; font-variant: normal; letter-spacing: normal; line-height: normal; orphans: auto; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 1; word-spacing: 0px; -webkit-text-stroke-width: 0px;" class="auto-style5">
    Điều chỉnh tài khoản cân đối: </h2> 
    <s:form id="balance_adjust_form" theme="simple">

    <table style="width: 100%">
        <tr>
            <td class="auto-style3" >Đơn vị: 

                <s:url var="buildComboUrl" 
                       action="Acc_Build_Combo"></s:url>

                <sj:select href="%{buildComboUrl}" 
                           name="pos_cd"
                           id="poscd_ID"
                           list="posList" 
                           listKey="sKey"
                           listValue="sDesc"
                           emptyOption="false"                                      
                           theme="simple"     
                           ></sj:select>  
                </td>
                <td class="auto-style3" >Tổng hợp:
                    <input type="checkbox" name="conflag" />
                </td>
                <td class="auto-style3" >Loại TK:
                    <input checked="checked" name="accTypeRadio" type="radio" value="1"
                           onchange="handleChange();"/> GL
                    <input name="accTypeRadio" type="radio" value="2"
                           onchange="handleChange();"/> SBV
                <s:hidden name="acc_type" id="acc_type_ID"/>
                <script>
                    function handleChange() {
                        var accTypeRadioVal
                                = $("input:radio[name ='accTypeRadio']:checked").val();
                        $("#acc_type_ID").val(accTypeRadioVal);
                        $("#acc_type_ID0").val(accTypeRadioVal);
                        alert('aaa');
                    }
                </script>
            </td>
            <td class="auto-style3">Kỳ BC:
                <s:url var="buildComboUrl_0" 
                       action="buildPeriodCombo"></s:url>
                <sj:select href="%{buildComboUrl_0}" 
                           name="period"
                           id="selectedPeriod"
                           list="periodList" 
                           listKey="sKey"
                           listValue="sDesc"
                           emptyOption="false"                                      
                           theme="simple"     
                           ></sj:select>  
                </td>		
                <td class="auto-style3">Ngày báo cáo:
                <sj:datepicker name="reportDate" 
                               value=""  
                               placeholder="DD/MM/YYYY" 
                               changeYear="true" 
                               changeMonth="true" 
                               displayFormat="dd/mm/yy"
                               id="selectedrptDate"
                               cssClass="NGAY_SL"
                               size="6"/>
                <script>
                    var lj_curDate = new Date();
                    var lj_setDate = (lj_curDate.getDate() - 1) + "/" +
                            (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();
                    document.getElementById("selectedrptDate").value = lj_setDate;
                </script>    
            </td>
            <td class="auto-style3">
                <s:url id="listPosAccID" 
                       value="Acc_ListAcc_Pos.action">                                
                </s:url>
                <sj:a 
                    id="listBtnID"
                    href="%{listPosAccID}"
                    formIds="balance_adjust_form"
                    targets="PosAccDIV"           
                    onBeforeTopics="before-next"
                    onCompleteTopics="after-next"
                    button="false">[1]Truy vấn</sj:a>     
                    <!--                    <a href="#" onclick="onClick(); return true;">Truy vấn</a>-->
                    <script>
//                        function onClick() {
//                            var accTypeRadioVal = $("input:radio[name ='accTypeRadio']:checked").val();
//                            alert(accTypeRadioVal);
//                            $("#acc_type_ID").val(accTypeRadioVal);
//                            $("#listBtnID").trigger("click");                            
//                        }

                        $.subscribe('before-next', function(event, data) {
//                            var accTypeRadioVal 
//                                    = $("input:radio[name ='accTypeRadio']:checked").val();                            
//                            $("#acc_type_ID").val(accTypeRadioVal);
//                            $("#acc_type_ID0").val(accTypeRadioVal);
//                            alert($("#acc_type_ID").val());
                            //alert('a');
                            $("#PosAccDIV").empty();
                            $("#PosAccDIV").hide();
                        });

                        $.subscribe('after-next', function(event, data) {
                            $("#PosAccDIV").show(); //.slideDown('slow');
                        });
                    </script>
                </td>                    
            </tr>

        </table>
        <hr />

        <div id="PosAccDIV"></div>

        <div id="bottom_div" class="footer">
            Người cập nhật: <s:textfield name="userName" readonly="true" cssStyle="border:none;outline:none; color: #0066cc;"/> 
    </div>

</s:form>

<script>
    $(function() {
        $("#listBtnID").trigger("click");
    });

</script>