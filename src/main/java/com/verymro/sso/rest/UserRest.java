package com.verymro.sso.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.verymro.sso.entity.SysUser;
import com.verymro.sso.entity.vo.ZtUserVO;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/api")
@Slf4j
public class UserRest {

	@GetMapping("/values")
	public String getUsername() {
		return "上海嘉岩";
	}
	
	@GetMapping("/zt-user")
	public ZtUserVO getZtUser() {
		
		ZtUserVO user = new ZtUserVO();
		user.setUsername("zhanghao");
		user.setPwdHash("ZT-d0b9770fe1428ff99829fd0ca3f12686689c0f0ee51e233ceac716a9a129385c");
		return user;
	}
	
	@PostMapping("/login")
	public void login(@RequestBody ZtUserVO vo) {
		log.info("------ 用户 ------->>>  {}", vo);
		String name = vo.getUsername();
		
		String pd = vo.getPassword();
		
		int a = 10;
	}
	
}
