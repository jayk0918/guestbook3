package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.GuestBookService;
import com.javaex.vo.GuestBookVo;

@Controller
public class GuestBookController {
	
	@Autowired
	private GuestBookService guestBookService;
	
	
	@RequestMapping(value = "/addList", method = {RequestMethod.GET, RequestMethod.POST})
	public String addList(Model model) {
		System.out.println("GuestBookController>addList()");
		
		List<GuestBookVo> gList = guestBookService.getList();
		
		model.addAttribute("gList", gList);
		
		return "addList";
	}
	
	@RequestMapping(value = "/add", method = {RequestMethod.GET, RequestMethod.POST})
	public String insert(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController>insert()");
		System.out.println(guestBookVo);
		
		guestBookService.personInsert(guestBookVo);
		
		return "redirect:/addList";
	}
	
	
	@RequestMapping(value = "/deleteForm", method = {RequestMethod.GET, RequestMethod.POST})
	public String deleteForm(Model model, @RequestParam("no") int no) {
		System.out.println("GuestBookController>deleteForm()");
		
		GuestBookVo guestBookVo = guestBookService.getList(no);
		
		model.addAttribute("guestBookVo", guestBookVo);
		
		return "deleteForm";
	}
	
	@RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@ModelAttribute GuestBookVo guestBookVo) {
		System.out.println("GuestBookController>delete()");
		
		String password = guestBookVo.getPassword();
		int no = guestBookVo.getNo();
		
		guestBookService.delete(password, no);
		
		return "redirect:/addList";
	}
	
	
}