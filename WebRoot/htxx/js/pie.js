function showPie(name,pic){
	var a =  [
                		{name:'A',y:40,href:'http://www.baidu.com',color:'#ffc200'},
                    {name:'B',y:30,href:'http://www.baidu.com',color:'#fee100'},
                    {name:'C',y:10,href:'http://www.baidu.com',color:'#f5fb00'},
                    {name:'D',y:10,href:'http://www.baidu.com',color:'#c6ff00'},
                    {name:'E',y:5,href:'http://www.baidu.com',color:'#7ffb16'},
                    {name:'F',y:5,href:'http://www.baidu.com',color:'#37e194'}
          ]
          
	var  chartOption = {
            chart: {
                renderTo: name,
                backgroundColor: 'rgba(255, 255, 255, 0)',
                plotBorderColor : null,
                plotBackgroundColor: null,
                plotBackgroundImage:null,
                plotBorderWidth: null,
                plotShadow: false,  
                borderWidth : 0,
                events: {
                load: function() {
                    this.renderer.image(pic, 40, 38, 40, 43)
                        .attr({
                            zIndex: 7
                        })
                        .add();
                }
            }
            },
						credits :{
								enabled:false
						},
            title: {
                text: ''
            },

            tooltip: {
                formatter: function() {
                    //alert(1);
                    return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                },
                style: {
                    color: '#333333',
                    fontSize: '10pt',
                    padding: 5,
                    zIndex:9999
                }
            },

            plotOptions: {
                pie: {
                    allowPointSelect: false,
                    borderWidth : 0,
                    cursor: 'pointer',
	                  dataLabels: {
	                  enabled: false
	                },
	              showInLegend: false,
	              point: {  
	                    events : {  
	                         click: function(event){
	                           //alert(this.y);      
	                            // window.open(this.href);                            
	                         }
	                    }
	                  },
               dataLabels: {
                  enabled: false,
                  color: '#000000',
                  connectorColor: '#000000',
                  formatter: function() {
                      return '<b>'+ this.point.name +'</b>: '+ this.percentage +' %';
                  }

              },
               showInLegend: false,
               size:68
	              }
            },
            
            series: [{
                type: 'pie',
                name: 'Browser share',
                data: a
                }]
         }	
         
          new Highcharts.Chart(chartOption); 
}