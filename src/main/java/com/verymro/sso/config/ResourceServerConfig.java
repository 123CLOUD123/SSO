package com.verymro.sso.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.header.writers.frameoptions.WhiteListedAllowFromStrategy;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

import com.verymro.sso.component.JyAuthExceptionEntryPoint;
import com.verymro.sso.service.impl.UserServiceImpl;

@Configuration
@Order(2)
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private RedisConnectionFactory connectionFactory;
	
	@Autowired
    private UserServiceImpl userService;
	
//	@Autowired
//	private TokenStore tokenStore;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Autowired
	private JyAuthExceptionEntryPoint jyAuthExceptionEntryPoint;
	
	@Bean
	public OAuth2AccessDeniedHandler oAuth2AccessDeniedHandler() {
		return new OAuth2AccessDeniedHandler();
	};
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resource) throws Exception {
//		super.configure(resource);
//		resource.stateless(true);
		resource.tokenStore(tokenStore);
		resource.authenticationEntryPoint(jyAuthExceptionEntryPoint);
		resource.accessDeniedHandler(oAuth2AccessDeniedHandler());	
//		AccessDeniedHandler ad = resource.getAccessDeniedHandler();
//		int a = 2;
	}
	
	
    @Override
	public void configure(HttpSecurity http) throws Exception {
    	
    	http.headers().frameOptions().disable();
    	
    	http
    	.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
		.and()
    	.csrf().disable()
    	.cors().disable()
//    	.headers().addHeaderWriter(new XFrameOptionsHeaderWriter(
//                new WhiteListedAllowFromStrategy(
//                        Arrays.asList("http://localhost:3002", "http://localhost",
//                                "http://localhost:3002/#/login"))))
//    	.and()
    	.httpBasic().disable()
    	.authorizeRequests()
    	.antMatchers("/rest/test", "/rest/test2").authenticated()
    	.antMatchers("/error2", "/", "/api/login", "/oauth/**", "/login").permitAll()
    	.anyRequest().authenticated()
    	.and().formLogin().loginPage("/login");
//    	.and().formLogin().defaultSuccessUrl("http://localhost:3002/").failureUrl("http://localhost:3002/");
    	
    }
    
}