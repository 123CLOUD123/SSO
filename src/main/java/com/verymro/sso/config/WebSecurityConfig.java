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
@Order(1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
//    @Bean
//    public AuthenticationManager authenticationManagerBean() throws Exception {
//        return super.authenticationManagerBean();
//    }
    @Autowired
    private UserServiceImpl userService;
	
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
	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth)
//			throws Exception {
//		auth.inMemoryAuthentication().withUser("peterwanghao")
//				.password(passwordEncoder().encode("123456")).roles("USER");
//	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception{
		
//		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//	        .and()
//		    .requestMatchers().anyRequest()
//		    .and()
//		    .authorizeRequests().antMatchers("/oauth/**", "/oauth/**/**").permitAll()
//		    .and()
//		    .anonymous()
//		    .and()
//		    .authorizeRequests()
//		    .antMatchers("/rest/test/**").authenticated();//配置访问控制，必须认证过后才可以访问
//		
		
		super.configure(http);
//		http.formLogin()
//			.and()
////			.authorizeRequests().antMatchers("/", "/rest/test/test").permitAll()
////			.and()
//			.authorizeRequests().anyRequest().authenticated()
//			.and()
//	        .csrf() //防止CSRF（跨站请求伪造）配置
//	        .requireCsrfProtectionMatcher(new AntPathRequestMatcher("/oauth/authorize")).disable();
		
//		http.authorizeRequests()
//	        .antMatchers( "/oauth/**","/swagger-ui.html")
//	        .permitAll()
//	        .and().authorizeRequests().and().csrf().disable();
		
//		http.antMatcher("/**")
//		.requestMatchers()
//		.antMatchers("/oauth/authorize**", "/login**", "/error**")
//		.and()
//		.authorizeRequests().anyRequest().authenticated()
//		.and()
//		.formLogin().permitAll();
    }


}