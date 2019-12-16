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
		
		
	
	
		$(function(){
			debugger;
			var id_userId='${id_userId}';
			$('a[title]').click(function(){
				debugger;
				var title = $(this).html();
				var url = 'userManage.do' + getMethodByTitle(title);
				url =url+'&id_userId='+id_userId;
				if($('#tt').tabs('exists' ,title)){
					$('#tt').tabs('select',title);
				} else {
					$('#tt').tabs('add',{
						title: title,
						closable:true,//可关闭tab
						href: url //跳转到新的页面url
					});  
				}
			});
		});
		function exitUserInfo(){
			var actionurl='exit.do?';
	      	setTimeout("window.location.href='"+actionurl+"'", 200);
		}
		function export_po(){
			if(!confirm("确定导出?")){
				return false;
			}
			$.ajax({
 	            //几个参数需要注意一下
 	                type: "GET",//方法类型
 	                url: "export_po.do?" ,//url
 	                data: {
 	                	
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
	<style type="text/css">
			
		#exit {
		    position:absolute; 
			right: 0px;
			 top: 0px;
			 } 
		</style>
  </head>
  
  <body>
  	<input id="userVOList" name="userVOList" value="${userVOList}" type="hidden"/>
	<input id="secuVOList" name="secuVOList" value="${secuVOList}" type="hidden"/>
	<input id="auditVOList" name="auditVOList" value="${auditVOList}" type="hidden"/>
	<div id="layout_div" class="easyui-layout" fit=true style="width:100%;height:100%;">  
	    <div region="west" split="true" title="功能管理" style="width:200px;">
			<div id="aa" class="easyui-accordion" fit=true >  
			<c:if test="${not empty userVOList}">
			    <div title="用户管理" style="border-style:solid;border-width:1px;">  
			    	
		 					<c:forEach items="${userVOList}" var="vo" >
		 						<a title="${vo}" style="text-align: center; display:block;font-size:15px;background-color:66FFFF;border-style:solid;border-width:1px;">${vo}</a><br>
		 					</c:forEach>
			    </div>  
	 				</c:if>	
			    	<c:if test="${not empty secuVOList}">
			    <div title="安全管理" >  
	 					<c:forEach items="${secuVOList}" var="vo" >
	 						<a title="${vo}" style="text-align: center; display:block;font-size:15px;background-color:66FFFF;border-style:solid;border-width:1px;">${vo}</a><br>
	 					</c:forEach>
			    </div>  
	 				</c:if>	
			    	<c:if test="${not empty auditVOList}">	
			    <div title="审计管理">  
	 					<c:forEach items="${auditVOList}" var="vo" >
	 						<a title="${vo}" style="text-align: center; display:block;font-size:15px;background-color:66FFFF;border-style:solid;border-width:1px;">${vo}</a><br>
	 					</c:forEach>
			    </div> 
	 				</c:if>	
			</div>  
	    </div>  
	    <div region="center"  title="主界面" style="padding:10px;">
	    <div id="export_po">  
               <a   onclick="export_po();" class="easyui-linkbutton" icon="icon-redo" style="color:black;">导出PO文件</a>
		</div>
	    <c:if test="${empty exit}">
	    <div id="exit" >  
               <a   onclick="exitUserInfo();" style="color:red">退出</a>
		</div> 
		</c:if>
			<div id="tt" class="easyui-tabs" fit=true style="width:500px;height:250px;padding:0px 0px 0px 0px;">  

			</div>  
	    </div>  
	</div>  
  	
  </body>
</html>
