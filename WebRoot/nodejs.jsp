<%@ page contentType="text/html; charset=utf-8"%>

<!DOCTYPE html>
<html lang="cn">
<meta charset="utf-8" />
	<head>
		<title>欢迎</title>
		<script src="js/jquery-1.7.1.js"></script>
		
		<script type="text/javascript">

	$.ajax({
		// url:	"/portal/constructionNotice/showLineInfo.action?random="+Math.random(),
		url :	"http://10.1.41.252:8888",
		type:	"post",
		data:	{
					"startDate" : "2012/04/08",
					"endDate"	: "2012/04/12"
					},
	   cache: 	false,
	   error:	function(){alert('系统连接失败，请稍后再试！')},
	 success: 	function(obj){	
					if(obj){
						alert(obj);
					}		//html，即要显示的信息，后台已完成拼接
				}
	});

</script>

</head>

<div id="zs"></div>

</html>