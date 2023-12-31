package data;

import interfaces.UpdateCallback;

/**
 * Pair action.
 */
public class PairAction extends Action {
    static final int CODES_MIX_AMOUNT = 1;

    public PairAction(UpdateCallback actionCallback) {
        super("Pair", actionCallback);
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
        CodesContainer properties = creature.codesContainer;
        CodesContainer otherProperties = otherCreature.codesContainer;
        mixCodes(properties, otherProperties);
        return true;
    }

    /**
     * Mixes codes of two creatures.
     * 
     * @param codes Codes of the creature executing the action
     * @param otherCodes Codes of the other creature
     */
    void mixCodes(CodesContainer codes,
        CodesContainer otherCodes) {
        for (int i = 0; i < CODES_MIX_AMOUNT; i++) {
            int randomIndex = (int) (Math.random()
                * codes.size());
            Code property = codes.get(randomIndex);
            int randomOtherIndex = (int) (Math.random()
                * otherCodes.size());
            Code otherProperty = otherCodes.get(randomOtherIndex);
            codes.set(randomIndex, otherProperty);
            otherCodes.set(randomOtherIndex, property);
        }
    }
}
