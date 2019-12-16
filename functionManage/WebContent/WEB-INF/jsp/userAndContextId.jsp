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
		function searchUserAndContextIdList() {
			var usercontextId = $('#usercontextId').val();
			debugger;
			try{
			$('#t_userAndContextId').datagrid({
				title:'权限列表',
				width:"100%",
				height:780,
				nowrap: false,
				striped: true,
				collapsible:false,
				//url:'json/operationList.json',
				url:'queryUserAndContextId.do?usercontextId='+usercontextId+'&pageNumber='+userpageNumber+'&pageSize='+userpageSize,//此处应为服务器请求数据，暂时读取json中的数据
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
                       {field:'userId',title:'用户',width:100},
                       {field:'contextId',title:'上下文',width:100}
			    ]],  
				    
				pagination:true,
				rownumbers:true
			});
			var p = $('#t_userAndContextId').datagrid('getPager');
			$(p).pagination({
				showRefresh: false,
				onSelectPage: function(pageNumber, pageSize) {
					userpageNumber=pageNumber;
					userpageSize=pageSize;
			  $('#t_userAndContextId').datagrid('options').url ='queryUserAndContextId.do?usercontextId='+usercontextId+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
			  $("#t_userAndContextId").datagrid('reload');
				}
			}); 
			}catch(e){
				alert(e.message);
			}
		}
			
		
		//重置
		function resetUserAndContextIdList() {
			$('#queryOrUpdateform').form('clear'); 
		}
	</script>
	<div id="userAndContextIddiv"	style="width: 100%; height: 100%" >
		<form id="userAndContextIdform" >
			<table align="center">
				
				<tr style="height:10px">
					<td>用户名(ID):</td>
					<td><input id="usercontextId" class="easyui-validatebox" name="usercontextId" />  
					</td>
				</tr>
				
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-search" onclick="searchUserAndContextIdList();">查询</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetUserAndContextIdList();">重置</a> </td>
				</tr>
			</table>
			<div region="center" >
				<table id="t_userAndContextId"></table>
			</div>	
		</form>
	</div>
</body>
</html>