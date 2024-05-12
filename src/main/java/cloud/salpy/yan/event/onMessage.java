package cloud.salpy.yan.event;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.Objects;

public class onMessage extends ListenerAdapter {
    @Override
    public void onMessageReceived(MessageReceivedEvent event) {
        Message message = event.getMessage();
        if (event.getAuthor().isBot()) {
            return;
        }
        switch (message.getContentRaw()) {
            case "ควย":
                message.reply("พูดหยาบทำไมคะ เค้า โกรธเเล้ว :pleading_face::pleading_face:").queue();
                break;
            case "หี":
                message.reply("อยากดูหรือคะ").queue();
                break;
            case "<@1155418043678212107>":
                message.reply("ปิงหาพ่อมึงหรอ").queue();
                break;
            case "555":
                message.reply("ขำไรอะ ขำด้วยคนสิ").queue();
                break;
            case "เงี่ยน":
                message.reply("https://pornhub.com").queue();
                break;
        }
    }
}
