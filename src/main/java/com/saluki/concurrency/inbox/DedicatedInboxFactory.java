package com.saluki.concurrency.inbox;

/**
 * Inbox Factory implementation to create DedicatedInbox instances
 * 
 * @author ulmermark
 *
 */
public class DedicatedInboxFactory  implements InboxFactory{

    public Inbox createInbox(String name) throws Exception {
        Inbox inbox = new DedicatedInbox(name);
        InboxManager.getInstance().addInbox(name,inbox);
        return inbox;
    }}
