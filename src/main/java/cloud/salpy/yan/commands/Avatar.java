package cloud.salpy.yan.commands;

import cloud.salpy.yan.core.Client;
import cloud.salpy.yan.core.format.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import org.jetbrains.annotations.NotNull;

public class Avatar extends Command {
    public Avatar(Client client) {
        super(client);
        this
                .setName("avatar")
                .setDescription("Get Mentioned user's avatar")
                .addOption(
                        new OptionData(OptionType.USER,"member","Get Member's avatar").setRequired(false)
                );
    }

    @Override
    public void executePrefix(@NotNull MessageReceivedEvent event, String[] args) {
        String userid;
        if (args[1].isEmpty()) {
            userid = event.getAuthor().getId();
        }
        else
            if (args[1].matches("^<@\\\\d+>$")) {
            userid = args[1].replace("<@","").replace(">","");
        } else {
            userid = args[1];
        }

        User usr = event.getJDA().retrieveUserById(userid).complete();
        event.getMessage().reply(new MessageCreateBuilder().setEmbeds(
                new EmbedBuilder()
                        .setTitle("Avatar of " + usr.getEffectiveName())
                        .setColor(0xFFFFFF)
                        .setImage(usr.getEffectiveAvatar().getUrl(2048))
                        .build()
        ).build()).queue();
    }

    @Override
    public void executeSlash(@NotNull SlashCommandInteractionEvent event) {
        User usr = event.getOption("member", event.getUser(), OptionMapping::getAsUser);
        event.reply(new MessageCreateBuilder().setEmbeds(
                new EmbedBuilder()
                        .setTitle("Avatar of " + usr.getEffectiveName())
                        .setColor(0xFFFFFF)
                        .setImage(usr.getEffectiveAvatar().getUrl(2048))
                        .build()
        ).build()).queue();
    }
}
