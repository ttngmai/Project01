package com.project.lsh.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.lsh.beans.BoardBean;
import com.project.lsh.beans.ContentBean;
import com.project.lsh.service.MainService;

@Controller
public class MainController {
	@Autowired
	private MainService mainService;
	
	@GetMapping("/main")
	public String main(Model model) {
		List<BoardBean> boardList = mainService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		ArrayList<List<ContentBean>> contentList = new ArrayList<List<ContentBean>>();
		
		for (int i = 1; i <= 4; i++) {
			List<ContentBean> list = mainService.getMainContentList(i);
			contentList.add(list);
		}
		
		model.addAttribute("contentList", contentList);
		
		return "main";
	}
}
