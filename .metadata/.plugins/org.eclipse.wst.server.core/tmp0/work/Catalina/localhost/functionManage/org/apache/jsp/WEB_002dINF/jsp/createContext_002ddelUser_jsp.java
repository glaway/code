package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createContext_002ddelUser_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("</head>\r\n");
      out.write("      \r\n");
      out.write("<body >\r\n");
      out.write("\t\t\t<div id=\"box1\">\r\n");
      out.write("\t\t\t\t   <table align=\"center\">\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td>用户名:</td>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td><input id=\"userName\" class=\"easyui-validatebox\" name=\"userName\"/></td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-search\" onclick=\"searchUserBaseList();\">查询</a></td>\r\n");
      out.write("\t\t\t\t\t            <td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-undo\" onclick=\"resetUserBaseList();\">重置</a> </td>\r\n");
      out.write("\t\t\t\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t   </table>\r\n");
      out.write("\t\t    \r\n");
      out.write("\t\t\t <div id=\"box2\" style=\"margin-bottom: 1px\">\r\n");
      out.write("\t\t\t\t\t<table id=\"t_baseUser\"></table>\r\n");
      out.write("\t\t\t\t\t<div style=\"float:right;display:none\" id=\"okButton\">\r\n");
      out.write("\t\t\t\t\t\t   <a class=\"easyui-linkbutton\"  icon=\"icon-ok\" onclick=\"delokUserBaseList();\">确定</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t </div>\r\n");
      out.write("\t\t\t  </div>\r\n");
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
