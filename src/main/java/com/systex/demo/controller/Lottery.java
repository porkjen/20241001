package com.systex.demo.controller;

import java.util.LinkedList;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.systex.demo.service.LotteryService;

import jakarta.annotation.Resource;

@Controller
public class Lottery {
	@Resource
	LotteryService lotteryService;
	
	@GetMapping("/main")
	public String getLot() {
			return "main";
	}
	
	@PostMapping("/checkLottery")
	public String check(@RequestParam("group")String group, @RequestParam("exclude")String exclude, Model model) {
		LinkedList<String>errorMsgs = new LinkedList<>();
		int groupNum=0;
		TreeSet<Integer> excludeNum = new TreeSet<>();
		
		model.addAttribute("errors", errorMsgs);
		if(group == null || group.trim().isEmpty()) {
			errorMsgs.add("組數必須填寫");
		}
		
		if(exclude == null || exclude.trim().isEmpty()) {
			errorMsgs.add("排除欄位必須填寫");
		}
		
		try {
		   groupNum = Integer.parseInt(group.trim());
		   if(groupNum<1) errorMsgs.add("產生組數至少為1組");
		  } catch (NumberFormatException e) {
		    errorMsgs.add("組數欄位請填寫數字");
		}
		int excludeCount=0;
		for(String n : exclude.trim().split(" ")){
			try {
				if(excludeNum.add(Integer.parseInt(n)))
					excludeCount++;
			  } catch (NumberFormatException e) {
			    errorMsgs.add("請輸入正確排除數字");
			    break;
			}
        }
		System.out.println("count"+excludeCount);
		if(excludeCount>43)errorMsgs.add("最多加入43個不同數字");
		if(!errorMsgs.isEmpty()) {
			model.addAttribute("errors", errorMsgs);
			return "main";
		}
		
		model.addAttribute("results", lotteryService.getNumbers(groupNum, excludeNum));
		return "result";
	}
}
