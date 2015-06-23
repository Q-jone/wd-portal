Date.prototype.dateDiff = function(interval, endTime) {
	switch (interval) {
	//計算秒差
	case "s":
		return parseInt((endTime - this) / 1000);
		// 計算分差
	case "n":
		return parseInt((endTime - this) / 60000);
		// 計算時差
	case "h":
		return parseInt((endTime - this) / 3600000);

		// 計算日差
	case "d":
		return parseInt((endTime - this) / 86400000);

		// 計算週差
	case "w":
		return parseInt((endTime - this) / (86400000 * 7));

		// 計算月差
	case "m":
		return (endTime.getMonth() + 1)
				+ ((endTime.getFullYear() - this.getFullYear()) * 12)
				- (this.getMonth() + 1);
		// 計算年差
	case "y":
		return endTime.getFullYear() - this.getFullYear();

		// 輸入有誤
	default:
		return undefined;
	}
}

function getJSDate(date){
	if(date!=null){
		var result =  (parseInt(date)-1);
		//return result >=10 ? result : ("0"+result);
		return result;
	}
}

function getNomalDate(date){
	if(date!=null){
		var result =  (parseInt(date)+1);
		return result >=10 ? result : ("0"+result);
	}
}

function formatDate(date){
	if(date!=null && date!=''){
		var result = parseInt(date);
		return result >=10 ? result : ("0"+result);
	}
}
