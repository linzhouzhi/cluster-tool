/**
 * Created by lzz on 2017/8/4.
 */

window.STATIC_URL = "http://localhost:8080/";
window.SCRIPT_FILE = window.STATIC_URL + "jstpl/";
set_server_arg(1460194057, "v0.2", true, 1);


makeCharts("light", "#FFFFFF","used_cpu_sys");

// Theme can only be applied when creating chart instance - this means
// that if you need to change theme at run time, youhave to create whole
// chart object once again.

function makeCharts(theme, bgColor, field) {
    var len = window.chartData.length;
    var char3_data = [];
    var char_data_table = [];
    var j = 0;
    if(len > 10){
        for(var i = (len - 10); i < len; i++ ){
            char_data_table[j] = window.chartData[i];
            j++;
        }
    }else{
        char_data_table = window.chartData;
    }
    var k = 0;
    if(len > 5){
        for(var i = (len - 5); i < len; i++ ){
            char3_data[k] = window.chartData[i];
            k++;
        }
    }else{
        char3_data = window.chartData;
    }


    var chart1;
    var chart2;
    var chart3;

    if (chart1) {
        chart1.clear();
    }
    if (chart2) {
        chart2.clear();
    }
    if (chart3) {
        chart3.clear();
    }

    // background
    if (document.body) {
        document.body.style.backgroundColor = bgColor;
    }

    // column chart
    chart1 = AmCharts.makeChart("chartdiv_table1", {
        type: "serial",
        theme: theme,
        dataProvider: char_data_table,
        categoryField: "date",
        startDuration: 1,

        categoryAxis: {
            gridPosition: "start"
        },
        valueAxes: [{
            title: "Million USD"
        }],
        graphs: [{
            type: "column",
            title: field,
            valueField: field,
            lineAlpha: 0,
            fillAlphas: 0.8,
            balloonText: "[[title]] in [[category]]:<b>[[value]]</b>"
        }],
        legend: {
            useGraphSettings: true
        }

    });

    chart2 = AmCharts.makeChart("chartdiv_table2", {
        type: "serial",
        theme: theme,
        dataProvider: char_data_table,
        categoryField: "date",
        startDuration: 1,

        categoryAxis: {
            gridPosition: "start"
        },
        valueAxes: [{
            title: "Million USD"
        }],
        graphs: [{
            type: "line",
            title: field,
            valueField: field,
            lineThickness: 2,
            fillAlphas: 0,
            bullet: "round",
            balloonText: "[[title]] in [[category]]:<b>[[value]]</b>"
        }],
        legend: {
            useGraphSettings: true
        }

    });

    // pie chart
    chart3 = AmCharts.makeChart("chartdiv_table3", {
        type: "pie",
        theme: theme,
        dataProvider: char3_data,
        titleField: "date",
        valueField: field,
        balloonText: "[[title]]<br><b>[[value]]</b> ([[percents]]%)",
        legend: {
            align: "center",
            markerType: "circle"
        }
    });
}

// 设置一下最后一个元素的效果
var char_data = window.chartData;
var data_len = char_data.length;
char_data[ data_len -1 ].bulletClass = "lastBullet";
char_data[ data_len -1 ].alpha = "0.4";
var chart;
AmCharts.ready(function () {
    // SERIAL CHART
    chart = new AmCharts.AmSerialChart();
    chart.addClassNames = true;
    chart.dataProvider = char_data;
    chart.categoryField = "date";
    chart.startused_cpu_sys = 1;
    chart.color = "#000000";
    chart.marginLeft = 0;
    chart.addListener("dataUpdated", function () {
        if( char_data.length > 10 ){
            chart.zoomToIndexes(char_data.length - 10, char_data.length);
        }
    });


    // as we have data of different units, we create three different value axes
    // connected_clients value axis
    var connected_clientsAxis = new AmCharts.ValueAxis();
    connected_clientsAxis.title = "connected_clients";
    connected_clientsAxis.gridAlpha = 0;
    connected_clientsAxis.axisAlpha = 0;
    chart.addValueAxis(connected_clientsAxis);

    // used_memory_peak value axis
    var used_memory_peakAxis = new AmCharts.ValueAxis();
    used_memory_peakAxis.gridAlpha = 0;
    used_memory_peakAxis.axisAlpha = 0;
    used_memory_peakAxis.title = "used_cpu_sys and used_memory_peak";
    used_memory_peakAxis.offset = 83;
    used_memory_peakAxis.titleOffset = 10;
    used_memory_peakAxis.position = "right";
    chart.addValueAxis(used_memory_peakAxis);

    // used_cpu_sys value axis
    var used_cpu_sysAxis = new AmCharts.ValueAxis();
    // the following line makes this value axis to convert values to used_cpu_sys
    // it tells the axis what used_cpu_sys unit it should use. mm - minute, hh - hour...
    used_cpu_sysAxis.gridAlpha = 0;
    used_cpu_sysAxis.axisAlpha = 0;
    used_cpu_sysAxis.inside = false;
    used_cpu_sysAxis.position = "right";
    chart.addValueAxis(used_cpu_sysAxis);

    // GRAPHS
    // connected_clients graph
    var connected_clientsGraph = new AmCharts.AmGraph();
    connected_clientsGraph.valueField = "connected_clients";
    connected_clientsGraph.title = "connected_clients";
    connected_clientsGraph.type = "column";
    connected_clientsGraph.fillAlphas = 0.9;
    connected_clientsGraph.valueAxis = connected_clientsAxis; // indicate which axis should be used
    connected_clientsGraph.balloonText = "[[value]] miles";
    connected_clientsGraph.legendValueText = "[[value]] mi";
    connected_clientsGraph.legendPeriodValueText = "total: [[value.sum]] mi";
    connected_clientsGraph.lineColor = "#5bc0de";
    connected_clientsGraph.alphaField = "alpha";
    chart.addGraph(connected_clientsGraph);

    // used_memory_peak graph
    var used_memory_peakGraph = new AmCharts.AmGraph();
    used_memory_peakGraph.valueField = "used_memory_peak";
    used_memory_peakGraph.id = "g1";
    used_memory_peakGraph.classNameField = "bulletClass";
    used_memory_peakGraph.title = "used_memory_peak/city";
    used_memory_peakGraph.type = "line";
    used_memory_peakGraph.valueAxis = used_memory_peakAxis; // indicate which axis should be used
    used_memory_peakGraph.lineColor = "#f0ad4e";
    used_memory_peakGraph.lineThickness = 1;
    used_memory_peakGraph.bullet = "round";
    //used_memory_peakGraph.bulletSizeField = "used_memory_peak"; // indicate which field should be used for bullet size
    used_memory_peakGraph.bulletBorderColor = "#efe948";
    used_memory_peakGraph.bulletBorderAlpha = 1;
    used_memory_peakGraph.bulletBorderThickness = 2;
    used_memory_peakGraph.bulletColor = "#000000";
    used_memory_peakGraph.labelText = "[[used_memory_peak]]"; // not all data points has townName2 specified, that's why labels are displayed only near some of the bullets.
    used_memory_peakGraph.labelPosition = "right";
    used_memory_peakGraph.balloonText = "used_memory_peak:[[value]]";
    used_memory_peakGraph.showBalloon = true;
    used_memory_peakGraph.animationPlayed = true;
    chart.addGraph(used_memory_peakGraph);

    // used_cpu_sys graph
    var used_cpu_sysGraph = new AmCharts.AmGraph();
    used_cpu_sysGraph.id = "g2";
    used_cpu_sysGraph.title = "used_cpu_sys";
    used_cpu_sysGraph.valueField = "used_cpu_sys";
    used_cpu_sysGraph.type = "line";
    used_cpu_sysGraph.valueAxis = used_cpu_sysAxis; // indicate which axis should be used
    used_cpu_sysGraph.lineColor = "#ff5755";
    used_cpu_sysGraph.balloonText = "[[value]]";
    used_cpu_sysGraph.lineThickness = 1;
    used_cpu_sysGraph.legendValueText = "[[value]]";
    used_cpu_sysGraph.bullet = "square";
    used_cpu_sysGraph.bulletBorderColor = "#ff5755";
    used_cpu_sysGraph.bulletBorderThickness = 1;
    used_cpu_sysGraph.bulletBorderAlpha = 1;
    used_cpu_sysGraph.dashLengthField = "dashLength";
    used_cpu_sysGraph.animationPlayed = true;
    chart.addGraph(used_cpu_sysGraph);

    // CURSOR
    var chartCursor = new AmCharts.ChartCursor();
    chartCursor.zoomable = false;
    chartCursor.categoryBalloonDateFormat = undefined;
    chartCursor.cursorAlpha = 0;
    chartCursor.valueBalloonsEnabled = false;
    chartCursor.valueLineBalloonEnabled = true;
    chartCursor.valueLineEnabled = true;
    chartCursor.valueLineAlpha = 0.5;
    chart.addChartCursor(chartCursor);

    // SCROLLBAR
    var chartScrollbar = new AmCharts.ChartScrollbar();
    chart.addChartScrollbar(chartScrollbar);

    // LEGEND
    var legend = new AmCharts.AmLegend();
    legend.bulletType = "round";
    legend.equalWidths = false;
    legend.valueWidth = 120;
    legend.useGraphSettings = true;
    legend.color = "#000000";
    chart.addLegend(legend);

    // WRITE
    chart.write("chartdiv");
});

$("#field-title > th").click(function () {
    var field = $(this).data("field");
    $("#field-title > th").removeClass("selected");
    $(this).addClass("selected");
    makeCharts("light", "#FFFFFF", field);
});
/*
var data = {};
data["date"] = "hour";
data["type"] = "sum";
smarty.html( "monitor_list2", JSON.stringify(data), "monitor-list-div",function () {
    $.ajax({
        url:'/monitor/monitor_info?type=' + data.type + "&&date=" + data.date,
        type:'GET',
        contentType: 'application/json',
        async:true,
        timeout:5000,
        success:function(data){
            window.chartData = data.result;
            console.log( data.result );
            //amcharts();
        },
        error: function (data) {
            sparrow_win.msg( "刷新失败！", {icon:5} );
        }
    });
});
*/

