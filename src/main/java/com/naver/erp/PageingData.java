package com.naver.erp;

//************************************************************************
public class PageingData {
//************************************************************************
	public int totRowCnt;         // [珥� 寃��깋 �뻾�쓽 媛쒖닔] ���옣 蹂��닔 �꽑�뼵
	public int selectPageNo;      // [�꽑�깮�맂 �럹�씠吏� 踰덊샇] ���옣 蹂��닔 �꽑�뼵

	public int lastPageNo;        // 珥� 留덉�留� �럹�씠吏� 踰덊샇 ���옣 蹂��닔 �꽑�뼵
	public int beginPageNo;       // �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 [�떆�옉 �럹�씠吏� 踰덊샇] �샇
	public int endPageNo;         // �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 [�걹 �럹�씠吏� 踰덊샇] ���옣 蹂��닔 �꽑�뼵
	//---
	public int beginRowNo;        // �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 [�떆�옉 �뻾�쓽 寃��깋 紐⑸줉 �긽 �닚�꽌 踰덊샇] ���옣 蹂��닔 �꽑�뼵
	public int endRowNo;          // �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 [�걹 �뻾�쓽 寃��깋 紐⑸줉 �긽 �닚�꽌 踰덊샇] ���옣 蹂��닔 �꽑�뼵
	public int beginRowNo_desc;   // �럹�씠吏��뿉�꽌 蹂댁씠�뒗 [�떆�옉 �뻾�쓽 寃��깋 紐⑸줉 �긽 �뿭�닚 �닚�꽌 踰덊샇] ���옣 蹂��닔 �꽑�뼵
	//--------------------------------------------------
	public PageingData(
		int totRowCnt
		, int selectPageNo
		, int rowCntPerPage
		, int pageNoCnPerPage
	) {
		if( totRowCnt>0 && selectPageNo>0 && rowCntPerPage>0 && pageNoCnPerPage>0 ) { 
			this.totRowCnt = totRowCnt;
			//-------------------------------
			// 留덉�留� �럹�씠吏� 踰덊샇 �뼸湲�. <二쇱쓽>諛섎뱶�떆 �긽�떒�뿉 癒쇱� �굹���빞�븿
			//-------------------------------
			this.lastPageNo = totRowCnt/rowCntPerPage  +  (totRowCnt%rowCntPerPage>0?1:0);
				// 留뚯빟 [�꽑�깮 �럹�씠吏� 踰덊샇]�� [珥� �뻾�쓽 媛쒖닔]�쓽 愿�怨꾧� �씠�긽�븯硫� [�꽑�깮 �럹�씠吏� 踰덊샇]瑜� 1濡� 珥덇린�솕 �븯湲�
				// <�삁>�럹�씠吏��떦 蹂댁씠�뒗 �뻾�쓽 媛쒖닔媛� 10媛� �씪�븣, �꽑�깮 �럹�씠吏� 踰덊샇媛� 3 �씤�뜲 珥� 媛쒖닔媛� 3�씪 寃쎌슦
				if( this.lastPageNo<selectPageNo ) { selectPageNo = 1; }
			this.selectPageNo = selectPageNo;
			//-------------------------------
			// �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �떆�옉 �럹�씠吏� 踰덊샇 �뼸湲�
			//-------------------------------
			this.beginPageNo = (selectPageNo/pageNoCnPerPage + (selectPageNo%pageNoCnPerPage>0?1:0) ) * pageNoCnPerPage - pageNoCnPerPage + 1;
			//-------------------------------
			// �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �걹 �럹�씠吏� 踰덊샇 �뼸湲�
			//-------------------------------
			this.endPageNo = (selectPageNo/pageNoCnPerPage + (selectPageNo%pageNoCnPerPage>0?1:0) ) * pageNoCnPerPage;
				// 留뚯빟 [�걹 �럹�씠吏� 踰덊샇] > [留덉�留� �럹�씠吏� 踰덊샇]  寃쎌슦 [�걹 �럹�씠吏� 踰덊샇]瑜� [留덉�留� �럹�씠吏� 踰덊샇]濡� 諛붽씀湲�
				if( this.endPageNo>this.lastPageNo ) { this.endPageNo = this.lastPageNo; }

			//-------------------------------
			// �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �떆�옉 �뻾�쓽 寃��깋 紐⑸줉 �긽 [�닚�꽌 踰덊샇] �뼸湲�
			//-------------------------------
			this.beginRowNo = (selectPageNo * rowCntPerPage) - (rowCntPerPage) + 1;
			//-------------------------------
			// �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �걹 �뻾�쓽 寃��깋 紐⑸줉 �긽 �닚�꽌 踰덊샇 �뼸湲�. 
			//-------------------------------
			this.endRowNo = (selectPageNo * rowCntPerPage);
				// 留뚯빟 [珥� �뻾�쓽 媛쒖닔] < [留덉�留� �뻾�쓽 �닚�꽌踰덊샇]  寃쎌슦 [留덉�留� �뻾�쓽 �닚�꽌踰덊샇]瑜� [珥� �뻾�쓽 媛쒖닔]濡� 諛붽씀湲�
				if( this.endRowNo>totRowCnt ) {  this.endRowNo = totRowCnt;  }
			//-------------------------------
			// �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �떆�옉 �뻾�쓽 寃��깋 紐⑸줉 �긽 [�뿭�닚 �닚�꽌 踰덊샇] �뼸湲�
			//-------------------------------
			this.beginRowNo_desc = totRowCnt - this.beginRowNo + 1;

		}
	}		
	//--------------------------------------------------
	
	
	/**/
//************************************************************************
}
//************************************************************************





//public int selectPageNo;      // �꽑�깮 �럹�씠吏� 踰덊샇
//public int rowCntPerPag;      // �럹�씠吏� �떦 蹂댁씠�뒗 �뻾�쓽 媛쒖닔
//public int pageNoCnPerPage;   // �럹�씠吏� �떦 蹂댁씠�뒗 �럹�씠吏� 踰덊샇 媛쒖닔
//public int totRowCnt;         // 珥� �뻾�쓽 媛쒖닔
//---
//public int beginPrintNo_asc;  // �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �떆�옉 �뻾�쓽 寃��깋 紐⑸줉 �긽 �닚�꽌 踰덊샇          // �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �떆�옉 �뻾�쓽 �삤由� �닚�꽌 �떆�옉 異쒕젰 踰덊샇
//public int beginPrintNo_desc; // �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �떆�옉 �뻾�쓽 寃��깋 紐⑸줉 �긽 �뿭�닚 �닚�꽌 踰덊샇          // �꽑�깮 �럹�씠吏��뿉�꽌 蹂댁씠�뒗 �떆�옉 �뻾�쓽 �궡由� �닚�꽌 �떆�옉 異쒕젰 踰덊샇
//---
//this.beginPrintNo_asc = this.beginRowNo;
//this.beginPrintNo_desc = totRowCnt - this.beginPrintNo_asc + 1;
//this.beginRowNo_desc = totRowCnt - this.beginRowNo + 1;