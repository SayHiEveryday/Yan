package cloud.salpy.yan.commands.sCmd;

import cloud.salpy.yan.Structure.format.SlashCommandBuilder;
import cloud.salpy.yan.component.button.pong;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

public class Ping extends SlashCommandBuilder {
    public Ping() {
        this.name = "ping";
        this.description = "pong!";
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        event.reply("pong!").addActionRow(new pong().build()).queue();
    }
}
