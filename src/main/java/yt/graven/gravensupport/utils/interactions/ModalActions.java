package yt.graven.gravensupport.utils.interactions;

import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import org.springframework.context.ApplicationContext;
import yt.graven.gravensupport.commands.ticket.interactions.OtherOpeningReasonHandler;
import yt.graven.gravensupport.commands.ticket.interactions.ReportUserModalHandler;
import yt.graven.gravensupport.utils.exceptions.TicketException;

@RequiredArgsConstructor
public enum ModalActions {
    OPENING_REASON("op-other-reason", (context) -> context.getBean(OtherOpeningReasonHandler.class)),
    REPORT_USER_MODAL("op-report-user", (context) -> context.getBean(ReportUserModalHandler.class));

    private final String actionId;
    private final Function<ApplicationContext, InteractionAction<ModalInteractionEvent>> handler;

    public void run(ApplicationContext context, ModalInteractionEvent event) throws TicketException, IOException {
        handler.apply(context).run(event);
    }

    public static Optional<ModalActions> getFromActionId(String actionId) {
        return Arrays.stream(values())
                .filter(a -> Objects.equals(a.actionId, actionId))
                .findFirst();
    }
}
