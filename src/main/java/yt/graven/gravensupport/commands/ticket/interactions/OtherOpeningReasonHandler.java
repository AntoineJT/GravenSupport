package yt.graven.gravensupport.commands.ticket.interactions;

import java.awt.*;
import java.io.IOException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.channel.ChannelType;
import net.dv8tion.jda.api.entities.channel.concrete.PrivateChannel;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.modals.ModalMapping;
import org.springframework.stereotype.Component;
import yt.graven.gravensupport.commands.ticket.Ticket;
import yt.graven.gravensupport.commands.ticket.TicketManager;
import yt.graven.gravensupport.commands.ticket.TicketOpeningReason;
import yt.graven.gravensupport.utils.exceptions.TicketException;
import yt.graven.gravensupport.utils.interactions.InteractionAction;
import yt.graven.gravensupport.utils.messages.Embeds;

@Component
@RequiredArgsConstructor
public class OtherOpeningReasonHandler implements InteractionAction<ModalInteractionEvent> {

    private final Embeds embeds;
    private final TicketManager manager;

    @Override
    public void run(ModalInteractionEvent event) throws TicketException, IOException {

        if (event.getChannel().getType() != ChannelType.PRIVATE) return;

        PrivateChannel channel = event.getChannel().asPrivateChannel();
        Optional<Ticket> ticket = manager.get(channel.getUser());

        if (ticket.isEmpty()) {
            ticket = Optional.of(manager.create(channel.getUser()));
        }

        if (ticket.get().isOpened()) {
            event.deferReply(true)
                    .addEmbeds(embeds.ticketAlreadyExists(true).build())
                    .queue();
            return;
        }

        String reason = Optional.ofNullable(event.getValue("reason"))
                .map(ModalMapping::getAsString)
                .orElse("");

        if (reason.isEmpty()) {
            event.deferReply(true)
                    .addEmbeds(embeds.error("Vous devez entrer une raison pour ouvrir un ticket.")
                            .build())
                    .queue();
            return;
        }

        ticket.get().openOnServer(false, null, new TicketOpeningReason.Simple(reason));
        event.deferReply(true)
                .addEmbeds(new EmbedBuilder()
                        .setColor(Color.GREEN)
                        .setTitle("Ticket ouvert !")
                        .setDescription(
                                "Le ticket a bien été ouvert ! Vous pouvez désormais communiquer avec la modération")
                        .build())
                .queue();
    }
}
