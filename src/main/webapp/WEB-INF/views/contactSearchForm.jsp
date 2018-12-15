<!--mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm-->
<!--JSP 기술의 한 종류인 [Page Directive] 를 이용하여 현 JSP 페이지 처리 방식 선언하기-->
<!--mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm-->
	<!--현재 이 JSP 페이지 실행 후 생성되는 문서는 HTML이고, 이문서는 UTF-8 방식으로 인코딩 한다 라고 설정함.-->
	<!--현재 이 JSP 페이지는 UTF-8 방식으로 인코딩 한다-->
	<!--UTF-8 인코딩 방식은 한글을 포함 전 세계 모든 문자열을 부호화할수 있는 방법이다.-->
<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!--mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm-->
<!--JSP 기술의 한 종류인 [Include Directive]를 이용하여 common2.jsp 파일 내의 소스를 삽입하기-->
<!--mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm-->
<%@include file="common.jsp"%>

<html><head><title>연락검색</title></head>
<script>

	//*************************************************
	// body 태그를 모두 실행한 후에 실행할 자스 코드 설정
	//*************************************************
	$(document).ready(function(){
		// name=contactSearchForm 을 가진 form 태그를 관리하는 JQuery 객체 구하기
		var contactSearchFormObj=$("[name=contactSearchForm]");
		
		
		// [행보기] 목록 상자에서 change 이벤트시 실행 할 구문 설정하기
		contactSearchFormObj.find("[name=rowCntPerPage]").change(function(){
		//	alert(contactSearchFormObj.find("[name=rowCntPerPage]").val()); return;
			// goContactSearchForm() 함수 호출하기
			goContactSearchForm();
		});
		
		// [검색] 버튼에 클릭 실행할 구문 설정하기
		contactSearchFormObj.find(".searchContact").click(function(){
			// goContactSearchForm() 함수 호출하기
			goContactSearchForm();
		});
		
		// [모두검색] 버튼에 클릭 이벤트 시 실행할 구문 설정하기
		contactSearchFormObj.find(".searchContactAll").click(function(){
			// 검색 조건 관련 입력 양식의 데이터를 비우거나 체크를 풀기
			// <주의>type=button, name=selectPageNo, name=rowCntPerPage 를 가진 입력 양식은 제외한다.
			
			/*	
			<방법 1>
			var selector = "[name=min_reg_year],[name=min_reg_month],[name=max_reg_year],[name=max_reg_month],[name=keyword1],[name=sort],[name=sort_multi]";
			contactSearchFormObj.find(selector).val('');
			contactSearchFormObj.find("[name=saup_field]").prop("checked",false);
			
			<방법 2>
			contactSearchFormObj.find("[name=min_reg_year]").val('');
			contactSearchFormObj.find("[name=min_reg_month]").val('');
			contactSearchFormObj.find("[name=max_reg_year]").val('');
			contactSearchFormObj.find("[name=max_reg_month]").val('');
			contactSearchFormObj.find("[name=keyword1]").val('');
			contactSearchFormObj.find("[name=sort]").val('');
			contactSearchFormObj.find("[name=sort_multi]").val('');
			
			contactSearchFormObj.find("[name=saup_field]").prop("checked",false); */
			
			//방법 3
			var selector = "[type=button],[name=selectPageNo],[name=rowCntPerPage]";
			contactSearchFormObj.find("input, textarea, select").not(selector).each(function(){
				if($(this).is("[type=checkbox]") || $(this).is("[type=radio]"))
					$(this).prop("checked",false)
				else
					$(this).val('');
			});
			goContactSearchForm();
		});
		
		// 연락처 등록일 날짜 검색 조건 입력 양식에서 onChange 이벤트가 발생하면 실행할 코드 설정
		//	name 속성값이 min_reg_ 또는 max_reg_로 시작하는 select 태그 4개에서 change 이벤트가 발생하면 실행할 코드설정
		contactSearchFormObj.find("[name^=min_reg_],[name^=max_reg_]").change(function(){
			// 연락처 등록일 날짜 검색 조건 입력 양식의 유효성 체크함수 호출
			check_reg_date();
	//		goContactSearchForm();
		});
		
		
		// [name=sort_multi]를 가진 입력양식에 change 이벤트가 발생하면 실행할 코드 설정하기
		contactSearchFormObj.find("[name=sort_multi]").change(function(){
			// 멀티 정렬 데이터 가져오기
			var sort_multi = contactSearchFormObj.find("[name=sort_multi]").val();
			// 멀티 정렬 데이터가 있으면 name=sort 을 가진 hidden 태그에 값을 저장
			if(sort_multi!=''){
				contactSearchFormObj.find("[name=sort]").val(sort_multi);
			}
			goContactSearchForm();
		});
		
		// [이번달로] 글씨를 클릭하면 실행할 구문 설정
		contactSearchFormObj.find(".to_today1").click(function(){
			// 현재 날짜를 관리하는 Date 객체 생성해서 객체의 메모리위치주소를 today에 저장
			//	2002년 2월 마지막날 출력 객체 생성
			// var today = new Date(2002, 2, 0);
			var today = new Date();
			// 현재 날짜의 년도 얻기
			var year = today.getFullYear();
			// 현재 날짜의 월 얻기, <주의> 월을 얻고나서 1을 더할 것
			var month = today.getMonth()+1;  if(month<10){month="0"+month}
			// 현재 날짜의 년을 name=min_reg_year 을 가진 입력양식에 넣어주기
			contactSearchFormObj.find("[name=min_reg_year]").val(year);
			// 현재 날짜의 월을 name=min_reg_month 을 가진 입력양식에 넣어주기
			contactSearchFormObj.find("[name=min_reg_month]").val(month);
			// check_reg_date() 함수 호출하여 날짜 범위의 유효성 체크하기
			check_reg_date();
			goContactSearchForm();
		});
		contactSearchFormObj.find(".to_today2").click(function(){
			var today = new Date();
			var year = today.getFullYear();
			var month = today.getMonth()+1;  if(month<10){month="0"+month}
			contactSearchFormObj.find("[name=max_reg_year]").val(year);
			contactSearchFormObj.find("[name=max_reg_month]").val(month);
			check_reg_date();
			goContactSearchForm();
		});
		
		// [비움], [모두비움] 글씨를 클릭하면 실행할 구문 설정
		contactSearchFormObj.find(".empty1").click(function(){
			// name 속성 값이 min_reg_로 시작하는 입력양식의 value 값을 비우기
			// JQuery 선택자에서 ^는 '시작'이라는 의미를 가진다
			// JQuery 선택자에서 $는 '끝'이라는 의미를 가진다
			// JQuery 선택자에서 *는 '해당단어 들어감'이라는 의미를 가진다
			contactSearchFormObj.find("[name^=min_reg_]").val('');
		});
		contactSearchFormObj.find(".empty2").click(function(){
			contactSearchFormObj.find("[name^=max_reg_]").val('');
		});
		contactSearchFormObj.find(".emptyAll").click(function(){
			contactSearchFormObj.find("[name^=min_reg_],[name^=max_reg_]").val('');
		});
		
//		contactSearchFormObj.find("[name=saup_field]").change(function(){
			//goContactSearchForm();
//			$(".searchContact").click();
			
//		});
//		contactSearchFormObj.find("[name=keyword1]").keyup(function(){
//			goContactSearchForm();
			//contactSearchFormObj.find("[name=keyword1]").focus();
			//$(".searchContact").click();
//		});
		
		
		// 연락처 검색 결과물의 짝, 홀수행 배경색 달리하기
		// 즉 id=aaa 를 가진 태그의 후손 태그중 1번쨰 tr 태그 이후 tr 태그들에
		// 차례대로 접근하여 bgcolor 의 인덱스 번호가 짝수인 tr의 속성값을 #CFCFCF 로 설정한다. 
		$("#aaa").find("tr:eq(0)").nextAll().each(function(){
			// id=aaa 를 가진 태그의 후손 중 tr태그에서 1번째를 뺀 나머지 tr 태그 중 
			// i 번째 tr 태그를 관리하는 JQuery 객체 생성하여 obj에 저장
			var obj = $(this);
			// i 번째 tr 태그가 형제 tr태그 중 위치하는 인덱스 번호 얻기
			// 즉 i 번째 tr 태그의 형제 서열 인덱스 번호 얻기
			// index메소드는 0으로 시작
			var index=$(this).index();
			// 형제 서열 인덱스 번호가 홀수면 배경색을 #CFCFCF로 설정하기
			// tr 태그에 bgcolor=#CFCFCF 로 설정하여 배경색을 바꾸는 원리이다
			if(index%2==1){
				obj.attr("bgcolor","#CFCFCF")
			}
		});
		// 연락처 검색 결과물의 행에 마우스 대면 색상 변하고 띠면 원래대로 돌리기
		$("#aaa").find("tr:eq(0)").nextAll().hover(
				function(){
					var obj = $(this);
					obj.attr("bgcolor","#A2A2A2");
				},function(){
					var obj = $(this);
					var index = $(this).index();
					if(index%2==1){
						obj.attr("bgcolor","#CFCFCF");
					}else{
						obj.attr("bgcolor","white");
					}
				})
	
	//	$('#aaa  tr:even').css('background','#AAAAAA');
	//	$('#aaa   tr:odd').css('background','#FFFFFF');
		// 파라미터값을 입력 양식에 삽입하여 [검색조건] 및 기타 입력 양식에
		// 이전 화면에서의 데이터 흔적 남기기
		// 사업분야의 체크 흔적 남기기
		// 파라미터값으로 들어온 사업분야의 데이터를 가진 체크박스에 체크를 넣어주기 
		<c:forEach var="saup_field" items="${paramValues.saup_field}">
			contactSearchFormObj.find("[name=saup_field]").filter("[value=${saup_field}]").attr("checked",true);
		</c:forEach>
		contactSearchFormObj.find("[name=min_reg_year]").val("${param.min_reg_year}");
		contactSearchFormObj.find("[name=min_reg_month]").val("${param.min_reg_month}");
		contactSearchFormObj.find("[name=max_reg_year]").val("${param.max_reg_year}");
		contactSearchFormObj.find("[name=max_reg_month]").val("${param.max_reg_month}");
		contactSearchFormObj.find("[name=keyword1]").val("${param.keyword1}").focus();
		contactSearchFormObj.find("[name=sort]").val("${param.sort}");
		contactSearchFormObj.find("[name=sort_multi]").val("${param.sort_multi}");
		
		
		contactSearchFormObj.find("[name=selectPageNo]").val("${contactSearchDTO.selectPageNo}");
		$("[name=contactUpDelForm]").find("[name=selectPageNo]").val("${contactSearchDTO.selectPageNo}");
		contactSearchFormObj.find("[name=rowCntPerPage]").val("${contactSearchDTO.rowCntPerPage}");
		//alert(contactSearchFormObj.find("[name=rowCntPerPage]").val()); return;
		
//		contactSearchFormObj.find("[name=selectPageNo]").val(
//			"$(empty param.selectPageNo?1:param.selectPageNo)"		
//		);
//		contactSearchFormObj.find("[name=rowCntPerPage]").val(
///				"$(empty param.rowCntPerPage?10:param.rowCntPerPage)"		
//		);
	}); 
			
	//*************************************************
	// 연락처 등록일 검색 조건 관련 태그에서 change 이멘트 발생 시 실행할 코드 설정
	//*************************************************
	function check_reg_date(){
		var contactSearchFormObj=$("[name=contactSearchForm]");
		//----------------------------------
		var min_reg_year = contactSearchFormObj.find("[name=min_reg_year]").val( );
		var min_reg_month = contactSearchFormObj.find("[name=min_reg_month]").val( );
		var max_reg_year = contactSearchFormObj.find("[name=max_reg_year]").val( );
		var max_reg_month = contactSearchFormObj.find("[name=max_reg_month]").val( );
		
		
		alert("min_reg_year=>"+min_reg_year+"/ min_reg_month=>"+min_reg_month+"/ max_reg_month=>"+max_reg_month+"/ max_reg_year=>"+max_reg_year);
		
		//----------------------------------
		if( (min_reg_year==null || min_reg_year.length==0) && (min_reg_month!=null && min_reg_month.length>0) ){
			alert("왼쪽 년도를 먼저 선택해야 합니다.");
			contactSearchFormObj.find("[name=min_reg_month]").val( "" );
			return;
		}
		if( (max_reg_year==null || max_reg_year.length==0) && (max_reg_month!=null && max_reg_month.length>0) ){
			alert("왼쪽 년도를 먼저 선택해야 합니다.");
			contactSearchFormObj.find("[name=max_reg_month]").val( "" );
			return;
		}
		
		//----------------------------------
		if(  (min_reg_year!=null && min_reg_year.length>0)  && (min_reg_month==null || min_reg_month.length==0) ){
			contactSearchFormObj.find("[name=min_reg_month]").val( "01" );
			min_reg_month="01";
		}
		if(  (max_reg_year!=null && max_reg_year.length>0)  && (max_reg_month==null || max_reg_month.length==0) ){
			contactSearchFormObj.find("[name=max_reg_month]").val( "12" );
			max_reg_month="12";
		}
		
		//----------------------------------
		if( (min_reg_year!=null && min_reg_year.length>0) && (min_reg_month!=null && min_reg_month.length>0)
			&& (max_reg_year!=null && max_reg_year.length>0) &&  (max_reg_month!=null && max_reg_month.length>0) ){

			var max_date = contactSearchFormObj.find("[name=max_reg_year]").val(  )+contactSearchFormObj.find("[name=max_reg_month]").val(  );
			var min_date = contactSearchFormObj.find("[name=min_reg_year]").val(  )+contactSearchFormObj.find("[name=min_reg_month]").val(  );
			if( parseInt(max_date,10) < parseInt(min_date,10) ) {
				alert("[최소 년월]이 [최대 년월] 보다 큽니다. 재 선택 바람!");
				contactSearchFormObj.find("[name^=min_reg_]").val("");
				contactSearchFormObj.find("[name^=max_reg_]").val("");
				return;
			}
		}
		
	}

	function goContactSearchForm(){
		document.contactSearchForm.submit();
	}
	
	// 연락처 등록 버튼 클릭하면 호출되는 함수 선언
	function goContactRegForm(){
		location.replace("/erp/contactRegForm.do");
	}
	
	// 연락처 목록의 행을 클릭하면 호출되는 함수 선언
	function openContactUpDelForm(contact_no){
		
		// 매개변수로 들어온 연락처 고유번호를 name=contactUpDelForm 가 있는 form 태그 내부의
		// name=contact_no 가 있는 hidden 태그에 value 값으로 삽입
		$("[name=contactUpDelForm]").find("[name=contact_no]").val(contact_no);
//		alert($("[name=contactUpDelForm]").find("[name=contact_no]").val());
//		return;
		// name=contactUpDelForm 가 있는 form 태그의 action 값의 URL 주소로 이동하기
		document.contactUpDelForm.submit();
		
	}
</script>


<body><center><br>


<script>
</script>

<!--mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm-->
<!--[연락처 검색] 화면을 출력하는 form 태그 선언-->
<!--mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm-->
<form  name="contactSearchForm" action="/erp/contactSearchForm.do" method="post">

	<b>[연락처 검색]</b>
	<table class="tbcss1">
		<tr>
			<th bgcolor=#D8D8D8>사업분야</th>
			<td>
				<c:forEach var="saup_field" items="${saup_fieldList}">
					<input type="checkbox" name="saup_field" value="${saup_field.saup_field_code}" >${saup_field.saup_field_name}
				</c:forEach>
		<tr>
			<th bgcolor=#D8D8D8>등록일
			<td>
				<select name="min_reg_year">
					<option value="">
					<script>
					   for( var i=2000 ; i<new Date( ).getFullYear( )+2 ; i++){
					       document.write( "<option value="+i+">"+i );
					   }
					</script>
				</select>년
				<select name="min_reg_month">
					<option value="">
					<script>
					   for( var i=1 ; i<=12 ; i++){
					       if(i<10){ document.write( "<option value='0"+i+"'>0"+i ); }
					       else   { document.write( "<option value='"+i+"'>"+i ); }
					   }
					</script>
				</select>월
				<span style="cursor:pointer" class="to_today1">[이번달로]</span>
				<span style="cursor:pointer" class="empty1">[비움]</span>
				~
				<select name="max_reg_year">
					<option value="">
					<script>
					   for( var i=2000 ; i<new Date( ).getFullYear( )+2 ; i++){
					       document.write( "<option value="+i+">"+i );
					   }
					</script>
				</select>년
				<select name="max_reg_month">
					<option value="">
					<script>
					   for( var i=1 ; i<=12 ; i++){
					       if(i<10){ document.write( "<option value='0"+i+"'>0"+i ); }
					       else   { document.write( "<option value='"+i+"'>"+i ); }
					   }
					</script>
				</select>월
				<span style="cursor:pointer" class="to_today2">[이번달로]</span>
				<span style="cursor:pointer" class="empty2">[비움]</span>&nbsp;&nbsp;
				<span style="cursor:pointer" class="emptyAll">[모두비움]</span>
		<tr>
			<th bgcolor=#D8D8D8>키워드</th>
			<td><input type="text" name="keyword1">
	</table>

	<div style="height:6"></div>

	<!--**********************************************-->
	<!--[검색 버튼], [모두검색 버튼], [연락처 등록 링크], [로그아웃 링크] 출력--->
	<!--**********************************************-->
	<input type=button value="                 검색                 " class="searchContact">&nbsp;&nbsp;
	<input type=button value="초기화 후 모두검색" class="searchContactAll">&nbsp;&nbsp;
	<span style="cursor:pointer" class="showContactRegForm" onclick="goContactRegForm();">[연락처 등록]</span>

	&nbsp;&nbsp;&nbsp;

	<div style="height:6"></div>

	<!--**********************************************-->
	<!--[검색 수] 출력--->
	<!--**********************************************-->
	<font size="2"><b>[검색 개수 : ${contactListAllCnt}개]</b></font>
	<table><tr><td height=1 width=650 bgcolor=white></table>

	<!--**********************************************-->
	<!-- 자바스크립트 함수 호출로 [페이징 번호] 출력. [페이지당 보여줄 행의 개수] 출력-->
	<!--**********************************************-->
	<table border=0 class="pagingNos">
		<tr>
			<td align=left>
				<select name="sort_multi">
					<option value="">
					<option value="reg_date desc, contact_name asc">등록일 내림차순>이름 오름차순
					<option value="reg_date desc, contact_name desc">등록일 내림차순>이름 내림차순
					<option value="reg_date asc, contact_name asc">등록일 오름차순>이름 오름차순
					<option value="reg_date asc, contact_name desc">등록일 오름차순>이름 내림차순
					
					<option value="reg_date desc, phone asc">등록일 내림차순>전화 오름차순
					<option value="reg_date desc, phone desc">등록일 내림차순>전화 내림차순
					<option value="reg_date asc, phone asc">등록일 오름차순>이름 오름차순
					<option value="reg_date asc, phone desc">등록일 오름차순>이름 내림차순
				</select>
			<td width=500 align=center>
				
			<!----------------------------------->
			<!-- 검색 결과물의 개수가 1개 이상일 경우 페이징 처리 관련 html 출력-->
			<!----------------------------------->
			<c:if test="${contactListAllCnt>0}">
				<!-- 만약 [시작페이지 번호]가 [화면당 보이는 페이지 번호 개수]보다 크면-->
				<!-- [처음], [이전] 글씨 보이게 하고, 클릭하면 원하는 페이지번호를 hidden 입력 양식에 담고-->
				<!-- 현재 페이지로 다시 이동하기-->
				<c:if test="${pageingData.beginPageNo>pageingData.pageNoCntPerPage}">
					<a href="javascript:$('[name=selectPageNo]').val('1');goContactSearchForm( );">[처음] </a>
					<a href="javascript:$('[name=selectPageNo]').val('${pageingData.beginPageNo-1}');goContactSearchForm( );">[이전]  </a>
				</c:if>
				<!-- [시작페이지 번호]부터 [마지막 페이지 번호]까지 페이지번호를 출력하기-->
				<!-- 선택된 페이지 번호는 번호만 출력하고 클릭하지 못하게 하고-->
				<!-- 선택 안된 페이지 번호는 대괄호로 감싸 출력하고 클릭하면 원하는 -->
				<!-- 원하는 페이지번호를 hidden 입력 양식에 담고 현재 페이지로 다시 이동하기-->
				<c:forEach var="pageNo" begin="${pageingData.beginPageNo}" end="${pageingData.endPageNo}">
					<c:if test="${pageingData.selectPageNo!=pageNo}">
						<a href="javascript:$('[name=selectPageNo]').val('${pageNo}');goContactSearchForm()">[${pageNo}]</a>
					</c:if>
					<c:if test="${pageingData.selectPageNo==pageNo}">
						${pageNo}
					</c:if>
				</c:forEach>
				<!-- 만약면 현재 화면에서 보이는 [끝 페이지 번호]가 [맨 마지막 페이지 번호]보다 작으면-->
				<!-- [다음], [마지막] 글씨 보이게 하고, 클릭하면 원하는 페이지번호를 hidden 입력 양식에 담고-->
				<!-- 현재 페이지로 다시 이동하기-->
				<c:if test="${pageingData.endPageNo<paginData.lastPageNo}">
					<a href="javascript:$('[name=selectPageNo]').val('${pageingData.endPageNo+1}');goContactSearchForm();">  [다음] </a>
					<a href="javascript:$('[name=selectPageNo]').val('${pageingData.lastPageNo}');goContactSearchForm();">[마지막] </a>
				</c:if>
			</c:if> 


			
			<td width=90 align=right>
				<select name="rowCntPerPage">
					<option value="10">10
					<option value="15">15
					<option value="20">20
					<option value="25">25
					<option value="30">30
					<option value="35">35
					<option value="40">40
				</select> 행보기
	</table>

	<!--**********************************************-->
	<!-- [hidden 입력 양식] 선언.
	<!--**********************************************-->
	<!--	[정렬값 저장 입력 hidden 양식]-->
	<!--	[선택한 페이지 번호 저장 hidden 양식] -->
	<!--	[연락처 등록 div 태그의 존재 여부 저장 hidden 양식]-->
	<!--	[연락처 등록 div 태그의 y 좌표 hidden 양식]-->
	<!--	[연락처 등록 div 태그의 x 좌표 hidden 양식] -->
	<!--**********************************************-->
	<input type="hidden" name="sort">
	<input type="hidden" name="selectPageNo">
	
</form>


<!--mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm-->
<!--검색 결과물 출력하는 table 태그 선언-->
<!--mmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm-->
<table id="aaa" border=1 cellpadding=5 cellspacing=0 bordercolor=gray width=600  class="tbcss2">
	<!------------------------------------------>
	<!--검색 결과의 헤더 행 출력하기. 클릭하면 원하는 정렬 데이터를 hidden 태그에 담고 자기 자신으로 접속하기---->
	<!------------------------------------------>
	<tr bgcolor=#C6C6C6>
		<th>번호
		<!------------------------------------->
		<!-- 만약 파라미터명이 sort 놈의 파라미터값이 contact_name desc 면 아래 소스 표현하기 -->
			<!-- 
				<th style="cursor:pointer"
					onclick="$('[name=sort]').val( 'contact_name asc' );
						$('[name=sort_multi]').val( '' );goContactSearchForm( );">▼연락처명
			 -->
		 <!-- 만약 파라미터명이 sort 놈의 파라미터값이 contact_name asc 면 아래 소스 표현하기 -->
			<!-- 
				<th style="cursor:pointer"
					onclick="$('[name=sort]').val('contact_name desc');
						$('[name=sort_multi]').val( '' );goContactSearchForm( );">▲연락처명
			 -->
		 <!-- 만약 파라미터명이 sort 놈의 파라미터값이 contact_name asc 도 아니고 contact_name desc도 아니면 아래 소스 표현하기 -->
			<!-- 
				<th style="cursor:pointer"
					onclick="$('[name=sort]').val('contact_name asc');
						$('[name=sort_multi]').val( '' );goContactSearchForm( );">연락처명
			 -->
		<c:choose>
			<c:when test="${param.sort=='contact_name desc'}">
				<th style="cursor:pointer"
					onclick="$('[name=sort]').val( 'contact_name asc' );
						$('[name=sort_multi]').val( '' );goContactSearchForm( );">▼연락처명
			</c:when>
			<c:when test="${param.sort=='contact_name asc'}">
				<th style="cursor:pointer"
					onclick="$('[name=sort]').val('contact_name desc');
						$('[name=sort_multi]').val( '' );goContactSearchForm( );">▲연락처명
			</c:when>
			<c:otherwise>
				<th style="cursor:pointer"
					onclick="$('[name=sort]').val('contact_name asc');
						$('[name=sort_multi]').val( '' );goContactSearchForm( );">연락처명
			</c:otherwise>
		</c:choose>
		<!------------------------------------->
		<c:choose>
			<c:when test="${param.sort=='phone desc'}">
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('phone asc');goContactSearchForm( );">▼전화
			</c:when>
			<c:when test="${param.sort=='phone asc'}">
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('phone desc');goContactSearchForm( );">▲전화
			</c:when>
			<c:otherwise>
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('phone asc');goContactSearchForm( );">전화
			</c:otherwise>
		</c:choose>
		<!------------------------------------->
		<c:choose>
			<c:when test="${param.sort=='4 desc'}">
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('4 asc');goContactSearchForm( );">▼사업분야
			</c:when>
			<c:when test="${param.sort=='4 asc'}">
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('4 desc');goContactSearchForm( );">▲사업분야
			</c:when>
			<c:otherwise>
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('4 asc');goContactSearchForm( );">사업분야
			</c:otherwise>
		</c:choose>
		<!------------------------------------->
		<c:choose>
			<c:when test="${param.sort=='reg_date desc'}">
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('reg_date asc');goContactSearchForm( );">▼등록일
			</c:when>
			<c:when test="${param.sort=='reg_date asc'}">
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('reg_date desc');goContactSearchForm( );">▲등록일
			</c:when>
			<c:otherwise>
				<th style="cursor:pointer"
					onclick="$('[name=sort_multi]').val( '' );$('[name=sort]').val('reg_date asc');goContactSearchForm( );">등록일
			</c:otherwise>
		</c:choose>
		<!------------------------------------->
		<!--연락처 검색 결과물 출력-->
		<!-- HttpServletRequest 객체에 contactList 라는 키값으로 저장된
		 List<Map<String,String>> 안의 데이터를 커스텀태그의 반복문으로 출력하기 -->
		 <!-- var="contact" 는 Map 객체를 담는 변수이고 -->
		 <!-- varStatus="loopTagStatus" 에서 loopTagStatus는 LoopTagStatus 객체를 담는 변수이다 -->
		 <!-- LoopTagStatus 객체의 속성변수 중 index 는 반복문이 돌때마다 인덱스 번호를 리턴한다. -->
		<!------------------------------------->
		<c:forEach var="contact" items="${contactList }" varStatus="loopTagStatus">
				<tr align="center" style="cursor:pointer" onClick="openContactUpDelForm(${contact.contact_no})">
				<td>${pageingData.beginRowNo_desc-loopTagStatus.index }	<!-- 내림 순서 번호 출력 -->
				<td>${contact.contact_name }							<!-- 연락처명 출력 -->
				<td>${contact.phone }									<!-- 전화번호 출력 -->
				<td>${contact.saup_field }								<!-- 연락처 사업분야를 모아 컴마를 중간에 넣어 출력 -->
				<td>${contact.reg_date }								<!-- 연락처 등록일 출력 -->
		</c:forEach>
</table>

<table><tr><td height=4></table>

<c:if test="${empty contactList}">
	<b>검색 조건에 맞는 결과가 없습니다.</b><br>
</c:if>

<!-- 연락처 수정/삭제 화면으로 이동하는데 사용되는 form 태그 선언 -->
<form name="contactUpDelForm" method=post action="/erp/contactUpDelForm.do">
	<input type="hidden" name="contact_no">
	<input type="hidden" name="selectPageNo">
</form>

</body>
</html>









































