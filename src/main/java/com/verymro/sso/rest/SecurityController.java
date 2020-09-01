package com.verymro.sso.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {

	@GetMapping("/error2")
	public String error() {
		return "错误";
	}
	
}
