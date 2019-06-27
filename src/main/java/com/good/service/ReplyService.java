package com.good.service;

import java.util.List;
import java.util.Map;

import com.good.dto.ReplyVO;
import com.good.dto.VideoBoardVO;

public interface ReplyService {
	
	// 게시물 목록
	public List<ReplyVO> listAll() throws Exception;
	
	public void insert(ReplyVO vo) throws Exception;
}
