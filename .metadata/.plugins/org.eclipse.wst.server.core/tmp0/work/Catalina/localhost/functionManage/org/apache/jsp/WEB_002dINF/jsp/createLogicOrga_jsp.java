package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createLogicOrga_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t<title>创建逻辑组织</title>\r\n");
      out.write("</head>\r\n");
      out.write("<body >\r\n");
      out.write("<script type=\"text/javascript\">\r\n");
      out.write("\t\t//查询\r\n");
      out.write("\t\tfunction saveLogicOrga() {\r\n");
      out.write("\t\t\tdebugger;\r\n");
      out.write("\t\t\tvar orgaNumber = $('#orgaNumber').val();//组织编号\r\n");
      out.write("\t\t\tvar parentOrgaNumber = $('#parentOrgaNumber').combobox('getText');\r\n");
      out.write("\t\t\tif(!isEmpty(orgaNumber)){\r\n");
      out.write("\t\t\t    tipMessage('请输入组织编号');\r\n");
      out.write("\t\t\t    return false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(!isEmpty(parentOrgaNumber)){\r\n");
      out.write("\t\t\t    tipMessage('请选择父组织编号');\r\n");
      out.write("\t\t\t    return false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t  $.ajax({\r\n");
      out.write("\t \t            //几个参数需要注意一下\r\n");
      out.write("\t \t                type: \"POST\",//方法类型\r\n");
      out.write("\t \t                url: \"createLogicOrga.do?\" ,//url\r\n");
      out.write("\t \t                data: {\r\n");
      out.write("\t \t                \t'orgaNumber':orgaNumber,\r\n");
      out.write("\t \t                \t'parentOrgaNumber':parentOrgaNumber\r\n");
      out.write("\t \t                },\r\n");
      out.write("\t \t               beforeSend: function () {\r\n");
      out.write("\t                       $(\".easyui-linkbutton\").attr(\"disabled\", true);\r\n");
      out.write("\t                   },\r\n");
      out.write("\t \t                success: function (data) {\r\n");
      out.write("\t \t                \t$(\".easyui-linkbutton\").removeAttr(\"disabled\");\r\n");
      out.write("\t \t                \tvar d = $.parseJSON(data);\r\n");
      out.write("\t \t   \t\t\t\t    tipMessage(d.message);\r\n");
      out.write("\t \t                }\r\n");
      out.write("\t \t            });\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//重置\r\n");
      out.write("\t\tfunction resetLogicOrga() {\r\n");
      out.write("\t\t\t$('#orgaNumber').val('');\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t $(function(){\r\n");
      out.write("\t\t\t\t$('#parentOrgaNumber').combobox({    \r\n");
      out.write("\t\t\t\t    url:'organization.do?',    \r\n");
      out.write("\t\t\t\t    valueField:'org_id',    \r\n");
      out.write("\t\t\t\t    textField:'org_id',\r\n");
      out.write("\t\t\t\t    editable:false\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t</script>\r\n");
      out.write("\t<div id=\"mydiv\" align=\"center\" style=\"width: 100%; height: 100%\" >\r\n");
      out.write("\t\t<form id=\"myform\" >\r\n");
      out.write("\t\t\t<table >\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>* 组织编号：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"orgaNumber\" type=\"text\" name=\"orgaNumber\" class=\"easyui-validatebox\" required=true missingMessage=\"组织编号必填!\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td> 父组织编号：</td>\r\n");
      out.write("\t\t\t\t\t<td> <input id=\"parentOrgaNumber\" name=\"parentOrgaNumber\" >  </td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr align=\"right\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" onclick=\"saveLogicOrga();\" icon=\"icon-ok\">确定</a></td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-undo\" onclick=\"resetLogicOrga();\">重置</a></td>\r\n");
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
