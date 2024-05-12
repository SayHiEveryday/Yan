package cloud.salpy.yan.Structure.manager;

import cloud.salpy.yan.Structure.format.sCmd;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class CommandManager extends ListenerAdapter {
    private final List<sCmd> command = new ArrayList<sCmd>();

    @Override
    public void onReady(ReadyEvent event) {
        event.getJDA().updateCommands().addCommands().queue();
        for (Guild guild : event.getJDA().getGuilds())
        {
            for (sCmd c : command)
            {
                if (c.option == null) {
                    guild.upsertCommand(c.name.toLowerCase(),c.description).queue();
                } else {
                    guild.upsertCommand(c.name.toLowerCase(),c.description).addOptions(c.option).queue();
                }
            }
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        for (sCmd c : command)
        {
            if (c.name.equals(event.getName()))
            {
                try {
                    c.execute(event);
                } catch (Exception e) {
                    EmbedBuilder embed = new EmbedBuilder()
                            .setDescription(e.toString())
                            .setColor(new Color((int)(Math.random() * 0x1000000)));
                    event.replyEmbeds(embed.build()).queue();
                }
            }

        }
    }

    public void add(sCmd c)
    {
        command.add(c);
    }
    public void add(sCmd... commands) {
        Collections.addAll(this.command, commands);
    }
}
