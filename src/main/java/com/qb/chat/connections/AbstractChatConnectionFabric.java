package com.qb.chat.connections;

import org.jivesoftware.smack.AbstractXMPPConnection;

public interface AbstractChatConnectionFabric<T extends AbstractXMPPConnection, C extends QBBaseXmppConfigurationBuilder> {
    T buildChatConnection();

    void setConfigurationBuilder(C var1);

    C getConfigurationBuilder();
}
