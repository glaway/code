package com.glaway.ids.functionManage.properties;
 /** 
 * Created on 2016-6-18 ����9:49:40
 * Created by cnie 
 */

import java.io.IOException;
import java.io.InputStream;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

/**
 * @desription TODO
 * @author cnie
 * @date 2016-6-18 ����9:49:40
 */
public class CommonProperties {
	
	private static ResourceBundle _cfgResourceBundle = null;
	
	static {
		InputStream is = null;
		try{
			if(_cfgResourceBundle == null){
				is = CommonProperties.class.getResourceAsStream("vci.properties");
				if(is!=null)
					_cfgResourceBundle = new PropertyResourceBundle(is);
			}
		}catch(Exception ee){
			ee.printStackTrace();
		}finally{
			if(is!=null)
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
	}
	
	public static String getStringProperty(String strKey){
		return getPro(_cfgResourceBundle, strKey);
	}
	
	public static int getIntProperty(String strKey){
		int nResult = 0;
		String strValue = getPro(_cfgResourceBundle, strKey);
		try{
			nResult = Integer.valueOf(strValue).intValue();
		}catch (Exception e) {
			e.printStackTrace();
			nResult = -1;
		}
		return nResult;
	}
	
	
	private static String getPro(ResourceBundle rb,String key){
		try{
			return rb.getString(key).trim();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

}
