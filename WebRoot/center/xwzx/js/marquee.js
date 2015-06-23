var sobj;
		var first = true;
		var intHand = null;
		var objLength = 0;
		var k = 0;
    		
			(function($){
				/**$(document).on("mouseenter","a",function(){
					var offset = $(this).offset();
					var i = $(this).attr("ct");
					$(".detail").html(i);
	                $(".detail").css("left", offset.left+500);
	                $(".detail").css("top", offset.top+10);
				    $(".detail").show();
				}).on("mouseleave","a",function(){
					$(".detail").html("");
				    $(".detail").hide();
				    $(".detail").css("left", "");
	               	$(".detail").css("top", "");
				});*/
				
				
				$.getSN = function(options){
					if (typeof(options) == "undefined" || options== null){
						return "";
					//}else if(options.length > 75){
					//	return options.substr(0,75) + "... [请点击]";
					}else{
						return options +" [请点击]";
					}
				}
			
				$.fn.myScroll = function(options){
				//默认配置
				var defaults = {
					speed:40,  //滚动速度,值越大速度越慢
					rowHeight:24 //每行的高度
				};
				
				var opts = $.extend({}, defaults, options),intId = [];
				
				function marquee(obj, step){
					obj.find("ul").animate({
						marginTop: '-='+opts["rowHeight"]
					},1000,function(){
							var s = Math.abs(parseInt($(this).css("margin-top")));
							if(s >= step){
								
								$(this).find("li").slice(0, 1).remove();
								$(this).css("margin-top", 0);
							}
							k++;
							//console.log(k);
							
							if(k==objLength-1){
								k = 0;
								showMarquee();
							}
						});
					}
					
					this.each(function(i){
						var sh = opts["rowHeight"],speed = opts["speed"],_this = $(this);
						
						intId[i] = setInterval(function(){
							if(_this.find("ul").height()<=_this.height()){
								clearInterval(intId[i]);
							}else{
								marquee(_this, sh);
							}
						}, speed);
			
						_this.hover(function(){
							clearInterval(intId[i]);
						},function(){
							intId[i] = setInterval(function(){
								if(_this.find("ul").height()<=_this.height()){
									clearInterval(intId[i]);
								}else{
									marquee(_this, sh);
								}
							}, speed);
						});
					
					});
			
				}
			
			})(jQuery);
			
        	$(function(){
        		
        		//intHand = setInterval("getMarquee()",11*60*1000);
        		
        		$("#marqueeDiv").width($(document).width()+220);
        		$("#copyright").width($(document).width()-300);
        		getMarquee();
        		//alert($(document).width());
        		
        	})
        	
        	function getMarquee(){
       			$.ajax({
       				url: "/portal/marquee/getMarquee.action?random="+Math.random(),
       				type: 'post', 
       				dataType: 'json', 
       				error:function(){
       					//alert("系统连接失败，请稍后再试！");
       				},
       				success: function(obj){			
       	              if(obj!=null&&obj.list!=null){
       	            	sobj = obj;
       	           	  	objLength = obj.list.length;
       	              }
       	              if(first){
         	            	showMarquee();
       	              }
       	           	setTimeout("getMarquee()",11*60*1000);
       				}
       			});	
        	}
        	
        	function showMarquee(){
        					//alert(obj);
        					var obj = sobj;
        					if(obj !=null && obj.list != null && obj.list.length > 0){
        						var ls = "";
        						var msg = obj.list;
        						for(var i in msg){
        							ls += "<li>";
        							if(msg[i].url != null && (msg[i].url).length >0){
        								ls += "<a  href='"+msg[i].url+"' target='_blank'>";
        								if(msg[i].type !=null && msg[i].type.length > 0){
        									ls += "<font class='"+msg[i].app+"'>["+msg[i].type+"] </font>";
        								}
        								ls += $.getSN(msg[i].title)+"</a>"
        							}else{
        								ls += $.getSN(msg[i].title);
        							}
        							
        							
        							ls+="</li>";
        							
        							if(msg[i].content !=null && msg[i].content.length > 0){
        								ls += "<li class='priority"+msg[i].priority+
            							"' >";
            							if(msg[i].url != null && (msg[i].url).length >0){
            								ls += "<a class='priority"+msg[i].priority+ 
            								"' href='"+msg[i].url+"' target='_blank'>"+
            								""+$.getSN(msg[i].content)+"</a>"
            							}else{
            								ls += $.getSN(msg[i].content);
            							}
            							
            							
            							ls+="</li>";
        							}
        						}
        						//alert(ls);
        						
        						if(first){
        							$("#cul").html(ls);
        							first = false;
	        						$("#copyright").myScroll({
	        							speed:obj.speed,
	        							rowHeight:30
	        						});
        						}else{
        							$("#cul").append(ls);
            					}
        					}
        				}
