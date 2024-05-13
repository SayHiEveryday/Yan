package cloud.salpy.yan.commands.sCmd;

import cloud.salpy.yan.Structure.format.SlashCommandBuilder;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionMapping;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;

import java.awt.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class purge extends SlashCommandBuilder {
    public purge() {
        this.name = "purge";
        this.description = "Property.purge.description";
        this.addOption(
                new OptionData(OptionType.INTEGER,"amount","amount you what to purge").setRequired(false),
                new OptionData(OptionType.BOOLEAN,"private","The result will be visible to you").setRequired(false)

        );
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        OptionMapping amountdata = event.getOption("amount");
        OptionMapping privatedata = event.getOption("private");
        boolean pri = privatedata == null || privatedata.getAsBoolean();
        int amount = (amountdata == null) ? 100 : amountdata.getAsInt();
        List<Message> messages = event.getChannel().getHistory().retrievePast(amount).complete();
        event.getChannel().purgeMessages(messages);
        MessageCreateBuilder messageBuilder = new MessageCreateBuilder()
                .setEmbeds(
                        new EmbedBuilder()
                                .setDescription("Deleted " + Integer.toString(amount) + " Message")
                                .setColor(new Color((int) (Math.random() * 0x1000000)))
                                .build()
                );
        event.reply(messageBuilder.build()).setEphemeral(pri).queue(message -> {
            message.deleteOriginal().queueAfter(5, TimeUnit.SECONDS);
        });

    }
}
