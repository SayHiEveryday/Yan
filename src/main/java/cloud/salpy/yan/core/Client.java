package cloud.salpy.yan.core;


import cloud.salpy.yan.commands.Ping;
import cloud.salpy.yan.commands.Snipe;
import cloud.salpy.yan.core.Manager.CommandManager;
import cloud.salpy.yan.core.Manager.CacheManger;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;

import static java.lang.System.exit;

public class Client {
    private JDABuilder jdaBuilder;
    private ShardManager shardmanager;
    public Client(String token, String[] arg)
    {
        CommandManager commandManager = new CommandManager()
                .addCommands(
                        new Ping(),
                        new Snipe()
                );
        if (arg.length == 0) {
                jdaBuilder = JDABuilder.createLight(token)
                        .setMemberCachePolicy(MemberCachePolicy.ALL)
                        .enableIntents(
                                GatewayIntent.GUILD_MEMBERS,
                                GatewayIntent.MESSAGE_CONTENT,
                                GatewayIntent.GUILD_MESSAGES
                        )
                        .setEventPassthrough(true)
                        .addEventListeners(commandManager)
                        .addEventListeners(new CacheManger());
                jdaBuilder.useSharding(0,1);
        } else if (arg[0].equals("register")) {
            commandManager.register((JDABuilder.createLight(token)).build());
        }
    }
    public JDA start() {
        return jdaBuilder.build();
    }

    public ShardManager getShardmanager() {
        return shardmanager;
    }
}
