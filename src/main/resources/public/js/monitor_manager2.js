/**
 * Created by lzz on 2017/8/4.
 */

window.STATIC_URL = "http://localhost:8080/";
window.SCRIPT_FILE = window.STATIC_URL + "jstpl/";
set_server_arg(1460194057, "v0.2", true, 1);



// in order to set theme for a chart, all you need to include theme file
// located in amcharts/themes folder and set theme property for the chart.

var chart1;
var chart2;

// Theme can only be applied when creating chart instance - this means
// that if you need to change theme at run time, youhave to create whole
// chart object once again.

function makeCharts(theme, bgColor, bgImage) {

    if (chart1) {
        chart1.clear();
    }
    if (chart2) {
        chart2.clear();
    }

    // background
    if (document.body) {
        document.body.style.backgroundColor = bgColor;
        document.body.style.backgroundImage = "url(" + bgImage + ")";
    }

    // column chart
    chart1 = AmCharts.makeChart("chartdiv1", {
        type: "serial",
        theme: theme,
        dataProvider: [{
            "year": 2005,
            "income": 23.5,
            "expenses": 18.1
        }, {
            "year": 2006,
            "income": 26.2,
            "expenses": 22.8
        }, {
            "year": 2007,
            "income": 30.1,
            "expenses": 23.9
        }, {
            "year": 2008,
            "income": 29.5,
            "expenses": 25.1
        }, {
            "year": 2009,
            "income": 24.6,
            "expenses": 25
        }],
        categoryField: "year",
        startDuration: 1,

        categoryAxis: {
            gridPosition: "start"
        },
        valueAxes: [{
            title: "Million USD"
        }],
        graphs: [{
            type: "column",
            title: "Income",
            valueField: "income",
            lineAlpha: 0,
            fillAlphas: 0.8,
            balloonText: "[[title]] in [[category]]:<b>[[value]]</b>"
        }, {
            type: "line",
            title: "Expenses",
            valueField: "expenses",
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
    chart2 = AmCharts.makeChart("chartdiv2", {
        type: "pie",
        theme: theme,
        dataProvider: [{
            "country": "Czech Republic",
            "litres": 156.9
        }, {
            "country": "Ireland",
            "litres": 131.1
        }, {
            "country": "Germany",
            "litres": 115.8
        }, {
            "country": "Australia",
            "litres": 109.9
        }, {
            "country": "Austria",
            "litres": 108.3
        }, {
            "country": "UK",
            "litres": 65
        }, {
            "country": "Belgium",
            "litres": 50
        }],
        titleField: "country",
        valueField: "litres",
        balloonText: "[[title]]<br><b>[[value]]</b> ([[percents]]%)",
        legend: {
            align: "center",
            markerType: "circle"
        }
    });

}

var data = {};
data["a"] = 1;
smarty.post( "/monitor/list2", JSON.stringify(data), "monitor_list2","monitor-list-div",function () {
    makeCharts("light", "#FFFFFF");
});
