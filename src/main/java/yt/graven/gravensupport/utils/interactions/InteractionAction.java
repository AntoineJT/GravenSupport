package yt.graven.gravensupport.utils.interactions;

import java.io.IOException;
import net.dv8tion.jda.api.events.Event;
import yt.graven.gravensupport.utils.exceptions.TicketException;

public interface InteractionAction<T extends Event> {

    void run(T event) throws TicketException, IOException;
}
