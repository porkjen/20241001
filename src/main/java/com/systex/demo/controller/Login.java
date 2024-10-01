package com.systex.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
		model.addAttribute("title", "登入");
		model.addAttribute("api", "login");
		return "login";
	}
	
	@GetMapping("/registerForm")
	public String directToRegister(Model model) {
		model.addAttribute("title", "註冊");
		model.addAttribute("api", "register");
		return "login";
	}

	@PostMapping("/register")
	public String register(@Valid @ModelAttribute Account account, BindingResult br ,Model model) {
		
		if(br.hasErrors()) {	
			model.addAttribute("title", "註冊");
			model.addAttribute("api", "register");
			model.addAttribute("hint", "註冊失敗");
			return "login";
		}
		if(accountRepository.findByAccNum(account.getAccNum())!=null){
			model.addAttribute("title", "註冊");
			model.addAttribute("api", "register");
			model.addAttribute("hint", "此帳號名稱已註冊過");
			return "login";
		}
		System.out.println("register");
		this.accountRepository.save(account);
		model.addAttribute("title", "登入");
		model.addAttribute("hint", "註冊成功請重新登入以使用功能");
		model.addAttribute("api", "login");
		return "login";
		
	}
	
	@PostMapping("/login")
	public String login(@Valid @ModelAttribute Account account, BindingResult br ,Model model, HttpServletRequest request) {
		Account acc = accountRepository.findByAccNum(account.getAccNum());
		System.out.println("login");
		if(acc == null) {
			
			model.addAttribute("title", "登入");
			model.addAttribute("hint", "此帳號沒有註冊過");
			model.addAttribute("api", "login");
			return "login";
		}
		else if(!acc.getPassword().equals(account.getPassword())) {
			//System.out.println("login2");
			model.addAttribute("title", "登入");
			model.addAttribute("hint", "密碼錯誤");
			model.addAttribute("api", "login");
			return "login";
		}
		else {
			//System.out.println("login3");
			HttpSession session = request.getSession();
			session.setAttribute("user", account.getAccNum());
			return "main";
		}
	}
}
