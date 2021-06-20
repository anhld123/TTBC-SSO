<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head jqueryui="false" jquerytheme="simple"/>

<s:form id="balance_adjust_form0" theme="simple">
    <p class="auto-style1"><span class="auto-style4">Tài khoản: 


            <s:url var="buildComboUrl_x" 
                   action="Acc_Build_Acc_Combo"></s:url>

            <sj:select href="%{buildComboUrl_x}" 
                       name="adjustAcc.tk"
                       id="account_ID"
                       list="accountList" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="false"                                  
                       theme="simple"     
                       ></sj:select> 

            <s:url id="fetchDataUrlID" 
                   value="Acc_Fetch_Data.action">                                
            </s:url>
            <sj:a 
                id="fetchDataBtnID"
                href="%{fetchDataUrlID}"
                formIds="balance_adjust_form0"
                targets="accountViewDIV"           
                onBeforeTopics="clearData"
                onCompleteTopics="showData"            
                button="false">[2]Tải dữ liệu </font> </sj:a>     
                <!--            <a href="#" onclick="handleClick();" ></a>-->
                <script>
                    function handleClick() {
                        $("#fetchDataBtnID").trigger("click");
                    }

                    $.subscribe('clearData', function(event, data) {
                        $("#accountViewDIV").empty();
                        $("#accountViewDIV").hide();
                    });

                    $.subscribe('showData', function(event, data) {
                        $("#accountViewDIV").show(); //.slideDown('slow');
                    });
                </script>
            </span>
        </p>
        <hr/>        

    <s:hidden name="acc_type"  id="acc_type_ID0"/>

    <s:hidden name="adjustAcc.mapos"
              />
    <s:hidden name="adjustAcc.ngaybc"
              />
    <s:hidden name="adjustAcc.kybc"
              />
    <s:hidden name="adjustAcc.tonghop"
              />

    <s:hidden name="userName"
              />

    <div id="accountViewDIV"></div>
</s:form>
<script>
    $(function() {
        $("#fetchDataBtnID").trigger("click");
    });
</script>