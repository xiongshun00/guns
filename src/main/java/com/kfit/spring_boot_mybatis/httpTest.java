package com.kfit.spring_boot_mybatis;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class httpTest {
	
	@ResponseBody
	@RequestMapping("/aaa")
	public String testHttp(HttpServletRequest req) {
		System.out.println(req);
	    return null;
	}

}
