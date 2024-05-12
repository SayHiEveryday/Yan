package cloud.salpy.yan.commands.sCmd;

import cloud.salpy.yan.Structure.format.sCmd;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;

import java.awt.*;
import java.util.Objects;

public class embedbuilder extends sCmd {
    public embedbuilder() {
        this.name = "embedbuilder";
        this.description = "Property.embedbuilder";
        this.addOption(
                new OptionData(OptionType.STRING,"title","title of the embed").setRequired(true),
                new OptionData(OptionType.STRING,"description","Embed description").setRequired(true),
                new OptionData(OptionType.INTEGER,"color","Embed Color INT").setRequired(false),
                new OptionData(OptionType.ATTACHMENT, "image","Embed Image").setRequired(false),
                new OptionData(OptionType.STRING, "footer","Embed footer").setRequired(false),
                new OptionData(OptionType.STRING, "footerurl","Embed footer url").setRequired(false)
        );
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        String title = Objects.requireNonNull(event.getOption("title")).getAsString();
        String des = Objects.requireNonNull(event.getOption("description")).getAsString();

        EmbedBuilder embedBuilder = new EmbedBuilder()
                .setTitle(title)
                .setDescription(des);
        if (event.getOption("image") != null) {
            String image = event.getOption("image").getAsAttachment().getUrl();
            embedBuilder.setImage(image);
        }
        if (event.getOption("color") != null) {
            int color = event.getOption("color").getAsInt();
            embedBuilder.setColor(new Color((int) color));
        }
        if (event.getOption("footer") != null) {
            String footer = event.getOption("footer").getAsString();
            embedBuilder.setFooter(footer);
        }

        event.replyEmbeds(embedBuilder.build()).queue();
    }
}
