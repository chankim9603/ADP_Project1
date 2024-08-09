package com.example.customer_management_service;

import java.io.IOException;
import java.util.Enumeration;

import org.hibernate.annotations.Comment;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Component
@Order(1)
public class AuthToken implements Filter{

    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// get authorization header
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		String uri = req.getRequestURI();
		String method = req.getMethod();
		
		if ((!uri.startsWith("/api/customers") || uri.startsWith("/api/customers/name/")) ||
    		(!method.equals("GET") && !method.equals("PUT") && !method.equals("DELETE"))) {
			chain.doFilter(request, response);
			return;			
		}else{
			

			String authheader = req.getHeader("security");
			if(authheader != null && authheader.length() > 7 ) {
                String jwt_token = authheader.substring(7, authheader.length());
                if(JWTHelper.verifyToken(jwt_token)) {
					chain.doFilter(request, response);
				    return;
					
				}
			}
		}		
		// continue
		res.sendError(HttpServletResponse.SC_FORBIDDEN, "failed authentication");

	}
	



}
