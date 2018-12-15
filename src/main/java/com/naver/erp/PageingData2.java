

package com.naver.erp;
import java.util.*;

//************************************************************************
public class PageingData2 {
//************************************************************************
	public static Map<String, Integer> getPageingData(
		int totRowCnt          // [총 검색 행의 개수] 저장 변수 선언
		, int selectPageNo     // [선택된 페이지 번호] 저장 변수 선언
		, int rowCntPerPage    // [한 페이지에서 보이는 행의 개수] 저장 변수 선언
		, int pageNoCntPerPage  // [한 페이지에서 보이는 페이지 번호 개수] 저장 변수 선언
	) {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put( "pageNoCntPerPage", pageNoCntPerPage );
		map.put( "totRowCnt", totRowCnt ); 
		map.put( "selectPageNo", selectPageNo ); 
		map.put( "lastPageNo", 0 );
		map.put( "beginPageNo", 0 );
		map.put( "endPageNo", 0 );
		map.put( "beginRowNo", 0 ); 
		map.put( "endRowNo", 0 ); 
		map.put( "beginRowNo_desc", 0 );

		if( totRowCnt>0 && selectPageNo>0 && rowCntPerPage>0 && pageNoCntPerPage>0 ) { 
			//-------------------------------
			// 마지막 페이지 번호 얻기. <주의>반드시 상단에 먼저 나와야함
			//-------------------------------
			int lastPageNo = totRowCnt/rowCntPerPage  +  (totRowCnt%rowCntPerPage>0?1:0);
				// 만약 [선택 페이지 번호]와 [총 행의 개수]의 관계가 이상하면 [선택 페이지 번호]를 1로 초기화 하기
				// <예>페이지당 보이는 행의 개수가 10개 일때, 선택 페이지 번호가 3 인데 총 개수가 3일 경우
				if( lastPageNo<selectPageNo ) { selectPageNo = 1; }
			//-------------------------------
			// 선택 페이지에서 보이는 시작 페이지 번호 얻기
			//-------------------------------
			int beginPageNo = (selectPageNo/pageNoCntPerPage + (selectPageNo%pageNoCntPerPage>0?1:0) ) * pageNoCntPerPage - pageNoCntPerPage + 1;
			//-------------------------------
			// 선택 페이지에서 보이는 끝 페이지 번호 얻기
			//-------------------------------
			int endPageNo = (selectPageNo/pageNoCntPerPage + (selectPageNo%pageNoCntPerPage>0?1:0) ) * pageNoCntPerPage;
				// 만약 [끝 페이지 번호] > [마지막 페이지 번호]  경우 [끝 페이지 번호]를 [마지막 페이지 번호]로 바꾸기
				if( endPageNo>lastPageNo ) { endPageNo = lastPageNo; }

			//-------------------------------
			// 선택 페이지에서 보이는 시작 행의 검색 목록 상 [순서 번호] 얻기
			//-------------------------------
			int beginRowNo = (selectPageNo * rowCntPerPage) - (rowCntPerPage) + 1;
			//-------------------------------
			// 선택 페이지에서 보이는 끝 행의 검색 목록 상 순서 번호 얻기. 
			//-------------------------------
			int endRowNo = (selectPageNo * rowCntPerPage);
				// 만약 [총 행의 개수] < [마지막 행의 순서번호]  경우 [마지막 행의 순서번호]를 [총 행의 개수]로 바꾸기
				if( endRowNo>totRowCnt ) {  endRowNo = totRowCnt;  }
			//-------------------------------
			// 선택 페이지에서 보이는 시작 행의 검색 목록 상 [역순 순서 번호] 얻기
			//-------------------------------
			int beginRowNo_desc = totRowCnt - beginRowNo + 1;

			map.put( "pageNoCntPerPage", pageNoCntPerPage );             // [총 검색 행의 개수] 저장
			map.put( "totRowCnt", totRowCnt );             // [총 검색 행의 개수] 저장
			map.put( "selectPageNo", selectPageNo );       // [선택된 페이지 번호] 저장
			map.put( "lastPageNo", lastPageNo );           // [마지막 페이지 번호] 저장
			map.put( "beginPageNo", beginPageNo );         //  [시작 페이지 번호] 저장
			map.put( "endPageNo", endPageNo );             // [끝 페이지 번호] 저장
			map.put( "beginRowNo", beginRowNo );           // 선택 페이지에서 보이는 시작 행의 검색 목록 상 [순서 번호] 얻기
			map.put( "endRowNo", endRowNo );               // 선택 페이지에서 보이는 끝 행의 검색 목록 상 [순서 번호] 얻기
			map.put( "beginRowNo_desc", beginRowNo_desc ); // 선택 페이지에서 보이는 시작 행의 검색 목록 상 [역순 순서 번호] 얻기
		}
		return map;
	}		
//************************************************************************
}
//************************************************************************


