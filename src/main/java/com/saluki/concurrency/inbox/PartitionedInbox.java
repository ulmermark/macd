package com.saluki.concurrency.inbox;

import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

import com.google.common.base.Preconditions;

/**
 * Inbox implementation that insures thread affinity when processing payloads by delivering each payload
 * to the same Partition index based on the {partitionExpression}.hashCode() % partitionCount
 * 
 * @author ulmermark
 *
 */
public class PartitionedInbox extends AbstractInbox{

	private static final Logger log = LoggerFactory.getLogger(PartitionedInbox.class);

	private final int numPartitions;
	private final String partitionExpression;
	private final Partition[] partitions;
	private final ExpressionParser parser;
	private final Expression expression;
	
	public PartitionedInbox(String name, int numPartitions, String partitionExpression) {
		super(name);
		log.info("PartitionedInbox.ctor()- name:[{}] numPartitions:[{}] partitionExpression:[{}]", name, numPartitions, partitionExpression);
    	Preconditions.checkArgument(numPartitions > 0, "numPartitions must be greater than 0");
    	Preconditions.checkArgument(partitionExpression != null, "partitionExpression must be not null and not empty");
		this.numPartitions = numPartitions;
		this.partitionExpression = partitionExpression;
		this.partitions = new Partition[this.numPartitions];
		this.parser = new SpelExpressionParser();
		this.expression = this.parser.parseExpression(this.partitionExpression);
		initialize();
	}

	/**
	 * Initialize the instance by instantiating each partition
	 */
	private void initialize() {
		for ( int inx=0; inx < this.numPartitions; inx++){
			Partition partition = new Partition( Executors.newSingleThreadExecutor());
			this.partitions[inx] = partition;
		}
	}

	/**
	 * Deliver the payload to the target, insuring partition affinity ( hence thread affinity)
	 */
	@Override
	public <T> void deliver(Target<T> target, T payload){
		Object obj = expression.getValue(payload);
		int index = obj.hashCode() % this.numPartitions;
		if (log.isDebugEnabled()) log.debug("Delivering [{}] to [{}] hash:[{}] index:[{}]", payload, target,obj.hashCode(), index);
		this.partitions[index].deliver(target, payload);
	}

	@Override
	public int depth() throws Exception {
		return 0; // for now until we figure out
	}

	@Override
	public void clear() throws Exception {
		
		Arrays.asList(partitions).forEach(partition->{
			partition.clear();
		});
	}

	/**
	 * A Partition of the inbox served by its own single threaded executor service
	 * @author ulmermark
	 *
	 */
	static class Partition
	{
		private final ExecutorService executor;
		
		Partition( ExecutorService executor)
		{
			this.executor = executor;
		}
		<T> void deliver(Target<T> target, T payload){
			
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
		
		void clear() {
			// TODO implement when needed
		}

	}
}
