

package com.naver.erp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
// URL 접속 시 [컨트롤러 클래스의 메소드] 호출 전 또는 후에 
// 실행될 메소드를 소유한 [SessionInterceptor 클래스] 선언 선언
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
		//*************************************************
		// [HandlerInterceptorAdapter 객체]의 메소드 종류와 호출 시점
		//*************************************************
				// preHandle()       : URL 접속 시 호출되는 컨트롤러 클래스의 메소드 실행 전에 호출. 
				//			           boolean 값을 return 하며 Controller 클래스의 특정 메소드 실행 여부를 결정
				// postHandle()      : URL 접속 시 호출되는 컨트롤러 클래스의 메소드 실행 후, JSP 를 실행 전에 호출
				// afterCompletion() : URL 접속 시 호출되는 컨트롤러 클래스의 메소드 실행 후, JSP 를 실행 후 호출됨. 
				//				       responseBody를 이용할 경우 값을 클라이언에게 전달 후 호출

		//*************************************************
		// [HandlerInterceptorAdapter 객체]의 메소드가 호출되도록 설정하기
		//*************************************************
		//		<1> 스프링이 제공하는 [HandlerInterceptorAdapter 클래스]를 상속 받는다.
		//		<2> HandlerInterceptorAdapter 클래스의 preHandle 메소드를 재정의한다.
		// 		<3> servlset-context.xml 파일에 아래 태그를 삽입한다
		//*************************************************
				//		<interceptors>
				//		<interceptor>
				//			<mapping path="/**/*" />
				//		    <exclude-mapping path="/loginForm.do"/>
				//		    <exclude-mapping path="/loginProc.do"/>
				//		    <beans:bean class="com.naver.erp.SessionInterceptor"></beans:bean>            
				//		</interceptor>        
				//		</interceptors>
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
public class SessionInterceptor extends HandlerInterceptorAdapter {	
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
	//*************************************************
	// 반드시 HandlerInterceptorAdapter객체를 상속받아야 한다.
	// URL 접속 시 [컨트롤러 클래스의 메소드] 호출 전에 실행될 preHandle(~) 메소드 선언
	// 메소드가 false 를 리턴하면 메소드 호출 후 컨트롤러의 메소드를 호출하지 않고, 반대면 호출한다.
	//*************************************************
	@Override
	public boolean preHandle(
		HttpServletRequest request 
		, HttpServletResponse response 
		, Object handler
	) throws Exception {
		//-------------------------------------
		// Session 객체 얻기.
		// Session 객체에서 키값이 admin_id 로 저장된 데이터 꺼내기. 즉 로그인 정보 꺼내기
		//-------------------------------------
		HttpSession session = request.getSession();  
		String admin_id = (String) session.getAttribute("admin_id");		
		//-------------------------------------
		// Session 객체에 로그인 정보가 없으면 클라이언가 이동할 URL 주소 설정하고 false 리턴한다.
		// false 리턴 시 컨트롤러 클래스의 메소드를 호출하지 않는다.
		//-------------------------------------
		if(admin_id==null) {
			session.setAttribute("msg", "로그인을 해야 접속 가능!");
			// HttpServletResponse 객체의 sendRedirect 메솓 호출로 로그인 화면으로 이동. 
				//<주의> 이동 URL 주소에서 컨택스루트명인 /erp 를 빼지 말것
			response.sendRedirect("/erp/loginForm.do");
			return false;
		} 
		//-------------------------------------
		// Session 객체에 로그인 정보가 있으면 true 리턴한다.
		// true 리턴 시 이후 컨트롤러의 메소드를 호출한다..
		//-------------------------------------
		else { 
			return true;
		}
	}
}





















