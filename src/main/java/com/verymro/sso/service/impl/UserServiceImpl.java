package com.verymro.sso.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
public class UserServiceImpl implements UserDetailsService {
	
	@Resource
	private TestMapper testMapper;
	
	public String test() {
		
		LambdaQueryWrapper<Test> qry = Wrappers.lambdaQuery();
		Test t = testMapper.selectOne(qry);
		
		return t.getName();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		SysUser user = new SysUser();
		user.setId(2L);
		user.setUsername("zh");
		user.setPassword("123");
		user.setAuthorities(defaultAuthorities());
		user.setIsAccountNonLocked(true);
		user.setIsAccountNonExpired(true);
		user.setIsCredentialsNonExpired(true);
		user.setIsEnabled(true);
		return user;
		
	}
	
	private List<GrantedAuthority> defaultAuthorities() {
		List<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();       
		authList.add(new SimpleGrantedAuthority("ROLE_USER"));      
		authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));     
		return authList;
	}
	
}
