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
	function exportSecStatistics() {
		debugger;
		var sec_startDate = $('#sec_startDate').datebox('getValue');
		var sec_endDate = $('#sec_endDate').datebox('getValue');
		var sec_operationType = $('#sec_operationType').combobox('getText');
		if(!isEmpty(sec_startDate)){
			tipMessage("开始时间必填");
			return false;
		} 
	    if (!isEmpty(sec_endDate)) {
			tipMessage("结束时间必填");
			
		} 
	    if (!isEmpty(sec_operationType)) {
			tipMessage("事件类型必填");
			
		} 
	    var url = 'secStatistics.do?sec_startDate=' + sec_startDate+'&sec_endDate='+sec_endDate+'&sec_operationType='+sec_operationType;
		url = encodeURI(url);
		window.location.href = url;
	}
	//重置
	function resetSecStatistics() {
		$('#secStatisticsform').form('clear'); 
	}
	
	</script>	
	<div id="secStatisticsdiv"	 align="center"
		style="width: 100%; height: 100%" title="用户新增">
		<form id="secStatisticsform" >
			<table  >
				<tr style="height:10px">
					<td>*开始时间:</td>
					<td><input id="sec_startDate" type="text" class="easyui-datebox" required="required" editable="false" />
					</td>
				</tr>
				<tr >
					<td>*结束时间:</td>
					<td><input id="sec_endDate" type="text" class="easyui-datebox" required="required" editable="false" />
					</td>
				</tr>
				<tr>
					<td>*事件类型:</td>
					<td><select id="sec_operationType" class="easyui-combobox" name="sec_operationType" editable="false" >   
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
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-redo" onclick="exportSecStatistics();">导出</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetSecStatistics();" >重置</a> </td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>