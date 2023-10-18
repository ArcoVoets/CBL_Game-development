abstract public class Action {

    public String name;
    public ActionCallback actionCallback;

    public Action(String name) {
        this.name = name;
    }

    public void setCallback(ActionCallback actionCallback) {
        this.actionCallback = actionCallback;
    }

    public void execute(Creature creature) {
        boolean success = runAction(creature);
        if (success) {
            creature.statsContainer.getProperty("energy").SubtractValue(1);
        }
        actionCallback.Callback();
    }

    abstract boolean runAction(Creature creature);
}
