package com.verymro.sso.service.impl;

import javax.annotation.Resource;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.verymro.sso.entity.SysUser;
import com.verymro.sso.entity.Test;
import com.verymro.sso.mapper.TestMapper;
import com.verymro.sso.service.TestService;


@Service
public class TestServiceImpl implements UserDetailsService {
	
	@Resource
	private TestMapper testMapper;
	
	public String test() {
		
		LambdaQueryWrapper<Test> qry = Wrappers.lambdaQuery();
		Test t = testMapper.selectOne(qry);
		
		return t.getName();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
//		return null;
		
//		return new SocialUser(username, userInfo.getPassword(), true, true, true, true, authorities);
		
		return new SysUser(1L, "username", "password");
	}
	
}
