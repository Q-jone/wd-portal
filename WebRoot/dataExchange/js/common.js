function ywbl(recValid){
	if(recValid=='0'){
		alert("已办理");
	}else{
		var objFilePaths = document.getElementsByName("filePath");
		var objFileNames = document.getElementsByName("fileName");
		var objFileExtNames = document.getElementsByName("fileExtName");		
		var objFileSizes = document.getElementsByName("fileSize");
		var objVersions = document.getElementsByName("version");
		var objMemos = document.getElementsByName("memo");

		var vFilePath = '';
		var vFileName = '';
		var vFileExtName = '';		
		var vFileSize = '';		
		var vVersion = '';
		var vMemo = '';
		if(objFilePaths){
			var num = objFilePaths.length;
			for(var i=0;i<num;i++){
				if(i>0){
					vFilePath+=",";
					vFileName+=",";
					vFileExtName+=",";					
					vFileSize+=",";
					vVersion+=",";
					vMemo+=",";
				}
				vFilePath += objFilePaths[i].value;	
				vFileName += objFileNames[i].value;	
				vFileExtName += objFileExtNames[i].value;	
				vFileSize += objFileSizes[i].value;					
				vVersion += objVersions[i].value;	
				vMemo += objMemos[i].value;	
			}
		}		

		$.ajax({
			type: 'POST',
			url: '/portal/dataExchange/saveAttach.action?random='+Math.random(),
			data:{
				"appName" : "stpt1819",
				"filePath" : vFilePath,	
				"fileName" : vFileName,	
				"fileExtName" : vFileExtName,					
				"fileSize" : vFileSize,	
				"version" : vVersion,	
				"memo" : vMemo			
			},
			dataType:'json',
			cache : false,
			error:function(){alert('操作失败，请稍后再试！')},
			success: function(obj){
				if(obj.success){	
				//alert(obj.groupId);			
					document.formAdd.attachFj.value=obj.groupId;
					document.getElementById('formAdd').submit();		
					setTimeout("confirmValid()",3000);
				}
			
			}
		});	
	}
}

function confirmValid(){
	alert("操作成功!");
	$("[name=greataContractNum]").val($("[name=contractNum]").val());
	document.getElementById('recUpdate').submit();	
	document.getElementById('ywbl').style.display="none";	
}

function contractYwbl(recValid){
	
	if(recValid=='0'){
		alert("已办理");
	}else{
		var r=window.showModalDialog("modalDialog.jsp?p=deptLeader",window,"dialogWidth=300px;dialogHeight=155px;scroll=auto");
		if(r!=null){
			
			var objFilePaths = document.getElementsByName("filePath");
			var objFileNames = document.getElementsByName("fileName");
			var objFileExtNames = document.getElementsByName("fileExtName");				
			var objFileSizes = document.getElementsByName("fileSize");
			var objVersions = document.getElementsByName("version");
			var objMemos = document.getElementsByName("memo");

			var vFilePath = '';
			var vFileName = '';
			var vFileExtName = '';				
			var vFileSize = '';
			var vVersion = '';
			var vMemo = '';
			if(objFilePaths){
				var num = objFilePaths.length;
				for(var i=0;i<num;i++){
					if(i>0){
						vFilePath+=",";
						vFileName+=",";
						vFileExtName+=",";								
						vFileSize+=",";
						vVersion+=",";
						vMemo+=",";
					}
					vFilePath += objFilePaths[i].value;	
					vFileName += objFileNames[i].value;	
					vFileExtName += objFileExtNames[i].value;						
					vFileSize += objFileSizes[i].value;	
					vVersion += objVersions[i].value;	
					vMemo += objMemos[i].value;	
				}
			}		

			$.ajax({
				type: 'POST',
				url: '/portal/dataExchange/saveAttach.action?random='+Math.random(),
				data:{
					"appName" : "stpt1819",				
					"filePath" : vFilePath,
					"fileName" : vFileName,	
					"fileExtName" : vFileExtName,						
					"fileSize" : vFileSize,	
					"version" : vVersion,	
					"memo" : vMemo
				},
				dataType:'json',
				cache : false,
				error:function(){alert('操作失败，请稍后再试！')},
				success: function(obj){
					if(obj.success){	
						//alert(obj.groupId);			
						document.formAdd.htAttach.value=obj.groupId;
						document.formAdd.leader.value=r;
						document.getElementById('formAdd').submit();		
						setTimeout("confirmValid()",3000);
					}
				
				}
			});				
		}else{
			alert("请先选择部门领导!");
		}	
	
	}
}

function backUpContract(){
	$.ajax({
		type: 'POST',
		url: '/portal/dataExchange/backUpContract.action?random='+Math.random(),
		data:{
			"id" : $("[name=id]").val()
		},
		dataType:'json',
		cache : false,
		error:function(){alert('系统连接失败，请稍后再试！')},
		success: function(obj){
			alert(obj.result);
			if("保存成功"==obj.result){
				document.getElementById('ywbl').style.display="none";	
				document.getElementById('backup').style.display="none";
			}			
		}
	});
}

 function getContractNum(){
	var r=window.showModalDialog("modalDialog.jsp?p=bh",window,"dialogWidth=600px;dialogHeight=120px;scroll=no");
	if(r!=null){	 
		document.formAdd.contractNum.value=r;
	}
	
}