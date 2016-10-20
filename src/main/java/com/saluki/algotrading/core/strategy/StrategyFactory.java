package com.saluki.algotrading.core.strategy;

import com.saluki.algotrading.core.message.NewOrderSingle;
import com.saluki.algotrading.marketdata.MarketDataResource;
import com.saluki.concurrency.inbox.Inbox;

public interface StrategyFactory {

	 public Strategy create(Inbox serviceInbox, MarketDataResource marketData, NewOrderSingle order) throws Exception;
}
