package com.saluki.concurrency.inbox;

/**
 * Abstract Inbox class..subclasses must implement deliver() and clear()
 * 
 * Base inbox implementation of shared functionality
 * 
 * @author ulmermark
 *
 */
public abstract class AbstractInbox   implements Inbox
{
    protected boolean busy = false;
    protected int count = 0;
    protected final String name;
    protected boolean closed = false;
    
    public String getName() throws Exception {
        return name;
    }

    public boolean getBusy() throws Exception {
        return busy;
    }

    public int getCount() throws Exception {
        return count;
    }

    public boolean getClosed() throws Exception {
        return closed;
    }

    public boolean getDone() throws Exception {
        return getClosed() && depth() == 0;
    }

    public AbstractInbox(String name){
        this.name = name;
    }

    public abstract <T> void deliver(Target<T> target, T payload) throws Exception;

    public abstract void clear() throws Exception ;

    public void resetCount() throws Exception {
        count = 0;
    }

    protected <T>void process(Target<T> target, T payload) throws Exception {
        busy = true;
        target.onReceived(payload);
        busy = false;
    }

    public void close() throws Exception {
        closed = true;
    }

    public void shutdown() throws Exception {
        close();
        while (!getDone())
            ;
    }

    /**
    * Override for more detail
    * 
    *  @return
    */
    public String detail() throws Exception {
        return this.toString();
    }

}


