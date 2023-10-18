abstract public class Action {

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
        boolean success = runAction(creature);
        if (success) {
            creature.statsContainer.getProperty("energy").subtractValue(
                creature.environment.calculateDamage(creature));
            creature.statsContainer.getProperty("luminosity").addValue(
                creature.environment.calculateEnergyProduction(creature));
        }
        actionCallback.Callback();
    }

    abstract boolean runAction(Creature creature);
}
