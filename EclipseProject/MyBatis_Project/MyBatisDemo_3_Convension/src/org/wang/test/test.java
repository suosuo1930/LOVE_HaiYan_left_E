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

	// ����ѧ�ţ� ��ѯѧ��, һ������ ��֤
	public static void queryStudentByStuno_cache_One() throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
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

	// ����ѧ�ţ� ��ѯѧ��, ���� ���� ��֤
	public static void queryStudentByStuno_cache_Two() throws Exception {
		// Connection --------SqlSession,��Ҫǰ�ö��������
		// �� conf.xml �����ļ� ���뵽�ڴ���ȥ
		Reader reader = Resources.getResourceAsReader("conf.xml");
		// reader -----> SqlSession
		// ����ͨ�� build �ĵڶ������� ��ǿ�� ָ�� ���ݿ�� ����
		SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(reader);

		// ��һ�β�ѯ
		SqlSession session = factory.openSession();
		StudentMapper studentMapper = session.getMapper(StudentMapper.class);
		Student student = studentMapper.queryStudentByStuno(150);
		System.out.println(student);
		session.close();// ���л���� ʱ�� ��һ���� ˢ��ȥ

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
		

		// �� 2 �β�ѯ
		SqlSession session2 = factory.openSession();
		StudentMapper studentMapper2 = session2.getMapper(StudentMapper.class);
		Student student2 = studentMapper2.queryStudentByStuno(150);
		System.out.println(student2);
		session2.close();

		/*
		 * //�� 3 �β�ѯ SqlSession session3 = factory.openSession(); StudentMapper
		 * studentMapper3 = session3.getMapper(StudentMapper.class); Student
		 * student3 = studentMapper3.queryStudentByStuno(150);
		 * System.out.println(student3); session3.close();
		 * 
		 * 
		 * //�� 4 �β�ѯ SqlSession session4 = factory.openSession(); StudentMapper
		 * studentMapper4 = session4.getMapper(StudentMapper.class); Student
		 * student4 = studentMapper4.queryStudentByStuno(150);
		 * System.out.println(student4); session4.close();
		 */

	}

	public static void main(String[] args) throws Exception {
		queryStudentByStuno_cache_Two();
	}

}