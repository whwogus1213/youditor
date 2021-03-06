package com.good.service;

import java.util.List;

import com.good.dto.NoticeBoardVO;
import com.good.dto.NoticeCategoryVO;
import com.good.dto.Search;
import com.good.dto.SearchBoard;

public interface NoticeBoardService {

	// 게시물 목록 + 페이징 + 검색
	public List<NoticeBoardVO> listAll(SearchBoard search) throws Exception;
	
	// 카테고리 정보 취득
	public NoticeCategoryVO getCatInfo(int categoryId) throws Exception;

	// 게시물 상세보기
	public NoticeBoardVO view(int boardId) throws Exception;

	// 게시물 총 갯수
	public int getBoardListCnt(Search search) throws Exception;

	// 글 쓰기
	public void insertNoticeBoard(NoticeBoardVO vo) throws Exception;
	
	// 권한 확인
	public int getEditAuth(int boardId) throws Exception;

	// 수정
	public void updateNoticeBoard(NoticeBoardVO vo) throws Exception;

	// 삭제
	public void deleteNoticeBoard(NoticeBoardVO vo) throws Exception;

	// 조회수
	public void viewCount(int boardId) throws Exception;
	
	// 공지사항 랭킹
	public List<NoticeBoardVO> rankList() throws Exception;
}
