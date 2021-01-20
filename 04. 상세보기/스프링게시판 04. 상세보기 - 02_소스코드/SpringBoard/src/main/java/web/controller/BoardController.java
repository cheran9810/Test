package web.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import web.dto.Board;
import web.service.face.BoardService;
import web.util.Paging;

@Controller
public class BoardController {

	@Autowired private BoardService boardService;
	
	@RequestMapping(value="/board/list")
	public void list(Paging curPage, Model model) {
		
		//페이징 계산
		Paging paging = boardService.getPaging(curPage);
		model.addAttribute("paging", paging);

		//게시글 목록
		List<Board> list = boardService.list(paging);
		model.addAttribute("list", list);
		
	}

	@RequestMapping(value="/board/view")
	public String view( Board viewBoard, Model model) {
		
		// 게시글 번호가 1보다 작으면 목록으로 보내기
		if(viewBoard.getBoardNo() < 1) {
			return "redirect:/board/list";
		}
		
		// 게시글 상세 정보 전달
		viewBoard = boardService.boardView(viewBoard);
		model.addAttribute("view", viewBoard);

		return "board/view";
	}
	
}


