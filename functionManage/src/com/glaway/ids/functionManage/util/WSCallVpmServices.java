/** 
 * Created on 2016-6-18 ����9:50:48
 * Created by cnie 
 */
/** 
 * Created on 2016-6-19 ����3:43:04
 * Created by cnie 
 */
package com.glaway.ids.functionManage.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import com.glaway.ids.functionManage.properties.CommonProperties;

/**
 * @desription TODO
 * @author cnie
 * @date 2016-6-18 ����9:50:48
 */
public class WSCallVpmServices {

	public String callVpmServices(String command, String param)
			throws Exception {
		return openServiceEXE(command, param);
	}

	public synchronized String openServiceEXE(String command, String param)
			throws Exception {
		String str1 = CommonProperties.getStringProperty("program");
		String str2 = CommonProperties.getStringProperty("methods");
		String str3 = CommonProperties.getStringProperty("environment");
		String str4 = CommonProperties.getStringProperty("direnv");
		String textPath = CommonProperties.getStringProperty("testFilePath");
		String fileName = "";
		String textName = System.currentTimeMillis() + "";
		fileName = textPath + textName + ".sh";
		String tempcmd = str1 + " -run \"" + str2 + " " + command + " " + param
				+ " \" -env " + str3 + " -direnv \"" + str4 + "\"";
		String cmd = "su - ev5adm -c '" + tempcmd + "'";
		FileUtils.writeText(fileName, cmd);
		System.out.println("---当前执行的cmd:"+cmd);
		Runtime rn = Runtime.getRuntime();
		String str = "chmod 777 " + fileName;
		Process process = rn.exec(str);
		Thread.sleep(1000);
		Process process2 = rn.exec(fileName);
		/*
		 * while (!new File(fileName).exists()) { Thread.sleep(1000 * 3); }
		 */
		new RunThread(process2.getInputStream(), "INFO").start();
		new RunThread(process2.getErrorStream(), "ERR").start();
		int value = process2.waitFor();
		if (value==0) {
			System.out.println("---success---");
			return fileName;
		}else {
			System.out.println("---fail---");
			return "error";
		}
	}

	public void createUserAndPwd(String userId, String userLevel) {
		String text = userId + " 123456 " + userLevel;
		FileUtils.addOrUpdateFile(userId, userLevel, text);
	}



	public void updateUserAndPwd(String userId, String dialogPwd2) {
		String textPath = CommonProperties.getStringProperty("userAndPwdPath");// 模拟数据库文件路径
		String fileName = "";
		String textName = "userAndPwdFile";
		fileName = textPath + textName;
		FileModify fileModify = new FileModify();
		fileModify.write(fileName,
				fileModify.read(fileName, userId, dialogPwd2)); // 读取修改文件
	}

	public void cancellUserAndPwd(String userId, String dialogPwd2) {
		String textPath = CommonProperties.getStringProperty("userAndPwdPath");// 模拟数据库文件路径
		String fileName = "";
		String textName = "userAndPwdFile";
		fileName = textPath + textName;
		FileModify fileModify = new FileModify();
		fileModify.write(fileName,
				fileModify.cancellRad(fileName, userId, dialogPwd2)); // 读取修改文件
	}

	public void createUser(String userId) throws Exception {
		String chpasswdTextPath = CommonProperties
				.getStringProperty("testFilePath");
		String chpasswdfileName = chpasswdTextPath + userId + "/"
				+ System.currentTimeMillis() + ".txt";
		File chpasswdfileNamefile = new File(chpasswdTextPath + userId);
		if (!chpasswdfileNamefile.exists()) {
			chpasswdfileNamefile.mkdirs();
		}
        FileUtils.writeText(chpasswdfileName, userId + ":" + userId + "@cac");
		String textPath = CommonProperties.getStringProperty("testFilePath");
		String fileName = "";
		String textName = System.currentTimeMillis() + "";
		fileName = textPath + textName + ".sh";// linux
		String cmd = "useradd -d /home/" + userId + " -m " + userId;// linux
		cmd = cmd + "\n" + "cat " + chpasswdfileName + "|chpasswd";
        FileUtils.writeText(fileName, cmd);
		Runtime rn = Runtime.getRuntime();
		Process process = rn.exec("chmod 777 " + fileName);
		rn.exec(textPath + "./" + textName + ".sh");
		process.waitFor();
		System.out.println("finish:" + process.waitFor());
	}

	public void updateUserPwd(String userId, String dialogPwd2) {
		String chpasswdTextPath = CommonProperties
				.getStringProperty("testFilePath");
		String chpasswdfileName = chpasswdTextPath + userId + "/"
				+ System.currentTimeMillis() + ".txt";
		File chpasswdfileNamefile = new File(chpasswdTextPath + userId);
		if (!chpasswdfileNamefile.exists()) {
			chpasswdfileNamefile.mkdirs();
		}
        FileUtils.writeText(chpasswdfileName, userId + ":" + dialogPwd2);
		String textPath = CommonProperties.getStringProperty("testFilePath");
		String fileName = "";// linux
		String textName = System.currentTimeMillis() + "";
		fileName = textPath + textName + ".sh";
		String cmd = "cat " + chpasswdfileName + "|chpasswd";// linux
        FileUtils.writeText(fileName, cmd);
		try {
			Runtime rn = Runtime.getRuntime();
			Process process = rn.exec("chmod 777 " + fileName);
			rn.exec(textPath + "./" + textName + ".sh");
			process.waitFor();
			System.out.println("finish:" + process.waitFor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void cancellUserPwd(String userId) {
		String textPath = CommonProperties.getStringProperty("testFilePath");
		String fileName = "";
		String textName = System.currentTimeMillis() + "";
		// fileName = textPath+textName+".bat";
		fileName = textPath + textName + ".sh";
		// String cmd = "net  user "+ userId+" "+ dialogPwd2;//window
		String cmd = "userdel  " + userId;// linux
        FileUtils.writeText(fileName, cmd);
		try {
			Runtime rn = Runtime.getRuntime();
			Process process = rn.exec("chmod 777 " + fileName);
			rn.exec(fileName);
			process.waitFor();
			System.out.println("finish:" + process.waitFor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@SuppressWarnings("unused")
	private void removeFile(String fileName) {

		try {
			File file = new File(fileName);
			if (file.exists()) {
				file.delete();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
