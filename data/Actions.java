package data;

public class Actions implements interfaces.Actions {
    public static Creature selectedCreature;
    public static Creature actionCreature;

    Creature creature;

    Action[] actions;

    public Actions(Action[] actions) {
        this.actions = actions;
    }

    void setActionsCreature(Creature creature) {
        this.creature = creature;
        for (Action action : actions) {
            action.setCreature(creature);
        }
    }

    public <T extends Action> Action getAction(Class<T> actionClass) {
        for (Action action : actions) {
            if (action.getClass().equals(actionClass)) {
                return action;
            }
        }
        return null;
    }

    @Override
    public Action[] getActions() {
        return actions;
    }

    @Override
    public void selectCreature() {
        selectedCreature = this.creature;
    }

    @Override
    public boolean isSelectedCreature() {
        return selectedCreature == this.creature;
    }

    public void selectActionCreature() {
        actionCreature = creature;
    }

    public boolean isActionCreature() {
        return actionCreature == this.creature;
    }
}
