package com.app.controller;


import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.dao.IAdminDao;


@RestController
@RequestMapping
public class TestController 
{
	
	@Autowired
	private IAdminDao dao;
	
	public TestController() 
	{
	System.out.println("in TestController");
	}
	
	@GetMapping
	public Integer changeStatusToAvailable(Date dateTo)
	{
		//Date date=new Date();
		dao.getBookings(dateTo);
		return 1;
	}
}
