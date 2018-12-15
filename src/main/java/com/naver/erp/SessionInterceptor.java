

package com.naver.erp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;  
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
// URL ���� �� [��Ʈ�ѷ� Ŭ������ �޼ҵ�] ȣ�� �� �Ǵ� �Ŀ� 
// ����� �޼ҵ带 ������ [SessionInterceptor Ŭ����] ���� ����
//mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm
		//*************************************************
		// [HandlerInterceptorAdapter ��ü]�� �޼ҵ� ������ ȣ�� ����
		//*************************************************
				// preHandle()       : URL ���� �� ȣ��Ǵ� ��Ʈ�ѷ� Ŭ������ �޼ҵ� ���� ���� ȣ��. 
				//			           boolean ���� return �ϸ� Controller Ŭ������ Ư�� �޼ҵ� ���� ���θ� ����
				// postHandle()      : URL ���� �� ȣ��Ǵ� ��Ʈ�ѷ� Ŭ������ �޼ҵ� ���� ��, JSP �� ���� ���� ȣ��
				// afterCompletion() : URL ���� �� ȣ��Ǵ� ��Ʈ�ѷ� Ŭ������ �޼ҵ� ���� ��, JSP �� ���� �� ȣ���. 
				//				       responseBody�� �̿��� ��� ���� Ŭ���̾𿡰� ���� �� ȣ��

		//*************************************************
		// [HandlerInterceptorAdapter ��ü]�� �޼ҵ尡 ȣ��ǵ��� �����ϱ�
		//*************************************************
		//		<1> �������� �����ϴ� [HandlerInterceptorAdapter Ŭ����]�� ��� �޴´�.
		//		<2> HandlerInterceptorAdapter Ŭ������ preHandle �޼ҵ带 �������Ѵ�.
		// 		<3> servlset-context.xml ���Ͽ� �Ʒ� �±׸� �����Ѵ�
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
	// �ݵ�� HandlerInterceptorAdapter��ü�� ��ӹ޾ƾ� �Ѵ�.
	// URL ���� �� [��Ʈ�ѷ� Ŭ������ �޼ҵ�] ȣ�� ���� ����� preHandle(~) �޼ҵ� ����
	// �޼ҵ尡 false �� �����ϸ� �޼ҵ� ȣ�� �� ��Ʈ�ѷ��� �޼ҵ带 ȣ������ �ʰ�, �ݴ�� ȣ���Ѵ�.
	//*************************************************
	@Override
	public boolean preHandle(
		HttpServletRequest request 
		, HttpServletResponse response 
		, Object handler
	) throws Exception {
		//-------------------------------------
		// Session ��ü ���.
		// Session ��ü���� Ű���� admin_id �� ����� ������ ������. �� �α��� ���� ������
		//-------------------------------------
		HttpSession session = request.getSession();  
		String admin_id = (String) session.getAttribute("admin_id");		
		//-------------------------------------
		// Session ��ü�� �α��� ������ ������ Ŭ���̾� �̵��� URL �ּ� �����ϰ� false �����Ѵ�.
		// false ���� �� ��Ʈ�ѷ� Ŭ������ �޼ҵ带 ȣ������ �ʴ´�.
		//-------------------------------------
		if(admin_id==null) {
			session.setAttribute("msg", "�α����� �ؾ� ���� ����!");
			// HttpServletResponse ��ü�� sendRedirect �ޙ� ȣ��� �α��� ȭ������ �̵�. 
				//<����> �̵� URL �ּҿ��� ���ý���Ʈ���� /erp �� ���� ����
			response.sendRedirect("/erp/loginForm.do");
			return false;
		} 
		//-------------------------------------
		// Session ��ü�� �α��� ������ ������ true �����Ѵ�.
		// true ���� �� ���� ��Ʈ�ѷ��� �޼ҵ带 ȣ���Ѵ�..
		//-------------------------------------
		else { 
			return true;
		}
	}
}





















