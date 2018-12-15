package com.naver.erp;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

//=====================================
//[BoardDAO �������̽�] ����
//=====================================
public interface BoardDAO {
   //--------------------------------
   //[�˻��� �Խ��� ��� ����] �����ϴ� �޼ҵ� ����
   //--------------------------------
   int getBoardListCnt(BoardSearchDTO boardSearchDTO);
   
   List<Map<String,String>> getBoardList(BoardSearchDTO boardSearchDTO);
   
   int updatePrint_no(BoardDTO boardDTO);
   
   int insertBoard(BoardDTO boardDTO);
   
   BoardDTO getBoardDTO(int b_no);
   int updateReadcount(int b_no);
   
   int updateBoard(BoardDTO boardDTO);
   int deleteBoard(BoardDTO boardDTO);
   
}