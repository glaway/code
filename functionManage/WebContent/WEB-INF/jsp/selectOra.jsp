<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>组织查询</title>
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
		function selectOra() {
			var oraNumber = $('#oraNumber').val();
			debugger;
			try{
			$('#ora').datagrid({
				title:'组织列表',
				width:"100%",
				height:780,
				nowrap: false,
				striped: true,
				collapsible:false,
				//url:'json/operationList.json',
				url:'selectOra.do?oraNumber='+oraNumber+'&pageNumber='+userpageNumber+'&pageSize='+userpageSize,//此处应为服务器请求数据，暂时读取json中的数据
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
                       {field:'org_id',title:'组织ID',width:100}
			    ]],  
				    
				pagination:true,
				rownumbers:true
			});
			var p = $('#ora').datagrid('getPager');
			$(p).pagination({
				showRefresh: false,
				onSelectPage: function(pageNumber, pageSize) {
					userpageNumber=pageNumber;
					userpageSize=pageSize;
			  $('#ora').datagrid('options').url ='selectOra.do?oraNumber='+oraNumber+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
			  $("#ora").datagrid('reload');
				}
			}); 
			}catch(e){
				alert(e.message);
			}
		}
			
		
		//重置
		function resetOra() {
			$('#oraForm').form('clear'); 
		}
	</script>
	<div id="oraDiv"	style="width: 100%; height: 100%" >
		<form id="oraForm" >
			<table align="center">
				
				<tr style="height:10px">
					<td>组织(ID):</td>
					<td><input id="oraNumber" class="easyui-validatebox" name="oraNumber" />  
					</td>
				</tr>
				
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-search" onclick="selectOra();">查询</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetOra();">重置</a> </td>
				</tr>
			</table>
			<div region="center" >
				<table id="ora"></table>
			</div>	
		</form>
	</div>
</body>
</html>