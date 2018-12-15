<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>.
<%@include file="/WEB-INF/views/common.jsp" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">

	// body 태그 안의 태그를 모두 실행한 후에 실행할 자스코드 설정
//	$(document).ready(function(){
//		<c:if test="${!empty param.b_no}">
//			$("[name=b_no]").val("${param.b_no}");
//		</c:if>
//	})

// [게시판 새글쓰기 화면]에 입력된 데이터의 유효성 체크 함수 선언
function checkBoardRegForm(){
	// 입력된 작성자명 가져와서 변수에 저장
	var writer = $("[name=writer]").val();
	// 작성자명이 입력되지 않았으면 경고하고 함수중단
	if(writer.split(" ").join("")==""){
		alert("이름을 입력해 주십시오");
		$("[name=writer]").focus();
		return;
	}
	// 입력된 [제목]을 가져와서 변수에 저장
	var subject = $("[name=subject]").val();
	// [제목]이 입력되지 않았으면 경고하고 함수 중단
	if(subject.split(" ").join("")==""){
		alert("제목을 입력해 주십시오");
		$("[name=subject]").focus();
		return;
	}
	// 입력된 [이메일]을 가져와서 변수에 저장
	var email = $("[name=email]").val();
	// [이메일]이 입력되지 않았으면 경고하고 함수 중단
	if(email.split(" ").join("")==""){
		alert("이메일을 입력해 주십시오");
		$("[name=email]").focus();
		return;
	}
	
	// 입력된 [게시판글]을 가져와서 변수에 저장
	var content = $("[name=content]").val();
	// [게시판글]이 입력되지 않았으면 경고하고 함수 중단
	if(content.split(" ").join("")==""){
		alert("내용을 입력해 주십시오");
		$("[name=content]").focus();
		return;
	}
	
	// 입력된 [암호]을 가져와서 변수에 저장
	var pwd = $("[name=pwd]").val();
	// [암호]이 입력되지 않았으면 경고하고 함수 중단
	if(pwd.split(" ").join("")==""){
		alert("암호를 입력해 주십시오");
		$("[name=pwd]").focus();
		return;
	}
	
	if( confirm("정말 저장하시겠습니까?")==false){return;}
	
	//alert($("[name=boardRegForm]").serialize());
	// 현재 화면에서 페이지 이동 없이 서버쪽 "/z_jsp/boardRegProc.do" 을 호출하여
	// 게시판글 입력 후 취할 행동이 있는  html 소스를 문자열로 받기
	$.ajax({
		// 호출할 서버쪽 URL 주소 설정
		url : "/erp/boardRegProc.do",
		// 전송 방법 설정
		type : "post",
		// 서버에 보낼 파라미터명과 파라미터값을 설정
		//data : {'admin_id':admin_id,'pwd':pwd},
		data : $("[name=boardRegForm]").serialize(),
		dataType : "json",
		
		// 서버의 응답을 성공적으로 받았을 경우 실행할 익명함수 설정
		// 익명함수의 매개변수 html 에는 서버가 응답한 [html 소스 문자열]이 들어온다
		success : function(insertCnt){
			// 서버가 응답한 html 소스 문자열을 현재페이지의
			// body 태그 마지막에 html로 삽입하고 실행하기
			alert(insertCnt);
			if(insertCnt>0){
				alert("게시판 새글쓰기 성공");
				location.replace("/erp/boardList.do");
			}else{
				alert("게시판 새글쓰기 실패..관리자에게 문의 바람..");
			}
		},
		// 서버의 응답을 못 받았을 경우 실행할 익명함수 설정
		error : function(){
			alert("서버와 비동기방식 접속 실패!");
		}
	});
}


</script>
</head>
<body><br><center>
<!-- [게시판 등록] 화면을 출력하는 form 태그 선언 -->
<form method="post" name="boardRegForm" action="/erp/boardRegProc.do">
	<c:if test="${empty param.b_no }">
		<b>[새 글쓰기]</b>
	</c:if>
	<c:if test="${!empty param.b_no }">
		<b>[댓글 쓰기]</b>
	</c:if>
	
	<table class="tbcss1" border="1" bordercolor=gray cellpadding="5" cellspacing="0" align="center">
		<tr>
			<th bgcolor=#C6C6C6>이름
			<td><input type="text" size="10" maxlength="10" name="writer">
		</tr>
		<tr>
			<th bgcolor=#C6C6C6>제목
			<td><input type="text" size="40" maxlength="50" name="subject">
		</tr>
		<tr>
			<th bgcolor=#C6C6C6>이메일
			<td><input type="text" size="40" maxlength="30" name="email">
		</tr>
		<tr>
			<th bgcolor=#C6C6C6>내용
			<td><textarea name="content" rows="13" cols="40"></textarea>
		</tr>
		<tr>
			<th bgcolor=#C6C6C6>비밀번호
			<td><input type="password" size="8" maxlength="12" name="pwd">
		</tr>
	</table>
	
	<table>
		<tr height=4><td>
	</table>
	
	<input type="hidden" name="b_no" value="${param.b_no }" >
	<input type="hidden" name="selectPageNo" value="${selectPageNo }" >
	
	<input type="button" value="저장" onClick="checkBoardRegForm()">
	<input type="reset" value="다시작성">
	<!--   <input type="button" value="목록보기" class="TEST" onClick="document.xxx.submit()"> -->
    <input type="button" value="목록보기" class="TEST" onClick="location.replace('/erp/boardList.do')">  	
</form>
<form name="xxx" action="/erp/boardList.do">
<input type="hidden" name="selectPageNo" value="${selectPageNo }" >

</form>
</body>
</html>