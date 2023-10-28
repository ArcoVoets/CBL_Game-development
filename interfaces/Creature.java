package interfaces;

import data.Actions;

/**
 * Creature interface with codes, stats, environment, and actions.
 */
public interface Creature {
    public ProgressBarDataProvider getCodesContainer();

    public ProgressBarDataProvider getStatsContainer();

    public ProgressBarDataProvider getEnvironment();

    public Actions getActionsContainer();

    public boolean isDead();
}
