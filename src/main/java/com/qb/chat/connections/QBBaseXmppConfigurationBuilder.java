//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.quickblox.chat.connections;

import javax.net.SocketFactory;

import com.quickblox.chat.QBSettings;

public abstract class QBBaseXmppConfigurationBuilder<T> {
    private boolean useTls = true;
    private int port = 5222;
    private String host = QBSettings.getInstance().getChatEndpoint();
    private String serviceName = QBSettings.getInstance().getChatEndpoint();
    private SocketFactory socketFactory;
    private boolean isAutojoinEnabled = false;
    private boolean isAutoMarkMessagesDelivered = true;
    private boolean reconnectionAllowed = true;
    private boolean allowListenNetworkStateChanges = true;

    public QBBaseXmppConfigurationBuilder() {
    }

    public boolean isUseTls() {
        return this.useTls;
    }

    public T setUseTls(boolean useTls) {
        this.useTls = useTls;
        return this.getThis();
    }

    public int getPort() {
        return this.port;
    }

    public T setPort(int port) {
        this.port = port;
        return this.getThis();
    }

    public String getHost() {
        return this.host;
    }

    public T setHost(String host) {
        this.host = host;
        return this.getThis();
    }

    public String getServiceName() {
        return this.serviceName;
    }

    public T setServiceName(String serviceName) {
        this.serviceName = serviceName;
        return this.getThis();
    }

    public SocketFactory getSocketFactory() {
        return this.socketFactory;
    }

    public T setSocketFactory(SocketFactory socketFactory) {
        this.socketFactory = socketFactory;
        return this.getThis();
    }

    public boolean isAutojoinEnabled() {
        return this.isAutojoinEnabled;
    }

    public T setAutojoinEnabled(boolean autojoinEnabled) {
        this.isAutojoinEnabled = autojoinEnabled;
        return this.getThis();
    }

    public boolean isAutoMarkMessagesDelivered() {
        return this.isAutoMarkMessagesDelivered;
    }

    public T setAutoMarkDelivered(boolean autoMarkDelivered) {
        this.isAutoMarkMessagesDelivered = autoMarkDelivered;
        return this.getThis();
    }

    protected abstract T getThis();

    public boolean isReconnectionAllowed() {
        return this.reconnectionAllowed;
    }

    public T setReconnectionAllowed(boolean reconnectionAllowed) {
        this.reconnectionAllowed = reconnectionAllowed;
        return this.getThis();
    }

    public boolean isAllowListenNetworkStateChanges() {
        return this.allowListenNetworkStateChanges;
    }

    public T setAllowListenNetwork(boolean allowed) {
        this.allowListenNetworkStateChanges = allowed;
        return this.getThis();
    }
}
