package cloud.salpy.yan.commands;

import cloud.salpy.yan.core.cache.DeleteMessageCache;
import cloud.salpy.yan.core.format.SlashCommand;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

import java.awt.*;

public class Snipe extends SlashCommand {
    public Snipe() {
        this.name = "snipe";
        this.description = "See latest delete message";
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        EmbedBuilder embed;
        MessageCreateBuilder createBuilder = new MessageCreateBuilder();
        try {
            Message cache = DeleteMessageCache.getInstance().get(event.getChannelId());

            embed = new EmbedBuilder()
                    .setAuthor(cache.getAuthor().getEffectiveName(),null,cache.getAuthor().getEffectiveAvatarUrl())
                    .setDescription(cache.getContentRaw().isEmpty() ? "No Message" : cache.getContentRaw())
                    .setColor(new Color(0xFFFFFF))
                    .setTimestamp(cache.getTimeCreated());
            createBuilder.addEmbeds(embed.build());
            if (!cache.getEmbeds().isEmpty()) {
                cache.getEmbeds().forEach(createBuilder::addEmbeds);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            embed = new EmbedBuilder()
                    .setColor(new Color(0xFFFFFF))
                    .setDescription("No delete message found");
            createBuilder.addEmbeds(embed.build());
        }
        event.reply(createBuilder.build()).queue();
    }
}
