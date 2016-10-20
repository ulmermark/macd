package com.saluki.concurrency.inbox;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.saluki.algotrading.strategy.core.StrategyServiceGroup;

/**
 * Inbox Factory implementation to create DedicatedInbox instances
 * 
 * @author ulmermark
 *
 */
public class DedicatedInboxFactory  implements InboxFactory{

    private static final Logger log = LoggerFactory.getLogger(DedicatedInboxFactory.class);

    public Inbox createInbox(String name) throws Exception {
        Inbox inbox = new DedicatedInbox(name);
        InboxManager.getInstance().addInbox(name,inbox);
    	log.info("inbox created: name:[{}]", inbox.getName());
        return inbox;
    }}
