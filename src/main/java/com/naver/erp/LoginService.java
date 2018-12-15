package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.naver.erp.*;

//=======================================
//[BoardService 인터페이스] 선언
//=======================================
public interface LoginService {
   //---------------------------------
   //[검색한 게시판 목록 개수] 리턴하는 메소드 선언
   //---------------------------------
   int getAdminCnt(Map<String,String> admin_id_pwd);
   
 
}
