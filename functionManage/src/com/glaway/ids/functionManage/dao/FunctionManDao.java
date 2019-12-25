package com.glaway.ids.functionManage.dao;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class FunctionManDao {
	
	//private static final Logger Logger = LoggerFactory.getLogger(FunctionManDao.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	
	public void insertLog(String created_date,String user_name,String type,String context){
		String sql = "INSERT INTO FUNCTION_MANAGE_LOG (CREATED_DATE,USER_NAME,TYPE,CONTEXT) VALUES (?,?,?,?)";
		System.out.println("-----"+sql+"-----"+created_date+","+user_name+","+type+","+context);
		jdbcTemplate.update(sql,created_date,user_name,type,context);
	}
	
	public List<Map<String,Object>> selectLog(){
		String sql = "SELECT * FROM FUNCTION_MANAGE_LOG";
		System.out.println("-----"+sql+"-----");
		return jdbcTemplate.queryForList(sql);
	}
}
