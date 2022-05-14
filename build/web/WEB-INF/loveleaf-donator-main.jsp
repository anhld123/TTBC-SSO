<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@taglib prefix="s" uri="/struts-tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>
<s:head/>
<sj:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>

<script>
    //TRUNGNT88: 15-May-14       

    function confirm_0() {
        var r = confirm("Khi chọn tạo lại toàn bộ dữ liệu đã nhập cho ngày này sẽ bị xoá.Bạn có chắc chắn ? (OK-tiếp tục,Cancel-Huỷ)");
        if (r === false) {
            var object_0 = document.getElementById('gendata_FLG_ID');
            object_0.checked = false;
            return false;
        }
    }

    //Desc: khi thay doi selectbox thi goi den su kien click
    $(function() {
        $('#selectedrptDate').change(function() {
            $("#loadDonator").trigger("click");
        });
        //Khi load xong
        $('#selectedrptDate').ready(function() {
            $("#loadDonator").trigger("click");
        });
    });

    $.subscribe('before-next',
            function(event, data) {
                $("#divListDonator").empty();
                $("#divListDonator").hide();
                $("#loadingImageDiv").show();
            });



    $.subscribe('after-next', function(event, data) {
        loveleaf_com.loadpage.onTableLoad();
        $("#divListDonator").show();
        $("#loadingImageDiv").hide();
    });

</script>


<body>
    <div style="padding-left: 5px;">            
        <s:form id="loveleaf_main_form" 
                action="loveleaf_list_donator.action"
                theme="simple">
            <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; "> 
            Ngày báo cáo: 
            </font>
            <sj:datepicker name="tran_dt" value=""                                        
                           onblur="validatedate(this.value)"
                           placeholder="DD/MM/YYYY" changeYear="true" 
                           changeMonth="true" displayFormat="dd/mm/yy"
                           id="selectedrptDate" size="8"/>
            <script>
                var lj_curDate = new Date();
                var lj_setDate = (lj_curDate.getDate()) + "/" +
                        (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();
                document.getElementById("selectedrptDate").value = lj_setDate;
            </script>
            &nbsp;&nbsp;
            <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; ">
            Kỳ báo cáo: 
            </font>
            <s:url var="buildTermComboUrl" 
                   action="loveleaf_build_period_combo"></s:url>
            <sj:select href="%{buildTermComboUrl}" 
                       name="period"
                       id="period_id"
                       list="periods" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="false"                                             
                       theme="simple"     
                       ></sj:select>    
                &nbsp;&nbsp;
                <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; ">
            Chương trình: 
            </font>
            <s:url var="buildProgramComboUrl" 
                   action="loveleaf_build_program_combo"></s:url>
            <sj:select href="%{buildProgramComboUrl}" 
                       name="program"
                       id="program_id"
                       list="programs" 
                       listKey="sKey"
                       listValue="sDesc"
                       emptyOption="false"                                             
                       theme="simple"     
                       ></sj:select>    
                &nbsp;&nbsp;
                <input type="checkbox" name="gendata_FLG" value="Y" 
                       id="gendata_FLG_ID"
                       onclick="confirm_0();">
                <font style="color: red; font: 13px Arial, Helvetica, sans-serif; "> Tạo lại DL</font>
                </input>
                &nbsp;&nbsp;
                <u> <sj:a id="loadDonator" 
                  formIds="loveleaf_main_form" 
                  targets="divListDonator" 
                  indicator="loadingImage_next" 
                  href="#"                               
                  onBeforeTopics="before-next"                                                         
                  onCompleteTopics="after-next"
                  cssClass="metroButtonStyle"
                  button="false">                                                          
                    <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; "> 
                    <b>                                   
                        Truy vấn
                    </b>
                    </font></sj:a>      </u>                        

        </s:form>

        <hr/>           
        <sj:div id="divListDonator"></sj:div>
            <center>
                <div id="loadingImageDiv" style="display: none;">
                    <img id="loadingImage" src='img/ajax-loader_1.gif' 
                         style="max-height: 80px; max-width: 80px;"
                         border='0' >
                </div>
            </center>
            <script>
                if (!loveleaf_com)
                    var loveleaf_com = {};
                loveleaf_com.loadpage = {
                    onTableLoad: function() {
                        // Gets called when the data loads
                        $("#search_table th.sortable").each(function() {
                            $(this).click(function() {
                                var link = $(this).find("a").attr("href");
                                $("#divListDonator").load(link, {},
                                        loveleaf_com.loadpage.onTableLoad);
                                return false;
                            });
                        });

                        $("#divListDonator .pagelinks a").each(function() {
                            $(this).click(function() {
                                var link = $(this).attr("href");
                                var rplink = link.replace("gennew=Y", "gennew=N");
                                $("#divListDonator").load(rplink, {},
                                        loveleaf_com.loadpage.onTableLoad);
                                return false;
                            });
                        });

                        //                                    $("#divListDonator .pagelinks strong").each(function () {
                        //                                        var htmlString = $(this).html();
                        //                                        $(this).text("trang " + htmlString);
                        //                                    });
                    }
                };
            </script>
        </div>
    <s:hidden name="username" id="role_ID"  />                         
    <s:hidden name="permit" id="permit_ID"  />                         
</body>