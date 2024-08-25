package cloud.salpy.yan.core.Manager;

import cloud.salpy.yan.core.Cache.MessageCache;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class CacheManager extends ListenerAdapter {
//    @Override
//    public void onMessageDelete(MessageDeleteEvent event) {
//        DeleteMessageCache cache = DeleteMessageCache.getInstance();
//        Message messagecache = MessageCache.getInstance().get(event.getChannel().getId());
//        cache.put(event.getChannel().getId(),messagecache);
//    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {
        try {
            MessageCache cache = MessageCache.getInstance();
            cache.put(event.getChannel().getId(), event.getMessage());
        } catch ( Exception ignored) {

        }
    }
}