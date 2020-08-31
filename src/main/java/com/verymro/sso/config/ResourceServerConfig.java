package com.verymro.sso.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Autowired
	private RedisConnectionFactory connectionFactory;
	
	@Autowired
	private TokenStore tokenStore;
	
	@Override
	public void configure(ResourceServerSecurityConfigurer resource) {
		resource.stateless(true);
		resource.tokenStore(tokenStore);
	}
	
	
    @Override
    public void configure(HttpSecurity http) throws Exception {
//    	super.configure(http);
//    		http.authorizeRequests()
//    			.antMatchers("/").permitAll()
//    			.antMatchers("/rest/**", "/rest/test/test").authenticated()
//    			.and().authorizeRequests().and().csrf().disable();
    		
    		
    		
//    		http
//        	// Since we want the protected resources to be accessible in the UI as well we need 
//			// session creation to be allowed (it's disabled by default in 2.0.6)
//        	//另外，如果不设置，那么在通过浏览器访问被保护的任何资源时，每次是不同的SessionID，并且将每次请求的历史都记录在OAuth2Authentication的details的中
//			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//			.and()
//       		.requestMatchers()
//           .antMatchers("/user","/res/**")
//           .and()
//           .authorizeRequests()
//           .antMatchers("/user","/res/**")
//           .authenticated();
    		
    	super.configure(http);
    	
//    		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
//	        .and()
//		    .requestMatchers().anyRequest()
//		    .and()
//		    .authorizeRequests().antMatchers("/oauth/**", "/oauth/**/**", "/rest/test/test").permitAll()
//		    .and()
//		    .anonymous()
//		    .and()
//		    .authorizeRequests()
//		    .antMatchers("/rest/test/**").authenticated();//配置访问控制，必须认证过后才可以访问

    		
    		
//    		http.authorizeRequests()
//            .antMatchers("/user/operate/**").authenticated()
//            .antMatchers( "/oauth/**","/swagger-ui.html").permitAll()
//            .and().authorizeRequests().and().csrf().disable();
    	
//    	http
//        .csrf().disable()
//        .exceptionHandling()
//        .authenticationEntryPoint((request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
//       .and()
//        .authorizeRequests()
//        .anyRequest().authenticated()
//       .and()
//        .httpBasic();
    }
    
}