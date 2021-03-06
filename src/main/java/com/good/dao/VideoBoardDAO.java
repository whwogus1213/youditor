package com.good.dao;

import java.util.List;

import com.good.dto.SearchBoard;
import com.good.dto.VideoBoardVO;
import com.good.dto.VideoCategoryVO;

public interface VideoBoardDAO {

	// 게시물 목록
	public List<VideoBoardVO> listAll(SearchBoard search) throws Exception;

	// 게시물 갯수
	public int getBoardListCnt(SearchBoard search) throws Exception;
	
	// 게시물 카테고리 정보
	public VideoCategoryVO getCatInfo(int categoryId) throws Exception;

	// 게시물 보기
	public VideoBoardVO view(int boardId) throws Exception;

	// 글 쓰기
	public void insertVideoBoard(VideoBoardVO vo) throws Exception;

	// 수정
	public void updateVideoBoard(VideoBoardVO vo) throws Exception;

	// 삭제
	public void deleteVideoBoard(VideoBoardVO vo) throws Exception;

	//조회수
	public void viewCount(int boardId) throws Exception;

	// 팔로우 체크
	public int followCheck(int accountId, int accountId2) throws Exception;
	
	// 게시물의 editAuthority
	public int getEditAuth(int boardId) throws Exception;

//	// 팔로우 게시물 목록
//	public List<VideoBoardVO> followBoardList(int followAccountId) throws Exception;

}
