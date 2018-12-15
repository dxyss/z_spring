<!-- JSP 기술의 한 종류인 [Page Directive]를 이용하여 현 JSP 페이지 처리 방식 선언하기 -->
<!-- 현재 이 JSP 페이지 실행 후 생성되는 문서는 HTML이고, 이 문서는 UTF-8방식으로 인코딩 한다라고 설정함. -->
<!-- 현재 이 JSP 페이지는 UTF-8 방식으로 인코딩한다. -->
<!-- UTF-8 인코딩 방식은 한글을 포함 전 세계 모든 문자열을 부호화할 수 있는 방법이다. -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <!-- JSP 기술의 한 종류인 [include Directive]를 이용하여 common.jsp 파일 내의 소스를 삽입하기 -->
 <!-- JSP 페이지에서 공통으로 선언하는 코딩은 따로 뺀후 이렇게 수입하면 좋다 -->
 <%@include file="/WEB-INF/views/common.jsp" %>

<!-- C코어 커스텀 태그를 사용하여 HttpSession 객체에 msg 이란 키값으로 저장된 놈이 있으면 자바스크립트로 경고 문구 보여줘... -->
<c:if test="${!empty sessionScope.msg }">
	<script>
		alert("${sessionScope.msg}");
	</script>
	
	<!-- C코어 커스텀 태그를 사용하여 HttpSession 객체에  msg 란 키값으로 저장된 놈을 지우기 -->
	<c:remove var="msg" scope="session"/>
</c:if>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
<script>
	// body 태그 안의 소스를 모두 실행한 후에 실행할 자바스크립트 코드 설정
	$(document).ready(function(){
		
		// name=loginForm 가진 태그 안의 class=login 가진 태그에
		// click 이벤트 발생 시 실행할 코드 설정하기
		$("[name=loginForm] .login").click(function(){
			checkLoginForm();
		});
		// 아이디와 암호를 넣어주기
		// 테스트 시 아이디와 암호를 손으로 넣기 귀찮아서
		// 테스트가 끝나면 없애야함
		$("[name=admin_id]").val("abc");
		$("[name=pwd]").val("123");
	});
	
	// 아이디와 암호의 유효성을 체크하고 비동기 방식으로 웹서버와  통신하는 함수 선언
	function checkLoginForm(){
		// 입력한 [아이디]를 가져와 변수에 저장
		var admin_id = $(".admin_id").val();
	    //var admin_id = document.loginForm.admin_id.value;
		// 입력한 [암호]를 가져와 변수에 저장
		var pwd = $(".pwd").val();
		//var pwd = document.loginForm.pwd.value;
		//$(".msg").text(admin_id+ "/ "+pwd);
		
		// 관리자 아이디를 입력 안했으면 경고하고 함수 중단
		if(admin_id.split(" ").join()==""){
			alert("관리자 아이디 입력 요망");
			$("[name=admin_id]").val("");
			return;
		}
		
		// 관리자 암호를 입력 안했으면 경고하고 함수 중단
		if(pwd.split(" ").join()==""){
			alert("관리자 암호 입력 요망");
			$("[name=pwd]").val("");
			return;
		}
		
		// 동기 방식으로 웹서버 /z_jsp/loginProc.do 로 접속하기
		// 비동기를 쓰기로 했으므로 참조만 할 것
		// name="loginForm"을 가진 폼태그 내부의 
		// 입력양식값을 파라미터값을 가지고 
		// 웹서버에 /z_jsp/loginProc.do 로 접속하기
		//document.loginForm.submit();
		
		// 현재 화면에서 페이지 이동 없이(=비동기 방식으로)
		// 서버쪽 "/z_jsp/loginProc.do" 로 접속하여
		// HTML 소스를 응답 받아 실행하기
		$.ajax({
			// 서버쪽 호출 URL 주소 지정
			url : "/erp/loginProc.do",
			// form 태그 안의 데이터를 보내는 방법 지정
			type : "POST",
			// 서버에 보낼 파라미터명과 파라미터값을 설정
			data : {'admin_id':admin_id,'pwd':pwd},
			//data : $("form").filter("[name=loginForm]").serialize()
			// data : $("[name=loginForm]").serialize();
			// 서버가 무슨 파일로 응답하는지 지정
			// 서버가 응답할 페이지 종류, html 일 경우 생략 가능
			datatype : "json",
			// 서버의 응답을 성공적으로 받았을 경우 실행할 익명함수 설정
			// 익명함수의 매개변수 adminCnt 에는 서버가 응답한 [로그인 아이디의 존재 개수]가 들어온다
			success : function(adminCnt){
				// 로그인 아이디의 존재 개수가 1이면 환영 경고하고 연락처 검색화면으로 이동하기
				if(adminCnt==1){
					//alert("로그인 성공...");
					location.replace('/erp/contactSearchForm.do');
				}
				// 로그인 아이디의 존재 개수가 0개면 경고하기
				else if(adminCnt==0){
					alert("아이디/암호가 틀립니다.");
				}
				// 로그인 아이디의 존재 개수가 음수면 경고하기
				else if(adminCnt==-1){
					alert("서버 오류 발생... 관리자에게 문의 바람");
				}
			},
			// 서버의 응답을 못 받았을 경우 실행할 익명함수 설정
			error : function(){
				alert("서버 접속 실패!");
			}
		});
	}
</script>
</head>
<body>
<center><br><br><br>
	<div class="msg"></div>
	<!-- name="loginForm" 이 설정된 form 태그 선언 -->
	<!-- form 태그 내부에는 입력 양식이 있고 이 입력 양식에 입력/선택된 데이터가 -->
	<!-- form 태그 안의 action 에 설정된 URL 로 전송된다 -->
	<form name="loginForm" method="post" action="/erp/loginProc.do">
		<b>[로그인]</b>
		<table class="tbcss1" border=1 cellpadding=5 cellspacing=0 bordercolor="gray">
			<tr>
				<th align=center>아이디
				<td><input type="text" name="admin_id" class="admin_id" size="20">
			<tr>
				<th align=center>암 호
				<td><input type="password" class="pwd" name="pwd" size="20">
		</table>
		<div style="height:6px"></div>
		<input type="button" value="로그인" class="login">
		<input type="checkbox" name="is_login" value="y">아이디, 암호 기억
		
		<input type ="button" value="게시판 화면으로" onClick="location.replace('/erp/boardList.do')">
		
	</form>	
</body>
</html>