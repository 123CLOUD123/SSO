package com.verymro.sso.rest;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.verymro.sso.service.TestService;

import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/rest/test")
@Slf4j
public class TestRest {
	
//	@Resource
//	private TestService ts;
	
	private static final String OAUTH2_CLIENT_ID = "oauth2-client-id";
	
//	@Resource
//	private CacheManager cacheManager;
	
//	@RequestMapping(value = "/connect/authorize")
//    public String connect(@RequestParam(value = "response_type") String strResponseType, 
//    						@RequestParam(value = "client_id") String strClientId, 
//    						@RequestParam(value = "redirect_uri") String strRedirectUri, 
//    						@RequestParam(value = "scope") String strScope, 
//    						HttpSession session) {
//        log.error("response_type={},client_id={},redirect_uri={}, scope={}", strResponseType, strClientId, strRedirectUri, strScope);
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("response_type", strResponseType);
//        jsonObject.put("client_id", strClientId);
//        jsonObject.put("redirect_uri", strRedirectUri);
//        jsonObject.put("scope", strScope);
//        cacheManager.getCache(OAUTH2_CLIENT_ID).put(strClientId, jsonObject);
//        session.setAttribute("client_id", strClientId);
//        return "login";
//    }
//	
//	@RequestMapping(value = "/authorize")
//    public String authorize(@RequestParam(value = "username") String strUsername, @RequestParam(value = "password") String strPassword, HttpSession session){
//        log.error("username={},password={}", strUsername, strPassword);
//        String strClientId = session.getAttribute("client_id").toString();
//        Object obj = cacheManager.getCache(OAUTH2_CLIENT_ID).get(strClientId).get();
//        JSONObject jsonObject = JSON.parseObject(obj.toString());
//        log.error(jsonObject.toString());
//        String strRedirectUri = jsonObject.get("redirect_uri").toString()+"?code=SplxlOBeZQQYbYS6WxSbIA&state=xyz";
//        return "redirect:"+strRedirectUri;
//	}
//	
//	@RequestMapping(value = "/access_token")
//    public void accessToken(@RequestParam(value = "grant_type") String strGrantType, 
//    						@RequestParam(value = "client_id") String strClientId, 
//    						@RequestParam(value = "redirect_uri") String strRedirectUri, 
//    						@RequestParam(value = "code") String strCode, 
//    						HttpServletResponse response) {
//        log.error("grant_type={},client_id={},redirect_uri={}, code={}", strGrantType, strClientId, strRedirectUri, strCode);
//        Object obj = cacheManager.getCache(OAUTH2_CLIENT_ID).get(strClientId).get();
//        JSONObject jsonObject = JSON.parseObject(obj.toString());
//        log.error(jsonObject.toString());
//        String strOldRedirectUri = jsonObject.get("redirect_uri").toString()+"?code=SplxlOBeZQQYbYS6WxSbIA&state=xyz";
//        String retURL = null;
//        if(strOldRedirectUri.equals(strRedirectUri)){
//            JSONObject retJsonObject = new JSONObject();
//            retJsonObject.put("access_token", "2YotnFZFEjr1zCsicMWpAA");
//            retJsonObject.put("token_type", "bearer");
//            retJsonObject.put("expires_in", 7200);
//            retJsonObject.put("refresh_token", "tGzv3JOkF0XG5Qx2TlKWIA");
//            retJsonObject.put("scope", jsonObject.get("scope"));
//            response.setStatus(200);
//            response.setDateHeader("expries", -1);
//            response.setHeader("Cache-Control", "no-cache");
//            response.setHeader("Pragma", "no-cache");
//            response.setContentType("application/json");
//            try {
//                response.getWriter().write(retJsonObject.toJSONString());
//            } catch (IOException e) {
//                response.setStatus(500);
//                e.printStackTrace();
//            }
//        }
//        response.setStatus(400);
//	}
	
	@GetMapping("/test")
	public String test() {
		return "hello world 123 123 eeeeeeeee";
//		return ts.test();
	}
	
}
