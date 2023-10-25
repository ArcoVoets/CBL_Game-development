package interfaces;

import data.Actions;

public interface Creature {
    public ProgressBarDataProvider getCodesContainer();

    public ProgressBarDataProvider getStatsContainer();

    public ProgressBarDataProvider getEnvironment();

    public Actions getActionsContainer();

    public boolean isDead();
}
