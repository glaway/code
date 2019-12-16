<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>创建用户</title>

</head>
<body >
<script type="text/javascript">
		//查询
		function createProjOk() {
			var projectNumber = $('#projectNumber').val();
			if(!isEmpty(projectNumber)){
				tipMessage('请输入项目编号');
				return false;
			}
		    $.ajax({
 	            //几个参数需要注意一下
 	                type: "POST",//方法类型
 	                url: "createProj.do?" ,//url
 	                data: {
 	                	'projectNumber':projectNumber
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
		function resetProj() {
			$('#projectform').form('clear'); 
		}
	</script>
	<div id="projectdiv" align="center" style="width: 100%; height: 100%" >
		<form id="projectform" >
			<table >
				<tr>
					<td>项目编号：</td>
					<td><input id="projectNumber" type="text" name="projectNumber" class="easyui-validatebox" required=true missingMessage="项目编号必填!"/></td>
				</tr>
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" onclick="createProjOk();" icon="icon-ok">确定</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetProj();">重置</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>