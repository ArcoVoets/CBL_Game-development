package data;

public class Creature implements interfaces.Creature {
    CodesContainer codesContainer;
    StatsContainer statsContainer;
    Environment environment;
    Actions actionsContainer;
    boolean isDead = false;

    /**
     * Constructor.
     */
    public Creature(Actions actionsContainer,
        Environment environment) {
        this.actionsContainer = actionsContainer;
        actionsContainer.setActionsCreature(this);
        this.environment = environment;
        this.statsContainer = new StatsContainer();
        this.codesContainer = CodesContainer.randomCodesContainer(6);
    }

    @Override
    public CodesContainer getCodesContainer() {
        return codesContainer;
    }

    @Override
    public StatsContainer getStatsContainer() {
        return statsContainer;
    }

    @Override
    public Environment getEnvironment() {
        return environment;
    }

    @Override
    public Actions getActionsContainer() {
        return actionsContainer;
    }

    @Override
    public boolean isDead() {
        return isDead;
    }
}
