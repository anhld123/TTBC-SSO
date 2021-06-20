<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s" %>
<%@taglib uri="/struts-dojo-tags" prefix="sx" %>
<%@taglib uri="/struts-jquery-tags" prefix="sj" %>

<link href="css/bcn-style.css" type="text/css" rel="stylesheet" />
<sj:head/>
<script>
    //An nut s:submit
    $("#sSubmit").hide();

    function fireSubmit() {
        $("#sSubmit").trigger("click");
    }

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
        var pos_cd = '';
        var i = document.idViewReportFast.elements.length;
        for (var k = 0; k < i; k++)
        {
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
        var arr1 = new Array();
        arr1 = $('#idrightColumnList').val();

        var arr2 = new Array();
        arr2 = $('#idrightDateList').val();
//        alert(arr1);
//        var arr = getArrayList('rightColumnList');
        console.log(arr1);
        console.log(arr2);
//        var titlereport = $('#titlereport').val();
//        if (titlereport == null || titlereport == '')
//        {
//            alert('Bạn phải điền tiêu đề cho báo cáo');
//            return;
//        }
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
//        
//        
//        var url = "ViewReportFast.action?vbsprandom=" + Math.round(1000);
//        //cong them chuoi doan "&namBc="+namBc de lay nam bao cao
//       
//        var resize = window.open(url, "IMS_REPORTS", fullscreen = "yes");
//         $('#titlereport').val(titlereport);
//        
//        $('#idrightColumnList').val(arr1);
//        $('#idrightDateList').val(arr2);

    }
</script>

<!--<h3>Chọn tiêu chí lấy dữ liệu cho báo cáo nhanh </h3>-->
<!--<hr/>-->

<div class="report_group_form" style="height:auto; margin: 0 auto; align:center;">
    <s:form id="idViewReportFast" name="idViewReportFast" action="SaveReportFast" method="post" theme="simple">
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

                                        name="leftColumnList"
                                        list="leftModuleColumnList"
                                        listKey="sKey" 
                                        listValue="sDesc"

                                        rightTitle="Các cột đã chọn tạo báo cáo"
                                        doubleHeaderKey="0"
                                        doubleHeaderValue="----Liệt kê các column đã chọn-----"                                            
                                        id="idleftColumnList"
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

                id="idleftDateList"
                name="leftDateList"
                list="leftModuleDateList"
                listKey="sKey" 
                listValue="sDesc"

                rightTitle="Tham số đã chọn"
                doubleHeaderKey="0"
                doubleHeaderValue="----Tham số đã chọn-----"                                            

                doubleId="idrightDateList"
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
            <s:submit id="sSubmit"></s:submit>
            <s:url id="urlSaveReport" value="ViewReportFast"></s:url>
            <sj:submit href="%{urlSaveReport}" targets="viewReport" value="Xem Thử Báo Cáo" onmouseover="fireSubmit()"
                       onBeforeTopics="beforediv1"
                       onCompleteTopics="completediv1" onclick="popup()"></sj:submit>


            <sj:submit id="sSave" value="Lưu Báo Cáo" 
                       targets="viewReport" onmouseover="fireSubmit()"
                       onCompleteTopics="saveComplete"></sj:submit>

            </div>                    
    </s:form>

    <div id="loadingImageDiv" style="display: none;">
        <img id="loadingImage" src='img/loading.gif' border='0' >
    </div>
    <sx:div id="viewReport" executeScripts="true" ></sx:div>

</div>
