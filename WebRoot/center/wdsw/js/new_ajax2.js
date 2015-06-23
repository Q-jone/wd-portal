var o1= 0;
var n1 = 0;
var intHandn=null;
		var rtnn=null;	
			function checkWinn(){
				if(rtnn!=null && rtnn.closed){
					clearInterval(intHandn);
					intHandn=null;
					rtnn=null;
					$("#todolist").html("");
					upNew("");
					//upOld("");
					//getJrswDbx($("#oldDept").val());
				}
			}
		function openNew(url,i){
			rtnn = window.open(url+"&rand="+Math.random(),"workflowwdsw");
			intHandn=setInterval("checkWinn()",3000);
			return false;
		}
		
		function upOld(oldDept){
			//alert(1);
			var url = "/portal/todoNew/todoOld.action";
			$.post(
					url+'?random='+Math.random(),
					{
						"ndeptId":oldDept
					},
					
					function(obj, textStatus, jqXHR){
						var dbxn = "";
						var im = "";
						o1 = 0;
						if(obj !=null && obj.length>0){
							if(n1 == 0){
								$("#todolist").html("");
							}else{
								$("#todolist li:gt("+(n1-1)+")").remove();
							}
							for(var i=0;i<obj.length;i++){
								o1++;
								if(obj[i].type==0){
									im = "";
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
							$("#todolist").append(dbxn);
						}else{
							dbxn += "<li style='border:none;background:none;line-height:30px;' class='clearfix'><a ";
							dbxn +=" style='color:#f17003;font-weight:bold;font-size:14px;' href='javascript:void(0);'>您目前无待办事项</a></li>";
						}
						if(o1==0&&n1==0){
							$("#todolist").html(dbxn);
						}
						var dbxP = "<a target='_blank' class='more_3'";
						dbxP +=" href='/portal/todo/todoItemList.action'>更多</a>";
						$("#todolist").next("p").html(dbxP);
						if($("#todolist").text().indexOf("您目前无待办事项")>-1){
							$("#dbx_num").text("0");
						}else{
							$("#dbx_num").text($("#todolist").find("li").length);
						}
					},
					"json"
				).error(function() {/** alert("服务器连接失败，请稍后再试!"); */})
		}
		
		function upNew(oldDept){
			//alert(1);
			var url = "/portal/todoNew/todoNew.action";
			$.post(
					url+'?random='+Math.random(),
					{
						"ndeptId":oldDept
					},
					
					function(obj, textStatus, jqXHR){
						n1 = 0;
						var dbxn = "";
						var im = "";
						if(obj !=null && obj.length>0){
							for(var i=0;i<obj.length;i++){
								n1++;
								if(obj[i].type==0){
									im = "";
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
							
						}
						//$(".otherTodoLi").eq(0).click();
						setTimeout(getDept,2000);
					},
					"json"
				).error(function() {/** alert("服务器连接失败，请稍后再试!"); */})
		}