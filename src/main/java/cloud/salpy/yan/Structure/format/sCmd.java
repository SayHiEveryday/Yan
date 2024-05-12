package cloud.salpy.yan.Structure.format;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;


import java.util.Collections;
import java.util.List;

public abstract class sCmd {
    public String name = "Null";
    public String description = "";
    public List<OptionData> option = null;
    public void addOption(OptionData... optionData) {
       Collections.addAll(option, optionData);
    }
    public void addOption(OptionData optionData) {
        option.add(optionData);
    }
    public abstract void execute(SlashCommandInteractionEvent event);
}