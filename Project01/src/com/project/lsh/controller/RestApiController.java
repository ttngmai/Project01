package com.project.lsh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.lsh.beans.CommentBean;
import com.project.lsh.beans.ContentBean;
import com.project.lsh.service.LikeService;
import com.project.lsh.service.UserService;

@RestController
public class RestApiController {
	@Autowired
	private UserService userService;
	@Autowired
	private LikeService likeService;
	
	@GetMapping("/user/checkUserIdExist/{user_id}")
	public String checkUserIdExist(@PathVariable String user_id) {
		boolean chk = userService.checkUserIdExist(user_id);
		
		return chk + "";
	}
	
	@PostMapping("/like/{content_idx}/{like_flag}")
	public ContentBean insertLikeContent(@PathVariable int content_idx,
									 	 @PathVariable String like_flag) {

		return likeService.insertLikeContent(content_idx, like_flag);
	}
	
	@PostMapping("/like/{content_idx}/{comment_idx}/{like_flag}")
	public CommentBean insertLikeComment(@PathVariable int content_idx,
										 @PathVariable int comment_idx,
									 	 @PathVariable String like_flag) {

		return likeService.insertLikeComment(content_idx, comment_idx, like_flag);
	}
}
