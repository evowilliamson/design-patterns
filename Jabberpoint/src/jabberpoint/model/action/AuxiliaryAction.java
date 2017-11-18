package jabberpoint.model.action;

import java.awt.*;

/**
 * Class that implements auxiliary actions
 * @author Ivo Willemsen
 */
public class AuxiliaryAction implements Action {

    private AuxiliaryAction.AuxAction action;

    private AuxiliaryAction(){}
    protected AuxiliaryAction(AuxiliaryAction.AuxAction auxAction) {
        this.action = auxAction;
    }

    @Override
    public void execute() {

        if (this.action == AuxAction.BEEP) {
            Toolkit.getDefaultToolkit().beep();
        } else if (this.action == AuxAction.FLASH) {
            // flash
        } else if (this.action == AuxAction.EXIT){
        	System.exit(0);
        }

    }

    public enum AuxAction {
        BEEP, FLASH, EXIT
    }

}
