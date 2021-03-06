package com.lvmama.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TilesAction {
	@RequestMapping("tiles")
	public String test(Model model,HttpSession req){
		model.addAttribute("name1", "李四_model中");
		req.setAttribute("name", "李四1");
		return "tiles";
	}
	@RequestMapping("tiles1")
	public String test1(Model model,HttpSession req){
		model.addAttribute("name1", "王五_model中");
		//req.setAttribute("name", "王五1");
		return "tiles1";
	}
	@RequestMapping("tiles2")
	public String test2(Model model,HttpSession req){
		model.addAttribute("name1", "赵六_model中");
		req.setAttribute("name", "赵六1");
		return "tiles2";
	}
	@RequestMapping("tiles3")
	public String test3(Model model,HttpSession req){
		model.addAttribute("name1", "大秦_model中");
		req.setAttribute("name", "大秦1");
		return "tiles3";
	}
}
