package yt.graven.gravensupport.utils.messages.builder;

import yt.graven.gravensupport.utils.messages.builder.data.TicketMessage;

public class MessageFactory {

    private MessageFactory() {}

    public static TicketMessage create() {
        return new TicketMessage();
    }
}
