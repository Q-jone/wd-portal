
function check(){
	if($("#projectNumber").val().length>50){
		alert("项目编号长度不能超过100个字节");
		$("#projectNumber").focus();
		return false;
	}
	
	if($("#projectName").val().length>100){
		alert("项目名称长度不能超过100个汉字");
		$("#projectName").focus();
		return false;
	}
	if($("#projectType").val().length>50){
		alert("项目类型长度不能超过50个汉字");
		$("#projectType").focus();
		return false;
	}
	
	return true;
}