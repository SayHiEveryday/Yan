package cloud.salpy.yan.commands;

import cloud.salpy.yan.core.Client;
import cloud.salpy.yan.core.format.Command;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class Ping extends Command {
    public Ping(Client client) {
        super(client);
        this
                .setName("ping")
                .setDescription("Return latency of bot");
    }

    @Override
    public void executePrefix(MessageReceivedEvent event, String[] args) {
        event.getMessage().reply("Pong! My latency is `" + event.getJDA().getGatewayPing() + "` ms").queue();
    }

    @Override
    public void executeSlash(SlashCommandInteractionEvent event) {
        event.reply("Pong! My latency is `" + event.getJDA().getGatewayPing() + "` ms").setEphemeral(true).queue();
    }
}
    