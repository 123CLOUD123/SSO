package com.verymro.sso.component;

import java.util.Collection;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.verymro.sso.entity.exception.JyException;
import com.verymro.sso.service.impl.UserServiceImpl;

/**
 * 认证处理
 * 
 * @author ZhangHao
 */
@Component
public class JyAuthenticationProvider implements AuthenticationProvider {
	
	@Resource
	private UserServiceImpl userService;
	
	@Resource
    private ApplicationEventPublisher publisher;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        if(StringUtils.isBlank(username)){
            throw new JyException("username用户名不可以为空");
        }
        if(StringUtils.isBlank(password)){
            throw new JyException("密码不可以为空");
        }
        //获取用户信息
        UserDetails user = userService.loadUserByUsername(username);
        //比较前端传入的密码明文和数据库中加密的密码是否相等
        if (!password.equals(user.getPassword())) {
            //发布密码不正确事件
            publisher.publishEvent(authentication);
            throw new JyException("password密码不正确");
        }
        
        //获取用户权限信息
        Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
