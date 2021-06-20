<%-- 
    Document   : main_bccongthuc
    Created on : Jul 18, 2014, 8:58:04 AM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<script src="js/formula.js"></script>
<!DOCTYPE html>
<html>
    
    <head>
        <sj:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <style type="text/css">
        th{
            background-color: #DCDCDC;
            border-color: #999;
        }
        td{
            border-color: #999;
        }
        body,div{
            font-family: Arial;
            font-size: 13px;
        }
        a.button{
            font-weight: bold;
            text-decoration:none;
            color: #018c3b;
        }
        a{
            text-decoration:none;
            color: #018c3b;
        }
        .idcot{
            font-weight: bold;

        }
        .iddong{
            font-weight: bold;
        }
        .idcot,.iddong,.idtd,.idcont{
            width: 150px;
        }
        .cbonguon{
            width:140px;
        }
    </style>
    <body>
        <div align="center">
        <!--<h1 align="center">Tạo báo cáo theo công thức!</h1>-->
        <s:form align="center" id="formula_create"  name="formula_create" action="FormulaCreate" theme="simple">
            <table border="1" cellspacing="0px" cellpadding="3px" style="width: 80%;" align="center">
                <tr>
                    <td style="width: 20%; height: 10px;">                    
                    <s:label value="Nguồn số liệu:" id="labelsource" />
                        <s:select id="sourcedata" 
                                  name="source_data"      
                                  value = "#{'03'}"
                                  list="lstSourceData" 
                                  listKey="sKey"
                                  listValue="sDesc"
                                  headerKey="-1"
                                  headerValue="--- Chọn Nguồn SL ---"
                                  cssClass="cbonguon" >                    
                        </s:select>
                    </td>
                    <td style="width: 20%; height: 10px;">
                        <s:label value="Đơn vị tính:" id="labeluntil" />
                        <s:select id="untildata" 
                                  name="until_data"                                   
                                  list="lstUntilData" 
                                  listKey="sKey"
                                  listValue="sDesc"
                                  cssClass="cbonguon" value="1">                     
                        </s:select>
                    </td>
                    <td rowspan="5" colspan="3" style="width: 25%;border: 1px solid; border-color: #018c3b;"  valign="top">
                        <div align="left" id="showandhidden_main" style="visibility: hidden;"> 
                            <s:checkbox id ="allCheck" name="allCheck" onclick="selectallMe()" value="1"/>
                            <s:label value="Chọn tất cả" cssStyle="font-weight: bold" id="labelselect_all"/> 
                        </div>

                        <div align="left" id="showandhidden_table" style="width: 100%;height:155px;overflow-y: scroll; visibility: hidden;">
                            <s:iterator value="#attr.lstBranchObj" var="lstBranchObj" status="rowstatus">
                                <s:checkbox id ="check_branch" name="check_branch" fieldValue="%{sKey}"
                                            onclick="selectall()" value="1"/>
                                <s:property  value="sDesc" /></br>
                            </s:iterator>
                        </div>
                    </td>
                </tr>
                <tr>

                    <td style="width: 15%; height: 10px;">
                        <s:label value="Loại báo cáo:"  id="labelreport_type"/>
                        <s:select id="report_type" 
                                  name="report_type"
                                  list="lstReportType" 
                                  listKey="sKey"
                                  listValue="sDesc"
                                  value="1" onchange="showandhidden()">                    
                        </s:select>
                    </td>
                    <td style="width: 15%; height: 10px;">
                        <s:label value="Kỳ báo cáo:" id="label_times"/>
                        <s:select id="report_times" 
                                  name="report_times"
                                  list="lstReportTimes" 
                                  listKey="sKey"
                                  listValue="sDesc"
                                  headerKey="-1"
                                  headerValue="--- Chọn kỳ BC ---"
                                  cssClass="cbonguon" value="%{setDefaultSourceData}">                    
                        </s:select>
                    </td>
                </tr>
                <tr>
                    <td colspan="2" style="height: 10px;">
                        <s:label value="Tiêu đề báo cáo:" id="labelrpt_title"/>
                        <s:textfield name="title_name" value="Tao thu bao cao theo cong thuc" size="80" id="title_name"/>
                    </td>
                </tr>
                <tr>
                    <td style="width: 15%; height: 10px;">
                        <s:label value="Số dòng: " id="labelrow_number"/>
                        <s:textfield name="number_row" id="number_row" value="5"/>
                    </td>
                    <td>
                        <s:label value="Số cột: "/>
                        <s:textfield name="number_column" id="number_column" value="5"/>
                    </td>
                </tr>
                <tr>
                    <td  align="center" style="height: 10px;">
                        <div id="showandhidden_rowcolumn" style="visibility: hidden;"> 
                            <s:label value="Hiển thị theo dòng/cột: " id="labeldisplay"/>
                            <s:radio id="row_column"
                                     name="row_column" 
                                     list="lstRowColumn" 
                                     listKey="sKey"
                                     listValue="sDesc" 
                                     value="1"
                                     onclick="DisableEnable()"
                                     />     
                        </div>
                    </td>
                     <td  align="center" style="height: 10px;">
                            <s:label value="Cấp xuất BC: " id="labeldisplay"/>
                          <s:checkboxlist list="lstGrade" value="defaultGrade" listKey="sKey" listValue="sDesc"
                                        name="rptGrade"></s:checkboxlist>   
                    </td>
                </tr>
            </table>
            </br>
            <div  align="right" id="Main_report_formula" style="width: 90%;">
                <sj:submit id="create_report" name="create_report" value="Tạo bảng dữ liệu" targets="divShowPage"/>
            </div>
        </s:form>
    </div>
        <div id="containParm" align="center">
            <div id="divShowPage" align="center"></div>
        </div>
   
      
    </body>
</html>
