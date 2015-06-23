jQuery.notNull = function(options){
			if (typeof(options) == "undefined" || options== null){
				return "";
			}else {
				return options;
			}
		}
	
		
		function list(){
			//$.blockUI({ message: $('#domMessage') }); 
			 $("#list").submit();
		}
		
		function refresh(){
	 		$("#page").val(1);
      		 list();
		}
		
	 	$(function(){
	 		
	 		
	 		$("#prePage").click(function(){
	 			if($("#page_out").val() == 1 || $("#page_out").val() == 0){}
	 			else{
	 				$("#page_out").val(parseInt($("#page_out").val()-1));
	 				$("#page").val($("#page_out").val());
	 	        	list();
	 			}
	 			
	 		})
	 		
	 		$("#nextPage").click(function(){
	 			if(($("#page_out").val() == $("#totalPage_out").val()) || $("#page_out").val() ==0){}
	 			else{
	 				$("#page_out").val(parseInt($("#page_out").val())+1);
	 				$("#page").val($("#page_out").val());
	 	        	list();
	 			}
	 			
	 		})
	 	
	 		
	 	$("#submit").click(function(){
       		 list();
       	 })
       	 
	 	$("#query").click(function(){
	 		refresh();
       	 })       	 
       	 
       	 $("#reset").click(function(){
       		$('.q-field').val('');
	 		refresh();
       	 })       	 
       	
        $(document).on("change","#page_out",function(){
        	$("#page").val($(this).val());
        	list();
        })
        
        $(document).on("change","#pageSize_out",function(){
        	if($("#page_out").val()!=0){
	        	$("#pageSize").val($(this).val());
	        	list();
         	}
        })
        
 		$('[name=create_date_startDate]').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'create_date_startDate'//仅作为“清除”按钮的判断条件						
		});
 		$('[name=create_date_endDate]').datepicker({
			//inline: true								
			"changeYear":true,
			"showButtonPanel":true,	
			"closeText":'清除',	
			"currentText":'create_date_endDate'//仅作为“清除”按钮的判断条件						
		});	
		//datepicker的“清除”功能
          $(document).on("click", ".ui-datepicker-close",function (){              
             if($(this).parent("div").children("button:eq(0)").text()=="create_date_startDate") $("[name=create_date_startDate]").val("");
             if($(this).parent("div").children("button:eq(0)").text()=="create_date_endDate") $("[name=create_date_endDate]").val("");     
            });
		
           //list();
           
		})	