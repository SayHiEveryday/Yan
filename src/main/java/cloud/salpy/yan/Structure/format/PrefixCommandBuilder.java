package cloud.salpy.yan.Structure.format;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public abstract class PrefixCommandBuilder {
    public String name;
    public abstract void execute(MessageReceivedEvent event, String[] args);
}
