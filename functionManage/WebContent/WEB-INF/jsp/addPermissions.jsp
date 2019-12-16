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
	function addOK() {
		var addPermission_contextId = $('#addPermission_contextId').combobox('getValue');
		var type = $('#type').combobox('getValue');
		var operationGroup = $('#operationGroup').combobox('getValue');
		var dataGroup = $('#dataGroup').combobox('getValue');
		if (!isEmpty(addPermission_contextId)) {
			tipMessage("请选择上下文");
			return false;
		}
		if(type=='VPM'||type=='VPMAdmin'){
			if(operationGroup!='LOGIN'){
				tipMessage("类型为VPM时操作/操作组应为LOGIN");
				return false;
			}
			if(isEmpty(dataGroup)){
				tipMessage("类型为VPM或VPMAdmin时无数据组");
				return false;
			}
		}else{
			if(operationGroup=='LOGIN'){
				tipMessage("类型为ProcessGroup时操作/操作组不能为LOGIN");
				return false;
			}else{
				if(operationGroup=='AllGlobalProcess'&&isEmpty(dataGroup)){
					tipMessage("操作/操作组为AllGlobalProcess时无数据组");
					return false;
				}
				if(operationGroup=='AllObjectProcess'&&!isEmpty(dataGroup)){
					tipMessage("操作/操作组为AllObjectProcess时数据组不能为空");
					return false;
				}
				
			}
		}
		 $.ajax({
	            //几个参数需要注意一下
	                type: "POST",//方法类型
	                url: "addPermissions.do?" ,//url
	                data: {
	                	'contextId':addPermission_contextId,
	    				'type':type,
	    				'operationGroup':operationGroup,
	    				'dataGroup':dataGroup
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
	function resetAdd() {
		$('#addPermission_contextId').combobox('clear');
		//$('#addPermissionsform').form('clear'); 
	}
	 $(function(){
			$('#addPermission_contextId').combobox({    
			    url:'addPermission_contextId.do?',    
			    valueField:'contextId',    
			    textField:'contextId',
			    editable:false
			});
		}); 
	 
</script>
	<div id="addPermissionsdiv"	 align="center"
		style="width: 100%; height: 100%" title="用户新增">
		<form id="addPermissionsform" >
			<table  >
				<tr>
					<td>上下文(Context ID):</td>
					<td>
					<!-- <input id="addPermission_contextId" class="easyui-validatebox" name="addPermission_contextId" required="true"/> -->
					<input id="addPermission_contextId" name="addPermission_contextId" >  
					</td>
				</tr>
				<tr>
					<td>*类型:</td>
					<td><select id="type" class="easyui-combobox" name="type" editable="false">   
						    <option value="VPM">VPM</option>   
						    <option value="VPMAdmin">VPMAdmin</option>   
						    <option value="ProcessGroup">ProcessGroup</option>   
						</select>
					</td>
				</tr>
				<tr>
					<td>*操作/操作组:</td>
					<td><select id="operationGroup" class="easyui-combobox" name="operationGroup" editable="false">   
						    <option value="LOGIN">LOGIN</option>   
						    <option value="AllGlobalProcess">AllGlobalProcess</option>   
						    <option value="AllObjectProcess">AllObjectProcess</option>   
						</select>
					</td>
				</tr>
				<tr>
					<td>数据组:</td>
					<td><select id="dataGroup" class="easyui-combobox" name="dataGroup" editable="false">   
						    <option value=""> </option>     
						    <option value="AllData">AllData</option> 
						    <option value="MyAncestor">MyAncestor</option>   
						    <option value="MyData">MyData</option>   
						    <option value="MyDescendant">MyDescendant</option>   
						    <option value="MyOrgData">MyOrgData</option>   
						</select>
					</td>
				</tr>
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-ok" onclick="addOK();">确定</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetAdd();" >重置</a></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>