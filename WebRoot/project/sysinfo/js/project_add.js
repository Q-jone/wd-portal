function checkout(){
	var zz=new RegExp(/^\d+(\.\d+)?$/);
	
	if($("#projectName").val().length>0&&$("#projectName").val().length<=100){
		
	}else{
		alert("项目名称为必填且长度不能超过100个汉字");
		return false;
	}
	if($("#department").val().length>0&&$("#department").val().length<=50){
			
		}else{
			alert("责任单位为必填且长度不能超过50个汉字");
			return false;
		}
	if($("#leader").val().length>0&&$("#leader").val().length<=50){
			
		}else{
			alert("分管领导项目名称为必填且长度不能超过50个汉字");
			return false;
		}
	if($("#responsible").val().length>0&&$("#responsible").val().length<=50){
		
	}else{
		alert("责任人为必填且长度不能超过50个汉字");
		return false;
	}
if($("#projectType").val().length>0&&$("#projectType").val().length<=50){
		
	}else{
		alert("项目类别为必填且长度不能超过50个汉字");
		return false;
	}
	if($("#projectNumber").val().length>0&&$("#projectNumber").val().length<=50){
		
	}else{
		alert("项目编号为必填且长度不能超过50个汉字");
		return false;
	}
	if($("#level").val().length>0&&$("#level").val().length<=50){
		
	}else{
		alert("等保定级为必填且长度不能超过50个汉字");
		return false;
	}
	if($("#planActivateTime").val().length>0){
		
	}else{
		alert("计划建成时间为必填且长度不能超过50个汉字");
		return false;
	}
	if($("#investEstimate").val().length>0&&zz.test($("#investEstimate").val())){//校验位数字
		
	}else{
		alert("总投资估算为必填且为数字");
		return false;
	}
	if($("#projectDiscription").val().length>0&&$("#projectDiscription").val().length<=250){
		
	}else{
		alert("项目措施为必填且长度不能超过250个汉字");
		return false;
	}
	if($("#projectGoal").val().length>0&&$("#projectGoal").val().length<=250){
		
	}else{
		alert("项目目标计划建成时间为必填且长度不能超过250个汉字");
		return false;
	}
	/**
	if($("#projectForwardGoals").val().length>0&&$("#projectForwardGoals").val().length<=250){
		
	}else{
		alert("年度推进目标为必填且长度不能超过250个汉字");
		return false;
	}*/
	if($("#projectStatus").val().length>0&&$("#projectStatus").val().length<=250){
		
	}else{
		alert("项目状态为必填且长度不能超过250个汉字");
		return false;
	}
	if($("#memo").val().length<=250){
		
	}else{
		alert("说明长度不能超过250个汉字");
		return false;
	}
	return true;
}