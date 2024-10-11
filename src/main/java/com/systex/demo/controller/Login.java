package com.systex.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.systex.demo.model.Account;
import com.systex.demo.model.AccountRepository;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class Login {
	
	@Resource
	AccountRepository accountRepository;
	
	@GetMapping("/loginForm")
	public String directToLogin(Model model) {
		System.out.println("loginForm");
		model.addAttribute("title", "登入");
		model.addAttribute("api", "login");
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute Account account, BindingResult br ,Model model, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", account.getAccNum());
		return "main";
	}
	@PostMapping("/ajaxlogin")
	public ResponseEntity<String> ajaxlogin(@RequestParam String accNum, @RequestParam String password, HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.setAttribute("user", accNum);
		return ResponseEntity.ok("Data received");
	}

	@GetMapping("/logout")
	public String logout(HttpServletRequest request){
		HttpSession session = request.getSession();
		session.invalidate();
		return "login";
	}
}
