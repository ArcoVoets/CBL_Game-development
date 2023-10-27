package data;

import interfaces.*;

public class EatAction extends Action {

    public EatAction(UpdateCallback actionCallback) {
        super("Eat", actionCallback);
    }

    @Override
    public boolean runAction() {
        Creature otherCreature = Actions.selectedCreature;
        if (otherCreature == null || otherCreature.isDead) {
            return false;
        }
        if (creature == otherCreature) {
            return false;
        }
        int creatureCodesScore = creature.codesContainer.calculateCodesScore();
        int otherCreatureCodesScore = otherCreature.codesContainer
            .calculateCodesScore();
        if (creatureCodesScore > otherCreatureCodesScore) {
            creature.statsContainer.energy
                .addValue(otherCreature.statsContainer.energy.getValue() / 2);
            otherCreature.isDead = true;
            World.winChecker.Callback();
        } else {
            creature.statsContainer.energy.subtractValue(1);
        }
        return true;
    }
}
