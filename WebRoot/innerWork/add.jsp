<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<title>部门工作添加</title>
<link rel="stylesheet" href="../css/formalize.css" />
<link rel="stylesheet" href="../css/page.css" />
<link rel="stylesheet" href="../css/default/imgs.css" />
<link rel="stylesheet" href="../css/reset.css" />


<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
        <script src="../js/html5.js"></script>
        <script src="../js/jquery-1.7.1.js"></script>
        <script src="js/jquery.form.js"></script>
		<script src="../js/jquery.formalize.js"></script>
		<!--<script src="../js/switchDept.js"></script>-->
		<script src="../js/show.js"></script>
		<script src="../js/loading.js"></script>
		<link type="text/css" href="../css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />	
		<script type="text/javascript" src="../js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script type="text/javascript" src="../js/flick/jquery.ui.datepicker-zh-CN.js"></script>
		
		
        <style type="text/css">
        .ui-datepicker-title span {display:inline;}
        button.ui-datepicker-current { display: none; }
        
        .f_window .con ul li{
            float:left;
            width:80px;
            margin-top: 10px;
            margin-left:20px;
        }
        
        .f_window .con ul li input[type="checkbox"]{
              vertical-align: top;
			  top: 3px;
			  top: 0 \0;
			  *top: -3px;
			  position: static;
        }
		</style>
		
		
		<script type="text/javascript">
		var addOptions = {
				cache:false,
				type:'post',
				callback:null,
				url:'/portal/innerWork/add.action',
			    success:function(data){
						if(data !=null && data.length > 0){
							alert("添加成功");
							window.location.href = "/portal/innerWork/add.jsp";
						}else{
							alert("添加失败！");
							window.location.href = "/portal/innerWork/add.jsp";
						}
					return false;
			    }
			};
		
		
         $(function(){
        	 $("#submit").click(function(){
        	     //对必填项进行判断
        	     var noticeWarrn="";
        	     var jobName=$("#jobName").val();
        	     if(jobName==""){
        	         noticeWarrn+="督办工作不能为空！\n"
        	     }
        	     var bTime=$("#bTime").val();
        	     if(bTime==""){
        	       noticeWarrn+="开始时间不能为空！\n";
        	     }
        	     var pfTime=$("#pfTime").val();
        	      if(pfTime==""){
        	       noticeWarrn+="要求完成时间不能为空！\n";
        	     }
        	     var status=$("#status").val();
        	     if(pfTime==""){
        	       noticeWarrn+="完成状态不能为空！\n";
        	     }
        	      var rPeople=$("#rPeople").val();
        	     if(rPeople==""){
        	       noticeWarrn+="责任人不能为空！\n";
        	     }
        	     var rLeader=$("#rLeader").val();
        	     if(rLeader==""){
        	       noticeWarrn+="分管领导不能为空！\n";
        	     }
        	     if(noticeWarrn!=""){
        	        alert(noticeWarrn);
        	     }else{
        	        $("#add").ajaxSubmit(addOptions);
        	     }   
        	 });
      
        	 
			$(".odd tr:odd").css("background","#fafafa");	
			$('#pfTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'pfTime'//仅作为“清除”按钮的判断条件						
			});
			$('#bTime').datepicker({
				//inline: true								
				"changeYear":true,
				"showButtonPanel":true,	
				"closeText":'清除',	
				"currentText":'bTime'//仅作为“清除”按钮的判断条件						
			});
			//datepicker的“清除”功能
            $(".ui-datepicker-close").live("click", function (){              
              if($(this).parent("div").children("button:eq(0)").text()=="pfTime") $("#pfTime").val("");
              if($(this).parent("div").children("button:eq(0)").text()=="bTime") $("#bTime").val("");                 
            });
			
			loadShow();	
			
		
			$("#rChoose1").click(function(){
			   var status=$("#blk1").css("display");
			   if(status=="none"){
			      $("#blk1").css("display","block");
			      var statusBlk2=$("#blk2").css("display");
			      if(statusBlk2=="block"){
			         $("#blk2").css("display","none"); 
			      }
			   }else{
			      $("#blk1").css("display","none");
			   }
			});
			$("#rChoose2").click(function(){
			    var status=$("#blk2").css("display");
			    if(status=="none"){
			      $("#blk2").css("display","block");
			       var statusBlk1=$("#blk1").css("display");
			      if(statusBlk1=="block"){
			         $("#blk1").css("display","none"); 
			      }
			   }else{
			      $("#blk2").css("display","none");
			   }
			});
			
			
			$("#comfirmBtn").click(function(){
			   var status=$("#blk1").css("display");
			   if(status=="none"){
			      $("#blk1").css("display","block");
			   }else{
			      $("#blk1").css("display","none");
			   }
			});
			$("#cancelBtn").click(function(){
			   var status=$("#blk1").css("display");
			   if(status=="none"){
			      $("#blk1").css("display","block");
			   }else{
			      $("#blk1").css("display","none");
			   }
			});
			
			$("#comfirmBtn1").click(function(){
			   var status=$("#blk2").css("display");
			   if(status=="none"){
			      $("#blk2").css("display","block");
			   }else{
			      $("#blk2").css("display","none");
			   }
			});
			$("#cancelBtn1").click(function(){
			   var status=$("#blk2").css("display");
			   if(status=="none"){
			      $("#blk2").css("display","block");
			   }else{
			      $("#blk2").css("display","none");
			   }
			});
			
			
			//异步获取用户数据
			getDeptUserByLoginUser();
			
			
			//给div中的每个checkbox绑定一个单击事件
			$("#blk1 input").change(function(){	
			     //查看责任人rPeople中是否存在手动编辑的项
			     var rPeopleName=$("#rPeople").val();
			     var rPeopleNameArray=new Array();
			     rPeopleNameArray=rPeopleName.split(",");
			     var rPeopleNameExtend="";
			     $.each( rPeopleNameArray, function(i, n){
			          var flag=false;
			          $.each( $("#blk1 input"), function(j, m){
			              if($(m).parent().text()==n){
			                  flag=true;
			              }
				      });
				      if(!flag){
				         //手动编辑的姓名 
			             rPeopleNameExtend+=n+","
				      }
				      
				 });
			          
			     var checkboxs=$("#blk1 input:checked");
			     var names="";
			     var loginNamesRPeople="";
			     $.each( checkboxs, function(i, n){
			          if(i==checkboxs.length-1){
			            names+=$(n).parent().text();
			            loginNamesRPeople+=$(n).val();
			          }else{
			             names+=$(n).parent().text()+",";
			             loginNamesRPeople+=$(n).val()+",";
			          }
				 });

				 names+=","+rPeopleNameExtend
				 for(var i=names.length-1;i>=0;i--){
				    if(names.substring(names.length-1)==","){
				       names=names.substring(0,names.lastIndexOf(","));
				    } 
				 }
				 //去掉开头逗号
				 while(names.substring(0,1)==","){
				    names=names.substring(1);
				 }
			     $("#rPeople").val(names);
			     $("#loginNamesRPeople").val(loginNamesRPeople);
			     
			});
			
			$("#blk2 input").change(function(){
			
			      //查看分管领导rPeople中是否存在手动编辑的项
			     var rLeaderName=$("#rLeader").val();
			     var rLeaderNameArray=new Array();
			     rLeaderNameArray=rLeaderName.split(",");
			     var rLeaderNameExtend="";
			     $.each( rLeaderNameArray, function(i, n){
			          var flag=false;
			          $.each( $("#blk2 input"), function(j, m){
			              if($(m).parent().text()==n){
			                  flag=true;
			              }
				      });
				      if(!flag){
				         //手动编辑的姓名 
			             rLeaderNameExtend+=n+","
				      }
				      
				 });
			     var checkboxs=$("#blk2 input:checked");
			     var names="";
			     var loginNamesRLeader="";
			     $.each( checkboxs, function(i, n){
			          if(i==checkboxs.length-1){
			            names+=$(n).parent().text();
			            loginNamesRLeader+=$(n).val();
			          }else{
			             names+=$(n).parent().text()+",";
			             loginNamesRLeader+=$(n).val()+",";
			          }
				 });
				 names+=","+rLeaderNameExtend
				 for(var i=names.length-1;i>=0;i--){
				    if(names.substring(names.length-1)==","){
				       names=names.substring(0,names.lastIndexOf(","));
				    } 
				 }
				 //去掉开头逗号
				 while(names.substring(0,1)==","){
				    names=names.substring(1);
				 }
			     $("#rLeader").val(names);
			     $("#loginNamesRLeader").val(loginNamesRLeader);
			});
			
			//将输入的中文逗号转换为英文逗号
			$("#rPeople").keyup(function(){
			      var rpeople=$("#rPeople").val();
					while(rpeople.indexOf("，")!=-1)//寻找每一个中文逗号，并替换
					{
					    $("#rPeople").val(rpeople.replace("，",","));
						rpeople=$("#rPeople").val();
					}
					$("#rPeople").val(rpeople);
			});
			
			
			//根据rPeople框的变化动态设置id为blk1的div的checkbox状态
			$("#rPeople").blur(function(){
			      
			      var rpeople=$("#rPeople").val();
			      if(rpeople==""){//责任人input为空
			          var checkboxs=$("#blk1 input");
			          $.each( checkboxs, function(i, n){
				          if($(n).attr('checked')=='checked'){
				             $(n).removeAttr('checked');
				          }
				     });
				     $("#loginNamesRPeople").val("");
			      }
			      
			      if(rpeople!=""){//责任人input不为空
			           //去掉开头逗号
			           while(rpeople.substring(0,1)==","){
			               rpeople= rpeople.substring(1);
			           }
			           $("#rPeople").val(rpeople);
			            //去掉尾端的逗号
			           while(rpeople.substring(rpeople.length-1)==","){
			             rpeople= rpeople.substring(0,rpeople.length-1);
			           }
			           $("#rPeople").val(rpeople);
			           //将多个逗号替换为一个逗号
			           rpeople=rpeople.replace(new RegExp(',+',"gm"),',');
			           $("#rPeople").val(rpeople);
			           
			           var rpeopleArray=new Array();
			           rpeopleArray=rpeople.split(",");
			           var loginNamesRPeople="";
			           //根据责任人input中内容，遍历id为blk1的div，将与责任人input框中姓名相符的设置为选中状态，其他为非选中状态
			           //将所有checkbox设为非选中
			           var checkboxs=$("#blk1 input");
			            $.each( checkboxs, function(i, n){
				          if($(n).attr('checked')=='checked'){
				             $(n).removeAttr('checked');
				          }
				        });
				         //责任人input隐藏域值设为空
				        $("#loginNamesRPeople").val("");
				        $.each( rpeopleArray, function(i, n){
					          $.each( $("#blk1 input"), function(i, m){
						          if($(m).parent().text()==n){
						             $(m).attr("checked","checked");
						             loginNamesRPeople+=$(m).val()+",";
						          }
					          });
				        });
				         $("#loginNamesRPeople").val(loginNamesRPeople);
			      }
			});
			
			//将中文逗号替换为英文逗号
			$("#rLeader").keyup(function(){
			      var rLeader=$("#rLeader").val();
					while(rLeader.indexOf("，")!=-1)//寻找每一个中文逗号，并替换
					{
					    $("#rLeader").val(rLeader.replace("，",","));
						rLeader=$("#rLeader").val();
					}
					$("#rLeader").val(rLeader);
			});
			
			//根据rLeader框的变化动态设置id为blk2的div的checkbox状态
			$("#rLeader").blur(function(){
			      var rLeader=$("#rLeader").val();
			      if(rLeader==""){//分管领导input为空
			          var checkboxs=$("#blk2 input");
			          $.each( checkboxs, function(i, n){
				          if($(n).attr('checked')=='checked'){
				             $(n).removeAttr('checked');
				          }
				     });
				     $("#loginNamesRLeader").val("");
			      }
			      
			      if(rLeader!=""){//分管领导input不为空
			            //去掉开头逗号
			           while(rLeader.substring(0,1)==","){
			               rLeader= rLeader.substring(1);
			           }
			           $("#rLeader").val(rLeader);
			           
			           //去掉尾端的逗号
			           while(rLeader.substring(rLeader.length-1)==","){
			             rLeader= rLeader.substring(0,rLeader.length-1);
			           }
			           $("#rLeader").val(rLeader);
			           
			            //将多个逗号替换为一个逗号
			           rLeader=rLeader.replace(new RegExp(',+',"gm"),',');
			           $("#rLeader").val(rLeader);
			           
			           var rLeaderArray=new Array();
			           rLeaderArray=rLeader.split(",");
			           var loginNamesRLeader="";
			           //根据分管领导input中内容，遍历id为blk2的div，将与分管领导input框中姓名相符的设置为选中状态，其他为非选中状态
			           //将所有checkbox设为非选中
			           var checkboxs=$("#blk2 input");
			            $.each( checkboxs, function(i, n){
				          if($(n).attr('checked')=='checked'){
				             $(n).removeAttr('checked');
				          }
				        });
				         //分管领导input隐藏域值设为空
				        $("#loginNamesRLeader").val("");
				        $.each( rLeaderArray, function(i, n){
					          $.each( $("#blk2 input"), function(i, m){
						          if($(m).parent().text()==n){
						             $(m).attr("checked","checked");
						             loginNamesRLeader+=$(m).val()+",";
						          }
					          });
				        });
				         $("#loginNamesRLeader").val(loginNamesRLeader);
			      }
			});
		});
		
		//异步获取用户数据
    function getDeptUserByLoginUser(){
          $.ajax({  
                    async:false,  //改为同步状态
				    url : '/portal/innerWork/getDeptUserByLoginUser.action',
					type : 'post',
					dataType:'json',
					success: function(data) {
					   if(data!=null){
					        //添加部门员工
					       if(data.deptUserOptions!=null){ 
					          var str="";
					          for(var i=0;i<data.deptUserOptions.length;i++){
					              var flag=false;
					              for(var j=0;j<data.deptLeaderOptions.length;j++){
						               if(data.deptUserOptions[i].id==data.deptLeaderOptions[j].id){
						                  flag=true;
						                  break;
						               }
						           }
						           if(flag==false){
						              str+= "<li><input type=\"checkbox\" value=\""+data.deptUserOptions[i].loginName+"\" />"+data.deptUserOptions[i].name+"</li>";
						           }
					          }
					           
					           $("#blk1 ul").html(str);
					       }
					       if(data.deptLeaderOptions!=null){
					           var strLeader="";
					           for(var j=0;j<data.deptLeaderOptions.length;j++){
					              strLeader+= "<li><input type=\"checkbox\" value=\""+data.deptLeaderOptions[j].loginName+"\" />"+data.deptLeaderOptions[j].name+"</li>";
					           }
					           $("#blk2 ul").html(strLeader);
					       }
					       
					   }
					},
					error:function(){
					   alert("请求失败！");
					}
			   });
    }	 
		
		function shut(){
		  window.opener=null;
		  window.open("","_self");
		  window.close();
		}
	
		function check(){
		
		
		showLoading();
		}
		
		
        </script>
       </head>

<body>
	<div class="main" >
    	<!--Ctrl-->
		<div class="ctrl clearfix">
        	<div class="fl"><img id="show" onclick="showHide();" src="../css/default/images/sideBar_arrow_right.jpg" width="46" height="30" title="收起"></div>
            <div class="posi fl">
            	<ul>
            		<li class="fin">部门工作添加</li>
                </ul>
            </div>
   		</div>
        <!--Ctrl End-->
        <!--Filter--><!--Filter End-->
        <!--Table-->
        <div class="mb10 pt45" >
        <form id="add">
          <input type="hidden" name="loginNamesRPeople" id="loginNamesRPeople"/>
          <input type="hidden" name="loginNamesRLeader" id="loginNamesRLeader"/>
          <table width="100%"  style="height:600px" class="table_1">
           <thead>
            <th colspan="4" class="t_r">
       	      &nbsp;</th>
                                  </thead>
                              <tbody>
                               <tr>
                                <td class="t_r lableTd">督办工作：</td>
                                <td colspan="3">
                                <input type="text" id="jobName" name="jobName" class="input_xxlarge"/><font color="red" style="display: inline;">*</font>
                                </td>
                                </tr>
                                
                                <tr>
                                <td class="t_r lableTd">工作要求：</td>
                                <td colspan="3">
                                <textarea cols="10" rows="5" id="jobDemand" name="jobDemand"></textarea>
                                </td>
                                </tr>
                                
                                
                               <tr>
                                <td class="t_r lableTd">开始时间：</td>
                                <td>
                                <input readonly="readonly" type="date" id="bTime" name="bTime"/>
                                <font color="red" style="display: inline;">*</font>
                                </td>
                                <td class="t_r lableTd">要求完成时间：</td>
                                <td>
                                <input readonly="readonly" type="date" id="pfTime" name="pfTime"/>
                                <font color="red" style="display: inline;">*</font>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">最新状态：</td>
                                <td>
                                <input type="text" id="process" name="process" class="input_xxlarge"/>
                                </td>
                                <td class="t_r lableTd">进展标志：</td>
                                <td>
                                 <select name="pFlag" id="pFlag" class="input_large" > 
                                	<option value="正常">正常</option>
                                	<option value="延迟">延迟</option>
                                </select>
                                </td>
                                </tr>
                                
                                <tr>
                                <td class="t_r lableTd">延迟原因分类：</td>
                                <td>
                                <input type="text" id="delayType" name="delayType" class="input_xxlarge"/>
                                </td>
                                <td class="t_r lableTd">延迟原因描述：</td>
                                <td>
                                 <input type="text" id="delayDetail" name="delayDetail" class="input_xxlarge"/>
                                </td>
                                </tr>
                                
                                
                              <tr>
                                <td class="t_r lableTd">完成标志：</td>
                                <td>
                               <input type="text" id="fFlag" name="fFlag" class="input_xxlarge"/>
                                </td>
                                <td class="t_r lableTd">完成状态：</td>
                                 <td>
                                <select name="status" id="status" class="input_large" > 
                                	<option value="进行中">进行中</option>
                                	<option value="未完成">未完成</option>
                                	<option value="已完成">已完成</option>
                                </select>
                                <font color="red" style="display: inline;">*</font>
                                </td>
                                </tr>
                              <tr>
                                <td class="t_r lableTd">责任人：</td>
                                <td colspan="3">
                                   <input type="text" id="rPeople" name="rPeople" class="input_xxlarge" placeholder="手动添加，请以逗号分隔"/>
                                   <font color="red" style="display: inline;">*</font>
                                   <a href="javascript:void(0);" id="rChoose1" style="text-decoration: none;display: inline;" >选择</a>
                                </td>
                                
                               </tr>    
                             <tr>
                                <td class="t_r lableTd">分管领导：</td>
                               <td colspan="3">
                                  <input type="text" id="rLeader" name="rLeader" class="input_xxlarge" placeholder="手动添加，请以逗号分隔"/>
                                  <font color="red" style="display: inline;">*</font>
                                  <a href="javascript:void(0);" id="rChoose2" style="text-decoration: none;display: inline;" >选择</a>
                               </td>
                             </tr>                 
                             
                             <tr>
				            	<td class="lableTd t_r">附件内容：</td>
				            	<td colspan="3">
				            	<input type="hidden" name="attach" id='attach'/>
				            	<iframe scrolling="auto" height="150" frameborder="1" border="1" marginheight="0" marginwidth="0" width="100%" id="attachFrame" name="attachFrame" src="/portal/attach/loadFileList.action?fileGroup=attach&fileGroupName=attachGroup&fileGroupId=&userName=<s:property value="#session.userName"/>&loginName=<s:property value="#session.loginName"/>&procType=edit&targetType=frame&type=1"></iframe>
				            	</td>
				            </tr>
                             
                               <tr>
        	      				<td class="t_r lableTd">填报人：</td>
                               <td>
                               <s:property value="#session.userName"/>
                               <input type="hidden" id="operator" name="operator" value="<s:property value='#session.loginName'/>"/>
        	      				<input type="hidden" id="operatorName" name="operatorName" value="<s:property value='#session.userName'/>"/>
        	      				</td>
        	      				<td>
        	      				</td>
                             </tr>
                             
                              </tbody>
                              <tr class="tfoot">
                                <td colspan="4" class="t_r"><input id="submit" type="button" value="添 加"/>&nbsp;
                                <input type="button" value="关 闭" onclick="shut();"/>&nbsp;
                                <input type="reset" value="重 置" />&nbsp;</td>
                              </tr>
                             
                              
                            </table>
             </form>
      </div>
        <!--Table End-->
</div>

    <!-- 隐藏div，存放部门员工数据 -->
    <div class="f_window" style="position: absolute;top:200px;right:150px; display:none" id="blk1" >
       
        <div class="con" >
        	<ul style="height:150px; overflow:auto;">
            </ul>
            <div style="clear:both"></div>
      </div>
      <div class="button t_c"><input type="button" value="确 认" id="comfirmBtn"/>&nbsp;&nbsp;<input type="reset" value="关闭窗口" id="cancelBtn" /></div>
    </div>
     <div class="f_window" style="position: absolute;top:210px;right:150px;display:none" id="blk2">
        <div class="con" >
        	<ul style="height:150px; overflow:auto;">
     
            </ul>
      </div>
      <div class="button t_c"><input type="button" value="确 认" id="comfirmBtn1"/>&nbsp;&nbsp;<input type="reset" value="关闭窗口" id="cancelBtn1"/></div>
    </div>

       
</body>
</html>
