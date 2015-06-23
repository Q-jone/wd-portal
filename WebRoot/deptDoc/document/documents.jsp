<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="cn">
<head>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<meta charset="utf-8" />
<title></title>
<link rel="stylesheet" href="<%=path %>/css/formalize.css" />
<link rel="stylesheet" href="../css/reset.css" />
<link rel="stylesheet" href="<%=path%>/deptDoc/js/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="<%=path%>/deptDoc/css/style.css" type="text/css">
<link type="text/css" href="<%=path%>/css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	

<script src="<%=path%>/js/jquery-1.7.1.js"></script>
<script src="<%=path%>/deptDoc/js/ztree/jquery.ztree.all-3.4.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/flick/jquery-ui-1.8.18.custom.min.js"></script>
<script src="<%=path%>/js/flick/jquery.ui.datepicker-zh-CN.js"></script>
<script src="<%=path%>/deptDoc/js/page-common.js"></script>
<style type="text/css">
.ui-datepicker-title span {display:inline;}
button.ui-datepicker-current { display: none; }
</style>		
<script type="text/javascript">
      $(document).ready(function () {
     	 var totalPage = '${pageResultSet.pageInfo.totalPage}';
     	totalPage = totalPage*1;
     	if(totalPage > 0){
         	var totalOption = "";
         	for(var i=1;i<=totalPage;i++){
    			totalOption += "<option value='"+i+"'>"+i+"</option>";						
    		}
    		$("#page_out").html(totalOption);
    		$("#page_out").val('${pageResultSet.pageInfo.currentPage}');     		
     	}else{
     		$("#page_out").html("<option value='0'>0</option>");
     	}

     	//$("#pageSize_out option:last").val('${pageResultSet.pageInfo.totalRow}');
     	$("#pageSize_out").val('${pageResultSet.pageInfo.pageSize}');

      });
		
      function newFile(){
      	 $("#handleZone").dialog({
      		title: '新增文件',
				modal: true,
				autoOpen: false,
				width: 660,
				height: 450,
				zIndex: 9999,
				buttons: [
					{
						text: "取消",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
				]});    	  
    	  var surl = "<%=path%>/zdocsFile/newDoc.action?catalogId=${vo.catalogId}&flag=${vo.folder.flag}"
    	  var frame = '<iframe frameBorder="0" scrolling="yes" src="'+surl+'" style="HEIGHT:350px; VISIBILITY: inherit; WIDTH:620px; Z-INDEX:999"></iframe>'
    	  $('#handleZone').html(frame);
    	  $("#handleZone").dialog('open');
      }
      
      function modifyDoc(fileId){
       	 $("#handleZone").dialog({
       		title: '编辑文件',
 				modal: true,
 				autoOpen: false,
 				width: 660,
 				height: 450,
 				zIndex: 9999,
 				buttons: [
 					{
 						text: "取消",
 						click: function() {
 							$( this ).dialog( "close" );
 						}
 					}
 				]});    	  
     	  var surl = "<%=path%>/zdocsFile/modifyDoc.action?fileId="+fileId;
     	  var frame = '<iframe frameBorder="0" scrolling="yes" src="'+surl+'" style="HEIGHT:350px; VISIBILITY: inherit; WIDTH:620px; Z-INDEX:999"></iframe>'
     	  $('#handleZone').html(frame);
     	  $("#handleZone").dialog('open');
       }
      
      function copyFile(op){
      	 $("#handleZone").dialog({
      		 	title: op=='move'?'移动文件':'复制文件',
				modal: true,
				autoOpen: false,
				width: 320,
				height: 510,
				zIndex: 9999,
				close: function(){
				},				
				buttons: [
					{
						text: "取消",
						click: function() {
							$( this ).dialog( "close" );
						}
					}
				]});    	  
      	 
      	 var fileIds = '';
      	 $("input[name='filechk']:checked").each(function(){
      		fileIds += ','+this.value;
      	 });
      	 if(fileIds == ''){
      		 alert('没有勾选文件！');
      		 return;
      	 }
      	fileIds = fileIds.substr(1);
		var surl = "<%=path%>/zdocsCatalog/copyDestTree.action?fileIds="+fileIds+"&flag=${vo.folder.flag}&op="+op+"&catalogId=${vo.catalogId}";
    	var frame = '<iframe frameBorder="0" scrolling="yes" src="'+surl+'" style="HEIGHT:400px; VISIBILITY: inherit; WIDTH:300px; Z-INDEX:999"></iframe>'
    	$('#handleZone').html(frame);
    	$("#handleZone").dialog('open');
	}
      
	function editDir(create){
       	 $("#handleZone").dialog({
       		title: '文件夹管理',
 				modal: true,
 				autoOpen: false,
 				width: 660,
 				height: 450,
 				zIndex: 9999,
 				close: function(){
 					
 				},
 				buttons: [
 					{
 						text: "取消",
 						click: function() {
 							$( this ).dialog( "close" );
 						}
 					}
 				]});    
       	 
     	  var surl = create?"<%=path%>/zdocsCatalog/newDir.action?catalogId=${vo.catalogId}":"<%=path%>/zdocsCatalog/modifyDir.action?catalogId=${vo.catalogId}";
     	  var frame = '<iframe frameBorder="0" scrolling="yes" src="'+surl+'" style="HEIGHT:350px; VISIBILITY: inherit; WIDTH:620px; Z-INDEX:999"></iframe>'
     	  $('#handleZone').html(frame);
     	  $("#handleZone").dialog('open');
	}
	
	function removeDir(){
		if(confirm('确定要删除当前文件夹吗？')){
	 		$.post(
					'/portal/zdocsCatalog/removeDir.action?random='+Math.random(),
					{
						"catalogId": 	'${vo.catalogId}'
					},
					function(obj, textStatus, jqXHR){
						if(obj == '1'){
							parent.reloadDirTree('${vo.folder.parentSid}');
							$('body').html('<div class="browseMessages">当前文件夹已删除</div>');
						}else{
							alert("服务器连接失败，请稍后再试!");
						}
					},
					"json"
				).error(function() { alert("服务器连接失败，请稍后再试!"); });			
		}
	}
	
	function removeDoc(fileId){
		if(confirm('确定要删除这个文件吗？')){
	 		$.post(
					'/portal/zdocsFile/removeDoc.action?random='+Math.random(),
					{
						"fileId": fileId
					},
					function(obj, textStatus, jqXHR){
						if(obj == '1'){
							alert('文件删除成功！');
							list();
						}else{
							alert("服务器连接失败，请稍后再试!");
						}
					},
					"json"
				).error(function() { alert("服务器连接失败，请稍后再试!"); });			
		}
	}	
	
	function authority(flag){
		$("#handleZone").dialog({
            title: '阅读文件权限设置（请在文件列表中勾选）',
            modal: true,
            autoOpen: false,
            width: 320,
            height: 510,
            zIndex: 9999,
            close: function(){
            },              
            buttons: [
                {
                    text: "取消",
                    click: function() {
                        $( this ).dialog( "close" );
                    }
                }
            ]});          
     
	     var fileIds = '';
	     $("input[name='filechk']:checked").each(function(){
	        fileIds += ','+this.value;
	     });
	     if(fileIds == ''){
	         alert('没有勾选文件！');
	         return;
	     }
	    fileIds = fileIds.substr(1);
	    var surl = flag?
	    		"<%=path%>/zdocsFile/authorityPerson.action?fileIds="+fileIds+"&flag=${vo.folder.flag}&catalogId=${vo.catalogId}&authorityFlag=1":
	    		"<%=path%>/zdocsFile/authorityPerson.action?fileIds="+fileIds+"&flag=${vo.folder.flag}&catalogId=${vo.catalogId}&authorityFlag=0";
	                    	
	    var frame = '<iframe frameBorder="0" scrolling="yes" src="'+surl+'" style="HEIGHT:400px; VISIBILITY: inherit; WIDTH:300px; Z-INDEX:999"></iframe>'
	    $('#handleZone').html(frame);
	    $("#handleZone").dialog('open');
	}
	
	function closeDialog(){
		$("#handleZone").dialog('close');  
	}
	
	function reloadDirTree(){
		parent.reloadDirTree('${vo.catalogId}');
		closeDialog();
	}
	
	function toggleQuery(){
		$(".querybar").fadeToggle();
	}
	
	function filechkSelectedAll(allObj){
		$("input[name='filechk']").attr("checked",allObj.checked);
	}
	function filechkSelected(allChkName,chkObj){
		var allChecked = true;
		$("input[name='filechk']").each(function(){
			if(!this.checked){
				allChecked = false;
				return false;
			}
		});
		$("input[name='filechk_SelectAll']").attr("checked",allChecked);
	}
</script>


</head>

<body style="height:500px;">
<div class="navbar">
<s:iterator value="#request.vo.parentFolders" id="zfile" status="s">
	<s:if test="#s.index!=0"> >> </s:if>${zfile.CATALOG_NAME}
</s:iterator>
</div>
<div class="toolbar">
	<table>
	<tr>
		<s:if test="#request.vo.write">
			<td><a href="javascript:newFile()">新增文件</a></td>
			<td><a href="javascript:copyFile('copy')">复制文件</a></td>
			<td><a href="javascript:copyFile('move')">移动文件</a></td>
			<td><a href="javascript:editDir(true)">新建文件夹</a></td>	
			<s:if test='#request.vo.catalogLevel==1 || 
			     #request.vo.folder.parentSid == "share"'>
			</s:if>
			<s:else>
			 <td><a href="javascript:editDir()">修改文件夹</a></td>
			 <td><a href="javascript:removeDir()">删除文件夹</a></td>
			</s:else>
			<s:if test='#request.vo.folder.flag == "100"'>
			 <td><a href="javascript:authority(true)">新增权限</a></td>
			 <td><a href="javascript:authority(false)">撤销权限</a></td>
			</s:if>
		</s:if>
			<td><a href="javascript:toggleQuery()">查询文件</a></td>
	</tr>
	</table>
</div>
<div style="width: 100%">
<s:form action="documents" id="list" method="post" namespace="/zdocsFile" theme="simple">
<input type="hidden" name="parent_sid_equal" value="${vo.parent_sid_equal}">
<input type="hidden" name="catalogId" value="${vo.catalogId}">
<input type="hidden" name="catalogLevel" value="${vo.catalogLevel}">
<input type="hidden" name="order" value="update_date desc">

<div class="querybar">
	<div>
		<span>主题词：<input type="text" name="keyword_like" value="${vo.keyword_like}" class="q-field"/></span>	
		<span>文件标题：<input type="text" name="file_name_like" value="${vo.file_name_like}" class="q-field"/></span>
	</div>
	<div>
		<span>上传人：<input type="text" name="create_user_name_like" value="${vo.create_user_name_like}" class="q-field"/></span>
		<span>上传时间：<input type="text" size="15" name="create_date_startDate" class="q-field date" value="${vo.create_date_startDate}" readonly="readonly"/></span>
        -
		<span><input type="text" size="15" name="create_date_endDate" class="q-field date" value="${vo.create_date_endDate}" readonly="readonly" /></span>
		<span><input class="btn" type="button" value="查询" id="query"/></span>
		<span><input class="btn" type="button" value="还原" id="reset"/></span>
	</div>
</div>
<div style="width:100%" id="">
<table class="datTbl" id="mytable" cellspacing="0" cellpadding="0" width="97%">
	<thead>
		<tr>
		<th width="5%">
		<s:if test="#request.vo.write">
		<input onclick='filechkSelectedAll(this);' style="valign: middle; align: center" type=checkbox value="" name=filechk_SelectAll>
		</s:if>
		</th>
		<th width="5%">序号</th>
		<th width="30%">文件名称</th>
		<th width="30%">主题词</th>
		<th width="10%">上传人</th>
		<th>操作</th>
		</tr>
	</thead>
	<tbody>
 	<s:iterator value="#request.pageResultSet.list" id="zfile" status="s">
	<tr>
	<td>
	<s:if test="#request.vo.write">
	<input name="filechk" onclick="filechkSelected('filechk_SelectAll',this);" style="valign: middle; align: center" type="checkbox" value="${zfile.SID}" />
	</s:if>
	</td>
	<td style="text-align:center;"><s:property value="#s.index+1"/></td>
	<td>${zfile.FILE_NAME}</td>
	<td>${zfile.KEYWORD}</td>
	<td>${zfile.CREATE_USER_NAME}</td>
	<td style="text-align:center;">
	<a href="${zfile.FILE_PATH}" target="_blank">下载</a>&nbsp;
	<s:if test="#request.vo.write">
		<a href="javascript:modifyDoc('${zfile.SID}')">修改</a>&nbsp;
		<a href="javascript:removeDoc('${zfile.SID}')">删除</a>
	</s:if>
	</td>
	</tr>
	</s:iterator>
                              <tr>
                                <td colspan="6"><div class="clearfix">
					        	<input type="hidden" name="pageSize" id="pageSize" value="${pageResultSet.pageInfo.pageSize}"/>
                                <input type="hidden" name="page" id="page" value="${pageResultSet.pageInfo.currentPage}"/>                                
                                <input type="hidden" id="totalPage_out" value="${pageResultSet.pageInfo.totalPage}">
                                <span class="fl">&nbsp;每页显示条数：  
                                <select id="pageSize_out">
	                                <option value="10">10</option>
	                                <option value="15">15</option>
	                                <option value="20">20</option>
	                                <option value="30">30</option>
	                                <!-- <option value="-1">显示全部</option> -->
                                </select>
                                </span>
                                <span class="fl">
                                &nbsp; 跳转至：
                                    <select id="page_out"></select>
                                </span>
                                <s:set name="start" value="(#request.pageResultSet.pageInfo.currentPage-1)*#request.pageResultSet.pageInfo.pageSize + 1"/>
                                <span id="totalPage" class="fr">当前显示
	                                <s:if test="#request.pageResultSet.pageInfo.totalRow==0">0条记录，总记录：0
	                                </s:if>
	                                <s:else>
	                                <s:property value="#start"/>-<s:property value="#start+#request.pageResultSet.list.size-1"/>条记录，总记录：${pageResultSet.pageInfo.totalRow}
	                                </s:else>
                                </span>
                                <span class="fr" style="margin-right:10px;">
                                <ul class="clearfix pager">
                                    <li class="fl" id="prePage"><a href="javascript:void(0)"> &nbsp;上一页 &nbsp;</a> </li>
                                    <li class="fl" id="nextPage"><a href="javascript:void(0)"> &nbsp;下一页 &nbsp;</a></li>
                                </ul>
                                </span>
                              </div>
                                </td>
                              </tr>		
	</tbody>

</table>
</div>
</s:form>
</div>
<div id="handleZone" title="增加"></div>
</body>
</html>