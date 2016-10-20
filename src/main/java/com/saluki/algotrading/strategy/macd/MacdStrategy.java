package com.saluki.algotrading.strategy.macd;

import com.saluki.algotrading.core.message.NewOrderSingle;
import com.saluki.algotrading.core.strategy.Strategy;
import com.saluki.algotrading.marketdata.MarketDataResource;
import com.saluki.concurrency.inbox.DedicatedInboxFactory;
import com.saluki.concurrency.inbox.Inbox;

public class MacdStrategy implements Strategy{

	private final Inbox serviceInbox;
	private final Inbox internalInbox;
	private final MarketDataResource marketData;
	private final NewOrderSingle order;
	
	public MacdStrategy(Inbox serviceInbox, MarketDataResource marketData, NewOrderSingle order) throws Exception {
		this.serviceInbox = serviceInbox;
		this.marketData = marketData;
		this.order = order;
		this.internalInbox = new DedicatedInboxFactory().createInbox(getInboxName());
	}

	@Override
	public void start() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		
	}
	
	private String getInboxName() {
		return String.format("%s-%s-%s-%s", this.order.getStrategy(), this.order.getSymbol(), this.order.getSid(), this.order.getTid());
	}


}
