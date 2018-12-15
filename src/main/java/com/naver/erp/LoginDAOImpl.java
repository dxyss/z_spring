package com.naver.erp;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO{
	// SqlSessionTemplate 객체를 생성해 속변 sqlSession에 저장
	@Autowired
	   private SqlSessionTemplate sqlSession;
	
	public int getAdminCnt(Map<String,String> admin_id_pwd) {
		// SqlSessionTemplate 객체의 selectOne 메소드 호출로 [로그인 아이디, 암호의 존재 개수]를 리턴
		// selectOne("com.naver.erp.LoginDAO.getAdminCnt", admin_id_pwd); 의미
			// 마이바티스 SQL 구문 설정 XML파일(=mapper_login.xml))에서
			// <mapper namespace="com.naver.erp.LoginDAO">태그 내부의
			// <select id="getAdminCnt"> 태그 내부의
			// [1행 리턴 select 쿼리문]을 실행하고 얻은 데이터를 int 로 리턴한다
			// 2번째 인자는 [select 쿼리문]에 삽입될 데이터이다.
			// 리턴 자료형은 무조건 int 이다.
		int adminCnt = this.sqlSession.selectOne(
	            "com.naver.erp.LoginDAO.getAdminCnt"   //실행할 SQL 구문 위치 지정
	            ,admin_id_pwd                        //실행할 SQL 구문에서 사용할 데이터 설정
	      );
	      return adminCnt;
	   }
	}

