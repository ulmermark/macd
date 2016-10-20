package com.saluki.algotrading.marketdata;

import java.time.Period;

import com.saluki.concurrency.inbox.Target;

public class SubscriptionKey {
	
	private final TKey key;
	private final Target<Tick> target;
	private final Period period;
	
	public SubscriptionKey(TKey key, Target<Tick> target, Period period) {
		this.key = key;
		this.target = target;
		this.period = period;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((period == null) ? 0 : period.hashCode());
		result = prime * result + ((target == null) ? 0 : target.hashCode());
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
		SubscriptionKey other = (SubscriptionKey) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (period == null) {
			if (other.period != null)
				return false;
		} else if (!period.equals(other.period))
			return false;
		if (target == null) {
			if (other.target != null)
				return false;
		} else if (!target.equals(other.target))
			return false;
		return true;
	}
	
	

}
