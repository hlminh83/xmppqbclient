package com.quickblox.chat;

public class QBSettings {

	private String applicationId;
	private String authorizationKey;
	private String authorizationSecret;
	private String accountKey;
	private String chatEndpoint = "chat-boschthingbook.quickblox.com";
	private static QBSettings instance;
	    
	    
	public static QBSettings getInstance() {
		if(instance == null)
		{
			instance = new QBSettings();
			return instance;
		}
		return instance;
	}
	
	public void init(String applicationId, String authKey, String authSecret, String accountKey){
		this.applicationId = applicationId; 
		this.authorizationKey = authKey;
		this.authorizationSecret = authSecret; 
		this.accountKey = accountKey;
	}
	public String getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(String applicationId) {
		this.applicationId = applicationId;
	}
	public String getAuthorizationKey() {
		return authorizationKey;
	}
	public void setAuthorizationKey(String authorizationKey) {
		this.authorizationKey = authorizationKey;
	}
	public String getAuthorizationSecret() {
		return authorizationSecret;
	}
	public void setAuthorizationSecret(String authorizationSecret) {
		this.authorizationSecret = authorizationSecret;
	}
	public String getAccountKey() {
		return accountKey;
	}
	public void setAccountKey(String accountKey) {
		this.accountKey = accountKey;
	}

	public String getChatEndpoint() {
		return this.chatEndpoint;
	}


	
}
