<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <s:head/>
        <sj:head  jqueryui="true" jquerytheme="smoothness"/>
        <script src="js/picker.js" type="text/javascript"></script>

        <style>
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: .9em;
            }
        </style>

        <script>            
            $.subscribe('before-next', function(event, data) {
                $("#divGridBcnt").empty();
                $("#divGridBcnt").hide();
            });

            $.subscribe('after-next', function(event, data) {
                $("#divGridBcnt").slideDown("slow");
            });
            
            //CuongBM: 17Jun14
            //Kiem tra các báo cáo
            $(function(){
               $("#gridBcnt").click(function(e){                   
//                   //Fire su kien #loadGridBcnt.click
                   $("#loadGridBcnt").trigger("click");
               });
               
               $("#datePicker").hide();
               
            });
            
        </script>
    </head>
    <body>
        <h4>Báo cáo nhập tay</h4>
        <hr/>

        <div class="report_group_form_bcnt">
            <s:form id="bcntId" action="loadBcntGrid" theme="simple">            
                <s:url var="getReportLisUrl" action="populateReportId"></s:url>
                <s:url var="getPosLisUrl" action="populatePosId"></s:url>

                    <table>
                        <tr>
                            <td width="150">Mã Báo Cáo:</td>
                            <td width="400">
                            <sj:select href="%{getReportLisUrl}" 
                                       id="reportId"
                                       name="reportId"
                                       list="reportList" 
                                       listKey="id"
                                       listValue="desc"
                                       emptyOption="false"></sj:select>
                                <div id="datePicker">
                                    <sj:datepicker id="reportDate" name="reportDate" value="%{new java.util.Date()}" 
                                               placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>
                                </div>
                                
                            </td>
                        </tr>
                            
                                               
                        <tr>
                            <td width="150">Quý Báo Cáo:</td>
                            <td width="400">
                                <select id="quarteryear" name="quarteryear"></select>
                             </td>
                        </tr>
                        
                        <tr>
                            <td width="150">Phòng Giao Dịch:</td>
                            <td width="400">
                                <sj:select href="%{getPosLisUrl}" 
                                       id="posId"
                                       name="posId"
                                       list="posList" 
                                       listKey="id"
                                       listValue="desc"
                                       emptyOption="false" 
                                       ></sj:select>
                            </td>
                        </tr>
                    </table> 
            </s:form>
        </div>
        <hr/>        

        <div align="right" >
            <img id="loadingImage_next" src="img/loaderB32.gif" style="display:none"/>
            <a id="gridBcnt" href="#">Tiếp theo</a>
            <sj:a id="loadGridBcnt" formIds="bcntId" targets="divGridBcnt" indicator="loadingImage_next" href="#" onBeforeTopics="before-next" onCompleteTopics="after-next"></sj:a>
        </div>
        
        <!-- CuongBM: 15-Apr-14 -->
        <!-- The div de load cac tham so -->
        <div id="divGridBcnt" style="width: 100%"></div>
    </body>
</html>
