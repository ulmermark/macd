package com.saluki.concurrency.inbox;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inbox implementation based on a number of threads to process the delivered payloads.
 * @author ulmermark
 *
 */
public class ThreadedInbox extends AbstractInbox{
	private static final Logger log = LoggerFactory.getLogger(ThreadedInbox.class);

	private final ExecutorService executor;
	
	public ThreadedInbox(String name, int numThreads) {
		super(name);
		log.info("ThreadedInbox.ctor()- name:[{}] numThreads:[{}]", name, numThreads);
		executor = Executors.newFixedThreadPool(numThreads);
	}

	@Override
	public <T> void deliver(Target<T> target, T payload){
		
		Runnable callback = () ->{
			try
			{
				target.onReceived(payload);
			}catch( Exception e)
			{
				throw new RuntimeException(e);
			}
		};
		
		this.executor.execute(callback);
	}

	@Override
	public int depth() throws Exception {
		return 0; // for now until we figure out
	}

	@Override
	public void clear() throws Exception {
	}

}
