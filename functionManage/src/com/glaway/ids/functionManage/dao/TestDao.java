package com.glaway.ids.functionManage.dao;


import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class TestDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public String query() {
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM PROCESS");
		System.out.println(list.size());
		return list.toString();
	}
}
