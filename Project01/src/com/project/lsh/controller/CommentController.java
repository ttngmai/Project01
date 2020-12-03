package com.project.lsh.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.project.lsh.beans.CommentBean;
import com.project.lsh.beans.PageBean;
import com.project.lsh.service.BoardService;
import com.project.lsh.service.CommentService;

@Controller
@RequestMapping("/comment")
public class CommentController {
	@Autowired
	private CommentService commentService;
	@Autowired
	private BoardService boardService;

	@RequestMapping(value="/write", method = RequestMethod.POST, produces="application/text; charset=UTF-8")
	@ResponseBody
	public String write(@ModelAttribute("writeCommentBean") CommentBean writeCommentBean) {
		try {
			ObjectMapper obm = new ObjectMapper();
			Map<String, Object> writeResultMap = new HashMap<String, Object>();
			int content_idx = writeCommentBean.getContent_idx();
			int comment_parent_idx = writeCommentBean.getComment_parent_idx();
			
			commentService.insertComment(writeCommentBean);
			
			if (comment_parent_idx == 0) {
				writeResultMap.put("comment_cnt", boardService.selectCommentCnt(content_idx));
			} else {
				writeResultMap.put("reply_cnt", commentService.selectReplyCnt(content_idx, comment_parent_idx));
			}
			
			writeResultMap.put("last_page", commentService.selectLastPage(content_idx, comment_parent_idx));
			
			String jsonStr = obm.writerWithDefaultPrettyPrinter().writeValueAsString(writeResultMap);
			
			return jsonStr;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET, produces="application/text; charset=UTF-8")
	@ResponseBody 
	public String read(@RequestParam("content_idx") int content_idx,
					   @RequestParam("comment_parent_idx") int comment_parent_idx,
					   @RequestParam(value = "page", defaultValue = "1") int page) {
        try {
    		ObjectMapper obm  = new ObjectMapper();
    		Map<String ,Object> commentListMap = new HashMap<String, Object>();
    		List<CommentBean> commentList = commentService.selectCommentList(content_idx, comment_parent_idx, page);
    		PageBean pageBean = commentService.createPaging(content_idx, comment_parent_idx, page);
            
            commentListMap.put("comment_list", commentList);
            commentListMap.put("page_info", pageBean);
            
			String jsonStr = obm.writerWithDefaultPrettyPrinter().writeValueAsString(commentListMap);
			
			return jsonStr;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@RequestMapping(value="/read_one_text", method = RequestMethod.GET, produces="application/text; charset=UTF-8")
	@ResponseBody
	public String readOneText(@RequestParam("comment_idx") int comment_idx) {
		return commentService.selectCommentText(comment_idx);
	}
	
	@PostMapping("/modify")
	@ResponseBody
	public void modify(@ModelAttribute("modifyCommentBean") CommentBean modifyCommentBean) {
		commentService.updateComment(modifyCommentBean);
	}
	
	@PostMapping("/delete")
	@ResponseBody
	public void delete(@RequestParam("comment_idx") int comment_idx) {
		commentService.updateCommentDeleteStatus(comment_idx);
	}
	
	@GetMapping("/not_writer")
	public String notWriter() {
		return "board/not_writer";
	}
}
