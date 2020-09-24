package com.verymro.sso.component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

/**
 * 自定义授权错误处理器
 * 
 * @author ZhangHao
 * @since 2020-09-24
 *
 */
@Component
public class JyAuthExceptionEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws ServletException {
//		Map<String, Object> map = new HashMap<String, Object>();
		Throwable cause = authException.getCause();

		response.setStatus(HttpStatus.OK.value());
		response.setHeader("Content-Type", "application/json;charset=UTF-8");
		try {
			if (cause instanceof InvalidTokenException) {
//				response.getWriter().write(ResultJsonUtil.build(ResponseCodeConstant.REQUEST_FAILED,
//						ResponseStatusCodeConstant.OAUTH_TOKEN_FAILURE, ResponseMessageConstant.OAUTH_TOKEN_ILLEGAL));
				response.getWriter().write("错误1");
			} else {
				/*
				 * response.getWriter().write(ResultJsonUtil.build(ResponseCodeConstant.
				 * REQUEST_FAILED, ResponseStatusCodeConstant.OAUTH_TOKEN_MISSING,
				 * ResponseMessageConstant.OAUTH_TOKEN_MISSING));
				 */
				response.getWriter().write("未授权");
			}
		} catch (IOException e) {
			e.printStackTrace();
			
		}
	}
}
