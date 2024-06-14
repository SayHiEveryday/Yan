package cloud.salpy.yan.core.format;

import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.components.buttons.Button;


public class ButtonExecutable {
    private ButtonExecutableInter _runable;
    private String _customid;
    private Button _button;

    @FunctionalInterface
    public interface ButtonExecutableInter {
        void run(ButtonInteractionEvent event);
    }

    public ButtonExecutableInter getRunable() {
        return _runable;
    }

    public String getCustomid() {
        return _customid;
    }

    public Button getButton() {
        return _button;
    }

    public ButtonExecutable setButton(Button button) {
        this._button = button;
        this._customid = button.getId();
        return this;
    }
    public ButtonExecutable setRunable(ButtonExecutableInter run) {
        this._runable = run;
        return this;
    }
}
