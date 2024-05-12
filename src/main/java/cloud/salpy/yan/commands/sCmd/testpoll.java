package cloud.salpy.yan.commands.sCmd;

import cloud.salpy.yan.Structure.format.sCmd;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.utils.messages.MessagePollBuilder;

import java.util.Objects;

public class testpoll extends sCmd {
    public testpoll() {
        this.name = "testpoll";
        this.description = "cloud.salpy.yan.Constant.testpoll";
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        MessagePollBuilder messagePollBuilder = new MessagePollBuilder("this is a test").addAnswer("1").addAnswer("2");
        event.replyPoll(messagePollBuilder.build()).queue();
    }
}
