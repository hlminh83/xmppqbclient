package com.bosch.thingbook;

import org.jivesoftware.smack.chat2.ChatManager;
import org.springframework.stereotype.Component;

@Component
public class ChatListner {
	
	private ChatManager chatManager;

	public ChatManager getChatManager() {
		return chatManager;
	}

	public void setChatManager(ChatManager chatManager) {
		this.chatManager = chatManager;
	} 
	
	
	
}
