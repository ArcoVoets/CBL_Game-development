abstract class Action {

    public String name;
    public ActionCallback actionCallback;

    public Action(String name) {
        this.name = name;
    }

    public void setCallback(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }

    /**
     * Executes the action.
     * 
     * @param creature The creature for which the action is executed
     */
    public void execute(Creature creature) {
        int energyCost = 1
            + creature.environment.calculateTemperatureDamage(creature)
            - creature.environment.calculateEnergyProduction(creature);
        if (creature.statsContainer.energy.getValue() < energyCost) {
            return;
        }
        boolean success = runAction(creature);
        if (success) {
            creature.statsContainer.energy.subtractValue(energyCost);
        }
        actionCallback.Callback();
    }

    abstract boolean runAction(Creature creature);
}
