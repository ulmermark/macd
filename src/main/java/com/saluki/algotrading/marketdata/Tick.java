package com.saluki.algotrading.marketdata;

import java.time.LocalDateTime;

public class Tick {
	
    private final LocalDateTime tickTime;
    private final double price;
    private final String symbol;

	public Tick(LocalDateTime tickTime, double price, String symbol) {
		this.tickTime = tickTime;
		this.price = price;
		this.symbol = symbol;
	}

	public LocalDateTime getTickTime() {
		return tickTime;
	}

	public double getPrice() {
		return price;
	}

	public String getSymbol() {
		return symbol;
	}
    
    

}
