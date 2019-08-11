//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.qb.chat.connections.tcp;

import javax.net.ssl.SSLContext;

import com.qb.chat.connections.QBBaseXmppConfigurationBuilder;

public class QBTcpConfigurationBuilder extends QBBaseXmppConfigurationBuilder<QBTcpConfigurationBuilder> {
    private int defaultSocketTimeOut = 60;
    private boolean keepAlive = true;
    private SSLContext sslContext;
    private boolean useStreamManagement = false;
    private boolean useSmResumption = false;
    private int preferredResumptionTime;

    public QBTcpConfigurationBuilder() {
    }

    public int getSocketTimeout() {
        return this.defaultSocketTimeOut;
    }

    public QBTcpConfigurationBuilder setSocketTimeout(int defaultSocketTimeOut) {
        this.defaultSocketTimeOut = defaultSocketTimeOut;
        return this.getThis();
    }

    public boolean isKeepAlive() {
        return this.keepAlive;
    }

    public QBTcpConfigurationBuilder setKeepAlive(boolean keepAlive) {
        this.keepAlive = keepAlive;
        return this.getThis();
    }

    public SSLContext getSslContext() {
        return this.sslContext;
    }

    public QBTcpConfigurationBuilder setCustomSSLContext(SSLContext sslContext) {
        this.sslContext = sslContext;
        return this;
    }

    public boolean isUseStreamManagement() {
        return this.useStreamManagement;
    }

    public QBTcpConfigurationBuilder setUseStreamManagement(boolean useStreamManagement) {
        this.useStreamManagement = useStreamManagement;
        return this.getThis();
    }

    public boolean isUseSmResumption() {
        return this.useSmResumption;
    }

    public QBTcpConfigurationBuilder setUseStreamManagementResumption(boolean useSmResumption) {
        this.useSmResumption = useSmResumption;
        return this.getThis();
    }

    public int getPreferredResumptionTime() {
        return this.preferredResumptionTime;
    }

    public QBTcpConfigurationBuilder setPreferredResumptionTime(int preferredResumptionTime) {
        this.preferredResumptionTime = preferredResumptionTime;
        return this.getThis();
    }

    protected QBTcpConfigurationBuilder getThis() {
        return this;
    }
}
