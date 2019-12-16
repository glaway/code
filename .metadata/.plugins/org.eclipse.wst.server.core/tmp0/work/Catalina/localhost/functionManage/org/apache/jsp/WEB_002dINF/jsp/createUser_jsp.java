package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createUser_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\t//确定\r\n");
      out.write("\t\tfunction saveUserInfo() {\r\n");
      out.write("\t\t\tvar userId = $('#id_userId').val();\r\n");
      out.write("\t\t\tvar firstName = $('#id_firstName').val();\r\n");
      out.write("\t\t\tvar lastName = $('#id_lastName').val();\r\n");
      out.write("\t\t\tvar phone = $('#id_phone').val();\r\n");
      out.write("\t\t\tvar address = $('#id_address').val();\r\n");
      out.write("\t\t\tvar email = $('#id_email').val();\r\n");
      out.write("\t\t\tvar organization = $('#id_organization').combobox('getText');\r\n");
      out.write("\t\t\tvar user_level = $('#user_level').combobox('getValue');\r\n");
      out.write("\t\t\tif(!isEmpty(userId)){\r\n");
      out.write("\t\t\t\ttipMessage(\"用户名必填\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(!isEmpty(organization)){\r\n");
      out.write("\t\t\t\ttipMessage(\"请选择组织\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\t      $.ajax({\r\n");
      out.write("\t \t            //几个参数需要注意一下\r\n");
      out.write("\t \t                type: \"POST\",//方法类型\r\n");
      out.write("\t \t                url: \"createUser.do?\" ,//url\r\n");
      out.write("\t \t                data: {\r\n");
      out.write("\t \t                \t'userId':userId,\r\n");
      out.write("\t \t    \t\t\t\t'firstName':firstName,\r\n");
      out.write("\t \t    \t\t\t\t'lastName':lastName,\r\n");
      out.write("\t \t    \t\t\t\t'phone':phone,\r\n");
      out.write("\t \t    \t\t\t\t'address':address,\r\n");
      out.write("\t \t    \t\t\t\t'email':email,  \r\n");
      out.write("\t \t    \t\t\t\t'organization':organization,\r\n");
      out.write("\t \t    \t\t\t\t'user_level':user_level\r\n");
      out.write("\t \t                },\r\n");
      out.write("\t \t               beforeSend: function () {\r\n");
      out.write("\t                       $(\".easyui-linkbutton\").attr(\"disabled\", true);\r\n");
      out.write("\t                   },\r\n");
      out.write("\t \t                success: function (data) {\r\n");
      out.write("\t \t                \t$(\".easyui-linkbutton\").removeAttr(\"disabled\");\r\n");
      out.write("\t \t                \tvar d = $.parseJSON(data);\r\n");
      out.write("\t \t   \t\t\t\t    tipMessage(d.message);\r\n");
      out.write("\t \t                }\r\n");
      out.write("\t \t            }); \r\n");
      out.write("\t\t      \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//重置\r\n");
      out.write("\t\tfunction cancelSave() {\r\n");
      out.write("\t\t\t$('#createUserform').form('clear'); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t $(function(){\r\n");
      out.write("\t\t\t$('#id_organization').combobox({    \r\n");
      out.write("\t\t\t    url:'organization.do?',    \r\n");
      out.write("\t\t\t    valueField:'org_id',    \r\n");
      out.write("\t\t\t    textField:'org_id',\r\n");
      out.write("\t\t\t    editable:false\t\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}); \r\n");
      out.write("\t\t\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<div id=\"createUserdiv\" align=\"center\" style=\"width: 100%;  height: 100%\" >\r\n");
      out.write("\t\t<form id=\"createUserform\" >\r\n");
      out.write("\t\t\t<table >\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>用户名(ID)：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"id_userId\" type=\"text\" name=\"id\" class=\"easyui-validatebox\" required=true missingMessage=\"用户名必填!\"/></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>姓(First Name)：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"id_firstName\" type=\"text\" name=\"firstName\" class=\"easyui-validatebox\" value=\"\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>名(Last Name)：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"id_lastName\" type=\"text\" name=\"lastName\" class=\"easyui-validatebox\" value=\"\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>电话(Phone)：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"id_phone\" type=\"text\" name=\"phone\" class=\"easyui-numberbox\" value=\"\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>地址(Address)：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"id_address\" type=\"text\" name=\"address\" class=\"easyui-validatebox\"  value=\"\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>邮箱(Email)：</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"id_email\" type=\"text\" name=\"email\" value=\"\" /></td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>组织(Organization)：</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t           <input id=\"id_organization\" name=\"id_organization\" >  \r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>密级：</td>\r\n");
      out.write("\t\t\t\t\t<td><select id=\"user_level\" class=\"easyui-combobox\" name=\"user_level\" editable=\"false\">   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"1\">普通用户</option>   \r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr align=\"right\">\r\n");
      out.write("\t\t\t\t\t<td ><a class=\"easyui-linkbutton\" onclick=\"saveUserInfo();\" icon=\"icon-ok\">确定</a></td>\r\n");
      out.write("\t\t\t\t\t<td ><a class=\"easyui-linkbutton\" onclick=\"cancelSave();\" icon=\"icon-undo\">重置</a></td>\r\n");
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
