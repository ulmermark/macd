package com.saluki.algotrading.strategy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableConfigurationProperties( StrategyServiceConfig.StrategyServiceProperties.class)
public class StrategyServiceConfig {

    private final StrategyServiceProperties serviceProps;
    
    @Autowired
    public StrategyServiceConfig( StrategyServiceProperties serviceProps)
    {
            this.serviceProps = serviceProps;
    }
    
     @Bean
     public TaskScheduler taskScheduler() {
         return new ThreadPoolTaskScheduler();
     }
     
    public StrategyServiceProperties getServiceProps() {
            return serviceProps;
    }
    
    @ConfigurationProperties(prefix="strategy-svc")
    public static class StrategyServiceProperties {
    	
    	private String name;
    	
        public StrategyServiceProperties()
        {
        }

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

    }
}        
        

