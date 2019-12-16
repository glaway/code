/**
 * 功能管理公共js
 */

/**
 * 根据title获得需要跳转的方法
 * @param title 标题：即左侧a标签
 */
function getMethodByTitle(title) {
		var method = "";
		if("用户创建" == title){
			method = "?method=createUser";
		}
		if("用户查询/修改" == title){
			method = "?method=viewOrUpdate";
		}
		if("修改密码" == title){
			method = "?method=editPwd";
		}
		if("创建项目" == title){
			method = "?method=createProj";
		}
		if("创建组织" == title){
			method = "?method=createLogicOrga";
		}
		if("创建上下文" == title){
			method = "?method=createContext";
		}
		if("权限查询/修改" == title){
			method = "?method=queryOrUpdate";
		}
		if("用户权限" == title){
			method = "?method=userAndContextId";
		}
		if("添加权限" == title){
			method = "?method=addPermissions";
		}
		if("用户行为审计" == title){
			method = "?method=userStatistics";
		}
		if("管理员行为审计" == title){
			method = "?method=secStatistics";
		}
		if("审计查询" == title){
			method = "?method=logFileQuery";
		}
		return method;
	}
/**
 * 判断参数是否为空
 * @param param 传入的参数
 */
function isEmpty(param) {
	if(param != null && param != '' && param != undefined){
		return true;
	} else {
		return false;
	}
}
/**
 * 消息提示
 * @param msg 消息内容
 */
function tipMessage(msg) {
	$.messager.show({
		title:'提示消息',
		msg:msg,
		timeout:5000,
		showType:'slide'
	});
}
//加载信息
function loading(name, overlay) {
	$('body').append('<div id="overlay"></div><div id="preloader" style="width:100px;">' + name + '..</div>');
	if (overlay == 1) {
		$('#overlay').css('opacity', 0.1).fadeIn(function() {
			$('#preloader').fadeIn();
		});
		return false;
	}
	$('#preloader').fadeIn();
}
function hideTop() {
	$('#alertMessage').animate({
		opacity : 0,
		right : '-20'
	}, 500, function() {
		$(this).hide();
	});
}
function unloading() {
	$('#preloader').fadeOut('fast', function() {
		$('#overlay').fadeOut();
	});
}
