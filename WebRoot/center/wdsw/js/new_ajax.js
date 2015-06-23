var intHandn=null;
		var rtnn=null;	
			function checkWinn(){
				if(rtnn!=null && rtnn.closed){
					clearInterval(intHandn);
					intHandn=null;
					rtnn=null;
					upNew();
					//getJrswDbx($("#oldDept").val());
				}
			}
		function openNew(url,i){
			rtnn = window.open(url+"&rand="+Math.random(),'w' + i);
			intHand2=setInterval("checkWinn()",3000);
			return false;
		}
		
		function upNew(){
			var url = "/portal/todoNew/todoNew.action";
			$.post(
					url+'?random='+Math.random(),
					function(obj, textStatus, jqXHR){
						var dbxn = "";
						var im = "";
						if(obj !=null && obj.length>0){
							for(var i=0;i<obj.length;i++){
								if(obj[i].type==0){
									im = "<img style=\"display:inline;\" src=\"../../css/default/images/new.gif\"></img>";
								}else{
									im = "";
								}
								var su = "";
								if(obj[i].title.length>15){
									su = obj[i].title.substr(0,15)+"...";
								}else{
									su = obj[i].title;
								}
								dbxn += "<li class=\"clearfix\">" +
								"<a href=\"javascript:void(0);\"  style=\"color:#f17003;font-weight:bold;font-size:14px;\" title=\""+obj[i].title+" "+obj[i].occurTime.substr(0,10)+"\"";
								dbxn += " onclick=\"openNew("+"'"+obj[i].url+"',"+i+")\" >"+su+" "+im+" "+"("+obj[i].occurTime.substr(0,10)+")"+"</a>";
								dbxn += "</li>";
							}
							//alert(dbxn);
							$("#todolist").prepend(dbxn);
						}else{
							dbxn += "<li style='border:none;background:none;line-height:30px;' class='clearfix'><a ";
							dbxn +=" style='color:#f17003;font-weight:bold;font-size:14px;' href='javascript:void(0);'>您目前无待办事项</a></li>";
						}
						$("#todolist").html(dbxn);
						var dbxP = "<a target='_blank' class='more_3'";
						dbxP +=" href='/portal/todo/todoItemList.action'>更多</a>";
						$("#todolist").next("p").html(dbxP);
					},
					"json"
				).error(function() { alert("服务器连接失败，请稍后再试!"); })
		}