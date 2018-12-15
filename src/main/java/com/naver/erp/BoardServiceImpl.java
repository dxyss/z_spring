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
   //�Ӽ����� boardDTO �����ϰ�, [BoardDAO �������̽�]�� �������� [BoardDAOImpl] ��ü�� ������ ����
   //--------------------------------
   // @Autowired ���� => �Ӽ������� ���� �ڷ����� [�������̽�]�� ������ [Ŭ����]�� ��üȭ�Ͽ� �����Ѵ�
      @Autowired
      private BoardDAO boardDAO;
      //--------------------------------
      //[�˻��� �Խ��� ��� ����]�����ϴ� �޼ҵ� ����
      //--------------------------------
   public int getBoardListCnt(BoardSearchDTO boardSearchDTO) {
      int boardListCnt = this.boardDAO.getBoardListCnt(boardSearchDTO);
      return boardListCnt;
   }
   
   public  List<Map<String,String>> getBoardList(BoardSearchDTO boardSearchDTO){	  
	   List<Map<String,String>> boardList= this.boardDAO.getBoardList(boardSearchDTO);
	   return boardList;
   }
   
   // [1�� �Խ��� �� �Է� �� �Է� ���� ���� ����] �����ϴ� �޼ҵ� ����
   public int insertBoard(BoardDTO boardDTO) {
	   // ���� BoardDTO ��ü ������ b_no �Ӻ��� null �� �ƴϰ� ���ڵ� ���̰� 0�� �̻��̸�
	   // ���� ����� ��� ���� ��ȣ�� ������Ʈ�϶�� BoardDAOImpl ��ü��
	   // updatePrint_no �޼ҵ� ȣ���ϱ�
	   // �� ���۾��Ⱑ �ƴ϶� ��۾����� ���� ����� ��� ���� ��ȣ�� ������Ʈ�϶�� �ǹ�
	   if(boardDTO.getB_no()!=null && boardDTO.getB_no().length()>0) {
		   int updatePrint_noCnt = this.boardDAO.updatePrint_no(boardDTO);
	   }
	   // BoardDAOImpl ��ü�� getBoardList �޼ҵ� ȣ��� �Խ��� �� �Է��ϱ�
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