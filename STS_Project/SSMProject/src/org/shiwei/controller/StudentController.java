package org.shiwei.controller;

import java.util.Map;

import org.shiwei.entity.Student;
import org.shiwei.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("controller")
@Controller
public class StudentController{
	
	@Autowired
	 private IStudentService  studentService;
	 public void setStudentService(IStudentService studentService) {
		this.studentService = studentService;
	}
	 
	@RequestMapping("queryStudentByNo/{id}")
	public  String queryStudentByNo(@PathVariable("id") Integer id,Map<String,Object> map) {
         // ������ ���� ��  Service 
		Student student = studentService.queryStudentByNo(id);
		
		map.put("student", student);
		
		return "success";
	}
	
	
}
		


