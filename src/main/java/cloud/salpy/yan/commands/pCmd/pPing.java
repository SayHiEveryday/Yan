package cloud.salpy.yan.commands.pCmd;

import cloud.salpy.yan.Structure.format.PrefixCommandBuilder;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

public class pPing extends PrefixCommandBuilder {
    public pPing() {
        this.name = "ping";
    }
    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        event.getMessage().reply("pong!").queue();
    }
}
