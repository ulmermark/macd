package com.saluki.algotrading.marketdata;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
@EnableConfigurationProperties( MarketDataResourceConfig.MarketDataResourceProperties.class)
public class MarketDataResourceConfig {

    private final MarketDataResourceProperties resourceProps;
    
    @Autowired
    public MarketDataResourceConfig( MarketDataResourceProperties resourceProps)
    {
            this.resourceProps = resourceProps;
    }
    
     @Bean
     public TaskScheduler taskScheduler() {
         return new ThreadPoolTaskScheduler();
     }
     
    public MarketDataResourceProperties getProperties() {
            return resourceProps;
    }
    
    @ConfigurationProperties(prefix="market-data")
    public static class MarketDataResourceProperties {
        public MarketDataResourceProperties()
        {
        }

    }
}        
        

