package com.kfit.spring_boot_mybatis;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;

@Controller
public class TestController {
	
	@Autowired
	private DemoService demoService;
	
//	@Value("{application.message:Hello Word}")
//	private String Message = "Hello Word";
	
	@RequestMapping(value="/hello")
	public String hello(){
		System.out.println("hello word");
		return "login";
	}
	
	@RequestMapping("/login")
	public Demo login(String name){
		Demo login = demoService.login(name);
		return login;
	}
	
	@RequestMapping("/getUser")
	public Demo getUser(){
		Demo demo = new Demo();
		demo.setId(1);
		demo.setName("张三");
		return demo;
	}
	
	
	@RequestMapping("/getAllUser")
	
	public List<Demo> getAllUser(){
		PageHelper.startPage(1, 3);
		List<Demo> allDemo = demoService.getAllDemo();
		return allDemo;
	}

}
