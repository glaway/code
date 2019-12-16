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
		 $(function(){
				$('#projectNo').combobox({    
				    url:'projectNo.do?',    
				    valueField:'project_id',    
				    textField:'project_id'
				});
				$('#role').combobox({    
				    url:'role.do?',    
				    valueField:'role',    
				    textField:'role',
				    editable:false
				});
				$('#organization').combobox({    
				    url:'organization.do?',    
				    valueField:'org_id',    
				    textField:'org_id',
				    editable:false
				});
			});
		//查询
		var searchpageNumber=1;
	    var searchpageSize=10;
		function searchUserBaseList() {
			var userName = $('#userName').val();
			var viewOrUpdateFullName = '';
			try{
			$('#t_baseUser').datagrid({
				title:'用户列表',
				width:800,
				height:400,
				//url:'json/baseUserList.json',
				 url:'queryUser.do?viewOrUpdateUserId='+userName+'&viewOrUpdateFullName='+viewOrUpdateFullName+'&pageNumber='+searchpageNumber+'&pageSize='+searchpageSize, //此处应为服务器请求数据，暂时读取json中的数据
				//sortName: 'code',
				fitColumns: true,
				sortOrder: 'desc',
				remoteSort: false,
				pageSize:10,   //表格中每页显示的行数
				pageList:[10,20,30],
				rownumbers: true,  //行号 
				//idField:'contextId',
	            columns:[[
                       {field:'userId',title:'用户名',width:100}, 
		               {field:'fullName',title:'全名',width:100},
		               {field:'phone',title:'电话',width:100},
		               {field:'address',title:'地址',width:100},
		               {field:'email',title:'邮箱',width:100},
		               {field:'organization',title:'组织',width:100}
			    ]],  
				pagination:true,
				rownumbers:true
			});
			var p = $('#t_baseUser').datagrid('getPager');
			$(p).pagination({
				showRefresh: false,
				onSelectPage: function(pageNumber, pageSize) {
					searchpageNumber=pageNumber;
					searchpageSize=pageSize;
			  $('#t_baseUser').datagrid('options').url ='queryUser.do?viewOrUpdateUserId='+$('#userName').val()+'&viewOrUpdateFullName='+viewOrUpdateFullName+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
			  $("#t_baseUser").datagrid('reload');
				}
			}); 
			//
			$("#okButton").show();
			}catch(e){
				alert(e.message);
			}
		}
		//查询
		function addUserBaseList() {
				$('#dlg').dialog({    
				    title: '用户/上下文查询条件',    
				    width: 800,  
		            height: 600,  
		            closed: false,  
		            cache: false,    
				    cache: false,    
				    href: 'userManage.do?method=addBaseUser',  
				    modal: true
				    
				});
			$('#dlg').dialog('refresh', 'userManage.do?method=addBaseUser');  
		}
		//删除查询
		function delUserBaseList() {
				$('#dlg').dialog({    
				    title: '用户/上下文查询条件',    
				    width: 800,  
		            height: 600,  
		            closed: false,  
		            cache: false,    
				    cache: false,    
				    href: 'userManage.do?method=delBaseUser',  
				    modal: true
				    
				});
			$('#dlg').dialog('refresh', 'userManage.do?method=delBaseUser');  
		}
		
		//查询确定 
		function okUserBaseList() {
			debugger;
			var rows = $('#t_baseUser').datagrid('getSelections');
			var userIds = [];
			for (var i = 0; i < rows.length; i++) {
				userIds.push(rows[i].userId);
			}
			var id=userIds.join(',');
			$('#users').val(id);
			setTimeout(function(){
        		$('#dlg').dialog('close');
        	}, 300);
		}
		//删除人员查询确定 
		function delokUserBaseList() {
			debugger;
			var rows = $('#t_baseUser').datagrid('getSelections');
			var userIds = [];
			for (var i = 0; i < rows.length; i++) {
				userIds.push(rows[i].userId);
			}
			var id=userIds.join(',');
			$('#delusers').val(id);
			setTimeout(function(){
        		$('#dlg').dialog('close');
        	}, 300);
		}
		
		//重置
		function resetUserBaseList() {
			//$('#createContextUserForm').form('clear'); 
			$('#userName').val(''); 
		}
		//重置
		function createContextReset() {
			$('#createContextform').form('clear'); 
		}
		//确定
		function createContextSave() {
			var projectNo = $('#projectNo').combobox('getValue');
			var role = $('#role').combobox('getValue');
			var organization = $('#organization').combobox('getValue');
			var users = $('#users').val();
			var delusers = $('#delusers').val();
			if(!isEmpty(projectNo)){
				tipMessage("请选择项目编号");
				return false;
			}
			if(!isEmpty(role)){
				tipMessage("请选择角色");
				return false;
			} 
			if(!isEmpty(organization)){
				tipMessage("请选择组织");
				return false;
			} 
		     $.ajax({
	 	            //几个参数需要注意一下
	 	                type: "POST",//方法类型
	 	                url: "createContext.do?" ,//url
	 	                data: {
	 	               	'projectNo':projectNo,
	 	 				'role':role,
	 	 				'organization':organization,
	 	 				'users':users,
	 	 				'delusers':delusers
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
	<div id="createContextdiv"	 align="center"
		style="width: 100%; height: 100%" title="用户新增">
		<form id="createContextform" >
			<table  >
				<tr style="height:10px">
					<td>*项目编号:</td>
					<td><!-- <select id="projectNo" class="easyui-combobox" name="projectNo" >   
						    <option value="DEFAULT">DEFAULT</option>   
						    <option value="NEWPROJ">NEWPROJ</option>   
						    <option value="TSTPRJ2">TSTPRJ2</option>   
						    <option value="TSTPRJ">TSTPRJ</option>   
						</select> -->
						<input id=projectNo name="projectNo" >  
					</td>
				</tr>
				<tr style="height:10px">
					<td></td>
					<td>
  					</td>
				</tr>
				<tr >
					<td>*角色:</td>
					<td>
						<input id=role name="role" >  
					</td>
				</tr>
				<tr style="height:10px">
					<td></td>
					<td>
  					</td>
				</tr>
				<tr>
					<td>*组织:</td>
					<td>
					<!-- <select id="organization" class="easyui-combobox" name="organization" >   
						    <option value="MANUF">MANUF</option>   
						    <option value="DESIGN">DESIGN</option>   
						    <option value="GLAWAY">GLAWAY</option>   
						    <option value="GLAWAY2">GLAWAY2</option>   
						    <option value="1">1</option>   
						    <option value="TST">TST</option>   
						</select> -->
						<input id="organization" name="organization" >  
					</td>
				</tr>
				<tr style="height:10px">
					<td></td>
					<td>
  					</td>
				</tr>
				<tr>
					<td>增加人员:</td>
					<td>
					<input class="easyui-textbox" name="users" id="users" data-options="multiline:true" style="height:50px;width:155px"/> 
  					</td>
  					<td>
					<a href="javascript:void(0)" class="easyui-linkbutton"  icon="icon-add"  onclick="addUserBaseList();">添加</a>
  					</td>
				</tr>
				<tr>
					<td>删除人员:</td>
					<td>
					<input class="easyui-textbox" name="delusers" id="delusers" data-options="multiline:true" style="height:50px;width:155px"/> 
  					</td>
  					<td>
					<a href="javascript:void(0)" class="easyui-linkbutton"  icon="icon-add"  onclick="delUserBaseList();">添加</a>
  					</td>
				</tr>
				<tr style="height:30px">
					<td></td>
					<td>
  					</td>
				</tr>
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" onclick="createContextSave();" icon="icon-ok">确定</a></td>
					<td colspan="1"><a class="easyui-linkbutton" onclick="createContextReset();" icon="icon-undo">重置</a> </td>
				</tr>
			</table>
		</form>
	</div>
			<div id="dlg" class="easyui-dialog" title="用户/上下文查询条件" style="width:800px;height:800px;padding:10px" closed="true"
			data-options="
				iconCls: 'icon-save',
				modal:false,
				closed:true
			"> 
			</div>
</body>
</html>