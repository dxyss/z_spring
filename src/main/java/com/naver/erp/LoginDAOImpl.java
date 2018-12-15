package com.naver.erp;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class LoginDAOImpl implements LoginDAO{
	// SqlSessionTemplate ��ü�� ������ �Ӻ� sqlSession�� ����
	@Autowired
	   private SqlSessionTemplate sqlSession;
	
	public int getAdminCnt(Map<String,String> admin_id_pwd) {
		// SqlSessionTemplate ��ü�� selectOne �޼ҵ� ȣ��� [�α��� ���̵�, ��ȣ�� ���� ����]�� ����
		// selectOne("com.naver.erp.LoginDAO.getAdminCnt", admin_id_pwd); �ǹ�
			// ���̹�Ƽ�� SQL ���� ���� XML����(=mapper_login.xml))����
			// <mapper namespace="com.naver.erp.LoginDAO">�±� ������
			// <select id="getAdminCnt"> �±� ������
			// [1�� ���� select ������]�� �����ϰ� ���� �����͸� int �� �����Ѵ�
			// 2��° ���ڴ� [select ������]�� ���Ե� �������̴�.
			// ���� �ڷ����� ������ int �̴�.
		int adminCnt = this.sqlSession.selectOne(
	            "com.naver.erp.LoginDAO.getAdminCnt"   //������ SQL ���� ��ġ ����
	            ,admin_id_pwd                        //������ SQL �������� ����� ������ ����
	      );
	      return adminCnt;
	   }
	}

