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
<!--    <script src="js/google-chart.js"></script>-->
    <script type="text/javascript">
      google.charts.load('current', {'packages':['corechart']});
      google.charts.setOnLoadCallback(drawChart);
      
      google.charts.load('current', {'packages':['bar']});
      google.charts.setOnLoadCallback(drawChart1);
      
      google.charts.setOnLoadCallback(drawChart4);
      google.charts.setOnLoadCallback(drawChart3);
      
      google.charts.load('current', {'packages':['line']});
      google.charts.setOnLoadCallback(drawChart2);
      
      google.charts.load('current', {packages: ['corechart', 'bar']});
      google.charts.setOnLoadCallback(drawMultSeries);
      

      function drawChart() {

        var data = google.visualization.arrayToDataTable(${data_chart});

        var options = {
          title: 'Dư nợ xấu theo vùng kinh tế'
        };

        var chart = new google.visualization.PieChart(document.getElementById('piechart'));
        
        chart.draw(data, options);
        
      }
      
      function drawChart1() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Nợ trong hạn', 'Nợ quá hạn', 'Nợ khoanh'],
          ['2012', 1120, 400, 200],
          ['2013', 1000, 400, 200],
          ['2014', 1000, 400, 200],
          ['2015', 1170, 460, 250],
          ['2016', 660, 1120, 300],
          ['2017', 1030, 540, 350]
        ]);
        
        var options = {
          chart: {
            title: 'Dư nợ xấu',
            subtitle: 'Nợ trong hạn, nợ quá hạn, and nợ khoanh: 2012-2017',
          },
          vAxis: {format: 'decimal'}
//          vAxis: {format:'###,###'}
        };
        
//        options['vAxis']['format'] = 'decimal'
//        options['vAxis']['format'] = {format: 'decimal'};
//         optionsvAxis: {format: 'decimal'};
        var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
//        options.hAxis.format = 'decimal'
//        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
      
      function drawChart3() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Tổng dư nợ'],
          ['2012', 1120],
          ['2013', 1000],
          ['2014', 1000],
          ['2015', 1170],
          ['2016', 660],
          ['2017', 1030]
        ]);
        
        var options = {
          chart: {
            title: 'Dư nợ xấu',
            subtitle: 'Nợ trong hạn, nợ quá hạn, and nợ khoanh: 2012-2017',
          },
          vAxis: {format: 'decimal'}
//          vAxis: {format:'###,###'}
        };
        
//        options['vAxis']['format'] = 'decimal'
//        options['vAxis']['format'] = {format: 'decimal'};
//         optionsvAxis: {format: 'decimal'};
        var chart = new google.charts.Bar(document.getElementById('columnchart_material1'));

        chart.draw(data, google.charts.Bar.convertOptions(options));
//        options.hAxis.format = 'decimal'
//        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
      
      function drawChart4() {
        var data = google.visualization.arrayToDataTable([
          ['Year', 'Tổng dư nợ'],
          ['Hộ nghèo', 1120],
          ['Cận nghèo', 1000],
          ['HSSV', 1000],
          ['SKLĐ', 1170],
          ['NS&VSMT', 660],
          ['Nhà 167', 1030]   ,      
          ['Hộ nghèo', 1120],
          ['Cận nghèo', 1000],
          ['HSSV', 1000],
          ['SKLĐ', 1170],
          ['NS&VSMT', 660],
          ['Hộ nghèo', 1120],
          ['Cận nghèo', 1000],
          ['HSSV', 1000],
          ['SKLĐ', 1170],
          ['NS&VSMT', 660]
//          ['Nhà 167', 1030]
        ]);
        
        var options = {
          chart: {
            title: 'Dư nợ xấu',
            subtitle: 'Nợ trong hạn, nợ quá hạn, and nợ khoanh: 2012-2017',
          },
          vAxis: {format: 'decimal'},   //format number
          colors: ['#9575cd'],
//          hAxis: {format: 'none'}
//            fontSize: 8,  //font size
            hAxis: {showTextEvery: 0, slantedText: true, slantedTextAngle: 45}  //text X xoay 45 độ
        };
        
//        options['vAxis']['format'] = 'decimal'
//        options['vAxis']['format'] = {format: 'decimal'};
//         optionsvAxis: {format: 'decimal'};

        var chart = new google.visualization.ColumnChart(
        document.getElementById('columnchart_4'));

      chart.draw(data, options);
//        var chart = new google.charts.Bar(document.getElementById('columnchart_4'));
//
//        chart.draw(data, google.charts.Bar.convertOptions(options));
////        options.hAxis.format = 'decimal'
//        chart.draw(data, google.charts.Bar.convertOptions(options));
      }
      
      function drawChart2() {

      var data = new google.visualization.DataTable();
      data.addColumn('number', 'Day');
      data.addColumn('number', 'Guardians of the Galaxy');
      data.addColumn('number', 'The Avengers');
      data.addColumn('number', 'Transformers: Age of Extinction');

      data.addRows([
//        [2004,  37.8, 80.8, 41.8],
//        [2005,  30.9, 69.5, 32.4],
//        [2006,  25.4,   57, 25.7],
//        [2007,  11.7, 18.8, 10.5],
//        [2008,  11.9, 17.6, 10.4],
//        [2009,   8.8, 13.6,  7.7],
//        [2010,   7.6, 12.3,  9.6],
        [2011,  12.3, 29.2, 10.6],
        [2012,  16.9, 42.9, 14.8],
        [2013, 12.8, 30.9, 11.6],
        [2014,  5.3,  7.9,  4.7],
        [2015,  6.6,  8.4,  5.2],
        [2016,  4.8,  6.3,  3.6],
        [2017,  4.2,  6.2,  3.4]
      ]);

      var options = {
        chart: {
          title: 'Box Office Earnings in First Two Weeks of Opening',
          subtitle: 'in millions of dollars (USD)'
        },
        hAxis: {format: ''},
//        pointSize: 3,
        width: 900,
        height: 500
      };
      
      var lineChart = new google.visualization.LineChart(document.getElementById('linechart_material'));
                    lineChart.draw(data, options);

//      var chart = new google.charts.Line(document.getElementById('linechart_material'));
//
//      chart.draw(data, google.charts.Line.convertOptions(options));
    }
    
    function drawMultSeries() {
       var data = google.visualization.arrayToDataTable([
         ['Element', 'Density', { role: 'style' }],
         ['Copper', 8.94, '#b87333'],            // RGB value
         ['Silver', 10.49, 'silver'],            // English color name
         ['Gold', 19.30, 'silver'],

       ['Platinum', 21.45, 'silver' ], // CSS-style declaration
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
        ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
       ['Platinum1', 21.45, 'silver' ],
      ]);
      var options = {
        title: 'Motivation and Energy Level Throughout the Day',
//        hAxis: {
//          title: 'Time of Day',
//          format: 'h:mm a',
//          viewWindow: {
//            min: [7, 30, 0],
//            max: [17, 30, 0]
//          }
//        },
//        vAxis: {
//          title: 'Rating (scale of 1-10)'
//        }
      };

      var chart = new google.visualization.ColumnChart(
        document.getElementById('chart_div_21'));

      chart.draw(data, options);
    }
      
    </script>
  </head>
    <body>        
        <!--<div id="columnchart_4" style="width: 1000px; height: 500px;"></div>-->
        <div id="piechart" style="width: 900px; height: 500px;"></div>
        <!--<div id="columnchart_material" style="width: 800px; height: 500px;"></div>-->
        <!--<div id="columnchart_material1" style="width: 800px; height: 500px;"></div>-->

        <!--<div id="linechart_material" style="width: 800px; height: 500px;"></div>-->

        <!--<div id="chart_div_21" style="width: 800px; height: 500px;"></div>--> 
        
    </body>
</html>
