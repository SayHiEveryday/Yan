package cloud.salpy.yan.Structure;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import java.util.ArrayList;


public class Client {
    private JDABuilder jdaBuilder;
    public Client(String token) {
        this.jdaBuilder = JDABuilder.createDefault(token)
                .enableIntents(
                        GatewayIntent.GUILD_MEMBERS,
                        GatewayIntent.GUILD_MESSAGES,
                        GatewayIntent.MESSAGE_CONTENT,
                        GatewayIntent.GUILD_MESSAGE_TYPING,
                        GatewayIntent.GUILD_PRESENCES
                )
                .setEventPassthrough(true)
                .setMemberCachePolicy(MemberCachePolicy.ALL);
    }
    public void addEvent(ListenerAdapter event) {
        this.jdaBuilder.addEventListeners(event);
    }
    public JDA build() {
        return this.jdaBuilder.build();
    }

}
