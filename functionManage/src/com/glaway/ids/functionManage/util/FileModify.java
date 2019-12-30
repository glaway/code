package com.glaway.ids.functionManage.util;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
  
/** 
 * 修改文件 
 */  
public class FileModify {  
  
    /** 
     * 读取文件内容 
     *  
     * @param filePath 
     * @return 
     */  
    public String read(String filePath,String userId,String pwd) {  
        BufferedReader br = null;  
        String line = null;  
        StringBuffer buf = new StringBuffer();  
          
        try {  
            // 根据文件路径创建缓冲输入流  
            br = new BufferedReader(new FileReader(filePath));  
              
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中  
            while ((line = br.readLine()) != null) {  
                // 此处根据实际需要修改某些行的内容  
                if (line.startsWith(userId)) {  
                	String[] tempArray=line.split("\\s+");
                    buf.append(tempArray[0].trim().toString()+" "+pwd+" "+tempArray[2].trim().toString());
                }  
                // 如果不用修改, 则按原来的内容回写  
                else {  
                    buf.append(line);  
                }  
                buf.append(System.getProperty("line.separator"));  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭流  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                }  
            }  
        }  
          
        return buf.toString();  
    }  
     
    /** 
     * 删除用户读取文件内容 
     *  模拟数据库文件
     * @param filePath 
     * @return 
     */  
    public String cancellRad(String filePath,String userId,String pwd1) {  
        BufferedReader br = null;  
        String line = null;  
        StringBuffer buf = new StringBuffer();  
          
        try {  
            // 根据文件路径创建缓冲输入流  
            br = new BufferedReader(new FileReader(filePath));  
              
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中  
            while ((line = br.readLine()) != null) {  
                // 此处根据实际需要修改某些行的内容  
                if (line.startsWith(userId)) {  
                    buf.append("");//设置为空
                }  
                // 如果不用修改, 则按原来的内容回写  
                else {  
                    buf.append(line);  
                }  
                buf.append(System.getProperty("line.separator"));  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭流  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                }  
            }  
        }  
          
        return buf.toString();  
    }
    
    /** 
     * 读取文件内容 
     *  
     * @param filePath 
     * @return 
     */  
    public String readLevel(String filePath,String userId,String level) {  
        BufferedReader br = null;  
        String line = null;  
        StringBuffer buf = new StringBuffer();  
          
        try {  
            // 根据文件路径创建缓冲输入流  
            br = new BufferedReader(new FileReader(filePath));  
              
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中  
            while ((line = br.readLine()) != null) {  
                // 此处根据实际需要修改某些行的内容  
                if (line.startsWith(userId)) {  
                	String[] tempArray=line.split("\\s+");
                    buf.append(tempArray[0].trim().toString()+" "+tempArray[1].trim().toString()+" "+level);
                }
                // 如果不用修改, 则按原来的内容回写  
                else {  
                    buf.append(line);  
                }  
                buf.append(System.getProperty("line.separator"));  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭流  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                }  
            }  
        }  
          
        return buf.toString();  
    } 
    /** 
     * 读取文件内容 ,用户是否存在
     *  
     * @param filePath 
     * @return 
     */  
    public boolean ifExitsUser(String filePath,String userId) {  
        BufferedReader br = null;  
        String line = null;  
        boolean b=false;
        try {  
            // 根据文件路径创建缓冲输入流  
            br = new BufferedReader(new FileReader(filePath));  
              
            // 循环读取文件的每一行, 对需要修改的行进行修改, 放入缓冲对象中  
            while ((line = br.readLine()) != null) {  
                // 此处根据实际需要修改某些行的内容  
                if (line.startsWith(userId)) { 
                	b=true;
                	break;
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭流  
            if (br != null) {  
                try {  
                    br.close();  
                } catch (IOException e) {  
                    br = null;  
                }  
            }  
        }  
          
        return b;  
    } 
    
    /** 
     * 将内容回写到文件中 
     *  
     * @param filePath 
     * @param content 
     */  
    public void write(String filePath, String content) {  
        BufferedWriter bw = null;  
          
        try {  
            // 根据文件路径创建缓冲输出流  
            bw = new BufferedWriter(new FileWriter(filePath));  
            // 将内容写入文件中  
            bw.write(content);  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // 关闭流  
            if (bw != null) {  
                try {  
                    bw.close();  
                } catch (IOException e) {  
                    bw = null;  
                }  
            }  
        }  
    }
}  