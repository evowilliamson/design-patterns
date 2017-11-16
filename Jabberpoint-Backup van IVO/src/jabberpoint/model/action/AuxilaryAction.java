package jabberpoint.model.action;

import java.awt.*;

public class AuxilaryAction implements Action {

    private AuxilaryAction.Action action;

    public AuxilaryAction(AuxilaryAction.Action action) {
        this.action = action;
    }

    @Override
    public void execute() {

        if (this.action == Action.BEEP) {
            Toolkit.getDefaultToolkit().beep();
        } else if (this.action == Action.FLASH) {
            // flash
        }


    }

    public enum Action {
        BEEP, FLASH
    }

}
