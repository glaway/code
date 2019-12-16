/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package com.glaway.ids.functionManage.util;

import com.alibaba.fastjson.JSONObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AjaxJson {
	private boolean success;
	private List<String> errMsg;
	private String msg;
	private Object obj;
	private Map<String, Object> attributes;

	public AjaxJson() {
		this.success = true;

		this.errMsg = null;

		this.msg = "操作成功";

		this.obj = null;
	}

	public Map<String, Object> getAttributes() {
		return this.attributes;
	}

	public void setAttributes(Map<String, Object> attributes) {
		this.attributes = attributes;
	}

	public String getMsg() {
		String eMsg = "";
		if ((this.errMsg != null) && (this.errMsg.size() > 0)) {
			for (String str : this.errMsg) {
				eMsg = eMsg + str + "<br/>";
			}
		}
		if (eMsg.equals("")) {
			eMsg = this.msg;
		}
		return eMsg;
	}

	public String getErrMsg() {
		String eMsg = "";
		if ((this.errMsg != null) && (this.errMsg.size() > 0)) {
			for (String str : this.errMsg) {
				eMsg = eMsg + str + "<br/>";
			}
		}
		return eMsg;
	}

	public void setMsg(String msg) {
		if (this.errMsg == null) {
			this.errMsg = new ArrayList();
		}
		this.msg = msg;
		this.errMsg.add(msg);
	}

	public Object getObj() {
		return this.obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getJsonStr() {
		JSONObject obj = new JSONObject();
		obj.put("success", Boolean.valueOf(isSuccess()));

		obj.put("msg", getErrMsg());
		obj.put("obj", this.obj);
		obj.put("attributes", this.attributes);
		return obj.toJSONString();
	}
}