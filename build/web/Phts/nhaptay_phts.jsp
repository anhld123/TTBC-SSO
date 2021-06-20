<%-- 
    Document   : exp_excel
    Created on : Jul 14, 2014, 4:17:12 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-jquery-tree-tags" prefix="sjt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>

    <head>
       
        <sj:head/>      
        <!--<script type="text/javascript" src="js/jquery-1.10.2.js"></script>-->
        <script>                             
                        
            
            function onTranData()
            {    
                var ngay_bc = $("#ngay_bc").val();
                
                var lv_day = parseInt(ngay_bc.substr(0, 2));                
                var lv_month = parseInt(ngay_bc.substr(3, 2));                
                var lv_year = parseInt(ngay_bc.substr(6, 4));                
                if (lv_day !== getDaysOfMonth(lv_month, lv_year)) {
                    var r = confirm("Ngày báo cáo không phải ngày cuối tháng. Bạn có thật sự muốn tiếp tục  không ? OK : Đồng ý, Cancel : Hủy bỏ");
                    if (r == true) {
                        <%
                    if (session.getAttribute("reportGrade").equals("1") || session.getAttribute("reportGrade").equals("2")) {
                %>
                   alert("Hiện tại chương trình chưa hỗ trợ cấp CN và PGD. Vui lòng chọn kỳ báo cáo và ấn Đóng");
                   return;
                <% }%> 
                    
                var sContentInput = $.trim($("#sContent").val()).length;                
                if (sContentInput <1)
                {
//                    alert('Bạn đã nhập dữ liệu nguyên nhân chênh lệch nên không thể nhập dữ liệu cho trường này');
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải nhập phản hồi trước khi chuyển tiếp! </h2>");                    
                        return;
                    }

                    $.subscribe("beforediv_send", function (event, data) {
                        $("#loadingImageDiv_data").show();
                    });
                    $.subscribe("completediv_send", function (event, data) {
                        $("#loadingImageDiv_data").hide();
                    });    
                    $('#divExportReport').empty();  
                    $("#idTranPhts")[0].click();  
                        return true;
                    }
                    else
                        return false;
                }   
                
                <%
                    if (session.getAttribute("reportGrade").equals("1") || session.getAttribute("reportGrade").equals("2")) {
                %>
                   alert("Hiện tại chương trình chưa hỗ trợ cấp CN và PGD. Vui lòng chọn kỳ báo cáo và ấn Đóng");
                   return;
                <% }%> 
                    
                var sContentInput = $.trim($("#sContent").val()).length;                
                if (sContentInput <1)
                {
//                    alert('Bạn đã nhập dữ liệu nguyên nhân chênh lệch nên không thể nhập dữ liệu cho trường này');
                    $('#divExportReport').html("<h2 style='color: red'>Bạn phải nhập phản hồi trước khi chuyển tiếp! </h2>");                    
                    return;
                }
                
                $.subscribe("beforediv_send", function (event, data) {
                    $("#loadingImageDiv_data").show();
                });
                $.subscribe("completediv_send", function (event, data) {
                    $("#loadingImageDiv_data").hide();
                });    
                $('#divExportReport').empty();  
                $("#idTranPhts")[0].click();                
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
            
            function openPage()
            {
                //alert(pageURL);
                var ngay_bc = $("#ngay_bc").val();
                
                var lv_day = parseInt(ngay_bc.substr(0, 2));                
                var lv_month = parseInt(ngay_bc.substr(3, 2));                
                var lv_year = parseInt(ngay_bc.substr(6, 4));                
                if (lv_day !== getDaysOfMonth(lv_month, lv_year)) {
                    var r = confirm("Ngày báo cáo không phải ngày cuối tháng. Bạn có thật sự muốn tiếp tục  không ? OK : Đồng ý, Cancel : Hủy bỏ");
                    if (r == true) {
                        window.location.href = "getMainDataPhts.action?ngay_bc=" +  ngay_bc ;                               
                        return true;
                    }
                    else
                        return false;
                }        
                window.location.href = "getMainDataPhts.action?ngay_bc=" +  ngay_bc ;                               
            }
        </script>
                        

        <style>

            #container{
                width: 100%;
                height: 460px;
                border: 0px solid;
                padding-left: 0px;        
                /*background: #FFE6B0*/

            }

            #containTree{
                width: 18%;
                border-left: 1px solid;
                border-right: 1px solid;
                height: 480px;
                float: left;
                overflow: scroll;
                background: #DDFFDD;
                /*border: 1px solid;*/
            }

            #containParm{
                width: 100%;
                height: 200px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                /*border: 1px solid;*/
                /*background: #d58512;*/
            }
            
            #containReport{
                width: 90%;
                height: 200px;
                padding-left: 5px;
                float: left;
                overflow: scroll;
                border: 1px solid;
                /*background: #d58512;*/
            }

            #navParam{
                width: 81%;
                padding-left: 5px;
                height: 250px;
                float: right;
                /*border: 1px solid;*/
            }
            
            a:hover {
                color: hotpink;
            }

        </style>
    </head>
    <body>
        
        <s:form id="exp_phts_input" action="export_report_phts_input" theme="simple">            
            <div id="navParam"  align="center">
                
                &nbsp;                   
                    <s:url id="loadtree_kybc" action="loadKyBC_TreePhts.action"></s:url>       
                <table style="width: 97%" >
                    <tr style="hight: 20px">
                        <td width="4%">Kỳ báo cáo: </td>
                        <td width="30%">   
                            <sj:datepicker name="ngay_bc" id="ngay_bc"
                                           value="%{new java.util.Date()}" 
                                           placeholder="DD/MM/YYYY" changeYear="true"  changeMonth="true" displayFormat="dd/mm/yy" 
                                           cssStyle="font-weight: bold;vertical-align: middle;"/>  
                        </td>
                    </tr>                                                                        
                    <tr>
                                <td colspan="2">
                                    <span id="idTitle">Nội dung</span>
                                </td>
                            </tr>

                            <tr align="center">
                                <td  colspan="2" align="center" >
                                    <textarea id="sContent"
                                              name="mainContent"
                                              style="width: 100%;background-color: #DDFFDD;" 
                                              rows="6"></textarea>
                                </td>
                            </tr>                                                 
                </table> 
                <hr/>
                <div align="right" id="link">
                    <s:url id="idTran" action="tranPhts.action"></s:url>                                      
                    <sj:submit id="idTranPhts" name="nameSend" href="%{idTran}" value="Chuyển" targets="divExportReport"
                               onBeforeTopics="beforediv_send"
                               onCompleteTopics="completediv_send" cssStyle="display:none"/>
                    <input type="button" id="idTranPhtsTmp" name="nameidSendtmp"  onclick="onTranData()" value=" Chuyển "/>
                    &nbsp;&nbsp;
                    <input type="button" value="  Đóng  " name="AuthorizationManager"
                        onclick="openPage('')" /> &nbsp;&nbsp;
                                         
                </div>                
                <div id="loadingImageDiv_data" style="margin-left: 20px;display: none;" >
                                        <img id="loadingImage" src='img/loading.gif' border='0' >
                </div>
                 &nbsp;&nbsp;
                 <div id="divExportReport" align="center" ></div>
            </div>
            
        <div id="containTree">
            <sjt:tree href="%{loadtree_kybc}"                               
                      name="pos_cd"
                            id="treeDynamicCheckboxes"
                            jstreetheme="apple"
                            rootNode="nodes_pos"
                            childCollectionProperty="children"
                            nodeTitleProperty="title"
                            nodeIdProperty="id"
                            openAllOnLoad="true"
                            checkbox="true"
                            showThemeDots="false"
                            showThemeIcons="true" 
                      />
        </div>        
        </s:form>    
        </div>
    </body>
</html>
