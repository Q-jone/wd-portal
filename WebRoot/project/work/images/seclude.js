function checkout(){
	var reg=new RegExp(/^(\(\d{3,4}\)|\d{3,4}-)?\d{7,8}$/);//联系人电话的正则表达式
	if($("#year").val()==0){
		alert("请选择年份");
		return false;
	}
	
	if($("#workName").val().length>0&&$("#workName").val().length<=50){}else{
		alert("重点工作不能为空且字数不能超过50.");
		return false;
	}
	if($("#contactTel").val().length>0){
		
	}else{
		alert("联系电话为空！！！");
		return false;
	}
	if($("#kpi").val().length>0&&$("#kpi").val().length<=50){}else{
		alert("对应绩效考核项不能为空且字数不能超过50");
		return false;
	}
	if($("#responsible").val().length>0&&$("#responsible").val().length<=100){}else{
		alert("责任单位不能为空且字数不能超过100");
		return false;
	}
	if($("#cooperate").val().length<=100){}else{
		alert("配合单位字数不能超过100");
		return false;
	}
	if($("#objective").val().length>0&&$("#objective").val().length<=250){}else{
		alert("目标及要求不能为空且字数不能超过250");
		return false;
	}
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