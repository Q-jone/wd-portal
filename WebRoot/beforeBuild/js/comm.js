/*	
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
	 */
	 
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
        
			/*
			$("[id=status_td]").each(function(){
					$(this).html($(this).html().replace("1","进行中").replace("2","预归档").replace("3","已归档").replace("4","已终止"));
			});
			
			var h_status=$("#h_status");
			  var status=$("#status");		  
			  for(var i=1;i<status.children("option").length;i++){
			    if(status.children("option:eq("+i+")").val()==h_status.val())
			    status.children("option:eq("+i+")").attr("selected",true);
			  }	
       		*/
      })
      
      function   isUnsignedInteger(a)
		{
        	var   reg =/^\d+$/;
		    return reg.test(a);
		} 
        
        function deleteFunc(id,namespace){
			if(confirm("确定删除？")){
	 			$.ajax({
					url: "/portal/"+namespace+"/delete.action",
					type: 'post', 
					data:{
						id:id,
						random:Math.random()
					},
					dataType: 'json', 
					error:function(){
						alert("系统连接失败，请稍后再试！");
					},
					success: function(obj){			
					   $("#form").submit();
					}
				});	
			}
 		}
        
        function orderFunc(){
        	$("[order=yes]").children("th").each(function(){
        		$(this).click(function(){
        			if($(this).attr("orderParam")!=null&&$(this).attr("orderParam")!=""){
        				if($("[name=orderParam]").val()==$(this).attr("orderParam")){
            				if($("[name=orderValue]").val()=="asc"){
            					$("[name=orderValue]").val("desc");
            				}else{
            					$("[name=orderValue]").val("asc");
            				}
            			}else{
            				$("[name=orderValue]").val("asc");
            			}
            			$("[name=orderParam]").val($(this).attr("orderParam"));
            			$("#form").submit();
        			}
        		});
        	});
        	
        	if($("[name=orderParam]").val()!=""){
        		$("[orderParam="+$("[name=orderParam]").val()+"]").append("<img style='display:inline;' src='/portal/beforeBuild/images/"+$("[name=orderValue]").val()+".png'/>");
        	}
        }
      
      