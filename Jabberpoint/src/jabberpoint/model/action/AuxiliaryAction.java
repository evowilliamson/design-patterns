package jabberpoint.model.action;

import java.awt.*;

/**
 * Class that implements auxiliary actions
 * @author Ivo Willemsen
 */
public class AuxiliaryAction implements Action {

    private AuxiliaryAction.Action action;

    private AuxiliaryAction(){}
    protected AuxiliaryAction(AuxiliaryAction.Action action) {
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
