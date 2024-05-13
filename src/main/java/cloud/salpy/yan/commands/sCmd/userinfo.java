package cloud.salpy.yan.commands.sCmd;

import cloud.salpy.yan.Structure.format.SlashCommandBuilder;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.Objects;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

public class userinfo extends SlashCommandBuilder {
    public userinfo() {
        this.name = "userinfo";
        this.description = "Get mentioned user infomation";
        this.addOption(
                new OptionData(OptionType.USER,"member","Member you want to getinfo").setRequired(false)
        );
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping user = event.getOption("member");
        User usr = (user == null) ? event.getUser() : user.getAsUser();
        User.Profile userprofile = usr.retrieveProfile().complete();
        Member member = (user == null) ? event.getMember() : user.getAsMember();
        assert member != null;
        String roleMentions = member.getRoles().stream()
                .map(role -> "<@&" + role.getId() + ">")
                .limit(960)
                .collect(Collectors.joining(", "));
        int remainingRoles = member.getRoles().size() - 960;
        if (remainingRoles > 0) {
            roleMentions += String.format(" and %d more...", remainingRoles);
        }
        try {
            EmbedBuilder embedBuilder = new EmbedBuilder()
                    .setTitle("Basic infomation of " + usr.getGlobalName())
                    .setDescription(" "
                            + "Is bot: " + usr.isBot() + "\n"
                            + "Username: " + usr.getName() + "\n"
                            + "Registered: <t:" + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").parse(String.valueOf(usr.getTimeCreated())).getTime() / 1000 +":F>\n"
                    )
                    .addField("Role[" + member.getRoles().size() + "]", roleMentions, true)
                    .setThumbnail(usr.getEffectiveAvatarUrl())
                    .setColor((userprofile.getAccentColor() == null) ? new Color((int) (Math.random() * 0x1000000)) : new Color(userprofile.getAccentColorRaw()))
                    .setFooter("ID: " + usr.getId());
            if (userprofile.getBannerUrl() != null) {
                embedBuilder.setImage(Objects.requireNonNull(userprofile.getBanner()).getUrl(2048));
            }
            event.replyEmbeds(embedBuilder.build()).queue();
        } catch (Exception e) {
            event.reply(e.toString()).setEphemeral(true).queue();
        }
    }
}
