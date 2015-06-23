function checkout(){
	var text="";
	$("input[id^=forwardGoal]").each(function(){
		
		if($(this).val().length>250){
			alert("推进目标计划字数不能超过250个汉字！！！");
			return false;
		}
	});
	return true;
}