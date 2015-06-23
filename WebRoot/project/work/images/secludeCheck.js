function realse(){
	
	if($("#progress").val().length<=250){}else{
		alert("推进情况字数不能超过250");
		return false;
	}
	if($("#memo").val().length<=250){}else{
		alert("补充说明字数不能超过250");
		return false;
	}
	return true;
}