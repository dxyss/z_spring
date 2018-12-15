package com.naver.erp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

//+++++++++++++++++++++++++++++++++++++++++++++++++
//가상 URL 주소로 접속하면 호출되는 메소드를 소유한 [컨트롤러 클래스] 선언
//+++++++++++++++++++++++++++++++++++++++++++++++++
//   @Controller 를 붙임으로써 [컨트롤러 클래스]임을 지정한다
//+++++++++++++++++++++++++++++++++++++++++++++++++
@Controller
public class LoginController {
	// 속성변수 loginService 선언하고, LoginService 라는 인터페이스를 구현한 클래스를 객체화하여 저장
		// @Autowired 가 붙은 속성변수에는 인터페이스 자료형을 쓰고
		// 이 인터페이스를 구현한 클래스를 객체화하여 저장한다.
		// LoginService 라는 인터페이스를 구현한 클래스의 이름을 몰라도 관계없다. 1개 존재하기만 하면 된다.
	@Autowired
	private LoginService loginService;
	
	
	// 가상주소 /erp/loginForm.do 로 접속하면 호출되는 메소드 선언
		// [컨트롤러 클래스]의 메소드에 @ResponseBody 가 없고, @RequestMapping이 붙고
		// 메소드의 리턴형이 String 일 경우 리턴하는 문자열은 호출할 JSP 페이지명이다.
	@RequestMapping(value="loginForm.do")
	public String loginForm(
			// [HttpSession 객체]가 들어올 매개변수 선언
			// 매개변수에 자료형이 HttpSession이면 웹서버가 생성한 HttpSession 객체가 들어온다.
			HttpSession session
	) {
			// HttpSession 객체에 저장된 기존 [로그인 아이디] 제거하기
			session.removeAttribute("admin_id");
			
				// <참고> HttpSession 객체에 저장된 [로그인 아이디]뿐만 아니라 모든 객체를 제거한다
				// session.invalidate();
			
			// [호출할 JSP 페이지명] 을 리턴한다
			return "loginForm";
	}
	
	
	
	@RequestMapping(value="/loginProc.do", method=RequestMethod.POST, produces="application/json;charset=UTF-8")
	@ResponseBody
	public int loginProc(
			@RequestParam(value="admin_id") String admin_id,
			@RequestParam(value="pwd")	String pwd,
			HttpSession session
	){
		
		int adminCnt=0;
		try {
			// 매개변수로 들어온 [로그인 아이디, 암호]를 HashMap 객체에 저장하기
			Map<String,String> admin_id_pwd = new HashMap<String,String>();
			admin_id_pwd.put("admin_id", admin_id);
			admin_id_pwd.put("pwd", pwd);
			
			// loginServiceImpl 객체의 메소드 호출로 [로그인 아이디의 존재 개수]를 얻기
			adminCnt = this.loginService.getAdminCnt(admin_id_pwd);
			// [로그인 아이디의 존재 개수]가 1이면 HttpSession 객체에 로그인 아이디 저장하기
			if(adminCnt==1) {
				session.setAttribute("admin_id", admin_id);
				
			}
			
		}catch(Exception e) {
			System.out.println("LoginController.loginProc() 에서 예외발생");
			adminCnt=-1;
		}
		return adminCnt;
	}
	


}