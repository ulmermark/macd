package com.saluki.algotrading.core.message;

public class NewOrderSingle extends FixMessage{

    private final String symbol;
    private final String strategy;

    public NewOrderSingle(String symbol, String strategy) {
		this.symbol = symbol;
		this.strategy = strategy;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getStrategy() {
		return strategy;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("NewOrderSingle [symbol=");
		builder.append(symbol);
		builder.append(", strategy=");
		builder.append(strategy);
		builder.append("]");
		return builder.toString();
	}
    
    
}
