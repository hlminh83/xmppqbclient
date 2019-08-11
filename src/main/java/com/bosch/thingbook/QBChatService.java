package com.bosch.thingbook;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.SASLAuthentication;
import org.jivesoftware.smack.SmackException;
import org.jivesoftware.smack.SmackException.AlreadyLoggedInException;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.chat2.Chat;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.chat2.IncomingChatMessageListener;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.sasl.javax.SASLCramMD5Mechanism;
import org.jivesoftware.smack.sasl.javax.SASLDigestMD5Mechanism;
import org.jivesoftware.smack.sasl.javax.SASLExternalMechanism;
import org.jivesoftware.smack.sasl.javax.SASLGSSAPIMechanism;
import org.jivesoftware.smack.sasl.javax.SASLPlainMechanism;
import org.jxmpp.jid.EntityBareJid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quickblox.chat.JIDHelper;
import com.quickblox.chat.connections.tcp.QBTcpConfigurationBuilder;


@Component
public class QBChatService {

	private AbstractXMPPConnection connection;
	private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	private QBUser userForLogin;
//	private QBIncomingMessagesManager messagesManager;
//	private static AbstractChatConnectionFabric connectionFabric;
//	private static QBChatService.ConfigurationBuilder configurationBuilder;
//	private static QBChatService instance;
	private boolean useStreamManagement = false;

	
//	public static QBChatService getInstance() {
//		if (instance == null) {
//			instance = new QBChatService();
//		}
//		return instance;
//	}

	@Autowired
	MessageForwarder forwarder ;
	
	private abstract class Task extends ThreadTask {
		protected boolean end = false; 
		public boolean isEnd() {
			return end;
		}
		public void setEnd(boolean end) {
			this.end = end;
		}
		public Task() {
			super(QBChatService.this.scheduler);
		}
	}

	public synchronized void login(QBUser user, String resource, IncomingChatMessageListener listner) throws XMPPException, IOException, SmackException {
//        this.validateUser(user);
		if (this.isLoggedIn()) {
			throw new AlreadyLoggedInException();
		} else {
//            this.checkValidSessionToken(user);
			String chatServerEndpoint = "chat-boschthingbook.quickblox.com";
			JIDHelper.INSTANCE.setChatServerEndpoint(chatServerEndpoint);
			String jidLocalpart = JIDHelper.INSTANCE.getJidLocalpart(user);
			this.connect();
			if (this.isConnected() && !this.isAuthenticated()) {
                if (5223 == this.connection.getPort()) {
                	  SASLAuthentication.registerSASLMechanism(new SASLExternalMechanism());
                      SASLAuthentication.registerSASLMechanism(new SASLGSSAPIMechanism());
                      SASLAuthentication.registerSASLMechanism(new SASLDigestMD5Mechanism());
                      SASLAuthentication.registerSASLMechanism(new SASLCramMD5Mechanism());
                      SASLAuthentication.registerSASLMechanism(new SASLPlainMechanism());
                }
				this.userForLogin = user;
				try {
					System.out.println("connected done...login---> " + user.getPassword());
					this.connection.login(jidLocalpart, user.getPassword(), null);
					
					QBChatService.Task var10001 = new QBChatService.Task() {
						@Override
						public void performInAsync() {
							ChatManager chatManager = ChatManager.getInstanceFor(connection);
							chatManager.addIncomingListener(listner);
							while(!end) {
								//do forever
							}
						}
					};
					scheduler.execute(var10001);
//					var10001.setEnd(true);
//					Thread thread = new Thread(var10001);
//					thread.start();
//					int i = 0;
//					while(true) {
//						i++;
//						Thread.sleep(3000);
//						if(i ==100) {
//							connection.disconnect();
//							break;
//						}
//					}
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void initIncomingMessagesManager() {

	}

	private boolean isAuthenticated() {
		return connection.isAuthenticated();
	}

	private boolean isConnected() {
		        return this.connection != null && this.connection.isConnected();
	}

	 synchronized void connect() throws IOException, SmackException, XMPPException {
	        if (!this.isConnected()) {
	            if (this.connection == null) {
	            	QuickBloxService service = new QuickBloxService(); 
	            	try {
						this.connection = service.getConnectionToQuickBloxWithSessionToken();
					} catch (KeyManagementException | NoSuchAlgorithmException e) {
						e.printStackTrace();
					}
	            }
	            try {
					this.connection.connect();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
//	            this.subscribeToConnectionChecker();
	        }

	    }
//
//	private AbstractXMPPConnection createChatConnection(AbstractChatConnectionFabric connectionFabric2) {
//		return connectionFabric.buildChatConnection();
//	}

//	private AbstractChatConnectionFabric buildConnectionFabric(ConfigurationBuilder configurationBuilder2) {
//		configurationBuilder.setUseStreamManagement(this.isStreamManagementEnabled());
////		configurationBuilder.setUseStreamManagementResumption(this.useSmResumption);
////		configurationBuilder.setPreferredResumptionTime(this.preferredResumptionTime);
////		configurationBuilder.setReconnectionAllowed(this.isReconnectionAllowed());
//		return new QBTcpChatConnectionFabric(configurationBuilder);
//	}

	private boolean isStreamManagementEnabled() {
		return this.useStreamManagement;
	}

	public static class ConfigurationBuilder extends QBTcpConfigurationBuilder {
		public ConfigurationBuilder() {
		}
	}

	private boolean isLoggedIn() {
		return false;
	}

//	private void login(QBUser user, String resource, MyQBEntityCallback myQBEntityCallback) {
//		QBChatService.Task var10001 = new QBChatService.Task() {
//            public void performInAsync() {
//                try {
//                    QBChatService.this.login(user, null);
//                } catch (Exception var5) {
////                    QBChatService.notifyFailResult(myQBEntityCallback, var5.getMessage());
//                }
//
//            }
//        };
//	}

//	public void login(QBUser user, MyQBEntityCallback myQBEntityCallback) {
//		QBChatService.Task var10001 = new QBChatService.Task() {
//            public void performInAsync() {
//                try {
//                    QBChatService.this.login(user, null, null);
//                } catch (Exception var5) {
////                    QBChatService.notifyFailResult(myQBEntityCallback, var5.getMessage());
//                }
//
//            }
//        };
//	}

//	 public void login(QBUser user, QBEntityCallback callback) {
//	        this.login(user, defaultResource, callback);
//	    }
//
//	    public void login(final QBUser user, final String resource, final QBEntityCallback<Void> callback) {
//	        this.validateUser(user);
//	        QBChatService.Task var10001 = new QBChatService.Task() {
//	            public void performInAsync() {
//	                try {
//	                    QBChatService.this.login(user, resource);
//	                    QBChatService.notifySuccessResult(callback);
//	                } catch (AlreadyLoggedInException var2) {
//	                    QBChatService.notifyFailResult(callback, "You have already logged in chat");
//	                } catch (ConnectionException var3) {
//	                    QBChatService.notifyFailResult(callback, "Connection failed. Please check your internet connection.");
//	                } catch (SASLErrorException var4) {
//	                    QBChatService.notifyFailResult(callback, "Authentication failed, check user's ID and password");
//	                } catch (Exception var5) {
//	                    QBChatService.notifyFailResult(callback, var5.getMessage());
//	                }
//
//	            }
//	        };
//	    }
}
