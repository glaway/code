<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>功能管理</title>
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" href="js/jquery-easyui-1.2.6/themes/default/easyui.css">
	<link rel="stylesheet" href="js/jquery-easyui-1.2.6/themes/icon.css">
	<link rel="stylesheet" href="js/jquery-easyui-1.2.6/demo/demo.css">

</head>
<body >
    <script type="text/javascript">
    var userpageNumber=1;
    var userpageSize=10;
		//查询
		function searchUserAndContextIdList1() {
			var userlogFileQueryId = $('#userlogFileQueryId').val();
			var query_startDate = $('#query_startDate').datebox('getValue');
			var query_endDate = $('#query_endDate').datebox('getValue');
			var user_logFileQueryType = $('#user_logFileQueryType').combobox('getText');
			debugger;
			try{
			$('#logFileQuery').datagrid({
				title:'权限列表',
				width:"100%",
				height:780,
				nowrap: false,
				striped: true,
				collapsible:false,
				//url:'json/operationList.json',
				url:'logFileQuery.do?userlogFileQueryId='+userlogFileQueryId+'&query_startDate='+query_startDate+'&query_endDate='+query_endDate+'&user_logFileQueryType='+user_logFileQueryType+'&pageNumber='+userpageNumber+'&pageSize='+userpageSize,//此处应为服务器请求数据，暂时读取json中的数据
				//sortName: 'code',
				fitColumns: true,
				sortOrder: 'desc',
				remoteSort: false,
				rownumbers: true,  //行号 
				singleSelect: true,
				pageSize:10,   //表格中每页显示的行数
				pageList:[10,20,30],
				//idField:'contextId',
	            columns:[[
                       {field:'date',title:'时间',width:100},
                       {field:'userId',title:'用户',width:100},
                       {field:'type',title:'类型',width:100},
                       {field:'content',title:'内容',width:100}
			    ]],  
				pagination:true,
				rownumbers:true
			});
			var p = $('#logFileQuery').datagrid('getPager');
			$(p).pagination({
				showRefresh: false,
				onSelectPage: function(pageNumber, pageSize) {
					userpageNumber=pageNumber;
					userpageSize=pageSize;
			  $('#logFileQuery').datagrid('options').url ='logFileQuery.do?userlogFileQueryId='+userlogFileQueryId+'&query_startDate='+query_startDate+'&query_endDate='+query_endDate+'&user_logFileQueryType='+user_logFileQueryType+'&pageNumber='+userpageNumber+'&pageSize='+userpageSize;
			  $("#logFileQuery").datagrid('reload');
				}
			}); 
			}catch(e){
				alert(e.message);
			}
		}
			
		
		//重置
		function resetUserAndContextIdList1() {
			$('#queryOrUpdateform').form('clear'); 
		}
	</script>
	<div id="userAndContextIddiv"	style="width: 100%; height: 100%" >
		<form id="userAndContextIdform" >
			<table align="center">
				
				<tr style="height:10px">
					<td>用户:</td>
					<td><input id="userlogFileQueryId" class="easyui-validatebox" name="userlogFileQueryId" />  
					</td>
				</tr>
				<tr style="height:10px">
					<td>类型:</td>
					<td><select id="user_logFileQueryType" class="easyui-combobox" name="user_logFileQueryType" editable="false">   
						    <option value=""> </option>   
						    <option value="登陆">登陆</option>   
						    <option value="登出">登出</option>   
						    <option value="创建零件">创建零件</option>   
						    <option value="删除零件">删除零件</option>   
						    <option value="删除图文档">删除图文档</option>   
						    <option value="用户创建">用户创建</option>   
						    <option value="用户删除">用户删除</option>   
						    <option value="添加权限">添加权限</option>   
						    <option value="删除权限">删除权限</option>   
						    <option value="修改权限">修改权限</option>
						    <option value="添加上下文用户">添加上下文用户</option>   
						    <option value="删除上下文用户">删除上下文用户</option> 
						</select>
					</td>
				</tr>
				<tr style="height:10px">
					<td>*开始时间:</td>
					<td><input id="query_startDate" type="text" class="easyui-datebox"  editable="false" />
					</td>
				</tr>
				<tr >
					<td>*结束时间:</td>
					<td><input id="query_endDate" type="text" class="easyui-datebox"   editable="false" />
					</td>
				</tr>
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-search" onclick="searchUserAndContextIdList1();">查询</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetUserAndContextIdList1();">重置</a> </td>
				</tr>
			</table>
			<div region="center" >
				<table id="logFileQuery"></table>
			</div>	
		</form>
	</div>
</body>
</html>