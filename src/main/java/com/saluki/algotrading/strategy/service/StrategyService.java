package com.saluki.algotrading.strategy.service;

import org.springframework.stereotype.Service;

import com.saluki.algotrading.core.message.NewOrderSingle;

@Service
public class StrategyService {

	public void start() {
	}

	public String watch(NewOrderSingle nos) {
		return "success";
	}

}
