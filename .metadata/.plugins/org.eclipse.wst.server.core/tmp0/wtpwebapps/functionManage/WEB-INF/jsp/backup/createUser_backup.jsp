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
	
	<script type="text/javascript">
	$(function(){
		// 自定义的校验器
		$.extend($.fn.validatebox.defaults.rules, {   
				midLength: {   
   			 			validator: function(value, param){   
        								return value.length >= param[0] && value.length <= param[1];    
    					},   
    					message: ''  
				} ,
				equalLength : {
   			 			validator: function(value, param){   
        								return value.length == param[0];    
    					},   
    					message: '密码必须为4个字符!'  
				}
		}); 
		
		
		//数值验证组件 
		$('#age').numberbox({
			min:0 , 
			max:150 ,
			required:true , 
			missingMessage:'年龄必填!' ,
			precision:0
		});
		
		//日期组件
		$('#birthday').datebox({
			required:true , 
			missingMessage:'生日必填!' ,
			editable:false
		});
		
		$('#salary').numberbox({
			min:1000 , 
			max:20000 ,
			required:true , 
			missingMessage:'薪水必填!' ,
			precision:2
		});
		
		//日期时间组件
		$('#startTime,#endTime').datetimebox({
			required:true , 
			missingMessage:'时间必填!' ,
			editable:false
		});

		//定义form表单 
		$('#myform').form({
				url:'UserServlet?method=save' , 
				onSubmit:function(){
					if(!$('#myform').form('validate')){
						$.messager.show({
							title:'提示信息' , 
							msg:'验证没有通过,不能提交表单!'
						});
						return false ;		//当表单验证不通过的时候 必须要return false 
					}
				} ,
				success:function(result){
					var result = $.parseJSON(result);
					$.messager.show({
						title:result.status , 
						msg:result.message
					});
				}
		});
		
		
	});
		
	</script>
</head>
<body >
	<div id="mydiv" class="easyui-panel"
		style="width: 400px; height: 350px" title="用户新增">
		<form id="myform" action="" method="post">
			<table>
				<tr>
					<td>*用户名(ID)：</td>
					<td><input type="text" name="id" class="easyui-validatebox" required="true" value="" /></td>
				</tr>
				<tr>
					<td>姓(First Name)：</td>
					<td><input type="text" name="firstName" class="easyui-validatebox" value="" /></td>
				</tr>
				<tr>
					<td>名(Last Name)：</td>
					<td><input type="text" name="lastName" class="easyui-validatebox" value="" /></td>
				</tr>
				<tr>
					<td>电话(Phone)：</td>
					<td><input type="text" name="phone" class="easyui-numberbox" value="" /></td>
				</tr>
				<tr>
					<td>地址(Address)：</td>
					<td><input type="text" name="address" class="easyui-validatebox"  value="" /></td>
				</tr>
				<tr>
					<td>邮箱(Email)：</td>
					<td><input type="text" name="email" value="" /></td>
				</tr>
				<tr>
					<td>组织(Organization)：</td>
					<td><input type="text" name="organization"  class="easyui-combobox" 
						url="organization.do?method=getOrganization" valueField="id" textField="name"  value="" /></td>
				</tr>
				<tr align="center">
					<td colspan="2"><a class="easyui-linkbutton">保存</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>