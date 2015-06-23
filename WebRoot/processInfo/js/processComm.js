$(function(){
			$('#date_start').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'date_start'//仅作为“清除”按钮的判断条件						
			});
						
			//$("#indicatorDate").datepicker('option', $.datepicker.regional['zh-CN']); 
			$('#date_end').datepicker({
				//inline: true
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'date_end'//仅作为“清除”按钮的判断条件
			});	
			
			
			
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="date_start") $("#date_start").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="date_end") $("#date_end").val("");     
                              
            });
		});	
	 
	 
	 //跳转到制定页
        function goPage(pageNo,type){
			//type=0,直接跳转到制定页
	       if(type=="0"){
	       		
	       		var pageCount = $("#totalPageCount").val();
	       		var number = $("#number").val();
	       		if(!number.match(/^[0-9]*$/)){
	       			alert("请输入数字");
	       			$("#number").val($("#currentNumber").val());
	       			$("#number").focus();
	       			return ;
	       		}
	       		if(parseInt(number)>parseInt(pageCount)){
	       			$("#number").val(pageCount);
	       			$("#page").val(pageCount);
	       		}else{
	       			$("#page").val(number);
	       		}
	       }
			//type=1,跳转到上一页	       
	       if(type=="1"){
	       		$("#page").val(parseInt($("#number").val())-1);
	       }
			//type=2,跳转到下一页	       
	       if(type=="2"){
	            //alert($("#number").val());	       		
	       		$("#page").val(parseInt($("#number").val())+1);
	       		//alert($("#pageNo").val());
	       }
	       
	       //type=3,跳转到最后一页,或第一页
	       if(type=="3"){
	   	    	$("#page").val(pageNo);
	       }
       	   $("#form").submit();

        }
      

		
        $(document).ready(function () {
        	$(".t_c a").css("display","inline");
            var $tbInfo = $(".filter .query input:text");
            $tbInfo.each(function () {
                $(this).focus(function () {
                    $(this).attr("placeholder", "");
                });
            });
			
			var $tblAlterRow = $(".table_1 tbody tr:even");
			if ($tblAlterRow.length > 0)
				$tblAlterRow.css("background","#fafafa");	
        
			$("[id=status_td]").each(function(){
					$(this).html($(this).html().replace("1","进行中").replace("2","预归档").replace("3","已归档").replace("4","已终止"));
			});
			
			var h_status=$("#h_status");
			  var status=$("#status");		  
			  for(var i=1;i<status.children("option").length;i++){
			    if(status.children("option:eq("+i+")").val()==h_status.val())
			    status.children("option:eq("+i+")").attr("selected",true);
			  }	
       
      })
      
      function openForm1(task_user_name,model_id,instance_id,processName,pinstance_id,task_id){
				var url =''; 
				url += stptPath+'/openflowform.action';
				url +="?task_id="+encodeURI(task_id);
				url +="&task_user_name="+ encodeURI('ST/G00100000161');
				if (model_id == ''){
					url +="&model_id=" + encodeURI(processName);
				}else{
					url +="&model_id=" + encodeURI(model_id);
				}
		
				if (instance_id == ''){
					url +="&instance_id="+ encodeURI(pinstance_id);
				}else{
					url +="&instance_id="+ encodeURI(instance_id);
				}
				url +="&step_name=aa";
				url +="&pinstance_id=" + encodeURI(pinstance_id);
				url +="&processName=" + encodeURI(processName);
				url +="&task_type=1" ;
				var rtn = window.open(url);
				return false
			}

			function openScan1(processName,pinstance_id,task_id){
					var url1 = "";
					url1 = ultimusPath;
					url1 +='/sLogin/workflow/TaskStatus.aspx?TaskID=' + encodeURI(task_id);
					window.open(url1);
					return false; 
				}
			
			function openNewDocSendForm(processname,incident,taskid){
	        	var url = workflowPath+"/send-tDocSend/toFormPage.action?incidentNo="+incident+"&modelName="+encodeURI(processname)+"&taskId="+taskid;
	        	window.open(url);
	        }