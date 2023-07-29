package com.bitc.board.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bitc.board.util.Criteria;
import com.bitc.board.vo.BoardVO;

@Repository
public class BoardDAOImpl implements BoardDAO {
	
	@Autowired
	SqlSession session;
	
	/**
	 * 게시글 삽입
	 */
	@Override
	public int create(BoardVO vo) throws Exception {
		int result = session.insert("boardMapper.insert",vo);
		return result;
	}
	
	/**
	 * 게시글 상세보기
	 */
	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board = session.selectOne("boardMapper.selectDetail", bno);
		return board;
	}
	
	/**
	 * 게시글 수정
	 */
	@Override
	public int update(BoardVO vo) throws Exception {
		int result = session.update("boardMapper.update", vo);
		return result;
	}
	
	/**
	 * 게시글 삭제
	 */
	@Override
	public int delete(int bno) throws Exception {
		int result = session.delete("boardMapper.delete", bno);
		return result;
	}

	/**
	 * 게시글 조회수
	 */
	@Override
	public void updateCnt(int bno) throws Exception {
		session.update("boardMapper.updateCnt", bno);		
	}

	/**
	 * 전체 게시글 목록
	 */
	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> list = session.selectList("boardMapper.selectListAll");
		return list;
	}

	/**
	 * 게시글 개수
	 */
	@Override
	public int totalCount() throws Exception {
		int totalCount = session.selectOne("boardMapper.totalCount");
		return totalCount;
	}

	/**
	 * 게시글 목록 - 페이징
	 */
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> list = session.selectList("boardMapper.selectList", cri);		
		return list;
	}

}
