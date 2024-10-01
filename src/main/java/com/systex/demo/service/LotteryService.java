package com.systex.demo.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.stereotype.Service;

@Service
public class LotteryService {
	public ArrayList<Set<Integer>> getNumbers(int groups, TreeSet<Integer> excludes) {
		ArrayList<Set<Integer>> lottery = new ArrayList();
		for(int i=0;i<groups;i++) {
			Set<Integer> result = new TreeSet<>();
	        while(result.size()<6){
	            int num = (int)(Math.random()*49)+1;
	            if(!excludes.contains(num)){
	                result.add(num);
	            }
	        }
	        lottery.add(result);
		}
        return lottery;
	}

}
