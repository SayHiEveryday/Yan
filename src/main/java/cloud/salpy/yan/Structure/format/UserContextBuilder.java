package cloud.salpy.yan.Structure.format;

import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

public abstract class UserContextBuilder {
    public String name;
    public CommandData build() {
        return Commands.context(Command.Type.USER,this.name);
    }
    public abstract void execute(UserContextInteractionEvent event);
}
