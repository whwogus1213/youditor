package com.good.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.good.dto.SearchBoard;
import com.good.dto.VideoBoardVO;
import com.good.dto.VideoCategoryVO;

@Repository
public class VideoBoardDAOImpl implements VideoBoardDAO {

	// 주입
	@Inject
	private SqlSession sqlSession;

	private static final String NAMESPACE = "com.good.mapper.videoBoardMapper";

	// 게시물 목록
	@Override
	public List<VideoBoardVO> listAll(SearchBoard search) throws Exception {
		return sqlSession.selectList(NAMESPACE + ".listAll", search);
	}

	// 게시물 갯수
	@Override
	public int getBoardListCnt(SearchBoard search) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getBoardListCnt", search);
	}
	
	// 게시물 카테고리 정보
	public VideoCategoryVO getCatInfo(int categoryId) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getCatInfo", categoryId);
	}

	// 게시물 보기
	@Override
	public VideoBoardVO view(int boardId) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".view", boardId);
	}

	// 글쓰기
	@Override
	public void insertVideoBoard(VideoBoardVO vo) throws Exception {
		sqlSession.insert(NAMESPACE + ".insertVideoBoard", vo);
	}

	// 수정
	@Override
	public void updateVideoBoard(VideoBoardVO vo) throws Exception {
		sqlSession.update(NAMESPACE + ".updateVideoBoard", vo);
	}

	// 삭제
	@Override
	public void deleteVideoBoard(VideoBoardVO vo) throws Exception {
		sqlSession.delete(NAMESPACE + ".deleteVideoBoard", vo);
	}

	//조회수
	@Override
	public void viewCount(int boardId) throws Exception {
		sqlSession.update(NAMESPACE + ".viewCount", boardId);
	}

	// 팔로우 체크
	@Override
	public int followCheck(int accountId, int accountId2) throws Exception {
		Map<String, Integer> map = new HashMap<String, Integer>();
		map.put("accountId", accountId);
		map.put("accountId2", accountId2);
		
		return sqlSession.selectOne(NAMESPACE + ".followCheck", map);
	}
	
	// 게시물의 editAuthority
	@Override
	public int getEditAuth(int boardId) throws Exception {
		return sqlSession.selectOne(NAMESPACE + ".getEditAuth", boardId);
	}

//	// 팔로우 게시물 목록
//	@Override
//	public List<VideoBoardVO> followBoardList(int followAccountId) throws Exception {
//		return sqlSession.selectList(NAMESPACE + ".followBoardList", followAccountId);
//	}

}
