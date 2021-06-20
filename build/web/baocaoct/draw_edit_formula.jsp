<%-- 
    Document   : draw_report_bccongthuc
    Created on : Jul 22, 2014, 11:16:21 AM
    Author     : LION
--%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="vbsp.ims.dao.DaoRptFormula" %>
<%@page import="vbsp.ims.action.FormulaRptAction" %>
<!DOCTYPE html>
<html>

    <script>
        // Thay doi cong thuc cua dong, cot tuong ung
        function changeAll(param) {
            //Lay class name
            var className = $(param).attr('class');
            //Lay gia tri
            var valueInput = $(param).val();
            console.log(className + " -> " + valueInput);
            //For thay doi toan bo class name
            $("input." + className).each(function (index)
            {
                $(this).val(valueInput);
            });

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
    </script>
    <script type="text/javascript" src="js/jquery-1.10.0.min.js"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            //Load dữ liệu từ server về và tr
            $("#check_formula").click(function (e) {
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
                <s:hidden name="save_id"/>
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
                <!--<div style="width: 100%;height:200px;overflow: scroll;">-->
                <div style="height:200px; width: 1024px;overflow: scroll;margin: 0 auto">
                    <table align="center" border="1" cellpadding="0" cellspacing="0" style="width: 80%;">
                        <% DaoRptFormula daoFormula = new DaoRptFormula();
                            FormulaRptAction rptAction = new FormulaRptAction();

//                            String number_row_edit = request.getAttribute("number_row").toString();
//                            String number_col_edit = request.getAttribute("number_column").toString();
                            HashMap<Integer, List<String>> hmArr = daoFormula.getLoadBodyDataHashMap(
                                    request.getAttribute("save_id").toString(),
                                    Integer.parseInt(request.getAttribute("number_row") == null ? "0" : request.getAttribute("number_row").toString()),
                                    Integer.parseInt(request.getAttribute("number_column") == null ? "0" : request.getAttribute("number_column").toString()),
                                    request.getAttribute("row_column").toString(),
                                    request.getAttribute("check_branch").toString());
                            String sReport_type = request.getAttribute("report_type").toString();

                            //                        int nRow=Integer.parseInt(request.getAttribute("number_row").toString());
                            //                        int nColumn=Integer.parseInt(request.getAttribute("number_column").toString());
                            String sDisplayRowColumn = request.getAttribute("row_column").toString();
                            int nRow = hmArr.size();
                            //                         out.println(nRow);
                            //               System.err.println(rptAction.getSave_id());
                            //               Object ob = request.getAttribute("save_id");
                            //                out.println(ob);
                            for (int i = 0; i < nRow; i++) {
    //                            out.println(i);
                        %> 
                        <tr>
                            <%
                                List<String> lst = hmArr.get(i);
                                for (int j = 0; j < lst.size(); j++) {
                                    String strData = lst.get(j);
                                    String sRowname = "row_data_" + Integer.toString(i);
                                    String sClass = "";
                                    if (sDisplayRowColumn.equals("1") && sReport_type.equals("02")) {
                                        sClass = "TEXT_" + Integer.toString(j);
                                    } else if (sDisplayRowColumn.equals("2") && sReport_type.equals("02")) {
                                        sClass = "TEXT_" + Integer.toString(i);
                                    }
                                    if (i == 0 && j == 0) {
                                        strData = strData.replace("**", ",");%>
                            <th>
                                <input type="text" name="title_column_name" id="title_column_name" value="<%=strData%>" size="21" style="height: 16px;font-weight: bold;"/>
                            </th>
                            <%} else if (i == 0) {
                                if (sReport_type.equals("02") && sDisplayRowColumn.equals("2")) {
                                    strData = strData.replace("**", ",");
                            %>
                            <th>
                                <input type="text" name="title_column_name" id="title_column_name" disabled="true" value="<%=strData%>" size="21" style="height: 16px;font-weight: bold;"/>
                            </th>
                            <%} else {
                                strData = strData.replace("**", ",");%>
                            <th>
                                <input type="text" name="title_column_name" id="title_column_name" value="<%=strData%>" size="21" style="height: 16px; font-weight: bold;"/>
                            </th>
                            <%      }
                            } else if (j == 0) {
                                if (sReport_type.equals("02") && sDisplayRowColumn.equals("1")) {
                                    strData = strData.replace("**", ",");
                            %>
                            <th>
                                <input type="text" name="title_row_name" disabled="true" id="title_row_name" value="<%=strData%>" size="21" style="height: 16px; font-weight: bold;"/>
                            </th>
                            <% } else {
                                strData = strData.replace("**", ",");%>
                            <th>
                                <input type="text" name="title_row_name" placeholder="Dien cong thuc" id="title_row_name" value="<%=strData%>" size="21" style="height: 16px; font-weight: bold;"/>
                            </th>
                            <% }

                            } else {
                            %>
                            <%--<s:textfield name="title_column_name" id="Test" value= "<%=strData%>"/>--%>
                            <th>
                                <input type="text" name="<%=sRowname%>" id="<%=sRowname%>" value="<%=strData%>" class="<%=sClass%>" size="21" style="height: 16px;" onblur="changeAll(this);"/>
                            </th>
                            <%}
                                    }
                                }%>
                    </table>
                </div>
                <div  align="right" id="save_div_report"  style="width: 90%;">
                    <img id="loadingImage" src="img/loading.gif" align="center" style="display:none"/>
                    <sj:submit id="Save_Edit_formula" name="Save_Edit_formula" value="Lưu báo cáo" targets="divShowPage_Save" align="right"></sj:submit>
                        <input type="button" name="check_formula" id="check_formula" value="Kiểm tra công thức" align="right"/>
                    </div>
            </s:form>
        </div>
        <div id="containParm">
            <div id="divShowPage_Save"></div>
        </div>
    </body>
</html>
<script>
    reloadData1();

    function reloadData1()
    {
        try {
            var element;
            //Duyet form
            var classNameold = '';
            var nameinputold = '';
            for (var i = 0; i < document.forms["ViewFormulaReport"].elements.length; i++)
            {
                //Lay ra cac phan tu cua form
                element = document.forms["ViewFormulaReport"].elements[i];
                //Neu phan tu do la text thi bat dau xu ly
                if (element.value.length > 0 && element.type == 'text')
                {

                    var nameinput = element.name;
//                    var valueInput = element.value;

                    if (nameinput.match("row_data"))
                    {
                        var id = element.id;
                        var className = element.className;//$("#" + id).attr('class');
//                        var className = $("#test").prop("class");
                        //Lay gia tri
                        element.className
                        var valueInput = element.value;
                        if (className != classNameold || nameinputold != nameinputold)
                        {
                            classNameold = className;
                            nameinputold = nameinput;
//                        reloadData(nameinput,valueInput);
//                            console.log("classNameold=" + classNameold +" className=" + className + " " + nameinput + " -> " + valueInput + " id=" + id);
//                        //For thay doi toan bo class name
                            $("input." + className).each(function (index)
                            {
                                $(this).val(valueInput);
                            });

                        }
//                        console.log(element.name);
//                        changeAll(element.name);

                    }
                }

            }

            //Console.log(params);
        } catch (e) {
            alert('loi khoi tao cong thuc ' + e.description);
        }
    }
</script>
