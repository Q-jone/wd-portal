

function loadPage(){
	showLoading();
	$.post(
			'/portal/userMsg/msgByPage.action?random='+Math.random(),
			{
				title : 	$("#title").val(),
				sender : 	$("#sender").val(),
				receiver : 	$("#receiver").val(),
				sendStart : $("#sendStart").val(),
				sendEnd : 	$("#sendEnd").val(),
				seeStart : 	$("#seeStart").val(),
				seeStart : 	$("#seeStart").val(),
				page : 		$("#redirectPage").val(),
				seeState:	$("#seeState").val(),
				sendState:	$("#sendState").val(),
				state:		$("#state").val(),
				rid:		$("#rid").val(),
				sid:		$("#sid").val(),
				pageSize:	10
			},
			function(data, textStatus, jqXHR){
				if(data){
					initTable(data);
					initPage(data);
					hideLoading();
				}
			},
			"json");
}

function initTable(data){
	var id,sender,receiver,title,sendDate,seeDate,pageHtml = "";
	var pageTrs = "<tr>";
	var pageTrE = "</tr>";
	var pageTdS = "<td class='t_c'>"
	var pageTdE = "</td>"
	var chkS = "<input type='checkbox' id='msgChk' name='msgChk' value='";
	var chkE = "'/>";
	
	var l = data.list;
	if(l!=null&&l.length>0){
		for(var i=0;i<l.length;i++){
			pageHtml += pageTrs
			pageHtml += pageTdS + chkS + l[i].id + chkE + pageTdE;
			if($("#flag").val()=="2"){
				pageHtml += pageTdS + ""  + pageTdE;
			}else{
				pageHtml += pageTdS + l[i].receiver  + pageTdE;
			}
			pageHtml += pageTdS + l[i].sender  + pageTdE;
			pageHtml += pageTdS + l[i].title  + pageTdE;
			pageHtml += pageTdS + l[i].sendDate  + pageTdE;
			pageHtml += pageTdS + l[i].seeDate  + pageTdE;	
			if($("#flag").val()=="1"){
				pageHtml += pageTdS + "<a class='mr5' href='msgSee.action?msgId="+l[i].id+"' target='_self'>查看</a>"  + pageTdE;
			}else if($("#flag").val()=="2"){
				pageHtml += pageTdS + "<a class='mr5' href='msgUpdate.action?msgId="+l[i].id+"' target='_self'>查看</a>"  + pageTdE;
			}else{
				pageHtml += pageTdS + "<a class='mr5' href='msgDetail.action?msgId="+l[i].id+"' target='_self'>查看</a>"  + pageTdE;
			}
			
			pageHtml += pageTrE;
		}
		
	}
	$("#pageTable tr").each(function(i,n){
		if(i>0){
			$(n).remove();
		}
		});
	$("#pageTable").append(pageHtml);
}
function initPage(data){
	var p = data.pageInfo;
	if(p!=null){
		$("#totalRow").html("记录总数："+p.totalRow);
		$("#totalPage").html(p.totalPage+"&nbsp;");
		$("#redirectPage").val(p.currentPage);
		$("#firstPage").attr("href","javascript:goPage('1');");
		$("#lastPage").attr("href","javascript:goPage('"+(p.currentPage-1)+"');");
		$("#nextPage").attr("href","javascript:goPage('"+(p.currentPage+1)+"');");
		$("#finalPage").attr("href","javascript:goPage('"+p.totalPage+"');");
		if(p.totalPage==0){
			$(".tfoot").hide();
		}else{
			$(".tfoot").show();
		}
	}
}

// 点击取消按钮后 清空所有数据
function clearInput() {
	//清空时间
	$("#title").val("");
	$("#sender").val("");
	$("#receiver").val("");
	$("#sendStart").val("");
	$("#sendEnd").val("");
	$("#seeStart").val("");
	$("#seeEnd").val("");
}

function goPage(page) {
	$("#redirectPage").val(page);
	loadPage();
}

function goPageRedirect() {
	if (!$("#redirectPage").val().match(/^[0-9]*$/)) {
		alert("请输入数字");
		$("#redirectPage").val("");
		$("#redirectPage").focus();
		return;
	}
	loadPage();
}
var cache = {};
var lastXhr;
var cacheData;
reCount = 0;
function addReceiver(){
	reCount++;
	var template = $("#hideRe").find("div");
	var emptyRow = template.clone(true);
	emptyRow.find("input[type=text]").each(function(i,n){
		$(n).attr("name","rname"+reCount);
		$(n).attr("id","rname"+reCount);
	});
	emptyRow.find("input[type=hidden]").each(function(i,n){
		$(n).attr("name","rd"+reCount);
		$(n).attr("id","rd"+reCount);
	});
	emptyRow.appendTo($('#reTd'));
	$("#rname"+reCount).autocomplete({
		source: function( request, response ) {
				var term = request.term;
				if ( term in cache ) {
					response( cache[ term ] );
					return;
				}
				lastXhr = $.post(
						'/portal/userMsg/getUser.action?random='+Math.random(),
						{
							maxRows: 12,
							name_startsWith: request.term
						},
						function(data, textStatus, jqXHR){
							 cacheData = $.map( data, function( item ) {
									return {
										label: 		item.name+"("+item.dept+")",
										value: 		item.name+"("+item.dept+")",
										rid:		item.cid
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
			$("#rname").val($("#rname").val()+ui.item.label+"; ");
			$(this).prev("input[type=hidden]").val(ui.item.rid);
		}
	
});
}

