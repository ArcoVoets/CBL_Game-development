package interfaces;

/**
 * Actions interface with a list of actions and a selected creature to do the
 * action on.
 */
public interface Actions {
    public Action[] getActions();

    public void selectCreature();

    public boolean isSelectedCreature();
}
