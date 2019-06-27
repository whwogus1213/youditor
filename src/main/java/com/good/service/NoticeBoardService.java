package com.good.service;

import java.util.List;
import java.util.Map;

import com.good.dto.NoticeBoardVO;

public interface NoticeBoardService {

	// 게시물 목록
	public List<NoticeBoardVO> listAll() throws Exception;

	public NoticeBoardVO view(int boardId) throws Exception;

	//글 쓰기
	public void insertNoticeBoard(NoticeBoardVO vo) throws Exception;

	//수정
	public void updateNoticeBoard(NoticeBoardVO vo) throws Exception;

	//삭제
	public void deleteNoticeBoard(int boardId) throws Exception;
}
