<%-- 
    Document   : edit_query
    Created on : Jul 12, 2014, 10:26:52 PM
    Author     : LION
--%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/css/jquery.multiselect.css" />
        <link rel="stylesheet" type="text/css" href="css/css/style.css" />
        <link rel="stylesheet" type="text/css" href="css/css/prettify.css" />
        <link rel="stylesheet" type="text/css" href="css/css/jquery-ui.css" />
        <script type="text/javascript" src="js/js/jquery.js"></script>
        <script type="text/javascript" src="js/js/jquery-ui.min.js"></script>
        <script type="text/javascript" src="js/js/jquery.multiselect.js"></script>
        <script type="text/javascript" src="js/js/prettify.js"></script>
    </head>
    <script type="text/javascript">
        $('#messageDiv').text('');
    </script>
    <script>
        $(document).ready(function () {

            $("#group_id").multiselect({
                selectedList: 1
            });
        });
        function openChild(file, window) {

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
                var save_id = $.trim($("#save_id").val());
                var gradeid = '';
                //lay ra tat ca cac checklistbox da duoc check voi ten rptGrade
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
                $('#divExportReport').text('');
                //                do su dung json nen phai dua ve đung dinh dang
                var sdata = {
                    "title": title,
                    "query": query,
                    "group_id": groupid,
                    "grade_id": gradeid,
                    "save_id": save_id
                };
                
                var data1 = JSON.stringify(sdata);
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
                        $('#divExportReport').append('<h2 style="color: red">Lỗi bạn chưa lưu được báo cáo xin kiểm tra lại! '+res.message+'</h2>');
                    }
                });
//                $.getJSON('addquerykt740', sdata, function (jsonResponse) {
//                    alert(jsonResponse.message);
//                })
//                        .success(function () {
////                            alert('Bạn đã lưu báo cáo thành công !');
//                            $("#containBcttv").load('kt740/exp_query.jsp');
//                        })
//                        .error(function () {
//                            //                            alert(jsonResponse.message);
//                            $('#divExportReport').append('<h2 style="color: red">Lỗi bạn chưa lưu được báo cáo do bạn không phải là người tạo bao cáo xin kiểm tra lại!</h2>');
//                        });
            } catch (e)
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
    <body>
        <div align="center">
            <h2 style="color: red">Sửa báo cáo!</h2>
            <s:form id="addquerykt740" name="nameaddquery" action="addquerykt740" theme="simple">
                <s:hidden name="save_id" id="save_id"></s:hidden>
                    <table border="1" cellspacing="0px" cellpadding="2px">
                        <tr>
                            <td width="width: 150px; height: 10px;">Chọn nhóm báo cáo: </td>
                            <td width="width: 650px; height: 10px;">   
                                <select id="group_id" multiple="multiple" name="lstGroup">
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
                        <td style="width: 150px; height: 10px">
                            Nhập tiêu đề báo cáo
                        </td>
                        <td style="width: 650px; height: 10px">
                            <s:textfield id="title" name="title" label="Nhập tiêu đề báo cáo" size="100"></s:textfield>
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
                            <td style="text-align: right;">
                            </td>
                            <td style="text-align: right">
                                <INPUT TYPE="button" VALUE="Xem bao cao"  onClick="openChild('kt740/load_view_rpt_query.jsp', 'win2')">
                                &nbsp|&nbsp
                            <%--<sj:submit id="idAbc" name="idAbc" value="Lưu báo cáo" targets="divExportReport"/>--%>
                             <INPUT TYPE="button" VALUE="Lưu báo cáo"  onClick="isCheckInput();">  
                        </td>
                    </tr>
                </table>

                <div id="containParm">
                    <div id="divExportReport"></div>
                </div>
            </s:form> 
        </div>
    </body>
</html>
