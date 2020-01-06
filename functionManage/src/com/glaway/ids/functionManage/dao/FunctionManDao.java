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
	
	public List<Map<String,Object>> selectLog(String query_startDate,String query_endDate,String user_name,String type){
		System.out.println("查询类型:"+type+",查询人员:"+user_name+",查询时间:"+query_startDate+"-"+query_endDate);
		String sql = "SELECT * FROM FUNCTION_MANAGE_LOG WHERE 1=1";
		if(!"".equals(user_name)&&null!=user_name){
			sql += " AND USER_NAME LIKE '%"+user_name+"%'";
		}
		if(!"".equals(type)&&null!=type&&!" ".equals(type)){
			sql += " AND TYPE='"+type+"'"; 
		}
		if(!"".equals(query_startDate)&&null!=query_startDate){
			sql += " AND CREATED_DATE>'"+query_startDate+"'";
		}
		if(!"".equals(query_endDate)&&null!=query_endDate){
			sql += " AND CREATED_DATE<'"+query_endDate+"'";
		}
		System.out.println("-----"+sql+"-----");
		return jdbcTemplate.queryForList(sql);
	}
}
