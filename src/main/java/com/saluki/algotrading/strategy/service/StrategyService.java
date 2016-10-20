package com.saluki.algotrading.strategy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saluki.algotrading.core.message.NewOrderSingle;
import com.saluki.algotrading.core.strategy.Strategy;
import com.saluki.algotrading.core.strategy.StrategyFactory;
import com.saluki.algotrading.marketdata.MarketDataResource;
import com.saluki.concurrency.inbox.InboxFactory;

@Service
public class StrategyService {

	private final MarketDataResource marketData;
	private final StrategyServiceConfig config;
	private InboxFactory serviceInbox;
	
	@Autowired
	public StrategyService( final MarketDataResource marketData, StrategyServiceConfig config, InboxFactory serviceInbox){
		this.config = config;
		this.marketData = marketData;
		this.serviceInbox = serviceInbox;
	}
	
	public void start() {
	}

	public String process(NewOrderSingle nos) throws Exception {
		StrategyFactory factory = getFactory( nos);
		Strategy strategy = factory.create(serviceInbox.createInbox(this.config.getServiceProps().getName()), marketData, nos);
		strategy.start();
		
		return "order accepted";
	}

	private String getStrategyName(NewOrderSingle nos) {
		return String.format("%s-%s-%s-%s", nos.getStrategy(), nos.getSymbol(), nos.getSid(), nos.getTid());
	}

	private StrategyFactory getFactory(NewOrderSingle nos) {
		// TODO Auto-generated method stub
		return null;
	}

}
