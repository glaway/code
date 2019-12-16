<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>创建逻辑组织</title>
</head>
<body >
<script type="text/javascript">
		//确定
		function saveUserInfo() {
			var userId = $('#id_userId').val();
			var firstName = $('#id_firstName').val();
			var lastName = $('#id_lastName').val();
			var phone = $('#id_phone').val();
			var address = $('#id_address').val();
			var email = $('#id_email').val();
			var organization = $('#id_organization').combobox('getText');
			var user_level = $('#user_level').combobox('getValue');
			if(!isEmpty(userId)){
				tipMessage("用户名必填");
				return false;
			}
			if(!isEmpty(organization)){
				tipMessage("请选择组织");
				return false;
			} 
		      $.ajax({
	 	            //几个参数需要注意一下
	 	                type: "POST",//方法类型
	 	                url: "createUser.do?" ,//url
	 	                data: {
	 	                	'userId':userId,
	 	    				'firstName':firstName,
	 	    				'lastName':lastName,
	 	    				'phone':phone,
	 	    				'address':address,
	 	    				'email':email,  
	 	    				'organization':organization,
	 	    				'user_level':user_level
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
		//重置
		function cancelSave() {
			$('#createUserform').form('clear'); 
		}
		 $(function(){
			$('#id_organization').combobox({    
			    url:'organization.do?',    
			    valueField:'org_id',    
			    textField:'org_id',
			    editable:false	
			});
		}); 
		
	</script>
	<div id="createUserdiv" align="center" style="width: 100%;  height: 100%" >
		<form id="createUserform" >
			<table >
				<tr>
					<td>用户名(ID)：</td>
					<td><input id="id_userId" type="text" name="id" class="easyui-validatebox" required=true missingMessage="用户名必填!"/></td>
				</tr>
				<tr>
					<td>姓(First Name)：</td>
					<td><input id="id_firstName" type="text" name="firstName" class="easyui-validatebox" value="" /></td>
				</tr>
				<tr>
					<td>名(Last Name)：</td>
					<td><input id="id_lastName" type="text" name="lastName" class="easyui-validatebox" value="" /></td>
				</tr>
				<tr>
					<td>电话(Phone)：</td>
					<td><input id="id_phone" type="text" name="phone" class="easyui-numberbox" value="" /></td>
				</tr>
				<tr>
					<td>地址(Address)：</td>
					<td><input id="id_address" type="text" name="address" class="easyui-validatebox"  value="" /></td>
				</tr>
				<tr>
					<td>邮箱(Email)：</td>
					<td><input id="id_email" type="text" name="email" value="" /></td>
				</tr>
				<tr>
					<td>组织(Organization)：</td>
					<td>
			           <input id="id_organization" name="id_organization" >  
					</td>
				</tr>
				<tr>
					<td>密级：</td>
					<td><select id="user_level" class="easyui-combobox" name="user_level" editable="false">   
						    <option value="1">普通用户</option>   
						</select>
					</td>
				</tr>
				<tr align="right">
					<td ><a class="easyui-linkbutton" onclick="saveUserInfo();" icon="icon-ok">确定</a></td>
					<td ><a class="easyui-linkbutton" onclick="cancelSave();" icon="icon-undo">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>