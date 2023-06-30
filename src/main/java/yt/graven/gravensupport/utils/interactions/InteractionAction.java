package yt.graven.gravensupport.utils.interactions;

import java.io.IOException;
import net.dv8tion.jda.api.events.Event;

public interface InteractionAction<T extends Event> {

    void run(T event) throws IOException;
}
