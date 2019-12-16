package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class addPermissions_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\tfunction addOK() {\r\n");
      out.write("\t\tvar addPermission_contextId = $('#addPermission_contextId').combobox('getValue');\r\n");
      out.write("\t\tvar type = $('#type').combobox('getValue');\r\n");
      out.write("\t\tvar operationGroup = $('#operationGroup').combobox('getValue');\r\n");
      out.write("\t\tvar dataGroup = $('#dataGroup').combobox('getValue');\r\n");
      out.write("\t\tif (!isEmpty(addPermission_contextId)) {\r\n");
      out.write("\t\t\ttipMessage(\"请选择上下文\");\r\n");
      out.write("\t\t\treturn false;\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\tif(type=='VPM'||type=='VPMAdmin'){\r\n");
      out.write("\t\t\tif(operationGroup!='LOGIN'){\r\n");
      out.write("\t\t\t\ttipMessage(\"类型为VPM时操作/操作组应为LOGIN\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(isEmpty(dataGroup)){\r\n");
      out.write("\t\t\t\ttipMessage(\"类型为VPM或VPMAdmin时无数据组\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}else{\r\n");
      out.write("\t\t\tif(operationGroup=='LOGIN'){\r\n");
      out.write("\t\t\t\ttipMessage(\"类型为ProcessGroup时操作/操作组不能为LOGIN\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}else{\r\n");
      out.write("\t\t\t\tif(operationGroup=='AllGlobalProcess'&&isEmpty(dataGroup)){\r\n");
      out.write("\t\t\t\t\ttipMessage(\"操作/操作组为AllGlobalProcess时无数据组\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\tif(operationGroup=='AllObjectProcess'&&!isEmpty(dataGroup)){\r\n");
      out.write("\t\t\t\t\ttipMessage(\"操作/操作组为AllObjectProcess时数据组不能为空\");\r\n");
      out.write("\t\t\t\t\treturn false;\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t $.ajax({\r\n");
      out.write("\t            //几个参数需要注意一下\r\n");
      out.write("\t                type: \"POST\",//方法类型\r\n");
      out.write("\t                url: \"addPermissions.do?\" ,//url\r\n");
      out.write("\t                data: {\r\n");
      out.write("\t                \t'contextId':addPermission_contextId,\r\n");
      out.write("\t    \t\t\t\t'type':type,\r\n");
      out.write("\t    \t\t\t\t'operationGroup':operationGroup,\r\n");
      out.write("\t    \t\t\t\t'dataGroup':dataGroup\r\n");
      out.write("\t                },\r\n");
      out.write("\t               beforeSend: function () {\r\n");
      out.write("                    $(\".easyui-linkbutton\").attr(\"disabled\", true);\r\n");
      out.write("                },\r\n");
      out.write("\t                success: function (data) {\r\n");
      out.write("\t                \t$(\".easyui-linkbutton\").removeAttr(\"disabled\");\r\n");
      out.write("\t                \tvar d = $.parseJSON(data);\r\n");
      out.write("\t   \t\t\t\t    tipMessage(d.message);\r\n");
      out.write("\t                }\r\n");
      out.write("\t            });\r\n");
      out.write("\t\t \r\n");
      out.write("\t}\r\n");
      out.write("\tfunction resetAdd() {\r\n");
      out.write("\t\t$('#addPermission_contextId').combobox('clear');\r\n");
      out.write("\t\t//$('#addPermissionsform').form('clear'); \r\n");
      out.write("\t}\r\n");
      out.write("\t $(function(){\r\n");
      out.write("\t\t\t$('#addPermission_contextId').combobox({    \r\n");
      out.write("\t\t\t    url:'addPermission_contextId.do?',    \r\n");
      out.write("\t\t\t    valueField:'contextId',    \r\n");
      out.write("\t\t\t    textField:'contextId',\r\n");
      out.write("\t\t\t    editable:false\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t}); \r\n");
      out.write("\t \r\n");
      out.write("</script>\r\n");
      out.write("\t<div id=\"addPermissionsdiv\"\t align=\"center\"\r\n");
      out.write("\t\tstyle=\"width: 100%; height: 100%\" title=\"用户新增\">\r\n");
      out.write("\t\t<form id=\"addPermissionsform\" >\r\n");
      out.write("\t\t\t<table  >\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>上下文(Context ID):</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<!-- <input id=\"addPermission_contextId\" class=\"easyui-validatebox\" name=\"addPermission_contextId\" required=\"true\"/> -->\r\n");
      out.write("\t\t\t\t\t<input id=\"addPermission_contextId\" name=\"addPermission_contextId\" >  \r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>*类型:</td>\r\n");
      out.write("\t\t\t\t\t<td><select id=\"type\" class=\"easyui-combobox\" name=\"type\" editable=\"false\">   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"VPM\">VPM</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"VPMAdmin\">VPMAdmin</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"ProcessGroup\">ProcessGroup</option>   \r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>*操作/操作组:</td>\r\n");
      out.write("\t\t\t\t\t<td><select id=\"operationGroup\" class=\"easyui-combobox\" name=\"operationGroup\" editable=\"false\">   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"LOGIN\">LOGIN</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"AllGlobalProcess\">AllGlobalProcess</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"AllObjectProcess\">AllObjectProcess</option>   \r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>数据组:</td>\r\n");
      out.write("\t\t\t\t\t<td><select id=\"dataGroup\" class=\"easyui-combobox\" name=\"dataGroup\" editable=\"false\">   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"\"> </option>     \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"AllData\">AllData</option> \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"MyAncestor\">MyAncestor</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"MyData\">MyData</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"MyDescendant\">MyDescendant</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"MyOrgData\">MyOrgData</option>   \r\n");
      out.write("\t\t\t\t\t\t</select>\r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr align=\"right\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-ok\" onclick=\"addOK();\">确定</a></td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-undo\" onclick=\"resetAdd();\" >重置</a></td>\r\n");
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
