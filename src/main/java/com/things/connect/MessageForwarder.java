package com.bosch.thingbook;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jsunsoft.http.HttpRequest;
import com.jsunsoft.http.HttpRequestBuilder;
import com.jsunsoft.http.ResponseDeserializer;
import com.quickblox.chat.QBSettings;

@Component
public class MessageForwarder {
	
	private Map<Long, PAConfig> PAMap = new HashMap<Long, PAConfig>(); 
	
	@Autowired
	QuickBloxService service; 
	
	@Autowired
	QBChatService chatService; 
	
	
	int userId = 53135;  
	String appId = "4";
	//appId: 4, authKey: "XKSnFfzA9kdv5xv", authSecret: "kP7cpbJhk7Q9mRt", accountKey:  "so97-KJv2S8KxY1uZaot"
	String token = "e94cec84227ce1bce22d9b7e4b88ee943a000004";
	String authKey = "XKSnFfzA9kdv5xv"; 
	String authSecret = "kP7cpbJhk7Q9mRt"; 
	String accountKey = "so97-KJv2S8KxY1uZaot";
	
	
//	@PostConstruct
//    public void init() {
////        LOG.info(Arrays.asList(environment.getDefaultProfiles()));
//		//retrieve the PA 
//		try {
//
//			QBUser user = new QBUser();
//			user.setId(userId);
//			//init setup
//			QBSettings.getInstance().init(appId, authKey, authSecret, accountKey);
//			user.setPassword(token);
//			chatService.login(user, null,  new IncomingChatMessageListener() {
//				@Override
//				public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
//					System.out.println("message---> " + message.getBody());
//					//post to webhooks 
//					postToWebHook(message.getBody());
//				}
//			});
////			chatService.login(user, null);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}
//	
	public void postToWebHook(String message) {
		//server url
	    String url = "http://localhost:8080/sendMessage";
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("message",message);
	    // static class "HttpUtility" with static method "newRequest(url,method,callback)"
	    HttpRequest<String> httpRequest = HttpRequestBuilder.createPost(url, String.class)
                .responseDeserializer(ResponseDeserializer.toStringDeserializer()).build();
	    httpRequest.execute(params).get(); // see javadoc of get method
	}
}
