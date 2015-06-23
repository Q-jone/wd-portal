$(function () {
    $("#passCapTotalChart").highcharts({
        chart: {
            plotBackgroundColor: null,
            backgroundColor:null,
            plotBorderWidth: 0,
            plotShadow: false,
            width:290,
            height:300
        },
        credits: {
            enabled: false
        },
        title: {
            text: null,
            align: 'center',
            verticalAlign: 'middle',
            y: 50
        },
        tooltip: {
            pointFormat: '{point.name}: <b>{point.y}</b>'
        },
        series: [
            {
                type:'pie',
                size: '85%',
                innerSize: '80%',
                data: [
                    {y: 27, color: 'blue',name:'去年'}
                ],
                borderColor: 'white',
                borderWidth: 0,
                startAngle:120,
                endAngle:360,
                dataLabels: {
                    x:250,
                    y:20,
                    useHTML: true,
                    formatter: function() {
                        return this.point.name + '<br>'+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: 'blue',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            },
            {
                type: 'pie',
                data: [
                    {y:200,name:'管控值',color:'#6495ED'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:90,
                endAngle:360,
                dataLabels: {
                    x:240,
                    y:-110,
                    useHTML: true,
                    formatter: function() {
                        return '100% '+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: '#6495ED',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }

            },
            {
                type: 'pie',
                data: [ {y:17.34,name:'实际',color:'#008B00'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:180,
                endAngle:360,
                dataLabels: {
                    x:190,
                    y:-10,
                    useHTML: true,
                    formatter: function() {
                        return '超计划<br>' + this.point.name + ''+this.y+'亿<br>15.43';
                    },

                    connectorWidth: 0,
                    color: '#008B00',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            },
            {
                type: 'pie',
                data: [ {y:180,name:'计划',color:'#FFD700'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:200,
                endAngle:360,
                dataLabels: {
                    y:100,
                    x:50,
                    useHTML: true,
                    formatter: function() {
                        return  this.point.name + '<br>12.12%<br>'+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: '#FFD700',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            }]
    }, function (chart) { // on complete

        chart.renderer.text('', 220, 200)
            .css({
                color: '#4572A7',
                fontSize: '16px'
            })
            .add();

    });

    $("#incomeTotalChart").highcharts({
        chart: {
            plotBackgroundColor: null,
            backgroundColor:null,
            plotBorderWidth: 0,
            plotShadow: false,
            width:290,
            height:300
        },
        credits: {
            enabled: false
        },
        title: {
            text: null,
            align: 'center',
            verticalAlign: 'middle',
            y: 50
        },
        tooltip: {
            pointFormat: '{point.name}: <b>{point.y}</b>'
        },
        series: [
            {
                type:'pie',
                size: '85%',
                innerSize: '80%',
                data: [
                    {y: 27, color: 'blue',name:'去年'}
                ],
                borderColor: 'white',
                borderWidth: 0,
                startAngle:120,
                endAngle:360,
                dataLabels: {
                    x:250,
                    y:20,
                    useHTML: true,
                    formatter: function() {
                        return this.point.name + '<br>'+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: 'blue',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            },
            {
                type: 'pie',
                data: [
                    {y:200,name:'管控值',color:'#6495ED'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:90,
                endAngle:360,
                dataLabels: {
                    x:240,
                    y:-110,
                    useHTML: true,
                    formatter: function() {
                        return '100% '+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: '#6495ED',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }

            },
            {
                type: 'pie',
                data: [ {y:17.34,name:'实际',color:'#008B00'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:180,
                endAngle:360,
                dataLabels: {
                    x:190,
                    y:-10,
                    useHTML: true,
                    formatter: function() {
                        return '超计划<br>' + this.point.name + ''+this.y+'亿<br>15.43';
                    },

                    connectorWidth: 0,
                    color: '#008B00',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            },
            {
                type: 'pie',
                data: [ {y:180,name:'计划',color:'#FFD700'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:200,
                endAngle:360,
                dataLabels: {
                    y:100,
                    x:50,
                    useHTML: true,
                    formatter: function() {
                        return  this.point.name + '<br>12.12%<br>'+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: '#FFD700',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            }]
    }, function (chart) { // on complete

        chart.renderer.text('', 220, 200)
            .css({
                color: '#4572A7',
                fontSize: '16px'
            })
            .add();

    });

    $("#metroDistanceTotalChart").highcharts({
        chart: {
            plotBackgroundColor: null,
            backgroundColor:null,
            plotBorderWidth: 0,
            plotShadow: false,
            width:290,
            height:300
        },
        credits: {
            enabled: false
        },
        title: {
            text: null,
            align: 'center',
            verticalAlign: 'middle',
            y: 50
        },
        tooltip: {
            pointFormat: '{point.name}: <b>{point.y}</b>'
        },
        series: [
            {
                type:'pie',
                size: '85%',
                innerSize: '80%',
                data: [
                    {y: 27, color: 'blue',name:'去年'}
                ],
                borderColor: 'white',
                borderWidth: 0,
                startAngle:120,
                endAngle:360,
                dataLabels: {
                    x:250,
                    y:20,
                    useHTML: true,
                    formatter: function() {
                        return this.point.name + '<br>'+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: 'blue',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            },
            {
                type: 'pie',
                data: [
                    {y:200,name:'管控值',color:'#6495ED'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:90,
                endAngle:360,
                dataLabels: {
                    x:240,
                    y:-110,
                    useHTML: true,
                    formatter: function() {
                        return '100% '+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: '#6495ED',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }

            },
            {
                type: 'pie',
                data: [ {y:17.34,name:'实际',color:'#008B00'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:180,
                endAngle:360,
                dataLabels: {
                    x:190,
                    y:-10,
                    useHTML: true,
                    formatter: function() {
                        return '超计划<br>' + this.point.name + ''+this.y+'亿<br>15.43';
                    },

                    connectorWidth: 0,
                    color: '#008B00',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            },
            {
                type: 'pie',
                data: [ {y:180,name:'计划',color:'#FFD700'}],
                borderColor: 'white',
                borderWidth: 0,
                innerSize: '60%',
                size: '80%',
                startAngle:200,
                endAngle:360,
                dataLabels: {
                    y:100,
                    x:50,
                    useHTML: true,
                    formatter: function() {
                        return  this.point.name + '<br>12.12%<br>'+this.y+'亿';
                    },

                    connectorWidth: 0,
                    color: '#FFD700',
                    style: {"fontSize": "13px","fontWeight":"bold"}
                }
            }]
    }, function (chart) { // on complete

        chart.renderer.text('', 220, 200)
            .css({
                color: '#4572A7',
                fontSize: '16px'
            })
            .add();

    });


});

