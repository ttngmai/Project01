package com.project.lsh.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.lsh.beans.ContentBean;
import com.project.lsh.beans.PageBean;
import com.project.lsh.beans.UserBean;
import com.project.lsh.service.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	@Autowired
	private BoardService boardService;
	@Resource(name = "loginUserBean")
	private UserBean loginUserBean;

	@GetMapping("/main")
	public String main(@RequestParam("board_idx") int board_idx,
					   @RequestParam(value = "searchType", defaultValue = "") String searchType,
					   @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
					   @RequestParam(value = "page", defaultValue = "1") int page,
					   Model model) {
		String board_name = boardService.selectBoardName(board_idx);
		List<ContentBean> contentList = boardService.selectContentList(board_idx, searchType, searchKeyword, page);
		PageBean pageBean = boardService.selectContentCnt(board_idx, searchType, searchKeyword, page);

		model.addAttribute("board_idx", board_idx);
		model.addAttribute("board_name", board_name);
		model.addAttribute("contentList", contentList);
		model.addAttribute("pageBean", pageBean);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("page", page);
		
		return "board/main";
	}
	
	@GetMapping("/read")
	public String read(@RequestParam("board_idx") int board_idx,
					   @RequestParam("content_idx") int content_idx,
					   @RequestParam(value = "searchType", defaultValue = "") String searchType,
					   @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
					   @RequestParam(value = "page", defaultValue = "1") int page,
					   Model model) {
		ContentBean readContentBean = boardService.selectContent(content_idx);
		
		model.addAttribute("loginUserBean", loginUserBean);
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("readContentBean", readContentBean);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("page", page);
		
		return "board/read";
	}
	
	@GetMapping("/write")
	public String write(@RequestParam("board_idx") int board_idx,
					    @RequestParam(value = "searchType", defaultValue = "") String searchType,
					    @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
					    @RequestParam(value = "page", defaultValue = "1") int page,
						@ModelAttribute("writeContentBean") ContentBean writeContentBean,
						Model model) {
		writeContentBean.setBoard_idx(board_idx);
		
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("page", page);
		
		return "board/write";
	}
	
	@PostMapping("/write_pro")
	public String writePro(@Valid @ModelAttribute("writeContentBean") ContentBean writeContentBean,
						    BindingResult result,
						    Model model) {
		model.addAttribute("board_idx", writeContentBean.getBoard_idx());
		
		if (result.hasErrors()) {
			return "board/write";
		}
		
		boardService.insertContent(writeContentBean);
		
		return "board/write_success";
	}
	
	@GetMapping("/modify")
	public String modify(@RequestParam("board_idx") int board_idx,
						 @RequestParam("content_idx") int content_idx,
						 @RequestParam(value = "searchType", defaultValue = "") String searchType,
						 @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
						 @RequestParam("page") int page,
						 @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
						 Model model) {
		model.addAttribute("board_idx", board_idx);
		model.addAttribute("content_idx", content_idx);
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("page", page);
		
		ContentBean tempContentBean = boardService.selectContent(content_idx);
		
		modifyContentBean.setContent_idx(content_idx);
		modifyContentBean.setBoard_idx(board_idx);
		modifyContentBean.setContent_subject(tempContentBean.getContent_subject());
		modifyContentBean.setContent_text(tempContentBean.getContent_text());
		modifyContentBean.setContent_file(tempContentBean.getContent_file());
		modifyContentBean.setContent_writer_idx(tempContentBean.getContent_writer_idx());
		modifyContentBean.setContent_writer_name(tempContentBean.getContent_writer_name());
		modifyContentBean.setContent_date(tempContentBean.getContent_date());
		
		return "board/modify";
	}
	
	@PostMapping("/modify_pro")
	public String modifyPro(@RequestParam(value = "searchType", defaultValue = "") String searchType,
							 @RequestParam(value = "searchKeyword", defaultValue = "") String searchKeyword,
			 				 @RequestParam("page") int page,
							 @Valid @ModelAttribute("modifyContentBean") ContentBean modifyContentBean,
							 BindingResult result,
							 Model model) {
		model.addAttribute("board_idx", modifyContentBean.getBoard_idx());
		model.addAttribute("content_idx", modifyContentBean.getContent_idx());
		model.addAttribute("searchType", searchType);
		model.addAttribute("searchKeyword", searchKeyword);
		model.addAttribute("page", page);
		
		if (result.hasErrors()) {
			return "board/modify";
		}
		
		boardService.updateContent(modifyContentBean);
		
		return "board/modify_success";
	}
	
	@GetMapping("/delete")
	public String delete(@RequestParam("board_idx") int board_idx,
						 @RequestParam("content_idx") int content_idx,
						 Model model) {
		boardService.deleteContent(content_idx);
		
		model.addAttribute("board_idx", board_idx);
		
		return "board/delete";
	}
	
	@GetMapping("/not_writer")
	public String notWriter() {
		return "board/not_writer";
	}
	
}
