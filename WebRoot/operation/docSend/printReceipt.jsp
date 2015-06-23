<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
        <script src="../../validFile/js/jquery-1.10.2.min.js"></script>
        <script src="../../validFile/js/jquery-migrate-1.2.1.min.js"></script>
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
		
		function prints(){
			//alert(1);
			window.print();
		}
		
	</script>
	
  </head>
  
  <body >
  	<center><span class='newsTitle1'>
  	发文回执
  	</span></center>
  	<br>
  	<table id='tab' cellpadding="3" cellspacing="1" align="center"  style="width:100%;word-break:break-all" align='center'>
  	
    <script type="text/javascript">
    	document.write(window.opener.getReceiptTitle());
    </script>
</table>
  	<hr size="1">
  <table id='tab' cellpadding="3" cellspacing="1" align="center"  style="width:100%;word-break:break-all" align='center'>
  	
    <script type="text/javascript">
    	document.write(window.opener.getReceiptContent());
    </script>
</table>
<hr size="1">
<center class='nprint'><input type='button' value=' 打印 ' onclick='prints();'>&nbsp;<input type='button' value=' 关闭 ' onclick='javascript:window.close();'></center>
  </body>
</html>
