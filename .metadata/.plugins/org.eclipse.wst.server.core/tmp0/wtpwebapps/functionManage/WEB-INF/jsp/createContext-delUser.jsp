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
			<div id="box1">
				   <table align="center">
							<tr>
								<td>用户名:</td>
								<td><input id="userName" class="easyui-validatebox" name="userName"/></td>
							</tr>
							<tr>
								<td colspan="1"><a class="easyui-linkbutton" icon="icon-search" onclick="searchUserBaseList();">查询</a></td>
					            <td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetUserBaseList();">重置</a> </td>
							</tr>
				   </table>
		    
			 <div id="box2" style="margin-bottom: 1px">
					<table id="t_baseUser"></table>
					<div style="float:right;display:none" id="okButton">
						   <a class="easyui-linkbutton"  icon="icon-ok" onclick="delokUserBaseList();">确定</a>
					</div>
			 </div>
			  </div>
</body>
</html>