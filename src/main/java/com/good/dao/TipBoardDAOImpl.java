package com.good.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.good.dto.TipBoardVO;

@Repository
public class TipBoardDAOImpl implements TipBoardDAO {

	// 주입
	@Inject
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.good.mapper.tipBoardMapper";

	// 게시물 목록
	@Override
	public List<TipBoardVO> listAll() throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll");
	}

	// 게시물 보기
	@Override
	public TipBoardVO view(int boardId) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".view", boardId);
	}

	// 글쓰기
	@Override
	public void insertTipBoard(TipBoardVO vo) throws Exception {
		sqlSession.insert(NAMESPACE + ".insertTipBoard", vo);
	}

	// 수정
	@Override
	public void updateTipBoard(TipBoardVO vo) throws Exception {
		sqlSession.update(NAMESPACE + ".updateTipBoard", vo);
	}

	// 삭제
	@Override
	public void deleteTipBoard(int boardId) throws Exception {
		sqlSession.delete(NAMESPACE + ".deleteTipBoard", boardId);
	}
}
