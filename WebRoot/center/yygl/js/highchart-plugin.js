/**
 * Created by yaung on 2014/12/20.
 */
(function($){
    $.extend({
        getStartAngle : function (value,control) {
            if(value == control){
                return 90;
            }else if(value < control){
                return 360 - ($.getPercent(value,control)*2.7).toFixed(0);
            }else{
                return (90 - ($.getPercent(value-control,control) * 2.7 ).toFixed(0))<0?0:
                    90 - ($.getPercent(value-control,control) * 2.7 ).toFixed(0);
            }
        },
        getEndAngle : function (value,control) {
            return 360;
        },
        getPercent : function (value,control){
            if(value == control){
                return 100;
            }else{
                return control != 0 ? (value/control*100).toFixed(1):0
            }
        }
    });

    $.fn.extend({
        highchart_pie : function(options,callback) {
            var defaults = {
                pieArray: [], control: 0,
                width: '', height: '' ,unit:''
            };
            var opts = $.extend({}, defaults, options);
            var seriesArray = [];
            $.each(opts.pieArray, function (i,o) {
                seriesArray.push(
                    {
                        type: 'pie',
                        data: [
                            {y: o.y, name: o.name, color: o.color}
                        ],
                        borderColor: 'white',
                        borderWidth: 0,
                        innerSize: o.innerSize,
                        size: o.size,
                        startAngle: $.getStartAngle(o.y, opts.control),
                        endAngle: $.getEndAngle(o.y, opts.control),
                        dataLabels: {
                            enable : false,
                            //x: o.xPos,
                           // y: o.yPos,
                            useHTML: true,
                            formatter: function () {
                                return '';
                            },
                            connectorWidth: 0,
                            color: o.color,
                            style: {"fontSize": "13px", "fontWeight": "bold"}
                        }
                    }
                )
            });


            this.highcharts({
                chart: {
                    plotBackgroundColor: null,
                    backgroundColor: null,
                    plotBorderWidth: 0,
                    plotShadow: false,
                    width: opts.width,
                    height: opts.height,
                    marginLeft: -35
                },
                credits: {
                    enabled: false
                },
                title: {
                    text: null
                },
                tooltip: {
                    //pointFormat: '{point.name}: <b>{point.y}</b>' + opts.unit
                    pointFormat: '<b>{point.y}</b>' + opts.unit
                },
                series: seriesArray
            }, function (chart) { // on complete
//'<img src="smile.png" ' 'style="display:inline";/><br>
                $.each(opts.pieArray, function (i,o) {
                    var value = o.y/10000>=1 ? (o.y/10000).toFixed(2)+o.unit.replace('万','亿'): o.y.toFixed(0) + o.unit;
                    var temp = (o.overPlan?($.getPercent(o.y, opts.control)>100?'超计划'+($.getPercent(o.y, opts.control)-100).toFixed(1)+'%<br>':''):'');
                    chart.renderer.text(
                        temp+o.phtml+value,
                        o.xPos, o.yPos)
                        .css({
                            color: o.color,
                            fontSize: '12px',
                            fontWeight: 'bold',
                            zIndex:9999
                        })
                        .add();
                    if(temp.length > 0){
                        chart.renderer.image('smile.png', 115, 95, 20, 20)
                            .add();
                    }
                });


            })
        }
    });
})(jQuery);