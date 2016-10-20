package com.saluki.concurrency.inbox;

/**
 * Callback mechanism that delivery a payload to its inbox for processing at a later time
 * based on Inbox implementation
 * 
 * @author ulmermark
 *
 * @param <T> - the payload to process
 */
public abstract class Target <T>  
{
    private final Inbox inbox;
    public Inbox getInbox() throws Exception {
        return inbox;
    }

    public Target(Inbox inbox) throws Exception {
        this.inbox = inbox;
    }

    public abstract void onReceived(T payload);

    public void deliver(T payload) throws Exception {
        this.inbox.deliver(this, payload);
    }

}
