package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class userAndContextId_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("    <script type=\"text/javascript\">\r\n");
      out.write("    var userpageNumber=1;\r\n");
      out.write("    var userpageSize=10;\r\n");
      out.write("\t\t//查询\r\n");
      out.write("\t\tfunction searchUserAndContextIdList() {\r\n");
      out.write("\t\t\tvar usercontextId = $('#usercontextId').val();\r\n");
      out.write("\t\t\tdebugger;\r\n");
      out.write("\t\t\ttry{\r\n");
      out.write("\t\t\t$('#t_userAndContextId').datagrid({\r\n");
      out.write("\t\t\t\ttitle:'权限列表',\r\n");
      out.write("\t\t\t\twidth:\"100%\",\r\n");
      out.write("\t\t\t\theight:780,\r\n");
      out.write("\t\t\t\tnowrap: false,\r\n");
      out.write("\t\t\t\tstriped: true,\r\n");
      out.write("\t\t\t\tcollapsible:false,\r\n");
      out.write("\t\t\t\t//url:'json/operationList.json',\r\n");
      out.write("\t\t\t\turl:'queryUserAndContextId.do?usercontextId='+usercontextId+'&pageNumber='+userpageNumber+'&pageSize='+userpageSize,//此处应为服务器请求数据，暂时读取json中的数据\r\n");
      out.write("\t\t\t\t//sortName: 'code',\r\n");
      out.write("\t\t\t\tfitColumns: true,\r\n");
      out.write("\t\t\t\tsortOrder: 'desc',\r\n");
      out.write("\t\t\t\tremoteSort: false,\r\n");
      out.write("\t\t\t\trownumbers: true,  //行号 \r\n");
      out.write("\t\t\t\tsingleSelect: true,\r\n");
      out.write("\t\t\t\tpageSize:10,   //表格中每页显示的行数\r\n");
      out.write("\t\t\t\tpageList:[10,20,30],\r\n");
      out.write("\t\t\t\t//idField:'contextId',\r\n");
      out.write("\t            columns:[[\r\n");
      out.write("                       {field:'userId',title:'用户',width:100},\r\n");
      out.write("                       {field:'contextId',title:'上下文',width:100}\r\n");
      out.write("\t\t\t    ]],  \r\n");
      out.write("\t\t\t\t    \r\n");
      out.write("\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\trownumbers:true\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\tvar p = $('#t_userAndContextId').datagrid('getPager');\r\n");
      out.write("\t\t\t$(p).pagination({\r\n");
      out.write("\t\t\t\tshowRefresh: false,\r\n");
      out.write("\t\t\t\tonSelectPage: function(pageNumber, pageSize) {\r\n");
      out.write("\t\t\t\t\tuserpageNumber=pageNumber;\r\n");
      out.write("\t\t\t\t\tuserpageSize=pageSize;\r\n");
      out.write("\t\t\t  $('#t_userAndContextId').datagrid('options').url ='queryUserAndContextId.do?usercontextId='+usercontextId+'&pageNumber='+pageNumber+'&pageSize='+pageSize;\r\n");
      out.write("\t\t\t  $(\"#t_userAndContextId\").datagrid('reload');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\talert(e.message);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//重置\r\n");
      out.write("\t\tfunction resetUserAndContextIdList() {\r\n");
      out.write("\t\t\t$('#queryOrUpdateform').form('clear'); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<div id=\"userAndContextIddiv\"\tstyle=\"width: 100%; height: 100%\" >\r\n");
      out.write("\t\t<form id=\"userAndContextIdform\" >\r\n");
      out.write("\t\t\t<table align=\"center\">\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<tr style=\"height:10px\">\r\n");
      out.write("\t\t\t\t\t<td>用户名(ID):</td>\r\n");
      out.write("\t\t\t\t\t<td><input id=\"usercontextId\" class=\"easyui-validatebox\" name=\"usercontextId\" />  \r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t\r\n");
      out.write("\t\t\t\t<tr align=\"right\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-search\" onclick=\"searchUserAndContextIdList();\">查询</a></td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" icon=\"icon-undo\" onclick=\"resetUserAndContextIdList();\">重置</a> </td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t\t<div region=\"center\" >\r\n");
      out.write("\t\t\t\t<table id=\"t_userAndContextId\"></table>\r\n");
      out.write("\t\t\t</div>\t\r\n");
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
