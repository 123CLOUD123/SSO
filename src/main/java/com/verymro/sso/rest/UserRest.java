package com.verymro.sso.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Slf4j
public class UserRest {

	@GetMapping("/values")
	public String getUsername() {
		return "上海嘉岩";
	}
	
}
