package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// @Repository 를 붙임으로서 [DAO 클래스] 임을 지정하게되고, bean 태그로 자동 등록된다.
@Repository
public class BoardDAOImpl implements BoardDAO{
   //================================
   //속성변수 sqlSession 선언하고, [SqlSessionTemplate 객체]를 생성해 저장
   //================================
      //@Autowired 역할 => 속성변수에 붙은 자료형이 [인터페이스]면 이를 구현한 [클래스]를 객체화 하여 저장한다
      //@Autowired 역할 => 속성변수에 붙은 자료형이 [클래스]면 이를 객체화하여 저장한다
   @Autowired
   private SqlSessionTemplate sqlSession;
   //================================
   //[검색한 게시판 목록 개수] 리턴하는 메소드 선언
   //================================
   public int getBoardListCnt(BoardSearchDTO boardSearchDTO) {
	   // [SqlSessionTemplate 객체]의 selectOne(~,~) 메소드를 호출해 [검색한 게시판 결과물 개수]를 int로 얻기
	   // DTO|Map|String|int|double selectOne(
	   //					"쿼리문설정 XML 파일안에 mapper 태그의 namespace 속성값, select태그 id속성값"
	   //					, select 쿼리에 삽입되는 외부 데이터 자료형
	   // )
	   // [쿼리문설정 XML 파일]은 아래 root-context.xml 파일에 서 아래처럼 등록해 놓은 파일이디.
	   //	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	   //	<property name="dataSource" ref="log4jdbcProxyDataSource"/>
	   //	<property name="mapperLocations" value="classpath:com/naver/erp/*.xml"/>
	   //	</bean>
	   int boardListCnt = sqlSession.selectOne(
            "com.naver.erp.BoardDAO.getBoardListCnt"   //실행할 SQL 구문 위치 지정
            ,boardSearchDTO                        //실행할 SQL 구문에서 사용할 데이터 설정
      );
      return boardListCnt;
   }
   
   // [검색한 게시판 목록] 리턴하는 메소드 선언
   public List<Map<String,String>> getBoardList(BoardSearchDTO boardSearchDTO){
	   // [SqlSessionTemplate 객체]의 selectList(~,~) 메소드를 호출하여 [n행 검색 결과물]을
	   // List<Map<String,String>> 객체로 리턴하기
	   // List<Map<String,String>>|List<DTO>|List<String>|List<Integer> selectList(
	   //		"쿼리문설정 XML 파일안에 mapper 태그의 namespace 속성값, select태그 id 속성값"
	   //		, select 쿠리에 삽입되는 외부 데이터 자료형
	   List<Map<String,String>> boardList = sqlSession.selectList(
			   "com.naver.erp.BoardDAO.getBoardList"		// 실행할 SQL 구문의 위치지정
			   ,boardSearchDTO								// 실행할 SQL 구문에서 사용할 데이터 설정
			   );
	   return boardList;
   }
 
   // [1개 게시판 글 출력번호 수정하고 수정 행의 개수] 리턴하는 메소드 선언
   public int updatePrint_no(BoardDTO boardDTO) {
	// [SqlSessionTemplate 객체]의 update(~,~) 메소드를 호출하여 [입력 후 입력적용행의 개수]를
	   // 리턴하기
	   // int update(
	   //				"쿼리문설정 XML 파일안에 mapper 태그의 namespace 속성값.update태그 id 속성값"
	   //				, update 쿼리에 삽입되는 외부 데이터 자료형)
	   int updatePrint_noCnt = sqlSession.update("com.naver.erp.BoardDAO.updatePrint_no",boardDTO);
	   return updatePrint_noCnt;
   }
   
   // [게시판 글 입력 후 입력 적용 행의 개수] 리턴하는 메소드 선언
   public int insertBoard(BoardDTO boardDTO) {
	   // [SqlSessionTemplate 객체]의 insert(~,~) 메소드를 호출하여 [입력 후 입력적용행의 개수]를
	   // 리턴하기
	   // int insert(
	   //				"쿼리문설정 XML 파일안에 mapper 태그의 namespace 속성값.insert태그 id 속성값"
	   //				, insert 쿼리에 삽입되는 외부 데이터 자료형)
	   int boardRegCnt = sqlSession.insert("com.naver.erp.BoardDAO.insertBoard",boardDTO);
	   return boardRegCnt;
   }
   
   
   
   public BoardDTO getBoardDTO(int b_no) {
	   BoardDTO boardDTO = sqlSession.selectOne("com.naver.erp.BoardDAO.getBoardDTO",b_no);
	   return boardDTO;
   }
   public int updateReadcount(int b_no) {
	   int readcount = sqlSession.update("com.naver.erp.BoardDAO.updateReadcount",b_no);
	   return readcount;
   }
   
   
   public int updateBoard(BoardDTO boardDTO) {
	   int updateBoardCnt = sqlSession.update("com.naver.erp.BoardDAO.updateBoard",boardDTO);
	   return updateBoardCnt;
   }
   public int deleteBoard(BoardDTO boardDTO) {
	   int deleteBoardCnt = sqlSession.delete("com.naver.erp.BoardDAO.deleteBoard",boardDTO);
	   return deleteBoardCnt;
   }
}