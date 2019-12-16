package com.glaway.ids.functionManage.controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class test {

	public static  List<Map<String,String>> queryuserData=new ArrayList<Map<String,String>>();//权限数据
	public static void main(String[] args) {
		try {
			String tempString="2013-10-11 19:54:32	EV5ADM@VPMADMIN.ADMIN.DEFAULT	创建零件	Part101102.2	Part101102[1]	---	父节点:Product032101.1";
//			String[] tempStringArray=a.split("\t");
//			System.out.println(tempStringArray[0].substring(0, 10)); 	
			 tempString=tempString.replaceAll("EV5ADM", "vpmsysadmin");
			 System.out.println(tempString);
			 System.out.println((int)(1+Math.random()*(1000)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void writeText(String newfileName,String text){
		FileWriter fw = null;
		try{
			File file = new File(newfileName);
			if(!file.exists()){
				file.createNewFile();
			}
			fw = new FileWriter(file,true);
			fw.write(text);
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

}
