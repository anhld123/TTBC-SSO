<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sj" uri="/struts-jquery-tags"%>
<%@ taglib prefix="sjt" uri="/struts-jquery-tree-tags"%>
<%@ taglib prefix="sjg" uri="/struts-jquery-grid-tags"%>

<s:head/>
<sj:head/>

<script src="js/JavaScriptUtil.js"></script>
<script src="js/InputMask.js"></script>
<script src="js/Parsers.js"></script>
<script src="js/Checkdate.js"></script>

<script>
    //TRUNGNT88: 15-May-14       

    //Desc: khi thay doi selectbox thi goi den su kien click
    $(function() {
        //Khi thay doi
        $('#module_id').change(function() {
            $("#loadParameter").trigger("click");
        });
        //Khi load xong
        $('#module_id').ready(function() {
            $("#loadParameter").trigger("click");
        });

        $('#selectedrptDate').change(function() {
            $("#loadParameter").trigger("click");
        });
        //Khi load xong
        $('#selectedrptDate').ready(function() {
            $("#loadParameter").trigger("click");
        });

        //Khi thay doi
        $('#period_id').change(function() {
            var ls_selectObj = document.getElementById(
                    'period_id');
            var ls_periodCode = ls_selectObj.value;
            var ls_today = new Date();

            switch (ls_periodCode) {
                case 'D':
                    break;
                case 'W':
                    var curr = new Date; // get current date
                    var first = curr.getDate() - curr.getDay(); // First day is the day of the month - the day of the week
                    var last = first + 5; // last day is the first day + 5 - friday
//                    var firstday = new Date(curr.setDate(first));
                    var lastday = new Date(curr.setDate(last));
                    
                    
                    var lj_setDate = (lastday.getDate()) + "/" +
                            (ls_today.getMonth() + 1) + "/" + ls_today.getFullYear();
                    
//                    var lj_setDate = (ls_today.getDate() - ls_today.getDate() % 5) + "/" +
//                            (ls_today.getMonth() + 1) + "/" + ls_today.getFullYear();
                    document.getElementById("selectedrptDate").value = lj_setDate;
                    break;
                case 'M':
                    var lastDayOfMonth =
                            new Date(ls_today.getFullYear(), ls_today.getMonth() + 1, 0);
                    var lastDayOfMonth_fstr = formatDate(lastDayOfMonth);
                    document.getElementById("selectedrptDate").value = lastDayOfMonth_fstr;
                    break;
                case 'NONE':
                    alert('Bạn phải chọn kỳ trước.');
                    return;
            }

            $("#loadParameter").trigger("click");
        });
        //Khi load xong
        $('#period_id').ready(function() {
            $("#loadParameter").trigger("click");
        });


        $('#reload_data_flag').change(function() {
            var username_ID = document.getElementById('username_ID').value;
            if (
                    username_ID.toString().startsWith('ADMIN')
                    ||username_ID.toString().startsWith('SUPERMOD')
                )
                check_date();
            else {
                alert('Chỉ user quản trị mới có quyền sử dụng chức năng này để tạo lại dữ liệu');
                document.getElementById('reload_data_flag').checked = false;
                document.getElementById('reload_data_flag').disabled = true;
                return;
            }
        });

        $('#reload_data_flag').ready(function() {
            $("#loadParameter").trigger("click");
        });
    });

    $.subscribe('before-next',
            function(event, data) {
                $("#divListParams").empty();
                $("#divListParams").hide();
            });


    function check_date() {

        var ls_selectObj = document.getElementById(
                'period_id');
        var ls_periodCode = ls_selectObj.value;

        switch (ls_periodCode) {
            case 'D':
                if (document.getElementById(
                        'reload_data_flag'
                        ).checked === true) {
                    $('#loadParameter').text('Thực hiện');
                } else {
                    $('#loadParameter').text('Làm mới');
                }
                return true;
            case 'W':
                var lv_date_str = document.getElementById(
                        "selectedrptDate"
                        ).value.toString();
//                alert(lv_date_str);
                var lv_dtcomp = lv_date_str.split("/");
//                for (var i = 0; i < lv_dtcomp.length; i++)
//{
//	alert(lv_dtcomp[i]);
//}

                var lv_day = parseInt(lv_dtcomp[0]);
                var lv_month = parseInt(lv_dtcomp[1]);
                var lv_year = parseInt(lv_dtcomp[2]);

                var lv_date = new Date(lv_year, lv_month - 1, lv_day);
                var ln_day = lv_date.getDay();

                var ln_mod_val = ln_day % 5;
//                alert(lv_date);
//                alert(ln_mod_val);
                if (ln_mod_val !== 0) {
                    alert('Ngày báo cáo không phải ngày cuối tuần');
                    document.getElementById('reload_data_flag').checked = false;
                    return false;
                } else {
                    if (document.getElementById(
                            'reload_data_flag'
                            ).checked === true) {
                        $('#loadParameter').text('Thực hiện');
                    } else {
                        $('#loadParameter').text('Làm mới');
                    }
                    return true;
                }
                break;
            case 'M':
                var lv_date_str = document.getElementById(
                        "selectedrptDate"
                        ).value.toString();
                var lv_day = parseInt(lv_date_str.substr(0, 2));
                var lv_month = parseInt(lv_date_str.substr(3, 2));
                var lv_year = parseInt(lv_date_str.substr(6, 4));
                if (lv_day !== getDaysOfMonth(lv_month, lv_year)) {
                    alert('Ngày báo cáo không phải ngày cuối tháng');
                    document.getElementById('reload_data_flag').checked = false;
                    return false;
                } else {
                    if (document.getElementById(
                            'reload_data_flag'
                            ).checked === true) {
                        $('#loadParameter').text('Thực hiện');
                    } else {
                        $('#loadParameter').text('Làm mới');
                    }
                    return true;
                }
                break;
            case 'NONE':
                alert('Bạn phải chọn kỳ trước.');
                return;
        }
    }

    function getDaysOfMonth(month, year) {
        switch (month) {
            case 1:
                return 31;
            case 2:
                if (year % 4 === 0)
                    return 29;
                else
                    return 28;
            case 3:
                return 31;
            case 4:
                return 30;
            case 5:
                return 31;
            case 6:
                return 30;
            case 7:
                return 31;
            case 8:
                return 31;
            case 9:
                return 30;
            case 10:
                return 31;
            case 11:
                return 30;
            case 12:
                return 31;
        }
    }
    ;



    $.subscribe('after-next', function(event, data) {
        // Effect cho the div
        $("#divListParams").show();
        document.getElementById('reload_data_flag').checked = false;
        var allDate = $(".hasDatepicker").map(function() {
            return $(this).attr("name");
        }).get();
        //2. Them input mask
        for (var i = 0; i < allDate.length; i++) {
            new DateMask("dd/MM/yyyy", allDate[i].toString());
        }
    });


    function formatDate(value)
    {
        return value.getDate()
                + "/"
                + (value.getMonth() + 1)
                + "/"
                + value.getFullYear();
    }

</script>

<style type="text/css">
    *{
        margin: 0px;
    }
    .main_div{
        padding:0px;
        width:100%;    
        /*        background:#f9f9f9;*/
        border:1px solid #ccc;
        text-align:left;   
        font-family:Arial;    
        font-size: 10pt;
    }
    table.table_1{
        border-style: solid;
        border-collapse: collapse;
        /*        background:#f9f9f9;*/
        width: 100%;
        font: 13px Arial, Helvetica, sans-serif; 
        height: 20px;
    }
    table.table_2{
        border-style: solid;
        border-collapse: collapse;
        width: 100%;
        font: 13px Arial, Helvetica, sans-serif; 
        line-height: 25px;
    }
    .tbhead{
        background-color: #5e5e55;
        font-weight: bold;
        height: 20px;
        color: #fff;
        text-align: center;            
    }
    .cscontent td{
        padding-left:5px;
        line-height: 25px;
        height: 25px;
        font: 13px Arial, Helvetica, sans-serif; 
    }
    .cscontent:hover{
        background-color: #ffff99;
    }

    .metroButtonStyle:hover {
        background: #ffff99;
        font-weight: bold;
    }
    .metroButtonStyle:active {
        background: #DCDCDC;
    }
</style>



<div style="padding-left: 5px;">        
    <div id="main_form_div" class="main_div">    
        <s:form id="rpt_form" theme="simple" action="eom_load_task_of_module">
            <table border="0" cellspacing="0" cellpading="0" height="100%" width="100%"
                   class="table_1">
                <tr>
                    <td style="width: 450px;">
                        <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; "> 
                        Module:                                       </font>
                        <s:url var="buildModuleComboUrl" 
                               action="eom_build_module_combo.action"></s:url>
                        <sj:select href="%{buildModuleComboUrl}" 
                                   name="module_id"
                                   id="module_id"
                                   list="modules"    
                                   onChangeTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false"                                                               
                                   theme="simple"    
                                   headerKey="ALL"
                                   headerValue="--- Tất cả ---"
                                   ></sj:select>

                            <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; "> 
                            Ngày báo cáo: 
                            </font>
                        <sj:datepicker name="reportDate" value=""                                        
                                       onblur="validatedate(this.value)"
                                       placeholder="DD/MM/YYYY" changeYear="true" 
                                       changeMonth="true" displayFormat="dd/mm/yy"
                                       id="selectedrptDate" size="15"/>
                        <script>
                            var lj_curDate = new Date();
                            var lj_setDate = (lj_curDate.getDate()) + "/" +
                                    (lj_curDate.getMonth() + 1) + "/" + lj_curDate.getFullYear();
                            document.getElementById("selectedrptDate").value = lj_setDate;
                        </script>



                        <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; "> 
                        Kỳ báo cáo:                                       </font>
                        <%--  <s:url var="buildPeriodComboUrl" 
                                 action="eom_build_period_combo.action"></s:url> --%>

                        <s:url var="buildModuleComboUrl" 
                               action="eom_build_module_combo.action"></s:url>
                        <sj:select href="%{buildModuleComboUrl}" 
                                   name="reportPeriod"
                                   id="period_id"
                                   list="periods"    
                                   reloadTopics="reloadModuleList"
                                   listKey="sKey"
                                   listValue="sDesc"
                                   emptyOption="false"   
                                   headerKey="NONE"
                                   headerValue="--- Chọn kỳ ---"
                                   theme="simple"                                       
                                   ></sj:select>

                            <!--                        headerKey="ALL"
                                                               headerValue="--- Tất cả ---"-->
                            &nbsp;&nbsp;   
                            &nbsp;&nbsp;
                            <!--                        <div style="display: none;">-->
                            <font style="color: blue; font: 13px Arial, Helvetica, sans-serif; "> 
                            <input type="checkbox" id="reload_data_flag" name="reload_data_flag" 
                                   />tạo dữ liệu
                            <!--                        </div>   -->
                            &nbsp;&nbsp;
                            &nbsp;&nbsp;
                    <u> <sj:a id="loadParameter" 
                      formIds="rpt_form" 
                      targets="divListParams" 
                      indicator="loadingImage_next" 
                      href="#"                               
                      onBeforeTopics="before-next"                                                         
                      onCompleteTopics="after-next"
                      cssClass="metroButtonStyle"
                      button="false"> Làm mới</sj:a>      </u>   

                    </td>



                    </font>
                    </td>





                    </tr>                    
                </table>       
        </s:form>            
    </div>    
    <%--    <div align="right" >
            <sj:a id="loadParameter" formIds="rpt_form" targets="divListParams" 
                  indicator="loadingImage_next" href="#" onBeforeTopics="before-next" 
                  onCompleteTopics="after-next"></sj:a>
            </div>         --%>
    <sj:div id="divListParams"></sj:div>

        <input type="hidden" value="<%= session.getAttribute("username")%>" id="username_ID"/>




</div>
