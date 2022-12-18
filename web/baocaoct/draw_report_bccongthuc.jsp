<%-- 
    Document   : draw_report_bccongthuc
    Created on : Jul 22, 2014, 11:16:21 AM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <script type="text/javascript" src="js/jquery-1.10.0.min.js"></script>
    <script type="text/javascript" src="js/jquery.js"></script>
    <!--<script type="text/javascript" src="js/base.js"></script>-->
    <script>
        function getData() {
            var data = '';
            var params = {};
            var arr = [];
            try {
                var data = '';
                var selectedCount = 0;
                var element;
                for (var i = 0; i < document.forms["ViewFormulaReport"].elements.length; i++)
                {
                    element = document.forms["ViewFormulaReport"].elements[i];
                    if (element.value.length > 0 && element.type == 'text')
                    {
                        selectedCount++;
                        var nameinput = element.name;
                        if (nameinput.match("row_data"))
                        {
                            data = data + ',' + element.value;
                            var p1 = {"sData": element.value, "nID": selectedCount, "Name": nameinput, "sColor": "white"};
                            arr.push(p1);
                            //alert('p1 '+p1);
                        }
                    }

                }
                params["data"] = arr;

                //Console.log(params);
            } catch (e) {
                alert('getData ' + e.description);
            }
            //alert(params["data"]);
            return data;
        }

        function getDataObj() {
            var data = '';
            var params = {};
            var arr = [];
            try {
                var data = '';
                var selectedCount = 0;
                var element;
                //Duyet form
                for (var i = 0; i < document.forms["ViewFormulaReport"].elements.length; i++)
                {
                    //Lay ra cac phan tu cua form
                    element = document.forms["ViewFormulaReport"].elements[i];
                    //Neu phan tu do la text thi bat dau xu ly
                    if (element.value.length > 0 && element.type == 'text')
                    {

                        var nameinput = element.name;
                        //Neu text do co name la row_data_XX thi xu ly kiem tra cong thuc
                        //Day ra 1 mảng
                        if (nameinput.match("row_data"))
                        {
                            selectedCount++;
                            data = data + ',' + element.value;
                            var p1 = {"sData": element.value, "nID": selectedCount, "sName": nameinput, "sColor": "white"};
                            arr.push(p1);
                        }
                    }

                }
                params["data"] = arr;

                //Console.log(params);
            } catch (e) {
                alert('getData ' + e.description);
            }
            //alert(params["data"]);
            return params;
        }
        //Ham nay update style cua text paraID, paraName, pavaValue
        function setUpdateStyle(paraID, paraName, pavaValue, paraColor) {
            var data = 'OK';
            try {
                var selectedCount = 0;
                var element;
                for (var i = 0; i < document.forms["ViewFormulaReport"].elements.length; i++)
                {
                    element = document.forms["ViewFormulaReport"].elements[i];
                    if (element.value.length > 0 && element.type == 'text')
                    {
                        var nameinput = element.name;
                        if (nameinput.match("row_data"))
                        {
                            selectedCount++;
                            //element.style.backgroundColor = paraColor;
                            //alert(paraColor);
//                            if (selectedCount == paraID)
//                            {
                            if (selectedCount == paraID && element.value == pavaValue && nameinput == paraName)
                                element.style.backgroundColor = paraColor;
//                                    element.style.backgroundColor = "yellow";
//                                else
//                                    element.style.backgroundColor = "white";
                            //}
                        }
                    }
                }
            } catch (e) {
                alert('setUpdateStyle ' + e.description);
            }
            return data;
        }

        // Thay doi cong thuc cua dong, cot tuong ung
        function changeAll(param) {
            //Lay class name
            var className = $(param).attr('class');
            //Lay gia tri
            var valueInput = $(param).val();
            //For thay doi toan bo class name
            $("input." + className).each(function (index)
            {
                $(this).val(valueInput);
            });
        }

    </script>

    <script type="text/javascript">
        $(document).ready(function () {

            //Load dữ liệu từ server về và tr
            $("#check_formula").click(function (e) {
                //Hien thi loading image
                $("#loadingImage").show();

                //var dataObj = getData();
                //lay ra cac object tren web
                var dataObj = getDataObj();
                //convert ve string 
                var data1 = JSON.stringify(dataObj);
                //alert(data1);
                //console.log(dataObj);
                //console.log(data1);
                //Goi action de kiem tra cong thuc
                var url = "checkformula.action";
                $.ajax({
                    url: url,
                    data: data1,
                    dataType: 'json',
                    contentType: 'application/json',
                    type: 'POST',
                    async: true,
                    success: function (res) {
                        //$('#divShowPage_Save').html(res.data);
                        //console.log(res.data);
                        //console.log(res.objReturn.length);
                        var nfalse = 0;
                        for (var i = 0; i < res.objReturn.length; i++) {
                            //console.log(res.objReturn[i].sColor + " - " + res.objReturn[i].sData + "-" + res.objReturn[i].nID + "-" + res.objReturn[i].sName);
                            setUpdateStyle(res.objReturn[i].nID, res.objReturn[i].sName, res.objReturn[i].sData, res.objReturn[i].sColor);
                            if (res.objReturn[i].sColor == 'red')
                                nfalse++;
                        }
                        if (nfalse == 0)
                            $('#divShowPage_Save').html("<h2 style='color: red'>Tất cả công thức đã đúng bạn có thể lưu báo cáo ! </h2>");
                        else
                            $('#divShowPage_Save').html("<h2 style='color: red'>Có công thức sai đã được bôi đỏ bạn kiểm tra lại ! </h2>");

                        $("#loadingImage").hide();
                    }
                });
            });
        });
    </script>
    <head>
        <sj:head/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div style="width: 100%; margin: 0 auto">
            <s:form align="center" id="ViewFormulaReport" name="ViewFormulaReport" action="SaveFormulaReport" theme="simple">
                <s:hidden name="source_data"/>
                <s:hidden name="until_data"/>
                <s:hidden name="check_branch"/>
                <s:hidden name="report_type"/>
                <s:hidden name="report_times"/>
                <s:hidden name="title_name"/>
                <s:hidden name="number_row"/>
                <s:hidden name="number_column"/>
                <s:hidden name="row_column"/>
                <s:iterator value="rptGrade" status="row">
                    <s:hidden name="rptGrade[%{#row.index}]" />
                </s:iterator>
                <div align="center"><h1><s:property value="title_name"></s:property> </h1>
                    </div> 
                    <div align="right" style="width: 85%;">
                    <s:if test="until_data.equalsIgnoreCase('1')">
                        <h4 style='color: blue'>Đơn vị tính: Đồng</h4>
                    </s:if>
                    <s:if test="until_data.equalsIgnoreCase('1000')">
                        <h4 style='color: blue'>Đơn vị tính: Nghìn đồng</h4>
                    </s:if>
                    <s:if test="until_data.equalsIgnoreCase('1000000')">
                        <h4 style='color: blue'>Đơn vị tính: Triệu đồng</h4>
                    </s:if>
                </div>
                <div style="height:300px; width: 88%;overflow: scroll;margin: 0 auto">
                    <table align="center" border="1" cellpadding="0" cellspacing="0">
                        <s:iterator value="lstRowTable" var="objRow">  
                            <tr>
                                <%--<s:property value="sDesc" escape="false"></s:property>--%>
                                <s:iterator value="lstColumnTable" var="objColumn">               
                                    <th style="height: 7px;">
                                        <!--Nếu row là vị trí 0-->
                                        <s:if test="#objRow.row.equalsIgnoreCase('0')">  
                                            <!--Nếu cột là vị trí 0 thì điền tiêu đề cột-->
                                            <s:if test="#objColumn.column.equalsIgnoreCase('0')">
                                                <s:textfield id="title_column_name"  name="title_column_name" value="Tiêu đề cột" 
                                                             cssStyle="font-weight: bold" size="21"></s:textfield>
                                            </s:if>
                                            <!--trường hợp cột khác 0 thì vẽ lên tên cột-->
                                            <s:else>
                                                <s:if test="report_type.equalsIgnoreCase('02')">
                                                    <s:if test="row_column.equalsIgnoreCase('1')">
                                                        <s:textfield id="title_column_name" name="title_column_name" value="%{#objColumn.row}" cssStyle="font-weight: bold" size="21"></s:textfield>
                                                    </s:if>
                                                    <s:else>
                                                        <s:textfield id="title_column_name" name="title_column_name" disabled="true" value="%{#objColumn.row}" cssStyle="font-weight: bold" size="21"></s:textfield>
                                                    </s:else>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="title_column_name" placeholder="dien cong thu" name="title_column_name" value="%{#objColumn.row}" cssStyle="font-weight: bold" size="21"></s:textfield>
                                                </s:else>
                                            </s:else>
                                        </s:if>
                                        <!--trường hợp vị trí cột bằng 0 thì để người sử dụng điền tên dòng trên bảng-->           
                                        <s:elseif test="#objColumn.column.equalsIgnoreCase('0')">
                                            <s:if test="report_type.equalsIgnoreCase('02')">
                                                <s:if test="row_column.equalsIgnoreCase('1')">
                                                    <s:textfield id="title_row_name"  name="title_row_name" placeholder="Dien cong thuc" disabled="true" value="%{#objRow.column}" cssStyle="font-weight: bold" size="21"></s:textfield>
                                                </s:if>
                                                <s:else>
                                                    <s:textfield id="title_row_name"  name="title_row_name" placeholder="Dien cong thuc" value="%{#objRow.column}" cssStyle="font-weight: bold" size="21"></s:textfield>
                                                </s:else>
                                            </s:if>
                                            <s:else>
                                                <s:textfield id="title_row_name"  name="title_row_name" placeholder="Dien cong thuc" value="%{#objRow.column}" cssStyle="font-weight: bold" size="21"></s:textfield>
                                            </s:else>
                                        </s:elseif>

                                        <!--Trương hợp vẽ cột và dòng cho phần dữ liệu-->
                                        <s:else>
                                            <!--Nếu kiểu báo cáo là chi tiết thì các dòng các cột vẽ bình thường-->
                                            <s:if test="report_type.equalsIgnoreCase('01')">
                                                <s:textfield id="row_data"  name="row_data_%{#objRow.row}"  placeholder="Dien cong thuc" value="[%{#objRow.row}]"  size="21"></s:textfield>
                                            </s:if>
                                            <!--Truong hợp báo cáo là tổng hợp-->
                                            <s:else>
                                                <!--Nếu trường hợp chọn hiển thị chi nhánh là dòng dữ liệu (row) thì vẽ tên chi nhánh là dòng,-->
                                                <!--chi tiêu nhập là cột-->
                                                <s:if test="row_column.equalsIgnoreCase('1')">
                                                    <s:textfield id="row_data"  name="row_data_%{#objRow.row}" placeholder="Dien cong thuc" value="[%{#objRow.row}]"  size="21" cssClass="TEXT_%{#objColumn.column}" onblur="changeAll(this);"></s:textfield>
                                                </s:if>
                                                <!--trường hợp hiển thị chỉ tiêu là dòng, chi nhánh là cột-->
                                                <s:else>
                                                    <s:textfield id="row_data"  name="row_data_%{#objRow.row}" placeholder="Dien cong thuc" value="[%{#objRow.row}]"  size="21" cssClass="TEXT_%{#objRow.row}" onblur="changeAll(this);"></s:textfield>
                                                </s:else>
                                            </s:else>
                                        </s:else>
                                        <%--<s:property value="%{#objRow.row}" escape="false"></s:property>--%> 
                                        <%--<s:property value="%{#objColumn.column}" escape="false"></s:property>--%>
                                    </th>
                                </s:iterator>
                            </tr>
                        </s:iterator>
                    </table>
                </div>
                <div  align="right" id="save_div_report"  style="width: 90%;">
                    <img id="loadingImage" src="img/loading.gif" align="center" style="display:none"/>
                    <sj:submit id="Save_rpt_formula" name="Save_rpt_formula" value="Lưu báo cáo" targets="divShowPage_Save" align="right"></sj:submit>
                    <%--<sj:submit id="check_formula" name="check_formula" value="Kiểm tra công thức" targets="divShowPage_Save" align="right"/>--%>  
                    <input type="button" name="check_formula" id="check_formula"  value="Kiểm tra công thức" align="right"/>
                </div>
            </s:form>
        </div>
        <div id="containParm">

            <div name="divShowPage_Save" id="divShowPage_Save"></div>
        </div>

    </body>
</html>
