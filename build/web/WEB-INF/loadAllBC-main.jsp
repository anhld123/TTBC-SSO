<%@taglib prefix="s" uri="/struts-tags" %>
<%@taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<script src="js/js.reload.para.ver.1.1.js" type="text/javascript"></script>
        <script>
            //CuongBM: 26-Jun-14
            //Xu ly truong hop chi cho xem nguoi dung tao bao cao, xuat bao cao 1 lan
            //Cach xu ly:
            //      1. Them 2 nut giả: 
            //                      #mapViewReport (De xuat bao cao)
            //                      #mapGenReport (De tao bao cao)
            //      2. Khi click vao nut gia nay: 
            //              a. Bo luon su kien click bang function unbin('click')
            //              b. Goi den su kien click cua nut that
            //      3. Khi hoan thanh xuat bao cao, tao bao cao: lai bind lai su kien nhu buoc 2
            
            var ajaxGetting = false;    //true: He thong dang sinh bao cao, phai doi sinh xong
                                        //false: He thong khong sinh bao cao
            function xemBaoCao(){
                //Neu khong co bao cao dang sinh thi cho phep sinh bao cao
                if(ajaxGetting == false){
                    ajaxGetting = true;
                    $("#aViewRpt").trigger('click');
                }
            }
            
            function xuatBaoCao(){
                //Neu khong co bao cao dang sinh thi cho phep sinh bao cao
                if(ajaxGetting == false){
                    ajaxGetting = true;
                    $("#idGenJasperReport").trigger('click');
                }
            }
            
            $.subscribe('beforeClick', function(event, data) {
                $("#divExportReport").empty();
            });

            //CuongBM: 22-Jun-14
            //Desc: Khi hoan thanh view thi cho phep nguoi dung bam tiep
            $.subscribe('completeView', function(event, data) {
                //Khi tao bao cao xong khoi tao lai bien ajaxGetting
                ajaxGetting = false;
            });

            //CuongBM: 22-Jun-14
            //Desc: Khi hoan thanh view thi cho phep nguoi dung bam tiep
            $.subscribe('completeGen', function(event, data) {
                //Khi tao bao cao xong khoi tao lai bien ajaxGetting
                ajaxGetting = false;
            });
        </script>

        <style>
            .ui-datepicker{
                font-family: Trebuchet MS, Tahoma, Verdana, Arial, sans-serif; 
                font-size: .9em;
            }
        </style>


    </head>
    <body onload="func_clear_data();">
        <strong>Nhập tham số</strong>
        <hr/>
        <div class="report_group_form" id="listParam">
            <s:form id="genReportJasper" theme="simple" action="genReportJasper">               
                <s:hidden name="reportId"/>
                <table>
                    <td width="150">Kiểu file:</td>
                    <td width="700">
                        <select name="exportType">
                            <option value="PDF">PDF</option>
                            <option value="EXEL">EXCEL</option>
                        </select>
                    </td>       
                    <s:iterator value="reportParamsList" var="objReport">
                        <tr>
                            <td width="150"><s:property value="label"></s:property>:</td>
                                <td width="500">
                                    <!-- TUNGNV: Neu la T thi gen textfield -->
                                <s:if test="type.equalsIgnoreCase('T')">                                     
                                    <s:textfield  name="%{fieldName}_TEXT" cssClass="parameter"></s:textfield>
                                </s:if>
								
                               							
								<!-- VinhNP xử lsy lại khi chọn selectbox -->
                                
                                <s:if test="fieldName.equals('PARA_MAXA') || fieldName.equals('PV_MAXAD')  || fieldName.equals('PV_MAXA') || fieldName.equals('PARA_MATO') || fieldName.equals('PV_MATO') || fieldName.equals('PARA_MATHON') || fieldName.equals('PV_MATHON')">
                                    <select name="<s:property value="fieldName"/>_LIST" id="<s:property value="fieldName"/>">
                                        <option value='000000' selected='selected'>--Tất cả---</option>
                                    </select>
                                    <s:if test="type.equalsIgnoreCase('L')">
                                        <s:select  list="comboList" name="%{fieldName}_DATA" listKey="key" listValue="value" id="%{fieldName}_DATA" cssStyle="display:none"></s:select>
                                    </s:if>
                                </s:if>
                                <s:else>
                                    <s:if test="type.equalsIgnoreCase('L')">
                                        <s:select  list="comboList" name="%{fieldName}_LIST" listKey="key" listValue="value" id="%{fieldName}"></s:select>
                                    </s:if>
                                </s:else>
                                <!--VinhNP: End-->
								
                                <!-- TUNGNV: Neu la D thi gen Date -->
                                <s:if test="type.equalsIgnoreCase('D')"> 
                                    <sj:datepicker name="%{fieldName}_DATE" value="%{defaultRptdate}" onblur="validatedate(this.value)"
                                                   placeholder="DD/MM/YYYY" changeYear="true" changeMonth="true" displayFormat="dd/mm/yy"/>
                                </s:if>
                            </td>
                        </tr>                        
                    </s:iterator>                     
                    <tr>
                        <td align="center" style="color: red">
                            <strong><s:property value="message" /> </strong>
                        </td>
                    </tr>            
                </table>
            </s:form>
        </div>
        <hr/>
        <div align="right" id="link">
            <img id="loadingImage" src="img/loaderB32.gif" style="display:none"/>
            <a href="javascript:void(0);" id="mapViewReport" onclick="xemBaoCao()">Xem báo cáo</a>&nbsp;|&nbsp;
            <a href="javascript:void(0);" id="mapGenReport" onclick="xuatBaoCao()">Xuất báo cáo</a>            
            
            <s:url id="ViewReport" action="viewReportJasper" />
            <sj:a id="aViewRpt" formIds="genReportJasper" targets="divExportReport"  href="%{ViewReport}" indicator="loadingImage" onCompleteTopics="completeView" onBeforeTopics="beforeClick"></sj:a>
            <sj:a id="idGenJasperReport" formIds="genReportJasper" targets="divExportReport" 
                  indicator="loadingImage" href="#" onCompleteTopics="completeGen" onBeforeTopics="beforeClick">
            </sj:a>
        </div>
        <div id="divExportReport"></div>
        
        <script>
            //CuongBM: 31Jul14
            //Desc: Xu truong hop dat gia tri mac dich cho combox Quy (Quater), la quy hien tai
            //      Cac bao cao Quy phai co id la PARA_QUY           
            // TrungNT88 sua
           
            var date = new Date(); 
            var year = date.getFullYear(); //nam
            var quarter = Math.floor(date.getMonth() / 3) + 1; //quy
            //Gan quy mac dinh
            $("#PARA_QUY").val(year + "-" + quarter);
            
        </script>
    </body>
</html>
