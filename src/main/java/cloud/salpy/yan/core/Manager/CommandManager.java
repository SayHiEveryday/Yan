package cloud.salpy.yan.core.Manager;

import cloud.salpy.yan.core.format.Command;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CommandManager extends ListenerAdapter {
    private final Map<String, Command> commandMap = new HashMap<>();
    private String prefix;
    @Override
    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {

        Command cmd = commandMap.get(event.getName());
        cmd.executeSlash(event);
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if (!args[0].contains(this.prefix) || event.getAuthor().isBot()) {
            return;
        }
        String cmdName = args[0].replaceFirst(this.prefix, "");
        Command cmd = this.commandMap.get(cmdName);
        cmd.executePrefix(event, args);
    }

    public CommandManager addSlashCommands(Command... command) {
        for (Command scm : command) {
            commandMap.put(scm.getName(),scm);
        }
        return this;
    }
    public void register(JDA jda) {
        jda.updateCommands().queue();
        for (String i : commandMap.keySet()) {
            Command slashCommand = commandMap.get(i);
            jda.upsertCommand(slashCommand.getName(), slashCommand.getDescription()).addOptions(slashCommand.getOption()).queue();
        }
    }

    public String getPrefix() {
        return prefix;
    }

    public CommandManager setPrefix(String prefix) {
        this.prefix = prefix;
        return this;
    }
}
