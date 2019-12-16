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
    
     var querypageNumber=1;
     var querypageSize=10;
	 	//查询
		function searchOperationList() {
			var queryOrUpdatecontextId = $('#contextId').val();
			debugger;
			try{
			$('#t_operation').datagrid({
				title:'权限列表',
				width:"100%",
				height:780,
				nowrap: false,
				striped: true,
				collapsible:false,
				//url:'json/operationList.json',
				url:'queryOrUpdatecontextId.do?queryOrUpdatecontextId='+queryOrUpdatecontextId+'&pageNumber='+querypageNumber+'&pageSize='+querypageSize,//此处应为服务器请求数据，暂时读取json中的数据
				//sortName: 'code',
				fitColumns: true,
				sortOrder: 'desc',
				remoteSort: false,
				rownumbers: true,  //行号 
				singleSelect: true,
				//idField:'contextId',
	            columns:[[
                       {field:'contextId',title:'上下文',width:100},
		               {field:'type',title:'类型',width:100},
		               {field:'operationGroup',title:'操作/操作组',width:100},
		               {field:'dataGroup',title:'数据组',width:100}
			    ]],  
				    
				pagination:true,
				rownumbers:true
			});
			var p = $('#t_operation').datagrid('getPager');
			$(p).pagination({
				showRefresh: false,
				onSelectPage: function(pageNumber, pageSize) {
					querypageNumber=pageNumber;
					querypageSize=pageSize;
			  $('#t_operation').datagrid('options').url ='queryOrUpdatecontextId.do?queryOrUpdatecontextId='+queryOrUpdatecontextId+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
			  $("#t_operation").datagrid('reload');
				}
			}); 
			}catch(e){
				alert(e.message);
			}
		}
			
		
		//重置
		function resetOperationList() {
			$('#queryOrUpdateform').form('clear'); 
		}
	</script>
	<div id="queryOrUpdatediv"	style="width: 100%; height: 100%" >
		<form id="queryOrUpdateform" >
			<table align="center">
				
				<tr style="height:10px">
					<td>上下文(Context ID):</td>
					<td><input id="contextId" class="easyui-validatebox" name="contextId" />  
					</td>
				</tr>
				
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-search" onclick="searchOperationList();">查询</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetOperationList();">重置</a> </td>
				</tr>
			</table>
			<div region="center" >
				<table id="t_operation"></table>
			</div>	
		</form>
	</div>
</body>
</html>