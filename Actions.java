class Actions {
    Action[] actions;
    ActionCallback actionCallback;

    public Actions(Action[] actions, ActionCallback actionCallback) {
        this.actions = actions;
        for (int i = 0; i < actions.length; i++) {
            actions[i].setCallback(i, actionCallback);
        }
    }
}

interface ActionCallback {
    void Callback();
}
