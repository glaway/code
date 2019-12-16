package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createProj_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<title>创建用户</title>\r\n");
      out.write("\r\n");
      out.write("</head>\r\n");
      out.write("<body >\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\t//查询\r\n");
      out.write("\t\tfunction createProjOk() {\r\n");
      out.write("\t\t\tvar projectNumber = $('#projectNumber').val();\r\n");
      out.write("\t\t\tif(!isEmpty(projectNumber)){\r\n");
      out.write("\t\t\t\ttipMessage('请输入项目编号');\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t    $.ajax({\r\n");
      out.write(" \t            //几个参数需要注意一下\r\n");
      out.write(" \t                type: \"POST\",//方法类型\r\n");
      out.write(" \t                url: \"createProj.do?\" ,//url\r\n");
      out.write(" \t                data: {\r\n");
      out.write(" \t                \t'projectNumber':projectNumber\r\n");
      out.write(" \t                },\r\n");
      out.write(" \t               beforeSend: function () {\r\n");
      out.write("                       $(\".easyui-linkbutton\").attr(\"disabled\", true);\r\n");
      out.write("                   },\r\n");
      out.write(" \t                success: function (data) {\r\n");
      out.write(" \t                \t$(\".easyui-linkbutton\").removeAttr(\"disabled\");\r\n");
      out.write(" \t                \tvar d = $.parseJSON(data);\r\n");
      out.write(" \t   \t\t\t\t    tipMessage(d.message);\r\n");
      out.write(" \t                }\r\n");
      out.write(" \t            });\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//重置\r\n");
      out.write("\t\tfunction resetProj() {\r\n");
      out.write("\t\t\t$('#projectform').form('clear'); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<div id=\"projectdiv\" align=\"center\" style=\"width: 100%; height: 100%\" >\r\n");
      out.write("\t\t<form id=\"projectform\" >\r\n");
      out.write("\t\t\t<table >\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>项目编号：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"projectNumber\" type=\"text\" name=\"projectNumber\" class=\"easyui-validatebox\" required=true missingMessage=\"项目编号必填!\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr align=\"right\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" onclick=\"createProjOk();\" icon=\"icon-ok\">确定</a></td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-undo\" onclick=\"resetProj();\">重置</a></td>\r\n");
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
