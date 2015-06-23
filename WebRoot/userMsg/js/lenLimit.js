var txtobj={
   divName:"area", //外层容器的class
   textareaName:"chackTextarea", //textarea的class
   numName:"num", //数字的class
   num:140 //数字的最大数目
  }
  
  var textareaFn=function(){
   //定义变量
   var $onthis;//指向当前
   var $divname=txtobj.divName; //外层容器的class
   var $textareaName=txtobj.textareaName; //textarea的class
   var $numName=txtobj.numName; //数字的class
   var $num=txtobj.num; //数字的最大数目
   
   function isChinese(str){  //判断是不是中文
    var reCh=/[u00-uff]/;
    return !reCh.test(str);
   }
   function numChange(){
    var strlen=0; //初始定义长度为0
    var txtval = $.trim($onthis.val());
    for(var i=0;i<txtval.length;i++){
     if(isChinese(txtval.charAt(i))==true){
      strlen=strlen+2;//中文为2个字符
     }else{
      strlen=strlen+1;//英文一个字符
     }
    }
    strlen=Math.ceil(strlen/2);//中英文相加除2取整数
    if($num-strlen<0){
     $par.html("超出 <b style='display:inline;color:red;font-weight:lighter' class="+$numName+">"+Math.abs($num-strlen)+"</b> 字"); //超出的样式
    }
    else{
     $par.html("还可以输入 <b style='display:inline;' class="+$numName+">"+($num-strlen)+"</b> 字"); //正常时候
    }
    $b.html($num-strlen);   
    $("#areaLength").val(strlen);
   }
   $("."+$textareaName).live("focus",function(){
    $b=$(this).parents("."+$divname).find("."+$numName); //获取当前的数字
    $par=$b.parent(); 
    $onthis=$(this); //获取当前的textarea
    var setNum=setInterval(numChange,500);    
   });
  }     
  textareaFn();