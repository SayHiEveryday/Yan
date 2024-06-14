package cloud.salpy.yan.core;


import cloud.salpy.yan.commands.Avatar;
import cloud.salpy.yan.commands.Ping;
import cloud.salpy.yan.commands.Purge;
import cloud.salpy.yan.core.Manager.CommandManager;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.internal.utils.JDALogger;

public class Client {
    private JDABuilder jdaBuilder;
    private ShardManager shardmanager;
    private final CommandManager commandManager;
    public Client(String token, String[] arg)
    {
        JDALogger.setFallbackLoggerEnabled(false);
        commandManager = new CommandManager()
                .setPrefix(">")
                .addSlashCommands(
                        new Ping(this),
                        new Purge(this),
                        new Avatar(this)
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
                        .addEventListeners(commandManager);
                jdaBuilder.useSharding(0,1);
        } else if (arg[0].equals("register")) {
            commandManager.register((JDABuilder.createLight(token)).build());
        }
    }
    public JDA start() {
        return jdaBuilder.build();
    }

    public String getPrefix() {
        return commandManager.getPrefix();
    }

    public ShardManager getShardmanager() {
        return shardmanager;
    }
}
