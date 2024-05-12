package cloud.salpy.yan.component.button;

import cloud.salpy.yan.Structure.format.buttonBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

public class pong extends buttonBuilder {
    public pong() {
        this.label = "getLatency";
        this.idOrUrl = "pongbutton";
        this.style = ButtonStyle.PRIMARY;
    }
    @Override
    public void execute(ButtonInteractionEvent event) {
        String ping = String.valueOf(Math.round(event.getJDA().getGatewayPing()));
        event.getInteraction().reply(ping + "ms").setEphemeral(true).queue();
    }
}
