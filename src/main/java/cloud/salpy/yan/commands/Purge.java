package cloud.salpy.yan.commands;

import cloud.salpy.yan.core.Client;
import cloud.salpy.yan.core.format.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class Purge extends Command {
    public Purge(Client client) {
        super(client);
        this
                .setName("purge")
                .setDescription("Bulk Delete channel message")
                .addOption(
                        new OptionData(OptionType.INTEGER,"limit","Limit of the delete (Max 100)").setRequired(false)
                );

    }

    @Override
    public void executePrefix(@NotNull MessageReceivedEvent event, String[] args) {
        int limit;
        if (args[0].isEmpty()) {
            limit = 100;
        } else {
            limit = Integer.parseInt(args[0]);
        }
        if (!Objects.requireNonNull(event.getMember()).getPermissions().contains(Permission.MESSAGE_MANAGE)) {
            event
                    .getMessage()
                    .replyEmbeds(
                            new EmbedBuilder()
                                    .setDescription("You don't have permission to use this command. required: `Manage Message`")
                                    .setColor(0xFFFFFF)
                                    .build()
                    )
                    .queue();
        }
        if (limit == 1) {
            event.getMessage().delete().queue();
        } else {
            List<Message> message = event.getChannel().getHistory().retrievePast(limit).complete();
            event.getChannel().purgeMessages(message);
            event
                    .getMessage()
                    .replyEmbeds(
                            new EmbedBuilder()
                                    .setDescription("Deleted " + limit + "Messages")
                                    .setColor(0xFFFFFF)
                                    .build()
                    )
                    .queue();
        }
    }
    @Override
    public void executeSlash(@NotNull SlashCommandInteractionEvent event) {
        int limit;
        try {
            limit = event.getOption("limit").getAsInt();
        } catch (NullPointerException e) {
            limit = 100;
        }
        if (!Objects.requireNonNull(event.getMember()).getPermissions().contains(Permission.MESSAGE_MANAGE)) {
            event
                    .replyEmbeds(
                            new EmbedBuilder()
                                .setDescription("You don't have permission to use this command. required: `Manage Message`")
                                .setColor(0xFFFFFF)
                                .build()
                    )
                    .setEphemeral(true)
                    .queue();
        }
        List<Message> message = event.getChannel().getHistory().retrievePast(limit).complete();
        message.forEach(m -> m.delete().queue());
        event
                .replyEmbeds(
                        new EmbedBuilder()
                                .setDescription("Deleted " + limit + "Messages")
                                .setColor(0xFFFFFF)
                                .build()
                )
                .setEphemeral(true)
                .queue();
        event.getChannel()
                .sendMessageEmbeds(
                    new EmbedBuilder()
                            .setAuthor(event.getUser().getEffectiveName(),null,event.getUser().getAvatarUrl())
                            .setDescription("**Deleted" + limit + "Messages**")
                            .setColor(0xFFFFFF)
                            .build()
                )
                .queue(success -> {
                    success.delete().queueAfter(5, TimeUnit.SECONDS);
                });
    }
}
