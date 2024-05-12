package cloud.salpy.yan.Structure.format;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;


import java.util.ArrayList;
import java.util.List;

public abstract class sCmd {
    public String name = "Null";
    public String description = "";
    public List<OptionData> option = new ArrayList<>();
    public void addOption(OptionData... optionData) {
       option.addAll(List.of(optionData));
    }
    public abstract void execute(SlashCommandInteractionEvent event);
}