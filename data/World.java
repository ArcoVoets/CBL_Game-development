package data;

import interfaces.UpdateCallback;

/**
 * World class with a list of creatures in the world and the player creature.
 */
public class World implements interfaces.World {
    static UpdateCallback winChecker;
    Creature playerCreature;
    Creature[] worldCreatures;
    private int lastWorldCreatureActionIndex = -1;
    boolean isWorldCreatureActionBeingRun = false;

    /**
     * Constructor.
     * 
     * @param playerCreature Player creature to add to the world
     * @param worldCreatures Other creatures in the world
     * @param winChecker Callback to check if the player has won
     */
    public World(Creature playerCreature, Creature[] worldCreatures,
        UpdateCallback winChecker) {
        this.playerCreature = playerCreature;
        this.worldCreatures = worldCreatures;
        World.winChecker = winChecker;
        playerCreature.getActionsContainer().selectActionCreature();
        playerCreature.setWorld(this);
        for (Creature creature : worldCreatures) {
            creature.setWorld(this);
        }
    }

    /**
     * Deletes a creature from the world.
     * 
     * @param creature Creature to delete
     */
    public void deleteCreature(Creature creature) {
        Creature[] newWorldCreatures = new Creature[worldCreatures.length - 1];
        int i = 0;
        for (Creature worldCreature : worldCreatures) {
            if (worldCreature != creature) {
                newWorldCreatures[i] = worldCreature;
                i++;
            }
        }
        worldCreatures = newWorldCreatures;
    }

    @Override
    public Creature getPlayerCreature() {
        return playerCreature;
    }

    @Override
    public Creature[] getWorldCreatures() {
        return worldCreatures;
    }

    /**
     * Gets the next alive world creature that has not yet run an action this
     * round.
     * 
     * @return The next alive world creature that has not yet run an action this
     *         round, if all world creatures have run an action this round,
     *         returns null
     */
    public Creature getNextRunnableWorldCreature() {
        for (int i = lastWorldCreatureActionIndex
            + 1; i < worldCreatures.length; i++) {
            Creature creature = worldCreatures[i];
            if (!creature.isDead()) {
                lastWorldCreatureActionIndex = i;
                if (!hasNextRunnableWorldCreature()) {
                    lastWorldCreatureActionIndex = -1;
                }
                return creature;
            }
        }
        lastWorldCreatureActionIndex = -1;
        return null;
    }

    /**
     * Checks if there is a next alive world creature that has not yet run an
     * action this round.
     * 
     * @return True if there is a next alive world creature that has not yet
     *         run, else false
     */
    public boolean existsNextRunnableWorldCreature() {
        if (lastWorldCreatureActionIndex == -1) {
            return false;
        }
        return hasNextRunnableWorldCreature();
    }

    /**
     * Checks if there is a next alive world creature that has not yet run an
     * action this round.
     * 
     * @return True if there is a next alive world creature that has not yet
     *         run, else false
     */
    private boolean hasNextRunnableWorldCreature() {
        for (int i = lastWorldCreatureActionIndex
            + 1; i < worldCreatures.length; i++) {
            Creature creature = worldCreatures[i];
            if (!creature.isDead()) {
                return true;
            }
        }
        return false;
    }

    public boolean isWorldCreatureActionBeingRun() {
        return isWorldCreatureActionBeingRun;
    }

    public void setWorldCreatureActionBeingRun(
        boolean worldCreatureActionBeingRun) {
        this.isWorldCreatureActionBeingRun = worldCreatureActionBeingRun;
    }
}
