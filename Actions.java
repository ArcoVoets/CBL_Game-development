class Actions {
    public static Creature selectedCreature;

    Action[] actions;
    ActionCallback actionCallback;

    public Actions(Action[] actions, ActionCallback actionCallback) {
        this.actions = actions;
        for (Action action : actions) {
            action.setCallback(actionCallback);
        }
    }
}

interface ActionCallback {
    void Callback();
}
