package com.saluki.algotrading.strategy.macd;

import com.saluki.algotrading.core.message.NewOrderSingle;
import com.saluki.algotrading.core.strategy.Strategy;
import com.saluki.algotrading.core.strategy.StrategyFactory;
import com.saluki.algotrading.marketdata.MarketDataResource;
import com.saluki.concurrency.inbox.Inbox;

public class MacdStrategyFactory implements StrategyFactory {

	@Override
	public Strategy create(Inbox serviceInbox, MarketDataResource marketData, NewOrderSingle order) throws Exception {
		
		return new MacdStrategy( serviceInbox, marketData, order);
	}

}
