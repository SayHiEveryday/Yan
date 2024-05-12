package cloud.salpy.yan;

import cloud.salpy.yan.Structure.manager.ButtonManager;
import cloud.salpy.yan.Structure.Client;
import cloud.salpy.yan.Structure.manager.CommandManager;
import cloud.salpy.yan.Structure.manager.PrefixCommandManager;
import cloud.salpy.yan.commands.pCmd.pAvatar;
import cloud.salpy.yan.commands.pCmd.pPing;
import cloud.salpy.yan.commands.sCmd.*;
import cloud.salpy.yan.component.button.getJpeg;
import cloud.salpy.yan.component.button.getWebp;
import cloud.salpy.yan.component.button.pong;
import cloud.salpy.yan.event.*;

import cloud.salpy.yan.pri.Constant;
import net.dv8tion.jda.api.JDA;

public class Main {
    public static void main(String[] args) {
        CommandManager manager = new CommandManager();
        manager.add(
                new Ping(),
                new sysinfo(),
                new testpoll(),
                new purge(),
                new embedbuilder(),
                new userinfo(),
                new guildinfo()
        );
        PrefixCommandManager prefixCommandManager = new PrefixCommandManager();
        prefixCommandManager.add(
                new pPing(),
                new pAvatar()
        );
        ButtonManager buttonManager = new ButtonManager();
        buttonManager.add(
                new pong(),
                new getJpeg(),
                new getWebp()
        );

        Client client = new Client(Constant.token);
        client.addEvent(manager);
        client.addEvent(prefixCommandManager);
        client.addEvent(new onMessage());
        client.addEvent(new onReady());
        client.addEvent(buttonManager);

        JDA jda = client.build();
    }
}