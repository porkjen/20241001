package com.systex.demo.filter;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebFilter("/*") // 設定過濾所有 URL
@Component
public class WFilter extends HttpFilter {
	
	/**
     * @see HttpFilter#HttpFilter()
     */
    public WFilter() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
    	
    	HttpServletRequest httpRequest = (HttpServletRequest)request;
    	HttpServletResponse httpResponse = (HttpServletResponse)response;
    	String user = (String)httpRequest.getSession().getAttribute("user");
    	
    	request.setCharacterEncoding("UTF-8");
    	
        if(httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/index.jsp") ||
           httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/loginForm")||
           httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/registerForm")||
           httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/login")||
           httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/register")||
           httpRequest.getRequestURI().contains("/h2-console")) {
        	System.out.println("pass");
        	chain.doFilter(request, response); //把控制權還給container
        	return;
        }
        if(user!=null) {
        	System.out.println("pass");
        	chain.doFilter(request, response); //把控制權還給container
        	return;
        }
        System.out.println("redirect");
        httpResponse.sendRedirect(httpRequest.getContextPath() + "/loginForm");
        return;
        // pass the request along the filter chain
        
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}
