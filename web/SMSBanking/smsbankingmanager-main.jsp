<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>


<s:head/>
<sj:head/>

<style>
    .normal_style {
        font-family: Arial;
        font-size: 12pt;
        font-weight: bold;
    }
    input.readonly {
        background: #99ffff;
    }
</style>

<s:form id="smsbanking_form" theme="simple">
    <div style="padding-left: 5px;  font-family:Arial;font-size: 12pt; ">
        <h3 style="text-align: center; vertical-align: middle;"> 
            Quản lý khách hàng đăng ký dịch vụ SMSBanking</h3>

        <s:url id="submitDataUrl" action="SMSListCustomer.action">                                            
        </s:url>



        <p>Truy vấn thông tin Pos/Số Cif:  
            <input type="text" name="keyStr"  placeholder="Nhập số Cif/Pos"/>
            &nbsp; <u>  

            <sj:a id="submitData_id"  
                  href="%{submitDataUrl}"                                                                                                
                  formIds="smsbanking_form"  
                  onBeforeTopics="before-next"
                  onCompleteTopics="after-next"
                  targets="messageDiv"
                  button="false"                                                                                 
                  theme="simple">Tìm kiếm</sj:a> </p>



                <script>
                    //            function submitClick() {                
                    //                $("#submitData_id").trigger("click");                
                    //            }

                    $.subscribe('before-next',
                            function(event, data) {
                                $("#messageDiv").empty();
                                $("#messageDiv").hide();
                                $("#loadingImageDiv").show();
                            });

                    $.subscribe('after-next',
                            function(event, data) {
                                com.smsbanking.onTableLoad();
                                $("#messageDiv").show();
                                $("#loadingImageDiv").hide();
                            });

                    if (!com)
                        var com = {};
                    com.smsbanking = {
                        onTableLoad: function() {
                            // Gets called when the data loads

                            $("#messageDiv .pagelinks a").each(function() {
                                $(this).click(function() {
                                    var link = $(this).attr("href");
                                    $("#messageDiv").load(link, {},
                                            com.smsbanking.onTableLoad);
                                    return false;
                                });
                            });

                            var pageCount = 0;

                            $("#messageDiv .pagelinks a").each(function() {
                                pageCount++;
                            });

                            if (pageCount === 0) {
                                $("#messageDiv .pagelinks strong").each(function() {
                                    var htmlString = $(this).html();
                                    $(this).text("[Phân trang] " + htmlString);
                                });
                            }
                        }
                    };

                </script>
                <!--        <hr/>-->
        </div>
        <div  style="float: left; width: 100%; font-family: Arial;font-size: 8pt;" >          
            <div id="loadingImageDiv" style="display: none;">
                <img id="loadingImage" src='img/loading.gif' border='0' >
            </div>            
            <div id="messageDiv"></div>                  
        </div>

</s:form>

