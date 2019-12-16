package org.apache.jsp.WEB_002dINF.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class createContext_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("\t\t<script type=\"text/javascript\">\r\n");
      out.write("\t\t $(function(){\r\n");
      out.write("\t\t\t\t$('#projectNo').combobox({    \r\n");
      out.write("\t\t\t\t    url:'projectNo.do?',    \r\n");
      out.write("\t\t\t\t    valueField:'project_id',    \r\n");
      out.write("\t\t\t\t    textField:'project_id'\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$('#role').combobox({    \r\n");
      out.write("\t\t\t\t    url:'role.do?',    \r\n");
      out.write("\t\t\t\t    valueField:'role',    \r\n");
      out.write("\t\t\t\t    textField:'role',\r\n");
      out.write("\t\t\t\t    editable:false\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t\t$('#organization').combobox({    \r\n");
      out.write("\t\t\t\t    url:'organization.do?',    \r\n");
      out.write("\t\t\t\t    valueField:'org_id',    \r\n");
      out.write("\t\t\t\t    textField:'org_id',\r\n");
      out.write("\t\t\t\t    editable:false\r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t//查询\r\n");
      out.write("\t\tvar searchpageNumber=1;\r\n");
      out.write("\t    var searchpageSize=10;\r\n");
      out.write("\t\tfunction searchUserBaseList() {\r\n");
      out.write("\t\t\tvar userName = $('#userName').val();\r\n");
      out.write("\t\t\tvar viewOrUpdateFullName = '';\r\n");
      out.write("\t\t\ttry{\r\n");
      out.write("\t\t\t$('#t_baseUser').datagrid({\r\n");
      out.write("\t\t\t\ttitle:'用户列表',\r\n");
      out.write("\t\t\t\twidth:800,\r\n");
      out.write("\t\t\t\theight:400,\r\n");
      out.write("\t\t\t\t//url:'json/baseUserList.json',\r\n");
      out.write("\t\t\t\t url:'queryUser.do?viewOrUpdateUserId='+userName+'&viewOrUpdateFullName='+viewOrUpdateFullName+'&pageNumber='+searchpageNumber+'&pageSize='+searchpageSize, //此处应为服务器请求数据，暂时读取json中的数据\r\n");
      out.write("\t\t\t\t//sortName: 'code',\r\n");
      out.write("\t\t\t\tfitColumns: true,\r\n");
      out.write("\t\t\t\tsortOrder: 'desc',\r\n");
      out.write("\t\t\t\tremoteSort: false,\r\n");
      out.write("\t\t\t\tpageSize:10,   //表格中每页显示的行数\r\n");
      out.write("\t\t\t\tpageList:[10,20,30],\r\n");
      out.write("\t\t\t\trownumbers: true,  //行号 \r\n");
      out.write("\t\t\t\t//idField:'contextId',\r\n");
      out.write("\t            columns:[[\r\n");
      out.write("                       {field:'userId',title:'用户名',width:100}, \r\n");
      out.write("\t\t               {field:'fullName',title:'全名',width:100},\r\n");
      out.write("\t\t               {field:'phone',title:'电话',width:100},\r\n");
      out.write("\t\t               {field:'address',title:'地址',width:100},\r\n");
      out.write("\t\t               {field:'email',title:'邮箱',width:100},\r\n");
      out.write("\t\t               {field:'organization',title:'组织',width:100}\r\n");
      out.write("\t\t\t    ]],  \r\n");
      out.write("\t\t\t\tpagination:true,\r\n");
      out.write("\t\t\t\trownumbers:true\r\n");
      out.write("\t\t\t});\r\n");
      out.write("\t\t\tvar p = $('#t_baseUser').datagrid('getPager');\r\n");
      out.write("\t\t\t$(p).pagination({\r\n");
      out.write("\t\t\t\tshowRefresh: false,\r\n");
      out.write("\t\t\t\tonSelectPage: function(pageNumber, pageSize) {\r\n");
      out.write("\t\t\t\t\tsearchpageNumber=pageNumber;\r\n");
      out.write("\t\t\t\t\tsearchpageSize=pageSize;\r\n");
      out.write("\t\t\t  $('#t_baseUser').datagrid('options').url ='queryUser.do?viewOrUpdateUserId='+$('#userName').val()+'&viewOrUpdateFullName='+viewOrUpdateFullName+'&pageNumber='+pageNumber+'&pageSize='+pageSize;\r\n");
      out.write("\t\t\t  $(\"#t_baseUser\").datagrid('reload');\r\n");
      out.write("\t\t\t\t}\r\n");
      out.write("\t\t\t}); \r\n");
      out.write("\t\t\t//\r\n");
      out.write("\t\t\t$(\"#okButton\").show();\r\n");
      out.write("\t\t\t}catch(e){\r\n");
      out.write("\t\t\t\talert(e.message);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//查询\r\n");
      out.write("\t\tfunction addUserBaseList() {\r\n");
      out.write("\t\t\t\t$('#dlg').dialog({    \r\n");
      out.write("\t\t\t\t    title: '用户/上下文查询条件',    \r\n");
      out.write("\t\t\t\t    width: 800,  \r\n");
      out.write("\t\t            height: 600,  \r\n");
      out.write("\t\t            closed: false,  \r\n");
      out.write("\t\t            cache: false,    \r\n");
      out.write("\t\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t\t    href: 'userManage.do?method=addBaseUser',  \r\n");
      out.write("\t\t\t\t    modal: true\r\n");
      out.write("\t\t\t\t    \r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t$('#dlg').dialog('refresh', 'userManage.do?method=addBaseUser');  \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//删除查询\r\n");
      out.write("\t\tfunction delUserBaseList() {\r\n");
      out.write("\t\t\t\t$('#dlg').dialog({    \r\n");
      out.write("\t\t\t\t    title: '用户/上下文查询条件',    \r\n");
      out.write("\t\t\t\t    width: 800,  \r\n");
      out.write("\t\t            height: 600,  \r\n");
      out.write("\t\t            closed: false,  \r\n");
      out.write("\t\t            cache: false,    \r\n");
      out.write("\t\t\t\t    cache: false,    \r\n");
      out.write("\t\t\t\t    href: 'userManage.do?method=delBaseUser',  \r\n");
      out.write("\t\t\t\t    modal: true\r\n");
      out.write("\t\t\t\t    \r\n");
      out.write("\t\t\t\t});\r\n");
      out.write("\t\t\t$('#dlg').dialog('refresh', 'userManage.do?method=delBaseUser');  \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//查询确定 \r\n");
      out.write("\t\tfunction okUserBaseList() {\r\n");
      out.write("\t\t\tdebugger;\r\n");
      out.write("\t\t\tvar rows = $('#t_baseUser').datagrid('getSelections');\r\n");
      out.write("\t\t\tvar userIds = [];\r\n");
      out.write("\t\t\tfor (var i = 0; i < rows.length; i++) {\r\n");
      out.write("\t\t\t\tuserIds.push(rows[i].userId);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar id=userIds.join(',');\r\n");
      out.write("\t\t\t$('#users').val(id);\r\n");
      out.write("\t\t\tsetTimeout(function(){\r\n");
      out.write("        \t\t$('#dlg').dialog('close');\r\n");
      out.write("        \t}, 300);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//删除人员查询确定 \r\n");
      out.write("\t\tfunction delokUserBaseList() {\r\n");
      out.write("\t\t\tdebugger;\r\n");
      out.write("\t\t\tvar rows = $('#t_baseUser').datagrid('getSelections');\r\n");
      out.write("\t\t\tvar userIds = [];\r\n");
      out.write("\t\t\tfor (var i = 0; i < rows.length; i++) {\r\n");
      out.write("\t\t\t\tuserIds.push(rows[i].userId);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar id=userIds.join(',');\r\n");
      out.write("\t\t\t$('#delusers').val(id);\r\n");
      out.write("\t\t\tsetTimeout(function(){\r\n");
      out.write("        \t\t$('#dlg').dialog('close');\r\n");
      out.write("        \t}, 300);\r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t\r\n");
      out.write("\t\t//重置\r\n");
      out.write("\t\tfunction resetUserBaseList() {\r\n");
      out.write("\t\t\t//$('#createContextUserForm').form('clear'); \r\n");
      out.write("\t\t\t$('#userName').val(''); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//重置\r\n");
      out.write("\t\tfunction createContextReset() {\r\n");
      out.write("\t\t\t$('#createContextform').form('clear'); \r\n");
      out.write("\t\t}\r\n");
      out.write("\t\t//确定\r\n");
      out.write("\t\tfunction createContextSave() {\r\n");
      out.write("\t\t\tvar projectNo = $('#projectNo').combobox('getValue');\r\n");
      out.write("\t\t\tvar role = $('#role').combobox('getValue');\r\n");
      out.write("\t\t\tvar organization = $('#organization').combobox('getValue');\r\n");
      out.write("\t\t\tvar users = $('#users').val();\r\n");
      out.write("\t\t\tvar delusers = $('#delusers').val();\r\n");
      out.write("\t\t\tif(!isEmpty(projectNo)){\r\n");
      out.write("\t\t\t\ttipMessage(\"请选择项目编号\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tif(!isEmpty(role)){\r\n");
      out.write("\t\t\t\ttipMessage(\"请选择角色\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\t\tif(!isEmpty(organization)){\r\n");
      out.write("\t\t\t\ttipMessage(\"请选择组织\");\r\n");
      out.write("\t\t\t\treturn false;\r\n");
      out.write("\t\t\t} \r\n");
      out.write("\t\t     $.ajax({\r\n");
      out.write("\t \t            //几个参数需要注意一下\r\n");
      out.write("\t \t                type: \"POST\",//方法类型\r\n");
      out.write("\t \t                url: \"createContext.do?\" ,//url\r\n");
      out.write("\t \t                data: {\r\n");
      out.write("\t \t               \t'projectNo':projectNo,\r\n");
      out.write("\t \t \t\t\t\t'role':role,\r\n");
      out.write("\t \t \t\t\t\t'organization':organization,\r\n");
      out.write("\t \t \t\t\t\t'users':users,\r\n");
      out.write("\t \t \t\t\t\t'delusers':delusers\r\n");
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
      out.write("\t\t     \r\n");
      out.write("\t\t}\r\n");
      out.write("\t</script>\r\n");
      out.write("\t<div id=\"createContextdiv\"\t align=\"center\"\r\n");
      out.write("\t\tstyle=\"width: 100%; height: 100%\" title=\"用户新增\">\r\n");
      out.write("\t\t<form id=\"createContextform\" >\r\n");
      out.write("\t\t\t<table  >\r\n");
      out.write("\t\t\t\t<tr style=\"height:10px\">\r\n");
      out.write("\t\t\t\t\t<td>*项目编号:</td>\r\n");
      out.write("\t\t\t\t\t<td><!-- <select id=\"projectNo\" class=\"easyui-combobox\" name=\"projectNo\" >   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"DEFAULT\">DEFAULT</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"NEWPROJ\">NEWPROJ</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"TSTPRJ2\">TSTPRJ2</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"TSTPRJ\">TSTPRJ</option>   \r\n");
      out.write("\t\t\t\t\t\t</select> -->\r\n");
      out.write("\t\t\t\t\t\t<input id=projectNo name=\"projectNo\" >  \r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr style=\"height:10px\">\r\n");
      out.write("\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("  \t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr >\r\n");
      out.write("\t\t\t\t\t<td>*角色:</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t\t<input id=role name=\"role\" >  \r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr style=\"height:10px\">\r\n");
      out.write("\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("  \t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>*组织:</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<!-- <select id=\"organization\" class=\"easyui-combobox\" name=\"organization\" >   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"MANUF\">MANUF</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"DESIGN\">DESIGN</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"GLAWAY\">GLAWAY</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"GLAWAY2\">GLAWAY2</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"1\">1</option>   \r\n");
      out.write("\t\t\t\t\t\t    <option value=\"TST\">TST</option>   \r\n");
      out.write("\t\t\t\t\t\t</select> -->\r\n");
      out.write("\t\t\t\t\t\t<input id=\"organization\" name=\"organization\" >  \r\n");
      out.write("\t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr style=\"height:10px\">\r\n");
      out.write("\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("  \t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>增加人员:</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input class=\"easyui-textbox\" name=\"users\" id=\"users\" data-options=\"multiline:true\" style=\"height:50px;width:155px\"/> \r\n");
      out.write("  \t\t\t\t\t</td>\r\n");
      out.write("  \t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\"  icon=\"icon-add\"  onclick=\"addUserBaseList();\">添加</a>\r\n");
      out.write("  \t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr>\r\n");
      out.write("\t\t\t\t\t<td>删除人员:</td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<input class=\"easyui-textbox\" name=\"delusers\" id=\"delusers\" data-options=\"multiline:true\" style=\"height:50px;width:155px\"/> \r\n");
      out.write("  \t\t\t\t\t</td>\r\n");
      out.write("  \t\t\t\t\t<td>\r\n");
      out.write("\t\t\t\t\t<a href=\"javascript:void(0)\" class=\"easyui-linkbutton\"  icon=\"icon-add\"  onclick=\"delUserBaseList();\">添加</a>\r\n");
      out.write("  \t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr style=\"height:30px\">\r\n");
      out.write("\t\t\t\t\t<td></td>\r\n");
      out.write("\t\t\t\t\t<td>\r\n");
      out.write("  \t\t\t\t\t</td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t\t<tr align=\"right\">\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" onclick=\"createContextSave();\" icon=\"icon-ok\">确定</a></td>\r\n");
      out.write("\t\t\t\t\t<td colspan=\"1\"><a class=\"easyui-linkbutton\" onclick=\"createContextReset();\" icon=\"icon-undo\">重置</a> </td>\r\n");
      out.write("\t\t\t\t</tr>\r\n");
      out.write("\t\t\t</table>\r\n");
      out.write("\t\t</form>\r\n");
      out.write("\t</div>\r\n");
      out.write("\t\t\t<div id=\"dlg\" class=\"easyui-dialog\" title=\"用户/上下文查询条件\" style=\"width:800px;height:800px;padding:10px\" closed=\"true\"\r\n");
      out.write("\t\t\tdata-options=\"\r\n");
      out.write("\t\t\t\ticonCls: 'icon-save',\r\n");
      out.write("\t\t\t\tmodal:false,\r\n");
      out.write("\t\t\t\tclosed:true\r\n");
      out.write("\t\t\t\"> \r\n");
      out.write("\t\t\t</div>\r\n");
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
