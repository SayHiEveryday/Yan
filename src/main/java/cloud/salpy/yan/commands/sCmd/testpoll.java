package cloud.salpy.yan.commands.sCmd;

import cloud.salpy.yan.Structure.format.SlashCommandBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.messages.MessagePollBuilder;

public class testpoll extends SlashCommandBuilder {
    public testpoll() {
        this.name = "testpoll";
        this.description = "cloud.salpy.yan.pri.Constant.testpoll";
    }
    @Override
    public void execute(SlashCommandInteractionEvent event) {
        MessagePollBuilder messagePollBuilder = new MessagePollBuilder("this is a test").addAnswer("1").addAnswer("2");
        event.replyPoll(messagePollBuilder.build()).queue();
    }
}
