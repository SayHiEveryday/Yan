package cloud.salpy.yan.commands.context;

import cloud.salpy.yan.Structure.format.UserContextBuilder;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.MessageContextInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.Command;

import java.awt.*;
import java.util.Objects;

public class getav extends UserContextBuilder {
    public getav() {
        this.name = "Get avatar";
    }
    @Override
    public void execute(UserContextInteractionEvent userevent) {

       String av = Objects.requireNonNull(userevent.getTarget().getAvatar()).getUrl(2048);
       EmbedBuilder embed = new EmbedBuilder()
               .setTitle(userevent.getTarget().getEffectiveName() + "'s avatar")
               .setImage(av)
               .setColor(new Color((int) (Math.random() * 0x1000000)));
       userevent.replyEmbeds(embed.build()).queue();
    }
}
