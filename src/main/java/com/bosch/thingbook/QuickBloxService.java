package com.bosch.thingbook;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.jivesoftware.smack.AbstractXMPPConnection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.chat2.ChatManager;
import org.jivesoftware.smack.tcp.XMPPTCPConnection;
import org.jivesoftware.smack.tcp.XMPPTCPConnectionConfiguration;
import org.jxmpp.stringprep.XmppStringprepException;
import org.springframework.stereotype.Component;


@Component
public class QuickBloxService {

	private String username; 
	private String psw; 
	private String appId; 
	private String userId;
	
	private final int port = 5222; //non ssl port
	private final String xmppDomain = "chat-boschthingbook.quickblox.com"; 
	private final String xmppHost = "chat-boschthingbook.quickblox.com";
	
//	private final String xmppDomain = "chat.quickblox.com"; 
//	private final String xmppHost = "chat.quickblox.com";
	
	private List<ChatManager> managers = new ArrayList<ChatManager>(); 
	
	public QuickBloxService() {
		super();
	}
	//sample with hard code
	public AbstractXMPPConnection getConnectionToQuickBlox(String userId, String appId, String psw) throws XmppStringprepException, KeyManagementException, NoSuchAlgorithmException {
		XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
		configBuilder.setUsernameAndPassword(userId + "-" + appId, psw);
		configBuilder.setXmppDomain(xmppDomain);
		configBuilder.setHost(xmppHost);
		configBuilder.setPort(port);
		configBuilder.setDebuggerEnabled(true);

		//		configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.required) ;
		
//		configBuilder.addEnabledSaslMechanism(SASLMechanism.PLAIN);
//		SSLContext ctx = getSSLContext();
//		configBuilder.setCustomSSLContext(ctx);
		
		AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
		return connection;
	}
	
	public AbstractXMPPConnection getConnectionToQuickBloxWithSessionToken() throws XmppStringprepException, KeyManagementException, NoSuchAlgorithmException {
		XMPPTCPConnectionConfiguration.Builder configBuilder = XMPPTCPConnectionConfiguration.builder();
//		configBuilder.setUsernameAndPassword(userId, psw);
		configBuilder.setXmppDomain(xmppDomain);
		configBuilder.setHost(xmppHost);
		configBuilder.setPort(port);
//		configBuilder.setDebuggerEnabled(true);
		
		configBuilder.setSecurityMode(ConnectionConfiguration.SecurityMode.required) ;
		SSLContext ctx = getSSLContext();
		configBuilder.setCustomSSLContext(ctx);
		AbstractXMPPConnection connection = new XMPPTCPConnection(configBuilder.build());
		return connection;
	}
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	private SSLContext getSSLContext() throws NoSuchAlgorithmException, KeyManagementException {
		SSLContext sslcontext = SSLContext.getInstance("TLS");
		sslcontext.init(null, new TrustManager[] { new X509TrustManager() {
			
			@Override
			public X509Certificate[] getAcceptedIssuers() {
				// TODO Auto-generated method stub
				return null;
			}
			@Override
			public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
			}
			@Override
			public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
				// TODO Auto-generated method stub
			}
		}}, null);
		return sslcontext;
	}

	public List<ChatManager> getManagers() {
		return managers;
	}

	public void setManagers(List<ChatManager> managers) {
		this.managers = managers;
	}
	
}
