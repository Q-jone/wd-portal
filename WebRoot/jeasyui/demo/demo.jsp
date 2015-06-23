<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="cn">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="keywords" content="jquery,ui,easy,easyui,web">
	<meta name="description" content="easyui help you build your web page easily!">
	<title>Build CRUD Application with edit form in expanded row details - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="../themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="../themes/icon.css">
	<link rel="stylesheet" type="text/css" href="../css/demo.css">
	<style type="text/css">
		form{
			margin:0;
			padding:0;
		}
		.dv-table td{
			border:0;
		}
		.dv-table input{
			border:1px solid #ccc;
		}
	</style>
	
	<script type="text/javascript" src="../jquery.min.js"></script>
	<script type="text/javascript" src="../jquery.easyui.min.js"></script>
	<script type="text/javascript" src="../locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript">
		$(function(){
			//datagrid初始化  
		    $('#list_data').datagrid({  
		        title:'招标计划基本表',  
		        iconCls:'icon-edit',//图标  
		        width: 'auto',  
		        height: 'auto',  
		        nowrap: false,  
		        striped: true,  
		        border: true,  
		        collapsible:false,//是否可折叠的  
		        fit: true,//自动大小  
		        url:'',  
		        
		        //sortName: 'code',  
		        //sortOrder: 'desc',  
		        remoteSort:false,   
		        idField:'fldId',  
		        singleSelect:false,//是否单选  
		        pagination:true,//分页控件  
		        rownumbers:true,//行号  
		        frozenColumns:[[  
		            {field:'ck',checkbox:true}  
		        ]],  
		        toolbar: [{  
		            text: '添加',  
		            iconCls: 'icon-add',  
		            handler: function() {  
		              //  openDialog("add_dialog","add");  
		            }  
		        }, '-', {  
		            text: '修改',  
		            iconCls: 'icon-edit',  
		            handler: function() {  
		               // openDialog("add_dialog","edit");  
		            }  
		        }, '-',{  
		            text: '删除',  
		            iconCls: 'icon-remove',  
		            handler: function(){  
		               // delAppInfo();  
		            }  
		        }],  
		    });  
		    //设置分页控件  
		    var p = $('#list_data').datagrid('getPager');  
		    $(p).pagination({  
		        pageSize: 10,//每页显示的记录条数，默认为10  
		        pageList: [5,10,15,20,25],//可以设置每页记录条数的列表  
		        beforePageText: '第',//页数文本框前显示的汉字  
		        afterPageText: '页    共 {pages} 页',  
		        displayMsg: '当前显示 {from} - {to} 条记录   共 {total} 条记录',  
		        /*onBeforeRefresh:function(){ 
		            $(this).pagination('loading'); 
		            alert('before refresh'); 
		            $(this).pagination('loaded'); 
		        }*/ 
		    });  
		});
		
	</script>
</head>
<body>
	
	<table id="list_data" cellspacing="0" cellpadding="0">
		<thead>
		<tr>  
            <th field="projectCompany" sortable="true" width="100px;">项目公司</th>  
            <th field="line"  sortable="true" width="50px;">线路</th>  
            <th field="catalog"  sortable="true" width="50px;">子目</th>  
            <th field="type"  sortable="true" width="100px;">类别</th>  
            <th field="major"  sortable="true" width="100px;">专业</th>  
            <th field="bidNum"  sortable="true" width="100px;">标段号</th>  
            <th field="projectName"  sortable="true" width="400px;">工程名称</th>  
            <th field="bidPlanDate"  sortable="true" width="100px;">招标计划</th>  
            <th field="bidAmount"  sortable="true" width="100px;">中标金额</th>  
            <th field="bidInfoDate"  sortable="true" width="100px;">中标通知书发出日期</th>  
        </tr>  
		</thead>
		<tbody>
		<tr>  
			<td></td>
            <td>11南</td>
            <td>16号线</td>
            <td>施工</td>
            <td>土建</td>
            <td>装修安装</td>
            <td></td>
            <td>车站装修4标（龙阳路站、华夏西路站）含小水电西路站）含小水电西路站）含小水电西路站）含小水电西路站）含小水电西路站）含小水电</td>
            <td>2014年1月20日</td>
            <td>11南</td>
            <td>11南</td>
        </tr> 
        </tbody>
	</table>
	
</body>
</html>