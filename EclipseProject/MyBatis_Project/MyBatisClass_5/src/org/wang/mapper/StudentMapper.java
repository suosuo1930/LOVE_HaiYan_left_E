package org.wang.mapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.wang.entity.Student;

// ������ MyBatis �Ľ�� 
public interface StudentMapper {
	/*
	 * 1. ������ �� mapper.xml �ļ� �б�ǩ ��id ֵ ��ͬ
	 * 2. ������ �β� ��  mapper.xml �ļ��� ��ǩ�� parameterType ����һ��
	 * 3. �����ķ���ֵ  �� mapper.xml �ļ���  ��ǩ �� resultType  ����һ�¡�
	 *   
	 */
	  //��ѯһ��, ����ѧ��
	 Student queryStudentByStuno(int stuno);
	 
	 // ��ѯ һ��ѧ���� ��������
	 Student  queryStudentByStuname(String name);
	 
	 // �����ֶ��� ���� ��̬����
	 List<Student> queryStudentOrderByColumn(String column);
	 
	 //���� ���� ���� �� ���� ��ѯѧ��  
	 List<Student>  queryStudentByStuageOrStuname(Student student);
	 
	 //ʹ��    HashMap 
	 List<Student> queryStudentByStuageOrStunameWithHashMap(Map<String,Object> map);
	 
	 //���� �洢���� ��ѯĳ���꼶 ��  ѧ��  ����
	 void  queryCountByGradeWithProcedure(Map<String,Object> params);
	 
	 //  ͨ�� �洢���� ʵ��  ɾ��  
	 void deleteStuBynoWithPtocedure(Map<String,Object> params);

	 
	 
	 
	 
	
	 // ���ݼ�ͥ��ַ �� ѧУ ��ַ ��ѯ  ѧ��
	 List<Student> queryStudentByaddress(Student student);
	 
	 
	 //����
	  void  addStudent(Student student);
	  //ɾ��
	  void deleteStudentByStuno(int id);
	  // ����
	  void updateStudentByStuno(Student student);
	  // ��ѯ���� ѧ��
	  List<Student>  queryAllStudents();
	  
	  
	  //ʹ��������ת�������� stuNo ��ѯѧ��
	  Student queryStudentByStunoWithConverter(int stuno);
	  
      //ʹ������ת���� ���� ѧ��
	  void addStudentWithConverter(Student student);
	  
	  // ��ѯ ѧ��  ����
	  int queryStudentCount();
	  
	  //������� Ϊ  Map ���ͣ� 	ͨ������ ��Ϊ Map �� Key
	  HashMap<String,Object> queryStudentOutByHashMap();
	  
}