package com.good.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.good.dao.ReplyDAO;
import com.good.dao.VideoBoardDAO;
import com.good.dto.ReplyVO;
import com.good.dto.VideoBoardVO;

@Service
public class ReplyServiceImpl implements ReplyService {

	@Inject
	ReplyDAO dao;
	// 게시물 목록

	@Override
	public List<ReplyVO> listAll() throws Exception {
		return dao.listAll();
	}

	@Override
	public void insert(ReplyVO vo) throws Exception {
		dao.insert(vo);
		
	}
	

}
