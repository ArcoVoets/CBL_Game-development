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
        runAction(creature);
        actionCallback.Callback();
    }

    abstract void runAction(Creature creature);
}
