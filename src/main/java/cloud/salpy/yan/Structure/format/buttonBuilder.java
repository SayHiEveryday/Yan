package cloud.salpy.yan.Structure.format;

import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;

public abstract class buttonBuilder {
    public String idOrUrl;
    public String label;
    public boolean disable = false;
    public ButtonStyle style = ButtonStyle.PRIMARY;
    public Emoji emoji = null;
    public abstract void execute(ButtonInteractionEvent event);
    public ItemComponent build() {
        return Button.of(this.style,this.idOrUrl,this.label,this.emoji).withDisabled(this.disable);
    }
}
