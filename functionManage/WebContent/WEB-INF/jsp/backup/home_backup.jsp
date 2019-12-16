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
	
	<style type="text/css">
		#parentDiv{
			position: relative;
			float: left;
			height: 620px;
			width: 950px;
			margin-left: 100px;
			margin-top: 60px;
		} 
		#childDiv_1st{
			position: relative;
			float: left;
			height: 620px;
			width: 264px;
			background-color: rgb(204,204,204);
			
		}
		
		.div_disable{
			color: rgb(0,0,255);
		}
		.div_manage{
			list-style-type:none;
			font-size:24px;
			margin:10px 0;
			margin-left: 40px;
		}
		/*给li加上手型*/
		li{
			list-style-type:none;
			font-size:24px;
			margin:10px 0;
			cursor:pointer;
		}
		#left_top{
			height: 19%;
			width: 100%;
		}
		#left_top{
			height: 89%;
			width: 100%;
		}
		#line_x{
			width: 100%;
			margin-top: -68%
		}
		
	</style>
	<script type="text/javascript">
		$(function(){
			
		});
		
		function userManEvent(userVo) {
			debugger;
			if(paramIsValid(userVo)){
				var url = "";
				if("用户创建" == userVo){
					url = "userManage.do?method=createUser";
				}
				if("用户查询/修改" == userVo){
					url = "userManage.do?method=viewOrUpdate";
				}
				if("创建项目" == userVo){
					url = "userManage.do?method=createProj";
				}
				if("创建逻辑组织" == userVo){
					url = "userManage.do?method=createLogicOrga";
				}
				$('#childDiv_2nd').panel({
					href : url
				});
			}
		}
		
		//判断参数是否有效
		function paramIsValid(para){
			if (para != null && para != '' && para != undefined) {
				return true;
			} else{
				return false;
			}
		}
	</script>
</head>
<body >
	<input id="userVOList" name="userVOList" value="${userVOList}" type="hidden"/>
	<input id="secuVOList" name="secuVOList" value="${secuVOList}" type="hidden"/>
	<input id="auditVOList" name="auditVOList" value="${auditVOList}" type="hidden"/>
         <div id="parentDiv" >
 			<div id="childDiv_1st" align="left" >
 				<div class="div_manage">功能管理</div>
 				<div class="div_disable div_manage">用户管理</div>
	 				<c:if test="${not empty userVOList}">
		 				<ul> 
		 					<c:forEach items="${userVOList}" var="vo" >
		 						<li onclick="userManEvent('${vo}');">${vo}</li>
		 					</c:forEach>
		 				</ul>
	 				</c:if>	
 				<div class="div_disable div_manage">安全管理</div>
 					<c:if test="${not empty secuVOList}">
		 				<ul> 
		 					<c:forEach items="${secuVOList}" var="vo" >
		 						<li>${vo}</li>
		 					</c:forEach>
		 				</ul>
	 				</c:if>	
 				<div class="div_disable div_manage">审计管理</div>
 					<c:if test="${not empty auditVOList}">
		 				<ul> 
		 					<c:forEach items="${auditVOList}" var="vo" >
		 						<li>${vo}</li>
		 					</c:forEach>
		 				</ul>
	 				</c:if>	
 			</div>
 			<div id="childDiv_2nd" class="easyui-panel" align="right" style="height: 620px;width: 680px;background-color: rgb(240,240,240);">
 			</div>
         </div>
</body>
</html>