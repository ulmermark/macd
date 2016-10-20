package com.saluki.algotrading.strategy.macd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saluki.algotrading.core.message.NewOrderSingle;
import com.saluki.algotrading.marketdata.MarketDataResource;
import com.saluki.algotrading.strategy.core.Strategy;
import com.saluki.algotrading.strategy.core.StrategyServiceGroup;
import com.saluki.concurrency.inbox.DedicatedInboxFactory;
import com.saluki.concurrency.inbox.Inbox;

public class MacdStrategy implements Strategy{

    private static final Logger log = LoggerFactory.getLogger(MacdStrategy.class);

	private final Inbox serviceInbox;
	private final MarketDataResource marketData;
	private final NewOrderSingle order;
	
	public MacdStrategy(final Inbox serviceInbox, final MarketDataResource marketData, final NewOrderSingle order) throws Exception {
		this.serviceInbox = serviceInbox;
		this.marketData = marketData;
		this.order = order;
	}

	@Override
	public void start() {
		log.info("Start [{}]", this);
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MacdStrategy [order=");
		builder.append(order);
		builder.append("]");
		return builder.toString();
	}
	
	
}
