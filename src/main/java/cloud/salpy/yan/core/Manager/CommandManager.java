package cloud.salpy.yan.core.Manager;

import cloud.salpy.yan.core.format.SlashCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import java.util.HashMap;
import java.util.Map;

public class CommandManager extends ListenerAdapter {
    private Map<String, SlashCommand> slashmap = new HashMap<>();
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        slashmap.get(event.getName()).execute(event);
    }
    public CommandManager addCommands(SlashCommand... command) {
        for (SlashCommand scm : command) {
            slashmap.put(scm.name,scm);
        }
        return this;
    }
    public CommandManager register(JDA jda) {
        for (String i : slashmap.keySet()) {
            SlashCommand slashCommand = slashmap.get(i);
            jda.upsertCommand(slashCommand.name, slashCommand.description).addOptions(slashCommand.option).queue();
        }
        return this;
    }
}
