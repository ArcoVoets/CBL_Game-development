package interfaces;

/**
 * An action that can be executed.
 */
public interface Action {
    public void execute();

    public String getName();

    public int calculateEnergyCost();
}
