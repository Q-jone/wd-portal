function checkout(){
	if($("#department").val().length>0&&$("#department").val().length<=50){
		
	}else{
		alert("单位名称不能为空且长度不能超过50个汉字长度");
		return false;
	}
	if($("#inspectDate").val().length>0){
		
	}else{
		alert("检查日期必填");
		return false;
	}
	if($("#degree").val().length>0&&$("#degree").val().length<=25){
		
	}else{
		alert("严重程度不为空且不超过25个汉字长度");
		return false;
	}
	if($("#category").val().length>0&&$("#category").val().length<=50){
		
	}else{
		alert("业务类型不为空且不超过50个汉字长度");
		return false;
	}
	if($("#subject").val().length>0&&$("#subject").val().length<=50){
		
	}else{
		alert("科目不为空且不超过50个汉字长度");
		return false;
	}
	if($("#subSubject").val().length>0&&$("#subSubject").val().length<=50){
		
	}else{
		alert("科目子类不为空且不超过50个汉字长度");
		return false;
	}
	if($("#planBeginDate").val().length>0){
		
	}else{
		alert("计划开始时间不为空");
		return false;
	}
	if($("#planEndDate").val().length>0){
		
	}else{
		alert("计划结束时间不为空");
		return false;
	}
	if(new Date($("#planEndDate").val()).getTime()<new Date($("#planBeginDate").val()).getTime()){
		alert("计划结束时间不应小于计划开始时间！！");
		return false;
	}
	if($("#tractDate").val().length>0){
		
	}else{
		alert("跟踪日期不为空！！");
		return false;
	}
	if($("#inspectInfo").val().length>0&&$("#inspectInfo").val().length<=250){
		
	}else{
		alert("检查问题描述不能超过250个汉字长度！！！");
		return false;
	}
	if($("#plan").val().length<=250){
		
	}else{
		alert("整改方案不能超过250个汉字长度！！！");
		return false;
	}
	if($("#workable").val().length<=100){
		
	}else{
		alert("落实情况不能超过100个汉字长度！！！");
		return false;
	}
	if($("#reformMemo").val().length<=250){
		
	}else{
		alert("整改备注不能超过250个汉字长度！！！");
		return false;
	}
	if($("#review").val().length<=100){
		
	}else{
		alert("复核情况不能超过100个汉字长度！！！");
		return false;
	}
	if($("#tractMemo").val().length<=250){
		
	}else{
		alert("跟踪备注不能超过250个汉字长度！！！");
		return false;
	}
	if($("#memo").val().length<=250){
		
	}else{
		alert("备注不能超过250个汉字长度！！！");
		return false;
	}

	return true;
}