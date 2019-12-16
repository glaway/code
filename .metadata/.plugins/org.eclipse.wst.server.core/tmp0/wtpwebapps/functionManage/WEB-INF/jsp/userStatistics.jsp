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
	//确定
	function exportUserStatistics() {
		debugger;
		var user_startDate = $('#user_startDate').datebox('getValue');
		var user_endDate = $('#user_endDate').datebox('getValue');
		var user_operationType = $('#user_operationType').combobox('getText');
		if(!isEmpty(user_startDate)){
			tipMessage("开始时间必填");
			return false;
		} 
	    if (!isEmpty(user_endDate)) {
			tipMessage("结束时间必填");
			
		} 
	    if (!isEmpty(user_operationType)) {
			tipMessage("事件类型必填");
			
		} 
	    var url = 'userStatistics.do?user_startDate=' + user_startDate+'&user_endDate='+user_endDate+'&user_operationType='+user_operationType;
		url = encodeURI(url);
		window.location.href = url;
	}
	//重置
	function resetUserStatistics() {
		$('#userStatisticsform').form('clear'); 
	}
		
	</script>	
	<div id="mydiv"	 align="center"
		style="width: 100%; height: 100%" title="用户新增">
		<form id="userStatisticsform" >
			<table >
				<tr style="height:10px">
					<td>*开始时间:</td>
					<td><input id="user_startDate" type="text" class="easyui-datebox" required="required" editable="false" />
					</td>
				</tr>
				<tr >
					<td>*结束时间:</td>
					<td><input id="user_endDate" type="text" class="easyui-datebox" required="required" editable="false"/>
					</td>
				</tr>
				<tr>
					<td>*事件类型:</td>
					<td><select id="user_operationType" class="easyui-combobox" name="user_operationType" editable="false">   
						    <option value="登陆">登陆</option>   
						    <option value="登出">登出</option>   
						    <option value="创建零件">创建零件</option>   
						    <option value="删除零件">删除零件</option>   
						    <option value="删除图文档">删除图文档</option>   
						</select>
					</td>
				</tr>
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-redo" onclick="exportUserStatistics();">导出</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetUserStatistics();" >重置</a> </td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>