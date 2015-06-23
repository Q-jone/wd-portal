 agentCount = 0;
 var cache = {};
 var lastXhr;
 var cacheData;
		function addAgent(){
			agentCount++;
			var template = $("#hideTr").find("tr");
			//alert(template.html());
			var emptyRow = template.clone(true);
			emptyRow.find("input[type=text]").each(function(i,n){
				$(n).attr("name","userName"+agentCount);
				$(n).attr("id","userName"+agentCount);
			});
			emptyRow.appendTo($('#userAgent'));
			
			$("#userName"+agentCount).autocomplete({
					source: function( request, response ) {
							var term = request.term;
							if ( term in cache ) {
								response( cache[ term ] );
								return;
							}
							lastXhr = $.post(
									'/portal/userManage/findStptUserName.action?random='+Math.random(),
									{
										maxRows: 12,
										name_startsWith: request.term
									},
									function(data, textStatus, jqXHR){
										 cacheData = $.map( data, function( item ) {
												return {
													label: 		item.name,
													value: 		item.name,
													cid :		item.cid,
													tid:		item.tid,
													loginName:	item.loginName,
													deptName:	item.dept,
													nickName:	item.nickName,
													role:		item.role,
													createDate:	item.createDate,
													rank:		item.rank
												};
											});
										cache[ term ] = cacheData;
										if ( jqXHR === lastXhr ) {
											response(cacheData);
										}
									},
									"json"
								)
					},
					minLength: 1,
					select: function( event, ui ) {
						$(this).parent().parent("tr").attr("tid",ui.item.tid);
						$(this).parent().parent("tr").attr("cid",ui.item.cid);
						$(this).parent().parent("tr").attr("loginName",ui.item.loginName);
						$(this).parent().parent("tr").attr("deptName",ui.item.deptName);
						$(this).parent().parent("tr").attr("tid",ui.item.tid);
						$(this).parent().parent("tr").attr("nickName",ui.item.nickName);
						$(this).parent().parent("tr").attr("role",ui.item.role);
						$(this).parent().parent("tr").attr("createDate",ui.item.createDate);
						$(this).parent().parent("tr").attr("rank",ui.item.rank);
						var tmp = $(this).parent().parent("tr").attr("deptName").split(",");
						var depts ="";
						for(var i=0;i<tmp.length;i++){
							depts +="<option value='"+tmp[i]+"'>"+tmp[i]+"</option>";
						}
						$(this).parent().parent("tr").find("td[row=deptName]").html("<select row='deptName'>"+depts+"</select>");
						$(this).parent().parent("tr").find("td[row=loginName]").html($(this).parent().parent("tr").attr("loginName").split(",")[0]);
						$(this).parent().parent("tr").find("td[row=nickName]").html($(this).parent().parent("tr").attr("nickName").split(",")[0]);
						$(this).parent().parent("tr").find("td[row=role]").html($(this).parent().parent("tr").attr("role").split(",")[0]);
						$(this).parent().parent("tr").find("td[row=createDate]").html($(this).parent().parent("tr").attr("createDate").split(",")[0]);			
						$(this).parent().parent("tr").find("td[row=rank]").html($(this).parent().parent("tr").attr("rank").split(",")[0]);			
						$(this).parent().parent("tr").find("input[row=cid]").val($(this).parent().parent("tr").attr("cid").split(",")[0]);					

					}
				
			});
		}
		
		function saveAgent(obj){
			var cid = $(obj).parent().parent("tr").find("input[row=cid]").val();
			var selfId = "";
			$("input[type=hidden][row=selfId]").each(function(i,n){
				selfId += $(n).val()+",";
			})
			if(cid!=""){
				if(selfId.indexOf(cid)<0){
					if(confirm("确认增加？")){
						$.post(
								'/portal/userManage/saveAgent.action?random='+Math.random(),
								{
									cid : cid,
									tid : $("#id").val()
								},
								function(data, textStatus, jqXHR){
									if(data){
										alert("新增成功");
										var trObj = $(obj).parent();
										trObj.parent("tr").find("td[row=userName]").html(trObj.parent("tr").find("input[type=text]").val());	
										
										trObj.parent("tr").find("td[row=deptName]").html(trObj.parent("tr").find("select").val());
										var param = '<input type="hidden" row="cid" value="'+cid+'"/>'+
					             		'<input type="button" value="取消" onclick="deleteAgent(this);"/>';
										trObj.html(param);
									}
								},
								"json");
					}
				}else{
					alert("请选择除自己外需要代理的人员！");
					return false;
				}
			}else{
				alert("请选择需要代理的人员！");
				return false;
			}
		}
		
		function deleteAgent(obj){
			if(confirm("确认取消？")){
				$.post(
						'/portal/userManage/deleteAgent.action?random='+Math.random(),
						{
							cid : $(obj).parent().parent("tr").find("input[row=cid]").val(),
							tid : $("#id").val()
						},
						function(data, textStatus, jqXHR){
							if(data){
								alert("取消成功");
								$(obj).parent().parent().remove();
							}
						},
						"json");	
			}
		}
		
		
		
		