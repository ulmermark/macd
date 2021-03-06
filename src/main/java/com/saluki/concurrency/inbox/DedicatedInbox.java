package com.saluki.concurrency.inbox;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Inbox that uses a single thread to process all events (Runnable)
 * @author ulmermark
 *
 */
public class DedicatedInbox extends AbstractInbox{

	private static final Logger log = LoggerFactory.getLogger(DedicatedInbox.class);

	private final ExecutorService executor;
	
	public DedicatedInbox(String name) {
		super(name);
		log.info("DedicatedInbox.ctor()");
		executor = Executors.newSingleThreadExecutor();
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
