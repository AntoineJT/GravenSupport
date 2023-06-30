package yt.graven.gravensupport.utils.commands;

import java.io.IOException;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import yt.graven.gravensupport.utils.exceptions.CommandCancelledException;

public interface ICommand {

    String getName();

    SlashCommandData getSlashCommandData();

    void run(SlashCommandInteractionEvent event) throws IOException, CommandCancelledException;
}
