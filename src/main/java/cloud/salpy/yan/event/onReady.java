package cloud.salpy.yan.event;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class onReady extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().getPresence().setStatus(OnlineStatus.INVISIBLE);
    }
}
