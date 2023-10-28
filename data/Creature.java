package data;

/**
 * Creature with codes, stats, environment, and actions.
 */
public class Creature implements interfaces.Creature {
    CodesContainer codesContainer;
    StatsContainer statsContainer;
    Environment environment;
    World world;
    Actions actionsContainer;
    boolean isDead = false;
    CreatureActionRunner decisionMaker;

    /**
     * Create a creature with all fields filled.
     * 
     * @param actionsContainer The actions container of the creature
     * @param environment The environment of the creature
     * @param decisionMaker The decision maker of the creature
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

    /**
     * Sets the world of the creature.
     * 
     * @param world The world in which the creature lives
     */
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

    public World getWorld() {
        return world;
    }
}
