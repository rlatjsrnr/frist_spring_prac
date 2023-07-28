package com.bitc.board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bitc.board.dao.BoardDAO;
import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

@Service
public class BoardServiceImpl implements BoardService {
	
	@Autowired
	BoardDAO bd;
	
	@Override
	public String regist(BoardVO board) throws Exception {
		int result = bd.create(board);
		if(result > 0) {
			return "성공";
		}
		return null;
	}

	@Override
	public void updateCnt(int bno) throws Exception {
		bd.updateCnt(bno);
		
	}

	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board = bd.read(bno);
		return board;
	}

	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> list = bd.listAll();
		return list;
	}

	@Override
	public String modify(BoardVO board) throws Exception {
		int result = bd.update(board);
		if(result > 0) {
			return "성공";
		}
		return null;
	}

	@Override
	public String remove(int bno) throws Exception {
		int result = bd.delete(bno);
		if(result > 0) {
			return "성공";
		}
		return null;
	}

	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> list = bd.listCriteria(cri);
		
		return list;
	}

	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {
		int totalCount = bd.totalCount();
		PageMaker pm = new PageMaker(cri, totalCount);
		return pm;
	}

}
