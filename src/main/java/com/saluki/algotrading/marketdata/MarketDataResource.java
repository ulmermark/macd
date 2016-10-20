package com.saluki.algotrading.marketdata;

import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;

import org.apache.commons.lang3.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saluki.concurrency.inbox.Target;

@Service
public class MarketDataResource {

	private final MarketDataResourceConfig config;

	@Autowired
	public MarketDataResource( final MarketDataResourceConfig config){
		this.config = config;
		
	}
	
	public void start(){
		
	}
	
    public Tick get(TKey key)
    {
        throw new NotImplementedException("Get(TKey key)");
    }

    public List<Tick> get(TKey key, LocalDateTime from, Period period)
    {
        throw new NotImplementedException("Get(TKey key, LocalDateTime from, TimePeriod period)");
    }

    public void get(TKey key, LocalDateTime from, Period period, Target<List<Tick>> target)
    {
        throw new NotImplementedException("Get(TKey key, LocalDateTime from, TimePeriod period, Target<IList<Tick>> target)");
    }

    public SubscriptionKey gubscribe(TKey key, Target<Tick> target, Period period)
    {
    	// TODO
    	return new SubscriptionKey(key, target, period);
    }
    public void unsubscribe(SubscriptionKey subscription)
    {
        if (subscription == null) return;
        throw new NotImplementedException("SubscriptionKey subscription");
    }
}
