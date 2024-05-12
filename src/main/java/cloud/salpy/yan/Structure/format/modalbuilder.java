package cloud.salpy.yan.Structure.format;

import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.interactions.components.ItemComponent;
import net.dv8tion.jda.api.interactions.modals.Modal;

import java.util.List;

public abstract class modalbuilder {
    public String id;
    public String title;
    public List<ItemComponent> componentList;
    public abstract void execute(ModalInteractionEvent event);
    public Modal build() {
        return Modal.create(this.id,this.title)
                .addActionRow(this.componentList)
                .setId(this.id)
                .build();
    }
}
