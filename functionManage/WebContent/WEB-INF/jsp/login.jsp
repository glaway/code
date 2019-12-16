<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
  <head>
    <base href="<%=basePath%>">
    
    <title>安全保密审计功能</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.2.6/themes/default/easyui.css" />
	<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.2.6/themes/icon.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	
	<script type="text/javascript">
	//回车登录
	$(document).keydown(function(e){
		if(e.keyCode == 13) {
			submit();
		}
	});
			$(function(){
				debugger;
				$('#id_userId').val(''); 
				$('#id_password').val('');
			});
		//确定
		function loginUserInfo(){
			debugger;
			var id_userId = $('#id_userId').val();
			var id_password = $('#id_password').val();
			if(!isEmpty(id_userId)){
				tipMessage("用户名必填");
				return false;
			}
			if(!isEmpty(id_password)){
				tipMessage("密码必填");
				return false;
			}
 			$.ajax({
 	            //几个参数需要注意一下
 	                type: "POST",//方法类型
 	                url: "checkUserLogin.do?" ,//url
 	                dataType: "json",//预期服务器
 	                data: {
 	                	'id_userId':id_userId,
 	                	'id_password':id_password
 	                },
 	               beforeSend: function () {
                       $("#loginUserInfo").attr("disabled", true);
                       $("#resetUserInfo").attr("disabled", true);
                   },
 	                success: function (data) {
 	                	debugger;
 	                   if(data.message=='登录成功'){
 	             			   var level=data.level;
 	             			   if(data.level=='$'){
 	             				  level='4';
 	             			   }
 	             				var actionurl='home.do?level='+level+'&id_userId='+id_userId;
 		 	                	setTimeout("window.location.href='"+actionurl+"'", 200); 
 	                	}else{
 	                		tipMessage(data.message);
 	                		$("#loginUserInfo").removeAttr("disabled");
 	 	                	$("#resetUserInfo").removeAttr("disabled");
 	                	}
 	                }
 	            }); 
		     
		}
		//重置
		function resetUserInfo() {
			$('#loginUserform').form('clear'); 
		}  
	</script>
  </head>
<body>
  
	<div id="logniUserdiv" align="center"  style="width: 500px;  height: 500px"  >
		<form id="loginUserform" >
			<table >
				<tr>
					<td>用户名(ID)：</td>
					<td><input id="id_userId" type="text" name="id_userId" class="easyui-validatebox" /></td>
				</tr>
				<tr>
					<td>密码：</td>
					<td><input id="id_password" type="password" name="id_password" class="easyui-validatebox" /></td>
				</tr>
				<tr align="right">
					<td ><a  id="loginUserInfo" class="easyui-linkbutton" onclick="loginUserInfo();" icon="icon-ok">登录</a></td>
					<td ><a  id="resetUserInfo" class="easyui-linkbutton"  onclick="resetUserInfo();"  icon="icon-undo">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>