//--身份证号码验证-支持新的带x身份证
		function isIdCardNo(num) 
		{
		    var factorArr = new Array(7,9,10,5,8,4,2,1,6,3,7,9,10,5,8,4,2,1);
		    var error;
		    var varArray = new Array();
		    var intValue;
		    var lngProduct = 0;
		    var intCheckDigit;
		    var intStrLen = num.length;
		    var idNumber = num;    
		    var idcard=$("#idcard");
		    // initialize
		    if ((intStrLen != 15) && (intStrLen != 18)) {        
		        alert("输入身份证号码长度不对！");
		        idcard.focus();
		        return false;
		    }    
		    // check and set value
		    for(i=0;i<intStrLen;i++) {
		        varArray[i] = idNumber.charAt(i);
		        if ((varArray[i] < '0' || varArray[i] > '9') && (i != 17)) {            
		            alert("错误的身份证号码！");
		            idcard.focus();
		            return false;
		        } else if (i < 17) {
		            varArray[i] = varArray[i]*factorArr[i];
		        }
		    }
		    if (intStrLen == 18) {
		        //check date
		        var date8 = idNumber.substring(6,14);        
		        if (checkDate(date8) == false) {            
		            alert("身份证中日期信息与您填写的出生年月不符！");
		            idcard.focus();
		            return false;
		        }        
		        // calculate the sum of the products
		        for(i=0;i<17;i++) {
		            lngProduct = lngProduct + varArray[i];
		        }        
		        // calculate the check digit
		        intCheckDigit = 12 - lngProduct % 11;
		        switch (intCheckDigit) {
		            case 10:
		                intCheckDigit = 'X';
		                break;
		            case 11:
		                intCheckDigit = 0;
		                break;
		            case 12:
		                intCheckDigit = 1;
		                break;
		        }        
		        // check last digit
		        if (varArray[17].toUpperCase() != intCheckDigit) {            
		            alert("身份证效验位（末位）错误!...正确为： " + intCheckDigit );
		            idcard.focus();
		            return false;
		        }
		    } 
		    else{        //length is 15
		        //check date
		        var date6 = idNumber.substring(6,12);
		        if (checkDate(date6) == false) {
		            alert("身份证中日期信息与您填写的出生年月不符！");
		            idcard.focus();
		            return false;
		        }
		    }    
		    //return true;
		}
		
		//验证身份证中的出生年月是否与填写的出生年月相符
		function checkDate(date)
		{
		    var birthday=$("#birthday").val();
		    birthday=(birthday.replace("-","")).replace("-","");    
		    if(birthday==date||birthday.substring(2,8)==date)   
		    return true;
		    else return false;
		}
				
				
		//邮政编码验证
		function isPostCode(obj){         
		         
		         var regExp = /^[1-9][0-9]{5}$/;
		         if(regExp.test(obj.val())){
		                   return true;
		         }else if(obj.val().length!=6){
		                   alert("邮政编码是6位数字！");                   
		                   obj.focus();
		                   return false;
		         }else {
		                   alert("邮政编码格式不对！");                   
		                   obj.focus();
		                   return false;
		         }
		}
		
		
		
		//验证手机号码
		function isMobileNO(obj){              
		        
		         if(obj.val().length!=11){
		                   alert('请输入11位手机号码！');
		                   obj.focus();
		                   return false;
		         }
		         var myreg = /^(1[3-9]{1}[0-9]{1})\d{8}$/;
		         if(!myreg.test(obj.val())){
		                   alert('请输入正确的手机号码！');                   
		                   obj.focus();
		                   return false;
		         }
		         return true;
		}
		

		
		