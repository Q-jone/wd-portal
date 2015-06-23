function init(){
	$.ajax({
		type: 'POST',
		url: '/portal/dataExchange/getDeptLeaders.action?random='+Math.random(),
		data:{
				"deptId":$("#deptId").val()
			},
		dataType:'json',
		cache : false,
		error:function(){alert('系统连接失败，请稍后再试！')},
		success: function(obj){
			var leaderOption = "";
			if(obj.length==1){
				leaderOption = "<option value='"+obj[0].loginName+"'>"+obj[0].name+"</option>";
				$("#mainPerson").html(leaderOption);
			}else if(obj.length>1){
				leaderOption = "<option value=''>---请选择---</option>";
				for(var i=0;i<obj.length;i++){
					leaderOption +="<option value='"+obj[i].loginName+"'>"+obj[i].name+"</option>";	
				}
				$("#mainPerson").html(leaderOption);
			}
		}	  
	});	
}
function eamProjectYwbl(ifSave,recValid){
	if(recValid=='0'){
		alert("已办理");
	}else{
		if(check(ifSave)){
			document.getElementById('formAdd').submit();		
			setTimeout("confirmValid()",3000);
		}
	}
}

function confirmValid(){
	//alert("操作成功!");
	document.getElementById('recUpdate').submit();	
	document.getElementById('ywbl').style.display="none";	
}

function saveAttachFiles(attachFileId){
	var objFilePaths = document.getElementsByName("filePath_"+attachFileId);
	var objFileNames = document.getElementsByName("fileName_"+attachFileId);
	var objFileExtNames = document.getElementsByName("fileExtName_"+attachFileId);		
	var objFileSizes = document.getElementsByName("fileSize_"+attachFileId);
	var objVersions = document.getElementsByName("version_"+attachFileId);
	var objMemos = document.getElementsByName("memo_"+attachFileId);

	var vFilePath = '';
	var vFileName = '';
	var vFileExtName = '';		
	var vFileSize = '';		
	var vVersion = '';
	var vMemo = '';
	if(objFilePaths){
		var num = objFilePaths.length;
		for(var i=0;i<num;i++){
			if(i>0){
				vFilePath+=",";
				vFileName+=",";
				vFileExtName+=",";					
				vFileSize+=",";
				vVersion+=",";
				vMemo+=",";
			}
			vFilePath += objFilePaths[i].value;	
			vFileName += objFileNames[i].value;	
			vFileExtName += objFileExtNames[i].value;	
			vFileSize += objFileSizes[i].value;					
			vVersion += objVersions[i].value;	
			vMemo += objMemos[i].value;	
		}
			

		$.ajax({
			type: 'POST',
			url: '/portal/dataExchange/saveAttach.action?random='+Math.random(),
			data:{
				"appName" : "attila1",
				"filePath" : vFilePath,	
				"fileName" : vFileName,	
				"fileExtName" : vFileExtName,					
				"fileSize" : vFileSize,	
				"version" : vVersion,	
				"memo" : vMemo			
			},
			dataType:'json',
			cache : false,
			error:function(){alert('保存附件失败！')},
			success: function(obj){
				if(obj.success){			
					document.getElementById(attachFileId).value=obj.groupId;
					if(check($("#ifSave").val())){
						document.getElementById('formAdd').submit();		
						setTimeout("confirmValid()",3000);
					}
				}
			
			}
		});
	
	}
}
function check(num){ 
	$("#ifSave").val(num);	
	$("#extCode14").val($('#fjcount1').html());
	$("#extCode15").val($('#fjcount2').html());
	$("#extCode16").val($('#fjcount3').html());
	$("#extCode17").val($('#fjcount4').html());
	$("#extCode18").val($('#fjcount5').html());
	
	var pName=$("#pName").val();
	//var isExistsName=$("#isExistsName").val();
	//var extCode4=$("#extCode4").val();
	var investCost=$("#investCost").val();	
	var mainPerson=$("#mainPerson").val();
	//var moneyNature=$("#moneyNature").val();
	
	var linkMan=$("#linkMan").val();
	var linkManTel=$("#linkManTel").val();
	var planStartDate=$("#planStartDate").val();
	var planEndDate=$("#planEndDate").val();
	var infoMan=$("#infoMan").val();
	var infoDept=$("#infoDept").val();
	var legislationInfo=$("#legislationInfo").val();
	var excuteSolution=$("#excuteSolution").val();
	var projectBudget=$("#projectBudget").val();
	var projectPlan=$("#projectPlan").val();

	//var extCode20=$.trim($("#extCode20").val());
	//alert(legislationInfo.length);
	
	if(pName==""){
 		alert('请输入项目名称');
		$("#pName").focus();
		return false;
	}
	/*
	if(isExistsName=='false'){
		alert('此项目名称已存在,请更换新的项目名称');
		$("#pName").focus();
		return false;
	}
	*/
	//if(extCode4==""){
 	//	alert('请输入项目简称');
	//	$("#extCode4").focus();
	//	return false;
	// }
	if(investCost==""){
 		alert('请输入投资估算');
		$("#investCost").focus();
		return false;
	 }
	if(isNaN(investCost)){
	   alert("请输入数字类型的投资估算值");
	   $("#investCost").focus();
	   return false;
	 }
	//var rd_safe = $('input:radio[name="safe"]');
	//var rd_value = '';
	//for (var i = 0 ; i < rd_safe.length; i++) {
	//	if (rd_safe[i].checked) {
	//		rd_value = rd_safe[i].value;
			
			//alert(rd_checked_value);
	//	}
	//}
	//alert('tt'+rd_value);
	if($("#safe").val()==""){
 		alert('请选择项目分类');
		$("#safe").focus();
		return false;
	 }else{
		 /*
		 if($("#safe").val()=="其他类"){
			 if($("#other").val().replace(/(^\s*)|(\s*$)/g,'')==""){
				alert('请填写其他类');
				$("#other").focus();
				return false;
			}
		 }
		 */
		 document.getElementById('extCode19').value = $("#safe").val().replace(/(^\s*)|(\s*$)/g,'');
		 
	 }
	//if (rd_value == "" || rd_value== undefined) {
	//	alert('请选择是否为安全类项目');
	//	return false;
	//}
	/*
	if($("#safe").val()=="其他类"){
		document.getElementById('extCode19').value = $("#other").val().replace(/(^\s*)|(\s*$)/g,'');
	}else{
		document.getElementById('extCode19').value = $("#safe").val().replace(/(^\s*)|(\s*$)/g,'');
	}
	*/
	

	//if (extCode20 ==""){
	//	 alert("请输入资产大修编号");
	//	 $("#extCode20").focus();
	//	 return false;
	//}
	if(mainPerson==""){
 		alert('请选择负责人');
		$("#mainPerson").focus();
		return false;
	 }
	//if(moneyNature==""){
 	//	alert('请选择资金性质');
	//	$("#moneyNature").focus();
	//	return false;
	// }
	
	var ck = $("#moneySource").parent().find("input[type=checkbox]");
	var ck_checked = '';
	var flag = false;
	for (var i = 0 ; i < ck.length; i++) {
		if (ck[i].checked) {
			/*
			if (ck[i].value == "4") {
				flag = true;
			}
			*/
			ck_checked += ck[i].value + ',';
		}
	}
    var moneySource = ck_checked;
    if(moneySource == "" || moneySource== undefined){
 		alert('请选择出资主体');
		$("#moneySource1").focus();
		return false;
	}else {
		moneySource = moneySource.substr(0,moneySource.length-1);
		$("#moneySource").val(moneySource);
	}
    /*
	var moneySourceInfo = $("#moneySourceInfo").val();
	if ((flag == true && moneySourceInfo== "请输入资金来源") || (flag == true && moneySourceInfo== "")) {
		alert("请输入资金来源");
	    $("#moneySourceInfo").focus();
	    return false;
	}
	*/
	if(linkMan==""){
 		alert('请输入联系人');
		$("#linkMan").focus();
		return false;
	}
	if(linkManTel==""){
 		alert('请输入联系电话');
		$("#linkManTel").focus();
		return false;
	}
	if(linkManTel!=null&&linkManTel!=""&&!linkManTel.match(/^[0-9-]*$/)){
 		alert('联系电话仅能输入数字和中划线');
		$("#linkManTel").focus();
		return false;
	}

	if(planStartDate==""){
 		alert('请输入计划开始时间');
		$("#planStartDate").focus();
		return false;
	}
	if(planEndDate==""){
 		alert('请输入计划完成时间');
		$("#planEndDate").focus();
		return false;
	}
	if(planStartDate>planEndDate){
		alert("计划开始时间不能早于计划完成时间。");  
		$("#planStartDate").focus();
		return false;
	}
	if(infoMan==""){
 		alert('请输入申报人');
		$("#infoMan").focus();
		return false;
	}
	if(infoDept==""){
 		alert('请输入申报单位');
		$("#infoDept").focus();
		return false;
	}

	var projectAttachId = $("#projectAttachId").val();
	if (projectAttachId == "" || projectAttachId == null || projectAttachId == "null"){
		//alert('请上传项目附件');
		saveAttachFiles("projectAttachId");
		return false;
	} 
	
	if(legislationInfo=="" || legislationInfo == $("#legislationInfo").attr("placeholder")){
 		alert('请输入立项依据');
		$("#legislationInfo").focus();
		return false;
	}
	if(legislationInfo.length > 500){
 		alert('立项依据不能超过500字符');
		$("#legislationInfo").focus();
		return false;
	}
	if(excuteSolution=="" || excuteSolution == $("#excuteSolution").attr("placeholder")){
 		alert('请输入实施方案');
		$("#excuteSolution").focus();
		return false;
	}
	if(excuteSolution.length > 500){
 		alert('实施方案不能超过500字符');
		$("#excuteSolution").focus();
		return false;
	}
	if(projectBudget=="" || projectBudget == $("#projectBudget").attr("placeholder")){
 		alert('请输入资金估算');
		$("#projectBudget").focus();
		return false;
	}
	if(projectBudget.length > 500){
 		alert('资金估算不能超过500字符');
		$("#projectBudget").focus();
		return false;
	}
	
	if(projectPlan=="" || projectPlan == $("#projectPlan").attr("placeholder")){
 		alert('请输入工程计划');
		$("#projectPlan").focus();
		return false;
	}
	if(projectPlan.length > 500){
 		alert('工程计划不能超过500字符');
		$("#projectPlan").focus();
		return false;
	}
	var major = "";
	$("input[id=majorShow]").each(function(index){
		if($(this).attr("checked")=="checked"){
			if(major!=""){
				major += ",";
			}
			major += $(this).val();
		}
	});
	if(major==""){
		alert("请选择专业分类！");
		$("#majorShow").focus();
		return false;
	}else{
		$("#major").val(major);
	}
	
	if($.trim($("#infoManTel").val())==""){
 		alert('请完整填写人员信息！');
		$("#infoManTel").focus();
		return false;
	}
	
	if($.trim($("#mainCompany").val())==""){
 		alert('请完整填写人员信息！');
		$("#mainCompany").focus();
		return false;
	}
	
	if($.trim($("#mainCompanyPerson").val())==""){
 		alert('请完整填写人员信息！');
		$("#mainCompanyPerson").focus();
		return false;
	}
	
	if($.trim($("#mainCompanyPersonTel").val())==""){
 		alert('请完整填写人员信息！');
		$("#mainCompanyPersonTel").focus();
		return false;
	}
	
	if($.trim($("#linkMan").val())==""){
 		alert('请完整填写人员信息！');
		$("#linkMan").focus();
		return false;
	}
	
	if($.trim($("#linkManTel").val())==""){
 		alert('请完整填写人员信息！');
		$("#linkManTel").focus();
		return false;
	}
	
	
	/*
	if($("#payType").val()==""){
		alert("请选择结算方式！");
		$("#payType").focus();
		return false;
	}
	
	
	var status1 = 0;
	var status2 = 0;
	if($.trim(extCode20)==""||$("#assetType").val()==""){
		status1 = 1;
	}
	if($.trim(extCode20)==""&&$("#assetType").val()==""){
		status1 = 2;
	}
	saveAssets($("#assetAdd"));
	if($.trim($("#assetAdd").val())==""){
		status2 = 1;
	}
	
	//alert(status1+"&"+status2);
	
	if($("#payType").val()=="1"){
		if((status1!=0&&status2!=0)||(status1==0&&status2==1)||(status2==0&&status1==1)){
			alert("请填写资产变动信息！变更资产状态或新增资产至少填写一种！");
			return false;
		}
	}else{
		if(status1==1){
			alert("如要变更资产状态，请完整填写变更信息！");
			if($.trim(extCode20)==""){
				$("#extCode20").focus();
			}else{
				$("#assetType").focus();
			}
			return false;
		}
		
	}
	*/
	/*
	var oldAsset = "";
	$("[id=oldAsset]").each(function(index){
		if(index>0){
			oldAsset += ",";
		}
		oldAsset += $.trim($(this).children("span").text());
	});
	
	$("#extCode20").val(oldAsset);
	*/
	/*
	if($("#extCode20").val().length>3700){
		alert("您填写的既有资产过多，请联系管理员！");
		return false;
	}
	*/
	/*
	saveAssets($("#assetAdd"));
	
	if($("#safe").val()=="大修类"){
		if($("#extCode20").val()==""){
			alert('请添加既有资产编码！');
			$("#extCode20_show").focus();
			return false;
		}
	}else if($("#safe").val()=="更新改造类"){
		if($("#assetAdd").val()==""){
			alert('请添加新增资产编码！');
			$("#assetAddId_show").focus();
			return false;
		}
	}
	*/
	
	/*
	if(extCode20!=""&&(!$.isNumeric(extCode20)||extCode20.indexOf(".")>-1||extCode20.length!=20||$("[id=noAsset]").length>0)){
		alert("请输入正确的资产编号！");
		$("#extCode20").focus();
		return false;
	}
	
	
	if($("#assetAdd").val().length>2500){
		alert("您新增的资产过多，请联系管理员！");
		return false;
	}
	*/
	
	if($("#infoManTel").val()!=""&&!$("#infoManTel").val().match(/^[0-9-]*$/)){
 		alert('联系电话仅能输入数字和中划线');
		$("#infoManTel").focus();
		return false;
	}
	
	if($("#mainCompanyPersonTel").val()!=""&&!$("#mainCompanyPersonTel").val().match(/^[0-9-]*$/)){
 		alert('联系电话仅能输入数字和中划线');
		$("#mainCompanyPersonTel").focus();
		return false;
	}
	
	var checkMonitor = false;
	  $('[name=monitor]').each(
	  	function(index){
	  		if($(this).attr("checked")=="checked"){
	  			checkMonitor = true;
	  		}
	  	}
	  )
	  if(!checkMonitor){
	  	alert("请选择是否需要监理！"); 
	    return false;
	  }
	
	var rd = $('input:radio[name="projectdevise1"]');
	var rd_checked_value = '';
	for (var i = 0 ; i < rd.length; i++) {
		if (rd[i].checked) {
			rd_checked_value = rd[i].value;
			
			//alert(rd_checked_value);
		}
	}
	//alert('tt'+rd_checked_value);
	if (rd_checked_value == "" || rd_checked_value== undefined) {
		alert('请选择项目设计');
		return false;
	}
	if ($("#extCode14").val()!="" && parseInt($("#extCode14").val())>0){
		var legislationInfoAttachId = $("#legislationInfoAttachId").val();	
		if (legislationInfoAttachId == "" || legislationInfoAttachId == null || legislationInfoAttachId == "null"){
			saveAttachFiles("legislationInfoAttachId");
			//alert('请上传立项依据附件');
			return false;
		} 
	}
	if ($("#extCode15").val()!="" && parseInt($("#extCode15").val())>0){
		var excuteSolutionAttachId = $("#excuteSolutionAttachId").val();	
		if (excuteSolutionAttachId == "" || excuteSolutionAttachId == null || excuteSolutionAttachId == "null"){
			//alert('请上传实施方案附件');
			saveAttachFiles("excuteSolutionAttachId");
			return false;
		} 
	}
	if ($("#extCode16").val()!="" && parseInt($("#extCode16").val())>0){
		var projectBudgetAttachId = $("#projectBudgetAttachId").val();	
		if (projectBudgetAttachId == "" || projectBudgetAttachId == null || projectBudgetAttachId == "null"){
			//alert('请上传资金估算附件');
			saveAttachFiles("projectBudgetAttachId");			
			return false;
		} 
	}
	if ($("#extCode17").val()!="" && parseInt($("#extCode17").val())>0){
		var projectPlanAttachId = $("#projectPlanAttachId").val();	
		if (projectPlanAttachId == "" || projectPlanAttachId == null || projectPlanAttachId == "null"){
			//alert('请上传项目计划附件');
			saveAttachFiles("projectPlanAttachId");
			return false;
		} 	
	}
	
	if ('3' == rd_checked_value) {
		if ($("#extCode18").val()!="" && parseInt($("#extCode18").val())>0){
			var projectDeviseAttachId = $("#projectDeviseAttachId").val();	
			if (projectDeviseAttachId == "" || projectDeviseAttachId == null || projectDeviseAttachId == "null"){
				saveAttachFiles("projectDeviseAttachId");
				//alert('请上传项目设计附件');
				return false;
			} 
		} 
	}
	document.getElementById('projectdevise').value = rd_checked_value;
	//alert($("#moneySource").val()+","+$("#projectdevise").val());
	if (num == 1) {
		if(confirm("确定保存吗？")) {
			var getValue = $("#legislationInfo").val();
			var endValue = (getValue.replace(/<(.+?)>/gi,"&lt;&1&gt;")).replace(/\n/gi,"<br>");
			$("#legislationInfo").val(endValue);
			
			var getValue1 = $("#excuteSolution").val();
			var endValue1 = (getValue1.replace(/<(.+?)>/gi,"&lt;&1&gt;")).replace(/\n/gi,"<br>");
			$("#excuteSolution").val(endValue1);
			
			var getValue2 = $("#projectBudget").val();
			var endValue2 = (getValue2.replace(/<(.+?)>/gi,"&lt;&1&gt;")).replace(/\n/gi,"<br>");
			$("#projectBudget").val(endValue2);
			
			var getValue3 = $("#projectPlan").val();
			var endValue3 = (getValue3.replace(/<(.+?)>/gi,"&lt;&1&gt;")).replace(/\n/gi,"<br>");
			$("#projectPlan").val(endValue3);
			
			$('#btn').attr('disabled',true);
			$('#btn_save').attr('disabled',true);

		}
	}	
	
	return true;
}

function checkInput(target,msg)
{  	

	var obj=eval("PclProjectBasicInfo."+target);	
	
	if(obj.value=="")
	{
		alert(msg);
		obj.focus();
		return false;
	}	
	return true;
	
}

function checkOthers(){
	var chk=$("#moneySource4").attr("checked");
	if (chk == "checked") {
		$("#moneySourceInfo").attr("disabled",false).css("display","block");
		$("#moneySourceInfo").attr("disabled",false).css("display","inline");
	}else {
		$("#moneySourceInfo").attr("disabled",false).css("display","none");
	}
}

function checkdevise(num){
	if (num == '3') {
		$("#upload").attr("disabled",false).css("display","block");
		$("#upload").attr("disabled",false).css("display","inline");
		$("#attachshow").attr("disabled",false).css("display","block");
		$("#attachshow").attr("disabled",false).css("display","inline");
	} else {
		$("#upload").attr("disabled",false).css("display","none");
		$("#attachshow").attr("disabled",false).css("display","none");
	}
}

function isExistsProjectName(oldName) {
	var name=$("#pName").val(); 
	name=$.trim(name);
	$("#isExistsName").val('true');
	if(name != null && name != "undefined" && name != '' && name != oldName) {
		$.ajax({
			type: 'POST',
			async:false,
			url: '/workflow//PclProjectBasicInfo/searchExistsProjectName.action',
			data:{
				"name" : name
			},
			dataType:'json',
			success: function(obj){
				 if(obj.info=="success" && obj.flag=="true") {
					alert('此项目名称已存在,请更换新的项目名称');
					$("#isExistsName").val('false');
					$("#pName").focus();
				 }else {
					$("#isExistsName").val('true');
				 }
			},
			error:function(){
				return true;
				//alert('系统错误,请与管理员联系');
			}  
		});
	}
}

function otherClass(obj){
    if($(obj).val()=='其他类'){
    	if($("#otherTr").length==1){
            $("#otherTr").remove();
        }
    	$(obj).parent("td").parent("tr").after("<tr id=\"otherTr\" class=\"content6\"><td class=\"lableTd t_r\">其他类</td><td colspan=\"3\" class=\"content6\"><input id=\"other\" type=\"text\" class=\"input_large\"></td></tr>");
    }else{
        if($("#otherTr").length==1){
            $("#otherTr").remove();
        }
    }
    
    var tdValue = "";
    if($(obj).val()=='大修类'){
    	resetAssets();
    	tdValue = $.trim($("#must1").html());
    	$("#must1").html("<span style='color:red;display:inline'>*</span>&nbsp;&nbsp;<span style='display:inline'>"+tdValue+"</span>");
    	$("#assetAddId_show").attr("disabled",true);
    	$("#assetAddName_show").attr("disabled",true);
    	$("#assetAddNum_show").attr("disabled",true);
    }else if($(obj).val()=='更新改造类'){
    	resetAssets();
    	tdValue = $.trim($("#must2").html());
    	$("#must2").html("<span style='color:red;display:inline'>*</span>&nbsp;&nbsp;<span style='display:inline'>"+tdValue+"</span>");
    	
    }else{
    	resetAssets();
    }
    
    /*
    if($(obj).val()=='其他类'||$(obj).val()=='能源合同类'){
    	$(obj).parent().find("span").remove();
    	$(obj).parent().append("<span style='display:inline'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox'/>&nbsp;新增资产&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox'/>&nbsp;更新资产</span>");
    }else{
    	$(obj).parent().find("span").remove();
    }
    */
    
}

function resetAssets(){
	$("#assetAddId_show").attr("disabled",false);
	$("#assetAddName_show").attr("disabled",false);
	$("#assetAddNum_show").attr("disabled",false);
	$("#must1").html($("#must1").html().replace("*",""));
	$("#must2").html($("#must2").html().replace("*",""));
}

function saveAssets(obj){
	var saveAsset = "";
	if($(obj).parent().find("p").length>0){
		$(obj).parent().children("p").each(function(index){
			if(index>0){
				saveAsset += "|";
			}
			saveAsset += $.trim($(this).children("span:eq(0)").html())+","+$.trim($(this).children("span:eq(1)").html())+","+$.trim($(this).children("span:eq(2)").html());
		});
	}
	$("#assetAdd").val(saveAsset);
}

function clickOtherMajor(obj){
	if($(obj).attr("checked")=="checked"){
		$("#safe").children("option[value=其他类]").attr("selected","selected");
		$("#safe").attr("disabled",true);
		//$("#safe").parent().find("span").remove();
		//$("#safe").parent().append("<span style='display:inline'>&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox'/>&nbsp;新增资产&nbsp;&nbsp;&nbsp;&nbsp;<input type='checkbox'/>&nbsp;更新资产</span>");
		if($("#otherTr").length==1){
            $("#otherTr").remove();
        }
		$("#safe").parent("td").parent("tr").after("<tr id=\"otherTr\" class=\"content6\"><td class=\"lableTd t_r\">其他类</td><td colspan=\"3\" class=\"content6\"><input id=\"other\" type=\"text\" class=\"input_large\"></td></tr>");
	}else{
		$("#safe").children("option:eq(0)").attr("selected","selected");
		$("#safe").attr("disabled",false);
		//$("#safe").parent().find("span").remove();
		if($("#otherTr").length==1){
            $("#otherTr").remove();
        }
	}
}

