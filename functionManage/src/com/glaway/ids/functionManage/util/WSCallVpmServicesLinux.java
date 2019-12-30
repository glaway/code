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
public class WSCallVpmServicesLinux {
	
	public boolean callVpmServices(String command,String param){
		boolean rs= false;
		try{
			openServiceEXE(command, param);
			rs = true;
		}catch (Exception e) {
			e.printStackTrace();
			rs = false;
		}
		return rs;
	}
	
	public  synchronized void openServiceEXE(String command,String param) throws Exception{
		
		String str1 = CommonProperties.getStringProperty("program");
		String str2 = CommonProperties.getStringProperty("methods");
		String str3 = CommonProperties.getStringProperty("environment");
		String str4 = CommonProperties.getStringProperty("direnv");
		String textPath = CommonProperties.getStringProperty("testFilePath");
		String fileName ="";
		String textName = System.currentTimeMillis()+"";
		fileName = textPath+textName+".sh";
		String cmd = str1 + " -run \"" +str2+" "+command+" "+param+" \" -env "+str3 + " -direnv \""+str4+"\"";
		writeText(fileName, cmd);
		try{
		 Runtime rn = Runtime.getRuntime();
		 String str = "chmod 777 "+fileName;
		 Process process = rn.exec(str);
		   try {
				Thread.sleep(1000*13);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		   process.waitFor();
		   System.out.println("finish:" +  process.waitFor());
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void createUser(String userId) {
    	String textPath = CommonProperties.getStringProperty("testFilePath");
		String fileName ="";
		String textName = System.currentTimeMillis()+"";
		fileName = textPath+textName+".sh";//linux
		String cmd = "useradd "+ userId+";echo '123456'|passwd -stdin "+userId;//linux
		writeText(fileName, cmd);
		try {
		 Runtime rn = Runtime.getRuntime();
		 Process process = rn.exec(fileName);
		 process.waitFor();
		System.out.println("finish:" +  process.waitFor());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void createUserAndPwd(String userId,String userLevel) {
		String text = userId+";123456;" + userLevel;
		FileUtils.addOrUpdateFile(userId, userLevel, text);
	}
	public void updateUserAndPwd(String userId,String dialogPwd2) {
    	String textPath = CommonProperties.getStringProperty("userAndPwdPath");//模拟数据库文件路径
		String fileName ="";
		String textName = "userAndPwdFile";
		fileName = textPath+textName;
		FileModify fileModify=new FileModify();
		fileModify.write(fileName, fileModify.read(fileName,userId,dialogPwd2)); // 读取修改文件  
	}
	
	public void updateUserPwd(String userId,String dialogPwd2) {
    	String textPath = CommonProperties.getStringProperty("testFilePath");
		String fileName ="";
		String textName = System.currentTimeMillis()+"";
		//fileName = textPath+textName+".bat";
		fileName = textPath+textName+".sh";
		//String cmd = "net  user "+ userId+" "+ dialogPwd2;//window
		String cmd = "echo "+ userId+":"+ dialogPwd2+"|chpasswd";//linux
		writeText(fileName, cmd);
		try {
		 Runtime rn = Runtime.getRuntime();
		 Process process = rn.exec(fileName);
		 process.waitFor();
		System.out.println("finish:" +  process.waitFor());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void writeText(String fileName,String text){
		FileWriter fw = null;
		try{
			File file = new File(fileName);
			boolean isFileExit = file.exists();
			fw = new FileWriter(file,true);
			if(isFileExit)
			fw.write("\n");
			fw.write(text);
			fw.write("\n");
			fw.write("exit");
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(fw!=null)
				try {
					fw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	@SuppressWarnings("unused")
	private void removeFile(String fileName){
		
		try{
			File file = new File(fileName);
			if(file.exists()){
				file.delete();
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
