package com.javaex.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@RequestMapping(value = "/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestBookController>addList()");
		
		GuestBookDao guestBookDao = new GuestBookDao();
		List<GuestBookVo> gList = guestBookDao.getList();
		
		model.addAttribute("gList", gList);
		
		return "/WEB-INF/views/addList.jsp";
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController>insert()");
		System.out.println(guestBookVo);
		
		GuestBookDao guestBookDao = new GuestBookDao();
		guestBookDao.insert(guestBookVo);
		
		return "redirect:/addList";
	}
	
	
	@RequestMapping(value = "/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @RequestParam("no") int no) {
		System.out.println("GuestBookController>deleteForm()");
		
		GuestBookDao guestBookDao = new GuestBookDao();
		GuestBookVo guestBookVo = guestBookDao.getList(no);
		
		model.addAttribute("guestBookVo", guestBookVo);
		
		return "/WEB-INF/views/deleteForm.jsp";
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController>delete()");
		
		GuestBookDao guestBookDao = new GuestBookDao();
		
		String password = guestBookVo.getPassword();
		int no = guestBookVo.getNo();
		
		guestBookDao.delete(password, no);
		
		return "redirect:/addList";
	}
	
	
}