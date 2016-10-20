package com.saluki.algotrading.strategy.core;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saluki.algotrading.core.message.NewOrderSingle;
import com.saluki.algotrading.marketdata.MarketDataResource;
import com.saluki.concurrency.inbox.DedicatedInboxFactory;
import com.saluki.concurrency.inbox.InboxFactory;

@Service
public class StrategyServiceGroup {

    private static final Logger log = LoggerFactory.getLogger(StrategyServiceGroup.class);

	private final StrategyService[] services;
	private final InboxFactory inboxFactory;
	
	@Autowired
	public StrategyServiceGroup( final MarketDataResource marketData, final StrategyServiceConfig config, final InboxFactory inboxFactory){
		
		services = new StrategyService[config.getServiceProps().getNumServices()];
		this.inboxFactory = inboxFactory;
		
		for ( int inx=0; inx < services.length; inx++){
			String serviceName = String.format("%s-%s", config.getServiceProps().getName(), inx);
			services[inx] = new StrategyService(serviceName, marketData, config, inboxFactory);
		}
	}
	
	public void start() {
		log.info("start() called");
		Arrays.asList(this.services).forEach( service->{
			try {
				service.start();
			} catch (Exception e) {
				log.error("Failure to start instance:[{}] name:[{}]", service.getClass(), service.getName());
			}
		});
	}

	public String process(NewOrderSingle nos) throws Exception {
		int index = nos.getSymbol().hashCode() % this.services.length;
		return this.services[index].process(nos);
	}

}
