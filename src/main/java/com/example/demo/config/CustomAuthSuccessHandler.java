package com.example.demo.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class CustomAuthSuccessHandler implements AuthenticationSuccessHandler {

	private Set<String> set;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// TODO Auto-generated method stub
		set = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
		if(set.contains("ROLE_ADMIN")) {
			response.sendRedirect("/Admin");
		}else {
			response.sendRedirect("/User/");
		}
	}

}
