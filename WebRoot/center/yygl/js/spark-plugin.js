/**
 * Created by yaung on 2014/12/20.
 */
(function ($) {
    Array.prototype.clone=function(){ return this.slice(0); }

    $.extend({
        getMax : function(arr1,arr2){
            var copy = arr1.clone();
            Array.prototype.push.apply(copy,arr2);
            return Math.max.apply(null,copy);
        },
        getMin : function(arr1,arr2){
            var copy = arr1.clone();
            Array.prototype.push.apply(copy,arr2);
            return Math.min.apply(null,copy);
        }
    });
    $.fn.extend({
        //管控线形图
        control_line : function (opts) {
            this.sparkline(opts.control,
                {type: 'line',
                    barWidth: 7,
                    barSpacing: 3,
                    width: opts.width, height: opts.height,
                    tooltipSuffix:opts.suffix,
                    composite: true,
                    tooltipFormat: '<span style="display:inline;color: {{color}}">&#9679;</span> '+opts.controlName+'：{{prefix}}{{y}}{{suffix}}',
                    fillColor: false,
                    lineColor: '#FFA042',
                    highlightLineColor: 'red',
                    chartRangeMin: $.getMin(opts.data,opts.control) - 0.1,
                    chartRangeMax: $.getMax(opts.data,opts.control) - 0.1
                });
        },

        //线形图
        spark_line: function (options, callback) {
            var defaults = {
                data : [0],control : [0],
                width : '',height : '',title: '',
                suffix : '',composite : '',controlName:'管控值',xdata:[0]
            };
            var opts = $.extend({}, defaults, options);

            this.html(opts.data.join(","));
            this.sparkline('html',
                { type: 'line',
                    width: opts.width, height: opts.height,
                    lineWidth: 1,
                    spotRadius: 2,
                    spotColor: false,
                    tooltipChartTitle: '<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>' + opts.title,
                    tooltipSuffix: opts.suffix,
                    tooltipFormat: '&nbsp;&nbsp;{{xdata}} <br> <span style="display:inline;color: {{color}}">&#9679;</span> 实际值：{{prefix}}{{y}}{{suffix}}',
                    fillColor: false,
                    lineColor: '#84C1FF',
                    chartRangeMin: $.getMin(opts.data,opts.control) - 0.1,
                    chartRangeMax: $.getMax(opts.data,opts.control) + 0.1,
                    xdata :opts.xdata
                });
            //代表有2张图
            if(opts.composite){
                this.control_line(opts);
            }

        },

        //柱状图
        spark_bar : function(options, callback){
            var defaults = {
                data : [0],control : [0],
                width : '',height : '',title: '',
                suffix : '',composite : '',controlName:'管控值',xdata:[0]
            };
            var opts = $.extend({}, defaults, options);
           // opts.data[opts.data.length-1] = opts.data[opts.data.length-1] + 0.000;
            var recentMap = {};recentMap[opts.data[opts.data.length-1]] = '#0000CC';
            this.html(opts.data.join(","));
            this.sparkline('html',
                { type: 'bar',
                    width: opts.width, height: opts.height,
                    barColor: '#0072E3',
                    barWidth: 7,
                    barSpacing: 3,
                    tooltipChartTitle: '<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>' + opts.title,
                    tooltipSuffix: opts.suffix,
                    tooltipFormat: '&nbsp;&nbsp;{{xdata}} <br> <span style="display:inline;color: {{color}}">&#9679;</span> 实际值：{{prefix}}{{value}}{{suffix}}',
                    //chartRangeMin: $.getMin(opts.data,opts.control) - 0.1,
                    chartRangeMin: 0,
                    chartRangeMax: $.getMax(opts.data,opts.control) + 0.1,
                    colorMap:recentMap,
                    xdata :opts.xdata
                });
            //代表有2张图
            if(opts.composite){
                this.control_line(opts);
            }
        },

        //饼图
        spark_pie : function (options,callback) {
            var defaults = {
                data : [0],control : [0],
                width : '',height : '',title: '',
                suffix : '',offset : ''
            };
            var opts = $.extend({}, defaults, options);


            this.html(opts.data);
            this.sparkline('html',
                {type:'pie',
                    height: opts.height,
                    offset:'-90',
                    tooltipFormat: '  <span style="display:inline;color: {{color}}">&#9679;</span> 编组数：{{value}}',
                    tooltipChartTitle:'<span style="display:inline;">&nbsp;&nbsp;&nbsp;</span>'+opts.title
                });
        }
    })
})(jQuery);