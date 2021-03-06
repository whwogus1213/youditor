package com.good.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.good.dao.VideoBoardDAO;
import com.good.dto.SearchBoard;
import com.good.dto.VideoBoardVO;
import com.good.dto.VideoCategoryVO;

@Service
public class VideoBoardServiceImpl implements VideoBoardService {

	// 주입
	@Inject
	VideoBoardDAO videoBoardDAO;

	// 게시물 목록
	@Override
	public List<VideoBoardVO> listAll(SearchBoard search) throws Exception {
		return videoBoardDAO.listAll(search);
	}
	
	// 게시물 갯수
	@Override
	public int getBoardListCnt(SearchBoard search) throws Exception {
		return videoBoardDAO.getBoardListCnt(search);
	}
	
	// 게시물 카테고리 정보
	public VideoCategoryVO getCatInfo(int categoryId) throws Exception {
		return videoBoardDAO.getCatInfo(categoryId);
	}

	// 게시물 읽기
	@Override
	public VideoBoardVO view(int boardId) throws Exception {
		videoBoardDAO.viewCount(boardId);
		return videoBoardDAO.view(boardId);
	}

	// 글쓰기
	@Override
	public void insertVideoBoard(VideoBoardVO vo) throws Exception {
		videoBoardDAO.insertVideoBoard(vo);
	}

	// 수정
	@Override
	public void updateVideoBoard(VideoBoardVO vo) throws Exception {
		videoBoardDAO.updateVideoBoard(vo);
	}

	// 삭제
	@Override
	public void deleteVideoBoard(VideoBoardVO vo) throws Exception {
		videoBoardDAO.deleteVideoBoard(vo);
	}
	
	// 조회수
	@Override
	public void viewCount(int boardId) throws Exception {
		videoBoardDAO.viewCount(boardId);
	}

	// 팔로우 체크
	@Override
	public int followCheck(int accountId, int accountId2) throws Exception {
		return videoBoardDAO.followCheck(accountId, accountId2);
	}
	
	// 게시물의 editAuthority
	@Override
	public int getEditAuth(int boardId) throws Exception {
		return videoBoardDAO.getEditAuth(boardId);
	}

//	// 팔로우 게시물 목록
//	@Override
//	public List<VideoBoardVO> followBoardList(int followAccountId) throws Exception {
//		return videoBoardDAO.followBoardList(followAccountId);
//	}

	
}
