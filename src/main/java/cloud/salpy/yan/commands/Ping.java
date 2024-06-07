package cloud.salpy.yan.commands;

import cloud.salpy.yan.core.format.SlashCommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Ping extends SlashCommand {
    public Ping() {
        this.name = "ping";
        this.description = "pong";
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("Pong! My latency is `" + event.getJDA().getGatewayPing() + "` ms").setEphemeral(true).queue();
    }
}
