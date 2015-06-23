function getDistinctValue(param,table){
		$.ajax({
			type : 'post',
			url : 'build/getListBySql.action?random='+Math.random(),
			dataType:'json',
			data:{
				sql:"select distinct "+param+" from "+table+" t"
			},
			cache : false,
			error:function(){
				alert("系统连接失败，请稍后再试！")
			},
			success:function(object){
				var addHtml = "";
				if(object!=null&&object.length>0){
					if(param!="stat_year"){
						addHtml += "<option value=''>---请选择---</option>";
					}
					for(var i=0;i<object.length;i++){
						addHtml += "<option value='"+object[i]+"'>"+object[i]+"</option>";
					}
				}
				$("[name="+param+"]").html(addHtml);
				$("[name="+param+"]").val($("#"+param).val());
			}
		});
	}