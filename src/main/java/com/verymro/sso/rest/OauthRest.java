package com.verymro.sso.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/oauth")
@Slf4j
public class OauthRest {

	@GetMapping("/mytoken")
	public String getToken() {
		String a = "123";
		return "123sfd";
	}
	
}
