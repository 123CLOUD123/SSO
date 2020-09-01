package com.verymro.sso.rest;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.verymro.sso.service.TestService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/rest")
@Slf4j
public class TestRest {
	
	@GetMapping("/test")
	public String test() {
		return "hello world 123 123 eeeeeeeee";
//		return ts.test();
	}
	
	@GetMapping("/test2")
	public String t2() {
		return "test2222222222222";
	}
	
}
