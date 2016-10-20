package com.saluki.algotrading.strategy.core;

import org.apache.commons.lang3.NotImplementedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saluki.algotrading.core.message.NewOrderSingle;
import com.saluki.algotrading.marketdata.MarketDataResource;
import com.saluki.algotrading.strategy.macd.MacdStrategyFactory;
import com.saluki.concurrency.inbox.Inbox;
import com.saluki.concurrency.inbox.InboxFactory;

public class StrategyService {

    private static final Logger log = LoggerFactory.getLogger(StrategyService.class);

	private static final String MACD = "MACD";
	
	private final String name;
	private final MarketDataResource marketData;
	private final StrategyServiceConfig config;
	private final InboxFactory serviceInboxFactory;
	private Inbox serviceInbox;
	
	public StrategyService( String name, final MarketDataResource marketData, StrategyServiceConfig config, InboxFactory serviceInboxFactory){
		this.name = name;
		this.config = config;
		this.marketData = marketData;
		this.serviceInboxFactory = serviceInboxFactory;
	}
	
	public void start() throws Exception {
		log.info("start() called");
		this.serviceInbox = this.serviceInboxFactory.createInbox(name);
	}

	public String process(NewOrderSingle nos) throws Exception {
		StrategyFactory factory = getFactory( nos);
		Strategy strategy = factory.create(serviceInbox, marketData, nos);
		strategy.start();
		
		return "order accepted";
	}

	private String getStrategyName(NewOrderSingle nos) {
		return String.format("%s-%s-%s-%s", nos.getStrategy(), nos.getSymbol(), nos.getSid(), nos.getTid());
	}

	private StrategyFactory getFactory(final NewOrderSingle nos) {
		
		StrategyFactory factory = null;
		switch( nos.getStrategy()){
			case MACD:
				factory = new MacdStrategyFactory();
				break;
			default:
				throw new NotImplementedException(String.format("StrategyFactory for strategy [%s] not supported", nos.getStrategy()));
		}
		return factory;
	}

	public String getName() {
		return name;
	}

}
