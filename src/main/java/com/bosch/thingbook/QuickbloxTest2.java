package com.bosch.thingbook;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jxmpp.jid.EntityBareJid;
import org.jxmpp.stringprep.XmppStringprepException;

import com.quickblox.chat.QBSettings;

public class QuickbloxTest2 {
	public static void sendChat1() throws InterruptedException, XmppStringprepException, KeyManagementException, NoSuchAlgorithmException {
		int userId = 313771;  
		String appId = "4";
		//appId: 4, authKey: "XKSnFfzA9kdv5xv", authSecret: "kP7cpbJhk7Q9mRt", accountKey:  "so97-KJv2S8KxY1uZaot"
		String token = "481f1d3db6e0cce0b5cdbca39b4bf79071000004";
		String authKey = "XKSnFfzA9kdv5xv"; 
		String authSecret = "kP7cpbJhk7Q9mRt"; 
		String accountKey = "so97-KJv2S8KxY1uZaot";
		//481f1d3db6e0cce0b5cdbca39b4bf79071000004  
		//id:313771  
		QBUser user = new QBUser();
		user.setId(userId);
		
		QBSettings.getInstance().init(appId, authKey, authSecret, accountKey);
		QBChatService chatService = new QBChatService();//.getInstance();
		try {
		    user.setPassword(token);
//		    chatService.login(user, new MyQBEntityCallback() {
//	            @Override
//	            public void onSuccess() {
//	 
//	            }
//	 
//	            @Override
//	            public void onError(MyQBResponseException errors) {
//	            }
//	        });
		    chatService.login(user, null, new IncomingChatMessageListener() {
				
				@Override
				public void newIncomingMessage(EntityBareJid from, Message message, Chat chat) {
					// TODO Auto-generated method stub
					System.out.println("hahah" + message.getBody());
				}
			});
		}catch(Exception e) {
		    e.printStackTrace();  
		}
	}	
	public static void main(String[] args) {
		try {
			sendChat1();
		} catch (KeyManagementException e) {
			e.printStackTrace();
		} catch (XmppStringprepException e) {
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
