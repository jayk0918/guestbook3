package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.GuestBookDao;
import com.javaex.vo.GuestBookVo;

@Service
public class GuestBookService {
	
	@Autowired
	private GuestBookDao guestBookDao;
	
	public List<GuestBookVo> getList(){
		return guestBookDao.getList();
	}
	
	public int personInsert(GuestBookVo guestBookVo) {
		return guestBookDao.insert(guestBookVo);
	}
	
	public GuestBookVo getList(int num) {
		return guestBookDao.getList(num);
	}
	
	public int delete(String password, int no) {
		return guestBookDao.delete(password, no);
	}
	
	
	
}
