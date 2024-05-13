package cloud.salpy.yan.component.button;

import cloud.salpy.yan.Structure.format.buttonBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

public class viewrole extends buttonBuilder {
    public viewrole() {
        this.label = "View role";
        this.style = ButtonStyle.PRIMARY;
        this.idOrUrl = "button:vrole";
    }
    @Override
    public void execute(ButtonInteractionEvent event) {

    }
}
