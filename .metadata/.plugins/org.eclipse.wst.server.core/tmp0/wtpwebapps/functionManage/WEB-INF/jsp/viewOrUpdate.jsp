<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>功能管理</title>
	<link rel="stylesheet" type="text/css" href="css/common.css" />
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery-1.7.2.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js"></script>
	<link rel="stylesheet" href="js/jquery-easyui-1.2.6/themes/default/easyui.css">
	<link rel="stylesheet" href="js/jquery-easyui-1.2.6/themes/icon.css">
	
	
</head>
<body >
    <script type="text/javascript">
    var viewpageNumber=1;
    var viewpageSize=10;
  //确定
	function searchUserList() {
		debugger;
		
		var viewOrUpdateUserId = $('#userId').val();
		var viewOrUpdateFullName = $('#fullName').val();
			  //datagrid显示数据 
			   $('#t_user').datagrid({
				title:'用户列表',
				width:"100%",
				height:760,
				nowrap: false,
				striped: true,//是否显示斑马线效果
				collapsible:false,//默认不可折叠
				url:'queryUser.do?viewOrUpdateUserId='+viewOrUpdateUserId+'&viewOrUpdateFullName='+viewOrUpdateFullName+'&pageNumber='+viewpageNumber+'&pageSize='+viewpageSize,//此处应为服务器请求数据，暂时读取json中的数据
				fitColumns: true,
				sortOrder: 'desc',
				remoteSort: false,
				singleSelect: true,
				pageSize:10,   //表格中每页显示的行数
				pageList:[10,20,30],
	            columns:[[//需要在datagrid中显示的列
                    {field:'userId',title:'用户名',width:100},
		               {field:'fullName',title:'全名',width:100},
		               {field:'phone',title:'电话',width:100},
		               {field:'address',title:'地址',width:100},
		               {field:'email',title:'邮箱',width:100},
		               {field:'organization',title:'组织',width:100},
		               //最后一列为操作列
		               {field:'edit',title:'修改密码',width:100,align:'center', rowspan:2,
							formatter:function(value,row){
								return "<a href='javascript:void(0);' onclick=doEdit('" + row.userId + "')>修改</a>";
							}
						},
			           {field:'cancell',title:'用户注销',width:100,align:'center', rowspan:2,
								formatter:function(value,row){
									return "<a href='javascript:void(0);' onclick=cancell('" + row.userId + "')>注销</a>";
								}
						}
			    ]],  
				    
				pagination:true,
				rownumbers:true
			});  
		
			var p = $('#t_user').datagrid('getPager');
			$(p).pagination({
				showRefresh: false,
				onSelectPage: function(pageNumber, pageSize) {
					viewpageNumber=pageNumber;
					viewpageSize=pageSize;
			  $('#t_user').datagrid('options').url ='queryUser.do?viewOrUpdateUserId='+$('#userId').val()+'&viewOrUpdateFullName='+$('#fullName').val()+'&pageNumber='+pageNumber+'&pageSize='+pageSize;
			  $("#t_user").datagrid('reload');
				}
			}); 
	}
    function cancell(userId){
    	 var truthBeTold = window.confirm('确认注销?');
    	 if(truthBeTold){
    		 $.ajax({
	 	            //几个参数需要注意一下
	 	                type: "POST",//方法类型
	 	                url: "cancellUser.do?" ,//url
	 	                data: {
	 	                	'userId':userId
	 	                },
	 	               beforeSend: function () {
	                       $(".easyui-linkbutton").attr("disabled", true);
	                   },
	 	                success: function (data) {
	 	                	$(".easyui-linkbutton").removeAttr("disabled");
	 	                	var d = $.parseJSON(data);
	 	   				    tipMessage(d.message);
	 	   				   $('#t_user').datagrid('reload');
	 	                }
	 	            }); 
    	 }
    	
    }
  
	//操作列  修改功能
    function doEdit(userId) {  
		debugger;
		var url = 'userManage.do?method=updateUserInfo&userId=' + userId;//需要跳转的URL
		$('#dialog_update').dialog({  
            title: '修改密码',  
            width: 800,  
            height: 400,  
            closed: false,  
            cache: false,  
            href: url,  
            modal: true,  
            buttons:[{ //为窗口添加按钮  
                text:'确定',  
                handler:function(){  
                	var dialogUserId = $('#userId2').val();//弹出框中的用户名
                	var dialogPwd = $('#password').val();//弹出框中的新密码
                	var dialogPwd2 = $('#password2').val();//弹出框中的确认密码
                	//都不为空提示修改成功
                	if(isEmpty(dialogUserId) && isEmpty(dialogPwd) && isEmpty(dialogPwd2)){
                		if(dialogPwd!=dialogPwd2){
        					tipMessage('密码不一致');
        					return false;
        				}
                		 $.post('updateUserPwd.do?', {
              		     	'userId':userId,
              				'dialogPwd2':dialogPwd2
              			}, function(data) {
              				debugger;
              				var d = $.parseJSON(data); 
             				tipMessage(d.message);
             				setTimeout(function(){
                        		$('#dialog_update').dialog('close');
                        	}, 300);
              			});
        			} else{
        				if(!isEmpty(dialogUserId)){
        					tipMessage('请输入用户名');
        					return false;
        				} 
        				if(!isEmpty(dialogPwd)){
        					tipMessage('请输入新密码');
        					return false;
        				}
        				if(!isEmpty(dialogPwd2)){
        					tipMessage('请输入确认密码');
        					return false;
        				}
        			}
                	//ajax进入后台  
                }  
            },{  
                text:'关闭',  
                handler:function(){
                	setTimeout(function(){
                		$('#dialog_update').dialog('close');
                	}, 300);
                }  
            }]  
            }); 
		$('#dialog_update').dialog('refresh', url); 
		//$('#dialog_update').dialog('open'); 
    }	
		
		//重置
		function resetUserList() {
			$('#queryUserform').form('clear'); 
		}
	</script>
	<div id="queryUserdiv"	style="width: 100%; height: 100%" >
		<form id="queryUserform" >
			<table align="center">
				
				<tr style="height:10px">
					<td>用户名(ID)：</td>
					<td><input id="userId" type="text" name="id" class="easyui-validatebox"/></td>
				</tr>
				<tr>
					<td>全名(Full Name)：</td>
					<td><input id="fullName" type="text" name="fullName" class="easyui-validatebox" /></td>
				</tr>
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-search" onclick="searchUserList();">查询</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-undo" onclick="resetUserList();">重置</a> </td>
				</tr>
			</table>
			<div region="center" >
				<table id="t_user"></table>
			</div>	
		</form>
		<div id="dialog_update"></div>
	</div>
</body>
</html>