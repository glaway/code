package com.glaway.ids.functionManage.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.glaway.ids.functionManage.dao.FunctionManDao;
import com.glaway.ids.functionManage.properties.CommonProperties;
import com.glaway.ids.functionManage.util.FunctionUtil;
import com.glaway.ids.functionManage.util.ResponseJsonUtils;
import com.glaway.ids.functionManage.util.WSCallVpmServices;


@Controller
public class FunctionManController {

	/*
	 * static{ ServletRequestAttributes servletRequestAttributes =
	 * (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
	 * HttpServletRequest request = servletRequestAttributes.getRequest();
	 * application = request.getSession().getServletContext(); }
	 * 
	 * public static ServletContext application;
	 */
	
	public Map<String, String> userMap = new HashMap<String, String>();

	/**
	 * 生成日志<br>
	 */
	private static final Log log = LogFactory
			.getLog(FunctionManController.class);

	public static List<Map<String, String>> alluserData = new ArrayList<Map<String, String>>();// 用户数据
	public static List<Map<String, String>> allpermissionsData = new ArrayList<Map<String, String>>();// 权限数据
	public static List<Map<String, String>> allprojectData = new ArrayList<Map<String, String>>();// 项目数据
	public static List<Map<String, String>> allorginaztionData = new ArrayList<Map<String, String>>();// 组织数据
	public static List<Map<String, String>> allcontextIdData = new ArrayList<Map<String, String>>();// 上下文数据
	public static List<Map<String, String>> allroleData = new ArrayList<Map<String, String>>();// 角色数据
	public static List<Map<String, String>> alluserAndContextData = new ArrayList<Map<String, String>>();// 用户对应的上下文数据
	public static List<String> allLogFileData = new ArrayList<String>();// 日志数据

	private String logFileStr1;

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		request.getSession().invalidate();
		return "login";
	}

	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
		request.getSession().invalidate();
		functionManDao.insertLog(getDateString(), userMap.get("userId")
				.toString(), "退出", "退出成功");
		return "login";
	}

	@Autowired
	private FunctionManDao functionManDao;

	/**
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("finally")
	@RequestMapping("/checkUserLogin")
	@ResponseBody
	public void checkUserLogin(HttpServletRequest request,
			HttpServletResponse response) {
		String message = "登录失败";
		String level = "";
		String id_userId = request.getParameter("id_userId");
		String id_password = request.getParameter("id_password");
		String status = "false";
		try {
			List<String> userId = new ArrayList<String>();
			if ("gladmin".equals(id_userId)) {
				if ("gladmin".equals(id_password)) {
					message = "登录成功";
					level = "0";
					status = "true";
				} else {
					message = "用户名与密码不匹配";
				}
			} else {
				String userAndPwdPath = CommonProperties
						.getStringProperty("userAndPwdPath");// 模拟数据库文件路径
				String userAndPwdPathFileNametextName = "userAndPwdFile";
				String userAndPwdPathFileName = userAndPwdPath
						+ userAndPwdPathFileNametextName;
				Map<String, String> userData = new HashMap<String, String>();// 读取模拟数据库文件用户
				queryUserDataByPwd(userData, userAndPwdPathFileName);// 用户数据
				Iterator<Map.Entry<String, String>> entries = userData
						.entrySet().iterator();
				while (entries.hasNext()) {
					Map.Entry<String, String> entry = entries.next();
					if (!entry.getKey().equals(id_userId)) {
						message = "用户名不存在";
					} else {
						message = "用户名存在";
						break;
					}
				}
				Iterator<Map.Entry<String, String>> newentries = userData
						.entrySet().iterator();
				if (message.equals("用户名存在")) {
					while (newentries.hasNext()) {
						Map.Entry<String, String> entry = newentries.next();
						if (entry.getKey().equals(id_userId)
								&& userData.get(entry.getKey().toString())
										.split("\\s+")[0].trim().equals(
										id_password)) {
							message = "登录成功";
							level = userData.get(entry.getKey().toString())
									.split("\\s+")[1].trim();
							break;
						} else {
							message = "用户名与密码不匹配";
						}
					}

				}
			}
			if (message.equals("登录成功")) {
				request.getSession().setAttribute("id_userId", id_userId);
				request.getSession().setAttribute("id_password", id_password);
				request.getSession().setMaxInactiveInterval(500);
				/*
				 * alluserData = new ArrayList<Map<String, String>>();
				 * allpermissionsData = new ArrayList<Map<String, String>>();
				 * allprojectData = new ArrayList<Map<String, String>>();
				 * allorginaztionData = new ArrayList<Map<String, String>>();
				 * allcontextIdData = new ArrayList<Map<String, String>>();
				 * allroleData = new ArrayList<Map<String, String>>();
				 * alluserAndContextData = new ArrayList<Map<String, String>>();
				 * request.getSession().setAttribute("id_userId", id_userId);
				 * request.getSession().setAttribute("id_password",
				 * id_password);
				 * request.getSession().setMaxInactiveInterval(500); String
				 * textPath = CommonProperties
				 * .getStringProperty("testFilePath"); String textName =
				 * "PO_query"; String fileName = textPath + textName;
				 * WSCallVpmServices wSCallVpmServices = new
				 * WSCallVpmServices();
				 * wSCallVpmServices.callVpmServices("export", fileName);
				 * System.out.println(fileName); File queryFile = new
				 * File(fileName); if (!queryFile.exists()) { try {
				 * Thread.sleep(1000 * 6); } catch (Exception e) {
				 * e.printStackTrace(); } } queryDataByType(alluserData, "user",
				 * queryFile); queryDataByType(allpermissionsData,
				 * "permissions", queryFile); queryDataByType(allprojectData,
				 * "project", queryFile); queryDataByType(allorginaztionData,
				 * "orginaztion", queryFile); queryDataByType(allcontextIdData,
				 * "contextId", queryFile); queryDataByType(allroleData, "role",
				 * queryFile);
				 */
			}
			response.setContentType("text/html;charset=utf-8");
		} catch (Exception e) {
			message = "登录失败";
			e.printStackTrace();
		} finally {
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("message", message);
			data.put("level", level);
			ResponseJsonUtils.json(response, data);
			functionManDao.insertLog(getDateString(), id_userId, "登陆", message);
			// application.setAttribute("userId", id_userId);
			userMap.put("userId", id_userId);
		}
	}

	@RequestMapping("/export_po")
	@ResponseBody
	public void export_po(HttpServletRequest request,
			HttpServletResponse response) {
		Map<String,String> result = new HashMap<String, String>();
		result.put("message", "失败");
		try {
			alluserData = new ArrayList<Map<String, String>>();
			allpermissionsData = new ArrayList<Map<String, String>>();
			allprojectData = new ArrayList<Map<String, String>>();
			allorginaztionData = new ArrayList<Map<String, String>>();
			allcontextIdData = new ArrayList<Map<String, String>>();
			allroleData = new ArrayList<Map<String, String>>();
			alluserAndContextData = new ArrayList<Map<String, String>>();
			String textPath = CommonProperties
					.getStringProperty("testFilePath");
			String textName = "PO_query";
			String fileName = textPath + textName;
			WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
			wSCallVpmServices.callVpmServices("export", fileName);
			System.out.println(fileName);
			File queryFile = new File(fileName);
			if (!queryFile.exists()) {
				Thread.sleep(1000 * 6);
			}
			queryDataByType(alluserData, "user", queryFile);
			queryDataByType(allpermissionsData, "permissions", queryFile);
			queryDataByType(allprojectData, "project", queryFile);
			queryDataByType(allorginaztionData, "orginaztion", queryFile);
			queryDataByType(allcontextIdData, "contextId", queryFile);
			queryDataByType(allroleData, "role", queryFile);
			result.put("message", "成功");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ResponseJsonUtils.json(response, result);
	}

	private String getDateString() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
	}

	/**
	 * 查询模拟数据库用户
	 * 
	 * @param userData
	 * @param string
	 * @param file
	 */
	private void queryUserDataByPwd(Map<String, String> userData,
			String fileName) {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					file), "GBK");
			reader = new BufferedReader(isr);
			String tempString = null;
			// 一次读入一行，直到读入null为文件结束
			while ((tempString = reader.readLine()) != null) {
				if (!"".equals(tempString) && tempString != null) {
					userData.put(
							tempString.split(";")[0].trim(),
							tempString.split(";")[1].trim() + " "
									+ tempString.split(";")[2].trim());
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	private List<Map<String, String>> queryDataByType(
			List<Map<String, String>> dataMaplist, String type, File file) {
		List<Map<String, String>> userAndContectIdData = new ArrayList<Map<String, String>>();// 用户以及对于的上下文数据
		Map<String, String> map = new HashMap<String, String>();
		if ("user".equals(type)) {
			List<String> datalist = new ArrayList<String>(); // 用户数据
			map.put("type", "1");// type为3的话为权限查询
			try {
				readFileByLines(file.getPath(), map, datalist);
				System.out.println("tempStringsize----------------"
						+ datalist.size());
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// 解析成Map到前台
			for (String data : datalist) {
				Map<String, String> mapVo = new HashMap<String, String>();
				String[] tempStringArray = data.split(";");
				if (tempStringArray.length != 8) {
					continue;
				}
				if (tempStringArray[7].trim().toString().equals("-1")) {// -1表示删除
					continue;
				}
				String fullname = "$".equals(tempStringArray[2]) ? ""
						: tempStringArray[2].toString()
								+ (("$".equals(tempStringArray[3]) ? ""
										: tempStringArray[3].toString()));
				try {
					String iso = new String(fullname.getBytes(), "ISO-8859-1");
					mapVo.put("userId",
							tempStringArray[0].split("\\s+")[1].toString());
					mapVo.put("organization",
							"$".equals(tempStringArray[1]) ? ""
									: tempStringArray[1].toString());
					mapVo.put("fullName", iso);
					mapVo.put("phone", "$".equals(tempStringArray[4]) ? ""
							: tempStringArray[4].toString());
					mapVo.put("address", "$".equals(tempStringArray[5]) ? ""
							: tempStringArray[5].toString());
					mapVo.put("email", "$".equals(tempStringArray[6]) ? ""
							: tempStringArray[6].toString());
					mapVo.put("level", tempStringArray[7].toString());
					dataMaplist.add(mapVo);
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}
			}
		} else if ("permissions".equals(type)) {
			List<String> datalist = new ArrayList<String>(); // 权限数据
			map.put("type", "3");// type为3的话为权限查询
			try {
				readFileByLines(file.getPath(), map, datalist);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (String data : datalist) {
				Map<String, String> mapVo = new HashMap<String, String>();
				String[] tempStringArray = data.split(";");
				int length = tempStringArray.length;
				mapVo.put("contextId", tempStringArray[1].split("=")[1].trim());
				if (tempStringArray[2].trim().contains("VPM")
						|| tempStringArray[2].trim().contains("VPMAdmin")) {
					String[] typeAndOperate = tempStringArray[2].trim().split(
							"=");
					mapVo.put("type", typeAndOperate[1].split("\\.")[0]);
					mapVo.put("operationGroup",
							typeAndOperate[1].split("\\.")[1]);
					mapVo.put("dataGroup", "");
				} else {
					String[] typeAndOperate = tempStringArray[2].trim().split(
							"=");
					mapVo.put("type", "ProcessGroup");
					mapVo.put("operationGroup", typeAndOperate[1]);
					if (data.contains("GLOBAL")) {
						mapVo.put("dataGroup", tempStringArray[length - 1]);
					} else {
						mapVo.put("dataGroup", "");
					}
				}
				dataMaplist.add(mapVo);
			}

		} else if ("project".equals(type)) {
			List<String> datalist = new ArrayList<String>(); // 项目数据
			map.put("type", "4");// type为4的话为项目查询
			try {
				readFileByLines(file.getPath(), map, datalist);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (String data : datalist) {
				Map<String, String> mapVo = new HashMap<String, String>();
				String[] tempStringArray = data.split(";");
				mapVo.put("project_name",
						tempStringArray[0].split("\\s+")[1].toString());
				dataMaplist.add(mapVo);
			}

		} else if ("orginaztion".equals(type)) {
			List<String> datalist = new ArrayList<String>(); // 组织数据
			map.put("type", "5");// type为5的话为组织查询
			try {
				readFileByLines(file.getPath(), map, datalist);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (String data : datalist) {
				Map<String, String> mapVo = new HashMap<String, String>();
				String[] tempStringArray = data.split(";");
				mapVo.put("org_id",
						tempStringArray[0].split("\\s+")[1].toString());
				dataMaplist.add(mapVo);
			}

		} else if ("contextId".equals(type)) {
			List<String> datalist = new ArrayList<String>(); // 上下文数据
			map.put("type", "6");// type为6的话为上下文查询
			try {
				readFileByLines(file.getPath(), map, datalist);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (String data : datalist) {
				Map<String, String> mapVo = new HashMap<String, String>();
				String[] tempStringArray = data.split(";");
				mapVo.put("contextId",
						tempStringArray[0].split("\\s+")[1].toString() + "."
								+ tempStringArray[1].trim() + "."
								+ tempStringArray[2].trim());
				dataMaplist.add(mapVo);
				List<String> userData = new ArrayList<String>();// 用户以及对于的上下文数据
				readUserAndContectIdData(file.getPath(), userData, data);
				for (String userId : userData) {
					Map<String, String> newmapVo = new HashMap<String, String>();
					newmapVo.put("userId", userId);
					newmapVo.put("contextId",
							tempStringArray[0].split("\\s+")[1].toString()
									+ "." + tempStringArray[1].trim() + "."
									+ tempStringArray[2].trim());
					alluserAndContextData.add(newmapVo);
				}

			}
		} else if ("role".equals(type)) {
			List<String> datalist = new ArrayList<String>(); // 角色数据
			map.put("type", "7");// type为6的话为角色查询
			try {
				readFileByLines(file.getPath(), map, datalist);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			for (String data : datalist) {
				Map<String, String> mapVo = new HashMap<String, String>();
				String[] tempStringArray = data.split(";");
				mapVo.put("role",
						tempStringArray[0].split("\\s+")[1].toString());
				dataMaplist.add(mapVo);
			}
		}
		return userAndContectIdData;
	}

	private void readUserAndContectIdData(String fileName,
			List<String> userAndContectIdData, String data) {
		File file = new File(fileName);
		BufferedReader reader = null;
		String tempString = null;
		try {
			List<String> tempList = new ArrayList<String>();
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					file), "GBK");
			reader = new BufferedReader(isr);
			int i = 0;
			int y = 0;
			String halfString = "";
			do {
				tempString = reader.readLine();
				if (tempString != null && !"null".equals(tempString)) {
					if (y == i && !"".equals(halfString)) {
						tempList.remove(halfString);
						tempString = halfString + tempString;
					}
					i++;
					tempList.add(tempString);
					halfString = tempString;
				} else {
					y = i;
					tempString = "a";
				}
			} while (!tempString.contains("End of export file"));

			//
			String start = "0";
			for (String string : tempList) {
				if (string.equals(data)) {
					start = "1";
				}
				if (start.equals("1") && string.contains("+PERSON")) {
					userAndContectIdData.add(string.split("\\s+")[1].trim()
							.toString());
				}
				if ((string.contains("+MASK") && start.equals("1"))) {
					break;
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}

	}

	@RequestMapping("/organization")
	@ResponseBody
	public void organization(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List jsonList = new ArrayList();
		for (Map<String, String> map : allorginaztionData) {
			JSONObject obj = new JSONObject();
			obj.put("org_id", map.get("org_id"));
			jsonList.add(obj);
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String jsonResult = JSON.toJSONString(jsonList);
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		out.flush();
		out.close();
	}

	@RequestMapping("/projectNo")
	@ResponseBody
	public void projectNo(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List jsonList = new ArrayList();
		for (Map<String, String> map : allprojectData) {
			JSONObject obj = new JSONObject();
			obj.put("project_id", map.get("project_id"));
			jsonList.add(obj);
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String jsonResult = JSON.toJSONString(jsonList);
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		out.flush();
		out.close();
	}

	@RequestMapping("/role")
	@ResponseBody
	public void role(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List jsonList = new ArrayList();
		for (Map<String, String> map : allroleData) {
			JSONObject obj = new JSONObject();
			obj.put("role", map.get("role"));
			jsonList.add(obj);
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String jsonResult = JSON.toJSONString(jsonList);
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		out.flush();
		out.close();
	}

	@RequestMapping("/addPermission_contextId")
	@ResponseBody
	public void addPermission_contextId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		List jsonList = new ArrayList();
		for (Map<String, String> map : allpermissionsData) {
			JSONObject obj = new JSONObject();
			obj.put("contextId", map.get("contextId"));
			jsonList.add(obj);
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String jsonResult = JSON.toJSONString(jsonList);
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		out.flush();
		out.close();
	}

	@RequestMapping("/level")
	@ResponseBody
	public void level(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		List jsonList = new ArrayList();
		for (int i = 0; i < 4; i++) {
			JSONObject obj = new JSONObject();
			obj.put("id", i + 1);
			if (i + 1 == 1) {
				obj.put("name", "系统管理员");
			} else if (i + 1 == 2) {
				obj.put("name", "安全管理员");
			} else if (i + 1 == 3) {
				obj.put("name", "安全管理员");
			} else if (i + 1 == 4) {
				obj.put("name", "普通用户");
			}
			jsonList.add(obj);
		}
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		String jsonResult = JSON.toJSONString(jsonList);
		PrintWriter out = response.getWriter();
		out.print(jsonResult);
		out.flush();
		out.close();
	}

	@RequestMapping("/home")
	public String home(HttpServletRequest request) {
		String userId = (String) request.getSession().getAttribute("id_userId");
		if ("".equals(userId) || userId == null) {
			request.getSession().invalidate();
			return "login";
		}
		String level = request.getParameter("level");
		// 用户管理vo
		List<String> userVOList = new ArrayList<String>();
		userVOList.add("用户创建");
		userVOList.add("用户查询/修改");
		userVOList.add("项目查询");
		userVOList.add("创建项目");
		userVOList.add("组织查询");
		userVOList.add("创建组织");
		// 安全管理vo
		List<String> secuVOList = new ArrayList<String>();
		secuVOList.add("创建上下文");
		secuVOList.add("权限查询/修改");
		secuVOList.add("添加权限");
		secuVOList.add("用户行为审计");
		secuVOList.add("用户权限");
		// 审计管理vo
		List<String> auditVOList = new ArrayList<String>();
		auditVOList.add("管理员行为审计");
		secuVOList.add("审计查询");
		if ("1".equals(level)) {
			request.setAttribute("userVOList", userVOList);
		} else if ("2".equals(level)) {
			request.setAttribute("secuVOList", secuVOList);
		} else if ("3".equals(level)) {
			request.setAttribute("auditVOList", auditVOList);
		} else if ("0".equals(level)) {
			request.setAttribute("userVOList", userVOList);
			request.setAttribute("secuVOList", secuVOList);
			request.setAttribute("auditVOList", auditVOList);
		} else {
			// 用户管理vo
			List<String> editUserVOList = new ArrayList<String>();
			editUserVOList.add("修改密码");
			request.setAttribute("userVOList", editUserVOList);
			request.setAttribute("id_userId", request.getParameter("id_userId"));
		}

		return "home";
	}

	@RequestMapping("/editPwd")
	public String editPwd(HttpServletRequest request) {
		String id_userId = request.getParameter("id_userId");
		// 用户管理vo
		List<String> editUserVOList = new ArrayList<String>();
		editUserVOList.add("修改密码");
		request.setAttribute("userVOList", editUserVOList);
		// byte[] ebyte=new BASE64Decoder().de
		request.setAttribute("id_userId", request.getParameter("id_userId"));
		request.setAttribute("exit", "0");
		return "home";
	}

	@RequestMapping("/userManage")
	public String userManage(HttpServletRequest request) {
		String method = request.getParameter("method");
		String viewPage = "";
		if (!FunctionUtil.isEmpty(method)) {
			if ("createUser".equals(method)) {// 1创建用户
				viewPage = "createUser";
			} else if ("viewOrUpdate".equals(method)) {// 2查询/修改
				viewPage = "viewOrUpdate";
			} else if ("editPwd".equals(method)) {// 修改
				viewPage = "editPwd";
				request.setAttribute("id_userId",
						request.getParameter("id_userId"));
			}else if("selectProj".equals(method)){
				viewPage = "selectProj";
			} else if ("createProj".equals(method)) {// 3创建项目
				viewPage = "createProj";
			} else if("selectOra".equals(method)){
				viewPage = "selectOra";
			}
			else if ("createLogicOrga".equals(method)) {// 4创建逻辑组织
				viewPage = "createLogicOrga";
			} else if ("updateUserInfo".equals(method)) {// 5、2中的修改页面跳转
				viewPage = "updateUserInfo";
				String userId = request.getParameter("userId");// 用户名（及ID）
				String level = request.getParameter("level");// 密级
				request.setAttribute("userId", userId);
				request.setAttribute("level", level);
			} else if ("createContext".equals(method)) {// 创建上下文
				viewPage = "createContext";
			} else if ("addBaseUser".equals(method)) {// 创建上下文添加用户
				viewPage = "createContext-addUser";
			} else if ("delBaseUser".equals(method)) {// 创建上下文添加用户
				viewPage = "createContext-delUser";
			} else if ("queryOrUpdate".equals(method)) {// 权限查询与修改
				viewPage = "queryOrUpdate";
			} else if ("addPermissions".equals(method)) {// 添加权限
				viewPage = "addPermissions";
			} else if ("userAndContextId".equals(method)) {// 创建上下文添加用户
				viewPage = "userAndContextId";
			} else if ("userStatistics".equals(method)) {// 用户审计统计
				viewPage = "userStatistics";
			} else if ("secStatistics".equals(method)) {// 安全管理员用户审计
				viewPage = "secStatistics";
			} else if ("logFileQuery".equals(method)) {// 用户审计日志查询
				viewPage = "logFileQuery";
			}
		}
		return viewPage;
	}

	@RequestMapping("/createUser")
	@ResponseBody
	public void createUser(HttpServletRequest request,
			HttpServletResponse response) {
		String message = "创建成功";
		try {
			String userId = request.getParameter("userId");
			String firstName = "".equals(request.getParameter("firstName")) ? "$"
					: request.getParameter("firstName");
			String lastName = "".equals(request.getParameter("lastName")) ? "$"
					: request.getParameter("lastName");
			String phone = "".equals(request.getParameter("phone")) ? "$"
					: request.getParameter("phone");
			String address = "".equals(request.getParameter("address")) ? "$"
					: request.getParameter("address");
			String email = "".equals(request.getParameter("email")) ? "$"
					: request.getParameter("email");
			String organization = request.getParameter("organization");
			String user_level = "0";// 普通用户
			for (int i = 0; i < alluserData.size(); i++) {
				if (alluserData.get(i).get("userId").trim()
						.equals(userId.trim())) {
					alluserData.remove(i);
				}
			}
			Map<String, String> createuserMap = new HashMap<String, String>();
			createuserMap.put("userId", request.getParameter("userId"));
			createuserMap.put("organization",
					request.getParameter("organization"));
			createuserMap.put("fullName", request.getParameter("firstName")
					+ request.getParameter("lastName"));
			createuserMap.put("phone", request.getParameter("phone"));
			createuserMap.put("address", request.getParameter("address"));
			createuserMap.put("email", request.getParameter("email"));
			alluserData.add(createuserMap);
			// *PERSON
			// <user_id>;<org_id>;<FirstName>;<LastName>;<Phone>;<Address>;<Email>;$
			// //为空则用$代替
			String content = "*PERSON " + userId + ";" + organization + ";"
					+ firstName + ";" + lastName + ";" + phone + ";" + address
					+ ";" + email + ";" + user_level;
			String textPath = CommonProperties
					.getStringProperty("testFilePath");
			String textName = "PO_create";
			String fileName = textPath + textName;
			writeText(fileName, content, "import");// 创建
			// 调用
			WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
			boolean success = wSCallVpmServices.callVpmServices("", fileName);
			wSCallVpmServices.createUser(userId);// 创建系统用户
		} catch (Exception e) {
			message = "创建失败";
			e.printStackTrace();
		} finally {
			String str = "{\"status\":\"ok\" , \"message\":\"" + message
					+ "!\"}";
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("-----创建用户-----");
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "用户创建", message);
		}
	}

	@RequestMapping("/updateUserPwd")
	@ResponseBody
	public void updateUserPwd(HttpServletRequest request,
			HttpServletResponse response) {
		String j = new String();
		String message = "修改成功";
		try {
			String userId = request.getParameter("userId");
			String dialogPwd2 = request.getParameter("dialogPwd2");
			// 调用
			WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
			wSCallVpmServices.updateUserPwd(userId, dialogPwd2);
			response.setContentType("text/html;charset=utf-8");
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		} finally {
			String str = "{\"status\":\"ok\" , \"message\":\"" + message
					+ "!\"}";
			try {
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "修改密码", message);
		}
	}

	@RequestMapping("/editUserPwd")
	@ResponseBody
	public void editUserPwd(HttpServletRequest request,
			HttpServletResponse response) {
		String j = new String();
		String message = "修改成功";
		try {
			String editUserId = request.getParameter("editUserId");
			boolean ifexist = false;
			for (Map<String, String> map : alluserData) {
				if (editUserId.equals(map.get("userId"))) {
					ifexist = true;
					break;
				}
			}
			if (!ifexist) {
				message = "用户不存在";
			} else {
				String editPassword = request.getParameter("editPassword");
				String editPassword2 = request.getParameter("editPassword2");
				// *PERSON
				// <user_id>;<org_id>;<FirstName>;<LastName>;<Phone>;<Address>;<Email>;$
				// //为空则用$代替
				// 调用
				WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
				wSCallVpmServices.updateUserPwd(editUserId, editPassword);
			}
			response.setContentType("text/html;charset=utf-8");
		} catch (Exception e) {
			message = "修改失败";
			e.printStackTrace();
		} finally {
			String str = "{\"status\":\"ok\" , \"message\":\"" + message
					+ "!\"}";
			try {
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "重置密码", message);
		}
	}

	@RequestMapping("/cancellUser")
	@ResponseBody
	public void cancellUser(HttpServletRequest request,
			HttpServletResponse response) {
		String j = new String();
		String message = "注销成功";
		try {
			String userId = request.getParameter("userId");
			String temporg = "";
			for (int i = 0; i < alluserData.size(); i++) {
				if (alluserData.get(i).get("userId").trim()
						.equals(userId.trim())) {
					temporg = alluserData.get(i).get("organization").trim();
					alluserData.remove(i);
				}
			}
			// *PERSON
			// <user_id>;<org_id>;<FirstName>;<LastName>;<Phone>;<Address>;<Email>;$
			// //为空则用$代替
			String content = "*PERSON " + userId + ";"
					+ ("".equals(temporg) ? "$" : temporg) + ";$;$;$;$;$;-1";
			for (int i = 0; i < allcontextIdData.size(); i++) {// 所有的上下文删除该用户
				String contextIdData = (String) allcontextIdData.get(i).get(
						"contextId");
				String[] contextIddataArray = contextIdData.split("\\.");
				content = content + "\n" + "*CTX " + contextIddataArray[0]
						+ ";" + contextIddataArray[1] + ";"
						+ contextIddataArray[2];
				content = content + "\n" + "-PERSON " + userId;
			}
			String textPath = CommonProperties
					.getStringProperty("testFilePath");
			String textName = "PO_create";
			String fileName = textPath + textName;
			writeText(fileName, content, "import");// 创建
			// 调用
			WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
			boolean success = wSCallVpmServices.callVpmServices("", fileName);
			wSCallVpmServices.cancellUserPwd(userId);// 删除系统用户
			// wSCallVpmServices.cancellUserAndPwd(userId, "");
		} catch (Exception e) {
			message = "注销失败";
			e.printStackTrace();
		} finally {
			String str = "{\"status\":\"ok\" , \"message\":\"" + message
					+ "!\"}";
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "注销", message);
		}
	}

	// 写入文件
	private void writeText(String fileName, String text, String type) {
		FileWriter fw = null;
		try {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
			file.createNewFile();
			if ("import".equals(type)) {
				FileInputStream ins = new FileInputStream(new File(
						CommonProperties.getStringProperty("copyFilePath")));
				FileOutputStream out = new FileOutputStream(new File(fileName));
				byte[] b = new byte[1024];
				int n = 0;
				while ((n = ins.read(b)) != -1) {
					out.write(b, 0, n);
				}
				ins.close();
				out.close();
				fw = new FileWriter(file, true);
				fw.write("\n");
				fw.write(text);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fw != null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}

	// 删除文件
	@SuppressWarnings("unused")
	private void removeFile(String fileName) {

		try {
			File file = new File(fileName);
			if (file.exists())
				file.delete();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	@RequestMapping("/createProj")
	@ResponseBody
	public void createProj(HttpServletRequest request,
			HttpServletResponse response) {
		String j = new String();
		String message = "创建成功";
		try {
			String projectNumber = request.getParameter("projectNumber");
			Map<String, String> createprojectMap = new HashMap<String, String>();
			for (int i = 0; i < allprojectData.size(); i++) {
				if (allprojectData.get(i).get("project_id").trim()
						.equals(projectNumber.trim())) {
					allprojectData.remove(i);
				}
			}
			createprojectMap.put("project_id", projectNumber);
			allprojectData.add(createprojectMap);// 添加给全局变量用户查看
			// *PRJ <project_id>;$;$ //为空则用$代替
			String content = "*PRJ " + projectNumber + ";$;$";
			String textPath = CommonProperties
					.getStringProperty("testFilePath");
			String textName = "PO_create";
			String fileName = textPath + textName;
			writeText(fileName, content, "import");// 创建
			// 调用
			WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
			boolean success = wSCallVpmServices.callVpmServices("", fileName);
		} catch (Exception e) {
			message = "创建失败";
			e.printStackTrace();
		} finally {
			String str = "{\"status\":\"ok\" , \"message\":\"" + message
					+ "!\"}";
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "创建项目", message);
		}
	}

	/**
	 * 创建组织
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/createLogicOrga")
	@ResponseBody
	public void createLogicOrga(HttpServletRequest request,
			HttpServletResponse response) {
		String j = new String();
		String message = "创建成功";
		try {
			String orgaNumber = request.getParameter("orgaNumber");
			String parentOrgaNumber = request.getParameter("parentOrgaNumber");
			boolean ifadd = true;
			for (int i = 0; i < allorginaztionData.size(); i++) {
				if (allorginaztionData.get(i).get("org_id").equals(orgaNumber)) {
					ifadd = false;
					break;
				}
			}
			if (ifadd) {
				Map<String, String> orgMap = new HashMap<String, String>();
				orgMap.put("org_id", orgaNumber);
				allorginaztionData.add(orgMap);// 添加给全局变量用户查看
			}
			// *ORG <org_id>;<parent_org_id>;$;$;$ //为空则用$代替
			String content = "*ORG " + orgaNumber + ";" + parentOrgaNumber
					+ ";$;$;$";
			String textPath = CommonProperties
					.getStringProperty("testFilePath");
			String textName = "PO_create";
			String fileName = textPath + textName;
			writeText(fileName, content, "import");// 创建
			// 调用
			WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
			boolean success = wSCallVpmServices.callVpmServices("", fileName);
		} catch (Exception e) {
			message = "创建失败";
			e.printStackTrace();
		} finally {
			String str = "{\"status\":\"ok\" , \"message\":\"" + message
					+ "!\"}";
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "创建组织", message);
		}
	}

	/**
	 * @param request
	 * @param response
	 */
	@RequestMapping("/createContext")
	@ResponseBody
	public void createContext(HttpServletRequest request,
			HttpServletResponse response) {
		String j = new String();
		String message = "创建成功";
		try {
			String projectNo = request.getParameter("projectNo");
			String role = request.getParameter("role");
			String organization = request.getParameter("organization");
			String users = request.getParameter("users");
			String delusers = request.getParameter("delusers");
			boolean ifadd = true;
			for (int i = 0; i < allcontextIdData.size(); i++) {
				String oldContextId = role + "." + organization + "."
						+ projectNo;
				if (allcontextIdData.get(i).get("contextId")
						.equals(oldContextId)) {
					ifadd = false;
					break;
				}
			}
			if (ifadd) {
				Map<String, String> contextMap = new HashMap<String, String>();
				contextMap.put("contextId", role + "." + organization + "."
						+ projectNo);
				allcontextIdData.add(contextMap);// 添加给全局变量用户查看
			}
			// *CTX <Role>;<Org>;<Project> //为空则用$代替
			String content = "*CTX " + role + ";" + organization + ";"
					+ projectNo;
			if (users != null && !"".equals(users)) {
				for (String user : users.split(",")) {
					content = content + "\n" + "+PERSON " + user;
					boolean ifadduserAndContext = true;
					for (int i = 0; i < alluserAndContextData.size(); i++) {// 用户上下文查看
						if (alluserAndContextData
								.get(i)
								.get("contextId")
								.equals(role + "." + organization + "."
										+ projectNo)
								&& alluserAndContextData.get(i).get("userId")
										.equals(user)) {
							ifadduserAndContext = false;
							break;
						}
					}
					if (ifadduserAndContext) {
						Map<String, String> tempMap = new HashMap<String, String>();
						tempMap.put("userId", user);
						tempMap.put("contextId", role + "." + organization
								+ "." + projectNo);
						alluserAndContextData.add(tempMap);
					}

				}
			}
			if (delusers != null && !"".equals(delusers)) {
				for (String user : delusers.split(",")) {
					content = content + "\n" + "-PERSON " + user;
					for (int i = 0; i < alluserAndContextData.size(); i++) {
						if (alluserAndContextData
								.get(i)
								.get("contextId")
								.equals(role + "." + organization + "."
										+ projectNo)
								&& alluserAndContextData.get(i).get("userId")
										.equals(user)) {
							alluserAndContextData.remove(i);
						}
					}

				}
			}
			String textPath = CommonProperties
					.getStringProperty("testFilePath");
			String textName = "PO_create";
			String fileName = textPath + textName;
			writeText(fileName, content, "import");// 创建
			// 调用
			WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
			boolean success = wSCallVpmServices.callVpmServices("", fileName);
			createLogFile(role + "." + organization + "." + projectNo, users,
					delusers);// 由于VPM客户端创建日志信息不全,网页端创建日志
		} catch (Exception e) {
			message = "创建失败";
			e.printStackTrace();
		} finally {
			String str = "{\"status\":\"ok\" , \"message\":\"" + message
					+ "!\"}";
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "创建上下文", message);
		}
	}

	/**
	 * 网页端日志文件的创建
	 * 
	 * @param string
	 * @param users
	 * @param delusers
	 */
	private void createLogFile(String contextId, String users, String delusers) {
		try {
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
			SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");// 设置日期格式2
			if (users != null && !"".equals(users)) {
				String content = "comtent1";
				for (String user : users.split(",")) {
					FileWriter fw = null;
					String time = df1.format(new Date())
							+ (int) (1 + Math.random() * (1000));
					String fileName = CommonProperties
							.getStringProperty("logFilePath")
							+ "/"
							+ time
							+ ".txt";
					File file = new File(fileName);
					boolean isFileExit = file.exists();
					fw = new FileWriter(file, true);
					if (isFileExit)
						fw.write("\n");
					content = df.format(new Date())
							+ "	vpmsec@VPMADMIN.ADMIN.DEFAULT	添加上下文用户	"
							+ contextId + "	" + user;
					fw.write(content);
					fw.write("\n");
					if (fw != null)
						try {
							fw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}

			}
			if (delusers != null && !"".equals(delusers)) {

				String content = "comtent2";
				for (String user : delusers.split(",")) {
					FileWriter fw = null;
					String time = df1.format(new Date())
							+ (int) (1 + Math.random() * (1000));
					String fileName = CommonProperties
							.getStringProperty("logFilePath")
							+ "/"
							+ time
							+ ".txt";
					File file = new File(fileName);
					boolean isFileExit = file.exists();
					fw = new FileWriter(file, true);
					if (isFileExit)
						fw.write("\n");
					content = df.format(new Date())
							+ "	vpmsec@VPMADMIN.ADMIN.DEFAULT	删除上下文用户	"
							+ contextId + "	" + user;
					fw.write(content);
					fw.write("\n");
					if (fw != null)
						try {
							fw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {

		}

	}

	/**
	 * 添加权限
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping("/addPermissions")
	@ResponseBody
	public void addPermissions(HttpServletRequest request,
			HttpServletResponse response) {
		String message = "添加成功";
		try {
			String contextId = request.getParameter("contextId");
			String type = request.getParameter("type");
			String operationGroup = request.getParameter("operationGroup");
			String dataGroup = "".equals(request.getParameter("dataGroup")) ? ""
					: request.getParameter("dataGroup");
			boolean idadd = true;
			for (int i = 0; i < allpermissionsData.size(); i++) {
				if (allpermissionsData.get(i).get("contextId")
						.equals(contextId)
						&& allpermissionsData.get(i).get("type").equals(type)
						&& allpermissionsData.get(i).get("operationGroup")
								.equals(operationGroup)
						&& allpermissionsData.get(i).get("dataGroup")
								.equals(dataGroup)) {
					idadd = false;
					break;
				}
			}
			if (idadd) {
				Map<String, String> addPermissionsMap = new HashMap<String, String>();
				addPermissionsMap.put("contextId", contextId);
				addPermissionsMap.put("type", type);
				addPermissionsMap.put("operationGroup", operationGroup);
				addPermissionsMap.put("dataGroup", dataGroup);
				allpermissionsData.add(addPermissionsMap);// 添加给全局变量用户查看
			}
			// *PRIV 1;CTX=<context_id>;<PROCESS=<类型.LOGIN> |
			// PROCESS_GROUP=<AllGlobalProcess>>[;<数据组>]
			String content = "";
			if ("VPM".equals(type) || "VPMAdmin".equals(type)) {
				content = "*PRIV 1;CTX=" + contextId + ";PROCESS=" + type
						+ ".LOGIN";
			} else {
				if (!"".equals(dataGroup) && dataGroup != null) {
					content = "*PRIV 1;CTX=" + contextId + ";PROCESS_GROUP="
							+ operationGroup + ";GLOBAL;" + dataGroup;
				} else {
					content = "*PRIV 1;CTX=" + contextId + ";PROCESS_GROUP="
							+ operationGroup;
				}
			}
			String textPath = CommonProperties
					.getStringProperty("testFilePath");
			String textName = "PO_create";
			String fileName = textPath + textName;
			writeText(fileName, content, "import");// 创建
			// 调用
			WSCallVpmServices wSCallVpmServices = new WSCallVpmServices();
			wSCallVpmServices.callVpmServices("", fileName);
		} catch (Exception e) {
			message = "添加失败";
			e.printStackTrace();
		} finally {
			String str = "{\"status\":\"ok\" , \"message\":\"" + message
					+ "!\"}";
			try {
				response.setContentType("text/html;charset=utf-8");
				response.getWriter().write(str);
			} catch (IOException e) {
				e.printStackTrace();
			}
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "添加权限", message);
		}
	}

	/**
	 * 权限查询修改功能
	 * 
	 * @param problemManagement
	 * @param request
	 * @return
	 * @throws IOException
	 * @see
	 */
	@RequestMapping("/queryOrUpdatecontextId")
	@ResponseBody
	public void queryOrUpdatecontextId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String queryOrUpdatecontextId = request
				.getParameter("queryOrUpdatecontextId");
		List<Map<String, String>> querypermissionsData = new ArrayList<Map<String, String>>();// 权限数据
		if (!"".equals(queryOrUpdatecontextId)
				&& queryOrUpdatecontextId != null) {
			for (Map<String, String> map : allpermissionsData) {
				if (map.get("contextId").contains(queryOrUpdatecontextId)) {
					querypermissionsData.add(map);
				}
			}
		} else {
			for (Map<String, String> map : allpermissionsData) {
				querypermissionsData.add(map);
			}
		}
		String pageNumber = request.getParameter("pageNumber") == null
				|| request.getParameter("pageNumber").equals("") ? "1"
				: request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") == null
				|| request.getParameter("pageSize").equals("") ? "10" : request
				.getParameter("pageSize");
		int pageSizeInt = Integer.valueOf(pageSize);
		int pageNumberInt = Integer.valueOf(pageNumber);
		List<Map<String, String>> queryuserPageData = new ArrayList<Map<String, String>>();// 用户分页数据
		for (int i = (pageNumberInt - 1) * pageSizeInt; i < (pageNumberInt - 1)
				* pageSizeInt + pageSizeInt
				&& i < querypermissionsData.size(); i++) {
			queryuserPageData.add(querypermissionsData.get(i));
		}

		String datagridStr = "{\"rows\":"
				+ JSON.toJSONString(queryuserPageData) + ",\"total\":"
				+ querypermissionsData.size() + "}";
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		pw.write(datagridStr);
		pw.flush();
		pw.close();
		functionManDao.insertLog(getDateString(), userMap.get("userId")
				.toString(), "权限查询", "查询成功");
	}

	/**
	 * 用户查询修改功能
	 * 
	 * @param request
	 * @return
	 * @throws IOException
	 * @see
	 */
	@RequestMapping("/queryUser")
	@ResponseBody
	public void queryUser(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String viewOrUpdateUserId = request.getParameter("viewOrUpdateUserId");
		String viewOrUpdateFullName = request
				.getParameter("viewOrUpdateFullName");
		List<Map<String, String>> queryuserData = new ArrayList<Map<String, String>>();// 权限数据
		if (!"".equals(viewOrUpdateUserId) && viewOrUpdateUserId != null
				&& !"".equals(viewOrUpdateFullName)
				&& viewOrUpdateFullName != null) {
			for (Map<String, String> map : alluserData) {
				if (map.get("userId").contains(viewOrUpdateUserId)
						&& map.get("fullName").contains(viewOrUpdateFullName)) {
					queryuserData.add(map);
				}
			}
		} else if ("".equals(viewOrUpdateUserId)
				&& !"".equals(viewOrUpdateFullName)) {
			for (Map<String, String> map : alluserData) {
				if (map.get("fullName").contains(viewOrUpdateFullName)) {
					queryuserData.add(map);
				}
			}
		} else if (!"".equals(viewOrUpdateUserId)
				&& "".equals(viewOrUpdateFullName)) {
			for (Map<String, String> map : alluserData) {
				if (map.get("userId").contains(viewOrUpdateUserId)) {
					queryuserData.add(map);
				}
			}
		} else {
			for (Map<String, String> map : alluserData) {
				queryuserData.add(map);
			}

		}
		String pageNumber = request.getParameter("pageNumber") == null
				|| request.getParameter("pageNumber").equals("") ? "1"
				: request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") == null
				|| request.getParameter("pageSize").equals("") ? "10" : request
				.getParameter("pageSize");
		int pageSizeInt = Integer.valueOf(pageSize);
		int pageNumberInt = Integer.valueOf(pageNumber);
		List<Map<String, String>> queryuserPageData = new ArrayList<Map<String, String>>();// 用户分页数据
		for (int i = (pageNumberInt - 1) * pageSizeInt; i < (pageNumberInt - 1)
				* pageSizeInt + pageSizeInt
				&& i < queryuserData.size(); i++) {
			queryuserPageData.add(queryuserData.get(i));
		}
		String datagridStr = "{\"rows\":"
				+ JSON.toJSONString(queryuserPageData) + ",\"total\":"
				+ queryuserData.size() + "}";
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		pw.write(datagridStr);
		pw.flush();
		pw.close();
		functionManDao.insertLog(getDateString(), userMap.get("userId")
				.toString(), "用户查询", "查询成功");
	}

	/**
	 * 用户查询修改功能
	 * 
	 * @param problemManagement
	 * @param request
	 * @return
	 * @throws IOException
	 * @see
	 */
	@RequestMapping("/queryUserAndContextId")
	@ResponseBody
	public void queryUserAndContextId(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 生成文件
		String usercontextId = request.getParameter("usercontextId");
		List<Map<String, String>> queryuserData = new ArrayList<Map<String, String>>();// 权限数据
		if (!"".equals(usercontextId) && usercontextId != null) {
			for (Map<String, String> map : alluserAndContextData) {
				if (map.get("userId").contains(usercontextId)) {
					queryuserData.add(map);
				}
			}
		} else {
			for (Map<String, String> map : alluserAndContextData) {
				queryuserData.add(map);
			}

		}
		String pageNumber = request.getParameter("pageNumber") == null
				|| request.getParameter("pageNumber").equals("") ? "1"
				: request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") == null
				|| request.getParameter("pageSize").equals("") ? "10" : request
				.getParameter("pageSize");
		int pageSizeInt = Integer.valueOf(pageSize);
		int pageNumberInt = Integer.valueOf(pageNumber);
		List<Map<String, String>> queryuserPageData = new ArrayList<Map<String, String>>();// 用户分页数据
		for (int i = (pageNumberInt - 1) * pageSizeInt; i < (pageNumberInt - 1)
				* pageSizeInt + pageSizeInt
				&& i < queryuserData.size(); i++) {
			queryuserPageData.add(queryuserData.get(i));
		}
		String datagridStr = "{\"rows\":"
				+ JSON.toJSONString(queryuserPageData) + ",\"total\":"
				+ queryuserData.size() + "}";
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		PrintWriter pw = response.getWriter();
		response.setContentType("application/json");
		response.setHeader("Cache-Control", "no-store");
		pw.write(datagridStr);
		pw.flush();
		pw.close();
		functionManDao.insertLog(getDateString(), userMap.get("userId")
				.toString(), "用户权限查询", "查询成功");
	}

	/**
	 * 日志查询功能
	 * 
	 * @param problemManagement
	 * @param request
	 * @return
	 * @throws IOException
	 * @see
	 */
	@RequestMapping("/logFileQuery")
	@ResponseBody
	public void logFileQuery(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		// 生成文件
		String userlogFileQueryId = request.getParameter("userlogFileQueryId");
		String query_startDate = request.getParameter("query_startDate");
		String query_endDate = request.getParameter("query_endDate");
		String user_logFileQueryType = new String(request.getParameter(
				"user_logFileQueryType").getBytes("ISO8859-1"), "utf-8");
		List<Map<String, String>> queryuserData = new ArrayList<Map<String, String>>();// 日志查询功能
		// 第一次全部查询，后面定时任务查询
		if (allLogFileData.size() == 0 || allLogFileData == null) {
			// 所有数据
			allLogFileData = new ArrayList<String>();
			String filepath = CommonProperties.getStringProperty("logFilePath");
			;// 日志文件夹
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "2");// type为2的话为查询日志
			File file = new File(filepath);
			if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "\\" + filelist[i]);
					try {
						readFileByLines(readfile.getPath(), map, allLogFileData);// 解析日志文件夹
					} catch (ParseException e) {
						e.printStackTrace();
					}
				}
			}
		}
		for (String logFileStr1 : allLogFileData) {
			if (logFileStr1.contains("comtent1")
					|| logFileStr1.contains("comtent2")) {
				continue;
			}
			String[] tempStringArray = logFileStr1.split("\t");
			if (!userlogFileQueryId.equals("") && userlogFileQueryId != null) {
				if (logFileStr1.contains(userlogFileQueryId)) {
					queryuserData.add(getMapInfo(tempStringArray));
				}
			} else if (!query_startDate.equals("") && query_startDate != null) {
				int startCompare = tempStringArray[0].substring(0, 10)
						.compareTo(query_startDate.toString());
				if (startCompare >= 0) {
					queryuserData.add(getMapInfo(tempStringArray));
				}
			} else if (!query_endDate.equals("") && query_endDate != null) {
				int endCompare = tempStringArray[0].substring(0, 10).compareTo(
						query_endDate.toString());
				if (endCompare <= 0) {
					queryuserData.add(getMapInfo(tempStringArray));
				}
			} else if (!user_logFileQueryType.equals(" ")
					&& user_logFileQueryType != null) {
				if (logFileStr1.contains(user_logFileQueryType)) {
					queryuserData.add(getMapInfo(tempStringArray));
				}
			} else {
				queryuserData.add(getMapInfo(tempStringArray));
			}
		}
		
		List<Map<String, Object>> logsList = functionManDao.selectLog();
		for (Map<String, Object> map : logsList) {
			Map<String,String> m = new HashMap<String, String>();
			m.put("userId", map.get("USER_NAME").toString());
			m.put("date", map.get("CREATED_DATE").toString());
			m.put("type", map.get("TYPE").toString());
			m.put("content", map.get("CONTEXT").toString());
			queryuserData.add(m);
		}
		
		String pageNumber = request.getParameter("pageNumber") == null
				|| request.getParameter("pageNumber").equals("") ? "1"
				: request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") == null
				|| request.getParameter("pageSize").equals("") ? "10" : request
				.getParameter("pageSize");
		int pageSizeInt = Integer.valueOf(pageSize);
		int pageNumberInt = Integer.valueOf(pageNumber);
		List<Map<String, String>> queryuserPageData = new ArrayList<Map<String, String>>();// 用户分页数据
		for (int i = (pageNumberInt - 1) * pageSizeInt; i < (pageNumberInt - 1)
				* pageSizeInt + pageSizeInt
				&& i < queryuserData.size(); i++) {
			queryuserPageData.add(queryuserData.get(i));
		}
		String datagridStr = "{\"rows\":"
				+ JSON.toJSONString(queryuserPageData) + ",\"total\":"
				+ queryuserData.size() + "}";
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(datagridStr);
		pw.flush();
		pw.close();
		functionManDao.insertLog(getDateString(), userMap.get("userId")
				.toString(), "审计查询", "查询成功");
	}
	
	@RequestMapping("/selectProj")
	@ResponseBody
	public void selectProj(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String projectName = request.getParameter("projectName");
		List<Map<String,String>> queryuserData = new ArrayList<Map<String,String>>();
		if(projectName==null||"".equals(projectName)){
			queryuserData = allprojectData;
		}else {
			for (Map<String, String> proj : allprojectData) {
				String project_name = proj.get("project_name");
				if(projectName.equals(project_name)){
					queryuserData.add(proj);
				}
			}
		}
		String pageNumber = request.getParameter("pageNumber") == null
				|| request.getParameter("pageNumber").equals("") ? "1"
				: request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") == null
				|| request.getParameter("pageSize").equals("") ? "10" : request
				.getParameter("pageSize");
		int pageSizeInt = Integer.valueOf(pageSize);
		int pageNumberInt = Integer.valueOf(pageNumber);
		List<Map<String, String>> queryuserPageData = new ArrayList<Map<String, String>>();// 用户分页数据
		for (int i = (pageNumberInt - 1) * pageSizeInt; i < (pageNumberInt - 1)
				* pageSizeInt + pageSizeInt
				&& i < queryuserData.size(); i++) {
			queryuserPageData.add(queryuserData.get(i));
		}
		String datagridStr = "{\"rows\":"
				+ JSON.toJSONString(queryuserPageData) + ",\"total\":"
				+ queryuserData.size() + "}";
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(datagridStr);
		pw.flush();
		pw.close();
		functionManDao.insertLog(getDateString(), userMap.get("userId")
				.toString(), "项目查询", "查询成功");
	}
	
	@RequestMapping("/selectOra")
	@ResponseBody
	public void selectOra(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String oraName = request.getParameter("oraName");
		String oraNumber = request.getParameter("oraNumber");
		List<Map<String,String>> queryuserData = new ArrayList<Map<String,String>>();
		if((oraName==null||"".equals(oraName))&&(oraNumber==null||"".equals(oraNumber))){
			queryuserData = allorginaztionData;
		}else {
			for (Map<String, String> proj : allorginaztionData) {
				String ora_id = proj.get("org_id");
				if(ora_id.equals(oraNumber)){
					queryuserData.add(proj);
				}
			}
		}
		String pageNumber = request.getParameter("pageNumber") == null
				|| request.getParameter("pageNumber").equals("") ? "1"
				: request.getParameter("pageNumber");
		String pageSize = request.getParameter("pageSize") == null
				|| request.getParameter("pageSize").equals("") ? "10" : request
				.getParameter("pageSize");
		int pageSizeInt = Integer.valueOf(pageSize);
		int pageNumberInt = Integer.valueOf(pageNumber);
		List<Map<String, String>> queryuserPageData = new ArrayList<Map<String, String>>();// 用户分页数据
		for (int i = (pageNumberInt - 1) * pageSizeInt; i < (pageNumberInt - 1)
				* pageSizeInt + pageSizeInt
				&& i < queryuserData.size(); i++) {
			queryuserPageData.add(queryuserData.get(i));
		}
		String datagridStr = "{\"rows\":"
				+ JSON.toJSONString(queryuserPageData) + ",\"total\":"
				+ queryuserData.size() + "}";
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pw = response.getWriter();
		pw.write(datagridStr);
		pw.flush();
		pw.close();
		functionManDao.insertLog(getDateString(), userMap.get("userId")
				.toString(), "组织查询", "查询成功");
	}

	private Map<String, String> getMapInfo(String[] tempStringArray)
			throws UnsupportedEncodingException {
		Map<String, String> a = new HashMap<String, String>();
		if (tempStringArray.length >= 1) {
			a.put("date", tempStringArray[0]);
		} else {
			a.put("date", "");
		}
		if (tempStringArray.length >= 2) {
			a.put("userId", tempStringArray[1]);
		} else {
			a.put("userId", "");
		}
		if (tempStringArray.length >= 3) {
			a.put("type", tempStringArray[2]);
			if ("登陆".equals(tempStringArray[2])
					|| "登出".equals(tempStringArray[2])
					|| "创建零件".equals(tempStringArray[2])
					|| "删除零件".equals(tempStringArray[2])
					|| "删除图文档".equals(tempStringArray[2])) {
				tempStringArray[1] = tempStringArray[1].replaceAll("EV5ADM",
						"vpmsysadmin");
			} else if ("用户创建".equals(tempStringArray[2])
					|| "用户删除".equals(tempStringArray[2])
					|| "添加权限".equals(tempStringArray[2])
					|| "删除权限".equals(tempStringArray[2])
					|| "修改权限".equals(tempStringArray[2])
					|| "添加上下文用户".equals(tempStringArray[2])
					|| "删除上下文用户".equals(tempStringArray[2])) {
				tempStringArray[1] = tempStringArray[1].replaceAll("EV5ADM",
						"vpmsecadmin");
			}
			a.put("userId", tempStringArray[1]);
		} else {
			a.put("type", "");
		}
		if (tempStringArray.length >= 4) {
			a.put("content", tempStringArray[3]);
			if (tempStringArray.length >= 5) {
				a.put("content", tempStringArray[3] + tempStringArray[4]);
			} else if (tempStringArray.length >= 6) {
				a.put("content", tempStringArray[3] + tempStringArray[4]
						+ tempStringArray[5]);
			} else if (tempStringArray.length >= 7) {
				a.put("content", tempStringArray[3] + tempStringArray[4]
						+ tempStringArray[5] + tempStringArray[6]);
			} else if (tempStringArray.length >= 8) {
				a.put("content", tempStringArray[3] + tempStringArray[4]
						+ tempStringArray[5] + tempStringArray[6]
						+ tempStringArray[7]);
			}
		} else {
			a.put("content", "");
		}
		return a;

	}

	@RequestMapping("/userStatistics")
	public void userStatistics(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String user_startDate = request.getParameter("user_startDate");
			String user_endDate = request.getParameter("user_endDate");
			String user_operationType = new String(request.getParameter(
					"user_operationType").getBytes("ISO8859-1"), "utf-8");
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "2");// type为2的话为审计管理导出查询
			map.put("startDate", user_startDate);
			map.put("endDate", user_endDate);
			map.put("operationType", user_operationType);
			map.put("operationWay", "用户行为审计");
			List<String> datalist = new ArrayList<String>(); // 日志数据
			String filepath = CommonProperties.getStringProperty("logFilePath");
			;// 日志文件夹
			File file = new File(filepath);
			if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "/" + filelist[i]);
					System.out.println("path=" + readfile.getPath());
					readFileByLines(filepath + "/" + filelist[i], map, datalist);// 解析日志文件夹
				}
			}

			// 导出excell
			// 创建HSSFWorkbook对象(excel的文档对象)
			HSSFWorkbook wb = new HSSFWorkbook();
			// 头字体
			HSSFFont headfond = wb.createFont();
			headfond.setFontName("黑体");
			headfond.setFontHeightInPoints((short) 20);
			headfond.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			HSSFCellStyle headstyle = wb.createCellStyle();
			headstyle.setFont(headfond);
			headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			headstyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
			headstyle.setFillForegroundColor(HSSFColor.YELLOW.index);
			headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			// 普通样式
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

			// 建立新的sheet对象（excel的表单）
			HSSFSheet sheet = wb.createSheet("用户审计统计");
			// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
			HSSFRow row1 = sheet.createRow(0);
			// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
			HSSFCell cell = row1.createCell(0);
			// 设置单元格内容
			cell.setCellValue("用户审计统计一览表");
			cell.setCellStyle(headstyle);
			// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
			int arrSize = 0;
			// 在sheet里创建第二行
			HSSFRow titleRow = sheet.createRow(1);
			HSSFCell cell0 = titleRow.createCell(0);
			cell0.setCellValue("操作时间");
			cell0.setCellStyle(cellStyle);
			HSSFCell cell1 = titleRow.createCell(1);
			cell1.setCellValue("操作用户");
			cell1.setCellStyle(cellStyle);
			HSSFCell cell2 = titleRow.createCell(2);
			cell2.setCellValue("操作类型");
			cell2.setCellStyle(cellStyle);
			if ("登陆".equals(user_operationType)
					|| "登出".equals(user_operationType)) {
			} else if ("创建零件".equals(user_operationType)
					|| "删除零件".equals(user_operationType)) {
				HSSFCell cell3 = titleRow.createCell(3);
				cell3.setCellValue("零件实例名");
				cell3.setCellStyle(cellStyle);
				HSSFCell cell4 = titleRow.createCell(4);
				cell4.setCellValue("零件参考名");
				cell4.setCellStyle(cellStyle);
				HSSFCell cell5 = titleRow.createCell(5);
				cell5.setCellValue("版本");
				cell5.setCellStyle(cellStyle);
				HSSFCell cell6 = titleRow.createCell(6);
				cell6.setCellValue("父节点实例名称");
				cell6.setCellStyle(cellStyle);
			} else if ("删除图文档".equals(user_operationType)) {
				HSSFCell cell3 = titleRow.createCell(3);
				cell3.setCellValue("文档名称及版本");
				cell3.setCellStyle(cellStyle);
			}
			for (int i = 0; i < datalist.size(); i++) {
				HSSFRow row2 = sheet.createRow(i + 2);
				String[] datalistArray = datalist.get(i).split("\t");
				arrSize = datalistArray.length;
				for (int j = 0; j < arrSize; j++) {
					HSSFCell cellj = row2.createCell(j);
					cellj.setCellStyle(cellStyle);
					cellj.setCellValue(datalistArray[j].toString().trim());
				}
			}
			if (arrSize > 0) {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, arrSize - 1));
			}
			sheet.setDefaultColumnWidth(40);
			// 输出Excel文件
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ new String("用户审计表".getBytes("gb2312"), "iso8859-1")
					+ ".xls\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream output = response.getOutputStream();
			wb.write(output);
			output.close();
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "用户行为审计", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("/secStatistics")
	public void secStatistics(HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String sec_startDate = request.getParameter("sec_startDate");
			String sec_endDate = request.getParameter("sec_endDate");
			// String sec_operationType = ;
			String sec_operationType = new String(request.getParameter(
					"sec_operationType").getBytes("ISO8859-1"), "utf-8");
			if ("用户删除".equals(sec_operationType)) {
				sec_operationType = "UpdatePerson";// 注意，由于并不是真正的从P&O中删除该用户而是修改用户信息，所以删除用户这个操作在VPM的日志中变更为UpdatePerson，因此WEB端在查询日志的时候需要注意。
			}
			Map<String, String> map = new HashMap<String, String>();
			map.put("type", "2");// type为2的话为审计管理导出查询
			map.put("startDate", sec_startDate);
			map.put("endDate", sec_endDate);
			map.put("operationType", sec_operationType);
			map.put("operationWay", "管理员行为审计");
			// 查找日志文件
			List<String> datalist = new ArrayList<String>(); // 日志数据
			String filepath = CommonProperties.getStringProperty("logFilePath");
			;// 日志文件夹
			File file = new File(filepath);
			if (file.isDirectory()) {
				String[] filelist = file.list();
				for (int i = 0; i < filelist.length; i++) {
					File readfile = new File(filepath + "/" + filelist[i]);
					System.out.println("path=" + readfile.getPath());
					readFileByLines(filepath + "/" + filelist[i], map, datalist);// 解析日志文件夹
				}
			}
			// 导出excell
			// 创建HSSFWorkbook对象(excel的文档对象)
			HSSFWorkbook wb = new HSSFWorkbook();
			// 头字体
			HSSFFont headfond = wb.createFont();
			headfond.setFontName("黑体");
			headfond.setFontHeightInPoints((short) 20);
			headfond.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
			HSSFCellStyle headstyle = wb.createCellStyle();
			headstyle.setFont(headfond);
			headstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			headstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			headstyle.setFillBackgroundColor(HSSFColor.YELLOW.index);
			headstyle.setFillForegroundColor(HSSFColor.YELLOW.index);
			headstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
			headstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
			headstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
			headstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
			// 普通样式
			HSSFCellStyle cellStyle = wb.createCellStyle();
			cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
			cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
			// 建立新的sheet对象（excel的表单）
			HSSFSheet sheet = wb.createSheet("安全管理员审计统计");
			// 在sheet里创建第一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
			HSSFRow row1 = sheet.createRow(0);
			// 创建单元格（excel的单元格，参数为列索引，可以是0～255之间的任何一个
			HSSFCell cell = row1.createCell(0);
			// 设置单元格内容
			cell.setCellValue("安全管理员审计统计一览表");
			cell.setCellStyle(headstyle);
			// 合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
			int arrSize = 0;
			// 在sheet里创建第二行
			HSSFRow titleRow = sheet.createRow(1);
			HSSFCell cell0 = titleRow.createCell(0);
			cell0.setCellValue("操作时间");
			cell0.setCellStyle(cellStyle);
			HSSFCell cell1 = titleRow.createCell(1);
			cell1.setCellValue("操作用户");
			cell1.setCellStyle(cellStyle);
			HSSFCell cell2 = titleRow.createCell(2);
			cell2.setCellValue("操作类型");
			cell2.setCellStyle(cellStyle);
			if ("用户创建".equals(sec_operationType)
					|| "用户删除".equals(sec_operationType)
					|| "UpdatePerson".equals(sec_operationType)) {
				HSSFCell cell3 = titleRow.createCell(3);
				cell3.setCellValue("用户名");
				cell3.setCellStyle(cellStyle);
			} else if ("添加权限".equals(sec_operationType)
					|| "删除权限".equals(sec_operationType)
					|| "修改权限".equals(sec_operationType)) {
				HSSFCell cell3 = titleRow.createCell(3);
				cell3.setCellValue("上下文");
				cell3.setCellStyle(cellStyle);
			} else if ("添加上下文用户".equals(sec_operationType)
					|| "删除上下文用户".equals(sec_operationType)) {
				HSSFCell cell3 = titleRow.createCell(3);
				cell3.setCellValue("上下文");
				cell3.setCellStyle(cellStyle);
				HSSFCell cell4 = titleRow.createCell(4);
				cell4.setCellValue("用户名");
				cell4.setCellStyle(cellStyle);
			}
			for (int i = 0; i < datalist.size(); i++) {
				HSSFRow row2 = sheet.createRow(i + 2);
				String[] datalistArray = datalist.get(i).split("\t");
				arrSize = datalistArray.length;
				for (int j = 0; j < arrSize; j++) {
					HSSFCell cellj = row2.createCell(j);
					cellj.setCellStyle(cellStyle);
					cellj.setCellValue(datalistArray[j].toString().trim());
					if (("添加权限".equals(sec_operationType)
							|| "删除权限".equals(sec_operationType) || "修改权限"
								.equals(sec_operationType)) && j == 3) {
						sheet.setColumnWidth(3, datalistArray[j].toString()
								.trim().getBytes().length * 2 * 256);
					}
				}
			}
			if (arrSize > 0) {
				sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, arrSize - 1));
			}
			sheet.setDefaultColumnWidth(40);
			// 输出Excel文件
			response.reset();
			response.setHeader("Content-Disposition", "attachment;filename=\""
					+ new String("安全管理员审计表".getBytes("gb2312"), "iso8859-1")
					+ ".xls\"");
			response.setContentType("application/octet-stream;charset=UTF-8");
			OutputStream output = response.getOutputStream();
			wb.write(output);
			output.close();
			functionManDao.insertLog(getDateString(), userMap.get("userId")
					.toString(), "管理员行为审计", "导出成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 6 fileName map
	 * 
	 * @throws ParseException
	 */
	public static void readFileByLines(String fileName,
			Map<String, String> map, List<String> datalist)
			throws ParseException {
		File file = new File(fileName);
		BufferedReader reader = null;
		try {
			List<String> tempList = new ArrayList<String>();
			InputStreamReader isr = new InputStreamReader(new FileInputStream(
					file), "GBK");
			reader = new BufferedReader(isr);
			String tempString = null;
			// (针对po导出的文件部分数据一行数据中间null存在的情况)用户读取不到的问题
			if (!"2".equals(map.get("type"))) {
				int i = 0;
				int y = 0;
				String halfString = "";
				do {
					tempString = reader.readLine();
					if (tempString != null && !"null".equals(tempString)) {
						if (y == i && !"".equals(halfString)) {
							tempList.remove(halfString);
							tempString = halfString + tempString;
						}
						i++;
						tempList.add(tempString);
						halfString = tempString;
					} else {
						y = i;
						tempString = "a";
					}
				} while (!tempString.contains("End of export file"));
				// 遍历数据
				for (String string : tempList) {
					if (!string.startsWith("//")) {
						if ("1".equals(map.get("type"))) {// 用户查询
							if (string.contains("*PERSON")) {
								if (!"".equals(map.get("viewOrUpdateFullName"))
										&& map.get("viewOrUpdateFullName") != null
										&& !"".equals(map
												.get("viewOrUpdateUserId"))
										&& map.get("viewOrUpdateUserId") != null) {
									if (string.contains(map
											.get("viewOrUpdateUserId"))
											&& string
													.contains(map
															.get("viewOrUpdateFullName"))) {
										datalist.add(string);
									}
								} else if ("".equals(map
										.get("viewOrUpdateUserId"))
										&& map.get("viewOrUpdateUserId") == null
										&& !"".equals(map
												.get("viewOrUpdateFullName"))
										&& map.get("viewOrUpdateFullName") != null) {
									if (string.contains(map
											.get("viewOrUpdateFullName"))) {
										datalist.add(string);
									}
								} else if (!"".equals(map
										.get("viewOrUpdateUserId"))
										&& map.get("viewOrUpdateUserId") != null
										&& "".equals(map
												.get("viewOrUpdateFullName"))
										&& map.get("viewOrUpdateFullName") == null) {
									if (string.contains(map
											.get("viewOrUpdateUserId"))) {
										datalist.add(string);
									}
								} else {
									datalist.add(string);
								}
							}
						} else if ("3".equals(map.get("type"))) {// 权限查询
							if (string.contains("*PRIV 1")) {
								if (!"".equals(map
										.get("queryOrUpdatecontextId"))
										&& map.get("queryOrUpdatecontextId") != null) {
									if (string.split(";")[1].contains(map
											.get("queryOrUpdatecontextId"))
											&& string.contains("*PRIV 1")) {
										datalist.add(string);
									}
								} else {
									datalist.add(string);
								}
							}
						} else if ("4".equals(map.get("type"))) {// 项目查询
							if (string.contains("*PRJ")) {
								datalist.add(string);
							}
						} else if ("5".equals(map.get("type"))) {// 组织数据查询
							if (string.contains("*ORG")) {
								datalist.add(string);
							}
						} else if ("6".equals(map.get("type"))) {// 上下文查询
							if (string.contains("*CTX")) {
								datalist.add(string);
							}
						} else if ("7".equals(map.get("type"))) {// 角色查询
							if (string.contains("*ROLE")) {
								datalist.add(string);
							}
						}
					}
				}
			} else {
				// 日志文件数据
				do {
					tempString = reader.readLine();
					if (tempString != null && !"null".equals(tempString)) {
						if (map.get("operationType") == null) {
							if (tempString.contains("UpdatePerson")) {
								tempString = tempString.replaceAll(
										"UpdatePerson", "用户删除");
								// 注意，由于并不是真正的从P&O中删除该用户而是修改用户信息，所以删除用户这个操作在VPM的日志中变更为UpdatePerson，因此WEB端在查询日志的时候需要注意。
							}
							datalist.add(tempString);
						} else {
							if (tempString.contains(map.get("operationType"))) {
								if ("".equals(map.get("startDate"))
										|| null == map.get("startDate")) {
									datalist.add(tempString);
								} else {
									String[] tempStringArray = tempString
											.split("\t");
									int startCompare = tempStringArray[0]
											.substring(0, 10).compareTo(
													map.get("startDate")
															.toString());
									int endCompare = tempStringArray[0]
											.substring(0, 10).compareTo(
													map.get("endDate")
															.toString());
									if ("添加上下文用户".equals(map
											.get("operationType"))
											|| "删除上下文用户".equals(map
													.get("operationType"))) {// 由于aix系统写入日期前俩数字乱码,日期比较无效,web端写人的日志不要求时间限制
										datalist.add(tempString);
									} else {
										if (startCompare >= 0
												&& endCompare <= 0) {
											if ("用户行为审计".equals(map
													.get("operationWay"))) {
												tempString = tempString
														.replaceAll("EV5ADM",
																"vpmsysadmin");
											} else {
												tempString = tempString
														.replaceAll("EV5ADM",
																"vpmsecadmin");
												if (tempString
														.contains("UpdatePerson")) {
													tempString = tempString
															.replaceAll(
																	"UpdatePerson",
																	"用户删除");
													// 注意，由于并不是真正的从P&O中删除该用户而是修改用户信息，所以删除用户这个操作在VPM的日志中变更为UpdatePerson，因此WEB端在查询日志的时候需要注意。
												}
											}
											datalist.add(tempString);
										}
									}
								}
							}
						}

					}
				} while ((tempString = reader.readLine()) != null);
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e1) {
				}
			}
		}
	}

	/**
	 * 定时任务，每天凌晨一点去查询P&O的文件数据放在全局变量里面 每天凌晨一点 0 0 1 * * ?
	 */
	// 测试： 每2分钟执行（由于P&O导出） 0 */2 * * * ?
	@Scheduled(cron = "0 0 2 * * ?")
	public void getAllDataByScheduled() {
		System.out.println("定时任务2分钟执行----------------------");
		// 所有数据
		allLogFileData = new ArrayList<String>();
		String filepath = CommonProperties.getStringProperty("logFilePath");
		;// 日志文件夹
		Map<String, String> map = new HashMap<String, String>();
		map.put("type", "2");// type为2的话为查询日志
		File file = new File(filepath);
		if (file.isDirectory()) {
			String[] filelist = file.list();
			for (int i = 0; i < filelist.length; i++) {
				File readfile = new File(filepath + "\\" + filelist[i]);
				try {
					readFileByLines(readfile.getPath(), map, allLogFileData);// 解析日志文件夹
				} catch (ParseException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
