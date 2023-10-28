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

    public int calculateEnergyCost() {
        return 1
            + creature.environment.calculateTemperatureDamage(creature)
            - creature.environment.calculateEnergyProduction(creature);
    }

    public String getName() {
        return name;
    }

    /**
     * Executes the action.
     */
    public void execute() {
        int energyCost = calculateEnergyCost();
        boolean success = runAction();
        if (success) {
            creature.statsContainer.energy.subtractValue(energyCost);
            creature.environment.updateEnvironmentStats();
            creature.isDead = creature.statsContainer.energy.getValue() <= 0;
            actionCallback.callback();
        }
    }

    abstract boolean runAction();
}
