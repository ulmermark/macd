package com.saluki.concurrency.inbox;

/**
 * Factory interface for creating various inbox implementations
 * 
 * @author ulmermark
 *
 */
public interface InboxFactory   
{
    public Inbox createInbox(String name) throws Exception ;

}


