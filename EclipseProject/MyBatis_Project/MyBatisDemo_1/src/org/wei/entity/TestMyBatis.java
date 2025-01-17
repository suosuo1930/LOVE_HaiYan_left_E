package org.wei.entity;

import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class TestMyBatis {
	public static void main(String[] args) throws Exception {
		// 加载 MyBatis 配置文件， （为了访问数据库）

		Reader	reader = Resources.getResourceAsReader("conf.xml");

		// SqlSessionFactory ---
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		// 相当于以前 的 connection
		SqlSession session = factory.openSession();
		/*
		 * 执行 SQL 语句 ， 步骤： 1. 找到 Mapper 文件，通过其中的<mapper>的 namespace +<select> 的
		 * id
		 */
		
			String statement = "org.wei.entity.personMapper.queryPersonById";
			Person person = session.selectOne(statement, 1);
			System.out.println(person);
			session.close();

		

	}

}
