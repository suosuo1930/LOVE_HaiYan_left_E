package org.shiwei.handler;



import org.shiwei.exception.MyArrayIndexOutofBoundsException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;




@Controller
@RequestMapping("SecondMVC")
public class SecondSpringMVCHandler {
	
	
	//�����쳣
	@RequestMapping("One")
	public  String testExceptionHandler() {
//		try {
			System.out.println(1/0);  //ArithmeticException�� �����쳣
	/*	} catch (Exception e) {
			// TODO: handle exception
		}*/
		
		return "success";
		
	}
	
	
	
	// ���� Խ�� �쳣
	@RequestMapping("Three")
	public  String testExceptionHandler2() {
        int[] ne =  new int[2];
        System.out.println(ne[2]);
		return "success";
		
	}
	
	
	
	
/*	
	
	@ExceptionHandler({Exception.class})
	public ModelAndView handlerArithmeticException2(ArithmeticException e) {
		System.out.println("222222�Ҳ�����=-----" + e);
		ModelAndView modelAndView = new  ModelAndView("error");
		modelAndView.addObject("e",e);
		return modelAndView;
	}*/
	
/*	@ExceptionHandler({ArithmeticException.class})
	public ModelAndView handlerArithmeticException1(ArithmeticException e) {
		System.out.println("111111�Ҳ�����=-----" + e);
		ModelAndView modelAndView = new  ModelAndView("error");
		modelAndView.addObject("e",e);
		return modelAndView;
	}*/
	
	
	
	
	
	
	
/*	// �÷��� ���Բ��� ������ �׳���  ArithmeticException �쳣
	@ExceptionHandler({ArithmeticException.class,ArrayIndexOutOfBoundsException.class})
	public ModelAndView handlerArithmeticException(ArithmeticException e) {
		System.out.println("�Ҳ�����=-----" + e);
		ModelAndView modelAndView = new  ModelAndView("error");
		modelAndView.addObject("e",e);
		map.put("e", e);
		return modelAndView;
	}
	*/
	
	
	// ���� Խ�� �쳣
	@RequestMapping("defineExcetpion1")
	public  String testSelfDefined(@RequestParam("i") Integer i ) throws MyArrayIndexOutofBoundsException {
		if(i == 3) {
			throw new MyArrayIndexOutofBoundsException();  //  �׳��쳣 
		}
		return "success";
		
	}
	
	
	@RequestMapping("defineExcetpion2")
	public  String testSelfDefined2(@RequestParam("i") Integer i )  {
		if(i == 3) {
			//ȥ�� ǰ׺�ͺ� ׺ 
			return "redirect:testResponseStatus";
		}
		return "success";
		
	}
	
	//�Զ��� �����쳣����
	@ResponseStatus(value=HttpStatus.CONFLICT,
			        reason="�����Զ��� �����쳣��������⵽�� ��������Խ����ѽ��  ")
	@RequestMapping("testResponseStatus")
	public  String testResponseStatus()  {	
		return "success";
	}
	
	// ʹ�� �����쳣 �� 
	@RequestMapping("SimpleMappingExceptionResolver")
	public String testSimpleMappingExceptionResolver() {
		System.out.println(1/0);
		return "error";
	}
	
	
	
	
	
	

}