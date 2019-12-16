 /** 
 * Created on 2016-6-19 ����3:43:04
 * Created by cnie 
 */
package com.glaway.ids.functionManage.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @desription TODO
 * @author cnie
 * @date 2016-6-19 ����3:43:04
 */
public class ProcessClearStream extends Thread{

	private InputStream inputStream;

	private String type;

	ProcessClearStream(InputStream inputStream, String type) {
		this.inputStream = inputStream;
		this.type = type;
	}

	@Override
	public void run() {
		try{
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader br = new BufferedReader(inputStreamReader);
			String line = null;
			while ((line = br.readLine()) != null) {
			System.out.println(type +">"+ line);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}
	
	

}
