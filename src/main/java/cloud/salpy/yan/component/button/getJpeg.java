package cloud.salpy.yan.component.button;

import cloud.salpy.yan.Structure.format.buttonBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

public class getJpeg extends buttonBuilder {
    private String avatarurl;
    public getJpeg() {
        this.idOrUrl = "button:getjpeg";
        this.label = "PNG format";
        this.style = ButtonStyle.SUCCESS;
    }
    public getJpeg setString(String avurl) {
        this.avatarurl = avurl;
        return this;
    }
    @Override
    public void execute(ButtonInteractionEvent event) {
        event.reply(this.avatarurl.replace("png","jpeg")).setEphemeral(true).queue();
    }
}
