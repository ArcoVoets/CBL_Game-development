package data;

public class Creature implements interfaces.Creature {
    CodesContainer codesContainer;
    StatsContainer statsContainer;
    Environment environment;
    World world;
    Actions actionsContainer;
    boolean isDead = false;
    CreatureActionRunner decisionMaker;

    /**
     * Constructor.
     */
    public Creature(Actions actionsContainer,
        Environment environment, CreatureActionRunner decisionMaker) {
        this.actionsContainer = actionsContainer;
        actionsContainer.setActionsCreature(this);
        this.environment = environment;
        this.statsContainer = new StatsContainer();
        this.codesContainer = CodesContainer.randomCodesContainer(6);
        this.decisionMaker = decisionMaker;
        if (decisionMaker != null) {
            decisionMaker.setActions(actionsContainer);
        }
    }

    public Creature(Actions actionsContainer, Environment environment) {
        this(actionsContainer, environment, null);
    }

    void setWorld(World world) {
        this.world = world;
        if (decisionMaker != null) {
            decisionMaker.setWorld(world);
        }
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

    public void runAction() {
        decisionMaker.chooseAction();
    }
}
