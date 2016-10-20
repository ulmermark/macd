package com.saluki.algotrading.core.message;

public abstract class FixMessage {

    protected String sid;
    protected String tid;
    
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
    
    
}
