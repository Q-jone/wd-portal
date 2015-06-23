<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String checkNode = request.getParameter("checkNode");
	if(checkNode==null){
		checkNode = "";
	}
	String rootId = request.getParameter("rootId");
	if(rootId==null){
		rootId = "2500";
	}
 %>
<!DOCTYPE html>
<html lang="cn">
<head>
<meta charset="utf-8" />
		<script type="text/javascript" src="js/jquery-1.7.2.min.js"></script>
<link type="text/css" href="../datepicker/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../datepicker/js/jquery-ui-1.8.18.custom.min.js"></script>
<script type="text/javascript">

		$(document).ready(function(){
				// Dialog
				$('#dialog').dialog({
					autoOpen: false,
					width: 600,
					buttons: {
						"Ok": function() {
							$(this).dialog("close");
						},
						"Cancel": function() {
							$(this).dialog("close");
						}
					}
				});
				
					// Dialog Link
				$('#dialog_link').click(function(){
					$('#dialog').dialog('open');
					return false;
				});
				
			
		});
		//-->
	</script>
			<style type="text/css">
			/*demo page css*/
			body{ font: 62.5% "Trebuchet MS", sans-serif; margin: 50px;}
			.demoHeaders { margin-top: 2em; }
			#dialog_link {padding: .4em 1em .4em 20px;text-decoration: none;position: relative;}
			#dialog_link span.ui-icon {margin: 0 5px 0 0;position: absolute;left: .2em;top: 50%;margin-top: -8px;}
			ul#icons {margin: 0; padding: 0;}
			ul#icons li {margin: 2px; position: relative; padding: 4px 0; cursor: pointer; float: left;  list-style: none;}
			ul#icons span.ui-icon {float: left; margin: 0 4px;}
		</style>
</head>
<body>

		<!-- ui-dialog -->
		<div id="dialog" title="Dialog Title">
			<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
		</div>


</body>
</html>