package data;

import interfaces.UpdateCallback;

/**
 * Action that can be extended with custom behavior. The action is executed by
 * buttons or creatures.
 */
public abstract class Action implements interfaces.Action {
    public String name;
    public UpdateCallback actionCallback;
    Creature creature;

    /**
     * Constructor.
     * 
     * @param name Name of the action
     * @param actionCallback Callback to run after the action is executed
     */
    public Action(String name, UpdateCallback actionCallback) {
        this.name = name;
        this.actionCallback = actionCallback;
    }

    void setCreature(Creature creature) {
        this.creature = creature;
    }

    /**
     * Calculates the energy cost of the action.
     * 
     * @return The energy cost of the action
     */
    public int calculateEnergyCost() {
        return 1
            + creature.environment.calculateTemperatureDamage(creature)
            - creature.environment.calculateEnergyProduction(creature);
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
