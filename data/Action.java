package data;

import interfaces.*;

public abstract class Action implements interfaces.Action {
    public String name;
    public UpdateCallback actionCallback;
    Creature creature;

    public Action(String name, UpdateCallback actionCallback) {
        this.name = name;
        this.actionCallback = actionCallback;
    }

    void setCreature(Creature creature) {
        this.creature = creature;
    }

    /**
     * Executes the action.
     * 
     * @param creature The creature for which the action is executed
     */
    public void execute() {
        int energyCost = 1
            + creature.environment.calculateTemperatureDamage(creature)
            - creature.environment.calculateEnergyProduction(creature);
        if (creature.statsContainer.energy.getValue() < energyCost) {
            return;
        }
        boolean success = runAction();
        if (success) {
            creature.statsContainer.energy.subtractValue(energyCost);
            creature.environment.updateEnvironmentStats();
            actionCallback.Callback();
        }
    }

    abstract boolean runAction();
}
