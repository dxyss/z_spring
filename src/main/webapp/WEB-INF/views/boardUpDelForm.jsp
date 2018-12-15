<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@include file="/WEB-INF/views/common.jsp" %>

<!-- 만얀 RequestServlet 객체에 boardDTO라는 키값으로 저장된 놈이 null이면 -->
<!-- 경고 메시지 보이고 게시판 목록 보기 화면으로 이동하기 -->
<!-- 즉 삭제된 게시판이면  경고 메시지 보이고 게시판 목록 보기 화면으로 이동하기 -->
<c:if test="${empty requestScope.boardDTO }">
<script language="JavaScript">
	alert("게시판 글이 삭제 되었습니다");
	location.replace("/erp/boardList.do");
</script>
</c:if>

<html>
<head><title>게시판</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script>
// [게시판 수정/삭제 화면]에 입력된 데이터의 유효성 체크 함수 선언
function checkBoardUpDelForm(upDel){
	if(upDel=="del"){
		var pwd = $("[name=pwd]").val();
		if(pwd.split(' ').join('')==''){
			alert("암호를 입력해 주십시오");
			$("[name=pwd]").focus();
			return;
		}
		document.boardUpDelForm.upDel.value="del";
		// $("[name=upDel]").val("del");
		if(confirm("정말 삭제 하시겠습니까?")==false){return;}
	}else if(upDel=='up'){
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
		if(confirm("정말 수정하시겠습니까?")==false){return ;}
	}
	// 현재 화면에서 페이지 이동 없이 서버쪽 "/z_jsp/boardUpDelProc.do"을 호출하여
	// [게시판 수정/삭제 행 적용개수]에 따른 명령어가 내장된 html 소스를 문자열로 받는다
	$.ajax({
		// 호출할 서버쪽 URL 주소 설정
		url : "/erp/boardUpDelProc.do",
		// 전송 방법 설정
		type : "post",
		// 서버에 보낼 파라미터명과 파라미터값을 설정
		//data : {'admin_id':admin_id,'pwd':pwd},
		data : $("[name=boardUpDelForm]").serialize(),
		
		// 서버가 무슨 파일로 응답하는지 지정
		// 서버가 응답할 페이지 종류, html 일 경우 생략 가능
		//datatype : "html",
		// 서버의 응답을 성공적으로 받았을 경우 실행할 익명함수 설정
		// 익명함수의 매개변수 html 에는 서버가 응답한 [html 소스 문자열]이 들어온다
		success : function(upDelCnt){
			// 서버가 응답한 html 소스 문자열을 현재페이지의
			// body 태그 마지막에 html로 삽입하고 실행하기
			//$("body").append(html);
			if(upDelCnt>0){
				alert("수정/삭제 성공");
			}else if(upDelCnt==0){
				alert("수정/삭제 실패! 비밀번호가 틀리거나 이미 삭제되었거나 자식글이 있어 불가능.");
			}
			else{
				alert("관리자에게 문의바람..");
			}
			location.replace("/erp/boardList.do");
		},
		// 서버의 응답을 못 받았을 경우 실행할 익명함수 설정
		error : function(){
			alert("서버 접속 실패!");
		}
	});
}
</script>
</head>
<body><br><center>
	<!-- [게시판 수정/삭제] 화면을 출력하는 form 태그 선언 -->
	<form method="post" name="boardUpDelForm" action="/erp/boardUpDelProc.do">
		<b>[글 수정/삭제]</b>
		<table class="tbcss1" border="1" bordercolor=gray cellspacing="0" cellpadding="5" align="center">
		<tr>
			<th bgcolor=#C6C6C6>작성자
			<td><input type="text" size="10" maxlength="10" name="writer" value="${boardDTO.writer }">
		</tr>
		<tr>
			<th bgcolor=#C6C6C6>제목
			<td><input type="text" size="40" maxlength="50" name="subject" value="${boardDTO.subject }">
		</tr>
		<tr>
			<th bgcolor=#C6C6C6>이메일
			<td><input type="text" size="40" maxlength="30" name="email" value="${boardDTO.email }">
		</tr>
		<tr>
			<th bgcolor=#C6C6C6>내용
			<td><textarea name="content" rows="13" cols="40">${boardDTO.content }</textarea>
		</tr>
		<tr>
			<th bgcolor=#C6C6C6>비밀번호
			<td><input type="password" size="8" maxlength="12" name="pwd">
		</tr>
		</table>
		<table><tr height=4><td></table>
		<input type="hidden" name="b_no" value="${boardDTO.b_no }">
		<input type="hidden" name="upDel" value="up">
		
		<input type="button"  value="수정 " onClick="checkBoardUpDelForm('up')">
		<input type="button"  value="삭제 " onClick="checkBoardUpDelForm('del')">
		<input type="button"  value="목록보기 " onClick="location.replace('/erp/boardList.do')">
		</form>
		
</body>
</html>