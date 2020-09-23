package com.verymro.sso.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.verymro.sso.service.TestService;
import com.verymro.sso.service.impl.UserServiceImpl;

@Configuration
@EnableWebSecurity
@Order(10)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @Autowired
    private UserServiceImpl userService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
	
//	@Bean
//	public NoOpPasswordEncoder passwordEncoder() {
//        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
//    }
	
//	@Override
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//		super.configure(auth);
////		auth.authenticationProvider(provider);
//		auth.userDetailsService(userService); 
//    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
//		super.configure(http);
		http.csrf().disable()
			.cors().disable()
			.httpBasic().disable()
			.authorizeRequests()
			.antMatchers("/oauth/**").permitAll()
			.anyRequest().authenticated()
			.and().formLogin().loginPage("http://localhost:3002/#/login");
    }


}