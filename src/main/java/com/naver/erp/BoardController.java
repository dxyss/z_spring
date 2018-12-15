package com.naver.erp;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
public class BoardController {
	// 속성변수 boardService 선언하고 [BoardService 인터페이스]를 구현받은
	// [BoardServiceImpl] 객체를  생성해 저장
	// [BoardServiceImpl] 객체의 이름이 바뀌어도 상관없다
	// [BoardService 인터페이스]를 구현받은 객체면 무조건 된다.
	// 현재 이 클래스 내부의 메소드에서 DB 연동 지시를 내리기 위해 서비스 객체를 생성한 것이다.
   @Autowired
   private BoardService boardService;

   //============================================
   //가상주소 /boardList.do 로 접근하면 호출되는 메소드 선언
   //============================================
   @RequestMapping( value="/boardList.do" )
   public ModelAndView getBoardList( 
         //--------------------------------------------
         //[파라미터값을 저장할 [BoardListDTO 객체]를 매개변수로 선언
         //--------------------------------------------
            //@RequestMapping 이 붙은 메소드의 매개변수에 DTO가 있으면
            //스프링은 메소드 호출 전에 다음과 같은 짓을 한다
            //<1> DTO 객체 생성한다
            //<2> [파라미터명]과 스펠링이 동일한 속성변수가 있으면 setter메소드를 호출하여 
            //     [파라미터값]을 [속성변수]에 저장한다
            //<3> HttpServletRequest 객체의 setAttribute("키값명",DTO 객체) 호출로
            //     DTO 객체를 HttpServletRequest에 저장한다
            //       이 말은 호출 후 이동하는 JSP 페이지에서 이 DTO 객체를 사용이 가능하다는 말이다
            //       호출 후 이동하는 JSP 페이지에서 EL로 ${키값명.속성변수명}으로 표현하여
            //     DTO 객체의 속성변수 표현이 가능하다
            //       이 때 DTO 매개변수 앞에 @ModelAttribute("키값명")을 붙이면 이 키값명이 
            //     setAttribute("키값명",DTO 객체)의 키값명이 된다
            //       만약  @ModelAttribute("키값명")이 없으면 키값명은 DTO 객체 이름 맨 앞 영대문자를
            //       소문자로 바꾼 스펠링이 키값이 된다
            //<4> 메소드를 호출하면서 매개변수 DTO 객체의 메위주를 전달한다
         @ModelAttribute ("boardSearchDTO") BoardSearchDTO boardSearchDTO 
   ){
      //--------------------------
      //[ModelAndView] 객체 생성하기
      //--------------------------
      ModelAndView mav = new ModelAndView();
      //--------------------------
      //[ModelAndView]객체에 [호출 JSP 페이지명]을 저장하기
      //--------------------------
      mav.setViewName("boardList");
      try {
         //--------------------------------------------
         //[BoardServiceImpl 객체]의 getBoardListCnt(boardListDTO) 메소드 호출로 [검색한 게시판 총 개수]를 얻기
         //서비스 객체의 메소드를 호출하여 DB 연동 지시를 내리고 결과를 받아온다
         //--------------------------------------------
    	  
         int boardListAllCnt = this.boardService.getBoardListCnt(boardSearchDTO);
         System.out.println(boardListAllCnt);
         
         // JSP 에서 보여지는 페이징 처리 관련 데이터를 
         int selectPageNo = boardSearchDTO.getSelectPageNo();
         	if(selectPageNo==0) {selectPageNo = 1;}
         int rowCntPerPage = boardSearchDTO.getRowCntPerPage();
         	if(rowCntPerPage==0) {rowCntPerPage = 10;}
         Map<String,Integer> pageingData = PageingData2.getPageingData(
        		 	boardListAllCnt		// totRowCnt
        		 	,selectPageNo		// selectPageNo
        		 	,rowCntPerPage		// rowCntPerPage
        		 	,10
        		 ); 
         // 검색 총 개수와 선택 페이지의 관계를 보정하여 수정된 선택 페이지 번호를
         // BoardSearchDTO 객체에 재 입력해 준다.
         // 이 보정 작업은 getPageingData 메소드 호출 시 작업해 준다.
         boardSearchDTO.setSelectPageNo(pageingData.get("selectPageNo"));
         // 페이지 번호에 맞는 시작행 번호를 구해 BoardSearchDTO 객체에 세팅
         boardSearchDTO.setBeginRowNo(pageingData.get("beginRowNo"));
         // 페이지 번호에 맞는 끝행 번호를 구해 BoardSearchDTO 객체에 세팅
         boardSearchDTO.setEndRowNo(pageingData.get("endRowNo"));
         System.out.println(boardSearchDTO.getBeginRowNo());
         System.out.println(boardSearchDTO.getEndRowNo());
         
         // [BoardServiceImpl 객체]의 getBoardList(boardListDTO) 메소드 호출로 [검색한 게시판 목록]을 얻기
         List<Map<String,String>> boardList = this.boardService.getBoardList(boardSearchDTO);
         
         // ModelAndView 객체에 [검색한 게시판 총 개수] 저장하기
         // ModelAndView 객체에 저장된 데이터는 HttpServletRequest 객체에 저장되고
         // 이 메소드 종료 후 실행되는 JSP 페이지에서 꺼낼 수 있다.
         mav.addObject("boardListAllCnt",boardListAllCnt);
         // ModelAndView 객체에 [검색한 게시판 목록] 저장하기
         mav.addObject("boardList",boardList);
        
         // 페이징 처리 관련 데이터를 저장
        
         mav.addObject("pageNoCntPerPage",pageingData.get("pageNoCntPerPage"));
         mav.addObject("selectPageNo",pageingData.get("selectPageNo"));
         mav.addObject("lastPageNo",pageingData.get("lastPageNo"));
         mav.addObject("beginPageNo",pageingData.get("beginPageNo"));
         mav.addObject("endPageNo",pageingData.get("endPageNo"));
         mav.addObject("beginRowNo",pageingData.get("beginRowNo"));
         mav.addObject("endRowNo",pageingData.get("endRowNo"));
         mav.addObject("beginRowNo_desc",pageingData.get("beginRowNo_desc"));
         System.out.println(boardSearchDTO.getSelectPageNo());
   	  System.out.println(boardSearchDTO.getRowCntPerPage());
   	  
       
      
      }catch(Exception ex) {
         System.out.println("BoardController.getBoardList(~) 메소드 호출 시 에러발생");
         mav.setViewName("error");
         mav.addObject("msg","BoardController.getBoardList(~) 메소드 호출 시 에러발생");
      }finally {
    	 
    	  
      }
      //mav.addObject("boardSearchDTO",boardSearchDTO);
      
      //--------------------------
      //ModelAndView 객체에 데이터 저장하기
      //--------------------------
      //mav.addObject("msg","성공! 축하합니다");
      //--------------------------
      //[ModelAndView]객체 리턴하기
      //--------------------------
      return mav;
         
   }
   
   
   
   @RequestMapping (value="/boardRegForm.do")
   public ModelAndView goBoardRegForm( 
		   // b_no 라는 [파라미터명]의 [파라미터값]을 저장한 int 형 매개변수 선언
		   // @RequestParam(value="파라미터명",required=true|false,defaultValue="디폴트값")자료형파라미터값 저장할 매개변수명
		   //	@RequestParam(value="b_no", required=false, defaultValue="0") int b_no 
		  // @RequestParam(value="b_no", required=false) String b_no
		   // [파라미터명]의 [파라미터값]을 [파라미터값저장할 매개변수명]에 저장한다.
		   // required=true		=> 파라미터명이 없을 경우 에러발생, 메소드안의 코딩은 한줄도 실행안된다. 생략가능
		   // required=false	=> 파라미터명이 없을 경우 null 값을 매개변수에 저장. 만약 매개변수 자료형이 기본형이면 에러 발생
		   // defaultValue="디폴트값" => 파라미터값이 없을 경우 디폴트값을 매개변수에 저장. required=false가 있어야함
	) {
	   // [ModelAndView 객체] 생성하기
	   ModelAndView mav = new ModelAndView();
	   
	   // [ModelAndView 객체]에 [호출 JSP 페이지명]을 저장하기
	   mav.setViewName("boardRegForm");
	   
	
	   // [ModelAndView 객체] 리턴하기
	   return mav;
   }
   
   
   
   // erp/boardRegProc.do 로 접근하면 호출되는 메소드 선언
   @RequestMapping(
		   // 클라이언트 접속 URL 설정
		   value="/boardRegProc.do"
		   // 클라이언트가 파라미터를 보내는 방법은 post로 설정, 즉 post 방식으로 보낸 데이터만 받겠다는 의미
		   , method=RequestMethod.POST
		   // 클라이언트가 응답받을 수 있는 데이터 형식과 문자셋 지정
		   , produces="application/json;charset=UTF-8")
   @ResponseBody		//=>메소드의 리턴값을 JSON으로 변경하여 클라이언트에게 전송하는 어노테이션 설정
   public int insertBoard(
		 //--------------------------------------------
	         // 파라미터값을 저장된 [BoardDTO 객체]를 매개변수로 선언
		   	//	게시판 등록 데이터들이 BoardDTO 객체에 담겨 들어온다.
		   //--------------------------------------------
	            //@RequestMapping 이 붙은 메소드의 매개변수에 DTO가 있으면
	            //스프링은 메소드 호출 전에 다음과 같은 짓을 한다
	            //<1> DTO 객체 생성한다
	            //<2> [파라미터명]과 스펠링이 동일한 속성변수가 있으면 setter메소드를 호출하여 
	            //     [파라미터값]을 [속성변수]에 저장한다
		   		// 		이때 파라미터명은 있는데 파라미터값이 없을 경우 setter 메소드를 호출할떄  null을 저장된다
		   		// 		그러므로 이 속성변수의 자료형은 String 이 되야한다.
		   		//		만약 파라미터명이 없을 경우 setter 메소드가 절대 호출되지 않는다.
	            //<3> HttpServletRequest 객체의 setAttribute("키값명",DTO 객체) 호출로
	            //     DTO 객체를 HttpServletRequest에 저장한다
	            //       이 말은 호출 후 이동하는 JSP 페이지에서 이 DTO 객체를 사용이 가능하다는 말이다
	            //       호출 후 이동하는 JSP 페이지에서 EL로 ${키값명.속성변수명}으로 표현하여
	            //     DTO 객체의 속성변수 표현이 가능하다
	            //       이 때 DTO 매개변수 앞에 @ModelAttribute("키값명")을 붙이면 이 키값명이 
	            //     setAttribute("키값명",DTO 객체)의 키값명이 된다
	            //       만약  @ModelAttribute("키값명")이 없으면 키값명은 DTO 객체 이름 맨 앞 영대문자를
	            //       소문자로 바꾼 스펠링이 키값이 된다
	            //<4> 메소드를 호출하면서 매개변수 DTO 객체의 메위주를 전달한다
		   @ModelAttribute("boardDTO") BoardDTO boardDTO
		   ) {
	   int result=0;
	   try {
		   // [BoardServiceImpl 객체]의 insertBoard 메소드 호출로 게시판 입력하고 [입력 성공 행의 개수] 얻기
		   int boardRegCnt = this.boardService.insertBoard(boardDTO);
		   System.out.println(result);
		   result = boardRegCnt;
		   System.out.println(result);
	   }catch(Exception e) {
		   // 예외 발생 시 실행할 코드 설정
		   result = -1;
		   System.out.println("boardController.insertBoard(~) 메소드 예외 발생!");
	   }
	   
	   return result;
   }
   
   
   // 가상주소 /boardContentForm.do 로 접근하면 호출되는 메소드 선언
   @RequestMapping(value="/boardContentForm.do", method=RequestMethod.POST)
   public ModelAndView goBoardContentForm(
		   @RequestParam(value="b_no") int b_no
	) {
	   	ModelAndView mav = new ModelAndView();
	   	mav.setViewName("boardContentForm");
	   	try {
	   		BoardDTO boardDTO = this.boardService.getBoardDTO(b_no);
	   		mav.addObject("boardDTO",boardDTO);
	   	}catch(Exception e) {
	   		System.out.println("BoardController.goBoardContentForm(~)메소드 예외 발생!");
	   	}
	   	return mav;
   }
  
   
   
   
   @RequestMapping(value="/boardUpDelForm.do", method=RequestMethod.POST)
  public ModelAndView goBoardUpDelForm(
		  @RequestParam(value="b_no") int b_no
		// [파라미터명]의 [파라미터값]을 [파라미터값저장할 매개변수명]에 저장한다.
		   // required=true		=> 파라미터명이 없을 경우 에러발생, 메소드안의 코딩은 한줄도 실행안된다. 생략가능
		   // required=false	=> 파라미터명이 없을 경우 null 값을 매개변수에 저장. 만약 매개변수 자료형이 기본형이면 에러 발생
		   // defaultValue="디폴트값" => 파라미터값이 없을 경우 디폴트값을 매개변수에 저장. required=false가 있어야함
		  ) {
	   ModelAndView mav = new ModelAndView();
	   mav.setViewName("boardUpDelForm");
	   try {
		   	BoardDTO boardDTO = this.boardService.getBoardDTO(b_no);
		   	mav.addObject("boardDTO",boardDTO);
	   }catch(Exception e) {
		   System.out.println("goBoardUpDelForm메소드 호출 예외 발생");
	   }
	   return mav;
   }
   
   
   // /erp/boardUpDelProc.do로 접근하면 호출되는 메소드 선언
   // 리턴되는 데이터는 ModelAndView 객체가 아니라 수정, 삭제 행의 적용개수이다.
   // 리턴되는 수정, 삭제 행의 적용개수가 클라이언트에게 바로 전송된다.
   @RequestMapping(
		   // 클라이언트 접속 URL 설정
		   value="/boardUpDelProc.do"
		   // 클라이언트 파라미터를 보내는 방법은 post로 설정, 즉 post 방식으로 보낸 데이터만 받겠다는 의미
		   , method=RequestMethod.POST
		   // 클라이언트의 기본형 또는 JSON으로 응답받을 수 있게 지정
		   , produces="application/json;charset=UTF-8")
   @ResponseBody		//	=>메소드의 리턴값을 JSON으로 변경하여 클라이언트에게 전송하는 어노테이션 설정
   public int boardUpDelProc(
		   // 파라미터값을 저장할 [BoardDTO 객체]를 매개변수로 선언
		   	@ModelAttribute("boardDTO") BoardDTO boardDTO
		   	// upDel 이란 파라미터명의 파라미터값을 받을 String 형 매개변수 선언
		   	,@RequestParam(value="upDel") String upDel
		   ) {
	   int upDelCnt = 0;
	   ModelAndView mav = new ModelAndView();
	   
	   mav.setViewName("boardUpDelProc");
	   
	   try {
		   // [ModelAndView 객체]에 [호출 JSP 페이지]에 반영할 정보 저장
		   // 만약 수정 모드이면 수정 실행하고 수정 적용행의 개수를 저장
		   if(upDel.equals("up")) {
			   upDelCnt=this.boardService.updateBoard(boardDTO);
		   }else {
			   upDelCnt = this.boardService.deleteBoard(boardDTO);
		   }
	   }catch(Exception e) {
		   upDelCnt = -3;
		   System.out.println("BoardController.boardUpDelProc 메소드 예외 발생!");
	   }
	   return upDelCnt;
   }
   
   
   
   
   
   
   //@RequestMapping( value="/boardList.do" )
   /*
   public ModelAndView getBoardList( HttpServletRequest request ){
      String keyword = request.getParameter("keyword");
      BoardSearchDTO boardSearchDTO = new BoardSearchDTO();
      boardSearchDTO.setKeyword(keyword);
      //--------------------------
      //[ModelAndView] 객체 생성하기
      //--------------------------
      ModelAndView mav = new ModelAndView();
      //--------------------------
      //[ModelAndView]객체에 [호출 JSP 페이지명]을 저장하기
      //--------------------------
      mav.setViewName("boardList");
      mav.addObject("boardSearchDTO",boardSearchDTO);
      //--------------------------
      //[ModelAndView]객체 리턴하기
      //--------------------------
      return mav;
         
   }*/

}