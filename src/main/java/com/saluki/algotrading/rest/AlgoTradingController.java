package com.saluki.algotrading.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;
import com.saluki.algotrading.core.message.NewOrderSingle;
import com.saluki.algotrading.strategy.core.StrategyService;
import com.saluki.algotrading.strategy.core.StrategyServiceGroup;
@RestController
@RequestMapping("/trading/")
public class AlgoTradingController{

        private static final Logger log = LoggerFactory.getLogger(AlgoTradingController.class);
        
        private final StrategyServiceGroup service;
        /**
         * Constructor
         * @param service
         */
        @Autowired
        public AlgoTradingController(final StrategyServiceGroup service)
        {
                Preconditions.checkNotNull(service, "Supplied service can not be null.");
                this.service = service;
        }

        @RequestMapping(value="/strategies/{strategy}/symbols/{symbol}", method=RequestMethod.POST)
        public String watch(@PathVariable("strategy") String strategy, @PathVariable("symbol") String symbol) throws Exception
        {
        	Preconditions.checkArgument(strategy != null, "Strategy must be specified");
        	Preconditions.checkArgument(symbol != null, "Symbol must be specified");
	        try {
	        	NewOrderSingle nos = new NewOrderSingle(symbol.toUpperCase(), strategy.toUpperCase());
	        	return this.service.process( nos);
	        } catch (Exception ex) {
	                log.error("{}", ex);
	                throw ex;
	        }
        }
}