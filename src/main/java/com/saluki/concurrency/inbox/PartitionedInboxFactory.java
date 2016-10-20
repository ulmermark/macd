package com.saluki.concurrency.inbox;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Preconditions;

/**
 * InboxFactory that creates a PartitionedInbox...
 * @author ulmermark
 *
 */
public class PartitionedInboxFactory  implements InboxFactory{

	private static final int DEFAULT_NUM_PARTITIONS = 2;
	private static final String DEFAULT_PARTITION_EXPRESSION = "hashCode()";
	
	private final int numPartitions;
	private final String partitionExpression;
	
	/** 
	 * Construct default instance
	 */
	public PartitionedInboxFactory()
	{
		this( DEFAULT_NUM_PARTITIONS, DEFAULT_PARTITION_EXPRESSION);
	}
	
	public PartitionedInboxFactory(int numPartitions)
	{
		this( numPartitions, DEFAULT_PARTITION_EXPRESSION);
	}
	
	
    public PartitionedInboxFactory(int numPartitions, String partitionExpression) {
    	Preconditions.checkArgument(numPartitions > 0, "numPartitions must be greater than 0");
    	Preconditions.checkArgument(partitionExpression != null, "partitionExpression must be not null and not empty");
		this.numPartitions = numPartitions;
		this.partitionExpression = partitionExpression;
	}


	public Inbox createInbox(String name) throws Exception {
        Inbox inbox = new PartitionedInbox(name, numPartitions, partitionExpression);
        InboxManager.getInstance().addInbox(name,inbox);
        return inbox;
    }}
