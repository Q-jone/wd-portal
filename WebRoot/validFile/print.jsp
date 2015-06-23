<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
 <script src="js/jquery-1.10.2.min.js"></script>
        <script src="js/jquery-migrate-1.2.1.min.js"></script>
<meta charset="utf-8" />
	<style>
	@media print{
	.nprint{display:none;}
	}
	</style>
	<style>
		.newsTitle1 {
				font-size: 27px;
				line-height: 120%;
				text-decoration: none;
				font-weight: bold;
				font-family: 黑体;
		}
	</style>
	<style>
		td ,th{
		border:1px solid #000000;
		}
		table{
			border-collapse : collapse;
		}
		body {
			font-size:12pt;
			font-family: 宋体;
		}
		</style>
	<script type="text/javascript">
		function testtt(){
			$("tr>td:nth-child("+$('tr:eq(0)>td').length+")").remove(); 
		}
		
		function prints(){
			//alert(1);
			window.print();
		}
		
		$(document).ready(function(){
			if($('tr:eq(0)>th').length < $('tr:eq(1)>td').length){
				$("tr>td:nth-child(1)").remove();
	            $("tr>th:nth-child(1)").remove();
	            $("tr>th:nth-child("+$('tr:eq(0)>th').length+")").remove();
	            $("tr>td:nth-child("+$('tr:eq(1)>td').length+")").remove();
	            $("tr>td:nth-child("+$('tr:eq(1)>td').length+")").remove();
	            $("tr>td:nth-child("+$('tr:eq(1)>td').length+")").remove();
			}else{
				$("tr>td:nth-child(1)").remove();
	            $("tr>th:nth-child(1)").remove();
			}
			
			
			
		});
	</script>
	
  </head>
  
  <body >
  	<br><br>
  	<center><span class='newsTitle1' style="width:100%;">
  	<script type="text/javascript">
    	document.write(window.opener.getTitle());
    </script>
  	</span></center>
  	<br>
  	<hr size="1">
  <table id='tab' cellpadding="3" cellspacing="1" align="center"  style="width:100%;word-break:break-all" align='center'>
  	
    <script type="text/javascript">
    	document.write(window.opener.getContent());
    </script>
</table>
<hr size="1">
<center class='nprint'><input type='button' value=' 打印 ' onclick='prints();'>&nbsp;<input type='button' value=' 关闭 ' onclick='javascript:window.close();'></center>
  </body>
</html>
