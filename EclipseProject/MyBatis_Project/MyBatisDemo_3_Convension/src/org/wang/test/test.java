package org.wang.test;

import java.io.IOException;
import java.io.Reader;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.wang.entity.Student;
import org.wang.mapper.StudentMapper;

public class test {

	// 查询所有学生
	public static void queryAllStudents() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper student = session.getMapper(StudentMapper.class);

		List<Student> allStudent = student.queryAllStudents();

		System.out.println(allStudent);

	}

	// 增加学生
	public static void addStudent() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		Student student = new Student(150, "wu", 8888, "an");
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.addStudent(student);

		// 没有提交 就不会 更新 数据
		session.commit();
		session.close();

	}

	// 修改学生信息
	public static void updateStudentByStuno() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		Student student = new Student(10, "7777", 7777, "7777");

		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.updateStudentByStuno(student);

		// 没有提交 就不会 更新 数据
		session.commit();
		session.close();
	}

	// 删除学生
	public static void deleteStudentByStuno() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.deleteStudentByStuno(10);

		// 没有提交 就不会 更新 数据
		session.commit();
		session.close();
	}

	// 根据学号， 查询学生
	public static void queryStudentByStuno(int id) throws Exception {
		// Connection --------SqlSession,需要前置对象来获得
		// 将 conf.xml 配置文件 读入到内存中去
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// 可以通过 build 的第二个参数 来强制 指定 数据库的 环境
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		//
		Student student = studentMapper.queryStudentByStuno(id);
		System.out.println(student);
		session.close();

	}

	// 使用了 转换器 (从数据库中 拿 )
	// 根据学号， 查询学生
	public static void queryStudentByStunoWithConverter(int id) throws Exception {
		// Connection --------SqlSession,需要前置对象来获得
		// 将 conf.xml 配置文件 读入到内存中去
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// 可以通过 build 的第二个参数 来强制 指定 数据库的 环境
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		//
		Student student = studentMapper.queryStudentByStunoWithConverter(id);
		System.out.println(student);
		session.close();

	}

	// 使用转换器 ， 向 数据库中 放
	// 增加学生
	public static void addStudentWithConverter() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		Student student = new Student(190, "laixi", 106660, "qi");
		student.setStuSex(true);
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.addStudentWithConverter(student);

		// 没有提交 就不会 更新 数据
		session.commit();
		session.close();

	}

	// 根据学号， 查询学生, 一级缓存 验证
	public static void queryStudentByStuno_cache_One() throws Exception {
		// Connection --------SqlSession,需要前置对象来获得
		// 将 conf.xml 配置文件 读入到内存中去
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// 可以通过 build 的第二个参数 来强制 指定 数据库的 环境
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		//
		Student student = studentMapper.queryStudentByStuno(150);

		session.commit();

		Student student2 = studentMapper.queryStudentByStuno(150);
		System.out.println(student);

		System.out.println(student2);
		session.close();

	}

	// 根据学号， 查询学生, 二级 缓存 验证
	public static void queryStudentByStuno_cache_Two() throws Exception {
		// Connection --------SqlSession,需要前置对象来获得
		// 将 conf.xml 配置文件 读入到内存中去
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// 可以通过 build 的第二个参数 来强制 指定 数据库的 环境
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);

		// 第一次查询
		SqlSession session = factory.openSession();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student student = studentMapper.queryStudentByStuno(150);
		System.out.println(student);
		session.close();// 进行缓存的 时机 ，一次性 刷进去

		// update   
/*		SqlSession updatesession = factory.openSession();
		StudentMapper updateStudent = updatesession.getMapper(StudentMapper.class);
		Student stud = new Student();
		stud.setStuNo(150);
		stud.setGraName("wangan");
		stud.setStuAge(20);
		
		updateStudent.updateStudentByStuno(stud);
//          	updatesession.commit();
		updatesession.close();
		*/
		

		// 第 2 次查询
		SqlSession session2 = factory.openSession();
		StudentMapper studentMapper2 = session2.getMapper(StudentMapper.class);
		Student student2 = studentMapper2.queryStudentByStuno(150);
		System.out.println(student2);
		session2.close();

		/*
		 * //第 3 次查询 SqlSession session3 = factory.openSession(); StudentMapper
		 * studentMapper3 = session3.getMapper(StudentMapper.class); Student
		 * student3 = studentMapper3.queryStudentByStuno(150);
		 * System.out.println(student3); session3.close();
		 * 
		 * 
		 * //第 4 次查询 SqlSession session4 = factory.openSession(); StudentMapper
		 * studentMapper4 = session4.getMapper(StudentMapper.class); Student
		 * student4 = studentMapper4.queryStudentByStuno(150);
		 * System.out.println(student4); session4.close();
		 */

	}

	public static void main(String[] args) throws Exception {
		queryStudentByStuno_cache_Two();
	}

}
