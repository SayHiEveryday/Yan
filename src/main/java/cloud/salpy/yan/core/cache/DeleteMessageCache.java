package cloud.salpy.yan.core.cache;

import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import net.dv8tion.jda.api.entities.Message;

public class DeleteMessageCache {

    private static final DeleteMessageCache INSTANCE = new DeleteMessageCache(); // Singleton pattern

    private final LoadingCache<String, Message> cache;

    private DeleteMessageCache() {
        cache = Caffeine.newBuilder()
                .build(this::loadValue); // Replace with your value loading logic
    }

    public static DeleteMessageCache getInstance() {
        return INSTANCE;
    }

    private Message loadValue(String key) {
        return cache.get(key);
    }

    public void put(String key, Message value) {
        cache.put(key, value);
    }

    public Message get(String key) {
        return cache.get(key);
    }
}
