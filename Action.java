public class Action {
    interface ActionExecuter {
        void Execute(Creature creature);
    }

    int index;
    public String name;
    public ActionExecuter actionExecuter;
    public ActionCallback actionCallback;

    public Action(String name, ActionExecuter actionExecuter) {
        this.name = name;
        this.actionExecuter = actionExecuter;
    }

    public void setCallback(int index, ActionCallback actionCallback) {
        this.index = index;
        this.actionCallback = actionCallback;
    }

    public void Execute(Creature creature) {
        actionExecuter.Execute(creature);
        actionCallback.Callback();
    }
}
