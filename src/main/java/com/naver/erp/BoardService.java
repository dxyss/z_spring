package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.naver.erp.*;

//=======================================
//[BoardService �������̽�] ����
//=======================================
public interface BoardService {
   //---------------------------------
   //[�˻��� �Խ��� ��� ����] �����ϴ� �޼ҵ� ����
   //---------------------------------
   int getBoardListCnt(BoardSearchDTO boardSearchDTO);
   
   // [�˻��� �Խ��� ���] �����ϴ� �޼ҵ� ����
   List<Map<String,String>> getBoardList(BoardSearchDTO boardSearchDTO);
   
   // [�Խ��� �� �Է� �� �Է� ���� ���� ����] �����ϴ� �޼ҵ� ����
   int insertBoard(BoardDTO boardDTO);
   
   // �󼼱� ȭ�鿡�� ������ [1�� �Խ��� �� ����] �����ϴ� �޼ҵ� ����
   BoardDTO getBoardDTO(int b_no);
   
   
   int updateBoard(BoardDTO boardDTO);
   int deleteBoard(BoardDTO boardDTO);
}
