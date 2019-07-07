package org.shiwei.handler;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.shiwei.entity.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;


//���Ҫ��  request ���д��student4���� ��ͬʱ�� �ö��� ���뵽Session���С�
//@SessionAttributes(value="student4") //��һ��д������һ�� ������
//n @SessionAttributes(types={Student.class,Address.class})  // �ڶ���д���� �Ÿ����Ͷ���� ���ж���
// �ӿ�/ ��/ ע��   ����
@Controller
@RequestMapping(value="SpringMVC")  // ӳ��
public class SpringMVCHandler {
	// ����һ�������� welcome ʱ�� �ͱ��������� 
	// �� ���õ�ַ  Ϊ SpringMVC/welcome,������ģ����� ������
  /*
   * @RequestMapping("welcome/abc") , ����� ��ַΪ��" SpringMVC/welcome/abc"
   * ���еĲ�����  	method, ���� ����ʽ��
   *            params: ���ò�������ʾ��������ԣ� �����ô�����д�����
   *       -
   *       ���е� age!=23 ��ʾ age�����е� age ���� ֵ �� ������ 23��Ҳ����û��
   *       ��age , ��ʾ������ age ������ԡ�
   *       headers : ������Ӧͷ 
   */     
	
	//@RequestMapping(value="welcome",method=RequestMethod.POST,params = {"name=shi","age!=23"})
	@RequestMapping("welcome/{name}")
	public String welcome(@PathVariable("name") String name){
		System.out.println(name);
//		 ������ ��������  perfix �� suffix ---->/view/success.j
  // Ĭ�� ʹ���� ����ת�� ����ת ��ʽ��
		return "success";  
	}
	
	@RequestMapping(value="testRest/{name}",method=RequestMethod.POST)
	public String testPost(@PathVariable("name") Integer name){
		System.out.println(name+"post: ��");
		// Service ��ʵ�� ��������
		return "success";  
	}
	
	@RequestMapping(value="testRest/{name}",method=RequestMethod.GET)
	public String testGet(@PathVariable("name") Integer name){
		System.out.println(name+"get: ��ѯ");
		// Service ��ʵ�� ��������
		return "success";  
	}
	
	@RequestMapping(value="testRest/{name}",method=RequestMethod.DELETE)
	@ResponseBody
	public String testDelete(@PathVariable("name") Integer name){
		System.out.println(name+"delete: ɾ��");
		// Service ��ʵ�� ��������
		return "success";  
	}
	
	
	@RequestMapping(value="testRest/{name}",method=RequestMethod.PUT)
	@ResponseBody
	public String testPut(@PathVariable("name") Integer name){
		System.out.println(name+"put: �ı�");
		// Service ��ʵ�� ��������
		return "success";  
	}
	
	
	@RequestMapping(value="testParam")
	public String testParam(@RequestParam("uname") String name
			,@RequestParam(value="uage",required=false,defaultValue="100") Integer id){
		System.out.println(name);
		System.out.println(id);
		// Service ��ʵ�� ��������
		return "success";  
	}
	
	
	
	
	@RequestMapping(value="testRequestHeader")
	public String testRequestHeader(@RequestHeader("Cookie") String name){
		System.out.println(name);
		// Service ��ʵ�� ��������
		return "success";  
	}
	
	
	
	@RequestMapping(value="testCookieValue")
	public String testCookieValue(@CookieValue("JSESSIONID") String name){
		System.out.println("JSESSIONID = " + name);
		// Service ��ʵ�� ��������
		return "success";  
	}
	
	
	//�ö���student�� ����������� form �����еı�ǩ����name ֵһ��(֧�ּ�������)
	@RequestMapping(value="testObjectProperties")
	public String testObjectProperties(Student student){
		System.out.println(student);
		
		// Service ��ʵ�� ��������
		return "success";  
	}
	
	
	//ʹ��ԭ�� Servlet 
	@RequestMapping("testServletAPI")
	public String  testServletAPI(HttpServletRequest request,HttpServletResponse response) {
		String uname = request.getParameter("uname");
		System.out.println(uname);
		return "success";
		
	}
	
	
	//���ؼ��� ���� ���� ��ͼ   ModelAanView 
	@RequestMapping("testModelAndView")
	public ModelAndView  testModelAndView() {
		//ModelAndView  Model---M   ; Viwe -----V 
		/*
		 * :view(success) ���ַ�ʽ Ҳ������ ǰ׺�� ��׺
		 * �����췽�� ��ֵ�� ��ʱ �� View  ��ͼ ��ֵ�� 
		 */
		ModelAndView mv = new ModelAndView("success"); 
		Student stu = new Student();
		stu.setAge(200);
		stu.setName("shiei1");
		
		mv.addObject("student1",stu); // ������ Request ������   ����(���)�Զ��ŵ�
		return mv;
	}
	
	
	
	//���ؼ��� ���� ���� ��ͼ   ModelMap 
	@RequestMapping("testModelMap")
	public String  testModelMap(ModelMap modelmap) {
		Student stu = new Student();
		stu.setAge(200);
		stu.setName("shiei2");
		modelmap.put("student2",stu);  // �ŵ� Request ����ȥ��   ����(���)�Զ��ŵ�
		
		return "success"; // ����   View  ҳ��
	}
	
	
	
	//���ؼ��� ���� ���� ��ͼ   Map
	@RequestMapping("testMap")
	public String  testMap(Map<String, Object> map) {
		Student stu = new Student();
		stu.setAge(200);
		stu.setName("shiei3");
		map.put("student3",stu);  // �ŵ� Request ����ȥ��  ����(���)�Զ��ŵ�
		return "success";  // ���� View  ҳ�� 
	}
	
	
	@RequestMapping("testModel")
	public String  testModel(Model model) {
		Student stu = new Student();
		stu.setAge(200);
		stu.setName("shiei4");
		model.addAttribute("student4",stu);// �ŵ� Request ����ȥ��     ����(���)�Զ��ŵ�
		return "success";  //  ����  View  ҳ�� 
	}
	
	
	
	// �ڸ����е��κ�һ�ε�����ǰ�� ���� �� ִ�� @ModelAttribute ���εķ�����
	@ModelAttribute
	public void  queryStudentById(Map<String, Object> map) {
		//ģ�� ���� �����ѯ���ݿ�� ����
		Student student = new Student();
		student.setId(123);
		student.setAge(300);
		student.setName("shiwei");
		map.put("wang", student); //Լ���� map �� key����  �˷��� �Ķ�Ӧ �����Ĳ������͵� ����ĸСд��
		
	}
// �����һ�£� ��Ҫ �� ���÷��� �� ����������ǰ ���� @ModelAttribute("xxxx")
	
	//�޸�
	@RequestMapping("testModelAttribute")
	public String  testModelAttribute(@ModelAttribute("wang")Student student) {
		System.out.println("��ǰ��"+student);
		System.out.println(student.getId());
		System.out.println("�ĺ� ��"+student);
		return "success";  //  ����  View  ҳ�� 
	}
	
	//ͨ�� MVC ����   ʵ�� jsp  �� jsp   
/*	@RequestMapping("direct")
	public String jsp_jsp() {
		
		System.out.println(" û�� ����   �� Ŷ�� ");
		return "success";
	}
	*/
	
	@RequestMapping("forward")
	public String forword() {
		System.out.println(" ���� Forword !");
		return "forward:/view/success.jsp";
	}
	
	@RequestMapping("redirect")
	public String redirect() {
		System.out.println(" ���� redirect !");
		return "redirect:/view/success.jsp";
	}
	
	
	// ����ת����-
	@RequestMapping("testConverter")
	public String  testConverter(@RequestParam("studentInfo")Student student) {
		System.out.println(student);
		return "success";
	}
	
	
	// ���� ��ʽ�� 
/*@RequestMapping("dateFormatting")
	public String  dateFormatting(@Valid Student student, BindingResult  result,Map<String, Object> map) {
		//Լ���� ��� Student ��ʽ�� ������ �Ὣ������Ϣ ����ڶ��������� ---�� result  �С�
		// �൱  ��    �쳣 ���� 
		System.out.println("Birthday=" + student.getBirthday());
		System.out.println("Id=" + student.getId());
		System.out.println("Email=" + student.getEmail());
		if(result.getErrorCount()>0) {
			for (FieldError error : result.getFieldErrors()) {
				System.out.println(error.getDefaultMessage());
		         map.put("errors", result.getFieldErrors() );
		       
			}
		}
		return "success";
	}*/
	
         //ͨ�� ajax ����  Springmvc 
/*@ResponseBody�����ã�
 * ���� SpringMVC ,��ʱ �ķ��� ����һ�� View ҳ�棬����һ�� ajax ���õķ���ֵ��
 * ˭ ������  �� �� �� �� ���� ���ظ� ˭ 
 */
	    @ResponseBody
		@RequestMapping("testJson")
		public List<Student>  testJson() {
			// Controller - Service --dao 
			//StudentService studentService = new  StudentServiceImp();
			//List<Student> students = studentService.queryAllStudent();
			// ģ��  ���� service �Ĳ�ѯ����
			Student student  = new Student(1,"za",100);
			Student student2 = new Student(2,"ww",200);
			Student student3 = new Student(3,"xj",300);
			List<Student> students = new  ArrayList<Student>();
			students.add(student);
			students.add(student2);
			students.add(student3);
			
			return students;
		}
		
			
	    
//	        SpringMVC ʵ�� �ļ��� �ϴ�  
		@RequestMapping("testMultipartResolver")
		public String  multipartResolver(@RequestParam("desc") String desc,
				                         @RequestParam("file") MultipartFile file ) throws IOException {
			System.out.println("�ļ��� ���� ��Ϣ=" + desc);
			// jsp �� �ϴ� �� �ļ� �� file 
			//�õ� ��  ���õ�  һ�� �ˣ� �� �� ������ һ�� ��Ϣ ��
			InputStream input = file.getInputStream();
			// ��ȡ ��Ҫ�ϴ��� �ļ��� ԭʼ �ļ��� 
			String filename = file.getOriginalFilename();
			
			// ��ǰ׼���� һ�� ����� �����ļ� �ϴ��� �������˵� ĳһ��  Ӳ���ļ���  ��
			OutputStream out= new FileOutputStream("F:\\" + filename);
			
			//�ֽ���  �� �ֽ����� ��Ϊ ������
            byte[] bs = new byte[1024];
            int len = -1;
            //�ж��Ƿ� �������� 
            while((len = input.read(bs)) != -1) {
            	// ������ �ͽ� ����  д�� �����  �У��� ������  
            	out.write(bs, 0, len);
            }
            // �ر� �� 
			out.close();
			input.close();
			System.out.println("�ϴ� �ɹ���  ");
			return "success";
		}
	    


      // c����  ������ 
		@RequestMapping("testInterceptor")
		public String testInterceptor() {
			System.out.println(" ���������  ����......");
			return "success";
		}
		
		// ���� �쳣 �� �����������е��쳣 
		@RequestMapping("testException")
		public String testException() {
			System.out.println(1/0);
			return "success";
		}
		
		

   


	
	
	

}