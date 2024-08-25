package cloud.salpy.yan.core.format;

import cloud.salpy.yan.core.Client;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Command {
    private String name;
    private String description;
    private final List<OptionData> option = new ArrayList<>();
    protected final Client client;
    private final Map<String, ButtonExecutable.ButtonExecutableInter> executableInterMap = new HashMap<>();
    protected Command(Client client) {
        this.client = client;
    }
    public List<OptionData> getOption() {
        return option;
    }
    public String getDescription() {
        return description;
    }

    public Command setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getName() {
        return name;
    }

    public Command setName(String name) {
        this.name = name;
        return this;
    }

    public Command addButton(ButtonExecutable buttonExecutable) {
        executableInterMap.put(this.name + "." +  buttonExecutable.getCustomid(),buttonExecutable.getRunable());
        return this;
    }
    public Command addButtons(ButtonExecutable... buttonExecutables) {
        for (ButtonExecutable be : buttonExecutables) {
            executableInterMap.put(this.name + "." + be.getCustomid(),be.getRunable());

        }
        return this;
    }
    public ButtonExecutable.ButtonExecutableInter getButtonRunnable(String customid) {
        return executableInterMap.get(this.name + "." + customid);
    }


    public Command addOption(OptionData... optionData) { option.addAll(List.of(optionData)); return this; }
    public void executePrefix(@NotNull MessageReceivedEvent event, String[] args) {
        event.getMessage().reply("Hey <@" + event.getAuthor().getId() + ">, we do not have this command available with the prefix, but we also have it as a slash command.").queue();
    }
    public void executeSlash(@NotNull SlashCommandInteractionEvent event) {
        event.reply("How do you interact with this command? Please contact developer").setEphemeral(true).queue();
    }
}
