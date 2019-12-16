<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>修改密码</title>
</head>
<body >
<script type="text/javascript">
	 	$(function(){
		}); 
	</script>
	<div id="mydiv" align="left" style="width: 100%; height: 100%" >
		<form id="myform" >
			<table >
				<tr>
					<td>用户名：</td>
					<td><input id="userId2" type="text" name="id" value="${userId}" readonly="readonly"/></td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input id="password" type="password" name="password"  /></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input id="password2" type="password" name="password2"  /></td>
				</tr>
				<!-- <tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" onclick="searchUserList();" icon="icon-ok">查询</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-cancel" onclick="resetUserList();">关闭</a></td>
				</tr> -->
			</table>
		</form>
	</div>
</body>
</html>