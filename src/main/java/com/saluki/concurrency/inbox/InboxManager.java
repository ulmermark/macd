package com.saluki.concurrency.inbox;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* Class used to cache all inboxes to manage and provide "Adminable" functionality for inboxes
*/
public class InboxManager 
{
 private static final Logger log = LoggerFactory.getLogger(InboxManager.class);
 final ConcurrentMap<String, Inbox> inboxes;
 
 private static final Object syncLock = new Object();
 private static final InboxManager instance;
 static {
         instance = new InboxManager();
 }

 private InboxManager() {
     inboxes = new ConcurrentHashMap<String, Inbox>();
 }

 public static InboxManager getInstance() throws Exception {
     return instance;
 }

 public String addInbox(String inboxName, Inbox inbox) throws Exception {
     synchronized (syncLock)
     {
         String originalInboxName = inboxName;
         int count = 2;
    	 Inbox existing = inboxes.putIfAbsent(inboxName,  inbox);
         while (existing != null)
         {
             inboxName = String.format("{0}-{1}", originalInboxName, count);
        	 existing = inboxes.putIfAbsent(inboxName,  inbox);
             count++;
         }
     }
     return inboxName;
 }

 public void clearCache() throws Exception {
     this.inboxes.clear();
 }

 public void shutdown() {
     log.info("Shutting down inboxes");
     
     inboxes.forEach((k,v)->{
     	try {
				v.shutdown();
			} catch (Exception e) {
				log.error("Message:[{}]", e.getMessage(), e);
			}
     });
 }

}


