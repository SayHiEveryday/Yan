package cloud.salpy.yan.Structure.format;

import net.dv8tion.jda.api.interactions.components.selections.EntitySelectMenu;
import net.dv8tion.jda.api.interactions.components.selections.SelectOption;

import java.util.List;

public abstract class EntitydropdownBuilder {
    public String id;
    public EntitySelectMenu.SelectTarget target;
    public String Placeholder;
    public int MaxValue;
    public int MinValue;
    public EntitySelectMenu build() {
        return EntitySelectMenu.create(this.id,this.target)
                .setPlaceholder(this.Placeholder)
                .setId(this.id)
                .setEntityTypes(this.target)
                .setMaxValues(this.MaxValue)
                .setMaxValues(this.MinValue)
                .build();
    }
    public abstract void execute();
}
