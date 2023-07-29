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
	
	/**
	 * 게시글 등록
	 */
	@Override
	public String regist(BoardVO board) throws Exception {
		int result = bd.create(board);
		if(result > 0) {
			return "board/read";
		}
		return "board/register";
	}

	/**
	 * 게시글 조회수
	 */
	@Override
	public void updateCnt(int bno) throws Exception {
		bd.updateCnt(bno);
	}

	/**
	 * 게시글 상세페이지
	 */
	@Override
	public BoardVO read(int bno) throws Exception {
		BoardVO board = bd.read(bno);
		return board;
	}

	/**
	 * 전체 게시글 목록
	 */
	@Override
	public List<BoardVO> listAll() throws Exception {
		List<BoardVO> list = bd.listAll();
		return list;
	}

	/**
	 * 게시글 수정
	 */
	@Override
	public String modify(BoardVO board) throws Exception {
		int result = bd.update(board);
		if(result > 0) {
			return "redirect:read?bno="+board.getBno();
		}
		return "board/modify";
	}

	/**
	 * 게시글 삭제
	 */
	@Override
	public String remove(int bno) throws Exception {
		int result = bd.delete(bno);
		if(result > 0) {
			return "redirect:listPage";
		}
		return "redirect:read?bno="+bno;
	}

	/**
	 * 게시글 목록 - 페이징
	 */
	@Override
	public List<BoardVO> listCriteria(Criteria cri) throws Exception {
		List<BoardVO> list = bd.listCriteria(cri);		
		return list;
	}

	/**
	 * PageMaker 요청
	 */
	@Override
	public PageMaker getPageMaker(Criteria cri) throws Exception {		
		PageMaker pm = new PageMaker(cri, bd.totalCount());
		return pm;
	}

}
