package com.good.dao;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.good.dto.ReplyVO;
import com.good.dto.VideoBoardVO;

@Repository
public class ReplyDAOImpl implements ReplyDAO {

	@Inject
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.good.mapper.replyMapper";

	@Override
	public List<ReplyVO> listAll(int boardId) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll", boardId);
	}

	@Override
	public void insert(ReplyVO vo) throws Exception {
		sqlSession.insert(NAMESPACE + ".insert", vo);
	}

	@Override
	public void delete(ReplyVO vo) throws Exception {
		sqlSession.delete(NAMESPACE + ".delete",vo);
	}

	@Override
	public void update(ReplyVO vo) throws Exception {
		sqlSession.update(NAMESPACE + ".update", vo);
	}

	@Override
	public void reInsert(ReplyVO vo) throws Exception {
		sqlSession.insert(NAMESPACE + ".reInsert", vo);
	}

	@Override
	public int isReReply(ReplyVO vo) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".isReReply", vo);
	}

	@Override
	public List<ReplyVO> reList(int replyCommentId) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".reList", replyCommentId);
	}

}
