package com.verymro.sso.entity;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

import lombok.Data;

@Data
public class SysClient implements ClientDetails {
	
	private Long id;

	// 客户端id
	private String clientId;
	
	// 客户端密码
	private String clientSecret;
	
	// 失效时间
	private Integer accessTokenValiditySeconds;
	
	// 回调地址
	private String registeredRedirectUri;
	
	// 刷新token 失效时间
	private Integer refreshTokenValiditySeconds;
	
	private boolean isAutoApprove;
	
	@Override
	public String getClientId() {
		// TODO Auto-generated method stub
		return clientId;
	}

	@Override
	public Set<String> getResourceIds() {
		// TODO Auto-generated method stub
		return Collections.emptySet();
	}

	@Override
	public boolean isSecretRequired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String getClientSecret() {
		// TODO Auto-generated method stub
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public Set<String> getScope() {
		// TODO Auto-generated method stub
		return Set.of("app", "test", "user");
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		// TODO Auto-generated method stub
		return Set.of("authorization_code", "client_credentials", "password", "refresh_token", "implicit");
	}
	
	@Override
	public Set<String> getRegisteredRedirectUri() {
		// TODO Auto-generated method stub
		return Set.of(registeredRedirectUri);
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return Collections.emptySet();
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return accessTokenValiditySeconds;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		// TODO Auto-generated method stub
		return refreshTokenValiditySeconds;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		// TODO Auto-generated method stub
		return isAutoApprove;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		// TODO Auto-generated method stub
		return null;
	}

}
