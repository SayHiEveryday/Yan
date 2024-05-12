package cloud.salpy.yan.commands.sCmd;

import cloud.salpy.yan.Structure.format.sCmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;

import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Objects;

public class guildinfo extends sCmd {
    public guildinfo() {
        this.name = "guildinfo";
        this.description = "Currect Guild infomation";
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        if (!event.isFromGuild()) {
            event.reply("This command can only be ran in guild").setEphemeral(true).queue();
        }
        Guild guild = event.getGuild();
        assert guild != null;
        try {
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Basic infomation of " + guild.getName())
                    .setDescription(" "
                            + "Total Channel: " + guild.getChannels().size() + "\n"
                            + "-  Channel: " + guild.getTextChannels().size() + "\n"
                            + "- Voice Channel: " + guild.getVoiceChannels().size() + "\n"
                            + "Total Member: " + guild.getMemberCount() + "\n"
                            + "Created at: <t:" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(String.valueOf(guild.getTimeCreated())).getTime() / 1000 + ":F>\n"
                            + "Boost tier: " + guild.getBoostTier() + "\n"
                            + "Owner: " + Objects.requireNonNull(guild.getOwner()).getAsMention() + "\n"
                    )
                    .setColor(new Color((int) (Math.random() * 0x1000000)))
                    .setFooter("ID: " + guild.getId(),event.getUser().getAvatarUrl());
                    if (guild.getBanner() != null) {
                        embedBuilder.setImage(guild.getBanner().getUrl(2048));
                    }
                    if (guild.getIcon() != null) {
                        embedBuilder.setThumbnail(guild.getIconUrl());
                    }
            event.replyEmbeds(embedBuilder.build()).queue();
        } catch (Exception e) {
            event.reply(e.toString()).setEphemeral(true).queue();
        }
    }
}
