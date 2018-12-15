package com.naver.erp;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// @Repository �� �������μ� [DAO Ŭ����] ���� �����ϰԵǰ�, bean �±׷� �ڵ� ��ϵȴ�.
@Repository
public class BoardDAOImpl implements BoardDAO{
   //================================
   //�Ӽ����� sqlSession �����ϰ�, [SqlSessionTemplate ��ü]�� ������ ����
   //================================
      //@Autowired ���� => �Ӽ������� ���� �ڷ����� [�������̽�]�� �̸� ������ [Ŭ����]�� ��üȭ �Ͽ� �����Ѵ�
      //@Autowired ���� => �Ӽ������� ���� �ڷ����� [Ŭ����]�� �̸� ��üȭ�Ͽ� �����Ѵ�
   @Autowired
   private SqlSessionTemplate sqlSession;
   //================================
   //[�˻��� �Խ��� ��� ����] �����ϴ� �޼ҵ� ����
   //================================
   public int getBoardListCnt(BoardSearchDTO boardSearchDTO) {
	   // [SqlSessionTemplate ��ü]�� selectOne(~,~) �޼ҵ带 ȣ���� [�˻��� �Խ��� ����� ����]�� int�� ���
	   // DTO|Map|String|int|double selectOne(
	   //					"���������� XML ���Ͼȿ� mapper �±��� namespace �Ӽ���, select�±� id�Ӽ���"
	   //					, select ������ ���ԵǴ� �ܺ� ������ �ڷ���
	   // )
	   // [���������� XML ����]�� �Ʒ� root-context.xml ���Ͽ� �� �Ʒ�ó�� ����� ���� �����̵�.
	   //	<bean id="sqlSessionFactory" class="org.mybatis.spring.SqlSessionFactoryBean">
	   //	<property name="dataSource" ref="log4jdbcProxyDataSource"/>
	   //	<property name="mapperLocations" value="classpath:com/naver/erp/*.xml"/>
	   //	</bean>
	   int boardListCnt = sqlSession.selectOne(
            "com.naver.erp.BoardDAO.getBoardListCnt"   //������ SQL ���� ��ġ ����
            ,boardSearchDTO                        //������ SQL �������� ����� ������ ����
      );
      return boardListCnt;
   }
   
   // [�˻��� �Խ��� ���] �����ϴ� �޼ҵ� ����
   public List<Map<String,String>> getBoardList(BoardSearchDTO boardSearchDTO){
	   // [SqlSessionTemplate ��ü]�� selectList(~,~) �޼ҵ带 ȣ���Ͽ� [n�� �˻� �����]��
	   // List<Map<String,String>> ��ü�� �����ϱ�
	   // List<Map<String,String>>|List<DTO>|List<String>|List<Integer> selectList(
	   //		"���������� XML ���Ͼȿ� mapper �±��� namespace �Ӽ���, select�±� id �Ӽ���"
	   //		, select ���� ���ԵǴ� �ܺ� ������ �ڷ���
	   List<Map<String,String>> boardList = sqlSession.selectList(
			   "com.naver.erp.BoardDAO.getBoardList"		// ������ SQL ������ ��ġ����
			   ,boardSearchDTO								// ������ SQL �������� ����� ������ ����
			   );
	   return boardList;
   }
 
   // [1�� �Խ��� �� ��¹�ȣ �����ϰ� ���� ���� ����] �����ϴ� �޼ҵ� ����
   public int updatePrint_no(BoardDTO boardDTO) {
	// [SqlSessionTemplate ��ü]�� update(~,~) �޼ҵ带 ȣ���Ͽ� [�Է� �� �Է��������� ����]��
	   // �����ϱ�
	   // int update(
	   //				"���������� XML ���Ͼȿ� mapper �±��� namespace �Ӽ���.update�±� id �Ӽ���"
	   //				, update ������ ���ԵǴ� �ܺ� ������ �ڷ���)
	   int updatePrint_noCnt = sqlSession.update("com.naver.erp.BoardDAO.updatePrint_no",boardDTO);
	   return updatePrint_noCnt;
   }
   
   // [�Խ��� �� �Է� �� �Է� ���� ���� ����] �����ϴ� �޼ҵ� ����
   public int insertBoard(BoardDTO boardDTO) {
	   // [SqlSessionTemplate ��ü]�� insert(~,~) �޼ҵ带 ȣ���Ͽ� [�Է� �� �Է��������� ����]��
	   // �����ϱ�
	   // int insert(
	   //				"���������� XML ���Ͼȿ� mapper �±��� namespace �Ӽ���.insert�±� id �Ӽ���"
	   //				, insert ������ ���ԵǴ� �ܺ� ������ �ڷ���)
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