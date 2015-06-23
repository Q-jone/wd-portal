function checkout(){
    var idsstart=new Array($("#mytable tr").length);
    var idsend=new Array($("#mytable tr").length);
    
    var ids="";
    $("input[id^=planBeginDate]").each(function(){
    	ids+=","+$(this).val();
    	
    });
    idsstart=ids.split(",");
    var id="";
    $("input[id^=planEndDate]").each(function(){
    	id+=","+$(this).val();
    	
    });
    idsend=id.split(",");
    for(var i=1;i<idsend.length;i++){
    	if(new Date(idsend[i]).getTime()<new Date(idsstart[i]).getTime()){
    		alert("计划开始时间不能大于计划结束时间！！！"+i);
    		return false;
    	}
    }
    
    $("input[id^=memo]").each(function(){
    	if($(this).val().length>250){
    		alert("说明字数不能超过250个汉字！！");
    		return false;
    	}
    	
    });
    return true;
}