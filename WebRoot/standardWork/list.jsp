<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="cn">
  <head>
		<meta charset="utf-8" />
		<title>标准内容管理</title>
		<link rel="stylesheet" href="<%=basePath %>css/formalize.css" />
		<link rel="stylesheet" href="<%=basePath %>css/page.css" />
		<link rel="stylesheet" href="<%=basePath %>css/default/imgs.css" />
		<link rel="stylesheet" href="<%=basePath %>css/reset.css" />
		<link type="text/css" href="<%=basePath %>css/flick/jquery-ui-1.8.18.custom.css" rel="stylesheet" />
		<!--[if IE 6.0]>
           <script src="js/iepng.js" type="text/javascript"></script>
           <script type="text/javascript">
                EvPNG.fix('div, ul, ol, img, li, input, span, a, h1, h2, h3, h4, h5, h6, p, dl, dt');
           </script>
       <![endif]-->
		<script src="<%=basePath %>js/html5.js"></script>
		<script src="<%=basePath %>js/jquery-1.7.1.js"></script>
		<script type="text/javascript" src="<%=basePath %>js/flick/jquery-ui-1.8.18.custom.min.js"></script>
		<script src="<%=basePath %>js/jquery.formalize.js"></script>
		<script src="js/jquery.jstree.js"></script>
		<script type="text/javascript">
			$(function(){
				$('#datepicker').datepicker({
					inline: true
				});
			});

			$(function(){
	        	var openId = $("#openId").val();
	        	if(openId==null||openId==""){
	        		openId = "1";
	        	}
	        	$.ajax( {  
			         url : "<%=basePath%>standardWork/showTree.action?openId="+openId+"&random="+Math.random(),  
			         type : 'post',  
			         dataType : 'json',  
			         async : false,  
			         success : function(data) {  
			         	showTree(data);
			         },  
			         error : function() {  
			             alert("ajax error");  
			         }  
			     }); 
	        });

			function showTree(jsonData){
	        	$("#jstree").jstree({
	 				"json_data":jsonData,
	 				"themes": { "theme": "default", "dots": true, "icons": true },               
	 				"plugins": ["themes", "json_data", "ui"]			 				
	 			}).bind("select_node.jstree", function (e, data) { 
	 				$("#typeId").val(data.rslt.obj.data("id"));
	 				$("#form").submit();
	 			});
	        }

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
		</script>
    <style type="text/css">
      .News_list .list dt, .News_list .list dd{
        float: left;
      }
      .News_list .list dt{
        background: none;
      }
      .News_list .list li{
        border-bottom: #ddd 1px solid;
      }
      .News_list .list dl{
        border: none;
      }
      .News_list .list dd{
        padding-left: 0;
        height: auto;
        max-height: 150px;
      }
      .News_list .op{
        background: #f9f9f9;
        padding: 5px 20px;
      }
      .News_list .op li{
        margin-right: 5px;
        float: left;
      }
      .News_list h5{
        padding-left: 25px;
        background-repeat: no-repeat;
        color: #000;
        background-image: url(images/file.png);
      }
      .News_list h5.docx{
        background-position: left top;
      }
      .News_list h5.pdf{
        background-position: left -50px;
      }
      .News_list h5.ppt{
        background-position: left -100px;
      }
      .News_list h5.exl{
        background-position: left -150px;
      }
      .News_list h5 a{
        color: #000;
      }
      .News_list h5 a:hover{
        color: #999;
      }
      .News_list h6{
        font-weight: normal;
      }
    </style>
		</head>

		<body>
        <div class="main"> 
          <!--Ctrl-->
          <div class="ctrl clearfix">
            <div class="fl"><img src="<%=basePath %>css/default/images/sideBar_arrow_left.jpg" width="46" height="30" alt="收起"></div>
            <div class="posi fl">
              <ul>
                <li><a href="#">首页</a></li>
                <li class="fin">标准内容管理</li>
              </ul>
            </div>
            <div class="fr lit_nav">
              <ul>
                <li class="selected"><a class="print" href="#">打印</a></li>
                <li><a class="storage" href="#">网络硬盘</a></li>
                <li><a class="rss" href="#">订阅</a></li>
                <li><a class="chat" href="#">聊天</a></li>
                <li><a class="query" href="#">查询</a></li>
              </ul>
            </div>
          </div>
          <!--Ctrl End-->
          <div class="clearfix pt45"> 
            <!--News_list right-->
            <aside class="mb10 related panel_5">
              <div class="tit">
                <h4>目录导航</h4>
              </div>
              <div class="con" id="jstree">
                
              </div>
            </aside>
            <!--News_list right End--> 
            <!--News_list-->
            <div class="fl" style="width:75%">
              <div class="mb10 right_main News_list" style="margin-left:0; float:none; width:100%">
                <header class="clearfix mb10">
                  <h4 class="fl"><s:property value="#request.typeName"/></h4>
                  <form action="<%=basePath %>standardWork/getFileListByPage.action" id="form" method="post">
                  <div class="fr p10">
                  	<input type="hidden" name="page" id="page" value="<s:property value="page"/>"/>
              		<input type="hidden" name="typeId" id="typeId" value="<s:property value="#request.typeId"/>"/>
                    <!-- <input type="text" id="datepicker" /> -->
                    <input type="text" id="text_inline" name="searchParam" value="<s:property value="#request.searchParam"/>"/>
                    <input type="submit" value="检索" />
                    <input type="button" value="高级检索" onclick="window.open('app_bzgl_form.html')"/>
                  </div>
                  </form>
                </header>
                <!--Tabs_2-->
                <!-- 
                <div class="tabs_2 nwarp">
                  <ul class="nwarp">
                      <li><a href="#"><span>工作标准</span></a></li>
                      <li><a href="#"><span>预览GB工作标准</span></a></li>
                      <li class="selected"><a href="#"><span>搜索结果</span></a></li>
                    </ul>
                </div>
                 -->
                <!-- 
                <div class="mb10 op clearfix">
                  <ul class="fl">
                    <li><a href="#">全选</a></li>
                    <li>|</li>
                    <li><a href="#">清空</a></li>
                  </ul>
                  <input type="checkbox" class="mr_5"><label>在结果中搜索</label>
                </div>
                 -->
                <!--Tabs_2 End-->
                <s:set name="r" value="#request.result"></s:set>  
                <div class="list">
                  <ul>
                    <s:iterator value="#r.list" id="items" status="s">
                    <li>
                      <dl class="clearfix">
                        <dt><!-- <input type="checkbox" class="fl mr8"> --></dt>
                        <dd>
                          <s:if test="#items[9]=='pdf'">
                          	<h5 class="pdf">
                          </s:if>
                          <s:elseif test="#items[9]=='docx'">
                          	<h5 class="docx">
                          </s:elseif>
                          <s:elseif test="#items[9]=='doc'">
                          	<h5 class="docx">
                          </s:elseif>
                          <s:elseif test="#items[9]=='ppt'">
                          	<h5 class="ppt">
                          </s:elseif>
                          <s:elseif test="#items[9]=='xlsx'">
                          	<h5 class="exl">
                          </s:elseif>
                          <s:elseif test="#items[9]=='xls'">
                          	<h5 class="exl">
                          </s:elseif>
                          <s:else>
                          	<h5>
                          </s:else>
                          <a href="<%=basePath %>standardWork/openFile.action?id=<s:property value='#items[0]'/>" target="_blank"><s:property value="#items[2]"/></a></h5>
                          <h6><span class="L_01"><s:property value="#items[10]"/></span></h6>
                          <div>所在文件路径：<s:property value="#request.typeName"/></div>
                          <div class="clearfix">
                            <div class="fl mr_5">创建人:test1</div>
                            <div class="fl mr_5">创建时间:13-12-10 15:28:57</div>
                            <div class="fl mr_5">大小:14.52KB</div>
                            <div class="fl mr_5">当前/最新版本:1.0/1.0</div>
                          </div>
                          <div>简介：<s:property value="#items[3]"/></div>
                        </dd>
                      </dl>
                    </li>
                    </s:iterator>
                  </ul>
                </div>
                <div class="clearfix"><span class="fl"><s:property value="#r.pageInfo.totalRow"/>条记录</span>
                           		<ul class="fr clearfix pager">
		                             <li>Pages:<s:property value="#r.pageInfo.currentPage"/>/<s:property value="#r.pageInfo.totalPage"/>
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.totalPage'/>" id="totalPageCount">
		                             		<input type="hidden" value="<s:property value='#r.pageInfo.currentPage'/>" id="currentNumber">
			                                  <input type="text" id="number" name="pageNumber" min="0" max="999" step="1" class="input_tiny" value="<s:property value='#r.pageInfo.currentPage'/>"/>
			                                  <input type="button" name="button" id="button" value="Go" onclick="goPage(0,0)">
		                             </li>
                             
		                             <s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">
		                             <li><a href="javascript:void(0)">&gt;&gt;</a></li>
		                             </s:if>
		                             <s:else>
		                              <li><a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.totalPage'/>,3)">&gt;&gt;</a></li>
		                             </s:else>
                              
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==#r.pageInfo.totalPage">	
	                             		<a href="javascript:void(0)">下一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,2)">下一页</a>
	                             	</s:else>
	                             	</li>
	                             	<li>
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             		<a href="javascript:void(0)">上一页</a>
	                             	</s:if>
	                             	<s:else>
	                             		<a href="javascript:void(0)" onclick="goPage(<s:property value='#r.pageInfo.currentPage'/>,1)">上一页</a>
	                             	</s:else>
	                             	
	                             	</li> 
	                             
	                             	<s:if test="#r.pageInfo.currentPage==1">
	                             	<li><a href="javascript:void(0)">&lt;&lt;</a></li>
	                             	</s:if>
	                             	<s:else>
	                             		<li><a href="javascript:void(0)" onclick="goPage(1,3)">&lt;&lt;</a></li>
	                             	</s:else>
	                             
                                
                            </ul>
                        </div>
              </div>
            <!--News_list End--> 
            </div>
          </div>
        </div>
</body>
</html>
