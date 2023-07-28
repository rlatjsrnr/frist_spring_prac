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
	
	@Override
	public int create(BoardVO vo) throws Exception {
		int result = session.insert("boardMapper.insert",vo);
		return result;
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board = session.selectOne("boardMapper.selectDetail", bno);
		return board;
	}

	@Override
	public int update(BoardVO vo) throws Exception {
		int result = session.update("boardMapper.update", vo);
		return result;
	}

	@Override
	public int delete(int bno) throws Exception {
		int result = session.delete("boardMapper.delete", bno);
		return result;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		session.update("boardMapper.updateCnt", bno);		
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> list = session.selectList("boardMapper.selectList");
		return list;
	}

	@Override
	public int totalCount() throws Exception {
		int totalCount = session.selectOne("boardMapper.totalCount");
		return totalCount;
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> list = session.selectList("boardMapper.selectList", cri);		
		return list;
	}

}
