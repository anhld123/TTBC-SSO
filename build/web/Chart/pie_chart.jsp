<%-- 
    Document   : pie_chart
    Created on : Nov 22, 2018, 11:07:55 AM
    Author     : Trung Nguyen
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@taglib prefix="sj" uri="/struts-jquery-tags" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Google Chart - Struts 2</title>
        <sj:head jqueryui="true" jquerytheme="smoothness"/> 
        <script type="text/javascript" src="https://www.google.com/jsapi"></script>
        <script type="text/javascript">
            // Load the Visualization API and the piechart package.
            google.load('visualization', '1.0', {'packages': ['corechart']});
            // Set a callback to run when the Google Visualization API is loaded.
            //google.setOnLoadCallback(drawChart);
            //google.setOnLoadCallback(drawChart2);
            // Callback that creates and populates a data table,
            // instantiates the pie chart, passes in the data and
            // draws it.
//            function drawChart() {
//                // Create the data table.    
//                var data = google.visualization.arrayToDataTable(
//                    [
//                [ 'Country', 'Area(square km)' ],
//                <bean name="vbsp.ims.chart.PieChartAction" var="pieData" />
//                <property value="#pieData.pieChartData" />
//                ]  
//                );
//                // Set chart options
//                var options = {
//                    'title': 'Area-wise Top Seven Countries in the World',
//                    is3D: true,
//                    pieSliceText: 'label',
//                    tooltip: {
//                        showColorCode: true
//                    },
//                    'width': 600,
//                    'height': 300
//                };
//                // Instantiate and draw our chart, passing in some options.
//                var chart = new google.visualization.PieChart(document.getElementById('chart_div'));
//                chart.draw(data, options);
//            }

//            function drawChart2() {
//                var data = google.visualization.arrayToDataTable([
//                    ['Language', 'Speakers (in millions)'],
//                    ['German', 5.85],
//                    ['French', 1.66],
//                    ['Italian', 0.316],
//                    ['Romansh', 0.0791]
//                ]);
//
//                var options = {
//                    legend: 'none',
//                    pieSliceText: 'label',
//                    title: 'Swiss Language Use (100 degree rotation)',
//                    pieStartAngle: 100,
//                };
//
//                var chart = new google.visualization.PieChart(document.getElementById('piechart'));
//                chart.draw(data, options);
//            }

            $(function(){
                $("#introForm").submit(function(){                    
                    var formInput = $(this).serialize();
                    $.getJSON('ajax/getChartData.action', formInput, function(data) {
                        $('#result').html("<ul id='dataTable'></ul>");
                        var list = $('#dataTable');                           
                        list.append('<li>' + data.jsonData +'</li>\n');                        
                        var array = JSON.parse(data.jsonData);
                        var chartData = google.visualization.arrayToDataTable(array);                        
                        // Set chart options
                        var options = {
                            'title': data.title,
                            is3D: data.is3D,
                            pieSliceText: 'label',
                            tooltip: {
                                showColorCode: data.showColorCode
                            },
                            'width': data.chartWidth,
                            'height': data.chartHeight
                        };
                        // Instantiate and draw our chart, passing in some options.
                        //alert(data.chartWidth + '~' + data.chartHeight);
                        $("#chart_div").width(data.chartWidth).height(data.chartHeight);
                        var chart = new google.visualization.PieChart(document.getElementById('chart_div'));                        
                        chart.draw(chartData, options);
                    });
                    return false;
                });
            }
            );
        </script>
    </head>
    <body>
        <form action="" id="introForm">
            <table cellspacing="0" cellpadding="5" style="width: 50%;">
                <tr>
                    <td><label for="title">Title</label></td>
                    <td><input name="title"></td>
                </tr>
                <tr>
                    <td>
                        <label for="is3D">is3D</label>
                    </td>
                    <td>                        
                        <input name="is3D" type="checkbox" value="true">
                    </td>
                </tr>
                <tr>
                    <td><label for="pieSliceText">Pie Slice Text</label></td>
                    <td><input name="pieSliceText" value="'label'"></td>
                </tr>
                <tr>
                    <td><label for="name">Show Color Code</label></td>
                    <td><input name="showColorCode" type="checkbox" value="true"></td>
                </tr>
            
                <tr>
                    <td><label for="chartWidth">Width</label></td>
                    <td><input name="chartWidth" value="600"></td>
                </tr>
                
                <tr>
                    <td><label for="chartHeight">Height</label></td>
                    <td><input name="chartHeight" value="300"></td>
                </tr>                            
                <tr>
                    <td colspan="2" style="text-align: right;">
                        <input type="submit" value="Hiển thị">
                    </td>
                </tr>            
            </table>
            <div id="result" class="result ui-widget-content ui-corner-all">Click on the button above.</div>
            <div style="width: 600px; height: 500px;" id="chart_div"></div>
        </form>
    </body>
</html>
