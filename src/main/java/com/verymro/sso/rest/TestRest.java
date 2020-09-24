package com.verymro.sso.rest;

import java.io.IOException;


import javax.annotation.Resource;

import org.springframework.cache.CacheManager;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
		int a = 1;
		int b = 2;
		int c = 3;
		return "hello world 123 123 eeeeeeeee";
	}
	
	@GetMapping("/test2")
	public String t2() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (!(authentication instanceof AnonymousAuthenticationToken)) {
		    String currentUserName = authentication.getName();
		    return currentUserName;
		}
		
		return "test2222222222222";
	}
	
	
	@GetMapping("/home")
	public String home() {
		return "test";
	}
}
