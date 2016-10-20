package com.saluki.algotrading.strategy.core;

import com.saluki.algotrading.core.message.NewOrderSingle;

public class StrategyKey {

	
    private final String symbol;
    private final String sid;
    private final String tid;

    public StrategyKey(final NewOrderSingle nos)
    {
        this.symbol = nos.getSymbol();
        this.tid = nos.getTid();
        this.sid = nos.getSid();
    }

	public String getSymbol() {
		return symbol;
	}

	public String getSid() {
		return sid;
	}

	public String getTid() {
		return tid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
		result = prime * result + ((symbol == null) ? 0 : symbol.hashCode());
		result = prime * result + ((tid == null) ? 0 : tid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		StrategyKey other = (StrategyKey) obj;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		if (symbol == null) {
			if (other.symbol != null)
				return false;
		} else if (!symbol.equals(other.symbol))
			return false;
		if (tid == null) {
			if (other.tid != null)
				return false;
		} else if (!tid.equals(other.tid))
			return false;
		return true;
	}
    
    
}
