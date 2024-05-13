package cloud.salpy.yan.commands.pCmd;

import cloud.salpy.yan.Structure.format.PrefixCommandBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;

import java.awt.*;
import java.util.Objects;

public class pAvatar extends PrefixCommandBuilder {
    public pAvatar() {
        this.name = "avatar";
    }
    @Override
    public void execute(MessageReceivedEvent event, String[] args) {
        try {
            String userid;
            if (args.length <= 2) {
                userid = event.getAuthor().getId();
            } else {
                userid = args[1].replaceFirst("<@", "").replace(">", "");
            }
            User user = event.getJDA().getUserById(userid);

            assert user != null;
            String av = Objects.requireNonNull(user.getAvatar()).getUrl(2048);
            EmbedBuilder embed = new EmbedBuilder()
                    .setImage(av)
                    .setColor(new Color((int) (Math.random() * 0x1000000)));
            event.getMessage().replyEmbeds(embed.build()).queue();
        } catch (Exception e) {
            event.getMessage().reply(e.toString()).queue();
        }
    }
}
