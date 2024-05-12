package cloud.salpy.yan.Structure.format;


import net.dv8tion.jda.api.events.interaction.component.StringSelectInteractionEvent;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;
import net.dv8tion.jda.api.interactions.components.selections.StringSelectMenu;

import java.util.List;

public abstract class StringdropdownBuilder {
    public String id;
    public boolean disable = false;
    public String Placeholder;
    public List<SelectOption> options;
    public int MaxValue;
    public int MinValue;
    public abstract void execute(StringSelectInteractionEvent event);
    public StringSelectMenu build() {
        return StringSelectMenu.create(this.id)
                .setDisabled(this.disable)
                .setId(this.id)
                .setPlaceholder(this.Placeholder)
                .addOptions(this.options)
                .setMaxValues(this.MaxValue)
                .setMaxValues(this.MinValue)
                .build();
    }
}
