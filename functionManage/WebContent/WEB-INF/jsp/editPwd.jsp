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
	 	//重置
		function resetPwd() {
			$('#editPassword').val(''); 
			$('#editPassword2').val(''); 
		}
		//确定
		function editPwdUserInfo() {
			var editUserId = $('#editUserId2').val();
			var editPassword = $('#editPassword').val();
			var editPassword2 = $('#editPassword2').val();
			if(!isEmpty(editPassword)||editPassword.length<8){
				tipMessage("请输入新密码并大于8位");
				return false;
			}
			if(!isEmpty(editPassword2)||editPassword2.length<8){
				tipMessage("请输入确认密码并大于8位");
				return false;
			}
			var reg =/^[A-Za-z0-9]{8,20}$/;
			if(!reg.test(editPassword)){
				tipMessage("新密码并大于8位");
				return false;
			}
			if(editPassword!=editPassword2){
				tipMessage('密码不一致');
				return false;
			}
		      $.ajax({
	 	            //几个参数需要注意一下
	 	                type: "POST",//方法类型
	 	                url: "editUserPwd.do?" ,//url
	 	                data: {
	 	                	'editUserId':editUserId,
	 	    				'editPassword':editPassword,
	 	    				'editPassword2':editPassword2
	 	                },
	 	               beforeSend: function () {
	                       $(".easyui-linkbutton").attr("disabled", true);
	                   },
	 	                success: function (data) {
	 	                	$(".easyui-linkbutton").removeAttr("disabled");
	 	                	var d = $.parseJSON(data);
	 	   				    tipMessage(d.message);
	 	                }
	 	            }); 
		      
		}
		
	</script>
	<div id="editUserdiv" align="center" style="width: 100%; height: 100%" >
		<form id="editUserform" >
			<table >
				<tr>
					<td>用户名：</td>
					<td><input id="editUserId2" type="text" name="editUserId2" value="${id_userId}"  readonly="readonly"/></td>
				</tr>
				<tr>
					<td>新密码：</td>
					<td><input id="editPassword" type="password" name="editPassword"  /></td>
				</tr>
				<tr>
					<td>确认密码：</td>
					<td><input id="editPassword2" type="password" name="editPassword2"  /></td>
				</tr>
				<!-- <tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" onclick="searchUserList();" icon="icon-ok">查询</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-cancel" onclick="resetUserList();">关闭</a></td>
				</tr> -->
				<tr align="right">
					<td ><a class="easyui-linkbutton" onclick="editPwdUserInfo();" icon="icon-ok">确定</a></td>
					<td ><a class="easyui-linkbutton" onclick="resetPwd();" icon="icon-undo">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>