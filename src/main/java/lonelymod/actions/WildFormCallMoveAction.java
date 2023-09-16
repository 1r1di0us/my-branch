package lonelymod.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import lonelymod.companions.AbstractCompanion;

public class WildFormCallMoveAction extends AbstractGameAction {

    private final byte move;
    private final AbstractCompanion currCompanion;
    //only reason this exists is to delay calling the move.
    //nothing special happens here.
    public WildFormCallMoveAction(byte move, AbstractCompanion currCompanion) {
        this.move = move;
        this.currCompanion = currCompanion;
    }

    public void update() {
        switch (move) {
            case 1:
                currCompanion.callMainMove(AbstractCompanion.ATTACK, false, true, true);
                break;
            case 2:
                currCompanion.callMainMove(AbstractCompanion.PROTECT, false, true, true);
                break;
            case 3:
                currCompanion.callMainMove(AbstractCompanion.SPECIAL, false, true, true);
                break;
        }
        this.isDone = true;
    }
}
