package cloud.salpy.yan.Structure.manager;

import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import cloud.salpy.yan.Structure.format.PrefixCommandBuilder;
import cloud.salpy.yan.pri.Constant;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PrefixCommandManager extends ListenerAdapter {
    private List<PrefixCommandBuilder> pCmds = new ArrayList<PrefixCommandBuilder>();
    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if(!args[0].contains(Constant.prefix) || Objects.requireNonNull(event.getMember()).getUser().isBot()) {
            return;
        }

        if(!args[0].startsWith(Constant.prefix)) {
            return;
        }
        String cmdName = args[0].replaceFirst(Constant.prefix, "");
        for (PrefixCommandBuilder cmd : pCmds) {
            if (cmdName.equals(cmd.name)) {
                cmd.execute(event,args);
            }
        }
    }
    public void add(PrefixCommandBuilder... cmd) {
        Collections.addAll(this.pCmds, cmd);
    }
}
