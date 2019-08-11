package com.bosch.thingbook;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.quickblox.chat.QBSettings;

@Controller
public class LoginController {

//	@Autowired 
//	MessageForwarder forwarder ; 
	
	int userId = 53135;  
	String appId = "4";
	//appId: 4, authKey: "XKSnFfzA9kdv5xv", authSecret: "kP7cpbJhk7Q9mRt", accountKey:  "so97-KJv2S8KxY1uZaot"
//	String token = "e94cec84227ce1bce22d9b7e4b88ee943a000004";
	String authKey = "XKSnFfzA9kdv5xv"; 
	String authSecret = "kP7cpbJhk7Q9mRt"; 
	String accountKey = "so97-KJv2S8KxY1uZaot";
	
	@Autowired
	QBChatService chatService; 
	
	@Autowired
	MessageForwarder forwarder;
    @PostMapping(path="/publicaccount/login")
    public @ResponseBody LoginForm postMessage(@RequestBody LoginForm  message) throws Exception {
    	
    	try {
			QBUser user = new QBUser(Integer.valueOf(message.getUserId()));
			//init setup
			QBSettings.getInstance().init(appId, authKey, authSecret, accountKey);
			user.setPassword(message.getSessionToken());
			chatService.login(user, null,  new IncomingChatMessageListener() {
				@Override
				public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
					forwarder.postToWebHook(message.getBody());
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return message;
    }
    
    
}
