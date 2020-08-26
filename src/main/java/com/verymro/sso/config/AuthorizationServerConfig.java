package com.verymro.sso.config;

import java.security.KeyPair;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.builders.InMemoryClientDetailsServiceBuilder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.security.oauth2.provider.token.store.KeyStoreKeyFactory;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import com.baomidou.mybatisplus.core.toolkit.ArrayUtils;
import com.verymro.sso.service.impl.TestServiceImpl;

/**
 * 授权认证服务类
 * 
 * @author ZhangHao
 * @since 2020-08-25
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

//	@Autowired
//    @Qualifier("dataSource")
//    private DataSource dataSource;
	
	/**
	 * 权限管理器
	 */
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private TestServiceImpl ts;
	
	@Autowired
	private RedisConnectionFactory connectionFactory;
	
//	@Autowired
//	private PasswordEncoder passwordEncoder;
	
//	private OAuth2Property oauth2 = new OAuth2Property();
//	private OAuth2ClientProperty[] clients = new OAuth2ClientProperty[];
	
	@Bean
	public TokenStore tokenStore() {
        return new RedisTokenStore(connectionFactory);
    }

//	@Override
//	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints.authenticationManager(authenticationManager).tokenStore(tokenStore());
//	}
	
	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//		endpoints
//        .authenticationManager(authenticationManager)
//        .userDetailsService(ts);
		
//		endpoints.authenticationManager(authenticationManager)
//        .userDetailsService(ts)		// XXX 待改
//        .allowedTokenEndpointRequestMethods(HttpMethod.GET, HttpMethod.POST)
//        .accessTokenConverter(jwtAccessTokenConverter())
//        .tokenStore(new JwtTokenStore(jwtAccessTokenConverter()));
		
		endpoints.tokenStore(tokenStore()) // 配置存储token的方式(默认InMemoryTokenStore)
				.allowedTokenEndpointRequestMethods(HttpMethod.GET,HttpMethod.POST)
		        .authenticationManager(authenticationManager) // 密码模式，必须配置AuthenticationManager，不然不生效
		        .userDetailsService(ts); // 密码模式，这里得配置UserDetailsService

		/*
		 * pathMapping用来配置端点URL链接，有两个参数，都将以 "/" 字符为开始的字符串
		 * 
		 * defaultPath：这个端点URL的默认链接
		 * 
		 * customPath：你要进行替代的URL链接
		 */
		endpoints.pathMapping("/oauth/token", "/oauth/mytoken");

	}

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//		security
//			.allowFormAuthenticationForClients()
//			.tokenKeyAccess("permitAll()")
//			.checkTokenAccess("isAuthenticated()");
		security
        	.allowFormAuthenticationForClients()
        	.tokenKeyAccess("permitAll()")
        	.checkTokenAccess("permitAll()");
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//		clients.inMemory().withClient("android").scopes("xx") // 此处的scopes是无用的，可以随意设置
//				.secret("android").authorizedGrantTypes("password", "authorization_code", "refresh_token").and()
//				.withClient("webapp").scopes("xx").authorizedGrantTypes("implicit");
		
//		clients.inMemory().withClient("clientapp")
//		.secret("123456")
//		.authorizedGrantTypes("password", "authorization_code",
//				"refresh_token")
//		.authorities("READ_ONLY_CLIENT").scopes("read_user_info")
//		.resourceIds("oauth2-resource")
//		.redirectUris("http://localhost:8086/login")
//		.accessTokenValiditySeconds(5000)
//		.refreshTokenValiditySeconds(50000);
		
//		InMemoryClientDetailsServiceBuilder builder = clients.inMemory();
//        OAuth2ClientProperty[] oauth2Clients = securityProperty.getOauth2().getClients();
//        if (ArrayUtils.isNotEmpty(oauth2Clients)) {
//            for (OAuth2ClientProperty config : oauth2Clients) {
//                builder // 使用in-memory存储
//                        .withClient(config.getClientId()).secret(config.getClientSecret())
//                        .accessTokenValiditySeconds(config.getAccessTokenValiditySeconds()) // 发出去的令牌有效时间(秒)
//                        .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token") // 该client允许的授权类型
//                        .scopes("all", "read", "write") // 允许的授权范围(如果是all，则请求中可以不要scope参数，否则必须加上scopes中配置的)
//                        .autoApprove(true); // 自动审核
//            }
//        }
		
		clients.inMemory() // 使用in-memory存储
        .withClient("clientId").secret("123456")	// client 信息
        .accessTokenValiditySeconds(100) // 发出去的令牌有效时间(秒)
        .authorizedGrantTypes("authorization_code", "client_credentials", "password", "refresh_token") // 该client允许的授权类型
        .scopes("all", "read", "write") // 允许的授权范围(如果是all，则请求中可以不要scope参数，否则必须加上scopes中配置的)
        .autoApprove(true); // 自动审核
	}
	
	
//	private JwtAccessTokenConverter jwtAccessTokenConverter() {
//        JwtAccessTokenConverter jwtAccessTokenConverter = new JwtAccessTokenConverter();
//        jwtAccessTokenConverter.setKeyPair(keyPair());
//        return jwtAccessTokenConverter;
//    }
//	
//	private KeyPair keyPair() {
//        return new KeyStoreKeyFactory(new ClassPathResource("xmall-auth.jks"), "123456".toCharArray()).getKeyPair("xmall-auth", "123456".toCharArray());
//    }

}
