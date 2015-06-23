function checkout(){
	var req=new RegExp(/^\d+$/);
	
	if($("#department").val().length>0&&$("#department").val().length<=50){}else{
		alert("单位不能为空且不超过50个汉字长度");
		return false;
	}
	if($("#riskFrom").val().length>0&&$("#riskFrom").val().length<=50){}else{
		alert("风险来源不能为空且不超过50个汉字长度");
		return false;
	}
	if($("#discovery").val().length>0){}else{
		alert("发现时间不能为空！！！");
		return false;
	}
	if($("#category").val().length>0&&$("#category").val().length<=50){}else{
		alert("类别不能为空且不超过50个汉字长度");
		return false;
	}
	if($("#riskLevel").val().length>0&&$("#riskLevel").val().length<=25){}else{
		alert("风险等级不能为空且不超过25个汉字长度");
		return false;
	}
	
	if($("#dateLimit").val().length>0&&req.test($("#dateLimit").val())){
		
	}else{
		alert("风险处置时间不能为空且位数字！！！");
		return false;
	}
	if($("#trackDate").val().length>0&&$("#trackDate").val().length<=50){}else{
		alert("跟踪日期不能为空");
		return false;
	}
	if($("#riskInfo").val().length>0&&$("#riskInfo").val().length<=250){}else{
		alert("风险描述不为空且不超过250个汉字长度");
		return false;
	}
	if($("#plan").val().length>0&&$("#plan").val().length<=250){}else{
		alert("风险处置方案不为空且不超过250个汉字长度");
		return false;
	}
	if($("#trackInfo").val().length>0&&$("#trackInfo").val().length<=25){}else{
		alert("处置跟踪情况不为空且不超过25个汉字长度");
		return false;
	}
	return true;
}