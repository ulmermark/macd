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
import com.saluki.algotrading.strategy.service.StrategyService;
@RestController
@RequestMapping("/trading/")
public class AlgoTradingController{

        private static final Logger log = LoggerFactory.getLogger(AlgoTradingController.class);
        
        private final StrategyService service;
        /**
         * Constructor
         * @param service
         */
        @Autowired
        public AlgoTradingController(final StrategyService service)
        {
                Preconditions.checkNotNull(service, "Supplied service can not be null.");
                this.service = service;
        }

        @RequestMapping(value="/strategies/{strategy}/symbols/{symbol}", method=RequestMethod.POST)
        public String watch(@PathVariable("strategy") String strategy, @PathVariable("symbol") String symbol) throws Exception
        {
	        try {
	        	NewOrderSingle nos = new NewOrderSingle(symbol, strategy);
	        	return this.service.watch( nos);
	        } catch (Exception ex) {
	                log.error("{}", ex);
	                throw ex;
	        }
        }
}