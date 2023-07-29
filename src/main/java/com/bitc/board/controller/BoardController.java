package com.bitc.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bitc.board.service.BoardService;
import com.bitc.board.util.Criteria;
import com.bitc.board.util.PageMaker;
import com.bitc.board.vo.BoardVO;

@Controller
public class BoardController {
	
	@Autowired
	BoardService bs;
	
	/* "게시글 작성 페이지 요청" */
	// @RequestMapping(value="/register",method=RequestMethod.GET)
	@GetMapping("board/register")
	public void register()throws Exception{
		// /board/register
		// WEB-INF/views/board/register.jsp
		System.out.println("게시글 작성 페이지 요청");
	}
	
	/**
	 * 게시글 등록 요청 처리 추가
	 */
	@PostMapping("board/register")
	public String registerSubmit(BoardVO board) {
		String location = null;
		try {
			location = bs.regist(board);
						
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}
		
	
	/**
	 * 게시글 상세보기 요청 처리 - 게시글 번호
	 */
	@GetMapping("board/read")
	public String read(int bno, Model model) {
		try {
			BoardVO board = bs.read(bno);
			bs.updateCnt(bno);
			if(board != null) {
				model.addAttribute(board);
				return "board/read";
			}			
		} catch (Exception e) {
			e.printStackTrace();
		}		
		return "board/listPage";
	}

	/**
	 * 게시글 수정 페이지 요청
	 * - 게시글 수정 화면 출력 
	 */
	@GetMapping("board/modify")
	public void modify(Model model, int bno) {
		try {
			BoardVO board = bs.read(bno);
			model.addAttribute("boardVO", board);
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("게시글 수정 페이지 요청");
	}

	/**
	 * 게시글 수정 처리 요청
	 * 게시글 수정 완료 후 redirect - 수정게시글 상세보기 페이지 이동
	 */
	@PostMapping("board/modify")
	public String modify(Model model, BoardVO board) {
		String location = null;
		try {
			location = bs.modify(board);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	}

	/**
	 * 게시글 삭제 완료 후 listPage 페이지 로 이동 - redirect 
	 */
	 @GetMapping("board/remove")
	 public String remove(int bno, Model model) {
		 String location = null;
		 try {
			 location = bs.remove(bno);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return location;
	 }
	
	 @GetMapping("board/listAll")
	 public String listAll(Model model) {
		 try {
			List<BoardVO> list = bs.listAll();
			model.addAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "board/listPage";
	 }
	 
	/**
	 *  페이징 처리 된 게시글 출력 페이지
	 *  param : 요청 page, perPageNum 
	 */
	// board/listPage
	 @GetMapping("board/listPage")
	 public String listPage(Model model, String page, Criteria cri) {
		 try {			
			if(page == null) {
				page = "1";
			}
			cri.setPage(Integer.parseInt(page));
			
			PageMaker pm = bs.getPageMaker(cri);
			List<BoardVO> list = bs.listCriteria(cri);		
			model.addAttribute("list", list);
			model.addAttribute("pm", pm);
		} catch (Exception e) {
			e.printStackTrace();
		}
		 return "board/listPage";
	 }

	
}















