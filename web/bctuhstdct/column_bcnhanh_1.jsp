<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%--<%@taglib uri="/struts-dojo-tags" prefix="sx" %>--%>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<link href="css/bcn-style.css" type="text/css" rel="stylesheet" />
<%--<sj:head/>--%>
<script>
    //An nut s:submit
//    $("#sSubmit").hide();
//
//    function fireSubmit() {
//        $("#sSubmit").trigger("click");
//    }

    $.subscribe('saveComplete', function(event, data) {
//        alert("Lưu báo cáo thành công");
    });

    $.subscribe('beforediv1', function(event, data) {
        $("#viewReport").empty();
        $("#viewReport").hide();
        $("#loadingImageDiv").show();
    });

    $.subscribe('completediv1', function(event, data) {
        $("#loadingImageDiv").hide();
        $("#viewReport").show();
        //$("#contentDiv").slideDown('slow');
    });

    function getArrayList(namelist)
    {
        var array = new Array();
        var i = document.idViewReportFast.elements.length;
        for (var k = 0; k < i; k++)
        {
            alert(document.idViewReportFast.elements[k].name);
            if (document.idViewReportFast.elements[k].name == namelist)
            {
                array.push(document.idViewReportFast.elements[k].value);
                alert(document.idViewReportFast.elements[k].value);
            }
        }
        return array;
    }
    function popup()
    {
        var ht1 = screen.availHeight - 100;
        var wt1 = 1050;
        var left1 = (screen.width / 2) - (wt1 / 2);
        var top1 = 10;
        var arr1 =  $('#idrightColumnList').val();
        //getArrayList('rightColumnList');
//        arrrightcol = $('#idrightColumnList').val();
        var titlereport = $('#titlereport').val();
        var arr2= $('#idrightDateList').val();
                //getArrayList('rightDateList');
//        arrrightcolDate=$('#idrightDateList').val();
        console.log(arr1);
        console.log(arr2);
        if (titlereport == null || titlereport == '')
        {
            alert('Bạn phải điền tiêu đề cho báo cáo');
            return;
        }
//        if (arr1 == null || arr1.length == 0)
//        {
//            alert('Bạn phải chọn cột dữ liệu cho báo cáo');
//            return;
//        }
//        if (arr2 == null || arr2.length == 0)
//        {
//            alert('Bạn phải chọn cột dữ liệu ngày cho báo cáo');
//            return;
//        }
//        alert(arr);
//        var arr = getArrayList('rightColumnList');
//        console.log(arr);
        var url = "ViewReportFast.action?vbsprandom=" + Math.round(1000);
        //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
        var resize = window.open(url, "IMS_REPORTS", fullscreen = "yes");


    }
</script>

<!--<h3>Chọn tiêu chí lấy dữ liệu cho báo cáo nhanh </h3>-->
<!--<hr/>-->

<div class="report_group_form" style="height:auto; margin: 0 auto; align:center;">
    <s:form id="idViewReportFast" action="SaveReportFast" method="post" theme="simple">
        <s:hidden name="module_id"/>
        <s:div>
            <h3><s:property value="message" /> </h3>
        </s:div>
        <div id="titleReport">
            <table border="0">
                <tr>
                    <td width="150"><b>Tiêu đề báo cáo:</b></td>
                    <td width="400">
                        <s:textfield name="titlereport" id="titlereport" maxLength="100" size="100"/>  
                    </td>
                </tr>
            </table>
        </div>
        <div id="columnChose">
            <s:optiontransferselect     leftTitle="Tất cả các cột của báo cáo "
                                        headerKey="0"
                                        headerValue="----Liệt kê danh sách các cột-----"

                                        id="idleftColumnList"
                                        name="leftColumnList"
                                        list="leftModuleColumnList"
                                        listKey="sKey" 
                                        listValue="sDesc"

                                        rightTitle="Các cột đã chọn tạo báo cáo"
                                        doubleHeaderKey="0"
                                        doubleHeaderValue="----Liệt kê các column đã chọn-----"                                            
                                        
                                        doubleId="idrightColumnList"
                                        doubleList="rightModuleColumnList" 
                                        doubleName="rightColumnList" 
                                        >
            </s:optiontransferselect>
        </div>
        <div id="dateChose">
            <s:optiontransferselect
                leftTitle="Tất cả tham số ngày"                
                headerKey="0"
                headerValue="----Danh sách tham số----"

                name="leftDateList"
                list="leftModuleDateList"
                listKey="sKey" 
                listValue="sDesc"

                id="idleftDateList"
                doubleId="idrightDateList"
                rightTitle="Tham số đã chọn"
                doubleHeaderKey="0"
                doubleHeaderValue="----Tham số đã chọn-----"                                            

                doubleList="rightModuleDateList" 
                doubleName="rightDateList"
                >
            </s:optiontransferselect> 
        </div>
        <div id="whereReport">
            <table>
                <tr>
                    <td width="120"><b>Thêm điều kiện:</b></td>
                    <td width="400">
                        <s:textfield name="addwhere" maxLength="150" size="130"/>  
                    </td>
                </tr>
            </table>
        </div>
        <div id="buttonSaveView" align="right">
            <s:url id="urlSaveReport" value="ViewReportFast"></s:url>
            <sj:submit href="%{urlSaveReport}" value="Xem Thử Báo Cáo" 
                       onBeforeTopics="beforediv1"
                       onCompleteTopics="completediv1" onclick="popup()"></sj:submit>


            <sj:submit id="sSave"  value="Lưu Báo Cáo" 
                       targets="viewReport" 
                       onCompleteTopics="saveComplete"></sj:submit>

            </div>                    
    </s:form>

    <div id="loadingImageDiv" style="display: none;">
        <img id="loadingImage" src='img/loading.gif' border='0' >
    </div>
    <div id="viewReport" ></div>

</div>
