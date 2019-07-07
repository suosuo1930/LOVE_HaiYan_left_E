package org.wang.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.wang.entity.Monkey;
import org.wang.entity.MonkeyBusiness;
import org.wang.mapper.MonkeyMapper;

public class MonkeyTest {
	
	
	
	
	// һ��һ ���ϲ�ѯ ʹ�� ҵ�� ��չ�� 
	public static void queryMonkeyByOO() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = factory.openSession();
		MonkeyMapper mapper = session.getMapper(MonkeyMapper.class);
		
		MonkeyBusiness monkeyBusiness = mapper.queryMonkeyByOO(100);
		
		System.out.println(monkeyBusiness);
		
		
	}
	
	
	//һ��һ   ���� ��ѯ ʹ��  resultMap 
	public static void queryMonkeyByNo_resultMap_OO() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = factory.openSession();
		MonkeyMapper mapper = session.getMapper(MonkeyMapper.class);
		
		Monkey monkey = mapper.queryMonkeyByNo_resultMap_OO(300);
		
		System.out.println(monkey);
		
		
	}
	
	
	
	//һ��һ   ���� ��ѯ ʹ��  resultMap �� �ӳټ��� ��
	public static void queryMonkeyByNo_resultMap_OO_lazy() throws IOException{
		InputStream inputStream = Resources.getResourceAsStream("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
		SqlSession session = factory.openSession();
		MonkeyMapper mapper = session.getMapper(MonkeyMapper.class);
		
		 List<Monkey> lazy = mapper.queryMonkeyByNo_resultMap_OO_lazy(300);
		 
		 for(Monkey monkey: lazy){
			 System.out.println(monkey);
		 }
		 
		
		
		
	}
	
	
	public static void main(String[] args) throws IOException {
//		queryMonkeyByOO();
		
		queryMonkeyByNo_resultMap_OO_lazy();
	}
	
	

}