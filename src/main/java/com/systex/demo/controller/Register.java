package com.systex.demo.controller;

import com.systex.demo.model.Account;
import com.systex.demo.model.AccountRepository;
import com.systex.demo.service.RegisterService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Register {

    @Resource
    RegisterService registerService;
    @GetMapping("/registerForm")
    public String directToRegister(Model model) {
        System.out.println("registerForm");
        return "register";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Account account, Model model) {
        System.out.println("register");
        registerService.successRegis(account);
        model.addAttribute("hint", "註冊成功請重新登入以使用功能");
        return "login";

    }
}
