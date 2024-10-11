package com.systex.demo.filter;

import com.systex.demo.model.Account;
import com.systex.demo.service.LoginService;
import com.systex.demo.service.RegisterService;
import jakarta.annotation.Resource;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.DigestUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.URLEncoder;

@Component
@Order(2)
public class Filter2 extends HttpFilter{
    @Resource
    RegisterService registerService;
    @Resource
    LoginService loginService;
    /**
     * @see HttpFilter#HttpFilter()
     */
    public Filter2() {
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
        HttpSession session = httpRequest.getSession();
        //String user = (String)httpRequest.getSession().getAttribute("user");
        String username = httpRequest.getParameter("accNum");
        String password = httpRequest.getParameter("password");
        //register
        if(httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/register")) {
            if(username.contains(" ") || password.contains(" ")) {
                session.setAttribute("hint", "帳號或密碼不能有空格");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/registerForm");
                return;
            }
            if(username.length()<3 || username.length()>10 ||
               password.length()<3 || password.length()>10) {
                session.setAttribute("hint", "帳號或密碼長度至少為3碼，最多10碼");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/registerForm");
                return;
            }
            if(!username.matches("^[a-zA-Z0-9]+$") || !password.matches("^[a-zA-Z0-9]+$")) {
                session.setAttribute("hint", "帳號或密碼只允許字母、數字");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/registerForm");
                return;
            }
            if(registerService.hasAccountName(username)){
                session.setAttribute("hint", "此帳號名稱已註冊過");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/registerForm");
                return;
            }
            chain.doFilter(request, response); //把控制權還給container
            return;
        }
        //login
        if(httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/login")) {
            if(username.contains(" ") || password.contains(" ")) {
                session.setAttribute("hint", "帳號或密碼不能有空格");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/loginForm?");
                return;
            }
            Account account = loginService.checkAccountName(username);
            if(account==null){
                session.setAttribute("hint", "此帳號沒有註冊過");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/loginForm");
                return;
            }
            if(!account.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                session.setAttribute("hint", "密碼錯誤");
                httpResponse.sendRedirect(httpRequest.getContextPath() + "/loginForm");
                return;
            }
            chain.doFilter(request, response); //把控制權還給container
            System.out.println("ajax pass");
            return;
        }
        //ajaxlogin
        if(httpRequest.getRequestURI().equals(httpRequest.getContextPath()+"/ajaxlogin")) {
            System.out.println("ajax");

            if(username.contains(" ") || password.contains(" ")) {
                session.setAttribute("hint", "帳號或密碼不能有空格");//400
                httpResponse.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }

            Account account = loginService.checkAccountName(username);
            if(account==null){
                session.setAttribute("hint", "此帳號沒有註冊過");
                httpResponse.setStatus(HttpServletResponse.SC_NOT_FOUND);//404
                return;
            }
            if(!account.getPassword().equals(DigestUtils.md5DigestAsHex(password.getBytes()))){
                session.setAttribute("hint", "密碼錯誤");
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);//401
                return;
            }
            chain.doFilter(request, response); //把控制權還給container

            return;
        }

        System.out.println("pass");
        chain.doFilter(request, response); //把控制權還給container
        return;
        //System.out.println("redirect");
        //httpResponse.sendRedirect(httpRequest.getContextPath() + "/loginForm");
        // pass the request along the filter chain

    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }
}
