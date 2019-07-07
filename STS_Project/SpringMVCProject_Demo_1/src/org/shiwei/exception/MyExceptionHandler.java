package org.shiwei.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



//���� �ǿ������� ������  ���� �����쳣 ����  
/*@RequestMapping("ExceptionHandler")*/
//@ControllerAdvice
public class MyExceptionHandler {    
	
	
	@ExceptionHandler({Exception.class})
	public ModelAndView handlerArithmeticException2(ArithmeticException e) {
		System.out.println("��ע�и�@ControllerAdvice����  ������=-----" + e);
		ModelAndView modelAndView = new  ModelAndView("error");
		modelAndView.addObject("e",e);
		return modelAndView;
	}
	
	
	

}