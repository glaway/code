package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userStatistics_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/jquery-1.7.2.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/jquery.easyui.min.js\"></script>\r\n");
      out.write("\t<script type=\"text/javascript\" src=\"js/jquery-easyui-1.2.6/locale/easyui-lang-zh_CN.js\"></script>\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"js/jquery-easyui-1.2.6/themes/default/easyui.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"js/jquery-easyui-1.2.6/themes/icon.css\">\r\n");
      out.write("\t<link rel=\"stylesheet\" href=\"js/jquery-easyui-1.2.6/demo/demo.css\">\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body >\r\n");
      out.write("\t<script type=\"text/javascript\">\r\n");
      out.write("\t//确定\r\n");
      out.write("\tfunction exportUserStatistics() {\r\n");
      out.write("\t\tdebugger;\r\n");
      out.write("\t\tvar user_startDate = $('#user_startDate').datebox('getValue');\r\n");
      out.write("\t\tvar user_endDate = $('#user_endDate').datebox('getValue');\r\n");
      out.write("\t\tvar user_operationType = $('#user_operationType').combobox('getText');\r\n");
      out.write("\t\tif(!isEmpty(user_startDate)){\r\n");
      out.write("\t\t\ttipMessage(\"开始时间必填\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t} \r\n");
      out.write("\t    if (!isEmpty(user_endDate)) {\r\n");
      out.write("\t\t\ttipMessage(\"结束时间必填\");\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t} \r\n");
      out.write("\t    if (!isEmpty(user_operationType)) {\r\n");
      out.write("\t\t\ttipMessage(\"事件类型必填\");\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t} \r\n");
      out.write("\t    var url = 'userStatistics.do?user_startDate=' + user_startDate+'&user_endDate='+user_endDate+'&user_operationType='+user_operationType;\r\n");
      out.write("\t\turl = encodeURI(url);\r\n");
      out.write("\t\twindow.location.href = url;\r\n");
      out.write("\t}\r\n");
      out.write("\t//重置\r\n");
      out.write("\tfunction resetUserStatistics() {\r\n");
      out.write("\t\t$('#userStatisticsform').form('clear'); \r\n");
      out.write("\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\t\r\n");
      out.write("\t<div id=\"mydiv\"\t align=\"center\"\r\n");
      out.write("\t\tstyle=\"width: 100%; height: 100%\" title=\"用户新增\">\r\n");
      out.write("\t\t<form id=\"userStatisticsform\" >\r\n");
      out.write("\t\t\t<table >\r\n");
      out.write("\t\t\t\t<tr style=\"height:10px\">\r\n");
      out.write("\t\t\t\t\t<td>*开始时间:</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"user_startDate\" type=\"text\" class=\"easyui-datebox\" required=\"required\" editable=\"false\" />\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr >\r\n");
      out.write("\t\t\t\t\t<td>*结束时间:</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"user_endDate\" type=\"text\" class=\"easyui-datebox\" required=\"required\" editable=\"false\"/>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>*事件类型:</td>\r\n");
      out.write("\t\t\t\t\t<td><select id=\"user_operationType\" class=\"easyui-combobox\" name=\"user_operationType\" editable=\"false\">   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"登陆\">登陆</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"登出\">登出</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"创建零件\">创建零件</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"删除零件\">删除零件</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"删除图文档\">删除图文档</option>   \r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr align=\"right\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-redo\" onclick=\"exportUserStatistics();\">导出</a></td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-undo\" onclick=\"resetUserStatistics();\" >重置</a> </td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
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
