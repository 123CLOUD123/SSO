package com.verymro.sso.service.impl;

import java.util.HashSet;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.verymro.sso.entity.SysUser;

public class SysUserServiceImpl implements UserDetailsService {

	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

//        UserCenter userCenter = userRepository.findByUserName(s);
//        if (null == userCenter) {
//            throw new UsernameNotFoundException("user not found");
//        }
		
		SysUser user = new SysUser();
		user.setUsername("zhanghao");
		user.setPassword("Cy123456");
		
		
        //查询用户权限然后赋值
        List<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(new HashSet<String>().toArray(new String[]{}));
        SysUser userDetails = new SysUser(user.getUsername(), user.getPassword(), true, authorities);
        return userDetails;
    }
	
}
