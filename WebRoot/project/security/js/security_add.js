function checkout(){
	var reg=new RegExp(/^\d+(\.\d+)?$/);
	
	if($("#sysName").val().length>0&&$("#sysName").val().length<=100){
		
	}else{
		alert("系统名称不能超过100个汉字！！！");
		return false;
	}
	if($("#category").val().length>0&&$("#category").val().length<=50){
		
	}else{
		alert("类别名称不能超过50个汉字！！！");
		return false;
	}
	if($("#department").val().length>0&&$("#department").val().length<=50){
		
	}else{
		alert("归属单位不能超过50个汉字！！！");
		return false;
	}
	if($("#important").val().length>0&&$("#important").val().length<=25){
		
	}else{
		alert("系统重要性不能超过25个汉字！！！");
		return false;
	}
	if($("#sysLevel").val().length>0&&$("#sysLevel").val().length<=25){
		
	}else{
		alert("系统定级不能超过25个汉字！！！");
		return false;
	}
	if($("#excuteQuantity").val().length>0&&reg.test($("#excuteQuantity").val())){
		
	}else{
		alert("实施系统数量不能为空或格式不对！！！");
		return false;
	}
	if($("#planBeginDate").val().length>0){
		
	}else{
		alert("计划开始时间不能为空！！！");
		return false;
	}
	if($("#planEndDate").val().length>0){
		
	}else{
		alert("计划结束时间不能为空！！！");
		return false;
	}
	if(new Date($("#planBeginDate").val()).getTime()>new Date(planEndDate).getTime()){
		alert("计划开始时间不能大于计划结束时间");
		return false;
	}
	if($("#memo").val().length>250){
		alert("备注字数过长，不能超过250个汉字");
		return false;
	}
	return true;
}