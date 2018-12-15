package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class BoardServiceImpl implements BoardService{
   //--------------------------------
   //속성변수 boardDTO 선언하고, [BoardDAO 인터페이스]를 구현받은 [BoardDAOImpl] 객체를 생성해 저장
   //--------------------------------
   // @Autowired 역할 => 속성변수에 붙은 자료형인 [인터페이스]를 구현한 [클래스]를 객체화하여 저장한다
      @Autowired
      private BoardDAO boardDAO;
      //--------------------------------
      //[검색한 게시판 목록 개수]리턴하는 메소드 선언
      //--------------------------------
   public int getBoardListCnt(BoardSearchDTO boardSearchDTO) {
      int boardListCnt = this.boardDAO.getBoardListCnt(boardSearchDTO);
      return boardListCnt;
   }
   
   public  List<Map<String,String>> getBoardList(BoardSearchDTO boardSearchDTO){	  
	   List<Map<String,String>> boardList= this.boardDAO.getBoardList(boardSearchDTO);
	   return boardList;
   }
   
   // [1개 게시판 글 입력 후 입력 적용 행의 개수] 리턴하는 메소드 선언
   public int insertBoard(BoardDTO boardDTO) {
	   // 만약 BoardDTO 객체 내부의 b_no 속변에 null 이 아니고 문자도 길이가 0개 이상이면
	   // 기존 댓글의 출력 순서 번호를 업데이트하라는 BoardDAOImpl 객체의
	   // updatePrint_no 메소드 호출하기
	   // 즉 새글쓰기가 아니라 댓글쓰기라면 기존 댓글의 출력 순서 번호를 업데이트하라는 의미
	   if(boardDTO.getB_no()!=null && boardDTO.getB_no().length()>0) {
		   int updatePrint_noCnt = this.boardDAO.updatePrint_no(boardDTO);
	   }
	   // BoardDAOImpl 객체의 getBoardList 메소드 호출로 게시판 글 입력하기
	   int boardRegtCnt = this.boardDAO.insertBoard(boardDTO);
	   return boardRegtCnt;
   }
   
   
   public BoardDTO getBoardDTO(int b_no) {
	   int readcount = this.boardDAO.updateReadcount(b_no);
	   BoardDTO boardDTO = this.boardDAO.getBoardDTO(b_no);
	   return boardDTO;
   }
   
   
   public int updateBoard(BoardDTO boardDTO) {
	   /*
	    * int sonCnt = this.boardDAO.getSonCnt(boardDTO);
	    * if(sonCnt>0){
	    * 	return -2;
	    * }
	    * int existCnt = this.boardDAO.getExistCnt(boardDTO);
	    * if(existCnt==0){
	    * 	return -1;
	    * }
	    * int pwdCnt = this.boardDAO.getPwdCnt(boardDTO);
	    * if(pwdCnt==0){
	    * 	return -3;
	    * }
	    * */
	   int updateBoardCnt = this.boardDAO.updateBoard(boardDTO);
	   return updateBoardCnt;
   }
   
   public int deleteBoard(BoardDTO boardDTO) {
	   int deleteBoardCnt = this.boardDAO.deleteBoard(boardDTO);
	   return deleteBoardCnt;
   }
}