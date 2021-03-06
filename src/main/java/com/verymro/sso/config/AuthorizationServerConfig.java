package com.verymro.sso.config;

import java.security.KeyPair;
import java.util.function.Function;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.verymro.sso.service.impl.ClientServiceImpl;
import com.verymro.sso.service.impl.UserServiceImpl;

/**
 * 授权认证服务类
 * 
 * @author ZhangHao
 * @since 2020-08-25
 */
@Configuration
@Order(1)
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

	/**
	 * 权限管理器
	 */
//	@Autowired
//	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Autowired
	private ClientServiceImpl clientService;
	
	@Bean
	public NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }
	
	/**
	 * token 存储方式
	 */
	@Bean
	public TokenStore tokenStore() {
		return new InMemoryTokenStore();
    }
	
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
				.tokenStore(tokenStore())
				.allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST);
//		        .authenticationManager(authenticationManager()); // 密码模式，必须配置AuthenticationManager，不然不生效
//		        .userDetailsService(userService); // 密码模式，这里得配置UserDetailsService

		/*
		 * pathMapping用来配置端点URL链接，有两个参数，都将以 "/" 字符为开始的字符串
		 * 
		 * defaultPath：这个端点URL的默认链接
		 * 
		 * customPath：你要进行替代的URL链接
		 */
//		endpoints.pathMapping("/oauth/token", "/oauth/mytoken");

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
        	.allowFormAuthenticationForClients()	// 主要是让/oauth/token支持client_id以及client_secret作登录认证
        	.tokenKeyAccess("permitAll()")
        	.checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(clientService);
	}
	

}
