package yt.graven.gravensupport.utils.interactions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import org.springframework.context.ApplicationContext;
import yt.graven.gravensupport.commands.ticket.interactions.FirstSentenceHandler;
import yt.graven.gravensupport.commands.ticket.interactions.OpeningReasonHandler;
import yt.graven.gravensupport.utils.exceptions.TicketException;

@RequiredArgsConstructor
public enum SelectionMenuActions {
    FIRST_SENTENCE("first-sentence", (context) -> context.getBean(FirstSentenceHandler.class)),
    OPENING_REASON("opening-menu", (context) -> context.getBean(OpeningReasonHandler.class));

    private final String actionId;
    private final Function<ApplicationContext, InteractionAction<StringSelectInteractionEvent>> handler;

    public void run(ApplicationContext context, StringSelectInteractionEvent event)
            throws TicketException, IOException {
        handler.apply(context).run(event);
    }

    public static Optional<SelectionMenuActions> getFromActionId(String actionId) {
        return Arrays.stream(values())
                .filter(a -> Objects.equals(a.actionId, actionId))
                .findFirst();
    }
}
