$(function(){
			$('#date_start').datepicker({
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'date_start'//仅作为“清除”按钮的判断条件						
			});
						
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
        
			
			var h_status=$("#h_status");
			  var status=$("#status");		  
			 // for(var i=1;i<status.children("option").length;i++){
			 //   if(status.children("option:eq("+i+")").val()==h_status.val())
			 //   status.children("option:eq("+i+")").attr("selected",true);
			 // }	
			  status.val(h_status.val());
			  
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
     
      