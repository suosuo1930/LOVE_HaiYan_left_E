package org.wang.test;

import java.io.IOException;
import java.io.Reader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.wang.entity.Address;
import org.wang.entity.Student;
import org.wang.mapper.StudentMapper;

public class test {

	// ��ѯ����ѧ��
	public static void queryAllStudents() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper student = session.getMapper(StudentMapper.class);

		List<Student> allStudent = student.queryAllStudents();

		System.out.println(allStudent);

	}

	// ����ѧ��
	public static void addStudent() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		Student student = new Student(150, "wu", 8888, "an");
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.addStudent(student);

		// û���ύ �Ͳ��� ���� ����
		session.commit();
		session.close();

	}

	// �޸�ѧ����Ϣ
	public static void updateStudentByStuno() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		Student student = new Student(10, "7777", 7777, "7777");

		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.updateStudentByStuno(student);

		// û���ύ �Ͳ��� ���� ����
		session.commit();
		session.close();
	}

	// ɾ��ѧ��
	public static void deleteStudentByStuno() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.deleteStudentByStuno(10);

		// û���ύ �Ͳ��� ���� ����
		session.commit();
		session.close();
	}

	// ����ѧ�ţ� ��ѯѧ��
	public static void queryStudentByStuno(int id) throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		//
		Student student = studentMapper.queryStudentByStuno(id);
		System.out.println(student);
		session.close();

	}

	// ���������� ��ѯѧ��
	public static void queryStudentByStuname(String name) throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		//
		Student student = studentMapper.queryStudentByStuname(name);
		System.out.println(student);
		session.close();

	}

	// �����ֶ��� ���� ��̬����
	public static void queryStudentOrderByColumn(String column) throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		//
		List<Student> student = studentMapper.queryStudentOrderByColumn(column);
		System.out.println(student);
		session.close();

	}

	// �������� ���� �� ���� ��ѯ ѧ��
	public static void queryStudentByStuageOrStuname() throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student student = new Student();
		student.setStuName("w");
		student.setStuAge(100);
/*		student.setStuNo(200);
		student.setStuSex(false);
		student.setGraName("wang");*/
		List<Student> stu = studentMapper.queryStudentByStuageOrStuname(student);
		System.out.println(stu);
		session.close();

	}
	
	// (�������  ʹ��hashMap)  �������� ���� �� ���� ��ѯ ѧ��
	public static void queryStudentByStuageOrStunameWithHashMap() throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Map<String, Object> studentMap = new HashMap<>();
		studentMap.put("stuAge",7777);
		studentMap.put("stuName", "w");

		
		List<Student> stu = studentMapper.queryStudentByStuageOrStunameWithHashMap(studentMap);
		System.out.println(stu);
		session.close();

	}
	
	
	//���� �洢���� ��ѯĳ���꼶 ��  ѧ��  ����
	public static void queryCountByGradeWithProcedure() throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Map<String, Object> studentMap = new HashMap<>();
		// ��������� ָ��ֵ 
		studentMap.put("gName",7777);
		

		//����   �洢 ����
		studentMapper.queryCountByGradeWithProcedure(studentMap);
	    // ��ȡ ��� ����  
		Object  params = studentMap.get("sCount");
		System.out.println(params);
		session.close();

	}
	
	
	
	
	//����  ѧ�� �� �洢����  ɾ��ѧ��  
		public static void deleteStuBynoWithPtocedure() throws Exception {
			// Connection --------SqlSession,��Ҫǰ�ö��������
			// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
			Reader reader = Resources.getResourceAsReader("conf.xml");
			// reader -----> SqlSession
			// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
			SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
			SqlSession session = factory.openSession();

			StudentMapper studentMapper = session.getMapper(StudentMapper.class);
			//ͨ�� Map �� �洢���� ��  ָ������ ���� ��
			Map<String, Object> studentMap = new HashMap<>();
			studentMap.put("sno",200); // ��������� ָ��ֵ 
			

			//����   �洢 ����
			studentMapper.deleteStuBynoWithPtocedure(studentMap);
			session.commit();
			session.close();

		}
		
		
		
		

	
	// ���ݼ�ͥ��ַ �� ѧУ ��ַ ��ѯ  ѧ��###           ʹ�ü�������
	public static void queryStudentByaddress() throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		
		//ʹ�ü�������
		Student student = new Student();
		Address add = new Address();
		add.setHomeAddress("500");
		add.setSchoolAddress("x");
		student.setAddress(add);
		
		List<Student> student2 = studentMapper.queryStudentByaddress(student);
		
		 for (Iterator iterator = student2.iterator(); iterator.hasNext();) {
			Student student3 = (Student) iterator.next();
			System.out.println(student3);
			
		}	

//      System.out.println(student2);
		session.close();

	}
	
	
	
	

	

	// ʹ���� ת���� (�����ݿ��� �� )
	// ����ѧ�ţ� ��ѯѧ��
	public static void queryStudentByStunoWithConverter(int id) throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		//
		Student student = studentMapper.queryStudentByStunoWithConverter(id);
		System.out.println(student);
		session.close();

	}

	// ʹ��ת���� �� �� ���ݿ��� ��
	// ����ѧ��
	public static void addStudentWithConverter() throws IOException {
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();

		Student student = new Student(190, "laixi", 106660, "qi");
		student.setStuSex(true);
		StudentMapper mapper = session.getMapper(StudentMapper.class);
		mapper.addStudentWithConverter(student);

		// û���ύ �Ͳ��� ���� ����
		session.commit();
		session.close();

	}
	  //��ѯ ѧ��  ����
	public static void queryStudentCount() throws IOException{
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();
		
		 StudentMapper mapper = session.getMapper(StudentMapper.class);
		 int count = mapper.queryStudentCount();
		 System.out.println("ѧ��  ����  = " + count);
	}
	
	//������� Ϊ  Map ���ͣ� 	ͨ������ ��Ϊ Map �� Key
	public static void  queryStudentOutByHashMap() throws IOException{
		Reader reader = Resources.getResourceAsReader("conf.xml");
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);
		SqlSession session = factory.openSession();
		
		 StudentMapper mapper = session.getMapper(StudentMapper.class);
		 HashMap<String,Object> hashMap = mapper.queryStudentOutByHashMap();
		 System.out.println(hashMap);
		
	}
	
	

	public static void main(String[] args) throws Exception {
//		queryAllStudents();
//		 queryStudentByStuname("7777");
		// queryAllStudents();
//		queryStudentByaddress();
//		queryStudentByStuageOrStunameWithHashMap();
//		queryStudentCount();
	   queryStudentOutByHashMap();

	}

}