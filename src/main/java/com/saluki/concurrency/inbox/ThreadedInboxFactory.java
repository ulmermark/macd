package com.saluki.concurrency.inbox;

/**
 * Inbox Factory implementation for creating ThreadedInbox instances
 * 
 * @author ulmermark
 *
 */
public class ThreadedInboxFactory  implements InboxFactory{

	private static final int DEFAULT_NUM_THREADS = 2;
	
	private final int numThreads;
	
	public ThreadedInboxFactory()
	{
		numThreads = DEFAULT_NUM_THREADS;
	}
	
	public ThreadedInboxFactory(int numThreads)
	{
		this.numThreads = numThreads;
	}
	
    public Inbox createInbox(String name) throws Exception {
        Inbox inbox = new ThreadedInbox(name, numThreads);
        InboxManager.getInstance().addInbox(name,inbox);
        return inbox;
    }}
