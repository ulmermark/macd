package com.saluki.concurrency.inbox;

/**
 * Interface definition for Inboxes.
 * 
 * Represents a queuing strategy based on implementation
 * 
 * @author ulmermark
 *
 */
public interface Inbox   
{
    String getName() throws Exception ;

    boolean getBusy() throws Exception ;

    int getCount() throws Exception ;

    boolean getClosed() throws Exception ;

    boolean getDone() throws Exception ;

    <T> void deliver(Target<T> target, T payload) throws Exception;

    int depth() throws Exception ;

    void resetCount() throws Exception ;

    void shutdown() throws Exception ;

    void clear() throws Exception ;

    String detail() throws Exception ;

}


