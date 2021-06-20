<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%--<sj:head/>--%>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!--        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css" />
                <link rel="stylesheet" href="css/bootstrap-multiselect.css" type="text/css" />
                <script type="text/javascript" src="js/jquery.min.js"></script>
                <script type="text/javascript" src="js/bootstrap.min.js"></script>
                <script type="text/javascript" src="js/bootstrap-multiselect.js"></script>-->
        <link rel="stylesheet" type="text/css" href="css/css/jquery.multiselect.css" />
        <link rel="stylesheet" type="text/css" href="css/css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/css/prettify.css" />
        <link rel="stylesheet" type="text/css" href="css/css/jquery-ui.css" />
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/js/jquery.multiselect.js"></script>
        <script type="text/javascript" src="js/js/prettify.js"></script>
    </head>
    <script>
        $(document).ready(function () {
//            $('#group_id').multiselect(
//                    {
//                        includeSelectAllOption: true
//                    });
            $("select").multiselect({
                selectedList: 1
            });

        });
        function openChild(file, window) {
            var groupid = $.trim($("#group_id").val());
            if (groupid == '-1' || groupid == null)
            {
                alert('Bạn chọn nhóm báo cáo');
                return;
            }
            var title = $("#title").val().trim();
            if (title == '' || title == null)
            {
                alert('Bạn phải điền tiêu đề cho báo cáo');
                return;
            }
            var query = $("#query").val().trim();
            if (query == '' || query == null)
            {
                alert('Bạn phải điền truy vấn cho báo cáo');
                return;
            }
            var x = screen.width / 2 - 700 / 2;
            var y = screen.height / 2 - 450 / 2;
//            window.open(meh.href, 'sharegplus', 'height=485,width=700,left=' + x + ',top=' + y);
//            alert(title+" "+query);
            childWindow = open(file, window, 'View báo cáo bằng truy vấn ', 'location=no');
//            var left = (screen.width / 2) - (w / 2);
//            var top = (screen.height / 2) - (h / 2);
//            window.open(file, window, 'location=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);

            if (childWindow.opener == null)
                childWindow.opener = self;
        }

    function isCheckInput()
        {
            try
            {
                var groupid = $.trim($("#group_id").val());
                var gradeid = '';
                $('input:checkbox[name=rptGrade]:checked').each(function () {
//                    allVals.push($(this).val());
                    gradeid = gradeid + $(this).val() + ',';
//                    alert(gradeid);
                });
//                alert(gradeid);
                if (groupid == '-1' || groupid == null || groupid == '' || groupid == ' ')
                {
                    alert('Bạn chọn nhóm báo cáo');
                    return;
                }
                var title = $.trim($("#title").val());
                if (title == '' || title == null)
                {
                    alert('Bạn phải điền tiêu đề cho báo cáo');
                    return;
                }

                var query = $.trim($("#query").val());
                if (query == '' || query == null)
                {
                    alert('Bạn phải điền truy vấn cho báo cáo');
                    return;
                }

                if (gradeid == '-1' || gradeid == null || gradeid == '' || gradeid == ' ')
                {
                    alert('Bạn chọn cấp xuất báo cáo');
                    return;
                }
                $('#divExportReportQuery').text('');
//                $("#idAbc").click();
                var sdata = {
                    "title": title,
                    "query": query,
                    "group_id": groupid,
                    "grade_id": gradeid
                };
                var data1 = JSON.stringify(sdata);
//                $.getJSON('addquerykt740', sdata, function (jsonResponse) {
//                    alert(jsonResponse.message);
//                })
//                        .success(function () {
////                            $('#divExportReportQuery').append('Bạn đã lưu báo cáo thành tông! ');
//                            $("#containBcttv").load('kt740/exp_query.jsp');
//                        })
//                        .error(function () {
////                            alert(jsonResponse.message);
//                            $('#divExportReportQuery').append('<h2 style="color: red">Lỗi bạn chưa lưu được báo cáo xin kiểm tra lại !</h2>');
//                        });
                $.ajax({
                    url: 'addquerykt740.action',
                    data: data1,
                    dataType: 'json',
                    contentType: 'application/json',
                    type: 'POST',
                    async: true,
                    success: function (res) {
                        alert('Bạn đã lưu báo cáo thành công ! '+res.message);
                        $("#containBcttv").load('kt740/exp_query.jsp');
                    },
                    error: function (res) {
                        //alert(res.message);
                        $('#divExportReportQuery').append('<h2 style="color: red">Lỗi bạn chưa lưu được báo cáo xin kiểm tra lại! '+res.message+'</h2>');
                    }
                });
            }
            catch (e)
            {
                alert(e.toString());
            }
        }
    </script>
    <style>
        #containPara{
            /*width: 12%;*/
            border-left: 1px solid;
            border-right: 1px solid;
            height: 450px;
            /*float: right;*/
            overflow: scroll;
        }

    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <div align="center">
            <h4>Thêm truy vấn</h4>
            <s:form id="idaddquery" name="nameaddquery" action="addquerykt740" theme="simple">
                <table border="1" cellspacing="0px" cellpadding="2px">
                    <tr>
                        <td width="width: 150px; height: 10px;">Chọn nhóm báo cáo: </td>
                            <td width="width: 650px; height: 10px;">   
                                <!--multiple="multiple"--> 
                                <select id="group_id" name="lstGroup" multiple="multiple">
                                <s:iterator value="lstObjGroup">
                                    <s:if test="group_id.contains(sKey)">
                                        <option value="<s:property value="sKey"/>" selected="selected"><s:property value="sDesc"/></option>
                                    </s:if>
                                    <s:else>
                                        <option value="<s:property value="sKey"/>"><s:property value="sDesc"/></option>
                                    </s:else>
                                </s:iterator>                                
                            </select>
                        </td>
                        <td rowspan="5" style="width: 250px;">
                                <div align="center" ><h3 style='color: red'>Danh sách các tham số</h3></div>
                                <div id="containPara">
                                    <div align="left">
                                        <table align="center" border="1" cellspacing="0px" cellpadding="1px">
                                            <tr align="left">
                                                <th>
                                            <h4 style='color: blue'>Tên tham số</h4>
                                            </th>
                                            <th>
                                            <h4 style='color: blue'> Mô tả</h4>
                                            </th>
                                            </tr>                                
                                        <s:iterator value="lstParaDesc">  
                                            <tr align="left">
                                                <td><s:property value="sKey"></s:property> </td>
                                                <td><s:property value="sDesc"></s:property> </td>
                                                </tr>
                                        </s:iterator>
                                    </table>
                                </div>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <td style="width: 150px; height: 10px;">
                            Nhập tiêu đề báo cáo
                        </td>
                        <td style="width: 650px; height: 10px;">
                            <s:textfield id="title" name="title" size="99"></s:textfield>
                        </td>                 
                            
                    </tr>
                    <tr>
                         <td style="width: 150px; height: 10px;">
                            Chọn cấp xuất báo cáo
                        </td>
                        <td style="width: 650px; height: 10px;">
                        <s:checkboxlist list="lstGrade" value="defaultGrade" listKey="sKey" listValue="sDesc"
                                        name="rptGrade"></s:checkboxlist>
                            </td>
                    </tr>
                    <tr>
                        <td style="width: 150px;">
                            Nhập query
                        </td>
                        <td style="width: 650px;">
                            <s:textarea id="query" name="query" label="Nhập query" cols="100" rows="24" ></s:textarea>
                            </td>
                        </tr>
                        <tr>
                            <td>
                            </td>
                            <td style="text-align: right">
                            <%--<s:url id="ViewReportQuery" action="viewReportQuery" />--%>
                            <%--<sj:submit id="idViewReportQuery" name="idViewReportQuery" value="Xem báo cáo" href="#" targets="divExportReportQuery" onclick="openChild('bctheotruyvan/load_view_rpt_query.jsp', 'win2')"></sj:submit>--%>
                            <%--<sj:a id="aViewRpt" formIds="genReportJasper" targets="divExportReport"  href="%{ViewReport}" indicator="loadingImage" onCompleteTopics="completeView" onBeforeTopics="beforeClick"></sj:a>--%>
                            <INPUT TYPE="button" VALUE="Xem bao cao"  onClick="openChild('kt740/load_view_rpt_query.jsp', 'win2')">
                            &nbsp|&nbsp
                            <%--<sj:submit id="idAbc" name="idAbc" value="Lưu báo cáo" targets="divExportReportQuery"></sj:submit>--%>
                            <INPUT TYPE="button" VALUE="Lưu báo cáo"  onClick="isCheckInput();">
                            </td>
                        </tr>
                    </table>
            </s:form>
        </div>
        <div id="containParm">
            <div id="divExportReportQuery"></div>
        </div>
    </body>
</html>