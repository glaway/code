package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write('\n');

String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";

      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\">\n");
      out.write("<html>\n");
      out.write("\n");
      out.write("  <head>\n");
      out.write("    <base href=\"");
      out.print(basePath);
      out.write("\">\n");
      out.write("    \n");
      out.write("    <title>安全保密审计功能</title>\n");
      out.write("    \n");
      out.write("\t<meta http-equiv=\"pragma\" content=\"no-cache\">\n");
      out.write("\t<meta http-equiv=\"cache-control\" content=\"no-cache\">\n");
      out.write("\t<meta http-equiv=\"expires\" content=\"0\">    \n");
      out.write("\t<meta http-equiv=\"keywords\" content=\"keyword1,keyword2,keyword3\">\n");
      out.write("\t<meta http-equiv=\"description\" content=\"This is my page\">\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"css/common.css\" />\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/jquery-1.7.2.min.js\"></script>\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"js/jquery-easyui-1.2.6/themes/default/easyui.css\" />\n");
      out.write("\t<link rel=\"stylesheet\" type=\"text/css\" href=\"js/jquery-easyui-1.2.6/themes/icon.css\" />\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/jquery.easyui.min.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js\"></script>\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/common.js\"></script>\n");
      out.write("\t\n");
      out.write("\t<script type=\"text/javascript\">\n");
      out.write("\t//回车登录\n");
      out.write("\t$(document).keydown(function(e){\n");
      out.write("\t\tif(e.keyCode == 13) {\n");
      out.write("\t\t\tsubmit();\n");
      out.write("\t\t}\n");
      out.write("\t});\n");
      out.write("\t\t\t$(function(){\n");
      out.write("\t\t\t\tdebugger;\n");
      out.write("\t\t\t\t$('#id_userId').val(''); \n");
      out.write("\t\t\t\t$('#id_password').val('');\n");
      out.write("\t\t\t});\n");
      out.write("\t\t//确定\n");
      out.write("\t\tfunction loginUserInfo(){\n");
      out.write("\t\t\tdebugger;\n");
      out.write("\t\t\tvar id_userId = $('#id_userId').val();\n");
      out.write("\t\t\tvar id_password = $('#id_password').val();\n");
      out.write("\t\t\tif(!isEmpty(id_userId)){\n");
      out.write("\t\t\t\ttipMessage(\"用户名必填\");\n");
      out.write("\t\t\t\treturn false;\n");
      out.write("\t\t\t}\n");
      out.write("\t\t\tif(!isEmpty(id_password)){\n");
      out.write("\t\t\t\ttipMessage(\"密码必填\");\n");
      out.write("\t\t\t\treturn false;\n");
      out.write("\t\t\t}\n");
      out.write(" \t\t\t$.ajax({\n");
      out.write(" \t            //几个参数需要注意一下\n");
      out.write(" \t                type: \"POST\",//方法类型\n");
      out.write(" \t                url: \"checkUserLogin.do?\" ,//url\n");
      out.write(" \t                dataType: \"json\",//预期服务器\n");
      out.write(" \t                data: {\n");
      out.write(" \t                \t'id_userId':id_userId,\n");
      out.write(" \t                \t'id_password':id_password\n");
      out.write(" \t                },\n");
      out.write(" \t               beforeSend: function () {\n");
      out.write("                       $(\"#loginUserInfo\").attr(\"disabled\", true);\n");
      out.write("                       $(\"#resetUserInfo\").attr(\"disabled\", true);\n");
      out.write("                   },\n");
      out.write(" \t                success: function (data) {\n");
      out.write(" \t                \tdebugger;\n");
      out.write(" \t                   if(data.message=='登录成功'){\n");
      out.write(" \t             \t\t\t   var level=data.level;\n");
      out.write(" \t             \t\t\t   if(data.level=='$'){\n");
      out.write(" \t             \t\t\t\t  level='4';\n");
      out.write(" \t             \t\t\t   }\n");
      out.write(" \t             \t\t\t\tvar actionurl='home.do?level='+level+'&id_userId='+id_userId;\n");
      out.write(" \t\t \t                \tsetTimeout(\"window.location.href='\"+actionurl+\"'\", 200); \n");
      out.write(" \t                \t}else{\n");
      out.write(" \t                \t\ttipMessage(data.message);\n");
      out.write(" \t                \t\t$(\"#loginUserInfo\").removeAttr(\"disabled\");\n");
      out.write(" \t \t                \t$(\"#resetUserInfo\").removeAttr(\"disabled\");\n");
      out.write(" \t                \t}\n");
      out.write(" \t                }\n");
      out.write(" \t            }); \n");
      out.write("\t\t     \n");
      out.write("\t\t}\n");
      out.write("\t\t//重置\n");
      out.write("\t\tfunction resetUserInfo() {\n");
      out.write("\t\t\t$('#loginUserform').form('clear'); \n");
      out.write("\t\t}  \n");
      out.write("\t</script>\n");
      out.write("  </head>\n");
      out.write("<body>\n");
      out.write("  \n");
      out.write("\t<div id=\"logniUserdiv\" align=\"center\"  style=\"width: 500px;  height: 500px\"  >\n");
      out.write("\t\t<form id=\"loginUserform\" >\n");
      out.write("\t\t\t<table >\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>用户名(ID)：</td>\n");
      out.write("\t\t\t\t\t<td><input id=\"id_userId\" type=\"text\" name=\"id_userId\" class=\"easyui-validatebox\" /></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr>\n");
      out.write("\t\t\t\t\t<td>密码：</td>\n");
      out.write("\t\t\t\t\t<td><input id=\"id_password\" type=\"password\" name=\"id_password\" class=\"easyui-validatebox\" /></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t\t<tr align=\"right\">\n");
      out.write("\t\t\t\t\t<td ><a  id=\"loginUserInfo\" class=\"easyui-linkbutton\" onclick=\"loginUserInfo();\" icon=\"icon-ok\">登录</a></td>\n");
      out.write("\t\t\t\t\t<td ><a  id=\"resetUserInfo\" class=\"easyui-linkbutton\"  onclick=\"resetUserInfo();\"  icon=\"icon-undo\">重置</a></td>\n");
      out.write("\t\t\t\t</tr>\n");
      out.write("\t\t\t</table>\n");
      out.write("\t\t</form>\n");
      out.write("\t</div>\n");
      out.write("</body>\n");
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
