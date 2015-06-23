var DEPT_STR = '<div id="deptDiv" class="f_window" style="width:300px; "> '
		+ '<h3 class="clearfix mb10"><span class="fl">选择部门</span><div class="fr close"><a href="javascript:closeDept();">关闭窗口</a></div></h3>'
		+ '<div class="con" style="min-height:200px;"></div><div class="button t_c">'
		+ '<input type="button" value="确 认" />&nbsp;<input onclick="closeDept();" type="button" value="取 消" />'
		+ '</div></div>'
		+ '<div id="mainDiv" class="transparent"></div>';
document.write(DEPT_STR);


function closeDept() {
	$(parent.window.frames["leftFrame"].document).find("#leftDiv").hide();
	$(parent.window.frames["topFrame"].document).find("#topDiv").hide();
	$("#mainDiv").hide();
	$("#deptDiv").hide();
}
