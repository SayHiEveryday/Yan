package cloud.salpy.yan.Structure.manager;

import cloud.salpy.yan.Structure.format.UserContextBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ContextManager extends ListenerAdapter {
    private final List<UserContextBuilder> usercontext = new ArrayList<UserContextBuilder>();
    @Override
    public void onReady(ReadyEvent event) {
        for (Guild guild : event.getJDA().getGuilds())
        {
            for (UserContextBuilder user : usercontext) {
                guild.upsertCommand(user.build()).queue();
            }
        }
    }

    @Override
    public void onUserContextInteraction(UserContextInteractionEvent event) {
        for (UserContextBuilder context : usercontext) {
            if (event.getName().equals(context.name)) {
                context.execute(event);
            }
        }
    }
    public void addUserContext(UserContextBuilder... userContextBuilders) {
        Collections.addAll(usercontext, userContextBuilders);
    }
}
