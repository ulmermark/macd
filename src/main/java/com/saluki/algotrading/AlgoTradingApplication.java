package com.saluki.algotrading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

import com.saluki.algotrading.marketdata.MarketDataResource;
import com.saluki.algotrading.strategy.core.StrategyServiceGroup;

@SpringBootApplication
@EnableScheduling
public class AlgoTradingApplication {

	public static void main(String[] args) {
		SpringApplication.run(AlgoTradingApplication.class, args);
	}

    @Component
    public static class OnAppStartup implements CommandLineRunner {
        private final StrategyServiceGroup service;
        private final MarketDataResource marketData;
        
        @Autowired
        public OnAppStartup( StrategyServiceGroup service, MarketDataResource marketData)
        {
        	this.marketData = marketData;
            this.service = service;
        }
        public void run(String... args) throws Exception {
        	this.marketData.start();
            this.service.start();
        }

    }

}
