package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class viewOrUpdate_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.AnnotationProcessor _jsp_annotationprocessor;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_annotationprocessor = (org.apache.AnnotationProcessor) getServletConfig().getServletContext().getAttribute(org.apache.AnnotationProcessor.class.getName());
  }

  public void _jspDestroy() {
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html; charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("\r\n");
      out.write("<head>\r\n");
      out.write("\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("\t<title>功能管理</title>\r\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/common.css\" />\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/jquery-1.7.2.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"js/jquery-easyui-1.2.6/themes/default/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"js/jquery-easyui-1.2.6/themes/icon.css\">\r\n");
      out.write("\t\r\n");
      out.write("\t\r\n");
      out.write("</head>\r\n");
      out.write("<body >\r\n");
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("    var viewpageNumber=1;\r\n");
      out.write("    var viewpageSize=10;\r\n");
      out.write("  //确定\r\n");
      out.write("\tfunction searchUserList() {\r\n");
      out.write("\t\tdebugger;\r\n");
      out.write("\t\t\r\n");
      out.write("\t\tvar viewOrUpdateUserId = $('#userId').val();\r\n");
      out.write("\t\tvar viewOrUpdateFullName = $('#fullName').val();\r\n");
      out.write("\t\t\t  //datagrid显示数据 \r\n");
      out.write("\t\t\t   $('#t_user').datagrid({\r\n");
      out.write("\t\t\t\ttitle:'用户列表',\r\n");
      out.write("\t\t\t\twidth:\"100%\",\r\n");
      out.write("\t\t\t\theight:760,\r\n");
      out.write("\t\t\t\tnowrap: false,\r\n");
      out.write("\t\t\t\tstriped: true,//是否显示斑马线效果\r\n");
      out.write("\t\t\t\tcollapsible:false,//默认不可折叠\r\n");
      out.write("\t\t\t\turl:'queryUser.do?viewOrUpdateUserId='+viewOrUpdateUserId+'&viewOrUpdateFullName='+viewOrUpdateFullName+'&pageNumber='+viewpageNumber+'&pageSize='+viewpageSize,//此处应为服务器请求数据，暂时读取json中的数据\r\n");
      out.write("\t\t\t\tfitColumns: true,\r\n");
      out.write("\t\t\t\tsortOrder: 'desc',\r\n");
      out.write("\t\t\t\tremoteSort: false,\r\n");
      out.write("\t\t\t\tsingleSelect: true,\r\n");
      out.write("\t\t\t\tpageSize:10,   //表格中每页显示的行数\r\n");
      out.write("\t\t\t\tpageList:[10,20,30],\r\n");
      out.write("\t            columns:[[//需要在datagrid中显示的列\r\n");
      out.write("                    {field:'userId',title:'用户名',width:100},\r\n");
      out.write("\t\t               {field:'fullName',title:'全名',width:100},\r\n");
      out.write("\t\t               {field:'phone',title:'电话',width:100},\r\n");
      out.write("\t\t               {field:'address',title:'地址',width:100},\r\n");
      out.write("\t\t               {field:'email',title:'邮箱',width:100},\r\n");
      out.write("\t\t               {field:'organization',title:'组织',width:100},\r\n");
      out.write("\t\t               //最后一列为操作列\r\n");
      out.write("\t\t               {field:'edit',title:'修改密码',width:100,align:'center', rowspan:2,\r\n");
      out.write("\t\t\t\t\t\t\tformatter:function(value,row){\r\n");
      out.write("\t\t\t\t\t\t\t\treturn \"<a href='javascript:void(0);' onclick=doEdit('\" + row.userId + \"')>修改</a>\";\r\n");
      out.write("\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t},\r\n");
      out.write("\t\t\t           {field:'cancell',title:'用户注销',width:100,align:'center', rowspan:2,\r\n");
      out.write("\t\t\t\t\t\t\t\tformatter:function(value,row){\r\n");
      out.write("\t\t\t\t\t\t\t\t\treturn \"<a href='javascript:void(0);' onclick=cancell('\" + row.userId + \"')>注销</a>\";\r\n");
      out.write("\t\t\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\t\t}\r\n");
      out.write("\t\t\t    ]],  \r\n");
      out.write("\t\t\t\t    \r\n");
      out.write("\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\trownumbers:true\r\n");
      out.write("\t\t\t});  \r\n");
      out.write("\t\t\r\n");
      out.write("\t\t\tvar p = $('#t_user').datagrid('getPager');\r\n");
      out.write("\t\t\t$(p).pagination({\r\n");
      out.write("\t\t\t\tshowRefresh: false,\r\n");
      out.write("\t\t\t\tonSelectPage: function(pageNumber, pageSize) {\r\n");
      out.write("\t\t\t\t\tviewpageNumber=pageNumber;\r\n");
      out.write("\t\t\t\t\tviewpageSize=pageSize;\r\n");
      out.write("\t\t\t  $('#t_user').datagrid('options').url ='queryUser.do?viewOrUpdateUserId='+$('#userId').val()+'&viewOrUpdateFullName='+$('#fullName').val()+'&pageNumber='+pageNumber+'&pageSize='+pageSize;\r\n");
      out.write("\t\t\t  $(\"#t_user\").datagrid('reload');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t}\r\n");
      out.write("    function cancell(userId){\r\n");
      out.write("    \t var truthBeTold = window.confirm('确认注销?');\r\n");
      out.write("    \t if(truthBeTold){\r\n");
      out.write("    \t\t $.ajax({\r\n");
      out.write("\t \t            //几个参数需要注意一下\r\n");
      out.write("\t \t                type: \"POST\",//方法类型\r\n");
      out.write("\t \t                url: \"cancellUser.do?\" ,//url\r\n");
      out.write("\t \t                data: {\r\n");
      out.write("\t \t                \t'userId':userId\r\n");
      out.write("\t \t                },\r\n");
      out.write("\t \t               beforeSend: function () {\r\n");
      out.write("\t                       $(\".easyui-linkbutton\").attr(\"disabled\", true);\r\n");
      out.write("\t                   },\r\n");
      out.write("\t \t                success: function (data) {\r\n");
      out.write("\t \t                \t$(\".easyui-linkbutton\").removeAttr(\"disabled\");\r\n");
      out.write("\t \t                \tvar d = $.parseJSON(data);\r\n");
      out.write("\t \t   \t\t\t\t    tipMessage(d.message);\r\n");
      out.write("\t \t   \t\t\t\t   $('#t_user').datagrid('reload');\r\n");
      out.write("\t \t                }\r\n");
      out.write("\t \t            }); \r\n");
      out.write("    \t }\r\n");
      out.write("    \t\r\n");
      out.write("    }\r\n");
      out.write("  \r\n");
      out.write("\t//操作列  修改功能\r\n");
      out.write("    function doEdit(userId) {  \r\n");
      out.write("\t\tdebugger;\r\n");
      out.write("\t\tvar url = 'userManage.do?method=updateUserInfo&userId=' + userId;//需要跳转的URL\r\n");
      out.write("\t\t$('#dialog_update').dialog({  \r\n");
      out.write("            title: '修改密码',  \r\n");
      out.write("            width: 800,  \r\n");
      out.write("            height: 400,  \r\n");
      out.write("            closed: false,  \r\n");
      out.write("            cache: false,  \r\n");
      out.write("            href: url,  \r\n");
      out.write("            modal: true,  \r\n");
      out.write("            buttons:[{ //为窗口添加按钮  \r\n");
      out.write("                text:'确定',  \r\n");
      out.write("                handler:function(){  \r\n");
      out.write("                \tvar dialogUserId = $('#userId2').val();//弹出框中的用户名\r\n");
      out.write("                \tvar dialogPwd = $('#password').val();//弹出框中的新密码\r\n");
      out.write("                \tvar dialogPwd2 = $('#password2').val();//弹出框中的确认密码\r\n");
      out.write("                \t//都不为空提示修改成功\r\n");
      out.write("                \tif(isEmpty(dialogUserId) && isEmpty(dialogPwd) && isEmpty(dialogPwd2)){\r\n");
      out.write("                \t\tif(dialogPwd!=dialogPwd2){\r\n");
      out.write("        \t\t\t\t\ttipMessage('密码不一致');\r\n");
      out.write("        \t\t\t\t\treturn false;\r\n");
      out.write("        \t\t\t\t}\r\n");
      out.write("                \t\t $.post('updateUserPwd.do?', {\r\n");
      out.write("              \t\t     \t'userId':userId,\r\n");
      out.write("              \t\t\t\t'dialogPwd2':dialogPwd2\r\n");
      out.write("              \t\t\t}, function(data) {\r\n");
      out.write("              \t\t\t\tdebugger;\r\n");
      out.write("              \t\t\t\tvar d = $.parseJSON(data); \r\n");
      out.write("             \t\t\t\ttipMessage(d.message);\r\n");
      out.write("             \t\t\t\tsetTimeout(function(){\r\n");
      out.write("                        \t\t$('#dialog_update').dialog('close');\r\n");
      out.write("                        \t}, 300);\r\n");
      out.write("              \t\t\t});\r\n");
      out.write("        \t\t\t} else{\r\n");
      out.write("        \t\t\t\tif(!isEmpty(dialogUserId)){\r\n");
      out.write("        \t\t\t\t\ttipMessage('请输入用户名');\r\n");
      out.write("        \t\t\t\t\treturn false;\r\n");
      out.write("        \t\t\t\t} \r\n");
      out.write("        \t\t\t\tif(!isEmpty(dialogPwd)){\r\n");
      out.write("        \t\t\t\t\ttipMessage('请输入新密码');\r\n");
      out.write("        \t\t\t\t\treturn false;\r\n");
      out.write("        \t\t\t\t}\r\n");
      out.write("        \t\t\t\tif(!isEmpty(dialogPwd2)){\r\n");
      out.write("        \t\t\t\t\ttipMessage('请输入确认密码');\r\n");
      out.write("        \t\t\t\t\treturn false;\r\n");
      out.write("        \t\t\t\t}\r\n");
      out.write("        \t\t\t}\r\n");
      out.write("                \t//ajax进入后台  \r\n");
      out.write("                }  \r\n");
      out.write("            },{  \r\n");
      out.write("                text:'关闭',  \r\n");
      out.write("                handler:function(){\r\n");
      out.write("                \tsetTimeout(function(){\r\n");
      out.write("                \t\t$('#dialog_update').dialog('close');\r\n");
      out.write("                \t}, 300);\r\n");
      out.write("                }  \r\n");
      out.write("            }]  \r\n");
      out.write("            }); \r\n");
      out.write("\t\t$('#dialog_update').dialog('refresh', url); \r\n");
      out.write("\t\t//$('#dialog_update').dialog('open'); \r\n");
      out.write("    }\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//重置\r\n");
      out.write("\t\tfunction resetUserList() {\r\n");
      out.write("\t\t\t$('#queryUserform').form('clear'); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<div id=\"queryUserdiv\"\tstyle=\"width: 100%; height: 100%\" >\r\n");
      out.write("\t\t<form id=\"queryUserform\" >\r\n");
      out.write("\t\t\t<table align=\"center\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<tr style=\"height:10px\">\r\n");
      out.write("\t\t\t\t\t<td>用户名(ID)：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"userId\" type=\"text\" name=\"id\" class=\"easyui-validatebox\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>全名(Full Name)：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"fullName\" type=\"text\" name=\"fullName\" class=\"easyui-validatebox\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr align=\"right\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-search\" onclick=\"searchUserList();\">查询</a></td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-undo\" onclick=\"resetUserList();\">重置</a> </td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<div region=\"center\" >\r\n");
      out.write("\t\t\t\t<table id=\"t_user\"></table>\r\n");
      out.write("\t\t\t</div>\t\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t\t<div id=\"dialog_update\"></div>\r\n");
      out.write("\t</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else log(t.getMessage(), t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
