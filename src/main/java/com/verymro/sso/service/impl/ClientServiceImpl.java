package com.verymro.sso.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.verymro.sso.entity.SysClient;
import com.verymro.sso.entity.exception.JyException;
import com.verymro.sso.mapper.SysClientMapper;

@Service
public class ClientServiceImpl implements ClientDetailsService {

	@Autowired
	private SysClientMapper clientMapper;
	
	@Override
	public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
		LambdaQueryWrapper<SysClient> qry = Wrappers.lambdaQuery();
		qry.eq(SysClient::getClientId, clientId);
		SysClient client = clientMapper.selectOne(qry);
		if (null == client) {
			throw new JyException("无客户信息");
		}
		
		return client;
	}
	
	
}