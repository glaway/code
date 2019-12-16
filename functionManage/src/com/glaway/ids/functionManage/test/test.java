package com.glaway.ids.functionManage.test;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.glaway.ids.functionManage.dao.TestDao;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-mvc.xml.xml" }) // 加载Spring配置文件
*/public class test {

	/*@Autowired
	private TestDao testDao;

	@Test
	public void queryTest() {
		testDao.query();
	}*/
	private JdbcTemplate jdbcTemplate;
	private ApplicationContext context;
	
	{
		context = new ClassPathXmlApplicationContext("spring-mvc.xml");
		jdbcTemplate =(JdbcTemplate) context.getBean("jdbcTemplate");
	}
	
	@Test
	public void name() throws SQLException {
		DataSource dataSource = context.getBean(DataSource.class);
		System.out.println("1111"+dataSource.getConnection());
		List<Map<String, Object>> list = jdbcTemplate
				.queryForList("SELECT * FROM PROCESS");
		System.out.println("2222"+list.toString());
	}
}
