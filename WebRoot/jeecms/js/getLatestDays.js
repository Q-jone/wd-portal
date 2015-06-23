function getLatestDays(type){
	var holidays = "2013-09-19,2013-09-20,2013-09-21,2013-10-01,2013-10-02,2013-10-03,2013-10-04,2013-10-05,2013-10-06,2013-10-07";
	var notHolidays = "2013-09-22,2013-09-29,2013-10-22";
	var days = 0;
	var latestDays = 0;
	var date = new Date();
	var strYear = "";    
	var strMonth = "";
    var strDay = ""; 
    var strWeek = ""; 
    var day_str = "";
	for(var i=1;i<20;i++){
		date = getOneDay(i,type);
		strYear = date.getFullYear();  
		strMonth = date.getMonth()+1; 
	    if(strMonth<10){
	    	strMonth = "0"+strMonth;
	    }   
	    strDay = date.getDate(); 
	    if(strDay<10){
	    	strDay = "0"+strDay;
	    }
	    day_str = strYear + "-" + strMonth + "-" + strDay;
	    strWeek = date.getDay(); 
		if(holidays.indexOf(day_str)>-1){
			
		}else if(notHolidays.indexOf(day_str)>-1){
			days += 1;
		}else if(strWeek>0&&strWeek<6){
			days += 1;
		}
		if(days==5){
			latestDays = i;
			break;
		}
	}
	return latestDays;
}

function getOneDay(num,type){
	var date = new Date();
	//var date = new Date("2013-10-09");
	var time = date.getTime();
	if(type=="past"){
		time-= num*24*3600000;
	}else if(type=="future"){
		time+= num*24*3600000;
	}
	date.setTime(time);
	return date;
}