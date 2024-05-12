package cloud.salpy.yan.Structure.manager;

import cloud.salpy.yan.Structure.format.buttonBuilder;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ButtonManager extends ListenerAdapter {
    private final List<buttonBuilder> buttonBuilderList = new ArrayList<buttonBuilder>();

    @Override
    public void onButtonInteraction(@NotNull ButtonInteractionEvent event) {
        for (buttonBuilder button : buttonBuilderList) {
            if (event.getComponentId().equals(button.idOrUrl)) {
                button.execute(event);
            }
        }
    }

    public void add(buttonBuilder... buttons) {
        Collections.addAll(buttonBuilderList, buttons);
    }
}
