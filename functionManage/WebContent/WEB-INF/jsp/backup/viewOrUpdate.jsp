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
		$(function(){
			$('#userList').hide();
		});
		//查询
		function searchUserList() {
			$('#userList').show();
			var IsCheckFlag = true;   
            $("#tt").datagrid({  
                title: "数据分页",  
                url: "json/datagrid_userList.json",  
                width: "100%",  
                height: "628px",  
                
                striped: true,     //交替行换色  
                rownumbers: true,  //行号  
                pagination: true,  //显示底部分页  
                fitColumns: true,//自动适应。先给列随便加个宽度  
                toolbar: "#tb",  
                checkOnSelect: false, //true，当用户点击行的时候该复选框就会被选中或取消选中。  
                selectOnCheck: true, //true，单击复选框将永远选择行。  
                onClickRow: function (index, row) {  
                    var d_id = row["d_id"];  
                    //alert(d_id);  
                },  
                onClickCell: function (rowIndex, field, value) {  
                    //alert(value);  
                    IsCheckFlag = false;  
                },  
                onSelect: function (rowIndex, rowData) {  
                    if (!IsCheckFlag) {  
                        IsCheckFlag = true;  
                        $("#tt").datagrid("unselectRow", rowIndex);  
                    }  
                },  
                onUnselect: function (rowIndex, rowData) {  
                    if (!IsCheckFlag) {  
                        IsCheckFlag = true;  
                        $("#tt").datagrid("selectRow", rowIndex);  
                    }  
                }  
            });  
  
            var p = $('#tt').datagrid('getPager');  
            $(p).pagination({  
                beforePageText: '第',  
                afterPageText: '页    共 {pages} 页',  
                displayMsg: '当前显示 {from}-{to} 条记录,共 {total} 条记录'  
            });  
        }  
  
        //搜索  
        function doSearch() {  
            $('#tt').datagrid('load', {  
                dname: $('#d_name').val(),  
                delse: $('#d_else').val()  
            }); 
		}
		//重置
		function resetUserList() {
         tipMessage(222);
		}
		//自定义操作列  
        function formatOper(val, row, index) {  
            var str = "";  
            str += '<a href="javascript:void(0);" onclick="doEdit(' + row.d_id + ')">修改</a>';  
            return str;  
        }
		
	</script>
	<div id="mydiv" align="left" style="width: 100%; height: 100%" >
		<form id="myform" >
			<table >
				<tr>
					<td>用户名(ID)：</td>
					<td><input id="userId" type="text" name="id" /></td>
				</tr>
				<tr>
					<td>全名(Full Name)：</td>
					<td><input type="text" name="fullName" class="easyui-validatebox" value="" /></td>
				</tr>
				<tr align="right">
					<td colspan="1"><a class="easyui-linkbutton" onclick="searchUserList();" icon="icon-ok">查询</a></td>
					<td colspan="1"><a class="easyui-linkbutton" icon="icon-cancel" onclick="resetUserList();">重置</a></td>
				</tr>
			</table>
			<div id="userList" >
				<table id="t_user">
					<thead>  
                    <tr>  
                        <th field="ck" width="100" align="center" checkbox="true"></th>  
                        <th field="userId" width="100" align="center" sortable="true">用户名</th>  
                        <th field="fullName" width="100" align="center" sortable="true">全名</th>  
                        <th field="phone" width="100" align="center" sortable="true">电话</th>  
                        <th field="address" width="100" align="center" sortable="true" >地址</th>  
                        <th field="email" width="100" align="center" sortable="true" >邮箱</th>  
                        <th field="organization" width="100" align="center" sortable="true" >组织</th>  
                        <th field="_operate" width="100" align="center" formatter="formatOper">操作</th>  
                    </tr>  
                </thead>  
				</table>
			</div>
		</form>
	</div>
</body>
</html>